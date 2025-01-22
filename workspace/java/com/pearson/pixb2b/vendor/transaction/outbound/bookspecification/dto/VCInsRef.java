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
 * Title		: 	VCInsRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * VCInsRef is a data transfer object to store the Specification NonPress Component 
 * Media Video Cassette Insert Reference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class VCInsRef implements java.io.Serializable {
	private static final long serialVersionUID = 3792618144844965642L;
	
	private String vcInsRef	= null;
	/**
	 * Default constructor.
	 */
	public VCInsRef() {
		super();
	}
	/**
	 * @return the vcInsRef
	 */
	public String getVcInsRef() {
		return vcInsRef;
	}
	/**
	 * @param vcInsRef the vcInsRef to set
	 */
	public void setVcInsRef(String vcInsRef) {
		this.vcInsRef = vcInsRef;
	}
}
