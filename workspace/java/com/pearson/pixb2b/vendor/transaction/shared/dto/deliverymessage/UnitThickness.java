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
 * Title		: 	UnitThickness.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitThickness is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box UnitItem Thickness details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitThickness implements java.io.Serializable {
	private static final long serialVersionUID = -8268121180857393004L;
	
	private UnitThicknessVal unitThicknessVal = null;
	/**
	 * Default constructor.
	 */
	public UnitThickness() {
		super();
	}
	/**
	 * @return the unitThicknessVal
	 */
	public UnitThicknessVal getUnitThicknessVal() {
		return unitThicknessVal;
	}
	/**
	 * @param unitThicknessVal the unitThicknessVal to set
	 */
	public void setUnitThicknessVal(UnitThicknessVal unitThicknessVal) {
		this.unitThicknessVal = unitThicknessVal;
	}
}