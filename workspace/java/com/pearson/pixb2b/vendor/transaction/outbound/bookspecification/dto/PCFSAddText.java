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
 * Title		: 	PCFSAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCFSAddText is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs FlatSize AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSAddText implements java.io.Serializable {
	private static final long serialVersionUID = -5659220822723848762L;
	
	private String pcFSAddText	= null;
	/**
	 * Default constructor.
	 */
	public PCFSAddText() {
		super();
	}
	/**
	 * @return the pcFSAddText
	 */
	public String getPcFSAddText() {
		return pcFSAddText;
	}
	/**
	 * @param pcFSAddText the pcFSAddText to set
	 */
	public void setPcFSAddText(String pcFSAddText) {
		this.pcFSAddText = pcFSAddText;
	}
}
