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
 * Title		: 	ESMaterial.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ESMaterial is a data transfer object to store the Endsheet 
 * Material details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESMaterial implements java.io.Serializable {
	private static final long serialVersionUID = -2649312860225661285L;
	
	private ESBMaterialChars esbmChars	= null;	
	private ArrayList esAddTextList		= null;	
	/**
	 * Default constructor.
	 */
	public ESMaterial() {
		super();
	}
	/**
	 * @return the esbmChars
	 */
	public ESBMaterialChars getEsbmChars() {
		return esbmChars;
	}
	/**
	 * @param esbmChars the esbmChars to set
	 */
	public void setEsbmChars(ESBMaterialChars esbmChars) {
		this.esbmChars = esbmChars;
	}
	/**
	 * @return the esAddTextList
	 */
	public ArrayList getEsAddTextList() {
		return esAddTextList;
	}
	/**
	 * @param esAddTextList the esAddTextList to set
	 */
	public void setEsAddTextList(ArrayList esAddTextList) {
		this.esAddTextList = esAddTextList;
	}
}
