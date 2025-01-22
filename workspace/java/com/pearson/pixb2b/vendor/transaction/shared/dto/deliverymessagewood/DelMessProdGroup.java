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
 * Title		: 	DelMessProdGroup.java
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
 * DelMessProdGroup is a data transfer object to store the DeliveryMessage ProductGroupShipment 
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMessProdGroup implements java.io.Serializable {
	private static final long serialVersionUID = 628680753774183708L;
	
	private ProductGroupID productGroupId	= null;
	private ArrayList delShipLineItemList 	= null;
	/**
	 * Default constructor.
	 */
	public DelMessProdGroup() {
		super();
	}
	/**
	 * @return the productGroupId
	 */
	public ProductGroupID getProductGroupId() {
		return productGroupId;
	}
	/**
	 * @param productGroupId the productGroupId to set
	 */
	public void setProductGroupId(ProductGroupID productGroupId) {
		this.productGroupId = productGroupId;
	}
	/**
	 * @return the delShipLineItemList
	 */
	public ArrayList getDelShipLineItemList() {
		return delShipLineItemList;
	}
	/**
	 * @param delShipLineItemList the delShipLineItemList to set
	 */
	public void setDelShipLineItemList(ArrayList delShipLineItemList) {
		this.delShipLineItemList = delShipLineItemList;
	}
}