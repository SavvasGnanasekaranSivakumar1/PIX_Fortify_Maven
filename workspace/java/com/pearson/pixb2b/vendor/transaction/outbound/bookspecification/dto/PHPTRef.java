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
 * Title		: 	PHPTRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PHPTRef is a data transfer object to store the specification Binding   
 * PunchedHole Template Reference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PHPTRef implements java.io.Serializable {
	private static final long serialVersionUID = 6325537730051051265L;
	
	private String phPTRef = null;
	/**
	 * Default constructor.
	 */
	public PHPTRef() {
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
