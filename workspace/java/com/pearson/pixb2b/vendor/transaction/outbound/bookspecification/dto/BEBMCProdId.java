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
 * Title		: 	BEBMCProdId.java
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
 * BEBMCProdId is a data transfer object to store the Binding Extras BindingMaterial Characteristics  
 * Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BEBMCProdId implements java.io.Serializable {
	private static final long serialVersionUID = -140723466090108203L;
	
	private BEBMCProdIdentifier bebmcProdId	= null;
	private ArrayList bebmcProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public BEBMCProdId() {
		super();
	}
	/**
	 * @return the bebmcProdId
	 */
	public BEBMCProdIdentifier getBebmcProdId() {
		return bebmcProdId;
	}
	/**
	 * @param bebmcProdId the bebmcProdId to set
	 */
	public void setBebmcProdId(BEBMCProdIdentifier bebmcProdId) {
		this.bebmcProdId = bebmcProdId;
	}
	/**
	 * @return the bebmcProdDesList
	 */
	public ArrayList getBebmcProdDesList() {
		return bebmcProdDesList;
	}
	/**
	 * @param bebmcProdDesList the bebmcProdDesList to set
	 */
	public void setBebmcProdDesList(ArrayList bebmcProdDesList) {
		this.bebmcProdDesList = bebmcProdDesList;
	}
}