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
 * Title		: 	SpecInformation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   20 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SpecInformation is a data transfer object to store the 
 * Specification Information details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecInformation implements java.io.Serializable {
	private static final long serialVersionUID = -9180500512606092927L;
	
	private String specificationNumber	= null;
	private String specVersion			= null;
	private SpecIssueDate specIssueDate	= null;
	private String	specGroupingID		= null;
	private ArrayList specReferenceList	= null;
	/**
	 * Default constructor.
	 */
	public SpecInformation() {
		super();
	}
	/**
	 * @return the specificationNumber
	 */
	public String getSpecificationNumber() {
		return specificationNumber;
	}
	/**
	 * @param specificationNumber the specificationNumber to set
	 */
	public void setSpecificationNumber(String specificationNumber) {
		this.specificationNumber = specificationNumber;
	}
	/**
	 * @return the specVersion
	 */
	public String getSpecVersion() {
		return specVersion;
	}
	/**
	 * @param specVersion the specVersion to set
	 */
	public void setSpecVersion(String specVersion) {
		this.specVersion = specVersion;
	}
	/**
	 * @return the specIssueDate
	 */
	public SpecIssueDate getSpecIssueDate() {
		return specIssueDate;
	}
	/**
	 * @param specIssueDate the specIssueDate to set
	 */
	public void setSpecIssueDate(SpecIssueDate specIssueDate) {
		this.specIssueDate = specIssueDate;
	}
	/**
	 * @return the specGroupingID
	 */
	public String getSpecGroupingID() {
		return specGroupingID;
	}
	/**
	 * @param specGroupingID the specGroupingID to set
	 */
	public void setSpecGroupingID(String specGroupingID) {
		this.specGroupingID = specGroupingID;
	}
	/**
	 * @return the specReferenceList
	 */
	public ArrayList getSpecReferenceList() {
		return specReferenceList;
	}
	/**
	 * @param specReferenceList the specReferenceList to set
	 */
	public void setSpecReferenceList(ArrayList specReferenceList) {
		this.specReferenceList = specReferenceList;
	}
}
