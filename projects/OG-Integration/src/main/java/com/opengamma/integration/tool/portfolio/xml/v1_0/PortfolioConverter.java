package com.opengamma.integration.tool.portfolio.xml.v1_0;

import static com.opengamma.integration.tool.portfolio.xml.v1_0.SwapLeg.Direction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.core.security.Security;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventionFactory;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.convention.frequency.SimpleFrequencyFactory;
import com.opengamma.financial.currency.CurrencyPair;
import com.opengamma.financial.security.option.AmericanExerciseType;
import com.opengamma.financial.security.option.EuropeanExerciseType;
import com.opengamma.financial.security.option.ExerciseType;
import com.opengamma.financial.security.option.FXOptionSecurity;
import com.opengamma.financial.security.option.NonDeliverableFXOptionSecurity;
import com.opengamma.financial.security.swap.FixedInterestRateLeg;
import com.opengamma.financial.security.swap.FloatingInterestRateLeg;
import com.opengamma.financial.security.swap.FloatingRateType;
import com.opengamma.financial.security.swap.InterestRateNotional;
import com.opengamma.financial.security.swap.Notional;
import com.opengamma.financial.security.swap.SwapSecurity;
import com.opengamma.id.ExternalId;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;
import com.opengamma.util.tuple.ObjectsPair;

public class PortfolioConverter {

  private final Portfolio _portfolio;


  public PortfolioConverter(Portfolio portfolio) {
    _portfolio = portfolio;
  }

  /**
   * Get the set of manageable positions for this portfolio. Note that this may add in positions
   * which were not in the original xml file e.g. where a set of trades were specified but no
   * positions, each trade will be added to a new position.
   */
  public Iterable<ObjectsPair<ManageablePosition, ManageableSecurity[]>> getPositions() {

    List<ObjectsPair<ManageablePosition, ManageableSecurity[]>> managedPositions = Lists.newArrayList();

    for (Position position : nullCheck(_portfolio.getPositions())) {

      List<Trade> trades = position.getTrades();

      for (Trade trade : trades) {

        ManageableSecurity security = extractSecurityFromTrade(trade, trades.size());
        managedPositions.add(createPositionSecurityPair(position, security));
      }
    }

    for (Trade trade : nullCheck(_portfolio.getTrades())) {

      // TODO we probably want logic to allow for the aggregation of trades into positions but for now we'll create a position per trade
      ManageableSecurity security = extractSecurityFromTrade(trade, 1);
      managedPositions.add(createPositionSecurityPair(trade, security));
    }

    return managedPositions;
  }

  private ManageableSecurity extractSecurityFromTrade(Trade trade, int tradesSize) {

    // If this is the case, then we should only have one trade in the original position
    if (trade instanceof SwapTrade) {

      if (tradesSize > 1) {
        throw new OpenGammaRuntimeException("Only one swap trade per position is allowed");
      }

      return handleSwapTrade((SwapTrade) trade);

    } else if (trade instanceof FxOptionTrade) {

      if (tradesSize > 1) {
        throw new OpenGammaRuntimeException("Only one fx option trade per position is allowed");
      }

      return handleFxOptionTrade((FxOptionTrade) trade);

    } else {
      throw new OpenGammaRuntimeException("Unable to handle trade with type [" + trade.getClass().getName() +
                                              "] - [" + trade + "]");
    }
  }

  private <T> Iterable<T> nullCheck(Iterable<T> collection) {

    return collection == null ? ImmutableList.<T>of() : collection;
  }

