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
 * Title		: 	SCSComAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCSComAddText is a data transfer object to store the Specification Component 
 * Supplied Component AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCSComAddText implements java.io.Serializable {
	private static final long serialVersionUID = -6937770501120774122L;
	
	private String scSComAddText = null;
	/**
	 * Default constructor.
	 */
	public SCSComAddText() {
		super();
	}
	/**
	 * @return the scSComAddText
	 */
	public String getScSComAddText() {
		return scSComAddText;
	}
	/**
	 * @param scSComAddText the scSComAddText to set
	 */
	public void setScSComAddText(String scSComAddText) {
		this.scSComAddText = scSComAddText;
	}
}
