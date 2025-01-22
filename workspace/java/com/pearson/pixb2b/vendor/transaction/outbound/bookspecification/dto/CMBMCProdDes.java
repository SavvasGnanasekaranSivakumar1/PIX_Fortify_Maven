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
 * Title		: 	CMBMCProdDes.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CMBMCProdDes is a data transfer object to store the CaseMaterial BindingMaterial
 * Characteristics ProductDescription details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CMBMCProdDes implements java.io.Serializable {
	private static final long serialVersionUID = -2594772166415567292L;
	
	private String lang 	= null;
	private String pdVal	= null;
	/**
	 * Default constructor.
	 */
	public CMBMCProdDes() {
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
