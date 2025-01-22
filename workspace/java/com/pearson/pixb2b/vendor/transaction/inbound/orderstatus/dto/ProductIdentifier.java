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
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;



/**
 * ProductIdentifier is a data transfer object to store the 
 * Product Identifier detail and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ProductIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -9101899873697622079L;
	
	private String agency 				 = null;
	private String productIdentifierType = null;
	private String productIdentifierVal  = null;
	/**
	 * Default constructor.
	 */
	public ProductIdentifier() {
		super();
	}
	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}
	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}
	/**
	 * @return the productIdentifierType
	 */
	public String getProductIdentifierType() {
		return productIdentifierType;
	}
	/**
	 * @param productIdentifierType the productIdentifierType to set
	 */
	public void setProductIdentifierType(String productIdentifierType) {
		this.productIdentifierType = productIdentifierType;
	}
	/**
	 * @return the productIdentifierVal
	 */
	public String getProductIdentifierVal() {
		return productIdentifierVal;
	}
	/**
	 * @param productIdentifierVal the productIdentifierVal to set
	 */
	public void setProductIdentifierVal(String productIdentifierVal) {
		this.productIdentifierVal = productIdentifierVal;
	}
}
