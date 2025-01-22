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
 * Title		: 	ACPrePrep.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ACPrePrep is a data transfer object to store the Specification NonPress Component 
 * Audio Cassette Label Characteristics PressPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACPrePrep implements java.io.Serializable {
	private static final long serialVersionUID = -5791107000959014391L;
	
	private String acPrepOutType 	  = null;
	private ArrayList acPrPrepInpList = null;
	/**
	 * Default constructor.
	 */
	public ACPrePrep() {
		super();
	}
	/**
	 * @return the acPrepOutType
	 */
	public String getAcPrepOutType() {
		return acPrepOutType;
	}
	/**
	 * @param acPrepOutType the acPrepOutType to set
	 */
	public void setAcPrepOutType(String acPrepOutType) {
		this.acPrepOutType = acPrepOutType;
	}
	/**
	 * @return the acPrPrepInpList
	 */
	public ArrayList getAcPrPrepInpList() {
		return acPrPrepInpList;
	}
	/**
	 * @param acPrPrepInpList the acPrPrepInpList to set
	 */
	public void setAcPrPrepInpList(ArrayList acPrPrepInpList) {
		this.acPrPrepInpList = acPrPrepInpList;
	}
}
