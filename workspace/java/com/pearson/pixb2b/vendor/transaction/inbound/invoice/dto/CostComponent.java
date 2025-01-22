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
 * Title		: 	CostComponent.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   10 Feb,2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;



/**
 * CostComponent is a data transfer object to store the 
 * Invoice details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class CostComponent implements java.io.Serializable {
	
	private static final long serialVersionUID = -9060531535611036754L;
	
	private String costComponentDesc		= null;

	/**
	 * @return Returns the costComponentDesc.
	 */
	public String getCostComponentDesc() {
		return costComponentDesc;
	}

	/**
	 * @param costComponentDesc The costComponentDesc to set.
	 */
	public void setCostComponentDesc(String costComponentDesc) {
		this.costComponentDesc = costComponentDesc;
	}

	
	
	
	

	
	
}
