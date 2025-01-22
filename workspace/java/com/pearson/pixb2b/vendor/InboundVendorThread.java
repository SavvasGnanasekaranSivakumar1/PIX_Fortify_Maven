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
 * Title		: 	InboundVendorThread.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.service.ftp.FTPClientB2B;
import com.pearson.pixb2b.vendor.service.sftp.SFTPClientB2B;
import com.pearson.pixb2b.vendor.service.xml.XmlReaderSax;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.asn.IAdvanceShipmentNoticeProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.IBusinessAckProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.IInboundVendorDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessage.IDeliveryMessageProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.IDeliveryMessageWoodProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.IGRProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.IInventoryChangeProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.IInventoryStatusProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.IInvoiceProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.IOrderConfirmationProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.IUsageProcessor;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.FTPServerInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DateUtils;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * This class starts a Inbound Vendor Thread for a particular B2B Vendor
 * in an asynchronous way and complete the processing of all transactions 
 * of that vendor parallel to others.
 * 
 * @author Yogesh Tyagi
 */
public class InboundVendorThread implements Runnable {
	private String processType 		= null;
	private String vendorSAN 		= null;
	private String vendorName 		= null;	
	private String papiNetSchema 	= null;
//	private Thread inVendorThread 	= null;
	private ArrayList<Integer> allTransactionStatus 	= null;
//	private Integer threadStatus = STATUS_FAIL;
	/**
	 * Constructor
	 * @param vendorSAN
	 * @param vendorName
	 */
	public InboundVendorThread(String processType, String vendorSAN, String vendorName, String papiNetSchema) {
		this.processType  	 = processType;
		this.vendorSAN  	 = vendorSAN;
		this.vendorName 	 = vendorName;
		this.papiNetSchema 	 = papiNetSchema;
		allTransactionStatus =new ArrayList<Integer>();
		/*inVendorThread = new Thread(this, vendorSAN);
		inVendorThread.start();*/
	}	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		InboundTransactionFactory inTransFactory = null;
		IInboundVendorDAO inVendorDAO = null;
		VendorDTO vendorDTO 		= null;
		FTPServerInfo ftpServerInfo	= null;		
		File fileDir				= null;
		String vendorXmlDownloadDir	= null;
		String inDirArchiveXmlValid = null;
		String inDirArchiveXmlInvalid = null;
		FTPClientB2B ftpClientB2B 	= null;
		SFTPClientB2B sftpClientB2B 	= null; // Nithya CTS - Added for FTP to SFTP Migration
		FileUtils fileUtils			= null;
		ArrayList vendorXmlList	= null;
		B2BHelper b2bHelper			= null;
		ArrayList xmlFileList 		= null;
		HashMap hmValidatedXmlList	= null;
		ArrayList validXmlList		= null;
		ArrayList invalidXmlList	= null;
		String xmlFileNameWithDir	= null;
		String correctXmlFileNameWithDir = null;
		File xmlFile 				= null;
		String xmlString 			= null;
		PapiNetEnvelopeDTO pneDTO 	= null;
		XmlReaderSax xmlReaderSax 	= null;
		String documentName 		= null;
		String documentNumber 		= null;
		String documentDate 		= null;
		String envYear = null;
		String envMonth = null;
		String envDay = null;
		TransactionStatusDTO transStatusDTO 		= null;
		
