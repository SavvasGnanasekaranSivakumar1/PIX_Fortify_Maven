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
 * Title		: 	CaseLining.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CaseLining is a data transfer object to store the specification Binding   
 * CaseLining Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseLining implements java.io.Serializable {
	private static final long serialVersionUID = -8491391432146431619L;
	
	private CLQuant clQuant = null;
	private String clDesp	= null;
	/**
	 * Default constructor.
	 */
	public CaseLining() {
		super();
	}
	/**
	 * @return the clQuant
	 */
	public CLQuant getClQuant() {
		return clQuant;
	}
	/**
	 * @param clQuant the clQuant to set
	 */
	public void setClQuant(CLQuant clQuant) {
		this.clQuant = clQuant;
	}
	/**
	 * @return the clDesp
	 */
	public String getClDesp() {
		return clDesp;
	}
	/**
	 * @param clDesp the clDesp to set
	 */
	public void setClDesp(String clDesp) {
		this.clDesp = clDesp;
	}
}
