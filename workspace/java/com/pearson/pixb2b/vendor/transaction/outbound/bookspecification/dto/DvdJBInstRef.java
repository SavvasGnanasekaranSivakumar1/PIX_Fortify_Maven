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
 * Title		: 	DvdJBInstRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdJBInstRef is a data transfer object to store the Specification NonPress Component
 * DVD JewelBox InsertReference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdJBInstRef implements java.io.Serializable {
	private static final long serialVersionUID = -4344526031389285689L;
	
	private String dvdJBInstRef = null;
	/**
	 * Default constructor.
	 */
	public DvdJBInstRef() {
		super();
	}
	/**
	 * @return the dvdJBInstRef
	 */
	public String getDvdJBInstRef() {
		return dvdJBInstRef;
	}
	/**
	 * @param dvdJBInstRef the dvdJBInstRef to set
	 */
	public void setDvdJBInstRef(String dvdJBInstRef) {
		this.dvdJBInstRef = dvdJBInstRef;
	}
}
