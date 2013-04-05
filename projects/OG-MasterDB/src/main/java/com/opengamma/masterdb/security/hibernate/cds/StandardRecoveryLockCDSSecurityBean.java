/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.masterdb.security.hibernate.cds;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * 
 */
@BeanDefinition
public class StandardRecoveryLockCDSSecurityBean extends StandardCDSSecurityBean {

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code StandardRecoveryLockCDSSecurityBean}.
   * @return the meta-bean, not null
   */
  public static StandardRecoveryLockCDSSecurityBean.Meta meta() {
    return StandardRecoveryLockCDSSecurityBean.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(StandardRecoveryLockCDSSecurityBean.Meta.INSTANCE);
  }

  @Override
  public StandardRecoveryLockCDSSecurityBean.Meta metaBean() {
    return StandardRecoveryLockCDSSecurityBean.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
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

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code StandardRecoveryLockCDSSecurityBean}.
   */
  public static class Meta extends StandardCDSSecurityBean.Meta {
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
    public BeanBuilder<? extends StandardRecoveryLockCDSSecurityBean> builder() {
      return new DirectBeanBuilder<StandardRecoveryLockCDSSecurityBean>(new StandardRecoveryLockCDSSecurityBean());
    }

    @Override
    public Class<? extends StandardRecoveryLockCDSSecurityBean> beanType() {
      return StandardRecoveryLockCDSSecurityBean.class;
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