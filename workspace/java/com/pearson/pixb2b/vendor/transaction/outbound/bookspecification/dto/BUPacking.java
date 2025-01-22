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
 * Title		: 	BUPacking.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BUPacking is a data transfer object to store the Book Spec
 * UnitPacking details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPacking implements java.io.Serializable {
	private static final long serialVersionUID = -349146642546713897L;
	
	private String bookUnitType 		= null;
	private String packageType			= null;
	private BUPUnitCharas bupUnitCharas = null;
	private ArrayList bupLabelCharsList = null;
	private BUPStenChars bupStenChars	= null;
	private BUPWrapChars bupWrapChars 	= null;
	private ArrayList bupAddTextList	= null;
	
	/**
	 * Default constructor.
	 */
	public BUPacking() {
		super();
	}

	/**
	 * @return the bookUnitType
	 */
	public String getBookUnitType() {
		return bookUnitType;
	}

	/**
	 * @param bookUnitType the bookUnitType to set
	 */
	public void setBookUnitType(String bookUnitType) {
		this.bookUnitType = bookUnitType;
	}

	/**
	 * @return the packageType
	 */
	public String getPackageType() {
		return packageType;
	}

	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	/**
	 * @return the bupUnitCharas
	 */
	public BUPUnitCharas getBupUnitCharas() {
		return bupUnitCharas;
	}

	/**
	 * @param bupUnitCharas the bupUnitCharas to set
	 */
	public void setBupUnitCharas(BUPUnitCharas bupUnitCharas) {
		this.bupUnitCharas = bupUnitCharas;
	}

	/**
	 * @return the bupLabelCharsList
	 */
	public ArrayList getBupLabelCharsList() {
		return bupLabelCharsList;
	}

	/**
	 * @param bupLabelCharsList the bupLabelCharsList to set
	 */
	public void setBupLabelCharsList(ArrayList bupLabelCharsList) {
		this.bupLabelCharsList = bupLabelCharsList;
	}

	/**
	 * @return the bupStenChars
	 */
	public BUPStenChars getBupStenChars() {
		return bupStenChars;
	}

	/**
	 * @param bupStenChars the bupStenChars to set
	 */
	public void setBupStenChars(BUPStenChars bupStenChars) {
		this.bupStenChars = bupStenChars;
	}

	/**
	 * @return the bupWrapChars
	 */
	public BUPWrapChars getBupWrapChars() {
		return bupWrapChars;
	}

	/**
	 * @param bupWrapChars the bupWrapChars to set
	 */
	public void setBupWrapChars(BUPWrapChars bupWrapChars) {
		this.bupWrapChars = bupWrapChars;
	}

	/**
	 * @return the bupAddTextList
	 */
	public ArrayList getBupAddTextList() {
		return bupAddTextList;
	}

	/**
	 * @param bupAddTextList the bupAddTextList to set
	 */
	public void setBupAddTextList(ArrayList bupAddTextList) {
		this.bupAddTextList = bupAddTextList;
	}
}
