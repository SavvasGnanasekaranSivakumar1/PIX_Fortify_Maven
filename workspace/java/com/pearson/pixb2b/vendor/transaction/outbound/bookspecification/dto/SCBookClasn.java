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
 * Title		: 	SCBookClasn.java
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
 * SCBookClasn is a data transfer object to store the Specification 
 * Component Book Classification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCBookClasn implements java.io.Serializable {
	private static final long serialVersionUID = 4820300033281988322L;
	
	private String bookClassType 	= null;
	private ArrayList scBCDespList	= null;
	private ArrayList scBSubClassnList= null;
	/**
	 * Default constructor.
	 */
	public SCBookClasn() {
		super();
	}
	/**
	 * @return the bookClassType
	 */
	public String getBookClassType() {
		return bookClassType;
	}
	/**
	 * @param bookClassType the bookClassType to set
	 */
	public void setBookClassType(String bookClassType) {
		this.bookClassType = bookClassType;
	}
	/**
	 * @return the scBCDespList
	 */
	public ArrayList getScBCDespList() {
		return scBCDespList;
	}
	/**
	 * @param scBCDespList the scBCDespList to set
	 */
	public void setScBCDespList(ArrayList scBCDespList) {
		this.scBCDespList = scBCDespList;
	}
	/**
	 * @return the scBSubClassnList
	 */
	public ArrayList getScBSubClassnList() {
		return scBSubClassnList;
	}
	/**
	 * @param scBSubClassnList the scBSubClassnList to set
	 */
	public void setScBSubClassnList(ArrayList scBSubClassnList) {
		this.scBSubClassnList = scBSubClassnList;
	}
}
