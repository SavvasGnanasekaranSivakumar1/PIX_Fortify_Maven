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
 * Title		: 	DateOther.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto;
/**
 * DateOther is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LineDateOther implements java.io.Serializable {
	private static final long serialVersionUID = -2792109093664645816L;
	
	private String lineDateType = null;	
    private LineShipDate lineShipDate = null;
	/**
	 * @return Returns the lineDateType.
	 */
	public String getLineDateType() {
		return lineDateType;
	}
	/**
	 * @param lineDateType The lineDateType to set.
	 */
	public void setLineDateType(String lineDateType) {
		this.lineDateType = lineDateType;
	}
	/**
	 * @return Returns the lineShipDate.
	 */
	public LineShipDate getLineShipDate() {
		return lineShipDate;
	}
	/**
	 * @param lineShipDate The lineShipDate to set.
	 */
	public void setLineShipDate(LineShipDate lineShipDate) {
		this.lineShipDate = lineShipDate;
	}
	/**
	 * Default constructor.
	 */
	public LineDateOther() {
		super();
	}
	
	
}