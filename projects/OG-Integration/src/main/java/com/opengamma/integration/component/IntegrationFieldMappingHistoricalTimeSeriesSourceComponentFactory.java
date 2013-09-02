/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.component;

import java.util.Collection;
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

import com.google.common.collect.ImmutableList;
import com.opengamma.bbg.referencedata.ReferenceDataProvider;
import com.opengamma.bbg.util.BloombergDataUtils;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.source.HistoricalTimeSeriesSourceComponentFactory;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolver;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesSelector;
import com.opengamma.master.historicaltimeseries.impl.DefaultHistoricalTimeSeriesSelector;
import com.opengamma.master.historicaltimeseries.impl.FieldMappingHistoricalTimeSeriesResolver;
import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesFieldAdjustmentMap;
import org.joda.beans.Bean;

/**
 * Component factory for the historical time-series source resolved using Bloomberg.
 */
@BeanDefinition
public class IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory extends HistoricalTimeSeriesSourceComponentFactory {

  /**
   * The reference data.
   */
  @PropertyDefinition(validate = "notNull")
  private ReferenceDataProvider _bbgReferenceData;

  //-------------------------------------------------------------------------
  @Override
  protected HistoricalTimeSeriesResolver createResolver(ComponentRepository repo) {
    HistoricalTimeSeriesFieldAdjustmentMap bbgFieldAdjustmentMap = BloombergDataUtils.createFieldAdjustmentMap(getBbgReferenceData(), getCacheManager());
    Collection<HistoricalTimeSeriesFieldAdjustmentMap> fieldAdjustmentMaps = ImmutableList.of(bbgFieldAdjustmentMap);
    
    HistoricalTimeSeriesSelector selector = new DefaultHistoricalTimeSeriesSelector(getConfigSource());
    
    return new FieldMappingHistoricalTimeSeriesResolver(fieldAdjustmentMaps, selector, getHistoricalTimeSeriesMaster());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta meta() {
    return IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta metaBean() {
    return IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the reference data.
   * @return the value of the property, not null
   */
  public ReferenceDataProvider getBbgReferenceData() {
    return _bbgReferenceData;
  }

  /**
   * Sets the reference data.
   * @param bbgReferenceData  the new value of the property, not null
   */
  public void setBbgReferenceData(ReferenceDataProvider bbgReferenceData) {
    JodaBeanUtils.notNull(bbgReferenceData, "bbgReferenceData");
    this._bbgReferenceData = bbgReferenceData;
  }

  /**
   * Gets the the {@code bbgReferenceData} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> bbgReferenceData() {
    return metaBean().bbgReferenceData().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory clone() {
    return (IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory) super.clone();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory other = (IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory) obj;
      return JodaBeanUtils.equal(getBbgReferenceData(), other.getBbgReferenceData()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBbgReferenceData());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory{");
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
    buf.append("bbgReferenceData").append('=').append(getBbgReferenceData()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory}.
   */
  public static class Meta extends HistoricalTimeSeriesSourceComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bbgReferenceData} property.
     */
    private final MetaProperty<ReferenceDataProvider> _bbgReferenceData = DirectMetaProperty.ofReadWrite(
        this, "bbgReferenceData", IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.class, ReferenceDataProvider.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "bbgReferenceData");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 789967022:  // bbgReferenceData
          return _bbgReferenceData;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory> builder() {
      return new DirectBeanBuilder<IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory>(new IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory());
    }

    @Override
    public Class<? extends IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory> beanType() {
      return IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bbgReferenceData} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> bbgReferenceData() {
      return _bbgReferenceData;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 789967022:  // bbgReferenceData
          return ((IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory) bean).getBbgReferenceData();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 789967022:  // bbgReferenceData
          ((IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory) bean).setBbgReferenceData((ReferenceDataProvider) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((IntegrationFieldMappingHistoricalTimeSeriesSourceComponentFactory) bean)._bbgReferenceData, "bbgReferenceData");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
