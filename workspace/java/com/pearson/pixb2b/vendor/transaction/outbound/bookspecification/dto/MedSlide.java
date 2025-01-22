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
 * Title		: 	MedSlide.java
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
 * MedSlide is a data transfer object to store the Specification NonPress Component 
 * Media Slide details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedSlide implements java.io.Serializable {
	private static final long serialVersionUID = 8267331079652550231L;
	
	private String slMountNum		= null;
	private String slMountCaps		= null;
	private String seqNumber		= null;
	private MSWidth msWidth 		= null;
	private MSLength msLength 		= null;
	private String numOfCols		= null;
	private MSColSpec msColSpec		= null;
	private String slMountDesp		= null;
	private String slMountMat 		= null;
	private String slContDesp 		= null;
	private String pagesPerSet 		= null;
	private String slPerPage 		= null;
	private MSHPunInf msHPunInf 	= null;
	private String slContMail		= null;
	private String slMlSizeDesp		= null;
	private ArrayList msAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public MedSlide() {
		super();
	}
	/**
	 * @return the slMountNum
	 */
	public String getSlMountNum() {
		return slMountNum;
	}
	/**
	 * @param slMountNum the slMountNum to set
	 */
	public void setSlMountNum(String slMountNum) {
		this.slMountNum = slMountNum;
	}
	/**
	 * @return the slMountCaps
	 */
	public String getSlMountCaps() {
		return slMountCaps;
	}
	/**
	 * @param slMountCaps the slMountCaps to set
	 */
	public void setSlMountCaps(String slMountCaps) {
		this.slMountCaps = slMountCaps;
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
	 * @return the msWidth
	 */
	public MSWidth getMsWidth() {
		return msWidth;
	}
	/**
	 * @param msWidth the msWidth to set
	 */
	public void setMsWidth(MSWidth msWidth) {
		this.msWidth = msWidth;
	}
	/**
	 * @return the msLength
	 */
	public MSLength getMsLength() {
		return msLength;
	}
	/**
	 * @param msLength the msLength to set
	 */
	public void setMsLength(MSLength msLength) {
		this.msLength = msLength;
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
	 * @return the msColSpec
	 */
	public MSColSpec getMsColSpec() {
		return msColSpec;
	}
	/**
	 * @param msColSpec the msColSpec to set
	 */
	public void setMsColSpec(MSColSpec msColSpec) {
		this.msColSpec = msColSpec;
	}
	/**
	 * @return the slMountDesp
	 */
	public String getSlMountDesp() {
		return slMountDesp;
	}
	/**
	 * @param slMountDesp the slMountDesp to set
	 */
	public void setSlMountDesp(String slMountDesp) {
		this.slMountDesp = slMountDesp;
	}
	/**
	 * @return the slMountMat
	 */
	public String getSlMountMat() {
		return slMountMat;
	}
	/**
	 * @param slMountMat the slMountMat to set
	 */
	public void setSlMountMat(String slMountMat) {
		this.slMountMat = slMountMat;
	}
	/**
	 * @return the slContDesp
	 */
	public String getSlContDesp() {
		return slContDesp;
	}
	/**
	 * @param slContDesp the slContDesp to set
	 */
	public void setSlContDesp(String slContDesp) {
		this.slContDesp = slContDesp;
	}
	/**
	 * @return the pagesPerSet
	 */
	public String getPagesPerSet() {
		return pagesPerSet;
	}
	/**
	 * @param pagesPerSet the pagesPerSet to set
	 */
	public void setPagesPerSet(String pagesPerSet) {
		this.pagesPerSet = pagesPerSet;
	}
	/**
	 * @return the slPerPage
	 */
	public String getSlPerPage() {
		return slPerPage;
	}
	/**
	 * @param slPerPage the slPerPage to set
	 */
	public void setSlPerPage(String slPerPage) {
		this.slPerPage = slPerPage;
	}
	/**
	 * @return the msHPunInf
	 */
	public MSHPunInf getMsHPunInf() {
		return msHPunInf;
	}
	/**
	 * @param msHPunInf the msHPunInf to set
	 */
	public void setMsHPunInf(MSHPunInf msHPunInf) {
		this.msHPunInf = msHPunInf;
	}
	/**
	 * @return the slContMail
	 */
	public String getSlContMail() {
		return slContMail;
	}
	/**
	 * @param slContMail the slContMail to set
	 */
	public void setSlContMail(String slContMail) {
		this.slContMail = slContMail;
	}
	/**
	 * @return the slMlSizeDesp
	 */
	public String getSlMlSizeDesp() {
		return slMlSizeDesp;
	}
	/**
	 * @param slMlSizeDesp the slMlSizeDesp to set
	 */
	public void setSlMlSizeDesp(String slMlSizeDesp) {
		this.slMlSizeDesp = slMlSizeDesp;
	}
	/**
	 * @return the msAddTextList
	 */
	public ArrayList getMsAddTextList() {
		return msAddTextList;
	}
	/**
	 * @param msAddTextList the msAddTextList to set
	 */
	public void setMsAddTextList(ArrayList msAddTextList) {
		this.msAddTextList = msAddTextList;
	}
}
