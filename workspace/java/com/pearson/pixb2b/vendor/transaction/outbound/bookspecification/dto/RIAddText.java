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
 * Title		: 	RIAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * RIAddText is a data transfer object to store the Ribbon Additional  
 * Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class RIAddText implements java.io.Serializable {
	private static final long serialVersionUID = -4142333803482605264L;
	
	private String riAddText = null;
	/**
	 * Default constructor.
	 */
	public RIAddText() {
		super();
	}
	/**
	 * @return the riAddText
	 */
	public String getRiAddText() {
		return riAddText;
	}
	/**
	 * @param riAddText the riAddText to set
	 */
	public void setRiAddText(String riAddText) {
		this.riAddText = riAddText;
	}
}