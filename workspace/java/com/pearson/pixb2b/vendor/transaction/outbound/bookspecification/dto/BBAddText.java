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
 * Title		: 	BBAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BBAddText is a data transfer object to store the Book Block EdgeTrim 
 * AdditionalText details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBAddText implements java.io.Serializable {
	private static final long serialVersionUID = -7326529294482844665L;
	
	private String bbAddText = null;
	/**
	 * Default constructor.
	 */
	public BBAddText() {
		super();
	}
	/**
	 * @return the bbAddText
	 */
	public String getBbAddText() {
		return bbAddText;
	}
	/**
	 * @param bbAddText the bbAddText to set
	 */
	public void setBbAddText(String bbAddText) {
		this.bbAddText = bbAddText;
	}
}
