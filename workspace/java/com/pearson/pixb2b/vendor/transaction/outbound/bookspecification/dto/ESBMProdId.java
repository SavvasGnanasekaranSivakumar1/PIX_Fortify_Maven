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
 * Title		: 	ESBMProdId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ESBMProdId is a data transfer object to store the Endsheet Binding Material
 * Characteristics Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESBMProdId implements java.io.Serializable {
	private static final long serialVersionUID = 5335305739413570810L;
	
	private ESBMProdIdentifier esbmProdId	= null;
	private ArrayList esbmSProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public ESBMProdId() {
		super();
	}
	/**
	 * @return the esbmProdId
	 */
	public ESBMProdIdentifier getEsbmProdId() {
		return esbmProdId;
	}
	/**
	 * @param esbmProdId the esbmProdId to set
	 */
	public void setEsbmProdId(ESBMProdIdentifier esbmProdId) {
		this.esbmProdId = esbmProdId;
	}
	/**
	 * @return the esbmSProdDesList
	 */
	public ArrayList getEsbmSProdDesList() {
		return esbmSProdDesList;
	}
	/**
	 * @param esbmSProdDesList the esbmSProdDesList to set
	 */
	public void setEsbmSProdDesList(ArrayList esbmSProdDesList) {
		this.esbmSProdDesList = esbmSProdDesList;
	}
}