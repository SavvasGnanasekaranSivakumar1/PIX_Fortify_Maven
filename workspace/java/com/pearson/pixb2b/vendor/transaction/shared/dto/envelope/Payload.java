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
 * Title		: 	Payload.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   27 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;


/**
 * Payload is a data transfer object to store the Payload 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class Payload implements java.io.Serializable {
	private static final long serialVersionUID = 5711385551355963572L;
	
	private BusinessDocument businessDocument = null;
	/**
	 * Default constructor.
	 */
	public Payload() {
		super();
		businessDocument = new BusinessDocument();
	}
	/**
	 * @return the businessDocument
	 */
	public BusinessDocument getBusinessDocument() {
		return businessDocument;
	}
	/**
	 * @param businessDocument the businessDocument to set
	 */
	public void setBusinessDocument(BusinessDocument businessDocument) {
		this.businessDocument = businessDocument;
	}
}
