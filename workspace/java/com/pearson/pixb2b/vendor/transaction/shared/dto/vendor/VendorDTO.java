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
 * Title		: 	VendorDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi   	8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.vendor;
/**
 * VendorDTO is a data transfer object to store the 
 * Vendor details and transfer the same between classes.
 * 
 * @author Yogesh Tyagi
 */
public class VendorDTO implements java.io.Serializable {
	private static final long serialVersionUID = -8086598903253770365L;
	
	private String vendorSAN 			= null;
	private String vendorName 			= null;
	private String processType 			= null;
	private String processId 			= null;
	private FTPServerInfo ftpServerInfo = null;
	private Integer transactionPriority	= null;
	/**
	 * Default constructor.
	 */
	public VendorDTO() {
		super();
	}
	/**
	 * @return the vendorSAN
	 */
	public String getVendorSAN() {
		return vendorSAN;
	}
	/**
	 * @param vendorSAN the vendorSAN to set
	 */
	public void setVendorSAN(String vendorSAN) {
		this.vendorSAN = vendorSAN;
	}
	/**
	 * @return the vendorName
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName the vendorName to set
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}
	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	/**
	 * @return the processId
	 */
	public String getProcessId() {
		return processId;
	}
	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	/**
	 * @return the ftpServerInfo
	 */
	public FTPServerInfo getFtpServerInfo() {
		return ftpServerInfo;
	}
	/**
	 * @param ftpServerInfo the ftpServerInfo to set
	 */
	public void setFtpServerInfo(FTPServerInfo ftpServerInfo) {
		this.ftpServerInfo = ftpServerInfo;
	}
	public Integer getTransactionPriority() {
		return transactionPriority;
	}
	public void setTransactionPriority(Integer transactionPriority) {
		this.transactionPriority = transactionPriority;
	}
}