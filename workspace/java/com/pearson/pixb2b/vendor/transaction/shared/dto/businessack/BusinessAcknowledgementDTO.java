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
 * Title		: 	BusinessAcknowledgementDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;

import java.util.ArrayList;
/**
 * BusinessAcknowledgementDTO is a data transfer object to store the 
 * BusinessAcknowledgementDTO success/failure details and transfer 
 * the same between classes.
 * 
 * @author Yogesh Tyagi
 */
public class BusinessAcknowledgementDTO implements java.io.Serializable {
	private static final long serialVersionUID = -2113047592111366857L;
	
	private String status				= null;
	private String businessAckNumber	= null;
	private BusinessAckDate businessAckDate = null;
	private Document document 			= null;
	private ArrayList documentErrorList	= null;
	/**
	 * Default constructor.
	 */
	public BusinessAcknowledgementDTO() {
		super();
	}
	/**
	 * @return Returns the businessAckDate.
	 */
	public BusinessAckDate getBusinessAckDate() {
		return businessAckDate;
	}
	/**
	 * @param businessAckDate The businessAckDate to set.
	 */
	public void setBusinessAckDate(BusinessAckDate businessAckDate) {
		this.businessAckDate = businessAckDate;
	}
	/**
	 * @return Returns the businessAckNumber.
	 */
	public String getBusinessAckNumber() {
		return businessAckNumber;
	}
	/**
	 * @param businessAckNumber The businessAckNumber to set.
	 */
	public void setBusinessAckNumber(String businessAckNumber) {
		this.businessAckNumber = businessAckNumber;
	}
	/**
	 * @return Returns the document.
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @param document The document to set.
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	/**
	 * @return Returns the documentErrorList.
	 */
	public ArrayList getDocumentErrorList() {
		return documentErrorList;
	}
	/**
	 * @param documentErrorList The documentErrorList to set.
	 */
	public void setDocumentErrorList(ArrayList documentErrorList) {
		this.documentErrorList = documentErrorList;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}