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
 * Title		: 	ICHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

import java.util.ArrayList;
/**
 * ICHeader is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam, Yogesh Tyagi
 */
public class ICHeader implements java.io.Serializable {
	private static final long serialVersionUID = 850742602191141348L;
	
	private ICIssueDate icdate 		= null;
    private String invChangeNumber 	= null;
    private ICReason icReason		= null;
    private ICLocationParty locationParty = null;    
    private ArrayList icRef 		= null;
	/**
	 * Default constructor.
	 */
	public ICHeader() {
		super();
		locationParty = new ICLocationParty();
		icRef = new ArrayList();
	}
	/**
	 * @return the icdate
	 */
	public ICIssueDate getIcdate() {
		return icdate;
	}
	/**
	 * @param icdate the icdate to set
	 */
	public void setIcdate(ICIssueDate icdate) {
		this.icdate = icdate;
	}
	/**
	 * @return the invChangeNumber
	 */
	public String getInvChangeNumber() {
		return invChangeNumber;
	}
	/**
	 * @param invChangeNumber the invChangeNumber to set
	 */
	public void setInvChangeNumber(String invChangeNumber) {
		this.invChangeNumber = invChangeNumber;
	}
	/**
	 * @return the icReason
	 */
	public ICReason getIcReason() {
		return icReason;
	}
	/**
	 * @param icReason the icReason to set
	 */
	public void setIcReason(ICReason icReason) {
		this.icReason = icReason;
	}
	/**
	 * @return the locationParty
	 */
	public ICLocationParty getLocationParty() {
		return locationParty;
	}
	/**
	 * @param locationParty the locationParty to set
	 */
	public void setLocationParty(ICLocationParty locationParty) {
		this.locationParty = locationParty;
	}
	/**
	 * @return the icRef
	 */
	public ArrayList getIcRef() {
		return icRef;
	}
	/**
	 * @param icRef the icRef to set
	 */
	public void setIcRef(ArrayList icRef) {
		this.icRef = icRef;
	}
}
