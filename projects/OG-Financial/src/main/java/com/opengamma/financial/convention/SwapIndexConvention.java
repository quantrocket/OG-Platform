/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import org.threeten.bp.LocalTime;

import com.opengamma.id.ExternalIdBundle;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 *
 */
@BeanDefinition
public class SwapIndexConvention extends Convention {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The fixing time.
   */
  @PropertyDefinition(validate = "notNull")
  private LocalTime _fixingTime;

  /**
   * The fixed leg convention name.
   */
  //TODO is this the best type?
  @PropertyDefinition(validate = "notNull")
  private String _fixedLegConventionName;

  /**
   * The floating leg convention name.
   */
  //TODO is this the best type?
  @PropertyDefinition(validate = "notNull")
  private String _floatingLegConventionName;

  /**
   * For the builder.
   */
  public SwapIndexConvention() {
    super();
  }

  public SwapIndexConvention(final String name, final ExternalIdBundle externalIdBundle, final LocalTime fixingTime, final String fixedLegConventionName,
      final String floatingLegConventionName) {
    super(name, externalIdBundle);
    setFixingTime(fixingTime);
    setFixedLegConventionName(fixedLegConventionName);
    setFloatingLegConventionName(floatingLegConventionName);
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SwapIndexConvention}.
   * @return the meta-bean, not null
   */
  public static SwapIndexConvention.Meta meta() {
    return SwapIndexConvention.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(SwapIndexConvention.Meta.INSTANCE);
  }

  @Override
  public SwapIndexConvention.Meta metaBean() {
    return SwapIndexConvention.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1255686170:  // fixingTime
        return getFixingTime();
      case 1936234038:  // fixedLegConventionName
        return getFixedLegConventionName();
      case 2063494116:  // floatingLegConventionName
        return getFloatingLegConventionName();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1255686170:  // fixingTime
        setFixingTime((LocalTime) newValue);
        return;
      case 1936234038:  // fixedLegConventionName
        setFixedLegConventionName((String) newValue);
        return;
      case 2063494116:  // floatingLegConventionName
        setFloatingLegConventionName((String) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_fixingTime, "fixingTime");
    JodaBeanUtils.notNull(_fixedLegConventionName, "fixedLegConventionName");
    JodaBeanUtils.notNull(_floatingLegConventionName, "floatingLegConventionName");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SwapIndexConvention other = (SwapIndexConvention) obj;
      return JodaBeanUtils.equal(getFixingTime(), other.getFixingTime()) &&
          JodaBeanUtils.equal(getFixedLegConventionName(), other.getFixedLegConventionName()) &&
          JodaBeanUtils.equal(getFloatingLegConventionName(), other.getFloatingLegConventionName()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getFixingTime());
    hash += hash * 31 + JodaBeanUtils.hashCode(getFixedLegConventionName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getFloatingLegConventionName());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the fixing time.
   * @return the value of the property, not null
   */
  public LocalTime getFixingTime() {
    return _fixingTime;
  }

  /**
   * Sets the fixing time.
   * @param fixingTime  the new value of the property, not null
   */
  public void setFixingTime(LocalTime fixingTime) {
    JodaBeanUtils.notNull(fixingTime, "fixingTime");
    this._fixingTime = fixingTime;
  }

  /**
   * Gets the the {@code fixingTime} property.
   * @return the property, not null
   */
  public final Property<LocalTime> fixingTime() {
    return metaBean().fixingTime().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the fixedLegConventionName.
   * @return the value of the property, not null
   */
  public String getFixedLegConventionName() {
    return _fixedLegConventionName;
  }

  /**
   * Sets the fixedLegConventionName.
   * @param fixedLegConventionName  the new value of the property, not null
   */
  public void setFixedLegConventionName(String fixedLegConventionName) {
    JodaBeanUtils.notNull(fixedLegConventionName, "fixedLegConventionName");
    this._fixedLegConventionName = fixedLegConventionName;
  }

  /**
   * Gets the the {@code fixedLegConventionName} property.
   * @return the property, not null
   */
  public final Property<String> fixedLegConventionName() {
    return metaBean().fixedLegConventionName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the floatingLegConventionName.
   * @return the value of the property, not null
   */
  public String getFloatingLegConventionName() {
    return _floatingLegConventionName;
  }

  /**
   * Sets the floatingLegConventionName.
   * @param floatingLegConventionName  the new value of the property, not null
   */
  public void setFloatingLegConventionName(String floatingLegConventionName) {
    JodaBeanUtils.notNull(floatingLegConventionName, "floatingLegConventionName");
    this._floatingLegConventionName = floatingLegConventionName;
  }

  /**
   * Gets the the {@code floatingLegConventionName} property.
   * @return the property, not null
   */
  public final Property<String> floatingLegConventionName() {
    return metaBean().floatingLegConventionName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SwapIndexConvention}.
   */
  public static class Meta extends Convention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code fixingTime} property.
     */
    private final MetaProperty<LocalTime> _fixingTime = DirectMetaProperty.ofReadWrite(
        this, "fixingTime", SwapIndexConvention.class, LocalTime.class);
    /**
     * The meta-property for the {@code fixedLegConventionName} property.
     */
    private final MetaProperty<String> _fixedLegConventionName = DirectMetaProperty.ofReadWrite(
        this, "fixedLegConventionName", SwapIndexConvention.class, String.class);
    /**
     * The meta-property for the {@code floatingLegConventionName} property.
     */
    private final MetaProperty<String> _floatingLegConventionName = DirectMetaProperty.ofReadWrite(
        this, "floatingLegConventionName", SwapIndexConvention.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "fixingTime",
        "fixedLegConventionName",
        "floatingLegConventionName");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1255686170:  // fixingTime
          return _fixingTime;
        case 1936234038:  // fixedLegConventionName
          return _fixedLegConventionName;
        case 2063494116:  // floatingLegConventionName
          return _floatingLegConventionName;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SwapIndexConvention> builder() {
      return new DirectBeanBuilder<SwapIndexConvention>(new SwapIndexConvention());
    }

    @Override
    public Class<? extends SwapIndexConvention> beanType() {
      return SwapIndexConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code fixingTime} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalTime> fixingTime() {
      return _fixingTime;
    }

    /**
     * The meta-property for the {@code fixedLegConventionName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> fixedLegConventionName() {
      return _fixedLegConventionName;
    }

    /**
     * The meta-property for the {@code floatingLegConventionName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> floatingLegConventionName() {
      return _floatingLegConventionName;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
