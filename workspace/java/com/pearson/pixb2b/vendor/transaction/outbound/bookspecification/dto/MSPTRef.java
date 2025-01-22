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
 * Title		: 	MSPTRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSPTRef is a data transfer object to store the Specification NonPress Component 
 * Media Slide HolePunch Template Reference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSPTRef implements java.io.Serializable {
	private static final long serialVersionUID = -2015336230495237898L;
	
	private String phPTRef = null;
	/**
	 * Default constructor.
	 */
	public MSPTRef() {
		super();
	}
	/**
	 * @return the phPTRef
	 */
	public String getPhPTRef() {
		return phPTRef;
	}
	/**
	 * @param phPTRef the phPTRef to set
	 */
	public void setPhPTRef(String phPTRef) {
		this.phPTRef = phPTRef;
	}
}
