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
 * Title		: 	PCFSDecSpec.java
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
 * PCFSDecSpec is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications FinishSpecs Decoration details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSDecSpec implements java.io.Serializable {
	private static final long serialVersionUID = 8450003798146854673L;
	
	private String decorType			= null;
	private String decLocType			= null;
	private PCFSColCode pcFSColCode		= null;
	private String colDesp				= null;
	private String numOfHits			= null;
	private PCFSDecCov pcFSDecCov		= null;
	private ArrayList pcFSProdIdList	= null;
	private ArrayList pcFSDAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public PCFSDecSpec() {
		super();
	}
	/**
	 * @return the decorType
	 */
	public String getDecorType() {
		return decorType;
	}
	/**
	 * @param decorType the decorType to set
	 */
	public void setDecorType(String decorType) {
		this.decorType = decorType;
	}
	/**
	 * @return the decLocType
	 */
	public String getDecLocType() {
		return decLocType;
	}
	/**
	 * @param decLocType the decLocType to set
	 */
	public void setDecLocType(String decLocType) {
		this.decLocType = decLocType;
	}
	/**
	 * @return the pcFSColCode
	 */
	public PCFSColCode getPcFSColCode() {
		return pcFSColCode;
	}
	/**
	 * @param pcFSColCode the pcFSColCode to set
	 */
	public void setPcFSColCode(PCFSColCode pcFSColCode) {
		this.pcFSColCode = pcFSColCode;
	}
	/**
	 * @return the colDesp
	 */
	public String getColDesp() {
		return colDesp;
	}
	/**
	 * @param colDesp the colDesp to set
	 */
	public void setColDesp(String colDesp) {
		this.colDesp = colDesp;
	}
	/**
	 * @return the numOfHits
	 */
	public String getNumOfHits() {
		return numOfHits;
	}
	/**
	 * @param numOfHits the numOfHits to set
	 */
	public void setNumOfHits(String numOfHits) {
		this.numOfHits = numOfHits;
	}
	/**
	 * @return the pcFSDecCov
	 */
	public PCFSDecCov getPcFSDecCov() {
		return pcFSDecCov;
	}
	/**
	 * @param pcFSDecCov the pcFSDecCov to set
	 */
	public void setPcFSDecCov(PCFSDecCov pcFSDecCov) {
		this.pcFSDecCov = pcFSDecCov;
	}
	/**
	 * @return the pcFSProdIdList
	 */
	public ArrayList getPcFSProdIdList() {
		return pcFSProdIdList;
	}
	/**
	 * @param pcFSProdIdList the pcFSProdIdList to set
	 */
	public void setPcFSProdIdList(ArrayList pcFSProdIdList) {
		this.pcFSProdIdList = pcFSProdIdList;
	}
	/**
	 * @return the pcFSDAddTextList
	 */
	public ArrayList getPcFSDAddTextList() {
		return pcFSDAddTextList;
	}
	/**
	 * @param pcFSDAddTextList the pcFSDAddTextList to set
	 */
	public void setPcFSDAddTextList(ArrayList pcFSDAddTextList) {
		this.pcFSDAddTextList = pcFSDAddTextList;
	}
}
