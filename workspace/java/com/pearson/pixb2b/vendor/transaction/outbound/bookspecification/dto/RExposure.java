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
 * Title		: 	RExposure.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	25 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * RExposure is a data transfer object to store the Ribbon Exposure   
 * Details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class RExposure implements java.io.Serializable {
	private static final long serialVersionUID = 6937489045457613148L;
	
	private RExpValue rExpValue = null;
	/**
	 * Default constructor.
	 */
	public RExposure() {
		super();
	}
	/**
	 * @return the rExpValue
	 */
	public RExpValue getRExpValue() {
		return rExpValue;
	}
	/**
	 * @param expValue the rExpValue to set
	 */
	public void setRExpValue(RExpValue expValue) {
		rExpValue = expValue;
	}
}
