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
 * Title		: 	BBBulk.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	20 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BBBulk is a data transfer object to store the 
 * BookBlock Bulk details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBBulk implements java.io.Serializable {
	private static final long serialVersionUID = -4981553486670285198L;
	
	private BBBulkVal value	= null;
	/**
	 * Default constructor.
	 */
	public BBBulk() {
		super();
	}
	/**
	 * @return the value
	 */
	public BBBulkVal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BBBulkVal value) {
		this.value = value;
	}
}