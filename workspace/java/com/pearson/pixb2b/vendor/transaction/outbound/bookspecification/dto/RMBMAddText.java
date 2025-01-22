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
 * Title		: 	RMBMAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * RMBMAddText is a data transfer object to store the Ribbon   
 * Additional Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class RMBMAddText implements java.io.Serializable {
	private static final long serialVersionUID = 279251287749032818L;
	
	private String rmbmAddText = null;
	/**
	 * Default constructor.
	 */
	public RMBMAddText() {
		super();
	}
	/**
	 * @return the rmbmAddText
	 */
	public String getRmbmAddText() {
		return rmbmAddText;
	}
	/**
	 * @param rmbmAddText the rmbmAddText to set
	 */
	public void setRmbmAddText(String rmbmAddText) {
		this.rmbmAddText = rmbmAddText;
	}
}