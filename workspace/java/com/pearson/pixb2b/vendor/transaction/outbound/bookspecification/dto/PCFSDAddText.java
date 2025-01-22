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
 * Title		: 	PCFSDAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCFSDAddText is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications FinishSpecs Decoration AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSDAddText implements java.io.Serializable {
	private static final long serialVersionUID = 3045427003925163976L;
	
	private String pcFSDAddText	= null;
	/**
	 * Default constructor.
	 */
	public PCFSDAddText() {
		super();
	}
	/**
	 * @return the pcFSDAddText
	 */
	public String getPcFSDAddText() {
		return pcFSDAddText;
	}
	/**
	 * @param pcFSDAddText the pcFSDAddText to set
	 */
	public void setPcFSDAddText(String pcFSDAddText) {
		this.pcFSDAddText = pcFSDAddText;
	}
}
