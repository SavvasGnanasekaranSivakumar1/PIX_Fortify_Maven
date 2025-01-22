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
 * Title		: 	UsageDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam  24 Dec, 2009	Initial version
 *
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.AggrUsageLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.BuyerContact;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.BuyerNameAddress;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.EndContact;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.EndNameAddress;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.ItemLineInfoQty;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.ItemLineQuantity;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.ItemProdIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.ItemUsgProduct;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.ItemisedUsgLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.LineInfoQty;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageBuyerParty;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageDate;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageEndUser;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageIssueDate;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageProdIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageProduct;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformationDup;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * UsageDAOImpl is a class to communicate with the PIX database
 * and process the B2B Usage transaction data.
 * 
 * @author Abhilasha Nigam
 */
public class UsageDAOImpl extends InboundTransStatusDAOImpl implements IUsageDAO{
	
	private static final String qry_sel_pix_po_id = "SELECT po_id, po_version "
		+ " FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_summary " 
		+ " WHERE po_no = ? "; 			
	
	
	private static final String qry_sel_count = "SELECT count(*) records_count"
		+ " FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage "
		+ " WHERE po_id = ? "
		+ " AND transaction_history_no = ? ";   //v_trans_hist_no = XML Trans ID
	
	
	private static final String qry_sel_status_id = "SELECT status_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_status_code "
	+ " WHERE UPPER(status_code) = UPPER(?)"
	+ " AND table_id = (SELECT table_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_table " 
	+ " WHERE table_name = 'PIX_USAGE')" ;
	
	
	private static final String qry_sel_pix_usage_id_nextval = "SELECT SEQ_PIX_USAGE_ID.nextval v_usage_id FROM DUAL";
	
	
	private static final String qry_ins_pix_usage = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage "
	+ "(usage_id,creation_date_time,created_by,mod_date_time,modified_by,usage_no,"
	+ " status_id,po_id,po_version,transaction_history_no) VALUES(?,SYSDATE,?,"
	+ " SYSDATE,?,?,?,?,?,?) ";
	
	
	private static final String qry_sel_supplier_san = "SELECT supplier_san "        
		+ "FROM  "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_list_summary p "
		+ "WHERE po_no = ? "
		+ "AND replace(p.supplier_san, '-', '') = ? " ;  
	

	private static final String qry_ins_supplier_pix_usage_party = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage_party "
	+ "(san,name_1,name_2,name_3,org_unit_code,org_unit_name,address_1,address_2,address_3,address_4,"
	+ "city,state,postal_code,party_type,comments,party_line_no,creation_date_time,"
	+ "mod_date_time,created_by,modified_by,usage_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'2',SYSDATE,SYSDATE,?,?,?)";
	
	
	private static final String qry_ins_supplier_pix_usage_party_contact = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage_party_contact "
	+ "(contact_no,contact_type,contact_first_name,phone,mobile,fax,email,usage_id,party_line_no)"
	+ " VALUES(1,?,?,?,?,?,?,?,'2')" ;  
	
	
	private static final String qry_sel_buyer_san = "SELECT pub_unit_san"
		+ " FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_list_summary p "
		+ " WHERE po_no = ? " ;
	// + " AND replace(p.pub_unit_san, '-', '') = ? " ;  
	
	
	private static final String qry_ins_buyer_pix_usage_party = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage_party "
	+ "(san,name_1,name_2,name_3,org_unit_code,org_unit_name,address_1,address_2,address_3,address_4,city,"
	+ "state,postal_code,party_type,comments,party_line_no,creation_date_time,mod_date_time,"
	+ "created_by,modified_by,usage_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'1',SYSDATE,SYSDATE,?,?,?)";
	
	
	private static final String qry_ins_buyer_pix_usage_party_contact = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage_party_contact "
	+ " (contact_no,contact_type,contact_first_name,phone,mobile,fax,email,usage_id,party_line_no)"
	+ " VALUES('2',?,?,?,?,?,?,?,'1')" ;
	
	private static final String qry_sel_line_item_no = "SELECT line_item_no FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_usage "
	+ "WHERE po_id = ? AND decode((case when instr(product_code, '-', 1, 1) > 0 then 0 WHEN "
	+ "instr(product_code, '-', 1, 1) = 0 then 1 end), 0,substr(product_code, 4, 5),ltrim(product_code, '0')) = ltrim(?, '0') ";           
	
	
	private static final String qry_ins_pix_usage_line = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage_line "
	+ "(usage_quantity, damaged_quantity, comments, creation_date_time, created_by, mod_date_time, modified_by, "
	+ " uom_id, usage_id, status_id, po_id, line_item_no)VALUES(?, ?, ?, SYSDATE, ?, SYSDATE, ?, "
	+ " '1',?, ?, ?, ?)" ;
	
	private static final String qry_sel_qty_description = "SELECT description from "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_ref "
	+ " WHERE group_code = 'USG_QTY_TYPE'";
	
	
	private static final String qry_sel_uom_id = "SELECT uom_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_uom_code "
	+ " WHERE  xuom = ? ";
	