		IOrderStatusProcessor osProcessor 			= null;
		IOrderConfirmationProcessor ocProcessor 	= null;
		IInventoryChangeProcessor icProcessor 		= null;
		IInventoryStatusProcessor isProcessor 		= null;
		IBusinessAckProcessor baProcessor			= null;
		IDeliveryMessageProcessor dmProcessor		= null;
		IDeliveryMessageWoodProcessor dmwProcessor	= null;
		IUsageProcessor  usgProcessor          	 	= null;
		IAdvanceShipmentNoticeProcessor asnProcessor= null;
		IGRProcessor  grProcessor               	= null;
		IInvoiceProcessor invoProcessor         	= null;
		String transaction_status					= null;
		String xmlSupplierSAN						= null;
		Boolean filesDownloded 				= Boolean.FALSE;
		Boolean dataValidationFlag			= Boolean.FALSE;
		Boolean invoiceErrorFlag			= Boolean.FALSE;
		Boolean usageErrorFlag				= Boolean.FALSE;
		Boolean grErrorFlag                 = Boolean.FALSE;
		Boolean invErrorFlag                = Boolean.FALSE;
		Boolean incErrorFlag                = Boolean.FALSE;
		Boolean asnErrorFlag				= Boolean.FALSE;
		
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		try {
			B2BLogger.debug("InboundVendorThread.run() method called - InboundVendorThread STARTED for vendorSAN = "+vendorSAN+", vendorName = "+vendorName);
			if(vendorSAN != null && vendorSAN != null && !"".equals(vendorSAN.trim())){
				inTransFactory = InboundTransactionFactory.newInstance();
				inVendorDAO = inTransFactory.geInboundVendorDAO();
				vendorDTO = inVendorDAO.getInboundVendorFTPInfo(processType, vendorSAN);
				b2bHelper = new B2BHelper();
				if(vendorDTO != null && vendorDTO.getVendorSAN() != null && !"".equals(vendorDTO.getVendorSAN().trim())){
					ftpServerInfo = vendorDTO.getFtpServerInfo();
					if(ftpServerInfo != null){
						
						//Create required directories if not exist.
						vendorXmlDownloadDir = IConfigConstants.INBOUND_DIR_DOWNLOAD_VENDOR_XML+File.separatorChar+vendorDTO.getVendorSAN();								
						fileDir = new File(vendorXmlDownloadDir);
						b2bHelper.createDirectory(fileDir);
//						if(!fileDir.exists())
//							fileDir.mkdirs();
						
						inDirArchiveXmlValid = IConfigConstants.INBOUND_DIR_ARCHIVE_XML_VALID+File.separatorChar+vendorDTO.getVendorSAN();
						fileDir = new File(inDirArchiveXmlValid);
						b2bHelper.createDirectory(fileDir);
//						if(!fileDir.exists())
//							fileDir.mkdirs();
						
						inDirArchiveXmlInvalid = IConfigConstants.INBOUND_DIR_ARCHIVE_XML_INVALID+File.separatorChar+vendorDTO.getVendorSAN();
						fileDir = new File(inDirArchiveXmlInvalid);
						b2bHelper.createDirectory(fileDir);
//						if(!fileDir.exists())
//							fileDir.mkdirs();
						
						
						String ftpInfo = ftpServerInfo.getFtpGetDir()+ftpServerInfo.getFtpUserName();
						B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
						if(globalData.containsFtpInfo(ftpInfo)){
							throw new B2BException("Stopping the Thread"+vendorDTO.getVendorSAN());
							//Thread.currentThread().stop();
						}else{
							globalData.addFtpInfo(ftpInfo);
						}
						//B2BLogger.debug("InboundVendorThread.run() FTP_SFTP"+ftpServerInfo.getFtpOrSftp());
						if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("FTP")){ // Nithya CTS - Added for FTP to SFTP Migration
							ftpClientB2B = new FTPClientB2B();
						B2BLogger.info("ftpServerInfo :"+ftpServerInfo.getFtpHostName());
						B2BLogger.info("vendorXmlDownloadDir :"+vendorXmlDownloadDir);
							filesDownloded = ftpClientB2B.downloadFiles(ftpServerInfo, vendorXmlDownloadDir);
							if(!filesDownloded) //error occurred during file download
							{
								//try to download once again
								B2BLogger.info("Error while downloading files for " + vendorSAN);
								B2BLogger.info("Retrying file download for " + vendorSAN);
								filesDownloded = ftpClientB2B.downloadFiles(ftpServerInfo, vendorXmlDownloadDir);
							}
							if(!filesDownloded){
								B2BLogger.error("Error downloading files for "+ vendorSAN + " trying to process partially downloaded/existing files");
							}/* Nithya CTS - Added for FTP to SFTP Migration starts */
						}else if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("SFTP")){
							sftpClientB2B = new SFTPClientB2B();
							filesDownloded = sftpClientB2B.downloadFiles(ftpServerInfo, vendorXmlDownloadDir);
							if(!filesDownloded) //error occurred during file download
							{
								//try to download once again
								B2BLogger.info("Error while downloading files for " + vendorSAN);
								B2BLogger.info("Retrying file download for " + vendorSAN);
								filesDownloded = sftpClientB2B.downloadFiles(ftpServerInfo, vendorXmlDownloadDir);
							}
							if(!filesDownloded){
								B2BLogger.error("Error downloading files for "+ vendorSAN + " trying to process partially downloaded/existing files");
							}
						}else if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("BOTH")){
							B2BLogger.info("Downloading file from both FTP and SFTP");
							sftpClientB2B = new SFTPClientB2B();
							B2BLogger.info("SFTP downloading files for "+ vendorSAN +"vendorXmlDownloadDir" +vendorXmlDownloadDir);
							filesDownloded = sftpClientB2B.downloadFiles(ftpServerInfo, vendorXmlDownloadDir);
							if(!filesDownloded) //error occurred during file download
							{
								//try to download once again
								B2BLogger.info("Error while downloading files for " + vendorSAN);
								B2BLogger.info("Retrying file download for " + vendorSAN);
								filesDownloded = sftpClientB2B.downloadFiles(ftpServerInfo, vendorXmlDownloadDir);
							}
							/** added for transfer the files to ftp temporarily **/
							ftpClientB2B = new FTPClientB2B();
							FTPServerInfo defaultFtpServer = new FTPServerInfo("peheftp.pearsoned.com",ftpServerInfo.getFtpUserName(),ftpServerInfo.getFtpPwd());
							defaultFtpServer.setFtpGetDir(ftpServerInfo.getFtpGetDir());
							
							//FTPServerInfo defaultFtpServer = new FTPServerInfo("peheftp.pearsoned.com","pehe019","x2m4lG");
							//defaultFtpServer.setFtpGetDir("/test/in");
							B2BLogger.info("FTP downloading files for "+ vendorSAN +"vendorXmlDownloadDir" +vendorXmlDownloadDir);
							filesDownloded = ftpClientB2B.downloadFiles(defaultFtpServer, vendorXmlDownloadDir);
							if(!filesDownloded) //error occurred during file download
							{
								//try to download once again
								B2BLogger.info("Error while downloading files for " + vendorSAN);
								B2BLogger.info("Retrying file download for " + vendorSAN);
								filesDownloded = ftpClientB2B.downloadFiles(defaultFtpServer, vendorXmlDownloadDir);
							}
							
							if(!filesDownloded){
								B2BLogger.error("Error downloading files for "+ vendorSAN + " trying to process partially downloaded/existing files");
							}
						}else{
							B2BLogger.error("Specify the transfer mode properly FTP/SFTP in PIX_SAN_TRANS_MAPPING table");
						}/* Nithya CTS - Added for FTP to SFTP Migration Ends */
						//proceed forward to check and process if some files are already downloaded.
						fileUtils = new FileUtils();
						vendorXmlList = fileUtils.getDirFileNameList(vendorXmlDownloadDir, null, IPixB2BConstants.fileExtn_XML);
						
						if(vendorXmlList != null && vendorXmlList.size() > 0){
							xmlReaderSax = new XmlReaderSax();
							b2bHelper = new B2BHelper();
							for(int i=0; i < vendorXmlList.size(); i++){
								xmlFileNameWithDir = (String)vendorXmlList.get(i);
								B2BLogger.info("InboundVendorThread.run() :: Vendor's XML file Name = "+fileUtils.getFileName(xmlFileNameWithDir));
								if(!(b2bHelper.validateFileName(xmlFileNameWithDir, inDirArchiveXmlInvalid))){
									B2BLogger.error("InboundVendorThread.run() :: The filename of "+xmlFileNameWithDir+"is invalid");
									//communicate this error via Email utility 1
									continue;
									}else{
											xmlFile = new File(xmlFileNameWithDir);
											xmlString = fileUtils.readFile(xmlFile.getAbsolutePath());
											
											pneDTO = xmlReaderSax.parseXML(xmlString, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), inTransFactory);
											if(pneDTO != null){
												documentNumber = pneDTO.getPayloadInfo().getDocument().getDocumentNumber();
												documentName = pneDTO.getPayloadInfo().getDocument().getDocumentName();
												//Read DocumentDate
												envYear = pneDTO.getPayloadInfo().getDocument().getDocumentDate().getDate().getYear();
												envMonth = pneDTO.getPayloadInfo().getDocument().getDocumentDate().getDate().getMonth();
												envDay = pneDTO.getPayloadInfo().getDocument().getDocumentDate().getDate().getDay();
												documentDate = envMonth+"/"+envDay+"/"+envYear;
												
												strFileNameWithExtn = fileUtils.getFileName(xmlFileNameWithDir);
												strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
												arrStrFileName = strFileNameWithoutExtn.split("_");
												
												xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, b2bHelper.getLongTransType(arrStrFileName[2], null));
												
												if(documentNumber != null && !"".equals(documentNumber.trim()) 
														&& documentName != null && !"".equals(documentName.trim())
														&& documentDate != null && !"".equals(documentDate.trim())
														&& null!=xmlSupplierSAN && !"".equals(xmlSupplierSAN.trim())){
												
													xmlSupplierSAN = b2bHelper.addDashesInSAN(xmlSupplierSAN);
												
													//String tableName = "PIX_ADMIN_PARTY";
													//String whereClause = "SAN='"+xmlSupplierSAN+"' and active_flag='Y'";
													String tableName = "PIX_SAN_TRANS_MAPPING";
													String whereClause = "SAN='"+xmlSupplierSAN+"' and ACTIVE='Y'";
												    int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
												    if(count<=0){
												    		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
											    			fileUtils.moveFile((String)xmlFileNameWithDir, inDirArchiveXmlInvalid);
											    			B2BLogger.info("The Supplier SAN: "+xmlSupplierSAN+ "is not a VALID SAN as it is not present in PIX_SAN_TRANS_MAPPING table");
											    			//communicate this error via Email utility 3
												    }else{
//														if(!(Thread.currentThread().getName()).equalsIgnoreCase(xmlSupplierSAN)){
//															correctXmlFileNameWithDir = xmlFileNameWithDir.replace(xmlFileNameWithDir.substring((xmlFileNameWithDir.indexOf('\\')+1), xmlFileNameWithDir.length()), xmlSupplierSAN);
//															fileUtils.moveFile((String)xmlFileNameWithDir, correctXmlFileNameWithDir);
//														}
//														else{
															if(IPixB2BConstants.OrderStatus.equalsIgnoreCase(documentName.trim())){
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.OrderStatus, IConfigConstants.SCHEMA_OrderStatus));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.OrderStatus, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){												
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.OrderStatus, IConfigConstants.SCHEMA_OrderStatus);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		osProcessor = inTransFactory.getOrderStatusProcessor();
																		dataValidationFlag = osProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_OST, IPixB2BConstants.OrderStatus, IConfigConstants.SCHEMA_OrderStatus, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_OST, IPixB2BConstants.OrderStatus, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			transaction_status = osProcessor.processOrderStatus(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_OST, IPixB2BConstants.OrderStatus, IConfigConstants.SCHEMA_OrderStatus, vendorDTO.getVendorSAN(), inTransFactory, documentNumber, documentDate, pneDTO, IPixB2BConstants.OS_ISSUE_DATE);					 
																	}
																}										
															}else if(IPixB2BConstants.OrderConfirmation.equalsIgnoreCase(documentName.trim())){
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.OrderConfirmation, IConfigConstants.SCHEMA_OrderConfirmation));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.OrderConfirmation, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.OrderConfirmation, IConfigConstants.SCHEMA_OrderConfirmation);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		ocProcessor = inTransFactory.getOrderConfirmationProcessor();
																		dataValidationFlag = ocProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_OCO, IPixB2BConstants.OrderConfirmation, IConfigConstants.SCHEMA_OrderConfirmation, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_OCO, IPixB2BConstants.OrderConfirmation, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			transaction_status = ocProcessor.processOrderConfirmation(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_OCO, IPixB2BConstants.OrderConfirmation, IConfigConstants.SCHEMA_OrderConfirmation, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.OC_ISSUE_DATE);					 
																	}
																}											
															}else if(IPixB2BConstants.InventoryChange.equalsIgnoreCase(documentName.trim())){
																String transId = null;
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.InventoryChange, IConfigConstants.SCHEMA_InventoryChange));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.InventoryChange, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.InventoryChange, IConfigConstants.SCHEMA_InventoryChange);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		icProcessor = inTransFactory.getInventoryChangeProcessor();
																		dataValidationFlag = icProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_INC, IPixB2BConstants.InventoryChange, IConfigConstants.SCHEMA_InventoryChange, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_INC, IPixB2BConstants.InventoryChange, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			ArrayList<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
																			transaction_status = icProcessor.processInventoryChange(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO, errorList);
																			
																			if(null!=errorList && errorList.size()>0){
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																				transId = transStatusDTO.getTransID();
																				incErrorFlag = icProcessor.registerError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_INC, IPixB2BConstants.InventoryChange, IConfigConstants.SCHEMA_InventoryChange, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate, errorList, transId, pneDTO);
																			}else{
																				if(IPixB2BConstants.flag_Y.equals(transaction_status))
																					updateTransStatus(IPixB2BConstants.STATUS_PASS);
																				else
																					updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			}
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_INC, IPixB2BConstants.InventoryChange, IConfigConstants.SCHEMA_InventoryChange, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.IC_ISSUE_DATE);					 
																	}
																}										
															}else if(IPixB2BConstants.InventoryStatus.equalsIgnoreCase(documentName.trim())){
																String transId = null;
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.InventoryStatus, IConfigConstants.SCHEMA_InventoryStatus));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.InventoryStatus, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.InventoryStatus, IConfigConstants.SCHEMA_InventoryStatus);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		isProcessor = inTransFactory.getInventoryStatusProcessor();
																		dataValidationFlag = isProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_INV, IPixB2BConstants.InventoryStatus, IConfigConstants.SCHEMA_InventoryStatus, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_INV, IPixB2BConstants.InventoryStatus, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			ArrayList<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
																			transaction_status = isProcessor.processInventoryStatus(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO, errorList);
																			
																			if(null!=errorList && errorList.size()>0){
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																				transId = transStatusDTO.getTransID();
																				invErrorFlag = isProcessor.registerError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_INV, IPixB2BConstants.InventoryStatus, IConfigConstants.SCHEMA_InventoryStatus, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate, errorList, transId, pneDTO);
																			}else{
																				if(IPixB2BConstants.flag_Y.equals(transaction_status))
																					updateTransStatus(IPixB2BConstants.STATUS_PASS);
																				else
																					updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			}
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_INV, IPixB2BConstants.InventoryStatus, IConfigConstants.SCHEMA_InventoryStatus, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.IS_ISSUE_DATE);					 
																	}
																}
															}else if(IPixB2BConstants.Usage.equalsIgnoreCase(documentName.trim())){
																String transId = null;
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.Usage, IConfigConstants.SCHEMA_Usage));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.Usage, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.Usage, IConfigConstants.SCHEMA_Usage);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		B2BGlobalData.getGlobalDataObject().addToFileContext(Thread.currentThread().getName(), new HashMap<String, String>());
																		usgProcessor = inTransFactory.getUsageProcessor();
																		dataValidationFlag = usgProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_USG, IPixB2BConstants.Usage, IConfigConstants.SCHEMA_Usage, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_USG, IPixB2BConstants.Usage, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			//ArrayList errorList = new ArrayList();
																			ArrayList<ErrorDTO> errorList = new ArrayList<ErrorDTO>();																			//B2BGlobalData.getGlobalDataObject().addToFileContext(Thread.currentThread().getName(), new HashMap<String, String>());

																			transaction_status = usgProcessor.processUsageDetails(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO, errorList);
																			
																			if(null!=errorList && errorList.size()>0){
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																				transId = transStatusDTO.getTransID();
																				usageErrorFlag = usgProcessor.registerError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_USG, IPixB2BConstants.Usage, IConfigConstants.SCHEMA_Usage, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate, errorList, transId, pneDTO);
																			}else{
																				if(IPixB2BConstants.flag_Y.equals(transaction_status))
																					updateTransStatus(IPixB2BConstants.STATUS_PASS);
																				else
																					updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			}
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_USG, IPixB2BConstants.Usage, IConfigConstants.SCHEMA_Usage, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.USAGE_ISSUE_DATE);					 
																	}
																}
															}else if(IPixB2BConstants.GoodsReceipt.equalsIgnoreCase(documentName.trim())){
																String transId = null;
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.GoodsReceipt, IConfigConstants.SCHEMA_GoodsReceipt));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.GoodsReceipt, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if(hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		//updateTransStatus(STATUS_PASS);
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.GoodsReceipt, IConfigConstants.SCHEMA_GoodsReceipt);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		//transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, transType_GRE, GoodsReceipt, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																		grProcessor = inTransFactory.getGRProcessor();
																		dataValidationFlag = grProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_GRE, IPixB2BConstants.GoodsReceipt, IConfigConstants.SCHEMA_GoodsReceipt, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_GRE, IPixB2BConstants.GoodsReceipt, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			ArrayList<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
																			transaction_status = grProcessor.processGRDetails(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO,errorList);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			
																			transId = transStatusDTO.getTransID();
																			if(null!=errorList && errorList.size()>0){
																				grErrorFlag = grProcessor.registerError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_GRE, IPixB2BConstants.GoodsReceipt, IConfigConstants.SCHEMA_GoodsReceipt, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate,errorList,transId);
																				if(!grErrorFlag){
																					updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																				}
																			}													
																	   }															
																		
																	}																	
																	
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_GRE, IPixB2BConstants.GoodsReceipt, IConfigConstants.SCHEMA_GoodsReceipt, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.GR_ISSUE_DATE);					 
																	}
																}
																
															}else if(IPixB2BConstants.Invoice.equalsIgnoreCase(documentName.trim())){
																
																String transId = null;
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.Invoice, IConfigConstants.SCHEMA_Invoice));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.Invoice, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){													
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.Invoice, IConfigConstants.SCHEMA_Invoice);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);													
																		invoProcessor = inTransFactory.getInvoiceProcessor();													
																		dataValidationFlag = invoProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_INVO, IPixB2BConstants.Invoice, IConfigConstants.SCHEMA_Invoice, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																																											
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_INVO, IPixB2BConstants.Invoice, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			ArrayList<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
																			transaction_status = invoProcessor.processInvoice(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO,errorList);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			
																			transId = transStatusDTO.getTransID();
																			if(null!=errorList && errorList.size()>0){
																				invoiceErrorFlag = invoProcessor.registerError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_INVO, IPixB2BConstants.Invoice, IConfigConstants.SCHEMA_Invoice, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate,errorList,transId);
																				if(!invoiceErrorFlag){
																					updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																				}
																			}													
																	   }
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_INVO, IPixB2BConstants.Invoice, IConfigConstants.SCHEMA_Invoice, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO,IPixB2BConstants.INVO_ISSUE_DATE);
																	}
																}
															}else if(IPixB2BConstants.ShipmentStatus.equalsIgnoreCase(documentName.trim()) && 
																	IPixB2BConstants.transType_DMB.equals(arrStrFileName[2])){
																String transId = null;
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.ShipmentStatus, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		asnProcessor = inTransFactory.getAdvanceShipmentNoticeProcessor();
																		dataValidationFlag = asnProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_DMB, IPixB2BConstants.ShipmentStatus, IConfigConstants.SCHEMA_DeliveryMessage, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			//updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_DMB, IPixB2BConstants.ShipmentStatus, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			ArrayList<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
																			transaction_status = asnProcessor.processAdvanceShipmentNotice(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO, errorList);
																			if(null!=errorList && errorList.size()>0){
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																				transId = transStatusDTO.getTransID();
																				asnErrorFlag = asnProcessor.registerError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_DMB, IPixB2BConstants.ShipmentStatus, IConfigConstants.SCHEMA_DeliveryMessage, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate, errorList, transId, pneDTO);
																			}else{
																				if(IPixB2BConstants.flag_Y.equals(transaction_status))
																					updateTransStatus(IPixB2BConstants.STATUS_PASS);
																				else
																					updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			}
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_DMB, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.ASN_ISSUE_DATE);					 
																	}
																}
															}else if(IPixB2BConstants.BusinessAcknowledgement.equalsIgnoreCase(documentName.trim())){
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.BusinessAcknowledgement, IConfigConstants.SCHEMA_BusinessAcknowledgement));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.BusinessAcknowledgement, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.BusinessAcknowledgement, IConfigConstants.SCHEMA_BusinessAcknowledgement);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		baProcessor = inTransFactory.getBusinessAckProcessor();
																		dataValidationFlag = baProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_ACK, IPixB2BConstants.BusinessAcknowledgement, IConfigConstants.SCHEMA_BusinessAcknowledgement, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_ACK, IPixB2BConstants.BusinessAcknowledgement, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			transaction_status = baProcessor.processBusinessAcknowledgement(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_ACK, IPixB2BConstants.BusinessAcknowledgement, IConfigConstants.SCHEMA_BusinessAcknowledgement, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.BA_ISSUE_DATE);					 
																	}
																}
															}
															else if(IPixB2BConstants.DeliveryMessage.equalsIgnoreCase(documentName.trim()) &&
																	IPixB2BConstants.transType_DM.equals(arrStrFileName[2])){
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.DeliveryMessage, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		dmProcessor = inTransFactory.getDeliveryMessageProcessor();
																		dataValidationFlag = dmProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_DME, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_DME, IPixB2BConstants.DeliveryMessage, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			transaction_status = dmProcessor.processDeliveryMessage(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_DME, IPixB2BConstants.DeliveryMessage, IConfigConstants.SCHEMA_DeliveryMessage, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.DM_ISSUE_DATE);					 
																	}
																}
															}else if(IPixB2BConstants.DeliveryMessageWood.equalsIgnoreCase(documentName.trim())){
																fileUtils.writeFile(xmlFileNameWithDir, b2bHelper.addTransSchemaIntoXml(xmlString, IPixB2BConstants.DeliveryMessageWood, IConfigConstants.SCHEMA_DeliveryMessageWood));
																xmlFileList = new ArrayList();
																xmlFileList.add(xmlFileNameWithDir);
																hmValidatedXmlList = b2bHelper.validateXmlFiles(xmlFileList, IPixB2BConstants.DeliveryMessageWood, papiNetSchema, vendorDTO.getVendorSAN(), vendorDTO.getVendorName());
																
																if(hmValidatedXmlList != null && hmValidatedXmlList.size()>0){
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.VALID_XML_LIST)){
																		b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, IPixB2BConstants.DeliveryMessageWood, IConfigConstants.SCHEMA_DeliveryMessageWood);
																		validXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.VALID_XML_LIST);
																		dmwProcessor = inTransFactory.getDeliveryMessageWoodProcessor();
																		dataValidationFlag = dmwProcessor.validateDataAndRegisterError(xmlFileNameWithDir, validXmlList, IPixB2BConstants.transType_DME, IPixB2BConstants.DeliveryMessageWood, IConfigConstants.SCHEMA_DeliveryMessageWood, vendorDTO.getVendorSAN(), inDirArchiveXmlInvalid, inTransFactory, documentNumber, documentDate);
																		if(!dataValidationFlag){
																			updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																			validXmlList.remove(0);
																		}
																		if(null!=validXmlList && validXmlList.size()>0)
																		{
																			updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			transStatusDTO = b2bHelper.getVendorsValidXmlStatus(validXmlList, documentNumber, IPixB2BConstants.transType_DME, IPixB2BConstants.DeliveryMessageWood, vendorDTO.getVendorSAN(), vendorDTO.getVendorName(), inDirArchiveXmlValid);
																			transaction_status = dmwProcessor.processDeliveryMessageWood(inTransFactory, xmlFileNameWithDir, inDirArchiveXmlValid, inDirArchiveXmlInvalid, vendorDTO.getVendorSAN(), transStatusDTO);
																			if(IPixB2BConstants.flag_Y.equals(transaction_status))
																				updateTransStatus(IPixB2BConstants.STATUS_PASS);
																			else
																				updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		}
																	}
																	if (hmValidatedXmlList.containsKey(IPixB2BConstants.INVALID_XML_LIST)){
																		updateTransStatus(IPixB2BConstants.STATUS_FAIL);
																		invalidXmlList = (ArrayList)hmValidatedXmlList.get(IPixB2BConstants.INVALID_XML_LIST);
																		b2bHelper.processVendorsInvalidXml(invalidXmlList, inDirArchiveXmlInvalid, IPixB2BConstants.transType_DME, IPixB2BConstants.DeliveryMessageWood, IConfigConstants.SCHEMA_DeliveryMessageWood, vendorDTO.getVendorSAN(), inTransFactory,documentNumber,documentDate, pneDTO, IPixB2BConstants.DM_ISSUE_DATE);					 
																	}
																}
															}
