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
 * Title		: 	ACMedLeng.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ACMedLeng is a data transfer object to store the Specification NonPress Component 
 * Audio Cassette Media Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACMedLeng implements java.io.Serializable {
	private static final long serialVersionUID = 1915226599976605434L;
	
	private String totalTime	= null;
	private String sideOneTime	= null;
	private String sideTwoTime	= null;
	/**
	 * Default constructor.
	 */
	public ACMedLeng() {
		super();
	}
	/**
	 * @return the totalTime
	 */
	public String getTotalTime() {
		return totalTime;
	}
	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * @return the sideOneTime
	 */
	public String getSideOneTime() {
		return sideOneTime;
	}
	/**
	 * @param sideOneTime the sideOneTime to set
	 */
	public void setSideOneTime(String sideOneTime) {
		this.sideOneTime = sideOneTime;
	}
	/**
	 * @return the sideTwoTime
	 */
	public String getSideTwoTime() {
		return sideTwoTime;
	}
	/**
	 * @param sideTwoTime the sideTwoTime to set
	 */
	public void setSideTwoTime(String sideTwoTime) {
		this.sideTwoTime = sideTwoTime;
	}
}
