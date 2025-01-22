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
 * Title		: 	ESRIBMaterialChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ESRIBMaterialChars is a data transfer object to store the Endsheet Reinforcement Binding  
 * Material Characteristics details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESRIBMaterialChars implements java.io.Serializable {
	private static final long serialVersionUID = -4961792140354153826L;
	
	private String finishType 			= null;
	private String bmDesp 				= null;
	private ESRIBMCBasisWgt basisWgt	= null;
	private ESRIBMCColCode bmcColCode	= null;
	private String colDesp				= null;
	private ArrayList esribmProdIdList	= null;
	/**
	 * Default constructor.
	 */
	public ESRIBMaterialChars() {
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
	public ESRIBMCBasisWgt getBasisWgt() {
		return basisWgt;
	}
	/**
	 * @param basisWgt the basisWgt to set
	 */
	public void setBasisWgt(ESRIBMCBasisWgt basisWgt) {
		this.basisWgt = basisWgt;
	}
	/**
	 * @return the bmcColCode
	 */
	public ESRIBMCColCode getBmcColCode() {
		return bmcColCode;
	}
	/**
	 * @param bmcColCode the bmcColCode to set
	 */
	public void setBmcColCode(ESRIBMCColCode bmcColCode) {
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
	 * @return the esribmProdIdList
	 */
	public ArrayList getEsribmProdIdList() {
		return esribmProdIdList;
	}
	/**
	 * @param esribmProdIdList the esribmProdIdList to set
	 */
	public void setEsribmProdIdList(ArrayList esribmProdIdList) {
		this.esribmProdIdList = esribmProdIdList;
	}
}
