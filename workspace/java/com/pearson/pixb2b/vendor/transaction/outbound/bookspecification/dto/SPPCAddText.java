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
 * Title		: 	SPPCAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCAddText is a data transfer object to store store the Book Spec Packing 
 * Additional Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCAddText implements java.io.Serializable {
	private static final long serialVersionUID = -2566060384180133829L;
	
	private String sppcAddText = null;
	/**
	 * Default constructor.
	 */
	public SPPCAddText() {
		super();
	}
	/**
	 * @return the sppcAddText
	 */
	public String getSppcAddText() {
		return sppcAddText;
	}
	/**
	 * @param sppcAddText the sppcAddText to set
	 */
	public void setSppcAddText(String sppcAddText) {
		this.sppcAddText = sppcAddText;
	}
}