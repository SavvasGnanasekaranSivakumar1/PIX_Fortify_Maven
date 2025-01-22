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
 * Title		: 	ChainOfCustody.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ChainOfCustody is a data transfer object to store the Specification Component
 * SafetyAndEnvironmentalInformation ChainOfCustody details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ChainOfCustody implements java.io.Serializable {
	private static final long serialVersionUID = 6675338252255258491L;
	
	private String certnBody 	= null;
	private String cOfCusdyVal	= null;
	/**
	 * Default constructor.
	 */
	public ChainOfCustody() {
		super();
	}
	/**
	 * @return the certnBody
	 */
	public String getCertnBody() {
		return certnBody;
	}
	/**
	 * @param certnBody the certnBody to set
	 */
	public void setCertnBody(String certnBody) {
		this.certnBody = certnBody;
	}
	/**
	 * @return the cOfCusdyVal
	 */
	public String getCOfCusdyVal() {
		return cOfCusdyVal;
	}
	/**
	 * @param ofCusdyVal the cOfCusdyVal to set
	 */
	public void setCOfCusdyVal(String ofCusdyVal) {
		cOfCusdyVal = ofCusdyVal;
	}
}
