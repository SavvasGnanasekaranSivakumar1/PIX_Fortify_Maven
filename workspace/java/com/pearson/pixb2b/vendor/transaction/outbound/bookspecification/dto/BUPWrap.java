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
 * Title		: 	BUPWrap.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPWrap is a data transfer object to store the Book Spec Unit Packing
 * Wrap details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPWrap implements java.io.Serializable {
	private static final long serialVersionUID = -1540020285868901667L;
	
	private String wrapProps	= null;
	private String wrapType		= null;
	private String wrapLoc		= null;
	private String numOfWraps	= null;
	private String brand		= null;
	/**
	 * Default constructor.
	 */
	public BUPWrap() {
		super();
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
	 * @return the numOfWraps
	 */
	public String getNumOfWraps() {
		return numOfWraps;
	}
	/**
	 * @param numOfWraps the numOfWraps to set
	 */
	public void setNumOfWraps(String numOfWraps) {
		this.numOfWraps = numOfWraps;
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
