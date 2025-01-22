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
 * Title		: 	IBookSpecificationDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Jul, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dao;

import java.util.ArrayList;
/**
 * IBookSpecificationDAO provide the interface to access 
 * the BookSpecification transaction details from database.
 * 
 * @author Yogesh Tyagi
 */
public interface IBookSpecificationDAO{
	/**
	 * This method returns BookSpecification transactions list.
	 * @param vendorSAN
	 * @param transactionType
	 * @param transactionName
	 * @return ArrayList
	 */
	public ArrayList getBookSpecificationDetails(String vendorSAN, String transactionType, String transactionName);
}