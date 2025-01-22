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
 * Title		: 	SPBCLabCharcs.java
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
 * SPBCLabCharcs is a data transfer object to store the Spec Packing
 * Box Label Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCLabCharcs implements java.io.Serializable {
	private static final long serialVersionUID = 428121692505759381L;
	
	private ArrayList spbcLCCMarksList 	= null;
	private String labStyle				= null;
	private String labBrandName			= null;
	private String labPosition			= null;	
	private String numOfLabels			= null;
	private SPBCLCLength spbclcLength	= null;
	private SPBCLCWidth spbclcWidth		= null;
	private SPBCLCColCode spbclcColCode	= null;
	private String colDesps 			= null;
	/**
	 * Default constructor.
	 */
	public SPBCLabCharcs() {
		super();
	}
	/**
	 * @return the spbcLCCMarksList
	 */
	public ArrayList getSpbcLCCMarksList() {
		return spbcLCCMarksList;
	}
	/**
	 * @param spbcLCCMarksList the spbcLCCMarksList to set
	 */
	public void setSpbcLCCMarksList(ArrayList spbcLCCMarksList) {
		this.spbcLCCMarksList = spbcLCCMarksList;
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
	 * @return the spbclcLength
	 */
	public SPBCLCLength getSpbclcLength() {
		return spbclcLength;
	}
	/**
	 * @param spbclcLength the spbclcLength to set
	 */
	public void setSpbclcLength(SPBCLCLength spbclcLength) {
		this.spbclcLength = spbclcLength;
	}
	/**
	 * @return the spbclcWidth
	 */
	public SPBCLCWidth getSpbclcWidth() {
		return spbclcWidth;
	}
	/**
	 * @param spbclcWidth the spbclcWidth to set
	 */
	public void setSpbclcWidth(SPBCLCWidth spbclcWidth) {
		this.spbclcWidth = spbclcWidth;
	}
	/**
	 * @return the spbclcColCode
	 */
	public SPBCLCColCode getSpbclcColCode() {
		return spbclcColCode;
	}
	/**
	 * @param spbclcColCode the spbclcColCode to set
	 */
	public void setSpbclcColCode(SPBCLCColCode spbclcColCode) {
		this.spbclcColCode = spbclcColCode;
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
