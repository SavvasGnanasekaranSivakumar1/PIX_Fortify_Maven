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
 * Title		: 	SPBCBandChars.java
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
 * SPBCBandChars is a data transfer object to store the Spec Packing
 * Box Band Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCBandChars implements java.io.Serializable {
	private static final long serialVersionUID = 3502552564272283971L;
	
	private String bandType			= null;
	private String bandsRequired	= null;
	private String bandDirection	= null;
	private String numOfBands 		= null;
	private ArrayList spbcBdColList = null;
	/**
	 * Default constructor.
	 */
	public SPBCBandChars() {
		super();
	}
	/**
	 * @return the bandType
	 */
	public String getBandType() {
		return bandType;
	}
	/**
	 * @param bandType the bandType to set
	 */
	public void setBandType(String bandType) {
		this.bandType = bandType;
	}
	/**
	 * @return the bandsRequired
	 */
	public String getBandsRequired() {
		return bandsRequired;
	}
	/**
	 * @param bandsRequired the bandsRequired to set
	 */
	public void setBandsRequired(String bandsRequired) {
		this.bandsRequired = bandsRequired;
	}
	/**
	 * @return the bandDirection
	 */
	public String getBandDirection() {
		return bandDirection;
	}
	/**
	 * @param bandDirection the bandDirection to set
	 */
	public void setBandDirection(String bandDirection) {
		this.bandDirection = bandDirection;
	}
	/**
	 * @return the numOfBands
	 */
	public String getNumOfBands() {
		return numOfBands;
	}
	/**
	 * @param numOfBands the numOfBands to set
	 */
	public void setNumOfBands(String numOfBands) {
		this.numOfBands = numOfBands;
	}
	/**
	 * @return the spbcBdColList
	 */
	public ArrayList getSpbcBdColList() {
		return spbcBdColList;
	}
	/**
	 * @param spbcBdColList the spbcBdColList to set
	 */
	public void setSpbcBdColList(ArrayList spbcBdColList) {
		this.spbcBdColList = spbcBdColList;
	}
}
