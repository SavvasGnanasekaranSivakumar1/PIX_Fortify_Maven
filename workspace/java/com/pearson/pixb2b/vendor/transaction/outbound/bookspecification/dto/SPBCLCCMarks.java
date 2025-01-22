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
 * Title		: 	SPBCLCCMarks.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCLCCMarks is a data transfer object to store the Spec Packing
 * Box Label Characteristics CustomerMarks details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCLCCMarks implements java.io.Serializable {
	private static final long serialVersionUID = 5585006714884756404L;
	
	private String spbcLCCMarks	= null;
	/**
	 * Default constructor.
	 */
	public SPBCLCCMarks() {
		super();
	}
	/**
	 * @return the spbcLCCMarks
	 */
	public String getSpbcLCCMarks() {
		return spbcLCCMarks;
	}
	/**
	 * @param spbcLCCMarks the spbcLCCMarks to set
	 */
	public void setSpbcLCCMarks(String spbcLCCMarks) {
		this.spbcLCCMarks = spbcLCCMarks;
	}
}
