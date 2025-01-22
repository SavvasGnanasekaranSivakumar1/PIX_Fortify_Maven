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
 * Title		: 	ISHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   24 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

import java.util.ArrayList;



/**
 * ISHeader is a data transfer object to store the 
 *I S details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISHeader implements java.io.Serializable {	
	private static final long serialVersionUID = 4004073307844513879L;
	
    private ISIssueDate isdate = null;
    private String inventoryStatusNumber = null;
    private LocationParty locationParty = null;
    private ReceiverParty receiverParty = null;
    private ArrayList isRef = null;

	/**
	 * Default constructor.
	 */
	public ISHeader() {
		super();
		locationParty = new LocationParty();
		isRef = new ArrayList();
	}

	/**
	 * @return Returns the isdate.
	 */
	public ISIssueDate getIsdate() {
		return isdate;
	}

	/**
	 * @param isdate The isdate to set.
	 */
	public void setIsdate(ISIssueDate isdate) {
		this.isdate = isdate;
	}

	/**
	 * @return Returns the inventoryStatusNumber.
	 */
	public String getInventoryStatusNumber() {
		return inventoryStatusNumber;
	}

	/**
	 * @param inventoryStatusNumber The inventoryStatusNumber to set.
	 */
	public void setInventoryStatusNumber(String inventoryStatusNumber) {
		this.inventoryStatusNumber = inventoryStatusNumber;
	}

	

	/**
	 * @return Returns the locationParty.
	 */
	public LocationParty getLocationParty() {
		return locationParty;
	}

	/**
	 * @param locationParty The locationParty to set.
	 */
	public void setLocationParty(LocationParty locationParty) {
		this.locationParty = locationParty;
	}

	/**
	 * @return Returns the receiverParty.
	 */
	public ReceiverParty getReceiverParty() {
		return receiverParty;
	}

	/**
	 * @param receiverParty The receiverParty to set.
	 */
	public void setReceiverParty(ReceiverParty receiverParty) {
		this.receiverParty = receiverParty;
	}

	/**
	 * @return Returns the isRef.
	 */
	public ArrayList getIsRef() {
		return isRef;
	}

	/**
	 * @param isRef The isRef to set.
	 */
	public void setIsRef(ArrayList isRef) {
		this.isRef = isRef;
	}
	
	
		
}
