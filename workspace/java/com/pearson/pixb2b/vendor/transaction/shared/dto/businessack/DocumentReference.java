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
 * Title		: 	DocumentReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;
/**
 * DocumentReference is a helper DTO object to store the DocumentReference.
 * 
 * @author Yogesh Tyagi
 */
public class DocumentReference implements java.io.Serializable {
	private static final long serialVersionUID = -3771573845085633993L;
	
	private String documentReferenceType	= null;
	private String assignedBy				= null;
	private String documentReferenceValue	= null;
	/**
	 * Default constructor.
	 */
	public DocumentReference() {
		super();
	}
	/**
	 * @return the documentReferenceType
	 */
	public String getDocumentReferenceType() {
		return documentReferenceType;
	}
	/**
	 * @param documentReferenceType the documentReferenceType to set
	 */
	public void setDocumentReferenceType(String documentReferenceType) {
		this.documentReferenceType = documentReferenceType;
	}
	/**
	 * @return the assignedBy
	 */
	public String getAssignedBy() {
		return assignedBy;
	}
	/**
	 * @param assignedBy the assignedBy to set
	 */
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	/**
	 * @return the documentReferenceValue
	 */
	public String getDocumentReferenceValue() {
		return documentReferenceValue;
	}
	/**
	 * @param documentReferenceValue the documentReferenceValue to set
	 */
	public void setDocumentReferenceValue(String documentReferenceValue) {
		this.documentReferenceValue = documentReferenceValue;
	}	
}