	private static final String qry_ins_pix_usage_other_quantities = "INSERT into "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_usage_other_quantities "
	+ "(Usage_id,TRANSACTION_HISTORY_NO,Document_no,Document_Issue_date,PO_ID,po_version,po_line_no,USAGE_TYPE,"
	+ " USAGE_QTY,UOM_id,CREATION_DATE_TIME,MOD_DATE_TIME) VALUES(?,?,?,to_date(?,'MM/DD/YYYY'),?,?,?,?,?,?,SYSDATE,SYSDATE)" ;
	
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.usage.dao.IUsageDAO#storeUsageDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String storeUsageDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid){
		
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		PreparedStatement prepStmt2	= null;
		ResultSet resultSet2		= null;		
		PreparedStatement prepStmt3	= null;
		ResultSet resultSet3		= null;
		PreparedStatement prepStmt4	= null;
		ResultSet resultSet4		= null;
		PreparedStatement prepStmt5	= null;
		PreparedStatement prepStmt6	= null;
		ResultSet resultSet6		= null;
		PreparedStatement prepStmt7	= null;
		PreparedStatement prepStmt8	= null;
		PreparedStatement prepStmt9	= null;
		ResultSet resultSet9		= null;
		PreparedStatement prepStmt10= null;
		PreparedStatement prepStmt11= null;
		PreparedStatement prepStmt12= null;
		ResultSet resultSet12		= null;
		PreparedStatement prepStmt13= null;
		PreparedStatement prepStmt14= null;
		ResultSet resultSet14		= null;
		PreparedStatement prepStmt15= null;
		ResultSet resultSet15		= null;
		PreparedStatement prepStmt16= null;
		
		
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		String dbPOId				= null;
		String dbPOVersion			= null;
		HashMap hmPoIdpoVersion		= null;
		long dbTransIdNext 			= -1;
		String usageInsStatus 	= IPixB2BConstants.flag_N;
		UsageDTO usgDTO = null;
		UsageHeader usgHdr = null;
		UsageIssueDate usgIssueDate = null;
		UsageDate usgDate = null;
		UsageLineItem usgLineItem = null;
		AggrUsageLineItem aggrLineItem = null;
		ItemisedUsgLineItem itemzd = null;
		POInformation poInfo = null;
		POInformationDup itemPoInfo	= null;
		ArrayList usgLineItemList  = null;
		UsageReference xmlUsgRef = null;
		EndNameAddress xmlNameAddress = null;
		EndContact endContactInfo = null;
		BuyerNameAddress xmlBuyerInfo = null;
		BuyerContact buyerContact = null;
		ArrayList usgRefList = null;
		String xmlUsgRefType = null;
		String xmlUsgTransID = null;
		String xmlUsgNo   = null;
		String xmlPONumber = null;
		String xmlUsgStatus = null;
		String usgStatusId = null;
		String usgId = null;
		String xmlSupplierSAN = null;
		String xmlBuyerSAN = null;
		String supplierSAN = null;
		String buyerSAN = null;
		ArrayList xmlProdIdentfList = null;
		UsageProduct usgProd = null;
		UsageProdIdentifier xmlProdIdentf = null;
		ItemProdIdentifier xmlItemProdIdentf = null;
		String xmlProdCode = null;
		String xmlLineQty = null;
		String xmlInfoQty = null;
		LineQuantity lineQty = null;
		LineInfoQty lineInfoQty = null;
		ItemLineInfoQty itemLineInfoQty	= null;
		ArrayList infoQtyList = null;
		String lineItemNo = null;
		ArrayList commentsList = null;
		String usgLineComments = "";
		String xmlComments = null;
		String qtyType = null;
		String xmlIssueDate = null;
		String usgInfoQty = null;
		String uomId = null;
		String uomDesc = null;
		HashMap qtyMap = null;
		String qtyValue = null;
		String qtyUOM = null;
		String orgCode= null;
		String orgName = null;
		String name1 = null;
		String name2 = null;
		String name3 = null;
		String addr1 = null;
		String addr2 = null;
		String addr3 = null;
		String addr4 = null;
		String state = null;		
		String postalCode = null;
		String city = null;
		String supplierComments = "";
		String contactType = null;
		String contactName = null;
		String phone = null;
		String mobile = null;
		String fax = null;
		String email = null;
		String buyerName1 = null;
		String buyerName2 = null;
		String buyerName3 = null;
		String buyerOrgCode = null;
		String buyerOrgName = null;
		String buyerAddr1 = null;
		String buyerAddr2 = null;
		String buyerAddr3 = null;
		String buyerAddr4= null;
		String buyerCity = null;
		String buyerState = null;
		String buyerPCode = null;
		String buyerComments = "";
		String buyerConType = null;
		String buyerConName = null;
		String buyerConPhone = null;
		String buyerConMobile = null;
		String buyerConFax = null;
		String buyerConEmail = null;
		UsageBuyerParty xmlBuyerParty = null;
		ArrayList buyCommentList = null;
		String xmlBuyComments = null;
		ArrayList supplCommentList = null;
		String xmlSuppComments = null;
		UsageEndUser xmlEndParty = null;
		ItemUsgProduct itemUsgProd = null;
		ItemLineQuantity itemLineQty = null;
		StringBuffer sbPOId 	= null;
		StringBuffer sbUsgId 	= null;
		String strPOId			= null;
		String strUsgId			= null;
		String xmlLineItemNo = null;
		Set poNumberSet	= null;
		Iterator itr	= null;
		String xmlPoNo	= null;
		int errorId		= -1 ;
		ErrorDTO daoErrorDTO    = null;
		
		String transRefId1			= null;
		String transRefLabel1		= null;	
		String transRefId2			= null;
		String transRefLabel2		= null;
		
