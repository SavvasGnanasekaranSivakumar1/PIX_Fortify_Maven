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
 * Title		: 	BBFExtras.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BBFExtras is a data transfer object to store the Book Block 
 * Finish Extras details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBFExtras implements java.io.Serializable {
	private static final long serialVersionUID = 4968204158047374726L;
	
	private String bbFExtras = null;
	/**
	 * Default constructor.
	 */
	public BBFExtras() {
		super();
	}
	/**
	 * @return the bbFExtras
	 */
	public String getBbFExtras() {
		return bbFExtras;
	}
	/**
	 * @param bbFExtras the bbFExtras to set
	 */
	public void setBbFExtras(String bbFExtras) {
		this.bbFExtras = bbFExtras;
	}
}
