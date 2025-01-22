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
 * Title		: 	NameAddress.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;
/**
 * ReceiverPartyNameAddress is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ReceiverPartyNameAddress implements java.io.Serializable {
	private static final long serialVersionUID = -3347910180183465826L;
	
	private String recvName1 = null;
	private String recvName2 = null;
	private String recvName3 = null;
	
	private String recvAddress1 = null;
	private String recvAddress2 = null;
	private String recvAddress3 = null;
	private String recvAddress4 = null;
	
	private String recvCity = null;
	private String recvState = null;
	private String recvPostalCode = null;
	private String recvCountry = null;
	/**
	 * @return Returns the recvAddress1.
	 */
	public String getRecvAddress1() {
		return recvAddress1;
	}
	/**
	 * @param recvAddress1 The recvAddress1 to set.
	 */
	public void setRecvAddress1(String recvAddress1) {
		this.recvAddress1 = recvAddress1;
	}
	/**
	 * @return Returns the recvAddress2.
	 */
	public String getRecvAddress2() {
		return recvAddress2;
	}
	/**
	 * @param recvAddress2 The recvAddress2 to set.
	 */
	public void setRecvAddress2(String recvAddress2) {
		this.recvAddress2 = recvAddress2;
	}
	/**
	 * @return Returns the recvAddress3.
	 */
	public String getRecvAddress3() {
		return recvAddress3;
	}
	/**
	 * @param recvAddress3 The recvAddress3 to set.
	 */
	public void setRecvAddress3(String recvAddress3) {
		this.recvAddress3 = recvAddress3;
	}
	/**
	 * @return Returns the recvAddress4.
	 */
	public String getRecvAddress4() {
		return recvAddress4;
	}
	/**
	 * @param recvAddress4 The recvAddress4 to set.
	 */
	public void setRecvAddress4(String recvAddress4) {
		this.recvAddress4 = recvAddress4;
	}
	/**
	 * @return Returns the recvCity.
	 */
	public String getRecvCity() {
		return recvCity;
	}
	/**
	 * @param recvCity The recvCity to set.
	 */
	public void setRecvCity(String recvCity) {
		this.recvCity = recvCity;
	}
	/**
	 * @return Returns the recvCountry.
	 */
	public String getRecvCountry() {
		return recvCountry;
	}
	/**
	 * @param recvCountry The recvCountry to set.
	 */
	public void setRecvCountry(String recvCountry) {
		this.recvCountry = recvCountry;
	}
	/**
	 * @return Returns the recvName1.
	 */
	public String getRecvName1() {
		return recvName1;
	}
	/**
	 * @param recvName1 The recvName1 to set.
	 */
	public void setRecvName1(String recvName1) {
		this.recvName1 = recvName1;
	}
	/**
	 * @return Returns the recvName2.
	 */
	public String getRecvName2() {
		return recvName2;
	}
	/**
	 * @param recvName2 The recvName2 to set.
	 */
	public void setRecvName2(String recvName2) {
		this.recvName2 = recvName2;
	}
	/**
	 * @return Returns the recvName3.
	 */
	public String getRecvName3() {
		return recvName3;
	}
	/**
	 * @param recvName3 The recvName3 to set.
	 */
	public void setRecvName3(String recvName3) {
		this.recvName3 = recvName3;
	}
	/**
	 * @return Returns the recvPostalCode.
	 */
	public String getRecvPostalCode() {
		return recvPostalCode;
	}
	/**
	 * @param recvPostalCode The recvPostalCode to set.
	 */
	public void setRecvPostalCode(String recvPostalCode) {
		this.recvPostalCode = recvPostalCode;
	}
	/**
	 * @return Returns the recvState.
	 */
	public String getRecvState() {
		return recvState;
	}
	/**
	 * @param recvState The recvState to set.
	 */
	public void setRecvState(String recvState) {
		this.recvState = recvState;
	}
	
	
	/**
	 * Default constructor.
	 */
	
	
	
}