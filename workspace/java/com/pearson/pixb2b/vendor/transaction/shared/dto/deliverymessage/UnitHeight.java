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
 * Title		: 	UnitHeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitHeight is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box UnitItem Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitHeight implements java.io.Serializable {
	private static final long serialVersionUID = -2196239657800241837L;
	
	private UnitHghtVal unitHghtVal = null;
	/**
	 * Default constructor.
	 */
	public UnitHeight() {
		super();
	}
	/**
	 * @return the unitHghtVal
	 */
	public UnitHghtVal getUnitHghtVal() {
		return unitHghtVal;
	}
	/**
	 * @param unitHghtVal the unitHghtVal to set
	 */
	public void setUnitHghtVal(UnitHghtVal unitHghtVal) {
		this.unitHghtVal = unitHghtVal;
	}
}