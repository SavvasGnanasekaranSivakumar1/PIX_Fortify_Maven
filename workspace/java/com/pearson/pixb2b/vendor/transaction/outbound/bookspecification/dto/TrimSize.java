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
 * Title		: 	TrimSize.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	20 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * TrimSize is a data transfer object to store the 
 * Trim Size details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class TrimSize implements java.io.Serializable {
	private static final long serialVersionUID = -8921719879414598420L;
	
	private Length length 		= null;
	private Width width			= null;
	private BBBulk bBlockBulk	= null;
	private SpineSize spineSize	= null;	
	/**
	 * Default constructor.
	 */
	public TrimSize() {
		super();
	}
	/**
	 * @return the length
	 */
	public Length getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Length length) {
		this.length = length;
	}
	/**
	 * @return the width
	 */
	public Width getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Width width) {
		this.width = width;
	}
	/**
	 * @return the bBlockBulk
	 */
	public BBBulk getBBlockBulk() {
		return bBlockBulk;
	}
	/**
	 * @param blockBulk the bBlockBulk to set
	 */
	public void setBBlockBulk(BBBulk blockBulk) {
		bBlockBulk = blockBulk;
	}
	/**
	 * @return the spineSize
	 */
	public SpineSize getSpineSize() {
		return spineSize;
	}
	/**
	 * @param spineSize the spineSize to set
	 */
	public void setSpineSize(SpineSize spineSize) {
		this.spineSize = spineSize;
	}
}