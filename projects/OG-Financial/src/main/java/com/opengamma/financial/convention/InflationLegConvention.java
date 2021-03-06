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

import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;

/**
 * Convention for inflation swap legs.
 */
@BeanDefinition
public class InflationLegConvention extends Convention {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The business day convention.
   */
  @PropertyDefinition(validate = "notNull")
  private BusinessDayConvention _businessDayConvention;

  /**
   * The day count.
   */
  @PropertyDefinition(validate = "notNull")
  private DayCount _dayCount;

  /**
   * Should dates follow the end-of-month convention
   */
  @PropertyDefinition
  private boolean _isEOM;

  /**
   * The price index fixing lag in months.
   */
  @PropertyDefinition
  private int _monthLag;

  /**
   * The spot lag in days.
   */
  @PropertyDefinition
  private int _spotLag;

  /**
   * The price index.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _priceIndexConvention;

  /**
   * For the builder.
   */
  /* package */ InflationLegConvention() {
    super();
  }

  /**
   * @param name The convention name, not null
   * @param externalIdBundle The external identifiers for this convention, not null
   * @param businessDayConvention The business day convention, not null
   * @param dayCount The day-count, not null
   * @param isEOM True if dates follow the end-of-month convention
   * @param monthLag The price index fixing lag in months
   * @param spotLag The spot lag in days
   * @param priceIndexConvention The id of the price index convention, not null
   */
  public InflationLegConvention(final String name, final ExternalIdBundle externalIdBundle, final BusinessDayConvention businessDayConvention,
      final DayCount dayCount, final boolean isEOM, final int monthLag, final int spotLag, final ExternalId priceIndexConvention) {
    super(name, externalIdBundle);
    setBusinessDayConvention(businessDayConvention);
    setDayCount(dayCount);
    setIsEOM(isEOM);
    setMonthLag(monthLag);
    setSpotLag(spotLag);
    setPriceIndexConvention(priceIndexConvention);
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code InflationLegConvention}.
   * @return the meta-bean, not null
   */
  public static InflationLegConvention.Meta meta() {
    return InflationLegConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(InflationLegConvention.Meta.INSTANCE);
  }

