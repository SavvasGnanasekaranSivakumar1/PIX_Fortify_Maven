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
 * Title		: 	PCFinPrep.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCFinPrep is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications FinishPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFinPrep implements java.io.Serializable {
	private static final long serialVersionUID = -4499535849200242104L;
	
	private String finPrepOutType 		= null;
	private ArrayList pcFPrepInpList 	= null;
	/**
	 * Default constructor.
	 */
	public PCFinPrep() {
		super();
	}
	/**
	 * @return the finPrepOutType
	 */
	public String getFinPrepOutType() {
		return finPrepOutType;
	}
	/**
	 * @param finPrepOutType the finPrepOutType to set
	 */
	public void setFinPrepOutType(String finPrepOutType) {
		this.finPrepOutType = finPrepOutType;
	}
	/**
	 * @return the pcFPrepInpList
	 */
	public ArrayList getPcFPrepInpList() {
		return pcFPrepInpList;
	}
	/**
	 * @param pcFPrepInpList the pcFPrepInpList to set
	 */
	public void setPcFPrepInpList(ArrayList pcFPrepInpList) {
		this.pcFPrepInpList = pcFPrepInpList;
	}
}
