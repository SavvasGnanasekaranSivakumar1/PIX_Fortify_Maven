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
 * Title		: 	ACCasLabChar.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ACCasLabChar is a data transfer object to store the Specification NonPress Component 
 * Audio Cassette Label Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACCasLabChar implements java.io.Serializable {
	private static final long serialVersionUID = 2178575111270832318L;
	
	private String casLabSide		= null;
	private String caseLabType		= null;
	private ACColSpecs acColSpecs 	= null;
	private ArrayList acPrePrepList = null;
	/**
	 * Default constructor.
	 */
	public ACCasLabChar() {
		super();
	}
	/**
	 * @return the casLabSide
	 */
	public String getCasLabSide() {
		return casLabSide;
	}
	/**
	 * @param casLabSide the casLabSide to set
	 */
	public void setCasLabSide(String casLabSide) {
		this.casLabSide = casLabSide;
	}
	/**
	 * @return the caseLabType
	 */
	public String getCaseLabType() {
		return caseLabType;
	}
	/**
	 * @param caseLabType the caseLabType to set
	 */
	public void setCaseLabType(String caseLabType) {
		this.caseLabType = caseLabType;
	}
	/**
	 * @return the acColSpecs
	 */
	public ACColSpecs getAcColSpecs() {
		return acColSpecs;
	}
	/**
	 * @param acColSpecs the acColSpecs to set
	 */
	public void setAcColSpecs(ACColSpecs acColSpecs) {
		this.acColSpecs = acColSpecs;
	}
	/**
	 * @return the acPrePrepList
	 */
	public ArrayList getAcPrePrepList() {
		return acPrePrepList;
	}
	/**
	 * @param acPrePrepList the acPrePrepList to set
	 */
	public void setAcPrePrepList(ArrayList acPrePrepList) {
		this.acPrePrepList = acPrePrepList;
	}
}
