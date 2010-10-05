/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

import static com.opengamma.id.IdentificationScheme.BLOOMBERG_TICKER;

import java.util.ArrayList;
import java.util.Collection;

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventionFactory;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.convention.frequency.SimpleFrequencyFactory;
import com.opengamma.id.IdentificationScheme;
import com.opengamma.id.Identifier;
import com.opengamma.id.IdentifierBundle;
import com.opengamma.id.IdentifierBundleMapper;
import com.opengamma.id.UniqueIdentifier;

/**
 * An in-memory, statically initialized master for convention bundles and their meta-data
 */
public class InMemoryConventionBundleMaster implements ConventionBundleMaster {
  /**
   * IdentificationScheme to use when specifying rates with simple descriptions e.g. 'LIBOR O/N', 'LIBOR 1w' etc.
   */
  public static final IdentificationScheme SIMPLE_NAME_SCHEME = new IdentificationScheme("Reference Rate Simple Name");

  /**
   * IdentificationScheme of the unique identifiers generated by this repository.
   */
  public static final IdentificationScheme IN_MEMORY_UNIQUE_SCHEME = new IdentificationScheme("In-memory Reference Rate unique");

  private final IdentifierBundleMapper<ConventionBundle> _mapper = new IdentifierBundleMapper<ConventionBundle>(IN_MEMORY_UNIQUE_SCHEME.getName());

  public InMemoryConventionBundleMaster() {
    addUSDFixedIncomeInstruments();
    addUSDCAPMDefinition();
  }

  @Override
  public synchronized UniqueIdentifier addConventionBundle(final IdentifierBundle bundle, final String name, final DayCount dayCount, final BusinessDayConvention businessDayConvention,
      final Frequency frequency, final int settlementDays) {
    final ConventionBundleImpl refRate = new ConventionBundleImpl(bundle, name, dayCount, businessDayConvention, frequency, settlementDays);
    final UniqueIdentifier uid = _mapper.add(bundle, refRate);
    refRate.setUniqueIdentifier(uid);
    return uid;
  }

  @Override
  public synchronized UniqueIdentifier addConventionBundle(final IdentifierBundle bundle, final String name, final DayCount dayCount, final BusinessDayConvention businessDayConvention,
      final Frequency frequency, final int settlementDays, final double pointValue) {
    final ConventionBundleImpl refRate = new ConventionBundleImpl(bundle, name, dayCount, businessDayConvention, frequency, settlementDays, pointValue);
    final UniqueIdentifier uid = _mapper.add(bundle, refRate);
    refRate.setUniqueIdentifier(uid);
    return uid;
  }

  @Override
  public synchronized UniqueIdentifier addConventionBundle(final IdentifierBundle bundle, final String name, final DayCount swapFixedLegDayCount,
      final BusinessDayConvention swapFixedLegBusinessDayConvention, final Frequency swapFixedLegFrequency, final Integer swapFixedLegSettlementDays, final DayCount swapFloatingLegDayCount,
      final BusinessDayConvention swapFloatingLegBusinessDayConvention, final Frequency swapFloatingLegFrequency, final Integer swapFloatingLegSettlementDays,
      final Identifier swapFloatingLegInitialRate) {
    final ConventionBundleImpl refRate = new ConventionBundleImpl(bundle, name, swapFixedLegDayCount, swapFixedLegBusinessDayConvention, swapFixedLegFrequency, swapFixedLegSettlementDays,
        swapFloatingLegDayCount, swapFloatingLegBusinessDayConvention, swapFloatingLegFrequency, swapFloatingLegSettlementDays, swapFloatingLegInitialRate);
    final UniqueIdentifier uid = _mapper.add(bundle, refRate);
    refRate.setUniqueIdentifier(uid);
    return uid;
  }

  @Override
  public synchronized UniqueIdentifier addConventionBundle(final IdentifierBundle bundle, final String name, final String capmRiskFreeRateName, final String capmMarketName) {
    final ConventionBundleImpl convention = new ConventionBundleImpl(name, capmRiskFreeRateName, capmMarketName);
    final UniqueIdentifier uid = _mapper.add(bundle, convention);
    convention.setUniqueIdentifier(uid);
    return uid;
  }

