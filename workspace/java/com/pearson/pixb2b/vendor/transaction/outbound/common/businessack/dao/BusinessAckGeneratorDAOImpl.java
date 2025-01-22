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
 * Title		: 	BusinessAckGeneratorDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	16 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao.EnvelopeDAOImpl;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.BusinessAckDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.BusinessAckDateDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.BusinessAcknowledgementDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.Document;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentDateDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentError;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessDocument;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Payload;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PayloadInfo;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * BusinessAckGeneratorDAOImpl is an implementation class to communicate with 
 * the database and get the BusinessAcknowledgement Success/Failure transaction details for 
 * all Inbound Transactions received from vendors.
 * 
 * @author Abhilasha Nigam
 */
public class BusinessAckGeneratorDAOImpl extends EnvelopeDAOImpl implements IBusinessAckGeneratorDAO {
	
	private static final String qry_sel_ba_failure_errors = "SELECT DISTINCT elog.error_id err_code, (SELECT ERROR_DESC FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_CODE WHERE error_id = elog.error_id) err_desc"
	+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_LOG elog"
	+" WHERE run_id = ?";
	
	//BA OutBound queries
	private static final String qry_sel_trans_id = "SELECT vendor_trans_id,trans_hist_no FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".Pix_xml_trans_mapping WHERE pix_trans_id = ?";
	
	private static final String qry_sel_ba_genr_transactions = "SELECT count(1) error_count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_error_log pel"
	+ " WHERE pel.tranref_id_1 = ? "
	+ "AND pel.tranref_label_1 = 'TRANS_ID' AND pel.run_id = ? "  ;
	
	/**private static final String qry_sel_ba_error_desc = "SELECT DISTINCT elog.error_id err_code,(SELECT ERROR_DESC" 
	 + " FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_CODE WHERE error_id = elog.error_id) err_desc FROM "
	 + "PIX_ERROR_LOG elog  WHERE run_id = ? AND tranref_id_1 = ? AND tranref_label_1 = 'TRANS_ID' "; */
	
	
	/*private static final String qry_sel_ba_error_desc = "SELECT DISTINCT elog.error_id error_id,"
	 + "(SELECT ERROR_code FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_CODE "
	 + " WHERE error_id = elog.error_id) err_code,(SELECT ERROR_DESC  FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_CODE "
	 + " WHERE error_id = elog.error_id) err_desc FROM  "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_LOG elog " 
	 + " WHERE run_id = ? AND tranref_id_1 = ? AND tranref_label_1 = 'TRANS_ID' ";*/
	
	private static final String qry_sel_ba_error_desc = "SELECT DISTINCT elog.error_id error_id,error_code err_code, additional_desc err_desc"
		+" FROM  PIX_ERROR_LOG elog  , PIX_ERROR_CODE pec " +
		" WHERE elog.run_id = ? AND elog.tranref_id_1 = ? AND elog.tranref_label_1 = 'TRANS_ID' "+
		" and pec.ERROR_ID = elog.error_id ";
	
