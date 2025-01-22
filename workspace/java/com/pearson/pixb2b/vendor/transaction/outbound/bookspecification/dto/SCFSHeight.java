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
 * Title		: 	SCFSHeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSHeight is a data transfer object to store the Specification Component 
 * Finished Size Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSHeight implements java.io.Serializable {
	private static final long serialVersionUID = -1144144026568826911L;
	
	private SCFSHValue scFSHValue		= null;
	private SCFSHRangeMin scFSHRangeMin	= null;
	private SCFSHRangeMax scFSHRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public SCFSHeight() {
		super();
	}
	/**
	 * @return the scFSHValue
	 */
	public SCFSHValue getScFSHValue() {
		return scFSHValue;
	}
	/**
	 * @param scFSHValue the scFSHValue to set
	 */
	public void setScFSHValue(SCFSHValue scFSHValue) {
		this.scFSHValue = scFSHValue;
	}
	/**
	 * @return the scFSHRangeMin
	 */
	public SCFSHRangeMin getScFSHRangeMin() {
		return scFSHRangeMin;
	}
	/**
	 * @param scFSHRangeMin the scFSHRangeMin to set
	 */
	public void setScFSHRangeMin(SCFSHRangeMin scFSHRangeMin) {
		this.scFSHRangeMin = scFSHRangeMin;
	}
	/**
	 * @return the scFSHRangeMax
	 */
	public SCFSHRangeMax getScFSHRangeMax() {
		return scFSHRangeMax;
	}
	/**
	 * @param scFSHRangeMax the scFSHRangeMax to set
	 */
	public void setScFSHRangeMax(SCFSHRangeMax scFSHRangeMax) {
		this.scFSHRangeMax = scFSHRangeMax;
	}
}
