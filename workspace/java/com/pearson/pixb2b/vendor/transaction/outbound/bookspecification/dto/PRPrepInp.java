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
 * Title		: 	PRPrepInp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PRPrepInp s a data transfer object to store the Specification 
 * Press Component PressPrep Input details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PRPrepInp implements java.io.Serializable {
	private static final long serialVersionUID = 912735672487933455L;
	
	private String mediaType 	 = null;
	private String prPrepInpType = null;
	/**
	 * Default constructor.
	 */
	public PRPrepInp() {
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
	 * @return the prPrepInpType
	 */
	public String getPrPrepInpType() {
		return prPrepInpType;
	}
	/**
	 * @param prPrepInpType the prPrepInpType to set
	 */
	public void setPrPrepInpType(String prPrepInpType) {
		this.prPrepInpType = prPrepInpType;
	}
}
