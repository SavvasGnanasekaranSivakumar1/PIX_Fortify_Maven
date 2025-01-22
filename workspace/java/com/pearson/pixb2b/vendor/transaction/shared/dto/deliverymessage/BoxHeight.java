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
 * Title		: 	BoxHeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxHeight is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxHeight implements java.io.Serializable {
	private static final long serialVersionUID = -8198648237305518068L;
	
	private BoxHeightVal boxHeightVal = null;
	/**
	 * Default constructor.
	 */
	public BoxHeight() {
		super();
	}
	/**
	 * @return the boxHeightVal
	 */
	public BoxHeightVal getBoxHeightVal() {
		return boxHeightVal;
	}
	/**
	 * @param boxHeightVal the boxHeightVal to set
	 */
	public void setBoxHeightVal(BoxHeightVal boxHeightVal) {
		this.boxHeightVal = boxHeightVal;
	}
}