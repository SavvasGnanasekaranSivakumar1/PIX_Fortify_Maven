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
 * Title		: 	ESRIAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ESRIAddText is a data transfer object to store the Endsheet Reinforcement
 * Material AdditionalText details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESRIAddText implements java.io.Serializable {
	private static final long serialVersionUID = -6877818154319094472L;
	
	private String esRIAddText	= null;	
	/**
	 * Default constructor.
	 */
	public ESRIAddText() {
		super();
	}
	public String getEsRIAddText() {
		return esRIAddText;
	}
	public void setEsRIAddText(String esRIAddText) {
		this.esRIAddText = esRIAddText;
	}
}
