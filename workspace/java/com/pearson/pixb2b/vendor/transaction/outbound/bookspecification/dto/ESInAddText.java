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
 * Title		: 	ESInAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ESInAddText is a data transfer object to store the Endsheet Information 
 * AdditionalText details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESInAddText implements java.io.Serializable {
	private static final long serialVersionUID = -5286485532955303116L;
	
	private String esInAddText = null;		
	/**
	 * Default constructor.
	 */
	public ESInAddText() {
		super();
	}
	/**
	 * @return the esInAddText
	 */
	public String getEsInAddText() {
		return esInAddText;
	}
	/**
	 * @param esInAddText the esInAddText to set
	 */
	public void setEsInAddText(String esInAddText) {
		this.esInAddText = esInAddText;
	}
}
