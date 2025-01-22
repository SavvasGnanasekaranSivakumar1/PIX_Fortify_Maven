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
 * 1.0	Abhilasha Nigam   25 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;


/**
 * TotalQuantity is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class TotalInfoQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -8237500511972861159L;
	
	private String totalQtyType  = null;
	private String totalTypeContext = null;
	private TotalInfoQtyValue totalInfoQtyValue = null;

	
	/**
	 * Default constructor.
	 */
	public TotalInfoQuantity() {
		super();
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
	 * @return Returns the totalInfoQtyValue.
	 */
	public TotalInfoQtyValue getTotalInfoQtyValue() {
		return totalInfoQtyValue;
	}


	/**
	 * @param totalInfoQtyValue The totalInfoQtyValue to set.
	 */
	public void setTotalInfoQtyValue(TotalInfoQtyValue totalInfoQtyValue) {
		this.totalInfoQtyValue = totalInfoQtyValue;
	}

	/**
	 * @return Returns the totalTypeContext.
	 */
	public String getTotalTypeContext() {
		return totalTypeContext;
	}

	/**
	 * @param totalTypeContext The totalTypeContext to set.
	 */
	public void setTotalTypeContext(String totalTypeContext) {
		this.totalTypeContext = totalTypeContext;
	}

}