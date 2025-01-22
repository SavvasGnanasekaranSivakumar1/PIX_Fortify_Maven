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
 * Title		: 	SPPCPalLCCMarks.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPalLCCMarks is a data transfer object to store the Spec Packing Pallet
 * Label Characteristics CustomerMarks details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalLCCMarks implements java.io.Serializable {
	private static final long serialVersionUID = -497141022296824002L;
	
	private String sppcLCCMarks	= null;
	/**
	 * Default constructor.
	 */
	public SPPCPalLCCMarks() {
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
