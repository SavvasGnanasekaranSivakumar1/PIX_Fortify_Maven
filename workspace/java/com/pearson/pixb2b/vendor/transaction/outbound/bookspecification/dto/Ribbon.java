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
 * Title		: 	Ribbon.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * Ribbon is a data transfer object to store the Ribbon details of 
 * SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Ribbon implements java.io.Serializable {
	private static final long serialVersionUID = 7864304480681527821L;
	
	private String numOfRibbon	 	= null;
	private RMatCharacs rMatCharacs = null;
	private RExposure rExposure		= null;
	private ArrayList riAddTextList = null;
	/**
	 * Default constructor.
	 */
	public Ribbon() {
		super();
	}
	/**
	 * @return the numOfRibbon
	 */
	public String getNumOfRibbon() {
		return numOfRibbon;
	}
	/**
	 * @param numOfRibbon the numOfRibbon to set
	 */
	public void setNumOfRibbon(String numOfRibbon) {
		this.numOfRibbon = numOfRibbon;
	}
	/**
	 * @return the rMatCharacs
	 */
	public RMatCharacs getRMatCharacs() {
		return rMatCharacs;
	}
	/**
	 * @param matCharacs the rMatCharacs to set
	 */
	public void setRMatCharacs(RMatCharacs matCharacs) {
		rMatCharacs = matCharacs;
	}
	/**
	 * @return the rExposure
	 */
	public RExposure getRExposure() {
		return rExposure;
	}
	/**
	 * @param exposure the rExposure to set
	 */
	public void setRExposure(RExposure exposure) {
		rExposure = exposure;
	}
	/**
	 * @return the riAddTextList
	 */
	public ArrayList getRiAddTextList() {
		return riAddTextList;
	}
	/**
	 * @param riAddTextList the riAddTextList to set
	 */
	public void setRiAddTextList(ArrayList riAddTextList) {
		this.riAddTextList = riAddTextList;
	}
}