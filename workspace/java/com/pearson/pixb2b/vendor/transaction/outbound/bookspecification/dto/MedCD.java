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
 * Title		: 	MedCD.java
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
 * MedCD is a data transfer object to store the Specification NonPress Component 
 * Media CD details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedCD implements java.io.Serializable {
	private static final long serialVersionUID = -8492917433211304546L;
	
	private String cdFormatType		= null;
	private String cdManfProcess	= null;
	private String cdRType			= null;
	private String dscPrinting		= null;
	private String seqNumber		= null;
	private String numOfCols		= null;
	private CDColSpecs cdColSpecs	= null;
	private CDMedLeng cdMedLeng		= null;
	private ArrayList cdPrePrepList	= null;
	private ArrayList cdPackgList	= null;
	private ArrayList cdAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public MedCD() {
		super();
	}
	/**
	 * @return the cdFormatType
	 */
	public String getCdFormatType() {
		return cdFormatType;
	}
	/**
	 * @param cdFormatType the cdFormatType to set
	 */
	public void setCdFormatType(String cdFormatType) {
		this.cdFormatType = cdFormatType;
	}
	/**
	 * @return the cdManfProcess
	 */
	public String getCdManfProcess() {
		return cdManfProcess;
	}
	/**
	 * @param cdManfProcess the cdManfProcess to set
	 */
	public void setCdManfProcess(String cdManfProcess) {
		this.cdManfProcess = cdManfProcess;
	}
	/**
	 * @return the cdRType
	 */
	public String getCdRType() {
		return cdRType;
	}
	/**
	 * @param cdRType the cdRType to set
	 */
	public void setCdRType(String cdRType) {
		this.cdRType = cdRType;
	}
	/**
	 * @return the dscPrinting
	 */
	public String getDscPrinting() {
		return dscPrinting;
	}
	/**
	 * @param dscPrinting the dscPrinting to set
	 */
	public void setDscPrinting(String dscPrinting) {
		this.dscPrinting = dscPrinting;
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
	 * @return the cdColSpecs
	 */
	public CDColSpecs getCdColSpecs() {
		return cdColSpecs;
	}
	/**
	 * @param cdColSpecs the cdColSpecs to set
	 */
	public void setCdColSpecs(CDColSpecs cdColSpecs) {
		this.cdColSpecs = cdColSpecs;
	}
	/**
	 * @return the cdMedLeng
	 */
	public CDMedLeng getCdMedLeng() {
		return cdMedLeng;
	}
	/**
	 * @param cdMedLeng the cdMedLeng to set
	 */
	public void setCdMedLeng(CDMedLeng cdMedLeng) {
		this.cdMedLeng = cdMedLeng;
	}
	/**
	 * @return the cdPrePrepList
	 */
	public ArrayList getCdPrePrepList() {
		return cdPrePrepList;
	}
	/**
	 * @param cdPrePrepList the cdPrePrepList to set
	 */
	public void setCdPrePrepList(ArrayList cdPrePrepList) {
		this.cdPrePrepList = cdPrePrepList;
	}
	/**
	 * @return the cdPackgList
	 */
	public ArrayList getCdPackgList() {
		return cdPackgList;
	}
	/**
	 * @param cdPackgList the cdPackgList to set
	 */
	public void setCdPackgList(ArrayList cdPackgList) {
		this.cdPackgList = cdPackgList;
	}
	/**
	 * @return the cdAddTextList
	 */
	public ArrayList getCdAddTextList() {
		return cdAddTextList;
	}
	/**
	 * @param cdAddTextList the cdAddTextList to set
	 */
	public void setCdAddTextList(ArrayList cdAddTextList) {
		this.cdAddTextList = cdAddTextList;
	}
}
