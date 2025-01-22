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
 * Title		: 	PurchaseOrderDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 07 Oct, 2009	Initial version
 * 2.0		Yogesh Tyagi 	16 Nov, 2009	Code Modified for logic change
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao.EnvelopeDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.BookClassification;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.BookManufacturing;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.BookSubClassification;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.BuyerParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.ComponentDueDate;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.Currency;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.GLAccount;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQtyValue;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POHeader;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POIssueDate;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POLineItem;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POSummary;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PPUValue;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PreviousVendorNameAddress;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PreviousVendorPlant;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PriceDetails;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PricePerUnit;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.Product;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.ProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PurchaseOrderDTO;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCCommonContact;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCDate;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCNameAddress;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCQtyValue;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SCSupplierParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.ShipToCharacteristics;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.ShipToParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SummaryCurrency;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SuppliedComponentInfo;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SupplierParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.TermsConditions;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.TotalAmount;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.TotalQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.ValueQty;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.OtherParty;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.OtherPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyCommonContact;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.Date;
import com.pearson.pixb2b.vendor.transaction.shared.dto.DateOther;
import com.pearson.pixb2b.vendor.transaction.shared.dto.LineDateOther;
import com.pearson.pixb2b.vendor.transaction.shared.dto.LineShipDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyCommonContact;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyPartyIdentifier;
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
 * PurchaseOrderDAOImpl is an implementation class to communicate with 
 * the database and get the PurchaseOrder transaction data.
 * 
 * @author Abhilasha Nigam, Yogesh Tyagi
 */
public class PurchaseOrderDAOImpl extends EnvelopeDAOImpl implements IPurchaseOrderDAO{
	
