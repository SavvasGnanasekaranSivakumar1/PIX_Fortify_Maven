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
 * Title		: 	SpecAssembly.java
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
 * SpecAssembly is a data transfer object to store the 
 * Specification Assembly details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecAssembly implements java.io.Serializable {
	private static final long serialVersionUID = -817102407291826259L;
	
	private ProdIdSpecAssembly prodIdSpecAssembly = null;
	private ArrayList pdSpecAssemblyList		  = null;
	private BillOfMaterials	bfMaterials		  	  = null;
	private HtSpecAssembly htSpecAssembly		  = null;
	private LngSpecAssembly lngSpecAssembly		  = null;
	private WidSpecAssembly widSpecAssembly		  = null;
	private ArrayList assemblyInstList			  = null;
	/**
	 * Default constructor.
	 */
	public SpecAssembly() {
		super();
	}
	/**
	 * @return the prodIdSpecAssembly
	 */
	public ProdIdSpecAssembly getProdIdSpecAssembly() {
		return prodIdSpecAssembly;
	}
	/**
	 * @param prodIdSpecAssembly the prodIdSpecAssembly to set
	 */
	public void setProdIdSpecAssembly(ProdIdSpecAssembly prodIdSpecAssembly) {
		this.prodIdSpecAssembly = prodIdSpecAssembly;
	}
	/**
	 * @return the pdSpecAssemblyList
	 */
	public ArrayList getPdSpecAssemblyList() {
		return pdSpecAssemblyList;
	}
	/**
	 * @param pdSpecAssemblyList the pdSpecAssemblyList to set
	 */
	public void setPdSpecAssemblyList(ArrayList pdSpecAssemblyList) {
		this.pdSpecAssemblyList = pdSpecAssemblyList;
	}
	/**
	 * @return the bfMaterials
	 */
	public BillOfMaterials getBfMaterials() {
		return bfMaterials;
	}
	/**
	 * @param bfMaterials the bfMaterials to set
	 */
	public void setBfMaterials(BillOfMaterials bfMaterials) {
		this.bfMaterials = bfMaterials;
	}
	/**
	 * @return the htSpecAssembly
	 */
	public HtSpecAssembly getHtSpecAssembly() {
		return htSpecAssembly;
	}
	/**
	 * @param htSpecAssembly the htSpecAssembly to set
	 */
	public void setHtSpecAssembly(HtSpecAssembly htSpecAssembly) {
		this.htSpecAssembly = htSpecAssembly;
	}
	/**
	 * @return the lngSpecAssembly
	 */
	public LngSpecAssembly getLngSpecAssembly() {
		return lngSpecAssembly;
	}
	/**
	 * @param lngSpecAssembly the lngSpecAssembly to set
	 */
	public void setLngSpecAssembly(LngSpecAssembly lngSpecAssembly) {
		this.lngSpecAssembly = lngSpecAssembly;
	}
	/**
	 * @return the widSpecAssembly
	 */
	public WidSpecAssembly getWidSpecAssembly() {
		return widSpecAssembly;
	}
	/**
	 * @param widSpecAssembly the widSpecAssembly to set
	 */
	public void setWidSpecAssembly(WidSpecAssembly widSpecAssembly) {
		this.widSpecAssembly = widSpecAssembly;
	}
	/**
	 * @return the assemblyInstList
	 */
	public ArrayList getAssemblyInstList() {
		return assemblyInstList;
	}
	/**
	 * @param assemblyInstList the assemblyInstList to set
	 */
	public void setAssemblyInstList(ArrayList assemblyInstList) {
		this.assemblyInstList = assemblyInstList;
	}
}
