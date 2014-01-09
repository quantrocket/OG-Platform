/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.regression;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.opengamma.component.tool.AbstractTool;
import com.opengamma.integration.tool.DataTrackingToolContext;

/**
 * 
 */
public class GoldenCopyCreationTool extends AbstractTool<DataTrackingToolContext> {

  public static void main(String[] args) {
    new GoldenCopyCreationTool().initAndRun(args, DataTrackingToolContext.class);
    System.exit(0);
  }

  @Override
  protected void doRun() throws Exception {
    
    CommandLine commandLine = getCommandLine();

    String viewName = commandLine.getOptionValue("view-name");
    String snapshotName = commandLine.getOptionValue("snapshot-name");
    String outputDir = commandLine.getOptionValue("db-dump-output-dir");

    GoldenCopyCreator goldenCopyCreator = new GoldenCopyCreator(getToolContext());

    GoldenCopy goldenCopy = goldenCopyCreator.run(viewName, snapshotName, snapshotName);
    
    new GoldenCopyPersistenceHelper().save(goldenCopy);
    DataTrackingToolContext tc = getToolContext();
    GoldenCopyDumpCreator goldenCopyDumpCreator = new GoldenCopyDumpCreator(outputDir, 
        tc.getSecurityMaster(),
        tc.getPositionMaster(),
        tc.getPortfolioMaster(),
        tc.getConfigMaster(),
        tc.getHistoricalTimeSeriesMaster(),
        tc.getHolidayMaster(),
        tc.getExchangeMaster(),
        tc.getMarketDataSnapshotMaster(),
        tc.getOrganizationMaster());
    
    goldenCopyDumpCreator.execute();
    
  }

  @Override
  protected Options createOptions(boolean mandatoryConfig) {
    Options options = super.createOptions(mandatoryConfig);
    options.addOption(createViewOption());
    options.addOption(createSnapshotOption());
    options.addOption(createDbDumpOutputDirectory());
    return options;
  }

  @SuppressWarnings("static-access")
  private static Option createViewOption() {
    return OptionBuilder.isRequired(true)
        .hasArg(true)
        .withDescription("The view to create the golden copy for")
        .withLongOpt("view-name")
        .create("v");
  }

  @SuppressWarnings("static-access")
  private static Option createSnapshotOption() {
    return OptionBuilder.isRequired(true)
        .hasArg(true)
        .withDescription("The snapshot to run the view off")
        .withLongOpt("snapshot-name")
        .create("s");
  }

  @SuppressWarnings("static-access")
  private static Option createDbDumpOutputDirectory() {
    return OptionBuilder.isRequired(true)
        .hasArg(true)
        .withDescription("The snapshot to run the view off")
        .withLongOpt("db-dump-output-dir")
        .create("o");
  }

}
