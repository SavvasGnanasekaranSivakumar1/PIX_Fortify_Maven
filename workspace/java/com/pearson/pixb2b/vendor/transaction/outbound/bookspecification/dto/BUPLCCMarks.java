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
 * Title		: 	BUPLCCMarks.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPLCCMarks is a data transfer object to store the Book Spec Unit Packing
 * Label Characteristics CustomerMarks details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPLCCMarks implements java.io.Serializable {
	private static final long serialVersionUID = -7256130733148759729L;
	
	private String bupLCCMarks 	= null;
	/**
	 * Default constructor.
	 */
	public BUPLCCMarks() {
		super();
	}
	/**
	 * @return the bupLCCMarks
	 */
	public String getBupLCCMarks() {
		return bupLCCMarks;
	}
	/**
	 * @param bupLCCMarks the bupLCCMarks to set
	 */
	public void setBupLCCMarks(String bupLCCMarks) {
		this.bupLCCMarks = bupLCCMarks;
	}
}
