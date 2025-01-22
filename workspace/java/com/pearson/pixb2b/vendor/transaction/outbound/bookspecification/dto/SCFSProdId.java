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
 * Title		: 	SCFSProdId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SCFSProdId is a data transfer object to store the Specification Component Supplied Component
 * FinishSpecs Decoration Coverage Product Identification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSProdId implements java.io.Serializable {
	private static final long serialVersionUID = 3476384800338892072L;
	
	private SCFSProdIdentifier sbdsProdId	= null;
	private ArrayList scfsProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public SCFSProdId() {
		super();
	}
	/**
	 * @return the sbdsProdId
	 */
	public SCFSProdIdentifier getSbdsProdId() {
		return sbdsProdId;
	}
	/**
	 * @param sbdsProdId the sbdsProdId to set
	 */
	public void setSbdsProdId(SCFSProdIdentifier sbdsProdId) {
		this.sbdsProdId = sbdsProdId;
	}
	/**
	 * @return the scfsProdDesList
	 */
	public ArrayList getScfsProdDesList() {
		return scfsProdDesList;
	}
	/**
	 * @param scfsProdDesList the scfsProdDesList to set
	 */
	public void setScfsProdDesList(ArrayList scfsProdDesList) {
		this.scfsProdDesList = scfsProdDesList;
	}
}