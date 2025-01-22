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
 * Title		: 	SCBCDesp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCBCDesp is a data transfer object to store the Specification Component 
 * Book Classification Description details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCBCDesp implements java.io.Serializable {
	private static final long serialVersionUID = 8452490784202991318L;
	
	private String scBookClassDesp	= null;
	/**
	 * Default constructor.
	 */
	public SCBCDesp() {
		super();
	}
	/**
	 * @return the scBookClassDesp
	 */
	public String getScBookClassDesp() {
		return scBookClassDesp;
	}
	/**
	 * @param scBookClassDesp the scBookClassDesp to set
	 */
	public void setScBookClassDesp(String scBookClassDesp) {
		this.scBookClassDesp = scBookClassDesp;
	}
}
