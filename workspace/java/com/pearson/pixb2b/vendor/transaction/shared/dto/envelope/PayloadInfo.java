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
 * Title		: 	PayloadInfo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;

import java.util.ArrayList;

/**
 * PayloadInfo is a data transfer object to store the Payload 
 * Information of Envelope for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal, Yogesh Tyagi
 */
public class PayloadInfo implements java.io.Serializable {
	private static final long serialVersionUID = -8132289908348537093L;
	
	private String testFlag					  = null;
	private ArrayList transmissionInfoList	  = null;
	private BusinessSender businessSender	  = null;
	private BusinessReceiver businessReceiver = null;
	private Document document				  = null;
	private String noOfAttachments			  = null;
	private Schema schema					  = null;
	
	private String transID					  = null;
	private String runId					  = null;
	private String senderSAN				  = null;
	private String receiverSAN				  = null;
	private String statusFlag				  = null;
	private String transType				  = null;	
	private String documentNo				  = null;
	private String transDate				  = null;
	private String xmlFileName				  = null;
	/**
	 * @return Returns the documentNo.
	 */
	public String getDocumentNo() {
		return documentNo;
	}
	/**
	 * @param documentNo The documentNo to set.
	 */
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	/**
	 * Default constructor.
	 */
	public PayloadInfo() {
		super();
	}
	/**
	 * @return the testFlag
	 */
	public String getTestFlag() {
		return testFlag;
	}
	/**
	 * @param testFlag the testFlag to set
	 */
	public void setTestFlag(String testFlag) {
		this.testFlag = testFlag;
	}
	/**
	 * @return the transmissionInfoList
	 */
	public ArrayList getTransmissionInfoList() {
		return transmissionInfoList;
	}
	/**
	 * @param transmissionInfoList the transmissionInfoList to set
	 */
	public void setTransmissionInfoList(ArrayList transmissionInfoList) {
		this.transmissionInfoList = transmissionInfoList;
	}
	/**
	 * @return the businessSender
	 */
	public BusinessSender getBusinessSender() {
		return businessSender;
	}
	/**
	 * @param businessSender the businessSender to set
	 */
	public void setBusinessSender(BusinessSender businessSender) {
		this.businessSender = businessSender;
	}
	/**
	 * @return the businessReceiver
	 */
	public BusinessReceiver getBusinessReceiver() {
		return businessReceiver;
	}
	/**
	 * @param businessReceiver the businessReceiver to set
	 */
	public void setBusinessReceiver(BusinessReceiver businessReceiver) {
		this.businessReceiver = businessReceiver;
	}
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
	/**
	 * @return the noOfAttachments
	 */
	public String getNoOfAttachments() {
		return noOfAttachments;
	}
	/**
	 * @param noOfAttachments the noOfAttachments to set
	 */
	public void setNoOfAttachments(String noOfAttachments) {
		this.noOfAttachments = noOfAttachments;
	}
	/**
	 * @return the schema
	 */
	public Schema getSchema() {
		return schema;
	}
	/**
	 * @param schema the schema to set
	 */
	public void setSchema(Schema schema) {
		this.schema = schema;
	}
	/**
	 * @return the transID
	 */
	public String getTransID() {
		return transID;
	}
	/**
	 * @param transID the transID to set
	 */
	public void setTransID(String transID) {
		this.transID = transID;
	}
	/**
	 * @return the senderSAN
	 */
	public String getSenderSAN() {
		return senderSAN;
	}
	/**
	 * @param senderSAN the senderSAN to set
	 */
	public void setSenderSAN(String senderSAN) {
		this.senderSAN = senderSAN;
	}
	/**
	 * @return the receiverSAN
	 */
	public String getReceiverSAN() {
		return receiverSAN;
	}
	/**
	 * @param receiverSAN the receiverSAN to set
	 */
	public void setReceiverSAN(String receiverSAN) {
		this.receiverSAN = receiverSAN;
	}
	/**
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return statusFlag;
	}
	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * @return the xmlFileName
	 */
	public String getXmlFileName() {
		return xmlFileName;
	}
	/**
	 * @param xmlFileName the xmlFileName to set
	 */
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	/**
	 * @return the runId
	 */
	public String getRunId() {
		return runId;
	}
	/**
	 * @param runId the runId to set
	 */
	public void setRunId(String runId) {
		this.runId = runId;
	}
	/**
	 * @return Returns the transDate.
	 */
	public String getTransDate() {
		return transDate;
	}
	/**
	 * @param transDate The transDate to set.
	 */
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
}
