package com.opengamma.integration.tool.portfolio.xml.v1_0;

import java.util.Map;
import java.util.concurrent.Callable;

import org.xml.sax.SAXException;

import com.google.common.collect.Maps;
import com.sun.xml.internal.bind.IDResolver;

public class IdRefResolver extends IDResolver {

  private final Map<String,Trade> _tradeIds = Maps.newHashMap();
  private final Map<String, Position> _positionIds = Maps.newHashMap();

  @Override
  public void bind(String s, Object o) throws SAXException {

    if (o instanceof Trade) {
      _tradeIds.put(s, (Trade) o);
    }
    else if (o instanceof Position) {
      _positionIds.put(s, (Position) o);
    }
  }

  @Override
  public Callable<?> resolve(final String s, final Class aClass) throws SAXException {
    return new Callable<Object>() {
      @Override
      public Object call() throws Exception {
        if (Trade.class.isAssignableFrom(aClass)) {
          return _tradeIds.get(s);
        }
        else if (aClass == Position.class) {
          return _positionIds.get(s);
        }
        else {
          return null;
        }
      }
    };
  }

}
