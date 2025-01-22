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
 * Title		: 	BEUnitChar.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BEUnitChar is a data transfer object to store the Binding Extras
 * UnitCharacteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BEUnitChar implements java.io.Serializable {
	private static final long serialVersionUID = -7454402298429994889L;
	
	private UCHeight ucHeight 	= null;
	private UCThickness ucThick = null;
	private UCWidth ucWidth 	= null;
	private UCWeight ucWeight 	= null;
	/**
	 * Default constructor.
	 */
	public BEUnitChar() {
		super();
	}
	/**
	 * @return the ucHeight
	 */
	public UCHeight getUcHeight() {
		return ucHeight;
	}
	/**
	 * @param ucHeight the ucHeight to set
	 */
	public void setUcHeight(UCHeight ucHeight) {
		this.ucHeight = ucHeight;
	}
	/**
	 * @return the ucThick
	 */
	public UCThickness getUcThick() {
		return ucThick;
	}
	/**
	 * @param ucThick the ucThick to set
	 */
	public void setUcThick(UCThickness ucThick) {
		this.ucThick = ucThick;
	}
	/**
	 * @return the ucWidth
	 */
	public UCWidth getUcWidth() {
		return ucWidth;
	}
	/**
	 * @param ucWidth the ucWidth to set
	 */
	public void setUcWidth(UCWidth ucWidth) {
		this.ucWidth = ucWidth;
	}
	/**
	 * @return the ucWeight
	 */
	public UCWeight getUcWeight() {
		return ucWeight;
	}
	/**
	 * @param ucWeight the ucWeight to set
	 */
	public void setUcWeight(UCWeight ucWeight) {
		this.ucWeight = ucWeight;
	}
}
