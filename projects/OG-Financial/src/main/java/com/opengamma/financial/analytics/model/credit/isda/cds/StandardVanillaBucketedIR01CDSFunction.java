/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.credit.isda.cds;

import java.util.Collections;
import java.util.Set;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZonedDateTime;

import com.google.common.collect.Iterables;
import com.opengamma.analytics.financial.credit.bumpers.InterestRateBumpers;
import com.opengamma.analytics.financial.credit.creditdefaultswap.definition.vanilla.CreditDefaultSwapDefinition;
import com.opengamma.analytics.financial.credit.creditdefaultswap.greeks.vanilla.isda.ISDACreditDefaultSwapBucketedIR01Calculator;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.CDSAnalytic;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.ISDACompliantCreditCurve;
import com.opengamma.analytics.financial.credit.creditdefaultswap.pricing.vanilla.isdanew.ISDACompliantYieldCurve;
import com.opengamma.analytics.financial.credit.isdayieldcurve.InterestRateBumpType;
import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.analytics.LocalDateLabelledMatrix1D;
import com.opengamma.financial.analytics.model.credit.CreditFunctionUtils;
import com.opengamma.financial.analytics.model.credit.CreditInstrumentPropertyNamesAndValues;

/**
 * 
 */
public class StandardVanillaBucketedIR01CDSFunction extends StandardVanillaIR01CDSFunction {
  private static final InterestRateBumpers BUMPER = new InterestRateBumpers();

  public StandardVanillaBucketedIR01CDSFunction() {
    super(ValueRequirementNames.BUCKETED_IR01);
  }

  @Override
  protected Set<ComputedValue> getComputedValue(final CreditDefaultSwapDefinition definition,
                                                final ISDACompliantYieldCurve yieldCurve,
                                                final ZonedDateTime[] times,
                                                final double[] marketSpreads,
                                                final ZonedDateTime valuationDate,
                                                final ComputationTarget target,
                                                final ValueProperties properties,
                                                final FunctionInputs inputs,
                                                ISDACompliantCreditCurve hazardCurve, CDSAnalytic analytic) {
    final Double interestRateCurveBump = Double.valueOf(Iterables.getOnlyElement(properties.getValues(
        CreditInstrumentPropertyNamesAndValues.PROPERTY_INTEREST_RATE_CURVE_BUMP)));
    final InterestRateBumpType interestRateBumpType =
        InterestRateBumpType.valueOf(Iterables.getOnlyElement(properties.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_INTEREST_RATE_BUMP_TYPE)));
    //final PriceType priceType = PriceType.valueOf(Iterables.getOnlyElement(properties.getValues(CreditInstrumentPropertyNamesAndValues.PROPERTY_CDS_PRICE_TYPE)));
    final double[] rates = yieldCurve.getR();
    final LocalDate[] dates = new LocalDate[rates.length];
    final double[] ir01 = new double[rates.length];
    for (int i = 0; i < rates.length; i++) {
      final double[] bumpedUpRates = BUMPER.getBumpedRates(rates, interestRateCurveBump * 1e-4, interestRateBumpType, i);
      final ISDACompliantYieldCurve bumpedUpYieldCurve = yieldCurve.withRates(bumpedUpRates);
      final double presentValue = StandardVanillaPresentValueCDSFunction.presentValue(definition, yieldCurve, hazardCurve, analytic);
      final double bumpedPresentValue = StandardVanillaPresentValueCDSFunction.presentValue(definition, bumpedUpYieldCurve, hazardCurve, analytic);
      ir01[i] = (bumpedPresentValue - presentValue) / interestRateCurveBump;
      dates[i] = valuationDate.plusDays((long) yieldCurve.getTimeAtIndex(i) * 365).toLocalDate();
    }
    //final String[] labels = CreditFunctionUtils.getFormattedBucketedXAxis(dates, valuationDate);
    final LocalDateLabelledMatrix1D ir01Matrix = new LocalDateLabelledMatrix1D(dates, ir01);
    final ValueSpecification spec = new ValueSpecification(ValueRequirementNames.BUCKETED_IR01, target.toSpecification(), properties);
    return Collections.singleton(new ComputedValue(spec, ir01Matrix));
  }

}
