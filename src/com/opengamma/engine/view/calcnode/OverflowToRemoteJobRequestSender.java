/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view.calcnode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.Lifecycle;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.NamedThreadPoolFactory;

/**
 * An implementation of {@link JobRequestSender} which will overflow to another
 * {@link JobRequestSender} when its internal pool of worker threads is exhausted.
 *
 * @author kirk
 */
public class OverflowToRemoteJobRequestSender implements JobRequestSender, Lifecycle {
  private static final Logger s_logger = LoggerFactory.getLogger(OverflowToRemoteJobRequestSender.class);
  private final JobRequestSender _overflowSender;
  private final CalculationNodeRequestReceiver _calculationNode;
  private final ExecutorService _executorService;
  private final SynchronousQueue<Runnable> _offerQueue;
  
  public OverflowToRemoteJobRequestSender(
      JobRequestSender overflowSender,
      CalculationNodeRequestReceiver calculationNode,
      int nLocalWorkers) {
    ArgumentChecker.checkNotNull(overflowSender, "Overflow Sender");
    ArgumentChecker.checkNotNull(calculationNode, "Calculation node");
    Validate.isTrue(nLocalWorkers > 0, "Must specify a positive number of local workers.");
    
    _overflowSender = overflowSender;
    _calculationNode = calculationNode;
    
    ThreadFactory tf = new NamedThreadPoolFactory("InMemoryQueueByteArrayRequestConduit", true);
    _offerQueue = new SynchronousQueue<Runnable>();
    ThreadPoolExecutor executor = new ThreadPoolExecutor(0, nLocalWorkers, 5l, TimeUnit.SECONDS, _offerQueue, tf);
    _executorService = executor;
  }

  /**
   * @return the overflowSender
   */
  public JobRequestSender getOverflowSender() {
    return _overflowSender;
  }

  /**
   * @return the calculationNode
   */
  public CalculationNodeRequestReceiver getCalculationNode() {
    return _calculationNode;
  }

  @Override
  public void sendRequest(CalculationJob job, JobResultReceiver resultReceiver) {
    ArgumentChecker.checkNotNull(job, "Calculation Job");
    ArgumentChecker.checkNotNull(resultReceiver, "Job result receiver");
    
    Runnable runnable = new LocalDispatchRunnable(job, resultReceiver);
    if(!_offerQueue.offer(runnable)) {
      // Overflow.
      s_logger.debug("Overflowing job {}-{} to overflow sender", job.getSpecification().getViewName(), job.getSpecification().getJobId());
      getOverflowSender().sendRequest(job, resultReceiver);
    }
  }

  @Override
  public boolean isRunning() {
    return true;
  }

  @Override
  public void start() {
    // Intentional NO-OP.
  }

  @Override
  public void stop() {
    _executorService.shutdown();
  }
  
  private final class LocalDispatchRunnable implements Runnable {
    private final CalculationJob _job;
    private final JobResultReceiver _resultReceiver;
    
    public LocalDispatchRunnable(CalculationJob job, JobResultReceiver resultReceiver) {
      // Intentional assertion; private inner class.
      assert job != null;
      assert resultReceiver != null;
      
      _job = job;
      _resultReceiver = resultReceiver;
    }
    
    @Override
    public void run() {
      getCalculationNode().sendRequest(_job, _resultReceiver);
    }
    
  }
}
