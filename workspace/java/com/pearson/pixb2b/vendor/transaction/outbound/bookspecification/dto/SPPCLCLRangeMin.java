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
 * Title		: 	SPPCLCLRangeMin.java
 * Company 		: 	HCL Technologies
  *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCLCLRangeMin is a data transfer object to store the Spec Packing Pallet Packaging
 * Label Characteristics Length RangeMin details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCLCLRangeMin implements java.io.Serializable {
	private static final long serialVersionUID = -6119454472844990006L;
	
	private String uom 	= null;
	private String value= null;
	/**
	 * Default constructor.
	 */
	public SPPCLCLRangeMin() {
		super();
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
}
