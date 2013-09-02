/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.livedata.cogda.msg;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.Bean;

/**
 * The common base class for any command sent by a client to a server
 * <em>after</em> the initial handshake.
 */
@BeanDefinition
public abstract class CogdaCommandMessage extends DirectBean {
  /**
   * A client-generated identifier to correlate request/response pairs.
   */
  @PropertyDefinition(validate = "notNull")
  private long _correlationId;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CogdaCommandMessage}.
   * @return the meta-bean, not null
   */
  public static CogdaCommandMessage.Meta meta() {
    return CogdaCommandMessage.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CogdaCommandMessage.Meta.INSTANCE);
  }

  @Override
  public CogdaCommandMessage.Meta metaBean() {
    return CogdaCommandMessage.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets a client-generated identifier to correlate request/response pairs.
   * @return the value of the property, not null
   */
  public long getCorrelationId() {
    return _correlationId;
  }

  /**
   * Sets a client-generated identifier to correlate request/response pairs.
   * @param correlationId  the new value of the property, not null
   */
  public void setCorrelationId(long correlationId) {
    JodaBeanUtils.notNull(correlationId, "correlationId");
    this._correlationId = correlationId;
  }

  /**
   * Gets the the {@code correlationId} property.
   * @return the property, not null
   */
  public final Property<Long> correlationId() {
    return metaBean().correlationId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public CogdaCommandMessage clone() {
    BeanBuilder<? extends CogdaCommandMessage> builder = metaBean().builder();
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
      CogdaCommandMessage other = (CogdaCommandMessage) obj;
      return (getCorrelationId() == other.getCorrelationId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getCorrelationId());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("CogdaCommandMessage{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("correlationId").append('=').append(getCorrelationId()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CogdaCommandMessage}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code correlationId} property.
     */
    private final MetaProperty<Long> _correlationId = DirectMetaProperty.ofReadWrite(
        this, "correlationId", CogdaCommandMessage.class, Long.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "correlationId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -764983747:  // correlationId
          return _correlationId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends CogdaCommandMessage> builder() {
      throw new UnsupportedOperationException("CogdaCommandMessage is an abstract class");
    }

    @Override
    public Class<? extends CogdaCommandMessage> beanType() {
      return CogdaCommandMessage.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code correlationId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Long> correlationId() {
      return _correlationId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -764983747:  // correlationId
          return ((CogdaCommandMessage) bean).getCorrelationId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -764983747:  // correlationId
          ((CogdaCommandMessage) bean).setCorrelationId((Long) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((CogdaCommandMessage) bean)._correlationId, "correlationId");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
