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
 * Title		: 	SBDSAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SBDSAddText is a data transfer object to store the specification Binding Case  
 * DecorationSpecs Additional Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SBDSAddText implements java.io.Serializable {
	private static final long serialVersionUID = -7757212146126712226L;
	
	private String addText = null;
	/**
	 * Default constructor.
	 */
	public SBDSAddText() {
		super();
	}
	/**
	 * @return the addText
	 */
	public String getAddText() {
		return addText;
	}
	/**
	 * @param addText the addText to set
	 */
	public void setAddText(String addText) {
		this.addText = addText;
	}
}