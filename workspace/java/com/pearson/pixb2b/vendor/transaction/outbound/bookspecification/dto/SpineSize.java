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
 * Title		: 	SpineSize.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	20 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SpineSize is a data transfer object to store the 
 * Spine Size details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpineSize implements java.io.Serializable {
	private static final long serialVersionUID = 305297395578790647L;
	
	private SpineSizeVal value	= null;
	/**
	 * Default constructor.
	 */
	public SpineSize() {
		super();
	}
	/**
	 * @return the value
	 */
	public SpineSizeVal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SpineSizeVal value) {
		this.value = value;
	}
}