		try {
			B2BLogger.debug("******* UsageDAOImpl.storeUsageDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
//					daoErrorDTO = new ErrorDTO();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_USG);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");						
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);						
						
						xmlUsgStatus = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsgStatusType(); //status code					
						
						usgHdr = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageHeader();						
						if(usgHdr != null){							
							xmlUsgNo = usgHdr.getUsageNumber();   //Document Number							
							
							usgIssueDate = usgHdr.getUsageDate();  //Document Date -xmlUsgIssueDate
							if(usgIssueDate != null){	
								xmlIssueDate = usgIssueDate.getUsageDate().getMonth()
								+ "/"+usgIssueDate.getUsageDate().getDay() 
								+ "/"+usgIssueDate.getUsageDate().getYear();										
							}								
							B2BLogger.info("UsageDAOImpl.storeUsageDetails() - UsageIssueDate = "+ xmlIssueDate);
							
							xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_USG);
							if(xmlSupplierSAN.contains("-")){
								xmlSupplierSAN = xmlSupplierSAN.replaceAll("-","");
							}
							xmlBuyerSAN = usgHdr.getUsageBuyerParty().getPartyIdentifier().getPartyIdentifierValue();
							
							xmlBuyerParty = usgHdr.getUsageBuyerParty();
							if(xmlBuyerParty != null){
								xmlBuyerInfo = xmlBuyerParty.getNameAddress();								
								if(xmlBuyerInfo != null){
									buyerName1 = xmlBuyerInfo.getName1();
									buyerName2 = xmlBuyerInfo.getName2();
									buyerName3 = xmlBuyerInfo.getName3();
									if(xmlBuyerInfo.getBuyerOrgUnit() != null){
										buyerOrgCode = xmlBuyerInfo.getBuyerOrgUnit().getOrgUnitCode();
										buyerOrgName = xmlBuyerInfo.getBuyerOrgUnit().getOrgUnitName();
									}
									buyerAddr1 = xmlBuyerInfo.getAddress1();
									buyerAddr2 = xmlBuyerInfo.getAddress2();
									buyerAddr3 = xmlBuyerInfo.getAddress3();
									buyerAddr4 = xmlBuyerInfo.getAddress4();
									buyerCity = xmlBuyerInfo.getCity();
									buyerState = xmlBuyerInfo.getStateOrProvince();
									buyerPCode = xmlBuyerInfo.getPostalCode();
								}
								
								buyerContact = usgHdr.getUsageBuyerParty().getContactInfo();
								if(buyerContact != null){
									buyerConType = buyerContact.getContactType();
									buyerConName = buyerContact.getContactFirstName();
									buyerConPhone = buyerContact.getPhone();
									buyerConMobile = buyerContact.getMobile();
									buyerConFax = buyerContact.getFax();
									buyerConEmail = buyerContact.getEmail();
								}
								buyCommentList = xmlBuyerParty.getBuyerComments();
								if(buyCommentList != null && buyCommentList.size()>0){
									for(int i=0;i<buyCommentList.size();i++){
										xmlBuyComments = (String)buyCommentList.get(i);
										buyerComments = buyerComments +(i == 0 ? "" : " ")+xmlBuyComments;
									}
								}
							}
							
							xmlEndParty = usgHdr.getUsageEndUser();
							if(xmlEndParty != null){
								xmlNameAddress = xmlEndParty.getNameAddress();	
								if(xmlNameAddress != null){
									name1 = xmlNameAddress.getName1();
									name2 = xmlNameAddress.getName2();
									name3 = xmlNameAddress.getName3();
									addr1 = xmlNameAddress.getAddress1();
									addr2 = xmlNameAddress.getAddress2();
									addr3= xmlNameAddress.getAddress3();
									addr4 = xmlNameAddress.getAddress4();
									city = xmlNameAddress.getCity();
									state =  xmlNameAddress.getStateOrProvince();
									postalCode = xmlNameAddress.getPostalCode();								
									if(xmlNameAddress.getOrgUnit() != null){								
										orgCode = xmlNameAddress.getOrgUnit().getOrgUnitCode();
										orgName = xmlNameAddress.getOrgUnit().getOrgUnitName();
									}									
								}
								
								endContactInfo = usgHdr.getUsageEndUser().getContactInfo();
								if(endContactInfo != null){
									contactType = endContactInfo.getContactType();
									contactName = endContactInfo.getContactName();
									phone = endContactInfo.getPhone();
									mobile = endContactInfo.getMobile();
									fax = endContactInfo.getFax();
									email = endContactInfo.getEmail();
								}	
								
								supplCommentList = xmlEndParty.getEndComments();
								if(supplCommentList != null && supplCommentList.size()>0){
									for(int i=0;i<supplCommentList.size();i++){
										xmlSuppComments = (String)supplCommentList.get(i);
										supplierComments = supplierComments +(i == 0 ? "" : " ")+xmlSuppComments;
									}
								}
								
							}
							
							usgRefList = usgHdr.getUsageRef();
							if(usgRefList != null && usgRefList.size()>0){
								for(int j=0;j<usgRefList.size();j++){
									xmlUsgRef = (UsageReference)usgRefList.get(j);
									if(xmlUsgRef != null && xmlUsgRef.getUsageRefType()!= null ){
										if(IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlUsgRef.getUsageRefType().trim()))
											xmlUsgTransID = xmlUsgRef.getUsageRefValue();
									}
								}		
							}						
						}	
						
						if(xmlUsgTransID != null && !"".equals(xmlUsgTransID.trim())){					
							dbCon.setAutoCommit(false);													
							usgLineItemList = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageLineItem();
							if(usgLineItemList != null && usgLineItemList.size()>0){
								poNumberSet = new HashSet();
								HashSet dupPONumList	= new HashSet();
								for(int k=0;k<usgLineItemList.size();k++){
									daoErrorDTO = new ErrorDTO();
									usgLineItem = (UsageLineItem)usgLineItemList.get(k);
									if(usgLineItem != null){	
										if(usgLineItem.getAggrLineItem()!= null && usgLineItem.getAggrLineItem().getPoInfo() != null){
											poInfo = usgLineItem.getAggrLineItem().getPoInfo();
											if(null!=poInfo){
												xmlPONumber = poInfo.getPoNumber();
												if(xmlPONumber != null && !"".equals(xmlPONumber)){
													String tableName = "PIX_PO_SUMMARY";
													String whereClause = "PO_NO='"+xmlPONumber+"'";
													int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
													if(count<=0){
														errorId = IPixB2BConstants.ERROR_ID_30;
														daoErrorDTO.setErrorID(errorId+"");
														daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_30.concat(":").concat(xmlPONumber));
														errorList.add(daoErrorDTO);
														B2BLogger.info("UsageDAOImpl.storeUsageDetails()-The PO Number: "+xmlPONumber+ "is not present in PIX_PO_SUMMARY table");
														if(!dupPONumList.contains(xmlPONumber)){
															B2BGlobalData.getGlobalDataObject().addInfoToFileContext(Thread.currentThread().getName(), IPixB2BConstants.PO_NO, xmlPONumber);															
														}
														dupPONumList.add(xmlPONumber);
													}else{
														poNumberSet.add(xmlPONumber);
													}
												}else{
													errorId = IPixB2BConstants.ERROR_ID_74;
													daoErrorDTO.setErrorID(errorId+"");
													daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_74);
													errorList.add(daoErrorDTO);
													B2BLogger.info("UsageDAOImpl.storeUsageDetails() - xmlPONumber is null or blank");
												}
											}
										}else if(usgLineItem.getItemzdLineItem()!= null && usgLineItem.getItemzdLineItem().getItemPoInfo() != null){
											itemPoInfo = usgLineItem.getItemzdLineItem().getItemPoInfo();
											if(null!=itemPoInfo){
												xmlPONumber = itemPoInfo.getPoNumber();
												if(xmlPONumber != null && !"".equals(xmlPONumber)){
													String tableName = "PIX_PO_SUMMARY";
													String whereClause = "PO_NO='"+xmlPONumber+"'";
													int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
													if(count<=0){
														errorId = IPixB2BConstants.ERROR_ID_30;
														daoErrorDTO.setErrorID(errorId+"");
														daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_30.concat(":").concat(xmlPONumber));
														errorList.add(daoErrorDTO);
														B2BLogger.info("UsageDAOImpl.storeUsageDetails()-The PO Number: "+xmlPONumber+ "is not present in PIX_PO_SUMMARY table");
														if(!dupPONumList.contains(xmlPONumber)){
															B2BGlobalData.getGlobalDataObject().addInfoToFileContext(Thread.currentThread().getName(), IPixB2BConstants.PO_NO, xmlPONumber);															
														}
														dupPONumList.add(xmlPONumber);
													}else{
														poNumberSet.add(xmlPONumber);
													}
												}else{
													errorId = IPixB2BConstants.ERROR_ID_74;
													daoErrorDTO.setErrorID(errorId+"");
													daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_74);
													errorList.add(daoErrorDTO);
													B2BLogger.info("UsageDAOImpl.storeUsageDetails() - xmlPONumber is null or blank");
												}
											}
										}
									}		
								}
							}
							if(null!=poNumberSet && poNumberSet.size()>0){
								itr = poNumberSet.iterator();
								sbPOId 	= new StringBuffer();
								sbUsgId = new StringBuffer();
								while (itr.hasNext()) {
									xmlPONumber = (String) itr.next();
									B2BGlobalData.getGlobalDataObject().addInfoToFileContext(Thread.currentThread().getName(), IPixB2BConstants.PO_NO, xmlPONumber);
									//Header level
									sqlQuery = qry_sel_pix_po_id;
									prepStmt1 = dbCon.prepareStatement(sqlQuery);
									prepStmt1.clearParameters();	
									prepStmt1.setString(IPixB2BConstants.ONE,xmlPONumber);
									qryParams = xmlPONumber;
									resultSet1 = prepStmt1.executeQuery();
									while(resultSet1.next()){
										dbPOId = resultSet1.getString("po_id");
										dbPOVersion = resultSet1.getString("po_version");
									}
									DBUtils.close(prepStmt1);
									DBUtils.close(resultSet1);	
									B2BLogger.info("UsageDAOImpl.storeUsageDetails() - dbPOId = "+ dbPOId + "dbPOVersion = " + dbPOVersion);
									if(null!=dbPOId)
										sbPOId.append(dbPOId+IPixB2BConstants.COMMA);
									
									sqlQuery = qry_sel_count;
									prepStmt2 = dbCon.prepareStatement(sqlQuery);
									prepStmt2.clearParameters();
									prepStmt2.setString(IPixB2BConstants.ONE,dbPOId);
									prepStmt2.setString(IPixB2BConstants.TWO,xmlUsgTransID);
									qryParams = dbPOId+","+xmlUsgTransID;
									resultSet2 = prepStmt2.executeQuery();
									if(resultSet2.next() && resultSet2.getLong("records_count") == 0){  //process transcn if no records found
										
										if(xmlUsgStatus != null || !"".equals(xmlUsgStatus.trim())){
											sqlQuery = qry_sel_status_id;
											prepStmt3 = dbCon.prepareStatement(sqlQuery);
											prepStmt3.clearParameters();
											prepStmt3.setString(IPixB2BConstants.ONE,xmlUsgStatus);
											qryParams = xmlUsgStatus;
											resultSet3 = prepStmt3.executeQuery();
											while(resultSet3.next()){
												usgStatusId = resultSet3.getString("status_id");
											}
											DBUtils.close(prepStmt3);
											DBUtils.close(resultSet3);	
										}	
										
										sqlQuery = qry_sel_pix_usage_id_nextval;
										prepStmt4 = dbCon.prepareStatement(sqlQuery);
										prepStmt4.clearParameters();
										resultSet4 = prepStmt4.executeQuery();
										while(resultSet4.next()){
											usgId = resultSet4.getString("v_usage_id");
										}
										DBUtils.close(prepStmt4);
										DBUtils.close(resultSet4);	
										if(null!=usgId){
											sbUsgId.append(usgId+IPixB2BConstants.COMMA);
										}
										
										sqlQuery = qry_ins_pix_usage;
										prepStmt5 = dbCon.prepareStatement(sqlQuery);
										prepStmt5.clearParameters();
										prepStmt5.setString(IPixB2BConstants.ONE,usgId);
										prepStmt5.setString(IPixB2BConstants.TWO,IPixB2BConstants.JavaB2B);
										prepStmt5.setString(IPixB2BConstants.THREE,IPixB2BConstants.JavaB2B);
										prepStmt5.setString(IPixB2BConstants.FOUR,xmlUsgNo);
										prepStmt5.setString(IPixB2BConstants.FIVE,usgStatusId);
										prepStmt5.setString(IPixB2BConstants.SIX,dbPOId);
										prepStmt5.setString(IPixB2BConstants.SEVEN,dbPOVersion);
										prepStmt5.setString(IPixB2BConstants.EIGHT,xmlUsgTransID);
										qryParams = usgId+","+xmlUsgNo+","+usgStatusId+","+dbPOId+","+dbPOVersion+","+xmlUsgTransID;
										recsNum = prepStmt5.executeUpdate();
										recsCount = recsCount + recsNum;
										DBUtils.close(prepStmt5);
										
										sqlQuery = qry_sel_supplier_san;
										prepStmt6 = dbCon.prepareStatement(sqlQuery);
										prepStmt6.clearParameters();
										prepStmt6.setString(IPixB2BConstants.ONE,xmlPONumber);
										prepStmt6.setString(IPixB2BConstants.TWO,xmlSupplierSAN);
										qryParams = xmlPONumber+","+xmlSupplierSAN;
										resultSet6 = prepStmt6.executeQuery();
										while(resultSet6.next()){
											supplierSAN = resultSet6.getString("supplier_san");
										}
										DBUtils.close(prepStmt6);
										DBUtils.close(resultSet6);	
										
										if(supplierSAN != null && !"".equals(supplierSAN.trim())){								
											sqlQuery = qry_ins_supplier_pix_usage_party;
											prepStmt7 = dbCon.prepareStatement(sqlQuery);
											prepStmt7.clearParameters();
											prepStmt7.setString(IPixB2BConstants.ONE, supplierSAN);
											prepStmt7.setString(IPixB2BConstants.TWO, name1);
											prepStmt7.setString(IPixB2BConstants.THREE, name2);
											prepStmt7.setString(IPixB2BConstants.FOUR, name3);
											prepStmt7.setString(IPixB2BConstants.FIVE, orgCode);
											prepStmt7.setString(IPixB2BConstants.SIX, orgName);
											prepStmt7.setString(IPixB2BConstants.SEVEN, addr1);
											prepStmt7.setString(IPixB2BConstants.EIGHT, addr2);
											prepStmt7.setString(IPixB2BConstants.NINE, addr3);
											prepStmt7.setString(IPixB2BConstants.TEN, addr4);
											prepStmt7.setString(IPixB2BConstants.ELEVEN, city);
											prepStmt7.setString(IPixB2BConstants.TWELVE, state);
											prepStmt7.setString(IPixB2BConstants.THIRTEEN,postalCode);
											prepStmt7.setString(IPixB2BConstants.FOURTEEN,IPixB2BConstants.flag_V);
											prepStmt7.setString(IPixB2BConstants.FIFTEEN,supplierComments);
											prepStmt7.setString(IPixB2BConstants.SIXTEEN,IPixB2BConstants.JavaB2B);
											prepStmt7.setString(IPixB2BConstants.SEVENTEEN,IPixB2BConstants.JavaB2B);
											prepStmt7.setString(IPixB2BConstants.EIGHTEEN,usgId);								
											qryParams = supplierSAN+","+name1+","+name2+","+name3+","+orgCode+","+orgName+","+addr1+","+addr2+","+addr3+","+addr4+","+city+","+state+","+postalCode+","+supplierComments+","+usgId;
											recsNum = prepStmt7.executeUpdate();
											recsCount = recsCount + recsNum;
											DBUtils.close(prepStmt7);
											
											//Update pix_usage_party_contact
											sqlQuery = qry_ins_supplier_pix_usage_party_contact;
											prepStmt8 = dbCon.prepareStatement(sqlQuery);
											prepStmt8.clearParameters();
											prepStmt8.setString(IPixB2BConstants.ONE,contactType);
											prepStmt8.setString(IPixB2BConstants.TWO,contactName);
											prepStmt8.setString(IPixB2BConstants.THREE,phone);
											prepStmt8.setString(IPixB2BConstants.FOUR,mobile);
											prepStmt8.setString(IPixB2BConstants.FIVE,fax);
											prepStmt8.setString(IPixB2BConstants.SIX,email);
											prepStmt8.setString(IPixB2BConstants.SEVEN,usgId);									
											qryParams = contactType+","+contactName+","+phone	+","+mobile+","+fax+","+email+","+usgId;
											recsNum = prepStmt8.executeUpdate();
											recsCount = recsCount + recsNum;
											DBUtils.close(prepStmt8);
										}
										
										sqlQuery = qry_sel_buyer_san;
										prepStmt9 = dbCon.prepareStatement(sqlQuery);
										prepStmt9.clearParameters();
										prepStmt9.setString(IPixB2BConstants.ONE,xmlPONumber);	
										qryParams = xmlPONumber ;
										resultSet9 = prepStmt9.executeQuery();
										while(resultSet9.next()){
											buyerSAN = resultSet9.getString("PUB_UNIT_SAN");
										}
										DBUtils.close(prepStmt9);
										DBUtils.close(resultSet9);	
										
										if(buyerSAN != null && !"".equals(buyerSAN.trim())){								
											//insert into pix_usage_party
											sqlQuery = qry_ins_buyer_pix_usage_party ;
											prepStmt10 = dbCon.prepareStatement(sqlQuery);
											prepStmt10.clearParameters();
											prepStmt10.setString(IPixB2BConstants.ONE,buyerSAN);
											prepStmt10.setString(IPixB2BConstants.TWO,buyerName1);
											prepStmt10.setString(IPixB2BConstants.THREE,buyerName2);
											prepStmt10.setString(IPixB2BConstants.FOUR,buyerName3);
											prepStmt10.setString(IPixB2BConstants.FIVE,buyerOrgCode);
											prepStmt10.setString(IPixB2BConstants.SIX,buyerOrgName);
											prepStmt10.setString(IPixB2BConstants.SEVEN,buyerAddr1);
											prepStmt10.setString(IPixB2BConstants.EIGHT,buyerAddr2);
											prepStmt10.setString(IPixB2BConstants.NINE,buyerAddr3);
											prepStmt10.setString(IPixB2BConstants.TEN,buyerAddr4);
											prepStmt10.setString(IPixB2BConstants.ELEVEN,buyerCity);
											prepStmt10.setString(IPixB2BConstants.TWELVE,buyerState);
											prepStmt10.setString(IPixB2BConstants.THIRTEEN,buyerPCode);
											prepStmt10.setString(IPixB2BConstants.FOURTEEN,IPixB2BConstants.flag_B);
											prepStmt10.setString(IPixB2BConstants.FIFTEEN,buyerComments);
											prepStmt10.setString(IPixB2BConstants.SIXTEEN,IPixB2BConstants.JavaB2B);
											prepStmt10.setString(IPixB2BConstants.SEVENTEEN,IPixB2BConstants.JavaB2B);
											prepStmt10.setString(IPixB2BConstants.EIGHTEEN,usgId);
											
											qryParams = buyerSAN+","+buyerName1+","+buyerName2+","+buyerName3+","+buyerOrgCode+","+buyerOrgName+","+buyerAddr1
											+","+buyerAddr2+","+buyerAddr3+","+buyerAddr4+","+buyerCity+","+buyerState+","+buyerPCode+","+buyerComments+","+usgId ;
											recsNum = prepStmt10.executeUpdate();
											recsCount = recsCount + recsNum;
											DBUtils.close(prepStmt10);
											
											//Insert into pix_usage_party_contact
											sqlQuery = qry_ins_buyer_pix_usage_party_contact;
											prepStmt11 = dbCon.prepareStatement(sqlQuery);
											prepStmt11.clearParameters();
											prepStmt11.setString(IPixB2BConstants.ONE,buyerConType);
											prepStmt11.setString(IPixB2BConstants.TWO,buyerConName);
											prepStmt11.setString(IPixB2BConstants.THREE,buyerConPhone);
											prepStmt11.setString(IPixB2BConstants.FOUR,buyerConMobile);
											prepStmt11.setString(IPixB2BConstants.FIVE,buyerConFax);
											prepStmt11.setString(IPixB2BConstants.SIX,buyerConEmail);
											prepStmt11.setString(IPixB2BConstants.SEVEN,usgId);	
											
											qryParams = buyerConType+","+buyerConName+","+buyerConPhone+","+buyerConMobile+","+buyerConFax+","+usgId;
											recsNum = prepStmt11.executeUpdate();
											recsCount = recsCount + recsNum;
											DBUtils.close(prepStmt11);
										}
										
										//Line Level Queries	
										//Chk dis line
										usgLineItemList = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageLineItem();
										if(usgLineItemList != null && usgLineItemList.size()>0){
											for(int l=0;l<usgLineItemList.size();l++){
												daoErrorDTO = new ErrorDTO();
												usgLineItem = (UsageLineItem)usgLineItemList.get(l);
												if(usgLineItem != null){
													poInfo = null;
													xmlPoNo = null;
													if(usgLineItem.getAggrLineItem()!= null || usgLineItem.getItemzdLineItem()!= null){
														if(usgLineItem.getAggrLineItem()!= null){
															poInfo = usgLineItem.getAggrLineItem().getPoInfo();
															if(poInfo != null){
																xmlPoNo = poInfo.getPoNumber();
															}
														}else if(usgLineItem.getItemzdLineItem().getItemPoInfo()!= null){
															xmlPoNo = usgLineItem.getItemzdLineItem().getItemPoInfo().getPoNumber();
														}
													}
													if(null==xmlPoNo || !xmlPoNo.equalsIgnoreCase(xmlPONumber)){
														continue;
													}else{
														usgLineComments = "" ;
														xmlLineItemNo = null;
														
														xmlLineItemNo = usgLineItem.getUsageLineItemNo();
														commentsList = usgLineItem.getComments();
														if(commentsList != null && commentsList.size()>0){
															for(int m=0;m<commentsList.size();m++){
																xmlComments = (String)commentsList.get(m);
																usgLineComments = usgLineComments +(m == 0 ? "" : " ")+ xmlComments;
															}
															
														}
														
														if(usgLineItem.getAggrLineItem()!= null || usgLineItem.getItemzdLineItem()!= null){
															xmlProdCode = null;	
															lineItemNo = null;
															xmlLineQty = null;
															xmlInfoQty = null;
															xmlProdIdentfList = null;
															infoQtyList = null;
//															poInfo = usgLineItem.getAggrLineItem().getPoInfo();
//															if(poInfo != null){
//																xmlPONumber = poInfo.getPoNumber();
//															}else if(usgLineItem.getItemzdLineItem().getItemPoInfo()!= null){
//																xmlPONumber = usgLineItem.getItemzdLineItem().getItemPoInfo().getPoNumber();
//															}										
															if(usgLineItem.getAggrLineItem()!= null){
																usgProd = usgLineItem.getAggrLineItem().getUsgProduct();
																if(usgProd != null){
																	xmlProdIdentfList = usgProd.getProdIdentifier();
																}
															}
															else if(usgLineItem.getItemzdLineItem()!=null){
																	itemUsgProd = usgLineItem.getItemzdLineItem().getIusgProduct();
																	if(itemUsgProd != null){
																		xmlProdIdentfList = itemUsgProd.getIProdIdentifier();	
																	}
															}
															
															if(xmlProdIdentfList != null && xmlProdIdentfList.size()> 0){
																for(int n=0; n<xmlProdIdentfList.size(); n++){
																	if(usgLineItem.getAggrLineItem()!= null){
																		xmlProdIdentf = (UsageProdIdentifier)xmlProdIdentfList.get(n);
																		if(xmlProdIdentf != null && xmlProdIdentf.getProductAgency() != null && IPixB2BConstants.Buyer.equalsIgnoreCase(xmlProdIdentf.getProductAgency().trim()))
																			xmlProdCode = xmlProdIdentf.getProductIdValue();
																	}
																	else if(usgLineItem.getItemzdLineItem()!=null){
																		xmlItemProdIdentf = (ItemProdIdentifier)xmlProdIdentfList.get(n);
																		if(xmlItemProdIdentf != null && xmlItemProdIdentf.getProductAgency() != null && IPixB2BConstants.Buyer.equalsIgnoreCase(xmlItemProdIdentf.getProductAgency().trim()))
																			xmlProdCode = xmlItemProdIdentf.getProductIdValue();
																	}
																}
																	String tableName = "PIX_PO_USAGE";
																	String whereClause = "PO_ID='"+dbPOId+"' AND decode((case when instr(product_code, '-', 1, 1) > 0 then 0 WHEN instr(product_code, '-', 1, 1) = 0 then 1 end), 0,substr(product_code, 4, 5),ltrim(product_code, '0')) = ltrim('"+xmlProdCode+"', '0')";
																	int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
																	if(count<=0){
																		errorId = IPixB2BConstants.ERROR_ID_12;
																		daoErrorDTO.setErrorID(errorId+"");
																		daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_12.concat(" For PO Number = ").concat(xmlPoNo));
																		errorList.add(daoErrorDTO);
																		B2BLogger.info("UsageDAOImpl.storeUsageDetails()- The Product code: "+xmlProdCode+ "is not present in PIX_PO_USAGE table");
																		B2BGlobalData.getGlobalDataObject().addInfoToFileContext(Thread.currentThread().getName(), IPixB2BConstants.PRODUCT_CODE, xmlProdCode);
																	}else{
																		sqlQuery = qry_sel_line_item_no;
																		prepStmt12 = dbCon.prepareStatement(sqlQuery);
																		prepStmt12.clearParameters();	
																		prepStmt12.setString(IPixB2BConstants.ONE,dbPOId);
																		prepStmt12.setString(IPixB2BConstants.TWO,xmlProdCode);
																		qryParams = dbPOId+","+xmlProdCode;
																		resultSet12 = prepStmt12.executeQuery();
																		while(resultSet12.next()){
																			lineItemNo = resultSet12.getString("line_item_no");
																		}
																		DBUtils.close(prepStmt12);
																		DBUtils.close(resultSet12);	
																		B2BLogger.info("UsageDAOImpl.storeUsageDetails() - lineItemNo = "+ lineItemNo );
																		    
																		
																		//Read Usage Quantity
																		if(usgLineItem.getAggrLineItem()!= null){
																			lineQty = usgLineItem.getAggrLineItem().getLineQty();
																			if(lineQty != null){
																				xmlLineQty = lineQty.getUsgQtyValue().getQtyValue();
																			}
																		}else if(usgLineItem.getItemzdLineItem()!=null){
																			itemLineQty = usgLineItem.getItemzdLineItem().getItemLineQty();
																			if(itemLineQty != null){
																				xmlLineQty = itemLineQty.getItemQtyValue().getQtyValue();
																			}
																		}
																		
																		//Read Damaged Quantity
																		if(usgLineItem.getAggrLineItem()!= null){
																			infoQtyList = usgLineItem.getAggrLineItem().getLineInfoQty();   //Read InfoQty from AggregateList
																			if(infoQtyList != null && infoQtyList.size()>0){
																				for(int p=0; p<infoQtyList.size(); p++){
																					lineInfoQty = (LineInfoQty)infoQtyList.get(p);														
																					if(lineInfoQty != null && lineInfoQty.getQtyTypeContext()!= null && IPixB2BConstants.Damaged.equalsIgnoreCase(lineInfoQty.getQtyTypeContext().trim()))
																						xmlInfoQty = lineInfoQty.getUsgQtyValue().getQtyValue();									   
																				}
																			}	
																		}
																		if((infoQtyList == null || infoQtyList.size() == 0) && null!=usgLineItem.getItemzdLineItem()){
																			infoQtyList = usgLineItem.getItemzdLineItem().getItemLineInfoQty();     //Read InfoQty from ItemizedList
																			if(infoQtyList != null && infoQtyList.size()>0){
																				for(int p=0; p<infoQtyList.size(); p++){
																					itemLineInfoQty = (ItemLineInfoQty)infoQtyList.get(p);														
																					if(itemLineInfoQty != null && itemLineInfoQty.getQtyTypeContext()!= null && IPixB2BConstants.Damaged.equalsIgnoreCase(itemLineInfoQty.getQtyTypeContext().trim()))
																						xmlInfoQty = itemLineInfoQty.getItemInfoQtyValue().getQtyValue();									   
																				}
																			}
																		}
																			
																		//Insert into Pix UsageLine table
																		sqlQuery = qry_ins_pix_usage_line;
																		prepStmt13 = dbCon.prepareStatement(sqlQuery);
																		prepStmt13.clearParameters();
																		prepStmt13.setString(IPixB2BConstants.ONE,xmlLineQty);
																		prepStmt13.setString(IPixB2BConstants.TWO,xmlInfoQty);
																		prepStmt13.setString(IPixB2BConstants.THREE,usgLineComments);   
																		prepStmt13.setString(IPixB2BConstants.FOUR,IPixB2BConstants.JavaB2B);
																		prepStmt13.setString(IPixB2BConstants.FIVE,IPixB2BConstants.JavaB2B);
																		prepStmt13.setString(IPixB2BConstants.SIX,usgId);
																		prepStmt13.setString(IPixB2BConstants.SEVEN,usgStatusId);
																		prepStmt13.setString(IPixB2BConstants.EIGHT,dbPOId);
																		prepStmt13.setString(IPixB2BConstants.NINE,lineItemNo);
																		qryParams = usgId+","+xmlUsgNo+","+usgStatusId+","+dbPOId+","+dbPOVersion+","+xmlUsgTransID;
																		recsNum = prepStmt13.executeUpdate();
																		recsCount = recsCount + recsNum;
																		DBUtils.close(prepStmt13);
																		
//																		Handle Damaged Quantity
																		sqlQuery = qry_sel_qty_description;
																		prepStmt14 = dbCon.prepareStatement(sqlQuery);
																		prepStmt14.clearParameters();										
																		resultSet14 = prepStmt14.executeQuery();
																		while(resultSet14.next()){
																			qtyType = resultSet14.getString("description");
																			qtyValue = null;
																			qtyUOM 	= null;
																			uomId 	= null;
																			if(qtyType != null){														
																				if(infoQtyList != null && infoQtyList.size()>0){
																					for(int q=0; q<infoQtyList.size(); q++){
																						if(usgLineItem.getAggrLineItem()!= null){
																							lineInfoQty = (LineInfoQty)infoQtyList.get(q);
																							if(lineInfoQty != null && qtyType.equalsIgnoreCase(lineInfoQty.getQtyTypeContext().trim())){
																								qtyValue = lineInfoQty.getUsgQtyValue().getQtyValue();
																								if(qtyValue != null && Integer.valueOf(qtyValue.trim()).intValue()!= 0 )
																									qtyUOM = lineInfoQty.getUsgQtyValue().getUOM();
																							}
																						}else if(null!=usgLineItem.getItemzdLineItem()){
																							itemLineInfoQty = (ItemLineInfoQty)infoQtyList.get(q);
																							if(itemLineInfoQty != null && qtyType.equalsIgnoreCase(itemLineInfoQty.getQtyTypeContext().trim())){
																								qtyValue = itemLineInfoQty.getItemInfoQtyValue().getQtyValue();
																								if(qtyValue != null && Integer.valueOf(qtyValue.trim()).intValue()!= 0 )
																									qtyUOM = itemLineInfoQty.getItemInfoQtyValue().getUOM();
																							}
																						}
																					}
																				}																	
																				
																				sqlQuery = qry_sel_uom_id;
																				prepStmt15 = dbCon.prepareStatement(sqlQuery);
																				prepStmt15.clearParameters();	
																				prepStmt15.setString(IPixB2BConstants.ONE,qtyUOM);
																				qryParams = qtyUOM;
																				resultSet15 = prepStmt15.executeQuery();
																				while(resultSet15.next()){
																					uomId = resultSet15.getString("uom_id");
																				}
																				DBUtils.close(prepStmt15);
																				DBUtils.close(resultSet15);	
																				
																				sqlQuery = qry_ins_pix_usage_other_quantities;
																				prepStmt16 = dbCon.prepareStatement(sqlQuery);
																				prepStmt16.clearParameters();
																				prepStmt16.setString(IPixB2BConstants.ONE,usgId);
																				prepStmt16.setString(IPixB2BConstants.TWO,xmlUsgTransID);
																				prepStmt16.setString(IPixB2BConstants.THREE,xmlUsgNo);        //Document No
																				prepStmt16.setString(IPixB2BConstants.FOUR,xmlIssueDate);     //Document Date
																				prepStmt16.setString(IPixB2BConstants.FIVE,dbPOId);
																				prepStmt16.setString(IPixB2BConstants.SIX,dbPOVersion);
																				prepStmt16.setString(IPixB2BConstants.SEVEN,lineItemNo);       //UsgLine No
																				prepStmt16.setString(IPixB2BConstants.EIGHT,qtyType);		 //UsgType
																				prepStmt16.setString(IPixB2BConstants.NINE,qtyValue);       //INFOUOMVALUE -usgInfoQty
																				prepStmt16.setString(IPixB2BConstants.TEN,uomId);            //INFOUOMVALUE
																				qryParams = usgId+","+xmlUsgTransID+","+xmlUsgNo+","+xmlIssueDate+","+dbPOId+","+dbPOVersion+","+lineItemNo
																				+","+qtyType+","+qtyValue+","+uomId;
																				recsNum = prepStmt16.executeUpdate();
																				recsCount = recsCount + recsNum;
																				DBUtils.close(prepStmt16);
																			}
																		}
																		DBUtils.close(prepStmt14);
																		DBUtils.close(resultSet14);	
																	}
//																}
															}
														}   //aggrlineItem or itemLineItem endif
													}
												}		
											}
										}
									}else {
										//ERROR LOG INSERT
										B2BLogger.error("Transaction already processed error");							
									}
									DBUtils.close(prepStmt2);
									DBUtils.close(resultSet2);	
								}
							}
