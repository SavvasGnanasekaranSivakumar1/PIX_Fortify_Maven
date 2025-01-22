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
 * Title		: 	PackQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * PackQuantity is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PackQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -518346022291176080L;
	
	private String qtyType 			= null;
	private PackQtyVal packQtyVal 	= null;
	/**
	 * Default constructor.
	 */
	public PackQuantity() {
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
	 * @return the packQtyVal
	 */
	public PackQtyVal getPackQtyVal() {
		return packQtyVal;
	}
	/**
	 * @param packQtyVal the packQtyVal to set
	 */
	public void setPackQtyVal(PackQtyVal packQtyVal) {
		this.packQtyVal = packQtyVal;
	}
}