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
 * Title		: 	SCNameAddress.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;
/**
 * SCNameAddress is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SCNameAddress implements java.io.Serializable {
	private static final long serialVersionUID = 6117520459729723194L;
	
	private String suppName1 = null;
	private String suppName2 = null;
	private String suppName3 = null;
	private String suppAddr1 = null;
	private String suppAddr2= null;
	private String suppAddr3 = null;
	private String suppAddr4 = null;
	private String scCity = null;
	private String scState = null;
	private String scPCode = null;
	private String scCountry = null;	
	
	/**
	 * Default constructor.
	 */
	public SCNameAddress() {
		super();
	}


	/**
	 * @return Returns the suppName1.
	 */
	public String getSuppName1() {
		return suppName1;
	}


	/**
	 * @param suppName1 The suppName1 to set.
	 */
	public void setSuppName1(String suppName1) {
		this.suppName1 = suppName1;
	}


	/**
	 * @return Returns the scCity.
	 */
	public String getScCity() {
		return scCity;
	}


	/**
	 * @param scCity The scCity to set.
	 */
	public void setScCity(String scCity) {
		this.scCity = scCity;
	}


	/**
	 * @return Returns the scCountry.
	 */
	public String getScCountry() {
		return scCountry;
	}


	/**
	 * @param scCountry The scCountry to set.
	 */
	public void setScCountry(String scCountry) {
		this.scCountry = scCountry;
	}


	/**
	 * @return Returns the scPCode.
	 */
	public String getScPCode() {
		return scPCode;
	}


	/**
	 * @param scPCode The scPCode to set.
	 */
	public void setScPCode(String scPCode) {
		this.scPCode = scPCode;
	}


	/**
	 * @return Returns the scState.
	 */
	public String getScState() {
		return scState;
	}


	/**
	 * @param scState The scState to set.
	 */
	public void setScState(String scState) {
		this.scState = scState;
	}


	/**
	 * @return Returns the suppAddr1.
	 */
	public String getSuppAddr1() {
		return suppAddr1;
	}


	/**
	 * @param suppAddr1 The suppAddr1 to set.
	 */
	public void setSuppAddr1(String suppAddr1) {
		this.suppAddr1 = suppAddr1;
	}


	/**
	 * @return Returns the suppAddr2.
	 */
	public String getSuppAddr2() {
		return suppAddr2;
	}


	/**
	 * @param suppAddr2 The suppAddr2 to set.
	 */
	public void setSuppAddr2(String suppAddr2) {
		this.suppAddr2 = suppAddr2;
	}


	/**
	 * @return Returns the suppAddr3.
	 */
	public String getSuppAddr3() {
		return suppAddr3;
	}


	/**
	 * @param suppAddr3 The suppAddr3 to set.
	 */
	public void setSuppAddr3(String suppAddr3) {
		this.suppAddr3 = suppAddr3;
	}


	/**
	 * @return Returns the suppAddr4.
	 */
	public String getSuppAddr4() {
		return suppAddr4;
	}


	/**
	 * @param suppAddr4 The suppAddr4 to set.
	 */
	public void setSuppAddr4(String suppAddr4) {
		this.suppAddr4 = suppAddr4;
	}


	/**
	 * @return Returns the suppName2.
	 */
	public String getSuppName2() {
		return suppName2;
	}


	/**
	 * @param suppName2 The suppName2 to set.
	 */
	public void setSuppName2(String suppName2) {
		this.suppName2 = suppName2;
	}


	/**
	 * @return Returns the suppName3.
	 */
	public String getSuppName3() {
		return suppName3;
	}


	/**
	 * @param suppName3 The suppName3 to set.
	 */
	public void setSuppName3(String suppName3) {
		this.suppName3 = suppName3;
	}
	

	
	
}