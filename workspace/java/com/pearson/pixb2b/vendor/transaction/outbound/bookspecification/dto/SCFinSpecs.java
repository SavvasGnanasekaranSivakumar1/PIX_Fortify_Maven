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
 * Title		: 	SCFinSpecs.java
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
 * SCFinSpecs is a data transfer object to store the Specification Component 
 * Supplied Component FinishSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFinSpecs implements java.io.Serializable {
	private static final long serialVersionUID = 4997256482824497632L;
	
	private ArrayList scFSCoatingList	= null;
	private ArrayList scFSDecSpecList	= null;
	/**
	 * Default constructor.
	 */
	public SCFinSpecs() {
		super();
	}
	/**
	 * @return the scFSCoatingList
	 */
	public ArrayList getScFSCoatingList() {
		return scFSCoatingList;
	}
	/**
	 * @param scFSCoatingList the scFSCoatingList to set
	 */
	public void setScFSCoatingList(ArrayList scFSCoatingList) {
		this.scFSCoatingList = scFSCoatingList;
	}
	/**
	 * @return the scFSDecSpecList
	 */
	public ArrayList getScFSDecSpecList() {
		return scFSDecSpecList;
	}
	/**
	 * @param scFSDecSpecList the scFSDecSpecList to set
	 */
	public void setScFSDecSpecList(ArrayList scFSDecSpecList) {
		this.scFSDecSpecList = scFSDecSpecList;
	}
}
