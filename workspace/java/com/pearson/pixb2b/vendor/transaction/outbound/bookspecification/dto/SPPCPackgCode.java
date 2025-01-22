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
 * Title		: 	SPPCPackgCode.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPackgCode is a data transfer object to store the Spec Packing Pallet Packaging
 * Characteristics Packaging Code details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPackgCode implements java.io.Serializable {
	private static final long serialVersionUID = 7335704505934522483L;
	
	private String agency 	= null;
	private String pacCode 	= null;
	/**
	 * Default constructor.
	 */
	public SPPCPackgCode() {
		super();
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getPacCode() {
		return pacCode;
	}
	public void setPacCode(String pacCode) {
		this.pacCode = pacCode;
	}
}
