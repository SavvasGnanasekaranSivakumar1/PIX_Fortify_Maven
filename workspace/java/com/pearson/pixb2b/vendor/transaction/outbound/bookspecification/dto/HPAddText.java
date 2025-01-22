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
 * Title		: 	HPAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * HPAddText is a data transfer object to store the specification Binding   
 * HolePunch Additional Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class HPAddText implements java.io.Serializable {
	private static final long serialVersionUID = -6989569077970189138L;
	
	private String hpAddText = null;
	/**
	 * Default constructor.
	 */
	public HPAddText() {
		super();
	}
	/**
	 * @return the hpAddText
	 */
	public String getHpAddText() {
		return hpAddText;
	}
	/**
	 * @param hpAddText the hpAddText to set
	 */
	public void setHpAddText(String hpAddText) {
		this.hpAddText = hpAddText;
	}
}