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
 * Title		: 	InventoryChangeDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam  30 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICBookManufacturing;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICLineReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICOrganisationUnit;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICProduct;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICReason;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.LPContact;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.LPNameAddress;
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
 * InventoryChangeDAOImpl is a class to communicate with the PIX database
 * and process the B2B InventoryChange transaction data.
 * 
 * @author Abhilasha Nigam
 */
public class InventoryChangeDAOImpl extends InboundTransStatusDAOImpl implements IInventoryChangeDAO{
	
	private static final String qry_sel_paper_master = "SELECT count(1) records_count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PMS_PAPER_CLASSIF_MV"
    	+" WHERE material_number = lpad(?,10,'0')";
	
	private static final String qry_sel_pix_inventory_id_1 ="SELECT inventory_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVENTORY_MASTER"
		+" WHERE san = ? AND product_code = lpad(?,10,'0')";   //for Product Code = lpad(?,10,'0')
	
	private static final String qry_sel_pix_inventory_id_2 ="SELECT DECODE(COUNT(1), 0, 0, MAX(INVENTORY_ID)) inventory_id"
        +" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVENTORY_MASTER"
        +" WHERE NVL(ISBN10, 99) = nvl(?, 99)"
        +" AND NVL(ISBN13, 99) = nvl(?, 99)"
        +" AND SAN = ?"
        +" AND PRODUCT_CODE = ?";
	
	private static final String qry_sel_uom_id = "SELECT uom_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_uom_code"
		+" WHERE uom = ?";
	
	private static final String qry_sel_pix_inventory_id_nextval = "SELECT SEQ_PIX_INVENTORY_ID.nextval inventory_id_next FROM DUAL";
	
	private static final String qry_ins_pix_inventory_master = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVENTORY_MASTER"
	    +" (INVENTORY_ID, LINE_ITEM_NO, SAN, ISBN10, ISBN13,"
	    +" PRODUCT_CODE, COMPONENT_FLAG, UOM_ID, CREATION_DATE_TIME, MOD_DATE_TIME)" 
	    +" VALUES(?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE)" ;
	
	private static final String qry_sel_inventory_change_id = "SELECT DISTINCT INVENTORY_CHANGE_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_change"
    	+" WHERE inventory_id = ?";
	
	private static final String qry_sel_pix_inventory_chng_id_nextval = "SELECT SEQ_PIX_INVENTORY_CHANGE_ID.nextval"
        +" inventory_id_next FROM DUAL";
	  
	private static final String qry_sel_version_no = "SELECT max(INVENTORY_VERSION) inventory_version"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_change_summary"
		+" WHERE inventory_change_id = ?";
	
	private static final String qry_ins_pix_inventory_change = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_change"
	    +" (inventory_change_id, inventory_version, print_no, transaction_history_no, created_by,"
	    +" modified_by, creation_date_time, mod_date_time, inventory_id)"
	    +" VALUES(?, ?, '0', ?, ?,"
	    +" ?, SYSDATE, SYSDATE, ?)" ;
	
	private static final String qry_ins_pix_inventory_change_line = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_change_line"
		+" (inventory_change_id, inventory_version, line_item_no, stock_change, uom_id, reason_desc)"
		+" VALUES(?, ?, ?, ?, ?, ?)" ;
	
	private static final String qry_ins_pix_inventory_change_party = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_change_party"
		+" (inventory_change_id, inventory_version, party_line_no, san,"
		+" name_1, name_2, name_3, org_unit_code, org_unit_name,"
		+" address_1, address_2, address_3, address_4, city, state, postal_code, party_type)"
	    +" VALUES (?, ?, '1', ?,"
	    +" ?, ?, ?, ?, ?,"
	    +" ?, ?, ?, ?, ?, ?, ?, ?)"; 
	
