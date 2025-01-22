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
 * Title		: 	CaseAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CaseAddText is a data transfer object to store the specification Binding Case  
 * Additional Text details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseAddText implements java.io.Serializable {
	private static final long serialVersionUID = -7052348124793166254L;
	
	private String caseAddText = null;
	/**
	 * Default constructor.
	 */
	public CaseAddText() {
		super();
	}
	/**
	 * @return the caseAddText
	 */
	public String getCaseAddText() {
		return caseAddText;
	}
	/**
	 * @param caseAddText the caseAddText to set
	 */
	public void setCaseAddText(String caseAddText) {
		this.caseAddText = caseAddText;
	}
}