//							insert in transaction log table
							if(recsCount > 0 && sbPOId != null && sbPOId.length()>0 && sbUsgId != null && sbUsgId.length()>0){
								strPOId = sbPOId.substring(0, sbPOId.length()-1);
								strUsgId = sbUsgId.substring(0, sbUsgId.length()-1);
								if(null!=errorList && errorList.size()>0){
									transStatusDTO.setStatusREAD("RE");
									transStatusDTO.setCompletePath(inDirArchiveXmlInvalid);
									transRefId1	= B2BGlobalData.getGlobalDataObject().getInfoFromContext(Thread.currentThread().getName(), IPixB2BConstants.PO_NO);
									if(null!=transRefId1 && !"".equals(transRefId1)){
										transRefLabel1	= IPixB2BConstants.PO_NO;	
									}
									transRefId2	= B2BGlobalData.getGlobalDataObject().getInfoFromContext(Thread.currentThread().getName(), IPixB2BConstants.PRODUCT_CODE);
									if(null!=transRefId2 && !"".equals(transRefId2)){
										transRefLabel2	= IPixB2BConstants.PRODUCT_CODE;							
									}
									dbTransIdNext = updateInboundTransStatus(dbCon,transStatusDTO,senderSAN,receiverSAN, 
											transRefId1,transRefId2,transRefLabel1,transRefLabel2,xmlUsgNo,xmlIssueDate, xmlUsgTransID);
								}else{
									dbTransIdNext = updateInboundTransStatus(dbCon,transStatusDTO,senderSAN,receiverSAN, 
											strPOId,strUsgId,IPixB2BConstants.PO_ID,IPixB2BConstants.USAGE_ID,xmlUsgNo,xmlIssueDate, xmlUsgTransID);
								}
								if(dbTransIdNext>0){
									String transID = Long.toString(dbTransIdNext);
									transStatusDTO.setTransID(transID);
								}
							}	
