/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.livedata.rest;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.util.PublicSPI;
import org.joda.beans.Bean;

/**
 *
 */
@PublicSPI
@BeanDefinition
public class RemoveValueRequest extends DirectBean {

  @PropertyDefinition
  private ValueRequirement _valueRequirement;

  @PropertyDefinition
  private ValueSpecification _valueSpecification;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RemoveValueRequest}.
   * @return the meta-bean, not null
   */
  public static RemoveValueRequest.Meta meta() {
    return RemoveValueRequest.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(RemoveValueRequest.Meta.INSTANCE);
  }

  @Override
  public RemoveValueRequest.Meta metaBean() {
    return RemoveValueRequest.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valueRequirement.
   * @return the value of the property
   */
  public ValueRequirement getValueRequirement() {
    return _valueRequirement;
  }

  /**
   * Sets the valueRequirement.
   * @param valueRequirement  the new value of the property
   */
  public void setValueRequirement(ValueRequirement valueRequirement) {
    this._valueRequirement = valueRequirement;
  }

  /**
   * Gets the the {@code valueRequirement} property.
   * @return the property, not null
   */
  public final Property<ValueRequirement> valueRequirement() {
    return metaBean().valueRequirement().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valueSpecification.
   * @return the value of the property
   */
  public ValueSpecification getValueSpecification() {
    return _valueSpecification;
  }

  /**
   * Sets the valueSpecification.
   * @param valueSpecification  the new value of the property
   */
  public void setValueSpecification(ValueSpecification valueSpecification) {
    this._valueSpecification = valueSpecification;
  }

  /**
   * Gets the the {@code valueSpecification} property.
   * @return the property, not null
   */
  public final Property<ValueSpecification> valueSpecification() {
    return metaBean().valueSpecification().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public RemoveValueRequest clone() {
    BeanBuilder<? extends RemoveValueRequest> builder = metaBean().builder();
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
      RemoveValueRequest other = (RemoveValueRequest) obj;
      return JodaBeanUtils.equal(getValueRequirement(), other.getValueRequirement()) &&
          JodaBeanUtils.equal(getValueSpecification(), other.getValueSpecification());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getValueRequirement());
    hash += hash * 31 + JodaBeanUtils.hashCode(getValueSpecification());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("RemoveValueRequest{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("valueRequirement").append('=').append(getValueRequirement()).append(',').append(' ');
    buf.append("valueSpecification").append('=').append(getValueSpecification()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RemoveValueRequest}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code valueRequirement} property.
     */
    private final MetaProperty<ValueRequirement> _valueRequirement = DirectMetaProperty.ofReadWrite(
        this, "valueRequirement", RemoveValueRequest.class, ValueRequirement.class);
    /**
     * The meta-property for the {@code valueSpecification} property.
     */
    private final MetaProperty<ValueSpecification> _valueSpecification = DirectMetaProperty.ofReadWrite(
        this, "valueSpecification", RemoveValueRequest.class, ValueSpecification.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "valueRequirement",
        "valueSpecification");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -755281390:  // valueRequirement
          return _valueRequirement;
        case 7765778:  // valueSpecification
          return _valueSpecification;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends RemoveValueRequest> builder() {
      return new DirectBeanBuilder<RemoveValueRequest>(new RemoveValueRequest());
    }

    @Override
    public Class<? extends RemoveValueRequest> beanType() {
      return RemoveValueRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code valueRequirement} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ValueRequirement> valueRequirement() {
      return _valueRequirement;
    }

    /**
     * The meta-property for the {@code valueSpecification} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ValueSpecification> valueSpecification() {
      return _valueSpecification;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -755281390:  // valueRequirement
          return ((RemoveValueRequest) bean).getValueRequirement();
        case 7765778:  // valueSpecification
          return ((RemoveValueRequest) bean).getValueSpecification();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -755281390:  // valueRequirement
          ((RemoveValueRequest) bean).setValueRequirement((ValueRequirement) newValue);
          return;
        case 7765778:  // valueSpecification
          ((RemoveValueRequest) bean).setValueSpecification((ValueSpecification) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
