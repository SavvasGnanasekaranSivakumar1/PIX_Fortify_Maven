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
 * Title		: 	PacIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * PacIdentifier is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Identifier details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PacIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = 985322420405748423L;
	
	private String idCodeType 	= null;
	private String idType		= null;
	private String idFormatType	= null;
	private String idValue		= null;
	/**
	 * Default constructor.
	 */
	public PacIdentifier() {
		super();
	}
	/**
	 * @return the idCodeType
	 */
	public String getIdCodeType() {
		return idCodeType;
	}
	/**
	 * @param idCodeType the idCodeType to set
	 */
	public void setIdCodeType(String idCodeType) {
		this.idCodeType = idCodeType;
	}
	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * @return the idFormatType
	 */
	public String getIdFormatType() {
		return idFormatType;
	}
	/**
	 * @param idFormatType the idFormatType to set
	 */
	public void setIdFormatType(String idFormatType) {
		this.idFormatType = idFormatType;
	}
	/**
	 * @return the idValue
	 */
	public String getIdValue() {
		return idValue;
	}
	/**
	 * @param idValue the idValue to set
	 */
	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}
}