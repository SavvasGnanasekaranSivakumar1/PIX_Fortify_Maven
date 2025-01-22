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
 * Title		: 	SCFSDecSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SCFSDecSpec is a data transfer object to store the Specification Component 
 * Supplied Component FinishSpecs Decoration details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSDecSpec implements java.io.Serializable {
	private static final long serialVersionUID = -5337911341066109277L;
	
	private String decorType			= null;
	private String decLocType			= null;
	private SCFSColCode scFSColCode		= null;
	private String colDesp				= null;
	private String numOfHits			= null;
	private SCFSDecCov scFSDecCov		= null;
	private ArrayList scFSProdIdList	= null;
	private ArrayList scFSDAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public SCFSDecSpec() {
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
	 * @return the scFSColCode
	 */
	public SCFSColCode getScFSColCode() {
		return scFSColCode;
	}
	/**
	 * @param scFSColCode the scFSColCode to set
	 */
	public void setScFSColCode(SCFSColCode scFSColCode) {
		this.scFSColCode = scFSColCode;
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
	 * @return the scFSDecCov
	 */
	public SCFSDecCov getScFSDecCov() {
		return scFSDecCov;
	}
	/**
	 * @param scFSDecCov the scFSDecCov to set
	 */
	public void setScFSDecCov(SCFSDecCov scFSDecCov) {
		this.scFSDecCov = scFSDecCov;
	}
	/**
	 * @return the scFSProdIdList
	 */
	public ArrayList getScFSProdIdList() {
		return scFSProdIdList;
	}
	/**
	 * @param scFSProdIdList the scFSProdIdList to set
	 */
	public void setScFSProdIdList(ArrayList scFSProdIdList) {
		this.scFSProdIdList = scFSProdIdList;
	}
	/**
	 * @return the scFSDAddTextList
	 */
	public ArrayList getScFSDAddTextList() {
		return scFSDAddTextList;
	}
	/**
	 * @param scFSDAddTextList the scFSDAddTextList to set
	 */
	public void setScFSDAddTextList(ArrayList scFSDAddTextList) {
		this.scFSDAddTextList = scFSDAddTextList;
	}
}
