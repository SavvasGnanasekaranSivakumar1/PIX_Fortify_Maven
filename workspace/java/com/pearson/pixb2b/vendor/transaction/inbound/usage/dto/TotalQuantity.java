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
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;


/**
 * TotalQuantity is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class TotalQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -8237500511972861159L;
	
	private String totalQtyType  = null;
	private String totalQtyContext  = null;
	private TotalQtyValue totalQtyValue = null;

	
	/**
	 * Default constructor.
	 */
	public TotalQuantity() {
		super();
	}

	
	

	/**
	 * @return Returns the totalQtyValue.
	 */
	public TotalQtyValue getTotalQtyValue() {
		return totalQtyValue;
	}


	/**
	 * @param totalQtyValue The totalQtyValue to set.
	 */
	public void setTotalQtyValue(TotalQtyValue totalQtyValue) {
		this.totalQtyValue = totalQtyValue;
	}




	/**
	 * @return Returns the totalQtyType.
	 */
	public String getTotalQtyType() {
		return totalQtyType;
	}




	/**
	 * @param totalQtyType The totalQtyType to set.
	 */
	public void setTotalQtyType(String totalQtyType) {
		this.totalQtyType = totalQtyType;
	}




	/**
	 * @return Returns the totalQtyContext.
	 */
	public String getTotalQtyContext() {
		return totalQtyContext;
	}




	/**
	 * @param totalQtyContext The totalQtyContext to set.
	 */
	public void setTotalQtyContext(String totalQtyContext) {
		this.totalQtyContext = totalQtyContext;
	}







	
	
	
}