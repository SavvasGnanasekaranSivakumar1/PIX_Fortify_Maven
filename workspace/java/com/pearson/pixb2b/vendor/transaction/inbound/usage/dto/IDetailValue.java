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
 * Title		: 	DetailValue.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * DetailValue is a data transfer object to store the 
 * Usage  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class IDetailValue implements java.io.Serializable {	
	private static final long serialVersionUID = 5822388919841391430L;

	private String uom = null;
	private String value = null;
	
	/**
	 * Default constructor.
	 */
	public IDetailValue() {
		super();
	}

	

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}



	/**
	 * @return Returns the uom.
	 */
	public String getUom() {
		return uom;
	}



	/**
	 * @param uom The uom to set.
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}


	
	
		
}
