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
 * Title		: 	Product.java
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
 * Product is a data transfer object to store the 
 * Product detail and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = -3817527972790851900L;
	
	private ArrayList productIdentifierList = null;	
	/**
	 * Default constructor.
	 */
	public Product() {
		super();
	}
	/**
	 * @return the productIdentifierList
	 */
	public ArrayList getProductIdentifierList() {
		return productIdentifierList;
	}
	/**
	 * @param productIdentifierList the productIdentifierList to set
	 */
	public void setProductIdentifierList(ArrayList productIdentifierList) {
		this.productIdentifierList = productIdentifierList;
	}
}
