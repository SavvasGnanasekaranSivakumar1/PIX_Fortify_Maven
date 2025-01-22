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
 * Title		: 	SIPOLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * SIPOLineItem is a helper data transfer object to store the 
 * Shipping Instructions PurchaseOrder Line Item details.
 * 
 * @author Ashish Agrawal
 */
public class SIPOLineItem implements java.io.Serializable {
	private static final long serialVersionUID = -6594226969654743867L;
	
	private String poLineItemNumber = null;
	private POInformation poInformation = null;
	private ProductIdentification productIdentification = null;
	
	/**
	 * Default constructor.
	 */
	public SIPOLineItem() {
		super();
	}
	/**
	 * @return the poLineItemNumber
	 */
	public String getPoLineItemNumber() {
		return poLineItemNumber;
	}
	/**
	 * @param poLineItemNumber the poLineItemNumber to set
	 */
	public void setPoLineItemNumber(String poLineItemNumber) {
		this.poLineItemNumber = poLineItemNumber;
	}
	/**
	 * @return the poInformation
	 */
	public POInformation getPoInformation() {
		return poInformation;
	}
	/**
	 * @param poInformation the poInformation to set
	 */
	public void setPoInformation(POInformation poInformation) {
		this.poInformation = poInformation;
	}
	/**
	 * @return the productIdentification
	 */
	public ProductIdentification getProductIdentification() {
		return productIdentification;
	}
	/**
	 * @param productIdentification the productIdentification to set
	 */
	public void setProductIdentification(ProductIdentification productIdentification) {
		this.productIdentification = productIdentification;
	}
}
