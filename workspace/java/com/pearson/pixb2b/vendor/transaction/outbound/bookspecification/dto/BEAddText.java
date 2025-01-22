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
 * Title		: 	BEAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BEAddText is a data transfer object to store the Binding Extra
 * AdditionalText details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BEAddText implements java.io.Serializable {
	private static final long serialVersionUID = 7235477353800035386L;
	
	private String beAddText = null;
	/**
	 * Default constructor.
	 */
	public BEAddText() {
		super();
	}
	/**
	 * @return the beAddText
	 */
	public String getBeAddText() {
		return beAddText;
	}
	/**
	 * @param beAddText the beAddText to set
	 */
	public void setBeAddText(String beAddText) {
		this.beAddText = beAddText;
	}
}
