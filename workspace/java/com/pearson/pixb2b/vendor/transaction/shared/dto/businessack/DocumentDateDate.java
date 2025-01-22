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
 * Title		: 	DocumentDateDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;
/**
 * DocumentDateDate is a helper DTO object to store the DocumentDate Date.
 * 
 * @author Yogesh Tyagi
 */
public class DocumentDateDate implements java.io.Serializable {
	private static final long serialVersionUID = -8725633409934768589L;
	
	private String documentDateYear		= null;
	private String documentDateMonth	= null;
	private String documentDateDay		= null;
	
	/**
	 * Default constructor.
	 */
	public DocumentDateDate() {
		super();
	}	
	/**
	 * @return Returns the documentDateDay.
	 */
	public String getDocumentDateDay() {
		return documentDateDay;
	}
	/**
	 * @param documentDateDay The documentDateDay to set.
	 */
	public void setDocumentDateDay(String documentDateDay) {
		this.documentDateDay = documentDateDay;
	}
	/**
	 * @return Returns the documentDateMonth.
	 */
	public String getDocumentDateMonth() {
		return documentDateMonth;
	}
	/**
	 * @param documentDateMonth The documentDateMonth to set.
	 */
	public void setDocumentDateMonth(String documentDateMonth) {
		this.documentDateMonth = documentDateMonth;
	}
	/**
	 * @return Returns the documentDateYear.
	 */
	public String getDocumentDateYear() {
		return documentDateYear;
	}
	/**
	 * @param documentDateYear The documentDateYear to set.
	 */
	public void setDocumentDateYear(String documentDateYear) {
		this.documentDateYear = documentDateYear;
	}
}