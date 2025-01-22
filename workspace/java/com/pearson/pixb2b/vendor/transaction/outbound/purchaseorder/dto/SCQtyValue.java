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
 * SCQtyValue is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SCQtyValue implements java.io.Serializable {
	private static final long serialVersionUID = -521258816658340737L;
	
	private String qtyUOM1= null;
	private String qtyValue1 = null;
	
	/**
	 * Default constructor.
	 */
	public SCQtyValue() {
		super();
	}

	/**
	 * @return Returns the qtyUOM1.
	 */
	public String getQtyUOM1() {
		return qtyUOM1;
	}

	/**
	 * @param qtyUOM1 The qtyUOM1 to set.
	 */
	public void setQtyUOM1(String qtyUOM1) {
		this.qtyUOM1 = qtyUOM1;
	}

	/**
	 * @return Returns the qtyValue1.
	 */
	public String getQtyValue1() {
		return qtyValue1;
	}

	/**
	 * @param qtyValue1 The qtyValue1 to set.
	 */
	public void setQtyValue1(String qtyValue1) {
		this.qtyValue1 = qtyValue1;
	}
	
	

	}