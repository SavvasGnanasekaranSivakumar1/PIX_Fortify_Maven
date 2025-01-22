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
 * Title		: 	ComponentDueDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;




/**
 * ComponentDueDate is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ComponentDueDate implements java.io.Serializable {
	private static final long serialVersionUID = -641432244505074926L;
	
	private SCDate scDate = null;
	
	/**
	 * Default constructor.
	 */
	public ComponentDueDate() {
		super();
	}

	
	/**
	 * @return Returns the scDate.
	 */
	public SCDate getScDate() {
		return scDate;
	}



	/**
	 * @param scDate The scDate to set.
	 */
	public void setScDate(SCDate scDate) {
		this.scDate = scDate;
	}









	

	
	
	
	
}