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
 * Title		: 	CaseSupers.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CaseSupers is a data transfer object to store the specification Binding   
 * CaseSupers Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseSupers implements java.io.Serializable {
	private static final long serialVersionUID = -4700369858693185142L;
	
	private String supType	= null;
	private CSQuant csQuant = null;
	/**
	 * Default constructor.
	 */
	public CaseSupers() {
		super();
	}
	/**
	 * @return the supType
	 */
	public String getSupType() {
		return supType;
	}
	/**
	 * @param supType the supType to set
	 */
	public void setSupType(String supType) {
		this.supType = supType;
	}
	/**
	 * @return the csQuant
	 */
	public CSQuant getCsQuant() {
		return csQuant;
	}
	/**
	 * @param csQuant the csQuant to set
	 */
	public void setCsQuant(CSQuant csQuant) {
		this.csQuant = csQuant;
	}
}
