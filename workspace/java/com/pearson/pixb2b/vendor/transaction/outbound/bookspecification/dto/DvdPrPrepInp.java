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
 * Title		: 	DvdPrPrepInp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdPrPrepInp is a data transfer object to store the Specification NonPress Component 
 * DVD PressPrep Input details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdPrPrepInp implements java.io.Serializable {
	private static final long serialVersionUID = 5730461983535602893L;
	
	private String mediaType 	  = null;
	private String dvdPrepInpType = null;
	/**
	 * Default constructor.
	 */
	public DvdPrPrepInp() {
		super();
	}
	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}
	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	/**
	 * @return the dvdPrepInpType
	 */
	public String getDvdPrepInpType() {
		return dvdPrepInpType;
	}
	/**
	 * @param dvdPrepInpType the dvdPrepInpType to set
	 */
	public void setDvdPrepInpType(String dvdPrepInpType) {
		this.dvdPrepInpType = dvdPrepInpType;
	}
}
