/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

/**
 * 
 */
public enum NewStripInstrumentType {
  /** Forward-rate agreement */
  FRA,
  /** Interest-rate future */
  FUTURE,
  /** Bankers acceptance */
  SWAP,
  /** Tenor swap */
  BASIS_SWAP,
  /** OIS swap */
  OIS,
  /** Periodic zero deposit strip */
  PERIODIC_ZERO_DEPOSIT,
  /** Rate strip */
  RATE,
  /** FX forward strip */
  FX_FORWARD,
  /** FX swap strip */
  FX_SWAP
}
