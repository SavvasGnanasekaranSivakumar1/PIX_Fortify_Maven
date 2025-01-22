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
 * Title		: 	SBDSProdId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SBDSProdId is a data transfer object to store the specification Binding Case Decoration Coverage  
 * Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SBDSProdId implements java.io.Serializable {
	private static final long serialVersionUID = 1946084884046450651L;
	
	private SBDSProdIdentifier sbdsProdId	= null;
	private ArrayList sbdsProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public SBDSProdId() {
		super();
	}
	/**
	 * @return the sbdsProdId
	 */
	public SBDSProdIdentifier getSbdsProdId() {
		return sbdsProdId;
	}
	/**
	 * @param sbdsProdId the sbdsProdId to set
	 */
	public void setSbdsProdId(SBDSProdIdentifier sbdsProdId) {
		this.sbdsProdId = sbdsProdId;
	}
	/**
	 * @return the sbdsProdDesList
	 */
	public ArrayList getSbdsProdDesList() {
		return sbdsProdDesList;
	}
	/**
	 * @param sbdsProdDesList the sbdsProdDesList to set
	 */
	public void setSbdsProdDesList(ArrayList sbdsProdDesList) {
		this.sbdsProdDesList = sbdsProdDesList;
	}
}