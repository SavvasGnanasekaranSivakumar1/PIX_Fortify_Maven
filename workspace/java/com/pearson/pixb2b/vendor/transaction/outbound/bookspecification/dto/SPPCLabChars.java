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
 * Title		: 	SPPCLabChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SPPCLabChars is a data transfer object to store the Spec Packing Pallet
 * Packaging Label Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCLabChars implements java.io.Serializable {
	private static final long serialVersionUID = 3857807913543671206L;
	
	private ArrayList sppcLCCMarksList 	= null;
	private String labStyle				= null;
	private String labBrandName			= null;
	private String labPosition			= null;	
	private String numOfLabels			= null;
	private SPPCLCLength sppclcLength	= null;
	private SPPCLCWidth sppclcWidth		= null;
	private SPPCLCColCode sppclcColCode	= null;
	private String colDesps 			= null;
	/**
	 * Default constructor.
	 */
	public SPPCLabChars() {
		super();
	}
	/**
	 * @return the sppcLCCMarksList
	 */
	public ArrayList getSppcLCCMarksList() {
		return sppcLCCMarksList;
	}
	/**
	 * @param sppcLCCMarksList the sppcLCCMarksList to set
	 */
	public void setSppcLCCMarksList(ArrayList sppcLCCMarksList) {
		this.sppcLCCMarksList = sppcLCCMarksList;
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
	 * @return the sppclcLength
	 */
	public SPPCLCLength getSppclcLength() {
		return sppclcLength;
	}
	/**
	 * @param sppclcLength the sppclcLength to set
	 */
	public void setSppclcLength(SPPCLCLength sppclcLength) {
		this.sppclcLength = sppclcLength;
	}
	/**
	 * @return the sppclcWidth
	 */
	public SPPCLCWidth getSppclcWidth() {
		return sppclcWidth;
	}
	/**
	 * @param sppclcWidth the sppclcWidth to set
	 */
	public void setSppclcWidth(SPPCLCWidth sppclcWidth) {
		this.sppclcWidth = sppclcWidth;
	}
	/**
	 * @return the sppclcColCode
	 */
	public SPPCLCColCode getSppclcColCode() {
		return sppclcColCode;
	}
	/**
	 * @param sppclcColCode the sppclcColCode to set
	 */
	public void setSppclcColCode(SPPCLCColCode sppclcColCode) {
		this.sppclcColCode = sppclcColCode;
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
