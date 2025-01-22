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
 * Title		: 	CDAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CDAddText is a data transfer object to store the Specification NonPress Component 
 * Media CD AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDAddText implements java.io.Serializable {
	private static final long serialVersionUID = -7763929641681964968L;
	
	private String cdAddText = null;
	/**
	 * Default constructor.
	 */
	public CDAddText() {
		super();
	}
	/**
	 * @return the cdAddText
	 */
	public String getCdAddText() {
		return cdAddText;
	}
	/**
	 * @param cdAddText the cdAddText to set
	 */
	public void setCdAddText(String cdAddText) {
		this.cdAddText = cdAddText;
	}
}
