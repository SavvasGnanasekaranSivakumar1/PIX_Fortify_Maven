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
 * Title		: 	Gimmick.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * Gimmick is a data transfer object to store the Specification NonPress Component 
 * Gimmick details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Gimmick implements java.io.Serializable {
	private static final long serialVersionUID = 6567825284802646543L;
	
	private ArrayList gimAddTextList = null;
	/**
	 * Default constructor.
	 */
	public Gimmick() {
		super();
	}
	/**
	 * @return the gimAddTextList
	 */
	public ArrayList getGimAddTextList() {
		return gimAddTextList;
	}
	/**
	 * @param gimAddTextList the gimAddTextList to set
	 */
	public void setGimAddTextList(ArrayList gimAddTextList) {
		this.gimAddTextList = gimAddTextList;
	}
}
