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
 * Title		: 	SCFSLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSLength is a data transfer object to store the Specification Component 
 * Finished Size Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSLength implements java.io.Serializable {
	private static final long serialVersionUID = -6181969813999051559L;
	
	private SCFSLValue scFSLValue		= null;
	private SCFSLRangeMin scFSLRangeMin	= null;
	private SCFSLRangeMax scFSLRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public SCFSLength() {
		super();
	}
	/**
	 * @return the scFSLValue
	 */
	public SCFSLValue getScFSLValue() {
		return scFSLValue;
	}
	/**
	 * @param scFSLValue the scFSLValue to set
	 */
	public void setScFSLValue(SCFSLValue scFSLValue) {
		this.scFSLValue = scFSLValue;
	}
	/**
	 * @return the scFSLRangeMin
	 */
	public SCFSLRangeMin getScFSLRangeMin() {
		return scFSLRangeMin;
	}
	/**
	 * @param scFSLRangeMin the scFSLRangeMin to set
	 */
	public void setScFSLRangeMin(SCFSLRangeMin scFSLRangeMin) {
		this.scFSLRangeMin = scFSLRangeMin;
	}
	/**
	 * @return the scFSLRangeMax
	 */
	public SCFSLRangeMax getScFSLRangeMax() {
		return scFSLRangeMax;
	}
	/**
	 * @param scFSLRangeMax the scFSLRangeMax to set
	 */
	public void setScFSLRangeMax(SCFSLRangeMax scFSLRangeMax) {
		this.scFSLRangeMax = scFSLRangeMax;
	}
}
