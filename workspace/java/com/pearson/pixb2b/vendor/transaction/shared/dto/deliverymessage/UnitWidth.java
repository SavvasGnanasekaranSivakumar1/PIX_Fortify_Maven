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
 * Title		: 	UnitWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitWidth is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box UnitItem Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitWidth implements java.io.Serializable {
	private static final long serialVersionUID = 8628308844700912696L;
	
	private UnitWidthVal unitWidthVal = null;
	/**
	 * Default constructor.
	 */
	public UnitWidth() {
		super();
	}
	/**
	 * @return the unitWidthVal
	 */
	public UnitWidthVal getUnitWidthVal() {
		return unitWidthVal;
	}
	/**
	 * @param unitWidthVal the unitWidthVal to set
	 */
	public void setUnitWidthVal(UnitWidthVal unitWidthVal) {
		this.unitWidthVal = unitWidthVal;
	}
}