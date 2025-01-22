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
 * Title		: 	UnitItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitItem is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box UnitItem details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitItem implements java.io.Serializable {
	private static final long serialVersionUID = 8908768492189668407L;
	
	private BoxUnitChars boxUnitChars = null;
	/**
	 * Default constructor.
	 */
	public UnitItem() {
		super();
	}
	/**
	 * @return the boxUnitChars
	 */
	public BoxUnitChars getBoxUnitChars() {
		return boxUnitChars;
	}
	/**
	 * @param boxUnitChars the boxUnitChars to set
	 */
	public void setBoxUnitChars(BoxUnitChars boxUnitChars) {
		this.boxUnitChars = boxUnitChars;
	}
}