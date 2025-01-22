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
 * Title		: 	VCPrPrepInp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * VCPrPrepInp is a data transfer object to store the Specification NonPress Component 
 * Video Cassette Label Characteristics PressPrep Input details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class VCPrPrepInp implements java.io.Serializable {
	private static final long serialVersionUID = 8908538050573935071L;
	
	private String mediaType 	 = null;
	private String vcPrepInpType = null;
	/**
	 * Default constructor.
	 */
	public VCPrPrepInp() {
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
	 * @return the vcPrepInpType
	 */
	public String getVcPrepInpType() {
		return vcPrepInpType;
	}
	/**
	 * @param vcPrepInpType the vcPrepInpType to set
	 */
	public void setVcPrepInpType(String vcPrepInpType) {
		this.vcPrepInpType = vcPrepInpType;
	}
}
