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
 * Title		: 	PCFSProdId.java
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
 * PCFSProdId is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications FinishSpecs Decoration Product Identification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSProdId implements java.io.Serializable {
	private static final long serialVersionUID = 9072667766714464465L;
	
	private PCFSProdIdentifier pcfsProdId	= null;
	private ArrayList pcfsProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public PCFSProdId() {
		super();
	}
	/**
	 * @return the pcfsProdId
	 */
	public PCFSProdIdentifier getPcfsProdId() {
		return pcfsProdId;
	}
	/**
	 * @param pcfsProdId the pcfsProdId to set
	 */
	public void setPcfsProdId(PCFSProdIdentifier pcfsProdId) {
		this.pcfsProdId = pcfsProdId;
	}
	/**
	 * @return the pcfsProdDesList
	 */
	public ArrayList getPcfsProdDesList() {
		return pcfsProdDesList;
	}
	/**
	 * @param pcfsProdDesList the pcfsProdDesList to set
	 */
	public void setPcfsProdDesList(ArrayList pcfsProdDesList) {
		this.pcfsProdDesList = pcfsProdDesList;
	}
}