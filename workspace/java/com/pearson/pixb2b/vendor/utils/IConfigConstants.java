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
 * Title		: 	IConfigConstants.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;
/**
 * IConfigConstants interface contains all common constants 
 * that are using in the application.
 * 
 * @author Yogesh Tyagi
 */
public interface IConfigConstants {
	public static final String FILE_PATH  						= System.getProperty("FILEPATH");
	public static final String CONFIG_FILE  					= System.getProperty("CONFIGFILE");
	//////////////////////////// CONFIGURABLE CONSTANTS read FROM .config/.properties FILE /////////////////////////
	
	//DataSource and Schema names for database connection
	public static final String INBOUND_SOCKET_PORT				= ConfigReader.getProperty ("INBOUND_SOCKET_PORT");
	public static final String OUTBOUND_SOCKET_PORT				= ConfigReader.getProperty ("OUTBOUND_SOCKET_PORT");

	public static final String PIX_DB_DRIVER_CLASS				= ConfigReader.getProperty ("PIX_DB_DRIVER_CLASS");
	public static final String PIX_DB_URL						= ConfigReader.getProperty ("PIX_DB_URL");
	public static final String PIX_DB_USERNAME					= ConfigReader.getProperty ("PIX_DB_USERNAME");
	public static final String PIX_DB_PWD						= ConfigReader.getProperty ("PIX_DB_PWD");
	public static final String PIX_DB_SCHEMA_NAME				= ConfigReader.getProperty ("PIX_DB_SCHEMA_NAME");
	public static final String CONNECTION_POOLING				= ConfigReader.getProperty ("CONNECTION_POOLING");
	
	// papiNetEnvelope properties
	public static final String pne_PayloadInfo_TestFlag			= ConfigReader.getProperty ("pne_PayloadInfo_TestFlag");
	public static final String pne_TransmissionInfo_SequenceNumber = ConfigReader.getProperty ("pne_TransmissionInfo_SequenceNumber");
	public static final String pne_Schema_Version				= ConfigReader.getProperty ("pne_Schema_Version");
	public static final String pne_Schema_Build					= ConfigReader.getProperty ("pne_Schema_Build");
	public static final String pne_Schema_URL					= ConfigReader.getProperty ("pne_Schema_URL");
	
	// SCHEMA(XSD) local path in application context
	public static final String SCHEMA_papiNetEnvelope			= FILE_PATH+ConfigReader.getProperty ("SCHEMA_papiNetEnvelope");
	
	public static final String SCHEMA_PurchaseOrder				= FILE_PATH+ConfigReader.getProperty ("SCHEMA_PurchaseOrder");
	public static final String SCHEMA_ShippingInstructions		= FILE_PATH+ConfigReader.getProperty ("SCHEMA_ShippingInstructions");
	public static final String SCHEMA_BookSpecification			= FILE_PATH+ConfigReader.getProperty ("SCHEMA_BookSpecification");
	
	public static final String SCHEMA_BusinessAcknowledgement	= FILE_PATH+ConfigReader.getProperty ("SCHEMA_BusinessAcknowledgement");
	public static final String SCHEMA_DeliveryMessage			= FILE_PATH+ConfigReader.getProperty ("SCHEMA_DeliveryMessage");
	public static final String SCHEMA_DeliveryMessageWood		= FILE_PATH+ConfigReader.getProperty ("SCHEMA_DeliveryMessageWood");
	public static final String SCHEMA_OrderStatus				= FILE_PATH+ConfigReader.getProperty ("SCHEMA_OrderStatus");
	public static final String SCHEMA_OrderConfirmation			= FILE_PATH+ConfigReader.getProperty ("SCHEMA_OrderConfirmation");
	public static final String SCHEMA_InventoryChange			= FILE_PATH+ConfigReader.getProperty ("SCHEMA_InventoryChange");
	public static final String SCHEMA_InventoryStatus			= FILE_PATH+ConfigReader.getProperty ("SCHEMA_InventoryStatus");
	public static final String SCHEMA_Usage						= FILE_PATH+ConfigReader.getProperty ("SCHEMA_Usage");
	public static final String SCHEMA_GoodsReceipt				= FILE_PATH+ConfigReader.getProperty ("SCHEMA_GoodsReceipt");
	public static final String SCHEMA_Invoice					= FILE_PATH+ConfigReader.getProperty ("SCHEMA_Invoice");
	
