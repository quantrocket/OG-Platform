/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.credit.cds;

import javax.time.calendar.DateAdjuster;
import javax.time.calendar.LocalDate;
import javax.time.calendar.LocalDateTime;
import javax.time.calendar.Period;
import javax.time.calendar.TimeZone;
import javax.time.calendar.ZonedDateTime;
import javax.time.calendar.format.DateTimeFormatter;
import javax.time.calendar.format.DateTimeFormatters;
import javax.time.Duration;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.instrument.cds.CDSDefinition;
import com.opengamma.analytics.financial.instrument.cds.CDSPremiumDefinition;
import com.opengamma.analytics.financial.interestrate.PeriodicInterestRate;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.FollowingBusinessDayConvention;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.ActualThreeSixty;
import com.opengamma.financial.convention.daycount.ActualThreeSixtyFive;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.frequency.Frequency;
import com.opengamma.financial.convention.frequency.SimpleFrequency;
import com.opengamma.util.money.Currency;


public class ISDATestGridHarness {
  
  private static final String[] selectedUnitTestGrids = { "HKD_20090908.xls", "USD_20090911.xls" };
  
  private static final double dirtyAbsoluteErrorLimit = 1E-4;
  private static final double dirtyRelativeErrorLimit = 1E-10;
  private static final double cleanPercentageErrorLimit = 1E-8;
  
  private static final DateTimeFormatter formatter = DateTimeFormatters.pattern("yyyy-MM-dd");
  private static final DayCount dayCount = new ActualThreeSixtyFive();
  private static final CDSApproxISDAMethod calculator = new CDSApproxISDAMethod();
  
  private ISDATestGridManager testGridManager;
  private ISDAStagedDataManager stagedDataManager;
  
  private class TestResult {
    public final double dirty;
    public final double dirtyExpected;
    public final double dirtyAbsoluteError;
    public final double dirtyRelativeError;
    
    public final double clean;
    public final double cleanExpected;
    public final double cleanPercentageError;
    
    public TestResult (final double dirty, final double dirtyExpected, final double dirtyAbsoluteError, final double dirtyRelativeError,
      final double clean, final double cleanExpected, final double cleanPercentageError) {
      
      this.dirty = dirty;
      this.dirtyExpected = dirtyExpected;
      this.dirtyAbsoluteError = dirtyAbsoluteError;
      this.dirtyRelativeError = dirtyRelativeError;
      
      this.clean = clean;
      this.cleanExpected = cleanExpected;
      this.cleanPercentageError = cleanPercentageError;
    }
  }
  
  private class TestGridResult {
    public final int cases;
    public final int failures;
    public final double seconds;
    public final double maxAbsoluteError;
    public final double maxRelativeError;
    public final double maxCleanPercentageError;
    
    public TestGridResult(final int cases, final int failures, final double seconds, final double maxAbsoluteError, final double maxRelativeError, final double maxCleanPercentageError) {
      this.cases = cases;
      this.failures = failures;
      this.seconds = seconds;
      this.maxAbsoluteError = maxAbsoluteError;
      this.maxRelativeError = maxRelativeError;
      this.maxCleanPercentageError = maxCleanPercentageError;
    }
  }
  
  public ISDATestGridHarness() {
    testGridManager = new ISDATestGridManager();
    stagedDataManager = new ISDAStagedDataManager();
  }
  
  @Test
  public void runSelectedTestGrids() throws Exception {
    runTestGrids(selectedUnitTestGrids);
  }
  
  @Test(groups="slow")
  public void runAllTestGrids() throws Exception {
	  runTestGrids(testGridManager.findAllTestGridFiles());
  }
  
