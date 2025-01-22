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
 * Title		: 	SCSpecRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCSpecRef is a data transfer object to store the Specification Component 
 * SpecReference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCSpecRef implements java.io.Serializable {
	private static final long serialVersionUID = -7969968202981440991L;
	
	private String specRefType	= null;
	private String assignedBy	= null;
	private String scSpecRefVal	= null;
	/**
	 * Default constructor.
	 */
	public SCSpecRef() {
		super();
	}
	/**
	 * @return the specRefType
	 */
	public String getSpecRefType() {
		return specRefType;
	}
	/**
	 * @param specRefType the specRefType to set
	 */
	public void setSpecRefType(String specRefType) {
		this.specRefType = specRefType;
	}
	/**
	 * @return the assignedBy
	 */
	public String getAssignedBy() {
		return assignedBy;
	}
	/**
	 * @param assignedBy the assignedBy to set
	 */
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	/**
	 * @return the scSpecRefVal
	 */
	public String getScSpecRefVal() {
		return scSpecRefVal;
	}
	/**
	 * @param scSpecRefVal the scSpecRefVal to set
	 */
	public void setScSpecRefVal(String scSpecRefVal) {
		this.scSpecRefVal = scSpecRefVal;
	}
}
