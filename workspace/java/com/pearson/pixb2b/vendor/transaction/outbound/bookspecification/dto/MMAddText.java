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
 * Title		: 	MMAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MMAddText is a data transfer object to store the specification Binding Mechanical Material  
 * Additional Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MMAddText implements java.io.Serializable {
	private static final long serialVersionUID = 3595162545119028017L;
	
	private String mmaddText = null;
	/**
	 * Default constructor.
	 */
	public MMAddText() {
		super();
	}
	/**
	 * @return the mmaddText
	 */
	public String getMmaddText() {
		return mmaddText;
	}
	/**
	 * @param mmaddText the mmaddText to set
	 */
	public void setMmaddText(String mmaddText) {
		this.mmaddText = mmaddText;
	}
}