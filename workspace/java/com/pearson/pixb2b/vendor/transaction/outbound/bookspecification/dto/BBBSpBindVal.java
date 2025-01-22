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
 * Title		: 	BBBSpBindVal.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BBBSpBindVal is a data transfer object to store the 
 * BookBlock Bulk Value details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBBSpBindVal implements java.io.Serializable {
	private static final long serialVersionUID = -2603093407730140113L;
	
	private String valValue = null;
	private String uom		= null;
	/**
	 * Default constructor.
	 */
	public BBBSpBindVal() {
		super();
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
}