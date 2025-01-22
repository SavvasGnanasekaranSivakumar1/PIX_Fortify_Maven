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
 * Title		: 	RMBMChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * RMBMChars is a data transfer object to store the Ribbon Binding Material   
 * Characteristics Details of SpecBinding and transfer the same between classes
 * 
 * @author Ashish Agrawal
 */
public class RMBMChars implements java.io.Serializable {
	private static final long serialVersionUID = 9122945671965397273L;
	
	private String finishType 			= null;
	private String bmDesp 				= null;
	private RMBMBasisWgt basisWgt		= null;
	private RMBMColCode bmcColCode		= null;
	private String colDesp				= null;
	private ArrayList rmbmProdIdList	= null;
	/**
	 * Default constructor.
	 */
	public RMBMChars() {
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
	 * @return the bmDesp
	 */
	public String getBmDesp() {
		return bmDesp;
	}
	/**
	 * @param bmDesp the bmDesp to set
	 */
	public void setBmDesp(String bmDesp) {
		this.bmDesp = bmDesp;
	}
	/**
	 * @return the basisWgt
	 */
	public RMBMBasisWgt getBasisWgt() {
		return basisWgt;
	}
	/**
	 * @param basisWgt the basisWgt to set
	 */
	public void setBasisWgt(RMBMBasisWgt basisWgt) {
		this.basisWgt = basisWgt;
	}
	/**
	 * @return the bmcColCode
	 */
	public RMBMColCode getBmcColCode() {
		return bmcColCode;
	}
	/**
	 * @param bmcColCode the bmcColCode to set
	 */
	public void setBmcColCode(RMBMColCode bmcColCode) {
		this.bmcColCode = bmcColCode;
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
	 * @return the rmbmProdIdList
	 */
	public ArrayList getRmbmProdIdList() {
		return rmbmProdIdList;
	}
	/**
	 * @param rmbmProdIdList the rmbmProdIdList to set
	 */
	public void setRmbmProdIdList(ArrayList rmbmProdIdList) {
		this.rmbmProdIdList = rmbmProdIdList;
	}
}
