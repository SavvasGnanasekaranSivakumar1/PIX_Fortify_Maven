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
 * Title		: 	ColDesp.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ColDesp is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Ink Characteristics ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ColDesp implements java.io.Serializable {
	private static final long serialVersionUID = -1072852623618824050L;
	
	private String colDesp			= null;
	/**
	 * Default constructor.
	 */
	public ColDesp() {
		super();
	}
	/**
	 * @return the colDesp
	 */
	public String getColDesp() {
		return colDesp;
	}
	/**
	 * @param colDesp the colDesp to set
	 */
	public void setColDesp(String colDesp) {
		this.colDesp = colDesp;
	}
}