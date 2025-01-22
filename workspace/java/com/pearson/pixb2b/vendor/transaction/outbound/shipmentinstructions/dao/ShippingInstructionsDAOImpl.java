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
 * Title		: 	ShippingInstructionsDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal 	14 Oct, 2009	Initial version
 * 2.0		Yogesh Tyagi 	16 Nov, 2009	Reviewed and Modified
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import oracle.jdbc.OracleTypes;

import com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao.EnvelopeDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.AdditionalTextDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.BuyerParty;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ByProduct;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.CarPartyNmAddDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.CarrierPartyDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.CurrencyDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DelOriginDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DelOriginDateDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DeliveryLegDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DeliverySchedRef;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DeliverySchedule;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.LocationPartyDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.LocationPartyNmAddDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.LocationPartyPartyIdentifierDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.OtherParty;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.OtherPartyIdenDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.OtherPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.PPValueDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.PriceDetailsDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.PricePerUnitDS;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ProductIdentification;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.Quantity;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SIHeader;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SIInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SIIssuedDate;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SILineItemByProduct;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SIPOLineItem;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SIReference;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShipToCharacteristics;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShipToInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShipToParty;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShippingInstructionsDTO;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.SupplierParty;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.TermsOfDelivery;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.Value;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.Date;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyCommonContact;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.LocationParty;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.LocationPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessDocument;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Payload;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PayloadInfo;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * ShippingInstructionsDAOImpl is an implementation class to communicate with 
 * the database and get the ShippingInstructions transaction data.
 * 
 * @author Ashish Agrawal, Yogesh Tyagi, Ranu Sharma
 */
public class ShippingInstructionsDAOImpl extends EnvelopeDAOImpl implements IShippingInstructionsDAO{
	
