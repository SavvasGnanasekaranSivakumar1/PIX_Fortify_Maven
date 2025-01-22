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
 * Title		: 	SPAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPAddText is a data transfer object to store store the Book Spec Packing 
 * Additional Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPAddText implements java.io.Serializable {
	private static final long serialVersionUID = -6032260062707526675L;
	
	private String spAddText = null;
	/**
	 * Default constructor.
	 */
	public SPAddText() {
		super();
	}
	/**
	 * @return the spAddText
	 */
	public String getSpAddText() {
		return spAddText;
	}
	/**
	 * @param spAddText the spAddText to set
	 */
	public void setSpAddText(String spAddText) {
		this.spAddText = spAddText;
	}
}