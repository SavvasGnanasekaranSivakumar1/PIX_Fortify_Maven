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
 * Title		: 	BBBSpecBind.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BBBSpecBind is a data transfer object to store the 
 * BookBlock Bulk details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBBSpecBind implements java.io.Serializable {
	private static final long serialVersionUID = -5756269311918538662L;
	
	private BBBSpBindVal value	= null;
	/**
	 * Default constructor.
	 */
	public BBBSpecBind() {
		super();
	}
	/**
	 * @return the value
	 */
	public BBBSpBindVal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BBBSpBindVal value) {
		this.value = value;
	}
}