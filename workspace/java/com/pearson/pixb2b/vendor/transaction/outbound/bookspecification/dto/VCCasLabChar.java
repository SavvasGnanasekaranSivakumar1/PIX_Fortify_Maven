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
 * Title		: 	VCCasLabChar.java
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
 * VCCasLabChar is a data transfer object to store the Specification NonPress Component 
 * Video Cassette Label Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class VCCasLabChar implements java.io.Serializable {
	private static final long serialVersionUID = -6159968136175823353L;
	
	private String casLabSide		= null;
	private String caseLabType		= null;
	private VCColSpecs vcColSpecs 	= null;
	private ArrayList vcPrePrepList = null;
	/**
	 * Default constructor.
	 */
	public VCCasLabChar() {
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
	 * @return the vcColSpecs
	 */
	public VCColSpecs getVcColSpecs() {
		return vcColSpecs;
	}
	/**
	 * @param vcColSpecs the vcColSpecs to set
	 */
	public void setVcColSpecs(VCColSpecs vcColSpecs) {
		this.vcColSpecs = vcColSpecs;
	}
	/**
	 * @return the vcPrePrepList
	 */
	public ArrayList getVcPrePrepList() {
		return vcPrePrepList;
	}
	/**
	 * @param vcPrePrepList the vcPrePrepList to set
	 */
	public void setVcPrePrepList(ArrayList vcPrePrepList) {
		this.vcPrePrepList = vcPrePrepList;
	}
}
