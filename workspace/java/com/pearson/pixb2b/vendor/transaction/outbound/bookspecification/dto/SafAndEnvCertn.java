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
 * Title		: 	SafAndEnvCertn.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SafAndEnvCertn is a data transfer object to store the Specification Component 
 * Safety And Environmental Certification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SafAndEnvCertn implements java.io.Serializable {
	private static final long serialVersionUID = 2188348886873067713L;
	
	private SAEValue saeValue		= null;
	private SAERangeMin saeRangeMin	= null;
	private SAERangeMax saeRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public SafAndEnvCertn() {
		super();
	}
	/**
	 * @return the saeValue
	 */
	public SAEValue getSaeValue() {
		return saeValue;
	}
	/**
	 * @param saeValue the saeValue to set
	 */
	public void setSaeValue(SAEValue saeValue) {
		this.saeValue = saeValue;
	}
	/**
	 * @return the saeRangeMin
	 */
	public SAERangeMin getSaeRangeMin() {
		return saeRangeMin;
	}
	/**
	 * @param saeRangeMin the saeRangeMin to set
	 */
	public void setSaeRangeMin(SAERangeMin saeRangeMin) {
		this.saeRangeMin = saeRangeMin;
	}
	/**
	 * @return the saeRangeMax
	 */
	public SAERangeMax getSaeRangeMax() {
		return saeRangeMax;
	}
	/**
	 * @param saeRangeMax the saeRangeMax to set
	 */
	public void setSaeRangeMax(SAERangeMax saeRangeMax) {
		this.saeRangeMax = saeRangeMax;
	}
}
