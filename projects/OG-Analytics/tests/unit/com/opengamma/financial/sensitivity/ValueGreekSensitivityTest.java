/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.sensitivity;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import java.util.Arrays;

import com.opengamma.financial.greeks.Greek;
import com.opengamma.financial.pnl.UnderlyingType;

/**
 * 
 */
public class ValueGreekSensitivityTest {
  private static final ValueGreek VALUE_GREEK = new ValueGreek(Greek.DELTA);
  private static final String NAME = "NAME";
  private static final Sensitivity<ValueGreek> SENSITIVITY = new ValueGreekSensitivity(VALUE_GREEK, NAME);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullValueGreek() {
    new ValueGreekSensitivity(null, NAME);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullIdentifier() {
    new ValueGreekSensitivity(VALUE_GREEK, null);
  }

  @Test
  public void testGetters() {
    assertEquals(VALUE_GREEK, SENSITIVITY.getSensitivity());
    assertEquals(NAME, SENSITIVITY.getIdentifier());
    assertEquals(1, SENSITIVITY.getOrder());
    assertEquals(Arrays.asList(UnderlyingType.SPOT_PRICE), SENSITIVITY.getUnderlyingTypes());
  }

  @Test
  public void testEquals() {
    final Sensitivity<ValueGreek> sensitivity = new ValueGreekSensitivity(VALUE_GREEK, NAME);
    assertFalse(SENSITIVITY.equals(sensitivity));
  }
}
