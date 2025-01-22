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
 * Title		: 	AssemblyInst.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * AssemblyInst is a data transfer object to store the Specification 
 * Assembly Instructionsdetails and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class AssemblyInst implements java.io.Serializable {
	private static final long serialVersionUID = -7145460721153023653L;
	
	private String assemblyInst  = null;
	/**
	 * Default constructor.
	 */
	public AssemblyInst() {
		super();
	}
	/**
	 * @return the assemblyInst
	 */
	public String getAssemblyInst() {
		return assemblyInst;
	}
	/**
	 * @param assemblyInst the assemblyInst to set
	 */
	public void setAssemblyInst(String assemblyInst) {
		this.assemblyInst = assemblyInst;
	}
}
