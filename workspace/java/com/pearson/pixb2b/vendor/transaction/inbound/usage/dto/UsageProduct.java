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
public class UsageProduct implements java.io.Serializable {
	private static final long serialVersionUID = -3995702544490269113L;

	private ArrayList prodIdentifier = null;
	private UsagePaper usagePaper = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public UsageProduct() {
		super();
	}


	


	/**
	 * @return Returns the prodIdentifier.
	 */
	public ArrayList getProdIdentifier() {
		return prodIdentifier;
	}


	/**
	 * @param prodIdentifier The prodIdentifier to set.
	 */
	public void setProdIdentifier(ArrayList prodIdentifier) {
		this.prodIdentifier = prodIdentifier;
	}





	/**
	 * @return Returns the usagePaper.
	 */
	public UsagePaper getUsagePaper() {
		return usagePaper;
	}





	/**
	 * @param usagePaper The usagePaper to set.
	 */
	public void setUsagePaper(UsagePaper usagePaper) {
		this.usagePaper = usagePaper;
	}


		
	
}