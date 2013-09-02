/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.masterdb.security.hibernate.option;

import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.security.option.IRFutureOptionSecurity;
import com.opengamma.financial.security.option.OptionType;
import com.opengamma.masterdb.security.hibernate.CurrencyBean;
import com.opengamma.masterdb.security.hibernate.ExchangeBean;
import com.opengamma.masterdb.security.hibernate.ExpiryBean;
import com.opengamma.masterdb.security.hibernate.ExternalIdBean;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import org.joda.beans.Bean;

/**
 * A Hibernate bean representation of {@link IRFutureOptionSecurity}.
 */
@BeanDefinition
public class IRFutureOptionSecurityBean extends SecurityBean {
  @PropertyDefinition
  private OptionExerciseType _optionExerciseType;
  @PropertyDefinition
  private OptionType _optionType;
  @PropertyDefinition
  private double _strike;
  @PropertyDefinition
  private ExpiryBean _expiry;
  @PropertyDefinition
  private CurrencyBean _currency;
  @PropertyDefinition
  private ExchangeBean _exchange;
  @PropertyDefinition
  private Boolean _margined;
  @PropertyDefinition
  private Double _pointValue;
  @PropertyDefinition
  private ExternalIdBean _underlying;

  public IRFutureOptionSecurityBean() {
    super();
  }

  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof IRFutureOptionSecurityBean)) {
      return false;
    }
    final IRFutureOptionSecurityBean option = (IRFutureOptionSecurityBean) other;
    return new EqualsBuilder()
      .append(getId(), option.getId())
      .append(getOptionType(), option.getOptionType())
      .append(getStrike(), option.getStrike())
      .append(getExpiry(), option.getExpiry())
      .append(getUnderlying(), option.getUnderlying())
      .append(getCurrency(), option.getCurrency())
      .append(getExchange(), option.getExchange())
      .append(getMargined(), option.getMargined())
      .append(getPointValue(), option.getPointValue())
      .append(getOptionExerciseType(), option.getOptionExerciseType())
      .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(getOptionType())
      .append(getStrike())
      .append(getExpiry())
      .append(getUnderlying())
      .append(getCurrency())
      .append(getExchange())
      .append(getMargined())
      .append(getPointValue())
      .append(getOptionExerciseType())
      .toHashCode();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IRFutureOptionSecurityBean}.
   * @return the meta-bean, not null
   */
  public static IRFutureOptionSecurityBean.Meta meta() {
    return IRFutureOptionSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IRFutureOptionSecurityBean.Meta.INSTANCE);
  }

  @Override
  public IRFutureOptionSecurityBean.Meta metaBean() {
    return IRFutureOptionSecurityBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the optionExerciseType.
   * @return the value of the property
   */
  public OptionExerciseType getOptionExerciseType() {
    return _optionExerciseType;
  }

  /**
   * Sets the optionExerciseType.
   * @param optionExerciseType  the new value of the property
   */
  public void setOptionExerciseType(OptionExerciseType optionExerciseType) {
    this._optionExerciseType = optionExerciseType;
  }

  /**
   * Gets the the {@code optionExerciseType} property.
   * @return the property, not null
   */
  public final Property<OptionExerciseType> optionExerciseType() {
    return metaBean().optionExerciseType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the optionType.
   * @return the value of the property
   */
  public OptionType getOptionType() {
    return _optionType;
  }

  /**
   * Sets the optionType.
   * @param optionType  the new value of the property
   */
  public void setOptionType(OptionType optionType) {
    this._optionType = optionType;
  }

  /**
   * Gets the the {@code optionType} property.
   * @return the property, not null
   */
  public final Property<OptionType> optionType() {
    return metaBean().optionType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike.
   * @return the value of the property
   */
  public double getStrike() {
    return _strike;
  }

  /**
   * Sets the strike.
   * @param strike  the new value of the property
   */
  public void setStrike(double strike) {
    this._strike = strike;
  }

  /**
   * Gets the the {@code strike} property.
   * @return the property, not null
   */
  public final Property<Double> strike() {
    return metaBean().strike().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry.
   * @return the value of the property
   */
  public ExpiryBean getExpiry() {
    return _expiry;
  }

  /**
   * Sets the expiry.
   * @param expiry  the new value of the property
   */
  public void setExpiry(ExpiryBean expiry) {
    this._expiry = expiry;
  }

  /**
   * Gets the the {@code expiry} property.
   * @return the property, not null
   */
  public final Property<ExpiryBean> expiry() {
    return metaBean().expiry().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property
   */
  public CurrencyBean getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property
   */
  public void setCurrency(CurrencyBean currency) {
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<CurrencyBean> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exchange.
   * @return the value of the property
   */
  public ExchangeBean getExchange() {
    return _exchange;
  }

  /**
   * Sets the exchange.
   * @param exchange  the new value of the property
   */
  public void setExchange(ExchangeBean exchange) {
    this._exchange = exchange;
  }

  /**
   * Gets the the {@code exchange} property.
   * @return the property, not null
   */
  public final Property<ExchangeBean> exchange() {
    return metaBean().exchange().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the margined.
   * @return the value of the property
   */
  public Boolean getMargined() {
    return _margined;
  }

  /**
   * Sets the margined.
   * @param margined  the new value of the property
   */
  public void setMargined(Boolean margined) {
    this._margined = margined;
  }

  /**
   * Gets the the {@code margined} property.
   * @return the property, not null
   */
  public final Property<Boolean> margined() {
    return metaBean().margined().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the pointValue.
   * @return the value of the property
   */
  public Double getPointValue() {
    return _pointValue;
  }

  /**
   * Sets the pointValue.
   * @param pointValue  the new value of the property
   */
  public void setPointValue(Double pointValue) {
    this._pointValue = pointValue;
  }

  /**
   * Gets the the {@code pointValue} property.
   * @return the property, not null
   */
  public final Property<Double> pointValue() {
    return metaBean().pointValue().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying.
   * @return the value of the property
   */
  public ExternalIdBean getUnderlying() {
    return _underlying;
  }

  /**
   * Sets the underlying.
   * @param underlying  the new value of the property
   */
  public void setUnderlying(ExternalIdBean underlying) {
    this._underlying = underlying;
  }

  /**
   * Gets the the {@code underlying} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBean> underlying() {
    return metaBean().underlying().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public IRFutureOptionSecurityBean clone() {
    return (IRFutureOptionSecurityBean) super.clone();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IRFutureOptionSecurityBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code optionExerciseType} property.
     */
    private final MetaProperty<OptionExerciseType> _optionExerciseType = DirectMetaProperty.ofReadWrite(
        this, "optionExerciseType", IRFutureOptionSecurityBean.class, OptionExerciseType.class);
    /**
     * The meta-property for the {@code optionType} property.
     */
    private final MetaProperty<OptionType> _optionType = DirectMetaProperty.ofReadWrite(
        this, "optionType", IRFutureOptionSecurityBean.class, OptionType.class);
    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<Double> _strike = DirectMetaProperty.ofReadWrite(
        this, "strike", IRFutureOptionSecurityBean.class, Double.TYPE);
    /**
     * The meta-property for the {@code expiry} property.
     */
    private final MetaProperty<ExpiryBean> _expiry = DirectMetaProperty.ofReadWrite(
        this, "expiry", IRFutureOptionSecurityBean.class, ExpiryBean.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<CurrencyBean> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", IRFutureOptionSecurityBean.class, CurrencyBean.class);
    /**
     * The meta-property for the {@code exchange} property.
     */
    private final MetaProperty<ExchangeBean> _exchange = DirectMetaProperty.ofReadWrite(
        this, "exchange", IRFutureOptionSecurityBean.class, ExchangeBean.class);
    /**
     * The meta-property for the {@code margined} property.
     */
    private final MetaProperty<Boolean> _margined = DirectMetaProperty.ofReadWrite(
        this, "margined", IRFutureOptionSecurityBean.class, Boolean.class);
    /**
     * The meta-property for the {@code pointValue} property.
     */
    private final MetaProperty<Double> _pointValue = DirectMetaProperty.ofReadWrite(
        this, "pointValue", IRFutureOptionSecurityBean.class, Double.class);
    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<ExternalIdBean> _underlying = DirectMetaProperty.ofReadWrite(
        this, "underlying", IRFutureOptionSecurityBean.class, ExternalIdBean.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "optionExerciseType",
        "optionType",
        "strike",
        "expiry",
        "currency",
        "exchange",
        "margined",
        "pointValue",
        "underlying");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -266326457:  // optionExerciseType
          return _optionExerciseType;
        case 1373587791:  // optionType
          return _optionType;
        case -891985998:  // strike
          return _strike;
        case -1289159373:  // expiry
          return _expiry;
        case 575402001:  // currency
          return _currency;
        case 1989774883:  // exchange
          return _exchange;
        case 243392205:  // margined
          return _margined;
        case 1257391553:  // pointValue
          return _pointValue;
        case -1770633379:  // underlying
          return _underlying;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IRFutureOptionSecurityBean> builder() {
      return new DirectBeanBuilder<IRFutureOptionSecurityBean>(new IRFutureOptionSecurityBean());
    }

    @Override
    public Class<? extends IRFutureOptionSecurityBean> beanType() {
      return IRFutureOptionSecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code optionExerciseType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<OptionExerciseType> optionExerciseType() {
      return _optionExerciseType;
    }

    /**
     * The meta-property for the {@code optionType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<OptionType> optionType() {
      return _optionType;
    }

    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> strike() {
      return _strike;
    }

    /**
     * The meta-property for the {@code expiry} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExpiryBean> expiry() {
      return _expiry;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CurrencyBean> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code exchange} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExchangeBean> exchange() {
      return _exchange;
    }

    /**
     * The meta-property for the {@code margined} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> margined() {
      return _margined;
    }

    /**
     * The meta-property for the {@code pointValue} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> pointValue() {
      return _pointValue;
    }

    /**
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBean> underlying() {
      return _underlying;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -266326457:  // optionExerciseType
          return ((IRFutureOptionSecurityBean) bean).getOptionExerciseType();
        case 1373587791:  // optionType
          return ((IRFutureOptionSecurityBean) bean).getOptionType();
        case -891985998:  // strike
          return ((IRFutureOptionSecurityBean) bean).getStrike();
        case -1289159373:  // expiry
          return ((IRFutureOptionSecurityBean) bean).getExpiry();
        case 575402001:  // currency
          return ((IRFutureOptionSecurityBean) bean).getCurrency();
        case 1989774883:  // exchange
          return ((IRFutureOptionSecurityBean) bean).getExchange();
        case 243392205:  // margined
          return ((IRFutureOptionSecurityBean) bean).getMargined();
        case 1257391553:  // pointValue
          return ((IRFutureOptionSecurityBean) bean).getPointValue();
        case -1770633379:  // underlying
          return ((IRFutureOptionSecurityBean) bean).getUnderlying();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -266326457:  // optionExerciseType
          ((IRFutureOptionSecurityBean) bean).setOptionExerciseType((OptionExerciseType) newValue);
          return;
        case 1373587791:  // optionType
          ((IRFutureOptionSecurityBean) bean).setOptionType((OptionType) newValue);
          return;
        case -891985998:  // strike
          ((IRFutureOptionSecurityBean) bean).setStrike((Double) newValue);
          return;
        case -1289159373:  // expiry
          ((IRFutureOptionSecurityBean) bean).setExpiry((ExpiryBean) newValue);
          return;
        case 575402001:  // currency
          ((IRFutureOptionSecurityBean) bean).setCurrency((CurrencyBean) newValue);
          return;
        case 1989774883:  // exchange
          ((IRFutureOptionSecurityBean) bean).setExchange((ExchangeBean) newValue);
          return;
        case 243392205:  // margined
          ((IRFutureOptionSecurityBean) bean).setMargined((Boolean) newValue);
          return;
        case 1257391553:  // pointValue
          ((IRFutureOptionSecurityBean) bean).setPointValue((Double) newValue);
          return;
        case -1770633379:  // underlying
          ((IRFutureOptionSecurityBean) bean).setUnderlying((ExternalIdBean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
