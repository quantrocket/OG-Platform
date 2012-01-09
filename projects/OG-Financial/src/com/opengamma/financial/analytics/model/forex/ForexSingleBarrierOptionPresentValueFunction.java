/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.forex;

import java.util.Collections;
import java.util.Set;

import org.apache.commons.lang.Validate;

import com.opengamma.engine.ComputationTarget;
import com.opengamma.engine.function.FunctionCompilationContext;
import com.opengamma.engine.function.FunctionInputs;
import com.opengamma.engine.value.ComputedValue;
import com.opengamma.engine.value.ValueProperties;
import com.opengamma.engine.value.ValuePropertyNames;
import com.opengamma.engine.value.ValueRequirementNames;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.financial.forex.calculator.PresentValueBlackForexCalculator;
import com.opengamma.financial.interestrate.InstrumentDerivative;
import com.opengamma.financial.model.option.definition.SmileDeltaTermStructureDataBundle;
import com.opengamma.financial.security.option.FXBarrierOptionSecurity;
import com.opengamma.util.money.Currency;
import com.opengamma.util.money.CurrencyAmount;
import com.opengamma.util.money.MultipleCurrencyAmount;

/**
 * 
 */
public class ForexSingleBarrierOptionPresentValueFunction extends ForexSingleBarrierOptionFunction {
  private static final PresentValueBlackForexCalculator CALCULATOR = PresentValueBlackForexCalculator.getInstance();

  @Override
  protected Set<ComputedValue> getResult(final InstrumentDerivative fxSingleBarrierOption, final SmileDeltaTermStructureDataBundle data, final FunctionInputs inputs, final ComputationTarget target,
      final String putFundingCurveName, final String putForwardCurveName, final String callFundingCurveName, final String callForwardCurveName, final String surfaceName) {
    final MultipleCurrencyAmount result = CALCULATOR.visit(fxSingleBarrierOption, data);
    Validate.isTrue(result.size() == 1);
    final CurrencyAmount ca = result.getCurrencyAmounts()[0];
    final Currency ccy = ca.getCurrency();
    final double amount = ca.getAmount();
    final ValueProperties properties = createValueProperties()
        .with(ValuePropertyNames.PAY_CURVE, putFundingCurveName)
        .with(ValuePropertyNames.RECEIVE_CURVE, callFundingCurveName)
        .with(ValuePropertyNames.SURFACE, surfaceName)
        .with(ValuePropertyNames.CURRENCY, ccy.getCode()).get();
    final ValueSpecification spec = new ValueSpecification(ValueRequirementNames.PRESENT_VALUE, target.toSpecification(), properties);
    return Collections.singleton(new ComputedValue(spec, amount));
  }

  @Override
  public Set<ValueSpecification> getResults(final FunctionCompilationContext context, final ComputationTarget target) {
    final FXBarrierOptionSecurity security = (FXBarrierOptionSecurity) target.getSecurity();
    final Currency putCurrency = security.getPutCurrency();
    final Currency callCurrency = security.getCallCurrency();
    Currency ccy;
    if (ForexUtils.isBaseCurrency(putCurrency, callCurrency)) {
      ccy = putCurrency;
    } else {
      ccy = callCurrency;
    }
    final ValueProperties properties = createValueProperties()
        .withAny(ValuePropertyNames.PAY_CURVE)
        .withAny(ValuePropertyNames.RECEIVE_CURVE)
        .withAny(ValuePropertyNames.SURFACE)
        .with(ValuePropertyNames.CURRENCY, ccy.getCode()).get();
    return Collections.singleton(new ValueSpecification(ValueRequirementNames.PRESENT_VALUE, target.toSpecification(), properties));
  }
}
