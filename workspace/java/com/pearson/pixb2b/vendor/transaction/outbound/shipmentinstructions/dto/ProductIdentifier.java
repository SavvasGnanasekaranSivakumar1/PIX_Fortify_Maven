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
 * ProductIdentifier is a helper data transfer object to store the 
 * ProductIdentifier Information details.
 * 
 * @author Vineeta Singh
 *
 */
public class ProductIdentifier {
	private static final long serialVersionUID = -6129581974333428800L;
	
	private String partNumber = null;
	private String productIdentifierAgency = null;
	private String productIdentifierType = null;
	
	/**
	 * Default constructor.
	 */
	public ProductIdentifier() {
		super();
	}

	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @return the productIdentifierAgency
	 */
	public String getProductIdentifierAgency() {
		return productIdentifierAgency;
	}

	/**
	 * @param productIdentifierAgency the productIdentifierAgency to set
	 */
	public void setProductIdentifierAgency(String productIdentifierAgency) {
		this.productIdentifierAgency = productIdentifierAgency;
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
	
}
