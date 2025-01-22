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
 * Title		: 	SIInformation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.util.ArrayList;

/**
 * SIInformation is a helper data transfer object to store the 
 * Shipping Instruction Information details.
 * 
 * @author Ashish Agrawal
 */
public class SIInformation implements java.io.Serializable {
	private static final long serialVersionUID = 3822424538119376646L;
	
	private String siNumber = null;
	private SIIssuedDate siIssueDate = null;
	private ArrayList siReferenceList = null;
	/**
	 * Default constructor.
	 */
	public SIInformation() {
		super();
	}
	/**
	 * @return the siNumber
	 */
	public String getSiNumber() {
		return siNumber;
	}
	/**
	 * @param siNumber the siNumber to set
	 */
	public void setSiNumber(String siNumber) {
		this.siNumber = siNumber;
	}
	/**
	 * @return the siIssueDate
	 */
	public SIIssuedDate getSiIssueDate() {
		return siIssueDate;
	}
	/**
	 * @param siIssueDate the siIssueDate to set
	 */
	public void setSiIssueDate(SIIssuedDate siIssueDate) {
		this.siIssueDate = siIssueDate;
	}
	/**
	 * @return the siReferenceList
	 */
	public ArrayList getSiReferenceList() {
		return siReferenceList;
	}
	/**
	 * @param siReferenceList the siReferenceList to set
	 */
	public void setSiReferenceList(ArrayList siReferenceList) {
		this.siReferenceList = siReferenceList;
	}
}
