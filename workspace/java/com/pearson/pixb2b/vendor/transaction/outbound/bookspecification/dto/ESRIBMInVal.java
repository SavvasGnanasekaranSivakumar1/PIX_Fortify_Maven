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
 * Title		: 	ESRIBMInVal.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ESRIBMInVal is a data transfer object to store the Endsheet Reinforcement Binding
 * Material Characteristics BasisWeight IncrementalValue details of SpecBinding 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESRIBMInVal implements java.io.Serializable {
	private static final long serialVersionUID = 5525208370710057354L;
	
	private String incrValue		= null;
	private String uomincrValue		= null;
	/**
	 * Default constructor.
	 */
	public ESRIBMInVal() {
		super();
	}
	/**
	 * @return the incrValue
	 */
	public String getIncrValue() {
		return incrValue;
	}
	/**
	 * @param incrValue the incrValue to set
	 */
	public void setIncrValue(String incrValue) {
		this.incrValue = incrValue;
	}
	/**
	 * @return the uomincrValue
	 */
	public String getUomincrValue() {
		return uomincrValue;
	}
	/**
	 * @param uomincrValue the uomincrValue to set
	 */
	public void setUomincrValue(String uomincrValue) {
		this.uomincrValue = uomincrValue;
	}
}
