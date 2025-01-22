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
 * Title		: 	CDPrPrepInp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CDPrPrepInp is a data transfer object to store the Specification NonPress Component 
 * CD PressPrep Input details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDPrPrepInp implements java.io.Serializable {
	private static final long serialVersionUID = 7397689714810294048L;
	
	private String mediaType 	 = null;
	private String cdPrepInpType = null;
	/**
	 * Default constructor.
	 */
	public CDPrPrepInp() {
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
	 * @return the cdPrepInpType
	 */
	public String getCdPrepInpType() {
		return cdPrepInpType;
	}
	/**
	 * @param cdPrepInpType the cdPrepInpType to set
	 */
	public void setCdPrepInpType(String cdPrepInpType) {
		this.cdPrepInpType = cdPrepInpType;
	}
}
