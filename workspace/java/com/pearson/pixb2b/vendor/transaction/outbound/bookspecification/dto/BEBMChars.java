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
 * Title		: 	BEBMChars.java
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
 * BEBMChars is a data transfer object to store the Binding Extras BindingMaterial  
 * Characteristics details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BEBMChars implements java.io.Serializable {
	private static final long serialVersionUID = 5569541784783610010L;
	
	private String finishType 			= null;
	private String bmDesp 				= null;
	private BEBMCBasisWgt basisWgt		= null;
	private BEBMCColCode bmcColCode		= null;
	private String colDesp				= null;
	private ArrayList bebmcProdIdList	= null;
	/**
	 * Default constructor.
	 */
	public BEBMChars() {
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
	public BEBMCBasisWgt getBasisWgt() {
		return basisWgt;
	}
	/**
	 * @param basisWgt the basisWgt to set
	 */
	public void setBasisWgt(BEBMCBasisWgt basisWgt) {
		this.basisWgt = basisWgt;
	}
	/**
	 * @return the bmcColCode
	 */
	public BEBMCColCode getBmcColCode() {
		return bmcColCode;
	}
	/**
	 * @param bmcColCode the bmcColCode to set
	 */
	public void setBmcColCode(BEBMCColCode bmcColCode) {
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
	 * @return the bebmcProdIdList
	 */
	public ArrayList getBebmcProdIdList() {
		return bebmcProdIdList;
	}
	/**
	 * @param bebmcProdIdList the bebmcProdIdList to set
	 */
	public void setBebmcProdIdList(ArrayList bebmcProdIdList) {
		this.bebmcProdIdList = bebmcProdIdList;
	}
}
