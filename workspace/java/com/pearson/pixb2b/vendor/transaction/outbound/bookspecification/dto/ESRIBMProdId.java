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
 * Title		: 	ESRIBMProdId.java
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
 * ESRIBMProdId is a data transfer object to store the Endsheet Reinforcement Binding Material
 * Characteristics Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESRIBMProdId implements java.io.Serializable {
	private static final long serialVersionUID = 2660876885131777118L;
	
	private ESRIBMProdIdentifier esribmProdId	= null;
	private ArrayList esribmSProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public ESRIBMProdId() {
		super();
	}
	/**
	 * @return the esribmProdId
	 */
	public ESRIBMProdIdentifier getEsribmProdId() {
		return esribmProdId;
	}
	/**
	 * @param esribmProdId the esribmProdId to set
	 */
	public void setEsribmProdId(ESRIBMProdIdentifier esribmProdId) {
		this.esribmProdId = esribmProdId;
	}
	/**
	 * @return the esribmSProdDesList
	 */
	public ArrayList getEsribmSProdDesList() {
		return esribmSProdDesList;
	}
	/**
	 * @param esribmSProdDesList the esribmSProdDesList to set
	 */
	public void setEsribmSProdDesList(ArrayList esribmSProdDesList) {
		this.esribmSProdDesList = esribmSProdDesList;
	}
}