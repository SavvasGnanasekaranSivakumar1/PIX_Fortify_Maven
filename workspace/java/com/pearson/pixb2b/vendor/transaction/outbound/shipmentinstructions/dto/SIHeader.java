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
 * Title		: 	SIHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * SIHeader is a helper data transfer object to store the 
 * Shipping Instruction Header details.
 * 
 * @author Ashish Agrawal
 */
public class SIHeader implements java.io.Serializable {
	private static final long serialVersionUID = 5862947942338588263L;
	
	private SIInformation siInformation = null;
	private BuyerParty buyerParty = null;
	private SupplierParty supplierParty = null;
	/**
	 * Default constructor.
	 */
	public SIHeader() {
		super();
	}
	/**
	 * @return the siInformation
	 */
	public SIInformation getSiInformation() {
		return siInformation;
	}
	/**
	 * @param siInformation the siInformation to set
	 */
	public void setSiInformation(SIInformation siInformation) {
		this.siInformation = siInformation;
	}
	/**
	 * @return the buyerParty
	 */
	public BuyerParty getBuyerParty() {
		return buyerParty;
	}
	/**
	 * @param buyerParty the buyerParty to set
	 */
	public void setBuyerParty(BuyerParty buyerParty) {
		this.buyerParty = buyerParty;
	}
	/**
	 * @return the supplierParty
	 */
	public SupplierParty getSupplierParty() {
		return supplierParty;
	}
	/**
	 * @param supplierParty the supplierParty to set
	 */
	public void setSupplierParty(SupplierParty supplierParty) {
		this.supplierParty = supplierParty;
	}
}
