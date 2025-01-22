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
 * Title		: 	MechMaterial.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * MechMaterial is a data transfer object to store the Mechanical Material  
 * details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MechMaterial implements java.io.Serializable {
	private static final long serialVersionUID = -7164465028241428299L;
	
	private MMChars mmChars = null;
	private ArrayList mmAddTextList = null;
	/**
	 * Default constructor.
	 */
	public MechMaterial() {
		super();
	}
	/**
	 * @return the mmChars
	 */
	public MMChars getMmChars() {
		return mmChars;
	}
	/**
	 * @param mmChars the mmChars to set
	 */
	public void setMmChars(MMChars mmChars) {
		this.mmChars = mmChars;
	}
	/**
	 * @return the mmAddTextList
	 */
	public ArrayList getMmAddTextList() {
		return mmAddTextList;
	}
	/**
	 * @param mmAddTextList the mmAddTextList to set
	 */
	public void setMmAddTextList(ArrayList mmAddTextList) {
		this.mmAddTextList = mmAddTextList;
	}
}