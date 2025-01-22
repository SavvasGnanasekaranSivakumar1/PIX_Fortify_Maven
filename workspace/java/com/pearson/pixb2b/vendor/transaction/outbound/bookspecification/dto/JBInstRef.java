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
 * Title		: 	JBInstRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * JBInstRef is a data transfer object to store the Specification NonPress Component
 * CD JewelBox InsertReference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class JBInstRef implements java.io.Serializable {
	private static final long serialVersionUID = -1892899277399564462L;
	
	private String jbInstRef = null;
	/**
	 * Default constructor.
	 */
	public JBInstRef() {
		super();
	}
	/**
	 * @return the jbInstRef
	 */
	public String getJbInstRef() {
		return jbInstRef;
	}
	/**
	 * @param jbInstRef the jbInstRef to set
	 */
	public void setJbInstRef(String jbInstRef) {
		this.jbInstRef = jbInstRef;
	}
}
