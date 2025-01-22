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
 * Title		: 	CaseShow.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CaseShow is a data transfer object to store the specification Binding   
 * CaseMaking CaseShow Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseShow implements java.io.Serializable {
	private static final long serialVersionUID = 1107176671090613967L;
	
	private String cShowType 	= null;
	private CShowValue cShvalue = null;
	/**
	 * Default constructor.
	 */
	public CaseShow() {
		super();
	}
	/**
	 * @return the cShowType
	 */
	public String getCShowType() {
		return cShowType;
	}
	/**
	 * @param showType the cShowType to set
	 */
	public void setCShowType(String showType) {
		cShowType = showType;
	}
	/**
	 * @return the cShvalue
	 */
	public CShowValue getCShvalue() {
		return cShvalue;
	}
	/**
	 * @param shvalue the cShvalue to set
	 */
	public void setCShvalue(CShowValue shvalue) {
		cShvalue = shvalue;
	}
}
