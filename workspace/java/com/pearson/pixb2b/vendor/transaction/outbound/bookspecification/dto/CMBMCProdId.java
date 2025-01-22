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
 * Title		: 	CMBMCProdId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * CMBMCProdId is a data transfer object to store the CaseMaterial BindingMaterial Characteristics  
 * Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CMBMCProdId implements java.io.Serializable {
	private static final long serialVersionUID = -5513801449230526504L;
	
	private CMBMCProdIdentifier cmbmcProdId	= null;
	private ArrayList cmbmcProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public CMBMCProdId() {
		super();
	}
	/**
	 * @return the cmbmcProdId
	 */
	public CMBMCProdIdentifier getCmbmcProdId() {
		return cmbmcProdId;
	}
	/**
	 * @param cmbmcProdId the cmbmcProdId to set
	 */
	public void setCmbmcProdId(CMBMCProdIdentifier cmbmcProdId) {
		this.cmbmcProdId = cmbmcProdId;
	}
	/**
	 * @return the cmbmcProdDesList
	 */
	public ArrayList getCmbmcProdDesList() {
		return cmbmcProdDesList;
	}
	/**
	 * @param cmbmcProdDesList the cmbmcProdDesList to set
	 */
	public void setCmbmcProdDesList(ArrayList cmbmcProdDesList) {
		this.cmbmcProdDesList = cmbmcProdDesList;
	}
}