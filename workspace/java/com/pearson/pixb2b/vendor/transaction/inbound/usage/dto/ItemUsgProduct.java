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
 * Title		: 	ICProduct.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;


/**
 * UsageProduct is a data transfer object to store the 
 * usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ItemUsgProduct implements java.io.Serializable {
	private static final long serialVersionUID = 7587979025089995715L;

	private ArrayList iProdIdentifier = null;
	private ItemUsagePaper iUsgPaper = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public ItemUsgProduct() {
		super();
	}



	/**
	 * @return Returns the iProdIdentifier.
	 */
	public ArrayList getIProdIdentifier() {
		return iProdIdentifier;
	}



	/**
	 * @param prodIdentifier The iProdIdentifier to set.
	 */
	public void setIProdIdentifier(ArrayList prodIdentifier) {
		iProdIdentifier = prodIdentifier;
	}



	/**
	 * @return Returns the iUsgPaper.
	 */
	public ItemUsagePaper getIUsgPaper() {
		return iUsgPaper;
	}



	/**
	 * @param usgPaper The iUsgPaper to set.
	 */
	public void setIUsgPaper(ItemUsagePaper usgPaper) {
		iUsgPaper = usgPaper;
	}



	


		
	
}