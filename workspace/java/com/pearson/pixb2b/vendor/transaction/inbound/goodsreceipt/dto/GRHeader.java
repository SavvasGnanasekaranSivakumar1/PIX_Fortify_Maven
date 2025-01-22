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
 * Title		: 	GRHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   12 Jan 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

import java.util.ArrayList;
/**
 * GRHeader is a data transfer object to store the 
 * Goods Receipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam, 
 */
public class GRHeader implements java.io.Serializable {
	private static final long serialVersionUID = -4272912786498650522L;	
	
	private String grAcceptType = null;
    private String grNumber 	= null;
    private String delMsgNo = null;
    private ArrayList grRef 		= null;
    private GRIssueDate grIssueDate = null;
    private GRArrivalDate grArrvDate = null;
    private BuyerParty buyerParty = null;
    private ShipToChar shipToChar = null;
   
    
   
	/**
	 * Default constructor.
	 */
	public GRHeader() {
		super();
		grRef = new ArrayList();
		shipToChar = new ShipToChar();
	}

	/**
	 * @return Returns the buyerParty.
	 */
	public BuyerParty getBuyerParty() {
		return buyerParty;
	}

	/**
	 * @param buyerParty The buyerParty to set.
	 */
	public void setBuyerParty(BuyerParty buyerParty) {
		this.buyerParty = buyerParty;
	}

	/**
	 * @return Returns the grArrvDate.
	 */
	public GRArrivalDate getGrArrvDate() {
		return grArrvDate;
	}

	/**
	 * @param grArrvDate The grArrvDate to set.
	 */
	public void setGrArrvDate(GRArrivalDate grArrvDate) {
		this.grArrvDate = grArrvDate;
	}

	/**
	 * @return Returns the grIssueDate.
	 */
	public GRIssueDate getGrIssueDate() {
		return grIssueDate;
	}

	/**
	 * @param grIssueDate The grIssueDate to set.
	 */
	public void setGrIssueDate(GRIssueDate grIssueDate) {
		this.grIssueDate = grIssueDate;
	}

	/**
	 * @return Returns the grNumber.
	 */
	public String getGrNumber() {
		return grNumber;
	}

	/**
	 * @param grNumber The grNumber to set.
	 */
	public void setGrNumber(String grNumber) {
		this.grNumber = grNumber;
	}

	/**
	 * @return Returns the grRef.
	 */
	public ArrayList getGrRef() {
		return grRef;
	}

	/**
	 * @param grRef The grRef to set.
	 */
	public void setGrRef(ArrayList grRef) {
		this.grRef = grRef;
	}

	/**
	 * @return Returns the shipToChar.
	 */
	public ShipToChar getShipToChar() {
		return shipToChar;
	}

	/**
	 * @param shipToChar The shipToChar to set.
	 */
	public void setShipToChar(ShipToChar shipToChar) {
		this.shipToChar = shipToChar;
	}

	/**
	 * @return Returns the delMsgNo.
	 */
	public String getDelMsgNo() {
		return delMsgNo;
	}

	/**
	 * @param delMsgNo The delMsgNo to set.
	 */
	public void setDelMsgNo(String delMsgNo) {
		this.delMsgNo = delMsgNo;
	}

	/**
	 * @return Returns the grAcceptType.
	 */
	public String getGrAcceptType() {
		return grAcceptType;
	}

	/**
	 * @param grAcceptType The grAcceptType to set.
	 */
	public void setGrAcceptType(String grAcceptType) {
		this.grAcceptType = grAcceptType;
	}
	
}
