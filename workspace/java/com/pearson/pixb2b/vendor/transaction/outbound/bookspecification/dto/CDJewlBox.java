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
 * Title		: 	CDJewlBox.java
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
 * CDJewlBox is a data transfer object to store the Specification NonPress Component Media
 * CD Packaging JewelBox details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDJewlBox implements java.io.Serializable {
	private static final long serialVersionUID = 4202119763658629428L;
	
	private String jewelBoxType  	= null;
	private String trayColType 		= null;
	private String inlayCardQuan	= null;
	private String wrapType 		= null;
	private String tearStripType  	= null;
	private JBColSpec jbColSpec 	= null;
	private ArrayList jbInstRefList = null;
	/**
	 * Default constructor.
	 */
	public CDJewlBox() {
		super();
	}
	/**
	 * @return the jewelBoxType
	 */
	public String getJewelBoxType() {
		return jewelBoxType;
	}
	/**
	 * @param jewelBoxType the jewelBoxType to set
	 */
	public void setJewelBoxType(String jewelBoxType) {
		this.jewelBoxType = jewelBoxType;
	}
	/**
	 * @return the trayColType
	 */
	public String getTrayColType() {
		return trayColType;
	}
	/**
	 * @param trayColType the trayColType to set
	 */
	public void setTrayColType(String trayColType) {
		this.trayColType = trayColType;
	}
	/**
	 * @return the inlayCardQuan
	 */
	public String getInlayCardQuan() {
		return inlayCardQuan;
	}
	/**
	 * @param inlayCardQuan the inlayCardQuan to set
	 */
	public void setInlayCardQuan(String inlayCardQuan) {
		this.inlayCardQuan = inlayCardQuan;
	}
	/**
	 * @return the wrapType
	 */
	public String getWrapType() {
		return wrapType;
	}
	/**
	 * @param wrapType the wrapType to set
	 */
	public void setWrapType(String wrapType) {
		this.wrapType = wrapType;
	}
	/**
	 * @return the tearStripType
	 */
	public String getTearStripType() {
		return tearStripType;
	}
	/**
	 * @param tearStripType the tearStripType to set
	 */
	public void setTearStripType(String tearStripType) {
		this.tearStripType = tearStripType;
	}
	/**
	 * @return the jbColSpec
	 */
	public JBColSpec getJbColSpec() {
		return jbColSpec;
	}
	/**
	 * @param jbColSpec the jbColSpec to set
	 */
	public void setJbColSpec(JBColSpec jbColSpec) {
		this.jbColSpec = jbColSpec;
	}
	/**
	 * @return the jbInstRefList
	 */
	public ArrayList getJbInstRefList() {
		return jbInstRefList;
	}
	/**
	 * @param jbInstRefList the jbInstRefList to set
	 */
	public void setJbInstRefList(ArrayList jbInstRefList) {
		this.jbInstRefList = jbInstRefList;
	}
}
