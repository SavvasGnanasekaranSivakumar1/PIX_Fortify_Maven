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
 * Title		: 	SCClassifn.java
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
 * SCClassifn is a data transfer object to store the Specification 
 * Component Classification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCClassifn implements java.io.Serializable {
	private static final long serialVersionUID = 223507001984984420L;
	
	private SCClassCode scClassCode 	= null;
	private ArrayList scClassDespList	= null;
	/**
	 * Default constructor.
	 */
	public SCClassifn() {
		super();
	}
	/**
	 * @return the scClassCode
	 */
	public SCClassCode getScClassCode() {
		return scClassCode;
	}
	/**
	 * @param scClassCode the scClassCode to set
	 */
	public void setScClassCode(SCClassCode scClassCode) {
		this.scClassCode = scClassCode;
	}
	/**
	 * @return the scClassDespList
	 */
	public ArrayList getScClassDespList() {
		return scClassDespList;
	}
	/**
	 * @param scClassDespList the scClassDespList to set
	 */
	public void setScClassDespList(ArrayList scClassDespList) {
		this.scClassDespList = scClassDespList;
	}
}
