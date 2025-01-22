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
 * Title		: 	LineQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan,2010    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.LineQtyValue;


/**
 * LineQuantity is a data transfer object to store the 
 * goodsreceipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LineQuantity implements java.io.Serializable {
	private static final long serialVersionUID = 7435769308822861095L;

	private String qtyType  = null;
	private String qtyTypeContext = null;
	private LineQtyValue grQtyValue = null;
	

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
	 * @return Returns the qtyTypeContext.
	 */
	public String getQtyTypeContext() {
		return qtyTypeContext;
	}



	/**
	 * @param qtyTypeContext The qtyTypeContext to set.
	 */
	public void setQtyTypeContext(String qtyTypeContext) {
		this.qtyTypeContext = qtyTypeContext;
	}



	/**
	 * @return Returns the grQtyValue.
	 */
	public LineQtyValue getGrQtyValue() {
		return grQtyValue;
	}



	/**
	 * @param grQtyValue The grQtyValue to set.
	 */
	public void setGrQtyValue(LineQtyValue grQtyValue) {
		this.grQtyValue = grQtyValue;
	}



	


		
		
}