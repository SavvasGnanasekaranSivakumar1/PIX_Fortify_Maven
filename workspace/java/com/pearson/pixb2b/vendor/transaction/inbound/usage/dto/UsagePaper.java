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
 * Title		: 	UsagePaper.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * UsagePaper is a data transfer object to store the 
 * usage paper details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class UsagePaper implements java.io.Serializable {	
	private static final long serialVersionUID = 8300610222880936087L;

	private PaperCharacteristics paperChar = null;
	private UsageReel reel = null;
	

	/**
	 * Default constructor.
	 */
	public UsagePaper() {
		super();
	}


	/**
	 * @return Returns the paperChar.
	 */
	public PaperCharacteristics getPaperChar() {
		return paperChar;
	}


	/**
	 * @param paperChar The paperChar to set.
	 */
	public void setPaperChar(PaperCharacteristics paperChar) {
		this.paperChar = paperChar;
	}


	/**
	 * @return Returns the reel.
	 */
	public UsageReel getReel() {
		return reel;
	}


	/**
	 * @param reel The reel to set.
	 */
	public void setReel(UsageReel reel) {
		this.reel = reel;
	}





		
		
}
