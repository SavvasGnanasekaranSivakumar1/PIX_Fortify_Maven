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
 * Title		: 	IPurchaseOrderDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Jul, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dao;

import java.util.ArrayList;
/**
 * IPurchaseOrderDAO provide the interface to access 
 * the PurchaseOrder transaction details from database.
 * 
 * @author Yogesh Tyagi
 */
public interface IPurchaseOrderDAO{
	/**
	 * This method returns PurchaseOrder transactions list.
	 * @param vendorSAN
	 * @param transactionType
	 * @param transactionName
	 * @return ArrayList
	 */
	public ArrayList getPurchaseOrderDetails(String vendorSAN, String transactionType, String transactionName);
}