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
 * Title		: 	ProductIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;


/**
 * IS ProductIdentifier is a data transfer object to store the 
 *inventory status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISProductIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -545101660008417999L;
	
	private String productAgency = null;
	private String productIdType = null;
	private String productIdValue = null;
	/**
	 * Default constructor.
	 */
	public ISProductIdentifier() {
		super();
	}
	public String getProductIdType() {
		return productIdType;
	}
	public void setProductIdType(String productIdType) {
		this.productIdType = productIdType;
	}
	public String getProductIdValue() {
		return productIdValue;
	}
	public void setProductIdValue(String productIdValue) {
		this.productIdValue = productIdValue;
	}
	/**
	 * @return Returns the productAgency.
	 */
	public String getProductAgency() {
		return productAgency;
	}
	/**
	 * @param productAgency The productAgency to set.
	 */
	public void setProductAgency(String productAgency) {
		this.productAgency = productAgency;
	}
		
}