//									}else {
//										//ERROR LOG INSERT
//										B2BLogger.error("Transaction already processed error");							
//									}
							dbCon.commit();
//								}
//							}

						}else{
							B2BLogger.info("UsageDAOImpl.storeUsageDetails() - xmlUsgTransId is null or blank");
						}
						/**
						if(recsCount > 0 && dbPOId != null && dbPOId.length()>0 && usgId != null && usgId.length()>0){
							dbPOId = dbPOId.substring(0, dbPOId.length()-1);
							usgId = usgId.substring(0, usgId.length()-1);
							dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO,senderSAN,receiverSAN, 
									dbPOId,usgId,PO_ID,USAGE_ID);
						}*/	
					}else{
						B2BLogger.info("UsageDAOImpl.storeUsageDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("UsageDAOImpl.storeUsageDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("UsageDAOImpl.storeUsageDetails() - pneDTO is null");
			}
			
			B2BLogger.info("UsageDAOImpl.storeUsageDetails() - recsCount = "+recsCount);
			if(dbTransIdNext > 0)
				usageInsStatus = IPixB2BConstants.flag_Y;			
			
			B2BLogger.debug("******* UsageDAOImpl.storeUsageDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			usageInsStatus = StringUtils.getStackTrace(e);
			if(usageInsStatus != null && usageInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				usageInsStatus = usageInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			usageInsStatus = StringUtils.getStackTrace(e);
			if(usageInsStatus != null && usageInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				usageInsStatus = usageInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);	
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet2);	
			DBUtils.close(prepStmt3);
			DBUtils.close(resultSet3);	
			DBUtils.close(prepStmt4);
			DBUtils.close(resultSet4);	
			DBUtils.close(prepStmt5);
			DBUtils.close(prepStmt6);
			DBUtils.close(resultSet6);	
			DBUtils.close(prepStmt7);
			DBUtils.close(prepStmt8);
			DBUtils.close(prepStmt9);
			DBUtils.close(resultSet9);	
			DBUtils.close(prepStmt10);
			DBUtils.close(prepStmt11);
			DBUtils.close(prepStmt12);
			DBUtils.close(resultSet12);	
			DBUtils.close(prepStmt13);
			DBUtils.close(prepStmt14);
			DBUtils.close(resultSet14);	
			DBUtils.close(prepStmt15);
			DBUtils.close(resultSet15);
			DBUtils.close(prepStmt16);
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			b2bHelper		= null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;		
		}
		return usageInsStatus;
	}
}