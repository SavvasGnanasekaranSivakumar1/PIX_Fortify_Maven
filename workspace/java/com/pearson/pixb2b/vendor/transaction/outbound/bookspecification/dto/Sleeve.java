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
 * Title		: 	Sleeve.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * Sleeve is a data transfer object to store the Specification NonPress Component 
 * Sleeve details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Sleeve implements java.io.Serializable {
	private static final long serialVersionUID = 856357205785652041L;
	
	private String spineLabel  = null;
	private String slvInstType = null;	
	/**
	 * Default constructor.
	 */
	public Sleeve() {
		super();
	}
	/**
	 * @return the spineLabel
	 */
	public String getSpineLabel() {
		return spineLabel;
	}
	/**
	 * @param spineLabel the spineLabel to set
	 */
	public void setSpineLabel(String spineLabel) {
		this.spineLabel = spineLabel;
	}
	/**
	 * @return the slvInstType
	 */
	public String getSlvInstType() {
		return slvInstType;
	}
	/**
	 * @param slvInstType the slvInstType to set
	 */
	public void setSlvInstType(String slvInstType) {
		this.slvInstType = slvInstType;
	}
}
