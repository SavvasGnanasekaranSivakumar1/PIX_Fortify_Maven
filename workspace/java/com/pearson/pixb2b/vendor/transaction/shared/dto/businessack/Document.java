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
 * Title		: 	Document.java
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
 * Document is a helper DTO object to store the Document usage details.
 * 
 * @author Yogesh Tyagi
 */
public class Document implements java.io.Serializable {
	private static final long serialVersionUID = 3137236992714329696L;
	
	private String documentName			= null;
	private String documentNumber		= null;
	private DocumentDate documentDate	= null;
	private ArrayList documentReferenceList	= null;
	/**
	 * Default constructor.
	 */
	public Document() {
		super();
	}
	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}
	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	/**
	 * @return the documentNumber
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}
	/**
	 * @param documentNumber the documentNumber to set
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	/**
	 * @return the documentDate
	 */
	public DocumentDate getDocumentDate() {
		return documentDate;
	}
	/**
	 * @param documentDate the documentDate to set
	 */
	public void setDocumentDate(DocumentDate documentDate) {
		this.documentDate = documentDate;
	}
	/**
	 * @return the documentReferenceList
	 */
	public ArrayList getDocumentReferenceList() {
		return documentReferenceList;
	}
	/**
	 * @param documentReferenceList the documentReferenceList to set
	 */
	public void setDocumentReferenceList(ArrayList documentReferenceList) {
		this.documentReferenceList = documentReferenceList;
	}
}