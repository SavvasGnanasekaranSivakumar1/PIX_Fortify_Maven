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
 * Title		: 	PCPrMdSpecs.java
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
 * PCPrMdSpecs is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPrMdSpecs implements java.io.Serializable {
	private static final long serialVersionUID = 7232139225492444939L;
	
	private String perforated 			= null;
	private String scored 				= null;
	private String folded 				= null;
	private String collated 			= null;
	private String roundCornered		= null;
	private String drilled 				= null;
	private String dieCut 				= null;
	private String numbOfSides			= null;
	private String dieCutDesp			= null;
	private PCFlatSz pcFlatSz			= null;
	private PCWrap pcWrap				= null;
	private ArrayList pcPMSAddTextList 	= null;
	/**
	 * Default constructor.
	 */
	public PCPrMdSpecs() {
		super();
	}
	/**
	 * @return the perforated
	 */
	public String getPerforated() {
		return perforated;
	}
	/**
	 * @param perforated the perforated to set
	 */
	public void setPerforated(String perforated) {
		this.perforated = perforated;
	}
	/**
	 * @return the scored
	 */
	public String getScored() {
		return scored;
	}
	/**
	 * @param scored the scored to set
	 */
	public void setScored(String scored) {
		this.scored = scored;
	}
	/**
	 * @return the folded
	 */
	public String getFolded() {
		return folded;
	}
	/**
	 * @param folded the folded to set
	 */
	public void setFolded(String folded) {
		this.folded = folded;
	}
	/**
	 * @return the collated
	 */
	public String getCollated() {
		return collated;
	}
	/**
	 * @param collated the collated to set
	 */
	public void setCollated(String collated) {
		this.collated = collated;
	}
	/**
	 * @return the roundCornered
	 */
	public String getRoundCornered() {
		return roundCornered;
	}
	/**
	 * @param roundCornered the roundCornered to set
	 */
	public void setRoundCornered(String roundCornered) {
		this.roundCornered = roundCornered;
	}
	/**
	 * @return the drilled
	 */
	public String getDrilled() {
		return drilled;
	}
	/**
	 * @param drilled the drilled to set
	 */
	public void setDrilled(String drilled) {
		this.drilled = drilled;
	}
	/**
	 * @return the dieCut
	 */
	public String getDieCut() {
		return dieCut;
	}
	/**
	 * @param dieCut the dieCut to set
	 */
	public void setDieCut(String dieCut) {
		this.dieCut = dieCut;
	}
	/**
	 * @return the numbOfSides
	 */
	public String getNumbOfSides() {
		return numbOfSides;
	}
	/**
	 * @param numbOfSides the numbOfSides to set
	 */
	public void setNumbOfSides(String numbOfSides) {
		this.numbOfSides = numbOfSides;
	}
	/**
	 * @return the dieCutDesp
	 */
	public String getDieCutDesp() {
		return dieCutDesp;
	}
	/**
	 * @param dieCutDesp the dieCutDesp to set
	 */
	public void setDieCutDesp(String dieCutDesp) {
		this.dieCutDesp = dieCutDesp;
	}
	/**
	 * @return the pcFlatSz
	 */
	public PCFlatSz getPcFlatSz() {
		return pcFlatSz;
	}
	/**
	 * @param pcFlatSz the pcFlatSz to set
	 */
	public void setPcFlatSz(PCFlatSz pcFlatSz) {
		this.pcFlatSz = pcFlatSz;
	}
	/**
	 * @return the pcWrap
	 */
	public PCWrap getPcWrap() {
		return pcWrap;
	}
	/**
	 * @param pcWrap the pcWrap to set
	 */
	public void setPcWrap(PCWrap pcWrap) {
		this.pcWrap = pcWrap;
	}
	/**
	 * @return the pcPMSAddTextList
	 */
	public ArrayList getPcPMSAddTextList() {
		return pcPMSAddTextList;
	}
	/**
	 * @param pcPMSAddTextList the pcPMSAddTextList to set
	 */
	public void setPcPMSAddTextList(ArrayList pcPMSAddTextList) {
		this.pcPMSAddTextList = pcPMSAddTextList;
	}
}
