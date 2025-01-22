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
 * Title		: 	PHDetails.java
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
 * PHDetails is a data transfer object to store the specification Binding   
 * PunchedHole Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PHDetails implements java.io.Serializable {
	private static final long serialVersionUID = -5173459691882711541L;
	
	private String phNumOfHoles		= null;
	private String phHoleReinf		= null;
	private String phHolPunType		= null;
	private String phStandSpac		= null;
	private PHSzOfHoPun phSzOfHoPun	= null;
	private PHolSpac pholSpac		= null;
	private ArrayList phDisFEdgList	= null;
	private ArrayList phPTRefList	= null;
	/**
	 * Default constructor.
	 */
	public PHDetails() {
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
	 * @return the phSzOfHoPun
	 */
	public PHSzOfHoPun getPhSzOfHoPun() {
		return phSzOfHoPun;
	}
	/**
	 * @param phSzOfHoPun the phSzOfHoPun to set
	 */
	public void setPhSzOfHoPun(PHSzOfHoPun phSzOfHoPun) {
		this.phSzOfHoPun = phSzOfHoPun;
	}
	/**
	 * @return the pholSpac
	 */
	public PHolSpac getPholSpac() {
		return pholSpac;
	}
	/**
	 * @param pholSpac the pholSpac to set
	 */
	public void setPholSpac(PHolSpac pholSpac) {
		this.pholSpac = pholSpac;
	}
	/**
	 * @return the phDisFEdgList
	 */
	public ArrayList getPhDisFEdgList() {
		return phDisFEdgList;
	}
	/**
	 * @param phDisFEdgList the phDisFEdgList to set
	 */
	public void setPhDisFEdgList(ArrayList phDisFEdgList) {
		this.phDisFEdgList = phDisFEdgList;
	}
	/**
	 * @return the phPTRefList
	 */
	public ArrayList getPhPTRefList() {
		return phPTRefList;
	}
	/**
	 * @param phPTRefList the phPTRefList to set
	 */
	public void setPhPTRefList(ArrayList phPTRefList) {
		this.phPTRefList = phPTRefList;
	}
}
