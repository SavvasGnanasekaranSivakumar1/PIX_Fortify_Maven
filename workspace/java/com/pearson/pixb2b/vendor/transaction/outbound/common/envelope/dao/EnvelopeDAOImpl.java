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
 * Title		: 	EnvelopeDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal 	27 Oct, 2009	Initial version
 * 2.0		Yogesh Tyagi 	04 Nov, 2009	Updated for PayloadInfo list
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessReceiver;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessReceiverPI;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessSender;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessSenderPI;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.DateEnvelope;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Document;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.DocumentDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PayloadInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Schema;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionReceiver;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionReceiverPI;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionSender;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionSenderPI;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.DateUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * EnvelopeDAOImpl is an implementation class to communicate with 
 * the database and get the Envelope details for a particular Transaction.
 * 
 * @author Ashish Agrawal
 */
public class EnvelopeDAOImpl implements IEnvelopeDAO{
	//NOTE:- Do not change the SELECT parameters for these Queries as both are using the same ResultSet.
	
	/*private static final String qry_sel_pix_xmlread_log ="SELECT (select vendor_trans_id from "+IConfigConstants.PIX_DB_SCHEMA_NAME+".Pix_xml_trans_mapping "
	+ " where trans_id=pix_trans_id ) trans_id, RUN_ID run_id, PUB_SAN sender_san, SAN receiver_san, XML_READ_FLAG status_flag, PROCESS_TYPE trans_type,CREATION_DATE_TIME trans_Date "
	+ " FROM PIX_XMLREAD_LOG  WHERE TRANS_TYPE = ? AND ACK_FLAG = ? AND SAN = ? AND TRANS_ID IS NOT NULL "
	+ "AND PROCESS_TYPE IS NOT NULL AND PROCESS_TYPE != 'ACK' ORDER BY TRANS_ID" ; */
	
	private static final String qry_sel_pix_xmlread_log ="SELECT TRANS_ID trans_id, RUN_ID run_id, PUB_SAN sender_san,"
		+ " SAN receiver_san, XML_READ_FLAG status_flag, PROCESS_TYPE trans_type,DOCUMENT_NUMBER doc_No,DOCUMENT_DATE doc_Date "
		+ " FROM PIX_XMLREAD_LOG  WHERE TRANS_TYPE = ? AND ACK_FLAG = ? AND SAN = ? AND TRANS_ID IS NOT NULL "
		+ " AND PROCESS_TYPE IS NOT NULL AND PROCESS_TYPE != 'ACK' 	UNION "
		+ " SELECT TRANS_ID trans_id, RUN_ID run_id, PUB_SAN sender_san,"
		+ " SAN receiver_san, XML_READ_FLAG status_flag, PROCESS_TYPE trans_type,"
		+ " DOCUMENT_NUMBER doc_No,DOCUMENT_DATE doc_Date FROM PIX_XMLREAD_LOG pxl"
		+ " WHERE TRANS_TYPE = ? AND ACK_FLAG = ? AND not exists (select SAN "
		+ " FROM pix_san_trans_mapping pstm WHERE pstm.san=pxl.san)	AND FOLDER_NAME = ? "
		+ " AND TRANS_ID IS NOT NULL  AND PROCESS_TYPE IS NOT NULL AND PROCESS_TYPE != 'ACK' ORDER BY TRANS_ID" ;
		
