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
 * Title		: 	SPPCWrap.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCWrap is a data transfer object to store the Spec Packing Pallet Packaging
 * Wrap Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCWrap implements java.io.Serializable {
	private static final long serialVersionUID = -7715458548192575744L;
	
	private String numOfWraps	= null;
	private String brand		= null;
	private String wrapProps	= null;
	private String wrapType		= null;
	private String wrapLocation	= null;
	/**
	 * Default constructor.
	 */
	public SPPCWrap() {
		super();
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
	 * @return the wrapLocation
	 */
	public String getWrapLocation() {
		return wrapLocation;
	}
	/**
	 * @param wrapLocation the wrapLocation to set
	 */
	public void setWrapLocation(String wrapLocation) {
		this.wrapLocation = wrapLocation;
	}
}
