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
 * Title		: 	BUPUnitCharas.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPUnitCharas is a data transfer object to store the Book Spec Packing
 * Unit Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPUnitCharas implements java.io.Serializable {
	private static final long serialVersionUID = -2163540353539620416L;
	
	private BUPHeight bupHeight 	= null;
	private BUPThickness bupThick 	= null;
	private BUPWidth bupWidth 		= null;
	private BUPWeight bupWeight 	= null;
	/**
	 * Default constructor.
	 */
	public BUPUnitCharas() {
		super();
	}
	/**
	 * @return the bupHeight
	 */
	public BUPHeight getBupHeight() {
		return bupHeight;
	}
	/**
	 * @param bupHeight the bupHeight to set
	 */
	public void setBupHeight(BUPHeight bupHeight) {
		this.bupHeight = bupHeight;
	}
	/**
	 * @return the bupThick
	 */
	public BUPThickness getBupThick() {
		return bupThick;
	}
	/**
	 * @param bupThick the bupThick to set
	 */
	public void setBupThick(BUPThickness bupThick) {
		this.bupThick = bupThick;
	}
	/**
	 * @return the bupWidth
	 */
	public BUPWidth getBupWidth() {
		return bupWidth;
	}
	/**
	 * @param bupWidth the bupWidth to set
	 */
	public void setBupWidth(BUPWidth bupWidth) {
		this.bupWidth = bupWidth;
	}
	/**
	 * @return the bupWeight
	 */
	public BUPWeight getBupWeight() {
		return bupWeight;
	}
	/**
	 * @param bupWeight the bupWeight to set
	 */
	public void setBupWeight(BUPWeight bupWeight) {
		this.bupWeight = bupWeight;
	}
}