	/**
	 private static final String qry_sel_ba_genr_transactions = "SELECT trans_id,process_type,san,pub_san,file_name,complete_path,run_id,"
	 + "(select count(1) from pix_error_log pel WHERE pel.tranref_id_1 = pxl.trans_id "
	 + "AND pel.tranref_label_1 = 'TRANS_ID' AND pel.run_id = pxl.run_id ) error_count "  
	 + "FROM pix_xmlread_log pxl WHERE ACK_FLAG = 'KU' AND FTP_FLAG != 'FU' AND trans_id is not null" ;	 	
	 
	 
	 private static final String qry_updt_ack_flag = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_xmlread_log "
	 + "SET ack_flag='KS' WHERE trans_id = ?" ;*/
	
	
	public ArrayList getBAOutboundDetails(String vendorSAN, String transactionType, String transactionName) {
		Connection dbCon 			= null;	
		PreparedStatement prepStmt	= null;
		ResultSet resultSet			= null;
		PreparedStatement prepStmt2	= null;
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		ResultSet resultSet2		= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		ArrayList BAList 			= null;		
		PapiNetEnvelopeDTO pneDTO	= null;		
		ArrayList payloadInfoList	= null;
		PayloadInfo payloadInfo		= null;
		Payload payload				= null;
		B2BHelper b2bHelper			= null;
		BusinessDocument businessDocument = null;		
		BusinessAcknowledgementDTO baDTO= null;
		BusinessAckDate businessAckDate = null;
		BusinessAckDateDate businessAckDateDate	= null;
		DocumentDate documentDate	= null;
		DocumentDateDate documentDateDate = null;
		DocumentError baError	= null;
		DocumentReference docRef = null;
		Document baDoc  = null;
		ArrayList baErrorList	= null;
		ArrayList docRefList = null;
		String dbTransID = null;
		String dbRunId = null;
		String processType = null;
		String dbSAN = null;
		String dbPUBSAN = null;
		String db = null;
		String docName = null;
		Long errorCount = null;
		boolean baSuccessFlag = false;
		boolean baFailureFlag = false;
		String docDate = null;
		String arrDocDate[] = null;
		String strDate = null;
		String arrDocDate1[] = null;
		String transID = null;
		String errCode = null;
		String errDesc = null;
		String documentNo = null;
		String transHistNo = null;
		
		try {
			B2BLogger.debug("******* BusinessAckGeneratorDAOImpl.getBAOutboundDetails() method ENTERED *******");			
			dbCon = DBUtils.getDBConnection();			
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			//dbCon.setAutoCommit(false);
			payloadInfoList = getEnvelopePayloadInfo(dbCon, vendorSAN, transactionType, transactionName);
			if(payloadInfoList != null && payloadInfoList.size()>0){
				BAList	= new ArrayList();
				for(int i=0; i<payloadInfoList.size(); i++){
					payloadInfo = (PayloadInfo)payloadInfoList.get(i);
					if(payloadInfo != null){	
						b2bHelper = new B2BHelper();
						dbTransID = payloadInfo.getTransID();
						dbRunId = payloadInfo.getRunId();						
						processType = payloadInfo.getTransType();
						documentNo = payloadInfo.getDocumentNo();
						B2BLogger.info("Trans_id=" +dbTransID+ " RunId=" +dbRunId+ " Process Type=" + processType+ " Doc Number=" + documentNo);
						
						pneDTO = new PapiNetEnvelopeDTO();
						pneDTO.setPayloadInfo(payloadInfo);					
						
						
						sqlQuery = qry_sel_trans_id;    //Convert pix_trans_id to Vendor_Trans_Id
						prepStmt = dbCon.prepareStatement(sqlQuery);
						prepStmt.clearParameters();
						prepStmt.setString(IPixB2BConstants.ONE, dbTransID);
						qryParams = dbTransID;
						resultSet = prepStmt.executeQuery();
						while(resultSet.next()){
							transID = resultSet.getString("vendor_trans_id");
							transHistNo = resultSet.getString("trans_hist_no");
						}
						DBUtils.close(prepStmt);
						DBUtils.close(resultSet);	
						
						baDTO = new BusinessAcknowledgementDTO();
						baDTO.setBusinessAckNumber(transID);       //Pix_Trans_Mapping - Vendor Trans Id						
						
						Calendar cal = Calendar.getInstance();
						String day = ""+cal.get(Calendar.DATE);
						String month = ""+(cal.get(Calendar.MONTH)+1); //Jan is zero th month acc to Calender class
						String year = ""+cal.get(Calendar.YEAR);
						
						
						businessAckDate = new BusinessAckDate();
						businessAckDateDate = new BusinessAckDateDate();
						businessAckDateDate.setBusinessAckDateYear(year);
						businessAckDateDate.setBusinessAckDateMonth(month);
						businessAckDateDate.setBusinessAckDateDay(day);
						businessAckDate.setBusinessAckDateDate(businessAckDateDate);
						baDTO.setBusinessAckDate(businessAckDate);
						
						//Set Document details	
						baDoc = new Document();						
						docName = b2bHelper.getTransName(processType);
						//System.out.println("Process Type=" + processType+" Doc Name="+docName+ " DocNumber="+documentNo);
						baDoc.setDocumentName(docName);
						baDoc.setDocumentNumber(documentNo);   
						
						documentDate = new DocumentDate();
						documentDateDate = new DocumentDateDate();
						strDate = payloadInfo.getTransDate();
						if(!org.apache.commons.lang.StringUtils.isEmpty(strDate))
						{
							arrDocDate = strDate.trim().split(" ");				
							docDate = arrDocDate[0];
							arrDocDate1 = docDate.trim().split("-");	
							documentDateDate.setDocumentDateYear(arrDocDate1[0]);
							documentDateDate.setDocumentDateMonth(arrDocDate1[1]);
							documentDateDate.setDocumentDateDay(arrDocDate1[2]);
							documentDate.setDocumentDateDate(documentDateDate);
							baDoc.setDocumentDate(documentDate);
						}else{ 
							//Populate system date incase document date is null
							documentDateDate.setDocumentDateYear(year);
							documentDateDate.setDocumentDateMonth(month);
							documentDateDate.setDocumentDateDay(day);
							documentDate.setDocumentDateDate(documentDateDate);
							baDoc.setDocumentDate(documentDate);
						}
						
						//Populate DocRef tag for all inbound transactions except OST
						if(!docName.equals(IPixB2BConstants.OrderStatus) && null!=transHistNo){
							docRefList = new ArrayList();
							docRef = new DocumentReference();
							docRef.setDocumentReferenceType(IPixB2BConstants.TransactionID);  
							docRef.setAssignedBy(IPixB2BConstants.PrinterFacility);						
							docRef.setDocumentReferenceValue(transHistNo);  //Pix_Trans_Mapping - Transaction History No
							docRefList.add(docRef);							
							baDoc.setDocumentReferenceList(docRefList);
						}else if(docName.equals(IPixB2BConstants.OrderStatus) && null!=transHistNo){
							docRefList = new ArrayList();
							docRef = new DocumentReference();
							docRef.setDocumentReferenceType(IPixB2BConstants.TransactionID);  
							docRef.setAssignedBy(IPixB2BConstants.PrinterFacility);						
							docRef.setDocumentReferenceValue(transHistNo);
							docRefList.add(docRef);							
							baDoc.setDocumentReferenceList(docRefList);
						}
						baDTO.setDocument(baDoc);
						
						
						
						sqlQuery = qry_sel_ba_genr_transactions;
						prepStmt1 = dbCon.prepareStatement(sqlQuery);
						prepStmt1.clearParameters();
						prepStmt1.setString(IPixB2BConstants.ONE, dbTransID);
						prepStmt1.setString(IPixB2BConstants.TWO, dbRunId);
						qryParams=dbTransID+","+dbRunId;
						resultSet1 = prepStmt1.executeQuery();
						if(resultSet1.next() && resultSet1.getLong("error_count") > 0){							
							baFailureFlag = true;
							baDTO.setStatus(IPixB2BConstants.Failure);			
							
							baErrorList = new ArrayList();
							sqlQuery = qry_sel_ba_error_desc;
							prepStmt2 = dbCon.prepareStatement(sqlQuery);
							prepStmt2.clearParameters();							
							prepStmt2.setString(IPixB2BConstants.ONE, dbRunId);
							prepStmt2.setString(IPixB2BConstants.TWO, dbTransID);
							qryParams = dbRunId +","+ dbTransID;
							resultSet2 = prepStmt2.executeQuery();							
							while(resultSet2.next()){								
								baError = new DocumentError();								
								errCode = resultSet2.getString("err_code");
								errDesc = resultSet2.getString("err_desc");
								//System.out.println("errCode= " +errCode+ "errDesc= " + errDesc );
								if(errCode != null && !"".equals(errCode.trim())){
									baError.setErrorCode(errCode);
								}
								if(errDesc != null && !"".equals(errDesc.trim())){
									baError.setErrorDescription(errDesc);
								}
								
								baErrorList.add(baError);	
								baDTO.setDocumentErrorList(baErrorList);
							}
							DBUtils.close(prepStmt2);
							DBUtils.close(resultSet2);	
						}else{
							baSuccessFlag = true;
							baDTO.setStatus(IPixB2BConstants.Success);
						}			
						DBUtils.close(prepStmt1);
						DBUtils.close(resultSet1);	
						
						businessDocument = new BusinessDocument();
						businessDocument.setBaDTO(baDTO);
						
						payload	= new Payload();
						payload.setBusinessDocument(businessDocument);							
						
						pneDTO.setPayload(payload);
						BAList.add(pneDTO);
						
					}else{
						B2BLogger.debug("BusinessAckGeneratorDAOImpl.getBAOutboundDetails() - payloadInfo is null for Envelope");
					}
				}
			}else{
				B2BLogger.debug("BusinessAckGeneratorDAOImpl.getBAOutboundDetails() - payloadInfoList is null or blank for Envelope");
			}
			dbCon.commit();
			B2BLogger.debug("******* BusinessAckGeneratorDAOImpl.getBAOutboundDetails() method EXIT *******");
		}catch(SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			BAList = null;
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			BAList = null;
		} finally{	
			DBUtils.close(prepStmt);
			DBUtils.close(prepStmt1);
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet);
			DBUtils.close(resultSet1);
			DBUtils.close(resultSet2);
			DBUtils.close(dbCon);
			dbCon 			= null;		
			qryParams		= null;
			pneDTO			= null;		
			payloadInfoList	= null;
			payloadInfo		= null;
			payload			= null;
			businessDocument= null;			
		}			
		return BAList;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.dao.IBusinessAckGeneratorDAO#getBusinessAcknowledgementDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList getBusinessAcknowledgementDetails(String vendorSAN, String transactionType, String transactionName) {
		Connection dbCon 			= null;	
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		String sqlQuery				= null;
		String qryParams			= null;
		
