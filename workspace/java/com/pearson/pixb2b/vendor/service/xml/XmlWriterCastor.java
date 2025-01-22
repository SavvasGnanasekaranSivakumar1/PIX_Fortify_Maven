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
 * Title		: 	XmlWriterCastor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.castor.xml.XMLConfiguration;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.service.ftp.FTPClientB2B;
import com.pearson.pixb2b.vendor.service.sftp.SFTPClientB2B;
import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.FTPServerInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.transaction.shared.helper.TransStatusHelper;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DateUtils;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * XmlWriterCastor class generate the B2B Outbound transactions XML 
 * in XBITS standards using castor API.
 * 
 * @author Yogesh Tyagi
 */
public class XmlWriterCastor{
	private ArrayList<Integer> allTransactionStatus 	= null;
	/**
	 * This method write B2B transactions XML in XBITS standards using castor API.
	 * This method also adds the transaction schema in transaction root tag in middle 
	 * into XML that is required for validating the XML at runtime.
	 * 
	 * @param mapping
	 * @param pneDTO
	 * @param xmlFileNameWithDir
	 * @param transactionName
	 * @param transactionSchema
	 */
	
	public XmlWriterCastor(){
		allTransactionStatus = new ArrayList<Integer>();
	}
	public boolean writeXML(Mapping mapping, PapiNetEnvelopeDTO pneDTO, String xmlFileNameWithDir, String transactionName, String transactionSchema, String vendorSAN, OutboundTransactionFactory outTransFactory){
		Marshaller marshaller 		= null;
		StringWriter stringWriter 	= null;
		B2BHelper b2bHelper			= null;
		FileUtils fileUtils			= null;
		boolean xmlWriteStatus		= false;
		
		String errorStr				= null;
		ErrorDTO errorDTO			= null;
		ArrayList errorList			= null;
		IErrorDAO errorDAO 			= null;	
		XMLContext xmlContext		=null;

		try {			
			
				xmlContext = new XMLContext();
				//to resolve castor bug for AIX http://jira.codehaus.org/browse/CASTOR-2411
				xmlContext.setProperty(XMLConfiguration.SERIALIZER_FACTORY, "org.exolab.castor.xml.XercesXMLSerializerFactory");
				marshaller = xmlContext.createMarshaller();
				//marshaller = new Marshaller();
				marshaller.setMapping(mapping);
				//marshaller.setProperty("org.exolab.castor.indent","true");
				stringWriter = new StringWriter();
				marshaller.setWriter(stringWriter);
				marshaller.marshal(pneDTO);
			
				b2bHelper = new B2BHelper();			
				fileUtils = new FileUtils();
			
				fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(stringWriter.toString(), transactionName, transactionSchema));
				xmlWriteStatus = true;
		} catch (MarshalException e) {
			B2BLogger.error("MarshalException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			xmlWriteStatus = false;
		} catch (ValidationException e) {
			B2BLogger.error("ValidationException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			xmlWriteStatus = false;
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			xmlWriteStatus = false;
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			xmlWriteStatus = false;
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			xmlWriteStatus = false;
		} finally{
			marshaller 	= null;
			stringWriter= null;
		}
		if(!xmlWriteStatus){			
			b2bHelper = new B2BHelper();
			errorList = new ArrayList();
			
			errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+IPixB2BConstants.ERROR_ID_72, vendorSAN, IPixB2BConstants.VENDOR_SAN, 
					IPixB2BConstants.XML, IPixB2BConstants.PIX_TO_XML, 
					""+pneDTO.getPayloadInfo().getTransID(), IPixB2BConstants.TRANS_ID, transactionName, IPixB2BConstants.TRANS_NAME,
					errorStr, IPixB2BConstants.flag_N, null, null);
			errorList.add(errorDTO);
			
			errorDAO = outTransFactory.geErrorDAO();
			errorDAO.storeOutboundXmlErrorDetails(errorList, IPixB2BConstants.B2B, IPixB2BConstants.PIX_TO_XML, IPixB2BConstants.flag_U);
		}
		return xmlWriteStatus;
	}
	/**
	 * This method validates Outbound transactions XML and FTP the valid XML at FTP server.
	 * @param xmlFileList
	 * @param transactionsList
	 * @param vendorDTO
	 * @param outDirGeneratedXml
	 * @param outDirArchiveXmlValid
	 * @param outDirArchiveXmlInvalid
	 * @param transType
	 * @param transactionName
	 * @param transactionSchema
	 * @param papiNetSchema
	 * @return ArrayList
	 */
	private ArrayList validateAndFtpTransXml(ArrayList xmlFileList, ArrayList transactionsList, VendorDTO vendorDTO, 
			String outDirGeneratedXml, String outDirArchiveXmlValid, String outDirArchiveXmlInvalid, 
			String transType, String transactionName, String transactionSchema, String papiNetSchema, OutboundTransactionFactory outTransFactory) {
		
		ArrayList processedTransList= null;
		B2BHelper b2bHelper			= null;
		HashMap hmValidatedXmlList	= null;
		ArrayList validXmlList		= null;
		ArrayList invalidXmlList	= null;
		FTPServerInfo ftpServerInfo	= null;
		FTPClientB2B ftpClientB2B	= null;
		SFTPClientB2B sftpClientB2B	= null;
		String ftpReply 			= null;
		String xmlFileNameWithDir	= null;	
		PapiNetEnvelopeDTO pneDTO	= null;
		String transIDInvalidXml 	= null; 
		HashMap hmXmlWithError 		= null;
		Iterator itr 				= null;		
		ArrayList errorList 		= null;
		String errorStr				= null;
		ErrorDTO errorDTO			= null;
		ArrayList errorListNew		= null;
		IErrorDAO errorDAO 			= null;		
		FileUtils fileUtils			= null;
		TransStatusHelper transStatusHelper = null;
		String processStatus		= null;
		String ackStatus			= null;
		TransactionStatusDTO transStatusDTO = null;
		String sftpReply = null;
		
		try {
			if(xmlFileList != null && xmlFileList.size() > 0){					
				b2bHelper = new B2BHelper();
				hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, transactionName, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
				
				if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){					
					processedTransList = new ArrayList();
					fileUtils = new FileUtils();
					transStatusHelper = new TransStatusHelper();									
					
					if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){							
						validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
						if(validXmlList != null && validXmlList.size()>0){
							updateTransStatus(IPixB2BConstants.STATUS_PASS);
							ftpServerInfo = vendorDTO.getFtpServerInfo();
							if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("FTP")){// Nithya CTS - Added for FTP to SFTP Migration
								ftpClientB2B = new FTPClientB2B();
								ftpReply = ftpClientB2B.uploadFiles(validXmlList, ftpServerInfo, 
									transactionName, transactionSchema, outDirArchiveXmlValid, outDirArchiveXmlInvalid);
								/* Nithya CTS - Added for FTP to SFTP Migration starts */
							}else if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("SFTP")){ 
								sftpClientB2B = new SFTPClientB2B();
								sftpReply = sftpClientB2B.uploadFiles(validXmlList, ftpServerInfo, 
									transactionName, transactionSchema, outDirArchiveXmlValid, outDirArchiveXmlInvalid);
							}else if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("BOTH")){ 
								sftpClientB2B = new SFTPClientB2B();
								sftpReply = sftpClientB2B.uploadFiles(validXmlList, ftpServerInfo, 
									transactionName, transactionSchema, outDirArchiveXmlValid, outDirArchiveXmlInvalid);
								
								/** added for transfer the files to ftp temporarily **/
								ftpClientB2B = new FTPClientB2B();
								FTPServerInfo defaultFtpServer = new FTPServerInfo("peheftp.pearsoned.com",ftpServerInfo.getFtpUserName(),ftpServerInfo.getFtpPwd());
								defaultFtpServer.setFtpPutDir(ftpServerInfo.getFtpPutDir());
								//FTPServerInfo defaultFtpServer = new FTPServerInfo("peheftp.pearsoned.com","pehe019","x2m4lG");
								//defaultFtpServer.setFtpPutDir("/test/out");
								ftpReply = ftpClientB2B.uploadFiles(validXmlList, defaultFtpServer, 
									transactionName, transactionSchema, outDirArchiveXmlValid, outDirArchiveXmlInvalid);
							}else{
								B2BLogger.error("Specify the transfer mode properly FTP/SFTP in PIX_SAN_TRANS_MAPPING table");
							}
							/* Nithya CTS - Added for FTP to SFTP Migration Ends */
							if(ftpReply != null && (ftpReply.trim().toUpperCase().contains(IPixB2BConstants.FTP_TRANSFER_COMPLETE.toUpperCase())
									|| ftpReply.trim().toUpperCase().contains("226 File received ok".toUpperCase()) 
									|| ftpReply.trim().toUpperCase().contains("226 Transfer OK".toUpperCase())) || sftpReply == "Success" ){
							//if(ftpReply != null && (IPixB2BConstants.FTP_TRANSFER_COMPLETE.toUpperCase()).contains(ftpReply.trim().toUpperCase())){
								for(int i=0; i < validXmlList.size(); i++){
									xmlFileNameWithDir = (String)validXmlList.get(i);
									for(int count=0; count<transactionsList.size(); count++){
										pneDTO = (PapiNetEnvelopeDTO)transactionsList.get(count);
										if(xmlFileNameWithDir.indexOf(pneDTO.getPayloadInfo().getXmlFileName()) != -1){

											if(IPixB2BConstants.transType_ACK.equals(transType)){
					    						processStatus = IPixB2BConstants.processType_INBOUND;
					    						ackStatus = IPixB2BConstants.status_KS;
					    					}else{
					    						processStatus = IPixB2BConstants.processType_OUTBOUND;
					    						ackStatus = IPixB2BConstants.status_KU;
					    					}
											
											transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, vendorDTO.getVendorSAN(), null, 
													pneDTO.getPayloadInfo().getTransID(), transType, transactionName,  
													pneDTO.getPayloadInfo().getXmlFileName(), vendorDTO.getVendorSAN(), outDirArchiveXmlValid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
													IPixB2BConstants.status_GS, null, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus,null,null);
											processedTransList.add(transStatusDTO);
										}
									}
								}
							}else{
								updateTransStatus(IPixB2BConstants.STATUS_FAIL);
							}
						} //end if - validXmlList
					} //end if - VALID_XML_LIST
					
					if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){												
						invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
						if(invalidXmlList != null && invalidXmlList.size()>0){
							updateTransStatus(IPixB2BConstants.STATUS_FAIL);
							for(int i=0; i < invalidXmlList.size(); i++){
								hmXmlWithError 	= (HashMap)invalidXmlList.get(i);
								if(hmXmlWithError != null && hmXmlWithError.size() > 0){
									itr = hmXmlWithError.keySet().iterator();
									while (itr.hasNext()) {
										xmlFileNameWithDir = (String) itr.next();
										
										for(int j=0; j<transactionsList.size(); j++){
											pneDTO = (PapiNetEnvelopeDTO)transactionsList.get(j);
											if(xmlFileNameWithDir.indexOf(pneDTO.getPayloadInfo().getXmlFileName()) != -1){
												transIDInvalidXml = pneDTO.getPayloadInfo().getTransID();
												
												if(IPixB2BConstants.transType_ACK.equals(transType)){
						    						processStatus = IPixB2BConstants.processType_INBOUND;
						    						ackStatus = IPixB2BConstants.status_KE;
						    					}else{
						    						processStatus = IPixB2BConstants.processType_OUTBOUND;
						    						ackStatus = IPixB2BConstants.status_KU;
						    					}
												
												transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, vendorDTO.getVendorSAN(), null, 
														transIDInvalidXml, transType, transactionName,  
														pneDTO.getPayloadInfo().getXmlFileName(), vendorDTO.getVendorSAN(), outDirArchiveXmlInvalid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
														IPixB2BConstants.status_GE, null, IPixB2BConstants.status_FU, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus,null,null);
												processedTransList.add(transStatusDTO);
											}
										}

										b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, transactionName, transactionSchema);
										fileUtils.moveFile(xmlFileNameWithDir, outDirArchiveXmlInvalid);
										B2BLogger.info("XmlWriterCastor.validateAndFtpTransXml() :: B2B GENERATED XML "+xmlFileNameWithDir+" IS INVALID FOR TRANS_ID = "+transIDInvalidXml);
										
										errorList = (ArrayList)hmXmlWithError.get(xmlFileNameWithDir);
										if(errorList != null && errorList.size()>0){
											errorListNew = new ArrayList();
											for(int j=0; j<errorList.size(); j++){
												errorStr = (String)errorList.get(j);			      					
						      					errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+IPixB2BConstants.ERROR_ID_72, fileUtils.getFileName(xmlFileNameWithDir), IPixB2BConstants.XML, 
						      							IPixB2BConstants.VENDOR, IPixB2BConstants.PIX_TO_XML, 
						      							""+transIDInvalidXml, IPixB2BConstants.TRANS_ID, outDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
						      							errorStr, IPixB2BConstants.flag_N, null, null);
						      					errorListNew.add(errorDTO);
						      				}
											errorDAO = outTransFactory.geErrorDAO();
					      					errorDAO.storeOutboundXmlErrorDetails(errorListNew, IPixB2BConstants.B2B, IPixB2BConstants.PIX_TO_XML, IPixB2BConstants.flag_U);
										}
									} //end while loop						
								}
							}
						} //end if - invalidXmlList						 
					} //end if - INVALID_XML_LIST						
				}else{
					B2BLogger.info("XmlWriterCastor.validateAndFtpTransXml() - NO VALIDATED "+transactionName+" XML FOUND ON "+DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy)+" FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
				}
			}else{
				B2BLogger.info("XmlWriterCastor.validateAndFtpTransXml() - NO "+transactionName+" XML FOUND TO VALIDATE ON "+DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy)+" FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
		} finally{
			b2bHelper		= null;
			hmValidatedXmlList= null;
			validXmlList	= null;
			invalidXmlList	= null;
			ftpServerInfo	= null;
			ftpClientB2B	= null;
			ftpReply 		= null;
			xmlFileNameWithDir = null;	
			pneDTO			= null;
			transIDInvalidXml = null;
			hmXmlWithError 	= null;
			itr 			= null;
			errorList 		= null;
			errorStr		= null;
			errorDTO		= null;
			errorListNew	= null;
			errorDAO 		= null;
			fileUtils		= null;
			transStatusHelper = null;
			transStatusDTO 	= null;
			processStatus	= null;
			ackStatus		= null;
		}
		return processedTransList;
	}
	/**
	 * This method generates Outbound transactions XML.
	 * @param transactionsList
	 * @param vendorDTO
	 * @param outDirGeneratedXml
	 * @param outDirArchiveXmlValid
	 * @param outDirArchiveXmlInvalid
	 * @param transType
	 * @param transactionName
	 * @param castorMappingFile
	 * @param transactionSchema
	 * @param papiNetSchema
	 * @param outTransFactory
	 * @return ArrayList
	 */
	public ArrayList generateXml(ArrayList transactionsList, VendorDTO vendorDTO, String outDirGeneratedXml, 
			String outDirArchiveXmlValid, String outDirArchiveXmlInvalid, String transType, String transactionName, 
			String castorMappingFile, String transactionSchema, String papiNetSchema, OutboundTransactionFactory outTransFactory) {

		ArrayList processedTransList= null;
		Mapping mapping 			= null;
		PapiNetEnvelopeDTO pneDTO	= null;
		String xmlFileNameWithDir	= null;		
//		XmlWriterCastor xmlWriterCastor	= null;
		FileUtils fileUtils			= null;
		ArrayList generatedXmlList= null;
		
		try {
			if(transactionsList != null && transactionsList.size()>0){
				mapping = new Mapping();
				mapping.loadMapping(castorMappingFile);
				
				for(int count=0; count<transactionsList.size(); count++){
					pneDTO = (PapiNetEnvelopeDTO)transactionsList.get(count);
					if(pneDTO != null){
						xmlFileNameWithDir = outDirGeneratedXml+File.separatorChar+pneDTO.getPayloadInfo().getXmlFileName();
						writeXML(mapping, pneDTO, xmlFileNameWithDir, transactionName, transactionSchema, vendorDTO.getVendorSAN(), outTransFactory);
					}
				} //end for loop - transactionsList				
				
				fileUtils = new FileUtils();				
				generatedXmlList = fileUtils.getDirFileNameList(outDirGeneratedXml, null, IPixB2BConstants.fileExtn_XML);
				if(generatedXmlList != null && generatedXmlList.size() > 0){
					processedTransList = validateAndFtpTransXml(generatedXmlList, transactionsList, vendorDTO, 
							outDirGeneratedXml, outDirArchiveXmlValid, outDirArchiveXmlInvalid, 
							transType, transactionName, transactionSchema, papiNetSchema, outTransFactory);
				}else{
					B2BLogger.info("XmlWriterCastor.generateXml() - NO "+transactionName+" XML GENERATED ON "+DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy)+" FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
				}
			}
		} catch (IOException e) {
			B2BLogger.error("IOException :: ",e);
			
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: ",e);
			
		} finally{
			updateGlobalStatus();
			mapping 	= null;
			pneDTO		= null;
			xmlFileNameWithDir = null;		
//			xmlWriterCastor	= null;
			fileUtils	= null;
			generatedXmlList = null;
		}
		return processedTransList;
	}
	
	/**
	 * 
	 * @param status
	 */
	private void updateTransStatus(int status) {
		
		allTransactionStatus.add(status);
	}
	/**
	 * 
	 */
	private void updateGlobalStatus()
	{
		if(allTransactionStatus.contains(IPixB2BConstants.STATUS_PASS) && allTransactionStatus.contains(IPixB2BConstants.STATUS_FAIL))
		{
			B2BGlobalData.getGlobalDataObject().updateStatus(IPixB2BConstants.STATUS_PARTIAL);
			return;
		}
		else
			if(allTransactionStatus.contains(IPixB2BConstants.STATUS_PASS))
			{
				B2BGlobalData.getGlobalDataObject().updateStatus(IPixB2BConstants.STATUS_PASS);
				return;
			}
			else
			{
				if(allTransactionStatus.contains(IPixB2BConstants.STATUS_FAIL))
				{
					B2BGlobalData.getGlobalDataObject().updateStatus(IPixB2BConstants.STATUS_FAIL);
					return;
				}
			}
	}
}