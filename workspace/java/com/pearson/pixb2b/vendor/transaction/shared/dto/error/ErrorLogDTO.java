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
 * Title		: 	ErrorLogDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal	18 Mar, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.error;
/**
 * ErrorLogDTO is a data transfer object to store the 
 * error details for B2B Inbound BA transactions.
 * 
 * @author Ashish Agrawal
 */
public class ErrorLogDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1408715402434616574L;
	
	private String errorDescription = null; //ADDITIONAL_DESC
	private String fileName			= null;
	private String poNumber			= null;
	/**
	 * Default constructor.
	 */
	public ErrorLogDTO() {
		super();
	}
	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}
	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
}