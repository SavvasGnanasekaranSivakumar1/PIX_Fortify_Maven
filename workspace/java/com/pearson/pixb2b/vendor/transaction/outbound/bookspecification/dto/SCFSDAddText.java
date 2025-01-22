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
 * Title		: 	SCFSDAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSDAddText is a data transfer object to store the Specification Component Supplied Component 
 * FinishSpecs Decoration AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSDAddText implements java.io.Serializable {
	private static final long serialVersionUID = 8706487808970557760L;
	
	private String scFSDAddText	= null;
	/**
	 * Default constructor.
	 */
	public SCFSDAddText() {
		super();
	}
	/**
	 * @return the scFSDAddText
	 */
	public String getScFSDAddText() {
		return scFSDAddText;
	}
	/**
	 * @param scFSDAddText the scFSDAddText to set
	 */
	public void setScFSDAddText(String scFSDAddText) {
		this.scFSDAddText = scFSDAddText;
	}
}
