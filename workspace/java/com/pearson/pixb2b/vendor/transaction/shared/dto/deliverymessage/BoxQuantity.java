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
 * Title		: 	BoxQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxQuantity is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information BoxItem Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -2948991045818016970L;
	
	private String qtyType 					= null;
	private BoxQuantityVal boxQuantityVal 	= null;
	/**
	 * Default constructor.
	 */
	public BoxQuantity() {
		super();
	}
	/**
	 * @return the qtyType
	 */
	public String getQtyType() {
		return qtyType;
	}
	/**
	 * @param qtyType the qtyType to set
	 */
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}
	/**
	 * @return the boxQuantityVal
	 */
	public BoxQuantityVal getBoxQuantityVal() {
		return boxQuantityVal;
	}
	/**
	 * @param boxQuantityVal the boxQuantityVal to set
	 */
	public void setBoxQuantityVal(BoxQuantityVal boxQuantityVal) {
		this.boxQuantityVal = boxQuantityVal;
	}
}