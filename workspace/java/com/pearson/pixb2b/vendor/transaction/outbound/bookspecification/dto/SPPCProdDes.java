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
 * Title		: 	SPPCProdDes.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCProdDes is a data transfer object to store the Spec Packing Pallet
 * Characteristics ProductDescription details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCProdDes implements java.io.Serializable {
	private static final long serialVersionUID = -5224280182012469318L;
	
	private String lang 	= null;
	private String pdVal	= null;
	/**
	 * Default constructor.
	 */
	public SPPCProdDes() {
		super();
	}
	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return the pdVal
	 */
	public String getPdVal() {
		return pdVal;
	}
	/**
	 * @param pdVal the pdVal to set
	 */
	public void setPdVal(String pdVal) {
		this.pdVal = pdVal;
	}
}
