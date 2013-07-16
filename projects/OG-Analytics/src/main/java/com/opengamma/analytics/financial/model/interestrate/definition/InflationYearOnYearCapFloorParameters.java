/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.model.interestrate.definition;

import com.opengamma.analytics.financial.instrument.index.IndexPrice;
import com.opengamma.util.ArgumentChecker;

/**
 *  Data bundle related to the year on year inflation cap/floor in price index market model.
 */
public class InflationYearOnYearCapFloorParameters {

  /**
   * The times separating the Ibor periods. 
   */
  private final double[] _expiryTime;
  /**
   * The strike.
   */
  private final double[] _strikes;

  /**
   * The volatilities. The dimensions of the volatility is respectively expiration and strikes.
   */
  private final double[][] _volatility;

  /**
   * The index price for which the volatility is valid. Not null.
   */
  private final IndexPrice _index;

  /**
   * The time tolerance between the dates given by the model and the dates of the instrument. To avoid rounding problems.
   */
  private static final double TIME_TOLERANCE = 1.0E-3;

  public InflationYearOnYearCapFloorParameters() {

    _expiryTime = new double[1];
    _strikes = new double[0];
    _volatility = new double[0][0];
    _index = new IndexPrice("index", null);
  }

  /**
   * Constructor from the model details.
   * @param expiryTimes The times separating the Ibor periods. 
   * @param strikes The accrual factors for the different periods.
   * @param volatility The displacements for the different periods.
   */
  public InflationYearOnYearCapFloorParameters(double[] expiryTimes, double[] strikes, final double[][] volatility, IndexPrice index) {
    ArgumentChecker.notNull(expiryTimes, "Inflation year on year options expiry times");
    ArgumentChecker.notNull(strikes, "Inflation year on year options strikes");
    ArgumentChecker.notNull(volatility, "Inflation year on year options volatilities");
    ArgumentChecker.isTrue(expiryTimes.length == volatility.length, "number of expiry should be the same in the volatility matrix and in the expiry vector");
    ArgumentChecker.isTrue(strikes.length == volatility[0].length, "number of strikes should be the same in the volatility matrix and in the strikes vector");
    _expiryTime = expiryTimes;
    _strikes = strikes;
    _volatility = volatility;
    _index = index;
  }

  /**
   * Create a new copy of the object with the same data. All the arrays are cloned.
   * @return The Inflation year on year option parameters.
   */
  public InflationYearOnYearCapFloorParameters copy() {
    double[][] vol = new double[_volatility.length][];
    for (int loopperiod = 0; loopperiod < _volatility.length; loopperiod++) {
      vol[loopperiod] = _volatility[loopperiod].clone();
    }
    return new InflationYearOnYearCapFloorParameters(_expiryTime.clone(), _strikes.clone(), vol, _index);
  }

  /**
   * Gets the expiries vector.
   * @return the _expiryTime
   */
  public double[] getExpiryTime() {
    return _expiryTime;
  }

  /**
   * Gets the strikes vector.
   * @return the _strikes
   */
  public double[] getStrikes() {
    return _strikes;
  }

  /**
   * Gets the index.
   * @return the _index
   */
  public IndexPrice getIndex() {
    return _index;
  }

  /**
   * Gets the Number Of Expiry Times.
   * @return the _expiryTime.length
   */
  public int getNumberOfExpiryTime() {
    return _expiryTime.length;
  }

  /**
   * Gets the Number Of Strikes.
   * @return the _strikes.length
   */
  public int getNumberOfStrikes() {
    return _strikes.length;
  }

  /**
  * Gets the _volatility field.
  * @return the _volatility
  */
  public double[][] getVolatility() {
    return _volatility;
  }

  public double getTimeTolerance() {
    return TIME_TOLERANCE;
  }

  /**
   * Change the model volatility in a block to a given volatility matrix.
   * @param volatility The changed volatility.
   * @param expiryIndex The start index for the block to change.
   */
  public final void setVolatility(double[][] volatility, int expiryIndex) {
    ArgumentChecker.notNull(volatility, "LMM volatility");
    ArgumentChecker.isTrue(volatility[0].length == _strikes.length, "LMM: incorrect number of factors");
    for (int loopperiod = 0; loopperiod < volatility.length; loopperiod++) {
      System.arraycopy(volatility[loopperiod], 0, _volatility[expiryIndex + loopperiod], 0, volatility[loopperiod].length);
    }
  }

  /**
   * Change the model volatility in a block to a given volatility matrix.
   * @param volatility The changed volatility.
   * @param expiryIndex The index for the value to change.
   * @param strikeIndex The index for the value to change.
   */
  public final void setVolatility(final double volatility, final int expiryIndex, final int strikeIndex) {
    System.arraycopy(volatility, strikeIndex, _volatility[expiryIndex], strikeIndex, 1);
  }

  /**
   * Change the model displacement in a block to a given displacement vector.
   * @param expiryTime The change displacement.
   * @param startIndex The start index for the block to change.
   */
  public final void setExpiryTime(final double[] expiryTime, final int startIndex) {
    ArgumentChecker.notNull(expiryTime, "Inflation year on year options expiry times");
    System.arraycopy(expiryTime, 0, _expiryTime, startIndex, expiryTime.length);
  }

  /**
   * Change the model displacement in a block to a given displacement vector.
   * @param strikes The change displacement.
   * @param startIndex The start index for the block to change.
   */
  public final void setStrikes(final double[] strikes, final int startIndex) {
    ArgumentChecker.notNull(_strikes, "Inflation year on year options strikes");
    System.arraycopy(strikes, 0, _strikes, startIndex, strikes.length);
  }

}
