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
 * Title		: 	RMatCharacs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	25 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * RMatCharacs is a data transfer object to store the Ribbon Material   
 * Characteristics Details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class RMatCharacs implements java.io.Serializable {
	private static final long serialVersionUID = 5764766373959220952L;
	
	private String ribFacType 		  = null;
	private RMBMChars rmbmChars		  = null;
	private RMBMWidth rmbmWidth		  = null;
	private ArrayList rmbmAddTextList = null;
	/**
	 * Default constructor.
	 */
	public RMatCharacs() {
		super();
	}
	/**
	 * @return the ribFacType
	 */
	public String getRibFacType() {
		return ribFacType;
	}
	/**
	 * @param ribFacType the ribFacType to set
	 */
	public void setRibFacType(String ribFacType) {
		this.ribFacType = ribFacType;
	}
	/**
	 * @return the rmbmChars
	 */
	public RMBMChars getRmbmChars() {
		return rmbmChars;
	}
	/**
	 * @param rmbmChars the rmbmChars to set
	 */
	public void setRmbmChars(RMBMChars rmbmChars) {
		this.rmbmChars = rmbmChars;
	}
	/**
	 * @return the rmbmWidth
	 */
	public RMBMWidth getRmbmWidth() {
		return rmbmWidth;
	}
	/**
	 * @param rmbmWidth the rmbmWidth to set
	 */
	public void setRmbmWidth(RMBMWidth rmbmWidth) {
		this.rmbmWidth = rmbmWidth;
	}
	/**
	 * @return the rmbmAddTextList
	 */
	public ArrayList getRmbmAddTextList() {
		return rmbmAddTextList;
	}
	/**
	 * @param rmbmAddTextList the rmbmAddTextList to set
	 */
	public void setRmbmAddTextList(ArrayList rmbmAddTextList) {
		this.rmbmAddTextList = rmbmAddTextList;
	}
}
