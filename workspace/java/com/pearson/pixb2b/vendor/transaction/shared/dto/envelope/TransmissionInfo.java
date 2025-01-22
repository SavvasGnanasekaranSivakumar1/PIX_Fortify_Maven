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
 * Title		: 	TransmissionInfo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;


/**
 * TransmissionInfo is a data transfer object to store the Transmission 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class TransmissionInfo implements java.io.Serializable {
	private static final long serialVersionUID = 8447582627310574130L;
	
	private String sequenceNumber = null;
	private String id		  	  = null;
	private String timeStamp 	  = null;
	private TransmissionSender transmissionSender = null;
	private TransmissionReceiver transmissionReceiver = null;
	/**
	 * Default constructor.
	 */
	public TransmissionInfo() {
		super();
	}
	/**
	 * @return the sequenceNumber
	 */
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return the transmissionSender
	 */
	public TransmissionSender getTransmissionSender() {
		return transmissionSender;
	}
	/**
	 * @param transmissionSender the transmissionSender to set
	 */
	public void setTransmissionSender(TransmissionSender transmissionSender) {
		this.transmissionSender = transmissionSender;
	}
	/**
	 * @return the transmissionReceiver
	 */
	public TransmissionReceiver getTransmissionReceiver() {
		return transmissionReceiver;
	}
	/**
	 * @param transmissionReceiver the transmissionReceiver to set
	 */
	public void setTransmissionReceiver(TransmissionReceiver transmissionReceiver) {
		this.transmissionReceiver = transmissionReceiver;
	}
}