	private static final String qry_sel_pix_xmlgen_log = "SELECT pxl.TRANS_ID trans_id, pxl.RUN_ID run_id, pxl.PUB_SAN sender_san, pxl.SAN receiver_san," 
		+" pxl.GEN_STATUS_FLAG status_flag, pxl.PROCESS_TYPE trans_type from pix_xmlgen_log pxl,pix_po_list_summary"
		+" where pxl.transref_id_1=po_id"
		+" and pxl.transref_id_2=Po_version"
		+" and pxl.process_type in ('POR','PLA','SIP')"
		+" AND status_id <> 22"
		+" AND pxl.TRANS_ID IS NOT NULL"
		+" AND pxl.TRANS_TYPE = ?"
		+" AND pxl.GEN_STATUS_FLAG = ?"
		+" AND pxl.SAN = ?"
		+" AND pxl.PROCESS_TYPE = ?"
		+" union all"
		+" SELECT pxl.TRANS_ID trans_id, pxl.RUN_ID run_id, pxl.PUB_SAN sender_san, pxl.SAN receiver_san,"
		+" pxl.GEN_STATUS_FLAG status_flag, pxl.PROCESS_TYPE trans_type  from pix_xmlgen_log pxl,pix_book_spec_summary"
		+" where pxl.transref_id_1=spec_id"
		+" and pxl.transref_id_2=spec_version"
		+" and pxl.process_type='BSP'"
		+" AND pxl.TRANS_ID IS NOT NULL"
		+" AND pxl.TRANS_TYPE = ?"
		+" AND pxl.GEN_STATUS_FLAG = ?"
		+" AND pxl.SAN = ?"
		+" AND pxl.PROCESS_TYPE = ?"
		+" union all"
		+" SELECT pxl.TRANS_ID trans_id, pxl.RUN_ID run_id, pxl.PUB_SAN sender_san, pxl.SAN receiver_san,"
		+" pxl.GEN_STATUS_FLAG status_flag, pxl.PROCESS_TYPE trans_type  from pix_xmlgen_log pxl"
		+" where pxl.process_type not in ('POR','PLA','BSP','SIP')"
		+" AND pxl.TRANS_ID IS NOT NULL"
		+" AND pxl.TRANS_TYPE = ?"
		+" AND pxl.GEN_STATUS_FLAG = ?"
		+" AND pxl.SAN = ?"
		+" AND pxl.PROCESS_TYPE = ?"
		+" ORDER BY TRANS_ID";	
	
		private static final String qry_sel_vendor_trans_id = "SELECT vendor_trans_id, process_type, trans_hist_no FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".Pix_xml_trans_mapping WHERE pix_trans_id = ?";

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao.IEnvelopeDAO#getEnvelopePayloadInfo(java.sql.Connection, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList getEnvelopePayloadInfo(Connection dbCon, String vendorSAN, String transactionType, String transactionName) throws SQLException, B2BException{
		PreparedStatement prepStmt			= null;
		ResultSet resultSet					= null;
		PreparedStatement prepStmt1			= null;
		ResultSet resultSet1				= null;
		String sqlQuery						= null;
		String qryParams					= null;
		
		String transID						= null;
		String vendorTransId                = null;
		String senderSAN					= null;
		String runId						= null;
		String receiverSAN					= null;
		String statusFlag					= null;
		String transType					= null;
		String transDate   				    = null;
		String documentNumber               = null;
		B2BHelper b2bHelper					= null;
		
		ArrayList payloadInfoList			= null;
		PayloadInfo payloadInfo 			= null;
		
		BusinessReceiver businessReceiver 	= null;
		BusinessSender businessSender 		= null;
		Document document 					= null;
		Schema schema 						= null;
		ArrayList transmissionInfoList	 	= null; 
		ArrayList piBusinessReceiverList	= null;
		BusinessReceiverPI businessReceiverPI= null;
		ArrayList piBusinessSenderList 		= null;
		BusinessSenderPI businessSenderPI 	= null;
		TransmissionInfo transmissionInfo 	= null;
		TransmissionReceiver transmissionReceiver = null;
		TransmissionSender transmissionSender= null;
		ArrayList piTransmissionReceiverList= null;
		TransmissionReceiverPI transmissionReceiverPI = null;
		ArrayList piTransmissionSenderList 	= null;
		TransmissionSenderPI transmissionSenderPI = null;
		DocumentDate documentDate 			= null;
		DateEnvelope dateEnvl 				= null;
		
		String currentTimestamp 			= null;
		String dateStr 						= null;
		String timeStr 						= null;
		
