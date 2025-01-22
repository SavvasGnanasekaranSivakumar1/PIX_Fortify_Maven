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
 * Title		: 	OCHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

import java.util.ArrayList;

/**
 * ISLineItem is a data transfer object to store the 
 * Iinventory status  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISLineItem implements java.io.Serializable {	

	private static final long serialVersionUID = 1898582772783678819L;
	
	private String isLineItemNumber = null;
	private ISProduct isProduct 	= null;
	private ArrayList isLineRef 	= null;
	private ISQuantity isQty 		= null;
	/**
	 * Default constructor.
	 */
	public ISLineItem() {
		super();
	}
	/**
	 * @return the isLineItemNumber
	 */
	public String getIsLineItemNumber() {
		return isLineItemNumber;
	}
	/**
	 * @param isLineItemNumber the isLineItemNumber to set
	 */
	public void setIsLineItemNumber(String isLineItemNumber) {
		this.isLineItemNumber = isLineItemNumber;
	}
	/**
	 * @return the isProduct
	 */
	public ISProduct getIsProduct() {
		return isProduct;
	}
	/**
	 * @param isProduct the isProduct to set
	 */
	public void setIsProduct(ISProduct isProduct) {
		this.isProduct = isProduct;
	}
	/**
	 * @return the isLineRef
	 */
	public ArrayList getIsLineRef() {
		return isLineRef;
	}
	/**
	 * @param isLineRef the isLineRef to set
	 */
	public void setIsLineRef(ArrayList isLineRef) {
		this.isLineRef = isLineRef;
	}
	/**
	 * @return the isQty
	 */
	public ISQuantity getIsQty() {
		return isQty;
	}
	/**
	 * @param isQty the isQty to set
	 */
	public void setIsQty(ISQuantity isQty) {
		this.isQty = isQty;
	}	
}