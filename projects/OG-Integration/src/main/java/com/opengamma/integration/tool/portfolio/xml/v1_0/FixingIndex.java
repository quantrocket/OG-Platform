package com.opengamma.integration.tool.portfolio.xml.v1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FixingIndex {

  public enum RateType {OIS, CMS, IBOR}

  @XmlElement(name = "id")
  private ExtId index;

  @XmlElement(name = "rateType")
  private RateType _rateType;

  public ExtId getIndex() {
    return index;
  }

  public void setIndex(ExtId index) {
    this.index = index;
  }

  public RateType getRateType() {
    return _rateType;
  }

  public void setRateType(RateType rateType) {
    _rateType = rateType;
  }
}
