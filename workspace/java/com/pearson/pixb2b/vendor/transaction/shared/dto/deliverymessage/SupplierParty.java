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
 * Title		: 	SupplierParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

/**
 * SupplierParty is a data transfer object to store the DeliveryMessageBook Header 
 * SupplierParty details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SupplierParty implements java.io.Serializable {
	private static final long serialVersionUID = -350709571885731450L;
	
	private ArrayList supplierPartyIdList			= null;
	private SupplierPartyNmAdd supplierPartyNmAdd	= null;
	private ArrayList supplierPartyCCList 			= null;
	/**
	 * Default constructor.
	 */
	public SupplierParty() {
		super();
		supplierPartyIdList = new ArrayList();
	}
	/**
	 * @return the supplierPartyIdList
	 */
	public ArrayList getSupplierPartyIdList() {
		return supplierPartyIdList;
	}
	/**
	 * @param supplierPartyIdList the supplierPartyIdList to set
	 */
	public void setSupplierPartyIdList(ArrayList supplierPartyIdList) {
		this.supplierPartyIdList = supplierPartyIdList;
	}
	/**
	 * @return the supplierPartyNmAdd
	 */
	public SupplierPartyNmAdd getSupplierPartyNmAdd() {
		return supplierPartyNmAdd;
	}
	/**
	 * @param supplierPartyNmAdd the supplierPartyNmAdd to set
	 */
	public void setSupplierPartyNmAdd(SupplierPartyNmAdd supplierPartyNmAdd) {
		this.supplierPartyNmAdd = supplierPartyNmAdd;
	}
	/**
	 * @return the supplierPartyCCList
	 */
	public ArrayList getSupplierPartyCCList() {
		return supplierPartyCCList;
	}
	/**
	 * @param supplierPartyCCList the supplierPartyCCList to set
	 */
	public void setSupplierPartyCCList(ArrayList supplierPartyCCList) {
		this.supplierPartyCCList = supplierPartyCCList;
	}
}