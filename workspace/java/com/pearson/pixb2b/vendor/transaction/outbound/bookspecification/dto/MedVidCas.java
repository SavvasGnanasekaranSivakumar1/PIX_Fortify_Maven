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
 * Title		: 	MedVidCas.java
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
 * MedVidCas is a data transfer object to store the Specification NonPress Component 
 * Media Video Cassette details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedVidCas implements java.io.Serializable {
	private static final long serialVersionUID = 8341734301565435873L;
	
	private String vidFormatType		= null;
	private String trackType			= null;
	private String tabs					= null;
	private String vidCasCaseType		= null;
	private String assemType			= null;
	private String wrapType				= null;
	private String seqNumber			= null;
	private String shellCol				= null;
	private String tapeDespn			= null;
	private VCMedLeng vcMedLeng			= null;
	private ArrayList vcCasLabCharList	= null;
	private ArrayList vcInsRefList		= null;
	private ArrayList vcAddTextList		= null;
	/**
	 * Default constructor.
	 */
	public MedVidCas() {
		super();
	}
	/**
	 * @return the vidFormatType
	 */
	public String getVidFormatType() {
		return vidFormatType;
	}
	/**
	 * @param vidFormatType the vidFormatType to set
	 */
	public void setVidFormatType(String vidFormatType) {
		this.vidFormatType = vidFormatType;
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
	 * @return the vidCasCaseType
	 */
	public String getVidCasCaseType() {
		return vidCasCaseType;
	}
	/**
	 * @param vidCasCaseType the vidCasCaseType to set
	 */
	public void setVidCasCaseType(String vidCasCaseType) {
		this.vidCasCaseType = vidCasCaseType;
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
	 * @return the vcMedLeng
	 */
	public VCMedLeng getVcMedLeng() {
		return vcMedLeng;
	}
	/**
	 * @param vcMedLeng the vcMedLeng to set
	 */
	public void setVcMedLeng(VCMedLeng vcMedLeng) {
		this.vcMedLeng = vcMedLeng;
	}
	/**
	 * @return the vcCasLabCharList
	 */
	public ArrayList getVcCasLabCharList() {
		return vcCasLabCharList;
	}
	/**
	 * @param vcCasLabCharList the vcCasLabCharList to set
	 */
	public void setVcCasLabCharList(ArrayList vcCasLabCharList) {
		this.vcCasLabCharList = vcCasLabCharList;
	}
	/**
	 * @return the vcInsRefList
	 */
	public ArrayList getVcInsRefList() {
		return vcInsRefList;
	}
	/**
	 * @param vcInsRefList the vcInsRefList to set
	 */
	public void setVcInsRefList(ArrayList vcInsRefList) {
		this.vcInsRefList = vcInsRefList;
	}
	/**
	 * @return the vcAddTextList
	 */
	public ArrayList getVcAddTextList() {
		return vcAddTextList;
	}
	/**
	 * @param vcAddTextList the vcAddTextList to set
	 */
	public void setVcAddTextList(ArrayList vcAddTextList) {
		this.vcAddTextList = vcAddTextList;
	}
}
