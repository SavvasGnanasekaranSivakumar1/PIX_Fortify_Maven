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
 * Title		: 	IErrorConstants.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	09 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils.constants;
/**
 * IErrorConstants interface contains all common Error Constants
 * (ERROR_ID & ERROR_DESCRIPTION)
 * 
 * @author Yogesh Tyagi
 */
public interface IErrorConstants {
	public static final String ERROR_TYPE_L		= "L";
	public static final String ERROR_TYPE_O		= "O";
	
	public static final String ERROR_CODE_GEN	= "GEN";
	public static final String ERROR_CODE_SYS	= "SYS";
	public static final String ERROR_CODE_POR	= "POR";
	public static final String ERROR_CODE_BSP	= "BSP";
	
	public static final int ERROR_ID_0			= 0;
	public static final String ERROR_DESC_0		= "DATABASE ERROR";
	
	public static final int ERROR_ID_1			= 1;
	public static final String ERROR_DESC_1		= "TRANSACTION ALREADY EXISTS";
	
	public static final int ERROR_ID_2			= 2;
	public static final String ERROR_DESC_2		= "ISBN10/ISBN13 NOT FOUND";
	
	public static final int ERROR_ID_3			= 3;
	public static final String ERROR_DESC_3		= "NOT A VALID ISBN13";
	
	public static final int ERROR_ID_4			= 4;
	public static final String ERROR_DESC_4		= "NOT A VALID ISBN10";
	
	public static final int ERROR_ID_5			= 5;
	public static final String ERROR_DESC_5		= "PRINTING NUMBER NOT FOUND";
	
	public static final int ERROR_ID_6			= 6;
	public static final String ERROR_DESC_6		= "PUBLISHER SAN NO NOT FOUND";
	
	public static final int ERROR_ID_7			= 7;
	public static final String ERROR_DESC_7		= "PUBLISHER NAME NOT FOUND";
	
	public static final int ERROR_ID_8			= 8;
	public static final String ERROR_DESC_8		= "SUPPLIER SAN NO NOT FOUND";
	
	public static final int ERROR_ID_9			= 9;
	public static final String ERROR_DESC_9		= "SUPPLIER NAME NO NOT FOUND";
	
	public static final int ERROR_ID_10			= 10;
	public static final String ERROR_DESC_10	= "SPECIFICATION ISSUE DATE NOT FOUND";
	
	public static final int ERROR_ID_11			= 11;
	public static final String ERROR_DESC_11	= "NO PRODUCT DETAIL FOUND";
	
	public static final int ERROR_ID_12			= 12;
//	public static final String ERROR_DESC_12	= "COMPONENT ID NOT FOUND";
	public static final String ERROR_DESC_12	= "Material not found in PIX PO";
	
	public static final int ERROR_ID_13			= 13;
	public static final String ERROR_DESC_13	= "PROUCT TYPE INFORMATION NOT AVAILABLE";
	
	public static final int ERROR_ID_14			= 14;
	public static final String ERROR_DESC_14	= "INVALID PRODUCT TYPE";
	
	public static final int ERROR_ID_15			= 15;
	public static final String ERROR_DESC_15	= "PROUCT DESCRIPTION NOT FOUND";
	
	public static final int ERROR_ID_16			= 16;
	public static final String ERROR_DESC_16	= "TRANSACTION HISTORY NUMBER NOT FOUND";
	
	public static final int ERROR_ID_17			= 17;
	public static final String ERROR_DESC_17	= "MEDIA TYPE INVALID";
	
	public static final int ERROR_ID_18			= 18;
	public static final String ERROR_DESC_18	= "INVALID ADDITIONAL INSTRUCTION";
	
	public static final int ERROR_ID_19			= 19;
	public static final String ERROR_DESC_19	= "NO FINISHED GOOD COMPONENT FOUND";
	
	public static final int ERROR_ID_20			= 20;
	public static final String ERROR_DESC_20	= "DELIVERY DATE NOT FOUND";
	
	public static final int ERROR_ID_21			= 21;
	public static final String ERROR_DESC_21	= "COMPONENT DELIVERY DATE NOT FOUND";
	
	public static final int ERROR_ID_22			= 22;
	public static final String ERROR_DESC_22	= "COMPONENT ESTIMATED QUANTITY NOT SPECIFIED";
	
