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
 * Title		: 	OCLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;
import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.Product;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQuantity;
/**
 * OCLineItem is a data transfer object to store the 
 * OCLineItem details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OCLineItem implements java.io.Serializable {
	private static final long serialVersionUID = -511551612066291322L;
	
	private String ocLineItemStatusType = null;
	private String poLineItemNo	 		= null;
	private Product product				= null;
	private LineQuantity ocLineQty 		= null;
	private POReference poRef 			= null;
	private OCLineDate ocLineDate 		= null;
	private ArrayList lineNotes       = null;

	/**
	 * Default constructor.
	 */
	public OCLineItem() {
		super();
	}

	/**
	 * @return Returns the ocLineItemStatusType.
	 */
	public String getOcLineItemStatusType() {
		return ocLineItemStatusType;
	}

	/**
	 * @param ocLineItemStatusType The ocLineItemStatusType to set.
	 */
	public void setOcLineItemStatusType(String ocLineItemStatusType) {
		this.ocLineItemStatusType = ocLineItemStatusType;
	}

	/**
	 * @return Returns the ocLineQty.
	 */
	public LineQuantity getOcLineQty() {
		return ocLineQty;
	}

	/**
	 * @param ocLineQty The ocLineQty to set.
	 */
	public void setOcLineQty(LineQuantity ocLineQty) {
		this.ocLineQty = ocLineQty;
	}

	/**
	 * @return Returns the poLineItemNo.
	 */
	public String getPoLineItemNo() {
		return poLineItemNo;
	}

	/**
	 * @param poLineItemNo The poLineItemNo to set.
	 */
	public void setPoLineItemNo(String poLineItemNo) {
		this.poLineItemNo = poLineItemNo;
	}

	/**
	 * @return Returns the product.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product The product to set.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return Returns the poRef.
	 */
	public POReference getPoRef() {
		return poRef;
	}

	/**
	 * @param poRef The poRef to set.
	 */
	public void setPoRef(POReference poRef) {
		this.poRef = poRef;
	}

	/**
	 * @return Returns the ocLineDate.
	 */
	public OCLineDate getOcLineDate() {
		return ocLineDate;
	}

	/**
	 * @param ocLineDate The ocLineDate to set.
	 */
	public void setOcLineDate(OCLineDate ocLineDate) {
		this.ocLineDate = ocLineDate;
	}

	/**
	 * @return Returns the lineNotes.
	 */
	public ArrayList getLineNotes() {
		return lineNotes;
	}

	/**
	 * @param lineNotes The lineNotes to set.
	 */
	public void setLineNotes(ArrayList lineNotes) {
		this.lineNotes = lineNotes;
	}

	
	
}
