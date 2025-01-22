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
 * Title		: 	SPPCLCCMarks.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCLCCMarks is a data transfer object to store the Spec Packing Pallet Packaging
 * Label Characteristics CustomerMarks details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCLCCMarks implements java.io.Serializable {
	private static final long serialVersionUID = 6477004074922816160L;
	
	private String sppcLCCMarks	= null;
	/**
	 * Default constructor.
	 */
	public SPPCLCCMarks() {
		super();
	}
	/**
	 * @return the sppcLCCMarks
	 */
	public String getSppcLCCMarks() {
		return sppcLCCMarks;
	}
	/**
	 * @param sppcLCCMarks the sppcLCCMarks to set
	 */
	public void setSppcLCCMarks(String sppcLCCMarks) {
		this.sppcLCCMarks = sppcLCCMarks;
	}
}
