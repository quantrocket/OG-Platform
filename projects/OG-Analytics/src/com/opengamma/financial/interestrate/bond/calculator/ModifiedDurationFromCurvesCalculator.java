/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.interestrate.bond.calculator;

import org.apache.commons.lang.Validate;

import com.opengamma.financial.interestrate.AbstractInterestRateDerivativeVisitor;
import com.opengamma.financial.interestrate.YieldCurveBundle;
import com.opengamma.financial.interestrate.bond.definition.BondFixedSecurity;
import com.opengamma.financial.interestrate.bond.method.BondSecurityDiscountingMethod;

/**
 * Calculate dirty price for bonds.
 */
public final class ModifiedDurationFromCurvesCalculator extends AbstractInterestRateDerivativeVisitor<YieldCurveBundle, Double> {

  /**
   * The calculator instance.
   */
  private static final ModifiedDurationFromCurvesCalculator s_instance = new ModifiedDurationFromCurvesCalculator();

  /**
   * Return the calculator instance.
   * @return The instance.
   */
  public static ModifiedDurationFromCurvesCalculator getInstance() {
    return s_instance;
  }

  /**
   * Private constructor.
   */
  private ModifiedDurationFromCurvesCalculator() {
  }

  @Override
  public Double visitBondFixedSecurity(final BondFixedSecurity bond, final YieldCurveBundle curves) {
    Validate.notNull(curves);
    Validate.notNull(bond);
    final BondSecurityDiscountingMethod method = BondSecurityDiscountingMethod.getInstance();
    return method.modifiedDurationFromCurves(bond, curves);
  }

}
