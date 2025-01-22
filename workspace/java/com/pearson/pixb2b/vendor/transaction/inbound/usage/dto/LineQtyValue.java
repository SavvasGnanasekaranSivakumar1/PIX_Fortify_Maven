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
 * Title		: 	SCQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;


/**
 * LineQtyValue is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LineQtyValue implements java.io.Serializable {
	private static final long serialVersionUID = 7304385309529276404L;

	private String qtyValue = null;
	private String UOM = null;	
	/**
	 * Default constructor.
	 */
	public LineQtyValue() {
		super();
	}
	/**
	 * @return the qtyValue
	 */
	public String getQtyValue() {
		return qtyValue;
	}
	/**
	 * @param qtyValue the qtyValue to set
	 */
	public void setQtyValue(String qtyValue) {
		this.qtyValue = qtyValue;
	}
	/**
	 * @return the uOM
	 */
	public String getUOM() {
		return UOM;
	}
	/**
	 * @param uom the uOM to set
	 */
	public void setUOM(String uom) {
		UOM = uom;
	}
}