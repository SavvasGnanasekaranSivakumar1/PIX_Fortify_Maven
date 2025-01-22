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
 * Title		: 	POLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam	   07 Oct,2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.shared.dto.LineDateOther;

/**
 * POLineItem is a helper Arraylistobject to store the 
 * Purchase Order Line Item details.
 * 
 * @author Abhilasha Nigam
 */
public class POLineItem implements java.io.Serializable {
	private static final long serialVersionUID = 8583158175211544123L;
	
	private LineDateOther deliveryDate	= null;
	private String poLineItemStatusType	= null;
	private String poLineItemNumber 	= null;
	private String buyerNotes 			= null;
	private Product product 			= null;
	private ArrayList priceDetailsList  = null;
	private ArrayList poReferenceList 	= null;
	private LineQuantity lineQty 		= null;
	private String poLVersion	 		= null;
	private String  poLId  				= null;
	private ArrayList additionalLineText = null;
	
	
	/*
	 * Added for Apollo Enhancement -Aishwarya --Start
	 */
	private PreviousVendorPlant previousVendorPlant=null;
	private PreviousVendorNameAddress previousVendorNameAddress=null;
	
	public PreviousVendorPlant getPreviousVendorPlant() {
		return previousVendorPlant;
	}
	public void setPreviousVendorPlant(PreviousVendorPlant previousVendorPlant) {
		this.previousVendorPlant = previousVendorPlant;
	}
	public PreviousVendorNameAddress getPreviousVendorNameAddress() {
		return previousVendorNameAddress;
	}
	public void setPreviousVendorNameAddress(
			PreviousVendorNameAddress previousVendorNameAddress) {
		this.previousVendorNameAddress = previousVendorNameAddress;
	}
	/*private String previousVendorPlant=null;
	private String partyType = null;
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getPreviousVendorPlant() {
		return previousVendorPlant;
	}
	public void setPreviousVendorPlant(String previousVendorPlant) {
		this.previousVendorPlant = previousVendorPlant;
	}*/
	/**
	 * Default constructor.
	 */
	public POLineItem() {
		super();
	}
	/**
	 * @return the deliveryDate
	 */
	public LineDateOther getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(LineDateOther deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the poLineItemStatusType
	 */
	public String getPoLineItemStatusType() {
		return poLineItemStatusType;
	}
	/**
	 * @param poLineItemStatusType the poLineItemStatusType to set
	 */
	public void setPoLineItemStatusType(String poLineItemStatusType) {
		this.poLineItemStatusType = poLineItemStatusType;
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
	 * @return the buyerNotes
	 */
	public String getBuyerNotes() {
		return buyerNotes;
	}
	/**
	 * @param buyerNotes the buyerNotes to set
	 */
	public void setBuyerNotes(String buyerNotes) {
		this.buyerNotes = buyerNotes;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the priceDetailsList
	 */
	public ArrayList getPriceDetailsList() {
		return priceDetailsList;
	}
	/**
	 * @param priceDetailsList the priceDetailsList to set
	 */
	public void setPriceDetailsList(ArrayList priceDetailsList) {
		this.priceDetailsList = priceDetailsList;
	}
	/**
	 * @return the poReferenceList
	 */
	public ArrayList getPoReferenceList() {
		return poReferenceList;
	}
	/**
	 * @param poReferenceList the poReferenceList to set
	 */
	public void setPoReferenceList(ArrayList poReferenceList) {
		this.poReferenceList = poReferenceList;
	}
	/**
	 * @return the lineQty
	 */
	public LineQuantity getLineQty() {
		return lineQty;
	}
	/**
	 * @param lineQty the lineQty to set
	 */
	public void setLineQty(LineQuantity lineQty) {
		this.lineQty = lineQty;
	}
	/**
	 * @return the poLVersion
	 */
	public String getPoLVersion() {
		return poLVersion;
	}
	/**
	 * @param poLVersion the poLVersion to set
	 */
	public void setPoLVersion(String poLVersion) {
		this.poLVersion = poLVersion;
	}
	/**
	 * @return the poLId
	 */
	public String getPoLId() {
		return poLId;
	}
	/**
	 * @param poLId the poLId to set
	 */
	public void setPoLId(String poLId) {
		this.poLId = poLId;
	}	
	/**
	 * @return Returns the additionalLineText.
	 */
	public ArrayList getAdditionalLineText() {
		return additionalLineText;
	}
	/**
	 * @param additionalLineText The additionalLineText to set.
	 */
	public void setAdditionalLineText(ArrayList additionalLineText) {
		this.additionalLineText = additionalLineText;
	}	
}