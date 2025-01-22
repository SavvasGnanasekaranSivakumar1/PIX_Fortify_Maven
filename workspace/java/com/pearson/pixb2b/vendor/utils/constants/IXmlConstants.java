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
 * Title		: 	IXmlConstants.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	09 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils.constants;
/**
 * IXmlConstants interface contains all XML constants 
 * that are using in the application.
 * 
 * @author Yogesh Tyagi
 */
public interface IXmlConstants extends IErrorConstants{
	//XML tag paths
	public static final String tag_papiNetEnvelope			= "/pne:papiNetEnvelope";
	public static final String tag_Document					= "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document";
	public static final String attr_DocumentName			= "DocumentName";
	public static final String tag_DocumentNumber			= "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document/pne:DocumentNumber";
	public static final String tag_DocumentDate    			= "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document/pne:DocumentDate";
	//public static final String tag_Date = "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document/pne:DocumentDate/pne:Date";
	public static final String tag_Year = "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document/pne:DocumentDate/pne:Date/pne:Year";
	public static final String tag_Month = "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document/pne:DocumentDate/pne:Date/pne:Month";
	public static final String tag_Day = "/pne:papiNetEnvelope/pne:PayloadInfo/pne:Document/pne:DocumentDate/pne:Date/pne:Day";

	public static final String tag_OS_Supp_PartyIdentifier 	 = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/OrderStatus/OrderStatusDetail/SupplierParty/PartyIdentifier";
	public static final String attr_OSPartyIdentifierType	 = "PartyIdentifierType";
	public static final String tag_OC_Supp_PartyIdentifier   = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/OrderConfirmation/OrderConfirmationHeader/SupplierParty/PartyIdentifier";
	public static final String attr_OCPartyIdentifierType	 = "PartyIdentifierType";
	public static final String tag_USG_Supp_PartyIdentifier	 = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/Usage/UsageHeader/EndUserParty/PartyIdentifier";
	public static final String tag_INVO_Supp_PartyIdentifier = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/Invoice/InvoiceHeader/SupplierParty/PartyIdentifier";
	public static final String tag_GR_Supp_PartyIdentifier   = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/GoodsReceipt/GoodsReceiptHeader/ShipToCharacteristics/ShipToParty/PartyIdentifier";
	public static final String tag_IA_Supp_PartyIdentifier   = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/InventoryChange/InventoryChangeHeader/LocationParty/PartyIdentifier";
	public static final String tag_IS_Supp_PartyIdentifier   = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/InventoryStatus/InventoryStatusHeader/LocationParty/PartyIdentifier";
	public static final String tag_DM_Supp_PartyIdentifier   = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/DeliveryMessageBook/DeliveryMessageBookHeader/SupplierParty/PartyIdentifier";
	public static final String tag_DMW_Supp_PartyIdentifier  = "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/DeliveryMessageWood/DeliveryMessageWoodHeader/SupplierParty/PartyIdentifier";
	
	public static final String tag_OS_Trans_Id 			= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/OrderStatus/OrderStatusHeader/OrderStatusReference";
	public static final String attr_OSReferenceType 	= "OrderStatusReferenceType";
	public static final String tag_OC_Trans_Id 			= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/OrderConfirmation/OrderConfirmationHeader/OrderConfirmationReference";
	public static final String attr_OCReferenceType 	= "OrderConfirmationReferenceType";
	public static final String tag_USG_Trans_Id 		= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/Usage/UsageHeader/UsageHeaderReference";
	public static final String attr_UsageReferenceType 	= "UsageReferenceType";
	public static final String tag_INVO_Trans_Id 		= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/Invoice/InvoiceHeader/InvoiceReference";
	public static final String attr_InvoReferenceType 	= "InvoiceReferenceType";
	public static final String tag_GR_Trans_Id 			= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/GoodsReceipt/GoodsReceiptHeader/GoodsReceiptReference";
	public static final String attr_GRReferenceType 	= "GoodsReceiptReferenceType";
	public static final String tag_IA_Trans_Id 			= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/InventoryChange/InventoryChangeHeader/InventoryChangeReference";
	public static final String attr_IAReferenceType 	= "InventoryChangeReferenceType";
	public static final String tag_IS_Trans_Id 			= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/InventoryStatus/InventoryStatusHeader/InventoryStatusReference";
	public static final String attr_ISReferenceType 	= "InventoryStatusReferenceType";
	public static final String tag_DM_Trans_Id 			= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/DeliveryMessageBook/DeliveryMessageBookHeader/DeliveryMessageReference";
	public static final String attr_DMReferenceType 	= "DeliveryMessageReferenceType";
	public static final String tag_DMW_Trans_Id 		= "/pne:papiNetEnvelope/pne:Payload/pne:BusinessDocument/DeliveryMessageWood/DeliveryMessageWoodHeader/DeliveryMessageReference";
	public static final String attr_DMWReferenceType 	= "DeliveryMessageReferenceType";

