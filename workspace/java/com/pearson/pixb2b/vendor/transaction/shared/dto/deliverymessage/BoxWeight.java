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
 * Title		: 	BoxWeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxWeight is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box Weight details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxWeight implements java.io.Serializable {
	private static final long serialVersionUID = 3303306397922300869L;
	
	private BoxWeightVal boxWeightVal = null;
	/**
	 * Default constructor.
	 */
	public BoxWeight() {
		super();
	}
	/**
	 * @return the boxWeightVal
	 */
	public BoxWeightVal getBoxWeightVal() {
		return boxWeightVal;
	}
	/**
	 * @param boxWeightVal the boxWeightVal to set
	 */
	public void setBoxWeightVal(BoxWeightVal boxWeightVal) {
		this.boxWeightVal = boxWeightVal;
	}
}