//														}
												    }
												}else{
													updateTransStatus(IPixB2BConstants.STATUS_FAIL);
													fileUtils.moveFile((String)xmlFileNameWithDir, inDirArchiveXmlInvalid);
													//communicate this error via Email utility 2
													B2BLogger.error("InboundVendorThread.run() :: DocumentName / DocumentNumber/ DocumentDate / SupplierSAN NOT found in Vendor XML "+xmlFileNameWithDir+" FOR vendorSAN = "+vendorDTO.getVendorSAN()+", vendorName = "+vendorDTO.getVendorName());
												}
											}else{
												updateTransStatus(IPixB2BConstants.STATUS_FAIL);
												B2BLogger.info("InboundVendorThread.run() :: Required data NOT found or parsing error in Vendor XML "+xmlFileNameWithDir+" FOR vendorSAN = "+vendorDTO.getVendorSAN()+", vendorName = "+vendorDTO.getVendorName());
											}					
									}
							} //end for loop - vendorXmlFileList
						}else{
							B2BLogger.info("XmlWriterCastor.generateXml() - VENDOR XML NOT FOUND ON "+DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy)+" FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
						}
					}else{
						B2BLogger.info("InboundVendorThread.run() :: FTP Server details are null");
					}					
				}else{
					B2BLogger.info("InboundVendorThread.run() : VendorSAN is null");
				}
			}else{
				B2BLogger.info("InboundVendorThread.run() : VendorSAN is null");
			}
			B2BLogger.debug("InboundVendorThread.run() method return - InboundVendorThread Thread FINISHED FOR vendorSAN = "+vendorSAN+", vendorName = "+vendorName);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: InboundVendorThread interrupted FOR vendorSAN = "+vendorSAN+", vendorName = "+vendorName);
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch(B2BException b2be) {
			B2BLogger.info("B2BException :: "+b2be.getMessage());
			
		} finally{
			updateGlobalStatus();
			inTransFactory 	= null;
			inVendorDAO 	= null;
			vendorDTO 		= null;
			ftpServerInfo	= null;		
			vendorXmlDownloadDir= null;
			inDirArchiveXmlValid = null;
			inDirArchiveXmlInvalid = null;
			fileDir			= null;
			ftpClientB2B 	= null;
			fileUtils		= null;
			vendorXmlList= null;
			b2bHelper		= null;
			xmlFileList 	= null;
			hmValidatedXmlList= null;
			validXmlList	= null;
			invalidXmlList	= null;
			xmlFileNameWithDir= null;
			xmlFile 		= null;
			xmlString 		= null;
			pneDTO 			= null;
			xmlReaderSax 	= null;
			documentName 	= null;
			documentNumber 	= null;
			transStatusDTO 	= null;
			osProcessor 	= null;
			ocProcessor 	= null;
			icProcessor 	= null;
			isProcessor 	= null;
			baProcessor		= null;
			usgProcessor     = null;
			
		}
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