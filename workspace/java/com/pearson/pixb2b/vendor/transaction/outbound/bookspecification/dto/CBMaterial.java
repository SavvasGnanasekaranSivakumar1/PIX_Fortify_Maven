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
 * Title		: 	CBMaterial.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * CBMaterial is a data transfer object to store the specification Binding   
 * CaseMaking CaseBoard Materials Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CBMaterial implements java.io.Serializable {
	private static final long serialVersionUID = 1077674748617654785L;
	
	private String cbType 			= null;
	private String clType			= null;
	private Caliper caliper			= null;
	private ArrayList cbAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public CBMaterial() {
		super();
	}
	/**
	 * @return the cbType
	 */
	public String getCbType() {
		return cbType;
	}
	/**
	 * @param cbType the cbType to set
	 */
	public void setCbType(String cbType) {
		this.cbType = cbType;
	}
	/**
	 * @return the clType
	 */
	public String getClType() {
		return clType;
	}
	/**
	 * @param clType the clType to set
	 */
	public void setClType(String clType) {
		this.clType = clType;
	}
	/**
	 * @return the caliper
	 */
	public Caliper getCaliper() {
		return caliper;
	}
	/**
	 * @param caliper the caliper to set
	 */
	public void setCaliper(Caliper caliper) {
		this.caliper = caliper;
	}
	/**
	 * @return the cbAddTextList
	 */
	public ArrayList getCbAddTextList() {
		return cbAddTextList;
	}
	/**
	 * @param cbAddTextList the cbAddTextList to set
	 */
	public void setCbAddTextList(ArrayList cbAddTextList) {
		this.cbAddTextList = cbAddTextList;
	}
}
