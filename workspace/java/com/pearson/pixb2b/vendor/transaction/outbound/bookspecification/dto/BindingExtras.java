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
 * Title		: 	BindingExtras.java
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
 * BindingExtras is a data transfer object to store the Binding 
 * Extra details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BindingExtras implements java.io.Serializable {
	private static final long serialVersionUID = -1195212307746187216L;
	
	private String edgeLocType		= null;
	private String beDesp			= null;
	private BEQuantity beQuantity	= null;
	private BEUnitChar beUnitChar	= null;	
	private ArrayList beBMCharsList	= null;
	private ArrayList beAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public BindingExtras() {
		super();
	}
	/**
	 * @return the edgeLocType
	 */
	public String getEdgeLocType() {
		return edgeLocType;
	}
	/**
	 * @param edgeLocType the edgeLocType to set
	 */
	public void setEdgeLocType(String edgeLocType) {
		this.edgeLocType = edgeLocType;
	}
	/**
	 * @return the beDesp
	 */
	public String getBeDesp() {
		return beDesp;
	}
	/**
	 * @param beDesp the beDesp to set
	 */
	public void setBeDesp(String beDesp) {
		this.beDesp = beDesp;
	}
	/**
	 * @return the beQuantity
	 */
	public BEQuantity getBeQuantity() {
		return beQuantity;
	}
	/**
	 * @param beQuantity the beQuantity to set
	 */
	public void setBeQuantity(BEQuantity beQuantity) {
		this.beQuantity = beQuantity;
	}
	/**
	 * @return the beUnitChar
	 */
	public BEUnitChar getBeUnitChar() {
		return beUnitChar;
	}
	/**
	 * @param beUnitChar the beUnitChar to set
	 */
	public void setBeUnitChar(BEUnitChar beUnitChar) {
		this.beUnitChar = beUnitChar;
	}
	/**
	 * @return the beBMCharsList
	 */
	public ArrayList getBeBMCharsList() {
		return beBMCharsList;
	}
	/**
	 * @param beBMCharsList the beBMCharsList to set
	 */
	public void setBeBMCharsList(ArrayList beBMCharsList) {
		this.beBMCharsList = beBMCharsList;
	}
	/**
	 * @return the beAddTextList
	 */
	public ArrayList getBeAddTextList() {
		return beAddTextList;
	}
	/**
	 * @param beAddTextList the beAddTextList to set
	 */
	public void setBeAddTextList(ArrayList beAddTextList) {
		this.beAddTextList = beAddTextList;
	}
}
