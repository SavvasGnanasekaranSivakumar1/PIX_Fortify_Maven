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
 * Title		: 	SCFSDecCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSDecCov is a data transfer object to store the Specification Component Supplied Component 
 * FinishSpecs Decoration Coverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSDecCov implements java.io.Serializable {
	private static final long serialVersionUID = -2536795834748019121L;
	
	private SCFSDCValue scFSDCValue			= null;
	private SCFSDCRangeMin scFSDCRangeMin	= null;
	private SCFSDCRangeMax scFSDCRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public SCFSDecCov() {
		super();
	}
	/**
	 * @return the scFSDCValue
	 */
	public SCFSDCValue getScFSDCValue() {
		return scFSDCValue;
	}
	/**
	 * @param scFSDCValue the scFSDCValue to set
	 */
	public void setScFSDCValue(SCFSDCValue scFSDCValue) {
		this.scFSDCValue = scFSDCValue;
	}
	/**
	 * @return the scFSDCRangeMin
	 */
	public SCFSDCRangeMin getScFSDCRangeMin() {
		return scFSDCRangeMin;
	}
	/**
	 * @param scFSDCRangeMin the scFSDCRangeMin to set
	 */
	public void setScFSDCRangeMin(SCFSDCRangeMin scFSDCRangeMin) {
		this.scFSDCRangeMin = scFSDCRangeMin;
	}
	/**
	 * @return the scFSDCRangeMax
	 */
	public SCFSDCRangeMax getScFSDCRangeMax() {
		return scFSDCRangeMax;
	}
	/**
	 * @param scFSDCRangeMax the scFSDCRangeMax to set
	 */
	public void setScFSDCRangeMax(SCFSDCRangeMax scFSDCRangeMax) {
		this.scFSDCRangeMax = scFSDCRangeMax;
	}
}
