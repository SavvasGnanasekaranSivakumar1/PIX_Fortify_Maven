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
 * Title		: 	UnitsPerCrtn.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitsPerCrtn is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Units PerCarton details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitsPerCrtn implements java.io.Serializable {
	private static final long serialVersionUID = -7584839846605957035L;
	
	private UnitsPerCrtnVal unitsPerCrtnVal = null;
	/**
	 * Default constructor.
	 */
	public UnitsPerCrtn() {
		super();
	}
	/**
	 * @return the unitsPerCrtnVal
	 */
	public UnitsPerCrtnVal getUnitsPerCrtnVal() {
		return unitsPerCrtnVal;
	}
	/**
	 * @param unitsPerCrtnVal the unitsPerCrtnVal to set
	 */
	public void setUnitsPerCrtnVal(UnitsPerCrtnVal unitsPerCrtnVal) {
		this.unitsPerCrtnVal = unitsPerCrtnVal;
	}
}