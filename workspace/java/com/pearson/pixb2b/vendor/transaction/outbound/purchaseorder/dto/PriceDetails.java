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
 * Title		: 	PriceDetails.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;
/**
 * PriceDetails is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class PriceDetails implements java.io.Serializable {
	private static final long serialVersionUID = -2571720943561613042L;
	
	private String qtyBasis 		= null;
	private String glCode 			= null;
	private String glDesc 			= null;	
	private String additionalText 	= null;
	private PricePerUnit ppunit 	= null;
	private GLAccount glAccount 	= null;
	/**
	 * Default constructor.
	 */
	public PriceDetails() {
		super();
	}
	/**
	 * @return the qtyBasis
	 */
	public String getQtyBasis() {
		return qtyBasis;
	}
	/**
	 * @param qtyBasis the qtyBasis to set
	 */
	public void setQtyBasis(String qtyBasis) {
		this.qtyBasis = qtyBasis;
	}
	/**
	 * @return the glCode
	 */
	public String getGlCode() {
		return glCode;
	}
	/**
	 * @param glCode the glCode to set
	 */
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	/**
	 * @return the glDesc
	 */
	public String getGlDesc() {
		return glDesc;
	}
	/**
	 * @param glDesc the glDesc to set
	 */
	public void setGlDesc(String glDesc) {
		this.glDesc = glDesc;
	}
	/**
	 * @return the additionalText
	 */
	public String getAdditionalText() {
		return additionalText;
	}
	/**
	 * @param additionalText the additionalText to set
	 */
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
	/**
	 * @return the ppunit
	 */
	public PricePerUnit getPpunit() {
		return ppunit;
	}
	/**
	 * @param ppunit the ppunit to set
	 */
	public void setPpunit(PricePerUnit ppunit) {
		this.ppunit = ppunit;
	}
	/**
	 * @return the glAccount
	 */
	public GLAccount getGlAccount() {
		return glAccount;
	}
	/**
	 * @param glAccount the glAccount to set
	 */
	public void setGlAccount(GLAccount glAccount) {
		this.glAccount = glAccount;
	}
}