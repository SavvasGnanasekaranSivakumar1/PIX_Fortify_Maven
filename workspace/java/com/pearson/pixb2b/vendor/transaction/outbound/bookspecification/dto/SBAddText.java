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
 * Title		: 	SBAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SBAddText is a data transfer object to store the Additional  
 * Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SBAddText implements java.io.Serializable {
	private static final long serialVersionUID = -6749924289610862780L;
	
	private String sbAddText = null;
	/**
	 * Default constructor.
	 */
	public SBAddText() {
		super();
	}
	/**
	 * @return the sbAddText
	 */
	public String getSbAddText() {
		return sbAddText;
	}
	/**
	 * @param sbAddText the sbAddText to set
	 */
	public void setSbAddText(String sbAddText) {
		this.sbAddText = sbAddText;
	}
}