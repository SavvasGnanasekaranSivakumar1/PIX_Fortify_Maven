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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

/**
 * DetailValue is a data transfer object to store the 
 * Inventory status  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class DetailValue implements java.io.Serializable {	
	private static final long serialVersionUID = -4308518437092004439L;

	private String uom = null;
	private String value = null;
	
	/**
	 * Default constructor.
	 */
	public DetailValue() {
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
