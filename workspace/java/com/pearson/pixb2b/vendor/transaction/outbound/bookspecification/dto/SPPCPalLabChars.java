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
 * Title		: 	SPPCPalLabChars.java
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
 * SPPCPalLabChars is a data transfer object to store the Spec Packing Pallet
 * Label Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalLabChars implements java.io.Serializable {
	private static final long serialVersionUID = 3036154886777328783L;
	
	private ArrayList sppcPalLCCMarksList 	= null;
	private String labStyle					= null;
	private String labBrandName				= null;
	private String labPosition				= null;	
	private String numOfLabels				= null;
	private SPPCPalLCLength sppcPalLCLength	= null;
	private SPPCPalLCWidth sppcPalLCWidth	= null;
	private SPPCPalLCColCode sppcPalLCColCode	= null;
	private String colDesps 				= null;
	/**
	 * Default constructor.
	 */
	public SPPCPalLabChars() {
		super();
	}
	/**
	 * @return the sppcPalLCCMarksList
	 */
	public ArrayList getSppcPalLCCMarksList() {
		return sppcPalLCCMarksList;
	}
	/**
	 * @param sppcPalLCCMarksList the sppcPalLCCMarksList to set
	 */
	public void setSppcPalLCCMarksList(ArrayList sppcPalLCCMarksList) {
		this.sppcPalLCCMarksList = sppcPalLCCMarksList;
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
	 * @return the sppcPalLCLength
	 */
	public SPPCPalLCLength getSppcPalLCLength() {
		return sppcPalLCLength;
	}
	/**
	 * @param sppcPalLCLength the sppcPalLCLength to set
	 */
	public void setSppcPalLCLength(SPPCPalLCLength sppcPalLCLength) {
		this.sppcPalLCLength = sppcPalLCLength;
	}
	/**
	 * @return the sppcPalLCWidth
	 */
	public SPPCPalLCWidth getSppcPalLCWidth() {
		return sppcPalLCWidth;
	}
	/**
	 * @param sppcPalLCWidth the sppcPalLCWidth to set
	 */
	public void setSppcPalLCWidth(SPPCPalLCWidth sppcPalLCWidth) {
		this.sppcPalLCWidth = sppcPalLCWidth;
	}
	/**
	 * @return the sppcPalLCColCode
	 */
	public SPPCPalLCColCode getSppcPalLCColCode() {
		return sppcPalLCColCode;
	}
	/**
	 * @param sppcPalLCColCode the sppcPalLCColCode to set
	 */
	public void setSppcPalLCColCode(SPPCPalLCColCode sppcPalLCColCode) {
		this.sppcPalLCColCode = sppcPalLCColCode;
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
