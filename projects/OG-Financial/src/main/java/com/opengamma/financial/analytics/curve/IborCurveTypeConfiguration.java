/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

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
import com.opengamma.util.time.Tenor;
import org.joda.beans.Bean;

/**
 * Configuration object for curves that are to be used as an ibor curve.
 */
@BeanDefinition
public class IborCurveTypeConfiguration extends CurveTypeConfiguration {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The convention of the index.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalId _convention;

  /**
   * The tenor of the index.
   */
  @PropertyDefinition(validate = "notNull")
  private Tenor _tenor;

  /**
   * For the builder.
   */
  /* package */ IborCurveTypeConfiguration() {
    super();
  }

  /**
   * @param convention The id of the index convention, not null
   * @param tenor The tenor of the index, not null
   */
  public IborCurveTypeConfiguration(final ExternalId convention, final Tenor tenor) {
    super();
    setConvention(convention);
    setTenor(tenor);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IborCurveTypeConfiguration}.
   * @return the meta-bean, not null
   */
  public static IborCurveTypeConfiguration.Meta meta() {
    return IborCurveTypeConfiguration.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IborCurveTypeConfiguration.Meta.INSTANCE);
  }

  @Override
  public IborCurveTypeConfiguration.Meta metaBean() {
    return IborCurveTypeConfiguration.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the convention of the index.
   * @return the value of the property, not null
   */
  public ExternalId getConvention() {
    return _convention;
  }

  /**
   * Sets the convention of the index.
   * @param convention  the new value of the property, not null
   */
  public void setConvention(ExternalId convention) {
    JodaBeanUtils.notNull(convention, "convention");
    this._convention = convention;
  }

  /**
   * Gets the the {@code convention} property.
   * @return the property, not null
   */
  public final Property<ExternalId> convention() {
    return metaBean().convention().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the tenor of the index.
   * @return the value of the property, not null
   */
  public Tenor getTenor() {
    return _tenor;
  }

  /**
   * Sets the tenor of the index.
   * @param tenor  the new value of the property, not null
   */
  public void setTenor(Tenor tenor) {
    JodaBeanUtils.notNull(tenor, "tenor");
    this._tenor = tenor;
  }

  /**
   * Gets the the {@code tenor} property.
   * @return the property, not null
   */
  public final Property<Tenor> tenor() {
    return metaBean().tenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public IborCurveTypeConfiguration clone() {
    return (IborCurveTypeConfiguration) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IborCurveTypeConfiguration other = (IborCurveTypeConfiguration) obj;
      return JodaBeanUtils.equal(getConvention(), other.getConvention()) &&
          JodaBeanUtils.equal(getTenor(), other.getTenor()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getConvention());
    hash += hash * 31 + JodaBeanUtils.hashCode(getTenor());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("IborCurveTypeConfiguration{");
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
    buf.append("convention").append('=').append(getConvention()).append(',').append(' ');
    buf.append("tenor").append('=').append(getTenor()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IborCurveTypeConfiguration}.
   */
  public static class Meta extends CurveTypeConfiguration.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code convention} property.
     */
    private final MetaProperty<ExternalId> _convention = DirectMetaProperty.ofReadWrite(
        this, "convention", IborCurveTypeConfiguration.class, ExternalId.class);
    /**
     * The meta-property for the {@code tenor} property.
     */
    private final MetaProperty<Tenor> _tenor = DirectMetaProperty.ofReadWrite(
        this, "tenor", IborCurveTypeConfiguration.class, Tenor.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "convention",
        "tenor");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 2039569265:  // convention
          return _convention;
        case 110246592:  // tenor
          return _tenor;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IborCurveTypeConfiguration> builder() {
      return new DirectBeanBuilder<IborCurveTypeConfiguration>(new IborCurveTypeConfiguration());
    }

    @Override
    public Class<? extends IborCurveTypeConfiguration> beanType() {
      return IborCurveTypeConfiguration.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code convention} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> convention() {
      return _convention;
    }

    /**
     * The meta-property for the {@code tenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Tenor> tenor() {
      return _tenor;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 2039569265:  // convention
          return ((IborCurveTypeConfiguration) bean).getConvention();
        case 110246592:  // tenor
          return ((IborCurveTypeConfiguration) bean).getTenor();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 2039569265:  // convention
          ((IborCurveTypeConfiguration) bean).setConvention((ExternalId) newValue);
          return;
        case 110246592:  // tenor
          ((IborCurveTypeConfiguration) bean).setTenor((Tenor) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((IborCurveTypeConfiguration) bean)._convention, "convention");
      JodaBeanUtils.notNull(((IborCurveTypeConfiguration) bean)._tenor, "tenor");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
