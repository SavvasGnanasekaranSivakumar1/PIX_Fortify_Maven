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
 * Title		: 	IPixB2BConstants.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	09 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils.constants;

import com.pearson.pixb2b.vendor.utils.IConfigConstants;

/**
 * IPixB2BConstants interface contains all common constants 
 * that are using in the application.
 * 
 * @author Yogesh Tyagi
 */
public interface IPixB2BConstants extends IXmlConstants{
	//Outbound Transactions mapping files
	public static final String PO_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_purchaseorder_mapping.xml";
	public static final String SI_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_shipmentinstructions_mapping.xml";
	public static final String BS_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_bookspecification_mapping.xml";
	
	public static final String BA_Mapping_XML 		= IConfigConstants.FILE_PATH+"/mapping/b2b_businessacknowledgement_mapping.xml";
	
	//Inbound Transactions mapping files
	public static final String OS_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_orderstatus_mapping.xml";
	public static final String OC_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_orderconfirmation_mapping.xml";
	public static final String IS_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_inventorystatus_mapping.xml";
	public static final String IC_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_inventorychange_mapping.xml";
	public static final String US_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_usage_mapping.xml";
	public static final String GR_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_goodsreceipt_mapping.xml";
	public static final String DM_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_deliverymessage_mapping.xml";
	public static final String DMW_Mapping_XML		= IConfigConstants.FILE_PATH+"/mapping/b2b_deliverymessagewood_mapping.xml";
	public static final String INVO_Mapping_XML  	= IConfigConstants.FILE_PATH+"/mapping/b2b_invoice_mapping.xml";
	public static final String transactionSchemaPath= "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"";
	
	public static final String dateFormat_dd_MMM_yyyy 		= "dd-MMM-yyyy";
	public static final String dateFormat_yyyy_MM_dd 		= "yyyy-MM-dd";
	public static final String dateFormat_MM_dd_yyyy		= "MM/dd/yyyy";
	public static final String timestampFormat_MM_dd_yyyy	= "MM-dd-yyyy'T'HH:mm:ss";
	public static final String timestampFormat_yyyy_MM_dd	= "yyyy-MM-dd'T'HH:mm:ss";
	
	public static final int ZERO					= 0;
	
	public static final int ONE						= 1;
	public static final int TWO						= 2;
	public static final int THREE					= 3;
	public static final int FOUR					= 4;
	public static final int FIVE					= 5;
	public static final int SIX						= 6;
	public static final int SEVEN					= 7;
	public static final int EIGHT					= 8;
	public static final int NINE					= 9;
	public static final int TEN						= 10;
	public static final int ELEVEN					= 11;
	public static final int TWELVE					= 12;
	public static final int THIRTEEN				= 13;
	public static final int FOURTEEN				= 14;
	public static final int FIFTEEN					= 15;
	public static final int SIXTEEN					= 16;
	public static final int SEVENTEEN				= 17;
	public static final int EIGHTEEN				= 18;
	public static final int NINETEEN				= 19;
	public static final int TWENTY					= 20;
	public static final int TWENTYONE				= 21;
	public static final int TWENTYTWO				= 22;
	public static final int TWENTYTHREE				= 23;
	public static final int TWENTYFOUR				= 24;
	public static final int TWENTYFIVE				= 25;
	
	public static final String PIPE					= "|";
	public static final String COMMA				= ",";
	
	public static final String B2B					= "B2B";
	public static final String JavaB2B				= "JavaB2B";
	public static final String XML					= "XML";
	public static final String VENDOR				= "VENDOR";
	
	public static final String processType_OUTBOUND	= "OUT";
	public static final String processType_INBOUND	= "IN";
	
	//Outbound Transactions
	public static final String PurchaseOrder 	    = "PurchaseOrder";
	public static final String transType_PO			= "PO";
	public static final String transType_POR		= "POR";
	
	public static final String ShippingInstructions = "ShippingInstructions";
	public static final String transType_SI			= "SI";
	public static final String transType_SIP		= "SIP";
	
	public static final String BookSpecification 	= "BookSpecification";
	public static final String transType_BS			= "BS";
	public static final String transType_BSP		= "BSP";
	
	public static final String DeliveryMessage 	    = "DeliveryMessageBook";
	public static final String DeliveryMessageWood  = "DeliveryMessageWood";
	public static final String transType_DM			= "DM";
	public static final String transType_DME		= "DME";
	
	public static final String BusinessAcknowledgement = "BusinessAcknowledgement";
	public static final String transType_BA			= "BA";
	public static final String transType_ACK		= "ACK";
	
	//Inbound Transactions
	public static final String OrderStatus 	    	= "OrderStatus";
	public static final String transType_OS			= "OS";
	public static final String transType_OST		= "OST";
	
	public static final String OrderConfirmation 	= "OrderConfirmation";
	public static final String transType_OC			= "OC";
	public static final String transType_OCO		= "OCO";
	
