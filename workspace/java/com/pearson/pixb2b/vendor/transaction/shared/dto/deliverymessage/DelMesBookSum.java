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
 * Title		: 	DelMesBookSum.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.TotalQuantity;


/**
 * DelMesBookSum is a data transfer object to store the DeliveryMessage BookSummary
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesBookSum implements java.io.Serializable {
	private static final long serialVersionUID = 2025891746309736754L;
	
	private String totNumOfShipments	= null;
	private TotalQuantity totalQty 		= null;
	private ArrayList totInfoQtyList	= null;
	/**
	 * Default constructor.
	 */
	public DelMesBookSum() {
		super();
	}
	/**
	 * @return the totNumOfShipments
	 */
	public String getTotNumOfShipments() {
		return totNumOfShipments;
	}
	/**
	 * @param totNumOfShipments the totNumOfShipments to set
	 */
	public void setTotNumOfShipments(String totNumOfShipments) {
		this.totNumOfShipments = totNumOfShipments;
	}
	/**
	 * @return the totalQty
	 */
	public TotalQuantity getTotalQty() {
		return totalQty;
	}
	/**
	 * @param totalQty the totalQty to set
	 */
	public void setTotalQty(TotalQuantity totalQty) {
		this.totalQty = totalQty;
	}
	/**
	 * @return the totInfoQtyList
	 */
	public ArrayList getTotInfoQtyList() {
		return totInfoQtyList;
	}
	/**
	 * @param totInfoQtyList the totInfoQtyList to set
	 */
	public void setTotInfoQtyList(ArrayList totInfoQtyList) {
		this.totInfoQtyList = totInfoQtyList;
	}
}