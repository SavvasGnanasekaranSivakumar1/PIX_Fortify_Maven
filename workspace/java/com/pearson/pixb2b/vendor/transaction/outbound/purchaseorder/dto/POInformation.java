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
 * Title		: 	PurchaseOrderDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Aug, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;

/**
 * POInformation is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class POInformation implements java.io.Serializable {
	private static final long serialVersionUID = -7167332056002101177L;

	private String poNumber 		= null;
	private String poReleaseNumber 	= null;
	private POIssueDate poIssueDate = null;
	private ArrayList poReference 	= null;
	/**
	 * Default constructor.
	 */
	public POInformation() {
		super();
	}
	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	/**
	 * @return the poReleaseNumber
	 */
	public String getPoReleaseNumber() {
		return poReleaseNumber;
	}
	/**
	 * @param poReleaseNumber the poReleaseNumber to set
	 */
	public void setPoReleaseNumber(String poReleaseNumber) {
		this.poReleaseNumber = poReleaseNumber;
	}
	/**
	 * @return the poIssueDate
	 */
	public POIssueDate getPoIssueDate() {
		return poIssueDate;
	}
	/**
	 * @param poIssueDate the poIssueDate to set
	 */
	public void setPoIssueDate(POIssueDate poIssueDate) {
		this.poIssueDate = poIssueDate;
	}
	/**
	 * @return the poReference
	 */
	public ArrayList getPoReference() {
		return poReference;
	}
	/**
	 * @param poReference the poReference to set
	 */
	public void setPoReference(ArrayList poReference) {
		this.poReference = poReference;
	}
}