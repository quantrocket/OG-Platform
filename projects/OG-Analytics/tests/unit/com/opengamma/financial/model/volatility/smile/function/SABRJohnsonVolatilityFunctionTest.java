/**
 * Copyright (C) 2009 - 2011 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.model.volatility.smile.function;

import org.testng.annotations.Test;
import org.apache.commons.lang.NotImplementedException;


/**
 * 
 */
public class SABRJohnsonVolatilityFunctionTest extends SABRVolatilityFunctionTestCase {
  private static final SABRJohnsonVolatilityFunction FUNCTION = new SABRJohnsonVolatilityFunction();

  @Override
  protected VolatilityFunctionProvider<SABRFormulaData> getFunction() {
    return FUNCTION;
  }

  @Override
  @Test(expectedExceptions = NotImplementedException.class)
  public void testApproachingLogNormalEquivalent2() {
    getFunction().getVolatilityFunction(OPTION).evaluate(APPROACHING_LOG_NORMAL_EQUIVALENT2);
  }

  @Override
  @Test(expectedExceptions = NotImplementedException.class)
  public void testApproachingLogNormalEquivalent3() {
    getFunction().getVolatilityFunction(OPTION).evaluate(APPROACHING_LOG_NORMAL_EQUIVALENT3);
  }

  @Test(expectedExceptions = NotImplementedException.class)
  public void testZeroBeta() {
    getFunction().getVolatilityFunction(OPTION).evaluate(new SABRFormulaData(103, 0.8, 0, 0.15, 0.5));
  }

}
