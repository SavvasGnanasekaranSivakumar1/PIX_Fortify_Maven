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
 * Title		: 	SBDecSpecs.java
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
 * SBDecSpecs is a data transfer object to store the specification Binding Case  
 * Decoration Specs Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SBDecSpecs implements java.io.Serializable {
	private static final long serialVersionUID = 2392720837433582516L;
	
	private String decType 				= null;
	private String decLocType			= null;
	private SBDSColCode colCode			= null;
	private String colDesp				= null;
	private String numOfHits			= null;
	private SBDSDecCovge sbDSDecCovge	= null;
	private	ArrayList sbdsProdIdList	= null;
	private ArrayList sbdsAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public SBDecSpecs() {
		super();
	}
	/**
	 * @return the decType
	 */
	public String getDecType() {
		return decType;
	}
	/**
	 * @param decType the decType to set
	 */
	public void setDecType(String decType) {
		this.decType = decType;
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
	 * @return the colCode
	 */
	public SBDSColCode getColCode() {
		return colCode;
	}
	/**
	 * @param colCode the colCode to set
	 */
	public void setColCode(SBDSColCode colCode) {
		this.colCode = colCode;
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
	 * @return the sbDSDecCovge
	 */
	public SBDSDecCovge getSbDSDecCovge() {
		return sbDSDecCovge;
	}
	/**
	 * @param sbDSDecCovge the sbDSDecCovge to set
	 */
	public void setSbDSDecCovge(SBDSDecCovge sbDSDecCovge) {
		this.sbDSDecCovge = sbDSDecCovge;
	}
	/**
	 * @return the sbdsProdIdList
	 */
	public ArrayList getSbdsProdIdList() {
		return sbdsProdIdList;
	}
	/**
	 * @param sbdsProdIdList the sbdsProdIdList to set
	 */
	public void setSbdsProdIdList(ArrayList sbdsProdIdList) {
		this.sbdsProdIdList = sbdsProdIdList;
	}
	/**
	 * @return the sbdsAddTextList
	 */
	public ArrayList getSbdsAddTextList() {
		return sbdsAddTextList;
	}
	/**
	 * @param sbdsAddTextList the sbdsAddTextList to set
	 */
	public void setSbdsAddTextList(ArrayList sbdsAddTextList) {
		this.sbdsAddTextList = sbdsAddTextList;
	}
}
