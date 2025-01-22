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
 * Title		: 	OrderStatusDetail.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

import java.util.ArrayList;

/**
 * OrderStatusDetail is a data transfer object to store the 
 * Order Status Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OrderStatusDetail implements java.io.Serializable {
	private static final long serialVersionUID = -5273363128904487399L;
	
	private PurchaseOrderInformation poInformation	= null;
	private String poLineItemNumber 				= null;
	private ArrayList supOrderNumberList			= null;
	private Product product							= null;
	private SupplierParty supplierParty				= null;
	private BuyerParty buyerParty					= null;
	private OrderStatusInformation osInformation	= null;
	private Quantity quantity						= null;
	private DeliveryDateWindow delDateWindow		= null;
	private ArrayList otherDateList					= null;
	private ArrayList osLineNotes      				= null;
	
	
	/**
	 * Default constructor.
	 */
	public OrderStatusDetail() {
		super();
		supplierParty = new SupplierParty();
	}
	/**
	 * @return the poInformation
	 */
	public PurchaseOrderInformation getPoInformation() {
		return poInformation;
	}
	/**
	 * @param poInformation the poInformation to set
	 */
	public void setPoInformation(PurchaseOrderInformation poInformation) {
		this.poInformation = poInformation;
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
	 * @return the supOrderNumberList
	 */
	public ArrayList getSupOrderNumberList() {
		return supOrderNumberList;
	}
	/**
	 * @param supOrderNumberList the supOrderNumberList to set
	 */
	public void setSupOrderNumberList(ArrayList supOrderNumberList) {
		this.supOrderNumberList = supOrderNumberList;
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
	 * @return the osInformation
	 */
	public OrderStatusInformation getOsInformation() {
		return osInformation;
	}
	/**
	 * @param osInformation the osInformation to set
	 */
	public void setOsInformation(OrderStatusInformation osInformation) {
		this.osInformation = osInformation;
	}
	/**
	 * @return the quantity
	 */
	public Quantity getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the delDateWindow
	 */
	public DeliveryDateWindow getDelDateWindow() {
		return delDateWindow;
	}
	/**
	 * @param delDateWindow the delDateWindow to set
	 */
	public void setDelDateWindow(DeliveryDateWindow delDateWindow) {
		this.delDateWindow = delDateWindow;
	}
	/**
	 * @return the otherDateList
	 */
	public ArrayList getOtherDateList() {
		return otherDateList;
	}
	/**
	 * @param otherDateList the otherDateList to set
	 */
	public void setOtherDateList(ArrayList otherDateList) {
		this.otherDateList = otherDateList;
	}
	/**
	 * @return Returns the osLineNotes.
	 */
	public ArrayList getOsLineNotes() {
		return osLineNotes;
	}
	/**
	 * @param osLineNotes The osLineNotes to set.
	 */
	public void setOsLineNotes(ArrayList osLineNotes) {
		this.osLineNotes = osLineNotes;
	}
}
