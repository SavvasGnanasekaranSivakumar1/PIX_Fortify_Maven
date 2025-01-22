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
 * Title		: 	POHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam	  07 Oct, 2009	   Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.DateOther;
/**
 * POHeader is a helper data transfer object to store the 
 * Purchase Order details.
 * 
 * @author Abhilasha Nigam
 */
public class POHeader implements java.io.Serializable {
	private static final long serialVersionUID = -869888097047316692L;
	
	private String poNumber 			= null;
	private String poReleaseNumber 		= null;
	private String poHeaderStatusType	= null;
	private POInformation poInformation = null;
	private BuyerParty buyerParty   	= null;
	private BillToParty billToParty  	= null;
	private SupplierParty supplierParty = null;
	private ShipToCharacteristics shipToCharacteristics = null;
	private DateOther dateOther 		= null;
	private TermsAndDisclaimers termsAndDisclaimers 	= null;
	private ArrayList additionalText 	= null;
	private String poVersion 			= null;
	private String poId 				= null;
	/**
	 * Default constructor.
	 */
	public POHeader() {
		super();
	}
	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	/**
	 * @return the poReleaseNumber
	 */
	public String getPoReleaseNumber() {
		return poReleaseNumber;
	}
	/**
	 * @param poReleaseNumber the poReleaseNumber to set
	 */
	public void setPoReleaseNumber(String poReleaseNumber) {
		this.poReleaseNumber = poReleaseNumber;
	}
	/**
	 * @return the poHeaderStatusType
	 */
	public String getPoHeaderStatusType() {
		return poHeaderStatusType;
	}
	/**
	 * @param poHeaderStatusType the poHeaderStatusType to set
	 */
	public void setPoHeaderStatusType(String poHeaderStatusType) {
		this.poHeaderStatusType = poHeaderStatusType;
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
	 * @return the billToParty
	 */
	public BillToParty getBillToParty() {
		return billToParty;
	}
	/**
	 * @param billToParty the billToParty to set
	 */
	public void setBillToParty(BillToParty billToParty) {
		this.billToParty = billToParty;
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
	/**
	 * @return the shipToCharacteristics
	 */
	public ShipToCharacteristics getShipToCharacteristics() {
		return shipToCharacteristics;
	}
	/**
	 * @param shipToCharacteristics the shipToCharacteristics to set
	 */
	public void setShipToCharacteristics(ShipToCharacteristics shipToCharacteristics) {
		this.shipToCharacteristics = shipToCharacteristics;
	}
	/**
	 * @return the dateOther
	 */
	public DateOther getDateOther() {
		return dateOther;
	}
	/**
	 * @param dateOther the dateOther to set
	 */
	public void setDateOther(DateOther dateOther) {
		this.dateOther = dateOther;
	}
	/**
	 * @return the termsAndDisclaimers
	 */
	public TermsAndDisclaimers getTermsAndDisclaimers() {
		return termsAndDisclaimers;
	}
	/**
	 * @param termsAndDisclaimers the termsAndDisclaimers to set
	 */
	public void setTermsAndDisclaimers(TermsAndDisclaimers termsAndDisclaimers) {
		this.termsAndDisclaimers = termsAndDisclaimers;
	}
	
	/**
	 * @return the poVersion
	 */
	public String getPoVersion() {
		return poVersion;
	}
	/**
	 * @param poVersion the poVersion to set
	 */
	public void setPoVersion(String poVersion) {
		this.poVersion = poVersion;
	}
	/**
	 * @return the poId
	 */
	public String getPoId() {
		return poId;
	}
	/**
	 * @param poId the poId to set
	 */
	public void setPoId(String poId) {
		this.poId = poId;
	}
	/**
	 * @return Returns the additionalText.
	 */
	public ArrayList getAdditionalText() {
		return additionalText;
	}
	/**
	 * @param additionalText The additionalText to set.
	 */
	public void setAdditionalText(ArrayList additionalText) {
		this.additionalText = additionalText;
	}
}