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
 * Title		: 	SCFPrepInp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFPrepInp is a data transfer object to store the Specification Component 
 * Supplied Component FinishPrep Input details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFPrepInp implements java.io.Serializable {
	private static final long serialVersionUID = -892307279448709977L;
	
	private String mediaType 	= null;
	private String finPrepInp	= null;
	/**
	 * Default constructor.
	 */
	public SCFPrepInp() {
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
	 * @return the finPrepInp
	 */
	public String getFinPrepInp() {
		return finPrepInp;
	}
	/**
	 * @param finPrepInp the finPrepInp to set
	 */
	public void setFinPrepInp(String finPrepInp) {
		this.finPrepInp = finPrepInp;
	}
}
