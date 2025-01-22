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
 * Title		: 	MMChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MMChars is a data transfer object to store the Mechanical Material  
 * Characteristics details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MMChars implements java.io.Serializable {
	private static final long serialVersionUID = 8763285836701337989L;
	
	private String mmDescp 			= null;
	private MMCWireMatl mmcWireMatl	= null;
	private MMCPlasMatl mmcPlasMatl	= null;
	private MMCColCode mmcColCode	= null;
	private String colDesp			= null;
	/**
	 * Default constructor.
	 */
	public MMChars() {
		super();
	}
	/**
	 * @return the mmDescp
	 */
	public String getMmDescp() {
		return mmDescp;
	}
	/**
	 * @param mmDescp the mmDescp to set
	 */
	public void setMmDescp(String mmDescp) {
		this.mmDescp = mmDescp;
	}
	/**
	 * @return the mmcWireMatl
	 */
	public MMCWireMatl getMmcWireMatl() {
		return mmcWireMatl;
	}
	/**
	 * @param mmcWireMatl the mmcWireMatl to set
	 */
	public void setMmcWireMatl(MMCWireMatl mmcWireMatl) {
		this.mmcWireMatl = mmcWireMatl;
	}
	/**
	 * @return the mmcPlasMatl
	 */
	public MMCPlasMatl getMmcPlasMatl() {
		return mmcPlasMatl;
	}
	/**
	 * @param mmcPlasMatl the mmcPlasMatl to set
	 */
	public void setMmcPlasMatl(MMCPlasMatl mmcPlasMatl) {
		this.mmcPlasMatl = mmcPlasMatl;
	}
	/**
	 * @return the mmcColCode
	 */
	public MMCColCode getMmcColCode() {
		return mmcColCode;
	}
	/**
	 * @param mmcColCode the mmcColCode to set
	 */
	public void setMmcColCode(MMCColCode mmcColCode) {
		this.mmcColCode = mmcColCode;
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
}