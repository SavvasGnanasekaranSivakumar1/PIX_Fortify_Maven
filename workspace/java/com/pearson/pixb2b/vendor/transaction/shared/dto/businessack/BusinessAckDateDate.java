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
 * Title		: 	BusinessAckDateDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	10 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.businessack;
/**
 * BusinessAckDateDate is a helper DTO to store the 
 * BusinessAcknowledgementDate Date. 
 * 
 * @author Yogesh Tyagi
 */
public class BusinessAckDateDate implements java.io.Serializable {
	private static final long serialVersionUID = 4676020014888342919L;
	
	private String businessAckDateYear	= null;
	private String businessAckDateMonth	= null;
	private String businessAckDateDay	= null;
	/**
	 * Default constructor.
	 */
	public BusinessAckDateDate() {
		super();
	}
	/**
	 * @return Returns the businessAckDateDay.
	 */
	public String getBusinessAckDateDay() {
		return businessAckDateDay;
	}
	/**
	 * @param businessAckDateDay The businessAckDateDay to set.
	 */
	public void setBusinessAckDateDay(String businessAckDateDay) {
		this.businessAckDateDay = businessAckDateDay;
	}
	/**
	 * @return Returns the businessAckDateMonth.
	 */
	public String getBusinessAckDateMonth() {
		return businessAckDateMonth;
	}
	/**
	 * @param businessAckDateMonth The businessAckDateMonth to set.
	 */
	public void setBusinessAckDateMonth(String businessAckDateMonth) {
		this.businessAckDateMonth = businessAckDateMonth;
	}
	/**
	 * @return Returns the businessAckDateYear.
	 */
	public String getBusinessAckDateYear() {
		return businessAckDateYear;
	}
	/**
	 * @param businessAckDateYear The businessAckDateYear to set.
	 */
	public void setBusinessAckDateYear(String businessAckDateYear) {
		this.businessAckDateYear = businessAckDateYear;
	}
}