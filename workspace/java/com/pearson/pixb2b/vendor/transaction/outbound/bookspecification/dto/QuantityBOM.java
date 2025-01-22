/**
 * Copyright 2009 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	QuantityBOM.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;


/**
 * QuantityBOM is a data transfer object to store the 
 * Bill Of Materials Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class QuantityBOM implements java.io.Serializable {
	private static final long serialVersionUID = -5490605982250489529L;
	
	private String quantityType	  = null;
	private String qtContext	  = null;
	private String adjustmentType = null;
	private String measAgency	  = null;
	private String measMethod	  = null;
	private ValueBOM valueBOM	  = null;
	private RangeMinBOM rangeMin  = null;
	private RangeMaxBOM rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public QuantityBOM() {
		super();
	}
	public String getQuantityType() {
		return quantityType;
	}
	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}
	public String getQtContext() {
		return qtContext;
	}
	public void setQtContext(String qtContext) {
		this.qtContext = qtContext;
	}
	public String getAdjustmentType() {
		return adjustmentType;
	}
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
	public String getMeasAgency() {
		return measAgency;
	}
	public void setMeasAgency(String measAgency) {
		this.measAgency = measAgency;
	}
	public String getMeasMethod() {
		return measMethod;
	}
	public void setMeasMethod(String measMethod) {
		this.measMethod = measMethod;
	}
	public ValueBOM getValueBOM() {
		return valueBOM;
	}
	public void setValueBOM(ValueBOM valueBOM) {
		this.valueBOM = valueBOM;
	}
	public RangeMinBOM getRangeMin() {
		return rangeMin;
	}
	public void setRangeMin(RangeMinBOM rangeMin) {
		this.rangeMin = rangeMin;
	}
	public RangeMaxBOM getRangeMax() {
		return rangeMax;
	}
	public void setRangeMax(RangeMaxBOM rangeMax) {
		this.rangeMax = rangeMax;
	}
}
