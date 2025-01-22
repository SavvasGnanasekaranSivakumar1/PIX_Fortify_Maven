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
 * Title		: 	BoxWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxWidth is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxWidth implements java.io.Serializable {
	private static final long serialVersionUID = 5965312757642537413L;
	
	private BoxWidthVal boxWidthVal = null;
	/**
	 * Default constructor.
	 */
	public BoxWidth() {
		super();
	}
	/**
	 * @return the boxWidthVal
	 */
	public BoxWidthVal getBoxWidthVal() {
		return boxWidthVal;
	}
	/**
	 * @param boxWidthVal the boxWidthVal to set
	 */
	public void setBoxWidthVal(BoxWidthVal boxWidthVal) {
		this.boxWidthVal = boxWidthVal;
	}
}