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
 * Title		: 	PapiNetEnvelopeDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;

/**
 * PapiNetEnvelopeDTO is a data transfer object to store the 
 * Envelope details for B2B Outbound and Inbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class PapiNetEnvelopeDTO implements java.io.Serializable {
	private static final long serialVersionUID = -8927975399855659446L;
	
	private PayloadInfo payloadInfo	= null;
	private Payload payload 		= null;
	/**
	 * Default constructor.
	 */
	public PapiNetEnvelopeDTO() {
		super();
	}
	/**
	 * @return the payloadInfo
	 */
	public PayloadInfo getPayloadInfo() {
		return payloadInfo;
	}
	/**
	 * @param payloadInfo the payloadInfo to set
	 */
	public void setPayloadInfo(PayloadInfo payloadInfo) {
		this.payloadInfo = payloadInfo;
	}
	/**
	 * @return the payload
	 */
	public Payload getPayload() {
		return payload;
	}
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
}
