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
 * Title		: 	UnitsPerCrtnVal.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * UnitsPerCrtnVal is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage UnitsPerCarton details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UnitsPerCrtnVal implements java.io.Serializable {
	private static final long serialVersionUID = 7957462026165134027L;
	
	private String value = null;
	private String uom 	 = null;
	/**
	 * Default constructor.
	 */
	public UnitsPerCrtnVal() {
		super();
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
}