		ArrayList baList 			= null;
		
		PapiNetEnvelopeDTO pneDTO	= null;		
		ArrayList payloadInfoList	= null;
		PayloadInfo payloadInfo		= null;
		Payload payload				= null;
		BusinessDocument businessDocument = null;
		
		BusinessAcknowledgementDTO baDTO= null;
		ArrayList documentErrorList	= null;
		DocumentError documentError	= null;
		BusinessAckDate businessAckDate = null;
		BusinessAckDateDate businessAckDateDate	= null;
		Document document 				= null;
		DocumentDate documentDate		= null;
		DocumentDateDate documentDateDate = null;
		ArrayList documentReferenceList	= null;
		DocumentReference documentReference = null;
		
		B2BHelper b2bHelper				= null;
		
		try {
			B2BLogger.debug("******* BusinessAckGeneratorDAOImpl.getBusinessAcknowledgementDetails() method ENTERED *******");			
			dbCon = DBUtils.getDBConnection();			
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			dbCon.setAutoCommit(false);
			
			payloadInfoList = getEnvelopePayloadInfo(dbCon, vendorSAN, transactionType, transactionName);
			if(payloadInfoList != null && payloadInfoList.size()>0){
				baList	= new ArrayList();
				for(int i=0; i<payloadInfoList.size(); i++){
					payloadInfo = (PayloadInfo)payloadInfoList.get(i);
					if(payloadInfo != null){						
						pneDTO = new PapiNetEnvelopeDTO();
						pneDTO.setPayloadInfo(payloadInfo);
						b2bHelper = new B2BHelper();
						
						baDTO = new BusinessAcknowledgementDTO();
						if(payloadInfo.getStatusFlag() != null && IPixB2BConstants.status_RE.equalsIgnoreCase(payloadInfo.getStatusFlag().trim())){
							baDTO.setStatus(IPixB2BConstants.Failure);
							documentErrorList = new ArrayList();							
							sqlQuery = qry_sel_ba_failure_errors;
							prepStmt1 = dbCon.prepareStatement(sqlQuery);
							prepStmt1.clearParameters();
							prepStmt1.setString(IPixB2BConstants.ONE, payloadInfo.getRunId());
							qryParams = vendorSAN;
							resultSet1 = prepStmt1.executeQuery();
							while(resultSet1.next()) {
								documentError = new DocumentError();
								documentError.setErrorCode(resultSet1.getString("err_code"));
								documentError.setErrorDescription(resultSet1.getString("err_desc"));								
								documentErrorList.add(documentError);
							}
							baDTO.setDocumentErrorList(documentErrorList);
						}else{
							baDTO.setStatus(IPixB2BConstants.Success);
						}
						baDTO.setBusinessAckNumber(""+payloadInfo.getTransID());
						
						businessAckDate = new BusinessAckDate();
						businessAckDateDate = new BusinessAckDateDate();
						businessAckDateDate.setBusinessAckDateYear(payloadInfo.getDocument().getDocumentDate().getDate().getYear());
						businessAckDateDate.setBusinessAckDateMonth(payloadInfo.getDocument().getDocumentDate().getDate().getMonth());
						businessAckDateDate.setBusinessAckDateDay(payloadInfo.getDocument().getDocumentDate().getDate().getDay());
						businessAckDate.setBusinessAckDateDate(businessAckDateDate);
						businessAckDate.setBusinessAckDateTime(payloadInfo.getDocument().getDocumentDate().getTime());
						baDTO.setBusinessAckDate(businessAckDate);
						
						document = new Document();
						document.setDocumentName(b2bHelper.getTransName(payloadInfo.getTransType()));
						document.setDocumentNumber("DocumentNumber query already raised to MANISH");						
						documentDate = new DocumentDate();
						documentDateDate = new DocumentDateDate();
						documentDateDate.setDocumentDateYear(payloadInfo.getDocument().getDocumentDate().getDate().getYear());
						documentDateDate.setDocumentDateMonth(payloadInfo.getDocument().getDocumentDate().getDate().getMonth());
						documentDateDate.setDocumentDateDay(payloadInfo.getDocument().getDocumentDate().getDate().getDay());
						documentDate.setDocumentDateDate(documentDateDate);
						document.setDocumentDate(documentDate);
						documentReferenceList = new ArrayList();
						documentReference = new DocumentReference();
						documentReference.setDocumentReferenceType(IPixB2BConstants.TransactionID);
						documentReference.setAssignedBy(IPixB2BConstants.PrinterFacility);
						documentReference.setDocumentReferenceValue(""+payloadInfo.getTransID());
						documentReferenceList.add(documentReference);
						document.setDocumentReferenceList(documentReferenceList);
						baDTO.setDocument(document);
						
						businessDocument = new BusinessDocument();
						businessDocument.setBaDTO(baDTO);
						
						payload	= new Payload();
						payload.setBusinessDocument(businessDocument);
						
						pneDTO.setPayload(payload);
						baList.add(pneDTO);
					}else{
						B2BLogger.debug("BusinessAckGeneratorDAOImpl.getBusinessAcknowledgementDetails() - payloadInfo is null for Envelope");
					}
				} //end for loop - payloadInfoList
			}else{
				B2BLogger.debug("BusinessAckGeneratorDAOImpl.getBusinessAcknowledgementDetails() - payloadInfoList is null or blank for Envelope");
			}
			dbCon.commit();
			B2BLogger.debug("******* BusinessAckGeneratorDAOImpl.getBusinessAcknowledgementDetails() method EXIT *******");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			baList = null;
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			baList = null;
		} finally{			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			DBUtils.close(dbCon);
			dbCon 			= null;
			
			qryParams		= null;
			pneDTO			= null;		
			payloadInfoList	= null;
			payloadInfo		= null;
			payload			= null;
			businessDocument= null;			
		}	
		return baList;
	}
}