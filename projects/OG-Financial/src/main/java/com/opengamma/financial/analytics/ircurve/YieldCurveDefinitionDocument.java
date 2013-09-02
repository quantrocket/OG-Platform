/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.io.Serializable;
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

import com.opengamma.id.UniqueId;
import com.opengamma.master.AbstractDocument;
import com.opengamma.util.ArgumentChecker;
import org.joda.beans.Bean;

/**
 * A document used to pass into and out of the yield curve definition master.
 */
@BeanDefinition
public class YieldCurveDefinitionDocument extends AbstractDocument implements Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The unique identifier.
   * This field is managed by the master but must be set for updates.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;
  /**
   * The yield curve definition.
   */
  @PropertyDefinition
  private YieldCurveDefinition _yieldCurveDefinition;

  /**
   * Creates an instance.
   */
  public YieldCurveDefinitionDocument() {
  }

  /**
   * Creates an instance.
   * 
   * @param uniqueId  the unique identifier, not null
   * @param yieldCurveDefinition  the yield curve definition, not null
   */
  public YieldCurveDefinitionDocument(final UniqueId uniqueId, final YieldCurveDefinition yieldCurveDefinition) {
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    ArgumentChecker.notNull(yieldCurveDefinition, "yieldCurveDefinition");
    setUniqueId(uniqueId);
    setYieldCurveDefinition(yieldCurveDefinition);
  }

  /**
   * Creates an instance.
   * This sets the unique identifier from the definition.
   * 
   * @param yieldCurveDefinition  the yield curve definition, not null
   */
  public YieldCurveDefinitionDocument(final YieldCurveDefinition yieldCurveDefinition) {
    ArgumentChecker.notNull(yieldCurveDefinition, "yieldCurveDefinition");
    setUniqueId(yieldCurveDefinition.getUniqueId());
    setYieldCurveDefinition(yieldCurveDefinition);
  }

  //-------------------------------------------------------------------------
  @Override
  public YieldCurveDefinition getValue() {
    return _yieldCurveDefinition;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code YieldCurveDefinitionDocument}.
   * @return the meta-bean, not null
   */
  public static YieldCurveDefinitionDocument.Meta meta() {
    return YieldCurveDefinitionDocument.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(YieldCurveDefinitionDocument.Meta.INSTANCE);
  }

  @Override
  public YieldCurveDefinitionDocument.Meta metaBean() {
    return YieldCurveDefinitionDocument.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier.
   * This field is managed by the master but must be set for updates.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique identifier.
   * This field is managed by the master but must be set for updates.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This field is managed by the master but must be set for updates.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the yield curve definition.
   * @return the value of the property
   */
  public YieldCurveDefinition getYieldCurveDefinition() {
    return _yieldCurveDefinition;
  }

  /**
   * Sets the yield curve definition.
   * @param yieldCurveDefinition  the new value of the property
   */
  public void setYieldCurveDefinition(YieldCurveDefinition yieldCurveDefinition) {
    this._yieldCurveDefinition = yieldCurveDefinition;
  }

  /**
   * Gets the the {@code yieldCurveDefinition} property.
   * @return the property, not null
   */
  public final Property<YieldCurveDefinition> yieldCurveDefinition() {
    return metaBean().yieldCurveDefinition().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public YieldCurveDefinitionDocument clone() {
    return (YieldCurveDefinitionDocument) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      YieldCurveDefinitionDocument other = (YieldCurveDefinitionDocument) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getYieldCurveDefinition(), other.getYieldCurveDefinition()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getYieldCurveDefinition());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("YieldCurveDefinitionDocument{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("uniqueId").append('=').append(getUniqueId()).append(',').append(' ');
    buf.append("yieldCurveDefinition").append('=').append(getYieldCurveDefinition()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code YieldCurveDefinitionDocument}.
   */
  public static class Meta extends AbstractDocument.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", YieldCurveDefinitionDocument.class, UniqueId.class);
    /**
     * The meta-property for the {@code yieldCurveDefinition} property.
     */
    private final MetaProperty<YieldCurveDefinition> _yieldCurveDefinition = DirectMetaProperty.ofReadWrite(
        this, "yieldCurveDefinition", YieldCurveDefinitionDocument.class, YieldCurveDefinition.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "uniqueId",
        "yieldCurveDefinition");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 36544597:  // yieldCurveDefinition
          return _yieldCurveDefinition;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends YieldCurveDefinitionDocument> builder() {
      return new DirectBeanBuilder<YieldCurveDefinitionDocument>(new YieldCurveDefinitionDocument());
    }

    @Override
    public Class<? extends YieldCurveDefinitionDocument> beanType() {
      return YieldCurveDefinitionDocument.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code yieldCurveDefinition} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<YieldCurveDefinition> yieldCurveDefinition() {
      return _yieldCurveDefinition;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((YieldCurveDefinitionDocument) bean).getUniqueId();
        case 36544597:  // yieldCurveDefinition
          return ((YieldCurveDefinitionDocument) bean).getYieldCurveDefinition();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          ((YieldCurveDefinitionDocument) bean).setUniqueId((UniqueId) newValue);
          return;
        case 36544597:  // yieldCurveDefinition
          ((YieldCurveDefinitionDocument) bean).setYieldCurveDefinition((YieldCurveDefinition) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
