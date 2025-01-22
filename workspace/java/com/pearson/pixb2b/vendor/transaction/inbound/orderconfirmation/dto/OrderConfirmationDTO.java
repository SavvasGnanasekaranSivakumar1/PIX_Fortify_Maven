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
 * Title		: 	OrderConfirmationDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;

import java.util.ArrayList;

/**
 * OrderConfirmationDTO is a data transfer object to store the 
 * OrderConfirmation details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class OrderConfirmationDTO implements java.io.Serializable {
	private static final long serialVersionUID = 6811953852297908667L;
	
	private String ocStatusType		= null;
	private OCHeader ocHeader		= null;
	private ArrayList ocLineItem 	= null;
	private OCSummary ocSummary		= null;
	
	/**
	 * Default constructor.
	 */
	public OrderConfirmationDTO() {
		super();
		ocHeader = new OCHeader();
	}
	
	/**
	 * @return Returns the ocHeader.
	 */
	public OCHeader getOcHeader() {
		return ocHeader;
	}
	
	/**
	 * @param ocHeader The ocHeader to set.
	 */
	public void setOcHeader(OCHeader ocHeader) {
		this.ocHeader = ocHeader;
	}
	
	/**
	 * @return Returns the ocSummary.
	 */
	public OCSummary getOcSummary() {
		return ocSummary;
	}
	
	/**
	 * @param ocSummary The ocSummary to set.
	 */
	public void setOcSummary(OCSummary ocSummary) {
		this.ocSummary = ocSummary;
	}

	/**
	 * @return Returns the ocStatusType.
	 */
	public String getOcStatusType() {
		return ocStatusType;
	}

	/**
	 * @param ocStatusType The ocStatusType to set.
	 */
	public void setOcStatusType(String ocStatusType) {
		this.ocStatusType = ocStatusType;
	}

	/**
	 * @return Returns the ocLineItem.
	 */
	public ArrayList getOcLineItem() {
		return ocLineItem;
	}

	/**
	 * @param ocLineItem The ocLineItem to set.
	 */
	public void setOcLineItem(ArrayList ocLineItem) {
		this.ocLineItem = ocLineItem;
	}
	
	
	
}
