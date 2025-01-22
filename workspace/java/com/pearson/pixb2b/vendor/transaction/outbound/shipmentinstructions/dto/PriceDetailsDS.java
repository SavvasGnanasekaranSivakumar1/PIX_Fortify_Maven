/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	PriceDetailsDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		21 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.PricePerUnitDS;

/**
 * @author Ranu.Sharma
 *
 */
public class PriceDetailsDS implements Serializable{
	
	private static final long serialVersionUID = -5109463395103416817L;
	private String qtyBasis 		= null;
	/*private String glCode 			= null;
	private String additionalText 	= null;
	private String glDesc 			= null;*/	
	private PricePerUnitDS ppunit 	= null;
	//private GLAccount glAccount 	= null;
	/**
	 * Default constructor.
	 */
	public PriceDetailsDS() {
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
	 *//*
	public String getGlCode() {
		return glCode;
	}
	*//**
	 * @param glCode the glCode to set
	 *//*
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	*//**
	 * @return the glDesc
	 *//*
	public String getGlDesc() {
		return glDesc;
	}
	*//**
	 * @param glDesc the glDesc to set
	 *//*
	public void setGlDesc(String glDesc) {
		this.glDesc = glDesc;
	}
	*//**
	 * @return the additionalText
	 *//*
	public String getAdditionalText() {
		return additionalText;
	}
	*//**
	 * @param additionalText the additionalText to set
	 *//*
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}*/
	/**
	 * @return the ppunit
	 */
	public PricePerUnitDS getPpunit() {
		return ppunit;
	}
	/**
	 * @param ppunit the ppunit to set
	 */
	public void setPpunit(PricePerUnitDS ppunit) {
		this.ppunit = ppunit;
	}
	/**
	 * @return the glAccount
	 *//*
	public GLAccount getGlAccount() {
		return glAccount;
	}
	*//**
	 * @param glAccount the glAccount to set
	 *//*
	public void setGlAccount(GLAccount glAccount) {
		this.glAccount = glAccount;
	}*/
	
}
