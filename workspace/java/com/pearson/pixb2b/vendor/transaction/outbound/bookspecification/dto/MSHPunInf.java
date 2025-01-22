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
 * Title		: 	MSHPunInf.java
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
 * MSHPunInf is a data transfer object to store the Specification NonPress Component 
 * Media Slide HolePunch Information details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSHPunInf implements java.io.Serializable {
	private static final long serialVersionUID = -3402750161953934868L;
	
	private String hptCover				= null;
	private MSPHDetail msPHDetail		= null;
	private ArrayList msHPAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public MSHPunInf() {
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
	 * @return the msPHDetail
	 */
	public MSPHDetail getMsPHDetail() {
		return msPHDetail;
	}
	/**
	 * @param msPHDetail the msPHDetail to set
	 */
	public void setMsPHDetail(MSPHDetail msPHDetail) {
		this.msPHDetail = msPHDetail;
	}
	/**
	 * @return the msHPAddTextList
	 */
	public ArrayList getMsHPAddTextList() {
		return msHPAddTextList;
	}
	/**
	 * @param msHPAddTextList the msHPAddTextList to set
	 */
	public void setMsHPAddTextList(ArrayList msHPAddTextList) {
		this.msHPAddTextList = msHPAddTextList;
	}
}
