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
 * Title		: 	PackItemCount.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * PackItemCount is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information ItemCount details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PackItemCount implements java.io.Serializable {
	private static final long serialVersionUID = 6437088999655893938L;
	
	private PacItemCntVal pacItemCntVal = null;
	/**
	 * Default constructor.
	 */
	public PackItemCount() {
		super();
	}
	/**
	 * @return the pacItemCntVal
	 */
	public PacItemCntVal getPacItemCntVal() {
		return pacItemCntVal;
	}
	/**
	 * @param pacItemCntVal the pacItemCntVal to set
	 */
	public void setPacItemCntVal(PacItemCntVal pacItemCntVal) {
		this.pacItemCntVal = pacItemCntVal;
	}
}