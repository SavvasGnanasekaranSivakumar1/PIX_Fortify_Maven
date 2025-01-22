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
 * Title		: 	SpecBinding.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SpecBinding is a data transfer object to store the 
 * Specification Binding details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecBinding implements java.io.Serializable {
	private static final long serialVersionUID = 8362844975828469518L;
	
	private String bindType					 = null;
	private String bindStyType				 = null;
	private String bindCovScoring			 = null;
	private String bindReinf				 = null;
	private String bindBkStyType			 = null;
	private String bindRibbon				 = null;
	private ProdIdSpecBinding prodIdSpecBind = null;
	private ArrayList pdSpecBindingList		 = null;
	private BookBlock bookBlock				 = null;
	private ArrayList endsheetInfoList		 = null;
	private ArrayList bindingExtrasList		 = null;
	private HPInfo hpinfo 					 = null;
	private Case bindCase					 = null;
	private MechMaterial mechMaterial		 = null;
	private ArrayList ribbonList			 = null;
	private Perforation perforation			 = null;
	private ArrayList sbAddTextList 		 = null;
	/**
	 * Default constructor.
	 */
	public SpecBinding() {
		super();
	}
	/**
	 * @return the bindType
	 */
	public String getBindType() {
		return bindType;
	}
	/**
	 * @param bindType the bindType to set
	 */
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	/**
	 * @return the bindStyType
	 */
	public String getBindStyType() {
		return bindStyType;
	}
	/**
	 * @param bindStyType the bindStyType to set
	 */
	public void setBindStyType(String bindStyType) {
		this.bindStyType = bindStyType;
	}
	/**
	 * @return the bindCovScoring
	 */
	public String getBindCovScoring() {
		return bindCovScoring;
	}
	/**
	 * @param bindCovScoring the bindCovScoring to set
	 */
	public void setBindCovScoring(String bindCovScoring) {
		this.bindCovScoring = bindCovScoring;
	}
	/**
	 * @return the bindReinf
	 */
	public String getBindReinf() {
		return bindReinf;
	}
	/**
	 * @param bindReinf the bindReinf to set
	 */
	public void setBindReinf(String bindReinf) {
		this.bindReinf = bindReinf;
	}
	/**
	 * @return the bindBkStyType
	 */
	public String getBindBkStyType() {
		return bindBkStyType;
	}
	/**
	 * @param bindBkStyType the bindBkStyType to set
	 */
	public void setBindBkStyType(String bindBkStyType) {
		this.bindBkStyType = bindBkStyType;
	}
	/**
	 * @return the bindRibbon
	 */
	public String getBindRibbon() {
		return bindRibbon;
	}
	/**
	 * @param bindRibbon the bindRibbon to set
	 */
	public void setBindRibbon(String bindRibbon) {
		this.bindRibbon = bindRibbon;
	}
	/**
	 * @return the prodIdSpecBind
	 */
	public ProdIdSpecBinding getProdIdSpecBind() {
		return prodIdSpecBind;
	}
	/**
	 * @param prodIdSpecBind the prodIdSpecBind to set
	 */
	public void setProdIdSpecBind(ProdIdSpecBinding prodIdSpecBind) {
		this.prodIdSpecBind = prodIdSpecBind;
	}
	/**
	 * @return the pdSpecBindingList
	 */
	public ArrayList getPdSpecBindingList() {
		return pdSpecBindingList;
	}
	/**
	 * @param pdSpecBindingList the pdSpecBindingList to set
	 */
	public void setPdSpecBindingList(ArrayList pdSpecBindingList) {
		this.pdSpecBindingList = pdSpecBindingList;
	}
	/**
	 * @return the bookBlock
	 */
	public BookBlock getBookBlock() {
		return bookBlock;
	}
	/**
	 * @param bookBlock the bookBlock to set
	 */
	public void setBookBlock(BookBlock bookBlock) {
		this.bookBlock = bookBlock;
	}
	/**
	 * @return the endsheetInfoList
	 */
	public ArrayList getEndsheetInfoList() {
		return endsheetInfoList;
	}
	/**
	 * @param endsheetInfoList the endsheetInfoList to set
	 */
	public void setEndsheetInfoList(ArrayList endsheetInfoList) {
		this.endsheetInfoList = endsheetInfoList;
	}
	/**
	 * @return the bindingExtrasList
	 */
	public ArrayList getBindingExtrasList() {
		return bindingExtrasList;
	}
	/**
	 * @param bindingExtrasList the bindingExtrasList to set
	 */
	public void setBindingExtrasList(ArrayList bindingExtrasList) {
		this.bindingExtrasList = bindingExtrasList;
	}
	/**
	 * @return the hpinfo
	 */
	public HPInfo getHpinfo() {
		return hpinfo;
	}
	/**
	 * @param hpinfo the hpinfo to set
	 */
	public void setHpinfo(HPInfo hpinfo) {
		this.hpinfo = hpinfo;
	}
	/**
	 * @return the bindCase
	 */
	public Case getBindCase() {
		return bindCase;
	}
	/**
	 * @param bindCase the bindCase to set
	 */
	public void setBindCase(Case bindCase) {
		this.bindCase = bindCase;
	}
	/**
	 * @return the mechMaterial
	 */
	public MechMaterial getMechMaterial() {
		return mechMaterial;
	}
	/**
	 * @param mechMaterial the mechMaterial to set
	 */
	public void setMechMaterial(MechMaterial mechMaterial) {
		this.mechMaterial = mechMaterial;
	}
	/**
	 * @return the ribbonList
	 */
	public ArrayList getRibbonList() {
		return ribbonList;
	}
	/**
	 * @param ribbonList the ribbonList to set
	 */
	public void setRibbonList(ArrayList ribbonList) {
		this.ribbonList = ribbonList;
	}
	/**
	 * @return the perforation
	 */
	public Perforation getPerforation() {
		return perforation;
	}
	/**
	 * @param perforation the perforation to set
	 */
	public void setPerforation(Perforation perforation) {
		this.perforation = perforation;
	}
	/**
	 * @return the sbAddTextList
	 */
	public ArrayList getSbAddTextList() {
		return sbAddTextList;
	}
	/**
	 * @param sbAddTextList the sbAddTextList to set
	 */
	public void setSbAddTextList(ArrayList sbAddTextList) {
		this.sbAddTextList = sbAddTextList;
	}
}
