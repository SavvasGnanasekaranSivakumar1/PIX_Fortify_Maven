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
 * Title		: 	DelMesWoodSum.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.TotalQuantity;

/**
 * DelMesWoodSum is a data transfer object to store the DeliveryMessageWood Summary details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesWoodSum implements java.io.Serializable {
	private static final long serialVersionUID = -5657068488061022120L;
	
	private String totNumOfShipments	= null;
	private TotalQuantity totalQty 		= null;
	/**
	 * Default constructor.
	 */
	public DelMesWoodSum() {
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
}