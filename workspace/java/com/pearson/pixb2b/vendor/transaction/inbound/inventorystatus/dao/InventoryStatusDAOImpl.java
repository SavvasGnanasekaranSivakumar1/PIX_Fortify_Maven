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
 * Title		: 	InventoryStatusDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam  30 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISBookManufacturing;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISLineReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISOrganisationUnit;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISProduct;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.LocationParty;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.LocationPartyContact;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.LocationPartyNameAddress;
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
 * InventoryStatusDAOImpl is a class to communicate with the PIX database
 * and process the B2B InventoryStatus transaction data.
 * 
 * @author Abhilasha Nigam
 */
public class InventoryStatusDAOImpl extends InboundTransStatusDAOImpl implements IInventoryStatusDAO{
	
	private static final String qry_sel_paper_master = "SELECT count(1) records_count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PMS_PAPER_CLASSIF_MV"
		+" WHERE material_number = lpad(?,10,'0')";
	
	private static final String qry_sel_pix_inventory_id_1 = "SELECT inventory_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVENTORY_MASTER"
		+" WHERE san = ? AND product_code = lpad(?,10,'0') ";  //lpad added here
	
	private static final String qry_sel_pix_inventory_id_2 = "SELECT DECODE(COUNT(1),0,0,MAX(INVENTORY_ID)) inventory_id"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVENTORY_MASTER"
		+" WHERE NVL(ISBN10,99) = nvl(?,99)"
		+" AND NVL(ISBN13,99) = nvl(?,99)"
		+" AND SAN = ?"
		+" AND PRODUCT_CODE = ?";
	
	private static final String qry_sel_uom_id = "SELECT uom_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_uom_code"
		+" WHERE uom = ?";
	
	private static final String qry_sel_pix_inventory_id_nextval = "SELECT SEQ_PIX_INVENTORY_ID.nextval inventory_id_next FROM DUAL";
	
