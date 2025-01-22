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
 * Title		: 	PCPerfor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCPerfor is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications Perforation details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPerfor implements java.io.Serializable {
	private static final long serialVersionUID = 6248347765713909469L;
	
	private String perforType 		 	= null;
	private PCPDisFrmEdge pcPDisFrmEdge	= null;
	private ArrayList pcpAddTextList 	= null;
	/**
	 * Default constructor.
	 */
	public PCPerfor() {
		super();
	}
	/**
	 * @return the perforType
	 */
	public String getPerforType() {
		return perforType;
	}
	/**
	 * @param perforType the perforType to set
	 */
	public void setPerforType(String perforType) {
		this.perforType = perforType;
	}
	/**
	 * @return the pcPDisFrmEdge
	 */
	public PCPDisFrmEdge getPcPDisFrmEdge() {
		return pcPDisFrmEdge;
	}
	/**
	 * @param pcPDisFrmEdge the pcPDisFrmEdge to set
	 */
	public void setPcPDisFrmEdge(PCPDisFrmEdge pcPDisFrmEdge) {
		this.pcPDisFrmEdge = pcPDisFrmEdge;
	}
	/**
	 * @return the pcpAddTextList
	 */
	public ArrayList getPcpAddTextList() {
		return pcpAddTextList;
	}
	/**
	 * @param pcpAddTextList the pcpAddTextList to set
	 */
	public void setPcpAddTextList(ArrayList pcpAddTextList) {
		this.pcpAddTextList = pcpAddTextList;
	}
}
