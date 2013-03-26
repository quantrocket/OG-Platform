/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.source;

import java.util.LinkedHashMap;
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

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.core.organization.OrganizationSource;
import com.opengamma.core.organization.impl.DataOrganizationSourceResource;
import com.opengamma.core.organization.impl.RemoteOrganizationSource;
import com.opengamma.master.organization.impl.MasterOrganizationSource;
import com.opengamma.master.orgs.OrganizationMaster;

/**
 * Component factory providing the {@code OrganizationSource}.
 */
@BeanDefinition
public class OrganizationSourceComponentFactory extends AbstractComponentFactory {

  /**
   * The classifier that the factory should publish under.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The flag determining whether the component should be published by REST (default true).
   */
  @PropertyDefinition
  private boolean _publishRest = true;
  /**
   * The underlying organization master.
   */
  @PropertyDefinition(validate = "notNull")
  private OrganizationMaster _organizationMaster;

  //-------------------------------------------------------------------------
  /**
   * Initializes the organization source, setting up component information and REST.
   * Override using {@link #createOrganizationSource(ComponentRepository)}.
   *
   * @param repo  the component repository, not null
   * @param configuration  the remaining configuration, not null
   */
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) {
    OrganizationSource source = createOrganizationSource(repo);

    ComponentInfo info = new ComponentInfo(OrganizationSource.class, getClassifier());
    info.addAttribute(ComponentInfoAttributes.LEVEL, 1);
    info.addAttribute(ComponentInfoAttributes.REMOTE_CLIENT_JAVA, RemoteOrganizationSource.class);
    repo.registerComponent(info, source);
    if (isPublishRest()) {
      repo.getRestComponents().publish(info, new DataOrganizationSourceResource(source));
    }
  }

  /**
   * Creates the organization source without registering it.
   *
   * @param repo  the component repository, only used to register secondary items like lifecycle, not null
   * @return the organization source, not null
   */
  protected OrganizationSource createOrganizationSource(ComponentRepository repo) {
    return new MasterOrganizationSource(getOrganizationMaster());
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code OrganizationSourceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static OrganizationSourceComponentFactory.Meta meta() {
    return OrganizationSourceComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(OrganizationSourceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public OrganizationSourceComponentFactory.Meta metaBean() {
    return OrganizationSourceComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        return getClassifier();
      case -614707837:  // publishRest
        return isPublishRest();
      case -1158737547:  // organizationMaster
        return getOrganizationMaster();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -281470431:  // classifier
        setClassifier((String) newValue);
        return;
      case -614707837:  // publishRest
        setPublishRest((Boolean) newValue);
        return;
      case -1158737547:  // organizationMaster
        setOrganizationMaster((OrganizationMaster) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_classifier, "classifier");
    JodaBeanUtils.notNull(_organizationMaster, "organizationMaster");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      OrganizationSourceComponentFactory other = (OrganizationSourceComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(isPublishRest(), other.isPublishRest()) &&
          JodaBeanUtils.equal(getOrganizationMaster(), other.getOrganizationMaster()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash += hash * 31 + JodaBeanUtils.hashCode(isPublishRest());
    hash += hash * 31 + JodaBeanUtils.hashCode(getOrganizationMaster());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier that the factory should publish under.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier that the factory should publish under.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the flag determining whether the component should be published by REST (default true).
   * @return the value of the property
   */
  public boolean isPublishRest() {
    return _publishRest;
  }

  /**
   * Sets the flag determining whether the component should be published by REST (default true).
   * @param publishRest  the new value of the property
   */
  public void setPublishRest(boolean publishRest) {
    this._publishRest = publishRest;
  }

  /**
   * Gets the the {@code publishRest} property.
   * @return the property, not null
   */
  public final Property<Boolean> publishRest() {
    return metaBean().publishRest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying organization master.
   * @return the value of the property, not null
   */
  public OrganizationMaster getOrganizationMaster() {
    return _organizationMaster;
  }

  /**
   * Sets the underlying organization master.
   * @param organizationMaster  the new value of the property, not null
   */
  public void setOrganizationMaster(OrganizationMaster organizationMaster) {
    JodaBeanUtils.notNull(organizationMaster, "organizationMaster");
    this._organizationMaster = organizationMaster;
  }

  /**
   * Gets the the {@code organizationMaster} property.
   * @return the property, not null
   */
  public final Property<OrganizationMaster> organizationMaster() {
    return metaBean().organizationMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code OrganizationSourceComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", OrganizationSourceComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code publishRest} property.
     */
    private final MetaProperty<Boolean> _publishRest = DirectMetaProperty.ofReadWrite(
        this, "publishRest", OrganizationSourceComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code organizationMaster} property.
     */
    private final MetaProperty<OrganizationMaster> _organizationMaster = DirectMetaProperty.ofReadWrite(
        this, "organizationMaster", OrganizationSourceComponentFactory.class, OrganizationMaster.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "publishRest",
        "organizationMaster");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -614707837:  // publishRest
          return _publishRest;
        case -1158737547:  // organizationMaster
          return _organizationMaster;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends OrganizationSourceComponentFactory> builder() {
      return new DirectBeanBuilder<OrganizationSourceComponentFactory>(new OrganizationSourceComponentFactory());
    }

    @Override
    public Class<? extends OrganizationSourceComponentFactory> beanType() {
      return OrganizationSourceComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code publishRest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> publishRest() {
      return _publishRest;
    }

    /**
     * The meta-property for the {@code organizationMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<OrganizationMaster> organizationMaster() {
      return _organizationMaster;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
