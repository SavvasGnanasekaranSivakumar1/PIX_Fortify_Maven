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
 * Title		: 	Quantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * LineQtyValue is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LineQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -3730076076068227666L;

	private String qtyType  = null;
	private LineQtyValue lineQtyValue = null;

	/**
	 * Default constructor.
	 */
	public LineQuantity() {
		super();
	}

	
	/**
	 * @return Returns the qtyType.
	 */
	public String getQtyType() {
		return qtyType;
	}

	/**
	 * @param qtyType The qtyType to set.
	 */
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}


	/**
	 * @return Returns the lineQtyValue.
	 */
	public LineQtyValue getLineQtyValue() {
		return lineQtyValue;
	}


	/**
	 * @param lineQtyValue The lineQtyValue to set.
	 */
	public void setLineQtyValue(LineQtyValue lineQtyValue) {
		this.lineQtyValue = lineQtyValue;
	}

	
	
	
}