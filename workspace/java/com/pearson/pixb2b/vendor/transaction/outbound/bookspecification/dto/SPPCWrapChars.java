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
 * Title		: 	SPPCWrapChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SPPCWrapChars is a data transfer object to store the Spec Packing Pallet Packaging
 * Wrap Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCWrapChars implements java.io.Serializable {
	private static final long serialVersionUID = 8165556189131969433L;
	
	private String packPerWrap		= null;
	private ArrayList sppcWrapList 	= null;
	/**
	 * Default constructor.
	 */
	public SPPCWrapChars() {
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
	 * @return the sppcWrapList
	 */
	public ArrayList getSppcWrapList() {
		return sppcWrapList;
	}
	/**
	 * @param sppcWrapList the sppcWrapList to set
	 */
	public void setSppcWrapList(ArrayList sppcWrapList) {
		this.sppcWrapList = sppcWrapList;
	}
}