	public static final int ERROR_ID_23			= 23;
	public static final String ERROR_DESC_23	= "NO SUPPLIED COMPONENT FOUND FOR PO";
	
	public static final int ERROR_ID_24			= 24;
	public static final String ERROR_DESC_24	= "BOOK SPECIFICATION NOT FOUND";
	
	public static final int ERROR_ID_25			= 25;
	public static final String ERROR_DESC_25	= "NO PRICE DETAIL FOUND FOR PO";
	
	public static final int ERROR_ID_26			= 26;
	public static final String ERROR_DESC_26	= "NO GL CODE FOUND FOR PRICE DETAIL LINE ITEM";
	
	public static final int ERROR_ID_27			= 27;
	public static final String ERROR_DESC_27	= "NO PRICE FOUND AGAINT GL CODE";
	
	public static final int ERROR_ID_28			= 28;
	public static final String ERROR_DESC_28	= "SUPPLIED COMPONENT VENDOR SAN NOT FOUND";
	
	public static final int ERROR_ID_29			= 29;
	public static final String ERROR_DESC_29	= "SUPPLIED COMPONENT SUPPLIER NAME NOT FOUND";
	
	public static final int ERROR_ID_30			= 30;
//	public static final String ERROR_DESC_30	= "PO NUMBER NOT FOUND";
	public static final String ERROR_DESC_30	= "Purchase order not found in PIX";
	
	public static final int ERROR_ID_31			= 31;
	public static final String ERROR_DESC_31	= "PO RELEASE NO NOT FOUND";
	
	public static final int ERROR_ID_32			= 32;
	public static final String ERROR_DESC_32	= "PO LINE ITEMS NOT FOUND";
	
	public static final int ERROR_ID_33			= 33;
	public static final String ERROR_DESC_33	= "PO SHIP TO SAN NUMBER NOT FOUND";
	
	public static final int ERROR_ID_34			= 34;
	public static final String ERROR_DESC_34	= "PO ORDER TYPE(RESERVED/STANDARD) NOT FOUND";
	
	public static final int ERROR_ID_35			= 35;
	public static final String ERROR_DESC_35	= "INVALID PO ORDER TYPE";
	
	public static final int ERROR_ID_36			= 36;
	public static final String ERROR_DESC_36	= "NO FINISHED GOOD COMPONENT FOUND";
	
	public static final int ERROR_ID_37			= 37;
	public static final String ERROR_DESC_37	= "NO DETAILS FOUND FOR COMPONENT LINE ITEM";
	
	public static final int ERROR_ID_38			= 38;
	public static final String ERROR_DESC_38	= "PO SHIP TO SAN NAME NOT FOUND";
	
	public static final int ERROR_ID_39			= 39;
	public static final String ERROR_DESC_39	= "PO CREATION DATE NOT FOUND";
	
	public static final int ERROR_ID_40			= 40;
	public static final String ERROR_DESC_40	= "COMPONENT QUANTITY CAN NOT BE NULL";
	
	public static final int ERROR_ID_41			= 41;
	public static final String ERROR_DESC_41	= "NO GL DESCRIPTION FOUND AGAINST ITS GL CODE";
	
	public static final int ERROR_ID_42			= 42;
	public static final String ERROR_DESC_42	= "CURRENCY CODE NOT FOUND AGAINST ITS PRICE DETAIL IN PO";
	
	public static final int ERROR_ID_43			= 43;
	public static final String ERROR_DESC_43	= "PO COMPONENT LEVEL STATUS NOT FOUND";
	
	public static final int ERROR_ID_44			= 44;
	public static final String ERROR_DESC_44	= "BINDING COMPONENT DETAIL NOT FOUND";
	
	public static final int ERROR_ID_45			= 45;
	public static final String ERROR_DESC_45	= "ASSEMBLY COMPONENT DETAIL NOT FOUND";
	
	public static final int ERROR_ID_46			= 46;
	public static final String ERROR_DESC_46	= "NON PRESS COMPONENT DETAIL NOT FOUND";
	
	public static final int ERROR_ID_47			= 47;
	public static final String ERROR_DESC_47	= "PRESS COMPONENT DETAIL NOT FOUND";

