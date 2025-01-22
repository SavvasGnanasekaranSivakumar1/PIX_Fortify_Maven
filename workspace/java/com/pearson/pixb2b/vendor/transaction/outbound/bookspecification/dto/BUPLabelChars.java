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
 * Title		: 	BUPLabelChars.java
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
 * BUPLabelChars is a data transfer object to store the Book Spec Unit Packing
 * Label Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPLabelChars implements java.io.Serializable {
	private static final long serialVersionUID = 6840922540844548958L;
	
	private ArrayList bupLCCMarksList 	= null;
	private String labStyle				= null;
	private String labBrandName			= null;
	private String labPosition			= null;	
	private String numOfLabels			= null;
	private BUPLCLength buplcLength		= null;
	private BUPLCWidth buplcWidth		= null;
	private BUPLCColCode buplcColCode	= null;
	private String colDesps 			= null;
	/**
	 * Default constructor.
	 */
	public BUPLabelChars() {
		super();
	}
	/**
	 * @return the bupLCCMarksList
	 */
	public ArrayList getBupLCCMarksList() {
		return bupLCCMarksList;
	}
	/**
	 * @param bupLCCMarksList the bupLCCMarksList to set
	 */
	public void setBupLCCMarksList(ArrayList bupLCCMarksList) {
		this.bupLCCMarksList = bupLCCMarksList;
	}
	/**
	 * @return the labStyle
	 */
	public String getLabStyle() {
		return labStyle;
	}
	/**
	 * @param labStyle the labStyle to set
	 */
	public void setLabStyle(String labStyle) {
		this.labStyle = labStyle;
	}
	/**
	 * @return the labBrandName
	 */
	public String getLabBrandName() {
		return labBrandName;
	}
	/**
	 * @param labBrandName the labBrandName to set
	 */
	public void setLabBrandName(String labBrandName) {
		this.labBrandName = labBrandName;
	}
	/**
	 * @return the labPosition
	 */
	public String getLabPosition() {
		return labPosition;
	}
	/**
	 * @param labPosition the labPosition to set
	 */
	public void setLabPosition(String labPosition) {
		this.labPosition = labPosition;
	}
	/**
	 * @return the numOfLabels
	 */
	public String getNumOfLabels() {
		return numOfLabels;
	}
	/**
	 * @param numOfLabels the numOfLabels to set
	 */
	public void setNumOfLabels(String numOfLabels) {
		this.numOfLabels = numOfLabels;
	}
	/**
	 * @return the buplcLength
	 */
	public BUPLCLength getBuplcLength() {
		return buplcLength;
	}
	/**
	 * @param buplcLength the buplcLength to set
	 */
	public void setBuplcLength(BUPLCLength buplcLength) {
		this.buplcLength = buplcLength;
	}
	/**
	 * @return the buplcWidth
	 */
	public BUPLCWidth getBuplcWidth() {
		return buplcWidth;
	}
	/**
	 * @param buplcWidth the buplcWidth to set
	 */
	public void setBuplcWidth(BUPLCWidth buplcWidth) {
		this.buplcWidth = buplcWidth;
	}
	/**
	 * @return the buplcColCode
	 */
	public BUPLCColCode getBuplcColCode() {
		return buplcColCode;
	}
	/**
	 * @param buplcColCode the buplcColCode to set
	 */
	public void setBuplcColCode(BUPLCColCode buplcColCode) {
		this.buplcColCode = buplcColCode;
	}
	/**
	 * @return the colDesps
	 */
	public String getColDesps() {
		return colDesps;
	}
	/**
	 * @param colDesps the colDesps to set
	 */
	public void setColDesps(String colDesps) {
		this.colDesps = colDesps;
	}
}
