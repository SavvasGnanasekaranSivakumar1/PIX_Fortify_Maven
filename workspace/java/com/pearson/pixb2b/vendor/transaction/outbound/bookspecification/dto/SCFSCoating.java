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
 * Title		: 	SCFSCoating.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSCoating is a data transfer object to store the Specification Component 
 * Supplied Component FinishSpecs Coating details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSCoating implements java.io.Serializable {
	private static final long serialVersionUID = 4879994246931640987L;
	
	private String coatCovType	= null;
	private String coatLocType	= null;
	private String finishType	= null;
	private String coatingVal	= null;
	/**
	 * Default constructor.
	 */
	public SCFSCoating() {
		super();
	}
	/**
	 * @return the coatCovType
	 */
	public String getCoatCovType() {
		return coatCovType;
	}
	/**
	 * @param coatCovType the coatCovType to set
	 */
	public void setCoatCovType(String coatCovType) {
		this.coatCovType = coatCovType;
	}
	/**
	 * @return the coatLocType
	 */
	public String getCoatLocType() {
		return coatLocType;
	}
	/**
	 * @param coatLocType the coatLocType to set
	 */
	public void setCoatLocType(String coatLocType) {
		this.coatLocType = coatLocType;
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
	 * @return the coatingVal
	 */
	public String getCoatingVal() {
		return coatingVal;
	}
	/**
	 * @param coatingVal the coatingVal to set
	 */
	public void setCoatingVal(String coatingVal) {
		this.coatingVal = coatingVal;
	}
}
