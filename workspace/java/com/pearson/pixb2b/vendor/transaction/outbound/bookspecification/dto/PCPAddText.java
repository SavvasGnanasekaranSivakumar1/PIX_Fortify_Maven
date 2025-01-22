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
 * Title		: 	PCPAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPAddText is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Perforation AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPAddText implements java.io.Serializable {
	private static final long serialVersionUID = -6479690015627240348L;
	
	private String pcpAddText	= null;
	/**
	 * Default constructor.
	 */
	public PCPAddText() {
		super();
	}
	/**
	 * @return the pcpAddText
	 */
	public String getPcpAddText() {
		return pcpAddText;
	}
	/**
	 * @param pcpAddText the pcpAddText to set
	 */
	public void setPcpAddText(String pcpAddText) {
		this.pcpAddText = pcpAddText;
	}
}
