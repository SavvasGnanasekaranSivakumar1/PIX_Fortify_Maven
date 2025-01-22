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
 * Title		: 	DocumentError.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;
/**
 * DocumentError is a helper DTO object to store the Document Error.
 * 
 * @author Yogesh Tyagi
 */
public class DocumentError implements java.io.Serializable {
	private static final long serialVersionUID = -7899377657823183714L;
	
	private String errorCode 		= null;
	private String errorDescription = null;
	/**
	 * Default constructor.
	 */
	public DocumentError() {
		super();
	}
	/**
	 * @return Returns the errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return Returns the errorDescription.
	 */
	public String getErrorDescription() {
		return errorDescription;
	}
	/**
	 * @param errorDescription The errorDescription to set.
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
}