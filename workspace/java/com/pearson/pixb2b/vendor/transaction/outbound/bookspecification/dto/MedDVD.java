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
 * Title		: 	MedDVD.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * MedDVD is a data transfer object to store the Specification NonPress Component 
 * Media DVD details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedDVD implements java.io.Serializable {
	private static final long serialVersionUID = 7337258373047048926L;
	
	private String dvdFormatType		= null;
	private String discPrinting			= null;
	private String seqNumber			= null;
	private String numOfCols			= null;
	private DvdColSpecs dvdColSpecs		= null;
	private DvdMedLeng dvdMedLeng		= null;
	private ArrayList dvdPrePrepList	= null;
	private ArrayList dvdPackgList		= null;
	private ArrayList dvdAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public MedDVD() {
		super();
	}
	/**
	 * @return the dvdFormatType
	 */
	public String getDvdFormatType() {
		return dvdFormatType;
	}
	/**
	 * @param dvdFormatType the dvdFormatType to set
	 */
	public void setDvdFormatType(String dvdFormatType) {
		this.dvdFormatType = dvdFormatType;
	}
	/**
	 * @return the discPrinting
	 */
	public String getDiscPrinting() {
		return discPrinting;
	}
	/**
	 * @param discPrinting the discPrinting to set
	 */
	public void setDiscPrinting(String discPrinting) {
		this.discPrinting = discPrinting;
	}
	/**
	 * @return the seqNumber
	 */
	public String getSeqNumber() {
		return seqNumber;
	}
	/**
	 * @param seqNumber the seqNumber to set
	 */
	public void setSeqNumber(String seqNumber) {
		this.seqNumber = seqNumber;
	}
	/**
	 * @return the numOfCols
	 */
	public String getNumOfCols() {
		return numOfCols;
	}
	/**
	 * @param numOfCols the numOfCols to set
	 */
	public void setNumOfCols(String numOfCols) {
		this.numOfCols = numOfCols;
	}
	/**
	 * @return the dvdColSpecs
	 */
	public DvdColSpecs getDvdColSpecs() {
		return dvdColSpecs;
	}
	/**
	 * @param dvdColSpecs the dvdColSpecs to set
	 */
	public void setDvdColSpecs(DvdColSpecs dvdColSpecs) {
		this.dvdColSpecs = dvdColSpecs;
	}
	/**
	 * @return the dvdMedLeng
	 */
	public DvdMedLeng getDvdMedLeng() {
		return dvdMedLeng;
	}
	/**
	 * @param dvdMedLeng the dvdMedLeng to set
	 */
	public void setDvdMedLeng(DvdMedLeng dvdMedLeng) {
		this.dvdMedLeng = dvdMedLeng;
	}
	/**
	 * @return the dvdPrePrepList
	 */
	public ArrayList getDvdPrePrepList() {
		return dvdPrePrepList;
	}
	/**
	 * @param dvdPrePrepList the dvdPrePrepList to set
	 */
	public void setDvdPrePrepList(ArrayList dvdPrePrepList) {
		this.dvdPrePrepList = dvdPrePrepList;
	}
	/**
	 * @return the dvdPackgList
	 */
	public ArrayList getDvdPackgList() {
		return dvdPackgList;
	}
	/**
	 * @param dvdPackgList the dvdPackgList to set
	 */
	public void setDvdPackgList(ArrayList dvdPackgList) {
		this.dvdPackgList = dvdPackgList;
	}
	/**
	 * @return the dvdAddTextList
	 */
	public ArrayList getDvdAddTextList() {
		return dvdAddTextList;
	}
	/**
	 * @param dvdAddTextList the dvdAddTextList to set
	 */
	public void setDvdAddTextList(ArrayList dvdAddTextList) {
		this.dvdAddTextList = dvdAddTextList;
	}
}
