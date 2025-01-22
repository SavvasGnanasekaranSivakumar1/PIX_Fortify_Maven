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
 * Title		: 	BUPWrapChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BUPWrapChars is a data transfer object to store the Book Spec Unit Packing
 * Wrap Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPWrapChars implements java.io.Serializable {
	private static final long serialVersionUID = 6150551361961609584L;
	
	private String packPerWrap		= null;
	private ArrayList bupWrapList 	= null;
	/**
	 * Default constructor.
	 */
	public BUPWrapChars() {
		super();
	}
	/**
	 * @return the packPerWrap
	 */
	public String getPackPerWrap() {
		return packPerWrap;
	}
	/**
	 * @param packPerWrap the packPerWrap to set
	 */
	public void setPackPerWrap(String packPerWrap) {
		this.packPerWrap = packPerWrap;
	}
	/**
	 * @return the bupWrapList
	 */
	public ArrayList getBupWrapList() {
		return bupWrapList;
	}
	/**
	 * @param bupWrapList the bupWrapList to set
	 */
	public void setBupWrapList(ArrayList bupWrapList) {
		this.bupWrapList = bupWrapList;
	}
}
