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
 * Title		: 	BoxLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxLength is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxLength implements java.io.Serializable {
	private static final long serialVersionUID = -1050140461547286886L;
	
	private BoxLengthVal boxLengthVal = null;
	/**
	 * Default constructor.
	 */
	public BoxLength() {
		super();
	}
	/**
	 * @return the boxLengthVal
	 */
	public BoxLengthVal getBoxLengthVal() {
		return boxLengthVal;
	}
	/**
	 * @param boxLengthVal the boxLengthVal to set
	 */
	public void setBoxLengthVal(BoxLengthVal boxLengthVal) {
		this.boxLengthVal = boxLengthVal;
	}
}