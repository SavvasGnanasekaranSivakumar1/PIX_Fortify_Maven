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
 * Title		: 	Value.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * Value is a data transfer object to store the 
 * Value Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Value implements java.io.Serializable {
	private static final long serialVersionUID = -512840439404463719L;
	
	private String uom = null;
	private String valValue= null;
	/**
	 * Default constructor.
	 */
	public Value() {
		super();
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
	/**
	 * @return the valValue
	 */
	public String getValValue() {
		return valValue;
	}
	/**
	 * @param valValue the valValue to set
	 */
	public void setValValue(String valValue) {
		this.valValue = valValue;
	}
}
