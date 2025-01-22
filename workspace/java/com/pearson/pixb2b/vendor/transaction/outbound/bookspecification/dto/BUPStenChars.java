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
 * Title		: 	BUPStenChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BUPStenChars is a data transfer object to store the Book Spec Unit Packing
 * Stencil Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPStenChars implements java.io.Serializable {
	private static final long serialVersionUID = 6239990758565977009L;
	
	private String stencilFormat		= null;
	private String stencilLocation		= null;
	private String stencilInkType		= null;	
	private String stencilType			= null;
	private String stencilContent		= null;
	private ArrayList bupStenTextList 	= null;
	/**
	 * Default constructor.
	 */
	public BUPStenChars() {
		super();
	}
	/**
	 * @return the stencilFormat
	 */
	public String getStencilFormat() {
		return stencilFormat;
	}
	/**
	 * @param stencilFormat the stencilFormat to set
	 */
	public void setStencilFormat(String stencilFormat) {
		this.stencilFormat = stencilFormat;
	}
	/**
	 * @return the stencilLocation
	 */
	public String getStencilLocation() {
		return stencilLocation;
	}
	/**
	 * @param stencilLocation the stencilLocation to set
	 */
	public void setStencilLocation(String stencilLocation) {
		this.stencilLocation = stencilLocation;
	}
	/**
	 * @return the stencilInkType
	 */
	public String getStencilInkType() {
		return stencilInkType;
	}
	/**
	 * @param stencilInkType the stencilInkType to set
	 */
	public void setStencilInkType(String stencilInkType) {
		this.stencilInkType = stencilInkType;
	}
	/**
	 * @return the stencilType
	 */
	public String getStencilType() {
		return stencilType;
	}
	/**
	 * @param stencilType the stencilType to set
	 */
	public void setStencilType(String stencilType) {
		this.stencilType = stencilType;
	}
	/**
	 * @return the stencilContent
	 */
	public String getStencilContent() {
		return stencilContent;
	}
	/**
	 * @param stencilContent the stencilContent to set
	 */
	public void setStencilContent(String stencilContent) {
		this.stencilContent = stencilContent;
	}
	/**
	 * @return the bupStenTextList
	 */
	public ArrayList getBupStenTextList() {
		return bupStenTextList;
	}
	/**
	 * @param bupStenTextList the bupStenTextList to set
	 */
	public void setBupStenTextList(ArrayList bupStenTextList) {
		this.bupStenTextList = bupStenTextList;
	}
}