	public static final String InventoryChange 	    = "InventoryChange";
	public static final String transType_IC			= "IA";
	public static final String transType_INC		= "INC";
	
	public static final String InventoryStatus 	   	= "InventoryStatus";
	public static final String transType_IS			= "IS";
	public static final String transType_INV		= "INV";
	
	public static final String Usage 	    		= "Usage";
	public static final String transType_UN			= "UN";
	public static final String transType_USG		= "USG";
	
	public static final String GoodsReceipt 	    = "GoodsReceipt";
	public static final String transType_GR			= "GR";
	public static final String transType_GRE		= "GRE";
	
	public static final String Invoice 	   			= "Invoice";
	public static final String transType_INVO		= "INVO";
	
	public static final String Plan 	   			= "Plan";
	public static final String transType_PLA		= "PLA";
		
	//public static final String AdvanceShipmentNotification = "AdvanceShipmentNotification";
	//public static final String transType_AS			= "AS";
	//public static final String transType_ASN		= "ASN";
	
	public static final String AdvanceShipmentNotification 	= "AdvanceShipmentNotification";
	public static final String ShipmentStatus 				= "DeliveryMessageBook";
	public static final String transType_DMB				= "DMB";
	
	public static final int table_id_35				= 35;
	public static final int table_id_36				= 36;
	public static final int table_id_37				= 37;
	public static final int table_id_50				= 50;
	
	public static final int status_id_19_Original	= 19;
	public static final int status_id_9_New			= 9;
	public static final int status_id_14_New		= 14;
	
	public static final String PO_ID				= "PO_ID";
	public static final String PO_VERSION			= "PO_VERSION";
	public static final String ORDER_STATUS_ID		= "ORDER_STATUS_ID";
	public static final String INVENTORY_CHANGE_ID	= "INVENTORY_CHANGE_ID";
	public static final String INVENTORY_VERSION	= "INVENTORY_VERSION";
	public static final String INVENTORY_ID			= "INVENTORY_ID";
	public static final String USAGE_ID				= "USAGE_ID";
	public static final String GOODSRECEIPT_ID		= "GOODSRECEIPT_ID";
	public static final String INVOICE_ID			= "INVOICE_ID";
	public static final String INVOICE_NUMBER		= "INVOICE_NUMBER";
	public static final String INVOICE_TRANS_ID		= "INVOICE_TRANS_ID";
	public static final String PO_TRANS_ID			= "PO_TRANS_ID";
	public static final String DELIVERY_MESSAGE_ID	= "DELIVERY_MESSAGE_ID";
	
	public static final String PIX_TO_XML			= "PIX_TO_XML";
	public static final String VEN_TO_PIX			= "VEN_TO_PIX";
	public static final String VENDOR_SAN			= "VENDOR_SAN";
	public static final String FILE_PATH			= "FILE_PATH";	
	public static final String TRANS_ID				= "TRANS_ID";
	public static final String DOCUMENT_DATE	    = "DOCUMENT_DATE";
	public static final String TRANS_NAME			= "TRANS_NAME";
	public static final String XML_TRANS_ID			= "XML_TRANS_ID";

	public static final String fileExtn_XML			= ".xml";
	public static final String fileExtn_XSD			= ".xsd";
	
	public static final String statusType_GEN		= "GEN"; //XML Generation
	public static final String status_GU			= "GU";
	public static final String status_GS			= "GS";
	public static final String status_GE			= "GE";
	
	public static final String statusType_RED		= "RED"; //XML Reading
	public static final String status_RU			= "RU";
	public static final String status_RS			= "RS";
	public static final String status_RE			= "RE";
	
	public static final String statusType_FTP		= "FTP"; //FTP
	public static final String status_FU			= "FU";
	public static final String status_FS			= "FS";
	public static final String status_FE			= "FE";
	
	public static final String statusType_ARC		= "ARC"; //Archive
	public static final String status_AU			= "AU";
	public static final String status_AS			= "AS";
	public static final String status_AE			= "AE";
	
	public static final String statusType_MAIL		= "MAIL"; //Email
	public static final String status_MU			= "MU";
	public static final String status_MS			= "MS";
	public static final String status_ME			= "ME";
	
	public static final String statusType_ACK		= "ACK"; //BusinessAcknowledgement
	public static final String status_KU			= "KU";
	public static final String status_KS			= "KS";
	public static final String status_KE			= "KE";
	
	public static final String VALID_XML_LIST		= "VALID_XML_LIST";
	public static final String INVALID_XML_LIST		= "INVALID_XML_LIST";
	public static final String ERROR_DTO			= "ERROR_DTO";
	public static final String TRANS_STATUS_DTO		= "TRANS_STATUS_DTO";
	
	public static final String flag_Y				= "Y";
	public static final String flag_N				= "N";
	
