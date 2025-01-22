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
 * Title		: 	PCPrinMat.java
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
 * PCPrinMat is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printing Materials details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPrinMat implements java.io.Serializable {
	private static final long serialVersionUID = -7736423507951170020L;
	
	private PCPMatCharcs pcPMatCharcs 	= null;
	private ArrayList pcPMAddTextList 	= null;
	/**
	 * Default constructor.
	 */
	public PCPrinMat() {
		super();
	}
	/**
	 * @return the pcPMatCharcs
	 */
	public PCPMatCharcs getPcPMatCharcs() {
		return pcPMatCharcs;
	}
	/**
	 * @param pcPMatCharcs the pcPMatCharcs to set
	 */
	public void setPcPMatCharcs(PCPMatCharcs pcPMatCharcs) {
		this.pcPMatCharcs = pcPMatCharcs;
	}
	/**
	 * @return the pcPMAddTextList
	 */
	public ArrayList getPcPMAddTextList() {
		return pcPMAddTextList;
	}
	/**
	 * @param pcPMAddTextList the pcPMAddTextList to set
	 */
	public void setPcPMAddTextList(ArrayList pcPMAddTextList) {
		this.pcPMAddTextList = pcPMAddTextList;
	}
}
