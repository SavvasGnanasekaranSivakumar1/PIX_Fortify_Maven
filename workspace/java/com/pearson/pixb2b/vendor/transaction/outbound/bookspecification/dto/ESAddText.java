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
 * Title		: 	ESAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ESAddText is a data transfer object to store the Endsheet Material 
 * AdditionalText details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESAddText implements java.io.Serializable {
	private static final long serialVersionUID = -3811138370142022816L;
	
	private String esAddText = null;	
	/**
	 * Default constructor.
	 */
	public ESAddText() {
		super();
	}
	/**
	 * @return the esAddText
	 */
	public String getEsAddText() {
		return esAddText;
	}
	/**
	 * @param esAddText the esAddText to set
	 */
	public void setEsAddText(String esAddText) {
		this.esAddText = esAddText;
	}
}
