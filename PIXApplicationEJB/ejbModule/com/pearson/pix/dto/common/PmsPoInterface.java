package com.pearson.pix.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  ###  Generated by Oracle TopLink Workbench 10.1.3.0.0 - Fri Aug 17 19:25:32 GMT+05:30 2007.  ###
 */

public class PmsPoInterface implements Serializable {

	private String comments;
	private Date deliveryDate;
	private String errorDesc;
	private BigDecimal poInterfaceId;
	private BigDecimal poLineNo;
	private String poNumber;
	private String processedFlag;
	private String status;

public PmsPoInterface() {
	super();
}

public String getComments() {
	return this.comments;
}

public Date getDeliveryDate() {
	return this.deliveryDate;
}

public String getErrorDesc() {
	return this.errorDesc;
}

public BigDecimal getPoInterfaceId() {
	return this.poInterfaceId;
}

public BigDecimal getPoLineNo() {
	return this.poLineNo;
}

public String getPoNumber() {
	return this.poNumber;
}

public String getProcessedFlag() {
	return this.processedFlag;
}

public String getStatus() {
	return this.status;
}

public void setComments(String comments) {
	this.comments = comments;
}

public void setDeliveryDate(Date deliveryDate) {
	this.deliveryDate = deliveryDate;
}

public void setErrorDesc(String errorDesc) {
	this.errorDesc = errorDesc;
}

public void setPoInterfaceId(BigDecimal poInterfaceId) {
	this.poInterfaceId = poInterfaceId;
}

public void setPoLineNo(BigDecimal poLineNo) {
	this.poLineNo = poLineNo;
}

public void setPoNumber(String poNumber) {
	this.poNumber = poNumber;
}

public void setProcessedFlag(String processedFlag) {
	this.processedFlag = processedFlag;
}

public void setStatus(String status) {
	this.status = status;
}

}
