/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.id;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.fudgemsg.FudgeField;
import org.fudgemsg.FudgeFieldContainer;
import org.fudgemsg.FudgeMessageFactory;
import org.fudgemsg.MutableFudgeFieldContainer;

import com.opengamma.util.ArgumentChecker;

/**
 * A bundle of identifiers.
 * <p>
 * Each identifier in the bundle will typically refer to the same physical item.
 * The identifiers represent different ways to represent the item, for example in multiple schemes.
 *
 * @author kirk
 */
public final class IdentifierBundle implements Serializable {

  /**
   * Fudge message key for the identifier set.
   */
  public static final String ID_FUDGE_FIELD_NAME = "ID";

  /**
   * The set of identifiers.
   */
  private final Set<Identifier> _identifiers;
  /**
   * The cached hash code.
   */
  private volatile transient int _hashCode;

  /**
   * Creates an empty bundle.
   */
  public IdentifierBundle() {
    _identifiers = Collections.emptySet();
    _hashCode = calcHashCode();
  }

  /**
   * Creates a bundle from a single identifier.
   * @param identifier  the identifier, null returns an empty bundle
   */
  public IdentifierBundle(Identifier identifier) {
    if (identifier == null) {
      _identifiers = Collections.emptySet();
    } else {
      _identifiers = Collections.singleton(identifier);
    }
    _hashCode = calcHashCode();
  }

  /**
   * Creates a bundle from an array of identifiers.
   * @param identifiers  the array of identifiers, null returns an empty bundle
   */
  public IdentifierBundle(Identifier... identifiers) {
    if ((identifiers == null) || (identifiers.length == 0)) {
      _identifiers = Collections.emptySet();
    } else {
      ArgumentChecker.noNulls(identifiers, "identifiers");
      _identifiers = Collections.unmodifiableSet(new TreeSet<Identifier>(Arrays.asList(identifiers)));
    }
    _hashCode = calcHashCode();
  }

  /**
   * Creates a bundle from a collection of identifiers.
   * @param identifiers  the collection of identifiers, null returns an empty bundle
   */
  public IdentifierBundle(Collection<? extends Identifier> identifiers) {
    if (identifiers == null) {
      _identifiers = Collections.emptySet();
    } else {
      ArgumentChecker.noNulls(identifiers, "identifiers");
      _identifiers = Collections.unmodifiableSet(new TreeSet<Identifier>(identifiers));
    }
    _hashCode = calcHashCode();
  }

  private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
    in.defaultReadObject();
    _hashCode = calcHashCode();
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the collection of identifiers in the bundle.
   * @return the identifier collection, unmodifiable, not null
   */
  public Set<Identifier> getIdentifiers() {
    return _identifiers;
  }

  /**
   * Returns a new bundle with the specified identifier added.
   * This instance is immutable and unaffected by this method call.
   * @param identifier  the identifier to add to the returned bundle, not null
   * @return the new bundle, not null
   */
  public IdentifierBundle withIdentifier(Identifier identifier) {
    Set<Identifier> ids = new HashSet<Identifier>(_identifiers);
    ids.add(identifier);
    return new IdentifierBundle(ids);
  }

  /**
   * Returns a new bundle with the specified identifier removed.
   * This instance is immutable and unaffected by this method call.
   * @param identifier  the identifier to remove from the returned bundle, null ignored
   * @return the new bundle, not null
   */
  public IdentifierBundle withoutIdentifier(Identifier identifier) {
    Set<Identifier> ids = new HashSet<Identifier>(_identifiers);
    ids.remove(identifier);
    return new IdentifierBundle(ids);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the standalone identifier for the specified scheme.
   * <p>
   * This returns the first identifier in the internal set that matches.
   * The set is not sorted, so this method is not consistent.
   * @param scheme  the scheme to query, null returns null
   * @return the standalone identifier, null if not found
   */
  public String getIdentifier(IdentificationScheme scheme) {
    for (Identifier identifier : _identifiers) {
      if (ObjectUtils.equals(scheme, identifier.getScheme())) {
        return identifier.getValue();
      }
    }
    return null;
  }

  /**
   * Gets the number of identifiers in the bundle.
   * @return the bundle size, zero or greater
   */
  public int size() {
    return _identifiers.size();
  }

  //-------------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof IdentifierBundle) {
      IdentifierBundle other = (IdentifierBundle) obj;
      return _identifiers.equals(other._identifiers);
    }
    return false;
  }

  protected int calcHashCode() {
    return 31 + _identifiers.hashCode();
  }

  @Override
  public int hashCode() {
    return _hashCode;
  }

  @Override
  public String toString() {
    return new StrBuilder()
      .append("Bundle")
      .append("[")
      .appendWithSeparators(_identifiers, ", ")
      .append("]")
      .toString();
  }

  //-------------------------------------------------------------------------
  public FudgeFieldContainer toFudgeMsg(FudgeMessageFactory fudgeMessageFactory) {
    ArgumentChecker.notNull(fudgeMessageFactory, "Fudge Context");
    MutableFudgeFieldContainer msg = fudgeMessageFactory.newMessage();
    for (Identifier identifier: getIdentifiers()) {
      msg.add(ID_FUDGE_FIELD_NAME, identifier.toFudgeMsg(fudgeMessageFactory));
    }
    return msg;
  }

  public static IdentifierBundle fromFudgeMsg(FudgeFieldContainer fudgeMsg) {
    Set<Identifier> identifiers = new HashSet<Identifier>();
    for (FudgeField field : fudgeMsg.getAllByName(ID_FUDGE_FIELD_NAME)) {
      if (field.getValue() instanceof FudgeFieldContainer == false) {
        throw new IllegalArgumentException("Message provider has field named " + ID_FUDGE_FIELD_NAME + " which doesn't contain a sub-Message");
      }
      identifiers.add(Identifier.fromFudgeMsg((FudgeFieldContainer) field.getValue()));
    }
    return new IdentifierBundle(identifiers);
  }

}
