package com.pearson.pix.dto.ces;

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomBookSpecReleaseContainerDTO implements Serializable {
  private PixEtlBookSpecBindingCes[] pixBookSpecBindingCes;
  
  private PixEtlBookSpecCes[] pixBookSpecCes;
  
  private PixEtlBookSpecLineCes[] pixBookSpecLineCes;
  
  private PixEtlBookSpecMiscCes[] pixBookSpecMiscCes;
  
  private PixEtlBookSpecNonpressCes[] pixBookSpecNonpressCes;
  
  private PixEtlBookSpecPressCes[] pixBookSpecPressCes;
  
  private Double transactionNumberBookSpec;
  
  public PixEtlBookSpecBindingCes[] getPixBookSpecBindingCes() {
    return this.pixBookSpecBindingCes;
  }
  
  public void setPixBookSpecBindingCes(PixEtlBookSpecBindingCes[] apixetlbookspecbindingces) {
    this.pixBookSpecBindingCes = apixetlbookspecbindingces;
  }
  
  public PixEtlBookSpecCes[] getPixBookSpecCes() {
    return this.pixBookSpecCes;
  }
  
  public void setPixBookSpecCes(PixEtlBookSpecCes[] apixetlbookspecces) {
    this.pixBookSpecCes = apixetlbookspecces;
  }
  
  public PixEtlBookSpecLineCes[] getPixBookSpecLineCes() {
    return this.pixBookSpecLineCes;
  }
  
  public void setPixBookSpecLineCes(PixEtlBookSpecLineCes[] apixetlbookspeclineces) {
    this.pixBookSpecLineCes = apixetlbookspeclineces;
  }
  
  public PixEtlBookSpecMiscCes[] getPixBookSpecMiscCes() {
    return this.pixBookSpecMiscCes;
  }
  
  public void setPixBookSpecMiscCes(PixEtlBookSpecMiscCes[] apixetlbookspecmiscces) {
    this.pixBookSpecMiscCes = apixetlbookspecmiscces;
  }
  
  public PixEtlBookSpecNonpressCes[] getPixBookSpecNonpressCes() {
    return this.pixBookSpecNonpressCes;
  }
  
  public void setPixBookSpecNonpressCes(PixEtlBookSpecNonpressCes[] apixetlbookspecnonpressces) {
    this.pixBookSpecNonpressCes = apixetlbookspecnonpressces;
  }
  
  public PixEtlBookSpecPressCes[] getPixBookSpecPressCes() {
    return this.pixBookSpecPressCes;
  }
  
  public void setPixBookSpecPressCes(PixEtlBookSpecPressCes[] apixetlbookspecpressces) {
    this.pixBookSpecPressCes = apixetlbookspecpressces;
  }
  
  public Double getTransactionNumberBookSpec() {
    return this.transactionNumberBookSpec;
  }
  
  public void setTransactionNumberBookSpec(Double bigdecimal) {
    this.transactionNumberBookSpec = bigdecimal;
  }
}
