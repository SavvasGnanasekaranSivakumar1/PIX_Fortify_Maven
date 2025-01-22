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
 * Title		: 	POInformation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Vineeta Singh   22 Apr, 2013	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * ProductIdentification is a helper data transfer object to store the 
 * Product Identification Information details.
 * 
 * @author Vineeta Singh
 *
 */
public class ProductIdentification implements java.io.Serializable {
	private static final long serialVersionUID = -6129581974333428800L;
	
	private ProductIdentifier productIdentifier;
	/**
	 * Default constructor.
	 */
	public ProductIdentification() {
		super();
	}
	/**
	 * @return the productIdentifier
	 */
	public ProductIdentifier getProductIdentifier() {
		return productIdentifier;
	}
	/**
	 * @param productIdentifier the productIdentifier to set
	 */
	public void setProductIdentifier(ProductIdentifier productIdentifier) {
		this.productIdentifier = productIdentifier;
	}
	
	
}