	// XML generation and directory details for XML store
	public static final String OUTBOUND_DIR_GENERATED_XML		= ConfigReader.getProperty ("OUTBOUND_DIR_GENERATED_XML");
	public static final String OUTBOUND_DIR_ARCHIVE_XML_VALID	= ConfigReader.getProperty ("OUTBOUND_DIR_ARCHIVE_XML_VALID");
	public static final String OUTBOUND_DIR_ARCHIVE_XML_INVALID	= ConfigReader.getProperty ("OUTBOUND_DIR_ARCHIVE_XML_INVALID");
	
	public static final String INBOUND_DIR_DOWNLOAD_VENDOR_XML	= ConfigReader.getProperty ("INBOUND_DIR_DOWNLOAD_VENDOR_XML");
	public static final String INBOUND_DIR_ARCHIVE_XML_VALID	= ConfigReader.getProperty ("INBOUND_DIR_ARCHIVE_XML_VALID");
	public static final String INBOUND_DIR_ARCHIVE_XML_INVALID	= ConfigReader.getProperty ("INBOUND_DIR_ARCHIVE_XML_INVALID");
	
	// Mail SMTP Host for Email delivery
	public static final String MAIL_SMTP_HOST					= ConfigReader.getProperty ("MAIL_SMTP_HOST");
	
	public static final String FTP_SERVER_URL		= ConfigReader.getProperty ("FTP_SERVER_URL");
	public static final String FTP_SERVER_USERNAME	= ConfigReader.getProperty ("FTP_SERVER_USERNAME");
	public static final String FTP_SERVER_PWD		= ConfigReader.getProperty ("FTP_SERVER_PWD");
	public static final String FTP_SERVER_DIR		= ConfigReader.getProperty ("FTP_SERVER_DIR");
	public static final String PIX_USAGE_SUCCESS_EMAIL_ID_FROM	= ConfigReader.getProperty ("PIX_USAGE_SUCCESS_EMAIL_ID_FROM");
	public static final String PIX_USAGE_SUCCESS_EMAIL_ID_TO	= ConfigReader.getProperty ("PIX_USAGE_SUCCESS_EMAIL_ID_TO");
	public static final String PIX_USAGE_SUCCESS_EMAIL_ID_CC	= ConfigReader.getProperty ("PIX_USAGE_SUCCESS_EMAIL_ID_CC");
	public static final String PIX_USAGE_SUCCESS_EMAIL_ID_BCC	= ConfigReader.getProperty ("PIX_USAGE_SUCCESS_EMAIL_ID_BCC");
	public static final String PIX_USAGE_SUCCESS_EMAIL_SUBJECT	= ConfigReader.getProperty ("PIX_USAGE_SUCCESS_EMAIL_SUBJECT");
	public static final String PIX_OUTBOUND_ERROR_LOG_EMAIL_SUBJECT	= ConfigReader.getProperty ("PIX_OUTBOUND_ERROR_LOG_EMAIL_SUBJECT");
	public static final String PIX_USAGE_FAILURE_EMAIL_ID_FROM	= ConfigReader.getProperty ("PIX_USAGE_FAILURE_EMAIL_ID_FROM");
	public static final String PIX_USAGE_FAILURE_EMAIL_ID_TO	= ConfigReader.getProperty ("PIX_USAGE_FAILURE_EMAIL_ID_TO");
	public static final String PIX_USAGE_FAILURE_EMAIL_ID_CC	= ConfigReader.getProperty ("PIX_USAGE_FAILURE_EMAIL_ID_CC");
	public static final String PIX_USAGE_FAILURE_EMAIL_ID_BCC	= ConfigReader.getProperty ("PIX_USAGE_FAILURE_EMAIL_ID_BCC");
	public static final String PIX_USAGE_FAILURE_EMAIL_SUBJECT	= ConfigReader.getProperty ("PIX_USAGE_FAILURE_EMAIL_SUBJECT");
	public static final String DIR_VALIDATION_FAILURE_XML 		= ConfigReader.getProperty ("DIR_VALIDATION_FAILURE_XML");

}