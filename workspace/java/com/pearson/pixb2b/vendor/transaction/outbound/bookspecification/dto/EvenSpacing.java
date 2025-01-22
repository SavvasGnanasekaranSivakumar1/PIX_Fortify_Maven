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
 * Title		: 	EvenSpacing.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * EvenSpacing is a data transfer object to store the specification Binding   
 * PunchedHole Hole Even Spacing details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class EvenSpacing implements java.io.Serializable {
	private static final long serialVersionUID = -7974005244249357549L;
	
	private ESValue	esValue = null;
	/**
	 * Default constructor.
	 */
	public EvenSpacing() {
		super();
	}
	/**
	 * @return the esValue
	 */
	public ESValue getEsValue() {
		return esValue;
	}
	/**
	 * @param esValue the esValue to set
	 */
	public void setEsValue(ESValue esValue) {
		this.esValue = esValue;
	}
}
