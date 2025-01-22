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
 * Title		: 	SCClassDesp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCClassDesp is a data transfer object to store the Specification 
 * Component Classification Description details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCClassDesp implements java.io.Serializable {
	private static final long serialVersionUID = 5934980142308819425L;
	
	private String scClassDesp	= null;
	/**
	 * Default constructor.
	 */
	public SCClassDesp() {
		super();
	}
	/**
	 * @return the scClassDesp
	 */
	public String getScClassDesp() {
		return scClassDesp;
	}
	/**
	 * @param scClassDesp the scClassDesp to set
	 */
	public void setScClassDesp(String scClassDesp) {
		this.scClassDesp = scClassDesp;
	}
}
