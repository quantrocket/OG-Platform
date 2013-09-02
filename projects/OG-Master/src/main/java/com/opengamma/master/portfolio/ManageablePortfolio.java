/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.portfolio;

import java.util.HashMap;
import java.util.Map;

import org.joda.beans.Bean;
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

import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;

/**
 * A portfolio tree representing an arbitrary structure within an organization
 * for the management of positions.
 * <p>
 * The portfolio tree contains the portfolio and the node hierarchy.
 * The position details are not held in the tree and must be retrieved separately
 * via the position master.
 */
@PublicSPI
@BeanDefinition
public class ManageablePortfolio extends DirectBean implements MutableUniqueIdentifiable, UniqueIdentifiable {

  /**
   * The portfolio tree unique identifier.
   * This must be null when adding to a master and not null when retrieved from a master.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;
  /**
   * The portfolio tree name.
   * This field must not be null for the object to be valid.
   */
  @PropertyDefinition
  private String _name = "";
  /**
   * The root node of the tree.
   * This field must not be null for the object to be valid.
   */
  @PropertyDefinition
  private ManageablePortfolioNode _rootNode = new ManageablePortfolioNode();
  /**
   * The general purpose portfolio attributes.
   */
  @PropertyDefinition
  private final Map<String, String> _attributes = new HashMap<String, String>();

  /**
   * Creates a portfolio.
   */
  public ManageablePortfolio() {
  }

  /**
   * Creates a portfolio specifying the name.
   * 
   * @param name  the name, not null
   */
  public ManageablePortfolio(final String name) {
    ArgumentChecker.notNull(name, "name");
    setName(name);
  }

  /**
   * Creates a portfolio specifying the name and root node.
   * 
   * @param name  the name, not null
   * @param rootNode  the root node, not null
   */
  public ManageablePortfolio(final String name, final ManageablePortfolioNode rootNode) {
    ArgumentChecker.notNull(name, "name");
    ArgumentChecker.notNull(rootNode, "rootNode");
    setName(name);
    setRootNode(rootNode);
  }
  
  //-------------------------------------------------------------------------
  /**
   * Adds a key value pair to attributes
   * 
   * @param key  the key to add, not null
   * @param value  the value to add, not null
   */
  public void addAttribute(String key, String value) {
    ArgumentChecker.notNull(key, "key");
    ArgumentChecker.notNull(value, "value");
    _attributes.put(key, value);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ManageablePortfolio}.
   * @return the meta-bean, not null
   */
  public static ManageablePortfolio.Meta meta() {
    return ManageablePortfolio.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ManageablePortfolio.Meta.INSTANCE);
  }

  @Override
  public ManageablePortfolio.Meta metaBean() {
    return ManageablePortfolio.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the portfolio tree unique identifier.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the portfolio tree unique identifier.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the portfolio tree name.
   * This field must not be null for the object to be valid.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the portfolio tree name.
   * This field must not be null for the object to be valid.
   * @param name  the new value of the property
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * This field must not be null for the object to be valid.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the root node of the tree.
   * This field must not be null for the object to be valid.
   * @return the value of the property
   */
  public ManageablePortfolioNode getRootNode() {
    return _rootNode;
  }

  /**
   * Sets the root node of the tree.
   * This field must not be null for the object to be valid.
   * @param rootNode  the new value of the property
   */
  public void setRootNode(ManageablePortfolioNode rootNode) {
    this._rootNode = rootNode;
  }

  /**
   * Gets the the {@code rootNode} property.
   * This field must not be null for the object to be valid.
   * @return the property, not null
   */
  public final Property<ManageablePortfolioNode> rootNode() {
    return metaBean().rootNode().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the general purpose portfolio attributes.
   * @return the value of the property
   */
  public Map<String, String> getAttributes() {
    return _attributes;
  }

  /**
   * Sets the general purpose portfolio attributes.
   * @param attributes  the new value of the property
   */
  public void setAttributes(Map<String, String> attributes) {
    this._attributes.clear();
    this._attributes.putAll(attributes);
  }

  /**
   * Gets the the {@code attributes} property.
   * @return the property, not null
   */
  public final Property<Map<String, String>> attributes() {
    return metaBean().attributes().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ManageablePortfolio clone() {
    BeanBuilder<? extends ManageablePortfolio> builder = metaBean().builder();
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
      ManageablePortfolio other = (ManageablePortfolio) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getRootNode(), other.getRootNode()) &&
          JodaBeanUtils.equal(getAttributes(), other.getAttributes());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getRootNode());
    hash += hash * 31 + JodaBeanUtils.hashCode(getAttributes());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("ManageablePortfolio{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("uniqueId").append('=').append(getUniqueId()).append(',').append(' ');
    buf.append("name").append('=').append(getName()).append(',').append(' ');
    buf.append("rootNode").append('=').append(getRootNode()).append(',').append(' ');
    buf.append("attributes").append('=').append(getAttributes()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ManageablePortfolio}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", ManageablePortfolio.class, UniqueId.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", ManageablePortfolio.class, String.class);
    /**
     * The meta-property for the {@code rootNode} property.
     */
    private final MetaProperty<ManageablePortfolioNode> _rootNode = DirectMetaProperty.ofReadWrite(
        this, "rootNode", ManageablePortfolio.class, ManageablePortfolioNode.class);
    /**
     * The meta-property for the {@code attributes} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<String, String>> _attributes = DirectMetaProperty.ofReadWrite(
        this, "attributes", ManageablePortfolio.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "name",
        "rootNode",
        "attributes");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case -167026172:  // rootNode
          return _rootNode;
        case 405645655:  // attributes
          return _attributes;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ManageablePortfolio> builder() {
      return new DirectBeanBuilder<ManageablePortfolio>(new ManageablePortfolio());
    }

    @Override
    public Class<? extends ManageablePortfolio> beanType() {
      return ManageablePortfolio.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code rootNode} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ManageablePortfolioNode> rootNode() {
      return _rootNode;
    }

    /**
     * The meta-property for the {@code attributes} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<String, String>> attributes() {
      return _attributes;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((ManageablePortfolio) bean).getUniqueId();
        case 3373707:  // name
          return ((ManageablePortfolio) bean).getName();
        case -167026172:  // rootNode
          return ((ManageablePortfolio) bean).getRootNode();
        case 405645655:  // attributes
          return ((ManageablePortfolio) bean).getAttributes();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          ((ManageablePortfolio) bean).setUniqueId((UniqueId) newValue);
          return;
        case 3373707:  // name
          ((ManageablePortfolio) bean).setName((String) newValue);
          return;
        case -167026172:  // rootNode
          ((ManageablePortfolio) bean).setRootNode((ManageablePortfolioNode) newValue);
          return;
        case 405645655:  // attributes
          ((ManageablePortfolio) bean).setAttributes((Map<String, String>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
