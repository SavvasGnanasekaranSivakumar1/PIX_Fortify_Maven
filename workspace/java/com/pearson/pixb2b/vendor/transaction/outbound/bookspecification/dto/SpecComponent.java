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
 * Title		: 	SpecComponent.java
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
 * SpecComponent is a data transfer object to store the Specification 
 * Component details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecComponent implements java.io.Serializable {
	private static final long serialVersionUID = 1145925912364558507L;
	
	private SCProdId scProdId 					= null;
	private ArrayList scProdDespList			= null;
	private SCClassifn scClassifn				= null;
	private ArrayList scSubClasnList 			= null;
	private ArrayList scBookClasnList			= null;
	private String numPerProduct				= null;
	private ArrayList scPmtInOrdMatrList 		= null;
	private SCFinidSize scFinidSize				= null;
	private SCSupComFinSpecs SCSupComFinSpecs 	= null;
	private PressComponent pressCompt			= null;
	private NonPressComponent nonPressCompnt	= null;
	private ArrayList scAddTextList				= null;
	private ArrayList scSpecRefList				= null;
	private ArrayList safAEnvInfoList			= null;
	/**
	 * Default constructor.
	 */
	public SpecComponent() {
		super();
	}
	/**
	 * @return the scProdId
	 */
	public SCProdId getScProdId() {
		return scProdId;
	}
	/**
	 * @param scProdId the scProdId to set
	 */
	public void setScProdId(SCProdId scProdId) {
		this.scProdId = scProdId;
	}
	/**
	 * @return the scProdDespList
	 */
	public ArrayList getScProdDespList() {
		return scProdDespList;
	}
	/**
	 * @param scProdDespList the scProdDespList to set
	 */
	public void setScProdDespList(ArrayList scProdDespList) {
		this.scProdDespList = scProdDespList;
	}
	/**
	 * @return the scClassifn
	 */
	public SCClassifn getScClassifn() {
		return scClassifn;
	}
	/**
	 * @param scClassifn the scClassifn to set
	 */
	public void setScClassifn(SCClassifn scClassifn) {
		this.scClassifn = scClassifn;
	}
	/**
	 * @return the scSubClasnList
	 */
	public ArrayList getScSubClasnList() {
		return scSubClasnList;
	}
	/**
	 * @param scSubClasnList the scSubClasnList to set
	 */
	public void setScSubClasnList(ArrayList scSubClasnList) {
		this.scSubClasnList = scSubClasnList;
	}
	/**
	 * @return the scBookClasnList
	 */
	public ArrayList getScBookClasnList() {
		return scBookClasnList;
	}
	/**
	 * @param scBookClasnList the scBookClasnList to set
	 */
	public void setScBookClasnList(ArrayList scBookClasnList) {
		this.scBookClasnList = scBookClasnList;
	}
	/**
	 * @return the numPerProduct
	 */
	public String getNumPerProduct() {
		return numPerProduct;
	}
	/**
	 * @param numPerProduct the numPerProduct to set
	 */
	public void setNumPerProduct(String numPerProduct) {
		this.numPerProduct = numPerProduct;
	}
	/**
	 * @return the scPmtInOrdMatrList
	 */
	public ArrayList getScPmtInOrdMatrList() {
		return scPmtInOrdMatrList;
	}
	/**
	 * @param scPmtInOrdMatrList the scPmtInOrdMatrList to set
	 */
	public void setScPmtInOrdMatrList(ArrayList scPmtInOrdMatrList) {
		this.scPmtInOrdMatrList = scPmtInOrdMatrList;
	}
	/**
	 * @return the scFinidSize
	 */
	public SCFinidSize getScFinidSize() {
		return scFinidSize;
	}
	/**
	 * @param scFinidSize the scFinidSize to set
	 */
	public void setScFinidSize(SCFinidSize scFinidSize) {
		this.scFinidSize = scFinidSize;
	}
	/**
	 * @return the sCSupComFinSpecs
	 */
	public SCSupComFinSpecs getSCSupComFinSpecs() {
		return SCSupComFinSpecs;
	}
	/**
	 * @param supComFinSpecs the sCSupComFinSpecs to set
	 */
	public void setSCSupComFinSpecs(SCSupComFinSpecs supComFinSpecs) {
		SCSupComFinSpecs = supComFinSpecs;
	}
	/**
	 * @return the pressCompt
	 */
	public PressComponent getPressCompt() {
		return pressCompt;
	}
	/**
	 * @param pressCompt the pressCompt to set
	 */
	public void setPressCompt(PressComponent pressCompt) {
		this.pressCompt = pressCompt;
	}
	/**
	 * @return the nonPressCompnt
	 */
	public NonPressComponent getNonPressCompnt() {
		return nonPressCompnt;
	}
	/**
	 * @param nonPressCompnt the nonPressCompnt to set
	 */
	public void setNonPressCompnt(NonPressComponent nonPressCompnt) {
		this.nonPressCompnt = nonPressCompnt;
	}
	/**
	 * @return the scAddTextList
	 */
	public ArrayList getScAddTextList() {
		return scAddTextList;
	}
	/**
	 * @param scAddTextList the scAddTextList to set
	 */
	public void setScAddTextList(ArrayList scAddTextList) {
		this.scAddTextList = scAddTextList;
	}
	/**
	 * @return the scSpecRefList
	 */
	public ArrayList getScSpecRefList() {
		return scSpecRefList;
	}
	/**
	 * @param scSpecRefList the scSpecRefList to set
	 */
	public void setScSpecRefList(ArrayList scSpecRefList) {
		this.scSpecRefList = scSpecRefList;
	}
	/**
	 * @return the safAEnvInfoList
	 */
	public ArrayList getSafAEnvInfoList() {
		return safAEnvInfoList;
	}
	/**
	 * @param safAEnvInfoList the safAEnvInfoList to set
	 */
	public void setSafAEnvInfoList(ArrayList safAEnvInfoList) {
		this.safAEnvInfoList = safAEnvInfoList;
	}
}
