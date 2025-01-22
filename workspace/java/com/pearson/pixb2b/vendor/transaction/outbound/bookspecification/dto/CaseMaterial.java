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
 * Title		: 	CaseMaterial.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * CaseMaterial is a data transfer object to store the specification Binding   
 * CaseMaking CaseMaterial Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseMaterial implements java.io.Serializable {
	private static final long serialVersionUID = -6832845005631818862L;
	
	private String cLocType 			= null;
	private CMBMChars cmbmChars			= null;
	private ArrayList caMtAddTextList 	= null;
	/**
	 * Default constructor.
	 */
	public CaseMaterial() {
		super();
	}
	/**
	 * @return the cLocType
	 */
	public String getCLocType() {
		return cLocType;
	}
	/**
	 * @param locType the cLocType to set
	 */
	public void setCLocType(String locType) {
		cLocType = locType;
	}
	/**
	 * @return the cmbmChars
	 */
	public CMBMChars getCmbmChars() {
		return cmbmChars;
	}
	/**
	 * @param cmbmChars the cmbmChars to set
	 */
	public void setCmbmChars(CMBMChars cmbmChars) {
		this.cmbmChars = cmbmChars;
	}
	/**
	 * @return the caMtAddTextList
	 */
	public ArrayList getCaMtAddTextList() {
		return caMtAddTextList;
	}
	/**
	 * @param caMtAddTextList the caMtAddTextList to set
	 */
	public void setCaMtAddTextList(ArrayList caMtAddTextList) {
		this.caMtAddTextList = caMtAddTextList;
	}
}
