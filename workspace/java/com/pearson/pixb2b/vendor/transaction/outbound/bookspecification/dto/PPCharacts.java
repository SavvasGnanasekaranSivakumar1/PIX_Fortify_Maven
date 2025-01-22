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
 * Title		: 	PPCharacts.java
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
 * PPCharacts is a data transfer object to store the Spec Packing Pallet
 * Packaging Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PPCharacts implements java.io.Serializable {
	private static final long serialVersionUID = -5766346828363870677L;
	
	private SPPCPackgCode sppcPackgCode	= null;
	private SPPCPackgDesp sppcPackgDesp	= null;
	private SPPCWrapChars sppcWrapChars	= null;
	private SPPCPalChars sppcPalChars	= null;
	private SPPCBandChars sppcBandChacs	= null;	
	private ArrayList sppcLabCharsList  = null;
	private ArrayList sppcStenCharsList	= null;
	private SPPCMaxHgt sppcMaxHgt		= null;
	private SPPCMaxGsWgt sppcMaxGsWgt	= null;
	private ArrayList sppcAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public PPCharacts() {
		super();
	}
	public SPPCPackgCode getSppcPackgCode() {
		return sppcPackgCode;
	}
	public void setSppcPackgCode(SPPCPackgCode sppcPackgCode) {
		this.sppcPackgCode = sppcPackgCode;
	}
	public SPPCPackgDesp getSppcPackgDesp() {
		return sppcPackgDesp;
	}
	public void setSppcPackgDesp(SPPCPackgDesp sppcPackgDesp) {
		this.sppcPackgDesp = sppcPackgDesp;
	}
	public SPPCWrapChars getSppcWrapChars() {
		return sppcWrapChars;
	}
	public void setSppcWrapChars(SPPCWrapChars sppcWrapChars) {
		this.sppcWrapChars = sppcWrapChars;
	}
	public SPPCPalChars getSppcPalChars() {
		return sppcPalChars;
	}
	public void setSppcPalChars(SPPCPalChars sppcPalChars) {
		this.sppcPalChars = sppcPalChars;
	}
	public SPPCBandChars getSppcBandChacs() {
		return sppcBandChacs;
	}
	public void setSppcBandChacs(SPPCBandChars sppcBandChacs) {
		this.sppcBandChacs = sppcBandChacs;
	}
	public ArrayList getSppcLabCharsList() {
		return sppcLabCharsList;
	}
	public void setSppcLabCharsList(ArrayList sppcLabCharsList) {
		this.sppcLabCharsList = sppcLabCharsList;
	}
	public ArrayList getSppcStenCharsList() {
		return sppcStenCharsList;
	}
	public void setSppcStenCharsList(ArrayList sppcStenCharsList) {
		this.sppcStenCharsList = sppcStenCharsList;
	}
	public SPPCMaxHgt getSppcMaxHgt() {
		return sppcMaxHgt;
	}
	public void setSppcMaxHgt(SPPCMaxHgt sppcMaxHgt) {
		this.sppcMaxHgt = sppcMaxHgt;
	}
	public SPPCMaxGsWgt getSppcMaxGsWgt() {
		return sppcMaxGsWgt;
	}
	public void setSppcMaxGsWgt(SPPCMaxGsWgt sppcMaxGsWgt) {
		this.sppcMaxGsWgt = sppcMaxGsWgt;
	}
	public ArrayList getSppcAddTextList() {
		return sppcAddTextList;
	}
	public void setSppcAddTextList(ArrayList sppcAddTextList) {
		this.sppcAddTextList = sppcAddTextList;
	}
}
