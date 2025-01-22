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
 * Title		: 	SCFSAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCFSAddText is a data transfer object to store the Specification Component 
 * Finished Size AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFSAddText implements java.io.Serializable {
	private static final long serialVersionUID = -1788744100672223042L;
	
	private String scFSAddText	= null;
	/**
	 * Default constructor.
	 */
	public SCFSAddText() {
		super();
	}
	/**
	 * @return the scFSAddText
	 */
	public String getScFSAddText() {
		return scFSAddText;
	}
	/**
	 * @param scFSAddText the scFSAddText to set
	 */
	public void setScFSAddText(String scFSAddText) {
		this.scFSAddText = scFSAddText;
	}
}
