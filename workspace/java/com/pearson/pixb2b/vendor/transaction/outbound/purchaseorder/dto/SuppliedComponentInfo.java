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
 * Title		: 	SuppliedComponentInfo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;


/**
 * SuppliedComponentInfo is a transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SuppliedComponentInfo implements java.io.Serializable {
	private static final long serialVersionUID = -3666500834159270193L;

	
	private String suppliedComponentType = null;
	private String productDescription = null;
	private SCSupplierParty scSupplierParty = null;
	private SCProductIdentifier scProductIdentifier = null;
	private SCQuantity scQty = null;
	private ComponentDueDate componentDueDate = null;
	private ArrayList notes = null;
	
	
	/**
	 * Default constructor.
	 */
	public SuppliedComponentInfo() {
		super();
	}

	
	/**
	 * @return Returns the productDescription.
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription The productDescription to set.
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * @return Returns the componentDueDate.
	 */
	public ComponentDueDate getComponentDueDate() {
		return componentDueDate;
	}

	/**
	 * @param componentDueDate The componentDueDate to set.
	 */
	public void setComponentDueDate(ComponentDueDate componentDueDate) {
		this.componentDueDate = componentDueDate;
	}

	

	
	
	public String getSuppliedComponentType() {
		return suppliedComponentType;
	}
	public void setSuppliedComponentType(String suppliedComponentType) {
		this.suppliedComponentType = suppliedComponentType;
	}


	/**
	 * @return Returns the scSupplierParty.
	 */
	public SCSupplierParty getScSupplierParty() {
		return scSupplierParty;
	}


	/**
	 * @param scSupplierParty The scSupplierParty to set.
	 */
	public void setScSupplierParty(SCSupplierParty scSupplierParty) {
		this.scSupplierParty = scSupplierParty;
	}


	/**
	 * @return Returns the scProductIdentifier.
	 */
	public SCProductIdentifier getScProductIdentifier() {
		return scProductIdentifier;
	}


	/**
	 * @param scProductIdentifier The scProductIdentifier to set.
	 */
	public void setScProductIdentifier(SCProductIdentifier scProductIdentifier) {
		this.scProductIdentifier = scProductIdentifier;
	}


	/**
	 * @return Returns the scQty.
	 */
	public SCQuantity getScQty() {
		return scQty;
	}


	/**
	 * @param scQty The scQty to set.
	 */
	public void setScQty(SCQuantity scQty) {
		this.scQty = scQty;
	}
	
	
	/**
	 * @return Returns the notes.
	 */
	public ArrayList getNotes() {
		return notes;
	}


	/**
	 * @param notes The notes to set.
	 */
	public void setNotes(ArrayList notes) {
		this.notes = notes;
	}
	
	
	
}