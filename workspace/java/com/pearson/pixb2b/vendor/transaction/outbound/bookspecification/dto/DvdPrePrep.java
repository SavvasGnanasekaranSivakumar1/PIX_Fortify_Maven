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
 * Title		: 	DvdPrePrep.java
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
 * DvdPrePrep is a data transfer object to store the Specification NonPress Component 
 * DVD PressPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdPrePrep implements java.io.Serializable {
	private static final long serialVersionUID = -4257885900112292028L;
	
	private String dvdPrepOutType 	   = null;
	private ArrayList dvdPrPrepInpList = null;
	/**
	 * Default constructor.
	 */
	public DvdPrePrep() {
		super();
	}
	/**
	 * @return the dvdPrepOutType
	 */
	public String getDvdPrepOutType() {
		return dvdPrepOutType;
	}
	/**
	 * @param dvdPrepOutType the dvdPrepOutType to set
	 */
	public void setDvdPrepOutType(String dvdPrepOutType) {
		this.dvdPrepOutType = dvdPrepOutType;
	}
	/**
	 * @return the dvdPrPrepInpList
	 */
	public ArrayList getDvdPrPrepInpList() {
		return dvdPrPrepInpList;
	}
	/**
	 * @param dvdPrPrepInpList the dvdPrPrepInpList to set
	 */
	public void setDvdPrPrepInpList(ArrayList dvdPrPrepInpList) {
		this.dvdPrPrepInpList = dvdPrPrepInpList;
	}
}
