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
 * Title		: 	SAFAEnvInfo.java
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
 * SAFAEnvInfo is a data transfer object to store the Specification Component 
 * Safety And Environmental Information details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SAFAEnvInfo implements java.io.Serializable {
	private static final long serialVersionUID = 5413100884297890098L;
	
	private String safAndEnvType			= null;
	private String agency					= null;
	private String licenceNum				= null;
	private ChainOfCustody chainOfCusdy		= null;
	private SafAndEnvCertn safAndEnvCertn	= null;
	private ArrayList safAddTextList		= null;
	/**
	 * Default constructor.
	 */
	public SAFAEnvInfo() {
		super();
	}
	/**
	 * @return the safAndEnvType
	 */
	public String getSafAndEnvType() {
		return safAndEnvType;
	}
	/**
	 * @param safAndEnvType the safAndEnvType to set
	 */
	public void setSafAndEnvType(String safAndEnvType) {
		this.safAndEnvType = safAndEnvType;
	}
	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}
	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}
	/**
	 * @return the licenceNum
	 */
	public String getLicenceNum() {
		return licenceNum;
	}
	/**
	 * @param licenceNum the licenceNum to set
	 */
	public void setLicenceNum(String licenceNum) {
		this.licenceNum = licenceNum;
	}
	/**
	 * @return the chainOfCusdy
	 */
	public ChainOfCustody getChainOfCusdy() {
		return chainOfCusdy;
	}
	/**
	 * @param chainOfCusdy the chainOfCusdy to set
	 */
	public void setChainOfCusdy(ChainOfCustody chainOfCusdy) {
		this.chainOfCusdy = chainOfCusdy;
	}
	/**
	 * @return the safAndEnvCertn
	 */
	public SafAndEnvCertn getSafAndEnvCertn() {
		return safAndEnvCertn;
	}
	/**
	 * @param safAndEnvCertn the safAndEnvCertn to set
	 */
	public void setSafAndEnvCertn(SafAndEnvCertn safAndEnvCertn) {
		this.safAndEnvCertn = safAndEnvCertn;
	}
	/**
	 * @return the safAddTextList
	 */
	public ArrayList getSafAddTextList() {
		return safAddTextList;
	}
	/**
	 * @param safAddTextList the safAddTextList to set
	 */
	public void setSafAddTextList(ArrayList safAddTextList) {
		this.safAddTextList = safAddTextList;
	}
}
