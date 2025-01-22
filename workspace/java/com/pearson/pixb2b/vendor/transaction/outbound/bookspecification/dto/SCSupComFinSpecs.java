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
 * Title		: 	SCSupComFinSpecs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SCSupComFinSpecs is a data transfer object to store the Specification Component 
 * Supplied Component details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCSupComFinSpecs implements java.io.Serializable {
	private static final long serialVersionUID = -8131694274891545821L;
	
	private SCFinSpecs scFinSpecs		= null;
	private ArrayList scFinPrepList 	= null;
	private ArrayList scSComAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public SCSupComFinSpecs() {
		super();
	}
	/**
	 * @return the scFinSpecs
	 */
	public SCFinSpecs getScFinSpecs() {
		return scFinSpecs;
	}
	/**
	 * @param scFinSpecs the scFinSpecs to set
	 */
	public void setScFinSpecs(SCFinSpecs scFinSpecs) {
		this.scFinSpecs = scFinSpecs;
	}
	/**
	 * @return the scFinPrepList
	 */
	public ArrayList getScFinPrepList() {
		return scFinPrepList;
	}
	/**
	 * @param scFinPrepList the scFinPrepList to set
	 */
	public void setScFinPrepList(ArrayList scFinPrepList) {
		this.scFinPrepList = scFinPrepList;
	}
	/**
	 * @return the scSComAddTextList
	 */
	public ArrayList getScSComAddTextList() {
		return scSComAddTextList;
	}
	/**
	 * @param scSComAddTextList the scSComAddTextList to set
	 */
	public void setScSComAddTextList(ArrayList scSComAddTextList) {
		this.scSComAddTextList = scSComAddTextList;
	}
}
