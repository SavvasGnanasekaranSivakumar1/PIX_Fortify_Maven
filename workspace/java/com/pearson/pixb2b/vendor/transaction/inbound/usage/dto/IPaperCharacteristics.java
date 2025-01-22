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
 * Title		: 	PaperCharacteristics.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * PaperCharacteristics is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class IPaperCharacteristics implements java.io.Serializable {	
	
	private static final long serialVersionUID = -5324106298505559858L;
	
	private IBasisWeight iBasisWt = null;

	/**
	 * Default constructor.
	 */
	public IPaperCharacteristics() {
		super();
	}

	/**
	 * @return Returns the iBasisWt.
	 */
	public IBasisWeight getIBasisWt() {
		return iBasisWt;
	}

	/**
	 * @param basisWt The iBasisWt to set.
	 */
	public void setIBasisWt(IBasisWeight basisWt) {
		iBasisWt = basisWt;
	}

	

	
	
}
