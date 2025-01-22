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
 * Title		: 	BillToPartyId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BillToPartyId is a data transfer object to store the DeliveryMessageBook Header 
 * BillToParty Party Identifier details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BillToPartyId implements java.io.Serializable {
	private static final long serialVersionUID = 4840532735547681819L;
	
	private String piType = null;
	private String piValue = null;
	/**
	 * Default constructor.
	 */
	public BillToPartyId() {
		super();
	}
	/**
	 * @return the piType
	 */
	public String getPiType() {
		return piType;
	}
	/**
	 * @param piType the piType to set
	 */
	public void setPiType(String piType) {
		this.piType = piType;
	}
	/**
	 * @return the piValue
	 */
	public String getPiValue() {
		return piValue;
	}
	/**
	 * @param piValue the piValue to set
	 */
	public void setPiValue(String piValue) {
		this.piValue = piValue;
	}
}