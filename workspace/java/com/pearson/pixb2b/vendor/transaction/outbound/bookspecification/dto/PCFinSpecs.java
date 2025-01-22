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
 * Title		: 	PCFinSpecs.java
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
 * PCFinSpecs is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications FinishSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFinSpecs implements java.io.Serializable {
	private static final long serialVersionUID = -2277554212933715958L;
	
	private ArrayList pcFSCoatingList	= null;
	private ArrayList pcFSDecSpecList	= null;
	/**
	 * Default constructor.
	 */
	public PCFinSpecs() {
		super();
	}
	/**
	 * @return the pcFSCoatingList
	 */
	public ArrayList getPcFSCoatingList() {
		return pcFSCoatingList;
	}
	/**
	 * @param pcFSCoatingList the pcFSCoatingList to set
	 */
	public void setPcFSCoatingList(ArrayList pcFSCoatingList) {
		this.pcFSCoatingList = pcFSCoatingList;
	}
	/**
	 * @return the pcFSDecSpecList
	 */
	public ArrayList getPcFSDecSpecList() {
		return pcFSDecSpecList;
	}
	/**
	 * @param pcFSDecSpecList the pcFSDecSpecList to set
	 */
	public void setPcFSDecSpecList(ArrayList pcFSDecSpecList) {
		this.pcFSDecSpecList = pcFSDecSpecList;
	}
}
