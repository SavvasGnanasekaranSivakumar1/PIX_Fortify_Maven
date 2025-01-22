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
 * Title		: 	SCQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;
/**
 * SCQuantity is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SCQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -5414940017515742108L;
	
	private String qtyType = null;
	private SCQtyValue scQtyValue = null;
	/** 
	 * Default constructor.
	 */
	public SCQuantity() {
		super();
	}
	/**
	 * @return the qtyType
	 */
	public String getQtyType() {
		return qtyType;
	}
	/**
	 * @param qtyType the qtyType to set
	 */
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}
	/**
	 * @return the scQtyValue
	 */
	public SCQtyValue getScQtyValue() {
		return scQtyValue;
	}
	/**
	 * @param scQtyValue the scQtyValue to set
	 */
	public void setScQtyValue(SCQtyValue scQtyValue) {
		this.scQtyValue = scQtyValue;
	}
}