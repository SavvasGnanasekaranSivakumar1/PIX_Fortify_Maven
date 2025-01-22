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
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;


/**
 * ICQuantity is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LineInfoQty implements java.io.Serializable {
	private static final long serialVersionUID = -6294124425035947304L;

	private String qtyType  = null;
	private String qtyTypeContext  = null;
	private LineInfoQtyValue usgQtyValue = null;

	

	/**
	 * Default constructor.
	 */
	public LineInfoQty() {
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
	public LineInfoQtyValue getUsgQtyValue() {
		return usgQtyValue;
	}







	/**
	 * @param usgQtyValue The usgQtyValue to set.
	 */
	public void setUsgQtyValue(LineInfoQtyValue usgQtyValue) {
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