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
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.LineQtyValue;


/**
 * LineQuantity is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LineQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -538723805901807810L;
	
	private String qtyType  = null;
	private String qtyTypeContext = null;
	private LineQtyValue usgQtyValue = null;
	

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
	 * @return Returns the usgQtyValue.
	 */
	public LineQtyValue getUsgQtyValue() {
		return usgQtyValue;
	}



	/**
	 * @param usgQtyValue The usgQtyValue to set.
	 */
	public void setUsgQtyValue(LineQtyValue usgQtyValue) {
		this.usgQtyValue = usgQtyValue;
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



	


		
		
}