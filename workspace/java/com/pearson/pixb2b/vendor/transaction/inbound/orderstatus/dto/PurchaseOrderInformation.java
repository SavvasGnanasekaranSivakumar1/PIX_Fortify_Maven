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
 * Title		: 	PurchaseOrderInformation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

import java.util.ArrayList;

/**
 * PurchaseOrderInformation is a data transfer object to store the 
 * Purchase Order Information and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PurchaseOrderInformation implements java.io.Serializable {
	private static final long serialVersionUID = -7586903083658834778L;
	
	private String poNumber						 = null;
	private String poReleaseNumber				 = null;
	private PurchaseOrderIssuedDate poIssuedDate = null;
	private ArrayList poReferenceList			 = null;
	/**
	 * Default constructor.
	 */
	public PurchaseOrderInformation() {
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
	 * @return the poIssuedDate
	 */
	public PurchaseOrderIssuedDate getPoIssuedDate() {
		return poIssuedDate;
	}
	/**
	 * @param poIssuedDate the poIssuedDate to set
	 */
	public void setPoIssuedDate(PurchaseOrderIssuedDate poIssuedDate) {
		this.poIssuedDate = poIssuedDate;
	}
	/**
	 * @return the poReferenceList
	 */
	public ArrayList getPoReferenceList() {
		return poReferenceList;
	}
	/**
	 * @param poReferenceList the poReferenceList to set
	 */
	public void setPoReferenceList(ArrayList poReferenceList) {
		this.poReferenceList = poReferenceList;
	}
}