	public static final int ERROR_ID_48			= 48;
	public static final String ERROR_DESC_48	= "PO TRANSACTION_ID IS NULL";
	
	public static final int ERROR_ID_49			= 49;
	public static final String ERROR_DESC_49	= "PURCHASE ORDER LINE ITEM STATUS TYPE IS NULL";
	
	public static final int ERROR_ID_50			= 50;
	public static final String ERROR_DESC_50	= "UOM QUANTITY IS NULL";
	
	public static final int ERROR_ID_51			= 51;
	public static final String ERROR_DESC_51	= "CURRENCY TYPE IN PRICEDETAILS IS NULL";
	
	public static final int ERROR_ID_52			= 52;
	public static final String ERROR_DESC_52	= "GL DESCRIPTION IS NULL WHEN GLCODE EXISTS";
	
	public static final int ERROR_ID_53			= 53;
	public static final String ERROR_DESC_53	= "SPECIFICATION NUMBER IS NULL";
	
	public static final int ERROR_ID_54			= 54;
	public static final String ERROR_DESC_54	= "PURCHASE ORDERTYPE IS NULL";
	
	public static final int ERROR_ID_55			= 55;
	public static final String ERROR_DESC_55	= "PURCHASE ORDER STATUS TYPE IS NULL";
	
	public static final int ERROR_ID_56			= 56;
	public static final String ERROR_DESC_56	= "JOB NUMBER FOR COURIER IS REQUIRED";
	
	public static final int ERROR_ID_57			= 57;
	public static final String ERROR_DESC_57	= "PurchaseOrderNumber IS NULL";
	
	public static final int ERROR_ID_58			= 58;
	public static final String ERROR_DESC_58	= "PO TransactionID IS NULL";
	
	public static final int ERROR_ID_59			= 59;
	public static final String ERROR_DESC_59	= "OC TransactionID IS NULL";
	
	public static final int ERROR_ID_60			= 60;
	public static final String ERROR_DESC_60	= "Order Confirmation LineItem StatusType IS NULL";
	
	public static final int ERROR_ID_61			= 61;
	public static final String ERROR_DESC_61	= "Order Confirmation LineItem StatusType IS NOT VALID";
	
	public static final int ERROR_ID_62			= 62;
	public static final String ERROR_DESC_62	= "Purchase OrderLineItemNumber IS NULL";
	
	public static final int ERROR_ID_63			= 63;
	public static final String ERROR_DESC_63	= "LINE ITEMS IN PO NOT IN SYNC WITH LINE ITEMS THAT APPEAR IN OC";
	
	public static final int ERROR_ID_64			= 64;
	public static final String ERROR_DESC_64	= "PURCHASE ORDERNUMBER AT LINE LEVEL IS NULL";
	
	public static final int ERROR_ID_65			= 65;
	public static final String ERROR_DESC_65	= "SI TRANSACTIONID IS NULL";
	
	public static final int ERROR_ID_66			= 66;
	public static final String ERROR_DESC_66	= "JOB NUMBER FOR RRD IS REQUIRED";
	
	public static final int ERROR_ID_67			= 67;
	public static final String ERROR_DESC_67	= "LINE ITEMS IN PO NOT IN SYNC WITH LINE ITEMS THAT APPEAR IN OS.";
	
	public static final int ERROR_ID_68			= 68;
	public static final String ERROR_DESC_68	= "TITLE DESCRIPTION IS NULL";
	
	public static final int ERROR_ID_69			= 69;
	public static final String ERROR_DESC_69	= "TRANSACTIONID IS NULL";
	
	public static final int ERROR_ID_70			= 70;
	public static final String ERROR_DESC_70	= "MIN REQUIRED INFORMATION IS NOT AVALIABLE IN THE XML.";
	
	public static final int ERROR_ID_71			= 71;
	public static final String ERROR_DESC_71	= "XML PARSING ERROR";
	
	public static final int ERROR_ID_72			= 72;
	public static final String ERROR_DESC_72	= "XML VALIDATION FAILED WITH SCHEMA";
	
	public static final int ERROR_ID_74			= 74;
	public static final String ERROR_DESC_74	= "PO NUMBER IS NULL";

