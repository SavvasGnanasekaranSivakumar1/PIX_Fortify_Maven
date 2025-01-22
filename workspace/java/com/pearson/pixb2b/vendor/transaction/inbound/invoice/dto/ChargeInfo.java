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
 * Title		: 	InvoiceDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 Feb,2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;

import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PriceDetails;
/**
 * InvoiceDTO is a data transfer object to store the 
 * Invoice details and transfer the same between classes.
 * @author Abhilasha Nigam
 */
public class ChargeInfo implements java.io.Serializable {
	private static final long serialVersionUID = 861326279964601125L;

	private QuantityInfo qtyInfo 	    = null;
	private ChargeAmount chrgAmnt 			= null;       //ChargeAmount
	private CostComponent costComp		= null;
	private PriceDetails priceDetail	= null;
	
	
	/**
	 * Default constructor.
	 */
	public ChargeInfo() {
		super();		
	}


	


	/**
	 * @return Returns the costComp.
	 */
	public CostComponent getCostComp() {
		return costComp;
	}


	/**
	 * @param costComp The costComp to set.
	 */
	public void setCostComp(CostComponent costComp) {
		this.costComp = costComp;
	}


	/**
	 * @return Returns the priceDetail.
	 */
	public PriceDetails getPriceDetail() {
		return priceDetail;
	}


	/**
	 * @param priceDetail The priceDetail to set.
	 */
	public void setPriceDetail(PriceDetails priceDetail) {
		this.priceDetail = priceDetail;
	}


	/**
	 * @return Returns the qtyInfo.
	 */
	public QuantityInfo getQtyInfo() {
		return qtyInfo;
	}


	/**
	 * @param qtyInfo The qtyInfo to set.
	 */
	public void setQtyInfo(QuantityInfo qtyInfo) {
		this.qtyInfo = qtyInfo;
	}





	/**
	 * @return Returns the chrgAmnt.
	 */
	public ChargeAmount getChrgAmnt() {
		return chrgAmnt;
	}





	/**
	 * @param chrgAmnt The chrgAmnt to set.
	 */
	public void setChrgAmnt(ChargeAmount chrgAmnt) {
		this.chrgAmnt = chrgAmnt;
	}
		
	
	
}
