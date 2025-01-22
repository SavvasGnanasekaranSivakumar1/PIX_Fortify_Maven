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
 * Title		: 	BoxItemCount.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxItemCount is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box ItemCount details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxItemCount implements java.io.Serializable {
	private static final long serialVersionUID = 8595055170008085273L;
	
	private BoxItemCntVal boxItemCntVal = null;
	/**
	 * Default constructor.
	 */
	public BoxItemCount() {
		super();
	}
	/**
	 * @return the boxItemCntVal
	 */
	public BoxItemCntVal getBoxItemCntVal() {
		return boxItemCntVal;
	}
	/**
	 * @param boxItemCntVal the boxItemCntVal to set
	 */
	public void setBoxItemCntVal(BoxItemCntVal boxItemCntVal) {
		this.boxItemCntVal = boxItemCntVal;
	}
}