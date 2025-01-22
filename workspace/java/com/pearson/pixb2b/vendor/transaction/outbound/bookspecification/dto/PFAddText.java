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
 * Title		: 	PFAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PFAddText is a data transfer object to store the Perforation Additional  
 * Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PFAddText implements java.io.Serializable {
	private static final long serialVersionUID = -4685511867960110087L;
	
	private String pfAddText = null;
	/**
	 * Default constructor.
	 */
	public PFAddText() {
		super();
	}
	/**
	 * @return the pfAddText
	 */
	public String getPfAddText() {
		return pfAddText;
	}
	/**
	 * @param pfAddText the pfAddText to set
	 */
	public void setPfAddText(String pfAddText) {
		this.pfAddText = pfAddText;
	}
}