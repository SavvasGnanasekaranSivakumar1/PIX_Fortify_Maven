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
 * Title		: 	BookSpecificationDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BookSpecificationDTO is a data transfer object to store the 
 * Book Specification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BookSpecificationDTO implements java.io.Serializable {
	private static final long serialVersionUID = -4894114233534010995L;
	
	private String specType		  		= null;
	private String specStatusType 		= null;
	private String nasta		  		= null;
	private String secure			  	= null;
	private String reIssued		  		= null;
	private String language		  		= null;
	private SpecGeneral specGeneral		= null;
	private ArrayList specComponentList	= null;
	private SpecAssembly specAssembly	= null;
	private SpecBinding	specBinding		= null;
	private ArrayList specPackingList	= null;
	/**
	 * Default constructor.
	 */
	public BookSpecificationDTO() {
		super();
	}
	/**
	 * @return the specStatusType
	 */
	public String getSpecStatusType() {
		return specStatusType;
	}
	/**
	 * @param specStatusType the specStatusType to set
	 */
	public void setSpecStatusType(String specStatusType) {
		this.specStatusType = specStatusType;
	}
	/**
	 * @return the specType
	 */
	public String getSpecType() {
		return specType;
	}
	/**
	 * @param specType the specType to set
	 */
	public void setSpecType(String specType) {
		this.specType = specType;
	}
	/**
	 * @return the nasta
	 */
	public String getNasta() {
		return nasta;
	}
	/**
	 * @param nasta the nasta to set
	 */
	public void setNasta(String nasta) {
		this.nasta = nasta;
	}
	/**
	 * @return the secure
	 */
	public String getSecure() {
		return secure;
	}
	/**
	 * @param secure the secure to set
	 */
	public void setSecure(String secure) {
		this.secure = secure;
	}
	/**
	 * @return the reIssued
	 */
	public String getReIssued() {
		return reIssued;
	}
	/**
	 * @param reIssued the reIssued to set
	 */
	public void setReIssued(String reIssued) {
		this.reIssued = reIssued;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the specGeneral
	 */
	public SpecGeneral getSpecGeneral() {
		return specGeneral;
	}
	/**
	 * @param specGeneral the specGeneral to set
	 */
	public void setSpecGeneral(SpecGeneral specGeneral) {
		this.specGeneral = specGeneral;
	}
	/**
	 * @return the specComponentList
	 */
	public ArrayList getSpecComponentList() {
		return specComponentList;
	}
	/**
	 * @param specComponentList the specComponentList to set
	 */
	public void setSpecComponentList(ArrayList specComponentList) {
		this.specComponentList = specComponentList;
	}
	/**
	 * @return the specAssembly
	 */
	public SpecAssembly getSpecAssembly() {
		return specAssembly;
	}
	/**
	 * @param specAssembly the specAssembly to set
	 */
	public void setSpecAssembly(SpecAssembly specAssembly) {
		this.specAssembly = specAssembly;
	}
	/**
	 * @return the specBinding
	 */
	public SpecBinding getSpecBinding() {
		return specBinding;
	}
	/**
	 * @param specBinding the specBinding to set
	 */
	public void setSpecBinding(SpecBinding specBinding) {
		this.specBinding = specBinding;
	}
	/**
	 * @return the specPackingList
	 */
	public ArrayList getSpecPackingList() {
		return specPackingList;
	}
	/**
	 * @param specPackingList the specPackingList to set
	 */
	public void setSpecPackingList(ArrayList specPackingList) {
		this.specPackingList = specPackingList;
	}
}
