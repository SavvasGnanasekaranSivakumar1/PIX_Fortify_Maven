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
 * Title		: 	CLRangeMax.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CLRangeMax is a data transfer object to store the specification Binding   
 * CaseLining RangeMax details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CLRangeMax implements java.io.Serializable {
	private static final long serialVersionUID = 4325066980044856962L;
	
	private String uom 	= null;
	private String value= null;
	/**
	 * Default constructor.
	 */
	public CLRangeMax() {
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
