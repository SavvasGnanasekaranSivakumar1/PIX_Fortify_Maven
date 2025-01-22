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
 * Title		: 	Case.java
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
 * Case is a data transfer object to store the specification Binding   
 * case Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Case implements java.io.Serializable {
	private static final long serialVersionUID = -1275186804039983914L;
	
	private String cloCrossCut			= null;
	private String bindBackType			= null;
	private CaseLining casLining		= null;
	private CaseSupers casSupers		= null;
	private ArrayList headbandList		= null;
	private CaseMaking casMaking		= null;
	private ArrayList sbDecSpecsList	= null;
	private ArrayList caseAddTextList 	= null;
	/**
	 * Default constructor.
	 */
	public Case() {
		super();
	}
	/**
	 * @return the cloCrossCut
	 */
	public String getCloCrossCut() {
		return cloCrossCut;
	}
	/**
	 * @param cloCrossCut the cloCrossCut to set
	 */
	public void setCloCrossCut(String cloCrossCut) {
		this.cloCrossCut = cloCrossCut;
	}
	/**
	 * @return the bindBackType
	 */
	public String getBindBackType() {
		return bindBackType;
	}
	/**
	 * @param bindBackType the bindBackType to set
	 */
	public void setBindBackType(String bindBackType) {
		this.bindBackType = bindBackType;
	}
	/**
	 * @return the casLining
	 */
	public CaseLining getCasLining() {
		return casLining;
	}
	/**
	 * @param casLining the casLining to set
	 */
	public void setCasLining(CaseLining casLining) {
		this.casLining = casLining;
	}
	/**
	 * @return the casSupers
	 */
	public CaseSupers getCasSupers() {
		return casSupers;
	}
	/**
	 * @param casSupers the casSupers to set
	 */
	public void setCasSupers(CaseSupers casSupers) {
		this.casSupers = casSupers;
	}
	/**
	 * @return the headbandList
	 */
	public ArrayList getHeadbandList() {
		return headbandList;
	}
	/**
	 * @param headbandList the headbandList to set
	 */
	public void setHeadbandList(ArrayList headbandList) {
		this.headbandList = headbandList;
	}
	/**
	 * @return the casMaking
	 */
	public CaseMaking getCasMaking() {
		return casMaking;
	}
	/**
	 * @param casMaking the casMaking to set
	 */
	public void setCasMaking(CaseMaking casMaking) {
		this.casMaking = casMaking;
	}
	/**
	 * @return the sbDecSpecsList
	 */
	public ArrayList getSbDecSpecsList() {
		return sbDecSpecsList;
	}
	/**
	 * @param sbDecSpecsList the sbDecSpecsList to set
	 */
	public void setSbDecSpecsList(ArrayList sbDecSpecsList) {
		this.sbDecSpecsList = sbDecSpecsList;
	}
	/**
	 * @return the caseAddTextList
	 */
	public ArrayList getCaseAddTextList() {
		return caseAddTextList;
	}
	/**
	 * @param caseAddTextList the caseAddTextList to set
	 */
	public void setCaseAddTextList(ArrayList caseAddTextList) {
		this.caseAddTextList = caseAddTextList;
	}
}