  @Override
  public InflationLegConvention.Meta metaBean() {
    return InflationLegConvention.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1002835891:  // businessDayConvention
        return getBusinessDayConvention();
      case 1905311443:  // dayCount
        return getDayCount();
      case 100464505:  // isEOM
        return isIsEOM();
      case -319031566:  // monthLag
        return getMonthLag();
      case -1998751440:  // spotLag
        return getSpotLag();
      case 1501179226:  // priceIndexConvention
        return getPriceIndexConvention();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -1002835891:  // businessDayConvention
        setBusinessDayConvention((BusinessDayConvention) newValue);
        return;
      case 1905311443:  // dayCount
        setDayCount((DayCount) newValue);
        return;
      case 100464505:  // isEOM
        setIsEOM((Boolean) newValue);
        return;
      case -319031566:  // monthLag
        setMonthLag((Integer) newValue);
        return;
      case -1998751440:  // spotLag
        setSpotLag((Integer) newValue);
        return;
      case 1501179226:  // priceIndexConvention
        setPriceIndexConvention((ExternalId) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_businessDayConvention, "businessDayConvention");
    JodaBeanUtils.notNull(_dayCount, "dayCount");
    JodaBeanUtils.notNull(_priceIndexConvention, "priceIndexConvention");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      InflationLegConvention other = (InflationLegConvention) obj;
      return JodaBeanUtils.equal(getBusinessDayConvention(), other.getBusinessDayConvention()) &&
          JodaBeanUtils.equal(getDayCount(), other.getDayCount()) &&
          JodaBeanUtils.equal(isIsEOM(), other.isIsEOM()) &&
          JodaBeanUtils.equal(getMonthLag(), other.getMonthLag()) &&
          JodaBeanUtils.equal(getSpotLag(), other.getSpotLag()) &&
          JodaBeanUtils.equal(getPriceIndexConvention(), other.getPriceIndexConvention()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBusinessDayConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDayCount());
    hash += hash * 31 + JodaBeanUtils.hashCode(isIsEOM());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMonthLag());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSpotLag());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPriceIndexConvention());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the business day convention.
   * @return the value of the property, not null
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Sets the business day convention.
   * @param businessDayConvention  the new value of the property, not null
   */
  public void setBusinessDayConvention(BusinessDayConvention businessDayConvention) {
    JodaBeanUtils.notNull(businessDayConvention, "businessDayConvention");
    this._businessDayConvention = businessDayConvention;
  }

  /**
   * Gets the the {@code businessDayConvention} property.
   * @return the property, not null
   */
  public final Property<BusinessDayConvention> businessDayConvention() {
    return metaBean().businessDayConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the day count.
   * @return the value of the property, not null
   */
  public DayCount getDayCount() {
    return _dayCount;
  }

  /**
   * Sets the day count.
   * @param dayCount  the new value of the property, not null
   */
  public void setDayCount(DayCount dayCount) {
    JodaBeanUtils.notNull(dayCount, "dayCount");
    this._dayCount = dayCount;
  }

  /**
   * Gets the the {@code dayCount} property.
   * @return the property, not null
   */
  public final Property<DayCount> dayCount() {
    return metaBean().dayCount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets should dates follow the end-of-month convention
   * @return the value of the property
   */
  public boolean isIsEOM() {
    return _isEOM;
  }

  /**
   * Sets should dates follow the end-of-month convention
   * @param isEOM  the new value of the property
   */
  public void setIsEOM(boolean isEOM) {
    this._isEOM = isEOM;
  }

  /**
   * Gets the the {@code isEOM} property.
   * @return the property, not null
   */
  public final Property<Boolean> isEOM() {
    return metaBean().isEOM().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the price index fixing lag in months.
   * @return the value of the property
   */
  public int getMonthLag() {
    return _monthLag;
  }

  /**
   * Sets the price index fixing lag in months.
   * @param monthLag  the new value of the property
   */
  public void setMonthLag(int monthLag) {
    this._monthLag = monthLag;
  }

  /**
   * Gets the the {@code monthLag} property.
   * @return the property, not null
   */
  public final Property<Integer> monthLag() {
    return metaBean().monthLag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the spot lag in days.
   * @return the value of the property
   */
  public int getSpotLag() {
    return _spotLag;
  }

  /**
   * Sets the spot lag in days.
   * @param spotLag  the new value of the property
   */
  public void setSpotLag(int spotLag) {
    this._spotLag = spotLag;
  }

  /**
   * Gets the the {@code spotLag} property.
   * @return the property, not null
   */
  public final Property<Integer> spotLag() {
    return metaBean().spotLag().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the price index.
   * @return the value of the property, not null
   */
  public ExternalId getPriceIndexConvention() {
    return _priceIndexConvention;
  }

  /**
   * Sets the price index.
   * @param priceIndexConvention  the new value of the property, not null
   */
  public void setPriceIndexConvention(ExternalId priceIndexConvention) {
    JodaBeanUtils.notNull(priceIndexConvention, "priceIndexConvention");
    this._priceIndexConvention = priceIndexConvention;
  }

  /**
   * Gets the the {@code priceIndexConvention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> priceIndexConvention() {
    return metaBean().priceIndexConvention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code InflationLegConvention}.
   */
  public static class Meta extends Convention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code businessDayConvention} property.
     */
    private final MetaProperty<BusinessDayConvention> _businessDayConvention = DirectMetaProperty.ofReadWrite(
        this, "businessDayConvention", InflationLegConvention.class, BusinessDayConvention.class);
    /**
     * The meta-property for the {@code dayCount} property.
     */
    private final MetaProperty<DayCount> _dayCount = DirectMetaProperty.ofReadWrite(
        this, "dayCount", InflationLegConvention.class, DayCount.class);
    /**
     * The meta-property for the {@code isEOM} property.
     */
    private final MetaProperty<Boolean> _isEOM = DirectMetaProperty.ofReadWrite(
        this, "isEOM", InflationLegConvention.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code monthLag} property.
     */
    private final MetaProperty<Integer> _monthLag = DirectMetaProperty.ofReadWrite(
        this, "monthLag", InflationLegConvention.class, Integer.TYPE);
    /**
     * The meta-property for the {@code spotLag} property.
     */
    private final MetaProperty<Integer> _spotLag = DirectMetaProperty.ofReadWrite(
        this, "spotLag", InflationLegConvention.class, Integer.TYPE);
    /**
     * The meta-property for the {@code priceIndexConvention} property.
     */
    private final MetaProperty<ExternalId> _priceIndexConvention = DirectMetaProperty.ofReadWrite(
        this, "priceIndexConvention", InflationLegConvention.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "businessDayConvention",
        "dayCount",
        "isEOM",
        "monthLag",
        "spotLag",
        "priceIndexConvention");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1002835891:  // businessDayConvention
          return _businessDayConvention;
        case 1905311443:  // dayCount
          return _dayCount;
        case 100464505:  // isEOM
          return _isEOM;
        case -319031566:  // monthLag
          return _monthLag;
        case -1998751440:  // spotLag
          return _spotLag;
        case 1501179226:  // priceIndexConvention
          return _priceIndexConvention;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends InflationLegConvention> builder() {
      return new DirectBeanBuilder<InflationLegConvention>(new InflationLegConvention());
    }

    @Override
    public Class<? extends InflationLegConvention> beanType() {
      return InflationLegConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code businessDayConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BusinessDayConvention> businessDayConvention() {
      return _businessDayConvention;
    }

    /**
     * The meta-property for the {@code dayCount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DayCount> dayCount() {
      return _dayCount;
    }

    /**
     * The meta-property for the {@code isEOM} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> isEOM() {
      return _isEOM;
    }

    /**
     * The meta-property for the {@code monthLag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> monthLag() {
      return _monthLag;
    }

    /**
     * The meta-property for the {@code spotLag} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> spotLag() {
      return _spotLag;
    }

    /**
     * The meta-property for the {@code priceIndexConvention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> priceIndexConvention() {
      return _priceIndexConvention;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