	public static final String flag_F				= "F";
	public static final String flag_M				= "M";
	public static final String flag_P				= "P";
	public static final String flag_R				= "R";
	public static final String flag_S				= "S";
	public static final String flag_U				= "U";
	public static final String flag_V				= "V";
	public static final String flag_B				= "B";
	public static final String GR					= "GR";
	public static final String block_N				= "N";
	public static final String block_Y				= "Y";
	public static final String block_S				= "S";
	public static final String BUYER				= "Buyer";
	public static final String BISAC				= "BISAC";
	public static final String DIVISIONIDENTIFIER	= "DivisionIdentifier";
	
	public static final String TRUE					= "TRUE";
	public static final String FALSE				= "FALSE";
	
	public static final String Success				= "Success";
	public static final String Failure				= "Failure";

	public static final int ERROR_STRING_LENGTH		= 300;	
	public static final String FTP_TRANSFER_COMPLETE= "226 Transfer complete";
	
	public static final String SAN					= "SAN";
	public static final String OTH					= "OTH";
	
	//Email Constants start
	public static final String FROM_ADDRESS 		= "FROM_ADDRESS";
	public static final String TO_ADDRESS 			= "TO_ADDRESS";
	public static final String CC_ADDRESS 			= "CC_ADDRESS";
	public static final String BCC_ADDRESS 			= "BCC_ADDRESS";
	public static final String SUBJECT 				= "SUBJECT";
	public static final String BODY_CONTENTS 		= "BODY_CONTENTS";	
	public static final String MIME_TYPE_TEXT_HTML	= "text/html;charset=UTF-8";
	
//	public static final String EMAIL_STATUS_SUCCESS = "EMAIL_SEND_SUCCESS";
//	public static final String EMAIL_STATUS_FAILED 	= "EMAIL_SEND_FAILED";
	public static final int EMAIL_SEND_SUCCESS 		= 1;
	public static final int EMAIL_SEND_FAILED 		= 0;
	public static final String EMAIL_STATUS_NO_EMAIL= "NO_EMAIL_DATA_TO_SEND";
	
	public static final int STATUS_PASS = 1;
	public static final int STATUS_FAIL = -1;
	public static final int STATUS_PARTIAL = 0;
	public static final String EMAIL_BA_XML_JOB_RUN 			= "BA_XML_JOB_RUN";
	public static final String EMAIL_BA_XML_VALIDATION_FAILURE 	= "BA_XML_VALIDATION_FAILURE";
	public static final String EMAIL_BA_PO_REPORT_JOB_RUN 		= "EMAIL_BA_PO_REPORT_JOB_RUN";
	public static final String FLAG_YES				= "Y";
	public static final String FLAG_NO				= "N";

	//Email Constants end
	
	public static final String OS_ISSUE_DATE		= "OS ISSUE DATE";
	public static final String OC_ISSUE_DATE		= "OC ISSUE DATE";
	public static final String IC_ISSUE_DATE		= "IC ISSUE DATE";
	public static final String IS_ISSUE_DATE		= "IS ISSUE DATE";
	public static final String USAGE_ISSUE_DATE		= "USAGE ISSUE DATE";
	public static final String GR_ISSUE_DATE		= "GR ISSUE DATE";
	public static final String BA_ISSUE_DATE		= "BA ISSUE DATE";
	public static final String DM_ISSUE_DATE		= "DM ISSUE DATE";
	public static final String INVO_ISSUE_DATE		= "INVOICE ISSUE DATE";
	public static final String ASN_ISSUE_DATE		= "ASN ISSUE DATE";
	
	public static final Long TIME_DELAY				= 1000l;
	public static final String DM_TABLE_NAME		= "PIX_ASN";
	public static final String DM_TABLE_ID			= "21";
	public static final String MSG_TYPE_ID			= "13";
	public static final String DM_COMMENTS			= "XML Generated Delivery Message";
	public static final String DM_APPROVAL_FLAG		= "N";
	public static final String PO_NO				= "PO_NO";
	public static final String PRODUCT_CODE			= "PRODUCT_CODE";
	
	public static final String ASN_COMMENTS			= "XML Generated ASN";
	public static final String BUYER_PARTY_LINE_NO	= "1";
	public static final String SUPP_PARTY_LINE_NO	= "2";
	public static final String SHIP_PARTY_LINE_NO	= "3";
	
	public static final String BUYER_PARTY_TYPE		= "B";
	public static final String SUPP_PARTY_TYPE		= "V";
	public static final String SHIP_PARTY_TYPE		= "S";
	public static final String BOX_PACKING_FLAG_F	= "F";
	public static final String BOX_PACKING_FLAG_P	= "P";
	
	public static final String ASN_PROCESSED_FLAG_N	= "N";
	public static final String ASN_ID				= "ASN_ID";
	public static final String PURCHASEORDERNUMBER	= "PurchaseOrderNumber";
	
	public static final String	SALES_OFFICE = "SalesOffice";
	
	public static final String	ORIGINAL_SUPPLIER = "OriginalSupplier";
}