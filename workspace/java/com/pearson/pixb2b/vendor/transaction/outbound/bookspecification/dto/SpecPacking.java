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
 * Title		: 	SpecPacking.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SpecPacking is a data transfer object to store the Book 
 * Spec Packing details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SpecPacking implements java.io.Serializable {
	private static final long serialVersionUID = -5491937678729377638L;
	
	private String isBoxed 					= null;
	private String isPalletized 			= null;
	private ProdIdSpecPacking pIdSpecPackg	= null;
	private ArrayList spProdDespList		= null;
	private ArrayList buPackingList			= null;
	private BoxCharacs boxCharacs 			= null;
	private PPCharacts ppCharacts			= null;
	private ArrayList spAddTextList			= null;
	/**
	 * Default constructor.
	 */
	public SpecPacking() {
		super();
	}
	/**
	 * @return the isBoxed
	 */
	public String getIsBoxed() {
		return isBoxed;
	}
	/**
	 * @param isBoxed the isBoxed to set
	 */
	public void setIsBoxed(String isBoxed) {
		this.isBoxed = isBoxed;
	}
	/**
	 * @return the isPalletized
	 */
	public String getIsPalletized() {
		return isPalletized;
	}
	/**
	 * @param isPalletized the isPalletized to set
	 */
	public void setIsPalletized(String isPalletized) {
		this.isPalletized = isPalletized;
	}
	/**
	 * @return the pIdSpecPackg
	 */
	public ProdIdSpecPacking getPIdSpecPackg() {
		return pIdSpecPackg;
	}
	/**
	 * @param idSpecPackg the pIdSpecPackg to set
	 */
	public void setPIdSpecPackg(ProdIdSpecPacking idSpecPackg) {
		pIdSpecPackg = idSpecPackg;
	}
	/**
	 * @return the spProdDespList
	 */
	public ArrayList getSpProdDespList() {
		return spProdDespList;
	}
	/**
	 * @param spProdDespList the spProdDespList to set
	 */
	public void setSpProdDespList(ArrayList spProdDespList) {
		this.spProdDespList = spProdDespList;
	}
	/**
	 * @return the buPackingList
	 */
	public ArrayList getBuPackingList() {
		return buPackingList;
	}
	/**
	 * @param buPackingList the buPackingList to set
	 */
	public void setBuPackingList(ArrayList buPackingList) {
		this.buPackingList = buPackingList;
	}
	/**
	 * @return the boxCharacs
	 */
	public BoxCharacs getBoxCharacs() {
		return boxCharacs;
	}
	/**
	 * @param boxCharacs the boxCharacs to set
	 */
	public void setBoxCharacs(BoxCharacs boxCharacs) {
		this.boxCharacs = boxCharacs;
	}
	/**
	 * @return the ppCharacts
	 */
	public PPCharacts getPpCharacts() {
		return ppCharacts;
	}
	/**
	 * @param ppCharacts the ppCharacts to set
	 */
	public void setPpCharacts(PPCharacts ppCharacts) {
		this.ppCharacts = ppCharacts;
	}
	/**
	 * @return the spAddTextList
	 */
	public ArrayList getSpAddTextList() {
		return spAddTextList;
	}
	/**
	 * @param spAddTextList the spAddTextList to set
	 */
	public void setSpAddTextList(ArrayList spAddTextList) {
		this.spAddTextList = spAddTextList;
	}
}
