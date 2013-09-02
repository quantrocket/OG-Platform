/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.security.forward;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.security.FinancialSecurityVisitor;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Expiry;

/**
 * A security for agriculture futures.
 */
@BeanDefinition
public class AgricultureForwardSecurity extends CommodityForwardSecurity {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  AgricultureForwardSecurity() { //For builder
    super();
  }

  public AgricultureForwardSecurity(String unitName, Double unitNumber, Expiry expiry, Currency currency, double unitAmount, String category) {
    super(unitName, unitNumber, expiry, currency, unitAmount, category);
  }

  //-------------------------------------------------------------------------
  @Override
  public <T> T accept(FinancialSecurityVisitor<T> visitor) {
    return visitor.visitAgricultureForwardSecurity(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AgricultureForwardSecurity}.
   * @return the meta-bean, not null
   */
  public static AgricultureForwardSecurity.Meta meta() {
    return AgricultureForwardSecurity.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AgricultureForwardSecurity.Meta.INSTANCE);
  }

  @Override
  public AgricultureForwardSecurity.Meta metaBean() {
    return AgricultureForwardSecurity.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public AgricultureForwardSecurity clone() {
    return (AgricultureForwardSecurity) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("AgricultureForwardSecurity{");
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
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AgricultureForwardSecurity}.
   */
  public static class Meta extends CommodityForwardSecurity.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends AgricultureForwardSecurity> builder() {
      return new DirectBeanBuilder<AgricultureForwardSecurity>(new AgricultureForwardSecurity());
    }

    @Override
    public Class<? extends AgricultureForwardSecurity> beanType() {
      return AgricultureForwardSecurity.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
