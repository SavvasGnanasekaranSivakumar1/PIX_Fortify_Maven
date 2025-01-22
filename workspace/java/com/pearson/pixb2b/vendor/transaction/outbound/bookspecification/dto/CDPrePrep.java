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
 * Title		: 	CDPrePrep.java
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
 * CDPrePrep is a data transfer object to store the Specification NonPress Component 
 * CD PressPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDPrePrep implements java.io.Serializable {
	private static final long serialVersionUID = 3157972933451851389L;
	
	private String cdPrepOutType 	  = null;
	private ArrayList cdPrPrepInpList = null;
	/**
	 * Default constructor.
	 */
	public CDPrePrep() {
		super();
	}
	/**
	 * @return the cdPrepOutType
	 */
	public String getCdPrepOutType() {
		return cdPrepOutType;
	}
	/**
	 * @param cdPrepOutType the cdPrepOutType to set
	 */
	public void setCdPrepOutType(String cdPrepOutType) {
		this.cdPrepOutType = cdPrepOutType;
	}
	/**
	 * @return the cdPrPrepInpList
	 */
	public ArrayList getCdPrPrepInpList() {
		return cdPrPrepInpList;
	}
	/**
	 * @param cdPrPrepInpList the cdPrPrepInpList to set
	 */
	public void setCdPrPrepInpList(ArrayList cdPrPrepInpList) {
		this.cdPrPrepInpList = cdPrPrepInpList;
	}
}
