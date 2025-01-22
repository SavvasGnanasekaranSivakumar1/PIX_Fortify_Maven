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
 * Title		: 	CDMedLeng.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CDMedLeng is a data transfer object to store the Specification NonPress Component 
 * CD Media Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDMedLeng implements java.io.Serializable {
	private static final long serialVersionUID = -5752799780761314003L;
	
	private String totalTime	= null;
	private String sideOneTime	= null;
	private String sideTwoTime	= null;
	/**
	 * Default constructor.
	 */
	public CDMedLeng() {
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
