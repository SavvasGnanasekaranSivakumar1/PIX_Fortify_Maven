/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	PPValueDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		21 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class PPValueDS implements Serializable{

	
	private String ppValue = null;
	private String ppUOM = null;
	
	/**
	 * Default constructor.
	 */
	public PPValueDS() {
		super();
	}

	/**
	 * @return Returns the ppUOM.
	 */
	public String getPpUOM() {
		return ppUOM;
	}

	/**
	 * @param ppUOM The ppUOM to set.
	 */
	public void setPpUOM(String ppUOM) {
		this.ppUOM = ppUOM;
	}

	/**
	 * @return Returns the ppValue.
	 */
	public String getPpValue() {
		return ppValue;
	}

	/**
	 * @param ppValue The ppValue to set.
	 */
	public void setPpValue(String ppValue) {
		this.ppValue = ppValue;
	}
	
}
