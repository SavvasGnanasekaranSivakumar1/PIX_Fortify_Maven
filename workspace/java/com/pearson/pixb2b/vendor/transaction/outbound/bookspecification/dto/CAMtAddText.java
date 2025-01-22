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
 * Title		: 	CAMtAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CAMtAddText is a data transfer object to store the specification Binding   
 * CaseMaterial AdditionalText Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CAMtAddText implements java.io.Serializable {
	private static final long serialVersionUID = -3521361296457934408L;
	
	private String caMtAddText = null;
	/**
	 * Default constructor.
	 */
	public CAMtAddText() {
		super();
	}
	/**
	 * @return the caMtAddText
	 */
	public String getCaMtAddText() {
		return caMtAddText;
	}
	/**
	 * @param caMtAddText the caMtAddText to set
	 */
	public void setCaMtAddText(String caMtAddText) {
		this.caMtAddText = caMtAddText;
	}
}
