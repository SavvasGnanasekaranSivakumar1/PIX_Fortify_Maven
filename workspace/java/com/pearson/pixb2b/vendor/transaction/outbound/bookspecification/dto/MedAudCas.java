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
 * Title		: 	MedAudCas.java
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
 * MedAudCas is a data transfer object to store the Specification NonPress Component 
 * Media Audio Cassette details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedAudCas implements java.io.Serializable {
	private static final long serialVersionUID = 2209458986902644786L;
	
	private String spineLab				= null;
	private String noiseRed				= null;
	private String dupRatio				= null;
	private String trackType			= null;
	private String tabs					= null;
	private String audCasCaseType		= null;
	private String audCTLengType		= null;
	private String shellType			= null;
	private String assemType			= null;
	private String wrapType				= null;
	private String seqNumber			= null;
	private String shellCol				= null;
	private String tapeDespn			= null;
	private ACMedLeng acMedLeng			= null;
	private ArrayList acCasLabCharList	= null;
	private String acCasCardRef			= null;
	private ArrayList acAddTextList		= null;
	/**
	 * Default constructor.
	 */
	public MedAudCas() {
		super();
	}
	/**
	 * @return the spineLab
	 */
	public String getSpineLab() {
		return spineLab;
	}
	/**
	 * @param spineLab the spineLab to set
	 */
	public void setSpineLab(String spineLab) {
		this.spineLab = spineLab;
	}
	/**
	 * @return the noiseRed
	 */
	public String getNoiseRed() {
		return noiseRed;
	}
	/**
	 * @param noiseRed the noiseRed to set
	 */
	public void setNoiseRed(String noiseRed) {
		this.noiseRed = noiseRed;
	}
	/**
	 * @return the dupRatio
	 */
	public String getDupRatio() {
		return dupRatio;
	}
	/**
	 * @param dupRatio the dupRatio to set
	 */
	public void setDupRatio(String dupRatio) {
		this.dupRatio = dupRatio;
	}
	/**
	 * @return the trackType
	 */
	public String getTrackType() {
		return trackType;
	}
	/**
	 * @param trackType the trackType to set
	 */
	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}
	/**
	 * @return the tabs
	 */
	public String getTabs() {
		return tabs;
	}
	/**
	 * @param tabs the tabs to set
	 */
	public void setTabs(String tabs) {
		this.tabs = tabs;
	}
	/**
	 * @return the audCasCaseType
	 */
	public String getAudCasCaseType() {
		return audCasCaseType;
	}
	/**
	 * @param audCasCaseType the audCasCaseType to set
	 */
	public void setAudCasCaseType(String audCasCaseType) {
		this.audCasCaseType = audCasCaseType;
	}
	/**
	 * @return the audCTLengType
	 */
	public String getAudCTLengType() {
		return audCTLengType;
	}
	/**
	 * @param audCTLengType the audCTLengType to set
	 */
	public void setAudCTLengType(String audCTLengType) {
		this.audCTLengType = audCTLengType;
	}
	/**
	 * @return the shellType
	 */
	public String getShellType() {
		return shellType;
	}
	/**
	 * @param shellType the shellType to set
	 */
	public void setShellType(String shellType) {
		this.shellType = shellType;
	}
	/**
	 * @return the assemType
	 */
	public String getAssemType() {
		return assemType;
	}
	/**
	 * @param assemType the assemType to set
	 */
	public void setAssemType(String assemType) {
		this.assemType = assemType;
	}
	/**
	 * @return the wrapType
	 */
	public String getWrapType() {
		return wrapType;
	}
	/**
	 * @param wrapType the wrapType to set
	 */
	public void setWrapType(String wrapType) {
		this.wrapType = wrapType;
	}
	/**
	 * @return the seqNumber
	 */
	public String getSeqNumber() {
		return seqNumber;
	}
	/**
	 * @param seqNumber the seqNumber to set
	 */
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	/**
	 * @return the shellCol
	 */
	public String getShellCol() {
		return shellCol;
	}
	/**
	 * @param shellCol the shellCol to set
	 */
	public void setShellCol(String shellCol) {
		this.shellCol = shellCol;
	}
	/**
	 * @return the tapeDespn
	 */
	public String getTapeDespn() {
		return tapeDespn;
	}
	/**
	 * @param tapeDespn the tapeDespn to set
	 */
	public void setTapeDespn(String tapeDespn) {
		this.tapeDespn = tapeDespn;
	}
	/**
	 * @return the acMedLeng
	 */
	public ACMedLeng getAcMedLeng() {
		return acMedLeng;
	}
	/**
	 * @param acMedLeng the acMedLeng to set
	 */
	public void setAcMedLeng(ACMedLeng acMedLeng) {
		this.acMedLeng = acMedLeng;
	}
	/**
	 * @return the acCasLabCharList
	 */
	public ArrayList getAcCasLabCharList() {
		return acCasLabCharList;
	}
	/**
	 * @param acCasLabCharList the acCasLabCharList to set
	 */
	public void setAcCasLabCharList(ArrayList acCasLabCharList) {
		this.acCasLabCharList = acCasLabCharList;
	}
	/**
	 * @return the acCasCardRef
	 */
	public String getAcCasCardRef() {
		return acCasCardRef;
	}
	/**
	 * @param acCasCardRef the acCasCardRef to set
	 */
	public void setAcCasCardRef(String acCasCardRef) {
		this.acCasCardRef = acCasCardRef;
	}
	/**
	 * @return the acAddTextList
	 */
	public ArrayList getAcAddTextList() {
		return acAddTextList;
	}
	/**
	 * @param acAddTextList the acAddTextList to set
	 */
	public void setAcAddTextList(ArrayList acAddTextList) {
		this.acAddTextList = acAddTextList;
	}
}