	private static final String qry_ins_pix_inventory_master = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVENTORY_MASTER"
		+" (INVENTORY_ID, LINE_ITEM_NO, SAN, ISBN10, ISBN13,"
		+" PRODUCT_CODE, COMPONENT_FLAG, UOM_ID, CREATION_DATE_TIME, MOD_DATE_TIME)" 
		+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE)" ;  
	
	private static final String qry_sel_version_no = "SELECT max(INVENTORY_VERSION) inventory_version"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_summary"
		+" WHERE inventory_id = ? ";
	
	private static final String qry_ins_pix_inventory = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory"
		+" (inventory_id, inventory_version, print_no, transaction_history_no, created_by,"
		+" modified_by, creation_date_time, mod_date_time)"
		+" VALUES (?, ?, '0', ?, ?, ?, SYSDATE, SYSDATE)";
	
	private static final String qry_ins_pix_inventory_line = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_line"
		+" (inventory_id, inventory_version, line_item_no, stock_in_hand, uom_id)"
		+" VALUES (?, ?, ?, ?, ?)" ;
	
	private static final String qry_ins_pix_inventory_party = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_party"
		+" (inventory_id, inventory_version, party_line_no, san,"
		+" name_1, name_2, name_3, org_unit_code, org_unit_name,"
		+" address_1, address_2, address_3, address_4, city, state, postal_code, party_type)"
		+" VALUES (?, ?, '1', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";                     
	
	private static final String qry_ins_pix_inventory_party_contact = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_inventory_party_contact"
		+" (inventory_id, inventory_version, party_line_no, contact_no, contact_type, contact_first_name, mobile)"
		+" VALUES (?, ?, '1', '1', ?, ?, ?)";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dao.IInventoryStatusDAO#storeInventoryStatusDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO)
	 */
	public String storeInventoryStatusDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid){
		Connection dbCon			= null;
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
		PreparedStatement prepStmt9	= null;
		PreparedStatement prepStmt10= null;
		PreparedStatement prepStmt11= null;
		
		ArrayList transmissionInfoList = null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		B2BHelper b2bHelper			= null;
		ISHeader xmlISHeader		= null;
		//String xmlPartyIdenfValue_SAN = null;
		LocationPartyNameAddress xmlNameAddress = null;
		LocationPartyContact xmlContactInfo = null;
		ArrayList xmlIsRefList 		= null;
		ISReference xmlISRef 		= null;
		String xmlTransID 			= null;
		String xmlISBN10 			= null;
		String xmlISBN13 			= null;
		ArrayList xmlIsLineItemList	= null;
		ISLineItem xmlISLineItem 	= null;
		String xmlLineItemNo 		= null;
		String xmlLineQtyValue 		= null;
		ArrayList xmlProdIdentfList = null;
		ISProductIdentifier xmlProdIdentf = null;
		String xmlProductCode 		= null;
		ISProduct isProduct			= null;
		ArrayList isLineRefList		= null;
		ISLineReference isLineReference	= null;
		String uom					= null;
		ISBookManufacturing isBookManu = null;
		boolean insertFlag			= false;
		String xmlBookClassifType 	= null;
		String dbInventoryID 		= null;		
		StringBuffer sbInventoryId 	= null;
		String strInventoryId 		= null;		
		String dbUOMId 				= null;
		String dbInventoryVersion 	= null;
		int inventoryVersionNo 		= 0;
		StringBuffer sbInventoryVersion = null;
		String strInventoryVersion	= null;
		String componentFlag 		= null;
		long dbTransIdNext 			= -1;
		String invStatus 			= IPixB2BConstants.flag_N;
		String isNumber 			= null;
		String isIssueDate 			= null;
		LocationParty locParty 		= null;
		ISOrganisationUnit orgUnit 	= null;
		String orgUnitName			= null;
		String orgUnitCode			= null;
		String contactName 			= null;
		String mobPhone 			= null;
		String contactType 			= null;
		int errorId					= -1 ;
		ErrorDTO daoErrorDTO   	 	= null;
		
		try {
			B2BLogger.debug("****** InventoryStatusDAOImpl.storeInventoryStatusDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}			
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_INV);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						xmlISHeader = pneDTO.getPayload().getBusinessDocument().getIsDTO().getIsHeader();
						if(xmlISHeader != null){
							isNumber = xmlISHeader.getInventoryStatusNumber();
							if(xmlISHeader.getIsdate() != null){
								isIssueDate = xmlISHeader.getIsdate().getDate().getMonth()
									+"/"+xmlISHeader.getIsdate().getDate().getDay()
									+"/"+xmlISHeader.getIsdate().getDate().getYear();
							}			
							
							//xmlPartyIdenfValue_SAN = xmlISHeader.getLocationParty().getPartyIdentifier().getPartyIdentifierValue();
							locParty = xmlISHeader.getLocationParty();
							if(null!=locParty){
								xmlNameAddress = locParty.getNameAddress();
								orgUnit = xmlNameAddress.getOrgUnit();
								if(null!=orgUnit){
									orgUnitName = orgUnit.getOrgUnitName();
									orgUnitCode	= orgUnit.getOrgUnitCode();
								}
								xmlContactInfo = locParty.getContactInfo();
								if(null!=xmlContactInfo){
									contactName = xmlContactInfo.getContactName();
									mobPhone = xmlContactInfo.getMobile();
									contactType = xmlContactInfo.getContactType();
								}
							}
							//xmlNameAddress = xmlISHeader.getLocationParty().getNameAddress();
							//xmlContactInfo = (LocationPartyContact)xmlISHeader.getLocationParty().getContactInfo();
							
							xmlIsRefList = xmlISHeader.getIsRef();
							if(xmlIsRefList != null && xmlIsRefList.size()>0){
								for(int i= 0; i<xmlIsRefList.size(); i++){
									xmlISRef = (ISReference) xmlIsRefList.get(i);
									if(xmlISRef != null && xmlISRef.getIsReferenceType()!= null ){
										if(IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlISRef.getIsReferenceType().trim()))
											xmlTransID = xmlISRef.getIsReferenceValue();							
										//else if(ISBN10.equalsIgnoreCase(xmlISRef.getIsReferenceType().trim()))
											//xmlISBN10 = xmlISRef.getIsReferenceValue();
										//else if(ISBN13.equalsIgnoreCase(xmlISRef.getIsReferenceType().trim()))
											//xmlISBN13 = xmlISRef.getIsReferenceValue();
									}
								} //end for loop - xmlIsRefList
								B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - xmlTransID = "+xmlTransID+" , xmlISBN10 = "+xmlISBN10+" , xmlISBN13 = "+xmlISBN13);
								
								if(xmlTransID != null && !"".equals(xmlTransID.trim())){
									dbCon.setAutoCommit(false);
									
									xmlIsLineItemList = pneDTO.getPayload().getBusinessDocument().getIsDTO().getIsLineItem();
									if(xmlIsLineItemList != null && xmlIsLineItemList.size() > 0){
										b2bHelper = new B2BHelper();
										//xmlPartyIdenfValue_SAN = b2bHelper.addDashesInSAN(xmlPartyIdenfValue_SAN);
										
										sbInventoryId = new StringBuffer("");
										sbInventoryVersion = new StringBuffer("");
										
										for(int i= 0; i<xmlIsLineItemList.size(); i++){
											xmlISLineItem = (ISLineItem) xmlIsLineItemList.get(i);
											if(xmlISLineItem != null){
												xmlProductCode 		= null;
												dbUOMId				= null;
												dbInventoryID 		= null;
												componentFlag 		= null;
												dbInventoryVersion	= null;
												insertFlag			= false;
												
												xmlLineItemNo = xmlISLineItem.getIsLineItemNumber();
												xmlLineQtyValue = xmlISLineItem.getIsQty().getIsQtyValue().getQtyValue();
												isProduct = xmlISLineItem.getIsProduct();
												isLineRefList = xmlISLineItem.getIsLineRef();
												uom = xmlISLineItem.getIsQty().getIsQtyValue().getUOM().toUpperCase();
												
												xmlProdIdentfList = isProduct.getProdIdentifier();
												if(xmlProdIdentfList != null && xmlProdIdentfList.size()>0){
													for(int j= 0; j<xmlProdIdentfList.size(); j++){
														xmlProdIdentf = (ISProductIdentifier)xmlProdIdentfList.get(j);
														if(xmlProdIdentf != null && xmlProdIdentf.getProductAgency() != null && IPixB2BConstants.Buyer.equalsIgnoreCase(xmlProdIdentf.getProductAgency().trim()))
															xmlProductCode = xmlProdIdentf.getProductIdValue();									   
													}
												}
												if(null==xmlProductCode){
													daoErrorDTO = new ErrorDTO();
													errorId = IPixB2BConstants.ERROR_ID_121;
													daoErrorDTO.setErrorID(errorId+"");
													String errorDesp =  b2bHelper.getErrorDesp(errorId);
													daoErrorDTO.setErrorDescription(errorDesp.concat("For Line Item Number::").concat(xmlLineItemNo));
													errorList.add(daoErrorDTO);
													B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails()-The Part Number is null");
													continue;	
												}
												
												//if product code is null then skip
												if(isLineRefList != null && isLineRefList.size()>0){
													for(int j= 0; j<isLineRefList.size(); j++){
														isLineReference = (ISLineReference)isLineRefList.get(j);
														if(isLineReference != null && isLineReference.getIsLineRefType()!= null ){
															if(IPixB2BConstants.ISBN10.equalsIgnoreCase(isLineReference.getIsLineRefType().trim()))
																xmlISBN10 = isLineReference.getIsLineRefValue();
															else if(IPixB2BConstants.ISBN13.equalsIgnoreCase(isLineReference.getIsLineRefType().trim()))
																xmlISBN13 = isLineReference.getIsLineRefValue();
														}
													}
												}
												
												isBookManu = isProduct.getIsBook();
												if(isBookManu != null){
													xmlBookClassifType = isBookManu.getBookClassification().getBookClassificationType();
													if(xmlBookClassifType != null && IPixB2BConstants.Material.equalsIgnoreCase(xmlBookClassifType.trim())){
														componentFlag = IPixB2BConstants.flag_R;	//Raw Material
														sqlQuery = qry_sel_paper_master;
														prepStmt1 = dbCon.prepareStatement(sqlQuery);
														prepStmt1.clearParameters();
														prepStmt1.setString(IPixB2BConstants.ONE,xmlProductCode);
														qryParams = xmlProductCode;
														resultSet1 = prepStmt1.executeQuery();
														if(resultSet1.next() && resultSet1.getLong("records_count") > 0){ //Paper Master Exist
															insertFlag = true;
															sqlQuery = qry_sel_pix_inventory_id_1;
															prepStmt2 = dbCon.prepareStatement(sqlQuery);
															prepStmt2.clearParameters();
															prepStmt2.setString(IPixB2BConstants.ONE, senderSAN );
															prepStmt2.setString(IPixB2BConstants.TWO, xmlProductCode);
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
															B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails()-The Product Code is invalid as it is not present in Paper Master table::PIX_PMS_PAPER_CLASSIF_MV for senderSAN::"+senderSAN);
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
													}
													DBUtils.close(prepStmt3);
													DBUtils.close(resultSet3);
													
													sqlQuery = qry_sel_uom_id;
													prepStmt4 = dbCon.prepareStatement(sqlQuery);
													prepStmt4.clearParameters();
													prepStmt4.setString(IPixB2BConstants.ONE, uom);
													qryParams = uom;
													resultSet4 = prepStmt4.executeQuery();
													while(resultSet4.next())
														dbUOMId = resultSet4.getString("uom_id");
													
													DBUtils.close(prepStmt4);
													DBUtils.close(resultSet4);
													
													if(dbInventoryID == null || "".equals(dbInventoryID.trim()) || Integer.parseInt(dbInventoryID) <= 0){				        	
														sqlQuery = qry_sel_pix_inventory_id_nextval;
														prepStmt5 = dbCon.prepareStatement(sqlQuery);
														prepStmt5.clearParameters();						    
														resultSet5 = prepStmt5.executeQuery();			
														while(resultSet5.next())
															dbInventoryID = resultSet5.getString("inventory_id_next");							
														
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
														//prepStmt6.setString(SIX, xmlProductCode);
														prepStmt6.setString(IPixB2BConstants.SIX, componentFlag.equals(IPixB2BConstants.flag_R)?StringUtils.leftPad(xmlProductCode,10,"0"):xmlProductCode);
														prepStmt6.setString(IPixB2BConstants.SEVEN, componentFlag);
														prepStmt6.setString(IPixB2BConstants.EIGHT, dbUOMId);
														qryParams = dbInventoryID+","+xmlLineItemNo+","+senderSAN+","+xmlISBN10+","+xmlISBN13+","+xmlProductCode+","+componentFlag+","+dbUOMId;
														//System.out.println("Product Code in Inventory Master= " + xmlProductCode);
														recsNum = prepStmt6.executeUpdate();
														recsCount = recsCount + recsNum;
														
														DBUtils.close(prepStmt6);
													}
													B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - dbInventoryID = "+dbInventoryID);
													sbInventoryId.append(dbInventoryID+IPixB2BConstants.COMMA);
													
													sqlQuery = qry_sel_version_no;
													prepStmt7 = dbCon.prepareStatement(sqlQuery);
													prepStmt7.clearParameters();
													prepStmt7.setString(IPixB2BConstants.ONE, dbInventoryID);
													qryParams = dbInventoryID;
													resultSet7 = prepStmt7.executeQuery();
													while(resultSet7.next())
														dbInventoryVersion = resultSet7.getString("inventory_version");
													
													DBUtils.close(prepStmt7);
													DBUtils.close(resultSet7);
													
													if(dbInventoryVersion != null && !"".equals(dbInventoryVersion.trim()))
														inventoryVersionNo = Integer.valueOf(dbInventoryVersion.trim()).intValue() + 1;
													else
														inventoryVersionNo = 1 ;									
													B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - inventoryVersionNo = "+inventoryVersionNo);
													sbInventoryVersion.append(inventoryVersionNo+IPixB2BConstants.COMMA);
													
													sqlQuery = qry_ins_pix_inventory ;
													prepStmt8 = dbCon.prepareStatement(sqlQuery);
													prepStmt8.clearParameters();
													prepStmt8.setString(IPixB2BConstants.ONE, dbInventoryID);
													prepStmt8.setInt(IPixB2BConstants.TWO, inventoryVersionNo);						
													prepStmt8.setString(IPixB2BConstants.THREE, xmlTransID);
													prepStmt8.setString(IPixB2BConstants.FOUR, IPixB2BConstants.JavaB2B);
													prepStmt8.setString(IPixB2BConstants.FIVE, IPixB2BConstants.JavaB2B);
													qryParams = dbInventoryID+","+inventoryVersionNo+","+xmlTransID+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B;
													recsNum = prepStmt8.executeUpdate();
													recsCount = recsCount + recsNum;
													
													DBUtils.close(prepStmt8);
													
													sqlQuery = qry_ins_pix_inventory_line;
													prepStmt9 = dbCon.prepareStatement(sqlQuery);
													prepStmt9.clearParameters();
													prepStmt9.setString(IPixB2BConstants.ONE, dbInventoryID);
													prepStmt9.setInt(IPixB2BConstants.TWO, inventoryVersionNo);
													prepStmt9.setString(IPixB2BConstants.THREE, xmlLineItemNo);	
													prepStmt9.setString(IPixB2BConstants.FOUR, xmlLineQtyValue);
													prepStmt9.setString(IPixB2BConstants.FIVE, dbUOMId);
													qryParams = dbInventoryID+","+inventoryVersionNo+","+xmlLineItemNo+","+xmlLineQtyValue+","+dbUOMId;
													recsNum = prepStmt9.executeUpdate();
													recsCount = recsCount + recsNum;
													
													DBUtils.close(prepStmt9);
													
													sqlQuery = qry_ins_pix_inventory_party;
													prepStmt10 = dbCon.prepareStatement(sqlQuery);
													prepStmt10.clearParameters();
													prepStmt10.setString(IPixB2BConstants.ONE, dbInventoryID);
													prepStmt10.setInt(IPixB2BConstants.TWO, inventoryVersionNo);
													prepStmt10.setString(IPixB2BConstants.THREE, senderSAN);	
													prepStmt10.setString(IPixB2BConstants.FOUR, xmlNameAddress.getName1());	
													prepStmt10.setString(IPixB2BConstants.FIVE, xmlNameAddress.getName2());	
													prepStmt10.setString(IPixB2BConstants.SIX, xmlNameAddress.getName3());	
													//prepStmt10.setString(SEVEN, xmlNameAddress.getOrgUnit().getOrgUnitCode());	
													//prepStmt10.setString(EIGHT, xmlNameAddress.getOrgUnit().getOrgUnitName());	
													prepStmt10.setString(IPixB2BConstants.SEVEN, orgUnitCode);	
													prepStmt10.setString(IPixB2BConstants.EIGHT, orgUnitName);
													prepStmt10.setString(IPixB2BConstants.NINE, xmlNameAddress.getAddress1());	
													prepStmt10.setString(IPixB2BConstants.TEN, xmlNameAddress.getAddress2());	
													prepStmt10.setString(IPixB2BConstants.ELEVEN, xmlNameAddress.getAddress3());	
													prepStmt10.setString(IPixB2BConstants.TWELVE, xmlNameAddress.getAddress4());	
													prepStmt10.setString(IPixB2BConstants.THIRTEEN, xmlNameAddress.getCity());	
													prepStmt10.setString(IPixB2BConstants.FOURTEEN, xmlNameAddress.getStateOrProvince());	
													prepStmt10.setString(IPixB2BConstants.FIFTEEN, xmlNameAddress.getStateOrProvince());
													prepStmt10.setString(IPixB2BConstants.SIXTEEN, IPixB2BConstants.flag_V);
													/*qryParams = dbInventoryID+","+inventoryVersionNo+","+senderSAN
														+","+xmlNameAddress.getName1()+","+xmlNameAddress.getName2()+","+xmlNameAddress.getName3()
														+","+xmlNameAddress.getOrgUnit().getOrgUnitCode()+","+xmlNameAddress.getOrgUnit().getOrgUnitName()
														+","+xmlNameAddress.getAddress1()+","+xmlNameAddress.getAddress2()+","+xmlNameAddress.getAddress3()+","+xmlNameAddress.getAddress4()
														+","+xmlNameAddress.getCity()+","+xmlNameAddress.getStateOrProvince()+","+xmlNameAddress.getStateOrProvince()+","+flag_V;*/
													qryParams = dbInventoryID+","+inventoryVersionNo+","+senderSAN
													+","+xmlNameAddress.getName1()+","+xmlNameAddress.getName2()+","+xmlNameAddress.getName3()
													+","+orgUnitCode+","+orgUnitName+","+xmlNameAddress.getAddress1()+","+xmlNameAddress.getAddress2()
													+","+xmlNameAddress.getAddress3()+","+xmlNameAddress.getAddress4()+","+xmlNameAddress.getCity()
													+","+xmlNameAddress.getStateOrProvince()+","+xmlNameAddress.getStateOrProvince()+","+IPixB2BConstants.flag_V;
													recsNum = prepStmt10.executeUpdate();
													recsCount = recsCount + recsNum;
													
													DBUtils.close(prepStmt10);
													
													sqlQuery = qry_ins_pix_inventory_party_contact;
													prepStmt11 = dbCon.prepareStatement(sqlQuery);
													prepStmt11.clearParameters();
													prepStmt11.setString(IPixB2BConstants.ONE, dbInventoryID);
													prepStmt11.setInt(IPixB2BConstants.TWO, inventoryVersionNo);
													/*prepStmt11.setString(THREE, xmlContactInfo.getContactType());						
													prepStmt11.setString(FOUR, xmlContactInfo.getContactName());						
													prepStmt11.setString(FIVE, xmlContactInfo.getMobile());*/
													prepStmt11.setString(IPixB2BConstants.THREE, contactType);						
													prepStmt11.setString(IPixB2BConstants.FOUR, contactName);						
													prepStmt11.setString(IPixB2BConstants.FIVE, mobPhone);
													//qryParams = dbInventoryID+","+inventoryVersionNo+","+xmlContactInfo.getContactType()+","+xmlContactInfo.getContactName()+","+xmlContactInfo.getMobile();
													qryParams = dbInventoryID+","+inventoryVersionNo+","+contactType+","+contactName+","+mobPhone;
													recsNum = prepStmt11.executeUpdate();
													recsCount = recsCount + recsNum;
													
													DBUtils.close(prepStmt11);
													
													dbCon.commit();
												}else{
													B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - insertFlag = "+insertFlag);
												}
											}else{
												B2BLogger.info("InventoryChangeDAOImpl.storeInventoryChangeDetails() - xmlISLineItem is null");
											}			
										} //end for loop - xmlIsLineItemList
										
										//insert in transaction log table
										if(recsCount > 0 && sbInventoryId != null && sbInventoryId.length()>0 && sbInventoryVersion != null && sbInventoryVersion.length()>0){
											strInventoryId = sbInventoryId.substring(0, sbInventoryId.length()-1);
											strInventoryVersion = sbInventoryVersion.substring(0, sbInventoryVersion.length()-1);
											/*dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
													senderSAN, receiverSAN, 
													strInventoryId, strInventoryVersion, INVENTORY_ID, INVENTORY_VERSION,isNumber,isIssueDate, xmlTransID);*/
										}
										if(null!=errorList && errorList.size()>0){
											transStatusDTO.setStatusREAD("RE");
											transStatusDTO.setCompletePath(inDirArchiveXmlInvalid);
											dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
													senderSAN, receiverSAN, 
													null, null, null, null, isNumber,isIssueDate, xmlTransID);
										}else{
											dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
													senderSAN, receiverSAN, 
													strInventoryId, strInventoryVersion, IPixB2BConstants.INVENTORY_ID, IPixB2BConstants.INVENTORY_VERSION,isNumber,isIssueDate, xmlTransID);
										}
										if(dbTransIdNext>0){
											String transID = Long.toString(dbTransIdNext);
											transStatusDTO.setTransID(transID);
										}
									}else{
										B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - xmlIsLineItemList is null or blank");
									}
									dbCon.commit();
								}else{
									B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - xmlTransID is null or blank");
								}
							}else{
								B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - xmlIsRefList is null or blank");
							}				
						}else{
							B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - xmlISHeader is null");
						}
					}else{
						B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - pneDTO is null");
			}
			
			B2BLogger.info("InventoryStatusDAOImpl.storeInventoryStatusDetails() - dbTransIdNext = "+dbTransIdNext);
			if(dbTransIdNext > 0)
				invStatus = IPixB2BConstants.flag_Y;	
			
			B2BLogger.debug("**** InventoryStatusDAOImpl.storeInventoryStatusDetails() method EXIT *******");
		}catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			invStatus = StringUtils.getStackTrace(e);
			if(invStatus != null && invStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				invStatus = invStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			invStatus = StringUtils.getStackTrace(e);
			if(invStatus != null && invStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				invStatus = invStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
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
			DBUtils.close(prepStmt9);
			DBUtils.close(prepStmt10);
			DBUtils.close(prepStmt11);
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;
			b2bHelper		= null;
			xmlISHeader		= null;
			//xmlPartyIdenfValue_SAN = null;
			xmlNameAddress 	= null;
			xmlContactInfo 	= null;
			xmlIsRefList 	= null;
			xmlISRef 		= null;
			xmlTransID 		= null;
			xmlISBN10 		= null;
			xmlISBN13 		= null;
			xmlIsLineItemList = null;
			xmlISLineItem 	= null;
			xmlLineItemNo 	= null;
			xmlLineQtyValue = null;
			xmlProdIdentfList = null;
			xmlProdIdentf 	= null;
			isProduct		= null;
			isLineRefList	= null;
			isLineReference	= null;
			uom				= null;
			isBookManu 		= null;
			xmlProductCode 	= null;
			xmlBookClassifType = null;
			dbInventoryID 	= null;		
			dbUOMId 		= null;
			dbInventoryVersion = null;
			componentFlag 	= null;	
		}
		return invStatus;
	}
}