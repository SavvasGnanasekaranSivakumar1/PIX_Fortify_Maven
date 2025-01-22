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
public class ItemUsagePaper implements java.io.Serializable {	
	private static final long serialVersionUID = -8057479533984094449L;
	
	private IPaperCharacteristics iPaperChar = null;
	private IUsageReel iReel = null;
	

	/**
	 * Default constructor.
	 */
	public ItemUsagePaper() {
		super();
	}


	/**
	 * @return Returns the iPaperChar.
	 */
	public IPaperCharacteristics getIPaperChar() {
		return iPaperChar;
	}


	/**
	 * @param paperChar The iPaperChar to set.
	 */
	public void setIPaperChar(IPaperCharacteristics paperChar) {
		iPaperChar = paperChar;
	}


	/**
	 * @return Returns the iReel.
	 */
	public IUsageReel getIReel() {
		return iReel;
	}


	/**
	 * @param reel The iReel to set.
	 */
	public void setIReel(IUsageReel reel) {
		iReel = reel;
	}


	

}
