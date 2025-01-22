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
 * Title		: 	PCPMatCharcs.java
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
 * PCPMatCharcs is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printing Material Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMatCharcs implements java.io.Serializable {
	private static final long serialVersionUID = -2975873925280528060L;
	
	private String finishType			= null;
	private PCPMCProdId pcPMCProdId 	= null;
	private ArrayList pcPMCProdDespList	= null;
	private PCPMCBasWgt pcPMCBasWgt	 	= null;
	private PCPMCBgtness pcPMCBgtness	= null;
	private PCPMCCaliper pcPMCCaliper 	= null;
	private PCPMCColSpec pcPMCColSpec	= null;
	private PCPMCOpacity pcPMCOpacity 	= null;
	private PCPMCPPI pcPMCPPI 			= null;
	private PCPMCWidth pcPMCWidth 		= null;
	private PCPMCLength pcPMCLength 	= null;
	private String pcPMCGrainDir 		= null;
	private PCPMCReCharcs pcPMCReCharcs = null;
	/**
	 * Default constructor.
	 */
	public PCPMatCharcs() {
		super();
	}
	/**
	 * @return the finishType
	 */
	public String getFinishType() {
		return finishType;
	}
	/**
	 * @param finishType the finishType to set
	 */
	public void setFinishType(String finishType) {
		this.finishType = finishType;
	}
	/**
	 * @return the pcPMCProdId
	 */
	public PCPMCProdId getPcPMCProdId() {
		return pcPMCProdId;
	}
	/**
	 * @param pcPMCProdId the pcPMCProdId to set
	 */
	public void setPcPMCProdId(PCPMCProdId pcPMCProdId) {
		this.pcPMCProdId = pcPMCProdId;
	}
	/**
	 * @return the pcPMCProdDespList
	 */
	public ArrayList getPcPMCProdDespList() {
		return pcPMCProdDespList;
	}
	/**
	 * @param pcPMCProdDespList the pcPMCProdDespList to set
	 */
	public void setPcPMCProdDespList(ArrayList pcPMCProdDespList) {
		this.pcPMCProdDespList = pcPMCProdDespList;
	}
	/**
	 * @return the pcPMCBasWgt
	 */
	public PCPMCBasWgt getPcPMCBasWgt() {
		return pcPMCBasWgt;
	}
	/**
	 * @param pcPMCBasWgt the pcPMCBasWgt to set
	 */
	public void setPcPMCBasWgt(PCPMCBasWgt pcPMCBasWgt) {
		this.pcPMCBasWgt = pcPMCBasWgt;
	}
	/**
	 * @return the pcPMCBgtness
	 */
	public PCPMCBgtness getPcPMCBgtness() {
		return pcPMCBgtness;
	}
	/**
	 * @param pcPMCBgtness the pcPMCBgtness to set
	 */
	public void setPcPMCBgtness(PCPMCBgtness pcPMCBgtness) {
		this.pcPMCBgtness = pcPMCBgtness;
	}
	/**
	 * @return the pcPMCCaliper
	 */
	public PCPMCCaliper getPcPMCCaliper() {
		return pcPMCCaliper;
	}
	/**
	 * @param pcPMCCaliper the pcPMCCaliper to set
	 */
	public void setPcPMCCaliper(PCPMCCaliper pcPMCCaliper) {
		this.pcPMCCaliper = pcPMCCaliper;
	}
	/**
	 * @return the pcPMCColSpec
	 */
	public PCPMCColSpec getPcPMCColSpec() {
		return pcPMCColSpec;
	}
	/**
	 * @param pcPMCColSpec the pcPMCColSpec to set
	 */
	public void setPcPMCColSpec(PCPMCColSpec pcPMCColSpec) {
		this.pcPMCColSpec = pcPMCColSpec;
	}
	/**
	 * @return the pcPMCOpacity
	 */
	public PCPMCOpacity getPcPMCOpacity() {
		return pcPMCOpacity;
	}
	/**
	 * @param pcPMCOpacity the pcPMCOpacity to set
	 */
	public void setPcPMCOpacity(PCPMCOpacity pcPMCOpacity) {
		this.pcPMCOpacity = pcPMCOpacity;
	}
	/**
	 * @return the pcPMCPPI
	 */
	public PCPMCPPI getPcPMCPPI() {
		return pcPMCPPI;
	}
	/**
	 * @param pcPMCPPI the pcPMCPPI to set
	 */
	public void setPcPMCPPI(PCPMCPPI pcPMCPPI) {
		this.pcPMCPPI = pcPMCPPI;
	}
	/**
	 * @return the pcPMCWidth
	 */
	public PCPMCWidth getPcPMCWidth() {
		return pcPMCWidth;
	}
	/**
	 * @param pcPMCWidth the pcPMCWidth to set
	 */
	public void setPcPMCWidth(PCPMCWidth pcPMCWidth) {
		this.pcPMCWidth = pcPMCWidth;
	}
	/**
	 * @return the pcPMCLength
	 */
	public PCPMCLength getPcPMCLength() {
		return pcPMCLength;
	}
	/**
	 * @param pcPMCLength the pcPMCLength to set
	 */
	public void setPcPMCLength(PCPMCLength pcPMCLength) {
		this.pcPMCLength = pcPMCLength;
	}
	/**
	 * @return the pcPMCGrainDir
	 */
	public String getPcPMCGrainDir() {
		return pcPMCGrainDir;
	}
	/**
	 * @param pcPMCGrainDir the pcPMCGrainDir to set
	 */
	public void setPcPMCGrainDir(String pcPMCGrainDir) {
		this.pcPMCGrainDir = pcPMCGrainDir;
	}
	/**
	 * @return the pcPMCReCharcs
	 */
	public PCPMCReCharcs getPcPMCReCharcs() {
		return pcPMCReCharcs;
	}
	/**
	 * @param pcPMCReCharcs the pcPMCReCharcs to set
	 */
	public void setPcPMCReCharcs(PCPMCReCharcs pcPMCReCharcs) {
		this.pcPMCReCharcs = pcPMCReCharcs;
	}
}
