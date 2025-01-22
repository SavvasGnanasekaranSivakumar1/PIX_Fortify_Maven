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
 * Title		: 	PCManfSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCManfSpec is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCManfSpec implements java.io.Serializable {
	private static final long serialVersionUID = 964518460113866313L;
	
	private String pressType 		= null;
	private String pressImpType 	= null;
	private PCMargins pcMargins		= null;
	private PCInkCharcs pcInkCharcs	= null;
	private PCFinSpecs pcFinSpecs	= null;
	private PCFinPrep pcFinPrep		= null;
	private PCPrMdSpecs pcPrMdSpecs	= null;
	private ArrayList pcPerforList 	= null;
	private ArrayList pcPrinMatList	= null;
	/**
	 * Default constructor.
	 */
	public PCManfSpec() {
		super();
	}
	/**
	 * @return the pressType
	 */
	public String getPressType() {
		return pressType;
	}
	/**
	 * @param pressType the pressType to set
	 */
	public void setPressType(String pressType) {
		this.pressType = pressType;
	}
	/**
	 * @return the pressImpType
	 */
	public String getPressImpType() {
		return pressImpType;
	}
	/**
	 * @param pressImpType the pressImpType to set
	 */
	public void setPressImpType(String pressImpType) {
		this.pressImpType = pressImpType;
	}
	/**
	 * @return the pcMargins
	 */
	public PCMargins getPcMargins() {
		return pcMargins;
	}
	/**
	 * @param pcMargins the pcMargins to set
	 */
	public void setPcMargins(PCMargins pcMargins) {
		this.pcMargins = pcMargins;
	}
	/**
	 * @return the pcInkCharcs
	 */
	public PCInkCharcs getPcInkCharcs() {
		return pcInkCharcs;
	}
	/**
	 * @param pcInkCharcs the pcInkCharcs to set
	 */
	public void setPcInkCharcs(PCInkCharcs pcInkCharcs) {
		this.pcInkCharcs = pcInkCharcs;
	}
	/**
	 * @return the pcFinSpecs
	 */
	public PCFinSpecs getPcFinSpecs() {
		return pcFinSpecs;
	}
	/**
	 * @param pcFinSpecs the pcFinSpecs to set
	 */
	public void setPcFinSpecs(PCFinSpecs pcFinSpecs) {
		this.pcFinSpecs = pcFinSpecs;
	}
	/**
	 * @return the pcFinPrep
	 */
	public PCFinPrep getPcFinPrep() {
		return pcFinPrep;
	}
	/**
	 * @param pcFinPrep the pcFinPrep to set
	 */
	public void setPcFinPrep(PCFinPrep pcFinPrep) {
		this.pcFinPrep = pcFinPrep;
	}
	/**
	 * @return the pcPrMdSpecs
	 */
	public PCPrMdSpecs getPcPrMdSpecs() {
		return pcPrMdSpecs;
	}
	/**
	 * @param pcPrMdSpecs the pcPrMdSpecs to set
	 */
	public void setPcPrMdSpecs(PCPrMdSpecs pcPrMdSpecs) {
		this.pcPrMdSpecs = pcPrMdSpecs;
	}
	/**
	 * @return the pcPerforList
	 */
	public ArrayList getPcPerforList() {
		return pcPerforList;
	}
	/**
	 * @param pcPerforList the pcPerforList to set
	 */
	public void setPcPerforList(ArrayList pcPerforList) {
		this.pcPerforList = pcPerforList;
	}
	/**
	 * @return the pcPrinMatList
	 */
	public ArrayList getPcPrinMatList() {
		return pcPrinMatList;
	}
	/**
	 * @param pcPrinMatList the pcPrinMatList to set
	 */
	public void setPcPrinMatList(ArrayList pcPrinMatList) {
		this.pcPrinMatList = pcPrinMatList;
	}
}