	public static final int ERROR_ID_75			= 75;
	public static final String ERROR_DESC_75	= "PO NUMBER IS INVALID";

	public static final int ERROR_ID_76			= 76;
	public static final String ERROR_DESC_76	= "PO TRANSACTION ID IS NULL";

	public static final int ERROR_ID_77			= 77;
	public static final String ERROR_DESC_77	= "PO TRANSACTION ID IS INVALID";
	
	public static final int ERROR_ID_78			= 78;
	public static final String ERROR_DESC_78	= "PO LINE NUMBER IS NULL";

	public static final int ERROR_ID_79			= 79;
	public static final String ERROR_DESC_79	= "PO LINE NUMBER IS INVALID";
	
	public static final int ERROR_ID_80			= 80;
	public static final String ERROR_DESC_80	= "NOT A VALID ORDER STATUS CODE";
	
	public static final int ERROR_ID_81			= 81;
	public static final String ERROR_DESC_81	= "ORDER STATUS CODE IS NULL";

	public static final int ERROR_ID_82			= 82;
	public static final String ERROR_DESC_82	= "NOT A FINISHED GOOD PRODUCT";
	
	//BA ERROR CODES
	public static final int ERROR_ID_83			= 83;
	public static final String ERROR_DESC_83	= "BA STATUS NOT FOUND";
	
	public static final int ERROR_ID_84			= 84;
	public static final String ERROR_DESC_84	= "TRANSACTION ID IS INVALID";
	
	public static final int ERROR_ID_85			= 85;
	public static final String ERROR_DESC_85	= "BA DOCUMENT NAME IS INVALID";
	
	public static final int ERROR_ID_86			= 86;
	public static final String ERROR_DESC_86	= "DOCUMENT NUMBER IS NULL";
	
	public static final int ERROR_ID_87			= 87;
	public static final String ERROR_DESC_87	= "BA TRANSACTION ID IS NULL";   //Doc Trans Id
	
	public static final int ERROR_ID_88			= 88;
	public static final String ERROR_DESC_88	= "BA TRANSACTION ID ALREADY EXIST"; //Doc Trans Id
	
	public static final int ERROR_ID_89			= 89;
	public static final String ERROR_DESC_89	= "BA ERROR CODE IS NULL";
	
	public static final int ERROR_ID_90			= 90;
	public static final String ERROR_DESC_90	= "BA ERROR DESCRIPTION IS NULL";
	
	public static final int ERROR_ID_91			= 91;
	public static final String ERROR_DESC_91	= "ERROR INFORMATION IS MISSING IN THE XML";
	
	public static final int ERROR_ID_92			= 92;
	public static final String ERROR_DESC_92	= "INVOICE NUMBER ALREADY EXISTS";
	
	public static final int ERROR_ID_93			= 93;
	public static final String ERROR_DESC_93	= "INVOICE NUMBER IS NULL";
	
	public static final int ERROR_ID_94			= 94;
	public static final String ERROR_DESC_94	= "INVOICE TRANS ID IS NULL";
	
	public static final int ERROR_ID_95			= 95;
	public static final String ERROR_DESC_95	= "INVALID PO/ISBN/PRINTING DATA - INVOICE CANNOT BE PROCESED";
	
	public static final int ERROR_ID_96			= 96;
	public static final String ERROR_DESC_96	= "INVALID GL CODE";
	
	public static final int ERROR_ID_97			= 97;
	public static final String ERROR_DESC_97	= "USAGE TRANSACTION ID IS NULL";
	
	public static final int ERROR_ID_99			= 99;
	public static final String ERROR_DESC_99	= "GL CODE IS NULL";
	
	public static final int ERROR_ID_100		= 100;
	public static final String ERROR_DESC_100	= "GoodsReceiptNumber is NULL";	
	
	public static final int ERROR_ID_101		= 101;
	public static final String ERROR_DESC_101	= "GoodsReceiptNumber is Invalid";
	
	public static final int ERROR_ID_102		= 102;
	public static final String ERROR_DESC_102	= "GoodsReceipt TransId is Invalid";
	
	public static final int ERROR_ID_103		= 103;
	public static final String ERROR_DESC_103	= "GoodsReceipt is already processed";
	
