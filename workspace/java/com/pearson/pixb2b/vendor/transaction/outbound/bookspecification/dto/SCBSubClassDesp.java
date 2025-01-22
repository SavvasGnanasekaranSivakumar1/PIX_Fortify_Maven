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
 * Title		: 	SCBSubClassDesp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCBSubClassDesp is a data transfer object to store the Specification Component 
 * Book Sub Classification Description details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCBSubClassDesp implements java.io.Serializable {
	private static final long serialVersionUID = -3023551404292000096L;
	
	private String scBSubClassDesp	= null;
	/**
	 * Default constructor.
	 */
	public SCBSubClassDesp() {
		super();
	}
	/**
	 * @return the scBSubClassDesp
	 */
	public String getScBSubClassDesp() {
		return scBSubClassDesp;
	}
	/**
	 * @param scBSubClassDesp the scBSubClassDesp to set
	 */
	public void setScBSubClassDesp(String scBSubClassDesp) {
		this.scBSubClassDesp = scBSubClassDesp;
	}
}
