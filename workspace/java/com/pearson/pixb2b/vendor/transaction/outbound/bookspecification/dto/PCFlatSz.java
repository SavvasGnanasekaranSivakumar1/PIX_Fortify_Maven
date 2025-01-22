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
 * Title		: 	PCFlatSz.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCFlatSz is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs FlatSize details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFlatSz implements java.io.Serializable {
	private static final long serialVersionUID = -8138968229217141225L;
	
	private PCFSLength pcFSLength		= null;
	private PCFSWidth pcFSWidth			= null;
	private ArrayList pcFSAddTextList 	= null;
	/**
	 * Default constructor.
	 */
	public PCFlatSz() {
		super();
	}
	/**
	 * @return the pcFSLength
	 */
	public PCFSLength getPcFSLength() {
		return pcFSLength;
	}
	/**
	 * @param pcFSLength the pcFSLength to set
	 */
	public void setPcFSLength(PCFSLength pcFSLength) {
		this.pcFSLength = pcFSLength;
	}
	/**
	 * @return the pcFSWidth
	 */
	public PCFSWidth getPcFSWidth() {
		return pcFSWidth;
	}
	/**
	 * @param pcFSWidth the pcFSWidth to set
	 */
	public void setPcFSWidth(PCFSWidth pcFSWidth) {
		this.pcFSWidth = pcFSWidth;
	}
	/**
	 * @return the pcFSAddTextList
	 */
	public ArrayList getPcFSAddTextList() {
		return pcFSAddTextList;
	}
	/**
	 * @param pcFSAddTextList the pcFSAddTextList to set
	 */
	public void setPcFSAddTextList(ArrayList pcFSAddTextList) {
		this.pcFSAddTextList = pcFSAddTextList;
	}
}
