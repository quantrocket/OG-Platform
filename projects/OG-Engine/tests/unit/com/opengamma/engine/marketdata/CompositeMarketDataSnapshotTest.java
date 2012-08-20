/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.marketdata;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.opengamma.engine.ComputationTargetType;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.id.UniqueId;

public class CompositeMarketDataSnapshotTest {

  private static final ValueRequirement REQUIREMENT1 = requirement("r1");
  private static final ValueRequirement REQUIREMENT2 = requirement("r2");
  private static final Object VALUE1 = new Object();
  private static final Object VALUE2 = new Object();
  private static final ValueRequirement UNKNOWN_REQUIREMENT = requirement("u");

  private MarketDataSnapshot _delegate1;
  private MarketDataSnapshot _delegate2;
  private CompositeMarketDataSnapshot _snapshot;

  @SuppressWarnings("unchecked")
  @BeforeMethod
  public void setUp() throws Exception {
    _delegate1 = mock(MarketDataSnapshot.class);
    _delegate2 = mock(MarketDataSnapshot.class);
    _snapshot = new CompositeMarketDataSnapshot(Lists.newArrayList(
        new UnderlyingSnapshot(_delegate1, Sets.newHashSet(REQUIREMENT1)),
        new UnderlyingSnapshot(_delegate2, Sets.newHashSet(REQUIREMENT2))));
    stub(_delegate1.query(REQUIREMENT1)).toReturn(VALUE1);
    stub(_delegate2.query(REQUIREMENT2)).toReturn(VALUE2);
    stub(_delegate1.query(Sets.newHashSet(REQUIREMENT1))).toReturn(ImmutableMap.of(REQUIREMENT1, VALUE1));
    stub(_delegate2.query(Sets.newHashSet(REQUIREMENT2))).toReturn(ImmutableMap.of(REQUIREMENT2, VALUE2));
  }

  @Test
  public void init() {
    // check all delegates are initialized
    _snapshot.init();
    verify(_delegate1).init();
    verify(_delegate2).init();
  }

  /**
   * initialize the snapshot with a set of requirements that is a subset of the requirements in the underlying snapshots
   */
  @SuppressWarnings("unchecked")
  @Test
  public void initMultiSubset() {
    // check all delegates are initialized with the appropriate subset of requirements
    Set<ValueRequirement> reqs = Sets.newHashSet(REQUIREMENT1, UNKNOWN_REQUIREMENT);
    _snapshot.init(reqs, 0, TimeUnit.MILLISECONDS);
    verify(_delegate1).init(Sets.newHashSet(REQUIREMENT1), 0, TimeUnit.MILLISECONDS);
    verify(_delegate2, never()).init();
    verify(_delegate2, never()).init(anySet(), anyLong(), (TimeUnit) anyObject());
  }

  /**
   * initialize the snapshot with a set of requirements that includes all the requirements in the underlying snapshots
   */
  @Test
  public void initMultiAll() {
    // check all delegates are initialized with the appropriate subset of requirements
    Set<ValueRequirement> reqs = Sets.newHashSet(REQUIREMENT1, REQUIREMENT2, UNKNOWN_REQUIREMENT);
    _snapshot.init(reqs, 0, TimeUnit.MILLISECONDS);
    verify(_delegate1).init(Sets.newHashSet(REQUIREMENT1), 0, TimeUnit.MILLISECONDS);
    verify(_delegate2).init(Sets.newHashSet(REQUIREMENT2), 0, TimeUnit.MILLISECONDS);
  }

  @Test
  public void queryOne() {
    // check a requirement is found from
    // * the first delegate
    // * another delegate
    // check null is returned for an unknown requirement
    assertEquals(VALUE1, _snapshot.query(REQUIREMENT1));
    assertEquals(VALUE2, _snapshot.query(REQUIREMENT2));
    assertNull(_snapshot.query(UNKNOWN_REQUIREMENT));
  }

  /**
   * query the snapshot with a set of requirements that is a subset of the requirements in the underlying snapshots
   */
  @Test
  public void queryMultiSubset() {
    Set<ValueRequirement> reqs = Sets.newHashSet(REQUIREMENT1, UNKNOWN_REQUIREMENT);
    Map<ValueRequirement, Object> result = _snapshot.query(reqs);
    assertEquals(VALUE1, result.get(REQUIREMENT1));
    assertNull(result.get(REQUIREMENT2));
    assertNull(result.get(UNKNOWN_REQUIREMENT));
  }

  /**
   * query the snapshot with a set of requirements that includes all the requirements in the underlying snapshots
   */
  @Test
  public void queryMultiAll() {
    Set<ValueRequirement> reqs = Sets.newHashSet(REQUIREMENT1, REQUIREMENT2, UNKNOWN_REQUIREMENT);
    Map<ValueRequirement, Object> result = _snapshot.query(reqs);
    assertEquals(VALUE1, result.get(REQUIREMENT1));
    assertEquals(VALUE2, result.get(REQUIREMENT2));
    assertNull(result.get(UNKNOWN_REQUIREMENT));
  }

  private static ValueRequirement requirement(String valueName) {
    return new ValueRequirement(valueName, ComputationTargetType.SECURITY, UniqueId.of("scheme", "value"));
  }
}
