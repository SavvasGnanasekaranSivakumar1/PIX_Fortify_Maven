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
 * Title		: 	SPBCWrapChars.java
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
 * SPBCWrapChars is a data transfer object to store the Spec Packing
 * Box Wrap Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCWrapChars implements java.io.Serializable {
	private static final long serialVersionUID = -990764248163275086L;
	
	private String packPerWrap		= null;
	private ArrayList spbcWrapList 	= null;
	/**
	 * Default constructor.
	 */
	public SPBCWrapChars() {
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
	 * @return the spbcWrapList
	 */
	public ArrayList getSpbcWrapList() {
		return spbcWrapList;
	}
	/**
	 * @param spbcWrapList the spbcWrapList to set
	 */
	public void setSpbcWrapList(ArrayList spbcWrapList) {
		this.spbcWrapList = spbcWrapList;
	}
}
