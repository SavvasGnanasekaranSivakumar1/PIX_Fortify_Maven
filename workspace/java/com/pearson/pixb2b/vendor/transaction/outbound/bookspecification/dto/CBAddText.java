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
 * Title		: 	CBAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CBAddText is a data transfer object to store the specification Binding CaseMaking   
 * CaseBoard AdditionalText Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CBAddText implements java.io.Serializable {
	private static final long serialVersionUID = 3862666922582160171L;
	
	private String cbAddText = null;
	/**
	 * Default constructor.
	 */
	public CBAddText() {
		super();
	}
	/**
	 * @return the cbAddText
	 */
	public String getCbAddText() {
		return cbAddText;
	}
	/**
	 * @param cbAddText the cbAddText to set
	 */
	public void setCbAddText(String cbAddText) {
		this.cbAddText = cbAddText;
	}
}
