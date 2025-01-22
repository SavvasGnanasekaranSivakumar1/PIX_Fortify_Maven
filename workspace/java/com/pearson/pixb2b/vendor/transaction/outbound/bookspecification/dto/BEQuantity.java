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
 * Title		: 	BEQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;


/**
 * BEQuantity is a data transfer object to store the Binding Extras
 * Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BEQuantity implements java.io.Serializable {
	private static final long serialVersionUID = 5729022845581059653L;
	
	private String adjustmentType = null;
	private String quantityType	  = null;
	private String qtContext	  = null;
	private String measAgency	  = null;
	private String measMethod	  = null;
	private BEValue beValue	  	  = null;
	private BERangeMin beRangeMin = null;
	private BERangeMax beRangeMax = null;
	/**
	 * Default constructor.
	 */
	public BEQuantity() {
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
	 * @return the beValue
	 */
	public BEValue getBeValue() {
		return beValue;
	}
	/**
	 * @param beValue the beValue to set
	 */
	public void setBeValue(BEValue beValue) {
		this.beValue = beValue;
	}
	/**
	 * @return the beRangeMin
	 */
	public BERangeMin getBeRangeMin() {
		return beRangeMin;
	}
	/**
	 * @param beRangeMin the beRangeMin to set
	 */
	public void setBeRangeMin(BERangeMin beRangeMin) {
		this.beRangeMin = beRangeMin;
	}
	/**
	 * @return the beRangeMax
	 */
	public BERangeMax getBeRangeMax() {
		return beRangeMax;
	}
	/**
	 * @param beRangeMax the beRangeMax to set
	 */
	public void setBeRangeMax(BERangeMax beRangeMax) {
		this.beRangeMax = beRangeMax;
	}
}
