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
 * Title		: 	AdditionalText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * AdditionalText is a data transfer object to store the DeliveryMessageBook Header 
 * Additional Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class AdditionalText implements java.io.Serializable {
	private static final long serialVersionUID = 2689158352252958541L;
	
	private String additionalText = null;
	/**
	 * Default constructor.
	 */
	public AdditionalText() {
		super();
	}
	/**
	 * @return the additionalText
	 */
	public String getAdditionalText() {
		return additionalText;
	}
	/**
	 * @param additionalText the additionalText to set
	 */
	public void setAdditionalText(String additionalText) {
		this.additionalText = additionalText;
	}
}