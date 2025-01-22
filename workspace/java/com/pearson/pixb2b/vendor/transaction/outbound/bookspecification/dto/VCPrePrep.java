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
 * Title		: 	VCPrePrep.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * VCPrePrep is a data transfer object to store the Specification NonPress Component 
 * Video Cassette Label Characteristics PressPrep details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class VCPrePrep implements java.io.Serializable {
	private static final long serialVersionUID = 5964462102240825200L;
	
	private String vcPrepOutType 	  = null;
	private ArrayList vcPrPrepInpList = null;
	/**
	 * Default constructor.
	 */
	public VCPrePrep() {
		super();
	}
	/**
	 * @return the vcPrepOutType
	 */
	public String getVcPrepOutType() {
		return vcPrepOutType;
	}
	/**
	 * @param vcPrepOutType the vcPrepOutType to set
	 */
	public void setVcPrepOutType(String vcPrepOutType) {
		this.vcPrepOutType = vcPrepOutType;
	}
	/**
	 * @return the vcPrPrepInpList
	 */
	public ArrayList getVcPrPrepInpList() {
		return vcPrPrepInpList;
	}
	/**
	 * @param vcPrPrepInpList the vcPrPrepInpList to set
	 */
	public void setVcPrPrepInpList(ArrayList vcPrPrepInpList) {
		this.vcPrPrepInpList = vcPrPrepInpList;
	}
}
