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
 * Title		: 	DocumentDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;
/**
 * DocumentDate is a helper DTO object to store the DocumentDate.
 * 
 * @author Yogesh Tyagi
 */
public class DocumentDate implements java.io.Serializable {
	private static final long serialVersionUID = -8532898706290877606L;
	
	private DocumentDateDate documentDateDate = null;
	/**
	 * Default constructor.
	 */
	public DocumentDate() {
		super();
	}	
	/**
	 * @return Returns the documentDateDate.
	 */
	public DocumentDateDate getDocumentDateDate() {
		return documentDateDate;
	}
	/**
	 * @param documentDateDate The documentDateDate to set.
	 */
	public void setDocumentDateDate(DocumentDateDate documentDateDate) {
		this.documentDateDate = documentDateDate;
	}
}