  public void runTestGrids(final String[] testFiles) throws Exception {
	  
	  ISDATestGrid testGrid;
	  ISDAStagedCurve stagedCurve;
	  ISDACurve discountCurve;
	  TestGridResult result;
	  
	  long grids = 0, gridFailures = 0, cases = 0, caseFailures = 0;
    double totalTime = 0.0;
	  double maxAbsoluteError = 0.0;
	  double maxRelativeError = 0.0;
	  double maxCleanPercentageError = 0.0;
	  
	  for (String fileName : testFiles) {
		  
		  testGrid = testGridManager.loadTestGrid(fileName);
		  stagedCurve = stagedDataManager.loadStagedCurveForGrid(fileName);
		  
		  if (stagedCurve == null)
		    continue;
		  
		  System.out.println("Running test grid: " + fileName);
		  
		  discountCurve = buildCurve(stagedCurve, "IR_CURVE");
		  result = runTestGrid(testGrid, discountCurve, fileName);
		  
		  grids += 1;
		  gridFailures += result.failures > 0 ? 1 : 0;
		  cases += result.cases;
		  caseFailures += result.failures;
		  totalTime += result.seconds;
		  maxAbsoluteError = result.maxAbsoluteError > maxAbsoluteError ? result.maxAbsoluteError : maxAbsoluteError;
		  maxRelativeError = result.maxRelativeError > maxRelativeError ? result.maxRelativeError : maxRelativeError;
		  maxCleanPercentageError = result.maxCleanPercentageError > maxCleanPercentageError ? result.maxCleanPercentageError : maxCleanPercentageError;
	  }
	  
	  System.out.println(" --- ISDA Test Grid run complete --- ");
	  System.out.println("Total execution time: " + totalTime + "s");
	  System.out.println("Total test grids executed: " + grids);
	  System.out.println("Total test cases executed: " + cases);
	  System.out.println("Total test grids failed: " + gridFailures);
	  System.out.println("Total test cases failed: " + caseFailures);
	  System.out.println("Largest dirty absolute error: " + maxAbsoluteError);
	  System.out.println("Largest dirty relative error: " + maxRelativeError);
	  System.out.println("Largest clean percentage error: " + maxCleanPercentageError);
  }
  
  public TestGridResult runTestGrid(ISDATestGrid testGrid, ISDACurve discountCurve, String testGridFileName) throws Exception {
    
    ISDAStagedCurve stagedCurve;
    ISDACurve hazardRateCurve = null;
    
    int i = 0, failures = 0;
    TestResult result;
    double maxAbsoluteError = 0.0;
    double maxRelativeError = 0.0;
    double maxCleanPercentageError = 0.0;
    
    final ZonedDateTime start = ZonedDateTime.now();
    
    for (ISDATestGridRow testCase : testGrid.getData()) {
      
      stagedCurve = stagedDataManager.loadStagedHazardCurveForGrid(testGridFileName, i);
      hazardRateCurve = stagedCurve != null ? buildCurve(stagedCurve, "HAZARD_RATE_CURVE") : null;
      result = runTestCase(testCase, discountCurve, hazardRateCurve);
      
      if (result.dirtyRelativeError >= dirtyRelativeErrorLimit || result.dirtyAbsoluteError >= dirtyAbsoluteErrorLimit || result.cleanPercentageError >= cleanPercentageErrorLimit) {
        
        ++failures;
        
        // Only print out worst cases, to avoid excessive output if the test results are massively out
        if (result.dirtyAbsoluteError > maxAbsoluteError || result.dirtyRelativeError > maxRelativeError || result.cleanPercentageError > maxCleanPercentageError) {
          System.out.println("Grid marked to fail: " + testGridFileName + " row " + (i+2) + ": "
            + "dirty = " + result.dirty + " (exepcted = " + result.dirtyExpected + "), "
            + "clean = " + result.clean + " (expected = " + result.cleanExpected + "), "
            + "absolute error = " + result.dirtyAbsoluteError + ", relative error = " + result.dirtyRelativeError + ", percentage error = " + result.cleanPercentageError);
        }
      }
      
      maxAbsoluteError = result.dirtyAbsoluteError > maxAbsoluteError ? result.dirtyAbsoluteError : maxAbsoluteError;
      maxRelativeError = result.dirtyRelativeError > maxRelativeError ? result.dirtyRelativeError : maxRelativeError;
      maxCleanPercentageError = result.cleanPercentageError > maxCleanPercentageError ? result.cleanPercentageError : maxCleanPercentageError;

      ++i;
    }
    
    final ZonedDateTime stop = ZonedDateTime.now();
    final Duration elapsedTime = Duration.between(start, stop);
    final double seconds = elapsedTime.getSeconds() + (elapsedTime.getNanoOfSecond() / 1000000) / 1000.0;
    
    System.out.println( "Executed " + i + " test cases in " + seconds + "s with " + failures + " failure(s)"
      + ", largest dirty absolute error was " + maxAbsoluteError
      + ", largest dirty relative error was " + maxRelativeError
      + ", largest clean percentage error was " + maxCleanPercentageError);
    
    return new TestGridResult(i, failures, seconds, maxAbsoluteError, maxRelativeError, maxCleanPercentageError);
  }
  
