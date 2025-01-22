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
 * Title		: 	SCFSWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSWidth is a data transfer object to store the Specification Component 
 * Finished Size Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSWidth implements java.io.Serializable {
	private static final long serialVersionUID = -66039274345064608L;
	
	private SCFSWValue scFSWValue		= null;
	private SCFSWRangeMin scFSWRangeMin	= null;
	private SCFSWRangeMax scFSWRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public SCFSWidth() {
		super();
	}
	/**
	 * @return the scFSWValue
	 */
	public SCFSWValue getScFSWValue() {
		return scFSWValue;
	}
	/**
	 * @param scFSWValue the scFSWValue to set
	 */
	public void setScFSWValue(SCFSWValue scFSWValue) {
		this.scFSWValue = scFSWValue;
	}
	/**
	 * @return the scFSWRangeMin
	 */
	public SCFSWRangeMin getScFSWRangeMin() {
		return scFSWRangeMin;
	}
	/**
	 * @param scFSWRangeMin the scFSWRangeMin to set
	 */
	public void setScFSWRangeMin(SCFSWRangeMin scFSWRangeMin) {
		this.scFSWRangeMin = scFSWRangeMin;
	}
	/**
	 * @return the scFSWRangeMax
	 */
	public SCFSWRangeMax getScFSWRangeMax() {
		return scFSWRangeMax;
	}
	/**
	 * @param scFSWRangeMax the scFSWRangeMax to set
	 */
	public void setScFSWRangeMax(SCFSWRangeMax scFSWRangeMax) {
		this.scFSWRangeMax = scFSWRangeMax;
	}
}
