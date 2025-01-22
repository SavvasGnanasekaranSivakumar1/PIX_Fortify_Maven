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
 * Title		: 	PCPMAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMAddText is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printing Materials AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMAddText implements java.io.Serializable {
	private static final long serialVersionUID = 4621064256382532972L;
	
	private String pcpmAddText	= null;
	/**
	 * Default constructor.
	 */
	public PCPMAddText() {
		super();
	}
	/**
	 * @return the pcpmAddText
	 */
	public String getPcpmAddText() {
		return pcpmAddText;
	}
	/**
	 * @param pcpmAddText the pcpmAddText to set
	 */
	public void setPcpmAddText(String pcpmAddText) {
		this.pcpmAddText = pcpmAddText;
	}
}
