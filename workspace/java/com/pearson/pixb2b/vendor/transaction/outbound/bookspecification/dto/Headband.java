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
 * Title		: 	Headband.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * Headband is a data transfer object to store the specification Binding   
 * Case Headband Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Headband implements java.io.Serializable {
	private static final long serialVersionUID = -2349586234148188115L;
	
	private String bandLocType	= null;
	private HBColCode hbColCode = null;
	private String colDesp 		= null;
	private String bandMatl		= null;
	/**
	 * Default constructor.
	 */
	public Headband() {
		super();
	}
	/**
	 * @return the bandLocType
	 */
	public String getBandLocType() {
		return bandLocType;
	}
	/**
	 * @param bandLocType the bandLocType to set
	 */
	public void setBandLocType(String bandLocType) {
		this.bandLocType = bandLocType;
	}
	/**
	 * @return the hbColCode
	 */
	public HBColCode getHbColCode() {
		return hbColCode;
	}
	/**
	 * @param hbColCode the hbColCode to set
	 */
	public void setHbColCode(HBColCode hbColCode) {
		this.hbColCode = hbColCode;
	}
	/**
	 * @return the colDesp
	 */
	public String getColDesp() {
		return colDesp;
	}
	/**
	 * @param colDesp the colDesp to set
	 */
	public void setColDesp(String colDesp) {
		this.colDesp = colDesp;
	}
	/**
	 * @return the bandMatl
	 */
	public String getBandMatl() {
		return bandMatl;
	}
	/**
	 * @param bandMatl the bandMatl to set
	 */
	public void setBandMatl(String bandMatl) {
		this.bandMatl = bandMatl;
	}
}
