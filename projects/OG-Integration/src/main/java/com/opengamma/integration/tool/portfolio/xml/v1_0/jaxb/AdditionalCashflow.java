/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.tool.portfolio.xml.v1_0.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.beans.BeanDefinition;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.threeten.bp.LocalDate;
import java.util.Map;
import org.joda.beans.BeanBuilder;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.Bean;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
@BeanDefinition
public class AdditionalCashflow extends DirectBean {

  public enum CashflowType {
    @XmlEnumValue(value = "premium")
    PREMIUM,
    @XmlEnumValue(value = "clearingFee")
    CLEARING_FEE,
    @XmlEnumValue(value = "other")
    OTHER
  }

  @XmlAttribute(name = "type")
  @PropertyDefinition
  private CashflowType _cashflowType;

  @XmlElement(name = "counterparty")
  @PropertyDefinition
  private IdWrapper _counterparty;

  @XmlElement(name = "monetaryAmount", required = true)
  @PropertyDefinition
  private MonetaryAmount _monetaryAmount;

  @XmlElement(name = "cashflowDate")
  @PropertyDefinition
  private LocalDate _cashflowDate;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AdditionalCashflow}.
   * @return the meta-bean, not null
   */
  public static AdditionalCashflow.Meta meta() {
    return AdditionalCashflow.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AdditionalCashflow.Meta.INSTANCE);
  }

  @Override
  public AdditionalCashflow.Meta metaBean() {
    return AdditionalCashflow.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cashflowType.
   * @return the value of the property
   */
  public CashflowType getCashflowType() {
    return _cashflowType;
  }

  /**
   * Sets the cashflowType.
   * @param cashflowType  the new value of the property
   */
  public void setCashflowType(CashflowType cashflowType) {
    this._cashflowType = cashflowType;
  }

  /**
   * Gets the the {@code cashflowType} property.
   * @return the property, not null
   */
  public final Property<CashflowType> cashflowType() {
    return metaBean().cashflowType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the counterparty.
   * @return the value of the property
   */
  public IdWrapper getCounterparty() {
    return _counterparty;
  }

  /**
   * Sets the counterparty.
   * @param counterparty  the new value of the property
   */
  public void setCounterparty(IdWrapper counterparty) {
    this._counterparty = counterparty;
  }

  /**
   * Gets the the {@code counterparty} property.
   * @return the property, not null
   */
  public final Property<IdWrapper> counterparty() {
    return metaBean().counterparty().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the monetaryAmount.
   * @return the value of the property
   */
  public MonetaryAmount getMonetaryAmount() {
    return _monetaryAmount;
  }

  /**
   * Sets the monetaryAmount.
   * @param monetaryAmount  the new value of the property
   */
  public void setMonetaryAmount(MonetaryAmount monetaryAmount) {
    this._monetaryAmount = monetaryAmount;
  }

  /**
   * Gets the the {@code monetaryAmount} property.
   * @return the property, not null
   */
  public final Property<MonetaryAmount> monetaryAmount() {
    return metaBean().monetaryAmount().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cashflowDate.
   * @return the value of the property
   */
  public LocalDate getCashflowDate() {
    return _cashflowDate;
  }

  /**
   * Sets the cashflowDate.
   * @param cashflowDate  the new value of the property
   */
  public void setCashflowDate(LocalDate cashflowDate) {
    this._cashflowDate = cashflowDate;
  }

  /**
   * Gets the the {@code cashflowDate} property.
   * @return the property, not null
   */
  public final Property<LocalDate> cashflowDate() {
    return metaBean().cashflowDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public AdditionalCashflow clone() {
    BeanBuilder<? extends AdditionalCashflow> builder = metaBean().builder();
    for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
      if (mp.readWrite().isWritable()) {
        Object value = mp.get(this);
        if (value instanceof Bean) {
          value = ((Bean) value).clone();
        }
        builder.set(mp.name(), value);
      }
    }
    return builder.build();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AdditionalCashflow other = (AdditionalCashflow) obj;
      return JodaBeanUtils.equal(getCashflowType(), other.getCashflowType()) &&
          JodaBeanUtils.equal(getCounterparty(), other.getCounterparty()) &&
          JodaBeanUtils.equal(getMonetaryAmount(), other.getMonetaryAmount()) &&
          JodaBeanUtils.equal(getCashflowDate(), other.getCashflowDate());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getCashflowType());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCounterparty());
    hash += hash * 31 + JodaBeanUtils.hashCode(getMonetaryAmount());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCashflowDate());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("AdditionalCashflow{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("cashflowType").append('=').append(getCashflowType()).append(',').append(' ');
    buf.append("counterparty").append('=').append(getCounterparty()).append(',').append(' ');
    buf.append("monetaryAmount").append('=').append(getMonetaryAmount()).append(',').append(' ');
    buf.append("cashflowDate").append('=').append(getCashflowDate()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AdditionalCashflow}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code cashflowType} property.
     */
    private final MetaProperty<CashflowType> _cashflowType = DirectMetaProperty.ofReadWrite(
        this, "cashflowType", AdditionalCashflow.class, CashflowType.class);
    /**
     * The meta-property for the {@code counterparty} property.
     */
    private final MetaProperty<IdWrapper> _counterparty = DirectMetaProperty.ofReadWrite(
        this, "counterparty", AdditionalCashflow.class, IdWrapper.class);
    /**
     * The meta-property for the {@code monetaryAmount} property.
     */
    private final MetaProperty<MonetaryAmount> _monetaryAmount = DirectMetaProperty.ofReadWrite(
        this, "monetaryAmount", AdditionalCashflow.class, MonetaryAmount.class);
    /**
     * The meta-property for the {@code cashflowDate} property.
     */
    private final MetaProperty<LocalDate> _cashflowDate = DirectMetaProperty.ofReadWrite(
        this, "cashflowDate", AdditionalCashflow.class, LocalDate.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "cashflowType",
        "counterparty",
        "monetaryAmount",
        "cashflowDate");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -702720581:  // cashflowType
          return _cashflowType;
        case -1651301782:  // counterparty
          return _counterparty;
        case 976931461:  // monetaryAmount
          return _monetaryAmount;
        case -703220177:  // cashflowDate
          return _cashflowDate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AdditionalCashflow> builder() {
      return new DirectBeanBuilder<AdditionalCashflow>(new AdditionalCashflow());
    }

    @Override
    public Class<? extends AdditionalCashflow> beanType() {
      return AdditionalCashflow.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code cashflowType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CashflowType> cashflowType() {
      return _cashflowType;
    }

    /**
     * The meta-property for the {@code counterparty} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<IdWrapper> counterparty() {
      return _counterparty;
    }

    /**
     * The meta-property for the {@code monetaryAmount} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MonetaryAmount> monetaryAmount() {
      return _monetaryAmount;
    }

    /**
     * The meta-property for the {@code cashflowDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> cashflowDate() {
      return _cashflowDate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -702720581:  // cashflowType
          return ((AdditionalCashflow) bean).getCashflowType();
        case -1651301782:  // counterparty
          return ((AdditionalCashflow) bean).getCounterparty();
        case 976931461:  // monetaryAmount
          return ((AdditionalCashflow) bean).getMonetaryAmount();
        case -703220177:  // cashflowDate
          return ((AdditionalCashflow) bean).getCashflowDate();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -702720581:  // cashflowType
          ((AdditionalCashflow) bean).setCashflowType((CashflowType) newValue);
          return;
        case -1651301782:  // counterparty
          ((AdditionalCashflow) bean).setCounterparty((IdWrapper) newValue);
          return;
        case 976931461:  // monetaryAmount
          ((AdditionalCashflow) bean).setMonetaryAmount((MonetaryAmount) newValue);
          return;
        case -703220177:  // cashflowDate
          ((AdditionalCashflow) bean).setCashflowDate((LocalDate) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