	private static final String qry_ins_pix_inventory_chg_party_cont = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_chg_party_cont"
		+" (inventory_change_id, inventory_version, party_line_no, contact_no, contact_type, contact_first_name, mobile)"
		+" VALUES (?, ?, '1', '1', ?, ?, ?)";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dao.IInventoryChangeDAO#storeInventoryChangeDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String storeInventoryChangeDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid){
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
		ResultSet resultSet5		= null;
		PreparedStatement prepStmt6	= null;
		PreparedStatement prepStmt7	= null;
		ResultSet resultSet7		= null;
		PreparedStatement prepStmt8	= null;
		ResultSet resultSet8		= null;
		PreparedStatement prepStmt9	= null;
		ResultSet resultSet9		= null;
		PreparedStatement prepStmt10= null;
		PreparedStatement prepStmt11= null;
		PreparedStatement prepStmt12= null;
		PreparedStatement prepStmt13= null;
		
		ArrayList transmissionInfoList = null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		B2BHelper b2bHelper			= null;
		ICHeader xmlICHeader 		= null;
		String xmlPartyIdenfValue_SAN= null;
		LPNameAddress xmlNameAddress= null;
		LPContact xmlContactInfo 	= null;
		String xmlReasonDesc		= null;
		ArrayList xmlIcRefList 		= null;
		ICReference xmlICRef 		= null;
		String xmlTransID 			= null;
		String xmlISBN10 			= null;
		String xmlISBN13 			= null;
		ArrayList xmlIcLineItemList	= null;
		ICLineItem xmlICLineItem 	= null;
		ArrayList icLineRefList 		= null;
		ICLineReference icLineReference = null;
		String uom					= null;
		String xmlLineItemNo 		= null;
		String xmlLineQtyValue 		= null;
		ArrayList xmlProdIdentfList = null;
		ICProductIdentifier xmlProdIdentf = null;
		String xmlProductCode 		= null;
		String xmlBookClassifType  	= null;
		ICProduct icProduct			= null;
		ICBookManufacturing icBookManu = null;
		boolean insertFlag			= false;
		String componentFlag 		= null;
		String dbInventoryID 		= null;
		String dbUOMId 				= null;
		String dbInventoryChangeId	= null;
		StringBuffer sbInventoryChageId = null;
		String strInventoryChageId 	= null;
		String dbInventoryVersion 	= null;
		int inventoryVersionNo 		= 0;
		StringBuffer sbInventoryVersion = null;
		String strInventoryVersion	= null;
		long dbTransIdNext 			= -1;
		String icInsStatus 			= IPixB2BConstants.flag_N;
		String icNumber 			= null;
		String icIssueDate 			= null;
		ICOrganisationUnit orgUnit 	= null;
		ICReason icReason			= null;
		String orgUnitName			= null;
		String orgUnitCode			= null;
		String contactName 			= null;
		String mobPhone 			= null;
		String contactType 			= null;
		int errorId					= -1 ;
		ErrorDTO daoErrorDTO   	 	= null;
		
