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
 * Title		: 	CLQuant.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;


/**
 * CLQuant is a data transfer object to store the specification Binding   
 * CaseLining Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CLQuant implements java.io.Serializable {
	private static final long serialVersionUID = -2682393811437504520L;
	
	private String adjustmentType = null;
	private String quantityType	  = null;
	private String qtContext	  = null;
	private String measAgency	  = null;
	private String measMethod	  = null;	
	private CLValue clValue	  	  = null;
	private CLRangeMin clRangeMin = null;
	private CLRangeMax clRangeMax = null;
	/**
	 * Default constructor.
	 */
	public CLQuant() {
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
	 * @return the clValue
	 */
	public CLValue getClValue() {
		return clValue;
	}
	/**
	 * @param clValue the clValue to set
	 */
	public void setClValue(CLValue clValue) {
		this.clValue = clValue;
	}
	/**
	 * @return the clRangeMin
	 */
	public CLRangeMin getClRangeMin() {
		return clRangeMin;
	}
	/**
	 * @param clRangeMin the clRangeMin to set
	 */
	public void setClRangeMin(CLRangeMin clRangeMin) {
		this.clRangeMin = clRangeMin;
	}
	/**
	 * @return the clRangeMax
	 */
	public CLRangeMax getClRangeMax() {
		return clRangeMax;
	}
	/**
	 * @param clRangeMax the clRangeMax to set
	 */
	public void setClRangeMax(CLRangeMax clRangeMax) {
		this.clRangeMax = clRangeMax;
	}
}
