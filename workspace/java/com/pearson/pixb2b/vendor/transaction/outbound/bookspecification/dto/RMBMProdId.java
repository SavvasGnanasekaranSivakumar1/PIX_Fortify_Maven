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
 * Title		: 	RMBMProdId.java
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
 * RMBMProdId is a data transfer object to store the Ribbon Binding Material Characteristics  
 * Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class RMBMProdId implements java.io.Serializable {
	private static final long serialVersionUID = 4166572784453051192L;
	
	private RMBMProdIdentifier rmbmProdId	= null;
	private ArrayList rmbmProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public RMBMProdId() {
		super();
	}
	/**
	 * @return the rmbmProdId
	 */
	public RMBMProdIdentifier getRmbmProdId() {
		return rmbmProdId;
	}
	/**
	 * @param rmbmProdId the rmbmProdId to set
	 */
	public void setRmbmProdId(RMBMProdIdentifier rmbmProdId) {
		this.rmbmProdId = rmbmProdId;
	}
	/**
	 * @return the rmbmProdDesList
	 */
	public ArrayList getRmbmProdDesList() {
		return rmbmProdDesList;
	}
	/**
	 * @param rmbmProdDesList the rmbmProdDesList to set
	 */
	public void setRmbmProdDesList(ArrayList rmbmProdDesList) {
		this.rmbmProdDesList = rmbmProdDesList;
	}
}