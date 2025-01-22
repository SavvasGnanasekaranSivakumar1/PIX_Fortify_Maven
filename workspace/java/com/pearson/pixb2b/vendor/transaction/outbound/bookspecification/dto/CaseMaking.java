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
 * Title		: 	CaseMaking.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * CaseMaking is a data transfer object to store the specification Binding   
 * CaseMaking Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseMaking implements java.io.Serializable {
	private static final long serialVersionUID = -1786650888344929109L;
	
	private String casType				= null;
	private String cfFoot 				= null;
	private CaseShow casShow 			= null;
	private CaseOversize coSize 		= null;
	private ArrayList cbMaterialList	= null;
	private ArrayList caseMaterialList	= null;
	/**
	 * Default constructor.
	 */
	public CaseMaking() {
		super();
	}
	/**
	 * @return the casType
	 */
	public String getCasType() {
		return casType;
	}
	/**
	 * @param casType the casType to set
	 */
	public void setCasType(String casType) {
		this.casType = casType;
	}
	/**
	 * @return the cfFoot
	 */
	public String getCfFoot() {
		return cfFoot;
	}
	/**
	 * @param cfFoot the cfFoot to set
	 */
	public void setCfFoot(String cfFoot) {
		this.cfFoot = cfFoot;
	}
	/**
	 * @return the casShow
	 */
	public CaseShow getCasShow() {
		return casShow;
	}
	/**
	 * @param casShow the casShow to set
	 */
	public void setCasShow(CaseShow casShow) {
		this.casShow = casShow;
	}
	/**
	 * @return the coSize
	 */
	public CaseOversize getCoSize() {
		return coSize;
	}
	/**
	 * @param coSize the coSize to set
	 */
	public void setCoSize(CaseOversize coSize) {
		this.coSize = coSize;
	}
	/**
	 * @return the cbMaterialList
	 */
	public ArrayList getCbMaterialList() {
		return cbMaterialList;
	}
	/**
	 * @param cbMaterialList the cbMaterialList to set
	 */
	public void setCbMaterialList(ArrayList cbMaterialList) {
		this.cbMaterialList = cbMaterialList;
	}
	/**
	 * @return the caseMaterialList
	 */
	public ArrayList getCaseMaterialList() {
		return caseMaterialList;
	}
	/**
	 * @param caseMaterialList the caseMaterialList to set
	 */
	public void setCaseMaterialList(ArrayList caseMaterialList) {
		this.caseMaterialList = caseMaterialList;
	}
}
