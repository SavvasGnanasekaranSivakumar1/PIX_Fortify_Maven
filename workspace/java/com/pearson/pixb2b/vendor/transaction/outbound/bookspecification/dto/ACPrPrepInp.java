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
 * Title		: 	ACPrPrepInp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ACPrPrepInp is a data transfer object to store the Specification NonPress Component 
 * Audio Cassette Label Characteristics PressPrep Input details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACPrPrepInp implements java.io.Serializable {
	private static final long serialVersionUID = 8424389625382353650L;
	
	private String mediaType 	 = null;
	private String acPrepInpType = null;
	/**
	 * Default constructor.
	 */
	public ACPrPrepInp() {
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
	 * @return the acPrepInpType
	 */
	public String getAcPrepInpType() {
		return acPrepInpType;
	}
	/**
	 * @param acPrepInpType the acPrepInpType to set
	 */
	public void setAcPrepInpType(String acPrepInpType) {
		this.acPrepInpType = acPrepInpType;
	}
}
