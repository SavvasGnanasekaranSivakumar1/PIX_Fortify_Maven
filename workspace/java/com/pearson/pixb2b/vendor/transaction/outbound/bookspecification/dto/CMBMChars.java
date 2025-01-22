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
 * Title		: 	CMBMChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * CMBMChars is a data transfer object to store the CaseMaterial BindingMaterial 
 * Characteristics details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CMBMChars implements java.io.Serializable {
	private static final long serialVersionUID = 7325302323973517581L;
	
	private String finishType 			= null;
	private String bmDesp 				= null;
	private CMBMCBasisWgt basisWgt		= null;
	private CMBMCColCode bmcColCode		= null;
	private String colDesp				= null;
	private ArrayList cmbmcProdIdList	= null;
	/**
	 * Default constructor.
	 */
	public CMBMChars() {
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
	public CMBMCBasisWgt getBasisWgt() {
		return basisWgt;
	}
	/**
	 * @param basisWgt the basisWgt to set
	 */
	public void setBasisWgt(CMBMCBasisWgt basisWgt) {
		this.basisWgt = basisWgt;
	}
	/**
	 * @return the bmcColCode
	 */
	public CMBMCColCode getBmcColCode() {
		return bmcColCode;
	}
	/**
	 * @param bmcColCode the bmcColCode to set
	 */
	public void setBmcColCode(CMBMCColCode bmcColCode) {
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
	 * @return the cmbmcProdIdList
	 */
	public ArrayList getCmbmcProdIdList() {
		return cmbmcProdIdList;
	}
	/**
	 * @param cmbmcProdIdList the cmbmcProdIdList to set
	 */
	public void setCmbmcProdIdList(ArrayList cmbmcProdIdList) {
		this.cmbmcProdIdList = cmbmcProdIdList;
	}
}
