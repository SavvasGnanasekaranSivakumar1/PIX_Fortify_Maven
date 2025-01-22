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
 * Title		: 	TotNumOfUnits.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQtyValue;

/**
 * TotNumOfUnits is a data transfer object to store the DeliveryShipmentLineItem 
 * TotalNumberOfUnits details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class TotNumOfUnits implements java.io.Serializable {
	private static final long serialVersionUID = 1384806064788705925L;
	
	private LineQtyValue lineQtyValue = null;	
	/**
	 * Default constructor.
	 */
	public TotNumOfUnits() {
		super();
	}
	/**
	 * @return the lineQtyValue
	 */
	public LineQtyValue getLineQtyValue() {
		return lineQtyValue;
	}
	/**
	 * @param lineQtyValue the lineQtyValue to set
	 */
	public void setLineQtyValue(LineQtyValue lineQtyValue) {
		this.lineQtyValue = lineQtyValue;
	}
}