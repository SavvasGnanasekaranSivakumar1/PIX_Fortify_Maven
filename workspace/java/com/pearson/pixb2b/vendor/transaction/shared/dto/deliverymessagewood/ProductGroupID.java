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
 * Title		: 	ProductGroupID.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

import java.util.ArrayList;

/**
 * ProductGroupID is a data transfer object to store the DeliveryMessage Shipment 
 * ProductGroupID details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ProductGroupID implements java.io.Serializable {
	private static final long serialVersionUID = 5490289489018679832L;
	
	private String prodGroupIdType	= null;
	private String prodGroupIdVal 	= null;
	/**
	 * Default constructor.
	 */
	public ProductGroupID() {
		super();
	}
	/**
	 * @return the prodGroupIdType
	 */
	public String getProdGroupIdType() {
		return prodGroupIdType;
	}
	/**
	 * @param prodGroupIdType the prodGroupIdType to set
	 */
	public void setProdGroupIdType(String prodGroupIdType) {
		this.prodGroupIdType = prodGroupIdType;
	}
	/**
	 * @return the prodGroupIdVal
	 */
	public String getProdGroupIdVal() {
		return prodGroupIdVal;
	}
	/**
	 * @param prodGroupIdVal the prodGroupIdVal to set
	 */
	public void setProdGroupIdVal(String prodGroupIdVal) {
		this.prodGroupIdVal = prodGroupIdVal;
	}
}