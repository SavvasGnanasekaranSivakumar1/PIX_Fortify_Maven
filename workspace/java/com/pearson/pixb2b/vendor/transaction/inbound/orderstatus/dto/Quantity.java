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
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * Quantity is a data transfer object to store the 
 * Quantity Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Quantity implements java.io.Serializable {
	private static final long serialVersionUID = 2288926342728412375L;
	
	private String quantityType = null;
	private Value val= null;
	/**
	 * Default constructor.
	 */
	public Quantity() {
		super();
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
	 * @return the val
	 */
	public Value getVal() {
		return val;
	}
	/**
	 * @param val the val to set
	 */
	public void setVal(Value val) {
		this.val = val;
	}
}
