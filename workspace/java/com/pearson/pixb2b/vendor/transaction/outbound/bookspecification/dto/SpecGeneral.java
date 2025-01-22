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
 * Title		: 	SpecGeneral.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	19 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.shared.dto.PreviousVendorPlant;

/**
 * SpecGeneral is a data transfer object to store the 
 * General Specification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecGeneral implements java.io.Serializable {
	private static final long serialVersionUID = -457087923912713641L;
	
	private SpecInformation specInfo 	= null;
	private String tranHisNumber		= null;
	private SenderParty senderParty  	= null;
	private ReceiverParty receiverParty = null;
	private TrimSize trimSize			= null;
	private String totPageCnt			= null;
	private ArrayList sgAddTextList		= null;
	private PreviousVendorPlant previousVendorPlant = null;
	/**
	 * Default constructor.
	 */
	public SpecGeneral() {
		super();
	}
	/**
	 * @return the specInfo
	 */
	public SpecInformation getSpecInfo() {
		return specInfo;
	}
	/**
	 * @param specInfo the specInfo to set
	 */
	public void setSpecInfo(SpecInformation specInfo) {
		this.specInfo = specInfo;
	}
	/**
	 * @return the tranHisNumber
	 */
	public String getTranHisNumber() {
		return tranHisNumber;
	}
	/**
	 * @param tranHisNumber the tranHisNumber to set
	 */
	public void setTranHisNumber(String tranHisNumber) {
		this.tranHisNumber = tranHisNumber;
	}
	/**
	 * @return the senderParty
	 */
	public SenderParty getSenderParty() {
		return senderParty;
	}
	/**
	 * @param senderParty the senderParty to set
	 */
	public void setSenderParty(SenderParty senderParty) {
		this.senderParty = senderParty;
	}
	/**
	 * @return the receiverParty
	 */
	public ReceiverParty getReceiverParty() {
		return receiverParty;
	}
	/**
	 * @param receiverParty the receiverParty to set
	 */
	public void setReceiverParty(ReceiverParty receiverParty) {
		this.receiverParty = receiverParty;
	}
	/**
	 * @return the trimSize
	 */
	public TrimSize getTrimSize() {
		return trimSize;
	}
	/**
	 * @param trimSize the trimSize to set
	 */
	public void setTrimSize(TrimSize trimSize) {
		this.trimSize = trimSize;
	}
	/**
	 * @return the totPageCnt
	 */
	public String getTotPageCnt() {
		return totPageCnt;
	}
	/**
	 * @param totPageCnt the totPageCnt to set
	 */
	public void setTotPageCnt(String totPageCnt) {
		this.totPageCnt = totPageCnt;
	}
	/**
	 * @return the sgAddTextList
	 */
	public ArrayList getSgAddTextList() {
		return sgAddTextList;
	}
	/**
	 * @param sgAddTextList the sgAddTextList to set
	 */
	public void setSgAddTextList(ArrayList sgAddTextList) {
		this.sgAddTextList = sgAddTextList;
	}
	//For Apollo
	public PreviousVendorPlant getPreviousVendorPlant() {
		return previousVendorPlant;
	}
	public void setPreviousVendorPlant(
			PreviousVendorPlant previousVendorPlant) {
		this.previousVendorPlant = previousVendorPlant;
	}
}
