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
 * Title		: 	GRArrivalDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  13 Jan, 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * GRArrivalDate is a data transfer object to store the 
 * GR details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class GRArrivalDate implements java.io.Serializable {
	private static final long serialVersionUID = 7992597554699484265L;

	private GRADate grADate = null;
	
	/**
	 * Default constructor.
	 */
	public GRArrivalDate() {
		super();
	}

	/**
	 * @return Returns the grADate.
	 */
	public GRADate getGrADate() {
		return grADate;
	}

	/**
	 * @param grADate The grADate to set.
	 */
	public void setGrADate(GRADate grADate) {
		this.grADate = grADate;
	}



}