/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;

/**
 * Convention for FX spot.
 */
@BeanDefinition
public class FXSpotConvention extends Convention {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The number of settlement days.
   */
  @PropertyDefinition
  private int _settlementDays;

  /**
   * The settlement region.
   */
  @PropertyDefinition
  private ExternalId _settlementRegion;

  /**
   * For the builder.
   */
  /* package */ FXSpotConvention() {
  }

  /**
   * @param name The convention name, not null
   * @param externalIdBundle The external identifiers for this convention, not null
   * @param settlementDays The number of settlement days
   * @param settlementRegion The settlement region, can be null
   */
  public FXSpotConvention(final String name, final ExternalIdBundle externalIdBundle, final int settlementDays, final ExternalId settlementRegion) {
    super(name, externalIdBundle);
    setSettlementDays(settlementDays);
    setSettlementRegion(settlementRegion);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FXSpotConvention}.
   * @return the meta-bean, not null
   */
  public static FXSpotConvention.Meta meta() {
    return FXSpotConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FXSpotConvention.Meta.INSTANCE);
  }

  @Override
  public FXSpotConvention.Meta metaBean() {
    return FXSpotConvention.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -295948000:  // settlementDays
        return getSettlementDays();
      case -534226563:  // settlementRegion
        return getSettlementRegion();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -295948000:  // settlementDays
        setSettlementDays((Integer) newValue);
        return;
      case -534226563:  // settlementRegion
        setSettlementRegion((ExternalId) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FXSpotConvention other = (FXSpotConvention) obj;
      return JodaBeanUtils.equal(getSettlementDays(), other.getSettlementDays()) &&
          JodaBeanUtils.equal(getSettlementRegion(), other.getSettlementRegion()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementDays());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSettlementRegion());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the number of settlement days.
   * @return the value of the property
   */
  public int getSettlementDays() {
    return _settlementDays;
  }

  /**
   * Sets the number of settlement days.
   * @param settlementDays  the new value of the property
   */
  public void setSettlementDays(int settlementDays) {
    this._settlementDays = settlementDays;
  }

  /**
   * Gets the the {@code settlementDays} property.
   * @return the property, not null
   */
  public final Property<Integer> settlementDays() {
    return metaBean().settlementDays().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the settlement region.
   * @return the value of the property
   */
  public ExternalId getSettlementRegion() {
    return _settlementRegion;
  }

  /**
   * Sets the settlement region.
   * @param settlementRegion  the new value of the property
   */
  public void setSettlementRegion(ExternalId settlementRegion) {
    this._settlementRegion = settlementRegion;
  }

  /**
   * Gets the the {@code settlementRegion} property.
   * @return the property, not null
   */
  public final Property<ExternalId> settlementRegion() {
    return metaBean().settlementRegion().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FXSpotConvention}.
   */
  public static class Meta extends Convention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code settlementDays} property.
     */
    private final MetaProperty<Integer> _settlementDays = DirectMetaProperty.ofReadWrite(
        this, "settlementDays", FXSpotConvention.class, Integer.TYPE);
    /**
     * The meta-property for the {@code settlementRegion} property.
     */
    private final MetaProperty<ExternalId> _settlementRegion = DirectMetaProperty.ofReadWrite(
        this, "settlementRegion", FXSpotConvention.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "settlementDays",
        "settlementRegion");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -295948000:  // settlementDays
          return _settlementDays;
        case -534226563:  // settlementRegion
          return _settlementRegion;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FXSpotConvention> builder() {
      return new DirectBeanBuilder<FXSpotConvention>(new FXSpotConvention());
    }

    @Override
    public Class<? extends FXSpotConvention> beanType() {
      return FXSpotConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code settlementDays} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> settlementDays() {
      return _settlementDays;
    }

    /**
     * The meta-property for the {@code settlementRegion} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> settlementRegion() {
      return _settlementRegion;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
