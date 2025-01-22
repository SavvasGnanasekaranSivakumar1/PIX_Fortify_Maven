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
 * Title		: 	UnitWeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitWeight is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box UnitItem Weight details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitWeight implements java.io.Serializable {
	private static final long serialVersionUID = -8388617856533977350L;
	
	private UnitWeightVal unitWeightVal = null;
	/**
	 * Default constructor.
	 */
	public UnitWeight() {
		super();
	}
	/**
	 * @return the unitWeightVal
	 */
	public UnitWeightVal getUnitWeightVal() {
		return unitWeightVal;
	}
	/**
	 * @param unitWeightVal the unitWeightVal to set
	 */
	public void setUnitWeightVal(UnitWeightVal unitWeightVal) {
		this.unitWeightVal = unitWeightVal;
	}
}