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
 * Title		: 	GdsRecptDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Jan, 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

import java.util.ArrayList;

/**
 * GdsRecptDTO is a data transfer object to store the 
 * Goods Receipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GdsRecptDTO implements java.io.Serializable {		
	private static final long serialVersionUID = -6386064940688186670L;
	
	private String grStatusType = null;
	private String grAcceptance = null;
	private GRHeader grHeader = null;
	private ArrayList grLineItem = null;
	private GRSummary grSummary = null;
	
	/**
	 * Default constructor.
	 */
	public GdsRecptDTO() {
		super();
		grHeader = new GRHeader();		
	}

	/**
	 * @return Returns the grAcceptance.
	 */
	public String getGrAcceptance() {
		return grAcceptance;
	}

	/**
	 * @param grAcceptance The grAcceptance to set.
	 */
	public void setGrAcceptance(String grAcceptance) {
		this.grAcceptance = grAcceptance;
	}

	/**
	 * @return Returns the grHeader.
	 */
	public GRHeader getGrHeader() {
		return grHeader;
	}

	/**
	 * @param grHeader The grHeader to set.
	 */
	public void setGrHeader(GRHeader grHeader) {
		this.grHeader = grHeader;
	}

	/**
	 * @return Returns the grLineItem.
	 */
	public ArrayList getGrLineItem() {
		return grLineItem;
	}

	/**
	 * @param grLineItem The grLineItem to set.
	 */
	public void setGrLineItem(ArrayList grLineItem) {
		this.grLineItem = grLineItem;
	}

	/**
	 * @return Returns the grStatusType.
	 */
	public String getGrStatusType() {
		return grStatusType;
	}

	/**
	 * @param grStatusType The grStatusType to set.
	 */
	public void setGrStatusType(String grStatusType) {
		this.grStatusType = grStatusType;
	}

	/**
	 * @return Returns the grSummary.
	 */
	public GRSummary getGrSummary() {
		return grSummary;
	}

	/**
	 * @param grSummary The grSummary to set.
	 */
	public void setGrSummary(GRSummary grSummary) {
		this.grSummary = grSummary;
	}

		
}
