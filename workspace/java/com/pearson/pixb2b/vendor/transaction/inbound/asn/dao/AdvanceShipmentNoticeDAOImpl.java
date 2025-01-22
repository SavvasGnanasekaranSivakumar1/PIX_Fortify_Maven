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
 * Title		: 	AdvanceShipmentNoticeDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		17 May,2010 	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.asn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POIssueDate;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.TotalQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.BoxItemTrackNumListDTO;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.BoxRef;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DelMesRefBookLineItem;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DeliveryLegRef;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyCommonContact;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BookPacInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxCharcs;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxHeight;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxItem;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxLength;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxQuantity;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxUnitChars;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxWeight;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BoxWidth;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BuyPartyCC;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.BuyPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.CarPartyCC;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.CarPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.CarrierParty;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DMDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesBookHd;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesBookShip;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesRef;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelOriginDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelShipBookLineItem;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DeliveryLeg;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.InfoQuantity;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.LocationPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.PacIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.SupplierPartyCC;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.SupplierPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.TotInfoQty;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.TotInfoQtyValue;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.UnitHeight;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.UnitItem;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.UnitThickness;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.UnitWeight;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.UnitWidth;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.UnitsPerCrtn;
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
 * AdvanceShipmentNoticeDAOImpl is a class to communicate with the PIX database
 * and process the B2B ASN transaction data.
 * 
 * @author Ashish Agrawal
 */
public class AdvanceShipmentNoticeDAOImpl extends InboundTransStatusDAOImpl implements IAdvanceShipmentNoticeDAO{
	
	private static final String qry_sel_pix_asn_id_nextval = "SELECT SEQ_PIX_ASN_ID.nextval asn_id FROM DUAL";
	
	private static final String qry_sel_status_id = "SELECT STATUS_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_STATUS_CODE psc, "
	+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_TABLE pt"
	+" WHERE pt.table_id=psc.table_id and psc.status_code = UPPER(?)"
	+" and pt.table_name = UPPER(?)";	
	
	private static final String qry_ins_pix_asn_hdr = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN"
	+" (ASN_ID, ASN_STATUS_ID, ASN_NO, DELIVERY_SHIPMENT_ID, DELIVERY_MSG_DATE, COMMENTS, TRANSACTION_HISTORY_NO, CREATED_BY,"
	+" MODIFIED_BY, CREATED_DATE_TIME, MODIFIED_DATE_TIME)"
	+" VALUES (?, ?, ?, ?, to_date(?,'MM/DD/YYYY'), ?, ?, ?, ?, SYSDATE, SYSDATE)";
	
	private static final String qry_ins_pix_asn_del_leg  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_DELIVERY_LEG"
	+" (ASN_ID, LOCATION_PARTY_SAN, LEG_SEQ_NO, SHIP_TO_DATE, CARRIER_CODE, CARRIER_PARTY_FIRST_NAME, CARRIER_PARTY_LAST_NAME, CONTACT_FIRST_NAME,"
	+" CONTACT_LAST_NAME, COMMENTS)"
	+" VALUES (?, ?, ?, to_date(?,'MM/DD/YYYY'), ?, ?, ?, ?, ?, ?)";

	private static final String qry_sel_cc = "SELECT COUNTRY_CODE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_COUNTRY_CODE"
	+" WHERE (COUNTRY_NAME = UPPER(?) or COUNTRY_CODE=UPPER(?))";
	
	private static final String qry_ins_pix_asn_party  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_PARTY"
	+" (ASN_ID, PARTY_LINE_NO, SAN, PARTY_TYPE, NAME, ADDRESS, CITY, STATE, POSTAL_CODE, COUNTRY_CODE,"
	+" COMMENTS)"
	+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String qry_ins_pix_asn_party_contact  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_PARTY_CONTACT"
	+" (ASN_ID, PARTY_LINE_NO, CONTACT_NO, CONTACT_FIRST_NAME, CONTACT_LAST_NAME, PHONE, MOBILE, FAX, EMAIL)"
	+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String qry_sel_uom_id_ = "SELECT UOM_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_UOM_CODE"
	+" WHERE XUOM = ?";
	