  private ManageableSecurity handleFxOptionTrade(FxOptionTrade fxOptionTrade) {


    CurrencyPair cp = CurrencyPair.parse(fxOptionTrade.getCurrencyPair());

    Currency optionCurrency = Currency.of(fxOptionTrade.getOptionCurrency());

    if (optionCurrency.equals(cp.getBase()) || optionCurrency.equals(cp.getCounter())) {

      Currency callCurrency;
      Currency putCurrency;
      BigDecimal callAmount;
      BigDecimal putAmount;


      BigDecimal notional = fxOptionTrade.getNotional();
      BigDecimal strike = fxOptionTrade.getStrike();
      Expiry expiry = new Expiry(fxOptionTrade.getExpiryDate().atStartOfDay(ZoneOffset.UTC));

      if (fxOptionTrade.getCallPut() == FxOptionTrade.CallPut.Call) {
        callCurrency = optionCurrency;

        // Get the other currency in the pair
        putCurrency = cp.getCounter().equals(optionCurrency) ? cp.getBase() : cp.getCounter();

        callAmount = notional;

        // The ordering of the currency pair indicates the structure of the strike price.
        // We therefore use this to determine whether we multiply or divide by the strike
        putAmount =  cp.getBase().equals(optionCurrency) ?
            notional.multiply(strike) :
            notional.divide(strike);

      } else {
        callCurrency = cp.getCounter().equals(optionCurrency) ? cp.getBase() : cp.getCounter();
        putCurrency = optionCurrency;
        callAmount = cp.getBase().equals(optionCurrency) ?
            notional.multiply(strike) :
            notional.divide(strike);

        putAmount = notional;
      }

      ZonedDateTime settlementDate = fxOptionTrade.getSettlementDate().atStartOfDay(ZoneOffset.UTC);
      boolean isLong = fxOptionTrade.getBuySell() == FxOptionTrade.BuySell.Buy;
      ExerciseType exerciseType = fxOptionTrade.getExerciseType() == FxOptionTrade.ExerciseType.American ?
          new AmericanExerciseType() : new EuropeanExerciseType();

      return fxOptionTrade.getSettlementType() == FxOptionTrade.SettlementType.Physical ?
          new FXOptionSecurity(putCurrency, callCurrency, putAmount.doubleValue(), callAmount.doubleValue(),
                               expiry, settlementDate, isLong, exerciseType) :
          new NonDeliverableFXOptionSecurity(putCurrency, callCurrency, putAmount.doubleValue(), callAmount.doubleValue(),
                               expiry, settlementDate, isLong, exerciseType,
                               fxOptionTrade.getSettlementCurrency().equals(callCurrency.getCode()));

    } else {
      throw new OpenGammaRuntimeException("Option currency: [" + optionCurrency +
                                                "] does not match either of the currencies in the currency pair: [" + cp +
                                                "]");
    }
  }

  private ManageableSecurity handleSwapTrade(SwapTrade swapTrade) {

    FixedLeg fixedLeg = swapTrade.getFixedLeg();
    FloatingLeg floatingLeg = swapTrade.getFloatingLeg();

    com.opengamma.financial.security.swap.SwapLeg payLeg = fixedLeg.getDirection() == Direction.Pay ?
        convertFixedLeg(fixedLeg) : convertFloatingLeg(floatingLeg);
    com.opengamma.financial.security.swap.SwapLeg receiveLeg = fixedLeg.getDirection() == Direction.Receive ?
        convertFixedLeg(fixedLeg) : convertFloatingLeg(floatingLeg);

    if (payLeg == receiveLeg) {
      throw new OpenGammaRuntimeException("One leg should be Pay and one Receive");
    }

    return new SwapSecurity(convertLocalDate(swapTrade),
                                             swapTrade.getEffectiveDate().atStartOfDay(ZoneOffset.UTC),
                                             swapTrade.getMaturityDate().atStartOfDay(ZoneOffset.UTC),
                                             swapTrade.getCounterparty().getExternalId().getId(),
                                             payLeg,
                                             receiveLeg);
  }

  private ZonedDateTime convertLocalDate(SwapTrade swapTrade) {
    return swapTrade.getTradeDate().atStartOfDay(ZoneOffset.UTC);
  }

  private ObjectsPair<ManageablePosition, ManageableSecurity[]> createPositionSecurityPair(Position position,
                                                                                           ManageableSecurity security) {
    return new ObjectsPair<>(convertPosition(position, security), new ManageableSecurity[]{security});
  }

  private ObjectsPair<ManageablePosition, ManageableSecurity[]> createPositionSecurityPair(Trade trade,
                                                                                           ManageableSecurity security) {
    return new ObjectsPair<>(convertTradeToPosition(trade, security), new ManageableSecurity[]{security});
  }

  private ManageablePosition convertTradeToPosition(Trade trade, ManageableSecurity security) {
    ManageablePosition manageablePosition = new ManageablePosition(BigDecimal.ONE, security.getExternalIdBundle());
    manageablePosition.addTrade(convertTrade(trade, security));
    return manageablePosition;
  }

