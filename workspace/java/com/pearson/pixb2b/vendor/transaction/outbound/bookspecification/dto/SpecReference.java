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
 * Title		: 	SpecReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   20 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SpecReference is a data transfer object to store the 
 * Specification Reference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecReference implements java.io.Serializable {
	private static final long serialVersionUID = 921843038300018405L;
	
	private String specReferenceType 	= null;
	private String specReferenceValue 	= null;
	/**
	 * Default constructor.
	 */
	public SpecReference() {
		super();
	}
	/**
	 * @return the specReferenceType
	 */
	public String getSpecReferenceType() {
		return specReferenceType;
	}
	/**
	 * @param specReferenceType the specReferenceType to set
	 */
	public void setSpecReferenceType(String specReferenceType) {
		this.specReferenceType = specReferenceType;
	}
	/**
	 * @return the specReferenceValue
	 */
	public String getSpecReferenceValue() {
		return specReferenceValue;
	}
	/**
	 * @param specReferenceValue the specReferenceValue to set
	 */
	public void setSpecReferenceValue(String specReferenceValue) {
		this.specReferenceValue = specReferenceValue;
	}
}
