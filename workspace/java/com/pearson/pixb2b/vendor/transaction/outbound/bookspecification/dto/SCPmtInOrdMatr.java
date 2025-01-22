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
 * Title		: 	SCPmtInOrdMatr.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SCPmtInOrdMatr is a data transfer object to store the Specification Component 
 * Placement InOrder Of Matter details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCPmtInOrdMatr implements java.io.Serializable {
	private static final long serialVersionUID = -3646554756609095362L;
	
	private String placementLoc		= null;
	private String placementRef		= null;
	private String relativeLoc		= null;
	private String placementSeq		= null;
	private ArrayList omAddtextList	= null;
	/**
	 * Default constructor.
	 */
	public SCPmtInOrdMatr() {
		super();
	}
	/**
	 * @return the placementLoc
	 */
	public String getPlacementLoc() {
		return placementLoc;
	}
	/**
	 * @param placementLoc the placementLoc to set
	 */
	public void setPlacementLoc(String placementLoc) {
		this.placementLoc = placementLoc;
	}
	/**
	 * @return the placementRef
	 */
	public String getPlacementRef() {
		return placementRef;
	}
	/**
	 * @param placementRef the placementRef to set
	 */
	public void setPlacementRef(String placementRef) {
		this.placementRef = placementRef;
	}
	/**
	 * @return the relativeLoc
	 */
	public String getRelativeLoc() {
		return relativeLoc;
	}
	/**
	 * @param relativeLoc the relativeLoc to set
	 */
	public void setRelativeLoc(String relativeLoc) {
		this.relativeLoc = relativeLoc;
	}
	/**
	 * @return the placementSeq
	 */
	public String getPlacementSeq() {
		return placementSeq;
	}
	/**
	 * @param placementSeq the placementSeq to set
	 */
	public void setPlacementSeq(String placementSeq) {
		this.placementSeq = placementSeq;
	}
	/**
	 * @return the omAddtextList
	 */
	public ArrayList getOmAddtextList() {
		return omAddtextList;
	}
	/**
	 * @param omAddtextList the omAddtextList to set
	 */
	public void setOmAddtextList(ArrayList omAddtextList) {
		this.omAddtextList = omAddtextList;
	}
}