		try {
			B2BLogger.debug("EnvelopeDAOImpl.getEnvelopePayloadInfo() method called");			
			
			if(transactionType != null && IPixB2BConstants.transType_ACK.equalsIgnoreCase(transactionType.trim())){
				sqlQuery = qry_sel_pix_xmlread_log;
				prepStmt = dbCon.prepareStatement(sqlQuery);
				prepStmt.clearParameters();
				prepStmt.setString(IPixB2BConstants.ONE, IPixB2BConstants.B2B);
				prepStmt.setString(IPixB2BConstants.TWO, IPixB2BConstants.status_KU);
				prepStmt.setString(IPixB2BConstants.THREE, vendorSAN);  //SAN
				prepStmt.setString(IPixB2BConstants.FOUR, IPixB2BConstants.B2B); //union query params
				prepStmt.setString(IPixB2BConstants.FIVE, IPixB2BConstants.status_KU);
				prepStmt.setString(IPixB2BConstants.SIX, vendorSAN);		//Folder_Name		
				qryParams = IPixB2BConstants.B2B+","+IPixB2BConstants.status_KU+","+vendorSAN+","+IPixB2BConstants.B2B+","+IPixB2BConstants.status_KU;
			}else{
				sqlQuery = qry_sel_pix_xmlgen_log;
				prepStmt = dbCon.prepareStatement(sqlQuery);
				prepStmt.clearParameters();
				prepStmt.setString(IPixB2BConstants.ONE, IPixB2BConstants.B2B);
				prepStmt.setString(IPixB2BConstants.TWO, IPixB2BConstants.status_GU);
				prepStmt.setString(IPixB2BConstants.THREE, vendorSAN);
				prepStmt.setString(IPixB2BConstants.FOUR, transactionType);
				prepStmt.setString(IPixB2BConstants.FIVE, IPixB2BConstants.B2B);
				prepStmt.setString(IPixB2BConstants.SIX, IPixB2BConstants.status_GU);
				prepStmt.setString(IPixB2BConstants.SEVEN, vendorSAN);
				prepStmt.setString(IPixB2BConstants.EIGHT, transactionType);
				prepStmt.setString(IPixB2BConstants.NINE, IPixB2BConstants.B2B);
				prepStmt.setString(IPixB2BConstants.TEN, IPixB2BConstants.status_GU);
				prepStmt.setString(IPixB2BConstants.ELEVEN, vendorSAN);
				prepStmt.setString(IPixB2BConstants.TWELVE, transactionType);
				
				qryParams = IPixB2BConstants.B2B+","+IPixB2BConstants.status_GU+","+vendorSAN+","+transactionType;
			}			
			resultSet = prepStmt.executeQuery();
			
			payloadInfoList = new ArrayList();
			
			while(resultSet.next()) {
				transID		= resultSet.getString("trans_id");	
				runId		= resultSet.getString("run_id");
				senderSAN	= resultSet.getString("sender_san");				
				receiverSAN	= resultSet.getString("receiver_san");
				statusFlag	= resultSet.getString("status_flag");
				transType	= resultSet.getString("trans_type");
				if(transactionType != null && IPixB2BConstants.transType_ACK.equalsIgnoreCase(transactionType.trim())){
				documentNumber = resultSet.getString("doc_No");
				transDate    = resultSet.getString("doc_Date");
				}
				
				if((transID != null && !"".equals(transID.trim())) 
						&& (runId != null && !"".equals(runId.trim())) 
						&& (senderSAN != null && !"".equals(senderSAN.trim()))
						&& (receiverSAN != null && !"".equals(receiverSAN.trim()))
						&& (statusFlag != null && !"".equals(statusFlag.trim()))
						&& (transType != null && !"".equals(transType.trim())))
				{
					
					transID 	= transID.trim();
					runId 		= runId.trim();
					senderSAN 	= senderSAN.trim();
					receiverSAN = receiverSAN.trim();
					statusFlag 	= statusFlag.trim();
					transType 	= transType.trim();
					
					senderSAN 	= senderSAN.replaceAll("-","");
					receiverSAN = receiverSAN.replaceAll("-","");
					
					b2bHelper = new B2BHelper();
					
					//Read Vendor Trans ID
					if(transactionType != null && IPixB2BConstants.transType_ACK.equalsIgnoreCase(transactionType.trim())){
						sqlQuery = qry_sel_vendor_trans_id;    //Convert pix_trans_id to Vendor_Trans_Id
						prepStmt1 = dbCon.prepareStatement(sqlQuery);
						prepStmt1.clearParameters();
						prepStmt1.setString(IPixB2BConstants.ONE, transID);
						qryParams = transID;
						resultSet1 = prepStmt1.executeQuery();
						while(resultSet1.next()){
							String processType = resultSet1.getString("process_type");
							String transHistNo = resultSet1.getString("trans_hist_no");
							if((IPixB2BConstants.transType_OST.equalsIgnoreCase(processType) && (null==transHistNo || "".equals(transHistNo)))||
								(!IPixB2BConstants.transType_OST.equalsIgnoreCase(processType)&& (null==transHistNo || "".equals(transHistNo)))){
								vendorTransId = resultSet1.getString("vendor_trans_id");
							}else{
								vendorTransId = transHistNo;
							}
						}
						DBUtils.close(prepStmt1);
						DBUtils.close(resultSet1);	
					}
					payloadInfo = new PayloadInfo();
					payloadInfo.setTransID(transID);
					payloadInfo.setRunId(runId);
					payloadInfo.setSenderSAN(senderSAN);
					payloadInfo.setReceiverSAN(receiverSAN);
					payloadInfo.setStatusFlag(statusFlag);
					payloadInfo.setTransType(transType);
					if(transactionType != null && IPixB2BConstants.transType_ACK.equalsIgnoreCase(transactionType.trim())){
						if(!org.apache.commons.lang.StringUtils.isEmpty(documentNumber))
						payloadInfo.setDocumentNo(documentNumber.trim());
						if(!org.apache.commons.lang.StringUtils.isEmpty(transDate))
						payloadInfo.setTransDate(transDate.trim());
						payloadInfo.setXmlFileName(senderSAN+"_"+receiverSAN+"_"+b2bHelper.getShortTransType(transactionType, null)+"_"+vendorTransId+IPixB2BConstants.fileExtn_XML);
					}else{
						payloadInfo.setXmlFileName(senderSAN+"_"+receiverSAN+"_"+b2bHelper.getShortTransType(transactionType, null)+"_"+transID+IPixB2BConstants.fileExtn_XML);
					}
					transmissionSenderPI = new TransmissionSenderPI();
					transmissionSenderPI.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					transmissionSenderPI.setPartyIdentifierValue(senderSAN);
					piTransmissionSenderList = new ArrayList();					
					piTransmissionSenderList.add(transmissionSenderPI);
					
					/*String sanId1 = (senderSAN.trim()).substring(0, 7);
					transmissionSenderPI = new TransmissionSenderPI();
					transmissionSenderPI.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					transmissionSenderPI.setPartyIdentifierValue(sanId1);
					piTransmissionSenderList.add(transmissionSenderPI);*/
					
					transmissionSender = new TransmissionSender();
					transmissionSender.setPIdTransmissionSenderList(piTransmissionSenderList);
					
					transmissionReceiverPI = new TransmissionReceiverPI();
					transmissionReceiverPI.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					transmissionReceiverPI.setPartyIdentifierValue(receiverSAN);
					piTransmissionReceiverList = new ArrayList();
					piTransmissionReceiverList.add(transmissionReceiverPI);
					transmissionReceiver = new TransmissionReceiver();
					transmissionReceiver.setPIdTransmissionReceiverList(piTransmissionReceiverList);
					
					businessSenderPI = new BusinessSenderPI();
					businessSenderPI.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					businessSenderPI.setPartyIdentifierValue(senderSAN);					
					piBusinessSenderList = new ArrayList();
					piBusinessSenderList.add(businessSenderPI);
					
					/*String sanId2 = (senderSAN.trim()).substring(0, 7);
					businessSenderPI = new BusinessSenderPI();
					businessSenderPI.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					businessSenderPI.setPartyIdentifierValue(sanId2);
					piBusinessSenderList.add(businessSenderPI);*/
					
					businessSender = new BusinessSender();
					businessSender.setPIdBusinessSenderList(piBusinessSenderList);
					
					businessReceiverPI = new BusinessReceiverPI();
					businessReceiverPI.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					businessReceiverPI.setPartyIdentifierValue(receiverSAN);
					piBusinessReceiverList	= new ArrayList();
					piBusinessReceiverList.add(businessReceiverPI);
					businessReceiver = new BusinessReceiver();
					businessReceiver.setPIdBusinessReceiverList(piBusinessReceiverList);
					
					document = new Document();
					document.setDocumentName(transactionName);
					document.setDocumentNumber(transID);
					documentDate = new DocumentDate();
					dateEnvl = new DateEnvelope();
					
					currentTimestamp = DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_yyyy_MM_dd);
					int idxT = currentTimestamp.indexOf('T');
					dateStr = currentTimestamp.substring(0,idxT);
					timeStr = currentTimestamp.substring(idxT+1);
					if(dateStr != null){
						String[] sDate = dateStr.split("-");						
						dateEnvl.setYear(sDate[0]);
						dateEnvl.setMonth(sDate[1]);
						dateEnvl.setDay(sDate[2]);						
						documentDate.setDate(dateEnvl);
					}
					if (timeStr != null)
						documentDate.setTime(timeStr);
					document.setDocumentDate(documentDate);
					
					schema = new Schema();
					schema.setVersion(IConfigConstants.pne_Schema_Version);
					schema.setBuild(IConfigConstants.pne_Schema_Build);
					schema.setSchemaVal(IConfigConstants.pne_Schema_URL+"/"+IConfigConstants.pne_Schema_Version+"/"+transactionName+IConfigConstants.pne_Schema_Version.toUpperCase()+IPixB2BConstants.fileExtn_XSD);
					
					transmissionInfo = new TransmissionInfo();
					transmissionInfoList = new ArrayList(); 
					transmissionInfo.setSequenceNumber(IConfigConstants.pne_TransmissionInfo_SequenceNumber);
					transmissionInfo.setId(transID);
					transmissionInfo.setTimeStamp(currentTimestamp);
					transmissionInfo.setTransmissionSender(transmissionSender);
					transmissionInfo.setTransmissionReceiver(transmissionReceiver);				
					transmissionInfoList.add(transmissionInfo);
					
					payloadInfo.setTestFlag(IConfigConstants.pne_PayloadInfo_TestFlag);
					payloadInfo.setTransmissionInfoList(transmissionInfoList);
					payloadInfo.setBusinessSender(businessSender);
					payloadInfo.setBusinessReceiver(businessReceiver);
					payloadInfo.setDocument(document);
					payloadInfo.setNoOfAttachments(""+IPixB2BConstants.ZERO);
					payloadInfo.setSchema(schema);
					
					payloadInfoList.add(payloadInfo);
				}else{
					B2BLogger.info("EnvelopeDAOImpl.getEnvelopePayloadInfo() : transID = "+transID
							+", runId = "+runId
							+", senderSAN = "+senderSAN
							+", receiverSAN = "+receiverSAN
							+", statusFlag = "+statusFlag
							+", transType = "+transType
							+", documentNumber = "+documentNumber
							+ ",transDate = "+transDate);
				}
			}	
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);	
			B2BLogger.debug("EnvelopeDAOImpl.getEnvelopePayloadInfo() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			throw new B2BException("B2BException :: " + StringUtils.getStackTrace(e));
		} finally{
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			
			sqlQuery		= null;
			qryParams		= null;
			b2bHelper		= null;
			senderSAN		= null;
			receiverSAN		= null;
			payloadInfo 	= null;
			businessReceiver= null;
			businessSender 	= null;
			document 		= null;
			schema 	 		= null;
			transmissionInfoList 	= null; 
			piBusinessReceiverList	= null;
			businessReceiverPI  	= null;
			piBusinessSenderList	= null;
			businessSenderPI= null;
			transmissionInfo= null;
			transmissionReceiver	= null;
			transmissionSender  	= null;
			piTransmissionReceiverList = null;
			transmissionReceiverPI 	= null;
			piTransmissionSenderList= null;
			transmissionSenderPI	= null;
			documentDate 	= null;
			dateEnvl 		= null;
			currentTimestamp= null;
			dateStr 		= null;
			timeStr 		= null;
		}
		return payloadInfoList;
	}
}