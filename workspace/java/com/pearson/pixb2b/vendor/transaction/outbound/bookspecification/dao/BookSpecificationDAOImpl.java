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
 * Title		: 	BookSpecificationDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal 		8 Dec, 2009		Initial version
 * 1.1		Ashish Agrawal		10 Dec, 2011	Modified as per Courier Suggestion		
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto.*;
import com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao.EnvelopeDAOImpl;

import com.pearson.pixb2b.vendor.transaction.shared.dto.PreviousVendorNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.PreviousVendorPlant;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.BusinessDocument;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Payload;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PayloadInfo;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * BookSpecificationDAOImpl is an implementation class to communicate with 
 * the database and get the BookSpecification transaction data.
 * 
 * @author Ashish Agrawal
 */
public class BookSpecificationDAOImpl extends EnvelopeDAOImpl implements IBookSpecificationDAO{
	
	private static final String qry_generate_bs_gen_list_proc  = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_BSP_LIST_PROC(?,?)}";
	private static final String qry_generate_bs_cmp_list_proc  = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_BSP_LINE_LIST_PROC(?,?)}";
	private static final String qry_generate_bs_assm_list_proc = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_BSP_ASM_PROC(?,?)}";
	private static final String qry_generate_bs_bind_list_proc = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_BSP_BIND_LIST_PROC(?,?)}";
	private static final String qry_generate_bs_pack_list_proc = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_BSP_PACK_LIST_PROC(?,?)}";
	
	private static final String qry_sel_pix_book_spec_misc = "SELECT LABEL, VALUE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_BOOK_SPEC_MISC"
		+" WHERE SPEC_ID = ? AND SPEC_VERSION = ? AND SPEC_LINE_NO = ?";

	private static final String qry_sel_pix_book_trim_size_gen = "SELECT VALUE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_BOOK_SPEC_MISC pbsp"
		+" WHERE 1=1 AND SPEC_ID = ? AND SPEC_VERSION = ? AND LABEL='Trim Descr'"
		+" AND EXISTS (SELECT 1 FROM PIX_BOOK_SPEC_LINE pbsl WHERE pbsl.SPEC_ID = pbsp.SPEC_ID"
		+" AND pbsl.SPEC_VERSION = pbsp.SPEC_VERSION AND pbsl.SPEC_LINE_NO = pbsp.SPEC_LINE_NO AND"
		+" pbsl.product_description='Text')";
		//+" AND pbsl.FINISHED_GOOD_FLAG ='Y')";
		
	private static final String qry_sel_trimlen_trimwid = "SELECT TRIM_WIDTH, TRIM_LENGTH FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_TRIM_SIZE_INFO_VW"
		+" WHERE TRIMSIZE_DESCRIPTION=?";
	
	private static String qry_sel_trimlen_trimwid1 = "SELECT TRIM_WIDTH, TRIM_LENGTH FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_TRIM_SIZE_INFO_VW"
	+" WHERE TRIMSIZE_DESCRIPTION = ";
	
	private static String qry_sel_trimlen_trimwid2 = "SELECT TRIM_WIDTH, TRIM_LENGTH FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_TRIM_SIZE_INFO_VW"
	+" WHERE TRIMSIZE_DESCRIPTION = ";

	private static final String qry_sel_xbit_value = "SELECT XBITS_VALUE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XBITS_MAPPING"
		+" WHERE COMPONENT = ? and SPEC_TYPE= ? and PEARSON_VALUE= ? and MAPPING_FLAG= ? and ACTIVE_FLAG= ?";

	private static final String qry_sel_finish_value = "SELECT b.COATING_TYPE COATINGTYPE, b.FINISH_TYPE FINISHTYPE, b.COATING_COVERAGE_TYPE COATINGCOVERAGETYPE"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XBITS_MAPPING a, "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XBITS_COVER_MAPPING b" 
		+" WHERE a.COMPONENT=? AND a.SPEC_TYPE=? AND a.PEARSON_VALUE=?"
		+" AND a.MAPPING_FLAG= ? and a.ACTIVE_FLAG= ? and a.MAPPING_ID=b.MAPPING_ID";
	
	private static final String qry_sel_value = "SELECT VALUE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_BOOK_SPEC_MISC"
		+" WHERE LABEL=? and SPEC_ID= ? and SPEC_VERSION=? and SPEC_LINE_NO=" 
		+"(SELECT SPEC_LINE_NO FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_BOOK_SPEC_LINE"
		+" WHERE SPEC_ID=? and SPEC_VERSION=? and product_description=?)";

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dao.IBookSpecificationDAO#getBookSpecificationDetails(java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList getBookSpecificationDetails(String vendorSAN, String transactionType, String transactionName){
		Connection dbCon 				= null;
		CallableStatement cs        	= null;
		ResultSet resultSet        		= null;
		CallableStatement cs1        	= null;
		ResultSet resultSet1       		= null;
		CallableStatement cs2        	= null;
		ResultSet resultSet2       		= null;
		CallableStatement cs3        	= null;
		ResultSet resultSet3       		= null;
		CallableStatement cs4        	= null;
		ResultSet resultSet4       		= null;
		
		ArrayList bsList				= null;
		ArrayList payloadInfoList		= null;
		PapiNetEnvelopeDTO pneDTO		= null;
		PayloadInfo payloadInfo			= null;
		Payload payload					= null;
		BusinessDocument busDocument	= null;
		BookSpecificationDTO bsDTO		= null;
		SpecGeneral specGeneral			= null;
		ArrayList specComponentList		= null;
		SpecAssembly specAssembly		= null;
		SpecBinding specBinding			= null;
		ArrayList specPackingList		= null;
		
		try {
			B2BLogger.debug("******* BookSpecificationDAOImpl.getBookSpecificationDetails() method ENTERED *******");			

			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			dbCon.setAutoCommit(false);
			
			payloadInfoList = getEnvelopePayloadInfo(dbCon, vendorSAN, transactionType, transactionName);
			if(payloadInfoList != null && payloadInfoList.size()>0){
				bsList = new ArrayList();
				for(int i=0; i<payloadInfoList.size(); i++){
					payloadInfo = (PayloadInfo)payloadInfoList.get(i);
					if(payloadInfo != null){
						DBUtils.close(cs);
						cs = null;
						DBUtils.close(resultSet);
						resultSet = null;
						bsDTO = null;
						
						pneDTO = new PapiNetEnvelopeDTO();
						pneDTO.setPayloadInfo(payloadInfo);
						String transID = payloadInfo.getTransID();						
					
						//Start SpecGeneral
						cs = dbCon.prepareCall(qry_generate_bs_gen_list_proc);
						cs.setLong(IPixB2BConstants.ONE, Long.valueOf(transID).longValue());
						cs.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						cs.execute();
						resultSet = (ResultSet) cs.getObject(IPixB2BConstants.TWO);
						bsDTO = new BookSpecificationDTO();
						while(resultSet.next()){
							//bsDTO = new BookSpecificationDTO();
							String nasta = resultSet.getString("NASTA");
							if(nasta != null && !"".equals(nasta.trim())){
								bsDTO.setNasta(nasta.trim());
							}
							String specStatusType = resultSet.getString("SPECSTATUSTYPE");
							if(specStatusType != null && !"".equals(specStatusType.trim())){
								bsDTO.setSpecStatusType(specStatusType.trim());
							}
							bsDTO.setSpecType("SpecOrder");
							specGeneral = setSpecGeneral(dbCon, resultSet, transID);
							if(specGeneral != null)
								bsDTO.setSpecGeneral(specGeneral);
							else
								B2BLogger.info("BookSpecificationDAOImpl.getBookSpecificationDetails() - specGeneral is null");
						}
						//End SpecGeneral
						
						//Start SpecComponent
						DBUtils.close(cs1);
						cs1 = null;
						DBUtils.close(resultSet1);
						resultSet1 = null;
						
						cs1 = dbCon.prepareCall(qry_generate_bs_cmp_list_proc);
						cs1.setLong(IPixB2BConstants.ONE, Long.valueOf(transID).longValue());
						cs1.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						cs1.execute();
						resultSet1 = (ResultSet) cs1.getObject(IPixB2BConstants.TWO);
						specComponentList = setSpecComponentList(dbCon, resultSet1);
						if(specComponentList != null && specComponentList.size()>0)
							bsDTO.setSpecComponentList(specComponentList);
						else
							B2BLogger.info("BookSpecificationDAOImpl.getBookSpecificationDetails() - specComponentList is null or empty");
						//End SpecComponent
						
						//Start SpecAssembly 
						DBUtils.close(cs2);
						cs2 = null;
						DBUtils.close(resultSet2);
						resultSet2 = null;
						
						cs2 = dbCon.prepareCall(qry_generate_bs_assm_list_proc);
						cs2.setLong(IPixB2BConstants.ONE, Long.valueOf(transID).longValue());
						cs2.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						cs2.execute();
						resultSet2 = (ResultSet) cs2.getObject(IPixB2BConstants.TWO);
						specAssembly = setSpecAssembly(resultSet2);
						if(specAssembly != null)
							bsDTO.setSpecAssembly(specAssembly);
						else
							B2BLogger.info("BookSpecificationDAOImpl.getBookSpecificationDetails() - specAssembly is null");
						//End SpecAssembly 
						
						//Start SpecBinding
						DBUtils.close(cs3);
						cs3 = null;
						DBUtils.close(resultSet3);
						resultSet3 = null;
						
						cs3 = dbCon.prepareCall(qry_generate_bs_bind_list_proc);
						cs3.setLong(IPixB2BConstants.ONE, Long.valueOf(transID).longValue());
						cs3.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						cs3.execute();
						resultSet3 = (ResultSet) cs3.getObject(IPixB2BConstants.TWO);
						specBinding = setSpecBinding(dbCon, resultSet3, bsDTO);
						if(specBinding != null)
							bsDTO.setSpecBinding(specBinding);
						else
							B2BLogger.info("BookSpecificationDAOImpl.getBookSpecificationDetails() - specBinding is null");
						//End SpecBinding
						
						//Start SpecPacking
						DBUtils.close(cs4);
						cs4 = null;
						DBUtils.close(resultSet4);
						resultSet4 = null;

						cs4 = dbCon.prepareCall(qry_generate_bs_pack_list_proc);
						cs4.setLong(IPixB2BConstants.ONE, Long.valueOf(transID).longValue());
						cs4.registerOutParameter(IPixB2BConstants.TWO, OracleTypes.CURSOR);
						cs4.execute();
						resultSet4 = (ResultSet) cs4.getObject(IPixB2BConstants.TWO);
						specPackingList = setSpecPackingList(dbCon, resultSet4);
						if(specPackingList != null && specPackingList.size()>0)
							bsDTO.setSpecPackingList(specPackingList);
						else
							B2BLogger.info("BookSpecificationDAOImpl.getBookSpecificationDetails() - specPackingList is null or empty");
						//End SpecPacking	
						
						busDocument = new BusinessDocument();
						busDocument.setBsDTO(bsDTO);
							
						payload	= new Payload();
						payload.setBusinessDocument(busDocument);
								
						pneDTO.setPayload(payload);
						bsList.add(pneDTO);
					}else{
						B2BLogger.debug("BookSpecificationDAOImpl.getBookSpecificationDetails() - Envelope PayloadInfo is null");
					}
				}//end payloadInfoList for loop
			}else{
				B2BLogger.debug("BookSpecificationDAOImpl.getBookSpecificationDetails() - payloadInfoList is null");
			}
			dbCon.commit();
			B2BLogger.debug("******* BookSpecificationDAOImpl.getBookSpecificationDetails() method EXIT *******");
		}catch (SQLException e) {			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
		}catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			bsList = null;
		}finally{
			DBUtils.close(cs);
			DBUtils.close(resultSet);
			DBUtils.close(cs1);
			DBUtils.close(resultSet1);
			DBUtils.close(cs2);
			DBUtils.close(resultSet2);
			DBUtils.close(cs3);
			DBUtils.close(resultSet3);
			DBUtils.close(cs4);
			DBUtils.close(resultSet4);
			DBUtils.close(dbCon);

			payloadInfoList		= null;
			pneDTO				= null;
			payloadInfo			= null;
			payload				= null;
			busDocument			= null;
			bsDTO				= null;
			specGeneral			= null;
			specComponentList	= null;
			specAssembly		= null;
			specBinding			= null;
			specPackingList		= null;
		}
		return bsList;
	}

	/**
	 * This method set the SpecGeneral details for a single XML to generate.
	 * @param dbCon
	 * @param resultSet
	 * @param transID
	 * @return SpecGeneral
	 */
	private SpecGeneral setSpecGeneral(Connection dbCon, ResultSet resultSet, String transID){
		
		SpecGeneral specGeneral				= null;

		PreparedStatement prepStmt3			= null;
		ResultSet resultSet8				= null;
		PreparedStatement prepStmt4			= null;
		ResultSet resultSet9				= null;		
		String qryParams					= null;
		String sqlQuery						= null;
		String trimSz						= null;
		
		SpecIssueDate specIssueDate			= null;
		SpecInformation specInfo			= null;
		Date date							= null;
		ArrayList specReferenceList			= null;
		SpecReference specReference			= null;
		ReceiverParty receiverParty			= null;
		ArrayList receiverPartyPIList		= null;
		ReceiverPartyPI receiverPartyPI		= null;
		ReceiverPartyNA receiverPartyNA		= null;
		RPOrgUnit rpOrgUnit					= null;
		ArrayList receiverPartyCCList		= null;
		ReceiverPartyCC receiverPartyCC		= null;
		SenderParty senderParty				= null;
		ArrayList senderPartyPIList			= null;
		SenderPartyPI senderPartyPI			= null;
		SenderPartyNA senderPartyNA			= null;
		SPOrgUnit sPOrgUnit 				= null;
		ArrayList senderPartyCCList 		= null;
		SenderPartyCC senderPartyCC 		= null;
		TrimSize trimSize					= null;
		Length length 						= null;
		Width width							= null;
		LengthVal lenValue					= null;
		WidthVal widvalue					= null;
		ArrayList sgAddTextList				= null;
		SGAddText sgAddText					= null;
		String trimWidth					= null;
		String trimLength					= null;
		
		//Added for Apollo Enhancement
		PreviousVendorPlant otherParty		=null;
		PreviousVendorNameAddress otherPartyNameAddress =null;
		//
		
		long specVersion = -1;
		long specId		= -1;
		
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecGeneral() method called");
			specGeneral = new SpecGeneral();
			String previousVendor = resultSet.getString("PREVIOUS_VENDOR_PLANT");
			otherParty = new PreviousVendorPlant();
			otherPartyNameAddress = new PreviousVendorNameAddress();
			if(previousVendor != null && !"".equals(previousVendor.trim())){
				otherPartyNameAddress.setName1(previousVendor);
				//System.out.println("otherPartyNameAddress"+otherPartyNameAddress.getName1());
				//System.out.println("otherPartyNameAddressToString"+otherPartyNameAddress.toString());
				otherParty.setOtherPartyType(IPixB2BConstants.ORIGINAL_SUPPLIER);
				//System.out.println("otherParty"+otherParty.toString());
				otherParty.setPreviousVendorNameAddress(otherPartyNameAddress);
				//System.out.println("otherParty in BSP "+previousVendor+" "+otherParty.toString());
				//poLineItem.setPreviousVendorPlant(otherParty);
				specGeneral.setPreviousVendorPlant(otherParty);
				//System.out.println("otherParty in BSP "+previousVendor+" "+specGeneral.toString());
			}
			specInfo= new SpecInformation();
			
			String specVer = resultSet.getString("SPEC_VERSION");
			if(specVer != null && !"".equals(specVer.trim())){
				specInfo.setSpecVersion(specVer.trim());
			}
			String specNum = resultSet.getString("SPEC_NO");
			if(specNum != null && !"".equals(specNum.trim())){
				specInfo.setSpecificationNumber(specNum.trim());
			}
			String spcIssDate = resultSet.getString("SPEC_ISSUE_DATE");
			if(spcIssDate != null && !"".equals(spcIssDate.trim())) {
				String[] temp = spcIssDate.split(" ");
				if(temp[0] != null){
					specIssueDate = new SpecIssueDate();
					String[] sDate =  temp[0].split("-");
					date = new Date();
					date.setYear(sDate[0]);
					date.setMonth(sDate[1]);
					date.setDay(sDate[2]);		        			
					specIssueDate.setDate(date);
					specIssueDate.setTime(temp[1]);
					specInfo.setSpecIssueDate(specIssueDate);
				}
			}					
			specReferenceList = new ArrayList();
			String isbn10 = resultSet.getString("ISBN10");
			if(isbn10!= null && !"".equals(isbn10.trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.ISBN10);
				specReference.setSpecReferenceValue(isbn10.trim());
				specReferenceList.add(specReference);

			}
			String isbn13 = resultSet.getString("ISBN13");
			if(isbn13!= null && !"".equals(isbn13.trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.ISBN13);
				specReference.setSpecReferenceValue(isbn13.trim());
				specReferenceList.add(specReference);

			}
			String printNo = resultSet.getString("PRINT_NO");
			if(printNo!= null && !"".equals(printNo.trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.PrintingNumber);
				specReference.setSpecReferenceValue(printNo.trim());
				specReferenceList.add(specReference);

			}
			if(transID!= null && !"".equals((transID.toString()).trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.TransactionID);
				specReference.setSpecReferenceValue((transID.toString()).trim());
				specReferenceList.add(specReference);
			}
			String authorName= resultSet.getString("AUTHOR");
			if(authorName!= null && !"".equals((authorName.toString()).trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.Author);
				specReference.setSpecReferenceValue((authorName.toString()).trim());
				specReferenceList.add(specReference);
			}
			String title = resultSet.getString("TITLE");
			if(title!= null && !"".equals((title.toString()).trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.Title);
				specReference.setSpecReferenceValue((title.toString()).trim());
				specReferenceList.add(specReference);
			}
			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: Start
			String edition = resultSet.getString("EDITION");
			if(edition!= null && !"".equals((edition.toString()).trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.Edition);
				specReference.setSpecReferenceValue((edition.toString()).trim());
				specReferenceList.add(specReference);
			}
			String copyRight = resultSet.getString("COPYRIGHT");
			if(copyRight!= null && !"".equals((copyRight.toString()).trim())){
				specReference = new SpecReference();
				specReference.setSpecReferenceType(IPixB2BConstants.Copyright);
				specReference.setSpecReferenceValue((copyRight.toString()).trim());
				specReferenceList.add(specReference);
			}			
			//Ashish:03/15/2015-CP#496114-Add edition & copyright information in PO and Book Spec XML as references: End
			if(null!=specReferenceList && specReferenceList.size()>0)
				specInfo.setSpecReferenceList(specReferenceList);
			
			specGeneral.setSpecInfo(specInfo);

			senderParty	= new SenderParty();	
			senderPartyCCList = new ArrayList();

			String contactNameFirst = resultSet.getString("BUYER_CON_FST_NM");
			String contactNameLast = resultSet.getString("BUYER_CON_LAST_NAME");
			if(contactNameFirst != null && !"".equals(contactNameFirst.trim())){
				senderPartyCC = new SenderPartyCC();
				if(contactNameLast != null && !"".equals(contactNameLast.trim())){
					senderPartyCC.setContactName(contactNameFirst.trim()+" "+contactNameLast.trim());
				}else{
					senderPartyCC.setContactName(contactNameFirst.trim());	
				}
				senderPartyCC.setContactType(IPixB2BConstants.Purchaser);
				String phone = resultSet.getString("BUYER_PHONE");
				if(phone != null && !"".equals(phone.trim()))
					senderPartyCC.setPhone(phone.trim());
				String mobile = resultSet.getString("BUYER_MOBILE");
				if(mobile != null && !"".equals(mobile.trim()))
					senderPartyCC.setMobile(mobile.trim());
				String email = resultSet.getString("BUYER_EMAIL");
				if(email != null && !"".equals(email.trim()))
					senderPartyCC.setEmail(email.trim());
				String fax = resultSet.getString("BUYER_FAX");
				if(fax!=null && !"".equals(fax.trim()))
					senderPartyCC.setFax(fax.trim());
				
				senderPartyCCList.add(senderPartyCC);
			}
			if(null!=senderPartyCCList && senderPartyCCList.size()>0)
				senderParty.setSenderPartyCCList(senderPartyCCList);
			
			senderPartyNA = new SenderPartyNA();
			String name1 = resultSet.getString("BUYER_NAME1");
			if(name1 != null && !"".equals(name1.trim())){
				senderPartyNA.setName1(name1.trim());
				String name2 = resultSet.getString("BUYER_NAME2");
				if(name2 != null && !"".equals(name2.trim()))
					senderPartyNA.setName2(name2.trim());
				String name3 = resultSet.getString("BUYER_NAME3");
				if(name3 != null && !"".equals(name3.trim()))
					senderPartyNA.setName3(name3.trim());
				sPOrgUnit = new SPOrgUnit();
				String orgUnitCode = resultSet.getString("BUYER_ORG_UNIT_CODE");
				if(orgUnitCode != null && !"".equals(orgUnitCode.trim()))
					sPOrgUnit.setOrgUnitCode(orgUnitCode);
				String orgUnitName = resultSet.getString("BUYER_ORD_UNIT_NAME");
				if(orgUnitName != null && !"".equals(orgUnitName.trim()))
					sPOrgUnit.setOrgUnitName(orgUnitName);
				String address1 = resultSet.getString("BUYER_ADD1");
				if(address1 != null && !"".equals(address1.trim()))
					senderPartyNA.setAddress1(address1.trim());
				String address2 = resultSet.getString("BUYER_ADD2");
				if(address2 != null && !"".equals(address2.trim()))
					senderPartyNA.setAddress2(address2.trim());
				String address3 = resultSet.getString("BUYER_ADD3");
				if(address3 != null && !"".equals(address3.trim()))
					senderPartyNA.setAddress3(address3.trim());
				String address4 = resultSet.getString("BUYER_ADD4");
				if(address4 != null && !"".equals(address4.trim()))
					senderPartyNA.setAddress4(address4.trim());
				String city = resultSet.getString("BUYER_CITY");
				if(city != null && !"".equals(city.trim()))
					senderPartyNA.setCity(city.trim());		
				String state = resultSet.getString("BUYER_STATE");
				if(state != null && !"".equals(state.trim()))
					senderPartyNA.setStateOrProvince(state.trim());
				String postalCode = resultSet.getString("BUYER_POSTAL_CODE");
				if(postalCode != null && !"".equals(postalCode.trim()))
					senderPartyNA.setPostalCode(postalCode.trim());
				String country = resultSet.getString("BUYER_CC");
				if(country != null && !"".equals(country.trim()))
					senderPartyNA.setCountry(country.trim());
				
				senderParty.setSenderPartyNA(senderPartyNA);
			}

			String senderpiVal = resultSet.getString("BUYER_SAN");
			if(senderpiVal != null && !"".equals(senderpiVal.trim())){
				senderpiVal = senderpiVal.replaceAll("-","");
				senderPartyPI = new SenderPartyPI();
				senderPartyPI.setPiType("AssignedByBuyer");
				senderPartyPI.setPiValue(senderpiVal.trim());
				senderPartyPIList = new ArrayList();
				senderPartyPIList.add(senderPartyPI);
				
				String sanId = (senderpiVal.trim()).substring(0, 7);
				senderPartyPI = new SenderPartyPI();
				senderPartyPI.setPiType(IPixB2BConstants.StandardAddressNumber);
				senderPartyPI.setPiValue(sanId);
				senderPartyPIList.add(senderPartyPI);
				
				senderParty.setSenderPartyPIList(senderPartyPIList);
			}			
			senderParty.setPartyType("Buyer");	
			specGeneral.setSenderParty(senderParty);
			
			receiverParty = new ReceiverParty();
			receiverPartyCCList	= new ArrayList();
			String rpContactName = resultSet.getString("CONTACT_FIRST_NAME");
			if(rpContactName != null && !"".equals(rpContactName.trim())){
				receiverPartyCC	= new ReceiverPartyCC();
				receiverPartyCC.setContactName(rpContactName.trim());
				receiverPartyCC.setContactType(IPixB2BConstants.CustomerService);
				String rpPhone = resultSet.getString("PHONE");
				if(rpPhone != null && !"".equals(rpPhone.trim()))
					receiverPartyCC.setPhone(rpPhone.trim());
				String rpMobile = resultSet.getString("MOBILE");
				if(rpMobile != null && !"".equals(rpMobile.trim()))
					receiverPartyCC.setMobile(rpMobile.trim());
				String rpEmail = resultSet.getString("EMAIL");
				if(rpEmail != null && !"".equals(rpEmail.trim()))
					receiverPartyCC.setEmail(rpEmail.trim());
				String rpFax = resultSet.getString("FAX");
				if(rpFax!=null && !"".equals(rpFax.trim()))
					receiverPartyCC.setFax(rpFax.trim());
				
				receiverPartyCCList.add(receiverPartyCC);
			}
			if(null!=receiverPartyCCList && receiverPartyCCList.size()>0)
				receiverParty.setReceiverPartyCCList(receiverPartyCCList);

			receiverPartyNA = new ReceiverPartyNA();
			String rpName1 = resultSet.getString("NAME_1");
			if(rpName1 != null && !"".equals(rpName1.trim())){
				receiverPartyNA.setName1(rpName1.trim());
				String rpName2 = resultSet.getString("NAME_2");
				if(rpName2 != null && !"".equals(rpName2.trim()))
					receiverPartyNA.setName2(rpName2.trim());
				String rpName3 = resultSet.getString("NAME_3");
				if(rpName3 != null && !"".equals(rpName3.trim()))
					receiverPartyNA.setName3(rpName3.trim());
				rpOrgUnit = new RPOrgUnit();
				String rpOrgUnitCode = resultSet.getString("ORG_UNIT_CODE");
				if(rpOrgUnitCode != null && !"".equals(rpOrgUnitCode.trim()))
					rpOrgUnit.setOrgUnitCode(rpOrgUnitCode);
				String rpOrgUnitName = resultSet.getString("ORG_UNIT_NAME");
				if(rpOrgUnitName != null && !"".equals(rpOrgUnitName.trim()))
					rpOrgUnit.setOrgUnitName(rpOrgUnitName);
				String rpAddress1 = resultSet.getString("ADDRESS_1");
				if(rpAddress1 != null && !"".equals(rpAddress1.trim()))
					receiverPartyNA.setAddress1(rpAddress1.trim());
				String rpAddress2 = resultSet.getString("ADDRESS_2");
				if(rpAddress2 != null && !"".equals(rpAddress2.trim()))
					receiverPartyNA.setAddress2(rpAddress2.trim());
				String rpAddress3 = resultSet.getString("ADDRESS_3");
				if(rpAddress3 != null && !"".equals(rpAddress3.trim()))
					receiverPartyNA.setAddress3(rpAddress3.trim());
				String rpAddress4 = resultSet.getString("ADDRESS_4");
				if(rpAddress4 != null && !"".equals(rpAddress4.trim()))
					receiverPartyNA.setAddress4(rpAddress4.trim());
				String rpCity = resultSet.getString("CITY");
				if(rpCity != null && !"".equals(rpCity.trim()))
					receiverPartyNA.setCity(rpCity.trim());		
				String rpState = resultSet.getString("STATE");
				if(rpState != null && !"".equals(rpState.trim()))
					receiverPartyNA.setStateOrProvince(rpState.trim());
				String rpPostalCode = resultSet.getString("POSTAL_CODE");
				if(rpPostalCode != null && !"".equals(rpPostalCode.trim()))
					receiverPartyNA.setPostalCode(rpPostalCode.trim());
				String rpCountry = resultSet.getString("COUNTRY_CODE");
				if(rpCountry != null && !"".equals(rpCountry.trim()))
					receiverPartyNA.setCountry(rpCountry.trim());
				
				receiverParty.setReceiverPartyNA(receiverPartyNA);
			}

			String recPIVal = resultSet.getString("SUP_SAN");
			if(recPIVal != null && !"".equals(recPIVal.trim())){
				recPIVal = recPIVal.replaceAll("-","");
				receiverPartyPI	= new ReceiverPartyPI();
				receiverPartyPI.setPiType("StandardAddressNumber");
				receiverPartyPI.setPiValue(recPIVal.trim());
				receiverPartyPIList	= new ArrayList();
				receiverPartyPIList.add(receiverPartyPI);
				receiverParty.setReceiverPartyPIList(receiverPartyPIList);	
			}			
			receiverParty.setPartyType("Supplier");	
			specGeneral.setReceiverParty(receiverParty);
						
			specId = resultSet.getLong("SPEC_ID");
			specVersion = resultSet.getLong("SPEC_VERSION");
			sqlQuery = qry_sel_pix_book_trim_size_gen;
			prepStmt3 = dbCon.prepareStatement(sqlQuery);
			prepStmt3.clearParameters();
			prepStmt3.setLong(IPixB2BConstants.ONE, specId);
			prepStmt3.setLong(IPixB2BConstants.TWO, specVersion);
			qryParams = specId+", "+specVersion;
			resultSet8 = prepStmt3.executeQuery();
			while(resultSet8.next()){
				trimSz = resultSet8.getString("VALUE");
			}
			
			sgAddTextList = new ArrayList();
			
			if(null!=trimSz && !"".equals(trimSz)){
				trimSz="'"+trimSz+"'";
				qry_sel_trimlen_trimwid1 = qry_sel_trimlen_trimwid1 + trimSz;
				//sqlQuery = qry_sel_trimlen_trimwid1 ;
				sqlQuery="SELECT TRIM_WIDTH, TRIM_LENGTH FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_TRIM_SIZE_INFO_VW"
				+" WHERE TRIMSIZE_DESCRIPTION = "+trimSz;
				Statement stmt = dbCon.createStatement();
				resultSet9 = stmt.executeQuery(sqlQuery);
				while(resultSet9.next()){
					trimWidth = resultSet9.getString("TRIM_WIDTH");
					trimLength = resultSet9.getString("TRIM_LENGTH");
				}
				DBUtils.close(stmt);
				
				if(null!=trimWidth && !"".equals(trimWidth) 
						&& null!=trimLength && !"".equals(trimLength)){
					
					lenValue = new LengthVal();
					lenValue.setUom("Inch");
					lenValue.setValue(trimLength);
					length = new Length();
					length.setValue(lenValue);

					widvalue = new WidthVal();
					widvalue.setUom("Inch");
					widvalue.setValue(trimWidth);
					width = new Width();
					width.setValue(widvalue);
					
					trimSize = new TrimSize();
					trimSize.setLength(length);
					trimSize.setWidth(width);
					specGeneral.setTrimSize(trimSize);
				}else{
					sgAddText = new SGAddText();
					sgAddText.setSgAddText("Trim Descr:"+trimSz.trim());
					sgAddTextList.add(sgAddText);
				}
			}
						
			/**if(trimSz != null && !"".equals(trimSz.trim())){
				String[] temp = trimSz.split("x | X");
				if(temp[0] != null || temp[1] != null){
					lenValue = new LengthVal();
					lenValue.setUom("Inch");
					lenValue.setValue(temp[0].substring(0, temp[0].trim().length()-1));
					length = new Length();
					length.setValue(lenValue);

					widvalue = new WidthVal();
					widvalue.setUom("Inch");
					widvalue.setValue(temp[1].substring(1, temp[1].trim().length()));
					width = new Width();
					width.setValue(widvalue);
		
					trimSize = new TrimSize();
					trimSize.setLength(length);
					trimSize.setWidth(width);
					specGeneral.setTrimSize(trimSize);
				} 
			}*/

			String pageCnt = resultSet.getString("TOTAL_PAGE_COUNT");
			if(pageCnt != null && !"".equals(pageCnt.trim())){
				specGeneral.setTotPageCnt(pageCnt.trim());
			}
			//sgAddTextList = new ArrayList();
			String buyerComm = resultSet.getString("BUYER_COMMENTS");
			if(buyerComm != null && !"".equals(buyerComm.trim())){
				sgAddText = new SGAddText();
				sgAddText.setSgAddText("Buyer Comments:"+buyerComm.trim());
				sgAddTextList.add(sgAddText);
				//specGeneral.setSgAddTextList(sgAddTextList);
			}
			if(null!=sgAddTextList && sgAddTextList.size()>0){
				specGeneral.setSgAddTextList(sgAddTextList);
			}
			
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecGeneral() method return");
		}catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			specGeneral = null;
		}finally{
			DBUtils.close(resultSet8);
			DBUtils.close(prepStmt3);
			DBUtils.close(resultSet9);
			DBUtils.close(prepStmt4);
			
			qryParams			= null;
			sqlQuery			= null;
			trimSz				= null;
			specIssueDate		= null;
			specInfo			= null;
			date				= null;
			specReferenceList	= null;
			specReference		= null;
			receiverParty		= null;
			receiverPartyPIList	= null;
			receiverPartyPI		= null;
			receiverPartyNA		= null;
			rpOrgUnit			= null;
			receiverPartyCCList	= null;
			receiverPartyCC		= null;
			senderParty			= null;
			senderPartyPIList	= null;
			senderPartyPI		= null;
			senderPartyNA		= null;
			sPOrgUnit 			= null;
			senderPartyCCList 	= null;
			senderPartyCC 		= null;
			trimSize			= null;
			length 				= null;
			width				= null;
			lenValue			= null;
			widvalue			= null;
			sgAddTextList		= null;
			sgAddText			= null;
		}
		return specGeneral;
	}
	
	/**
	 * This method set the SpecComponent List details for a single XML to generate.
	 * @param dbCon
	 * @param resultSet1
	 * @return ArrayList
	 */
	private ArrayList setSpecComponentList(Connection dbCon, ResultSet resultSet1){
		ArrayList specComponentList		= null;
		SpecComponent specComponent		= null;
		
		PCMargins pcMargins				= null;
		MarHead marHead					= null;
		HValue hValue					= null;
		MarThumb marThumb				= null;
		TValue tValue					= null;
		MarGutter marGutter				= null;
		GValue gValue					= null;
		MarFoot marFoot					= null;
		FValue fValue					= null;

		String cmpType = null;
		String classCode = null;
		String agency = null;
		String compName = null;
		String prodIdVal = null;
		String prodDesp = null;
		String bookClassType = null;
		String bookSubType = null;
		String finhGood		= null;
		String numOfPages = null;
		String numOfSig = null;
		String pagPerSig = null;
		String pressPrepInpType = null;
		String pressType = null;
		String marginVal = null;
		String value = null;
		String bleed = null;
		String numOfCols = null;
		String colDesp = null;
		String printProdIdVal = null;
		String printProdDesp = null;
		String detVal = null;
		String uomDetValue = null;
		String printColDesp = null;
		String ppiVal = null;
		String uomPPI = null;
		String widthVal = null;
		String uomWidth = null;
		String lengthVal = null;
		String uomLength = null;
		String formatType	= null;
		String nonPrepInpType  = null;
		String cdRType = null;
		String noOfCols = null;
		String bookClassificationData 	= null;
		
		ArrayList bookClassificationListData = null;
		ArrayList bookClassifList	= null;
		ArrayList bookClassificationList = null;
		
		long specVersion = -1;
		long specId		= -1;
		long specLineNumber	= -1;
		
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecComponentList() method called");
			specComponentList = new ArrayList();
			bookClassificationListData = new ArrayList();
			bookClassificationList = new ArrayList();
			
			boolean dataFound = false;
			long specLineNum = -1;
			long specLineNumPrev = -1;
			
			while(resultSet1.next()){
				dataFound = true;
				specLineNum = resultSet1.getLong("SPEC_LINE_NO");
				if(specLineNumPrev != -1  && specLineNum != specLineNumPrev){
					pcMargins = new PCMargins();
					pcMargins.setMarGutter(marGutter);
					pcMargins.setMarHead(marHead);
					pcMargins.setMarFoot(marFoot);
					pcMargins.setMarThumb(marThumb);
					
					specComponent = setSpecComponent(cmpType, classCode, agency, compName, prodIdVal, prodDesp, bookClassificationList,
							numOfPages, numOfSig, pagPerSig, pressPrepInpType, pressType, pcMargins,
							numOfCols, colDesp, printProdIdVal, printProdDesp, detVal, uomDetValue, printColDesp, ppiVal, 
							uomPPI, widthVal, uomWidth, lengthVal, uomLength, dbCon, specLineNumber, specId, specVersion,
							formatType, nonPrepInpType, cdRType, noOfCols);
					
					if(null!=specComponent){
						specComponentList.add(specComponent);
					}
					else
						B2BLogger.info("BookSpecificationDAOImpl.setSpecComponentList()- specComponent is null");
					
					bookClassificationListData = new ArrayList();
					bookClassificationList = new ArrayList();
				}
				
				//Get ResultSet
				specId	= resultSet1.getLong("SPEC_ID");
				specVersion = resultSet1.getLong("SPEC_VERSION");
				specLineNumber = resultSet1.getLong("SPEC_LINE_NO");
				
				cmpType = resultSet1.getString("PRODUCT_TYPE");
				classCode = resultSet1.getString("CLASSIF_CODE");
				agency = resultSet1.getString("AGENCY");
				compName = resultSet1.getString("DESCRIPTION");
				prodIdVal = resultSet1.getString("PRODUCT_CODE");
				prodDesp = resultSet1.getString("PRODUCT_DESCRIPTION");
				bookClassType = resultSet1.getString("BOOK_CLASSIF_TYPE");
				bookSubType = resultSet1.getString("BOOK_SUBCLASSIF_TYPE");
				finhGood  = resultSet1.getString("FINISHED_GOOD_FLAG");
				numOfPages = resultSet1.getString("NO_OF_PAGES");
				numOfSig = resultSet1.getString("NO_OF_SIGN");
				pagPerSig = resultSet1.getString("PAGES_PER_SIGN");
				pressPrepInpType = resultSet1.getString("PRESS_PREP_INPUT_TYPE");
				pressType = resultSet1.getString("PRESS_TYPE");
				marginVal = resultSet1.getString("MARGIN");
				value = resultSet1.getString("VALUE");
				bleed = resultSet1.getString("BLEED");
				numOfCols = resultSet1.getString("PRESS_NO_OF_COLORS");
				colDesp = resultSet1.getString("INK_COLOUR_SPECS");
				printProdIdVal = resultSet1.getString("PRINT_PRODUCT_CODE");
				printProdDesp = resultSet1.getString("PRINT_PRODUCT_DESCRIPTION");
				detVal = resultSet1.getString("BASIS_WT");
				uomDetValue = resultSet1.getString("BASIS_UOM");
				printColDesp = resultSet1.getString("COLOR_DESC");
				ppiVal = resultSet1.getString("PPI");
				uomPPI = resultSet1.getString("PPI_UOM");
				widthVal = resultSet1.getString("WIDTH");
				uomWidth = resultSet1.getString("WIDTH_UOM");
				lengthVal = resultSet1.getString("LENGTH");
				uomLength = resultSet1.getString("LENGTH_UOM");
				formatType = resultSet1.getString("FORMAT_TYPE");
				nonPrepInpType = resultSet1.getString("PRESS_PREINPUT_TYPE");
				cdRType = resultSet1.getString("CDR_TYPE");
				noOfCols = resultSet1.getString("NO_OF_COLORS");
				
				if(marginVal != null && !"".equals(marginVal.trim())){
					if("GUTTER".equalsIgnoreCase(marginVal.trim())){						
						if(value != null && !"".equals(value.trim())){
							marGutter = new MarGutter();
							if(bleed != null && !"".equals(bleed.trim()))								
								marGutter.setBleed(bleed.trim());
							gValue = new GValue();
							gValue.setUom("Inch");
							gValue.setValue(value.trim());
							marGutter.setGValue(gValue);								
						}
					}else if("HEADER".equalsIgnoreCase(marginVal.trim())){
						if(value != null && !"".equals(value.trim())){
							marHead = new MarHead();							
							if(bleed != null && !"".equals(bleed.trim()))
								marHead.setBleed(bleed.trim());
							hValue = new HValue();
							hValue.setUom("Inch");
							hValue.setValue(value.trim());
							marHead.setHValue(hValue);
						}
					}else if("FOOTER".equalsIgnoreCase(marginVal.trim())){			
						if(value != null && !"".equals(value.trim())){
							marFoot = new MarFoot();							
							if(bleed != null && !"".equals(bleed.trim()))									
								marFoot.setBleed(bleed.trim());
							fValue = new FValue();
							fValue.setUom("Inch");
							fValue.setValue(value.trim());
							marFoot.setFValue(fValue);
						}
					}else if("THUMB".equalsIgnoreCase(marginVal.trim())){					
						if(value != null && !"".equals(value.trim())){
							marThumb = new MarThumb();							
							if(bleed != null && !"".equals(bleed.trim()))
								marThumb.setBleed(bleed.trim());
							tValue = new TValue();
							tValue.setUom("Inch");
							tValue.setValue(value.trim());
							marThumb.setTValue(tValue);
						}
					}
				}
				//set BookClassification details
				bookClassificationData = bookClassType+bookSubType+finhGood;
				if(bookClassType != null && !"".equals(bookClassType.trim()) && !bookClassificationListData.contains(bookClassificationData)){
					bookClassificationListData.add(bookClassificationData);
					bookClassifList = setBookClassification(bookClassType, bookSubType, finhGood);
					if(bookClassifList != null && bookClassifList.size()>0){
						for(int j=0; j<bookClassifList.size();j++)
							bookClassificationList.add((SCBookClasn)bookClassifList.get(j));
					}
				}
				specLineNumPrev = specLineNum;
			}//end while loop
			
			if(dataFound){
				pcMargins = new PCMargins();
				pcMargins.setMarGutter(marGutter);
				pcMargins.setMarHead(marHead);
				pcMargins.setMarFoot(marFoot);
				pcMargins.setMarThumb(marThumb);
				
				specComponent = setSpecComponent(cmpType, classCode, agency, compName, prodIdVal, prodDesp, bookClassificationList,
						numOfPages, numOfSig, pagPerSig, pressPrepInpType, pressType, pcMargins,
						numOfCols, colDesp, printProdIdVal, printProdDesp, detVal, uomDetValue, printColDesp, ppiVal, 
						uomPPI, widthVal, uomWidth, lengthVal, uomLength, dbCon, specLineNumber, specId, specVersion,
						formatType, nonPrepInpType, cdRType, noOfCols);
				
				if(null!=specComponent){
					specComponentList.add(specComponent);
				}
				else
					B2BLogger.info("BookSpecificationDAOImpl.setSpecComponentList()- specComponent is null");
			}
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecComponentList() method return");
		}catch (SQLException e) {			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			specComponentList = null;
		}finally{
			specComponent		= null;
			pcMargins			= null;
			marHead				= null;
			hValue				= null;
			marThumb			= null;
			tValue				= null;
			marGutter			= null;
			gValue				= null;
			marFoot				= null;
			fValue				= null;
			
			cmpType 		= null;
			classCode 		= null;
			agency 			= null;
			compName 		= null;
			prodIdVal 		= null;
			prodDesp 		= null;
			bookClassType 	= null;
			bookSubType 	= null;
			finhGood		= null;
			numOfPages 		= null;
			numOfSig 		= null;
			pagPerSig 		= null;
			pressPrepInpType = null;
			pressType 		= null;
			marginVal 		= null;
			value 			= null;
			bleed 			= null;
			numOfCols 		= null;
			colDesp 		= null;
			printProdIdVal 	= null;
			printProdDesp 	= null;
			detVal	 		= null;
			uomDetValue 	= null;
			printColDesp 	= null;
			ppiVal 			= null;
			uomPPI 			= null;
			widthVal 		= null;
			uomWidth 		= null;
			lengthVal 		= null;
			uomLength 		= null;
			formatType		= null;
			nonPrepInpType  = null;
			cdRType 		= null;
			noOfCols 		= null;
			
			bookClassificationData 		= null;
			bookClassificationListData 	= null;
			bookClassifList				= null;
			bookClassificationList 		= null;
		}
		return specComponentList;
	}	
	
	/**
	 * This method set the BookClassification details for a single XML to generate.
	 * @param bookClassType
	 * @param bookSubType
	 * @param finhGood
	 * @return ArrayList
	 */
	private ArrayList setBookClassification(String bookClassType, String bookSubType, String finhGood){
		ArrayList bookClassifList 			= null;
		SCBookClasn bookClassification		= null;
		SCBSubClassn bookSubClassification 	= null;
		ArrayList scBSubClassnList			= null;
		
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setPOLineItemBookClassification() method called");
			bookClassifList = new ArrayList();
			
			bookClassification = new SCBookClasn();
			bookClassification.setBookClassType(bookClassType.trim());
			
			if(bookSubType != null && !"".equals(bookSubType.trim())){
				bookSubClassification = new SCBSubClassn();			
				bookSubClassification.setBookSubClassType(bookSubType.trim());
				scBSubClassnList = new ArrayList();
				scBSubClassnList.add(bookSubClassification);
				bookClassification.setScBSubClassnList(scBSubClassnList);
			}
			bookClassifList.add(bookClassification);
			
			/*if(finhGood != null && IPixB2BConstants.flag_Y.equals(finhGood.trim())){
				bookClassification = new SCBookClasn();
				bookClassification.setBookClassType(IPixB2BConstants.FinishedGoods);
				bookClassifList.add(bookClassification);								
			}*/
			B2BLogger.debug("BookSpecificationDAOImpl.setPOLineItemBookClassification() method return");
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			bookClassifList = null;
		} finally{
			bookClassification		= null;
			bookSubClassification 	= null;
			scBSubClassnList		= null;
		}
		return bookClassifList;
	}
	
	/**
	 * This method set the SpecComponent details for a single XML to generate.
	 * @param cmpType
	 * @param classCode
	 * @param agency
	 * @param compName
	 * @param prodIdVal
	 * @param prodDesp
	 * @param bookClassificationList
	 * @param numOfPages
	 * @param numOfSig
	 * @param pagPerSig
	 * @param pressPrepInpType
	 * @param pressType
	 * @param pcMargins
	 * @param numOfCols
	 * @param colDesp
	 * @param printProdIdVal
	 * @param printProdDesp
	 * @param detVal
	 * @param uomDetValue
	 * @param printColDesp
	 * @param ppiVal
	 * @param uomPPI
	 * @param widthVal
	 * @param uomWidth
	 * @param lengthVal
	 * @param uomLength
	 * @param dbCon
	 * @param specLineNumber
	 * @param specId
	 * @param specVersion
	 * @param formatType
	 * @param nonPrepInpType
	 * @param cdRType
	 * @param noOfCols
	 * @return SpecComponent
	 */
	private SpecComponent setSpecComponent(String cmpType, String classCode, String agency, String compName, String prodIdVal, 
			String prodDesp, ArrayList bookClassificationList, String numOfPages, String numOfSig,
			String pagPerSig, String pressPrepInpType, String pressType, PCMargins pcMargins, String numOfCols,
			String colDesp, String printProdIdVal, String printProdDesp, String detVal, String uomDetValue,
			String printColDesp, String ppiVal, String uomPPI, String widthVal, String uomWidth, String lengthVal,
			String uomLength, Connection dbCon,	long specLineNumber, long specId, long specVersion,
			String formatType, String nonPrepInpType, String cdRType, String noOfCols){
		
		String sqlQuery				= null;
		String qryParams			= null;
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet6		= null;
		
		SpecComponent specComponent		= null;
		SCProdId scProdId				= null;
		ArrayList scProdDespList		= null;
		SCClassifn scClassifn			= null;
		SCFinidSize scFinidSize			= null;
		SCFSLength scFSLength			= null;
		SCFSLValue scFSLValue			= null;
		SCFSWidth scFSWidth				= null;
		SCFSWValue scFSWValue			= null;
		
		PressComponent pressCompt		= null;
		PCPressPrep pcPressPrep			= null;
		PCManfSpec pcManfSpec			= null;
		ArrayList pcColSpecList			= null;
		PCInkCharcs pcInkCharcs			= null;
		PCFinSpecs pcFinSpecs			= null;
		ArrayList pcFSCoatingList 		= null;
		PCFSCoating pcFSCoating			= null;
		PCPrMdSpecs pcPrMdSpecs			= null;
		ArrayList pcPrinMatList			= null;		
		PCPrinMat pcPrinMat				= null;
		PCPMatCharcs pcPMatCharcs 		= null;
		PCPMCProdId pcPMCProdId 		= null;
		ArrayList pcPMCProdDespList		= null;
		PCPMCBasWgt pcPMCBasWgt	 		= null;	
		PCPMCColSpec pcPMCColSpec		= null;
		PCPMCPPI pcPMCPPI 				= null;
		PCPMCWidth pcPMCWidth 			= null;
		PCPMCLength pcPMCLength 		= null;
		ArrayList scAddTextList			= null;
		SCAddText scAddText				= null;
		
		NonPressComponent nonPressCompnt	= null;
		Media media							= null;
		ArrayList medCDList					= null;
		MedCD medCD							= null;
		ArrayList cdPrePrepList				= null;
		CDPrePrep cdPrePrep					= null;
		ArrayList cdPrPrepInpList 			= null;
		CDPrPrepInp cdPrPrepInp				= null;
		ArrayList medDVDList				= null;
		MedDVD medDVD						= null;
		ArrayList dvdPrePrepList			= null;
		DvdPrePrep dvdPrePrep				= null;
		ArrayList dvdPrPrepInpList 			= null;
		DvdPrPrepInp dvdPrPrepInp			= null;
		Sleeve sleeve						= null;
		SCBookClasn bookClassification		= null;
		int compNameNum = -1; 
		String colPerSide					= null;
		
		String papStyle1					= "None";
		String basisWt1						= null;
		String papColor1					= null;
		String papRollSiz1					= null;
		//String sheetWid1					= null;
		String sheetLeng1					= null;
		
		String papStyle2					= "None";
		String basisWt2						= null;
		String papColor2					= null;
		String papRollSiz2					= null;
		//String sheetWid2					= null;
		String sheetLeng2					= null;
		
		String papStyle3					= "None";
		String basisWt3						= null;
		String papColor3					= null;
		String papRollSiz3					= null;
		//String sheetWid3					= null;
		String sheetLeng3					= null;
		
		String colorDescr					= null;
		String susbtrate					= null;
		String endPapDesc1					= null;
		String endPapDesc2					= null;
		
		String prodDesp1					= null;
		String colorKey						= null;
		String docPageCnt					= null;
		String silkscreenCol				= null;
		
		DvdJBColSpec dvdjbColSpec 			= null;
		DvdJewlBox dvdJewlBox				= null;
		ArrayList dvdPackgList				= null;
		DvdPackg dvdPackg					= null;
		
		JBColSpec jbColSpec 				= null;
		CDJewlBox cdJewlBox					= null;
		ArrayList cdPackgList				= null;
		CDPackg cdPackg						= null;
		
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecComponent() method called");
			if(prodDesp != null && !"".equals(prodDesp.trim()) 
					&& !"Insert 2".equalsIgnoreCase(prodDesp.trim())){
				
				specComponent = new SpecComponent();
					
				sqlQuery = qry_sel_pix_book_spec_misc;
				prepStmt1 = dbCon.prepareStatement(sqlQuery);
				prepStmt1.clearParameters();
				prepStmt1.setLong(IPixB2BConstants.ONE, specId);
				prepStmt1.setLong(IPixB2BConstants.TWO, specVersion);
				prepStmt1.setLong(IPixB2BConstants.THREE, specLineNumber);
				qryParams = specId+", "+specVersion+", "+specLineNumber;
				resultSet6 = prepStmt1.executeQuery();

				if(prodIdVal != null && !"".equals(prodIdVal.trim())){
					scProdId = setPressProdId(prodIdVal);
					specComponent.setScProdId(scProdId);
				}
				/*if(prodDesp != null && !"".equals(prodDesp.trim())){
					scProdDespList = setPressProdDespList(prodDesp);
					specComponent.setScProdDespList(scProdDespList);
				}*/
				/*if(classCode != null && !"".equals(classCode.trim()) &&
						(agency != null && !"".equals(agency.trim()))){
					scClassifn = setClassifn(classCode, agency);
					specComponent.setScClassifn(scClassifn);
				}*/
				if(bookClassificationList != null && bookClassificationList.size()>0){
					specComponent.setScBookClasnList(bookClassificationList);
					String bookclassType = null;
					for(int scnt=0; scnt<bookClassificationList.size(); scnt++){
						bookClassification = (SCBookClasn)bookClassificationList.get(scnt);
						if(null!=bookClassification){
							bookclassType = bookClassification.getBookClassType();
							scClassifn = setClassifn(bookclassType, "Buyer");
							if(null!=scClassifn)
								specComponent.setScClassifn(scClassifn);
						}
					}
				}
			}
			B2BHelper b2bHelpr = new B2BHelper();
			if(cmpType != null && !"".equals(cmpType.trim()))
			{
				if("PRESSCOMP".equalsIgnoreCase(cmpType.trim()))
				{
					if(compName != null && !"".equals(compName.trim())){
						compNameNum = getCompNameNum(compName);
						switch(compNameNum){
						case 1: scAddTextList = new ArrayList();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");

									if(label != null && !"".equals(label.trim()) && value != null && !"".equals(value.trim()) 
										&& ("Preparation".equalsIgnoreCase(label)|| "Number Illustr".equalsIgnoreCase(label)
										|| "Plate Type".equalsIgnoreCase(label)
										|| "First Plates".equalsIgnoreCase(label) || "Num Of Plate Changes".equalsIgnoreCase(label)
										|| "Reprint Plates".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
										|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
										|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
										|| "1 Halftone".equalsIgnoreCase(label) || "Paper Bulk".equalsIgnoreCase(label)
										|| "Paper 2 Bulk".equalsIgnoreCase(label) || "Paper 3 Bulk".equalsIgnoreCase(label)
										|| "Sheet Width".equalsIgnoreCase(label) || "Sheet 2 Width".equalsIgnoreCase(label)
										|| "Sheet 3 Width".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label))){
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
												//|| "Paper Roll Size".equalsIgnoreCase(label)
												//|| "Number Colors".equalsIgnoreCase(label)
												//|| "Paper Color".equalsIgnoreCase(label)
											}
										}else if("Paper Roll Size".equalsIgnoreCase(label)){
											/*pcPMatCharcs = new PCPMatCharcs();
											pcPMCProdId = setPrintProdId(printProdIdVal);
											if(pcPMCProdId!=null){
												pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
											}
											pcPMCWidth = setPrintWidth(value, "Inch");
											if(null!=pcPMCWidth){
												pcPMatCharcs.setPcPMCWidth(pcPMCWidth);
											}
											pcPrinMat = new PCPrinMat();
											pcPrinMat.setPcPMatCharcs(pcPMatCharcs);*/
											papRollSiz1=value;
										}else if("Paper Color".equalsIgnoreCase(label)){
											papColor1=value;
										}else if("Paper Style".equalsIgnoreCase(label)){
											papStyle1 = value;
										}else if("Basis Wt".equalsIgnoreCase(label)){
											basisWt1 = value;
										}else if("Sheet Length".equalsIgnoreCase(label)){
											sheetLeng1=value;
										}else if("Paper 2 Roll Size".equalsIgnoreCase(label)){
											papRollSiz2=value;
										}else if("Paper 2 Color".equalsIgnoreCase(label)){
											papColor2=value;
										}else if("Paper 2 Style".equalsIgnoreCase(label)){
											papStyle2 = value;
										}else if("Basis 2 Wt".equalsIgnoreCase(label)){
											basisWt2 = value;
										}else if("Sheet 2 Length".equalsIgnoreCase(label)){
											sheetLeng2=value;
										}else if("Paper 3 Roll Size".equalsIgnoreCase(label)){
											papRollSiz3=value;
										}else if("Paper 3 Color".equalsIgnoreCase(label)){
											papColor3=value;
										}else if("Paper 3 Style".equalsIgnoreCase(label)){
											papStyle3 = value;
										}else if("Basis 3 Wt".equalsIgnoreCase(label)){
											basisWt3 = value;
										}else if("Sheet 3 Length".equalsIgnoreCase(label)){
											sheetLeng3=value;
										}else if("Color Descr".equalsIgnoreCase(label)){
											colorDescr=value;
										}else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}
									}
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								pressCompt = new PressComponent();
								if(numOfPages != null && !"".equals(numOfPages.trim())){
									pressCompt.setNumbOfPages(numOfPages.trim());
								}

								/*pcPressPrep = setPressPrepInp(pressPrepInpType);
								if(pcPressPrep!=null)
									pressCompt.setPcPressPrep(pcPressPrep);*/

								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim())){
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}
								if(pcMargins!=null)
									pcManfSpec.setPcMargins(pcMargins);
								
								//pcColSpecList = setColSpecList(colDesp);
								/*Ashish:07/27/2012- CP# 472814 - The spec COLOR DESCRIPTION is getting omitted from XBITS transaction 
								if the primary spec NUMBER OF COLORS is missing on the component: Start*/
								if((null==colorDescr || "".equals(colorDescr.trim()))
									&& (null!=numOfCols && !"".equals(numOfCols.trim()))){
									
									//colorDescr = numOfCols+" "+"Color";
									colorDescr = "NOT DEFINED";
								}else if((null==numOfCols || "".equals(numOfCols.trim()))
									&& (null!=colorDescr && !"".equals(colorDescr.trim()))){
								
									numOfCols = "0";
									//colorDescr = null;
								}
								/*Ashish:07/27/2012- CP# 472814 - The spec COLOR DESCRIPTION is getting omitted from XBITS transaction 
								if the primary spec NUMBER OF COLORS is missing on the component: End*/
								
								pcColSpecList = setColSpecList(colorDescr, null, null);
								/*if(numOfCols != null && !"".equals(numOfCols.trim()) 
										&& pcColSpecList!=null && pcColSpecList.size()>0){*/
									pcInkCharcs = new PCInkCharcs();
									if(numOfCols != null && !"".equals(numOfCols.trim()))
										pcInkCharcs.setNumOfCol(numOfCols.trim());

									if(pcColSpecList!=null && pcColSpecList.size()>0)
										pcInkCharcs.setPcColSpecList(pcColSpecList);
									
									if(numOfCols != null && !"".equals(numOfCols.trim()) 
											|| (pcColSpecList!=null && pcColSpecList.size()>0)){
										pcManfSpec.setPcInkCharcs(pcInkCharcs);
									}else{
										pcInkCharcs=null;
									}
										
								//}
								pcPrinMatList = new ArrayList();
								//Paper 1
								if((null!=basisWt1 && !"".equalsIgnoreCase(basisWt1))||
										(null!=papColor1 && !"".equalsIgnoreCase(papColor1))||
										(null!=papRollSiz1 && !"".equalsIgnoreCase(papRollSiz1))||
										(null!=sheetLeng1 && !"".equalsIgnoreCase(sheetLeng1))||
										(null!=papStyle1 && !"None".equalsIgnoreCase(papStyle1))){	
									pcPMatCharcs = new PCPMatCharcs();
									//pcPMCProdId = setPrintProdId(printProdIdVal);
									pcPMCProdId = setPrintProdId(papStyle1);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									/*pcPMCProdDespList = setPrintProdDespList(printProdDesp);
									if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
										pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);*/
									
									//pcPMCBasWgt = setPrintBasWgt(detVal, uomDetValue);
									pcPMCBasWgt = setPrintBasWgt(basisWt1, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									//pcPMCColSpec = setPrintColSpec(printColDesp);
									pcPMCColSpec = setPrintColSpec(papColor1);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCPPI = setPrintPPI(ppiVal, uomPPI);
									if(pcPMCPPI!=null)
										pcPMatCharcs.setPcPMCPPI(pcPMCPPI);
									
									//pcPMCWidth = null;
									//pcPMCWidth = setPrintWidth(widthVal, uomWidth);
									pcPMCWidth = setPrintWidth(papRollSiz1, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									//pcPMCLength = setPrintLength(lengthVal, uomLength);
									pcPMCLength = setPrintLength(sheetLeng1, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								//Paper 2
								if((null!=basisWt2 && !"".equalsIgnoreCase(basisWt2))||
										(null!=papColor2 && !"".equalsIgnoreCase(papColor2))||
										(null!=papRollSiz2 && !"".equalsIgnoreCase(papRollSiz2))||
										(null!=sheetLeng2 && !"".equalsIgnoreCase(sheetLeng2))||
										(null!=papStyle2 && !"None".equalsIgnoreCase(papStyle2))){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(papStyle2);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									pcPMCBasWgt = setPrintBasWgt(basisWt2, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor2);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCPPI = setPrintPPI(ppiVal, uomPPI);
									if(pcPMCPPI!=null)
										pcPMatCharcs.setPcPMCPPI(pcPMCPPI);
									
									pcPMCWidth = setPrintWidth(papRollSiz2, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(sheetLeng2, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								//Paper 3
								if((null!=basisWt3 && !"".equalsIgnoreCase(basisWt3))||
										(null!=papColor3 && !"".equalsIgnoreCase(papColor3))||
										(null!=papRollSiz3 && !"".equalsIgnoreCase(papRollSiz3))||
										(null!=sheetLeng3 && !"".equalsIgnoreCase(sheetLeng3))||
										(null!=papStyle3 && !"None".equalsIgnoreCase(papStyle3))){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(papStyle3);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
								
									pcPMCBasWgt = setPrintBasWgt(basisWt3, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor3);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCPPI = setPrintPPI(ppiVal, uomPPI);
									if(pcPMCPPI!=null)
										pcPMatCharcs.setPcPMCPPI(pcPMCPPI);
									
									pcPMCWidth = setPrintWidth(papRollSiz3, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(sheetLeng3, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								//pcPrinMatList = new ArrayList();
								/*if(null!=pcPrinMat){
									pcPrinMatList.add(pcPrinMat);
								}*/
								
								/*pcPrinMat = new PCPrinMat();
								pcPrinMat.setPcPMatCharcs(pcPMatCharcs);*/
								
								//pcPrinMatList.add(pcPrinMat);
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)|| pcMargins!=null ||
										null!=pcInkCharcs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								
								if((numOfPages != null && !"".equals(numOfPages.trim()))
										|| null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}

								if(numOfSig != null && !"".equals(numOfSig.trim()) 
									&& pagPerSig != null && !"".equals(pagPerSig.trim()))
								{
									scAddText = new SCAddText();
									scAddText.setScAddtext("NumberOfSignatures:"+numOfSig.trim());
									scAddTextList.add(scAddText);
									scAddText = new SCAddText();
									scAddText.setScAddtext("PagesPerSignature:"+pagPerSig.trim());
									scAddTextList.add(scAddText);
								}
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
									break;
								}else{
									specComponent = null;
									break;
								}
								
						case 2:	scAddTextList = new ArrayList();
								pcFSCoatingList = new ArrayList();
								pcFinSpecs = new PCFinSpecs();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										/*if("Finish".equalsIgnoreCase(label)){
											pcFSCoating = new PCFSCoating();
											pcFSCoating.setCoatingVal(value);
											pcFSCoatingList.add(pcFSCoating);
										}
										else if("Full Ink".equalsIgnoreCase(label)){
											pcFSCoating = new PCFSCoating();
											pcFSCoating.setCoatCovType("FullCoverage");
											pcFSCoating.setCoatingVal(value);
											pcFSCoatingList.add(pcFSCoating);
										}
										else if("Matte Mylar".equalsIgnoreCase(label)){
											pcFSCoating = new PCFSCoating();
											pcFSCoating.setCoatingVal("MylarFilm");
											pcFSCoatingList.add(pcFSCoating);
											
											pcFSCoating = new PCFSCoating();
											pcFSCoating.setFinishType("Matte");
											pcFSCoating.setCoatingVal(value);
											pcFSCoatingList.add(pcFSCoating);
										}else */
										
										if("Colors per Side".equalsIgnoreCase(label)){
											colPerSide = value;
										}else if("Substrate".equalsIgnoreCase(label)){
											susbtrate = value;
										}else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Color Descr".equalsIgnoreCase(label)){
											colorDescr=value;
										}else if("Finish".equalsIgnoreCase(label)){
											ResultSet resultSet13 = getFinshValues(dbCon, value);
											String coatingType = null;
											String finishType = null;
											String coatCovType = null;
											while(resultSet13.next()){
												coatingType = resultSet13.getString("COATINGTYPE");
												finishType = resultSet13.getString("FINISHTYPE");
												coatCovType = resultSet13.getString("COATINGCOVERAGETYPE");
												
												if(null!=coatingType && !"".equals(coatingType.trim())){
													pcFSCoating = new PCFSCoating();
													pcFSCoating.setCoatingVal(coatingType.trim());
													if(null!=finishType && !"".equals(finishType.trim())){
														pcFSCoating.setFinishType(finishType.trim());	
													}
													if(null!=coatCovType && !"".equals(coatCovType.trim())){
														pcFSCoating.setCoatCovType(coatCovType.trim());
													}
													pcFSCoatingList.add(pcFSCoating);
												}
											}
											if(null==coatingType || "".equals(coatingType.trim())){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
												
												pcFSCoating = new PCFSCoating();
												pcFSCoating.setCoatingVal("Non-Standard");
												pcFSCoatingList.add(pcFSCoating);
											}
											DBUtils.close(resultSet13);
										}
										else if("Trim Descr".equalsIgnoreCase(label)){
											scFinidSize = setFinidSize(dbCon, value);
											if(null==scFinidSize){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
										else if("Overage Print Qty".equalsIgnoreCase(label) || "Plate Type".equalsIgnoreCase(label)
											|| "First Plates".equalsIgnoreCase(label) || "Num Of Plate Changes".equalsIgnoreCase(label)
											|| "Reprint Plates".equalsIgnoreCase(label)|| "Preparation".equalsIgnoreCase(label)
											|| "Press Proofs".equalsIgnoreCase(label)|| "Color Key".equalsIgnoreCase(label)
											|| "Cromalin".equalsIgnoreCase(label)|| "Custom Cromalin".equalsIgnoreCase(label)
											|| "Blues 32s".equalsIgnoreCase(label)|| "Blues 16s".equalsIgnoreCase(label)
											|| "Blues Description".equalsIgnoreCase(label) || "ManufacturingIncr Rt".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "No Black".equalsIgnoreCase(label)
											|| "Number Pieces".equalsIgnoreCase(label)
											|| "State Label Required".equalsIgnoreCase(label) || "Stamping Ink Hits".equalsIgnoreCase(label)
											|| "Stamping Foil Hits".equalsIgnoreCase(label)	|| "Stamping Blind Hits".equalsIgnoreCase(label)
											|| "Ink Descr".equalsIgnoreCase(label) || "Foil Descr".equalsIgnoreCase(label)
											|| "Blind Descr".equalsIgnoreCase(label) || "Square Inches Foil".equalsIgnoreCase(label)
											|| "Post Emboss".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label)
											|| "Full Ink".equalsIgnoreCase(label)
											|| "Matte Mylar".equalsIgnoreCase(label) || "Bind Type".equalsIgnoreCase(label)
											|| "Bind Style".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												//|| "Finish".equalsIgnoreCase(label)
												//|| "Trim Descr".equalsIgnoreCase(label)
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								}
								if(null!=scFinidSize)
									specComponent.setScFinidSize(scFinidSize);
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								pressCompt = new PressComponent();
								if(numOfPages != null && !"".equals(numOfPages.trim())){
									pressCompt.setNumbOfPages(numOfPages.trim());
								}
								
								/*pcPressPrep = setPressPrepInp(pressPrepInpType);
								if(pcPressPrep!=null)
									pressCompt.setPcPressPrep(pcPressPrep);*/
	
								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim())){
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}
								
								//pcColSpecList = setCovColSpecList(colDesp, colPerSide);
								if((null==colorDescr || "".equals(colorDescr.trim()))
										&& (null!=numOfCols && !"".equals(numOfCols.trim()))){
										
										//colorDescr = numOfCols+" "+"Color";
									colorDescr = "NOT DEFINED";
								}else if((null==numOfCols || "".equals(numOfCols.trim()))
										&& ((null!=colorDescr && !"".equals(colorDescr.trim()))
										|| (null!=colPerSide && !"".equals(colPerSide.trim())))){
									
										numOfCols = "0";
									//colorDescr = null;
									//colPerSide = null;
								}
								pcColSpecList = setCovColSpecList(colorDescr, null, colPerSide);
								/*if(numOfCols != null && !"".equals(numOfCols.trim()) 
										&& pcColSpecList!=null && pcColSpecList.size()>0){*/
									pcInkCharcs = new PCInkCharcs();
									if(numOfCols != null && !"".equals(numOfCols.trim()))
										pcInkCharcs.setNumOfCol(numOfCols.trim());
									
									if(pcColSpecList!=null && pcColSpecList.size()>0)
										pcInkCharcs.setPcColSpecList(pcColSpecList);
									
									
									if(numOfCols != null && !"".equals(numOfCols.trim()) 
											|| (pcColSpecList!=null && pcColSpecList.size()>0)){
										pcManfSpec.setPcInkCharcs(pcInkCharcs);
									}else{
										pcInkCharcs=null;
									}
								//}

								if(null!=pcFSCoatingList && pcFSCoatingList.size()>0){
									pcFinSpecs.setPcFSCoatingList(pcFSCoatingList);
									pcManfSpec.setPcFinSpecs(pcFinSpecs);
								}else{
									pcFinSpecs=null;
								}
									
								pcPrinMatList = new ArrayList();
								if(null!=susbtrate && !"".equalsIgnoreCase(susbtrate)){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(susbtrate);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
									
								/*pcPMatCharcs = new PCPMatCharcs();
								pcPMCProdId = setPrintProdId(printProdIdVal);
								if(pcPMCProdId!=null){
									pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
								}*/
								/*pcPMCProdDespList = setPrintProdDespList(printProdDesp);
								if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
									pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);*/
								
								/*pcPrinMat = new PCPrinMat();								
								pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
								pcPrinMatList = new ArrayList();
								pcPrinMatList.add(pcPrinMat);
								pcManfSpec.setPcPrinMatList(pcPrinMatList);*/
								
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
					
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)||
										null!=pcInkCharcs || null!=pcFinSpecs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								if((numOfPages != null && !"".equals(numOfPages.trim()))
										|| null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0) ||
										(null!=scFinidSize)){
									break;
								}else{
									specComponent = null;
									break;
								}
								
						case 3:	scAddTextList = new ArrayList();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Trim Descr".equalsIgnoreCase(label)){
											scFinidSize = setFinidSize(dbCon, value);
											if(null==scFinidSize){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
//										else if("Insert Type".equalsIgnoreCase(label)){
//											scClassDesp = new SCClassDesp();
//											scClassDesp.setScClassDesp(value);
//											scClassDespList = new ArrayList();
//											scClassDespList.add(scClassDesp);
//											scClassifn = new SCClassifn();
//											scClassifn.setScClassDespList(scClassDespList);
//										}
										else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Color Descr".equalsIgnoreCase(label)){
											colorDescr=value;
										}/*else if("Paper Roll Size".equalsIgnoreCase(label)){
											papRollSiz1=value;
										}*/else if("Substrate".equalsIgnoreCase(label)){
											susbtrate = value;
										}else if("Color Key".equalsIgnoreCase(label)){
											colorKey = value;
										}else if("Paper Color".equalsIgnoreCase(label)){
											papColor1=value;
										}else if("Paper Style".equalsIgnoreCase(label)){
											papStyle1 = value;
										}else if("Basis Wt".equalsIgnoreCase(label)){
											basisWt1 = value;
										}/*else if("Sheet Length".equalsIgnoreCase(label)){
											sheetLeng1=value;
										}*/else if("Paper 2 Roll Size".equalsIgnoreCase(label)){
											papRollSiz2=value;
										}else if("Paper 2 Color".equalsIgnoreCase(label)){
											papColor2=value;
										}else if("Paper 2 Style".equalsIgnoreCase(label)){
											papStyle2 = value;
										}else if("Basis 2 Wt".equalsIgnoreCase(label)){
											basisWt2 = value;
										}else if("Sheet 2 Length".equalsIgnoreCase(label)){
											sheetLeng2=value;
										}else if("Paper 3 Roll Size".equalsIgnoreCase(label)){
											papRollSiz3=value;
										}else if("Paper 3 Color".equalsIgnoreCase(label)){
											papColor3=value;
										}else if("Paper 3 Style".equalsIgnoreCase(label)){
											papStyle3 = value;
										}else if("Basis 3 Wt".equalsIgnoreCase(label)){
											basisWt3 = value;
										}else if("Sheet 3 Length".equalsIgnoreCase(label)){
											sheetLeng3=value;
										}else if("Plate Type".equalsIgnoreCase(label) || "Num Of Plate Changes".equalsIgnoreCase(label) 
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Vendor To Supply".equalsIgnoreCase(label) 
											|| "Paper Style".equalsIgnoreCase(label) || "Sheeting 2 Rate".equalsIgnoreCase(label)
											|| "Preparation".equalsIgnoreCase(label) || "Press Proofs".equalsIgnoreCase(label)
											|| "Cromalin".equalsIgnoreCase(label)|| "Custom Cromalin".equalsIgnoreCase(label)
											|| "First Plates".equalsIgnoreCase(label)|| "Reprint Plates".equalsIgnoreCase(label)
											|| "Blues 32s".equalsIgnoreCase(label)|| "Blues 16s".equalsIgnoreCase(label)
											|| "Blues Description".equalsIgnoreCase(label) || "ManufacturingIncr Rt".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) 
											|| "Copy Supplied".equalsIgnoreCase(label) || "Insert Type".equalsIgnoreCase(label)
											|| "Paper Bulk".equalsIgnoreCase(label)
											|| "Paper 2 Bulk".equalsIgnoreCase(label) || "Paper 3 Bulk".equalsIgnoreCase(label)
											|| "Sheet 2 Width".equalsIgnoreCase(label)|| "Sheet 3 Width".equalsIgnoreCase(label)
											|| "Sheeting 3 Rate".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												//|| "Trim Descr".equalsIgnoreCase(label)
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								}
								if(null!=scFinidSize)
									specComponent.setScFinidSize(scFinidSize);
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								pressCompt = new PressComponent();
								if(numOfPages != null && !"".equals(numOfPages.trim())){
									pressCompt.setNumbOfPages(numOfPages.trim());
								}
								
								/*pcPressPrep = setPressPrepInp(pressPrepInpType);
								if(pcPressPrep!=null)
									pressCompt.setPcPressPrep(pcPressPrep);*/
								
								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim())){
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}
								if(pcMargins!=null)
									pcManfSpec.setPcMargins(pcMargins);
								
								//pcColSpecList = setColSpecList(colDesp);
								if((null==colorDescr || "".equals(colorDescr.trim()))
										&& (null!=numOfCols && !"".equals(numOfCols.trim()))){
										
										//colorDescr = numOfCols+" "+"Color";
									colorDescr = "NOT DEFINED";
								}else if((null==numOfCols || "".equals(numOfCols.trim()))
										&& ((null!=colorDescr && !"".equals(colorDescr.trim()))
										|| (null!=colorKey && !"".equals(colorKey.trim())))){
									
										numOfCols = "0";
									//colorDescr = null;
									//colorKey = null;
								}
								pcColSpecList = setColSpecList(colorDescr, null, colorKey);
								/*if(numOfCols != null && !"".equals(numOfCols.trim()) 
										&& pcColSpecList!=null && pcColSpecList.size()>0){*/
								pcInkCharcs = new PCInkCharcs();
								if(numOfCols != null && !"".equals(numOfCols.trim()))
									pcInkCharcs.setNumOfCol(numOfCols.trim());
								
								if(pcColSpecList!=null && pcColSpecList.size()>0)
									pcInkCharcs.setPcColSpecList(pcColSpecList);
								
								if(numOfCols != null && !"".equals(numOfCols.trim()) 
										|| (pcColSpecList!=null && pcColSpecList.size()>0)){
									pcManfSpec.setPcInkCharcs(pcInkCharcs);									
								}else{
									pcInkCharcs=null;
								}

								//}
					
								/*pcPMCProdId = setPrintProdId(printProdIdVal);
								if(pcPMCProdId!=null){
									pcPMatCharcs = new PCPMatCharcs();
									pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									
									pcPMCProdDespList = setPrintProdDespList(printProdDesp, null);
									if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
										pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);
									
									pcPMCBasWgt = setPrintBasWgt(detVal, uomDetValue);
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt); 
									
									pcPMCColSpec = setPrintColSpec(printColDesp);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);
									
									pcPMCWidth = setPrintWidth(widthVal, uomWidth);
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(lengthVal, uomLength);
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									pcPrinMat = new PCPrinMat();								
									pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									pcPrinMatList = new ArrayList();
									pcPrinMatList.add(pcPrinMat);
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								}*/
									
								pcPrinMatList = new ArrayList();
								//Paper 1
								if((null!=basisWt1 && !"".equalsIgnoreCase(basisWt1))||
										(null!=papColor1 && !"".equalsIgnoreCase(papColor1))||
										(null!=papStyle1 && !"None".equalsIgnoreCase(papStyle1))){	
									pcPMatCharcs = new PCPMatCharcs();

									pcPMCProdId = setPrintProdId(papStyle1);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									/*pcPMCProdDespList = setPrintProdDespList(susbtrate, null);
									if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
										pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);*/
									
									pcPMCBasWgt = setPrintBasWgt(basisWt1, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor1);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								if(null!=susbtrate && !"".equalsIgnoreCase(susbtrate)){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(susbtrate);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								//Paper 2
								if((null!=basisWt2 && !"".equalsIgnoreCase(basisWt2))||
										(null!=papColor2 && !"".equalsIgnoreCase(papColor2))||
										(null!=papRollSiz2 && !"".equalsIgnoreCase(papRollSiz2))||
										(null!=sheetLeng2 && !"".equalsIgnoreCase(sheetLeng2))||
										(null!=papStyle2 && !"None".equalsIgnoreCase(papStyle2))){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(papStyle2);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									pcPMCBasWgt = setPrintBasWgt(basisWt2, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor2);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCWidth = setPrintWidth(papRollSiz2, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(sheetLeng2, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								//Paper 3
								if((null!=basisWt3 && !"".equalsIgnoreCase(basisWt3))||
										(null!=papColor3 && !"".equalsIgnoreCase(papColor3))||
										(null!=papRollSiz3 && !"".equalsIgnoreCase(papRollSiz3))||
										(null!=sheetLeng3 && !"".equalsIgnoreCase(sheetLeng3))||
										(null!=papStyle3 && !"None".equalsIgnoreCase(papStyle3))){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(papStyle3);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
								
									pcPMCBasWgt = setPrintBasWgt(basisWt3, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor3);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCWidth = setPrintWidth(papRollSiz3, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(sheetLeng3, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)|| pcMargins!=null ||
										null!=pcInkCharcs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								
								if((numOfPages != null && !"".equals(numOfPages.trim()))
										|| null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0) ||
										(null!=scFinidSize)){
									break;
								}else{
									specComponent = null;
									break;
								}
							
						case 4:	scAddTextList = new ArrayList();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Trim Descr".equalsIgnoreCase(label)){
											scFinidSize = setFinidSize(dbCon, value);
											if(null==scFinidSize){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}else if("Description".equalsIgnoreCase(label)){
											endPapDesc1 = value;											
										}else if("End Paper Descr".equalsIgnoreCase(label)){
											endPapDesc2 = value;											
										}else if("Substrate".equalsIgnoreCase(label)){
											susbtrate = value;
										}else if("Paper Color".equalsIgnoreCase(label)){
											papColor1=value;
										}else if("Paper Style".equalsIgnoreCase(label)){
											papStyle1 = value;
										}else if("Basis Wt".equalsIgnoreCase(label)){
											basisWt1 = value;
										}else if("Paper 2 Roll Size".equalsIgnoreCase(label)){
											papRollSiz2=value;
										}else if("Paper 2 Color".equalsIgnoreCase(label)){
											papColor2=value;
										}else if("Paper 2 Style".equalsIgnoreCase(label)){
											papStyle2 = value;
										}else if("Basis 2 Wt".equalsIgnoreCase(label)){
											basisWt2 = value;
										}else if("Sheet 2 Length".equalsIgnoreCase(label)){
											sheetLeng2=value;
										}else if("Paper 3 Roll Size".equalsIgnoreCase(label)){
											papRollSiz3=value;
										}else if("Paper 3 Color".equalsIgnoreCase(label)){
											papColor3=value;
										}else if("Paper 3 Style".equalsIgnoreCase(label)){
											papStyle3 = value;
										}else if("Basis 3 Wt".equalsIgnoreCase(label)){
											basisWt3 = value;
										}else if("Sheet 3 Length".equalsIgnoreCase(label)){
											sheetLeng3=value;
										}else if("Color Descr".equalsIgnoreCase(label)){
											colorDescr=value;
										}
										else if("Plate Type".equalsIgnoreCase(label) || "Num Of Plate Changes".equalsIgnoreCase(label) 
											|| "Vendor To Supply".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label) 
											|| "Preparation".equalsIgnoreCase(label) || "Press Proofs".equalsIgnoreCase(label)
											|| "Cromalin".equalsIgnoreCase(label)|| "Custom Cromalin".equalsIgnoreCase(label)
											|| "First Plates".equalsIgnoreCase(label)|| "Reprint Plates".equalsIgnoreCase(label)
											|| "Blues 32s".equalsIgnoreCase(label)|| "Blues 16s".equalsIgnoreCase(label)
											|| "Blues Description".equalsIgnoreCase(label) || "ManufacturingIncr Rt".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Sheeting 2 Rate".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label)
											|| "Paper Bulk".equalsIgnoreCase(label) || "Sheeting 3 Rate".equalsIgnoreCase(label)
											|| "Paper 2 Bulk".equalsIgnoreCase(label) || "Paper 3 Bulk".equalsIgnoreCase(label)
											|| "Sheet 2 Width".equalsIgnoreCase(label)|| "Sheet 3 Width".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												// || "Trim Descr".equalsIgnoreCase(label)
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								}
								if(null!=scFinidSize)
									specComponent.setScFinidSize(scFinidSize);
								
								scProdDespList = setPressProdDespList(prodDesp, endPapDesc1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								pressCompt = new PressComponent();
								if(numOfPages != null && !"".equals(numOfPages.trim())){
									pressCompt.setNumbOfPages(numOfPages.trim());
								}
						
								/*pcPressPrep = setPressPrepInp(pressPrepInpType);
								if(pcPressPrep!=null)
									pressCompt.setPcPressPrep(pcPressPrep);*/
								
								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim())){
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}
								if(pcMargins!=null)
									pcManfSpec.setPcMargins(pcMargins);
											
								//pcColSpecList = setColSpecList(colDesp);
								if((null==colorDescr || "".equals(colorDescr.trim()))
										&& (null!=numOfCols && !"".equals(numOfCols.trim()))){
										
										//colorDescr = numOfCols+" "+"Color";
									colorDescr = "NOT DEFINED";
								}else if((null==numOfCols || "".equals(numOfCols.trim()))
										&& ((null!=colorDescr && !"".equals(colorDescr.trim()))
										|| (null!=endPapDesc2 && !"".equals(endPapDesc2.trim())))){
									
									  numOfCols = "0";
									//colorDescr = null;
									//endPapDesc2 =null;
								}
								pcColSpecList = setColSpecList(endPapDesc2,colorDescr, null);
								/*if(numOfCols != null && !"".equals(numOfCols.trim())
										&& pcColSpecList!=null && pcColSpecList.size()>0){*/
								pcInkCharcs = new PCInkCharcs();
								if(numOfCols != null && !"".equals(numOfCols.trim()))
									pcInkCharcs.setNumOfCol(numOfCols.trim());
												
								if(pcColSpecList!=null && pcColSpecList.size()>0)
									pcInkCharcs.setPcColSpecList(pcColSpecList);
								
								if(numOfCols != null && !"".equals(numOfCols.trim())
										|| (pcColSpecList!=null && pcColSpecList.size()>0)){
									pcManfSpec.setPcInkCharcs(pcInkCharcs);	
								}else{
									pcInkCharcs=null;
								}
									
								//}
								
								pcPrinMatList = new ArrayList();
								//Paper 1
								if((null!=basisWt1 && !"".equalsIgnoreCase(basisWt1))||
										(null!=papColor1 && !"".equalsIgnoreCase(papColor1))||
										(null!=papStyle1 && !"None".equalsIgnoreCase(papStyle1))){	
									pcPMatCharcs = new PCPMatCharcs();

									pcPMCProdId = setPrintProdId(papStyle1);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									/*pcPMCProdDespList = setPrintProdDespList(susbtrate, null);
									if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
										pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);*/
									
									pcPMCBasWgt = setPrintBasWgt(basisWt1, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor1);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								if(null!=susbtrate && !"".equalsIgnoreCase(susbtrate)){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(susbtrate);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								//Paper 2
								if((null!=basisWt2 && !"".equalsIgnoreCase(basisWt2))||
										(null!=papColor2 && !"".equalsIgnoreCase(papColor2))||
										(null!=papRollSiz2 && !"".equalsIgnoreCase(papRollSiz2))||
										(null!=sheetLeng2 && !"".equalsIgnoreCase(sheetLeng2))||
										(null!=papStyle2 && !"None".equalsIgnoreCase(papStyle2))){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(papStyle2);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									pcPMCBasWgt = setPrintBasWgt(basisWt2, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor2);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCWidth = setPrintWidth(papRollSiz2, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(sheetLeng2, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								//Paper 3
								if((null!=basisWt3 && !"".equalsIgnoreCase(basisWt3))||
										(null!=papColor3 && !"".equalsIgnoreCase(papColor3))||
										(null!=papRollSiz3 && !"".equalsIgnoreCase(papRollSiz3))||
										(null!=sheetLeng3 && !"".equalsIgnoreCase(sheetLeng3))||
										(null!=papStyle3 && !"None".equalsIgnoreCase(papStyle3))){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(papStyle3);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
								
									pcPMCBasWgt = setPrintBasWgt(basisWt3, "Pound");
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt);

									pcPMCColSpec = setPrintColSpec(papColor3);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);

									pcPMCWidth = setPrintWidth(papRollSiz3, "Inch");
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	

									pcPMCLength = setPrintLength(sheetLeng3, "Inch");
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
									
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)|| pcMargins!=null ||
										null!=pcInkCharcs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								
								if((numOfPages != null && !"".equals(numOfPages.trim()))
										|| null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}

								/*pcPMCProdId = setPrintProdId(printProdIdVal);
								if(pcPMCProdId!=null){
									pcPMatCharcs = new PCPMatCharcs();
									pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									
									pcPMCProdDespList = setPrintProdDespList(endPapDesc1, null);
									if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
										pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);
													
									pcPMCBasWgt = setPrintBasWgt(detVal, uomDetValue);
									if(pcPMCBasWgt!=null)
										pcPMatCharcs.setPcPMCBasWgt(pcPMCBasWgt); 
													
									pcPMCColSpec = setPrintColSpec(printColDesp);
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);
													
									pcPMCWidth = setPrintWidth(widthVal, uomWidth);
									if(pcPMCWidth!=null)
										pcPMatCharcs.setPcPMCWidth(pcPMCWidth);	
							
									pcPMCLength = setPrintLength(lengthVal, uomLength);
									if(pcPMCLength!=null)
										pcPMatCharcs.setPcPMCLength(pcPMCLength);
													
									pcPrinMat = new PCPrinMat();								
									pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									pcPrinMatList = new ArrayList();
									pcPrinMatList.add(pcPrinMat);
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								}*/
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);

								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0) ||
										(null!=scFinidSize)){
									break;
								}else{
									specComponent = null;
									break;
								}			

						case 5:	scAddTextList = new ArrayList();
								pcFSCoatingList = new ArrayList();
								pcFinSpecs = new PCFinSpecs();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Trim Descr".equalsIgnoreCase(label)){
											scFinidSize = setFinidSize(dbCon, value);
											if(null==scFinidSize){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
										/*else if(label != null && !"".equals(label.trim()) &&
												value != null && !"".equals(value.trim())){
											if("Finish".equalsIgnoreCase(label)){
												pcFSCoating = new PCFSCoating();
												pcFSCoating.setCoatingVal(value);
												pcFSCoatingList.add(pcFSCoating);
											}
											else if("Full Ink".equalsIgnoreCase(label)){
												pcFSCoating = new PCFSCoating();
												pcFSCoating.setCoatCovType("FullCoverage");
												pcFSCoating.setCoatingVal(value);
												pcFSCoatingList.add(pcFSCoating);
											}
										}*/
										else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Substrate".equalsIgnoreCase(label)){
											susbtrate = value;
										}else if("Colors per Side".equalsIgnoreCase(label)){
											colPerSide = value;
										}else if("Color Descr".equalsIgnoreCase(label)){
											colorDescr=value;
										}else if("Finish".equalsIgnoreCase(label)){
											ResultSet resultSet12 = getFinshValues(dbCon, value);
											String coatingType = null;
											String finishType = null;
											String coatCovType = null;
											while(resultSet12.next()){
												coatingType = resultSet12.getString("COATINGTYPE");
												finishType = resultSet12.getString("FINISHTYPE");
												coatCovType = resultSet12.getString("COATINGCOVERAGETYPE");
												
												if(null!=coatingType && !"".equals(coatingType.trim())){
													pcFSCoating = new PCFSCoating();
													pcFSCoating.setCoatingVal(coatingType.trim());
													if(null!=finishType && !"".equals(finishType.trim())){
														pcFSCoating.setFinishType(finishType.trim());	
													}
													if(null!=coatCovType && !"".equals(coatCovType.trim())){
														pcFSCoating.setCoatCovType(coatCovType.trim());
													}
													pcFSCoatingList.add(pcFSCoating);
												}
											}
											if(null==coatingType || "".equals(coatingType.trim())){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
												
												pcFSCoating = new PCFSCoating();
												pcFSCoating.setCoatingVal("Non-Standard");
												pcFSCoatingList.add(pcFSCoating);
											}
											DBUtils.close(resultSet12);
										}else if("Plate Type".equalsIgnoreCase(label) || "Num Of Plate Changes".equalsIgnoreCase(label)
											|| "No Black".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Preparation".equalsIgnoreCase(label) || "Press Proofs".equalsIgnoreCase(label)
											|| "Cromalin".equalsIgnoreCase(label)|| "Custom Cromalin".equalsIgnoreCase(label)
											|| "First Plates".equalsIgnoreCase(label)|| "Reprint Plates".equalsIgnoreCase(label)
											|| "Blues 32s".equalsIgnoreCase(label)|| "Blues 16s".equalsIgnoreCase(label)
											|| "Blues Description".equalsIgnoreCase(label) || "ManufacturingIncr Rt".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label)
											|| "Full Ink".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Stamping Ink Hits".equalsIgnoreCase(label)
											|| "Stamping Foil Hits".equalsIgnoreCase(label)	|| "Stamping Blind Hits".equalsIgnoreCase(label)
											|| "Ink Descr".equalsIgnoreCase(label) || "Foil Descr".equalsIgnoreCase(label)
											|| "Blind Descr".equalsIgnoreCase(label) || "Square Inches Foil".equalsIgnoreCase(label)
											|| "Color Key".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												//|| "Finish".equalsIgnoreCase(label) 
												//|| "Trim Descr".equalsIgnoreCase(label)
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								}
								if(null!=scFinidSize)
									specComponent.setScFinidSize(scFinidSize);
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								pressCompt = new PressComponent();
				
								/*pcPressPrep = setPressPrepInp(pressPrepInpType);
								if(pcPressPrep!=null)
									pressCompt.setPcPressPrep(pcPressPrep);*/
												
								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim())){
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}

								//pcColSpecList = setColSpecList(colDesp, null, null);
								if((null==colorDescr || "".equals(colorDescr.trim()))
										&& (null!=numOfCols && !"".equals(numOfCols.trim()))){
										
										//colorDescr = numOfCols+" "+"Color";
									colorDescr = "NOT DEFINED";
								}else if((null==numOfCols || "".equals(numOfCols.trim()))
										&& ((null!=colorDescr && !"".equals(colorDescr.trim()))
										|| (null!=colPerSide && !"".equals(colPerSide.trim())))){
									
									  numOfCols = "0";
									//colorDescr = null;
									//colPerSide = null;
								}
								pcColSpecList = setCovColSpecList(colorDescr, null, colPerSide);
								/*if(numOfCols != null && !"".equals(numOfCols.trim())
										&& pcColSpecList!=null && pcColSpecList.size()>0){*/
								pcInkCharcs = new PCInkCharcs();
								if(numOfCols != null && !"".equals(numOfCols.trim()))
									pcInkCharcs.setNumOfCol(numOfCols.trim());
																
								if(pcColSpecList!=null && pcColSpecList.size()>0)
									pcInkCharcs.setPcColSpecList(pcColSpecList);
																
								if(numOfCols != null && !"".equals(numOfCols.trim())
										|| (pcColSpecList!=null && pcColSpecList.size()>0)){
									pcManfSpec.setPcInkCharcs(pcInkCharcs);	
								}else{
									pcInkCharcs=null;
								}
									
								//}
								if(pcFSCoatingList!=null && pcFSCoatingList.size()>0){
									pcFinSpecs.setPcFSCoatingList(pcFSCoatingList);
									pcManfSpec.setPcFinSpecs(pcFinSpecs);
								}else{
									pcFinSpecs=null;
								}
								
								pcPrinMatList = new ArrayList();
								if(null!=susbtrate && !"".equalsIgnoreCase(susbtrate)){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(susbtrate);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
								
								
								/*pcPMCProdId = setPrintProdId(printProdIdVal);
								if(pcPMCProdId!=null){
									pcPMatCharcs = new PCPMatCharcs();
									pcPMatCharcs.setPcPMCProdId(pcPMCProdId);*/
									
									//pcPMCProdDespList = setPrintProdDespList(printProdDesp);
									/*pcPMCProdDespList = setPrintProdDespList(printProdDesp, null);
									if(pcPMCProdDespList!=null && pcPMCProdDespList.size()>0)
										pcPMatCharcs.setPcPMCProdDespList(pcPMCProdDespList);
																	
									pcPrinMat = new PCPrinMat();								
									pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									pcPrinMatList = new ArrayList();
									pcPrinMatList.add(pcPrinMat);
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								}*/
								
								
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
					
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)||
										null!=pcInkCharcs || null!=pcFinSpecs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								if(null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);

								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0) ||
										(null!=scFinidSize)){
									break;
								}else{
									specComponent = null;
									break;
								}
								
						case 6:	scAddTextList = new ArrayList();
								pcFSCoatingList = new ArrayList();
								pcFinSpecs = new PCFinSpecs();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										/*if("Size".equalsIgnoreCase(label)){
											String[] temp = value.split("x | X");
											if(temp[0] != null || temp[1] != null)
												scFinidSize = setFinidSize(temp[0], temp[1]); 
										}else */
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Card Colors Sides".equalsIgnoreCase(label)){
											scAddText = new SCAddText();
											scAddText.setScAddtext(label+":"+value);
											scAddTextList.add(scAddText);
										}else if("Card Substrate".equalsIgnoreCase(label)){
											susbtrate = value;
										}else if("Card Colors".equalsIgnoreCase(label)){
											//colorDescr=value;
											scAddText = new SCAddText();
											scAddText.setScAddtext(label+":"+value);
											scAddTextList.add(scAddText);
										}else if("Card Finishing".equalsIgnoreCase(label)){
											ResultSet resultSet12 = getFinshValues(dbCon, value);
											String coatingType = null;
											String finishType = null;
											String coatCovType = null;
											while(resultSet12.next()){
												coatingType = resultSet12.getString("COATINGTYPE");
												finishType = resultSet12.getString("FINISHTYPE");
												coatCovType = resultSet12.getString("COATINGCOVERAGETYPE");
												
												if(null!=coatingType && !"".equals(coatingType.trim())){
													pcFSCoating = new PCFSCoating();
													pcFSCoating.setCoatingVal(coatingType.trim());
													if(null!=finishType && !"".equals(finishType.trim())){
														pcFSCoating.setFinishType(finishType.trim());	
													}
													if(null!=coatCovType && !"".equals(coatCovType.trim())){
														pcFSCoating.setCoatCovType(coatCovType.trim());
													}
													pcFSCoatingList.add(pcFSCoating);
												}
											}
											if(null==coatingType || "".equals(coatingType.trim())){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
												
												pcFSCoating = new PCFSCoating();
												pcFSCoating.setCoatingVal("Non-Standard");
												pcFSCoatingList.add(pcFSCoating);
											}
											DBUtils.close(resultSet12);
										}else if("Cards Per Set".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Size".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Collation Rate".equalsIgnoreCase(label)
											|| "Insertion Req ".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label)
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label)|| "Single Source?".equalsIgnoreCase(label)
											|| "Trim Size".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												//|| "Card Finishing".equalsIgnoreCase(label)
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								}
								/*if(scFinidSize!=null)
									specComponent.setScFinidSize(scFinidSize);*/
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
																				
								pressCompt = new PressComponent();
								
								/*pcPressPrep = setPressPrepInp(pressPrepInpType);
								if(pcPressPrep!=null)
									pressCompt.setPcPressPrep(pcPressPrep);*/
																
								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim()))
								{
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}
								
								/*pcColSpecList = setColSpecList(colorDescr, null);
								if(pcColSpecList!=null && pcColSpecList.size()>0){
									pcInkCharcs = new PCInkCharcs();
									pcInkCharcs.setPcColSpecList(pcColSpecList);
									pcManfSpec.setPcInkCharcs(pcInkCharcs);
								}else{
									pcInkCharcs=null;
								}*/
								if(pcFSCoatingList!=null && pcFSCoatingList.size()>0){
									pcFinSpecs.setPcFSCoatingList(pcFSCoatingList);
									pcManfSpec.setPcFinSpecs(pcFinSpecs);
								}else{
									pcFinSpecs = null;
								}
								
								pcPrinMatList = new ArrayList();
								if(null!=susbtrate && !"".equalsIgnoreCase(susbtrate)){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(susbtrate);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
									
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
					
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)||
										null!=pcInkCharcs || null!=pcFinSpecs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								
								if(null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
									break;
								}else{
									specComponent = null;
									break;
								}
								
						case 7:	scAddTextList = new ArrayList();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if ("Doc Colors".equalsIgnoreCase(label)){
											pcPMCColSpec = setPrintColSpec(value);
										}else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Doc Page Count".equalsIgnoreCase(label)){
											docPageCnt=value;
										}else if("Overage Print Qty".equalsIgnoreCase(label)){
											 pcPrMdSpecs = new PCPrMdSpecs();
											 pcPrMdSpecs.setNumbOfSides(value);
										}else if("Doc Trim Size".equalsIgnoreCase(label)){
											scFinidSize = setFinidSize(dbCon, value);
											if(null==scFinidSize){
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}else if("Doc Printed Sides".equalsIgnoreCase(label) || "Doc Stock".equalsIgnoreCase(label)
											|| "Doc Finish".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label)
											|| "Unit Rate".equalsIgnoreCase(label) || "Packaging".equalsIgnoreCase(label)
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Vendor 1".equalsIgnoreCase(label)
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label)|| "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												//|| "Doc Trim Size".equalsIgnoreCase(label)
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								}
								if(null!=scFinidSize)
									specComponent.setScFinidSize(scFinidSize);
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								pressCompt = new PressComponent();
								if(docPageCnt != null && !"".equals(docPageCnt.trim())){
									pressCompt.setNumbOfPages(docPageCnt.trim());
								}
								
								pcManfSpec = new PCManfSpec();
								if(pressType != null && !"".equals(pressType.trim()))
								{
									//pcManfSpec.setPressType(pressType.trim());
									scAddText = new SCAddText();
									scAddText.setScAddtext("Press Type:"+pressType.trim());
									scAddTextList.add(scAddText);
								}
								
								/*pcPMCProdId = setPrintProdId(printProdIdVal);
								if(pcPMCProdId!=null){
									pcPMatCharcs = new PCPMatCharcs();
									pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									
									if(pcPMCColSpec!=null)
										pcPMatCharcs.setPcPMCColSpec(pcPMCColSpec);
									
									pcPrinMat = new PCPrinMat();								
									pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									pcPrinMatList = new ArrayList();
									pcPrinMatList.add(pcPrinMat);
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								}*/

								pcPrinMatList = new ArrayList();
								if(null!=pcPMCColSpec){	
									pcPMatCharcs = new PCPMatCharcs();
									pcPMCProdId = setPrintProdId(prodIdVal);
									if(pcPMCProdId!=null){
										pcPMatCharcs.setPcPMCProdId(pcPMCProdId);
									}
									if(null!=pcPMatCharcs){
										pcPrinMat = new PCPrinMat();
										pcPrinMat.setPcPMatCharcs(pcPMatCharcs);
									}
									if(null!=pcPrinMat){
										pcPrinMatList.add(pcPrinMat);
									}
								}
									
								if(null!=pcPrinMatList && pcPrinMatList.size()>0)
									pcManfSpec.setPcPrinMatList(pcPrinMatList);
								
								if(pcPrMdSpecs!=null)
									pcManfSpec.setPcPrMdSpecs(pcPrMdSpecs);
								
								if((null!=pcPrinMatList && pcPrinMatList.size()>0)||
										null!=pcPrMdSpecs){
									pressCompt.setPcManfSpec(pcManfSpec);
								}else{
									pcManfSpec=null;
								}
								
								if((docPageCnt != null && !"".equals(docPageCnt.trim()))
										|| null!=pcManfSpec){
									specComponent.setPressCompt(pressCompt);
								}else{
									pressCompt = null;
								}
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=pressCompt ||
									(null!=bookClassificationList && bookClassificationList.size()>0) ||
									(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
									(null!=scProdDespList && scProdDespList.size()>0) ||
									(null!=scFinidSize)){
									break;
								}else{
									specComponent = null;
									break;
								}
								
						case 8:	scAddTextList = new ArrayList();
								while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Component ISBN".equalsIgnoreCase(label) || "Printing Number".equalsIgnoreCase(label)
												|| "Component Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
												|| "Due Date".equalsIgnoreCase(label) || "Sister Company Stock".equalsIgnoreCase(label))
												{
													int valLength = value.length()+ label.length();
													if(valLength > 250){
														ArrayList ValueList = new ArrayList();
														ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
														for (int i=0; i<ValueList.size(); i++){
																scAddText = new SCAddText();
																scAddText.setScAddtext((String)ValueList.get(i));
																scAddTextList.add(scAddText);
														}
													}
													else{
														scAddText = new SCAddText();
														scAddText.setScAddtext(label+":"+value);
														scAddTextList.add(scAddText);
													}
										}
									}
								}
								
								scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								}
								
								if(scAddTextList!=null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);

								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
						case 43: scAddTextList = new ArrayList();
						 while(resultSet6.next()){
							String label = resultSet6.getString("LABEL");
							String value = resultSet6.getString("VALUE");

							if(label != null && !"".equals(label.trim()) &&
									value != null && !"".equals(value.trim())){
								if("Description".equalsIgnoreCase(label)){
									prodDesp1=value;
								}else if("OtherName".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label) 
									|| "Press Type".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
									|| "Total Page Count".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
									|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
									|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label)
									|| "Additional Spec 3".equalsIgnoreCase(label)
									|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label))
									{
									int valLength = value.length()+ label.length();
									if(valLength > 250){
										ArrayList ValueList = new ArrayList();
										ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
										for (int i=0; i<ValueList.size(); i++){
												scAddText = new SCAddText();
												scAddText.setScAddtext((String)ValueList.get(i));
												scAddTextList.add(scAddText);
										}
									}
									else{
										scAddText = new SCAddText();
										scAddText.setScAddtext(label+":"+value);
										scAddTextList.add(scAddText);
									}
								}
							}
						 }
						 
						 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
						 if(null!=scProdDespList && scProdDespList.size()>0){
							specComponent.setScProdDespList(scProdDespList);
						 }
						 
						if(scAddTextList != null && scAddTextList.size()>0)
							specComponent.setScAddTextList(scAddTextList);
						
						if((null!=scAddTextList && scAddTextList.size()>0)||
								(null!=bookClassificationList && bookClassificationList.size()>0) ||
								(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
								(null!=scProdDespList && scProdDespList.size()>0)){
								break;
						}else{
							specComponent = null;
							break;
						}		
						}
					}else
						B2BLogger.info("BookSpecificationDAOImpl.setSpecComponentList()- There is no Press Component");
				}else if("NPRESSCOMP".equalsIgnoreCase(cmpType.trim())){
					if(compName != null && !"".equals(compName.trim())){
						compNameNum = getCompNameNum(compName);
						switch(compNameNum){
						case 21: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Number Per Set".equalsIgnoreCase(label)){
											specComponent.setNumPerProduct(value);
										}else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Silkscreen Color".equalsIgnoreCase(label)){
											silkscreenCol = value;
										}else if("Packaging".equalsIgnoreCase(label)
											|| "Disc Log Number".equalsIgnoreCase(label) || "Storage Option".equalsIgnoreCase(label)
											|| "Press Type".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Unit Rate".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Sleeve Rate".equalsIgnoreCase(label) || "Duplication Rate".equalsIgnoreCase(label)
											|| "Collation Rate".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "New Order?".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label)||"Adoptions Form Devations".equalsIgnoreCase(label)
											|| "Adoptions Form Waranty Clause(1-4)".equalsIgnoreCase(label)	|| "Adoptions Input Complete?".equalsIgnoreCase(label)
											|| "Vendor 1".equalsIgnoreCase(label) || "Vendor 2".equalsIgnoreCase(label)
											|| "Vendor 3".equalsIgnoreCase(label)|| "Show on PO".equalsIgnoreCase(label)
											|| "CD-ROM Type".equalsIgnoreCase(label) || "Disc Format".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								nonPressCompnt = new NonPressComponent();
								
								/*if(nonPrepInpType!=null && !"".equals(nonPrepInpType.trim())){
									cdPrPrepInp = new CDPrPrepInp();
									cdPrPrepInp.setCdPrepInpType(nonPrepInpType.trim());
									cdPrPrepInpList = new ArrayList();
									cdPrPrepInpList.add(cdPrPrepInp);
									cdPrePrep = new CDPrePrep();
									cdPrePrep.setCdPrPrepInpList(cdPrPrepInpList);
									cdPrePrepList = new ArrayList();
									cdPrePrepList.add(cdPrePrep);
								}*/
								/*if((formatType!=null && !"".equals(formatType.trim()))
									|| (noOfCols!=null && !"".equals(noOfCols.trim()))
									|| (cdPrePrepList != null && cdPrePrepList.size()>0)
									|| (cdRType!=null && !"".equals(cdRType.trim()))){*/
									
									//medCD = new MedCD();
									if(noOfCols!=null && !"".equals(noOfCols.trim())){
										medCD = new MedCD();
										medCD.setNumOfCols(noOfCols.trim());
									/*if(formatType!=null && !"".equals(formatType.trim()))
										medCD.setCdFormatType(formatType.trim());
									if(cdPrePrepList != null && cdPrePrepList.size()>0)
										medCD.setCdPrePrepList(cdPrePrepList);
									if(cdRType!=null && !"".equals(cdRType.trim()))
										medCD.setCdRType(cdRType.trim());*/
									if(null!=silkscreenCol && !"".equalsIgnoreCase(silkscreenCol)){
										jbColSpec = new JBColSpec();
										jbColSpec.setColDesp(silkscreenCol);
										cdJewlBox	= new CDJewlBox();
										cdJewlBox.setJbColSpec(jbColSpec);
										cdPackg = new CDPackg();
										cdPackg.setCdJewlBox(cdJewlBox);
										cdPackgList = new ArrayList();
										cdPackgList.add(cdPackg);
										medCD.setCdPackgList(cdPackgList);
									}
										medCDList = new ArrayList();
										medCDList.add(medCD);
										media = new Media();
										media.setMedCDList(medCDList);
										nonPressCompnt.setMedia(media);
										specComponent.setNonPressCompnt(nonPressCompnt);
									}else {
										nonPressCompnt = null;
									}
								//}
								
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);

								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=nonPressCompnt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
					
						case 22: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Length".equalsIgnoreCase(label)){
											scFSLValue = new SCFSLValue();
											scFSLValue.setUom("Inch");
											scFSLValue.setValue(value);
											scFSLength = new SCFSLength();
											scFSLength.setScFSLValue(scFSLValue);
										}else if("Width".equalsIgnoreCase(label)){
											scFSWValue = new SCFSWValue();
											scFSWValue.setUom("Inch");
											scFSWValue.setValue(value);
											scFSWidth = new SCFSWidth();
											scFSWidth.setScFSWValue(scFSWValue);
										}else if("Material".equalsIgnoreCase(label)){
											sleeve = new Sleeve();
											sleeve.setSlvInstType(value);
											sleeve.setSpineLabel("None");
											nonPressCompnt = new NonPressComponent();
											nonPressCompnt.setSleeve(sleeve);
											specComponent.setNonPressCompnt(nonPressCompnt);
										}else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if(("PE Custom Sleeve".equalsIgnoreCase(label) && ("Bind-In Sleeve".equalsIgnoreCase(compName.trim()) 
											|| "2 CD Bind-In Sleeve".equalsIgnoreCase(compName.trim()) || "Bind-In Sleeve w/o warranty".equalsIgnoreCase(compName.trim())
											|| "Bind-In Sleeve w/ Access Code".equalsIgnoreCase(compName.trim()))) 
											|| ("From Inv".equalsIgnoreCase(label) && "Blister Paks".equalsIgnoreCase(compName.trim()))
											|| "Style".equalsIgnoreCase(label)
											|| "OrderNum".equalsIgnoreCase(label) || "ToHold".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Assembly Rate".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
	
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								if(scFSLength!=null || scFSWidth!=null){
									scFinidSize = new SCFinidSize();
									if(scFSLength != null)
										scFinidSize.setScFSLength(scFSLength);
									if(scFSWidth != null)
										scFinidSize.setScFSWidth(scFSWidth);
									
									specComponent.setScFinidSize(scFinidSize);
								}
								
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=nonPressCompnt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0) ||
										null!=scFinidSize){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 23: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Number Per Set".equalsIgnoreCase(label)){
											specComponent.setNumPerProduct(value);
										}else if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Silkscreen Color".equalsIgnoreCase(label)){
											silkscreenCol = value;
										}else if("Packaging".equalsIgnoreCase(label)
											|| "Disc Log Number".equalsIgnoreCase(label) || "Storage Option".equalsIgnoreCase(label)
											|| "Press Type".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Unit Rate".equalsIgnoreCase(label) || "CD-ROM Type".equalsIgnoreCase(label)
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Sleeve Rate".equalsIgnoreCase(label)
											|| "Duplication Rate".equalsIgnoreCase(label) || "Collation Rate".equalsIgnoreCase(label)
											|| "Insertion Rate".equalsIgnoreCase(label) || "Assembly Rate".equalsIgnoreCase(label)
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Disc Format".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label)
											|| "Adoptions Form Devations".equalsIgnoreCase(label)|| "Adoptions Form Waranty Clause(1-4)".equalsIgnoreCase(label)	
											|| "Adoptions Input Complete?".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label)
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								nonPressCompnt = new NonPressComponent();
								
								/*if(nonPrepInpType!=null && !"".equals(nonPrepInpType.trim())){
									dvdPrPrepInp = new DvdPrPrepInp();
									dvdPrPrepInp.setDvdPrepInpType(nonPrepInpType.trim());
									dvdPrPrepInpList = new ArrayList();
									dvdPrPrepInpList.add(dvdPrPrepInp);
									dvdPrePrep = new DvdPrePrep();
									dvdPrePrep.setDvdPrPrepInpList(dvdPrPrepInpList);
									dvdPrePrepList = new ArrayList();
									dvdPrePrepList.add(dvdPrePrep);
								}*/
								/*if((formatType!=null && !"".equals(formatType.trim()))
									|| (noOfCols!=null && !"".equals(noOfCols.trim()))
									|| (dvdPrePrepList != null && dvdPrePrepList.size()>0)){*/
									
									medDVD = new MedDVD();
									if(noOfCols!=null && !"".equals(noOfCols.trim())
											||(null!=silkscreenCol && !"".equalsIgnoreCase(silkscreenCol))){
										if(noOfCols!=null && !"".equals(noOfCols.trim())){
											medDVD.setNumOfCols(noOfCols.trim());
										}
									/*if(formatType!=null && !"".equals(formatType.trim()))
										medDVD.setDvdFormatType(formatType);
									if(dvdPrePrepList != null && dvdPrePrepList.size()>0)
										medDVD.setDvdPrePrepList(dvdPrePrepList);*/
									if(null!=silkscreenCol && !"".equalsIgnoreCase(silkscreenCol)){
										DvdColSpecs dvdColSpecs = new DvdColSpecs();
										dvdColSpecs.setColDesp("Silkscreen color:"+silkscreenCol);
										medDVD.setDvdColSpecs(dvdColSpecs);
										
										/*dvdJewlBox	= new DvdJewlBox();
										dvdPackg = new DvdPackg();
										dvdPackg.setDvdJewlBox(dvdJewlBox);
										dvdPackgList = new ArrayList();
										dvdPackgList.add(dvdPackg);
										medDVD.setDvdPackgList(dvdPackgList);*/
										
										/*dvdjbColSpec = new DvdJBColSpec();
										dvdjbColSpec.setColDesp(silkscreenCol);
										dvdJewlBox	= new DvdJewlBox();
										dvdJewlBox.setDvdjbColSpec(dvdjbColSpec);
										dvdPackg = new DvdPackg();
										dvdPackg.setDvdJewlBox(dvdJewlBox);
										dvdPackgList = new ArrayList();
										dvdPackgList.add(dvdPackg);
										medDVD.setDvdPackgList(dvdPackgList);*/
									}
										dvdJewlBox	= new DvdJewlBox();
										dvdPackg = new DvdPackg();
										dvdPackg.setDvdJewlBox(dvdJewlBox);
										dvdPackgList = new ArrayList();
										dvdPackgList.add(dvdPackg);
										medDVD.setDvdPackgList(dvdPackgList);

										medDVDList = new ArrayList();
										medDVDList.add(medDVD);
										media = new Media();
										media.setMedDVDList(medDVDList);
										nonPressCompnt.setMedia(media);
										specComponent.setNonPressCompnt(nonPressCompnt);
									}else{
										nonPressCompnt= null;
									}
								//}
								
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)|| null!=nonPressCompnt ||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 24: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("EnvelopeColor".equalsIgnoreCase(label) || "EnvelopeStock".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Single Source?".equalsIgnoreCase(label) || "Assembly Rate".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Size".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Adoptions Form Devations".equalsIgnoreCase(label)
											|| "Adoptions Form Special Features".equalsIgnoreCase(label) || "Adoptions Form Waranty Clause(1-4)".equalsIgnoreCase(label)
											|| "Adoptions Input Complete?".equalsIgnoreCase(label) || "Adoptions Form College Meets MSST".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}/*else if("Size".equalsIgnoreCase(label)){
											String[] temp = value.split("x | X");
											if(temp[0] != null || temp[1] != null)
												scFinidSize = setFinidSize(temp[0], temp[1]);
										}*/
									}
								 }
								/*if(scFinidSize!=null)
									 specComponent.setScFinidSize(scFinidSize);*/
		
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}							
							
						case 25: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Slipcase Style".equalsIgnoreCase(label) || "Slipcase Wrap Descr".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Assembly Rate".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Size".equalsIgnoreCase(label)
											|| "Slipcase Stock".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}/*else if("Size".equalsIgnoreCase(label)){
											String[] temp = value.split("x | X");
											if(temp[0] != null || temp[1] != null)
												scFinidSize = setFinidSize(temp[0], temp[1]);
										}*/
									}
								 }
								 /*if(scFinidSize!=null)
									 specComponent.setScFinidSize(scFinidSize);*/

								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 26: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Assembly Rate".equalsIgnoreCase(label) || "Insertion Rate".equalsIgnoreCase(label)
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}						
								
								
						case 27: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Zip Lock Bag Length".equalsIgnoreCase(label) || "Zip Lock Bag Width".equalsIgnoreCase(label)
											||"Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Assembly Rate".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Zip-Lock Bags".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 28: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Non-Book Product Desc".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Color Descr".equalsIgnoreCase(label)|| "Freight".equalsIgnoreCase(label)
											|| "Dimensions".equalsIgnoreCase(label) || "Num. Of Pieces".equalsIgnoreCase(label)
											|| "Detail Specs".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Unit Rate".equalsIgnoreCase(label) || "Packaging".equalsIgnoreCase(label)
											|| "Rate Unit Descr".equalsIgnoreCase(label) 
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 29: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("# of CDs held".equalsIgnoreCase(label) || "Material".equalsIgnoreCase(label) 
											|| "Color Descr".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Packaging".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Assembly Rate".equalsIgnoreCase(label) 
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 30: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Cards Per Set".equalsIgnoreCase(label) || "Card Colors".equalsIgnoreCase(label)
											|| "Card Colors Sides".equalsIgnoreCase(label) || "Card Substrate".equalsIgnoreCase(label)
											|| "Card Finishing".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label)
											|| "Press Type".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Unit Rate".equalsIgnoreCase(label) || "Packaging".equalsIgnoreCase(label)
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Collation Rate".equalsIgnoreCase(label)
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											||"Insertion Req".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Size".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}/*else if("Size".equalsIgnoreCase(label)){
											String[] temp = value.split("x | X");
											if(temp[0] != null || temp[1] != null)
												scFinidSize = setFinidSize(temp[0], temp[1]);
										}*/
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								 /*if(scFinidSize!=null)
									 specComponent.setScFinidSize(scFinidSize);*/
		
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 31: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("ZNBN#".equalsIgnoreCase(label) || "Component Qty".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
							
						case 32: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("OtherName".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label) 
											|| "Press Type".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Total Page Count".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 33: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Copy Supplied".equalsIgnoreCase(label)|| "Show on PO".equalsIgnoreCase(label)
											|| "Press Type".equalsIgnoreCase(label) || "Overage Print Qty".equalsIgnoreCase(label)
											|| "Unit Rate".equalsIgnoreCase(label) || "Packaging".equalsIgnoreCase(label)
											|| "Rate Unit Descr".equalsIgnoreCase(label) || "Assembly Rate".equalsIgnoreCase(label) 
											|| "Single Source?".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											||"Insertion Req".equalsIgnoreCase(label) || "Insertion Rate".equalsIgnoreCase(label)
											|| "Vendor 1".equalsIgnoreCase(label) || "Vendor 2".equalsIgnoreCase(label)
											|| "Vendor 3".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}

						case 34: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Overage Print Qty".equalsIgnoreCase(label)|| "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Assembly Rate".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
											|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Assembly Instruct 3".equalsIgnoreCase(label) 
											|| "Assembly Instruct 4".equalsIgnoreCase(label) ||  "Assembly Instruct 5".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label)
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label)	|| "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 35: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Size".equalsIgnoreCase(label) || "Label Colors".equalsIgnoreCase(label)
											|| "Label Colors Descr".equalsIgnoreCase(label) || "Label Print Method".equalsIgnoreCase(label)
											|| "Label Stock Wt".equalsIgnoreCase(label) || "Label Stock".equalsIgnoreCase(label)
											|| "Label Stock Finish".equalsIgnoreCase(label)|| "Label Corners".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label)|| "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label) 
											|| "Affixing Rate".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
											|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
											|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
											|| "Size".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}

						case 36: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Copy Supplied".equalsIgnoreCase(label)|| "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label) 
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
						case 37: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Material Descr".equalsIgnoreCase(label)|| "Size Width".equalsIgnoreCase(label)
											|| "Size Length".equalsIgnoreCase(label)|| "Size Height".equalsIgnoreCase(label)
											|| "Hole Size".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label) 
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 38: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Size".equalsIgnoreCase(label) || "J Card Substrate".equalsIgnoreCase(label)
											|| "J Card Finish".equalsIgnoreCase(label) || "J Card Color Side1".equalsIgnoreCase(label)
											|| "J Card Color Side2".equalsIgnoreCase(label) || "J Card Fold Descr".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label) 
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}

						case 39: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Size".equalsIgnoreCase(label) || "Binder Config".equalsIgnoreCase(label)
											|| "Binder Capacity".equalsIgnoreCase(label) || "Inside Front Cover".equalsIgnoreCase(label)
											|| "Inside Back Cover".equalsIgnoreCase(label) || "Back Cover".equalsIgnoreCase(label)
											|| "Spine Cover".equalsIgnoreCase(label) || "Front Cover".equalsIgnoreCase(label)
											|| "Material".equalsIgnoreCase(label) || "Cover Color".equalsIgnoreCase(label)
											|| "Binder Rivets".equalsIgnoreCase(label) || "BinderInsertDescr".equalsIgnoreCase(label)
											|| "BinderInsertSize".equalsIgnoreCase(label) || "BinderInsertColors".equalsIgnoreCase(label)
											|| "BinderInsertStock".equalsIgnoreCase(label) || "Sheet Lifter Descr".equalsIgnoreCase(label)
											|| "Sheet Lifter Stock".equalsIgnoreCase(label) || "Sheet Lifter Size".equalsIgnoreCase(label)
											|| "Shift Lifter Rate".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Assembly Rate".equalsIgnoreCase(label) || "Adoptions Form Devations".equalsIgnoreCase(label)
											|| "Adoptions Form Special Features".equalsIgnoreCase(label) || "Adoptions Form Waranty Clause(1-4)".equalsIgnoreCase(label)
											|| "Adoptions Input Complete?".equalsIgnoreCase(label) || "Adoptions Form College Meets MSST".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 40: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}else if("Box Style".equalsIgnoreCase(label) || "Box Colors".equalsIgnoreCase(label)
											|| "Box Colors Sides".equalsIgnoreCase(label) || "Box Size Descr".equalsIgnoreCase(label)
											|| "Box Material".equalsIgnoreCase(label) 
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 41: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}if("Tab Per Set".equalsIgnoreCase(label)){
											specComponent.setNumPerProduct(value);
										}else if("Size".equalsIgnoreCase(label) || "Tab Stock".equalsIgnoreCase(label)
											|| "Tab Num Of Banks".equalsIgnoreCase(label) || "Tab Prints".equalsIgnoreCase(label)
											|| "Tab Finish".equalsIgnoreCase(label)||"Tab Body Finish".equalsIgnoreCase(label)
											|| "Tab Body Printing".equalsIgnoreCase(label)||"Tab Body Reinfrc".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label)
											|| "Insertion Req".equalsIgnoreCase(label) || "Number of Tabs".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}
								
						case 42: scAddTextList = new ArrayList();
								 while(resultSet6.next()){
									String label = resultSet6.getString("LABEL");
									String value = resultSet6.getString("VALUE");
		
									if(label != null && !"".equals(label.trim()) &&
											value != null && !"".equals(value.trim())){
										if("Description".equalsIgnoreCase(label)){
											prodDesp1=value;
										}if("Transpar Per Set".equalsIgnoreCase(label)){
											specComponent.setNumPerProduct(value);
										}else if("Size".equalsIgnoreCase(label) || "Transpar Colors".equalsIgnoreCase(label)
											|| "Transpar Stock".equalsIgnoreCase(label) || "Transpar Finish".equalsIgnoreCase(label)
											|| "Transpar Interleaves".equalsIgnoreCase(label)||"Transpar Interleave".equalsIgnoreCase(label)
											|| "Copy Supplied".equalsIgnoreCase(label) || "Press Type".equalsIgnoreCase(label)
											|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label) 
											|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
											|| "Additional Spec 1".equalsIgnoreCase(label)|| "Additional Spec 2".equalsIgnoreCase(label) 
											|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label) 
											|| "Assembly Instruct 2".equalsIgnoreCase(label)|| "Vendor 1".equalsIgnoreCase(label) 
											|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
											|| "Show on PO".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label)
											|| "Insertion Req".equalsIgnoreCase(label) || "Reprint Plates".equalsIgnoreCase(label)
											|| "Adoptions Form Devations".equalsIgnoreCase(label)|| "Adoptions Form Type".equalsIgnoreCase(label) 
											|| "Adoptions Form Waranty Clause(1-4)".equalsIgnoreCase(label)|| "Adoptions Input Complete?".equalsIgnoreCase(label))
											{
											int valLength = value.length()+ label.length();
											if(valLength > 250){
												ArrayList ValueList = new ArrayList();
												ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
												for (int i=0; i<ValueList.size(); i++){
														scAddText = new SCAddText();
														scAddText.setScAddtext((String)ValueList.get(i));
														scAddTextList.add(scAddText);
												}
											}
											else{
												scAddText = new SCAddText();
												scAddText.setScAddtext(label+":"+value);
												scAddTextList.add(scAddText);
											}
										}
									}
								 }
								 
								 scProdDespList = setPressProdDespList(prodDesp, prodDesp1);
								 if(null!=scProdDespList && scProdDespList.size()>0){
									specComponent.setScProdDespList(scProdDespList);
								 }
								 
								if(scAddTextList != null && scAddTextList.size()>0)
									specComponent.setScAddTextList(scAddTextList);
								
								if((null!=scAddTextList && scAddTextList.size()>0)||
										(null!=bookClassificationList && bookClassificationList.size()>0) ||
										(prodIdVal != null && !"".equals(prodIdVal.trim())) ||
										(null!=scProdDespList && scProdDespList.size()>0)){
										break;
								}else{
									specComponent = null;
									break;
								}								
						}
					}
				}
			}else
				B2BLogger.info("BookSpecificationDAOImpl.setSpecComponentList()- There is no Press and NonPress Component");
			
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecComponent() method return");
		}catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			specComponent = null;
		}finally{
			DBUtils.close(resultSet6);
			DBUtils.close(prepStmt1);

			sqlQuery		= null;
			qryParams		= null;
			scProdId			= null;
			scProdDespList		= null;
			scClassifn			= null;
			scFinidSize			= null;
			scFSLength			= null;
			scFSLValue			= null;
			scFSWidth			= null;
			scFSWValue			= null;
			pressCompt			= null;
			pcPressPrep			= null;
			pcManfSpec			= null;
			pcColSpecList		= null;
			pcInkCharcs			= null;
			pcFinSpecs			= null;
			pcFSCoatingList 	= null;
			pcFSCoating			= null;
			pcPrMdSpecs			= null;
			pcPrinMatList		= null;		
			pcPrinMat			= null;
			pcPMatCharcs 		= null;
			pcPMCProdId 		= null;
			pcPMCProdDespList	= null;
			pcPMCBasWgt	 		= null;	
			pcPMCColSpec		= null;
			pcPMCPPI 			= null;
			pcPMCWidth 			= null;
			pcPMCLength 		= null;
			scAddTextList		= null;
			scAddText			= null;
			nonPressCompnt		= null;
			media				= null;
			medCDList			= null;
			medCD				= null;
			cdPrePrepList		= null;
			cdPrePrep			= null;
			cdPrPrepInpList 	= null;
			cdPrPrepInp			= null;
			medDVDList			= null;
			medDVD				= null;
			dvdPrePrepList		= null;
			dvdPrePrep			= null;
			dvdPrPrepInpList 	= null;
			dvdPrPrepInp		= null;
			sleeve				= null;
			colPerSide			= null;
		}
		return specComponent;
	}

	private ResultSet getTrimSizeValues(Connection dbCon, String trimSizeDescr) {
		PreparedStatement prepStmt15		= null;
		ResultSet resultSet15				= null;
		String qryParams					= null;
		String sqlQuery						= null;
		try {
			sqlQuery = qry_sel_trimlen_trimwid;
			prepStmt15 = dbCon.prepareStatement(sqlQuery);
			prepStmt15.clearParameters();
			prepStmt15.setString(IPixB2BConstants.ONE, trimSizeDescr);
			qryParams = trimSizeDescr;
			resultSet15 = prepStmt15.executeQuery();
		} catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			resultSet15 = null;
		} finally{
			DBUtils.close(prepStmt15);
			//DBUtils.close(resultSet15);			
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return resultSet15;
	}

	private ResultSet getFinshValues(Connection dbCon, String value) {
		PreparedStatement prepStmt13		= null;
		ResultSet resultSet13				= null;
		String qryParams					= null;
		String sqlQuery						= null;
		String xbitVal						= null;
		try {
			sqlQuery = qry_sel_finish_value;
			prepStmt13 = dbCon.prepareStatement(sqlQuery);
			prepStmt13.clearParameters();
			prepStmt13.setString(IPixB2BConstants.ONE, "COVER");
			prepStmt13.setString(IPixB2BConstants.TWO, "Finish");
			prepStmt13.setString(IPixB2BConstants.THREE, value);
			prepStmt13.setString(IPixB2BConstants.FOUR, IPixB2BConstants.flag_Y);
			prepStmt13.setString(IPixB2BConstants.FIVE, IPixB2BConstants.flag_Y);
			qryParams = "COVER, Finish"+", "+value+", "+IPixB2BConstants.flag_Y+", "+IPixB2BConstants.flag_Y;
			resultSet13 = prepStmt13.executeQuery();

		} catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			resultSet13 = null;
		} finally{
			//DBUtils.close(prepStmt13);
			//DBUtils.close(resultSet11);			
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return resultSet13;
	}

	/**
	 * This method maps each component name against a numeric value.
	 * @param compName
	 * @return integer
	 */
	private int getCompNameNum(String compName){
		int compTypeNum = -1;
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.getCompNameNum() method called");
			
			if("TEXT".equalsIgnoreCase(compName.trim()))
				compTypeNum = 1;
			else if("Paper Cover".equalsIgnoreCase(compName.trim())|| 
					"Case Cover".equalsIgnoreCase(compName.trim()) ||
					"Soft Front/Hard Back".equalsIgnoreCase(compName.trim()))
				compTypeNum = 2;
			else if("Insert".equalsIgnoreCase(compName.trim()))// ||"Insert 2".equalsIgnoreCase(compName.trim())
				compTypeNum = 3;
			else if("End Papers".equalsIgnoreCase(compName.trim()))
				compTypeNum = 4;
			else if("Jacket".equalsIgnoreCase(compName.trim()))
				compTypeNum = 5;
			else if("Cards".equalsIgnoreCase(compName.trim()))
				compTypeNum = 6;
			else if("Documentation".equalsIgnoreCase(compName.trim()) || 
					"Brochure".equalsIgnoreCase(compName.trim()))
				compTypeNum = 7;
			else if("Co-Owned Stock".equalsIgnoreCase(compName.trim()))
				compTypeNum = 8;
			else if("CD-ROM".equalsIgnoreCase(compName.trim())
				|| "CDR".equalsIgnoreCase(compName.trim()))
				compTypeNum = 21;
			else if("Bind-In Sleeve".equalsIgnoreCase(compName.trim()) 
				|| "2 CD Bind-In Sleeve".equalsIgnoreCase(compName.trim())
				|| "Blister Paks".equalsIgnoreCase(compName.trim())
				||"Bind-In Sleeve w/o warranty".equalsIgnoreCase(compName.trim())
				|| "Bind-In Sleeve w/ Access Code".equalsIgnoreCase(compName.trim())
				//Added by Aishwarya
				|| "Sleeves".equalsIgnoreCase(compName.trim()))
				compTypeNum = 22;
			else if("DVD 5".equalsIgnoreCase(compName.trim()) 
				|| "DVD 9".equalsIgnoreCase(compName.trim())
				|| "DVD R".equalsIgnoreCase(compName.trim()))
				compTypeNum = 23;
			else if("Envelopes".equalsIgnoreCase(compName.trim()))
				compTypeNum = 24;		
			else if("Slip Case".equalsIgnoreCase(compName.trim()))
				compTypeNum = 25;
			else if("Cassette Holder".equalsIgnoreCase(compName.trim()))
				compTypeNum = 26;
			else if("Zip-Lock Bags".equalsIgnoreCase(compName.trim()))
				compTypeNum = 27;
			else if("Non-Book".equalsIgnoreCase(compName.trim()))
				compTypeNum = 28;
			else if("Jewel Case".equalsIgnoreCase(compName.trim())
					|| "Amaray Case".equalsIgnoreCase(compName.trim()))
				compTypeNum = 29;
			else if("Access Code".equalsIgnoreCase(compName.trim()))
				compTypeNum = 30;
			else if("Co Owned Signature".equalsIgnoreCase(compName.trim()))
				compTypeNum = 31;
			else if("Other".equalsIgnoreCase(compName.trim()))
				compTypeNum = 32;
			else if("Shrinkwrapping".equalsIgnoreCase(compName.trim()))
				compTypeNum = 33;			
			else if("Sub-Assembly".equalsIgnoreCase(compName.trim()))
				compTypeNum = 34;
			else if("Labels".equalsIgnoreCase(compName.trim()))
				compTypeNum = 35;			
			else if("Back Cover Panel w/ Shrinkwrap".equalsIgnoreCase(compName.trim()))
				compTypeNum = 36;
			else if("Liner / Corrugation".equalsIgnoreCase(compName.trim()))
				compTypeNum = 37;
			else if("J Card / U Card".equalsIgnoreCase(compName.trim()))
				compTypeNum = 38;
			else if("Binders".equalsIgnoreCase(compName.trim()))
				compTypeNum = 39;
			else if("Carton/Box".equalsIgnoreCase(compName.trim()))
				compTypeNum = 40;
			else if("Tabs".equalsIgnoreCase(compName.trim()))
				compTypeNum = 41;
			else if("Transparency".equalsIgnoreCase(compName.trim()))
				compTypeNum = 42;
			//Added by Aishwarya
			else if("Finished Goods".equalsIgnoreCase(compName.trim()))
				compTypeNum = 43;
			
			B2BLogger.debug("BookSpecificationDAOImpl.getCompNameNum() method return");
		}catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		}
		return compTypeNum;
	}

	/**
	 * @param temp1
	 * @param temp2
	 * @return SCFinidSize
	 */
	private SCFinidSize setFinidSize(Connection dbCon, String trimSizeDescr){
		PreparedStatement prepStmt15	= null;
		ResultSet resultSet15			= null;
		SCFinidSize scFinidSize			= null;
		SCFSLValue scFSLValue 			= null;
		SCFSLength scFSLength 			= null;
		SCFSWValue scFSWValue 			= null;
		SCFSWidth scFSWidth   			= null;
		String qryParams				= null;
		String sqlQuery					= null;
		String trimWidth				= null;
		String trimLength				= null;
		try {
			
			trimSizeDescr="'"+trimSizeDescr+"'";
			qry_sel_trimlen_trimwid2 = qry_sel_trimlen_trimwid2 + trimSizeDescr;
			//sqlQuery = qry_sel_trimlen_trimwid2 ;
			sqlQuery="SELECT TRIM_WIDTH, TRIM_LENGTH FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_TRIM_SIZE_INFO_VW"
			+" WHERE TRIMSIZE_DESCRIPTION = "+trimSizeDescr;
			Statement stmt = dbCon.createStatement();
			resultSet15 = stmt.executeQuery(sqlQuery);
			while(resultSet15.next()){
				trimWidth = resultSet15.getString("TRIM_WIDTH");
				trimLength = resultSet15.getString("TRIM_LENGTH");
			}
			DBUtils.close(stmt);
			
			/*sqlQuery = qry_sel_trimlen_trimwid;
			prepStmt15 = dbCon.prepareStatement(sqlQuery);
			prepStmt15.clearParameters();
			prepStmt15.setString(IPixB2BConstants.ONE, trimSizeDescr);
			qryParams = trimSizeDescr;
			resultSet15 = prepStmt15.executeQuery();
			while(resultSet15.next()){
				trimWidth = resultSet15.getString("TRIM_WIDTH");
				trimLength = resultSet15.getString("TRIM_LENGTH");
			}*/
			if(null!=trimWidth && !"".equals(trimWidth) 
					&& null!=trimLength && !"".equals(trimLength)){
				scFinidSize = new SCFinidSize();
				
				scFSLValue = new SCFSLValue();
				scFSLValue.setUom("Inch");
				scFSLValue.setValue(trimLength);
				scFSLength = new SCFSLength();
				scFSLength.setScFSLValue(scFSLValue);
				scFinidSize.setScFSLength(scFSLength);

				scFSWValue = new SCFSWValue();
				scFSWValue.setUom("Inch");
				scFSWValue.setValue(trimWidth);
				scFSWidth = new SCFSWidth();
				scFSWidth.setScFSWValue(scFSWValue);
				scFinidSize.setScFSWidth(scFSWidth);				
			}
		} catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			scFinidSize = null;
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			scFinidSize = null;
		}finally{
			DBUtils.close(prepStmt15);
			DBUtils.close(resultSet15);			
		
			sqlQuery	= null;
			qryParams	= null;
			scFSLValue 	= null;
			scFSLength 	= null;
			scFSWValue 	= null;
			scFSWidth   = null;
			trimLength	= null;
			trimWidth	= null;
		}
		return scFinidSize;
	}

	private Float formatNumber(String temp1) {
		// TODO Auto-generated method stub
		String[] arrTotalCharaters  = temp1.split("");
		String formattedNumber;
		final String INITIAL = "INITIAL";
		String subNumber1 = INITIAL;
		String subNumber2=INITIAL;
		String subNumber3;
		for(String temp : arrTotalCharaters)
		{
			if(INITIAL.equals(temp))
			subNumber1 = temp;
			else
			{
				try {
						
						Integer.parseInt(temp);
						
						if(INITIAL.equals(subNumber2)){
						subNumber2 = temp;
						}
						else
						{
							
						}
												
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					//System.out.println("Ignoring: " + temp);
				}
			}
			
			
		}
		return null;
	}

	/**
	 * @param prodIdVal
	 * @return SCProdId
	 */
	private SCProdId setPressProdId(String prodIdVal){
		SCProdId scProdId = null;
		try {
			if(prodIdVal != null && !"".equals(prodIdVal.trim())){
				scProdId = new SCProdId();
				scProdId.setPiType("PartNumber");
				scProdId.setAgency("Buyer");
				scProdId.setPiVal(prodIdVal.trim());
				}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			scProdId = null;
		}
		return scProdId;
	}
	
	/**
	 * @param prodDesp
	 * @return ArrayList
	 */
	private ArrayList setPressProdDespList(String prodDesp, String prodDesp1){
		ArrayList scProdDespList = null;
		SCProdDesp scProdDesp 	 = null;
		try {
			scProdDespList = new ArrayList();
			if(prodDesp != null && !"".equals(prodDesp.trim())){
				scProdDesp = new SCProdDesp();
				scProdDesp.setLang("eng");
				scProdDesp.setPdVal(prodDesp.trim());
				//scProdDespList = new ArrayList();
				scProdDespList.add(scProdDesp);
			}
			if(prodDesp1 != null && !"".equals(prodDesp1.trim())){
				scProdDesp = new SCProdDesp();
				scProdDesp.setLang("eng");
				scProdDesp.setPdVal(prodDesp1.trim());
				scProdDespList.add(scProdDesp);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			scProdDespList = null;
		}finally{
			scProdDesp 	 = null;
		}
		return scProdDespList;
	}
	
	/**
	 * @param classCode
	 * @param agency
	 * @return SCClassifn
	 */
	private SCClassifn setClassifn(String classCode, String agency){
		SCClassifn scClassifn = null;
		SCClassCode scClassCode= null;
		try {
			if(classCode != null && !"".equals(classCode.trim())
					&& agency != null && !"".equals(agency.trim())){
				scClassCode= new SCClassCode();
				scClassCode.setAgency(agency.trim());
				scClassCode.setScCCVal(classCode.trim());
				scClassifn = new SCClassifn();
				scClassifn.setScClassCode(scClassCode);	
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			scClassifn = null;
		}finally{
			scClassCode= null;
		}
		return scClassifn;
	}
	
	/**
	 * @param pressPrepInpType
	 * @return PCPressPrep
	 */
	private PCPressPrep setPressPrepInp(String pressPrepInpType){
		PCPressPrep pcPressPrep = null;
		PRPrepInp prPrepInp 	= null;
		ArrayList prPrepInpList = null;
		
		try {
			if(pressPrepInpType != null && !"".equals(pressPrepInpType.trim())){
				prPrepInp = new PRPrepInp();
				prPrepInp.setPrPrepInpType(pressPrepInpType.trim());
				prPrepInpList = new ArrayList();
				prPrepInpList.add(prPrepInp);
				pcPressPrep = new PCPressPrep();
				pcPressPrep.setPrPrepInpList(prPrepInpList);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPressPrep = null;
		}finally{
			prPrepInp 		= null;
			prPrepInpList 	= null;
		}
		return pcPressPrep;
	}
	
	/**
	 * @param colDesp
	 * @return ArrayList
	 */
	private ArrayList setColSpecList(String colDesp, String colorDescr, String colorKey){
		ArrayList pcColSpecList = null;
		ArrayList colDespList	= null;
		ColDesp colDes			= null;
		PCColSpec pcColSpec 	= null;
		PCCSColCode pcCSColCode = null;
		B2BHelper b2bHelper		= null;
		try {
			b2bHelper = new B2BHelper();
			colDespList = new ArrayList();
			if(colDesp != null && !"".equals(colDesp.trim())){
				
				int valLength = colDesp.length();
				if(valLength > 71){
					ArrayList ValueList1 = new ArrayList();
					ValueList1 = b2bHelper.breakStringInSubstrgs(colDesp);
					for (int i=0; i<ValueList1.size(); i++){
							colDes = new ColDesp();
							colDes.setColDesp((String)ValueList1.get(i));
							colDespList.add(colDes);
					}
				}else {
					colDes = new ColDesp();
					colDes.setColDesp(colDesp);
					colDespList.add(colDes);
				}
			}
			if(colorDescr != null && !"".equals(colorDescr.trim())){
				int valLength = colorDescr.length();
				if(valLength > 71){
					ArrayList ValueList2 = new ArrayList();
					ValueList2 = b2bHelper.breakStringInSubstrgs(colorDescr.trim());
					for (int i=0; i<ValueList2.size(); i++){
							colDes = new ColDesp();
							colDes.setColDesp((String)ValueList2.get(i));
							colDespList.add(colDes);
					}
				}else {
					colDes = new ColDesp();
					colDes.setColDesp(colorDescr);
					colDespList.add(colDes);
				}
			}
			if(null!=colDespList && colDespList.size()>0){
				pcColSpec = new PCColSpec();
				pcColSpec.setColDespList(colDespList);
				if(colorKey != null && !"".equals(colorKey.trim())){
					pcCSColCode = new PCCSColCode();
					pcCSColCode.setCcVal(colorKey);
					pcColSpec.setPcCSColCode(pcCSColCode);
				}
				pcColSpecList = new ArrayList();
				pcColSpecList.add(pcColSpec);
			}
			/*if(colDesp != null && !"".equals(colDesp.trim())){
				pcColSpec = new PCColSpec();
				pcColSpec.setColDesp(colDesp.trim());
				if(colorKey != null && !"".equals(colorKey.trim())){
					pcCSColCode = new PCCSColCode();
					pcCSColCode.setCcVal(colorKey);
					pcColSpec.setPcCSColCode(pcCSColCode);
				}
				pcColSpecList = new ArrayList();
				pcColSpecList.add(pcColSpec);
			}*/
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcColSpecList = null;
		}finally{
			pcColSpec = null;
		}
		return pcColSpecList;
	}
	
	/**
	 * @param colDesp
	 * @param colPerSide
	 * @return
	 */
	private ArrayList setCovColSpecList(String colDesp, String prodDesp1, String colPerSide){
		ArrayList pcColSpecList = null;
		ArrayList colDespList	= null;
		ColDesp colDes			= null;
		PCColSpec pcColSpec 	= null;
		B2BHelper b2bHelper		= null;
		try {
			b2bHelper = new B2BHelper();
			colDespList = new ArrayList();
			if(colDesp != null && !"".equals(colDesp.trim())){
				int valLength = colDesp.length();
				if(valLength > 71){
					ArrayList ValueList1 = new ArrayList();
					ValueList1 = b2bHelper.breakStringInSubstrgs(colDesp.trim());
					for (int i=0; i<ValueList1.size(); i++){
							colDes = new ColDesp();
							colDes.setColDesp((String)ValueList1.get(i));
							colDespList.add(colDes);
					}
				}else {
					colDes = new ColDesp();
					colDes.setColDesp(colDesp);
					colDespList.add(colDes);
				}
			}
			if(prodDesp1 != null && !"".equals(prodDesp1.trim())){
				
				int valLength = prodDesp1.length();
				if(valLength > 71){
					ArrayList ValueList1 = new ArrayList();
					ValueList1 = b2bHelper.breakStringInSubstrgs(prodDesp1.trim());
					for (int i=0; i<ValueList1.size(); i++){
							colDes = new ColDesp();
							colDes.setColDesp((String)ValueList1.get(i));
							colDespList.add(colDes);
					}
				}else {
					colDes = new ColDesp();
					colDes.setColDesp(prodDesp1);
					colDespList.add(colDes);
				}
			}
			if(colPerSide != null && !"".equals(colPerSide.trim())){
				colDes = new ColDesp();
				colDes.setColDesp(colPerSide);
				colDespList.add(colDes);
			}
			if(null!=colDespList && colDespList.size()>0){
				pcColSpec = new PCColSpec();
				pcColSpec.setColDespList(colDespList);
				pcColSpecList = new ArrayList();
				pcColSpecList.add(pcColSpec);
			}
			
			/*pcColSpecList = new ArrayList();
			if(colDesp != null && !"".equals(colDesp.trim())){
				pcColSpec = new PCColSpec();
				pcColSpec.setColDesp(colDesp.trim());
				pcColSpecList.add(pcColSpec);
			}
			if(colPerSide != null && !"".equals(colPerSide.trim())){
				pcColSpec = new PCColSpec();
				pcColSpec.setColDesp(colPerSide.trim());
				pcColSpecList.add(pcColSpec);
			}*/
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcColSpecList = null;
		}finally{
			pcColSpec = null;
		}
		return pcColSpecList;
	}
	
	/**
	 * @param printProdIdVal
	 * @return PCPMCProdId
	 */
	private PCPMCProdId setPrintProdId(String printProdIdVal){
		PCPMCProdId pcPMCProdId = null;
		try {
			if(printProdIdVal != null && !"".equals(printProdIdVal.trim())){
				pcPMCProdId = new PCPMCProdId();
				pcPMCProdId.setAgency("Buyer");
				//pcPMCProdId.setPiType("PartNumber");
				pcPMCProdId.setPiType("GradeName");
				pcPMCProdId.setPiVal(printProdIdVal.trim());
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCProdId = null;
		}
		return pcPMCProdId;
	}
	
	/**
	 * @param printProdDesp
	 * @return ArrayList
	 */
	//private ArrayList setPrintProdDespList(String printProdDesp){
	private ArrayList setPrintProdDespList(String printProdDesp, String endpapDesc){
		ArrayList pcPMCProdDespList = null;
		PCPMCProdDesp pcPMCProdDesp = null;
		try {
			pcPMCProdDespList = new ArrayList();
			if(printProdDesp != null && !"".equals(printProdDesp.trim())){
				pcPMCProdDesp = new PCPMCProdDesp();
				pcPMCProdDesp.setLang("eng");
				pcPMCProdDesp.setPdVal(printProdDesp.trim());
				//pcPMCProdDespList = new ArrayList();
				pcPMCProdDespList.add(pcPMCProdDesp);
			}
			if(endpapDesc != null && !"".equals(endpapDesc.trim())){
				pcPMCProdDesp = new PCPMCProdDesp();
				pcPMCProdDesp.setLang("eng");
				pcPMCProdDesp.setPdVal(endpapDesc.trim());
				pcPMCProdDespList.add(pcPMCProdDesp);
			}
		}catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCProdDespList = null;
		}finally{
			pcPMCProdDesp = null;
		}
		return pcPMCProdDespList;
	}	
	
	/**
	 * @param detVal
	 * @param uomDetValue
	 * @return PCPMCBasWgt
	 */
	private PCPMCBasWgt setPrintBasWgt(String detVal, String uomDetValue){
		PCPMCBasWgt pcPMCBasWgt = null;
		try {
			if(detVal != null && !"".equals(detVal.trim()) 
					&& uomDetValue != null && !"".equals(uomDetValue.trim())){
				pcPMCBasWgt = new PCPMCBasWgt();
				pcPMCBasWgt.setDetValue(detVal.trim());
				pcPMCBasWgt.setUomDetValue(uomDetValue.trim());
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCBasWgt = null;
		}
		return pcPMCBasWgt;
	}
	
	/**
	 * @param printColDesp
	 * @return PCPMCColSpec
	 */
	private PCPMCColSpec setPrintColSpec(String printColDesp){
		PCPMCColSpec pcPMCColSpec = null;
		try {
			if(printColDesp != null && !"".equals(printColDesp.trim())){
				pcPMCColSpec = new PCPMCColSpec();
				pcPMCColSpec.setColDesp(printColDesp.trim());
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCColSpec = null;
		}
		return pcPMCColSpec;
	}
	
	/**
	 * @param ppiVal
	 * @param uomPPI
	 * @return PCPMCPPI
	 */
	private PCPMCPPI setPrintPPI(String ppiVal, String uomPPI){
		PCPMCPPI pcPMCPPI = null;
		PCPPIValue pcPPIValue = null;
		try {
			if(ppiVal != null && !"".equals(ppiVal.trim()) 
					&& uomPPI != null && !"".equals(uomPPI.trim())){
				pcPPIValue = new PCPPIValue();
				pcPPIValue.setUom(uomPPI.trim());
				pcPPIValue.setValue(ppiVal.trim());
				pcPMCPPI = new PCPMCPPI();
				pcPMCPPI.setPcPPIValue(pcPPIValue);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCPPI = null;
		}finally{
			pcPPIValue = null;
		}
		return pcPMCPPI;
	}
	
	/**
	 * @param widthVal
	 * @param uomWidth
	 * @return PCPMCWidth
	 */
	private PCPMCWidth setPrintWidth(String widthVal, String uomWidth){
		PCPMCWidth pcPMCWidth = null;
		PCPMCWValue pcPMCWValue = null;
		try {
			if(widthVal != null && !"".equals(widthVal.trim()) && 
					uomWidth != null && !"".equals(uomWidth.trim())){
				pcPMCWValue = new PCPMCWValue();
				pcPMCWValue.setUom(uomWidth.trim());
				pcPMCWValue.setValue(widthVal.trim());
				pcPMCWidth = new PCPMCWidth();
				pcPMCWidth.setPcPMCWValue(pcPMCWValue);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCWidth = null;
		}finally{
			pcPMCWValue = null;
		}
		return pcPMCWidth;
	}
	
	/**
	 * @param lengthVal
	 * @param uomLength
	 * @return PCPMCLength
	 */
	private PCPMCLength setPrintLength(String lengthVal, String uomLength){
		PCPMCLength pcPMCLength = null;
		PCPMCLValue pcPMCLValue = null;
		try {
			if(lengthVal != null && !"".equals(lengthVal.trim()) && 
					uomLength != null && !"".equals(uomLength.trim())){
				pcPMCLValue = new PCPMCLValue();
				pcPMCLValue.setUom(uomLength.trim());
				pcPMCLValue.setValue(lengthVal.trim());
				pcPMCLength = new PCPMCLength();
				pcPMCLength.setPcPMCLValue(pcPMCLValue);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			pcPMCLength = null;
		}finally{
			pcPMCLValue = null;
		}
		return pcPMCLength;
	}
	
	/**
	 * This method set the SpecAssembly details for a single XML to generate.
	 * @param resultSet2
	 * @return SpecAssembly
	 */
	private SpecAssembly setSpecAssembly(ResultSet resultSet2){
		SpecAssembly specAssembly				= null;
		ProdIdSpecAssembly prodIdSpecAssembly 	= null;
		ArrayList pdSpecAssemblyList		  	= null;
		PDSpecAssembly pdSpecAssembly		  	= null;
		BillOfMaterials	bfMaterials		  	  	= null;
		LngSpecAssembly lngSpecAssembly		  	= null;
		LngValue lValue	 	  					= null;
		WidSpecAssembly widSpecAssembly		  	= null;
		WidValue wValue		  					= null;
		ArrayList assemblyInstList			  	= null;
		AssemblyInst assemblyInst				= null;
		BOMSequence bomSequence					= null;
		ArrayList bomSequenceList				= null;

		String prodIdVal	= null;
		String pdAssembly	= null;
		String label 		= null;
		String value 		= null;
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecAssembly() method called");

			int rowCount = 1;
			specAssembly = new SpecAssembly();
			bomSequenceList = new ArrayList();
			assemblyInstList = new ArrayList();
			while(resultSet2.next()){
				if(rowCount == 1){
					prodIdVal = resultSet2.getString("PRODUCT_CODE");
					if(prodIdVal != null && !"".equals(prodIdVal.trim())){
						prodIdSpecAssembly = new ProdIdSpecAssembly();
						prodIdSpecAssembly.setAgency("Buyer");
						prodIdSpecAssembly.setPiType("PartNumber");
						prodIdSpecAssembly.setPiVal(prodIdVal.trim());
						specAssembly.setProdIdSpecAssembly(prodIdSpecAssembly);
					}
					pdAssembly = resultSet2.getString("PRODUCT_DESCRIPTION");
					if(pdAssembly != null && !"".equals(pdAssembly.trim())){
						pdSpecAssembly = new PDSpecAssembly();
						pdSpecAssembly.setLang("eng");
						pdSpecAssembly.setPdVal(pdAssembly.trim());
						pdSpecAssemblyList = new ArrayList();
						pdSpecAssemblyList.add(pdSpecAssembly);
						specAssembly.setPdSpecAssemblyList(pdSpecAssemblyList);
					}
				}
				rowCount++ ;

				label = resultSet2.getString("LABEL");
				value = resultSet2.getString("VALUE");
				if(label != null && !"".equals(label.trim()) &&
						value != null && !"".equals(value.trim())){
					
					if("BOM1".equalsIgnoreCase(label) || "BOM2".equalsIgnoreCase(label)
							|| "BOM3".equalsIgnoreCase(label) || "BOM4".equalsIgnoreCase(label)
							|| "BOM5".equalsIgnoreCase(label)){
		
						bomSequence = setBOMSequenceList(value);
						bomSequenceList.add(bomSequence);
					}
//					else if("Height".equalsIgnoreCase(label))
//					{
//						hVlue = new HtValue();
//						hVlue.setUom("Inch");
//						hVlue.setValue(value);
//						htSpecAssembly = new HtSpecAssembly();
//						htSpecAssembly.setValue(hVlue);
//						specAssembly.setHtSpecAssembly(htSpecAssembly);
//					}
					else if("Length".equalsIgnoreCase(label))
					{
						lValue = new LngValue();
						lValue.setUom("Inch");
						lValue.setValue(value);
						lngSpecAssembly = new LngSpecAssembly();
						lngSpecAssembly.setValue(lValue);
						specAssembly.setLngSpecAssembly(lngSpecAssembly);
					}else if("Width".equalsIgnoreCase(label))
					{
						wValue = new WidValue();
						wValue.setUom("Inch");
						wValue.setValue(value);
						widSpecAssembly = new WidSpecAssembly();
						widSpecAssembly.setValue(wValue);
						specAssembly.setWidSpecAssembly(widSpecAssembly);
					}else if("Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label)
							|| "Assembly Instruct 3".equalsIgnoreCase(label) || "Assembly Instruct 4".equalsIgnoreCase(label)
							|| "Assembly Instruct 5".equalsIgnoreCase(label) || "Additional Spec 1".equalsIgnoreCase(label)
							|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
							|| "Overage Print Qty".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
							|| "Packaging".equalsIgnoreCase(label) || "Rate Unit Descr".equalsIgnoreCase(label)
							|| "Assembly Rate".equalsIgnoreCase(label) || "Adoptions Form Devations".equalsIgnoreCase(label)
							|| "Adoptions Form Special Features".equalsIgnoreCase(label) || "Adoptions Form Waranty Clause(1-4)".equalsIgnoreCase(label)
							|| "Adoptions Input Complete?".equalsIgnoreCase(label) || "Adoptions Form College Meets MSST".equalsIgnoreCase(label)
							|| "Page count per signature".equalsIgnoreCase(label)
							|| "Single Source?".equalsIgnoreCase(label) || "Vendor 1".equalsIgnoreCase(label)
							|| "Vendor 2".equalsIgnoreCase(label) || "Vendor 3".equalsIgnoreCase(label)
							|| "Show on PO".equalsIgnoreCase(label)){						

						//Ashish:03/15/2015-CP#495575-Book Spec, PO and SI XML not transmitted via XBITS for ISBN# 0205874401 :Start

						/*assemblyInst = new AssemblyInst();
						assemblyInst.setAssemblyInst(label+":"+value);
						assemblyInstList.add(assemblyInst);*/
						
						int valLength = value.length()+ label.length();
						if(valLength > 250){
							ArrayList ValueList = new ArrayList();
							B2BHelper b2bHelpr = new B2BHelper();
							ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
							for (int i=0; i<ValueList.size(); i++){
								assemblyInst = new AssemblyInst();
								assemblyInst.setAssemblyInst((String)ValueList.get(i));
								assemblyInstList.add(assemblyInst);
							}
						}
						else{
							assemblyInst = new AssemblyInst();
							assemblyInst.setAssemblyInst(label+":"+value);
							assemblyInstList.add(assemblyInst);
						}
						//Ashish:03/15/2015-CP#495575-Book Spec, PO and SI XML not transmitted via XBITS for ISBN# 0205874401 :End
					}
				}
			}

			if(bomSequenceList!= null && bomSequenceList.size()>0){
				bfMaterials = new BillOfMaterials();
				bfMaterials.setBomSequenceList(bomSequenceList);
				specAssembly.setBfMaterials(bfMaterials);
			}
			if(assemblyInstList!= null && assemblyInstList.size()>0)
				specAssembly.setAssemblyInstList(assemblyInstList);
			
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecAssembly() method return");
		}catch (SQLException e) {			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			specAssembly = null;
		}finally{
			pdSpecAssemblyList	= null;
			pdSpecAssembly		= null;
			bfMaterials		  	= null;
			lngSpecAssembly		= null;
			lValue	 	  		= null;
			widSpecAssembly		= null;
			wValue		  		= null;
			assemblyInstList	= null;
			assemblyInst		= null;
			bomSequence			= null;
			bomSequenceList		= null;
			
			prodIdVal	= null;
			pdAssembly	= null;
			label 		= null;
			value 		= null;
		}
		if(prodIdSpecAssembly!=null)
			return specAssembly;
		else
			return null;
	}
	
	/**
	 * This method set the BOMSequence details for a single XML to generate.
	 * @param value
	 * @return BOMSequence
	 */
	private BOMSequence setBOMSequenceList(String value){
		BOMSequence bomSequence 	= null;
		ProdIDBOM prodIDBOM 		= null;
		ArrayList prodDescBOMList 	= null;
		ProdDescBOM prodDescBOM 	= null;
		QuantityBOM quantityBOM 	= null;
		ValueBOM valueBOM 			= null;
		StringUtils	strUtils		= null;
		
		int size 					= -1;
		String[] singleValue 		= null;
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setBOMSequenceList() method called");
			
			bomSequence = new BOMSequence();
			strUtils = new StringUtils();
			String valueWithoutSpace = strUtils.replaceMultipleSpaces(value);
			singleValue = valueWithoutSpace.split(" ");
			size = singleValue.length;
			String despSubStr = "";
			if(size>2){
				for(int j=2; j<size-1; j++){
					despSubStr = despSubStr+" "+singleValue[j];
				}
			}
			if(singleValue[0] != null && !"".equals(singleValue[0].trim())){
				prodIDBOM = new ProdIDBOM();
				prodIDBOM.setAgency("Buyer");
				prodIDBOM.setPiType("PartNumber");
				prodIDBOM.setPiVal(singleValue[0].trim());
				bomSequence.setProdIDBOM(prodIDBOM);
			}
			if(despSubStr != null && !"".equals(despSubStr.trim())){
				prodDescBOMList = new ArrayList();
				prodDescBOM = new ProdDescBOM();
				prodDescBOM.setLang("eng");
				prodDescBOM.setPdVal(despSubStr.trim());
				prodDescBOMList.add(prodDescBOM);
				bomSequence.setProdDescBOMList(prodDescBOMList);
			}
			if(singleValue[1] != null && !"".equals(singleValue[1].trim())){
				quantityBOM = new QuantityBOM();
				quantityBOM.setQuantityType("Count");
				valueBOM = new ValueBOM();
				valueBOM.setUom("Unit");
				valueBOM.setValue(singleValue[1].trim());
				quantityBOM.setValueBOM(valueBOM);
				bomSequence.setQuantityBOM(quantityBOM);
			}

			B2BLogger.debug("BookSpecificationDAOImpl.setBOMSequenceList() method return");
		}catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			bomSequence = null;
		}finally{
			prodIDBOM 		= null;
			prodDescBOMList = null;
			prodDescBOM 	= null;
			quantityBOM 	= null;
			valueBOM 		= null;
		}
		if(singleValue[0] != null && !"".equals(singleValue[0].trim()))
			return bomSequence;
		else
			return null;
	}
	
	/**
	 * This method set the SpecBinding details for a single XML to generate.
	 * @param resultSet3
	 * @param bsDTO 
	 * @return SpecBinding
	 */
	private SpecBinding setSpecBinding(Connection dbCon, ResultSet resultSet3, BookSpecificationDTO bsDTO){
		SpecBinding specBinding 		= null;
		ProdIdSpecBinding prodIdBind 	= null;
		ArrayList pdSpecBindingList		= null;
		PDSpecBinding pdSpecBinding		= null;
		MechMaterial mechMaterial		= null;
		MMChars mmChars 				= null;
		MMCWireMatl mmcWireMatl			= null;
		MMCWireGauge mmcWireGauge		= null;
		MMCWGValue mmCWGValue			= null;
		ArrayList endsheetInfoList		= null;
		EndsheetInfo endSheetInfo		= null;
		Case bindCase					= null;
		CaseMaking casMaking			= null;
		ArrayList caseMaterialList		= null;
		CaseMaterial caseMaterial		= null;
		CMBMChars cmbmChars				= null;
		ESMaterial esMaterial			= null;
		ESMaterial esMaterial2			= null;
		ESBMaterialChars esbmChars		= null;
		CaseLining casLining			= null;
		ArrayList headbandList			= null;
		Headband headband				= null;
		ArrayList cbMaterialList		= null;
		CBMaterial cbMaterial			= null;
		ArrayList cbAddTextList			= null;
		CBAddText cbAddText				= null;
		ArrayList sbAddTextList 		= null;
		SBAddText sbAddText				= null;
		ArrayList sbDecSpecsList		= null;
		SBDecSpecs sbDecSpecs			= null;
		SBDSDecCovge sbDSDecCovge		= null;
		SBDSValue sbdsvalue			 	= null;
		HPInfo hpinfo 					= null;
		ArrayList hpAddTextList			= null;
		HPAddText hpAddText				= null;
		PHDetails phDetails 			= null;
		PHSzOfHoPun phSzOfHoPun			= null;
		SzHolPunValue szHolPunvalue		= null;
		ArrayList phDisFEdgList			= null;
		PHDisFEdg phDisFEdg				= null;
		DisFEdgValue disFEdgvalue		= null;
		PHolSpac pholSpac				= null;
		EvenSpacing evenSpacing			= null;
		ESValue	esValue 				= null;
		Perforation perforation			= null;
		ArrayList pfAddTextList   		= null;
		PFAddText pfAddText				= null;
		DistFromEdge distFromEdge 		= null;
		DFEValue defValue				= null;
		BookBlock bookBlock				= null;
		ArrayList bbEdgeTrimList		= null;
		BBEdgeTrim bbEdgeTrim 			= null;
		CaseAddText caseAddText			= null;
		String sbProdId		= null;
		String sbProdDesp	= null;
		String wireGaugeVal	= null;	
		String label		= null;
		String value		= null;
		String mmDescp 		= null;
		String bindBkStyTyp = null;
		String cLocType		= null;
		String esType		= null;
		String bindBackType	= null;
		String bindReinf 	= null;
		String holePunSize	= null;
		String disFromEdg	= null;
		String prodDesp1	= null;
		String holePunThrCover = null;
		String numOfHoles	= null;
		String stamInkHit 	= null;
		String stamFoilHit	= null;
		String stamBlindHit	= null;
		String inkDescr		= null;
		String foilDescr	= null;
		String blindDescr	= null;
		String sqInchFoil	= null;
		String tightBack	= null;
		String reinforDescr	= null;
		String caseType		= null;
		String shrinkWrapVal			= null;
		ArrayList buPackingList			= null;
		BUPacking buPacking				= null;	
		BUPWrapChars bupWrapChars 		= null;
		ArrayList bupWrapList 			= null;
		BUPWrap bupWrap					= null;
		ArrayList specPackingList		= null;
		SpecPacking specPacking	  		= null;
		ProdIdSpecPacking pIdSpecPackg	= null;
		B2BHelper b2bHelper				= null;
		long specId 					= -1;
		long specVersion 				= -1;
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecBinding() method called");
			
			specBinding = new SpecBinding();
			sbAddTextList = new ArrayList();
			sbDecSpecsList = new ArrayList();
			hpAddTextList = new ArrayList();
			pdSpecBindingList = new ArrayList();
			
			int rowCount = 1;
			while(resultSet3.next()){
				if(rowCount == 1){
					/*bookBlock = new BookBlock();
					specBinding.setBookBlock(bookBlock);*/
					sbProdId = resultSet3.getString("PRODUCT_CODE");
					if(sbProdId != null && !"".equals(sbProdId.trim())){
						prodIdBind = new ProdIdSpecBinding();
						prodIdBind.setAgency("Buyer");
						prodIdBind.setPiType("PartNumber");
						prodIdBind.setPiVal(sbProdId.trim());
						specBinding.setProdIdSpecBind(prodIdBind);
					}
					sbProdDesp = resultSet3.getString("PRODUCT_DESCRIPTION");
					if(sbProdDesp != null && !"".equals(sbProdDesp.trim())){
						pdSpecBinding = new PDSpecBinding();
						pdSpecBinding.setLang("eng");
						pdSpecBinding.setPdVal(sbProdDesp.trim());
						//pdSpecBindingList = new ArrayList();
						pdSpecBindingList.add(pdSpecBinding);
						//specBinding.setPdSpecBindingList(pdSpecBindingList);
					}
					wireGaugeVal = resultSet3.getString("WIRE_GAUGE");
					if(wireGaugeVal != null && !"".equals(wireGaugeVal.trim())){
						boolean flag = true;
						try {
							Float.parseFloat(wireGaugeVal);
						} catch (NumberFormatException e) {
							flag = false;
						}
						if(flag){
							mmCWGValue = new MMCWGValue();
							mmCWGValue.setUom("Unit");
							mmCWGValue.setValue(wireGaugeVal.trim());
							mmcWireGauge = new MMCWireGauge();
							mmcWireGauge.setValue(mmCWGValue);
							mmcWireMatl = new MMCWireMatl();
							mmcWireMatl.setMmcWireGauge(mmcWireGauge);
						}						
					}
					/*bindBkStyTyp = resultSet3.getString("BACK_STYLE_TYPE");
					if(bindBkStyTyp != null && !"".equals(bindBkStyTyp.trim()))
						specBinding.setBindBkStyType(bindBkStyTyp.trim());*/
					
					/*bindReinf = resultSet3.getString("REINFORCEMENT");
					if(bindReinf != null && !"".equals(bindReinf.trim()))
						specBinding.setBindReinf(bindReinf.trim());*/
					
					holePunSize = resultSet3.getString("HOLEPUNCH_SIZE");
					if(holePunSize != null && !"".equals(holePunSize.trim())){
						szHolPunvalue = new SzHolPunValue();
						szHolPunvalue.setUom("Inch");
						szHolPunvalue.setValue(holePunSize.trim());
						phSzOfHoPun = new PHSzOfHoPun();
						phSzOfHoPun.setValue(szHolPunvalue);
					}
					specId = resultSet3.getLong("SPEC_ID");
					specVersion = resultSet3.getLong("SPEC_VERSION");
					/*disFromEdg = resultSet3.getString("HOLEPUNCH_EDGE_DISTANCE");
					if(disFromEdg != null && !"".equals(disFromEdg.trim())){
						disFEdgvalue = new DisFEdgValue();
						disFEdgvalue.setUom("Inch");
						disFEdgvalue.setValue(disFromEdg.trim());
						phDisFEdg = new PHDisFEdg();
						phDisFEdg.setValue(disFEdgvalue);
						phDisFEdgList = new ArrayList();
						phDisFEdgList.add(phDisFEdg);
					}*/
				}
				rowCount++ ;
				
				label = resultSet3.getString("LABEL");
				value = resultSet3.getString("VALUE");
				if(label != null && !"".equals(label.trim()) &&
						value != null && !"".equals(value.trim())){
					
					if("Bind Type".equalsIgnoreCase(label)){
						String xbitVal5=getXbitsMapping(dbCon, "BINDING", "Bind Type", value.trim());
						if(null==xbitVal5 || "".equals(xbitVal5)){
							sbAddText = new SBAddText();
							sbAddText.setSbAddText("Bind Type:"+value.trim());
							sbAddTextList.add(sbAddText);					
						}else{
							specBinding.setBindType(xbitVal5);
						}
					}
					else if("Bind Style".equalsIgnoreCase(label)){
						String xbitVal6=getXbitsMapping(dbCon, "BINDING", "Bind Style", value.trim());
						if(null==xbitVal6 || "".equals(xbitVal6)){
							sbAddText = new SBAddText();
							sbAddText.setSbAddText("Bind Style:"+value.trim());
							sbAddTextList.add(sbAddText);					
						}else{
							specBinding.setBindStyType(xbitVal6);
						}
					}
					else if("3 Piece Material".equalsIgnoreCase(label)){
						String xbitVal10=getXbitsMapping(dbCon, "BINDING", "3 Piece Material", value.trim());
						caseType = xbitVal10;
						
						esbmChars = new ESBMaterialChars();
						esbmChars.setBmDesp(value.trim());
						esMaterial2 = new ESMaterial();
						esMaterial2.setEsbmChars(esbmChars);
					}
					else if("Colored Wire Surchrg".equalsIgnoreCase(label))
						mmDescp = value;
					else if("State Label Required".equalsIgnoreCase(label))
						esType = value;
					else if("Endpaper Descr".equalsIgnoreCase(label)){
						esbmChars = new ESBMaterialChars();
						esbmChars.setBmDesp(value);
						esMaterial = new ESMaterial();
						esMaterial.setEsbmChars(esbmChars);
					}else if("Description".equalsIgnoreCase(label)){
						pdSpecBinding = new PDSpecBinding();
						pdSpecBinding.setLang("eng");
						pdSpecBinding.setPdVal(value.trim());
						pdSpecBindingList.add(pdSpecBinding);
					}
					else if("Cover Material".equalsIgnoreCase(label)){
						cmbmChars = new CMBMChars();
						cmbmChars.setBmDesp(value.trim());
					}
					else if("Edge Descr".equalsIgnoreCase(label))
						cLocType = value;
					else if("Backing Descr".equalsIgnoreCase(label)){
							bindBackType = value;
					}
					else if("Lining Up Descr".equalsIgnoreCase(label)){
						casLining = new CaseLining();
						casLining.setClDesp(value.trim());
					}
					else if("Headband Descr".equalsIgnoreCase(label)){
						headband = new Headband();
						headband.setColDesp(value.trim());
						headbandList = new ArrayList();
						headbandList.add(headband);
					}
					else if("Boards Descr".equalsIgnoreCase(label)){
						cbAddText = new CBAddText();
						cbAddText.setCbAddText(label+":"+value);
						cbAddTextList = new ArrayList();
						cbAddTextList.add(cbAddText);
						cbMaterial = new CBMaterial();
						cbMaterial.setCbAddTextList(cbAddTextList);
						cbMaterialList = new ArrayList();
						cbMaterialList.add(cbMaterial);
					}
					/*else if("Stamping Ink Hits".equalsIgnoreCase(label) || "Stamping Foil Hits".equalsIgnoreCase(label)
							|| "Stamping Blind Hits".equalsIgnoreCase(label)){
						sbDecSpecs = new SBDecSpecs();
						sbDecSpecs.setDecType("Stamping");
						sbDecSpecs.setNumOfHits(value);
						sbDecSpecsList.add(sbDecSpecs);
					}*/
					else if("Stamping Ink Hits".equalsIgnoreCase(label)){
						stamInkHit= value;
					}
					else if("Stamping Foil Hits".equalsIgnoreCase(label)){
						stamFoilHit= value;
					}
					else if("Stamping Blind Hits".equalsIgnoreCase(label)){
						stamBlindHit= value;
					}
					else if("Ink Descr".equalsIgnoreCase(label)){
						inkDescr=value;
					}
					else if("Foil Descr".equalsIgnoreCase(label)){
						foilDescr= value;
					}
					else if("Blind Descr".equalsIgnoreCase(label)){
						blindDescr= value;
					}
					else if("Square Inches Foil".equalsIgnoreCase(label)){
						sqInchFoil= value;						
					}
					else if("Post Emboss".equalsIgnoreCase(label)){
						sbDecSpecs = new SBDecSpecs();
						sbDecSpecs.setDecType(value);
						sbDecSpecsList.add(sbDecSpecs);
					}
					/*else if("Ink Descr".equalsIgnoreCase(label) || "Foil Descr".equalsIgnoreCase(label)
							|| "Blind Descr".equalsIgnoreCase(label)){
						sbDecSpecs = new SBDecSpecs();
						sbDecSpecs.setColDesp(value);
						sbDecSpecsList.add(sbDecSpecs);
					}
					else if("Square Inches Foil".equalsIgnoreCase(label)){
						sbdsvalue = new SBDSValue();
						sbdsvalue.setUom("SquareInch");
						sbdsvalue.setValue(value);
						sbDSDecCovge = new SBDSDecCovge();
						sbDSDecCovge.setValue(sbdsvalue);
						sbDecSpecs = new SBDecSpecs();
						sbDecSpecs.setSbDSDecCovge(sbDSDecCovge);
						sbDecSpecsList.add(sbDecSpecs);
					}*/
					//else if("Holes Punch Options".equalsIgnoreCase(label) || "Holes Punch Descr".equalsIgnoreCase(label)){
					else if("Holes Punch Descr".equalsIgnoreCase(label)){
						hpAddText = new HPAddText();
						hpAddText.setHpAddText(label+":"+value);
						hpAddTextList.add(hpAddText);
					}
					else if("Holes Punch Options".equalsIgnoreCase(label)){
						if(value.contains("Co")){
							holePunThrCover = "Yes";
						}else{
							holePunThrCover = "No";
						}
						if(value.contains("5")){
							numOfHoles="5";
						}else{
							numOfHoles="3";
						}
					}
							
					else if("Holes Punch Distance".equalsIgnoreCase(label)){
						esValue = new ESValue();
						esValue.setUom("Inch");
						esValue.setValue(value);
						evenSpacing = new EvenSpacing();
						evenSpacing.setEsValue(esValue);
						pholSpac = new PHolSpac();
						pholSpac.setEvenSpacing(evenSpacing);
					}
					else if("Press Perforations".equalsIgnoreCase(label) && "Yes".equalsIgnoreCase(value)){
						pfAddText = new PFAddText();
						pfAddText.setPfAddText(label+":"+value);
						pfAddTextList = new ArrayList();
						pfAddTextList.add(pfAddText);
					}
					/*else if("Perf Edge".equalsIgnoreCase(label)){
						distFromEdge = new DistFromEdge();
						distFromEdge.setEdgeType(value);
					}*/
					else if("Perf Distance".equalsIgnoreCase(label)){
						distFromEdge = new DistFromEdge();
						distFromEdge.setEdgeType("Spine");
						defValue = new DFEValue();
						defValue.setUom("Inch");
						defValue.setValue(value);
						distFromEdge.setValue(defValue);
					}
					else if("Tight Back".equalsIgnoreCase(label)){
						tightBack=value;     		
					}
					else if("Reinforcement Descr".equalsIgnoreCase(label)){
						reinforDescr=value;     		
					}

					else if("Shrinkwrap".equalsIgnoreCase(label)){
						shrinkWrapVal = value;
						
						sbAddText = new SBAddText();
						sbAddText.setSbAddText(label+":"+value);
						sbAddTextList.add(sbAddText);
					}
					else if("Insert Type".equalsIgnoreCase(label) || "Insert Placement".equalsIgnoreCase(label)
							|| "Reply Cards".equalsIgnoreCase(label) || "Jackets".equalsIgnoreCase(label)
							|| "ManufacturingIncr Rt".equalsIgnoreCase(label) || "Disk Pkg Adhesion".equalsIgnoreCase(label)
							|| "Cancel 8 pp.".equalsIgnoreCase(label) || "Cancel 16 pp.".equalsIgnoreCase(label)
							|| "Case-in Charge".equalsIgnoreCase(label) || "Cover Cutting".equalsIgnoreCase(label)
							|| "Cover Folding".equalsIgnoreCase(label) || "Deduction for PP ends".equalsIgnoreCase(label)
							|| "Keep Separate".equalsIgnoreCase(label) || "Bulk Cartons".equalsIgnoreCase(label)
							|| "Additional Spec 1".equalsIgnoreCase(label) || "Additional Spec 2".equalsIgnoreCase(label)
							|| "Additional Spec 3".equalsIgnoreCase(label) || "Assembly Instruct 1".equalsIgnoreCase(label)
							|| "Assembly Instruct 2".equalsIgnoreCase(label) || "Holes Punch Edge".equalsIgnoreCase(label)){
							//|| "Edge Descr".equalsIgnoreCase(label)
							//|| "Bind Style".equalsIgnoreCase(label)
							//|| "Bind Type".equalsIgnoreCase(label) 
							//|| "Shrinkwrap".equalsIgnoreCase(label)
						
						//Ashish:03/15/2015-CP#495575-Book Spec, PO and SI XML not transmitted via XBITS for ISBN# 0205874401 :Start
						/*sbAddText = new SBAddText();
						sbAddText.setSbAddText(label+":"+value);
						sbAddTextList.add(sbAddText);*/
						
						int valLength = value.length()+ label.length();
						if(valLength > 250){
							ArrayList ValueList = new ArrayList();
							B2BHelper b2bHelpr = new B2BHelper();
							ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
							for (int i=0; i<ValueList.size(); i++){
								sbAddText = new SBAddText();
								sbAddText.setSbAddText((String)ValueList.get(i));
								sbAddTextList.add(sbAddText);
							}
						}
						else{
							sbAddText = new SBAddText();
							sbAddText.setSbAddText(label+":"+value);
							sbAddTextList.add(sbAddText);
						}
						//Ashish:03/15/2015-CP#495575-Book Spec, PO and SI XML not transmitted via XBITS for ISBN# 0205874401 :End
					}
				}
			} // end While loop
			
			b2bHelper = new B2BHelper();

			String tableName1 = "PIX_BOOK_SPEC_LINE";
			String whereClause1 = "SPEC_ID='"+specId+"' and SPEC_VERSION='"+specVersion+"' and PRODUCT_DESCRIPTION='"+"Packaging"+"'";
			int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);

			if(count1==0 && null!=shrinkWrapVal && !"".equals(shrinkWrapVal)){
				bupWrap = new BUPWrap();
				bupWrap.setWrapType("ShrinkWrap");
				bupWrapList = new ArrayList();
				bupWrapList.add(bupWrap);
				bupWrapChars = new BUPWrapChars();
				bupWrapChars.setBupWrapList(bupWrapList);
				bupWrapChars.setPackPerWrap("1");
				buPacking = new BUPacking();
				buPacking.setBupWrapChars(bupWrapChars);
				buPackingList = new ArrayList();
				buPackingList.add(buPacking);
				specPacking = new SpecPacking();
				
				if(null!=sbProdId && !"".equals(sbProdId.trim())){
					pIdSpecPackg = new ProdIdSpecPacking();
					pIdSpecPackg.setPiType("PartNumber");
					pIdSpecPackg.setAgency("Buyer");
					pIdSpecPackg.setPiVal(sbProdId.trim());
					specPacking.setPIdSpecPackg(pIdSpecPackg);
				}
				specPacking.setBuPackingList(buPackingList);
				specPackingList = new ArrayList();
				specPackingList.add(specPacking);
				bsDTO.setSpecPackingList(specPackingList);
			}
			
			if(null!=tightBack && !"".equals(tightBack.trim())){
				String xbitVal3=getXbitsMapping(dbCon, "BINDING", "Tight Back", tightBack.trim());
				specBinding.setBindBkStyType(xbitVal3);	
			}
			
			if(null!=reinforDescr && !"".equals(reinforDescr.trim())){
				String xbitVal4=getXbitsMapping(dbCon, "BINDING", "Reinforcement Descr", reinforDescr.trim());
				specBinding.setBindReinf(xbitVal4);	
			}
			
			if(null!=pdSpecBindingList && pdSpecBindingList.size()>0)
				specBinding.setPdSpecBindingList(pdSpecBindingList);
			
			if(mmDescp!=null && !"".equals(mmDescp.trim())){
				mmChars = new MMChars();
				mmChars.setMmDescp(mmDescp.trim());
				if(mmcWireMatl != null)
					mmChars.setMmcWireMatl(mmcWireMatl);
				mechMaterial = new MechMaterial();
				mechMaterial.setMmChars(mmChars);
				specBinding.setMechMaterial(mechMaterial);	
			}
			if(cmbmChars != null){
				caseMaterial = new CaseMaterial();
				//if(cmbmChars != null)
				caseMaterial.setCmbmChars(cmbmChars);
				/*if(cLocType!=null && !"".equals(cLocType.trim()))
					caseMaterial.setCLocType(cLocType.trim());*/
				
				caseMaterialList = new ArrayList();
				caseMaterialList.add(caseMaterial);
			}
			if(cLocType!=null && !"".equals(cLocType.trim())){
				String xbitVal2=getXbitsMapping(dbCon, "BINDING", "Edge Descr", cLocType.trim());
				if(null==xbitVal2 || "".equals(xbitVal2)){
					sbAddText = new SBAddText();
					sbAddText.setSbAddText("Edge Descr:"+cLocType.trim());
					sbAddTextList.add(sbAddText);
				}else{
					bbEdgeTrim = new BBEdgeTrim();
					bbEdgeTrim.setEdgeFinish(xbitVal2);

					bbEdgeTrimList = new ArrayList();
					bbEdgeTrimList.add(bbEdgeTrim);
					
					bookBlock = new BookBlock();
					bookBlock.setBbEdgeTrimList(bbEdgeTrimList);
					specBinding.setBookBlock(bookBlock);
				}
			}else{
				bookBlock = new BookBlock();
				specBinding.setBookBlock(bookBlock);
			}
			if((cbMaterialList!=null && cbMaterialList.size()>0) 
				|| (caseMaterialList != null && caseMaterialList.size()>0)
				|| (null!=caseType && !"".equals(caseType.trim()))){
				
				casMaking = new CaseMaking();
				if(cbMaterialList!=null && cbMaterialList.size()>0)
					casMaking.setCbMaterialList(cbMaterialList);
				if(caseMaterialList != null && caseMaterialList.size()>0)
					casMaking.setCaseMaterialList(caseMaterialList);
				if(null!=caseType && !"".equals(caseType.trim())){
					casMaking.setCasType(caseType.trim());
				}
			}
			if((null!=stamInkHit && !"".equals(stamInkHit.trim()))
					||(null!=inkDescr && !"".equals(inkDescr.trim()))){
				sbDecSpecs = new SBDecSpecs();
				sbDecSpecs.setDecType("InkStamping");
				if(null!=stamInkHit && !"".equals(stamInkHit.trim())){
					sbDecSpecs.setNumOfHits(stamInkHit.trim());
				}
				if(null!=inkDescr && !"".equals(inkDescr.trim())){
					sbDecSpecs.setColDesp(inkDescr.trim());
				}
				if(null!=sqInchFoil && !"".equals(sqInchFoil.trim())){
					sbdsvalue = new SBDSValue();
					sbdsvalue.setUom("SquareInch");
					sbdsvalue.setValue(sqInchFoil.trim());
					sbDSDecCovge = new SBDSDecCovge();
					sbDSDecCovge.setValue(sbdsvalue);
					sbDecSpecs.setSbDSDecCovge(sbDSDecCovge);
				}
				sbDecSpecsList.add(sbDecSpecs);
			}else if((null!=stamFoilHit && !"".equals(stamFoilHit.trim()))
					||(null!=foilDescr && !"".equals(foilDescr.trim()))){
				sbDecSpecs = new SBDecSpecs();
				sbDecSpecs.setDecType("FoilStamping");
				if(null!=stamFoilHit && !"".equals(stamFoilHit.trim())){
					sbDecSpecs.setNumOfHits(stamFoilHit.trim());
				}
				if(null!=foilDescr && !"".equals(foilDescr.trim())){
					sbDecSpecs.setColDesp(foilDescr.trim());
				}
				if(null!=sqInchFoil && !"".equals(sqInchFoil.trim())){
					sbdsvalue = new SBDSValue();
					sbdsvalue.setUom("SquareInch");
					sbdsvalue.setValue(sqInchFoil.trim());
					sbDSDecCovge = new SBDSDecCovge();
					sbDSDecCovge.setValue(sbdsvalue);
					sbDecSpecs.setSbDSDecCovge(sbDSDecCovge);
				}
				sbDecSpecsList.add(sbDecSpecs);
			}else if((null!=stamBlindHit && !"".equals(stamBlindHit.trim()))
					||(null!=blindDescr && !"".equals(blindDescr.trim()))){
				sbDecSpecs = new SBDecSpecs();
				sbDecSpecs.setDecType("Stamping");
				if(null!=stamBlindHit && !"".equals(stamBlindHit.trim())){
					sbDecSpecs.setNumOfHits(stamBlindHit.trim());
				}
				if(null!=blindDescr && !"".equals(blindDescr.trim())){
					sbDecSpecs.setColDesp(blindDescr.trim());
				}
				if(null!=sqInchFoil && !"".equals(sqInchFoil.trim())){
					sbdsvalue = new SBDSValue();
					sbdsvalue.setUom("SquareInch");
					sbdsvalue.setValue(sqInchFoil.trim());
					sbDSDecCovge = new SBDSDecCovge();
					sbDSDecCovge.setValue(sbdsvalue);
					sbDecSpecs.setSbDSDecCovge(sbDSDecCovge);
				}
				sbDecSpecsList.add(sbDecSpecs);
			}else if(null!=sqInchFoil && !"".equals(sqInchFoil.trim())){
					sbDecSpecs = new SBDecSpecs();
					sbdsvalue = new SBDSValue();
					sbdsvalue.setUom("SquareInch");
					sbdsvalue.setValue(sqInchFoil.trim());
					sbDSDecCovge = new SBDSDecCovge();
					sbDSDecCovge.setValue(sbdsvalue);
					sbDecSpecs.setSbDSDecCovge(sbDSDecCovge);
					
					sbDecSpecsList.add(sbDecSpecs);
			}
			
			if((bindBackType!=null && !"".equals(bindBackType.trim())) 
				 || casLining != null ||(headbandList != null && headbandList.size()>0)
				 || (sbDecSpecsList != null && sbDecSpecsList.size()>0)
				 || casMaking != null){
				
				bindCase = new Case();
				if(bindBackType!=null && !"".equals(bindBackType.trim())){
					String xbitVal=getXbitsMapping(dbCon, "BINDING", "Backing Descr", bindBackType.trim());
					if(null==xbitVal || "".equals(xbitVal)){
						caseAddText = new CaseAddText();
						caseAddText.setCaseAddText("Backing Descr:"+bindBackType.trim());
						ArrayList caseAddTextList = new ArrayList();
						caseAddTextList.add(caseAddText);
						bindCase.setCaseAddTextList(caseAddTextList);						
					}else{
						bindCase.setBindBackType(xbitVal);
					}
				}
				if(casLining != null)
					bindCase.setCasLining(casLining);
				if((headbandList != null && headbandList.size()>0))
					bindCase.setHeadbandList(headbandList);
				if(sbDecSpecsList != null && sbDecSpecsList.size()>0)
					bindCase.setSbDecSpecsList(sbDecSpecsList);
				if(casMaking != null)
					bindCase.setCasMaking(casMaking);
				
				specBinding.setBindCase(bindCase);
			}
			if(esMaterial != null || (esType!=null && "Yes".equals(esType.trim()))
					|| null!=esMaterial2){
				endSheetInfo = new EndsheetInfo();
				if(esMaterial != null)
					endSheetInfo.setEsMaterial(esMaterial);
				if(esType!=null && "Yes".equals(esType.trim())){
					//endSheetInfo.setEsType(esType);
					endSheetInfo.setEsType("UniformStateLabel");
				}
				endsheetInfoList = new ArrayList();
				
				if(esMaterial != null || (esType!=null && "Yes".equals(esType.trim()))){
					endsheetInfoList.add(endSheetInfo);
				}
				if(null!=esMaterial2){
					endSheetInfo = new EndsheetInfo();
					endSheetInfo.setEsMaterial(esMaterial2);
					endsheetInfoList.add(endSheetInfo);
				}
				specBinding.setEndsheetInfoList(endsheetInfoList);
			}
			if(pholSpac != null	|| phSzOfHoPun != null 
				|| (phDisFEdgList != null && phDisFEdgList.size()>0)
				|| (null!=numOfHoles && !"".equalsIgnoreCase(numOfHoles))){
				phDetails = new PHDetails();
				if(pholSpac!=null)
					phDetails.setPholSpac(pholSpac);
				if(phSzOfHoPun!=null)
					phDetails.setPhSzOfHoPun(phSzOfHoPun);
				if(phDisFEdgList != null && phDisFEdgList.size()>0)
					phDetails.setPhDisFEdgList(phDisFEdgList);
				if(null!=numOfHoles && !"".equalsIgnoreCase(numOfHoles))
					phDetails.setPhNumOfHoles(numOfHoles);
			}
			if((hpAddTextList != null && hpAddTextList.size()>0) || phDetails!= null || (null!=holePunThrCover && !"".equalsIgnoreCase(holePunThrCover))){
				hpinfo = new HPInfo();
				if(hpAddTextList != null && hpAddTextList.size()>0)
					hpinfo.setHpAddTextList(hpAddTextList);
				if(phDetails!=null)
					hpinfo.setPhDetails(phDetails);
				
				if(null!=holePunThrCover && !"".equalsIgnoreCase(holePunThrCover)){
					hpinfo.setHptCover(holePunThrCover);
				}
				specBinding.setHpinfo(hpinfo);
			}
			//if((pfAddTextList != null && pfAddTextList.size()>0) || distFromEdge != null){
			if((pfAddTextList != null && pfAddTextList.size()>0)){
				perforation = new Perforation();
				perforation.setPerfType("Workbook");
				//if((pfAddTextList != null || pfAddTextList.size()>0))
					perforation.setPfAddTextList(pfAddTextList);
				if(distFromEdge != null)
					perforation.setDistFromEdge(distFromEdge);
				
				specBinding.setPerforation(perforation);
			}
			if(sbAddTextList!=null && sbAddTextList.size()>0)
				specBinding.setSbAddTextList(sbAddTextList);

			B2BLogger.debug("BookSpecificationDAOImpl.setSpecBinding() method return");
		}catch (SQLException e) {
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			specBinding = null;
		}finally{
			pdSpecBindingList	= null;
			pdSpecBinding		= null;
			mechMaterial		= null;
			mmChars 			= null;
			mmcWireMatl			= null;
			mmcWireGauge		= null;
			mmCWGValue			= null;
			endsheetInfoList	= null;
			endSheetInfo		= null;
			bindCase			= null;
			casMaking			= null;
			caseMaterialList	= null;
			caseMaterial		= null;
			cmbmChars			= null;
			esMaterial			= null;
			esbmChars			= null;
			casLining			= null;
			headbandList		= null;
			headband			= null;
			cbMaterialList		= null;
			cbMaterial			= null;
			cbAddTextList		= null;
			cbAddText			= null;
			sbAddTextList 		= null;
			sbAddText			= null;
			sbDecSpecsList		= null;
			sbDecSpecs			= null;
			sbDSDecCovge		= null;
			sbdsvalue			= null;
			hpinfo 				= null;
			hpAddTextList		= null;
			hpAddText			= null;
			phDetails 			= null;
			phSzOfHoPun			= null;
			szHolPunvalue		= null;
			phDisFEdgList		= null;
			phDisFEdg			= null;
			disFEdgvalue		= null;
			pholSpac			= null;
			evenSpacing			= null;
			esValue 			= null;
			perforation			= null;
			pfAddTextList   	= null;
			pfAddText			= null;
			distFromEdge 		= null;
			
			sbProdId		= null;
			sbProdDesp		= null;
			wireGaugeVal	= null;	
			label			= null;
			value			= null;
			mmDescp 		= null;
			bindBkStyTyp 	= null;
			cLocType		= null;
			esType			= null;
			bindBackType	= null;
			bindReinf 		= null;
			holePunSize		= null;
			disFromEdg		= null;
		}
		if(prodIdBind != null)
			return specBinding;
		else
			return null;
	}
	
	private String getXbitsMapping(Connection dbCon, String component, String specType, String value) {
		
		PreparedStatement prepStmt11		= null;
		ResultSet resultSet11				= null;
		String qryParams					= null;
		String sqlQuery						= null;
		String xbitVal						= null;
		try {
			sqlQuery = qry_sel_xbit_value;
			prepStmt11 = dbCon.prepareStatement(sqlQuery);
			prepStmt11.clearParameters();
			prepStmt11.setString(IPixB2BConstants.ONE, component);
			prepStmt11.setString(IPixB2BConstants.TWO, specType);
			prepStmt11.setString(IPixB2BConstants.THREE, value);
			prepStmt11.setString(IPixB2BConstants.FOUR, IPixB2BConstants.flag_Y);
			prepStmt11.setString(IPixB2BConstants.FIVE, IPixB2BConstants.flag_Y);
			qryParams = "BINDING"+", "+specType+", "+value+", "+IPixB2BConstants.flag_Y+", "+IPixB2BConstants.flag_Y;
			resultSet11 = prepStmt11.executeQuery();
			while(resultSet11.next()){
				xbitVal = resultSet11.getString("XBITS_VALUE");
			}
		} catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			xbitVal = null;
		} finally{
			DBUtils.close(prepStmt11);
			DBUtils.close(resultSet11);			
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return xbitVal;
	}

	/**
	 * This method set the SpecPacking List details for a single XML to generate.
	 * @param dbCon
	 * @param resultSet4
	 * @return ArrayList
	 */
	private ArrayList setSpecPackingList(Connection dbCon, ResultSet resultSet4){
		ArrayList specPackingList = null;
		SpecPacking specPacking	  = null;
		String prodIdVal 		  = null;
		String prodDesp 		  = null;
		
		long specVersion 	= -1;
		long specId			= -1;
		long specLineNumber	= -1;
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecPackingList() method called");
			
			specPackingList = new ArrayList();
			boolean dataFound 	 = false;
			long specLineNum 	 = -1;
			long specLineNumPrev = -1;
			
			while(resultSet4.next()){
				dataFound = true;

				specLineNum = resultSet4.getLong("SPEC_LINE_NO");
				if(specLineNumPrev != -1  && specLineNum != specLineNumPrev){
					specPacking = setSpecPacking(prodIdVal, prodDesp, dbCon, specLineNumber, specId, specVersion);
					if(specPacking != null)
						specPackingList.add(specPacking);
					else
						B2BLogger.info("BookSpecificationDAOImpl.setSpecPackingList()- specPacking is null");
				}
				//Get ResultSet
				specId	= resultSet4.getLong("SPEC_ID");
				specVersion = resultSet4.getLong("SPEC_VERSION");
				specLineNumber = resultSet4.getLong("SPEC_LINE_NO");
				prodIdVal = resultSet4.getString("PRODUCT_CODE");
				prodDesp = resultSet4.getString("PRODUCT_DESCRIPTION");
				
				specLineNumPrev = specLineNum;
			} //end while loop
			
			if(dataFound){
				specPacking = setSpecPacking(prodIdVal, prodDesp, dbCon, specLineNumber, specId, specVersion);
				
				if(specPacking != null)
					specPackingList.add(specPacking);
				else
					B2BLogger.info("BookSpecificationDAOImpl.setSpecPackingList()- specPacking is null");
			}
			
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecPackingList() method return");
		}catch (SQLException e) {
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			specPackingList = null;
		}finally{
			specPacking	  = null;
			prodIdVal 	  = null;
			prodDesp 	  = null;
			
			specVersion   = -1;
			specId        = -1;
			specLineNumber= -1;
		}
		return specPackingList;
	}

	/**
	 * This method set the SpecPacking details for a single XML to generate.
	 * @param prodIdVal
	 * @param prodDesp
	 * @param dbCon
	 * @param specLineNumber
	 * @param specId
	 * @param specVersion
	 * @return SpecPacking
	 */
	private SpecPacking setSpecPacking(String prodIdVal, String prodDesp, Connection dbCon,
			long specLineNumber, long specId, long specVersion){
		
		SpecPacking specPacking 		= null;
		ProdIdSpecPacking pIdSpecPackg	= null;
		ArrayList spProdDespList		= null;
		SPProdDesp spProdDesp			= null;
		ArrayList spAddTextList			= null;
		SPAddText spAddText				= null;
		
		String sqlQuery					= null;
		String qryParams				= null;
		PreparedStatement prepStmt2		= null;
		ResultSet resultSet7			= null;
		PreparedStatement prepStmt12	= null;
		ResultSet resultSet12			= null;
		String shrinkWrapVal			= null;
		ArrayList buPackingList			= null;
		BUPacking buPacking				= null;	
		BUPWrapChars bupWrapChars 		= null;
		ArrayList bupWrapList 			= null;
		BUPWrap bupWrap					= null;
		try {
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecPacking() method called");
			specPacking = new SpecPacking();
					
			sqlQuery = qry_sel_pix_book_spec_misc;
			prepStmt2 = dbCon.prepareStatement(sqlQuery);
			prepStmt2.clearParameters();
			prepStmt2.setLong(IPixB2BConstants.ONE, specId);
			prepStmt2.setLong(IPixB2BConstants.TWO, specVersion);
			prepStmt2.setLong(IPixB2BConstants.THREE, specLineNumber);
			qryParams = specId+", "+specVersion+", "+specLineNumber;
			resultSet7 = prepStmt2.executeQuery();

			spAddTextList = new ArrayList();
			while(resultSet7.next()){
				String label = resultSet7.getString("LABEL");
				String value = resultSet7.getString("VALUE");

				if(label != null && !"".equals(label.trim()) && value != null && !"".equals(value.trim()) 
					&& ("Packaging Inst. #1".equalsIgnoreCase(label)|| "Packaging Inst. #2".equalsIgnoreCase(label)
					|| "Packaging Inst. #3".equalsIgnoreCase(label) || "Packaging Inst. #4".equalsIgnoreCase(label)
					|| "Packaging Inst. #5".equalsIgnoreCase(label) || "Copy Supplied".equalsIgnoreCase(label)
					|| "Press Type".equalsIgnoreCase(label) || "Unit Rate".equalsIgnoreCase(label)
					|| "Overage Print Qty".equalsIgnoreCase(label) || "Packaging".equalsIgnoreCase(label)
					|| "Rate Unit Descr".equalsIgnoreCase(label) || "Preparation".equalsIgnoreCase(label)
					|| "Assembly Rate".equalsIgnoreCase(label) || "Vendor Plant".equalsIgnoreCase(label)
					|| "Vendor 1".equalsIgnoreCase(label) || "Vendor 2".equalsIgnoreCase(label)
					|| "Vendor 3".equalsIgnoreCase(label) || "Single Source?".equalsIgnoreCase(label)
					|| "Show on PO".equalsIgnoreCase(label)	|| "Additional Spec 1".equalsIgnoreCase(label)
					|| "Additional Spec 2".equalsIgnoreCase(label) || "Additional Spec 3".equalsIgnoreCase(label)
					|| "Assembly Instruct 1".equalsIgnoreCase(label) || "Assembly Instruct 2".equalsIgnoreCase(label))){

						//Ashish:03/15/2015-CP#495575-Book Spec, PO and SI XML not transmitted via XBITS for ISBN# 0205874401 :Start
						/*spAddText = new SPAddText();
						spAddText.setSpAddText(label+":"+value);
						spAddTextList.add(spAddText);*/
						
						int valLength = value.length()+ label.length();
						if(valLength > 250){
							ArrayList ValueList = new ArrayList();
							B2BHelper b2bHelpr = new B2BHelper();
							ValueList = b2bHelpr.breakStringInSubstrings(label+":"+value);
							for (int i=0; i<ValueList.size(); i++){
								spAddText = new SPAddText();
								spAddText.setSpAddText((String)ValueList.get(i));
								spAddTextList.add(spAddText);
							}
						}
						else{
							spAddText = new SPAddText();
							spAddText.setSpAddText(label+":"+value);
							spAddTextList.add(spAddText);
						}
						//Ashish:03/15/2015-CP#495575-Book Spec, PO and SI XML not transmitted via XBITS for ISBN# 0205874401 :End
						
					}
				}

			if(prodIdVal != null && !"".equals(prodIdVal.trim())){
				pIdSpecPackg = new ProdIdSpecPacking();
				pIdSpecPackg.setPiType("PartNumber");
				pIdSpecPackg.setAgency("Buyer");
				pIdSpecPackg.setPiVal(prodIdVal.trim());
				specPacking.setPIdSpecPackg(pIdSpecPackg);
				}
			if(prodDesp != null && !"".equals(prodDesp.trim())){
				spProdDesp = new SPProdDesp();
				spProdDesp.setLang("eng");
				spProdDesp.setPdVal(prodDesp.trim());
				spProdDespList = new ArrayList();
				spProdDespList.add(spProdDesp);
				specPacking.setSpProdDespList(spProdDespList);
			}
			
			sqlQuery = qry_sel_value;
			prepStmt12 = dbCon.prepareStatement(sqlQuery);
			prepStmt12.clearParameters();
			prepStmt12.setString(IPixB2BConstants.ONE, "Shrinkwrap");
			prepStmt12.setLong(IPixB2BConstants.TWO, specId);
			prepStmt12.setLong(IPixB2BConstants.THREE, specVersion);
			prepStmt12.setLong(IPixB2BConstants.FOUR, specId);
			prepStmt12.setLong(IPixB2BConstants.FIVE, specVersion);
			prepStmt12.setString(IPixB2BConstants.SIX, "Binding");
			qryParams = "Shrinkwrap"+", "+specId+", "+specVersion+", "+specId+", "+specVersion+", "+"Binding";
			resultSet12 = prepStmt12.executeQuery();
			
			while(resultSet12.next()){
				shrinkWrapVal = resultSet12.getString("VALUE");
			}
			if(null!=shrinkWrapVal && !"".equals(shrinkWrapVal)){
				bupWrap = new BUPWrap();
				bupWrap.setWrapType("ShrinkWrap");
				bupWrapList = new ArrayList();
				bupWrapList.add(bupWrap);
				bupWrapChars = new BUPWrapChars();
				bupWrapChars.setBupWrapList(bupWrapList);
				bupWrapChars.setPackPerWrap("1");
				buPacking = new BUPacking();
				buPacking.setBupWrapChars(bupWrapChars);
				buPackingList = new ArrayList();
				buPackingList.add(buPacking);
				specPacking.setBuPackingList(buPackingList);
			}
			
			if(null!=spAddTextList && spAddTextList.size()>0){
				specPacking.setSpAddTextList(spAddTextList);
			}
			
			B2BLogger.debug("BookSpecificationDAOImpl.setSpecPacking method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_book_spec_misc+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			specPacking = null;
		}finally{
			DBUtils.close(resultSet7);
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet12);
			DBUtils.close(prepStmt12);
			
			spProdDespList	= null;
			spProdDesp		= null;
			spAddTextList	= null;
			spAddText		= null;
			sqlQuery		= null;
			qryParams		= null;
			prepStmt2		= null;
			resultSet7		= null;
		}
		if(pIdSpecPackg!=null)
			return specPacking;
		else
			return null;
	}
}