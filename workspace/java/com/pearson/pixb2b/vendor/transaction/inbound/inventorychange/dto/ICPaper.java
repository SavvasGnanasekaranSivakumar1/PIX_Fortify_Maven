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
 * Title		: 	ISPaper.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

/**
 * ICPaper is a data transfer object to store the 
 * Inventory change  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICPaper implements java.io.Serializable {	
	private static final long serialVersionUID = 8300610222880936087L;

	private PaperCharacteristics paperChar = null;
	private ICReel reel = null;
	

	/**
	 * Default constructor.
	 */
	public ICPaper() {
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
	public ICReel getReel() {
		return reel;
	}


	/**
	 * @param reel The reel to set.
	 */
	public void setReel(ICReel reel) {
		this.reel = reel;
	}


		
		
}
