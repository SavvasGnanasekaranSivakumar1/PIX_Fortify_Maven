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
 * Title		: 	DvdPackg.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdPackg is a data transfer object to store the Specification NonPress Component Media
 * DVD Packaging details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdPackg implements java.io.Serializable {
	private static final long serialVersionUID = -5556223954119995880L;
	
	private String bulNumOfUnit		= null;
	private DvdJewlBox dvdJewlBox	= null;
	private DvdSleeve dvdSleeve		= null;
	/**
	 * Default constructor.
	 */
	public DvdPackg() {
		super();
	}
	/**
	 * @return the bulNumOfUnit
	 */
	public String getBulNumOfUnit() {
		return bulNumOfUnit;
	}
	/**
	 * @param bulNumOfUnit the bulNumOfUnit to set
	 */
	public void setBulNumOfUnit(String bulNumOfUnit) {
		this.bulNumOfUnit = bulNumOfUnit;
	}
	/**
	 * @return the dvdJewlBox
	 */
	public DvdJewlBox getDvdJewlBox() {
		return dvdJewlBox;
	}
	/**
	 * @param dvdJewlBox the dvdJewlBox to set
	 */
	public void setDvdJewlBox(DvdJewlBox dvdJewlBox) {
		this.dvdJewlBox = dvdJewlBox;
	}
	/**
	 * @return the dvdSleeve
	 */
	public DvdSleeve getDvdSleeve() {
		return dvdSleeve;
	}
	/**
	 * @param dvdSleeve the dvdSleeve to set
	 */
	public void setDvdSleeve(DvdSleeve dvdSleeve) {
		this.dvdSleeve = dvdSleeve;
	}
}
