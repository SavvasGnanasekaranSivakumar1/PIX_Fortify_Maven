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
 * Title		: 	SPPCProdId.java
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
 * SPPCProdId is a data transfer object to store the Spec Packing Pallet Characteristics  
 * Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCProdId implements java.io.Serializable {
	private static final long serialVersionUID = 6434224563603042397L;
	
	private SPPCProdIdentifier spbcProdIdentifier	= null;
	private ArrayList sppcProdDesList				= null;
	/**
	 * Default constructor.
	 */
	public SPPCProdId() {
		super();
	}
	/**
	 * @return the spbcProdIdentifier
	 */
	public SPPCProdIdentifier getSpbcProdIdentifier() {
		return spbcProdIdentifier;
	}
	/**
	 * @param spbcProdIdentifier the spbcProdIdentifier to set
	 */
	public void setSpbcProdIdentifier(SPPCProdIdentifier spbcProdIdentifier) {
		this.spbcProdIdentifier = spbcProdIdentifier;
	}
	/**
	 * @return the sppcProdDesList
	 */
	public ArrayList getSppcProdDesList() {
		return sppcProdDesList;
	}
	/**
	 * @param sppcProdDesList the sppcProdDesList to set
	 */
	public void setSppcProdDesList(ArrayList sppcProdDesList) {
		this.sppcProdDesList = sppcProdDesList;
	}
}