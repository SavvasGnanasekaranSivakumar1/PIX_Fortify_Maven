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
 * Title		: 	ICReasonCode.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi   10 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;
/**
 * ICReasonCode is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Yogesh Tyagi
 */
public class ICReasonCode implements java.io.Serializable {
	private static final long serialVersionUID = 8270006429364345105L;
	
	private String reasonCodeType = null;
    private String reasonCodeValue = null;
	/**
	 * Default constructor.
	 */
	public ICReasonCode() {
		super();
	}
	/**
	 * @return the reasonCodeType
	 */
	public String getReasonCodeType() {
		return reasonCodeType;
	}
	/**
	 * @param reasonCodeType the reasonCodeType to set
	 */
	public void setReasonCodeType(String reasonCodeType) {
		this.reasonCodeType = reasonCodeType;
	}
	/**
	 * @return the reasonCodeValue
	 */
	public String getReasonCodeValue() {
		return reasonCodeValue;
	}
	/**
	 * @param reasonCodeValue the reasonCodeValue to set
	 */
	public void setReasonCodeValue(String reasonCodeValue) {
		this.reasonCodeValue = reasonCodeValue;
	}
}