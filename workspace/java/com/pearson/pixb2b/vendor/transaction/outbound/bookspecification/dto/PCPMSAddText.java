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
 * Title		: 	PCPMSAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMSAddText is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMSAddText implements java.io.Serializable {
	private static final long serialVersionUID = -7174653476987695126L;
	
	private String pcPMSAddText	= null;
	/**
	 * Default constructor.
	 */
	public PCPMSAddText() {
		super();
	}
	/**
	 * @return the pcPMSAddText
	 */
	public String getPcPMSAddText() {
		return pcPMSAddText;
	}
	/**
	 * @param pcPMSAddText the pcPMSAddText to set
	 */
	public void setPcPMSAddText(String pcPMSAddText) {
		this.pcPMSAddText = pcPMSAddText;
	}
}
