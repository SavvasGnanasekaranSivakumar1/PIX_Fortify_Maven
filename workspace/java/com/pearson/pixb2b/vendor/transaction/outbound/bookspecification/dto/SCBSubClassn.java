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
 * Title		: 	SCBSubClassn.java
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
 * SCBSubClassn is a data transfer object to store the Specification Component 
 * Book Sub Classification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCBSubClassn implements java.io.Serializable {
	private static final long serialVersionUID = 9181882359459150733L;
	
	private String bookSubClassType 		= null;
	private ArrayList scBSubClassDespList	= null;
	/**
	 * Default constructor.
	 */
	public SCBSubClassn() {
		super();
	}
	/**
	 * @return the bookSubClassType
	 */
	public String getBookSubClassType() {
		return bookSubClassType;
	}
	/**
	 * @param bookSubClassType the bookSubClassType to set
	 */
	public void setBookSubClassType(String bookSubClassType) {
		this.bookSubClassType = bookSubClassType;
	}
	/**
	 * @return the scBSubClassDespList
	 */
	public ArrayList getScBSubClassDespList() {
		return scBSubClassDespList;
	}
	/**
	 * @param scBSubClassDespList the scBSubClassDespList to set
	 */
	public void setScBSubClassDespList(ArrayList scBSubClassDespList) {
		this.scBSubClassDespList = scBSubClassDespList;
	}
}