	public static final String NoAction 	      			= "NoAction";
	public static final String Pending 	      				= "Pending";
	public static final String BudgetCenter 	      		= "BudgetCenter";
	public static final String SpecificationNumber 	      	= "SpecificationReferenceNumber";
	public static final String SpecificationVersion		  	= "SpecificationVersion";
	public static final String ShippingInstructionsNumber 	= "ShippingInstructionsNumber";
	public static final String ISBN10  				 	  	= "ISBN10";
	public static final String ISBN13  				 	  	= "ISBN13";
	public static final String Title                 	  	= "Title";
	public static final String Author                	  	= "Author";
	public static final String Edition                    	= "Edition";
	public static final String Copyright	              	= "Copyright";
	public static final String PrintingNumber  		      	= "PrintingNumber";
	public static final String TransactionID  		      	= "TransactionID";
	public static final String PupilsTeachers             	= "PupilsTeachers";
	public static final String ReferenceNumber            	= "ReferenceNumber";	
	public static final String Count            		  	= "Count";
	public static final String Unit           		      	= "Unit";
	public static final String USD          		      	= "USD";
	public static final String Language					  	= "eng";
	public static final String AssignedByBuyer			  	= "AssignedByBuyer";
	public static final String Purchaser				  	= "Purchaser";
	public static final String StandardAddressNumber	  	= "StandardAddressNumber";
	public static final String CustomerService			  	= "CustomerService";
	public static final String ShipTo 					  	= "ShipTo";
	public static final String ShipInstructions			  	= "See Shipping Instructions";
	public static final String Buyer					  	= "Buyer";
	public static final String PartNumber				  	= "PartNumber";
	public static final String GrossWeight				  	= "GrossWeight";
	public static final String Pound 						= "Pound";
	public static final String ShippingInstructionsRouting	= "ShippingInstructionsRouting";
	public static final String Warehouse				  	= "Warehouse";
	public static final String FinishedGoods				= "FinishedGoods";
	public static final String BoundBookDate				= "BoundBookDate";
	public static final String ShipmentRequestedDate		= "ShipmentRequestedDate";
	public static final String OnPressDate					= "OnPressDate";
	public static final String DeliveryDate					= "DeliveryDate";
	public static final String Other						= "Other";
	public static final String JobNumber					= "JobNumber";
	public static final String AvailableToShipDate			= "AvailableToShipDate";
	public static final String Material						= "Material";
	public static final String PrinterFacility				= "PrinterFacility";	
	public static final String Damaged					  	= "Damaged";
	public static final String Balanced					  	= "Balance";
	public static final String Delivered                    = "Delivered";
	public static final String CustomerFacility				= "CustomerFacility";
	public static final String FreightPayer					= "FreightPayer";
	public static final String PayerAccountNumber			= "PayerAccountNumber";
	public static final String SUPPLIER						= "Supplier";
	public static final String customerOrderNumber			= "CustomerOrderNumber";
	public static final String invoiceNumber				= "InvoiceNumber";
}