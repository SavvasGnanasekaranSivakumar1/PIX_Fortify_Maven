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
 * Title		: 	SCClassCode.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCClassCode is a data transfer object to store the Specification 
 * Component Classification Code details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCClassCode implements java.io.Serializable {
	private static final long serialVersionUID = 8737818284208402361L;
	
	private String scCCVal 	= null;
	private String agency	= null;
	/**
	 * Default constructor.
	 */
	public SCClassCode() {
		super();
	}
	/**
	 * @return the scCCVal
	 */
	public String getScCCVal() {
		return scCCVal;
	}
	/**
	 * @param scCCVal the scCCVal to set
	 */
	public void setScCCVal(String scCCVal) {
		this.scCCVal = scCCVal;
	}
	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}
	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}
}
