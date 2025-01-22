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
 * Title		: 	GLAccount.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * GLAccount is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GLAccount implements java.io.Serializable {
	private static final long serialVersionUID = 2630516144879299022L;

	private String glAgency = null;
	private String glValue = null;

	/**
	 * Default constructor.
	 */
	public GLAccount() {
		super();
	}

	/**
	 * @return Returns the glAgency.
	 */
	public String getGlAgency() {
		return glAgency;
	}

	/**
	 * @param glAgency The glAgency to set.
	 */
	public void setGlAgency(String glAgency) {
		this.glAgency = glAgency;
	}

	/**
	 * @return Returns the glValue.
	 */
	public String getGlValue() {
		return glValue;
	}

	/**
	 * @param glValue The glValue to set.
	 */
	public void setGlValue(String glValue) {
		this.glValue = glValue;
	}


	
}