	public static final int ERROR_ID_104		= 104;
	public static final String ERROR_DESC_104	= "Delivery Message No. is NULL";
	
	public static final int ERROR_ID_105		= 105;
	public static final String ERROR_DESC_105	= "Delivery Message No. is Invalid";
	
	public static final int ERROR_ID_106		= 106;
	public static final String ERROR_DESC_106	= "DeliveryMsg LineItemNo. is NULL";
	
	public static final int ERROR_ID_107		= 107;
	public static final String ERROR_DESC_107	= "DeliveryMsg LineItemNo. is Invalid";
	
	public static final int ERROR_ID_108		= 108;
	public static final String ERROR_DESC_108	= "Material Number is Invalid";
	
	public static final int ERROR_ID_109		= 109;
	public static final String ERROR_DESC_109	= "Received Quantity is Invalid";
	
	public static final int ERROR_ID_111		= 111;
	public static final String ERROR_DESC_111	= "Delievered Quantity is NULL";

	public static final int ERROR_ID_112		= 112;
	public static final String ERROR_DESC_112	= "Purchase Order Lineitem is NULL";

	public static final int ERROR_ID_113		= 113;
	public static final String ERROR_DESC_113	= "Purchase Order Lineitem is Invalid";

	public static final int ERROR_ID_114		= 114;
	public static final String ERROR_DESC_114	= "DM transaction Id IS NULL";

	public static final int ERROR_ID_115		= 115;
	public static final String ERROR_DESC_115	= "DM transaction Id IS INVALID";

	public static final int ERROR_ID_116		= 116;
	public static final String ERROR_DESC_116	= "Inventory Status transaction Id IS NULL";

	public static final int ERROR_ID_117		= 117;
	public static final String ERROR_DESC_117	= "Inventory Status transaction Id IS INVALID";
	
	public static final int ERROR_ID_118		= 118;
	public static final String ERROR_DESC_118	= "Part Number is Invalid";

	public static final int ERROR_ID_119		= 119;
	public static final String ERROR_DESC_119	= "Inventory Change transaction Id IS NULL";

	public static final int ERROR_ID_120		= 120;
	public static final String ERROR_DESC_120	= "Inventory Change transaction Id IS INVALID";
	
	public static final int ERROR_ID_121		= 121;
	public static final String ERROR_DESC_121	= "Part Number is NULL";
	
	public static final int ERROR_ID_122		= 122;
	public static final String ERROR_DESC_122	= "SHIP DATE IS NULL";
	
	public static final int ERROR_ID_123		= 123;
	public static final String ERROR_DESC_123	= "PARTY IDENTIFIER FOR SHIPTO PARTY IS NULL";
	
	public static final int ERROR_ID_124		= 124;
	public static final String ERROR_DESC_124	= "PARTY IDENTIFIER FOR SHIPTO PARTY IS INVALID";
	
	public static final int ERROR_ID_125		= 125;
	public static final String ERROR_DESC_125	= "BOOKS PER CARTON IS NULL";
	
	public static final int ERROR_ID_126		= 126;
	public static final String ERROR_DESC_126	= "UNIT DIMENSIONS ARE EITHER NULL OR INVALID";
	
	public static final int ERROR_ID_127		= 127;
	public static final String ERROR_DESC_127	= "BOX DIMENSIONS ARE EITHER NULL OR INVALID";
	
	public static final int ERROR_ID_128		= 128;
	public static final String ERROR_DESC_128	= "TRANSACTION ID IS NULL";
	
	public static final int ERROR_ID_142		= 142;
	public static final String ERROR_DESC_142	= "TRACKING NUMBER EXIST FOR BOTH, PARCEL AND BOX ITEM";
	
	public static final int ERROR_ID_143		= 143;
	public static final String ERROR_DESC_143	= "TRACKING NUMBER DOES NOT EXIST FOR BOTH, PARCEL AND BOX ITEM";
	
	public static final int ERROR_ID_144		= 144;
	public static final String ERROR_DESC_144	= "INVALID COMBINATION OF PURCHASE ORDER NUMBER, ISBN AND BK NUMBER.";
	
	public static final int ERROR_ID_145		= 145;
	public static final String ERROR_DESC_145	= "TRACKING NUMBER ALREADY EXISTS IN SYSTEM.";
	
	
}