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
 * Title		: 	GRReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   12 Jan, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * GRReference is a data transfer object to store the 
 * goodsreceipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class GRRef implements java.io.Serializable {
	private static final long serialVersionUID = 5752958795811801279L;
	
	private String grRefType = null;	
	private String grRefValue = null;
	
	/**
	* Default constructor.
	*/
	public GRRef() {
		super();
	}

	/**
	 * @return Returns the grRefType.
	 */
	public String getGrRefType() {
		return grRefType;
	}

	/**
	 * @param grRefType The grRefType to set.
	 */
	public void setGrRefType(String grRefType) {
		this.grRefType = grRefType;
	}

	/**
	 * @return Returns the grRefValue.
	 */
	public String getGrRefValue() {
		return grRefValue;
	}

	/**
	 * @param grRefValue The grRefValue to set.
	 */
	public void setGrRefValue(String grRefValue) {
		this.grRefValue = grRefValue;
	}
	
	
	
}