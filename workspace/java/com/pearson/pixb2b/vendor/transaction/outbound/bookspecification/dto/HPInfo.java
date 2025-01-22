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
 * Title		: 	HPInfo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * HPInfo is a data transfer object to store the specification Binding   
 * HolePunch Information details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class HPInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1078566305523217094L;
	
	private String hptCover			= null;
	private PHDetails phDetails 	= null;
	private ArrayList hpAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public HPInfo() {
		super();
	}
	/**
	 * @return the hptCover
	 */
	public String getHptCover() {
		return hptCover;
	}
	/**
	 * @param hptCover the hptCover to set
	 */
	public void setHptCover(String hptCover) {
		this.hptCover = hptCover;
	}
	/**
	 * @return the phDetails
	 */
	public PHDetails getPhDetails() {
		return phDetails;
	}
	/**
	 * @param phDetails the phDetails to set
	 */
	public void setPhDetails(PHDetails phDetails) {
		this.phDetails = phDetails;
	}
	/**
	 * @return the hpAddTextList
	 */
	public ArrayList getHpAddTextList() {
		return hpAddTextList;
	}
	/**
	 * @param hpAddTextList the hpAddTextList to set
	 */
	public void setHpAddTextList(ArrayList hpAddTextList) {
		this.hpAddTextList = hpAddTextList;
	}
}
