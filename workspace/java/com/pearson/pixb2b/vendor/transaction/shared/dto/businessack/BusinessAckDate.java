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
 * Title		: 	BusinessAckDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;
/**
 * BusinessAckDate is a helper DTO to store the BusinessAcknowledgementDate 
 * 
 * @author Yogesh Tyagi
 */
public class BusinessAckDate implements java.io.Serializable {
	private static final long serialVersionUID = -5845488203897265476L;
	
	private BusinessAckDateDate businessAckDateDate	= null;
	private String businessAckDateTime	= null;	
	/**
	 * Default constructor.
	 */
	public BusinessAckDate() {
		super();
	}
	/**
	 * @return Returns the businessAckDateDate.
	 */
	public BusinessAckDateDate getBusinessAckDateDate() {
		return businessAckDateDate;
	}
	/**
	 * @param businessAckDateDate The businessAckDateDate to set.
	 */
	public void setBusinessAckDateDate(BusinessAckDateDate businessAckDateDate) {
		this.businessAckDateDate = businessAckDateDate;
	}
	/**
	 * @return Returns the businessAckDateTime.
	 */
	public String getBusinessAckDateTime() {
		return businessAckDateTime;
	}
	/**
	 * @param businessAckDateTime The businessAckDateTime to set.
	 */
	public void setBusinessAckDateTime(String businessAckDateTime) {
		this.businessAckDateTime = businessAckDateTime;
	}
}