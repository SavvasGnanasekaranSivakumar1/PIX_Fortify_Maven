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
 * Title		: 	SCSubClassDesp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCSubClassDesp is a data transfer object to store the Specification Component 
 * SubClassification Description details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCSubClassDesp implements java.io.Serializable {
	private static final long serialVersionUID = -4267351397354951286L;
	
	private String scSubClassDesp	= null;
	/**
	 * Default constructor.
	 */
	public SCSubClassDesp() {
		super();
	}
	/**
	 * @return the scSubClassDesp
	 */
	public String getScSubClassDesp() {
		return scSubClassDesp;
	}
	/**
	 * @param scSubClassDesp the scSubClassDesp to set
	 */
	public void setScSubClassDesp(String scSubClassDesp) {
		this.scSubClassDesp = scSubClassDesp;
	}
}