  public TestResult runTestCase(ISDATestGridRow testCase, ISDACurve discountCurve, ISDACurve hazardRateCurve) {
       
    final Calendar calendar = new MondayToFridayCalendar("TestCalendar");
    final BusinessDayConvention convention = new FollowingBusinessDayConvention();
    final DateAdjuster adjuster = convention.getDateAdjuster(calendar);
    
    final ZonedDateTime pricingDate = testCase.getTradeDate().atStartOfDayInZone(TimeZone.UTC); 
    final ZonedDateTime maturity = testCase.getMaturityDate().atStartOfDayInZone(TimeZone.UTC);
    
    // Step-in date is always T+1 calendar
    final ZonedDateTime stepinDate = pricingDate.plusDays(1);
    
    // If settlement date is not supplied, use T+3 business days
    final ZonedDateTime settlementDate = testCase.getCashSettle() != null
      ? testCase.getCashSettle().atStartOfDayInZone(TimeZone.UTC)
      : pricingDate.plusDays(1).with(adjuster).plusDays(1).with(adjuster).plusDays(1).with(adjuster);
    
    // If start date is not supplied, construct one that is before the pricing date
    final Period yearsToMaturity = Period.yearsBetween(pricingDate, maturity);
    final ZonedDateTime startDate = testCase.getStartDate() != null
      ? testCase.getStartDate().atStartOfDayInZone(TimeZone.UTC)
      : maturity.minusYears(yearsToMaturity.getYears() + 1).with(adjuster);
    
    // Spread and recovery are always given
    final double spread = testCase.getCoupon() / 10000.0;
    final double recoveryRate = testCase.getRecoveryRate();
    
    // Assume 1 billion notional, quarterly premiums and ACT360 day count
    final double notional = 1000000000;
    final Frequency couponFrequency = SimpleFrequency.QUARTERLY;
    final DayCount dayCount = new ActualThreeSixty();  
    
    // Now build the CDS object
    final CDSPremiumDefinition premiumDefinition = CDSPremiumDefinition.fromISDA(Currency.USD, startDate, maturity, couponFrequency, calendar, dayCount, convention, /*notional*/ 1.0, spread, /* protect start */ true);
    final CDSDefinition cdsDefinition = new CDSDefinition(premiumDefinition, null, startDate, maturity, /*notional*/1.0, spread, recoveryRate, /* accrualOnDefault */ true, /* payOnDefault */ true, /* protectStart */ true, dayCount);
    final CDSDerivative cds = cdsDefinition.toDerivative(pricingDate, "IR_CURVE");  
    
    // Par spread is always supplied
    final double marketSpread = testCase.getQuotedSpread() / 10000.0;
    
    // Now go price
    final double dirtyPrice = hazardRateCurve != null
      ? calculator.calculateUpfrontCharge(cds, discountCurve, hazardRateCurve, pricingDate, stepinDate, settlementDate, false)
      : calculator.calculateUpfrontCharge(cds, discountCurve, marketSpread, pricingDate, stepinDate, settlementDate, false);
    
    final double cleanPrice = hazardRateCurve != null
        ? calculator.calculateUpfrontCharge(cds, discountCurve, hazardRateCurve, pricingDate, stepinDate, settlementDate, true)
        : calculator.calculateUpfrontCharge(cds, discountCurve, marketSpread, pricingDate, stepinDate, settlementDate, true);
      
    final double dirtyExpected = testCase.getUpfront();
    final double dirtyAbsoluteError = Math.abs(notional * dirtyPrice - dirtyExpected);
    final double dirtyRelativeError = Math.abs(dirtyAbsoluteError / dirtyExpected);
    
    final double cleanPercentage = 100.0 - (cleanPrice * 100.0);
    final double cleanExpected = testCase.getCleanPrice();
    final double cleanPercentageError = Math.abs(cleanPercentage - cleanExpected);
    
    return new TestResult(notional * dirtyPrice, dirtyExpected, dirtyAbsoluteError, dirtyRelativeError, cleanPercentage, cleanExpected, cleanPercentageError);
  }
  
  public ISDACurve buildCurve(final ISDAStagedCurve curveData, final String curveName) {
    
    // Expect all curve objects to use annual compounding
    // Assert.assertEquals(Double.valueOf(curveData.getBasis()), 1.0);
    
    final LocalDate effectiveDate = LocalDate.parse(curveData.getEffectiveDate(), formatter);
    final LocalDate baseDate = LocalDate.parse(curveData.getSpotDate(), formatter);
    final double offset = dayCount.getDayCountFraction(effectiveDate, baseDate);
    
    final int nPoints = curveData.getPoints().size();
    double times[] = new double[nPoints];
    double rates[] = new double[nPoints];
    
    LocalDate date;
    Double rate;
    int i = 0;
    
    for (ISDAStagedCurve.Point dataPoint : curveData.getPoints()) {
      date = LocalDate.parse(dataPoint.getDate(), formatter);
      rate = (new PeriodicInterestRate(Double.valueOf(dataPoint.getRate()),1)).toContinuous().getRate();
      times[i] = dayCount.getDayCountFraction(baseDate, date);
      rates[i++] = rate;
    }
    
    return new ISDACurve(curveName, times, rates, offset);
  }

}
