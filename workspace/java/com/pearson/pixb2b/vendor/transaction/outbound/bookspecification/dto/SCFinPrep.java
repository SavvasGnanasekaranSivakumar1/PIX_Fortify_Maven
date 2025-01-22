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
 * Title		: 	SCFinPrep.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SCFinPrep is a data transfer object to store the Specification Component 
 * Supplied Component FinishPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFinPrep implements java.io.Serializable {
	private static final long serialVersionUID = -2907641249605415393L;
	
	private String finPrepOutType 		= null;
	private ArrayList scFPrepInpList 	= null;
	/**
	 * Default constructor.
	 */
	public SCFinPrep() {
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
	 * @return the scFPrepInpList
	 */
	public ArrayList getScFPrepInpList() {
		return scFPrepInpList;
	}
	/**
	 * @param scFPrepInpList the scFPrepInpList to set
	 */
	public void setScFPrepInpList(ArrayList scFPrepInpList) {
		this.scFPrepInpList = scFPrepInpList;
	}
}
