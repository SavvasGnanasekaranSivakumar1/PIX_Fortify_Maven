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
 * Title		: 	SPBCProdId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SPBCProdId is a data transfer object to store the Spec Packing Box Characteristics  
 * Product Identification details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCProdId implements java.io.Serializable {
	private static final long serialVersionUID = 5155419025307523626L;
	
	private SPBCProdIdentifier spbcProdIden	= null;
	private ArrayList spbcProdDesList		= null;
	/**
	 * Default constructor.
	 */
	public SPBCProdId() {
		super();
	}
	/**
	 * @return the spbcProdIden
	 */
	public SPBCProdIdentifier getSpbcProdIden() {
		return spbcProdIden;
	}
	/**
	 * @param spbcProdIden the spbcProdIden to set
	 */
	public void setSpbcProdIden(SPBCProdIdentifier spbcProdIden) {
		this.spbcProdIden = spbcProdIden;
	}
	/**
	 * @return the spbcProdDesList
	 */
	public ArrayList getSpbcProdDesList() {
		return spbcProdDesList;
	}
	/**
	 * @param spbcProdDesList the spbcProdDesList to set
	 */
	public void setSpbcProdDesList(ArrayList spbcProdDesList) {
		this.spbcProdDesList = spbcProdDesList;
	}
}