		try {
			B2BLogger.debug("****** InventoryChangeDAOImpl.storeInventoryChangeDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_INC);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						xmlICHeader = pneDTO.getPayload().getBusinessDocument().getIcDTO().getIcHeader();	
						if(xmlICHeader != null){
							icNumber = xmlICHeader.getInvChangeNumber(); 
							if(xmlICHeader.getIcdate() != null){
								icIssueDate = xmlICHeader.getIcdate().getDate().getMonth()
									+"/"+xmlICHeader.getIcdate().getDate().getDay()
									+"/"+xmlICHeader.getIcdate().getDate().getYear();
							}	
							//xmlPartyIdenfValue_SAN = xmlICHeader.getLocationParty().getPartyIdentifier().getPartyIdentifierValue();
							xmlNameAddress = xmlICHeader.getLocationParty().getNameAddress();
							orgUnit = xmlNameAddress.getOrgUnit();
							if(null!=orgUnit){
								orgUnitName = orgUnit.getOrgUnitName();
								orgUnitCode	= orgUnit.getOrgUnitCode();
							}
							xmlContactInfo = xmlICHeader.getLocationParty().getContactInfo();
							if(null!=xmlContactInfo){
								contactName = xmlContactInfo.getContactName();
								mobPhone = xmlContactInfo.getMobile();
								contactType = xmlContactInfo.getContactType();
							}
							//xmlReasonDesc  = xmlICHeader.getIcReason().getReasonDescription();
							icReason = xmlICHeader.getIcReason();
							if(null!=icReason){
								xmlReasonDesc = icReason.getReasonDescription();
							}
							
							xmlIcRefList = xmlICHeader.getIcRef();				
							if(xmlIcRefList != null && xmlIcRefList.size()>0){
								for(int i= 0; i<xmlIcRefList.size(); i++){
									xmlICRef = (ICReference) xmlIcRefList.get(i);
									if(xmlICRef != null && xmlICRef.getIcReferenceType()!= null ){
										if(IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlICRef.getIcReferenceType().trim()))
											xmlTransID = xmlICRef.getIcReferenceValue();								
										//else if(ISBN10.equalsIgnoreCase(xmlICRef.getIcReferenceType().trim()))
											//xmlISBN10 = xmlICRef.getIcReferenceValue();
										//else if(ISBN13.equalsIgnoreCase(xmlICRef.getIcReferenceType().trim()))
											//xmlISBN13 = xmlICRef.getIcReferenceValue();
									}						
								} //end for loop - xmlIcRefList
								B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlTransID = "+xmlTransID+" , xmlISBN10 = "+xmlISBN10+" , xmlISBN13 = "+xmlISBN13);
								
								if(xmlTransID != null && !"".equals(xmlTransID.trim())){
									dbCon.setAutoCommit(false);
									
									xmlIcLineItemList = pneDTO.getPayload().getBusinessDocument().getIcDTO().getIcLineItem();
									if(xmlIcLineItemList != null && xmlIcLineItemList.size() > 0){
										b2bHelper = new B2BHelper();
										//xmlPartyIdenfValue_SAN = b2bHelper.addDashesInSAN(xmlPartyIdenfValue_SAN);
										
										sbInventoryChageId = new StringBuffer("");
										sbInventoryVersion = new StringBuffer("");							
										
										for(int i= 0; i<xmlIcLineItemList.size(); i++){
											xmlICLineItem = (ICLineItem) xmlIcLineItemList.get(i);
											if(xmlICLineItem != null){
												xmlProductCode 		= null;
												dbUOMId				= null;
												dbInventoryID 		= null;
												componentFlag 		= null;
												dbInventoryChangeId = null;
												dbInventoryVersion	= null;
												insertFlag			= false;
												
												xmlLineItemNo = xmlICLineItem.getIcLineItemNumber();							
												xmlLineQtyValue = xmlICLineItem.getIcQty().getIcQtyValue().getQtyValue();
												icProduct = xmlICLineItem.getIcProduct();
												icLineRefList = xmlICLineItem.getIcLineRef();
												uom = xmlICLineItem.getIcQty().getIcQtyValue().getUOM().toUpperCase();
												
												if(null!=icProduct){
													xmlProdIdentfList = icProduct.getProdIdentifier();
													if(xmlProdIdentfList != null && xmlProdIdentfList.size()>0){
														for(int j= 0; j<xmlProdIdentfList.size(); j++){
															xmlProdIdentf = (ICProductIdentifier)xmlProdIdentfList.get(j);
															if(xmlProdIdentf != null && xmlProdIdentf.getProductAgency() != null && IPixB2BConstants.Buyer.equalsIgnoreCase(xmlProdIdentf.getProductAgency().trim()))
																xmlProductCode = xmlProdIdentf.getProductIdValue();									   
														}
													}
												}
												if(null==xmlProductCode){
													daoErrorDTO = new ErrorDTO();
													errorId = IPixB2BConstants.ERROR_ID_121;
													daoErrorDTO.setErrorID(errorId+"");
													String errorDesp =  b2bHelper.getErrorDesp(errorId);
													daoErrorDTO.setErrorDescription(errorDesp.concat("For Line Item Number::").concat(xmlLineItemNo));
													errorList.add(daoErrorDTO);
													B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails()-The Part Number is null");
													continue;
												}
												//if product code is null skip the line
												if(icLineRefList != null && icLineRefList.size()>0){
													for(int j= 0; j<icLineRefList.size(); j++){
														icLineReference = (ICLineReference)icLineRefList.get(j);
														if(icLineReference != null && icLineReference.getIcLineRefType()!= null ){
															if(IPixB2BConstants.ISBN10.equalsIgnoreCase(icLineReference.getIcLineRefType().trim()))
																xmlISBN10 = icLineReference.getIcLineRefValue();
															else if(IPixB2BConstants.ISBN13.equalsIgnoreCase(icLineReference.getIcLineRefType().trim()))
																xmlISBN13 = icLineReference.getIcLineRefValue();
														}
													}
												}
												if(null!=icProduct){
													icBookManu = icProduct.getIcBook();
													if(icBookManu != null){
														xmlBookClassifType = icBookManu.getBookClassification().getBookClassificationType();
														if(xmlBookClassifType != null && IPixB2BConstants.Material.equalsIgnoreCase(xmlBookClassifType.trim())){
															
															componentFlag = IPixB2BConstants.flag_R; //Raw Material											
															sqlQuery = qry_sel_paper_master;														
															prepStmt1 = dbCon.prepareStatement(sqlQuery);
															prepStmt1.clearParameters();
															prepStmt1.setString(IPixB2BConstants.ONE, xmlProductCode);
															qryParams = xmlProductCode;										
															resultSet1 = prepStmt1.executeQuery();
															if(resultSet1.next() && resultSet1.getLong("records_count") > 0){ //Paper Master Exist
																insertFlag = true;
																sqlQuery = qry_sel_pix_inventory_id_1;
																prepStmt2 = dbCon.prepareStatement(sqlQuery);
																prepStmt2.clearParameters();
																prepStmt2.setString(IPixB2BConstants.ONE, senderSAN );
																prepStmt2.setString(IPixB2BConstants.TWO, xmlProductCode);    //lpad in query
																qryParams = senderSAN+","+xmlProductCode;
																resultSet2 = prepStmt2.executeQuery();
																while(resultSet2.next()){
																	dbInventoryID = resultSet2.getString("inventory_id");
																}
																DBUtils.close(prepStmt2);
																DBUtils.close(resultSet2);	
															}else{
																insertFlag = false;
																//ERROR LOG INSERT
																daoErrorDTO = new ErrorDTO();
																errorId = IPixB2BConstants.ERROR_ID_118;
																daoErrorDTO.setErrorID(errorId+"");
																String errorDesp =  b2bHelper.getErrorDesp(errorId);
																daoErrorDTO.setErrorDescription(errorDesp.concat(":").concat(xmlProductCode).concat("For Line Item Number::").concat(xmlLineItemNo));
																errorList.add(daoErrorDTO);
																B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails()-The Part Number is invalid as it is not present in Paper Master table::PIX_PMS_PAPER_CLASSIF_MV for senderSAN::"+senderSAN);
																continue;	
															}
															DBUtils.close(prepStmt1);
															DBUtils.close(resultSet1);	
														}else{
															insertFlag = true;
															componentFlag = IPixB2BConstants.flag_M; //Manufactured Component - Non Material
														}
													}else{
														insertFlag = true;
														componentFlag = IPixB2BConstants.flag_M; //Manufactured Component - Non Material
													}
												}
												
												if(insertFlag){
													if(IPixB2BConstants.flag_M.equals(componentFlag)){
														sqlQuery = qry_sel_pix_inventory_id_2;
														prepStmt3 = dbCon.prepareStatement(sqlQuery);
														prepStmt3.clearParameters();
														prepStmt3.setString(IPixB2BConstants.ONE, xmlISBN10);
														prepStmt3.setString(IPixB2BConstants.TWO, xmlISBN13);
														prepStmt3.setString(IPixB2BConstants.THREE, senderSAN );
														prepStmt3.setString(IPixB2BConstants.FOUR, xmlProductCode);
														qryParams = xmlISBN10+","+xmlISBN13+","+senderSAN+","+xmlProductCode;
														resultSet3 = prepStmt3.executeQuery();
														while(resultSet3.next()){
															dbInventoryID = resultSet3.getString("inventory_id");
														}
														DBUtils.close(prepStmt3);
														DBUtils.close(resultSet3);	
													}
													
													sqlQuery = qry_sel_uom_id;
													prepStmt4 = dbCon.prepareStatement(sqlQuery);
													prepStmt4.clearParameters();
													prepStmt4.setString(IPixB2BConstants.ONE, uom);
													qryParams = uom;
													resultSet4 = prepStmt4.executeQuery();
													while(resultSet4.next()){
														dbUOMId = resultSet4.getString("uom_id");
													}
													DBUtils.close(prepStmt4);
													DBUtils.close(resultSet4);	
													
													if(dbInventoryID == null || "".equals(dbInventoryID.trim()) || Integer.valueOf(dbInventoryID.trim()).intValue() <= 0){				        	
														sqlQuery = qry_sel_pix_inventory_id_nextval;
														prepStmt5 = dbCon.prepareStatement(sqlQuery);
														prepStmt5.clearParameters();						    
														resultSet5 = prepStmt5.executeQuery();			
														while(resultSet5.next()){
															dbInventoryID = resultSet5.getString("inventory_id_next");
														}
														DBUtils.close(prepStmt5);
														DBUtils.close(resultSet5);	
														
														sqlQuery = qry_ins_pix_inventory_master;
														prepStmt6 = dbCon.prepareStatement(sqlQuery);
														prepStmt6.clearParameters();
														prepStmt6.setString(IPixB2BConstants.ONE, dbInventoryID);
														prepStmt6.setString(IPixB2BConstants.TWO, xmlLineItemNo);
														prepStmt6.setString(IPixB2BConstants.THREE, senderSAN);
														prepStmt6.setString(IPixB2BConstants.FOUR, xmlISBN10);
														prepStmt6.setString(IPixB2BConstants.FIVE, xmlISBN13);
														prepStmt6.setString(IPixB2BConstants.SIX, componentFlag.equals(IPixB2BConstants.flag_R)?StringUtils.leftPad(xmlProductCode,10,"0"):xmlProductCode);  //for Paper/R,Product Code = lpad(?,10,'0'); for component- Product code as it is
														prepStmt6.setString(IPixB2BConstants.SEVEN, componentFlag);
														prepStmt6.setString(IPixB2BConstants.EIGHT, dbUOMId);
														qryParams = dbInventoryID+","+xmlLineItemNo+","+senderSAN+","+xmlISBN10+","+xmlISBN13+","+xmlProductCode+","+componentFlag+","+dbUOMId;
														recsNum = prepStmt6.executeUpdate();
														recsCount = recsCount + recsNum;
														
														DBUtils.close(prepStmt6);
													}
													B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - dbInventoryID = "+dbInventoryID);
													
													sqlQuery = qry_sel_inventory_change_id ;
													prepStmt7 = dbCon.prepareStatement(sqlQuery);
													prepStmt7.clearParameters();
													prepStmt7.setString(IPixB2BConstants.ONE, dbInventoryID);
													qryParams = dbInventoryID;
													resultSet7 = prepStmt7.executeQuery();
													while(resultSet7.next()){
														dbInventoryChangeId = resultSet7.getString("INVENTORY_CHANGE_ID");
													}
													DBUtils.close(prepStmt7);
													DBUtils.close(resultSet7);	
													
													if(dbInventoryChangeId == null || "".equals(dbInventoryChangeId.trim()) || Integer.valueOf(dbInventoryChangeId.trim()).intValue() <= 0){				        	
														sqlQuery = qry_sel_pix_inventory_chng_id_nextval;
														prepStmt8 = dbCon.prepareStatement(sqlQuery);
														prepStmt8.clearParameters();						    
														resultSet8 = prepStmt8.executeQuery();			
														while(resultSet8.next()){
															dbInventoryChangeId = resultSet8.getString("inventory_id_next");
														}
														DBUtils.close(prepStmt8);
														DBUtils.close(resultSet8);	
													}
													B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - dbInventoryChangeId = "+dbInventoryChangeId);
													sbInventoryChageId.append(dbInventoryChangeId+IPixB2BConstants.COMMA);
													
													sqlQuery = qry_sel_version_no;
													prepStmt9 = dbCon.prepareStatement(sqlQuery);
													prepStmt9.clearParameters();
													prepStmt9.setString(IPixB2BConstants.ONE, dbInventoryChangeId);
													qryParams = dbInventoryChangeId;
													resultSet9 = prepStmt9.executeQuery();
													while(resultSet9.next()){
														dbInventoryVersion = resultSet9.getString("inventory_version");
													}
													DBUtils.close(prepStmt9);
													DBUtils.close(resultSet9);	
													
													if(dbInventoryVersion != null && !"".equals(dbInventoryVersion.trim()))
														inventoryVersionNo = Integer.valueOf(dbInventoryVersion.trim()).intValue() + 1;
													else
														inventoryVersionNo = 1 ;
													B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - inventoryVersionNo = "+inventoryVersionNo);
													sbInventoryVersion.append(inventoryVersionNo+IPixB2BConstants.COMMA);
													
													sqlQuery = qry_ins_pix_inventory_change ;
													prepStmt10 = dbCon.prepareStatement(sqlQuery);
													prepStmt10.clearParameters();
													prepStmt10.setString(IPixB2BConstants.ONE, dbInventoryChangeId);
													prepStmt10.setInt(IPixB2BConstants.TWO, inventoryVersionNo);						
													prepStmt10.setString(IPixB2BConstants.THREE, xmlTransID);
													prepStmt10.setString(IPixB2BConstants.FOUR, IPixB2BConstants.JavaB2B);
													prepStmt10.setString(IPixB2BConstants.FIVE, IPixB2BConstants.JavaB2B);	
													prepStmt10.setString(IPixB2BConstants.SIX, dbInventoryID);
													qryParams = dbInventoryChangeId+","+inventoryVersionNo+","+xmlTransID+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B+","+dbInventoryID;
													recsNum = prepStmt10.executeUpdate();
													recsCount = recsCount + recsNum;
													DBUtils.close(prepStmt10);
													
													sqlQuery = qry_ins_pix_inventory_change_line;
													prepStmt11 = dbCon.prepareStatement(sqlQuery);
													prepStmt11.clearParameters();
													prepStmt11.setString(IPixB2BConstants.ONE, dbInventoryChangeId);
													prepStmt11.setInt(IPixB2BConstants.TWO, inventoryVersionNo);
													prepStmt11.setString(IPixB2BConstants.THREE, xmlLineItemNo);	
													prepStmt11.setString(IPixB2BConstants.FOUR, xmlLineQtyValue);
													prepStmt11.setString(IPixB2BConstants.FIVE, dbUOMId);
													prepStmt11.setString(IPixB2BConstants.SIX, xmlReasonDesc);
													qryParams = dbInventoryChangeId+","+inventoryVersionNo+","+xmlLineItemNo+","+xmlLineQtyValue+","+dbUOMId+","+xmlReasonDesc;
													recsNum = prepStmt11.executeUpdate();
													recsCount = recsCount + recsNum;
													DBUtils.close(prepStmt11);
													
													sqlQuery = qry_ins_pix_inventory_change_party;
													prepStmt12 = dbCon.prepareStatement(sqlQuery);
													prepStmt12.clearParameters();
													prepStmt12.setString(IPixB2BConstants.ONE,dbInventoryChangeId);
													prepStmt12.setInt(IPixB2BConstants.TWO,inventoryVersionNo);
													prepStmt12.setString(IPixB2BConstants.THREE, senderSAN);	
													prepStmt12.setString(IPixB2BConstants.FOUR, xmlNameAddress.getName1());	
													prepStmt12.setString(IPixB2BConstants.FIVE, xmlNameAddress.getName2());	
													prepStmt12.setString(IPixB2BConstants.SIX, xmlNameAddress.getName3());	
													//prepStmt12.setString(SEVEN, xmlNameAddress.getOrgUnit().getOrgUnitCode());
													//prepStmt12.setString(EIGHT, xmlNameAddress.getOrgUnit().getOrgUnitName());
													prepStmt12.setString(IPixB2BConstants.SEVEN, orgUnitCode);
													prepStmt12.setString(IPixB2BConstants.EIGHT, orgUnitName);
													prepStmt12.setString(IPixB2BConstants.NINE, xmlNameAddress.getAddress1());	
													prepStmt12.setString(IPixB2BConstants.TEN, xmlNameAddress.getAddress2());	
													prepStmt12.setString(IPixB2BConstants.ELEVEN, xmlNameAddress.getAddress3());	
													prepStmt12.setString(IPixB2BConstants.TWELVE, xmlNameAddress.getAddress4());	
													prepStmt12.setString(IPixB2BConstants.THIRTEEN, xmlNameAddress.getCity());	
													prepStmt12.setString(IPixB2BConstants.FOURTEEN, xmlNameAddress.getStateOrProvince());	
													prepStmt12.setString(IPixB2BConstants.FIFTEEN, xmlNameAddress.getPostalCode());
													prepStmt12.setString(IPixB2BConstants.SIXTEEN, IPixB2BConstants.flag_V);
													/*qryParams = dbInventoryChangeId+","+inventoryVersionNo+","+xmlPartyIdenfValue_SAN
														+","+xmlNameAddress.getName1()+","+xmlNameAddress.getName2()+","+xmlNameAddress.getName3()
														+","+xmlNameAddress.getOrgUnit().getOrgUnitCode()+","+xmlNameAddress.getOrgUnit().getOrgUnitName()
														+","+xmlNameAddress.getAddress1()+","+xmlNameAddress.getAddress2()+","+xmlNameAddress.getAddress3()+","+xmlNameAddress.getAddress4()
														+","+xmlNameAddress.getCity()+","+xmlNameAddress.getStateOrProvince()+","+xmlNameAddress.getPostalCode()+","+flag_V;*/
													qryParams = dbInventoryChangeId+","+inventoryVersionNo+","+senderSAN
													+","+xmlNameAddress.getName1()+","+xmlNameAddress.getName2()+","+xmlNameAddress.getName3()
													+","+orgUnitCode+","+orgUnitName+","+xmlNameAddress.getAddress1()+","+xmlNameAddress.getAddress2()
													+","+xmlNameAddress.getAddress3()+","+xmlNameAddress.getAddress4()+","+xmlNameAddress.getCity()
													+","+xmlNameAddress.getStateOrProvince()+","+xmlNameAddress.getPostalCode()+","+IPixB2BConstants.flag_V;
													recsNum = prepStmt12.executeUpdate();
													recsCount = recsCount + recsNum;
													DBUtils.close(prepStmt12);
													
													sqlQuery = qry_ins_pix_inventory_chg_party_cont;
													prepStmt13 = dbCon.prepareStatement(sqlQuery);
													prepStmt13.clearParameters();
													prepStmt13.setString(IPixB2BConstants.ONE, dbInventoryChangeId);
													prepStmt13.setInt(IPixB2BConstants.TWO, inventoryVersionNo);
													/*prepStmt13.setString(THREE, xmlContactInfo.getContactType());
													prepStmt13.setString(FOUR, xmlContactInfo.getContactName());						
													prepStmt13.setString(FIVE, xmlContactInfo.getMobile());*/
													prepStmt13.setString(IPixB2BConstants.THREE, contactType);
													prepStmt13.setString(IPixB2BConstants.FOUR, contactName);						
													prepStmt13.setString(IPixB2BConstants.FIVE, mobPhone);
													//qryParams = dbInventoryChangeId+","+inventoryVersionNo+","+xmlContactInfo.getContactType()+","+xmlContactInfo.getContactName()+","+xmlContactInfo.getMobile();
													qryParams = dbInventoryChangeId+","+inventoryVersionNo+","+contactType+","+contactName+","+mobPhone;
													recsNum = prepStmt13.executeUpdate();
													recsCount = recsCount + recsNum;
													DBUtils.close(prepStmt13);
													
													dbCon.commit();
												}else{
													B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - insertFlag = "+insertFlag);
												}
											}else{
												B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlICLineItem is null");
											}			
										} //end for loop - xmlIcLineItemList
										
										//insert in transaction log table
										if(recsCount > 0 && sbInventoryChageId != null && sbInventoryChageId.length()>0 && sbInventoryVersion != null && sbInventoryVersion.length()>0){
											strInventoryChageId = sbInventoryChageId.substring(0, sbInventoryChageId.length()-1);
											strInventoryVersion = sbInventoryVersion.substring(0, sbInventoryVersion.length()-1);
											/*dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
													senderSAN, receiverSAN, 
													strInventoryChageId, strInventoryVersion, INVENTORY_CHANGE_ID, INVENTORY_VERSION,icNumber,icIssueDate, xmlTransID);*/
										}							
										if(null!=errorList && errorList.size()>0){
											transStatusDTO.setStatusREAD("RE");
											transStatusDTO.setCompletePath(inDirArchiveXmlInvalid);
											dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
													senderSAN, receiverSAN, 
													null, null, null, null, icNumber,icIssueDate, xmlTransID);
										}else{
											dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
													senderSAN, receiverSAN, 
													strInventoryChageId, strInventoryVersion, IPixB2BConstants.INVENTORY_CHANGE_ID, IPixB2BConstants.INVENTORY_VERSION,icNumber,icIssueDate, xmlTransID);
										}
										if(dbTransIdNext>0){
											String transID = Long.toString(dbTransIdNext);
											transStatusDTO.setTransID(transID);
										}
									}else{
										B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlIcLineItemList is null or blank");
									}
									dbCon.commit();
								}else{
									B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlTransID is null or blank");
								}
							}else{
								B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlIcRefList is null or blank");
							}
						}else{
							B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlICHeader is null");
						}
					}else{
						B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - pneDTO is null");
			}
			
			B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - dbTransIdNext = "+dbTransIdNext);
			if(dbTransIdNext > 0)
				icInsStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** InventoryChangeDAOImpl.storeInventoryChangeDetails() method EXIT *******");
		}catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			icInsStatus = StringUtils.getStackTrace(e);
			if(icInsStatus != null && icInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				icInsStatus = icInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			icInsStatus = StringUtils.getStackTrace(e);
			if(icInsStatus != null && icInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				icInsStatus = icInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
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
			DBUtils.close(resultSet5);
			DBUtils.close(prepStmt6);
			DBUtils.close(prepStmt7);
			DBUtils.close(resultSet7);
			DBUtils.close(prepStmt8);
			DBUtils.close(resultSet8);
			DBUtils.close(prepStmt9);
			DBUtils.close(resultSet9);
			DBUtils.close(prepStmt10);
			DBUtils.close(prepStmt11);
			DBUtils.close(prepStmt12);
			DBUtils.close(prepStmt13);			
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;
			b2bHelper		= null;
			xmlICHeader 	= null;
			xmlPartyIdenfValue_SAN= null;
			xmlNameAddress	= null;
			xmlContactInfo 	= null;
			xmlReasonDesc 	= null;
			xmlIcRefList 	= null;
			xmlICRef 		= null;
			xmlTransID 		= null;
			xmlISBN10 		= null;
			xmlISBN13 		= null;
			xmlIcLineItemList = null;
			xmlICLineItem 	= null;
			icLineRefList 		= null;
			icLineReference = null;
			uom				= null;
			xmlLineItemNo 	= null;
			xmlLineQtyValue = null;
			xmlProdIdentfList = null;
			xmlProdIdentf 	= null;
			xmlProductCode 	= null;
			icProduct		= null;
			icBookManu 		= null;
			xmlBookClassifType= null;
			componentFlag 	= null;
			dbInventoryID 	= null;
			dbUOMId 		= null;
			dbInventoryChangeId	= null;
			sbInventoryChageId  = null;
			strInventoryChageId	= null;
			dbInventoryVersion 	= null;
			sbInventoryVersion  = null;
			strInventoryVersion	= null;
		}		
		return icInsStatus;
	}
}