	private static final String qry_generate_po_list_proc = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_PO_LIST_PROC(?,?)}";
	
	private static final String qry_ckeck_arp_status="SELECT DECODE ((CASE WHEN (SELECT COUNT(*) FROM " +IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_HEADER PPH,"+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_BOOK_SPEC_PRESS PBSP"
		+" WHERE PPH.PO_ID=PPLS.PO_ID AND PPH.PO_VERSION=PPLS.PO_VERSION"
		+" AND PPH.SPEC_ID=PBSP.SPEC_ID AND PPH.SPEC_VERSION=PBSP.SPEC_VERSION"
		+" AND PBSP.press_type like '%Digital%') >0 THEN DECODE(SUBSTR(PPLS.PO_NO,1,4),'0418','A','0419','A','D')"
		+" ELSE 'O' END),'A','TRUE','FALSE' ) ARP_FLAG FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_LIST_SUMMARY PPLS"
		+" WHERE po_no=?";	
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dao.IPurchaseOrderDAO#getPurchaseOrderDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList getPurchaseOrderDetails(String vendorSAN, String transactionType, String transactionName) {				
		Connection dbCon 			= null;	
		CallableStatement cs  		= null;
		ResultSet resultSet         = null;
		String qryParams			= null;
		
		ArrayList poList 			= null;
		
		PapiNetEnvelopeDTO pneDTO	= null;		
		ArrayList payloadInfoList	= null;
		PayloadInfo payloadInfo		= null;
		Payload payload				= null;
		BusinessDocument businessDocument = null;
		PurchaseOrderDTO poDTO		= null;
		String poOrderType			= null;
		String poOrderStatusType	= null;
		POHeader poHeader  			= null;
		POSummary poSummary         = null;
		
		String bookClassificationData 		= null;
		ArrayList bookClassificationListData= null;
		ArrayList bookClassifList			= null;
		ArrayList bookClassificationList	= null;
		
		String suppCompInfoData 		= null;
		ArrayList suppCompInfoListData	= null;
		SuppliedComponentInfo suppCompInfo = null;
		ArrayList suppCompInfoList		= null;	
		
		String priceDetailsData			= null;
		ArrayList priceDetailsListData 	= null;
		PriceDetails priceDetails   	= null;
		ArrayList priceDetailsList		= null;
		
		POLineItem poLineItem			= null;
		ArrayList poLineItemList    	= null;
		
		long poLineItemNumber 		= -1;
		long poLineItemNumberPREV 	= -1;
		
		String poLineItemStatusType	= null;
		String prodPartNo			= null;	
		String requestedQty			= null;
		String lineUOM              = null;
		String shipReqDate			= null;
		String lineNotes			= null;
		String glCode				= null;		
		String glDesc				= null;	
		String currencyValue		= null;
		String scProductIdentifier	= null;
		String bookClassificationType = null;
		String bookSubClassificationType = null;
		String finishedGoodsFlag	= null;
		String suppComp 			= null;	
		String scPartyID 			= null;
		String scSupplierName		= null;
		String scSupplierName2		= null;
		String scSupplierName3		= null;
		String scSupplierAddr1		= null;
		String scSupplierAddr2		= null;
		String scSupplierAddr3		= null;
		String scSupplierAddr4		= null;
		String scSupplierCity		= null;
		String scSupplierState		= null;
		String scSupplierPCode		= null;
		String scSupplierCountry	= null;
		String scContactFName 		= null;
		String scContactLName 		= null;
		String scPhone 				= null;
		String scMobile 			= null;
		String scFax 				= null;
		String scEmail 				= null;
		String scName				= null;
		String scQuantity			= null;
		String scUOM                = null;
		String compDeliveryDate		= null;
		String scNotes				= null;
		
		String previousVendorPlant=null;
		
		try {
			B2BLogger.debug("******* PurchaseOrderDAO.getPurchaseOrderDetails() method ENTERED *******");			
			dbCon = DBUtils.getDBConnection();			
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			dbCon.setAutoCommit(false);
			
			payloadInfoList = getEnvelopePayloadInfo(dbCon, vendorSAN, transactionType, transactionName);
			if(payloadInfoList != null && payloadInfoList.size()>0){
				poList	= new ArrayList();
				for(int i=0; i<payloadInfoList.size(); i++){
					payloadInfo = (PayloadInfo)payloadInfoList.get(i);
					if(payloadInfo != null){						
						DBUtils.close(cs);
						cs = null;
						DBUtils.close(resultSet);
						resultSet = null;
						
						pneDTO = new PapiNetEnvelopeDTO();
						pneDTO.setPayloadInfo(payloadInfo);
						
						cs = dbCon.prepareCall(qry_generate_po_list_proc);
						cs.setLong(IPixB2BConstants.ONE, Long.valueOf(payloadInfo.getTransID()).longValue());
						cs.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						qryParams = ""+payloadInfo.getTransID();
						cs.execute();
						resultSet = (ResultSet) cs.getObject(IPixB2BConstants.TWO);			
						
						boolean dataFound = false;
						int rowCount = 1;		
						
						poDTO = new PurchaseOrderDTO();
						poLineItemList = new ArrayList();						
						
						bookClassificationListData = new ArrayList();
						bookClassificationList = new ArrayList();
						
						suppCompInfoListData = new ArrayList();
						suppCompInfoList = new ArrayList();
						
						priceDetailsListData = new ArrayList();
						priceDetailsList = new ArrayList();
						
						poLineItemNumberPREV = -1;
						
						while(resultSet.next()) {
							dataFound = true;							
							if(rowCount == 1){
								poOrderType 	  = resultSet.getString("PO_ORDER_TYPE");
								poOrderStatusType = resultSet.getString("PO_ORDER_STATUS");
								
								if(poOrderType != null && !"".equals(poOrderType.trim()))
									poDTO.setPoType(poOrderType.trim());
								
								if(poOrderStatusType != null && !"".equals(poOrderStatusType.trim()))
									poDTO.setPoStatusType(poOrderStatusType.trim());
								
								//set PurchaseOrderHeader details
								poHeader = setPurchaseOrderHeader(resultSet, dbCon);
								if(poHeader != null)
									poDTO.setPoHeader(poHeader);
								else
									B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - poHeader is null");
					
								//set PurchaseOrderSummary details
								poSummary = setPurchaseOrderSummary(resultSet);
								if(poSummary != null)
									poDTO.setPoSummary(poSummary);
								else
									B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - poSummary is null");
							}
							rowCount++ ;
							
							//set PurchaseOrderLineItem details
							poLineItemNumber = resultSet.getLong("PO_LINE_NO");	
							if(poLineItemNumberPREV != -1  && poLineItemNumber != poLineItemNumberPREV){
								poLineItem = setPurchaseOrderLineItem(poLineItemStatusType, poLineItemNumberPREV, 
										prodPartNo, bookClassificationList, suppCompInfoList, 
										priceDetailsList, requestedQty, lineUOM, shipReqDate, lineNotes,previousVendorPlant);								
								if(poLineItem != null)
									poLineItemList.add(poLineItem);
								else
									B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - poLineItem is null");								
								
								bookClassificationListData = new ArrayList();
								bookClassificationList = new ArrayList();
								
								suppCompInfoListData = new ArrayList();
								suppCompInfoList = new ArrayList();
								
								priceDetailsListData = new ArrayList();
								priceDetailsList = new ArrayList();
							}
							
							//get ResultSet data
							poLineItemStatusType= resultSet.getString("POLINEITEM_STATUS");
							prodPartNo 			= resultSet.getString("Product_PartNo");
							requestedQty 		= resultSet.getString("Total_Qty");
							lineUOM             = resultSet.getString("line_UOM");
							shipReqDate 		= resultSet.getString("LINEITEM_DELIVERY_DATE");
							lineNotes 			= resultSet.getString("LINEITEM_BuyerNotes");
							glCode 				= resultSet.getString("GL_Code");
							glDesc              = resultSet.getString("GL_DESCRIPTION");
							currencyValue 		= resultSet.getString("Currency_Value");
							scProductIdentifier = resultSet.getString("PRODUCT_IDENTIFIER");
							bookClassificationType = resultSet.getString("Product_Type");
							bookSubClassificationType = resultSet.getString("Product_SubType");
							finishedGoodsFlag 	= resultSet.getString("FINISHED_GOOD_FLAG");
							suppComp 			= resultSet.getString("SUPPLIEDCOMPONENT_TYPE");
							scPartyID 			= resultSet.getString("SUPPLIER_IDENTIFIER_SUP");
							scSupplierName 		= resultSet.getString("NAME_1");							
							scSupplierName2 	= resultSet.getString("NAME_2");
							scSupplierName3		= resultSet.getString("NAME_3");
							scSupplierAddr1		= resultSet.getString("ADDRESS_1");
							scSupplierAddr2		= resultSet.getString("ADDRESS_2");
							scSupplierAddr3		= resultSet.getString("ADDRESS_3");
							scSupplierAddr4		= resultSet.getString("ADDRESS_4");
							scSupplierCity		= resultSet.getString("CITY");
							scSupplierState		= resultSet.getString("STATE");
							scSupplierPCode		= resultSet.getString("POSTAL_CODE");
							scSupplierCountry	= resultSet.getString("COUNTRY_CODE");
							scContactFName 		= resultSet.getString("CONTACT_FIRST_NAME");
							scContactLName 		= resultSet.getString("CONTACT_LAST_NAME");
							scPhone 			= resultSet.getString("PHONE");
							scMobile 			= resultSet.getString("MOBILE");
							scFax 				= resultSet.getString("FAX");
							scEmail 			= resultSet.getString("EMAIL");						
							scName 				= resultSet.getString("COMPONENT_NAME");
							scQuantity 			= resultSet.getString("QUANTITY");
							scUOM               = resultSet.getString("Count_UOM");
							compDeliveryDate 	= resultSet.getString("COMPONENT_DELIVERY_DATE");
							scNotes 			= resultSet.getString("Supplied_Components_comments");
							previousVendorPlant = resultSet.getString("Previous_Vendor_Plant");
							
							//System.out.println("Previous_Vendor_Plant"+previousVendorPlant);
							
							//set PurchaseOrderLineItem BookClassification details
							bookClassificationData = bookClassificationType+bookSubClassificationType+finishedGoodsFlag;
							if(bookClassificationType != null && !"".equals(bookClassificationType.trim()) && !bookClassificationListData.contains(bookClassificationData)){
								bookClassificationListData.add(bookClassificationData);
								bookClassifList = setPOLineItemBookClassification(bookClassificationType, bookSubClassificationType, finishedGoodsFlag);
								if(bookClassifList != null && bookClassifList.size()>0){
									for(int j=0; j<bookClassifList.size();j++)
										bookClassificationList.add((BookClassification)bookClassifList.get(j));
								}
							}
							
							//set PurchaseOrderLineItem SuppliedComponentInformation details
							suppCompInfoData = scProductIdentifier+suppComp+scPartyID+scSupplierName+scSupplierName2+scSupplierName3
								+scSupplierAddr1+scSupplierAddr2+scSupplierAddr3+scSupplierAddr4+scSupplierCity+scSupplierState
								+scSupplierPCode+scSupplierCountry+scContactFName+scContactLName+scPhone+scMobile+scFax+scEmail
								+scName+scQuantity+scUOM+compDeliveryDate+scNotes;
							if(scProductIdentifier != null && !"".equals(scProductIdentifier.trim()) && !suppCompInfoListData.contains(suppCompInfoData)){
								suppCompInfoListData.add(suppCompInfoData);
								suppCompInfo = setPOLineItemSuppliedCompInfo(scProductIdentifier, suppComp, 
										scPartyID, scSupplierName, scSupplierName2, scSupplierName3, 
										scSupplierAddr1, scSupplierAddr2, scSupplierAddr3, scSupplierAddr4, 
										scSupplierCity, scSupplierState, scSupplierPCode, scSupplierCountry, 
										scContactFName, scContactLName, scPhone, scMobile, 
										scFax, scEmail, scName, scQuantity, scUOM,
										compDeliveryDate, scNotes);
								if(suppCompInfo != null)
									suppCompInfoList.add(suppCompInfo);
								else
									B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - suppCompInfo is null");
							}
							
							//set PurchaseOrderLineItem PriceDetails details
							priceDetailsData = currencyValue+requestedQty+lineUOM+glCode;
							if(currencyValue != null && !"".equals(currencyValue.trim()) && requestedQty != null && !"".equals(requestedQty.trim()) && !priceDetailsListData.contains(priceDetailsData)){
								priceDetailsListData.add(priceDetailsData);
								priceDetails = setPOLineItemPriceDetails(currencyValue, requestedQty, lineUOM, glCode, glDesc);
								if(priceDetails != null)
									priceDetailsList.add(priceDetails);
								else
									B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - priceDetails is null");
							}
							
							poLineItemNumberPREV = poLineItemNumber;							
						} //end while loop of resultSet
						
						if(dataFound){
							poLineItem = setPurchaseOrderLineItem(poLineItemStatusType, poLineItemNumber, 
									prodPartNo, bookClassificationList, suppCompInfoList, 
									priceDetailsList, requestedQty, lineUOM, shipReqDate, lineNotes,previousVendorPlant);								
							if(poLineItem != null)
								poLineItemList.add(poLineItem);
							else
								B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - poLineItem is null");								
							
							if(poLineItemList != null && poLineItemList.size()>0)
								poDTO.setPoLineItem(poLineItemList);
							else
								B2BLogger.info("PurchaseOrderDAO.getPurchaseOrderDetails() - poLineItemList is null or blank");
							
							businessDocument = new BusinessDocument();
							businessDocument.setPoDTO(poDTO);
							
							payload	= new Payload();
							payload.setBusinessDocument(businessDocument);
							
							pneDTO.setPayload(payload);
							poList.add(pneDTO);
						}
					}else{
						B2BLogger.debug("PurchaseOrderDAO.getPurchaseOrderDetails() - payloadInfo is null for Envelope");
					}
				} //end for loop of payloadInfoList
			}else{
				B2BLogger.debug("PurchaseOrderDAO.getPurchaseOrderDetails() - payloadInfoList is null or blank for Envelope");
			}
			dbCon.commit();
			B2BLogger.debug("******* PurchaseOrderDAO.getPurchaseOrderDetails() method EXIT *******");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+qry_generate_po_list_proc+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			poList = null;
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			poList = null;
		} finally{			
			DBUtils.close(cs);
			DBUtils.close(resultSet);
			DBUtils.close(dbCon);
			
			cs 				= null;
			resultSet 		= null;
			dbCon 			= null;
			
			qryParams		= null;
			pneDTO			= null;		
			payloadInfoList	= null;
			payloadInfo		= null;
			payload			= null;
			businessDocument= null;
			poDTO			= null;
			poOrderType		= null;
			poOrderStatusType= null;
			poHeader  		= null;
			poSummary       = null;			
			bookClassificationData = null;
			bookClassificationListData= null;
			bookClassifList	= null;
			bookClassificationList = null;			
			suppCompInfoData= null;
			suppCompInfoListData = null;
			suppCompInfo 	= null;
			suppCompInfoList= null;			
			priceDetailsData= null;
			priceDetailsListData = null;
			priceDetails   	= null;
			priceDetailsList= null;			
			poLineItem		= null;
			poLineItemList  = null;			
			poLineItemStatusType = null;
			prodPartNo		= null;	
			requestedQty	= null;
			lineUOM         = null;
			shipReqDate		= null;
			glCode			= null;		
			currencyValue	= null;
			scProductIdentifier	= null;
			bookClassificationType = null;
			bookSubClassificationType = null;
			finishedGoodsFlag = null;
			suppComp 		= null;	
			scPartyID 		= null;
			scSupplierName	= null;
			scSupplierName2	= null;
			scSupplierName3	= null;
			scSupplierAddr1	= null;
			scSupplierAddr2	= null;
			scSupplierAddr3	= null;
			scSupplierAddr4	= null;
			scSupplierCity	= null;
			scSupplierState	= null;
			scSupplierPCode	= null;
			scSupplierCountry= null;
			scContactFName 	= null;
			scContactLName 	= null;
			scPhone 		= null;
			scMobile 		= null;
			scFax 			= null;
			scEmail 		= null;

			scName			= null;
			scQuantity		= null;
			compDeliveryDate= null;
			scNotes			= null;
		}	
		return poList;		
	}
	/**
	 * This method set the PurchaseOrderHeader details for a single XML to generate.
	 * @param resultSet
	 * @return POHeader
	 */
	private POHeader setPurchaseOrderHeader(ResultSet resultSet, Connection dbCon) {
		POHeader poHeader  			= null;

		POInformation poInformation = null;
		Date date 					= null;
		POIssueDate poIssueDate     = null;
		BuyerParty buyerParty  		= null;
		BuyerPartyCommonContact bpCommonContact		= null;
		BuyerPartyNameAddress bpNameAddress  		= null;
		BuyerPartyPartyIdentifier buyerPartyId 		= null;
		SupplierParty supplierParty = null;
		SupplierPartyNameAddress spNameAddress		= null;
		SupplierPartyPartyIdentifier supplierPartyId= null;
		SupplierPartyCommonContact spCommonContact	= null;
		ShipToCharacteristics shipToChar 			= null;
		ShipToParty shipToParty		= null;
		ShipToPartyNameAddress shipToPartyNA		= null;
		ShipDate shipDate           = null;
		DateOther dateOther 		= null;
		POReference poReference 	= null;
		ArrayList poReferenceList 	= null;		

		String poHeaderStatus 		= null;
		String poNumber 			= null;
		String poReleaseNo			= null;
		String issueDate        	= null;
		String[] arrIssueDate 		= null;
		String strDate 				= null;
		//String strTime 			= null;
		String[] arrDate  			= null;
		String specNo				= null;
		String specVersion			= null;
		String isbn10				= null;
		String isbn13				= null;
		String printNumber			= null;
		String transID 				= null;
		//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: Start
		String edition 				= null;
		String copyRight			= null;
		String cartonQty			= null;
		//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: End
		String buyerID				= null;
		String buyerName1			= null;
		String buyerName2			= null;
		String buyerName3			= null;
		String buyerAddress1		= null;
		String buyerAddress2		= null;
		String buyerAddress3		= null;
		String buyerAddress4		= null;
		String buyerCity			= null;
		String buyerState			= null;
		String buyerPCode			= null;
		String buyerCountry			= null;
		String buyerContactName		= null;		
		String buyerPhone			= null;
		String buyerMobile			= null;
		String buyerEmail			= null;
		String buyerFax             = null;
		String supplierID			= null;
		String supplierName1		= null;
		String supplierName2		= null;
		String supplierName3		= null;
		String supplierAddress1		= null;
		String supplierAddress2		= null;
		String supplierAddress3		= null;
		String supplierAddress4		= null;
		String supplierCity			= null;
		String supplierState		= null;
		String supplierPCode		= null;
		String supplierCountry		= null;
		String supplierContactName 	= null;		
		String supplierPhone		= null;		
		String supplierEmail        = null;
		String supplierMobile       = null;
		String supplierFax			= null;
		String otherDate			= null;
		String[] arrOtherDate		= null;
		String strOtherDate			= null;
		String[] arrDateOthr		= null;
		String buyerHeaderNotes		= null;
		String poVersion			= null;
		String poID					= null;
		ArrayList headerNotesList 	= null;
		B2BHelper b2bHelper			= null;
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		String sqlQuery				= null;
		String qryParams			= null;
		
		ArrayList partyIdentifier 	= null;
		ArrayList partyIdenSupp 	= null;
		try {
			B2BLogger.debug("PurchaseOrderDAO.setPurchaseOrderHeader() method called");
			poHeader = new POHeader();
			b2bHelper = new B2BHelper();

			poHeaderStatus = resultSet.getString("POHEADER_STATUS");
			if(poHeaderStatus != null && !"".equals(poHeaderStatus.trim()))
				poHeader.setPoHeaderStatusType(poHeaderStatus.trim());
			
			poInformation 	= new POInformation();
			poNumber = resultSet.getString("PO_Number");
			if(poNumber != null && !"".equals(poNumber.trim()))
				poInformation.setPoNumber(poNumber.trim());
			poReleaseNo = resultSet.getString("PO_Release_Number");
			if(poReleaseNo != null && !"".equals(poReleaseNo.trim()))
				poInformation.setPoReleaseNumber(poReleaseNo.trim());
			issueDate = resultSet.getString("ISSUE_DATE");
			if(issueDate != null && !"".equals(issueDate.trim())){								
				arrIssueDate = issueDate.trim().split(" ");				
				strDate = arrIssueDate[0];
				//strTime = arrIssueDate[1];									
				arrDate =  strDate.split("-");
				date = new Date();
				date.setYear(arrDate[0]);
				date.setMonth(arrDate[1]);
				date.setDay(arrDate[2]);
				poIssueDate = new POIssueDate();
				poIssueDate.setDate(date);
				//poIssueDate.setTime(strTime);
				poInformation.setPoIssueDate(poIssueDate);
			}
			
			poReferenceList = new ArrayList();
			if(null!=poNumber && !"".equals(poNumber.trim())){
				String arpFlag = null;
				
				sqlQuery = qry_ckeck_arp_status;
				prepStmt1 = dbCon.prepareStatement(sqlQuery);
				prepStmt1.clearParameters();
				prepStmt1.setString(IPixB2BConstants.ONE,poNumber);
				qryParams = poNumber;
				resultSet1 = prepStmt1.executeQuery();
				while(resultSet1.next())
					arpFlag = resultSet1.getString("ARP_FLAG");
				
				DBUtils.close(resultSet1);
				DBUtils.close(prepStmt1);
				
				if(null!=arpFlag && "TRUE".equalsIgnoreCase(arpFlag)){
					poReference = new POReference();
					poReference.setPoReferenceType(IPixB2BConstants.BudgetCenter);
					poReference.setPoReferenceValue("ARP");    				
					poReferenceList.add(poReference);
				}
			}
			
			specNo = resultSet.getString("BOOK_SPEC_NUMBER");
			if(specNo!= null && !"".equals(specNo.trim())){
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.SpecificationNumber);
				poReference.setAssignedBy(IPixB2BConstants.Buyer);
				poReference.setPoReferenceValue(specNo.trim());    				
				poReferenceList.add(poReference);
			}								
			
			specVersion = resultSet.getString("BOOK_SPEC_VERSION_NUMBER");
			if(specVersion != null && !"".equals(specVersion.trim())){
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.SpecificationVersion);
				poReference.setPoReferenceValue(specVersion.trim());    				
				poReferenceList.add(poReference);
			}
			
			isbn10 = resultSet.getString("ISBN10");
			if(isbn10!= null && !"".equals(isbn10.trim())){
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.ISBN10);
				poReference.setPoReferenceValue(isbn10.trim());
				poReferenceList.add(poReference);
			}								
			isbn13 = resultSet.getString("ISBN13");
			if(isbn13!= null && !"".equals(isbn13.trim())){
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.ISBN13);
				poReference.setPoReferenceValue(isbn13.trim());
				poReferenceList.add(poReference);
			}								
			printNumber = resultSet.getString("PRINT_NUMBER");
			if(printNumber!= null && !"".equals(printNumber.trim())){
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.PrintingNumber);
				poReference.setPoReferenceValue(printNumber.trim());
				poReferenceList.add(poReference);
			}
			transID = resultSet.getString("Trans_id");
			if(transID!= null && !"".equals(transID.trim())) {
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.TransactionID);
				poReference.setPoReferenceValue(transID.trim());
				poReferenceList.add(poReference);
			}
			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: Start
			edition = resultSet.getString("EDITION");
			if(null!=edition && !"".equals(edition.trim())) {
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.Edition);
				poReference.setPoReferenceValue(edition.trim());
				poReferenceList.add(poReference);
			}
			copyRight = resultSet.getString("COPYRIGHT");
			if(null!=copyRight && !"".equals(copyRight.trim())) {
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.Copyright);
				poReference.setPoReferenceValue(copyRight.trim());
				poReferenceList.add(poReference);
			}
			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: End
			
			
			String sourceCode = resultSet.getString("Source_code");
			if(null!=sourceCode && !"".equals(sourceCode.trim())) {
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.DIVISIONIDENTIFIER);
				poReference.setAssignedBy(IPixB2BConstants.SALES_OFFICE);
				poReference.setPoReferenceValue(sourceCode.trim());
				poReferenceList.add(poReference);
			}
			String groupCode = resultSet.getString("Group_Code");
			if(null!=groupCode && !"".equals(groupCode.trim())) {
				poReference = new POReference();
				poReference.setPoReferenceType(IPixB2BConstants.BudgetCenter);
				poReference.setAssignedBy(IPixB2BConstants.BUYER);
				poReference.setPoReferenceValue(groupCode.trim());
				poReferenceList.add(poReference);
			}
			
			poInformation.setPoReference(poReferenceList); 			
			poHeader.setPoInformation(poInformation); 
			
			buyerParty = new BuyerParty();
			buyerID = resultSet.getString("BUYER_IDENTIFIER");
			if(buyerID != null && !"".equals(buyerID.trim())){
				buyerID = buyerID.replaceAll("-","");
				buyerPartyId = new BuyerPartyPartyIdentifier();
				buyerPartyId.setPartyIdentifierType(IPixB2BConstants.AssignedByBuyer);								
				buyerPartyId.setPartyIdentifierValue(buyerID.trim());
				partyIdentifier = new ArrayList();
				partyIdentifier.add(buyerPartyId);
				
				String sanId = (buyerID.trim()).substring(0, 7);
				buyerPartyId = new BuyerPartyPartyIdentifier();
				buyerPartyId.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
				buyerPartyId.setPartyIdentifierValue(sanId);
				partyIdentifier.add(buyerPartyId);
								
				buyerParty.setPartyIdentifier(partyIdentifier);
			}			
			bpNameAddress = new BuyerPartyNameAddress();
			buyerName1 = resultSet.getString("BUYER_NAME1");
			if(buyerName1 != null && !"".equals(buyerName1.trim())){
				bpNameAddress.setName1(buyerName1.trim());
				
				buyerName2 = resultSet.getString("BUYER_NAME2");
				if(buyerName2!= null && !"".equals(buyerName2.trim()))
					bpNameAddress.setName2(buyerName2.trim());	
				
				buyerName3 = resultSet.getString("BUYER_NAME3");
			    if(buyerName3!= null && !"".equals(buyerName3.trim()))
			    	bpNameAddress.setName3(buyerName3.trim());
			    
				buyerAddress1 = resultSet.getString("BUYER_ADDRESS1");
				if(buyerAddress1!= null && !"".equals(buyerAddress1.trim()))
					bpNameAddress.setAddress1(buyerAddress1.trim());
				
				buyerAddress2 = resultSet.getString("BUYER_ADDRESS2");
				if(buyerAddress2!= null && !"".equals(buyerAddress2.trim()))
					bpNameAddress.setAddress2(buyerAddress2.trim());
				
				buyerAddress3 = resultSet.getString("BUYER_ADDRESS3");
				if(buyerAddress3!= null && !"".equals(buyerAddress3.trim()))
					bpNameAddress.setAddress3(buyerAddress3.trim());
				
				buyerAddress4 = resultSet.getString("BUYER_ADDRESS4");
				if(buyerAddress4!= null && !"".equals(buyerAddress4.trim()))
					bpNameAddress.setAddress4(buyerAddress4.trim());
				
				buyerCity = resultSet.getString("BUYER_CITY");
				if(buyerCity!= null && !"".equals(buyerCity.trim()))
					bpNameAddress.setCity(buyerCity.trim());    
				buyerState = resultSet.getString("BUYER_STATE");
				if(buyerState!= null && !"".equals(buyerState.trim()))
					bpNameAddress.setStateOrProvince(buyerState.trim());
				buyerPCode = resultSet.getString("BUYER_POSTALCODE");
				if(buyerPCode != null && !"".equals(buyerPCode.trim()))
					bpNameAddress.setPostalCode(buyerPCode.trim());
				buyerCountry = resultSet.getString("BUYER_COUNTRY");
				if(buyerCountry != null && !"".equals(buyerCountry.trim()))
					bpNameAddress.setCountry(buyerCountry.trim());
				buyerParty.setNameAddress(bpNameAddress);
			}			
			bpCommonContact = new BuyerPartyCommonContact();
			buyerContactName = resultSet.getString("BUYER_CONTACT_NAME");
			if(buyerContactName!= null && !"".equals(buyerContactName.trim())){
				bpCommonContact.setContactType(IPixB2BConstants.Purchaser);
				bpCommonContact.setContactName(buyerContactName.trim());
				buyerPhone = resultSet.getString("BUYER_PHONE");
				if(buyerPhone != null && !"".equals(buyerPhone.trim()))
					bpCommonContact.setPhone(buyerPhone.trim());
				buyerMobile = resultSet.getString("BUYER_MOBILE");
				if(buyerMobile != null && !"".equals(buyerMobile.trim()))
					bpCommonContact.setMobile(buyerMobile.trim());
				buyerEmail = resultSet.getString("BUYER_EMAIL");
				if(buyerEmail != null && !"".equals(buyerEmail.trim()))
					bpCommonContact.setEmail(buyerEmail.trim());
				buyerFax = resultSet.getString("BUYER_FAX");
				if(buyerFax != null && !"".equals(buyerFax.trim()))
					bpCommonContact.setFax(buyerFax.trim());
				buyerParty.setCommonContact(bpCommonContact);
			} 								
			poHeader.setBuyerParty(buyerParty);						
			
			supplierParty = new SupplierParty();
			supplierID = resultSet.getString("SUPPLIER_IDENTIFIER");
			if(supplierID != null && !"".equals(supplierID.trim())){
				supplierID = supplierID.replaceAll("-","");
				supplierPartyId = new SupplierPartyPartyIdentifier();
				supplierPartyId.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
				supplierPartyId.setPartyIdentifierValue(supplierID.trim());
				// CP # 436230 Start
				partyIdenSupp = new ArrayList();
				partyIdenSupp.add(supplierPartyId);
				
				String venPlantCode = resultSet.getString("VEN_PLANT_CODE");
				if(null!=venPlantCode && !"".equals(venPlantCode.trim())){
					supplierPartyId = new SupplierPartyPartyIdentifier();
					supplierPartyId.setPartyIdentifierType(IPixB2BConstants.AssignedByBuyer);
					supplierPartyId.setPartyIdentifierValue(venPlantCode.trim());
					partyIdenSupp.add(supplierPartyId);
				}  
				supplierParty.setPartyIdentifier(partyIdenSupp);
				// CP # 436230 End
			}
			
			spNameAddress = new SupplierPartyNameAddress();
			supplierName1 = resultSet.getString("SUPPLIER_NAME1");
			if(supplierName1 != null && !"".equals(supplierName1.trim())){
				spNameAddress.setName1(supplierName1.trim());
				
				supplierName2 = resultSet.getString("SUPPLIER_NAME2");
				if(supplierName2 != null && !"".equals(supplierName2.trim()))
					spNameAddress.setName2(supplierName2.trim());
					
				supplierName3 = resultSet.getString("SUPPLIER_NAME3");
				if(supplierName3 != null && !"".equals(supplierName3.trim()))
					spNameAddress.setName3(supplierName3.trim());
				
				supplierAddress1 = resultSet.getString("SUPPLIER_ADDRESS1");
				if(supplierAddress1 != null && !"".equals(supplierAddress1.trim()))
					spNameAddress.setAddress1(supplierAddress1.trim());
				
				supplierAddress2 = resultSet.getString("SUPPLIER_ADDRESS2");
				if(supplierAddress2 != null && !"".equals(supplierAddress2.trim()))
					spNameAddress.setAddress2(supplierAddress2.trim());
				
				supplierAddress3 = resultSet.getString("SUPPLIER_ADDRESS3");
				if(supplierAddress3 != null && !"".equals(supplierAddress3.trim()))
					spNameAddress.setAddress3(supplierAddress3.trim());
				
				supplierAddress4 = resultSet.getString("SUPPLIER_ADDRESS4");
				if(supplierAddress4 != null && !"".equals(supplierAddress4.trim()))
					spNameAddress.setAddress4(supplierAddress4.trim());
				
				supplierCity = resultSet.getString("SUPPLIER_CITY");
				if(supplierCity != null && !"".equals(supplierCity.trim()))
					spNameAddress.setCity(supplierCity.trim());
				supplierState = resultSet.getString("SUPPLIER_STATE");
				if(supplierState != null && !"".equals(supplierState.trim()))
					spNameAddress.setStateOrProvince(supplierState.trim());
				supplierPCode = resultSet.getString("SUPPLIER_POSTALCODE");
				if(supplierPCode != null && !"".equals(supplierPCode.trim()))
					spNameAddress.setPostalCode(supplierPCode.trim());
				supplierCountry = resultSet.getString("SUPPLIER_COUNTRY");
				if(supplierCountry != null && !"".equals(supplierCountry.trim()))
					spNameAddress.setCountry(supplierCountry.trim());
				supplierParty.setNameAddress(spNameAddress);
			}
			spCommonContact = new SupplierPartyCommonContact();
			supplierContactName = resultSet.getString("SUPPLIER_CONTACT_NAME");
			if(supplierContactName != null && !"".equals(supplierContactName.trim())){
				spCommonContact.setContactType(IPixB2BConstants.CustomerService);
				spCommonContact.setContactName(supplierContactName.trim());
				supplierPhone = resultSet.getString("SUPPLIER_PHONE");
				if(supplierPhone != null && !"".equals(supplierPhone.trim()))
					spCommonContact.setPhone(supplierPhone.trim());
				supplierMobile = resultSet.getString("SUPPLIER_MOBILE");
				if(supplierMobile != null && !"".equals(supplierMobile.trim()))
					spCommonContact.setMobile(supplierMobile.trim());
				supplierEmail = resultSet.getString("SUPPLIER_EMAIL");
				if(supplierEmail != null && !"".equals(supplierEmail.trim()))
					spCommonContact.setEmail(supplierEmail.trim());
				supplierFax = resultSet.getString("SUPPLIER_FAX");
				if(supplierFax != null && !"".equals(supplierFax.trim()))
					spCommonContact.setFax(supplierFax.trim());
				supplierParty.setCommonContact(spCommonContact);
			}								
			poHeader.setSupplierParty(supplierParty);
			
			shipToParty	= new ShipToParty();
			shipToParty.setPartyType(IPixB2BConstants.ShipTo);
			shipToPartyNA = new ShipToPartyNameAddress();
			shipToPartyNA.setName1(IPixB2BConstants.ShipInstructions);    			
			shipToParty.setNameAddress(shipToPartyNA);
			shipToChar = new ShipToCharacteristics();
			shipToChar.setShipToParty(shipToParty);
			poHeader.setShipToCharacteristics(shipToChar);
			
			otherDate = resultSet.getString("DELIVERY_DATE");
			if(otherDate != null && !"".equals(otherDate.trim())){
				arrOtherDate = otherDate.split(" ");
				strOtherDate = arrOtherDate[0];
				arrDateOthr = strOtherDate.split("-");				
				shipDate = new ShipDate();
				shipDate.setYear(arrDateOthr[0]);
				shipDate.setMonth(arrDateOthr[1]);
				shipDate.setDay(arrDateOthr[2]);
				dateOther = new DateOther();
				dateOther.setDateType(IPixB2BConstants.ShipmentRequestedDate);
				dateOther.setShipDate(shipDate)  ;      
				poHeader.setDateOther(dateOther); 
			}

			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: Start
			cartonQty= resultSet.getString("Carton_Qty");
			buyerHeaderNotes = resultSet.getString("Header_Buyer_Notes");
			if((buyerHeaderNotes != null && !"".equals(buyerHeaderNotes.trim()) && !"PO Header Notes:".equalsIgnoreCase(buyerHeaderNotes.trim()))
			|| (null!=cartonQty && !"".equals(cartonQty.trim()))
			){
				headerNotesList = new ArrayList();
				
				if(null!=cartonQty && !"".equals(cartonQty.trim())){
					headerNotesList.add("Carton Quantity:"+cartonQty.trim());
				}				
				
				if(buyerHeaderNotes != null && !"".equals(buyerHeaderNotes.trim()) && !"PO Header Notes:".equalsIgnoreCase(buyerHeaderNotes.trim()))
				{
					String [] buyerHeaderNotesList	= null;
					if(buyerHeaderNotes.contains("\n")){
						buyerHeaderNotesList = buyerHeaderNotes.split("\n");
					}
					
					if(null!=buyerHeaderNotesList && buyerHeaderNotesList.length>0){
						for (int count=0; count<buyerHeaderNotesList.length; count++){
							if(buyerHeaderNotesList[count].length() > 250){
								ArrayList minheaderNotesList = new ArrayList();
								minheaderNotesList = b2bHelper.breakStringInSubstrings(buyerHeaderNotesList[count]);
								headerNotesList.addAll(minheaderNotesList);
							}else if(null!=buyerHeaderNotesList[count] && !"".equalsIgnoreCase(buyerHeaderNotesList[count].trim())){
								//headerNotesList = new ArrayList();
								headerNotesList.add(buyerHeaderNotesList[count]);
							}
						}
					}else{
						if(buyerHeaderNotes.length() > 250){					
							headerNotesList = b2bHelper.breakStringInSubstrings(buyerHeaderNotes);
						}else{
							headerNotesList.add(buyerHeaderNotes);
						}
					}
				}
				//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: End
				if(headerNotesList != null && headerNotesList.size() > 0)
					poHeader.setAdditionalText(headerNotesList);
				/*if(buyerHeaderNotes.length() > 250){					
					headerNotesList = b2bHelper.breakStringInSubstrings(buyerHeaderNotes);
				}else{
					headerNotesList = new ArrayList();
					headerNotesList.add(buyerHeaderNotes);
				}
				if(headerNotesList != null && headerNotesList.size() > 0)
					poHeader.setAdditionalText(headerNotesList);*/
			}
			poVersion = resultSet.getString("PO_VERSION");
			if(poVersion != null && !"".equals(poVersion.trim()))
				poHeader.setPoVersion(poVersion.trim());
			poID = resultSet.getString("PO_ID");
			if(poID != null && !"".equals(poID.trim()))
				poHeader.setPoId(poID.trim());

			B2BLogger.debug("PurchaseOrderDAO.setPurchaseOrderHeader() method return");
		} catch (SQLException e) {			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			poHeader = null;
		} finally{
			poInformation 	= null;
			date 			= null;
			poIssueDate     = null;
			buyerParty  	= null;
			bpCommonContact	= null;
			bpNameAddress  	= null;
			buyerPartyId 	= null;
			supplierParty 	= null;
			spNameAddress	= null;
			supplierPartyId	= null;
			spCommonContact	= null;
			shipToChar 		= null;
			shipToParty		= null;
			shipToPartyNA	= null;
			shipDate        = null;
			dateOther 		= null;
			poReference 	= null;
			poReferenceList = null;		
			poHeaderStatus 	= null;
			poNumber 		= null;
			poReleaseNo		= null;
			issueDate       = null;
			arrIssueDate 	= null;
			strDate 		= null;
			//strTime 		= null;
			arrDate  		= null;
			specNo			= null;
			specVersion		= null;
			isbn10			= null;
			isbn13			= null;
			printNumber		= null;
			transID 		= null;
			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: Start
			edition			= null;
			copyRight		= null;
			cartonQty		= null;
			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: End
			buyerID			= null;
			buyerName1		= null;
			buyerName2      = null;
			buyerName3      = null;
			buyerAddress1	= null;
			buyerAddress2	= null;
			buyerAddress3	= null;
			buyerAddress4	= null;
			buyerCity		= null;
			buyerState		= null;
			buyerPCode		= null;
			buyerCountry	= null;
			buyerContactName= null;		
			buyerPhone		= null;
			buyerMobile		= null;
			buyerEmail		= null;
			buyerFax        = null;
			supplierID		= null;
			supplierName1	= null;
			supplierName2	= null;
			supplierName3	= null;
			supplierAddress1= null;
			supplierAddress2= null;
			supplierAddress3= null;
			supplierAddress4= null;
			supplierCity	= null;
			supplierState	= null;
			supplierPCode	= null;
			supplierCountry	= null;
			supplierContactName = null;		
			supplierPhone	= null;		
			supplierEmail = null;
			supplierMobile = null;
			supplierFax		= null;
			otherDate		= null;
			arrOtherDate	= null;
			strOtherDate	= null;
			arrDateOthr		= null;
			buyerHeaderNotes= null;
			poVersion		= null;
			poID			= null;
			b2bHelper		= null;
			partyIdentifier = null;
			partyIdenSupp	= null;
		}
		return poHeader;
	}
	/**
	 * This method set the PurchaseOrderSummary details for a single XML to generate.
	 * @param resultSet
	 * @return POSummary
	 */
	private POSummary setPurchaseOrderSummary(ResultSet resultSet) {
		POSummary poSummary         = null;
		
		TotalQuantity totalQty 		= null;
		TotalAmount totalAmt		= null;
		ValueQty valueQty  			= null;
		SummaryCurrency summCurrency= null;
		TermsConditions termsConditions	= null;
		ArrayList termsCondsStringsList	= null;
		ArrayList termsConditionsList	= null;
		B2BHelper b2bHelper			= null;
		
		String poLineCount		= null;
		String strTotalQty		= null;
		String strTotalAmount	= null;
		String strTerms			= null;
		
		try {
			B2BLogger.debug("PurchaseOrderDAO.setPurchaseOrderSummary() method called");			
			poSummary = new POSummary();
			b2bHelper = new B2BHelper();
			
			poLineCount = resultSet.getString("TOTAL_NUMBER_OF_LINE_ITEMS"); 
			if(poLineCount != null && !"".equals(poLineCount.trim()))
				poSummary.setTotalNumberOfLineItems(poLineCount.trim());
			
			strTotalAmount = resultSet.getString("TOTAL_AMOUNT");
			if(strTotalAmount != null && !"".equals(strTotalAmount.trim())){
				summCurrency = new SummaryCurrency();
				summCurrency.setScurrencyType(IPixB2BConstants.USD) ;                  
				summCurrency.setScurrencyValue(strTotalAmount.trim());                        			
				totalAmt = new TotalAmount();
				totalAmt.setSummCurrency(summCurrency);
				poSummary.setTotalAmt(totalAmt);
			}
			strTerms = resultSet.getString("Terms_and_Conditions");
			if(strTerms != null && !"".equals(strTerms.trim())){
				String [] termAndConList	= null;
				if(strTerms.contains("\n")){
					termAndConList = strTerms.split("\n");
				}
				termsCondsStringsList = new ArrayList();
				if(null!=termAndConList && termAndConList.length>0){
					for (int count=0; count<termAndConList.length; count++){
						if(termAndConList[count].length() > 250){
							ArrayList mintermsCondsStringsList = new ArrayList();
							mintermsCondsStringsList = b2bHelper.breakStringInSubstrings(termAndConList[count]);
							termsCondsStringsList.addAll(mintermsCondsStringsList);
						}else if(null!=termAndConList[count] && !"".equalsIgnoreCase(termAndConList[count].trim())){
							termsCondsStringsList.add(termAndConList[count]);
						}
					}
				}else{
					if(strTerms.length() > 250){					
						termsCondsStringsList = b2bHelper.breakStringInSubstrings(strTerms);
					}else{
						termsCondsStringsList.add(strTerms.trim());
					}
				}
				if(termsCondsStringsList != null && termsCondsStringsList.size() > 0){
					termsConditionsList = new ArrayList();
					for(int i=0; i<termsCondsStringsList.size();i++){
						termsConditions = new TermsConditions();
						termsConditions.setLanguage(IPixB2BConstants.Language);
						termsConditions.setCondition((String)termsCondsStringsList.get(i));
						termsConditionsList.add(termsConditions);						
					}
					poSummary.setTermsConditions(termsConditionsList);
				}
				/*if(strTerms.length() > 250){					
					termsCondsStringsList = b2bHelper.breakStringInSubstrings(strTerms);
				}else{
					termsCondsStringsList = new ArrayList();
					termsCondsStringsList.add(strTerms.trim());
				}
				if(termsCondsStringsList != null && termsCondsStringsList.size() > 0){
					termsConditionsList = new ArrayList();
					for(int i=0; i<termsCondsStringsList.size();i++){
						termsConditions = new TermsConditions();
						termsConditions.setLanguage(IPixB2BConstants.Language);
						termsConditions.setCondition((String)termsCondsStringsList.get(i));
						termsConditionsList.add(termsConditions);						
					}
					poSummary.setTermsConditions(termsConditionsList);
				}*/
			}
			strTotalQty = resultSet.getString("TOTAL_QUANTITY");
			if(strTotalQty != null && !"".equals(strTotalQty.trim())){
				totalQty = new TotalQuantity();
				totalQty.setQtyType(IPixB2BConstants.Count);
				valueQty = new ValueQty();
				valueQty.setQtyUOM(IPixB2BConstants.Unit);						
				valueQty.setValValue(strTotalQty.trim());						    
				totalQty.setValueQty(valueQty);	
				poSummary.setTotalQty(totalQty);
			}else{
				poSummary = null;
			}			
			B2BLogger.debug("PurchaseOrderDAO.setPurchaseOrderSummary() method return");
		} catch (SQLException e) {			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			poSummary = null;
		} finally{
			totalQty 		= null;
			totalAmt		= null;
			valueQty  		= null;
			summCurrency	= null;
			termsConditions	= null;			
			poLineCount		= null;
			strTotalQty		= null;
			strTotalAmount	= null;
			strTerms		= null;
			termsCondsStringsList = null;
			termsConditionsList = null;
			b2bHelper		= null;
		}
		return poSummary;
	}
	/**
	 * This method set the BookClassification details for a single XML to generate.
	 * @param bookClassificationType
	 * @param bookSubClassificationType
	 * @param finishedGoodsFlag
	 * @return ArrayList
	 */
	private ArrayList setPOLineItemBookClassification(String bookClassificationType, String bookSubClassificationType, String finishedGoodsFlag) {
		BookClassification bookClassification= null;
		ArrayList bookClassifList = null;
		BookSubClassification bookSubClassification = null;
		
		try {
			B2BLogger.debug("PurchaseOrderDAO.setPOLineItemBookClassification() method called");
			bookClassifList = new ArrayList();
			
			bookClassification = new BookClassification();
			bookClassification.setBookClassificationType(bookClassificationType.trim());
			
			if(bookSubClassificationType != null && !"".equals(bookSubClassificationType.trim())){
				bookSubClassification = new BookSubClassification();			
				bookSubClassification.setBookSubClassificationType(bookSubClassificationType.trim());
				bookClassification.setBookSubClassification(bookSubClassification);
			}
			bookClassifList.add(bookClassification);
			
			if(finishedGoodsFlag != null && IPixB2BConstants.flag_Y.equals(finishedGoodsFlag.trim())){
				bookClassification = new BookClassification();
				bookClassification.setBookClassificationType(IPixB2BConstants.FinishedGoods);
				bookClassifList.add(bookClassification);								
			}
			B2BLogger.debug("PurchaseOrderDAO.setPOLineItemBookClassification() method return");
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			bookClassifList = null;
		} finally{
			bookClassification = null;
		}
		return bookClassifList;
	}
	/**
	 * This method set the SuppliedComponentInformation details for a single XML to generate.
	 * @param scProductIdentifier
	 * @param suppComp
	 * @param scPartyID
	 * @param scSupplierName
	 * @param scSupplierName2
	 * @param scSupplierName3
	 * @param scSupplierAddr1
	 * @param scSupplierAddr2
	 * @param scSupplierAddr3
	 * @param scSupplierAddr4
	 * @param scSupplierCity
	 * @param scSupplierState
	 * @param scSupplierPCode
	 * @param scSupplierCountry
	 * @param scContactFName
	 * @param scContactLName
	 * @param scPhone
	 * @param scMobile
	 * @param scFax
	 * @param scEmail
	 * @param scName
	 * @param scQuantity
	 * @param scUOM
	 * @param compDeliveryDate
	 * @param scNotes
	 * @return SuppliedComponentInfo
	 */
	private SuppliedComponentInfo setPOLineItemSuppliedCompInfo(String scProductIdentifier, String suppComp, 
			String scPartyID, String scSupplierName, String scSupplierName2, String scSupplierName3, 
			String scSupplierAddr1, String scSupplierAddr2, String scSupplierAddr3, String scSupplierAddr4, 
			String scSupplierCity, String scSupplierState, String scSupplierPCode, String scSupplierCountry, 
			String scContactFName, String scContactLName, String scPhone, String scMobile, 
			String scFax, String scEmail, String scName, String scQuantity, String scUOM,
			String compDeliveryDate, String scNotes) {
		SuppliedComponentInfo suppCompInfo 	= null;		
		SCSupplierParty scSupplierParty 	= null;
		SCProductIdentifier scProdIdent 	= null;
		SCQuantity scQty 					= null;
		SCQtyValue scQtyValue 				= null; 
		ComponentDueDate componentDueDate 	= null;
		SCPartyIdentifier scPartyIdentifier = null;
		SCNameAddress scNameAdd = null;		
		SCDate scDate			= null;
		SCCommonContact scCommonContact =  null;
		
		String scContactName 	= null;
		String[] arrayDate 		= null;
		String[] arrCompDate 	= null;
		String compDueDate 		= null;		
		ArrayList scNotesList   = null;
		B2BHelper b2bHelper 	= null;
		
		try {
			B2BLogger.debug("PurchaseOrderDAO.setPOLineItemSuppliedCompInfo() method called");
			suppCompInfo = new SuppliedComponentInfo();			
			
			if(suppComp != null && !"".equals(suppComp.trim()))
				suppCompInfo.setSuppliedComponentType(suppComp.trim());
			
			scSupplierParty = new SCSupplierParty();
			scPartyIdentifier = new SCPartyIdentifier();			
			scPartyIdentifier.setSuppPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
			if(scPartyID != null && !"".equals(scPartyID.trim())){
				scPartyID = scPartyID.replaceAll("-","");
				scPartyIdentifier.setSuppPartyIdentifierValue(scPartyID.trim());
				scSupplierParty.setScPartyIdentifier(scPartyIdentifier);
			}			
			if(scSupplierName != null && !"".equals(scSupplierName.trim())){
				scNameAdd = new SCNameAddress();
				scNameAdd.setSuppName1(scSupplierName.trim());
				
				if(scSupplierName2 != null && !"".equals(scSupplierName2.trim()))
					scNameAdd.setSuppName2(scSupplierName2.trim());					
				
				if(scSupplierName3 != null && !"".equals(scSupplierName3.trim()))
					scNameAdd.setSuppName3(scSupplierName3.trim());	
				
				if(scSupplierAddr1 != null && !"".equals(scSupplierAddr1.trim()))
					scNameAdd.setSuppAddr1(scSupplierAddr1.trim());	
				
				if(scSupplierAddr2 != null && !"".equals(scSupplierAddr2.trim()))
					scNameAdd.setSuppAddr2(scSupplierAddr2.trim());	
				
				if(scSupplierAddr3 != null && !"".equals(scSupplierAddr3.trim()))
					scNameAdd.setSuppAddr3(scSupplierAddr3.trim());
				
				if(scSupplierAddr4 != null && !"".equals(scSupplierAddr4.trim()))
					scNameAdd.setSuppAddr4(scSupplierAddr4.trim());
				
				if(scSupplierCity != null && !"".equals(scSupplierCity.trim()))
					scNameAdd.setScCity(scSupplierCity.trim());	
				
				if(scSupplierState != null && !"".equals(scSupplierState.trim()))
					scNameAdd.setScState(scSupplierState.trim());	
				
				if(scSupplierPCode != null && !"".equals(scSupplierPCode.trim()))
					scNameAdd.setScPCode(scSupplierPCode.trim());	
				
				if(scSupplierCountry != null && !"".equals(scSupplierCountry.trim()))
					scNameAdd.setScCountry(scSupplierCountry.trim());
				
				scSupplierParty.setScNameAddress(scNameAdd);
			}
			
		    if(scContactFName != null && !"".equals(scContactFName.trim()) || scContactLName != null && !"".equals(scContactLName.trim())){
		    	if(scContactFName == null)
		    		scContactFName = "";
		    	if(scContactLName == null)
		    		scContactLName = "";
		    	
		    	scContactName = scContactFName.trim() + " " + scContactLName.trim() ;			    	
				scCommonContact = new SCCommonContact();
				
		    	scCommonContact.setScContactName(scContactName.trim());			    	
		    	scCommonContact.setScContactType(IPixB2BConstants.Other);			    	
		    	
		    	if(scPhone != null && !"".equals(scPhone.trim()))
		    		scCommonContact.setScPhone(scPhone.trim());			    	
		    	
		    	if(scMobile != null && !"".equals(scMobile.trim()))
		    		scCommonContact.setScMobile(scMobile.trim());			    	
		    	
		    	if(scFax != null && !"".equals(scFax.trim()))
		    		scCommonContact.setScFax(scFax.trim());			    	
		    	
		    	if(scEmail != null && !"".equals(scEmail.trim()))
		    		scCommonContact.setScEmail(scEmail.trim());
		    	
		    	scSupplierParty.setScCommonContact(scCommonContact);
		    }
		    
			suppCompInfo.setScSupplierParty(scSupplierParty);
			
			scProdIdent = new SCProductIdentifier();
			scProdIdent.setProductAgency(IPixB2BConstants.Buyer);
			scProdIdent.setProductIdType(IPixB2BConstants.PartNumber);
			scProdIdent.setProductIdValue(scProductIdentifier.trim());
			suppCompInfo.setScProductIdentifier(scProdIdent);	
			
			if(scName != null && !"".equals(scName.trim()))
				suppCompInfo.setProductDescription(scName.trim());	
			
			if(scQuantity != null && !"".equals(scQuantity.trim())){
				scQty = new SCQuantity();
				scQty.setQtyType(IPixB2BConstants.Count);				
				scQtyValue = new SCQtyValue();
				scQtyValue.setQtyUOM1(scUOM.trim());
				scQtyValue.setQtyValue1(scQuantity.trim());
				scQty.setScQtyValue(scQtyValue);
				suppCompInfo.setScQty(scQty);
			}
			
			if(compDeliveryDate != null && !"".equals(compDeliveryDate.trim())){
				arrCompDate = compDeliveryDate.split(" ");
				compDueDate = arrCompDate[0];					
				arrayDate = compDueDate.split("-");
				scDate = new SCDate();
				scDate.setYear(arrayDate[0]);
				scDate.setMonth(arrayDate[1]);	
				scDate.setDay(arrayDate[2]);
				componentDueDate = new ComponentDueDate();
				componentDueDate.setScDate(scDate);
				suppCompInfo.setComponentDueDate(componentDueDate);
			}	
			
			if(scNotes != null && !"".equals(scNotes.trim())){
				scNotesList = new ArrayList();
				b2bHelper = new B2BHelper();
				if(scNotes.length() > 250){					
					scNotesList = b2bHelper.breakStringInSubstrings(scNotes);
				}else{
					scNotesList = new ArrayList();
					scNotesList.add(scNotes);
				}
				if(scNotesList != null && scNotesList.size() > 0)
					suppCompInfo.setNotes(scNotesList);
			}
			
			/**
			 if(scNotes != null && !"".equals(scNotes.trim())){
			 scNotesList = new ArrayList();
			 scNotesList = checkStrLen(scNotes);
			 suppCompInfo.setNotes(scNotesList);				
			 }*/
			
			B2BLogger.debug("PurchaseOrderDAO.setPOLineItemSuppliedCompInfo() method return");
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			suppCompInfo = null;
		} finally{
			scSupplierParty = null;
			scSupplierName	= null;	
			scSupplierName2	= null;
			scSupplierName3	= null;
			scSupplierAddr1	= null;
			scSupplierAddr2	= null;
			scSupplierAddr3	= null;
			scSupplierAddr4	= null;
			scSupplierCity	= null;
			scSupplierState	= null;
			scSupplierPCode	= null;
			scProdIdent 	= null;
			scQty 			= null;
			scQtyValue 		= null; 
			componentDueDate= null;
			scPartyIdentifier= null;
			scNameAdd 		= null;		
			scContactName 	= null;
			scContactFName 	= null;
			scContactLName 	= null;
			scPhone 		= null;		
			scMobile 		= null;
			scFax 			= null;
			scEmail 		= null;
			scDate			= null;
			suppComp 		= null;	
			scPartyID 		= null;
			scSupplierName	= null;	
			scQuantity		= null;			
			scNotes			= null;
			scName			= null;
			compDeliveryDate= null;
			arrayDate 		= null;
			arrCompDate 	= null;
			compDueDate 	= null;
		}
		return suppCompInfo;
	}
	/**
	 * This method set the PriceDetails details for a single XML to generate.
	 * @param currencyValue
	 * @param requestedQty
	 * @param glCode
	 * @return PriceDetails
	 */
	private PriceDetails setPOLineItemPriceDetails(String currencyValue, String requestedQty, String lineUOM, String glCode, String glDesc) {
		PriceDetails priceDetails   = null;
		Currency currency			= null;
		PricePerUnit ppUnit 		= null;
		PPUValue ppuValue 			= null;
		GLAccount glAccount 		= null;
		
		try {
			B2BLogger.debug("PurchaseOrderDAO.setPOLineItemPriceDetails() method called");
			priceDetails = new PriceDetails();
			
			priceDetails.setQtyBasis(IPixB2BConstants.Count);
			ppUnit = new PricePerUnit();			
			currency = new Currency();
			currency.setCurrencyType(IPixB2BConstants.USD);
			currency.setCurrencyValue(currencyValue.trim());				
			ppUnit.setCurrency(currency);			
			ppuValue = new PPUValue();
			ppuValue.setPpUOM(lineUOM.trim());					//Unit       
			ppuValue.setPpValue(requestedQty.trim());   
			ppUnit.setPpuValue(ppuValue);			
			priceDetails.setPpunit(ppUnit);			
			priceDetails.setAdditionalText(glDesc);   //Additional Text
			
			if(glCode != null && !"".equals(glCode.trim())){								
				glAccount = new GLAccount();
				glAccount.setGlAgency(IPixB2BConstants.Buyer);
				glAccount.setGlValue(glCode.trim());
				priceDetails.setGlAccount(glAccount);
			}
			
			B2BLogger.debug("PurchaseOrderDAO.setPOLineItemPriceDetails() method return");
		} catch (RuntimeException e) {			
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			priceDetails = null;
		} finally{
			currency	= null;
			ppUnit 		= null;
			ppuValue 	= null;
			glAccount	= null;
		}
		return priceDetails;
	}
	/**
	 * This method set the PurchaseOrderLineItem details for a single XML to generate.
	 * @param poLineItemStatusType
	 * @param poLineItemNumber
	 * @param prodPartNo
	 * @param bookClassificationList
	 * @param suppCompInfoList
	 * @param priceDetailsList
	 * @param requestedQty
	 * @param lineUOM
	 * @param shipReqDate
	 * @return POLineItem
	 */
	private POLineItem setPurchaseOrderLineItem(String poLineItemStatusType, long poLineItemNumber, 
			String prodPartNo, ArrayList bookClassificationList, ArrayList suppCompInfoList, 
			ArrayList priceDetailsList, String requestedQty, String lineUOM, String shipReqDate, String lineNotes,String previousVendorPlant) {

		POLineItem poLineItem		= null;
		Product product 			= null;
		ProductIdentifier prodIdentifier 	= null;		
		BookManufacturing bookManufacturing = null;
		LineQuantity lineQty 		= null;
		LineQtyValue lineQtyValue 	= null;
		LineShipDate lineShipDate 	= null;
		LineDateOther deliveryDate 	= null;
		
		String[] arrDeliveryDate	= null;
		String strDeliveryDate 		= null;
		String[] arrLineDate		= null;
		ArrayList lineNotesList 	= null;
		B2BHelper b2bHelper			= null;
		PreviousVendorPlant otherParty		=null;
		PreviousVendorNameAddress otherPartyNameAddress =null;
				
		try {
			B2BLogger.debug("PurchaseOrderDAO.setPurchaseOrderLineItem() method called");
			poLineItem = new POLineItem();
			b2bHelper = new B2BHelper();
			
			if(poLineItemStatusType != null && !"".equals(poLineItemStatusType.trim()))
				poLineItem.setPoLineItemStatusType(poLineItemStatusType.trim());
			
			poLineItem.setPoLineItemNumber(""+poLineItemNumber);
			otherParty = new PreviousVendorPlant();
			otherPartyNameAddress = new PreviousVendorNameAddress();
			if(previousVendorPlant != null && !"".equals(previousVendorPlant.trim())){
			//poLineItem.setPreviousVendorPlant(previousVendorPlant);
				//System.out.println("Before value set");
			otherPartyNameAddress.setName1(previousVendorPlant);
			//System.out.println("otherPartyNameAddress"+otherPartyNameAddress.getName1());
			//System.out.println("otherPartyNameAddressToString"+otherPartyNameAddress.toString());
			otherParty.setOtherPartyType(IPixB2BConstants.ORIGINAL_SUPPLIER);
			//System.out.println("otherParty"+otherParty.toString());
			otherParty.setPreviousVendorNameAddress(otherPartyNameAddress);
			//System.out.println("otherPartyfinal"+otherParty);
			poLineItem.setPreviousVendorPlant(otherParty);
			//System.out.println("poLineItem"+poLineItem.toString());
			//poLineItem.setPreviousVendorNameAddress(otherPartyNameAddress);
			}
			//poLineItem.setPartyType(IPixB2BConstants.PREVIOUS_VENDOR);
			product = new Product();
			if(prodPartNo != null && !"".equals(prodPartNo.trim())){
				prodIdentifier = new ProductIdentifier();
				prodIdentifier.setProductAgency(IPixB2BConstants.Buyer);
				prodIdentifier.setProductIdType(IPixB2BConstants.PartNumber);
				prodIdentifier.setProductIdValue(prodPartNo.trim());
				product.setProdIdentifier(prodIdentifier);
			}
			
			bookManufacturing = new BookManufacturing();				
			//set BookClassification
			if(bookClassificationList != null && bookClassificationList.size()>0)
				bookManufacturing.setBookClassification(bookClassificationList);			
			//set SuppliedComponentInformation
			if(suppCompInfoList != null && suppCompInfoList.size()>0)
				bookManufacturing.setSuppliedComponentInfo(suppCompInfoList);
			
			product.setBookManufacturing(bookManufacturing);
			poLineItem.setProduct(product);
			
			//set PriceDetails
			if(priceDetailsList != null && priceDetailsList.size()>0)
				poLineItem.setPriceDetailsList(priceDetailsList);
			
			if(requestedQty != null && !"".equals(requestedQty.trim())){
				lineQty = new LineQuantity();
				lineQty.setQtyType(IPixB2BConstants.Count);
				
				lineQtyValue = new LineQtyValue();
				lineQtyValue.setUOM(lineUOM.trim());     //Unit
				lineQtyValue.setQtyValue(requestedQty.trim());
				lineQty.setLineQtyValue(lineQtyValue);
				
				poLineItem.setLineQty(lineQty);
			}					
			if(shipReqDate != null && !"".equals(shipReqDate.trim())){
				arrDeliveryDate = shipReqDate.trim().split(" ");
				strDeliveryDate = arrDeliveryDate[0];
				arrLineDate =  strDeliveryDate.split("-");				
				lineShipDate = new LineShipDate ();
				lineShipDate.setLineYear(arrLineDate[0]);
				lineShipDate.setLineMonth(arrLineDate[1]);
				lineShipDate.setLineDay(arrLineDate[2]);
				deliveryDate = new LineDateOther();
				deliveryDate.setLineDateType(IPixB2BConstants.ShipmentRequestedDate);             
				deliveryDate.setLineShipDate(lineShipDate);        								
				poLineItem.setDeliveryDate(deliveryDate);							
			}
			
			if(lineNotes != null && !"".equals(lineNotes.trim())){
				String [] lineNoteList	= null;
				if(lineNotes.contains("\n")){
					lineNoteList = lineNotes.split("\n");
				}
				lineNotesList = new ArrayList();
				if(null!=lineNoteList && lineNoteList.length>0){
					for (int count=0; count<lineNoteList.length; count++){
						if(lineNoteList[count].length() > 250){
							ArrayList minlineNoteList = new ArrayList();
							minlineNoteList = b2bHelper.breakStringInSubstrings(lineNoteList[count]);
							lineNotesList.addAll(minlineNoteList);
						}else if(null!=lineNoteList[count] && !"".equalsIgnoreCase(lineNoteList[count].trim())){
							lineNotesList.add(lineNoteList[count]);
						}
					}
				}
				else{
					if(lineNotes.length() > 250){					
						lineNotesList = b2bHelper.breakStringInSubstrings(lineNotes);
					}else{
						lineNotesList.add(lineNotes);
					}
				}
				if(lineNotesList != null && lineNotesList.size() > 0)
					poLineItem.setAdditionalLineText(lineNotesList);
				/*if(lineNotes.length() > 250){					
					lineNotesList = b2bHelper.breakStringInSubstrings(lineNotes);
				}else{
					lineNotesList = new ArrayList();
					lineNotesList.add(lineNotes);
				}
				if(lineNotesList != null && lineNotesList.size() > 0)
					poLineItem.setAdditionalLineText(lineNotesList);*/
			}
			B2BLogger.debug("PurchaseOrderDAO.setPurchaseOrderLineItem() method return");
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			poLineItem = null;
		} finally{
			product 		= null;
			prodIdentifier 	= null;		
			bookManufacturing = null;
			lineQty 		= null;
			lineQtyValue 	= null;
			lineShipDate 	= null;
			deliveryDate 	= null;			
			arrDeliveryDate	= null;
			strDeliveryDate = null;
			arrLineDate		= null;
			b2bHelper		= null;
			otherParty		=null;
			otherPartyNameAddress =null;
			
		}
		return poLineItem;
	}
}