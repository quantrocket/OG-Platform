/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.bbg.referencedata;

import java.util.List;
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

import com.google.common.collect.Lists;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;
import org.joda.beans.Bean;

/**
 * Result from getting reference data.
 * <p>
 * This class is mutable and not thread-safe.
 */
@PublicSPI
@BeanDefinition
public class ReferenceDataProviderGetResult extends DirectBean {

  /**
   * The reference data that was obtained, one entry for each identifier.
   */
  @PropertyDefinition
  private final List<ReferenceData> _referenceData = Lists.newArrayList();

  /**
   * Creates an instance.
   */
  public ReferenceDataProviderGetResult() {
  }

  /**
   * Creates an instance.
   * 
   * @param results  the results, not null
   */
  public ReferenceDataProviderGetResult(List<ReferenceData> results) {
    setReferenceData(results);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the reference data for the specified identifier.
   * 
   * @param identifier  the identifier to find, not null
   * @return the reference data for the identifier, not null
   * @throws IllegalArgumentException if the identifier is not stored
   */
  public ReferenceData getReferenceData(String identifier) {
    ReferenceData referenceData = getReferenceDataOrNull(identifier);
    if (referenceData == null) {
      throw new IllegalArgumentException("Reference data not found: " + identifier);
    }
    return referenceData;
  }

  /**
   * Gets the reference data for the specified identifier, null if not found.
   * 
   * @param identifier  the identifier to find, not null
   * @return the reference data for the identifier, null if not found
   */
  public ReferenceData getReferenceDataOrNull(String identifier) {
    for (ReferenceData rd : getReferenceData()) {
      if (rd.getIdentifier().equals(identifier)) {
        return rd;
      }
    }
    return null;
  }

  /**
   * Adds reference data to the list contained.
   * 
   * @param referenceData  the reference data to add, not null
   * @throws IllegalArgumentException if the identifier is already stored
   */
  public void addReferenceData(ReferenceData referenceData) {
    ArgumentChecker.notNull(referenceData, "referenceData");
    if (getReferenceDataOrNull(referenceData.getIdentifier()) != null) {
      throw new IllegalArgumentException("Reference data already added for identifier: " + referenceData.getIdentifier());
    }
    getReferenceData().add(referenceData);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ReferenceDataProviderGetResult}.
   * @return the meta-bean, not null
   */
  public static ReferenceDataProviderGetResult.Meta meta() {
    return ReferenceDataProviderGetResult.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ReferenceDataProviderGetResult.Meta.INSTANCE);
  }

  @Override
  public ReferenceDataProviderGetResult.Meta metaBean() {
    return ReferenceDataProviderGetResult.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference data that was obtained, one entry for each identifier.
   * @return the value of the property
   */
  public List<ReferenceData> getReferenceData() {
    return _referenceData;
  }

  /**
   * Sets the reference data that was obtained, one entry for each identifier.
   * @param referenceData  the new value of the property
   */
  public void setReferenceData(List<ReferenceData> referenceData) {
    this._referenceData.clear();
    this._referenceData.addAll(referenceData);
  }

  /**
   * Gets the the {@code referenceData} property.
   * @return the property, not null
   */
  public final Property<List<ReferenceData>> referenceData() {
    return metaBean().referenceData().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ReferenceDataProviderGetResult clone() {
    BeanBuilder<? extends ReferenceDataProviderGetResult> builder = metaBean().builder();
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
      ReferenceDataProviderGetResult other = (ReferenceDataProviderGetResult) obj;
      return JodaBeanUtils.equal(getReferenceData(), other.getReferenceData());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getReferenceData());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ReferenceDataProviderGetResult{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("referenceData").append('=').append(getReferenceData()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ReferenceDataProviderGetResult}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code referenceData} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<ReferenceData>> _referenceData = DirectMetaProperty.ofReadWrite(
        this, "referenceData", ReferenceDataProviderGetResult.class, (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "referenceData");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1600456085:  // referenceData
          return _referenceData;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ReferenceDataProviderGetResult> builder() {
      return new DirectBeanBuilder<ReferenceDataProviderGetResult>(new ReferenceDataProviderGetResult());
    }

    @Override
    public Class<? extends ReferenceDataProviderGetResult> beanType() {
      return ReferenceDataProviderGetResult.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code referenceData} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<ReferenceData>> referenceData() {
      return _referenceData;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1600456085:  // referenceData
          return ((ReferenceDataProviderGetResult) bean).getReferenceData();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1600456085:  // referenceData
          ((ReferenceDataProviderGetResult) bean).setReferenceData((List<ReferenceData>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
