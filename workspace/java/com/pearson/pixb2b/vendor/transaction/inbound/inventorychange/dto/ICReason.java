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
 * Title		: 	ICReason.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi   10 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;
/**
 * ICReason is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Yogesh Tyagi
 */
public class ICReason implements java.io.Serializable {
	private static final long serialVersionUID = 6089331733318701155L;
	
	private ICReasonCode icReasonCode = null;
    private String reasonDescription = null;
	/**
	 * Default constructor.
	 */
	public ICReason() {
		super();
	}
	/**
	 * @return the icReasonCode
	 */
	public ICReasonCode getIcReasonCode() {
		return icReasonCode;
	}
	/**
	 * @param icReasonCode the icReasonCode to set
	 */
	public void setIcReasonCode(ICReasonCode icReasonCode) {
		this.icReasonCode = icReasonCode;
	}
	/**
	 * @return the reasonDescription
	 */
	public String getReasonDescription() {
		return reasonDescription;
	}
	/**
	 * @param reasonDescription the reasonDescription to set
	 */
	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}
}