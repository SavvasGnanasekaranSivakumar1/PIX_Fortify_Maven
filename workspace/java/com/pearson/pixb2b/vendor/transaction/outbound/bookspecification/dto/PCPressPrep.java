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
 * Title		: 	PCPressPrep.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCPressPrep s a data transfer object to store the Specification 
 * Press Component PressPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPressPrep implements java.io.Serializable {
	private static final long serialVersionUID = 4795234789167596855L;
	
	private String prPrepOutType 	= null;
	private ArrayList prPrepInpList = null;
	/**
	 * Default constructor.
	 */
	public PCPressPrep() {
		super();
	}
	/**
	 * @return the prPrepOutType
	 */
	public String getPrPrepOutType() {
		return prPrepOutType;
	}
	/**
	 * @param prPrepOutType the prPrepOutType to set
	 */
	public void setPrPrepOutType(String prPrepOutType) {
		this.prPrepOutType = prPrepOutType;
	}
	/**
	 * @return the prPrepInpList
	 */
	public ArrayList getPrPrepInpList() {
		return prPrepInpList;
	}
	/**
	 * @param prPrepInpList the prPrepInpList to set
	 */
	public void setPrPrepInpList(ArrayList prPrepInpList) {
		this.prPrepInpList = prPrepInpList;
	}
}