	private static final String qry_ins_pix_asn_summary  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_SUMMARY"
	+" (ASN_ID, ASN_MSG_NO, PUB_UNIT_SAN, SUP_UNIT_SAN, SHIPTO_SAN, SHIP_TO_NAME, TOTAL_QUANTITY_PALLET,"
	+" TOTAL_QUANTITY_BOX, TOTAL_QUANTITY_BOOK, TOTAL_QUANTITY_WT, UOM_ID_BOOK, UOM_ID_WT,"
	+" PROCESSED_FLAG, CREATED_BY, MODIFIED_BY, CREATION_DATE_TIME, MOD_DATE_TIME)"
	+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE)";
	
	private static final String qry_ins_pix_asn_po_line  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_PO_LINE"
	+" (ASN_ID, ASN_LINE_NO, SPEC_NO, PO_ID, PO_LINE_NO, PO_ISSUE_DATE, PO_TRANSACTION_ID, ISBN10, ISBN13,"
	+" PRINTING_NO, TOTAL_PALLET, TOTAL_BOX, TOTAL_BOOK_UNIT, GROSS_WEIGHT, UOM_ID, CREATED_BY, MODIFIED_BY,"
	+" BK_NUMBER, CUST_PO_NUMBER"
	+", CREATION_DATE_TIME, MOD_DATE_TIME, DESK_COPY)"
	+" VALUES (?, ?, ?, ?, ?, to_date(?,'MM/DD/YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE,?)";

	private static final String qry_ins_pix_asn_pallet  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_PALLET"
	+" (ASN_ID, ASN_LINE_NO, PALLET_ID, TOTAL_BOX, TOTAL_BOOKS_PER_PALLET)"
	+" VALUES (?, ?, ?, ?, ?)";
	
	private static final String qry_ins_pix_asn_pallet_group  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_PALLET_GROUP"
	+" (ASN_ID, ASN_LINE_NO, PALLET_ID, GROUP_ID, TOTAL_BOX, TOTAL_BOOK, BOOKS_PER_BOX, BOX_PACKING_FLAG, COMMENTS,"
	+" BOOK_HEIGHT, BOOK_THICKNESS, BOOK_WIDTH, BOOK_WEIGHT, BOX_HEIGHT, BOX_LENGTH, BOX_WIDTH, BOX_WEIGHT)"
	+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String qry_update_pix_asn_pallet_group  = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_PALLET_GROUP"
	+" SET BOX_PACKING_FLAG=? where ASN_ID=? and ASN_LINE_NO=? and PALLET_ID=? and GROUP_ID=? and BOX_PACKING_FLAG=?";

	private static final String qry_ins_pix_asn_tracking_number  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ASN_TRACKING_NUMBER"
	+" (ASN_ID, TRACKING_NUMBER, TOTAL_CARTONS_SHIPPED, UNITS_PER_CARTON, GROSS_WEIGHT, ASN_LINE_NO)"
	+" VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String qry_sel_pix_san_trans_mapping = "SELECT SAN FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_SAN_TRANS_MAPPING"
	+" WHERE PROCESS_ID=209 and ACTIVE ='Y'";
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.asn.dao.IAdvanceShipmentNoticeDAO#storeAdvanceShipmentNoticeDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList, java.lang.String)
	 */
	public String storeAdvanceShipmentNoticeDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid){
		Connection dbCon 				= null;
		PreparedStatement prepStmt1		= null;
		ResultSet resultSet1			= null;
		String sqlQuery					= null;
		String qryParams				= null;
		int recsCount					= 0;
		int recsNum						= 0;
		ArrayList transmissionInfoList 	= null;
		B2BHelper b2bHelper				= null;
		HashMap hmSAN					= null;		
		String senderSAN				= null;		
		String receiverSAN				= null;
		String dbPOId					= null;
		String dbPOVersion				= null;
		HashMap hmPoIdpoVersion			= null;
		long dbTransIdNext 				= -1;
		String delMesStatusType			= null;
		DelMesBookHd xmlASNBookHd   	= null;
		String delMesNumb 				= null;
		ArrayList deliveryLegList		= null;
		DMDate asnDate 					= null;
		String xmlASNDate				= null;
		DeliveryLeg deliveryLeg			= null;
		DelOriginDate delOriginDate 	= null;
		String xmlDelDate				= null;
		ArrayList xmlASNRefList			= null;
		DelMesRef asnReference			= null;
		String xmlASNTransId			= null;
		ArrayList xmlDMBookShipList		= null;
		DelMesBookShip delMesBookShip	= null;
		ArrayList delShipBookLineItemList 		= null;
		DelShipBookLineItem delShipBookLineItem = null;
		String xmlPOLineitemNumr		= null;
		POInformation poInformation 	= null;
		String xmlPONumr				= null;
		String poIssueDt				= null;
		String delShipLineItemNum		= null;
		POIssueDate poIssueDate 		= null;
		ArrayList poReferenceList		= null;
		POReference poReference  		= null;
		String specificationNum			= null;
		String isbn10					= null;
		String isbn13					= null;
		String printingNumber			= null;
		String poTransId				= null;
		LineQuantity lineQty 			= null;
		String numOfPallets 			= null;
		ArrayList infoQuantityList		= null;
		InfoQuantity infoQuantity		= null;
		int totNumOfBox	 				= 0;
		int totNumOfBook	 			= 0;
		String grossWgt	 				= null;
		String grossUom	 				= null;
		String grossUomId				= null;
		ArrayList bookPacInfoList		= null;
		BookPacInfo bookPacInfo			= null;
		ArrayList pacIdentifierList		= null;
		PacIdentifier pacIdentifier		= null;
		String pacIdValue				= null;
		int palNumOfBox	 				= 0;
		String palNumOfBook	 			= null;
		ArrayList boxItemList			= null;
		ArrayList boxItemList1			= null;
		BoxItem boxItem					= null;
		BoxItem boxItem2 				= null;
		int fulOrParBoxCount			= 0;
		BoxQuantity boxQuantity			= null;
		int bookCountBox				= 0;
		ArrayList unitItemList			= null;
		UnitItem unitItem				= null;
		BoxCharcs boxCharcs				= null;
		BoxUnitChars boxUnitChars 		= null;
		UnitHeight unitHeight 			= null;
		float bookHeight				= 0;
		UnitThickness unitThickness 	= null;
		float bookThickness				= 0;
		UnitWidth unitWidth 			= null;
		float bookWidth					= 0;
		UnitWeight unitWeight 			= null;
		float bookWeight				= 0;
		BoxHeight boxHeight 			= null;
		float boxHght					= 0;
		BoxLength boxLength 			= null;
		float boxLngth					= 0;
		BoxWidth boxWidth 				= null;
		float boxWidh					= 0;
		BoxWeight boxWeight 			= null;
		float boxWght					= 0;
		UnitsPerCrtn unitsPerCrtn		= null;
		int booksPerCrtn				= 0;
		int prevbooksPerCrtn			= 0;
		float prevBoxHght				= 0; 
		float prevBoxLngth 				= 0;
		float prevBoxWidh				= 0;
		float prevBoxWght				= 0;
		String fullOrParFlag			= null;
		String tqBookUomId				= null;
		String tqWtUomId				= null;
		String asnStatus 				= IPixB2BConstants.flag_N;
		Boolean correctLineItemFlag		= Boolean.FALSE;
		String asnId					= null;
		String asnStatusId				= null;
		String delLegSeqNumber			= null;
		LocationPartyId locPartyId 		= null;
		ArrayList locationPartyIdList	= null;
		String locationSAN				= null;
		CarrierParty carParty 			= null;
		ArrayList carPartyIdList		= null;
		CarPartyId carPartyId			= null;
		String carPartyCode				= null;
		String name1					= null;
		String naFirstName				= null;
		String naLastName				= null;
		ArrayList carPartyCCList 		= null;
		CarPartyCC carPartyCC	 		= null;
		String ccName					= null;
		String ccFirstName 				= null;
		String ccLastName 				= null;
		ArrayList buyPartyIdList		= null;
		BuyPartyId buyPartyId			= null;
		String buyerSAN					= null;
		String buyerName1				= null;
		String buyerAdd1				= null;
		String buyerCity				= null;
		String buyerState				= null;
		String buyerPostCode			= null;
		String buyerCountCode			= null;
		ArrayList buyPartyCCList 		= null;
		BuyPartyCC buyPartyCC			= null;
		String buyContFirstName			= null;
		String buyContLastName			= null;
		String buyPhone					= null;
		String buyMobile				= null;
		String buyEmail					= null;
		String buyFax					= null;
		ArrayList supplierPartyIdList	= null;
		SupplierPartyId suppPartyId		= null;
		String supSAN					= null;
		String supName1					= null;
		String supAdd1					= null;
		String supAdd2					= null;
		String supAdd3					= null;
		String supAdd4					= null;
		String supCity					= null;
		String supState					= null;
		String supPostCode				= null;
		String supCountCode				= null;
		ArrayList suppPartyCCList 		= null;
		SupplierPartyCC	supPartyCC 		= null;
		String supContFirstName			= null;
		String supContLastName			= null;
		String supPhone					= null;
		String supMobile				= null;
		String supEmail					= null;
		String supFax					= null;
		ArrayList shipPartyIdList		= null;
		ShipToPartyPartyIdentifier shipPartyId 	= null;
		String shipSAN					= null;
		String shipToName				= null;
		String shipName1				= null;
		String shipAdd1					= null;
		String shipAdd2					= null;
		String shipAdd3					= null;
		String shipAdd4					= null;
		String shipCity					= null;
		String shipState				= null;
		String shipPostCode				= null;
		String shipCountCode			= null;
		ArrayList shipComContactList 	= null;
		ShipToPartyCommonContact shipComContact	= null;
		String shipContFirstName		= null;
		String shipContLastName			= null;
		String shipPhone				= null;
		String shipMobile				= null;
		String shipEmail				= null;
		String shipFax					= null;
		String delShipmentId			= null;
		TotalQuantity totalQty 			= null;
		ArrayList infoTotalQtyList 		= null;
		TotInfoQty totInfoQty			= null;
		TotInfoQtyValue totInfoQtyValue = null;
		String tqUom					= null;
		String tqPalletValue			= null;
		String tqBoxValue				= null;
		String tqBookValue				= null;
		String tqWtValue				= null;
		StringBuffer sbPOId 			= null;
		//String strPOId				= null;
		int rowCount1					= -1;
		int rowCount2					= -1;
		//int rowCount3					= -1;
		int boxCountPerPO 				= 0;
		String transRefId1				= null;
		String transRefLabel1			= null;	
		String transRefId2				= null;
		String transRefLabel2			= null;
		int errorId						= -1 ;
		ErrorDTO daoErrorDTO    		= null;
		Boolean correctDataFlag			= Boolean.FALSE;
		int boxDimCount					= 0;
		String invoiceNumber			= null;/*BK Number*/
		String customerOrderNumber		= null;/*CUSTOMER PO NUMBER#*/
		String trackingNumberHeader		= null;
		ArrayList delLegRefList			= null;
		DeliveryLegRef deliveryLegRef	= null;
		ArrayList delMesRefList			= null;
		DelMesRefBookLineItem custOrdInvoiceNumRef  = null;
		BoxRef boxRef					= null;
		String trackingNumberBox		= null;
		String boxRefType				= null;
		Boolean trackNumHeadInsert		= Boolean.FALSE;
		Boolean trackNumBoxInsert		= Boolean.FALSE;
		Boolean isHeadBoxValid			= Boolean.FALSE;
		ArrayList boxTrackNumList		= null;	
		BoxItemTrackNumListDTO boxitemDTO = null;
		ArrayList boxItemRefList		= null;
		ArrayList invoiceNumList		= null;
		String firstInvNum				= null;
		int totBookAndBox				= 0;
		float wgtPerBoxItem				= 0;
		ArrayList wgtPerBoxList 		= null;
		String isDeskCopy				= "N";
		Boolean exitBoxLoop			    = false;
		ArrayList boxItemListAllPallet	= null;
		ArrayList<String> sendorSANList = new ArrayList<String>();
		try {
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			if(null!=pneDTO){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(null!=transmissionInfoList && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_DMB);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(null!=hmSAN && hmSAN.size()>0){
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(null!=senderSAN && !"".equals(senderSAN.trim()) && null!=receiverSAN && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						delMesStatusType = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMessageStatusType();
						xmlASNBookHd = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookHd();
						if(null!=xmlASNBookHd){
							dbCon.setAutoCommit(Boolean.FALSE);
							
							/*Getting the Active Sans details - By Jyoti*/
							sqlQuery = qry_sel_pix_san_trans_mapping;
							prepStmt1 = dbCon.prepareStatement(sqlQuery);
							prepStmt1.clearParameters();
							resultSet1 = prepStmt1.executeQuery();
							if (resultSet1 != null){
							while(resultSet1.next()){
								sendorSANList.add(resultSet1.getString(1));
							}
							}
							DBUtils.close(prepStmt1);
							DBUtils.close(resultSet1);	
							
							/*Fecthing out the tracking number if available in Header STARTS*/
							if(xmlASNBookHd.getDeliveryLegList()!= null){
								deliveryLegList = xmlASNBookHd.getDeliveryLegList();
								deliveryLeg = (DeliveryLeg)deliveryLegList.get(0);
								if(deliveryLeg.getDelLegRefList() != null){
								delLegRefList = new ArrayList();
								delLegRefList = deliveryLeg.getDelLegRefList();
								if(null!=delLegRefList && delLegRefList.size()>0){
									for(int i=0; i<delLegRefList.size(); i++){
										deliveryLegRef = (DeliveryLegRef)delLegRefList.get(i);
										/*if(null != senderSAN && (senderSAN.equals("179-1419") || senderSAN.equals("100-8654") ||
												senderSAN.equals("179-6763") || senderSAN.equals("179-6801") || 
												senderSAN.equals("179-6828") || senderSAN.equals("760-5625") ||	
												senderSAN.equals("760-5811") || senderSAN.equals("920-4865"))){*/
										//By Jyoti
										if(senderSAN != null && sendorSANList != null && sendorSANList.size()>0){
											Boolean sanFlag = false;
											for(int k=0; k<sendorSANList.size(); k++){
												String s = (String)sendorSANList.get(k);
												if(senderSAN.equals(s)){
													sanFlag = true;
													break;
												}
											}
											if(sanFlag){
											if(null!=deliveryLegRef && "TrackingNumber".equalsIgnoreCase(deliveryLegRef.getDeliveryLegRefType())){
												trackingNumberHeader = deliveryLegRef.getDeliveryLegRefValue();
												trackNumHeadInsert = Boolean.TRUE;
												correctDataFlag = Boolean.TRUE;
											}
										  }
										}
									}
								}
								}
							}
							/*if(null!=trackingNumberHeader && !"".equals(trackingNumberHeader)){
								String tableName3 = "PIX_ASN_TRACKING_NUMBER";
								String whereClause3 = "TRACKING_NUMBER='"+trackingNumberHeader+"'";
								int count5 = b2bHelper.checkForRefIntegrity(tableName3, whereClause3);
								if(count5>0){
									errorId = IPixB2BConstants.ERROR_ID_132;
									daoErrorDTO = new ErrorDTO();
									daoErrorDTO.setErrorID(errorId+"");
									daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(trackingNumberHeader));
									errorList.add(daoErrorDTO);
									correctDataFlag	= Boolean.FALSE;
									B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+trackingNumberHeader);
								}
							}*/
							/*ENDS*/
							sqlQuery = qry_sel_pix_asn_id_nextval;
							prepStmt1 = dbCon.prepareStatement(sqlQuery);
							prepStmt1.clearParameters();
							resultSet1 = prepStmt1.executeQuery();
							while(resultSet1.next()){
								asnId = resultSet1.getString("asn_id");
							}
							DBUtils.close(prepStmt1);
							DBUtils.close(resultSet1);	
							
							delMesNumb = xmlASNBookHd.getDelMeseNumb();
							buyPartyIdList = xmlASNBookHd.getBuyerParty().getBuyPartyIdList();
							if(null!=buyPartyIdList && buyPartyIdList.size()>0){
								for(int cn1=0; cn1<buyPartyIdList.size(); cn1++){
									buyPartyId = (BuyPartyId)buyPartyIdList.get(cn1);
									//if(null!=buyPartyId && "AssignedByBuyer".equalsIgnoreCase(buyPartyId.getPiType())){
									if(null!=buyPartyId && "StandardAddressNumber".equalsIgnoreCase(buyPartyId.getPiType())){
										buyerSAN = buyPartyId.getPiValue();
										buyerSAN = b2bHelper.addDashesInSAN(buyerSAN);
									}
								}
							}
							buyerName1 = xmlASNBookHd.getBuyerParty().getBuyPartyNmAdd().getName1();
							buyerAdd1 = xmlASNBookHd.getBuyerParty().getBuyPartyNmAdd().getAddress1();
							buyerCity = xmlASNBookHd.getBuyerParty().getBuyPartyNmAdd().getCity();
							buyerState = xmlASNBookHd.getBuyerParty().getBuyPartyNmAdd().getStateOrProvince();
							buyerPostCode = xmlASNBookHd.getBuyerParty().getBuyPartyNmAdd().getPostalCode();
							buyerCountCode = xmlASNBookHd.getBuyerParty().getBuyPartyNmAdd().getCountry();
							
							//Inserting the Buyer Party Name Address Details
							recsNum = insertAsnPartyDetails(dbCon, asnId, IPixB2BConstants.BUYER_PARTY_LINE_NO,
									buyerSAN, IPixB2BConstants.BUYER_PARTY_TYPE, buyerName1, buyerAdd1,
									buyerCity, buyerState, buyerPostCode, buyerCountCode);
							recsCount = recsCount + recsNum;
							
							
							buyPartyCCList = xmlASNBookHd.getBuyerParty().getBuyPartyCCList();
							if(null!=buyPartyCCList && buyPartyCCList.size()>0){
								for(int cnt2=0; cnt2<buyPartyCCList.size(); cnt2++){
									buyContFirstName = null;
									buyContLastName = null;
									buyPhone = null;
									buyMobile = null;
									buyEmail = null;
									buyFax = null;
									
									buyPartyCC = (BuyPartyCC)buyPartyCCList.get(cnt2);
									if(null!=buyPartyCC){
										String buyContactName = buyPartyCC.getContactName();
										if(buyContactName.contains(" ")){
											buyContFirstName = buyContactName.substring(0, buyContactName.lastIndexOf(" "));
											buyContLastName = buyContactName.substring(buyContactName.lastIndexOf(" ")+1, buyContactName.length());
										}else{
											buyContFirstName = buyContactName;
										}
										buyPhone = buyPartyCC.getPhone();
										buyMobile = buyPartyCC.getMobile();
										buyFax = buyPartyCC.getFax();
										buyEmail = buyPartyCC.getEmail();
									}
									//Inserting the Buyer Party Common Contact Details
									recsNum = insertAsnPartyContact(dbCon, asnId, IPixB2BConstants.BUYER_PARTY_LINE_NO,
											(cnt2+1), buyContFirstName, buyContLastName, buyPhone, buyMobile, buyFax,
											buyEmail);
									recsCount = recsCount + recsNum;
								}
							}
							
							supplierPartyIdList = xmlASNBookHd.getSupParty().getSupplierPartyIdList();
							if(null!=supplierPartyIdList && supplierPartyIdList.size()>0){
								for(int cnt3=0; cnt3<supplierPartyIdList.size(); cnt3++){
									suppPartyId = (SupplierPartyId)supplierPartyIdList.get(cnt3);
									if(null!=suppPartyId && "StandardAddressNumber".equalsIgnoreCase(suppPartyId.getPiType())){
										supSAN = suppPartyId.getPiValue();
										supSAN = b2bHelper.addDashesInSAN(supSAN);
									}
								}
							}
							supName1 = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getName1();
							supAdd1 = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress1();
							/** Extra address extraction fields added for DROP SHIP**/
							if(xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress2()!=null 
									&& !xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress2().equals("")){
							  supAdd2 = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress2();
							  supAdd1 = supAdd1 +", "+ supAdd2;
							}
							if(xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress3()!=null 
									&& !xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress3().equals("")){
							  supAdd3 = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress3();
							  supAdd1 = supAdd1 +", "+ supAdd3;
							}
							if(xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress4()!=null 
									&& !xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress4().equals("")){
							  supAdd4 = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getAddress4();
							  supAdd1 = supAdd1 +", "+ supAdd4;
							}
							/************ENDS************/
							supCity = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getCity();
							supState = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getStateOrProvince();
							supPostCode = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getPostalCode();
							supCountCode = xmlASNBookHd.getSupParty().getSupplierPartyNmAdd().getCountry();
							
							//Inserting the Supplier Party Name Address Details
							recsNum = insertAsnPartyDetails(dbCon, asnId, IPixB2BConstants.SUPP_PARTY_LINE_NO, supSAN, 
									IPixB2BConstants.SUPP_PARTY_TYPE, supName1, supAdd1, supCity, supState
									, supPostCode, supCountCode);
							recsCount = recsCount + recsNum;
							
							suppPartyCCList = xmlASNBookHd.getSupParty().getSupplierPartyCCList();
							if(null!=suppPartyCCList && suppPartyCCList.size()>0){
								for(int cnt4=0; cnt4<suppPartyCCList.size(); cnt4++){
									supContFirstName = null;
									supContLastName = null;
									supPhone = null;
									supMobile = null;
									supEmail = null;
									supFax = null;
									
									supPartyCC = (SupplierPartyCC)suppPartyCCList.get(cnt4);
									if(null!=supPartyCC){
										String supContactName = supPartyCC.getContactName();
										if(supContactName.contains(" ")){
											supContFirstName = supContactName.substring(0, supContactName.lastIndexOf(" "));
											supContLastName = supContactName.substring(supContactName.lastIndexOf(" ")+1, supContactName.length());
										}else{
											supContFirstName = supContactName;
										}
										supPhone = supPartyCC.getPhone();
										supMobile = supPartyCC.getMobile();
										supFax = supPartyCC.getFax();
										supEmail = supPartyCC.getEmail();
									}
									//Inserting the Supplier Party Common Contact Details
									recsNum = insertAsnPartyContact(dbCon, asnId, IPixB2BConstants.SUPP_PARTY_LINE_NO,
											(cnt4+1), supContFirstName, supContLastName, supPhone, supMobile, supFax,
											supEmail);
									recsCount = recsCount + recsNum;
								}
							}
							
							shipPartyIdList = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getPartyIdentifierList();
							String sipToParType = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getPartyType();
							if(null!= sipToParType && !sipToParType.equals("") && sipToParType.equals(IPixB2BConstants.Warehouse)){
							if(null!=shipPartyIdList && shipPartyIdList.size()>0){
								for(int cnt5=0; cnt5<shipPartyIdList.size(); cnt5++){
								shipPartyId = (ShipToPartyPartyIdentifier)shipPartyIdList.get(cnt5);
									if(null!=shipPartyId && "AssignedByBuyer".equalsIgnoreCase(shipPartyId.getPartyIdentifierType())){
										shipSAN = shipPartyId.getPartyIdentifierValue();
										Pattern patt = Pattern.compile("[a-zA-Z]{3}");
										Matcher match = patt.matcher(shipSAN);
										Boolean result = match.find();
										if(result){
											int start = match.start();
											int end = match.end();
											shipToName = shipSAN.substring(start, end);
										}else {
											shipToName = shipSAN;
										}
									}
								}
							}
							}
							if(null!= sipToParType && !sipToParType.equals("") && sipToParType.equals(IPixB2BConstants.Buyer)){
								isDeskCopy = "Y";
							}
							shipName1 = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getName1();
							shipAdd1 = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress1();
							/** Extra address extraction fields added for DROP SHIP**/
							if(xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress2() != null 
									&& !xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress2().equals("")){
								shipAdd2 = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress2();
								shipAdd1 = shipAdd1 +" ,"+ shipAdd2;
							}
							if(xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress3() != null 
									&& !xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress3().equals("")){
								shipAdd3 = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress3();
								shipAdd1 = shipAdd1 +" ,"+ shipAdd3;
							}
							if(xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress4() != null 
									&& !xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress4().equals("")){
								shipAdd4 = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getAddress4();
								shipAdd1 = shipAdd1 +" ,"+ shipAdd4;
							}
							/**ENDS**/
							shipCity = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getCity();
							shipState = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getStateOrProvince();
							shipPostCode = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getPostalCode();
							shipCountCode = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getNameAddress().getCountry();
							
							//Inserting the Ship To Party Name Address Details
							recsNum = insertAsnPartyDetails(dbCon, asnId, IPixB2BConstants.SHIP_PARTY_LINE_NO, shipSAN, 
									IPixB2BConstants.SHIP_PARTY_TYPE, shipName1, shipAdd1, shipCity,
									shipState, shipPostCode, shipCountCode);
							recsCount = recsCount + recsNum;
							
							shipComContactList = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getCommonContact();
							if(null!=shipComContactList && shipComContactList.size()>0){
								for(int cnt6=0; cnt6<shipComContactList.size(); cnt6++){
									shipContFirstName = null;
									shipContLastName = null;
									shipPhone = null;
									shipMobile = null;
									shipEmail = null;
									shipFax = null;
									
									shipComContact = (ShipToPartyCommonContact)shipComContactList.get(cnt6);
									if(null!=shipComContact){
										String shipContactName = shipComContact.getContactName();
										if(shipContactName.contains(" ")){
											shipContFirstName = shipContactName.substring(0, shipContactName.lastIndexOf(" "));
											shipContLastName = shipContactName.substring(shipContactName.lastIndexOf(" ")+1, shipContactName.length());
										}else{
											shipContFirstName = shipContactName;
										}
										shipPhone = shipComContact.getPhone();
										shipMobile = shipComContact.getMobile();
										shipFax = shipComContact.getFax();
										shipEmail = shipComContact.getEmail();
									}
									//Inserting the Ship To Party Common Contact Details
									recsNum = insertAsnPartyContact(dbCon, asnId, IPixB2BConstants.SHIP_PARTY_LINE_NO,
											(cnt6+1), shipContFirstName, shipContLastName, shipPhone, shipMobile,
											shipFax, shipEmail);
									recsCount = recsCount + recsNum;
								}
							}
							
							deliveryLegList = xmlASNBookHd.getDeliveryLegList();
							if(null!=deliveryLegList && deliveryLegList.size()>0){
								for(int cnt7=0; cnt7<deliveryLegList.size(); cnt7++){
									delLegSeqNumber = null;
									delOriginDate = null;
									xmlDelDate = null;
									locationPartyIdList = null;
									locPartyId = null;
									locationSAN = null;
									carParty = null;
									carPartyIdList = null;
									carPartyId = null;
									carPartyCode = null;
									naFirstName = null;
									naLastName = null;
									carPartyCCList = null;
									carPartyCC= null;
									ccName	= null;
									ccFirstName = null;
									ccLastName = null;
									
									deliveryLeg = (DeliveryLeg)deliveryLegList.get(cnt7);
									if(null!=deliveryLeg){
										delLegSeqNumber = deliveryLeg.getDelLegSeqNumber();
										delOriginDate = deliveryLeg.getDelOrigin().getDelOriginDate();
										if(null!=delOriginDate){
											xmlDelDate = delOriginDate.getMonth()
											+"/"+delOriginDate.getDay()
											+"/"+delOriginDate.getYear();
										}
										locationPartyIdList = deliveryLeg.getDelOrigin().getLocationParty().getLocationPartyIdList();
										if(null!=locationPartyIdList && locationPartyIdList.size()>0){
											for(int cnt8=0; cnt8<locationPartyIdList.size(); cnt8++){
												locPartyId = (LocationPartyId)locationPartyIdList.get(cnt8);
												if(null!=locPartyId && "StandardAddressNumber".equalsIgnoreCase(locPartyId.getPiType())){
													locationSAN = locPartyId.getPiValue();
													locationSAN = b2bHelper.addDashesInSAN(locationSAN);
												}
											}
										}
										if(null==locationSAN){
											locationSAN = supSAN;
										}
										carParty = deliveryLeg.getCarParty();
										if(null!=carParty){
											carPartyIdList = carParty.getCarPartyIdList();
											if(null!=carPartyIdList && carPartyIdList.size()>0){
												for(int cnt9=0; cnt9<carPartyIdList.size(); cnt9++){
													carPartyId = (CarPartyId)carPartyIdList.get(cnt9);
													if(null!=carPartyId && "StandardCarrierAlphaCode".equalsIgnoreCase(carPartyId.getPiType())){
														carPartyCode = carPartyId.getPiValue();
													}
												}
											}
											name1 = carParty.getCarPartyNmAdd().getName1();
											if(name1.contains(" ")){
												naFirstName = name1.substring(0, name1.lastIndexOf(" "));
												naLastName = name1.substring(name1.lastIndexOf(" ")+1, name1.length());
											}else{
												naFirstName = name1;
											}
											
											carPartyCCList = carParty.getCarPartyCCList();
											if(null!=carPartyCCList && carPartyCCList.size()>0){
												for(int cnt10=0; cnt10<carPartyCCList.size(); cnt10++){
													carPartyCC=(CarPartyCC)carPartyCCList.get(cnt10);
													if(null!=carPartyCC){
														ccName = carPartyCC.getContactName();
														if(ccName.contains(" ")){
															ccFirstName = ccName.substring(0, ccName.lastIndexOf(" "));
															ccLastName = ccName.substring(ccName.lastIndexOf(" ")+1, ccName.length());
														}
													}
												}
											}												
										}
									}
									sqlQuery = qry_ins_pix_asn_del_leg;
									prepStmt1 = dbCon.prepareStatement(sqlQuery);
									prepStmt1.clearParameters();
									prepStmt1.setString(IPixB2BConstants.ONE,asnId);
									prepStmt1.setString(IPixB2BConstants.TWO,locationSAN);
									prepStmt1.setString(IPixB2BConstants.THREE,delLegSeqNumber);
									prepStmt1.setString(IPixB2BConstants.FOUR,xmlDelDate);
									prepStmt1.setString(IPixB2BConstants.FIVE,carPartyCode);
									prepStmt1.setString(IPixB2BConstants.SIX,naFirstName);
									prepStmt1.setString(IPixB2BConstants.SEVEN,naLastName);
									prepStmt1.setString(IPixB2BConstants.EIGHT,ccFirstName);
									prepStmt1.setString(IPixB2BConstants.NINE,ccLastName);
									prepStmt1.setString(IPixB2BConstants.TEN,null);
									qryParams = asnId+","+locationSAN+","+delLegSeqNumber+","+xmlDelDate+
										","+carPartyCode+","+naFirstName+","+naLastName+","+ccFirstName+
										","+ccLastName+","+null;
									recsNum = prepStmt1.executeUpdate();
									recsCount = recsCount + recsNum;
									
									DBUtils.close(prepStmt1);
								}
							}
							
							asnDate =xmlASNBookHd.getDelMesDate().getDmDate();
							if(null!=asnDate){
								xmlASNDate = asnDate.getMonth()
								+"/"+asnDate.getDay()
								+"/"+asnDate.getYear();
							}
							
							xmlASNRefList = xmlASNBookHd.getDelMesRefList();							
							if(null!=xmlASNRefList && xmlASNRefList.size()>0){
								for(int cnt11=0; cnt11<xmlASNRefList.size(); cnt11++){
									asnReference = (DelMesRef)xmlASNRefList.get(cnt11);
									if(null!=asnReference && null!=asnReference.getDelMesRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(asnReference.getDelMesRefType().trim())){
										xmlASNTransId = asnReference.getDelMesRefVal();
									}
								}
								if(null!=xmlASNTransId && !"".equals(xmlASNTransId.trim())){
									sqlQuery = qry_sel_status_id;
									prepStmt1 = dbCon.prepareStatement(sqlQuery);
									prepStmt1.clearParameters();
									prepStmt1.setString(IPixB2BConstants.ONE,delMesStatusType);
									prepStmt1.setString(IPixB2BConstants.TWO,IPixB2BConstants.DM_TABLE_NAME);
									qryParams = delMesStatusType+","+IPixB2BConstants.DM_TABLE_NAME;
									resultSet1 = prepStmt1.executeQuery();
									while(resultSet1.next())
										asnStatusId = resultSet1.getString("STATUS_ID");
									
									DBUtils.close(prepStmt1);
									DBUtils.close(resultSet1);
									
									xmlDMBookShipList = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookShipList();
									if(null!=xmlDMBookShipList && xmlDMBookShipList.size() > 0){
										for(int cnt12= 0; cnt12<xmlDMBookShipList.size(); cnt12++){
											delMesBookShip = (DelMesBookShip)xmlDMBookShipList.get(cnt12);
											if(null!=delMesBookShip){
												delShipmentId = delMesBookShip.getDeliveryShipmentId();
											}
										}
									}
									
									sqlQuery = qry_ins_pix_asn_hdr;
									prepStmt1 = dbCon.prepareStatement(sqlQuery);
									prepStmt1.clearParameters();
									prepStmt1.setString(IPixB2BConstants.ONE,asnId);
									prepStmt1.setString(IPixB2BConstants.TWO,asnStatusId);
									prepStmt1.setString(IPixB2BConstants.THREE,delMesNumb);
									prepStmt1.setString(IPixB2BConstants.FOUR,delShipmentId);
									prepStmt1.setString(IPixB2BConstants.FIVE,xmlASNDate);
									prepStmt1.setString(IPixB2BConstants.SIX,IPixB2BConstants.ASN_COMMENTS);
									prepStmt1.setString(IPixB2BConstants.SEVEN,xmlASNTransId);
									prepStmt1.setString(IPixB2BConstants.EIGHT,IPixB2BConstants.JavaB2B);
									prepStmt1.setString(IPixB2BConstants.NINE,IPixB2BConstants.JavaB2B);
									qryParams = asnId+","+asnStatusId+","+delMesNumb+","+xmlASNDate+
										","+IPixB2BConstants.ASN_COMMENTS+","+IPixB2BConstants.JavaB2B;
									recsNum = prepStmt1.executeUpdate();
									recsCount = recsCount + recsNum;
									
									DBUtils.close(prepStmt1);
									
									totalQty = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookSum().getTotalQty();
									if(null!=totalQty){
										tqUom = totalQty.getValueQty().getQtyUOM();
										/*tqUomId = getUomId(dbCon, tqUom);
										tqValue = totalQty.getValueQty().getValValue();*/
										if("PalletUnit".equalsIgnoreCase(tqUom)){
											tqPalletValue=totalQty.getValueQty().getValValue();
										}
									}
									
									infoTotalQtyList = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookSum().getTotInfoQtyList();
									if(null!=infoTotalQtyList && infoTotalQtyList.size()>0){
										for (int count=0; count<infoTotalQtyList.size(); count++){
											totInfoQty = (TotInfoQty)infoTotalQtyList.get(count);
											if(null!=totInfoQty){
												tqUom = totInfoQty.getTotInfoQtyValue().getUom();
												/*if("PalletUnit".equalsIgnoreCase(tqUom)){
													tqPalletValue=totInfoQty.getTotInfoQtyValue().getQtyValue();
												}else*/ if("Box".equalsIgnoreCase(tqUom)){
													tqBoxValue=totInfoQty.getTotInfoQtyValue().getQtyValue();
												}else if("BookUnit".equalsIgnoreCase(tqUom)){
													tqBookUomId	= getUomId(dbCon, tqUom);
													tqBookValue=totInfoQty.getTotInfoQtyValue().getQtyValue();
												}else if("Pound".equalsIgnoreCase(tqUom)){
													tqWtUomId = getUomId(dbCon, tqUom);
													tqWtValue=totInfoQty.getTotInfoQtyValue().getQtyValue();
												}
											}
										}
									}
									
									sqlQuery = qry_ins_pix_asn_summary;
									prepStmt1 = dbCon.prepareStatement(sqlQuery);
									prepStmt1.clearParameters();
									prepStmt1.setString(IPixB2BConstants.ONE,asnId);
									prepStmt1.setString(IPixB2BConstants.TWO,delMesNumb);
									prepStmt1.setString(IPixB2BConstants.THREE,buyerSAN);
									prepStmt1.setString(IPixB2BConstants.FOUR,supSAN);
									prepStmt1.setString(IPixB2BConstants.FIVE,shipSAN);
									prepStmt1.setString(IPixB2BConstants.SIX,shipToName);
									prepStmt1.setString(IPixB2BConstants.SEVEN,tqPalletValue);
									prepStmt1.setString(IPixB2BConstants.EIGHT,tqBoxValue);
									prepStmt1.setString(IPixB2BConstants.NINE,tqBookValue);
									prepStmt1.setString(IPixB2BConstants.TEN,tqWtValue);
									prepStmt1.setString(IPixB2BConstants.ELEVEN,tqBookUomId);
									prepStmt1.setString(IPixB2BConstants.TWELVE,tqWtUomId);
									prepStmt1.setString(IPixB2BConstants.THIRTEEN,IPixB2BConstants.ASN_PROCESSED_FLAG_N);
									prepStmt1.setString(IPixB2BConstants.FOURTEEN,IPixB2BConstants.JavaB2B);
									prepStmt1.setString(IPixB2BConstants.FIFTEEN,IPixB2BConstants.JavaB2B);
									qryParams = asnId+","+delMesNumb+","+buyerSAN+","+supSAN+
										","+shipSAN+","+shipToName+","+tqPalletValue+","+tqBoxValue+
										","+tqBookValue+","+tqWtValue+","+tqBookUomId+","+tqWtUomId+
										","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B;
									recsNum = prepStmt1.executeUpdate();
									recsCount = recsCount + recsNum;
									
									DBUtils.close(prepStmt1);
									
									//Savepoint svpt1 = dbCon.setSavepoint("SAVEPOINT_1");
									xmlDMBookShipList = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookShipList();
									if(null!=xmlDMBookShipList && xmlDMBookShipList.size() > 0){
										sbPOId 	= new StringBuffer();
										for(int cnt13= 0; cnt13<xmlDMBookShipList.size(); cnt13++){
											delMesBookShip = (DelMesBookShip)xmlDMBookShipList.get(cnt13);
											if(null!=delMesBookShip){
												delShipBookLineItemList = delMesBookShip.getDelShipBookLineItemList();
												if(null!=delShipBookLineItemList && delShipBookLineItemList.size() > 0){
													for(int cnt14= 0; cnt14<delShipBookLineItemList.size(); cnt14++){
														rowCount1			= 1;
														//rowCount3			= 1;
														boxCountPerPO 		= 0;
														correctDataFlag		= Boolean.TRUE;
														delShipLineItemNum 	= null;
														xmlPOLineitemNumr 	= null;
														lineQty				= null;
														numOfPallets 		= null;
														xmlPONumr 			= null;
														poIssueDate			= null;
														poIssueDt			= null;
														poReferenceList		= null;
														poReference			= null;
														specificationNum	= null;
														isbn10				= null;
														isbn13				= null;
														printingNumber		= null;
														poTransId			= null;
														dbPOId				= null;
														dbPOVersion			= null;
														infoQuantityList	= null;
														infoQuantity		= null;
														totNumOfBox 		= 0;
														totNumOfBook 		= 0;
														grossUom			= null;
														grossUomId			= null;
														grossWgt			= null;
														bookPacInfoList		= null;
														bookPacInfo			= null;
														pacIdentifierList 	= null;
														pacIdentifier		= null;
														pacIdValue			= null;
														palNumOfBox			= 0;
														palNumOfBook		= null;
														boxItemList			= null;
														boxItemList1		= null;
														boxItem				= null;
														boxItem2 			= null;
														fulOrParBoxCount	= 0;
														bookCountBox		= 0;
														unitItemList		= null;
														unitItem			= null;
														boxUnitChars 		= null;
														unitHeight 			= null;
														bookHeight			= 0;
														unitThickness		= null;
														bookThickness		= 0;
														unitWidth			= null;
														bookWidth			= 0;
														unitWeight			= null;
														bookWeight			= 0;
														boxCharcs			= null;
														boxHeight			= null;
														boxHght				= 0;
														boxLength			= null;
														boxLngth			= 0;
														boxWidth			= null;
														boxWidh				= 0;
														boxWeight			= null;
														boxWght				= 0;
														unitsPerCrtn		= null;
														booksPerCrtn		= 0;
														fullOrParFlag		= null;
														prevBoxHght			= 0; 
														prevBoxLngth 		= 0;
														prevBoxWidh			= 0;
														prevBoxWght			= 0;
														boxDimCount			= 0;
														boxItemRefList		= null;
														totBookAndBox		= 0;
														wgtPerBoxItem 		= 0;
														boxItemListAllPallet = null;
														if(trackingNumberHeader!=null && !trackingNumberHeader.equals("")){
															trackNumHeadInsert = Boolean.TRUE;
														}
														
														delShipBookLineItem = (DelShipBookLineItem)delShipBookLineItemList.get(cnt14);
														if(null!=delShipBookLineItem){
															delShipLineItemNum = delShipBookLineItem.getDelShipLineItemNum();
															xmlPOLineitemNumr = delShipBookLineItem.getPoLineItemNumber();
															lineQty = delShipBookLineItem.getLineQty();
															if(null!=lineQty && "PalletUnit".equals(lineQty.getLineQtyValue().getUOM())){
																numOfPallets = lineQty.getLineQtyValue().getQtyValue();
															}
															poInformation = delShipBookLineItem.getPoInformation();
															if(null!=poInformation){
																xmlPONumr = poInformation.getPoNumber();
																poIssueDate = poInformation.getPoIssueDate();
																if(null!=poIssueDate){
																	poIssueDt = poIssueDate.getDate().getMonth()
																	+"/"+poIssueDate.getDate().getDay()
																	+"/"+poIssueDate.getDate().getYear(); 
																}
																poReferenceList = poInformation.getPoReference();
																if(null!=poReferenceList && poReferenceList.size()>0){
																	specificationNum	= null;
																	isbn10				= null;
																	isbn13				= null;
																	printingNumber		= null;
																	poTransId			= null;
																	for(int cnt15=0; cnt15<poReferenceList.size(); cnt15++){
																		poReference = (POReference)poReferenceList.get(cnt15);
																		if(null!=poReference){
																			if("SpecificationNumber".equals(poReference.getPoReferenceType())){
																				specificationNum = poReference.getPoReferenceValue();
																			}else if("ISBN10".equals(poReference.getPoReferenceType())){
																				isbn10 = poReference.getPoReferenceValue();
																			}else if("ISBN13".equals(poReference.getPoReferenceType())){
																				isbn13 = poReference.getPoReferenceValue();
																			}else if("PrintingNumber".equals(poReference.getPoReferenceType())){
																				printingNumber = poReference.getPoReferenceValue();
																			}else if("TransactionID".equals(poReference.getPoReferenceType())){
																				poTransId = poReference.getPoReferenceValue();
																			}
																		}
																	} // end of PO Reference for loop
																}
															}
															if(null==xmlPONumr || "".equals(xmlPONumr)){
																errorId = IPixB2BConstants.ERROR_ID_74;
																daoErrorDTO = new ErrorDTO();
																daoErrorDTO.setErrorID(errorId+"");
																daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																errorList.add(daoErrorDTO);
																correctDataFlag	= Boolean.FALSE;
																B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - xmlPONumr is null or blank");
															}else{
																String tableName = "PIX_PO_LIST_SUMMARY";
																String whereClause = "PO_NO='"+xmlPONumr+"'";
																int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
																if(count<=0){
																	errorId = IPixB2BConstants.ERROR_ID_75;
																	daoErrorDTO = new ErrorDTO();
																	daoErrorDTO.setErrorID(errorId+"");
																	daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(xmlPONumr));
																	errorList.add(daoErrorDTO);
																	correctDataFlag	= Boolean.FALSE;
																	B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+xmlPONumr+" is invalid");
																}else{
																	hmPoIdpoVersion = b2bHelper.getPoIdPoVersion(dbCon, xmlPONumr);
																	if(hmPoIdpoVersion != null && hmPoIdpoVersion.size()>0){
																		dbPOId = (String)hmPoIdpoVersion.get("PO_ID");
																		dbPOVersion= (String)hmPoIdpoVersion.get("PO_VERSION");
																		if(null!=dbPOId && !"".equals(dbPOId.trim()) && null!=dbPOVersion && !"".equals(dbPOVersion.trim())){
																			sbPOId.append(dbPOId+IPixB2BConstants.COMMA);
																			if(null==xmlPOLineitemNumr || "".equals(xmlPOLineitemNumr)){
																				errorId = IPixB2BConstants.ERROR_ID_78;
																				daoErrorDTO = new ErrorDTO();
																				daoErrorDTO.setErrorID(errorId+"");
																				daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																				errorList.add(daoErrorDTO);
																				correctDataFlag	= Boolean.FALSE;
																				B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - xmlPOLineitemNumr is null or blank");
																			}else{
																				String tableName1 = "PIX_PO_LINE";
																				String whereClause1 = "PO_LINE_NO='"+xmlPOLineitemNumr+"'and PO_ID='"+dbPOId+"' and PO_VERSION='"+dbPOVersion+"'";
																				int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
																				if(count1<=0){
																					errorId = IPixB2BConstants.ERROR_ID_79;
																					daoErrorDTO = new ErrorDTO();
																					daoErrorDTO.setErrorID(errorId+"");
																					daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(xmlPOLineitemNumr));
																					errorList.add(daoErrorDTO);
																					correctDataFlag	= Boolean.FALSE;
																					B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+xmlPOLineitemNumr+" is invalid");
																				}
																			}
																			
																			if((null==isbn13 || "".equals(isbn13))&& (null==isbn10 || "".equals(isbn10))){
																				errorId = IPixB2BConstants.ERROR_ID_2;
																				daoErrorDTO = new ErrorDTO();
																				daoErrorDTO.setErrorID(errorId+"");
																				daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																				errorList.add(daoErrorDTO);
																				correctDataFlag	= Boolean.FALSE;
																				B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - isbn13 is null or blank");
																			}else
																			{
																				if(null!=isbn13 && !"".equals(isbn13)){
																					String tableName2 = "PIX_PO_LIST_SUMMARY";
																					String whereClause2 = "ISBN13='"+isbn13+"'and PO_ID='"+dbPOId+"' and PO_NO='"+xmlPONumr+"'";
																					int count2 = b2bHelper.checkForRefIntegrity(tableName2, whereClause2);
																					if(count2<=0){
																						errorId = IPixB2BConstants.ERROR_ID_3;
																						daoErrorDTO = new ErrorDTO();
																						daoErrorDTO.setErrorID(errorId+"");
																						daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(isbn13));
																						errorList.add(daoErrorDTO);
																						correctDataFlag	= Boolean.FALSE;
																						B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+isbn13+" is invalid");
																					}
																				}
																				if(null!=isbn10 && !"".equals(isbn10)){
																					String tableName3 = "PIX_PO_LIST_SUMMARY";
																					String whereClause3 = "ISBN10='"+isbn10+"'and PO_ID='"+dbPOId+"' and PO_NO='"+xmlPONumr+"'";
																					int count3 = b2bHelper.checkForRefIntegrity(tableName3, whereClause3);
																					if(count3<=0){
																						errorId = IPixB2BConstants.ERROR_ID_4;
																						daoErrorDTO = new ErrorDTO();
																						daoErrorDTO.setErrorID(errorId+"");
																						daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(isbn10));
																						errorList.add(daoErrorDTO);
																						correctDataFlag	= Boolean.FALSE;
																						B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+isbn10+" is invalid");
																					}
																				}
																			}
																			
																			delMesRefList = delShipBookLineItem.getDelMesRefList();
																			if(delMesRefList != null){
																				if(null!=delMesRefList && delMesRefList.size()>0){
																					for(int i=0; i<delMesRefList.size(); i++){
																						custOrdInvoiceNumRef = new DelMesRefBookLineItem();
																						custOrdInvoiceNumRef = (DelMesRefBookLineItem)delMesRefList.get(i);
																						if(null!=custOrdInvoiceNumRef && "CustomerOrderNumber".equalsIgnoreCase(custOrdInvoiceNumRef.getDelMesRefType())){
																							customerOrderNumber = custOrdInvoiceNumRef.getDelMesRefVal();
																						}else if(null!=custOrdInvoiceNumRef && "InvoiceNumber".equalsIgnoreCase(custOrdInvoiceNumRef.getDelMesRefType())){
																							invoiceNumber = custOrdInvoiceNumRef.getDelMesRefVal();
																							firstInvNum = invoiceNumber;
																						}
																					}
																				}
																			}
																			
																			if(cnt14 < 0){
																				invoiceNumList.add(invoiceNumber);
																			}
																			
																			if(null!=customerOrderNumber && !"".equals(customerOrderNumber)){
																				String tableName3 = "PIX_DS_HEADER";
																				String whereClause3 = "ISBN10='"+isbn10+"'and PO_ID = '"+dbPOId+"' and BK_NUMBER = '"+invoiceNumber+"' and PO_LINE_No = "+xmlPOLineitemNumr/*+"' and CUST_PO_NUMBER = "+customerOrderNumber*/;
																				int count4 = b2bHelper.checkForRefIntegrity(tableName3, whereClause3);
																				if(count4<=0){
																					errorId = IPixB2BConstants.ERROR_ID_144;
																					daoErrorDTO = new ErrorDTO();
																					daoErrorDTO.setErrorID(errorId+"");
																					daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(customerOrderNumber));
																					errorList.add(daoErrorDTO);
																					correctDataFlag	= Boolean.FALSE;
																				}
																			}
																			
																			/* CHECK FOR STATUS ID
																			 * 
																			 * if(null!=customerOrderNumber && !"".equals(customerOrderNumber)){
																				String tableName3 = "PIX_DS_HEADER";
																				String whereClause3 = "ISBN10='"+isbn10+"'and PO_ID = '"+dbPOId+"' and BK_NUMBER = '"+invoiceNumber+"' and PO_LINE_No = "+xmlPOLineitemNumr+"' and STATUS_ID = "101;
																				int count4 = b2bHelper.checkForRefIntegrity(tableName3, whereClause3);
																				if(count4<=0){
																					errorId = IPixB2BConstants.ERROR_ID_131;
																					daoErrorDTO = new ErrorDTO();
																					daoErrorDTO.setErrorID(errorId+"");
																					daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(customerOrderNumber));
																					errorList.add(daoErrorDTO);
																					correctDataFlag	= Boolean.FALSE;
																				}
																			}*/
																			
																			infoQuantityList = delShipBookLineItem.getInfoQuantityList();
																			if(null!=infoQuantityList && infoQuantityList.size()>0){
																				totNumOfBox 	= 0;
																				totNumOfBook 	= 0;
																				grossUom		= null;
																				grossUomId		= null;
																				grossWgt		= null;
																				for(int cnt16=0; cnt16<infoQuantityList.size(); cnt16++){
																					infoQuantity = (InfoQuantity)infoQuantityList.get(cnt16);
																					if(null!=infoQuantity){
																						if("Count".equals(infoQuantity.getQtyType()) && "Box".equals(infoQuantity.getInfoQtyValue().getUom())){
																							totNumOfBox = Integer.parseInt(infoQuantity.getInfoQtyValue().getQtyValue());
																						}else if("Count".equals(infoQuantity.getQtyType()) && "BookUnit".equals(infoQuantity.getInfoQtyValue().getUom())){
																							totNumOfBook = Integer.parseInt(infoQuantity.getInfoQtyValue().getQtyValue());
																						}else if("GrossWeight".equals(infoQuantity.getQtyType())){
																							grossUom = infoQuantity.getInfoQtyValue().getUom();
																							grossUomId = getUomId(dbCon, grossUom);
																							grossWgt = infoQuantity.getInfoQtyValue().getQtyValue();
																						}
																					}
																				}
																			} // end for InformationalQuantity for loop
											
																			bookPacInfoList = delShipBookLineItem.getBookPacInfoList();
																			if(null!=bookPacInfoList && bookPacInfoList.size()>0){
																				boxTrackNumList = new ArrayList();
																				wgtPerBoxList 	= new ArrayList();
																				boxItemListAllPallet = new ArrayList();
																				for(int a=0; a<bookPacInfoList.size(); a++){
																					bookPacInfo = (BookPacInfo)bookPacInfoList.get(a);
																					boxItemList1 = bookPacInfo.getBoxItemList();
																					if(null!=boxItemList1 && boxItemList1.size()>0){
																						for(int b = 0;b<boxItemList1.size();b++){
																							boxItem2 = (BoxItem) boxItemList1.get(b);
																							if(null!=boxItem2){
																								boxItemListAllPallet.add(boxItem2);
																							}
																						}	
																					}
																				}
																				
																				for(int cnt17=0; cnt17<bookPacInfoList.size(); cnt17++){
																					//rowCount1=1;
																					rowCount2=1;
																					
																					bookPacInfo = (BookPacInfo)bookPacInfoList.get(cnt17);
																					pacIdentifierList 	= null;
																					pacIdentifier		= null;
																					pacIdValue			= null;
																					palNumOfBox			= 0;
																					palNumOfBook		= null;
																					boxItemList			= null;
																					boxItem				= null;
																					fulOrParBoxCount	= 0;
																					bookCountBox		= 0;
																					unitItemList		= null;
																					unitItem			= null;
																					boxUnitChars 		= null;
																					boxCharcs			= null;
																					boxHeight			= null;
																					boxHght				= 0;
																					boxLength			= null;
																					boxLngth			= 0;
																					boxWidth			= null;
																					boxWidh				= 0;
																					boxWeight			= null;
																					boxWght				= 0;
																					unitsPerCrtn		= null;
																					booksPerCrtn		= 0;
																					fullOrParFlag		= null;
																					
																					if(null!=bookPacInfo){
																						pacIdentifierList = bookPacInfo.getPacIdentifierList();
																						if(null!=pacIdentifierList && pacIdentifierList.size()>0){
																							pacIdValue	= null;
																							for(int cnt18=0; cnt18<pacIdentifierList.size(); cnt18++){
																								pacIdentifier = (PacIdentifier)pacIdentifierList.get(cnt18);
																								if(null!=pacIdentifier){
																									pacIdValue = pacIdentifier.getIdValue();
																								}
																							} //end for Package Identifier for loop
																						}
																						if("Box".equals(bookPacInfo.getPackItemCount().getPacItemCntVal().getUom())){
																							palNumOfBox = Integer.parseInt(bookPacInfo.getPackItemCount().getPacItemCntVal().getValue());
																							boxCountPerPO = boxCountPerPO + palNumOfBox;
																						}
																						if("Unit".equals(bookPacInfo.getPackQuantity().getPackQtyVal().getUom())){
																							palNumOfBook = bookPacInfo.getPackQuantity().getPackQtyVal().getValue();
																						}
																						boxItemList = bookPacInfo.getBoxItemList();
																						
																						if(null!=boxItemList && boxItemList.size()>0){
																							prevbooksPerCrtn	= 0;
																							/*prevBoxHght			= 0; 
																							prevBoxLngth 		= 0;
																							prevBoxWidh			= 0;
																							prevBoxWght			= 0;*/
																						for(int cnt19=0; cnt19<boxItemList.size(); cnt19++){
																							boxItem = (BoxItem)boxItemList.get(cnt19);
																							fulOrParBoxCount 	= 0;
																							bookCountBox	 	= 0;
																							unitItemList		= null;
																							unitItem			= null;
																							boxUnitChars 		= null;
																							boxCharcs			= null;
																							boxHeight			= null;
																							boxHght				= 0;
																							boxLength			= null;
																							boxLngth			= 0;
																							boxWidth			= null;
																							boxWidh				= 0;
																							boxWeight			= null;
																							boxWght				= 0;
																							unitsPerCrtn		= null;
																							booksPerCrtn		= 0;
																							fullOrParFlag		= null;
																							boxRef				= null;
																							trackingNumberBox	= null;
																							boxRefType			= null;
																							trackNumBoxInsert	= Boolean.FALSE;
																							isHeadBoxValid		= Boolean.FALSE;
													
																							if(null!=boxItem){
																								/*Getting tracking number pertaining to each BOX ITEM*/
																								/*if(null != senderSAN && (senderSAN.equals("179-1419") || senderSAN.equals("100-8654") || 
																										senderSAN.equals("179-6763") || senderSAN.equals("179-6801") || 
																										senderSAN.equals("179-6828") || senderSAN.equals("760-5625") ||
																										senderSAN.equals("760-5811") || senderSAN.equals("920-4865"))){*/
																									
																								// By Jyoti	
																									if(senderSAN != null && sendorSANList != null && sendorSANList.size()>0){
																										Boolean sanFlag = false;
																										for(int k=0; k<sendorSANList.size(); k++){
																											String s = (String)sendorSANList.get(k);
																											if(senderSAN.equals(s)){
																												sanFlag = true;
																												break;
																											}
																										}
																										if(sanFlag){
																									
																								if(boxItem.getBoxRefList() != null){
						
																									boxItemRefList = new ArrayList();
																									boxItemRefList = boxItem.getBoxRefList();
																										if(boxItemRefList != null && boxItemRefList.size()>0){
																											boxRef = (BoxRef)boxItemRefList.get(0);
																											
																											if(null != boxRef){
																												boxRefType = boxRef.getBoxRefType();
																												if(!"".equals(boxRefType) && "TrackingNumber".equals(boxRefType)){
																													trackingNumberBox = boxRef.getBoxRefValue();
																												}
																												
																												if(trackingNumberBox != null && trackingNumberHeader != null){
																													trackNumBoxInsert = Boolean.FALSE;
																													trackNumHeadInsert = Boolean.FALSE;
																													isHeadBoxValid = Boolean.FALSE;
																													errorId = IPixB2BConstants.ERROR_ID_142;
																													daoErrorDTO = new ErrorDTO();
																													daoErrorDTO.setErrorID(errorId+"");
																													daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(" Error For PO Number" + xmlPONumr + " in line item number "+ xmlPOLineitemNumr));
																													errorList.add(daoErrorDTO);
																													B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+trackingNumberBox+" TRACKING NUMBER EXIST FOR BOTH, PARCEL AND BOX ITEM");
																													exitBoxLoop = Boolean.TRUE;
																													correctDataFlag	= Boolean.FALSE;
																													break;
																												}else if(trackingNumberBox == null && trackingNumberHeader != null){
																													trackNumBoxInsert = Boolean.FALSE;
																													isHeadBoxValid = Boolean.FALSE;
																													trackNumHeadInsert = Boolean.TRUE;
																													correctDataFlag	= Boolean.TRUE;
																													break;
																												}else if(trackingNumberBox != null && trackingNumberHeader == null){
																													trackNumBoxInsert = Boolean.TRUE;
																													isHeadBoxValid = Boolean.TRUE;
																													trackNumHeadInsert = Boolean.FALSE;
																													correctDataFlag	= Boolean.TRUE;
																												}else if(trackingNumberBox == null && trackingNumberHeader == null){
																													trackNumBoxInsert = Boolean.FALSE;
																													isHeadBoxValid = Boolean.FALSE;
																													trackNumHeadInsert = Boolean.FALSE;
																													exitBoxLoop = Boolean.TRUE;
																													correctDataFlag	= Boolean.FALSE;
																													break;
																												}
																													
																												if(!isHeadBoxValid && !trackNumBoxInsert){
																														//isHeadBoxValid = Boolean.FALSE;
																														//trackNumBoxInsert = Boolean.FALSE;
																														correctDataFlag	= Boolean.FALSE;
																													}else{
																														if("Box".equals(boxItem.getBoxItemCount().getBoxItemCntVal().getUom())){
																															fulOrParBoxCount = Integer.parseInt(boxItem.getBoxItemCount().getBoxItemCntVal().getValue());
																														}
																														boxCharcs = boxItem.getBoxCharcs();
																														if(null!=boxCharcs){
																															unitsPerCrtn = boxCharcs.getUnitsPerCrtn();
																															if(null!=unitsPerCrtn){
																																booksPerCrtn = Integer.parseInt(unitsPerCrtn.getUnitsPerCrtnVal().getValue());
																															}
																														}
																														boxQuantity	= boxItem.getBoxQuantity();
																														if(null!=boxQuantity){
																															bookCountBox = Integer.parseInt(boxQuantity.getBoxQuantityVal().getValue());
																														}
																														if(booksPerCrtn<=0){
																															booksPerCrtn = (bookCountBox/fulOrParBoxCount);
																														}
																														BoxItemTrackNumListDTO listDTO = new BoxItemTrackNumListDTO();
																														listDTO.setTrackingNum(trackingNumberBox);
																														listDTO.setAsnId(asnId);
																														listDTO.setBooksPerCarton(booksPerCrtn);
																														listDTO.setBoxCount(fulOrParBoxCount);
																														boxTrackNumList.add(listDTO);
																														isHeadBoxValid = Boolean.TRUE;
																														correctDataFlag = Boolean.TRUE;
																													}
																												}
																											}
																										}
																									}
																								}
																								/*ENDS*/
																								if(boxItem.getBoxRefList() != null && boxItem.getBoxRefList().size()>0 && trackingNumberBox == null && trackingNumberHeader == null){
																									//correctDataFlag = Boolean.FALSE;
																									break;
																									//exitBoxLoop = Boolean.TRUE;
																								}else if(boxItem.getBoxRefList() != null && boxItem.getBoxRefList().size()>0 && trackingNumberBox != null && trackingNumberHeader != null){
																									//correctDataFlag = Boolean.FALSE;
																									break;
																									//exitBoxLoop = Boolean.TRUE;
																								}
																								
																								if("Box".equals(boxItem.getBoxItemCount().getBoxItemCntVal().getUom())){
																									fulOrParBoxCount = Integer.parseInt(boxItem.getBoxItemCount().getBoxItemCntVal().getValue());
																									if(boxItem.getBoxRefList() == null ){
																										boxCharcs = boxItem.getBoxCharcs();
																										if(null!=boxCharcs){
																											unitsPerCrtn = boxCharcs.getUnitsPerCrtn();
																											if(null!=unitsPerCrtn){
																												booksPerCrtn = Integer.parseInt(unitsPerCrtn.getUnitsPerCrtnVal().getValue());
																											}
																										}
																										boxQuantity	= boxItem.getBoxQuantity();
																										if(null!=boxQuantity){
																											bookCountBox = Integer.parseInt(boxQuantity.getBoxQuantityVal().getValue());
																										}
																										if(booksPerCrtn<=0){
																											booksPerCrtn = (bookCountBox/fulOrParBoxCount);
																										}
																										BoxItemTrackNumListDTO listDTO = new BoxItemTrackNumListDTO();
																										listDTO.setTrackingNum(trackingNumberHeader);
																										listDTO.setAsnId(asnId);
																										listDTO.setBooksPerCarton(booksPerCrtn);
																										listDTO.setBoxCount(fulOrParBoxCount);
																										boxTrackNumList.add(listDTO);
																									}
																								}
																								boxQuantity	= boxItem.getBoxQuantity();
																								if(null!=boxQuantity){
																									bookCountBox = Integer.parseInt(boxQuantity.getBoxQuantityVal().getValue());
																								}
																								unitItemList = boxItem.getUnitItemList();
																								if(null!=unitItemList && unitItemList.size()>0){
																									for(int cnt20=0; cnt20<unitItemList.size(); cnt20++){
																										if(rowCount1==1 || bookHeight<=0 || bookThickness<=0 || bookWidth<=0 || bookWeight<=0){
																											unitItem = (UnitItem)unitItemList.get(cnt20);
																											if(null!=unitItem){
																												boxUnitChars = unitItem.getBoxUnitChars();
																												if(null!=boxUnitChars){
																													unitHeight = boxUnitChars.getUnitHeight();
																													if(null!=unitHeight){
																														bookHeight = Float.parseFloat(unitHeight.getUnitHghtVal().getValue());
																													}
																													unitThickness = boxUnitChars.getUnitThickness();
																													if(null!=unitThickness){
																														bookThickness = Float.parseFloat(unitThickness.getUnitThicknessVal().getValue());
																													}
																													unitWidth = boxUnitChars.getUnitWidth();
																													if(null!=unitWidth){
																														bookWidth = Float.parseFloat(unitWidth.getUnitWidthVal().getValue());
																													}
																													unitWeight = boxUnitChars.getUnitWeight();
																													if(null!=unitWeight){
																														bookWeight = Float.parseFloat(unitWeight.getUnitWeightVal().getValue());
																													}
																												}
																											}
																										}
																										rowCount1++;
																									} // End of unitItemList for loop
																								}
																								boxCharcs = boxItem.getBoxCharcs();
																								if(null!=boxCharcs){
																									//if(rowCount3==1){
																										boxHeight = boxCharcs.getBoxHeight();
																										if(null!=boxHeight){
																											boxHght = Float.parseFloat(boxHeight.getBoxHeightVal().getValue());
																										}
																										boxLength = boxCharcs.getBoxLength();
																										if(null!=boxLength){
																											boxLngth = Float.parseFloat(boxLength.getBoxLengthVal().getValue());
																										}
																										boxWidth = boxCharcs.getBoxWidth();
																										if(null!=boxWidth){
																											boxWidh = Float.parseFloat(boxWidth.getBoxWidthVal().getValue());
																										}
																									//}
																									boxWeight = boxCharcs.getBoxWeight();
																									if(null!=boxWeight){
																										boxWght = Float.parseFloat(boxWeight.getBoxWeightVal().getValue());
																									}
																									unitsPerCrtn = boxCharcs.getUnitsPerCrtn();
																									if(null!=unitsPerCrtn){
																										booksPerCrtn = Integer.parseInt(unitsPerCrtn.getUnitsPerCrtnVal().getValue());
																									}
																								}
																								
																								if(booksPerCrtn<=0){
																									booksPerCrtn = (bookCountBox/fulOrParBoxCount);
																								}
																								if(booksPerCrtn<=0){
																									errorId = IPixB2BConstants.ERROR_ID_125;
																									daoErrorDTO = new ErrorDTO();
																									daoErrorDTO.setErrorID(errorId+"");
																									daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																									errorList.add(daoErrorDTO);
																									correctDataFlag	= Boolean.FALSE;
																									B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+booksPerCrtn+" is either null(0) or invalid");
																								}
																								/*if(fulOrParBoxCount>1 && (boxHght<=0 || boxLngth<=0 || boxWidh<=0 || boxWght<=0)){
																									errorId = IPixB2BConstants.ERROR_ID_127;
																									daoErrorDTO = new ErrorDTO();
																									daoErrorDTO.setErrorID(errorId+"");
																									daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																									errorList.add(daoErrorDTO);
																									correctDataFlag	= Boolean.FALSE;
																									B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - Box Dimensions are either null(0) or invalid");
																								}else if(fulOrParBoxCount>1){
																									boxDimCount++;
																									if(boxDimCount>1 && prevBoxHght!=boxHght && prevBoxLngth!=boxLngth && 
																											prevBoxWidh!=boxWidh && prevBoxWght!=boxWght){
																										errorId = IPixB2BConstants.ERROR_ID_127;
																										daoErrorDTO = new ErrorDTO();
																										daoErrorDTO.setErrorID(errorId+"");
																										daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																										errorList.add(daoErrorDTO);
																										correctDataFlag	= Boolean.FALSE;
																										B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - Box Dimensions are either null(0) or invalid");
																									}
																									prevBoxHght		= boxHght; 
																									prevBoxLngth 	= boxLngth;
																									prevBoxWidh		= boxWidh;
																									prevBoxWght		= boxWght;
																								}
																								if(bookHeight<=0 || bookThickness<=0 || bookWidth<=0 || bookWeight<=0){
																									errorId = IPixB2BConstants.ERROR_ID_126;
																									daoErrorDTO = new ErrorDTO();
																									daoErrorDTO.setErrorID(errorId+"");
																									daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																									errorList.add(daoErrorDTO);
																									correctDataFlag	= Boolean.FALSE;
																									B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - Unit Dimensions are either null(0) or invalid");
																								}*/
																								
																								if(palNumOfBox==1){
																									fullOrParFlag = IPixB2BConstants.BOX_PACKING_FLAG_P;
																								}else{
																									fullOrParFlag = IPixB2BConstants.BOX_PACKING_FLAG_F;
																									if(rowCount2>1 && booksPerCrtn==prevbooksPerCrtn){
																										fullOrParFlag = IPixB2BConstants.BOX_PACKING_FLAG_F;
																									}else if(rowCount2>1 && booksPerCrtn>prevbooksPerCrtn){
																										fullOrParFlag = IPixB2BConstants.BOX_PACKING_FLAG_F;
														
																										sqlQuery = qry_update_pix_asn_pallet_group;
																										prepStmt1 = dbCon.prepareStatement(sqlQuery);
																										prepStmt1.clearParameters();
																										prepStmt1.setString(IPixB2BConstants.ONE,IPixB2BConstants.BOX_PACKING_FLAG_P);
																										prepStmt1.setString(IPixB2BConstants.TWO,asnId);
																										prepStmt1.setString(IPixB2BConstants.THREE,delShipLineItemNum);
																										prepStmt1.setString(IPixB2BConstants.FOUR,pacIdValue);
																										prepStmt1.setInt(IPixB2BConstants.FIVE,cnt19);
																										prepStmt1.setString(IPixB2BConstants.SIX,IPixB2BConstants.BOX_PACKING_FLAG_F);
																										qryParams = IPixB2BConstants.BOX_PACKING_FLAG_P+","+asnId+","+delShipLineItemNum+","
																											+pacIdValue+","+cnt19+","+IPixB2BConstants.BOX_PACKING_FLAG_F;
																										recsNum = prepStmt1.executeUpdate();
																										recsCount = recsCount + recsNum;
																										
																										DBUtils.close(prepStmt1);
																									}else if (rowCount2>1 && booksPerCrtn<prevbooksPerCrtn){
																										fullOrParFlag = IPixB2BConstants.BOX_PACKING_FLAG_P;
																									}
																								}
																								prevbooksPerCrtn = booksPerCrtn;
																								rowCount2++;
																								//rowCount3++;
																								
																								
																								if(correctDataFlag){
																									sqlQuery = qry_ins_pix_asn_pallet_group;
																									prepStmt1 = dbCon.prepareStatement(sqlQuery);
																									prepStmt1.clearParameters();
																									prepStmt1.setString(IPixB2BConstants.ONE,asnId);
																									prepStmt1.setString(IPixB2BConstants.TWO,delShipLineItemNum);
																									prepStmt1.setString(IPixB2BConstants.THREE,pacIdValue);
																									prepStmt1.setInt(IPixB2BConstants.FOUR,(cnt19+1));
																									prepStmt1.setInt(IPixB2BConstants.FIVE,fulOrParBoxCount);
																									prepStmt1.setInt(IPixB2BConstants.SIX,bookCountBox);
																									prepStmt1.setInt(IPixB2BConstants.SEVEN,booksPerCrtn);
																									prepStmt1.setString(IPixB2BConstants.EIGHT,fullOrParFlag);
																									prepStmt1.setString(IPixB2BConstants.NINE,null);
																									prepStmt1.setFloat(IPixB2BConstants.TEN,bookHeight);
																									prepStmt1.setFloat(IPixB2BConstants.ELEVEN,bookThickness);
																									prepStmt1.setFloat(IPixB2BConstants.TWELVE,bookWidth);
																									prepStmt1.setFloat(IPixB2BConstants.THIRTEEN,bookWeight);
																									prepStmt1.setFloat(IPixB2BConstants.FOURTEEN,boxHght);
																									prepStmt1.setFloat(IPixB2BConstants.FIFTEEN,boxLngth);
																									prepStmt1.setFloat(IPixB2BConstants.SIXTEEN,boxWidh);    
																									prepStmt1.setFloat(IPixB2BConstants.SEVENTEEN,boxWght);
																									qryParams = asnId+","+delShipLineItemNum+","+pacIdValue+","+cnt19+
																										","+fulOrParBoxCount+","+bookCountBox+","+fullOrParFlag+","+null+
																										","+bookHeight+","+bookThickness+","+bookWidth+","+bookWeight+
																										","+boxHght+","+boxLngth+","+boxWidh+","+boxWght;
																									recsNum = prepStmt1.executeUpdate();
																									recsCount = recsCount + recsNum;
																									
																									DBUtils.close(prepStmt1);
																								}
																							}
																																						
																							/*Calculating gross weight for each box item for truck shipment*/
																							int totalBoxAndBooks = 0;
																							int BoxAndBook = 0;
																							totBookAndBox		= 0;
																							wgtPerBoxItem		= 0;
																							BoxCharcs boxCharcs1 = null;
																							UnitsPerCrtn unitsPerCrtn1	= null;
																							BoxQuantity boxQuantity1		= null;
																							int fulOrParBoxCount1 = 0;
																							for(int a=0; a<boxItemListAllPallet.size(); a++){
																								int bookCountBox1 = 0;
																								int booksPerCrtn1 = 0;
																								int boxCount = 0;
																								BoxItem boxItem1	= null;
																								boxItem1 = (BoxItem)boxItemListAllPallet.get(a);
																								fulOrParBoxCount1 = Integer.parseInt(boxItem1.getBoxItemCount().getBoxItemCntVal().getValue());
																								boxCount = Integer.parseInt(boxItem1.getBoxItemCount().getBoxItemCntVal().getValue());
																								boxCharcs1 = boxItem1.getBoxCharcs();
																								if(null!=boxCharcs1){
																									unitsPerCrtn1 = boxCharcs1.getUnitsPerCrtn();
																									if(null!=unitsPerCrtn1){
																										booksPerCrtn1 = Integer.parseInt(unitsPerCrtn1.getUnitsPerCrtnVal().getValue());
																									}
																								}
																								
																								boxQuantity1	= boxItem1.getBoxQuantity();
																								if(null!=boxQuantity1){
																									bookCountBox1 = Integer.parseInt(boxQuantity1.getBoxQuantityVal().getValue());
																								}
																								if(booksPerCrtn1<=0){
																									booksPerCrtn1 = (bookCountBox1/fulOrParBoxCount1);
																								}
																								BoxAndBook = boxCount*booksPerCrtn1;
																								totalBoxAndBooks = BoxAndBook + totalBoxAndBooks;
																							}
																							
																							totBookAndBox = fulOrParBoxCount*booksPerCrtn;
																							
																							//int grsWgt = Integer.parseInt(grossWgt);
																							Float grsWgt = new Float(grossWgt);
																							float wgt = (float)grsWgt/(float)totalBoxAndBooks;
																							wgtPerBoxItem = wgt *(totBookAndBox);
																							wgtPerBoxList.add(wgtPerBoxItem);
																							/*ends*/	
																						} // end for BoxItem for Loop
																						
																						if((boxItem.getBoxRefList() != null && trackingNumberBox == null) && trackingNumberHeader == null){
																							//correctDataFlag = Boolean.FALSE;
																							break;
																						}else if((boxItem.getBoxRefList() != null && trackingNumberBox != null) && trackingNumberHeader != null){
																							//correctDataFlag = Boolean.FALSE;
																							break;
																						}
																							/*Insert statement for Tracking Number entry per Box Item*/
																							if(correctDataFlag){
																								if(isHeadBoxValid){
																									if(boxTrackNumList != null && boxTrackNumList.size()>0){
																										for(int i =0;i<boxTrackNumList.size();i++){
																											float wgtPerBox = 0;
																											boxitemDTO = new BoxItemTrackNumListDTO();
																											boxitemDTO = (BoxItemTrackNumListDTO)boxTrackNumList.get(i);
																											wgtPerBox = (Float)wgtPerBoxList.get(i);
																											sqlQuery = qry_ins_pix_asn_tracking_number;
																											prepStmt1 = dbCon.prepareStatement(sqlQuery);
																											prepStmt1.clearParameters();
																											prepStmt1.setString(IPixB2BConstants.ONE,boxitemDTO.getAsnId());
																											prepStmt1.setString(IPixB2BConstants.TWO,boxitemDTO.getTrackingNum());
																											prepStmt1.setInt(IPixB2BConstants.THREE,boxitemDTO.getBoxCount());
																											prepStmt1.setInt(IPixB2BConstants.FOUR,boxitemDTO.getBooksPerCarton());
																											prepStmt1.setFloat(IPixB2BConstants.FIVE,wgtPerBox);
																											prepStmt1.setString(IPixB2BConstants.SIX,delShipLineItemNum);
																											qryParams = asnId+","+trackingNumberBox+","+booksPerCrtn+","+fulOrParBoxCount+","+wgtPerBoxItem+","+xmlPOLineitemNumr;
																											prepStmt1.executeUpdate();
																											
																											DBUtils.close(prepStmt1);
																										}
																									}
																								}
																							}
																							else if(trackingNumberHeader != null && trackingNumberBox != null){
																								errorId = IPixB2BConstants.ERROR_ID_142;
																								daoErrorDTO = new ErrorDTO();
																								daoErrorDTO.setErrorID(errorId+"");
																								daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(" Error For PO Number" + xmlPONumr + " in line item number "+ xmlPOLineitemNumr));
																								errorList.add(daoErrorDTO);
																								B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - "+trackingNumberBox+" TRACKING NUMBER EXIST FOR BOTH, PARCEL AND BOX ITEM");
																								//isHeadBoxValid = Boolean.FALSE;
																								//exitPoLoop = Boolean.TRUE;
																								correctDataFlag	= Boolean.FALSE;
																								break;
																							}else if(trackingNumberHeader == null && trackingNumberBox == null){
																								errorId = IPixB2BConstants.ERROR_ID_143;
																								daoErrorDTO = new ErrorDTO();
																								daoErrorDTO.setErrorID(errorId+"");
																								daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(" Error For PO Number" + xmlPONumr + " in line item number "+ xmlPOLineitemNumr));
																								errorList.add(daoErrorDTO);
																								B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() -  TRACKING NUMBER DOES NOT EXIST FOR BOTH, PARCEL AND BOX ITEM");
																								//exitPoLoop = Boolean.TRUE;
																								correctDataFlag	= Boolean.FALSE;
																								break;
																							}
																						}
																						

																						
																						if(correctDataFlag){
																							sqlQuery = qry_ins_pix_asn_pallet;
																							prepStmt1 = dbCon.prepareStatement(sqlQuery);
																							prepStmt1.clearParameters();
																							prepStmt1.setString(IPixB2BConstants.ONE,asnId);
																							prepStmt1.setString(IPixB2BConstants.TWO,delShipLineItemNum);
																							prepStmt1.setString(IPixB2BConstants.THREE,pacIdValue);
																							prepStmt1.setInt(IPixB2BConstants.FOUR,palNumOfBox);
																							prepStmt1.setString(IPixB2BConstants.FIVE,palNumOfBook);
																							qryParams = asnId+","+delShipLineItemNum+","+pacIdValue+","+palNumOfBox+","+palNumOfBook;
																							recsNum = prepStmt1.executeUpdate();
																							recsCount = recsCount + recsNum;
																							
																							DBUtils.close(prepStmt1);
																						}
																						
																					}
																				} // End of bookPacInfoList for loop
																			}
																		}else{
																			B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - dbPOId = "+dbPOId+" , dbPOVersion = "+dbPOVersion+" FOR PO NUMBER = "+xmlPONumr);
																		}
																	}
																	/*if(bookHeight<=0 || bookThickness<=0 || bookWidth<=0 || bookWeight<=0){
																		errorId = IPixB2BConstants.ERROR_ID_126;
																		daoErrorDTO = new ErrorDTO();
																		daoErrorDTO.setErrorID(errorId+"");
																		daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId));
																		errorList.add(daoErrorDTO);
																		correctDataFlag	= Boolean.FALSE;
																		B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - Unit Dimensions are either null(0) or invalid");
																	}*/
																	
																	if(correctDataFlag){
																		sqlQuery = qry_ins_pix_asn_po_line;
																		prepStmt1 = dbCon.prepareStatement(sqlQuery);
																		prepStmt1.clearParameters();
																		prepStmt1.setString(IPixB2BConstants.ONE,asnId);
																		prepStmt1.setString(IPixB2BConstants.TWO,delShipLineItemNum);
																		prepStmt1.setString(IPixB2BConstants.THREE,specificationNum);
																		prepStmt1.setString(IPixB2BConstants.FOUR,dbPOId);	 
																		prepStmt1.setString(IPixB2BConstants.FIVE,xmlPOLineitemNumr);
																		prepStmt1.setString(IPixB2BConstants.SIX,poIssueDt);
																		prepStmt1.setString(IPixB2BConstants.SEVEN,poTransId);
																		prepStmt1.setString(IPixB2BConstants.EIGHT,isbn10);
																		prepStmt1.setString(IPixB2BConstants.NINE,isbn13); 
																		/*prepStmt1.setFloat(IPixB2BConstants.TEN,bookHeight);
																		prepStmt1.setFloat(IPixB2BConstants.ELEVEN,bookThickness);
																		prepStmt1.setFloat(IPixB2BConstants.TWELVE,bookWidth);
																		prepStmt1.setFloat(IPixB2BConstants.THIRTEEN,bookWeight);
																		prepStmt1.setFloat(IPixB2BConstants.FOURTEEN,boxHght);
																		prepStmt1.setFloat(IPixB2BConstants.FIFTEEN,boxLngth);
																		prepStmt1.setFloat(IPixB2BConstants.SIXTEEN,boxWidh);    
																		prepStmt1.setFloat(IPixB2BConstants.SEVENTEEN,boxWght);*/
																		prepStmt1.setString(IPixB2BConstants.TEN,printingNumber);
																		prepStmt1.setString(IPixB2BConstants.ELEVEN,numOfPallets);
																		prepStmt1.setInt(IPixB2BConstants.TWELVE,totNumOfBox);
																		prepStmt1.setInt(IPixB2BConstants.THIRTEEN,totNumOfBook);
																		prepStmt1.setString(IPixB2BConstants.FOURTEEN,grossWgt);
																		prepStmt1.setString(IPixB2BConstants.FIFTEEN,grossUomId);
																		prepStmt1.setString(IPixB2BConstants.SIXTEEN,IPixB2BConstants.JavaB2B);
																		prepStmt1.setString(IPixB2BConstants.SEVENTEEN,IPixB2BConstants.JavaB2B);
																		prepStmt1.setString(IPixB2BConstants.EIGHTEEN,invoiceNumber);
																		prepStmt1.setString(IPixB2BConstants.NINETEEN,customerOrderNumber);
																		prepStmt1.setString(IPixB2BConstants.TWENTY,isDeskCopy);
																		
																		qryParams = asnId+","+delShipLineItemNum+","+specificationNum+","+dbPOId+","+xmlPOLineitemNumr
																			+","+poIssueDt+","+poTransId+","+isbn10+","+isbn13+","+printingNumber+","+numOfPallets
																			+","+totNumOfBox+","+totNumOfBook+","+grossWgt+","+grossUom+","+IPixB2BConstants.JavaB2B+","+invoiceNumber+","+customerOrderNumber+", "+isDeskCopy;
																		recsNum = prepStmt1.executeUpdate();
																		recsCount = recsCount + recsNum;
																		
																		DBUtils.close(prepStmt1);
																	}
																	if(correctDataFlag){
																		correctLineItemFlag = Boolean.TRUE;
																	}
																		/*svpt1 = dbCon.setSavepoint("SAVEPOINT_1");
																	}else{
																		dbCon.rollback(svpt1);
																	}*/
																}
															}
														}
														
														if(correctDataFlag){
															if(trackNumHeadInsert){
																for(int i =0;i<boxTrackNumList.size();i++){
																	float wgtPerBox = 0;
																	boxitemDTO = new BoxItemTrackNumListDTO();
																	boxitemDTO = (BoxItemTrackNumListDTO)boxTrackNumList.get(i);
																	wgtPerBox = (Float)wgtPerBoxList.get(i);
																	sqlQuery = qry_ins_pix_asn_tracking_number;
																	prepStmt1 = dbCon.prepareStatement(sqlQuery);
																	prepStmt1.clearParameters();
																	prepStmt1.setString(IPixB2BConstants.ONE,boxitemDTO.getAsnId());
																	prepStmt1.setString(IPixB2BConstants.TWO,boxitemDTO.getTrackingNum());
																	prepStmt1.setInt(IPixB2BConstants.THREE,boxitemDTO.getBoxCount());
																	prepStmt1.setInt(IPixB2BConstants.FOUR,boxitemDTO.getBooksPerCarton());
																	prepStmt1.setFloat(IPixB2BConstants.FIVE,wgtPerBox);
																	prepStmt1.setString(IPixB2BConstants.SIX,delShipLineItemNum);
																	qryParams = asnId+","+trackingNumberBox+","+booksPerCrtn+","+fulOrParBoxCount+","+wgtPerBoxItem+","+xmlPOLineitemNumr;
																	prepStmt1.executeUpdate();
																	
																	DBUtils.close(prepStmt1);
																}
															}
														}
													
														/*ENDS*/
													}
													/*if(firstInvNum != null  && invoiceNumList != null && invoiceNumList.contains(firstInvNum)){
														errorId = IPixB2BConstants.ERROR_ID_129;
														daoErrorDTO = new ErrorDTO();
														daoErrorDTO.setErrorID(errorId+"");
														daoErrorDTO.setErrorDescription(b2bHelper.getErrorDesp(errorId).concat(" : ").concat(" Error For PO Number" + xmlPONumr));
														errorList.add(daoErrorDTO);
														B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - MORE THAN ONE INVOICE NUMBER EXISTS.");
													}*/
													
													// end of for loop for delShipBookLineItemList
													
													/*if(trackingNumberBox != null && trackingNumberHeader != null){
														correctDataFlag = Boolean.FALSE;
														break;
													}*/
												}
											}
										} //end of for loop at line level
										/*if(correctDataFlag){
											strPOId = sbPOId.substring(0, sbPOId.length()-1);
											if(null!=errorList && errorList.size()>0){
												transStatusDTO.setStatusREAD("RE");
												transStatusDTO.setCompletePath(inDirArchiveXmlInvalid);
											}
											dbTransIdNext = updateInboundTransStatus(dbCon,transStatusDTO,senderSAN,receiverSAN, 
													strPOId,asnId,IPixB2BConstants.PO_ID,IPixB2BConstants.ASN_ID,delMesNumb,xmlASNDate, xmlASNTransId);
											if(dbTransIdNext>0){
												String transID = Long.toString(dbTransIdNext);
												transStatusDTO.setTransID(transID);
												dbCon.commit();
											}else{
												dbCon.rollback();
											}
										}else{
											dbCon.rollback();
										}*/
										
										if(correctLineItemFlag){
											transRefId1 = sbPOId.substring(0, sbPOId.length()-1);
											transRefLabel1 = IPixB2BConstants.PO_ID;
											transRefId2 = asnId;
											transRefLabel2 = IPixB2BConstants.ASN_ID;
										}else{
											dbCon.rollback();
										}
										if(null!=errorList && errorList.size()>0){
											transStatusDTO.setStatusREAD("RE");
											transStatusDTO.setCompletePath(inDirArchiveXmlInvalid);
										}
										dbTransIdNext = updateInboundTransStatus(dbCon,transStatusDTO,senderSAN,receiverSAN, 
												transRefId1,transRefId2,transRefLabel1,transRefLabel2,delMesNumb,xmlASNDate, xmlASNTransId);
										if(dbTransIdNext>0){
											String transID = Long.toString(dbTransIdNext);
											transStatusDTO.setTransID(transID);
											dbCon.commit();
										}else{
											// Serialize the state so that it can be persisted on the next run.still not Implemented.
											// The scenario can rarely happen so will handle it later.
										}
										
									}else{
										B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - xmlDMBookShipList is null or blank");
									}
								}else{
									B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - xmlASNTransId is null or blank");
								}
							}else{
								B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - xmlASNRefList is null or blank");
							}
						}else{
							B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails()- xmlASNBookHd is null");
						}
					}else{
						B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - pneDTO is null");
			}
 			B2BLogger.info("AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() - recsCount = "+recsCount+", dbTransIdNext = "+dbTransIdNext);
			if(dbTransIdNext > 0)
				asnStatus = IPixB2BConstants.flag_Y;			
			
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.storeAdvanceShipmentNoticeDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			asnStatus = StringUtils.getStackTrace(e);
			if(asnStatus != null && asnStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				asnStatus = asnStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("SQLException :: ",e1);
			}		
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			asnStatus = StringUtils.getStackTrace(e);
			if(asnStatus != null && asnStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				asnStatus = asnStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e);
			}
		} finally{			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);			
			DBUtils.close(dbCon);
			
			sqlQuery				= null;
			qryParams				= null;
			recsCount				= 0;
			recsNum					= 0;
			transmissionInfoList 	= null;
			b2bHelper				= null;
			hmSAN					= null;		
			senderSAN				= null;		
			receiverSAN				= null;
			dbPOId					= null;
			dbPOVersion				= null;
			hmPoIdpoVersion			= null;
			dbTransIdNext			= -1;
			delMesStatusType		= null;
			xmlASNBookHd   			= null;
			delMesNumb 				= null;
			deliveryLegList			= null;
			asnDate 				= null;
			xmlASNDate				= null;
			deliveryLeg				= null;
			delOriginDate 			= null;
			xmlDelDate				= null;
			xmlASNRefList			= null;
			asnReference			= null;
			xmlASNTransId			= null;
			xmlDMBookShipList		= null;
			delMesBookShip			= null;
			delShipBookLineItemList = null;
			delShipBookLineItem 	= null;
			xmlPOLineitemNumr		= null;
			poInformation 			= null;
			xmlPONumr				= null;
			poIssueDt				= null;
			delShipLineItemNum		= null;
			poIssueDate 			= null;
			poReferenceList			= null;
			poReference  			= null;
			specificationNum		= null;
			isbn10					= null;
			isbn13					= null;
			printingNumber			= null;
			poTransId				= null;
			lineQty 				= null;
			numOfPallets 			= null;
			infoQuantityList		= null;
			infoQuantity			= null;
			totNumOfBox	 			= 0;
			totNumOfBook	 		= 0;
			grossWgt	 			= null;
			grossUom	 			= null;
			grossUomId				= null;
			bookPacInfoList			= null;
			bookPacInfo				= null;
			pacIdentifierList		= null;
			pacIdentifier			= null;
			pacIdValue				= null;
			palNumOfBox	 			= 0;
			palNumOfBook	 		= null;
			boxItemList				= null;
			boxItem					= null;
			fulOrParBoxCount		= 0;
			boxQuantity				= null;
			bookCountBox			= 0;
			unitItemList			= null;
			unitItem				= null;
			boxCharcs				= null;
			boxUnitChars 			= null;
			unitHeight 				= null;
			bookHeight				= 0;
			unitThickness 			= null;
			bookThickness			= 0;
			unitWidth 				= null;
			bookWidth				= 0;
			unitWeight 				= null;
			bookWeight				= 0;
			boxHeight 				= null;
			boxHght					= 0;
			boxLength 				= null;
			boxLngth				= 0;
			boxWidth 				= null;
			boxWidh					= 0;
			boxWeight 				= null;
			boxWght					= 0;
			unitsPerCrtn			= null;
			booksPerCrtn			= 0;
			prevbooksPerCrtn		= 0;
			fullOrParFlag			= null;
			tqBookUomId				= null;
			tqWtUomId				= null;
			correctLineItemFlag		= Boolean.FALSE;
			asnId					= null;
			asnStatusId				= null;
			delLegSeqNumber			= null;
			locPartyId 				= null;
			locationPartyIdList		= null;
			locationSAN				= null;
			carParty 				= null;
			carPartyIdList			= null;
			carPartyId				= null;
			carPartyCode			= null;
			name1					= null;
			naFirstName				= null;
			naLastName				= null;
			carPartyCCList 			= null;
			carPartyCC	 			= null;
			ccName					= null;
			ccFirstName 			= null;
			ccLastName 				= null;
			buyPartyIdList			= null;
			buyPartyId				= null;
			buyerSAN				= null;
			buyerName1				= null;
			buyerAdd1				= null;
			buyerCity				= null;
			buyerState				= null;
			buyerPostCode			= null;
			buyerCountCode			= null;
			buyPartyCCList 			= null;
			buyPartyCC				= null;
			buyContFirstName		= null;
			buyContLastName			= null;
			buyPhone				= null;
			buyMobile				= null;
			buyEmail				= null;
			buyFax					= null;
			supplierPartyIdList		= null;
			suppPartyId				= null;
			supSAN					= null;
			supName1				= null;
			supAdd1					= null;
			supCity					= null;
			supState				= null;
			supPostCode				= null;
			supCountCode			= null;
			suppPartyCCList 		= null;
			supPartyCC 				= null;
			supContFirstName		= null;
			supContLastName			= null;
			supPhone				= null;
			supMobile				= null;
			supEmail				= null;
			supFax					= null;
			shipPartyIdList			= null;
			shipPartyId 			= null;
			shipSAN					= null;
			shipToName				= null;
			shipName1				= null;
			shipAdd1				= null;
			shipAdd2				= null;
			shipAdd3				= null;
			shipAdd4				= null;
			shipCity				= null;
			shipState				= null;
			shipPostCode			= null;
			shipCountCode			= null;
			shipComContactList 		= null;
			shipComContact			= null;
			shipContFirstName		= null;
			shipContLastName		= null;
			shipPhone				= null;
			shipMobile				= null;
			shipEmail				= null;
			shipFax					= null;
			delShipmentId			= null;
			infoTotalQtyList		= null;
			tqUom					= null;
			tqBookValue				= null;
			tqWtValue 				= null;
			tqBoxValue 				= null;
			tqPalletValue			= null;
			sbPOId 					= null;
			//strPOId				= null;
			rowCount1				= -1;
			rowCount2				= -1;
			//rowCount3				= -1;
			transRefId1				= null;
			transRefLabel1			= null;	
			transRefId2				= null;
			transRefLabel2			= null;
		}
		return asnStatus;
	}

	/**
	 * This method returns the UOM id equivalent to each OUM value.
	 * @param dbCon
	 * @param tqUom
	 * @return String
	 * @throws SQLException
	 * @throws B2BException
	 */
	private String getUomId(Connection dbCon, String tqUom) throws SQLException, B2BException{
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		String sqlQuery				= null;
		String qryParams			= null;
		String uomId				= null;
		
		try {
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.getUomId() method ENTERED *******");
			
			sqlQuery = qry_sel_uom_id_;
			prepStmt1 = dbCon.prepareStatement(sqlQuery);
			prepStmt1.clearParameters();
			prepStmt1.setString(IPixB2BConstants.ONE,tqUom);
			qryParams = tqUom;
			resultSet1 = prepStmt1.executeQuery();
			while(resultSet1.next())
				uomId = resultSet1.getString("UOM_ID");
			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.getUomId() method EXIT *******");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		}
		return uomId;
	}

	/**
	 * This method inserts the Party contact details into the database.
	 * @param dbCon
	 * @param asnId
	 * @param partyLineNo
	 * @param contactNum
	 * @param contFirstName
	 * @param contLastName
	 * @param phone
	 * @param mobile
	 * @param fax
	 * @param email
	 * @return integer
	 * @throws B2BException
	 */
	private int insertAsnPartyContact(Connection dbCon, String asnId,
			String partyLineNo, int contactNum, String contFirstName,
			String contLastName, String phone, String mobile,
			String fax, String email) throws B2BException{

		PreparedStatement prepStmt1	= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		
		try {
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.insertAsnPartyContact() method ENTERED *******");
			
			sqlQuery = qry_ins_pix_asn_party_contact;
			prepStmt1 = dbCon.prepareStatement(sqlQuery);
			prepStmt1.clearParameters();
			prepStmt1.setString(IPixB2BConstants.ONE,asnId);
			prepStmt1.setString(IPixB2BConstants.TWO,partyLineNo);
			prepStmt1.setInt(IPixB2BConstants.THREE,contactNum);
			prepStmt1.setString(IPixB2BConstants.FOUR,contFirstName);
			prepStmt1.setString(IPixB2BConstants.FIVE,contLastName);
			prepStmt1.setString(IPixB2BConstants.SIX,phone);
			prepStmt1.setString(IPixB2BConstants.SEVEN,mobile);
			prepStmt1.setString(IPixB2BConstants.EIGHT,fax);
			prepStmt1.setString(IPixB2BConstants.NINE,email);
			qryParams = asnId+","+partyLineNo+","+contactNum+","+contFirstName+","+contLastName
				+","+phone+","+mobile+","+fax+","+email;
			recsCount = prepStmt1.executeUpdate();
			
			DBUtils.close(prepStmt1);
			
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.insertAsnPartyContact() method EXIT *******");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		}finally{
			DBUtils.close(prepStmt1);
			sqlQuery = null;
			qryParams = null;
		}
		return recsCount;
	}

	/**
	 * This method inserts the Party Name/Address detail into the database.
	 * @param dbCon
	 * @param asnId
	 * @param partyLineNo
	 * @param san
	 * @param partyType
	 * @param name1
	 * @param address1
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param countCode
	 * @return integer
	 * @throws B2BException
	 */
	private int insertAsnPartyDetails(Connection dbCon, String asnId,
			String partyLineNo, String san, String partyType,
			String name1, String address1, String city,
			String state, String postalCode, String countCode) throws B2BException{

		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		String sqlQuery				= null;
		String qryParams			= null;
		String countryCode			= null;
		int recsCount				= 0;
		try {
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.insertAsnPartyDetails() method ENTERED *******");
			
			sqlQuery = qry_sel_cc;
			prepStmt1 = dbCon.prepareStatement(sqlQuery);
			prepStmt1.clearParameters();
			prepStmt1.setString(IPixB2BConstants.ONE,countCode);
			prepStmt1.setString(IPixB2BConstants.TWO,countCode);
			qryParams = countCode;
			resultSet1 = prepStmt1.executeQuery();
			while(resultSet1.next())
				countryCode = resultSet1.getString("COUNTRY_CODE");
			
			DBUtils.close(resultSet1);
			DBUtils.close(prepStmt1);
			
			sqlQuery = qry_ins_pix_asn_party;
			prepStmt1 = dbCon.prepareStatement(sqlQuery);
			prepStmt1.clearParameters();
			prepStmt1.setString(IPixB2BConstants.ONE,asnId);
			prepStmt1.setString(IPixB2BConstants.TWO,partyLineNo);
			prepStmt1.setString(IPixB2BConstants.THREE,san);
			prepStmt1.setString(IPixB2BConstants.FOUR,partyType);
			prepStmt1.setString(IPixB2BConstants.FIVE,name1);
			prepStmt1.setString(IPixB2BConstants.SIX,address1);
			prepStmt1.setString(IPixB2BConstants.SEVEN,city);
			prepStmt1.setString(IPixB2BConstants.EIGHT,state);
			prepStmt1.setString(IPixB2BConstants.NINE,postalCode);
			prepStmt1.setString(IPixB2BConstants.TEN,countryCode);
			prepStmt1.setString(IPixB2BConstants.ELEVEN,null);
			qryParams = asnId+","+partyLineNo+","+san+","+partyType+","+name1
				+","+address1+","+city+","+state+","+postalCode+","+countCode+","+null;
			recsCount = prepStmt1.executeUpdate();

			DBUtils.close(prepStmt1);
			
			B2BLogger.debug("******* AdvanceShipmentNoticeDAOImpl.insertAsnPartyDetails() method EXIT *******");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		}finally{
			DBUtils.close(resultSet1);
			DBUtils.close(prepStmt1);
			sqlQuery 	= null;
			qryParams 	= null;
			countryCode = null;
		}
		return recsCount;
	}
}