  private ManageablePosition convertPosition(Position position, Security security) {

    ManageablePosition manageablePosition = new ManageablePosition(position.getQuantity(), security.getExternalIdBundle());

    List<Trade> trades = position.getTrades();
    for (Trade trade : trades) {
      manageablePosition.addTrade(convertTrade(trade, security));
    }
    return manageablePosition;
  }

  private ManageableTrade convertTrade(Trade trade, Security security) {

    // Would anything other than 1 for quantity make sense here (for OTC trades)?
    ManageableTrade manageableTrade = new ManageableTrade(BigDecimal.ONE,
                                                          security.getExternalIdBundle(),
                                                          trade.getTradeDate(),
                                                          null,
                                                          convertIdWrapper(trade.getCounterparty()));

    manageableTrade.setProviderId(convertIdWrapper(trade.getExternalSystemId()));

    BigDecimal premium = trade.getPremium();
    if (premium != null) {
      manageableTrade.setPremium(premium.doubleValue());
      manageableTrade.setPremiumCurrency(Currency.of(trade.getPremiumCurrency()));
    }
    return manageableTrade;
  }

  private ExternalId convertIdWrapper(IdWrapper counterparty) {

    ExtId extId = counterparty.getExternalId();
    return convertExtId(extId);
  }

  private ExternalId convertExtId(ExtId extId) {
    return ExternalId.of(extId.getScheme(), extId.getId());
  }

  private com.opengamma.financial.security.swap.SwapLeg convertFloatingLeg(FloatingLeg floatingLeg) {

    Notional notional = extractNotional(floatingLeg);

    ExternalId region = extractRegion(floatingLeg);
    DayCount dayCount = DayCountFactory.INSTANCE.getDayCount(floatingLeg.getDayCount());
    Frequency frequency = SimpleFrequencyFactory.INSTANCE.getFrequency(floatingLeg.getFrequency());
    BusinessDayConvention businessDayConvention =
        BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention(floatingLeg.getBusinessDayConvention());
    boolean isEndOfMonth = floatingLeg.isEndOfMonth();

    FixingIndex fixingIndex = floatingLeg.getFixingIndex();
    ExternalId referenceRate = convertExtId(fixingIndex.getIndex());
    FloatingRateType rateType = FloatingRateType.valueOf(fixingIndex.getRateType().toString());


    return new FloatingInterestRateLeg(dayCount, frequency, region, businessDayConvention, notional, isEndOfMonth,
                                       referenceRate, rateType);
  }

  private Notional extractNotional(SwapLeg floatingLeg) {
    Currency currency = Currency.of(floatingLeg.getCurrency());
    return new InterestRateNotional(currency, floatingLeg.getNotional().doubleValue());
  }

  private ExternalId extractRegion(SwapLeg floatingLeg) {
    Set<String> calendarRegions = extractCalendarRegions(floatingLeg.getPaymentCalendars());

    return ExternalSchemes.financialRegionId(StringUtils.join(calendarRegions, "+"));
  }

  private com.opengamma.financial.security.swap.SwapLeg convertFixedLeg(FixedLeg fixedLeg) {

    Notional notional = extractNotional(fixedLeg);

    DayCount dayCount = DayCountFactory.INSTANCE.getDayCount(fixedLeg.getDayCount());
    Frequency frequency = SimpleFrequencyFactory.INSTANCE.getFrequency(fixedLeg.getFrequency());
    ExternalId region = extractRegion(fixedLeg);
    BusinessDayConvention businessDayConvention =
        BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention(fixedLeg.getBusinessDayConvention());
    boolean isEndOfMonth = fixedLeg.isEndOfMonth();
    return new FixedInterestRateLeg(dayCount, frequency, region, businessDayConvention, notional, isEndOfMonth, fixedLeg.getRate().doubleValue());
  }

  private Set<String> extractCalendarRegions(Set<Calendar> calendars) {

    Set<String> regions = Sets.newHashSet();
    for (Calendar calendar : calendars) {

      regions.add(calendar.getId().getId());
    }

    return regions;
  }
}
