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
 * Title		: 	SCSubClasn.java
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
 * SCSubClasn is a data transfer object to store the Specification 
 * Component SubClassification details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCSubClasn implements java.io.Serializable {
	private static final long serialVersionUID = -4796387904698802645L;
	
	private SCSubClassCode scSubClassCode 	= null;
	private ArrayList scSubClassDespList	= null;
	/**
	 * Default constructor.
	 */
	public SCSubClasn() {
		super();
	}
	/**
	 * @return the scSubClassCode
	 */
	public SCSubClassCode getScSubClassCode() {
		return scSubClassCode;
	}
	/**
	 * @param scSubClassCode the scSubClassCode to set
	 */
	public void setScSubClassCode(SCSubClassCode scSubClassCode) {
		this.scSubClassCode = scSubClassCode;
	}
	/**
	 * @return the scSubClassDespList
	 */
	public ArrayList getScSubClassDespList() {
		return scSubClassDespList;
	}
	/**
	 * @param scSubClassDespList the scSubClassDespList to set
	 */
	public void setScSubClassDespList(ArrayList scSubClassDespList) {
		this.scSubClassDespList = scSubClassDespList;
	}
}
