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
 * Title		: 	ErrorDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.error;
/**
 * ErrorDTO is a data transfer object to store the 
 * error details for B2B Outbound/Inbound transactions.
 * 
 * @author Yogesh Tyagi
 */
public class ErrorDTO implements java.io.Serializable {
	private static final long serialVersionUID = 4921446154307290202L;
	
	private String errorID		= null;
	private String errorCode 	= null;
	private String errorDescription = null; //ADDITIONAL_DESC
	
	private String sourceName	= null;
	private String sourceType	= null;
	
	private String destName		= null;
	private String destType		= null;
	
	private String transRefId_1		= null;
	private String transRefLabel_1	= null;	
	private String transRefId_2		= null;
	private String transRefLabel_2	= null;	
	private String emailFlag 		= null;	
	private String transRefId_3		= null;
	private String transRefLabel_3	= null;	
	private String processTypeInOut= null;
	/**
	 * Default constructor.
	 */
	public ErrorDTO() {
		super();
	}
	/**
	 * @return the errorID
	 */
	public String getErrorID() {
		return errorID;
	}
	/**
	 * @param errorID the errorID to set
	 */
	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}
	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	/**
	 * @return the destName
	 */
	public String getDestName() {
		return destName;
	}
	/**
	 * @param destName the destName to set
	 */
	public void setDestName(String destName) {
		this.destName = destName;
	}
	/**
	 * @return the destType
	 */
	public String getDestType() {
		return destType;
	}
	/**
	 * @param destType the destType to set
	 */
	public void setDestType(String destType) {
		this.destType = destType;
	}
	/**
	 * @return the transRefId_1
	 */
	public String getTransRefId_1() {
		return transRefId_1;
	}
	/**
	 * @param transRefId_1 the transRefId_1 to set
	 */
	public void setTransRefId_1(String transRefId_1) {
		this.transRefId_1 = transRefId_1;
	}
	/**
	 * @return the transRefLabel_1
	 */
	public String getTransRefLabel_1() {
		return transRefLabel_1;
	}
	/**
	 * @param transRefLabel_1 the transRefLabel_1 to set
	 */
	public void setTransRefLabel_1(String transRefLabel_1) {
		this.transRefLabel_1 = transRefLabel_1;
	}
	/**
	 * @return the transRefId_2
	 */
	public String getTransRefId_2() {
		return transRefId_2;
	}
	/**
	 * @param transRefId_2 the transRefId_2 to set
	 */
	public void setTransRefId_2(String transRefId_2) {
		this.transRefId_2 = transRefId_2;
	}
	/**
	 * @return the transRefLabel_2
	 */
	public String getTransRefLabel_2() {
		return transRefLabel_2;
	}
	/**
	 * @param transRefLabel_2 the transRefLabel_2 to set
	 */
	public void setTransRefLabel_2(String transRefLabel_2) {
		this.transRefLabel_2 = transRefLabel_2;
	}
	/**
	 * @return the emailFlag
	 */
	public String getEmailFlag() {
		return emailFlag;
	}
	/**
	 * @param emailFlag the emailFlag to set
	 */
	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
	/**
	 * @return the processTypeInOut
	 */
	public String getProcessTypeInOut() {
		return processTypeInOut;
	}
	/**
	 * @param processTypeInOut the processTypeInOut to set
	 */
	public void setProcessTypeInOut(String processTypeInOut) {
		this.processTypeInOut = processTypeInOut;
	}
	/**
	 * @return the transRefId_3
	 */
	public String getTransRefId_3() {
		return transRefId_3;
	}
	/**
	 * @param transRefId_3 the transRefId_3 to set
	 */
	public void setTransRefId_3(String transRefId_3) {
		this.transRefId_3 = transRefId_3;
	}
	/**
	 * @return the transRefLabel_3
	 */
	public String getTransRefLabel_3() {
		return transRefLabel_3;
	}
	/**
	 * @param transRefLabel_3 the transRefLabel_3 to set
	 */
	public void setTransRefLabel_3(String transRefLabel_3) {
		this.transRefLabel_3 = transRefLabel_3;
	}
}