  @Override
  public ConventionBundleDocument getConventionBundle(final UniqueIdentifier uniqueIdentifier) {
    return new ConventionBundleDocument(_mapper.get(uniqueIdentifier));
  }

  @Override
  public ConventionBundleSearchResult searchConventionBundle(final ConventionBundleSearchRequest request) {
    final Collection<ConventionBundle> collection = _mapper.get(request.getIdentifiers());
    return new ConventionBundleSearchResult(wrapReferenceRatesWithDocuments(collection));
  }

  @Override
  public ConventionBundleSearchResult searchHistoricConventionBundle(final ConventionBundleSearchHistoricRequest request) {
    final Collection<ConventionBundle> collection = _mapper.get(request.getIdentifiers());
    return new ConventionBundleSearchResult(wrapReferenceRatesWithDocuments(collection));
  }

  private Collection<ConventionBundleDocument> wrapReferenceRatesWithDocuments(final Collection<ConventionBundle> referenceRates) {
    final Collection<ConventionBundleDocument> results = new ArrayList<ConventionBundleDocument>(referenceRates.size());
    for (final ConventionBundle referenceRate : referenceRates) {
      results.add(new ConventionBundleDocument(referenceRate));
    }
    return results;
  }

  private void addUSDFixedIncomeInstruments() {
    // NOTE THESE ONLY APPLY TO US LIBOR RATES
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
    final DayCount thirty360 = DayCountFactory.INSTANCE.getDayCount("30/360");
    final Frequency freq = null;
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    final Frequency quarterly = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.QUARTERLY_NAME);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US00O/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR O/N")), "USD LIBOR O/N", act360, following, freq, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US00T/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR T/N")), "USD LIBOR T/N", act360, following, freq, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0001W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 1w")), "USD LIBOR 1w", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0002W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 2w")), "USD LIBOR 2w", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0001M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 1m")), "USD LIBOR 1m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0002M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 2m")), "USD LIBOR 2m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0003M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 3m")), "USD LIBOR 3m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0004M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 4m")), "USD LIBOR 4m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0005M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 5m")), "USD LIBOR 5m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0006M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 6m")), "USD LIBOR 6m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0007M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 7m")), "USD LIBOR 7m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0008M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 8m")), "USD LIBOR 8m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0009M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 9m")), "USD LIBOR 9m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0010M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 10m")), "USD LIBOR 10m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0011M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 11m")), "USD LIBOR 11m", act360, following, freq, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "US0012M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "USD LIBOR 12m")), "USD LIBOR 12m", act360, following, freq, 2);

    //TODO with improvement in settlement days definition (i.e. including holiday and adjustment) change this
    // should be 2, LON, following
    // holiday for swap should be NY+LON
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "USD_SWAP")), "USD_SWAP", thirty360, modified, semiAnnual, 2, act360, modified, quarterly, 2, Identifier.of(
        SIMPLE_NAME_SCHEME, "USD LIBOR 3m"));

    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "USD_FRA")), "USD_FRA", act360, following, null, 2);

    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "USD_IRFUTURE")), "USD_IRFUTURE", act360, following, null, 2, 0.25);
  }

  private void addEURFixedIncomeInstruments() {
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
    final DayCount thirty360 = DayCountFactory.INSTANCE.getDayCount("30/360");
    final Frequency annual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.ANNUAL_NAME);
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    final Frequency quarterly = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.QUARTERLY_NAME);
    //TODO looked at BSYM and the codes seem right but need to check
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU00O/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR O/N")), "EUR LIBOR O/N", act360, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU00T/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR T/N")), "EUR LIBOR T/N", act360, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0001W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 1w")), "EUR LIBOR 1w", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0002W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 2w")), "EUR LIBOR 2w", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0001M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 1m")), "EUR LIBOR 1m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0002M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 2m")), "EUR LIBOR 2m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0003M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 3m")), "EUR LIBOR 3m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0004M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 4m")), "EUR LIBOR 4m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0005M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 5m")), "EUR LIBOR 5m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0006M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 6m")), "EUR LIBOR 6m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0007M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 7m")), "EUR LIBOR 7m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0008M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 8m")), "EUR LIBOR 8m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0009M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 9m")), "EUR LIBOR 9m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0010M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 10m")), "EUR LIBOR 10m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0011M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 11m")), "EUR LIBOR 11m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "EU0012M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "EUR LIBOR 12m")), "EUR LIBOR 12m", act360, following, null, 2);

    //TODO holiday associated with EUR swaps is TARGET
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "EUR_SWAP")), "EUR_SWAP", thirty360, modified, annual, 2, act360, modified, semiAnnual, 2, Identifier.of(
        SIMPLE_NAME_SCHEME, "EUR LIBOR 6m"));
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "EUR_1Y_SWAP")), "EUR_1Y_SWAP", thirty360, modified, annual, 2, act360, modified, quarterly, 2, Identifier.of(
        SIMPLE_NAME_SCHEME, "EUR LIBOR 3m"));
  }

  private void addJPYFixedIncomeInstruments() {
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
    final DayCount act365 = DayCountFactory.INSTANCE.getDayCount("Actual/365");
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    //TODO looked at BSYM and the codes seem right but need to check
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY00O/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR O/N")), "JPY LIBOR O/N", act360, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY00T/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR T/N")), "JPY LIBOR T/N", act360, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0001W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 1w")), "JPY LIBOR 1w", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0002W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 2w")), "JPY LIBOR 2w", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0001M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 1m")), "JPY LIBOR 1m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0002M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 2m")), "JPY LIBOR 2m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0003M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 3m")), "JPY LIBOR 3m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0004M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 4m")), "JPY LIBOR 4m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0005M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 5m")), "JPY LIBOR 5m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0006M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 6m")), "JPY LIBOR 6m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0007M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 7m")), "JPY LIBOR 7m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0008M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 8m")), "JPY LIBOR 8m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0009M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 9m")), "JPY LIBOR 9m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0010M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 10m")), "JPY LIBOR 10m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0011M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 11m")), "JPY LIBOR 11m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "JY0012M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "JPY LIBOR 12m")), "JPY LIBOR 12m", act360, following, null, 2);

    //TODO holiday associated with JPY swaps is Tokyo
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "JPY_SWAP")), "JPY_SWAP", act365, modified, semiAnnual, 2, act360, modified, semiAnnual, 2, Identifier.of(
        SIMPLE_NAME_SCHEME, "JPY LIBOR 6m"));
  }

  private void addCADFixedIncomeInstruments() {
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
    final DayCount act365 = DayCountFactory.INSTANCE.getDayCount("Actual/365");
    final Frequency annual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.ANNUAL_NAME);
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    final Frequency quarterly = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.QUARTERLY_NAME);
    //TODO looked at BSYM and the codes seem right but need to check
    //TODO check daycount
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "CDOR01M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CDOR 1m")), "CDOR 1m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "CDOR02M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CDOR 2m")), "CDOR 2m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "CDOR03M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CDOR 3m")), "CDOR 3m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "CDOR06M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CDOR 6m")), "CDOR 6m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "CDOR12M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CDOR 12m")), "CDOR 12m", act360, following, null, 2);

    //TODO holiday associated with CAD swaps is Toronto
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "CAD_SWAP")), "CAD_SWAP", act365, modified, semiAnnual, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "CDOR 3m"));
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "CAD_1Y_SWAP")), "CAD_1Y_SWAP", act365, modified, annual, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "CDOR 3m"));
    //TODO according to my information:
    //"Floating leg compounded quarterly at CDOR Flat paid semi-annually or annually for 1y"
    //Don't know how we're going to put that in
  }

  private void addGBPFixedIncomeInstruments() {
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act365 = DayCountFactory.INSTANCE.getDayCount("Actual/365");
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    final Frequency quarterly = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.QUARTERLY_NAME);
    //TODO looked at BSYM and the codes seem right but need to check
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP00O/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR O/N")), "GBP LIBOR O/N", act365, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP00T/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR T/N")), "GBP LIBOR T/N", act365, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0001W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 1w")), "GBP LIBOR 1w", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0002W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 2w")), "GBP LIBOR 2w", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0001M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 1m")), "GBP LIBOR 1m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0002M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 2m")), "GBP LIBOR 2m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0003M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 3m")), "GBP LIBOR 3m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0004M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 4m")), "GBP LIBOR 4m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0005M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 5m")), "GBP LIBOR 5m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0006M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 6m")), "GBP LIBOR 6m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0007M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 7m")), "GBP LIBOR 7m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0008M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 8m")), "GBP LIBOR 8m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0009M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 9m")), "GBP LIBOR 9m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0010M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 10m")), "GBP LIBOR 10m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0011M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 11m")), "GBP LIBOR 11m", act365, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "BP0012M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "GBP LIBOR 12m")), "GBP LIBOR 12m", act365, following, null, 2);

    //TODO holiday associated with GBP swaps is London
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "GBP_SWAP")), "GBP_SWAP", act365, modified, semiAnnual, 0, act365, modified, semiAnnual, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "GBP LIBOR 6m"));
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "GBP_1Y_SWAP")), "GBP_1Y_SWAP", act365, modified, quarterly, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "GBP LIBOR 3m"));
  }

  private void addAUDFixedIncomeInstruments() {
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act365 = DayCountFactory.INSTANCE.getDayCount("Actual/365");
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    final Frequency quarterly = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.QUARTERLY_NAME);
    //TODO don't know if this is the right one - Australia also has Banker's Acceptance notes (like Canada) 
    //TODO check daycount
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "AU0003M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "AUD 3m Bill")), "AUD 3m Bill", act365, following, null, 2);

    //TODO holiday associated with AUD swaps is Sydney
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "AUD_SWAP")), "AUD_SWAP", act365, modified, semiAnnual, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "AUD 3m Bill"));
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "AUD_1Y_SWAP")), "AUD_1Y_SWAP", act365, modified, quarterly, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "AUD 3m Bill"));
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "AUD_2Y_SWAP")), "AUD_2Y_SWAP", act365, modified, quarterly, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "AUD 3m Bill"));
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "AUD_3Y_SWAP")), "AUD_3Y_SWAP", act365, modified, quarterly, 0, act365, modified, quarterly, 0, Identifier.of(
        SIMPLE_NAME_SCHEME, "AUD 3m Bill"));
  }

  private void addCHFFixedIncomeInstruments() {
    final BusinessDayConvention modified = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Modified Following");
    final BusinessDayConvention following = BusinessDayConventionFactory.INSTANCE.getBusinessDayConvention("Following");
    final DayCount act360 = DayCountFactory.INSTANCE.getDayCount("Actual/360");
    final DayCount thirty360 = DayCountFactory.INSTANCE.getDayCount("30/360");
    final Frequency semiAnnual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.SEMI_ANNUAL_NAME);
    final Frequency annual = SimpleFrequencyFactory.INSTANCE.getFrequency(Frequency.ANNUAL_NAME);
    //TODO check that it's actually libor that we need
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF00O/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR O/N")), "CHF LIBOR O/N", act360, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF00T/N Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR T/N")), "CHF LIBOR T/N", act360, following, null, 0);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0001W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 1w")), "CHF LIBOR 1w", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0002W Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 2w")), "CHF LIBOR 2w", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0001M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 1m")), "CHF LIBOR 1m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0002M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 2m")), "CHF LIBOR 2m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0003M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 3m")), "CHF LIBOR 3m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0004M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 4m")), "CHF LIBOR 4m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0005M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 5m")), "CHF LIBOR 5m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0006M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 6m")), "CHF LIBOR 6m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0007M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 7m")), "CHF LIBOR 7m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0008M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 8m")), "CHF LIBOR 8m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0009M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 9m")), "CHF LIBOR 9m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0010M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 10m")), "CHF LIBOR 10m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0011M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 11m")), "CHF LIBOR 11m", act360, following, null, 2);
    addConventionBundle(IdentifierBundle.of(Identifier.of(BLOOMBERG_TICKER, "SF0012M Index"), Identifier.of(SIMPLE_NAME_SCHEME, "CHF LIBOR 12m")), "CHF LIBOR 12m", act360, following, null, 2);

    //TODO holiday associated with CHF swaps is Zurich
    //TODO check reference rate
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "CHF_SWAP")), "CHF_SWAP", thirty360, modified, annual, 2, act360, modified, semiAnnual, 2, Identifier.of(
        SIMPLE_NAME_SCHEME, "CHF LIBOR 6m"));
  }

  private void addUSDCAPMDefinition() {
    addConventionBundle(IdentifierBundle.of(Identifier.of(SIMPLE_NAME_SCHEME, "USD_CAPM")), "USD_CAPM", "US0003M Index", "SPX Index");
  }

}
