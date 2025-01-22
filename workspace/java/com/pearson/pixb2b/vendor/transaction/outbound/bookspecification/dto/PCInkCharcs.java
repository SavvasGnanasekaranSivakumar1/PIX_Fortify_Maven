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
 * Title		: 	PCInkCharcs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCInkCharcs is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Ink Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCInkCharcs implements java.io.Serializable {
	private static final long serialVersionUID = -7505919473347882798L;
	
	private ArrayList pcInkCovList	= null;
	private String numOfCol			= null;
	private ArrayList pcColSpecList	= null;
	/**
	 * Default constructor.
	 */
	public PCInkCharcs() {
		super();
	}
	/**
	 * @return the pcInkCovList
	 */
	public ArrayList getPcInkCovList() {
		return pcInkCovList;
	}
	/**
	 * @param pcInkCovList the pcInkCovList to set
	 */
	public void setPcInkCovList(ArrayList pcInkCovList) {
		this.pcInkCovList = pcInkCovList;
	}
	/**
	 * @return the numOfCol
	 */
	public String getNumOfCol() {
		return numOfCol;
	}
	/**
	 * @param numOfCol the numOfCol to set
	 */
	public void setNumOfCol(String numOfCol) {
		this.numOfCol = numOfCol;
	}
	/**
	 * @return the pcColSpecList
	 */
	public ArrayList getPcColSpecList() {
		return pcColSpecList;
	}
	/**
	 * @param pcColSpecList the pcColSpecList to set
	 */
	public void setPcColSpecList(ArrayList pcColSpecList) {
		this.pcColSpecList = pcColSpecList;
	}
}
