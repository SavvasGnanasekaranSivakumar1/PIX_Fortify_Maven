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
 * Title		: 	SCQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * PPUValue is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class PPUValue implements java.io.Serializable {
	private static final long serialVersionUID = -2824649467772464159L;
	private String ppValue = null;
	private String ppUOM = null;
	
	/**
	 * Default constructor.
	 */
	public PPUValue() {
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