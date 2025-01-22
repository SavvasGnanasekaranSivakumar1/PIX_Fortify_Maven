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
 * Title		: 	PCWrap.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCWrap is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs Wrap details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCWrap implements java.io.Serializable {
	private static final long serialVersionUID = 8538411153123354855L;
	
	private String wrapType		= null;
	private String wrapProps	= null;
	private String wrapLoc		= null;
	private String numbOfWraps	= null;
	private String brand		= null;
	/**
	 * Default constructor.
	 */
	public PCWrap() {
		super();
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
	 * @return the wrapProps
	 */
	public String getWrapProps() {
		return wrapProps;
	}
	/**
	 * @param wrapProps the wrapProps to set
	 */
	public void setWrapProps(String wrapProps) {
		this.wrapProps = wrapProps;
	}
	/**
	 * @return the wrapLoc
	 */
	public String getWrapLoc() {
		return wrapLoc;
	}
	/**
	 * @param wrapLoc the wrapLoc to set
	 */
	public void setWrapLoc(String wrapLoc) {
		this.wrapLoc = wrapLoc;
	}
	/**
	 * @return the numbOfWraps
	 */
	public String getNumbOfWraps() {
		return numbOfWraps;
	}
	/**
	 * @param numbOfWraps the numbOfWraps to set
	 */
	public void setNumbOfWraps(String numbOfWraps) {
		this.numbOfWraps = numbOfWraps;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
