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
 * Title		: 	GRPaper.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan, 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * GRPaper is a data transfer object to store the 
 * goodsreceipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GRPaper implements java.io.Serializable {	
	private static final long serialVersionUID = -672261229250988161L;

	private PaperCharacteristics paperChar = null;
	private GRReel reel = null;
	

	/**
	 * Default constructor.
	 */
	public GRPaper() {
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
	public GRReel getReel() {
		return reel;
	}


	/**
	 * @param reel The reel to set.
	 */
	public void setReel(GRReel reel) {
		this.reel = reel;
	}


		
		
}