	//TODO: Change the proc for test
	private static final String qry_generate_po_ship_list_proc = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_PO_SHIP_LIST_PROC(?,?,?)}";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dao.IShippingInstructionsDAO#getShippingInstructionsDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList getShippingInstructionsDetails(String vendorSAN, String transactionType, String transactionName){
		Connection dbCon 			= null;
		CallableStatement cs        = null;
		ResultSet resultSet         = null;
		ResultSet resultSet1         = null;
		String qryParams			= null;

		ArrayList siList			= null;
		ArrayList payloadInfoList	= null;
		
		PapiNetEnvelopeDTO pneDTO	= null;
		PayloadInfo payloadInfo		= null;
		Payload payload				= null;
		BusinessDocument businessDocument = null;
		ShippingInstructionsDTO siDTO= null;
		SIHeader siHeader			= null;
		SIPOLineItem siPoLineItem 	= null;
		
		String shipToInfoData 		= null;
		ArrayList shipToInfoListData= null;
		ShipToInformation shipToInfo= null;
		ArrayList shipToInfoList	= null;	
		
		ByProduct byProduct 		= null;
		SILineItemByProduct siLineItemByProduct= null;
		
		String statusType 			= null;
		String shipInstNo 			= null;
		String poLineNo 			= null;
		String itemStatusType		= null;
		String lineItemNo 			= null;
		String contactName 			= null;
		String phone 				= null;
		String mobile 				= null;
		String email 				= null;
		String fax 					= null;
		String name1 				= null;
		String name2 				= null;
		String name3 				= null;
		String address1 			= null;
		String address2 			= null;
		String address3 			= null;
		String address4 			= null;
		String city 				= null;
		String state 				= null;
		String postalCode 			= null;
		String country 				= null;
		String piVal 				= null;
		String uomQty 				= null;
		//For Dropshipment starts -->
		String delLegSeqNumber  	= null;
		String carrierParty			= null;
		String billToName1			= null;
		String billToAddress1		= null;
		String billToAddress2		= null;
		String billToAddress3		= null;
		String billToAddress4		= null;
		String billToCity			= null;
		String billToState			= null;
		String billToPostalCode		= null;
		String billToCountry		= null;
		String additionalText		= null;
		String otherPartyPiVal		= null;
		String pricePerUnit			= null;
		String freightPayAt  		= null;
		String locationPiVal		= null;
		String supplierName			= null;
		String customerOrderNum		= null;
		String invoiceNum			= null;
		// Added by Vineeta for tracker#497209 Starts
		String partNumber = null;
		//Added by Vineeta for tracker#497209 Ends
		//For Dropshipment ends <--
		
		
		
		
		
		try {
			B2BLogger.debug("******* ShippingInstructionsDAOImpl.getShippingInstructionsDetails() method ENTERED *******");			

			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			dbCon.setAutoCommit(false);
			
			payloadInfoList = getEnvelopePayloadInfo(dbCon, vendorSAN, transactionType, transactionName);
			if(payloadInfoList != null && payloadInfoList.size()>0){
				siList = new ArrayList();
				for(int i=0; i<payloadInfoList.size(); i++){
					payloadInfo = (PayloadInfo)payloadInfoList.get(i);
					if(payloadInfo != null){
						DBUtils.close(cs);
						cs = null;
						DBUtils.close(resultSet);
						DBUtils.close(resultSet1);
						resultSet1 = null;
						resultSet = null;
						
						pneDTO = new PapiNetEnvelopeDTO();
						pneDTO.setPayloadInfo(payloadInfo);

						cs = dbCon.prepareCall(qry_generate_po_ship_list_proc);
						cs.setLong(IPixB2BConstants.ONE, Long.valueOf(payloadInfo.getTransID()).longValue());
						cs.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						cs.registerOutParameter(IPixB2BConstants.THREE, OracleTypes.CURSOR);
						qryParams = ""+payloadInfo.getTransID();
						System.out.println("PayloadInfo Transaction ID processed is >>> " + qryParams );
						System.out.println("<<<<<< VENDOR SAN PROCESSED IS >>>>" + vendorSAN);
						cs.execute();
						resultSet = (ResultSet) cs.getObject(IPixB2BConstants.TWO);
						resultSet1 = (ResultSet) cs.getObject(IPixB2BConstants.THREE);
						shipToInfoListData = new ArrayList();
						shipToInfoList = new ArrayList();
						
						siDTO = new ShippingInstructionsDTO();
						byProduct = new ByProduct();
						
						boolean dataFound = false;
						int rowCount = 1;

						while(resultSet.next()) {
							dataFound = true;
							if(rowCount == 1){
								siDTO.setSiType(IPixB2BConstants.ShippingInstructionsRouting);
								
								if(resultSet.getString("STATUS_TYPE") != null){
								statusType = resultSet.getString("STATUS_TYPE");
								}
								if(resultSet.getString("SHIP_INSTRUCTION_NUMBER") != null){
								shipInstNo = resultSet.getString("SHIP_INSTRUCTION_NUMBER");
								}
								if(resultSet.getString("PO_LINE_ITEM_NUMBER") != null){
								poLineNo   = resultSet.getString("PO_LINE_ITEM_NUMBER");
								}
								if(resultSet.getString("LINE_ITEM_STATUS_TYPE") != null){
								itemStatusType	= resultSet.getString("LINE_ITEM_STATUS_TYPE");
								}
								if(resultSet.getString("LINE_ITEM_NUMBER") != null){
								lineItemNo 		= resultSet.getString("LINE_ITEM_NUMBER");
								}
								if(resultSet.getString("SUPPLIER_IDENTIFIER_TYPE") != null){
								locationPiVal	= resultSet.getString("SUPPLIER_IDENTIFIER_TYPE");
								}
								if(resultSet.getString("SUPPLIER_NAME") != null){
								supplierName	= resultSet.getString("SUPPLIER_NAME");
								}
								if(resultSet.getString("PART_NUMBER") != null){
								partNumber = resultSet.getString("PART_NUMBER");
								}
								
								if(statusType != null && !"".equals(statusType.trim()))
									siDTO.setSiStatusType(statusType.trim());
									
								//set ShippingInstructionsHeader details
								siHeader = setShippingInstructionsHeader(shipInstNo, resultSet);
								if(siHeader != null)
									siDTO.setSiHeader(siHeader);
								else
									B2BLogger.info("ShippingInstructionsDAOImpl.getShippingInstructionsDetails() - siHeader is null");
								
								//set ShippingInstructionsPurchaseOrderLineItem details
								siPoLineItem = setSIPurchaseOrderLineItem(poLineNo, shipInstNo, partNumber);
								if(siPoLineItem != null)
									byProduct.setSiPoLineItem(siPoLineItem);
								else
									B2BLogger.info("ShippingInstructionsDAOImpl.getShippingInstructionsDetails() - siPoLineItem is null");
							}
							rowCount++ ;
						}
						int rowCount1 = 1;	
							
						while(resultSet1.next()) {
								dataFound = true;
							//get ResultSet data
							contactName = null;
							if(resultSet1.getString("SHIP_TO_CONTACT_NAME") != null){
							contactName 	= resultSet1.getString("SHIP_TO_CONTACT_NAME");
							}
							phone = null;
							if(resultSet1.getString("SHIP_TO_PHONE") != null){
							phone 			= resultSet1.getString("SHIP_TO_PHONE");
							}
							//mobile 			= resultSet1.getString("SHIP_TO_MOBILE");
							//email 			= resultSet1.getString("SHIP_TO_EMAIL");
							fax = null;
							if(resultSet1.getString("SHIP_TO_FAX") != null){
							fax 			= resultSet1.getString("SHIP_TO_FAX");
							}
							name1 = null;
							if(resultSet1.getString("SHIP_TO_NAME1") != null){
							name1 			= resultSet1.getString("SHIP_TO_NAME1");
							}
							//name2 			= resultSet.getString("SHIP_NAME2");
							//name3 			= resultSet.getString("SHIP_NAME3");
							address1 = null;
							if(resultSet1.getString("SHIP_TO_ADDRESS1") != null){
							address1 		= resultSet1.getString("SHIP_TO_ADDRESS1");
							}
							address2 = null;
							if(resultSet1.getString("SHIP_TO_ADDRESS2") != null){
							address2 		= resultSet1.getString("SHIP_TO_ADDRESS2");
							}
							address3 = null;
							if(resultSet1.getString("SHIP_TO_ADDRESS3") != null){
							address3 		= resultSet1.getString("SHIP_TO_ADDRESS3");
							}
							address4 = null;
							if(resultSet1.getString("SHIP_TO_ADDRESS4") != null){
							address4 		= resultSet1.getString("SHIP_TO_ADDRESS4");
							}
							city = null;
							if(resultSet1.getString("SHIP_TO_CITY") != null){
							city 			= resultSet1.getString("SHIP_TO_CITY");
							}
							state = null;
							if(resultSet1.getString("SHIP_TO_STATE") != null){
							state 			= resultSet1.getString("SHIP_TO_STATE");
							}
							postalCode = null;
							if(resultSet1.getString("SHIP_TO_POSTAL_CODE") != null){
							postalCode 		= resultSet1.getString("SHIP_TO_POSTAL_CODE");
							}
							country = null;
							if(resultSet1.getString("SHIP_TO_COUNTRY") != null){
							country 		= resultSet1.getString("SHIP_TO_COUNTRY");
							}
							piVal = null;
							if(resultSet1.getString("PARTY_IDENTIFIER_TYPE") != null){
							piVal 			= resultSet1.getString("PARTY_IDENTIFIER_TYPE");
							}
							uomQty = null;
							if(resultSet1.getString("QUANTITY") != null){
							uomQty 	= resultSet1.getString("QUANTITY");
							}
							delLegSeqNumber = null;
							//For Dropshipment starts
							if(resultSet1.getString("DELIVERY_LEG_SEQ_NUMBER") != null){
							delLegSeqNumber	= resultSet1.getString("DELIVERY_LEG_SEQ_NUMBER");
							}
							carrierParty = null;
							if(resultSet1.getString("CARRIER_PARTY") != null){
							carrierParty	= resultSet1.getString("CARRIER_PARTY");
							}
							billToName1 = null;
							if(resultSet1.getString("BILL_TO_NAME1") != null){
							billToName1		= resultSet1.getString("BILL_TO_NAME1");
							}
							billToAddress1 = null;
							if(resultSet1.getString("BILL_TO_ADDRESS1") != null){
							billToAddress1	= resultSet1.getString("BILL_TO_ADDRESS1");
							}
							billToAddress2 = null;
							if(resultSet1.getString("BILL_TO_ADDRESS2") != null){
							billToAddress2	= resultSet1.getString("BILL_TO_ADDRESS2");
							}
							billToAddress3 = null;
							if(resultSet1.getString("BILL_TO_ADDRESS3") != null){
							billToAddress3	= resultSet1.getString("BILL_TO_ADDRESS3");
							}
							billToAddress4 = null;
							if(resultSet1.getString("BILL_TO_ADDRESS4") != null){
							billToAddress4	= resultSet1.getString("BILL_TO_ADDRESS4");
							}
							billToCity = null;
							if(resultSet1.getString("BILL_TO_CITY") != null){
							billToCity		= resultSet1.getString("BILL_TO_CITY");
							}
							billToState = null;
							if(resultSet1.getString("BILL_TO_STATE") != null){
							billToState		= resultSet1.getString("BILL_TO_STATE");
							}
							billToPostalCode = null;
							if(resultSet1.getString("BILL_TO_POSTAL_CODE") != null){
							billToPostalCode= resultSet1.getString("BILL_TO_POSTAL_CODE");
							}
							billToCountry = null;
							if(resultSet1.getString("BILL_TO_COUNTRY") != null){
							billToCountry	= resultSet1.getString("BILL_TO_COUNTRY");
							}
							additionalText = null;
							if(resultSet1.getString("ADDITIONAL_TEXT") != null){
							additionalText	= resultSet1.getString("ADDITIONAL_TEXT");
							}
							freightPayAt = null;
							if(resultSet1.getString("FREIGHT_PAYABLE_AT") != null){
							freightPayAt	= resultSet1.getString("FREIGHT_PAYABLE_AT");
							}
							customerOrderNum = null;
							if(resultSet1.getString("CUST_ORDER_NUMBER") != null){
							customerOrderNum= resultSet1.getString("CUST_ORDER_NUMBER");
							}
							invoiceNum = null;
							if(resultSet1.getString("INVOICE_NUMBER") != null){
							invoiceNum		= resultSet1.getString("INVOICE_NUMBER");
							}
							pricePerUnit = null;
							if(resultSet1.getString("PRICE_PER_UNIT") != null){
							pricePerUnit	= resultSet1.getString("PRICE_PER_UNIT").toString();	
							}
							otherPartyPiVal = null;
							if(freightPayAt != null && resultSet1.getString("BILL_TO_ACC_NUMBER") != null){
								otherPartyPiVal = resultSet1.getString("BILL_TO_ACC_NUMBER");
							}else{
								B2BLogger.info("ShippingInstructionsDAOImpl.getShippingInstructionsDetails - freightPayAt, getString('BILL_TO_ACC_NUMBER') is null");
							}
							if(null!=uomQty && Integer.valueOf(uomQty)!=0){
							//set ShipToInformation details List for ShippingInstructionsLineItemByProduct
							shipToInfoData = lineItemNo+contactName+phone+mobile+email+fax+name1+name2+name3
								+address1+address2+address3+address4+city+state+postalCode+country+piVal+uomQty
								+delLegSeqNumber+carrierParty+billToName1+billToAddress1+billToAddress2
								+billToAddress3+billToAddress4+billToCity+billToState+billToPostalCode+billToCountry
								+additionalText+otherPartyPiVal+pricePerUnit+freightPayAt
								+locationPiVal+supplierName+customerOrderNum+invoiceNum;
							if(!shipToInfoListData.contains(shipToInfoData)){
								shipToInfoListData.add(shipToInfoData);
								shipToInfo = setShipToInformation(lineItemNo, contactName, phone, mobile,
										email, fax, name1, name2, name3, address1, address2, address3,
										address4, city, state, postalCode, country, piVal, uomQty,delLegSeqNumber,
										carrierParty,billToName1,billToAddress1,billToAddress2,billToAddress3
										,billToAddress4,billToCity,billToState,billToPostalCode,billToCountry
										,additionalText,otherPartyPiVal,pricePerUnit,freightPayAt
										,locationPiVal,supplierName,customerOrderNum,invoiceNum);
								if(shipToInfo != null)
									shipToInfoList.add(shipToInfo);
								else
									B2BLogger.info("ShippingInstructionsDAOImpl.getShippingInstructionsDetails() - shipToInfo is null");
							}
						 	rowCount1++ ;
						  //end resultSet while loop
							}
						}
						if(dataFound){
							if(byProduct != null){								
								//set ShippingInstructionsLineItemByProduct details
								siLineItemByProduct = setSILineItemByProduct(itemStatusType, lineItemNo, shipToInfoList);
								if(siLineItemByProduct != null)
									byProduct.setSiLineItemByProduct(siLineItemByProduct);
								else
									B2BLogger.info("ShippingInstructionsDAOImpl.getShippingInstructionsDetails() - siLineItemByProduct is null");
								
								siDTO.setByProduct(byProduct);
								businessDocument = new BusinessDocument();
								businessDocument.setSiDTO(siDTO);
								
								payload	= new Payload();
								payload.setBusinessDocument(businessDocument);
								
								pneDTO.setPayload(payload);
								siList.add(pneDTO);
							}
						}						
							
					}else{
						B2BLogger.debug("ShippingInstructionsDAOImpl.getShippingInstructionsDetails() - Envelope PayloadInfo is null");
					}
				}//end payloadInfoList for loop
			}else{
				B2BLogger.debug("ShippingInstructionsDAOImpl.getShippingInstructionsDetails() - payloadInfoList is null");
			}
			dbCon.commit();
			B2BLogger.debug("******* ShippingInstructionsDAOImpl.getShippingInstructionsDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_generate_po_ship_list_proc+"] ["+qryParams+"]");
			B2BLogger.error("SQLException in ***ShippingInstructionsDAOImpl.getShippingInstructionsDetails()*** :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception in ***ShippingInstructionsDAOImpl.getShippingInstructionsDetails()***  ::",e);
			siList = null;
		} catch (Exception e) {			
			B2BLogger.error("Exception in ***ShippingInstructionsDAOImpl.getShippingInstructionsDetails()*** :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception in ***ShippingInstructionsDAOImpl.getShippingInstructionsDetails()*** ::",e);
			siList = null;
		} finally{
			DBUtils.close(cs);
			DBUtils.close(resultSet);			
			DBUtils.close(dbCon);
			
			cs 				= null;
			resultSet 		= null;
			dbCon 			= null;
			
			qryParams		= null;
			payloadInfoList	= null;			
			pneDTO			= null;
			payloadInfo		= null;
			payload			= null;
			businessDocument= null;
			siDTO			= null;
			siHeader		= null;
			siPoLineItem 	= null;			
			shipToInfoData 	= null;
			shipToInfoListData= null;
			shipToInfo= null;
			shipToInfoList	= null;			
			byProduct 		= null;
			siLineItemByProduct= null;			
			statusType 		= null;
			shipInstNo 		= null;
			poLineNo 		= null;
			itemStatusType	= null;
			lineItemNo 		= null;
			contactName 	= null;
			phone 			= null;
			mobile 			= null;
			email 			= null;
			fax 			= null;
			name1 			= null;
			name2 			= null;
			name3 			= null;
			address1 		= null;
			address2 		= null;
			address3 		= null;
			address4 		= null;
			city 			= null;
			state 			= null;
			postalCode 		= null;
			country 		= null;
			piVal 			= null;
			uomQty 			= null;
			delLegSeqNumber = null;
			carrierParty	= null;
			billToName1		= null;
			billToAddress1	= null;
			billToAddress2	= null;
			billToAddress3	= null;
			billToAddress4	= null;
			billToCity		= null;
			billToState		= null;
			billToPostalCode= null;
			billToCountry	= null;
			additionalText	= null;
			otherPartyPiVal	= null;
			pricePerUnit	= null;
			freightPayAt  	= null;
			locationPiVal	= null;
			supplierName	= null;
			customerOrderNum= null;
			invoiceNum		= null;
		}
		return siList;
	}
	
	/**
	 * This method set the ShippingInstructionsHeader details for a single XML to generate.
	 * @param shipInstNo
	 * @param resultSet
	 * @return SIHeader
	 */
	private SIHeader setShippingInstructionsHeader(String shipInstNo, ResultSet resultSet){
		SIHeader siHeader							 = null;
		SIInformation siInformation 				 = null;
		BuyerParty buyerParty 						 = null;
		SupplierParty supplierParty 			 	 = null;
		SIIssuedDate siIssueDate 				 	 = null;
		ArrayList siReferenceList 					 = null;
		SIReference siReference 					 = null;
		ArrayList bpPartyIdentifierList				 = null;
		BuyerPartyPartyIdentifier buyerPartyId		 = null;
		BuyerPartyNameAddress bpNameAddress			 = null;
		ArrayList spPartyIdentifierList 			 = null;
		SupplierPartyPartyIdentifier supplierPartyId = null;
		SupplierPartyNameAddress spNameAddress	 	 = null;
		Date date 									 = null;
		
		try {
			B2BLogger.debug("ShippingInstructionsDAOImpl.setShippingInstructionsHeader() method called");
			siHeader = new SIHeader();
			
			siInformation = new SIInformation();
			if(shipInstNo != null && !"".equals(shipInstNo.trim()))
				siInformation.setSiNumber(shipInstNo.trim());			
			String shipIssueDate = resultSet.getString("SHIP_INSTRUCTIONS_ISSUED_DATE");
			if(shipIssueDate != null && !"".equals(shipIssueDate.trim())) {
				String[] temp = shipIssueDate.split(" ");
				if(temp[0] != null){
					siIssueDate = new SIIssuedDate();
					String[] sDate =  temp[0].split("-");
					date = new Date();
					date.setYear(sDate[0]);
					date.setMonth(sDate[1]);
					date.setDay(sDate[2]);		        			
					siIssueDate.setDate(date);
					siInformation.setSiIssueDate(siIssueDate);
				}
			}
			String transID = resultSet.getString("TRANSACTION_ID");
			if(transID != null && !"".equals(transID.trim())){
				siReference = new SIReference();
				siReference.setSiReferenceType(IPixB2BConstants.TransactionID);
				siReference.setSiReferenceValue(transID.trim());
				siReferenceList = new ArrayList();
				siReferenceList.add(siReference);
				if(shipInstNo != null && !"".equals(shipInstNo.trim())){
					siReference = new SIReference();
					siReference.setSiReferenceType(IPixB2BConstants.PURCHASEORDERNUMBER);
					siReference.setSiReferenceValue(shipInstNo.trim());
					siReferenceList.add(siReference);
				}
				String isbn10 = resultSet.getString("ISBN10");
				if(null!=isbn10 && !"".equals(isbn10.trim())){
					siReference = new SIReference();
					siReference.setSiReferenceType(IPixB2BConstants.ISBN10);
					siReference.setSiReferenceValue(isbn10.trim());
					siReferenceList.add(siReference);
				}
				String isbn13 = resultSet.getString("ISBN13");
				if(null!=isbn13 && !"".equals(isbn13.trim())){
					siReference = new SIReference();
					siReference.setSiReferenceType(IPixB2BConstants.ISBN13);
					siReference.setSiReferenceValue(isbn13.trim());
					siReferenceList.add(siReference);
				}
				String printNum = resultSet.getString("PRINT_NO");
				if(null!=printNum && !"".equals(printNum.trim())){
					siReference = new SIReference();
					siReference.setSiReferenceType(IPixB2BConstants.PrintingNumber);
					siReference.setSiReferenceValue(printNum.trim());
					siReferenceList.add(siReference);
				}
				siInformation.setSiReferenceList(siReferenceList);
			}
			siHeader.setSiInformation(siInformation);
			
			buyerParty = new BuyerParty();
			String buyerId = resultSet.getString("BUYER_IDENTIFIER_TYPE");
			if(buyerId != null && !"".equals(buyerId.trim())){
				buyerId = buyerId.replaceAll("-","");
				buyerPartyId = new BuyerPartyPartyIdentifier();
				buyerPartyId.setPartyIdentifierType(IPixB2BConstants.AssignedByBuyer);
				buyerPartyId.setPartyIdentifierValue(buyerId.trim());
				bpPartyIdentifierList = new ArrayList();
				bpPartyIdentifierList.add(buyerPartyId);
				
				String sanId = (buyerId.trim()).substring(0, 7);
				buyerPartyId = new BuyerPartyPartyIdentifier();
				buyerPartyId.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
				buyerPartyId.setPartyIdentifierValue(sanId);
				bpPartyIdentifierList.add(buyerPartyId);
								
				buyerParty.setPartyIdentifierList(bpPartyIdentifierList);
			}
			String buyerName = resultSet.getString("BUYER_NAME");
			if(buyerName != null && !"".equals(buyerName.trim())){
				bpNameAddress = new BuyerPartyNameAddress();
				bpNameAddress.setName1(buyerName.trim());
				buyerParty.setNameAddress(bpNameAddress);
			}
			siHeader.setBuyerParty(buyerParty);

			supplierParty = new SupplierParty();
			String supplierId = resultSet.getString("SUPPLIER_IDENTIFIER_TYPE");
			if(supplierId != null && !"".equals(supplierId.trim())){
				supplierId = supplierId.replaceAll("-","");
				supplierPartyId = new SupplierPartyPartyIdentifier();
				supplierPartyId.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
				supplierPartyId.setPartyIdentifierValue(supplierId.trim());
				spPartyIdentifierList = new ArrayList();
				spPartyIdentifierList.add(supplierPartyId);
				supplierParty.setPartyIdentifierList(spPartyIdentifierList);
			}
			String supName = resultSet.getString("SUPPLIER_NAME");
			if(supName != null && !"".equals(supName.trim())){
				spNameAddress	= new SupplierPartyNameAddress();
				spNameAddress.setName1(supName.trim());
				supplierParty.setNameAddress(spNameAddress);
			}
			siHeader.setSupplierParty(supplierParty);
			
			if((shipInstNo == null || "".equals(shipInstNo.trim())) 
					&& (shipIssueDate == null || "".equals(shipIssueDate.trim()))
					&& (transID == null || "".equals(transID.trim()))
					&& (buyerId == null || "".equals(buyerId.trim())) 
					&& (buyerName == null || "".equals(buyerName.trim()))
					&& (supplierId == null || "".equals(supplierId.trim())) 
					&& (supName == null || "".equals(supName.trim())))
				siHeader = null;
			
			B2BLogger.debug("ShippingInstructionsDAOImpl.setShippingInstructionsHeader() method return");
		} catch (SQLException e) {			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			siHeader = null;
		}finally{
			siInformation 			= null;
			buyerParty 				= null;
			supplierParty 			= null;
			siIssueDate 			= null;
			siReferenceList 		= null;
			siReference 			= null;
			bpPartyIdentifierList	= null;
			buyerPartyId	 		= null;
			bpNameAddress			= null;
			spPartyIdentifierList	= null;
			supplierPartyId	    	= null;
			spNameAddress	    	= null;
			date 			    	= null;
		}
		return siHeader;
	}	
	/**
	 * This method set the ShippingInstructionsPurchaseOrderLineItem details for a single XML to generate.
	 * @param poLineNo
	 * @param shipInstNo
	 * @return SIPOLineItem
	 */
	private SIPOLineItem setSIPurchaseOrderLineItem(String poLineNo, String shipInstNo, String partNumber){
		SIPOLineItem siPoLineItem 	= null;
		POInformation poInformation = null;
		ProductIdentification prodIdentification = null;
		ProductIdentifier productIdentifier = null;
		try {
			B2BLogger.debug("ShippingInstructionsDAOImpl.setSIPurchaseOrderLineItem() method called");			
			siPoLineItem  = new SIPOLineItem();
			
			
			if(poLineNo != null && !"".equals(poLineNo.trim()))
				siPoLineItem.setPoLineItemNumber(poLineNo.trim());
			
			if(shipInstNo != null && !"".equals(shipInstNo.trim())){
				poInformation = new POInformation();
				poInformation.setPoNumber(shipInstNo.trim());
				siPoLineItem.setPoInformation(poInformation);			
			}
			
			//Added by Vineeta for tracker#497209 Starts
			if(partNumber!=null && !"".equals(partNumber.trim())){
				productIdentifier = new ProductIdentifier();
				productIdentifier.setPartNumber(partNumber);
				productIdentifier.setProductIdentifierAgency(IPixB2BConstants.Buyer);
				productIdentifier.setProductIdentifierType(IPixB2BConstants.PartNumber);
				prodIdentification = new ProductIdentification();
				prodIdentification.setProductIdentifier(productIdentifier);
				siPoLineItem.setProductIdentification(prodIdentification);
			}
			//Added by Vineeta for tracker#497209 Ends
			
			if((poLineNo == null || "".equals(poLineNo.trim())) 
					&& (shipInstNo == null || "".equals(shipInstNo.trim())))
				siPoLineItem = null;
			
			B2BLogger.debug("ShippingInstructionsDAOImpl.setSIPurchaseOrderLineItem() method return");
		} catch (RuntimeException e) {			
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			siPoLineItem = null;
		}finally{
			poInformation = null;
		}
		return siPoLineItem;
	}
	/**
	 * This method set the ShipToInformation details for a single XML to generate.
	 * @param lineItemNo
	 * @param contactName
	 * @param phone
	 * @param mobile
	 * @param email
	 * @param fax
	 * @param name1
	 * @param name2
	 * @param name3
	 * @param address1
	 * @param address2
	 * @param address3
	 * @param address4
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param country
	 * @param piVal
	 * @param uomQty
	 * @return ShipToInformation
	 */
	private ShipToInformation setShipToInformation(String lineItemNo, String contactName, String phone, String mobile,
			String email, String fax, String name1, String name2, String name3, String address1, String address2, String address3,
			String address4, String city, String state, String postalCode, String country, String piVal, String uomQty,String delLegSeqNumber,
			String carrierParty,String billToName1,String billToAddress1,String billToAddress2,String billToAddress3,
			String billToAddress4,String billToCity,String billToState,String billToPostalCode,String billToCountry,
			String additionalText, String otherPartyPiVal, String pricePerUnit,
			String freightPayAt,String locationPiVal,String supplierName, String customerOrderNum ,String invoiceNum){
		
		ShipToInformation shipToInfo 			= null;
		ShipToCharacteristics shipToCharacteristics = null;
		ShipToParty shipToParty 				= null;
		ArrayList stpPartyIdentifierList		= null;
		ShipToPartyPartyIdentifier partyIdentShip= null;
		ShipToPartyNameAddress nameAddressShip 	= null;
		ArrayList commonConShipList 			= null;
		ShipToPartyCommonContact shipToPartyCommonCont = null;
		ArrayList deliveryScheduleList 			= null;
		DeliverySchedule delSchedule 			= null;
		Quantity quantity						= null;
		Value val								= null;		
		DeliveryLegDS deliveryLeg					= null;
		
		CarrierPartyDS carrierPartyDS 				= null;
		CarPartyNmAddDS carPartyNmAddDS 			= null;
		OtherParty otherParty 						= null;
		ArrayList otherPartyList 					= null;
		OtherPartyNameAddress otherPartyNameAddress = null;
		OtherPartyIdenDS otherPartyIdenDS 			= null; 			
		ArrayList otherPartyIdentifierList			= null;
		
		PriceDetailsDS priceDetailsDS 				= null;
		PricePerUnitDS perUnit						= null;
		
		TermsOfDelivery termsOfDelivery				= null;
		DelOriginDS delOrigin						= null;
		DelOriginDateDS originDate					= null;
		LocationPartyDS locationPartyDS				= null;
		LocationPartyId partyId						= null;
		LocationPartyPartyIdentifierDS locationParIdenDS = null;
		ArrayList locationPartyIdenList				= null;
		LocationPartyNmAddDS nmAddDS				= null;
		DeliverySchedRef deliverySchedRef			= null;	
		AdditionalTextDS additionalTextDS			= null;
		ArrayList locPartyList						= null;
		ArrayList delLegList						= null;
		ArrayList delSchedRef 						= null;
		ArrayList addTextList						= null;
		PPValueDS valueDS							= null;
		
		
		try {
			B2BLogger.debug("ShippingInstructionsDAOImpl.setShipToInformation() method called");
			shipToInfo = new ShipToInformation();			
			
			shipToParty	= new ShipToParty();			
			shipToPartyCommonCont = new ShipToPartyCommonContact();			
			if(contactName != null && !"".equals(contactName.trim())){				
				shipToPartyCommonCont.setContactName(contactName.trim());
				shipToPartyCommonCont.setContactType(IPixB2BConstants.Warehouse);
				if(phone != null && !"".equals(phone.trim()))
					shipToPartyCommonCont.setPhone(phone.trim());			
				if(mobile != null && !"".equals(mobile.trim()))
					shipToPartyCommonCont.setMobile(mobile.trim());
				if(email != null && !"".equals(email.trim()))
					shipToPartyCommonCont.setEmail(email);
				if(fax!=null && !"".equals(fax.trim()))
					shipToPartyCommonCont.setFax(fax.trim());				
				commonConShipList = new ArrayList();
				commonConShipList.add(shipToPartyCommonCont);
				if(commonConShipList != null && commonConShipList.size()>0){
					shipToParty.setCommonContact(commonConShipList);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - commonConShipList is null");
				}
				
			}			
			
			if(name1 != null && !"".equals(name1.trim())){
			  nameAddressShip = new ShipToPartyNameAddress();
			  
				nameAddressShip.setName1(name1.trim());
				if(name2 != null && !"".equals(name2.trim()))
					nameAddressShip.setName2(name2.trim());
				if(name3 != null && !"".equals(name3.trim()))
					nameAddressShip.setName3(name3.trim());
				if(address1 != null && !"".equals(address1.trim()))
					nameAddressShip.setAddress1(address1.trim());			
				if(address2 != null && !"".equals(address2.trim()))
					nameAddressShip.setAddress2(address2.trim());
				if(address3 != null && !"".equals(address3.trim()))
					nameAddressShip.setAddress3(address3.trim());
				if(address4 != null && !"".equals(address4.trim()))
					nameAddressShip.setAddress4(address4.trim());
				if(city != null && !"".equals(city.trim()))
					nameAddressShip.setCity(city.trim());			
				if(state != null && !"".equals(state.trim()))
					nameAddressShip.setStateOrProvince(state.trim());			
				if(postalCode != null && !"".equals(postalCode.trim()))
					nameAddressShip.setPostalCode(postalCode.trim());			
				if(country != null && !"".equals(country.trim()))
					nameAddressShip.setCountry(country.trim());
				
				if(nameAddressShip != null){
				shipToParty.setNameAddress(nameAddressShip);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - nameAddressShip is null");
				}
			}
			if(piVal != null && !"".equals(piVal.trim())){
				piVal = piVal.replaceAll("-","");
				partyIdentShip = new ShipToPartyPartyIdentifier();
				partyIdentShip.setPartyIdentifierType(IPixB2BConstants.AssignedByBuyer);
				partyIdentShip.setPartyIdentifierValue(piVal.trim());
				stpPartyIdentifierList = new ArrayList();
				stpPartyIdentifierList.add(partyIdentShip);
				if(stpPartyIdentifierList != null && stpPartyIdentifierList.size()>0)
					shipToParty.setPartyIdentifierList(stpPartyIdentifierList);
				shipToParty.setPartyType(IPixB2BConstants.Warehouse);
				}else{
				shipToParty.setPartyType(IPixB2BConstants.CustomerFacility);
			}
			/*Setting Freight in "Terms of Delivery".*/
			if(freightPayAt != null && !freightPayAt.equals("")){
				termsOfDelivery = new TermsOfDelivery();
				termsOfDelivery.setFreightPayableAt(freightPayAt);
			}else{
				B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - freightPayAt is null");
			}
			
			
			shipToCharacteristics = new ShipToCharacteristics();
			if(shipToParty != null)
			shipToCharacteristics.setShipToParty(shipToParty);
			if(termsOfDelivery != null && !termsOfDelivery.equals(""))
			shipToCharacteristics.setTermsOfDelivery(termsOfDelivery);
			if(shipToCharacteristics != null && !shipToCharacteristics.equals(""))
			shipToInfo.setShipToCharacteristics(shipToCharacteristics);
			
			/*Setting Delivery Schedule  starts -->*/
			delSchedule = new DeliverySchedule();			
			if(lineItemNo != null && !"".equals(lineItemNo.trim()))
				delSchedule.setDeliveryLineNumber(lineItemNo.trim());			
			if(uomQty != null && !"".equals(uomQty.trim())){
				val	= new Value();
				val.setUom(IPixB2BConstants.Unit);
				val.setValValue(uomQty.trim());
				quantity = new Quantity();
				quantity.setVal(val);
				quantity.setQuantityType(IPixB2BConstants.Count);
				if(quantity != null)
				delSchedule.setQuantity(quantity);
			}
			
			if(pricePerUnit != null && !"".equals(pricePerUnit.trim())){
				
				perUnit = new PricePerUnitDS();
				CurrencyDS currencyDS = new CurrencyDS();
				currencyDS.setCurrencyType(IPixB2BConstants.USD);
				currencyDS.setCurrencyValue(pricePerUnit);
				
				valueDS = new PPValueDS();
				valueDS.setPpUOM(IPixB2BConstants.Unit);
				valueDS.setPpValue("1");
				
				perUnit.setCurrency(currencyDS);
				perUnit.setPpuValue(valueDS);
				
				priceDetailsDS = new PriceDetailsDS();
				priceDetailsDS.setPpunit(perUnit);
				priceDetailsDS.setQtyBasis("Volume");
				
				delSchedule.setPriceDetails(priceDetailsDS);
			}else{
				B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - priceDetailsDS is null");
			}
			/*Setting Delivery Leg  starts -->*/
			
			if(delLegSeqNumber != null && !"".equals(delLegSeqNumber) && delLegSeqNumber.equals("1")){
				deliveryLeg = new DeliveryLegDS();
				deliveryLeg.setDelLegSeqNumber(delLegSeqNumber);
				
				if(locationPiVal != null && !"".equals(locationPiVal.trim())){
					delOrigin = new DelOriginDS();
					locationPartyDS = new LocationPartyDS();
					locationPiVal = locationPiVal.replaceAll("-","");
					locationParIdenDS = new LocationPartyPartyIdentifierDS();
					locationParIdenDS.setPartyIdentifierType(IPixB2BConstants.StandardAddressNumber);
					locationParIdenDS.setPartyIdentifierValue(locationPiVal.trim());
					locationPartyIdenList = new ArrayList();
					locationPartyIdenList.add(locationParIdenDS);
					locationPartyDS.setLocationPartyIdList(locationPartyIdenList);
					locationPartyDS.setPartyType(IPixB2BConstants.SUPPLIER);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - locationPiVal is null");
				}
				
				if(supplierName != null && !"".equals(supplierName.trim())){
					nmAddDS = new LocationPartyNmAddDS();
					nmAddDS.setName1(supplierName.trim());
					locationPartyDS.setLocPartyNmAdd(nmAddDS);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - supplierName is null");
				}
				if(locationPartyDS != null){
					delOrigin.setLocationParty(locationPartyDS);
					deliveryLeg.setDelOrigin(delOrigin);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - locationPartyDS is null");
				}
				
				if(carrierParty != null && !carrierParty.equals("")){
					carPartyNmAddDS = new CarPartyNmAddDS();
					if(carrierParty != null && !"".equals(carrierParty.trim())){
						carPartyNmAddDS.setName1(carrierParty.trim());
						carrierPartyDS = new CarrierPartyDS();
						carrierPartyDS.setCarPartyNmAdd(carPartyNmAddDS);
					}
					deliveryLeg.setCarParty(carrierPartyDS);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - carrierPartyDS is null");
				}
				
				
				
				//if(freightPayAt != null && !freightPayAt.equals("") && freightPayAt.contains("3RD")){
				if(billToName1 != null && !"".equals(billToName1.trim())){		
				otherParty = new OtherParty();
						if(otherPartyPiVal != null && !"".equals(otherPartyPiVal.trim())){
							otherPartyIdenDS = new OtherPartyIdenDS();
							otherPartyIdenDS.setPartyIdentifierType(IPixB2BConstants.PayerAccountNumber);
							otherPartyIdenDS.setPartyIdentifierValue(otherPartyPiVal.trim());
							otherPartyIdentifierList = new ArrayList();
							otherPartyIdentifierList.add(otherPartyIdenDS);
							otherParty.setOtherPartyIdList(otherPartyIdentifierList);
						}
						otherParty.setOtherPartyType(IPixB2BConstants.FreightPayer);
						
							otherPartyNameAddress = new OtherPartyNameAddress();
							
							otherPartyNameAddress.setName1(billToName1.trim());
							if(billToAddress1 != null && !"".equals(billToAddress1.trim()))
								otherPartyNameAddress.setAddress1(billToAddress1.trim());			
							if(billToAddress2 != null && !"".equals(billToAddress2.trim()))
								otherPartyNameAddress.setAddress2(billToAddress2.trim());
							if(billToAddress3 != null && !"".equals(billToAddress3.trim()))
								otherPartyNameAddress.setAddress3(billToAddress3.trim());
							if(billToAddress4 != null && !"".equals(billToAddress4.trim()))
								otherPartyNameAddress.setAddress4(billToAddress4.trim());
							if(billToCity != null && !"".equals(billToCity.trim()))
								otherPartyNameAddress.setCity(billToCity.trim());			
							if(billToState != null && !"".equals(billToState.trim()))
								otherPartyNameAddress.setStateOrProvince(billToState.trim());			
							if(billToPostalCode != null && !"".equals(billToPostalCode.trim()))
								otherPartyNameAddress.setPostalCode(billToPostalCode.trim());			
							if(billToCountry != null && !"".equals(billToCountry.trim()))
								otherPartyNameAddress.setCountry(billToCountry.trim());
							
							otherParty.setOtherPartyNameAddress(otherPartyNameAddress);
						
						otherPartyList = new ArrayList();
						otherPartyList.add(otherParty);			
						deliveryLeg.setOtherParty(otherPartyList);
				}
				/*}
			else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - freightPayAt is null");
				}*/
				
				if(deliveryLeg != null){
					delLegList = new ArrayList();
					delLegList.add(deliveryLeg);
					delSchedule.setDeliveryLegList(delLegList);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - deliveryLeg is null");
				}
				
			}
			
			/*Setting Delivery Leg  ends <--*/
			
			/*Setting DELIVERY SCHEDULE REFRENCE starts -->*/
			//if(shipToParty.equals(IPixB2BConstants.CustomerFacility)){
				deliverySchedRef = new DeliverySchedRef();
				delSchedRef = new ArrayList();
				if(customerOrderNum != null && !customerOrderNum.equals("")){
					deliverySchedRef = new DeliverySchedRef();
					deliverySchedRef.setDeliverySchedValue(customerOrderNum);
					deliverySchedRef.setDeliverySchedType(IPixB2BConstants.customerOrderNumber);
					delSchedRef.add(deliverySchedRef);
				}
				if(invoiceNum != null && !invoiceNum.equals("")){
					deliverySchedRef = new DeliverySchedRef();
					deliverySchedRef.setDeliverySchedValue(invoiceNum);
					deliverySchedRef.setDeliverySchedType(IPixB2BConstants.invoiceNumber);
					delSchedRef.add(deliverySchedRef);
				}
				if(delSchedRef != null && delSchedRef.size() >0){
					delSchedule.setDeliveryScheduleRef(delSchedRef);
				}else{
					B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - delSchedRef is null");
				}
			//}
			/*Setting DELIVERY SCHEDULE REFRENCE ends -->*/
			/*Setting Additional text starts -->*/
			if(shipToParty.getPartyType().equals(IPixB2BConstants.CustomerFacility)){
				if(additionalText != null && !additionalText.equals("")){
					addTextList 	 = new ArrayList();
					StringTokenizer st = new StringTokenizer(additionalText, ";");
					//Ashish:08/16/2012- CP# 480207 - The Shipping XML generated contains empty <AdditionalText> under <DeliverySchedule>: Start
					while(st.hasMoreTokens()) {
						String key = st.nextToken();
						if(null!=key && !"\r".equalsIgnoreCase(key)){
							additionalTextDS = new AdditionalTextDS();
							additionalTextDS.setAdditionalText(key);
							addTextList.add(additionalTextDS);
						}
					}
					//Ashish:08/16/2012- CP# 480207 - The Shipping XML generated contains empty <AdditionalText> under <DeliverySchedule>: End
					if(addTextList != null && addTextList.size()>0){
						delSchedule.setAdditionalTextList(addTextList);	
					}
					
				}
			}else{
				B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - addTextList is null");
			}
			/*Setting DELIVERY SCHEDULE REFRENCE ends -->*/
			//Setting delSchedule -->
			if(delSchedule != null){
			deliveryScheduleList = new ArrayList();
			deliveryScheduleList.add(delSchedule);			
			}else{
				B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - delSchedule is null");
			}
					
			if(deliveryScheduleList != null && deliveryScheduleList.size()>0){
				shipToInfo.setDeliverySchedule(deliveryScheduleList);
			}else{
				B2BLogger.info("ShippingInstructionsDAOImpl.setShipToInformation - deliveryScheduleList is null");
			}
			
			
			//For Dropshipment ends -->
			
			
			B2BLogger.debug("ShippingInstructionsDAOImpl.setShipToInformation() method return");
		}  catch (RuntimeException e) {			
			B2BLogger.error("RuntimeException in ShippingInstructionsDAOImpl.setShipToInformation() :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception in ShippingInstructionsDAOImpl.setShipToInformation()",e);
			shipToInfo = null;			
		}finally{
			shipToCharacteristics = null;
			stpPartyIdentifierList= null;
			shipToParty 		= null;
			partyIdentShip 		= null;
			nameAddressShip 	= null;
			commonConShipList	= null;
			shipToPartyCommonCont= null;
			deliveryScheduleList= null;
			delSchedule 		= null;
			quantity			= null;
			val					= null;
			delSchedule			= null;
			deliveryLeg			= null;
			shipToParty			= null;
			deliverySchedRef	= null;
			delSchedRef			= null;
			priceDetailsDS		= null;
			valueDS				= null;
			delLegSeqNumber 	= null;
		}
		return shipToInfo;
	}
	/**
	 * This method set the ShippingInstructionsLineItemByProduct details for a single XML to generate.
	 * @param itemStatusType
	 * @param lineItemNo
	 * @param shipToInfoList
	 * @return SILineItemByProduct
	 */
	private SILineItemByProduct setSILineItemByProduct(String itemStatusType, String lineItemNo, ArrayList shipToInfoList){
		SILineItemByProduct siLineItemByProduct	= null;

		try {
			B2BLogger.debug("ShippingInstructionsDAOImpl.setSILineItemByProduct() method called");
			siLineItemByProduct = new SILineItemByProduct();
			
			if(itemStatusType != null && !"".equals(itemStatusType.trim()))
				siLineItemByProduct.setSiLineItemStatusType(itemStatusType.trim());
			
			if(lineItemNo != null && !"".equals(lineItemNo.trim()))
				siLineItemByProduct.setSiLineItemNumber(lineItemNo.trim());
			
			if(shipToInfoList != null && shipToInfoList.size()>0)
				siLineItemByProduct.setShipToInformation(shipToInfoList);
			
			B2BLogger.debug("ShippingInstructionsDAOImpl.setSILineItemByProduct() method return");
		}  catch (RuntimeException e) {			
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			siLineItemByProduct = null;			
		}
		return siLineItemByProduct;
	}
}