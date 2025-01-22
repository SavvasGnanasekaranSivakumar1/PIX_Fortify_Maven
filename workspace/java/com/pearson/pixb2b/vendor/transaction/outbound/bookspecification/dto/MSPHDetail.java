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
 * Title		: 	MSPHDetail.java
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
 * MSPHDetail is a data transfer object to store the Specification NonPress Component 
 * Media Slide HolePunch Information Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSPHDetail implements java.io.Serializable {
	private static final long serialVersionUID = 804516455126296704L;
	
	private String phNumOfHoles		= null;
	private String phHoleReinf		= null;
	private String phHolPunType		= null;
	private String phStandSpac		= null;
	private MSSzOfHoPun msSzOfHoPun	= null;
	private MSHolSpac msHolSpac		= null;
	private ArrayList msDisFEdgList	= null;
	private ArrayList msPTRefList	= null;
	/**
	 * Default constructor.
	 */
	public MSPHDetail() {
		super();
	}
	/**
	 * @return the phNumOfHoles
	 */
	public String getPhNumOfHoles() {
		return phNumOfHoles;
	}
	/**
	 * @param phNumOfHoles the phNumOfHoles to set
	 */
	public void setPhNumOfHoles(String phNumOfHoles) {
		this.phNumOfHoles = phNumOfHoles;
	}
	/**
	 * @return the phHoleReinf
	 */
	public String getPhHoleReinf() {
		return phHoleReinf;
	}
	/**
	 * @param phHoleReinf the phHoleReinf to set
	 */
	public void setPhHoleReinf(String phHoleReinf) {
		this.phHoleReinf = phHoleReinf;
	}
	/**
	 * @return the phHolPunType
	 */
	public String getPhHolPunType() {
		return phHolPunType;
	}
	/**
	 * @param phHolPunType the phHolPunType to set
	 */
	public void setPhHolPunType(String phHolPunType) {
		this.phHolPunType = phHolPunType;
	}
	/**
	 * @return the phStandSpac
	 */
	public String getPhStandSpac() {
		return phStandSpac;
	}
	/**
	 * @param phStandSpac the phStandSpac to set
	 */
	public void setPhStandSpac(String phStandSpac) {
		this.phStandSpac = phStandSpac;
	}
	/**
	 * @return the msSzOfHoPun
	 */
	public MSSzOfHoPun getMsSzOfHoPun() {
		return msSzOfHoPun;
	}
	/**
	 * @param msSzOfHoPun the msSzOfHoPun to set
	 */
	public void setMsSzOfHoPun(MSSzOfHoPun msSzOfHoPun) {
		this.msSzOfHoPun = msSzOfHoPun;
	}
	/**
	 * @return the msHolSpac
	 */
	public MSHolSpac getMsHolSpac() {
		return msHolSpac;
	}
	/**
	 * @param msHolSpac the msHolSpac to set
	 */
	public void setMsHolSpac(MSHolSpac msHolSpac) {
		this.msHolSpac = msHolSpac;
	}
	/**
	 * @return the msDisFEdgList
	 */
	public ArrayList getMsDisFEdgList() {
		return msDisFEdgList;
	}
	/**
	 * @param msDisFEdgList the msDisFEdgList to set
	 */
	public void setMsDisFEdgList(ArrayList msDisFEdgList) {
		this.msDisFEdgList = msDisFEdgList;
	}
	/**
	 * @return the msPTRefList
	 */
	public ArrayList getMsPTRefList() {
		return msPTRefList;
	}
	/**
	 * @param msPTRefList the msPTRefList to set
	 */
	public void setMsPTRefList(ArrayList msPTRefList) {
		this.msPTRefList = msPTRefList;
	}
}
