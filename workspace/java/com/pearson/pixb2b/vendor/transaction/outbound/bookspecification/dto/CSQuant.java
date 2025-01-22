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
 * Title		: 	CSQuant.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;


/**
 * CSQuant is a data transfer object to store the specification Binding   
 * CaseSupers Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CSQuant implements java.io.Serializable {
	private static final long serialVersionUID = 3458465847492950379L;
	
	private String adjustmentType = null;
	private String quantityType	  = null;
	private String qtContext	  = null;
	private String measAgency	  = null;
	private String measMethod	  = null;
	private CSValue csValue	  	  = null;
	private CSRangeMin csRangeMin = null;
	private CSRangeMax csRangeMax = null;
	/**
	 * Default constructor.
	 */
	public CSQuant() {
		super();
	}
	/**
	 * @return the adjustmentType
	 */
	public String getAdjustmentType() {
		return adjustmentType;
	}
	/**
	 * @param adjustmentType the adjustmentType to set
	 */
	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}
	/**
	 * @return the quantityType
	 */
	public String getQuantityType() {
		return quantityType;
	}
	/**
	 * @param quantityType the quantityType to set
	 */
	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}
	/**
	 * @return the qtContext
	 */
	public String getQtContext() {
		return qtContext;
	}
	/**
	 * @param qtContext the qtContext to set
	 */
	public void setQtContext(String qtContext) {
		this.qtContext = qtContext;
	}
	/**
	 * @return the measAgency
	 */
	public String getMeasAgency() {
		return measAgency;
	}
	/**
	 * @param measAgency the measAgency to set
	 */
	public void setMeasAgency(String measAgency) {
		this.measAgency = measAgency;
	}
	/**
	 * @return the measMethod
	 */
	public String getMeasMethod() {
		return measMethod;
	}
	/**
	 * @param measMethod the measMethod to set
	 */
	public void setMeasMethod(String measMethod) {
		this.measMethod = measMethod;
	}
	/**
	 * @return the csValue
	 */
	public CSValue getCsValue() {
		return csValue;
	}
	/**
	 * @param csValue the csValue to set
	 */
	public void setCsValue(CSValue csValue) {
		this.csValue = csValue;
	}
	/**
	 * @return the csRangeMin
	 */
	public CSRangeMin getCsRangeMin() {
		return csRangeMin;
	}
	/**
	 * @param csRangeMin the csRangeMin to set
	 */
	public void setCsRangeMin(CSRangeMin csRangeMin) {
		this.csRangeMin = csRangeMin;
	}
	/**
	 * @return the csRangeMax
	 */
	public CSRangeMax getCsRangeMax() {
		return csRangeMax;
	}
	/**
	 * @param csRangeMax the csRangeMax to set
	 */
	public void setCsRangeMax(CSRangeMax csRangeMax) {
		this.csRangeMax = csRangeMax;
	}
}
