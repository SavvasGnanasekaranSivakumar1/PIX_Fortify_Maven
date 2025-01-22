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
 * Title		: 	SIReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * SIReference is a helper data transfer object to store the 
 * Shipping Instruction Reference details.
 * 
 * @author Ashish Agrawal
 */
public class SIReference implements java.io.Serializable {
	private static final long serialVersionUID = -8428777335477388233L;
	
	private String siReferenceType = null;
	private String siReferenceValue = null;
	/**
	 * Default constructor.
	 */
	public SIReference() {
		super();
	}
	/**
	 * @return the siReferenceType
	 */
	public String getSiReferenceType() {
		return siReferenceType;
	}
	/**
	 * @param siReferenceType the siReferenceType to set
	 */
	public void setSiReferenceType(String siReferenceType) {
		this.siReferenceType = siReferenceType;
	}
	/**
	 * @return the siReferenceValue
	 */
	public String getSiReferenceValue() {
		return siReferenceValue;
	}
	/**
	 * @param siReferenceValue the siReferenceValue to set
	 */
	public void setSiReferenceValue(String siReferenceValue) {
		this.siReferenceValue = siReferenceValue;
	}
}
