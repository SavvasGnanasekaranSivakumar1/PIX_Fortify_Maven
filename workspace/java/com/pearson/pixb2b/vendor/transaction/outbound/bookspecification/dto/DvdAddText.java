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
 * Title		: 	DvdAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdAddText is a data transfer object to store the Specification NonPress Component 
 * Media DVD AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdAddText implements java.io.Serializable {
	private static final long serialVersionUID = 3119382765855220526L;
	
	private String dvdAddText	= null;
	/**
	 * Default constructor.
	 */
	public DvdAddText() {
		super();
	}
	/**
	 * @return the dvdAddText
	 */
	public String getDvdAddText() {
		return dvdAddText;
	}
	/**
	 * @param dvdAddText the dvdAddText to set
	 */
	public void setDvdAddText(String dvdAddText) {
		this.dvdAddText = dvdAddText;
	}
}
