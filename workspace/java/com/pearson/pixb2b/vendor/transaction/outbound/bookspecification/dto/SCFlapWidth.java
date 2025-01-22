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
 * Title		: 	SCFlapWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFlapWidth is a data transfer object to store the Specification Component 
 * Finished Size Flap Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFlapWidth implements java.io.Serializable {
	private static final long serialVersionUID = 2681492580341823882L;
	
	private String flapLoc					= null;
	private SCFSFWValue scFSFWValue			= null;
	private SCFSFWRangeMin scFSFWRangeMin	= null;
	private SCFSFWRangeMax scFSFWRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public SCFlapWidth() {
		super();
	}
	/**
	 * @return the flapLoc
	 */
	public String getFlapLoc() {
		return flapLoc;
	}
	/**
	 * @param flapLoc the flapLoc to set
	 */
	public void setFlapLoc(String flapLoc) {
		this.flapLoc = flapLoc;
	}
	/**
	 * @return the scFSFWValue
	 */
	public SCFSFWValue getScFSFWValue() {
		return scFSFWValue;
	}
	/**
	 * @param scFSFWValue the scFSFWValue to set
	 */
	public void setScFSFWValue(SCFSFWValue scFSFWValue) {
		this.scFSFWValue = scFSFWValue;
	}
	/**
	 * @return the scFSFWRangeMin
	 */
	public SCFSFWRangeMin getScFSFWRangeMin() {
		return scFSFWRangeMin;
	}
	/**
	 * @param scFSFWRangeMin the scFSFWRangeMin to set
	 */
	public void setScFSFWRangeMin(SCFSFWRangeMin scFSFWRangeMin) {
		this.scFSFWRangeMin = scFSFWRangeMin;
	}
	/**
	 * @return the scFSFWRangeMax
	 */
	public SCFSFWRangeMax getScFSFWRangeMax() {
		return scFSFWRangeMax;
	}
	/**
	 * @param scFSFWRangeMax the scFSFWRangeMax to set
	 */
	public void setScFSFWRangeMax(SCFSFWRangeMax scFSFWRangeMax) {
		this.scFSFWRangeMax = scFSFWRangeMax;
	}
}
