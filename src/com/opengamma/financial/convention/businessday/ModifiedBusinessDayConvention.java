/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.convention.businessday;

import javax.time.calendar.LocalDate;

import com.opengamma.financial.convention.calendar.Calendar;

/**
 * Adjusts a date to the following business day, unless that date is in a
 * different month, in which case the date is adjusted to be the preceding
 * business day.
 * 
 * Also known as "Modified Following".
 * 
 * @author emcleod
 */

public class ModifiedBusinessDayConvention extends BusinessDayConvention {
  private static final BusinessDayConvention FOLLOWING = new FollowingBusinessDayConvention();
  private static final BusinessDayConvention PRECEDING = new PrecedingBusinessDayConvention();

  @Override
  public LocalDate adjustDate(final Calendar workingDays, final LocalDate date) {
    final LocalDate followingDate = FOLLOWING.adjustDate(workingDays, date);
    if (followingDate.getMonthOfYear() != date.getMonthOfYear()) {
      return PRECEDING.adjustDate(workingDays, date);
    }
    return followingDate;
  }
  
  @Override
  public String getConventionName () {
    return "Modified";
  }

}
