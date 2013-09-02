/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.util.db.tool;

import java.io.Closeable;
import java.util.Map;
import java.util.Set;

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

import com.opengamma.util.db.DbConnector;
import com.opengamma.util.db.management.DbManagement;
import org.joda.beans.Bean;

/**
 * A standard context that is used to provide components to database tools.
 */
@BeanDefinition
public class DbToolContext extends DirectBean implements Closeable {

  /**
   * The database connector.
   */
  @PropertyDefinition
  private DbConnector _dbConnector;
  /**
   * The database management instance.
   */
  @PropertyDefinition
  private DbManagement _dbManagement;
  /**
   * The database catalog name.
   */
  @PropertyDefinition
  private String _catalog;
  /**
   * The database schema name.
   */
  @PropertyDefinition
  private String _schema;
  /**
   * The database schema names on which to operate.
   */
  @PropertyDefinition
  private Set<String> _schemaNames;
  
  @Override
  public void close() {
    if (getDbConnector() != null) {
      getDbConnector().close();
      setDbConnector(null);
    }
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbToolContext}.
   * @return the meta-bean, not null
   */
  public static DbToolContext.Meta meta() {
    return DbToolContext.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(DbToolContext.Meta.INSTANCE);
  }

  @Override
  public DbToolContext.Meta metaBean() {
    return DbToolContext.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database connector.
   * @return the value of the property
   */
  public DbConnector getDbConnector() {
    return _dbConnector;
  }

  /**
   * Sets the database connector.
   * @param dbConnector  the new value of the property
   */
  public void setDbConnector(DbConnector dbConnector) {
    this._dbConnector = dbConnector;
  }

  /**
   * Gets the the {@code dbConnector} property.
   * @return the property, not null
   */
  public final Property<DbConnector> dbConnector() {
    return metaBean().dbConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database management instance.
   * @return the value of the property
   */
  public DbManagement getDbManagement() {
    return _dbManagement;
  }

  /**
   * Sets the database management instance.
   * @param dbManagement  the new value of the property
   */
  public void setDbManagement(DbManagement dbManagement) {
    this._dbManagement = dbManagement;
  }

  /**
   * Gets the the {@code dbManagement} property.
   * @return the property, not null
   */
  public final Property<DbManagement> dbManagement() {
    return metaBean().dbManagement().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database catalog name.
   * @return the value of the property
   */
  public String getCatalog() {
    return _catalog;
  }

  /**
   * Sets the database catalog name.
   * @param catalog  the new value of the property
   */
  public void setCatalog(String catalog) {
    this._catalog = catalog;
  }

  /**
   * Gets the the {@code catalog} property.
   * @return the property, not null
   */
  public final Property<String> catalog() {
    return metaBean().catalog().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database schema name.
   * @return the value of the property
   */
  public String getSchema() {
    return _schema;
  }

  /**
   * Sets the database schema name.
   * @param schema  the new value of the property
   */
  public void setSchema(String schema) {
    this._schema = schema;
  }

  /**
   * Gets the the {@code schema} property.
   * @return the property, not null
   */
  public final Property<String> schema() {
    return metaBean().schema().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the database schema names on which to operate.
   * @return the value of the property
   */
  public Set<String> getSchemaNames() {
    return _schemaNames;
  }

  /**
   * Sets the database schema names on which to operate.
   * @param schemaNames  the new value of the property
   */
  public void setSchemaNames(Set<String> schemaNames) {
    this._schemaNames = schemaNames;
  }

  /**
   * Gets the the {@code schemaNames} property.
   * @return the property, not null
   */
  public final Property<Set<String>> schemaNames() {
    return metaBean().schemaNames().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public DbToolContext clone() {
    BeanBuilder<? extends DbToolContext> builder = metaBean().builder();
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
      DbToolContext other = (DbToolContext) obj;
      return JodaBeanUtils.equal(getDbConnector(), other.getDbConnector()) &&
          JodaBeanUtils.equal(getDbManagement(), other.getDbManagement()) &&
          JodaBeanUtils.equal(getCatalog(), other.getCatalog()) &&
          JodaBeanUtils.equal(getSchema(), other.getSchema()) &&
          JodaBeanUtils.equal(getSchemaNames(), other.getSchemaNames());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getDbConnector());
    hash += hash * 31 + JodaBeanUtils.hashCode(getDbManagement());
    hash += hash * 31 + JodaBeanUtils.hashCode(getCatalog());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSchema());
    hash += hash * 31 + JodaBeanUtils.hashCode(getSchemaNames());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("DbToolContext{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("dbConnector").append('=').append(getDbConnector()).append(',').append(' ');
    buf.append("dbManagement").append('=').append(getDbManagement()).append(',').append(' ');
    buf.append("catalog").append('=').append(getCatalog()).append(',').append(' ');
    buf.append("schema").append('=').append(getSchema()).append(',').append(' ');
    buf.append("schemaNames").append('=').append(getSchemaNames()).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbToolContext}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code dbConnector} property.
     */
    private final MetaProperty<DbConnector> _dbConnector = DirectMetaProperty.ofReadWrite(
        this, "dbConnector", DbToolContext.class, DbConnector.class);
    /**
     * The meta-property for the {@code dbManagement} property.
     */
    private final MetaProperty<DbManagement> _dbManagement = DirectMetaProperty.ofReadWrite(
        this, "dbManagement", DbToolContext.class, DbManagement.class);
    /**
     * The meta-property for the {@code catalog} property.
     */
    private final MetaProperty<String> _catalog = DirectMetaProperty.ofReadWrite(
        this, "catalog", DbToolContext.class, String.class);
    /**
     * The meta-property for the {@code schema} property.
     */
    private final MetaProperty<String> _schema = DirectMetaProperty.ofReadWrite(
        this, "schema", DbToolContext.class, String.class);
    /**
     * The meta-property for the {@code schemaNames} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<String>> _schemaNames = DirectMetaProperty.ofReadWrite(
        this, "schemaNames", DbToolContext.class, (Class) Set.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "dbConnector",
        "dbManagement",
        "catalog",
        "schema",
        "schemaNames");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 39794031:  // dbConnector
          return _dbConnector;
        case 209279841:  // dbManagement
          return _dbManagement;
        case 555704345:  // catalog
          return _catalog;
        case -907987551:  // schema
          return _schema;
        case -1026748889:  // schemaNames
          return _schemaNames;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends DbToolContext> builder() {
      return new DirectBeanBuilder<DbToolContext>(new DbToolContext());
    }

    @Override
    public Class<? extends DbToolContext> beanType() {
      return DbToolContext.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code dbConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbConnector> dbConnector() {
      return _dbConnector;
    }

    /**
     * The meta-property for the {@code dbManagement} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<DbManagement> dbManagement() {
      return _dbManagement;
    }

    /**
     * The meta-property for the {@code catalog} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> catalog() {
      return _catalog;
    }

    /**
     * The meta-property for the {@code schema} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> schema() {
      return _schema;
    }

    /**
     * The meta-property for the {@code schemaNames} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Set<String>> schemaNames() {
      return _schemaNames;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 39794031:  // dbConnector
          return ((DbToolContext) bean).getDbConnector();
        case 209279841:  // dbManagement
          return ((DbToolContext) bean).getDbManagement();
        case 555704345:  // catalog
          return ((DbToolContext) bean).getCatalog();
        case -907987551:  // schema
          return ((DbToolContext) bean).getSchema();
        case -1026748889:  // schemaNames
          return ((DbToolContext) bean).getSchemaNames();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 39794031:  // dbConnector
          ((DbToolContext) bean).setDbConnector((DbConnector) newValue);
          return;
        case 209279841:  // dbManagement
          ((DbToolContext) bean).setDbManagement((DbManagement) newValue);
          return;
        case 555704345:  // catalog
          ((DbToolContext) bean).setCatalog((String) newValue);
          return;
        case -907987551:  // schema
          ((DbToolContext) bean).setSchema((String) newValue);
          return;
        case -1026748889:  // schemaNames
          ((DbToolContext) bean).setSchemaNames((Set<String>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
