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
 * Title		: 	Perforation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * Perforation is a data transfer object to store the Perforation  
 * details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Perforation implements java.io.Serializable {
	private static final long serialVersionUID = -3144628081374135340L;
	
	private String perfType 		  = null;
	private DistFromEdge distFromEdge = null;
	private ArrayList pfAddTextList   = null;
	/**
	 * Default constructor.
	 */
	public Perforation() {
		super();
	}
	/**
	 * @return the perfType
	 */
	public String getPerfType() {
		return perfType;
	}
	/**
	 * @param perfType the perfType to set
	 */
	public void setPerfType(String perfType) {
		this.perfType = perfType;
	}
	/**
	 * @return the distFromEdge
	 */
	public DistFromEdge getDistFromEdge() {
		return distFromEdge;
	}
	/**
	 * @param distFromEdge the distFromEdge to set
	 */
	public void setDistFromEdge(DistFromEdge distFromEdge) {
		this.distFromEdge = distFromEdge;
	}
	/**
	 * @return the pfAddTextList
	 */
	public ArrayList getPfAddTextList() {
		return pfAddTextList;
	}
	/**
	 * @param pfAddTextList the pfAddTextList to set
	 */
	public void setPfAddTextList(ArrayList pfAddTextList) {
		this.pfAddTextList = pfAddTextList;
	}
}