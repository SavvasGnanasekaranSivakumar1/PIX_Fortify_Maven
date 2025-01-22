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
 * Title		: 	XmlReaderSax.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	09 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.xml;

import java.io.CharArrayWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRRef;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.PartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISReference;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceRef;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCReference;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OSReference;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDetail;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.EndPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesRef;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.SupplierPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Document;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.Payload;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PayloadInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.transaction.shared.helper.TransStatusHelper;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * XmlReaderSax class use the SAX parser to parse and read the XML 
 * received from B2B Vendor. This class returns the parsed XML 
 * data to the client(caller). 
 * 
 * @author Yogesh Tyagi
 */
public class XmlReaderSax {
	private PapiNetEnvelopeDTO pneDTO	= null;
	
	class B2BXmlHandler extends DefaultHandler {
		private Stack tagStack = new Stack();
		private CharArrayWriter contents = new CharArrayWriter();
		
		private PayloadInfo payloadInfo = null;
		String documentName				= null;
		String ocReferenceType			= null;
		String ocPartyIdenType			= null;
		String osPartyIdenType			= null;
		String osReferenceType			= null;
		String usageReferenceType		= null;
		String invoReferenceType		= null;
		String grReferenceType			= null;
		String icReferenceType			= null;
		String isReferenceType			= null;
		String dmReferenceType			= null;
		String dmwReferenceType			= null;
		private Document document 		= null;
		
		private Payload payload			= null;
		private OrderStatusDetail osdetail		= null;
		private SupplierPartyPartyIdentifier spPartyIdentifier	= null;
		private EndPartyIdentifier epPartyIdentifier = null;		
		private OCReference ocReference			= null;
		private OSReference osReference			= null;
		private UsageReference usageReference	= null;
		private InvoiceRef invoiceRef			= null;
		private GRRef grRef						= null;
		private ICReference icRef				= null;
		private ISReference isRef				= null;
		private DelMesRef dmRef					= null;
		private DelMesRef dmRefWood				= null;
		private PartyIdentifier partyIdentifier = null;
		private SupplierPartyId supplierPartyId = null;
		private SupplierPartyId supPartyIdWood	= null;
		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
		 */
		public void startElement(String namespaceURI, String localName, String tagName, Attributes attributes) throws SAXException {
			contents.reset();
			tagStack.push(tagName);
			String tagPath = getTagPath();			
			
			if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_papiNetEnvelope)){
				document = new Document();
				payloadInfo = new PayloadInfo();
				pneDTO = new PapiNetEnvelopeDTO();
				payload = new Payload();
				osdetail = new OrderStatusDetail();
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_Document)){
				if(attributes.getIndex(IPixB2BConstants.attr_DocumentName) != -1){
					documentName = attributes.getValue(IPixB2BConstants.attr_DocumentName);
					document.setDocumentName(documentName);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OS_Trans_Id)){
				osReference = new OSReference();
				if(attributes.getIndex(IPixB2BConstants.attr_OSReferenceType) != -1){
					osReferenceType = attributes.getValue(IPixB2BConstants.attr_OSReferenceType);
					osReference.setOsReferenceType(osReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OC_Trans_Id)){
				ocReference = new OCReference();
				if(attributes.getIndex(IPixB2BConstants.attr_OCReferenceType) != -1){
					ocReferenceType = attributes.getValue(IPixB2BConstants.attr_OCReferenceType);
					ocReference.setOcReferenceType(ocReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OC_Supp_PartyIdentifier)){
				spPartyIdentifier = new SupplierPartyPartyIdentifier();
				if(attributes.getIndex(IPixB2BConstants.attr_OCPartyIdentifierType) != -1){
					ocPartyIdenType = attributes.getValue(IPixB2BConstants.attr_OCPartyIdentifierType);
					spPartyIdentifier.setPartyIdentifierType(ocPartyIdenType);
				}
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OS_Supp_PartyIdentifier)){
				spPartyIdentifier = new SupplierPartyPartyIdentifier();
				if(attributes.getIndex(IPixB2BConstants.attr_OSPartyIdentifierType) != -1){
					osPartyIdenType = attributes.getValue(IPixB2BConstants.attr_OSPartyIdentifierType);
					spPartyIdentifier.setPartyIdentifierType(osPartyIdenType);
				}
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_USG_Trans_Id)){
				usageReference = new UsageReference();
				if(attributes.getIndex(IPixB2BConstants.attr_UsageReferenceType) != -1){
					usageReferenceType = attributes.getValue(IPixB2BConstants.attr_UsageReferenceType);
					usageReference.setUsageRefType(usageReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_INVO_Trans_Id)){
				invoiceRef = new InvoiceRef();
				if(attributes.getIndex(IPixB2BConstants.attr_InvoReferenceType) != -1){
					invoReferenceType = attributes.getValue(IPixB2BConstants.attr_InvoReferenceType);
					invoiceRef.setInvRefType(invoReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_GR_Trans_Id)){
				grRef = new GRRef();
				if(attributes.getIndex(IPixB2BConstants.attr_GRReferenceType) != -1){
					grReferenceType = attributes.getValue(IPixB2BConstants.attr_GRReferenceType);
					grRef.setGrRefType(grReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_IA_Trans_Id)){
				icRef = new ICReference();
				if(attributes.getIndex(IPixB2BConstants.attr_IAReferenceType) != -1){
					icReferenceType = attributes.getValue(IPixB2BConstants.attr_IAReferenceType);
					icRef.setIcReferenceType(icReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_IS_Trans_Id)){
				isRef = new ISReference();
				if(attributes.getIndex(IPixB2BConstants.attr_ISReferenceType) != -1){
					isReferenceType = attributes.getValue(IPixB2BConstants.attr_ISReferenceType);
					isRef.setIsReferenceType(isReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DM_Trans_Id)){
				dmRef = new DelMesRef();
				if(attributes.getIndex(IPixB2BConstants.attr_DMReferenceType) != -1){
					dmReferenceType = attributes.getValue(IPixB2BConstants.attr_DMReferenceType);
					dmRef.setDelMesRefType(dmReferenceType);
				}			
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DMW_Trans_Id)){
				dmRefWood = new DelMesRef();
				if(attributes.getIndex(IPixB2BConstants.attr_DMWReferenceType) != -1){
					dmwReferenceType = attributes.getValue(IPixB2BConstants.attr_DMWReferenceType);
					dmRefWood.setDelMesRefType(dmwReferenceType);
				}			
			}
		}
		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
		 */
		public void endElement(String namespaceURI, String localName, String tagName ) throws SAXException {
			String tagPath = getTagPath();
			
			if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DocumentNumber)){
				document.setDocumentNumber(contents.toString().trim());
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_papiNetEnvelope)){
				payloadInfo.setDocument(document);
				pneDTO.setPayloadInfo(payloadInfo);
				pneDTO.setPayload(payload);
			}else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_Year)){				
				document.getDocumentDate().getDate().setYear(contents.toString().trim());				
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_Month)){				
				document.getDocumentDate().getDate().setMonth(contents.toString().trim());				
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_Day)){				
				document.getDocumentDate().getDate().setDay(contents.toString().trim());				
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OS_Supp_PartyIdentifier)){
					//spPartyIdentifier = new SupplierPartyPartyIdentifier();
					spPartyIdentifier.setPartyIdentifierValue(contents.toString().trim());
					//osdetail = new OrderStatusDetail();
					osdetail.getSupplierParty().getPartyIdentifierList().add(spPartyIdentifier);
					payload.getBusinessDocument().getOsDTO().getOsDetailList().add(osdetail);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OC_Supp_PartyIdentifier)){
				spPartyIdentifier.setPartyIdentifierValue(contents.toString().trim());
				payload.getBusinessDocument().getOcDTO().getOcHeader().getOcSupplier().getPartyIdentifier().add(spPartyIdentifier);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_USG_Supp_PartyIdentifier)){
				epPartyIdentifier = new EndPartyIdentifier();
				payload.getBusinessDocument().getUsgDTO().getUsageHeader().getUsageEndUser().getPartyIdentifier().setPartyIdentifierValue(contents.toString().trim());
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_INVO_Supp_PartyIdentifier)){
				spPartyIdentifier = new SupplierPartyPartyIdentifier();
				spPartyIdentifier.setPartyIdentifierValue(contents.toString().trim());
				payload.getBusinessDocument().getInvoDTO().getInvHeader().getSuppParty().getSuppPartyIdList().add(spPartyIdentifier);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_GR_Supp_PartyIdentifier)){
				partyIdentifier = new PartyIdentifier();
				payload.getBusinessDocument().getGrDTO().getGrHeader().getShipToChar().getShipToParty().getPartyId().setPartyIdentifierValue(contents.toString().trim());
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_IA_Supp_PartyIdentifier)){
				payload.getBusinessDocument().getIcDTO().getIcHeader().getLocationParty().getPartyIdentifier().setPartyIdentifierValue(contents.toString().trim());
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_IS_Supp_PartyIdentifier)){
				payload.getBusinessDocument().getIsDTO().getIsHeader().getLocationParty().getPartyIdentifier().setPartyIdentifierValue(contents.toString().trim());
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DM_Supp_PartyIdentifier)){
				supplierPartyId = new SupplierPartyId();
				supplierPartyId.setPiValue(contents.toString().trim());
				payload.getBusinessDocument().getDmDTO().getDelMesBookHd().getSupParty().getSupplierPartyIdList().add(supplierPartyId);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DMW_Supp_PartyIdentifier)){
				supPartyIdWood = new SupplierPartyId();
				supPartyIdWood.setPiValue(contents.toString().trim());
				payload.getBusinessDocument().getDmwDTO().getDelMesWoodHd().getSupParty().getSupplierPartyIdList().add(supPartyIdWood);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OS_Trans_Id)){
				osReference.setOsReferenceValue(contents.toString().trim());
				payload.getBusinessDocument().getOsDTO().getOsHeader().getOsReferenceList().add(osReference);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_OC_Trans_Id)){
				ocReference.setOcReferenceValue(contents.toString().trim());
				payload.getBusinessDocument().getOcDTO().getOcHeader().getOcReference().add(ocReference);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_USG_Trans_Id)){
				usageReference.setUsageRefValue(contents.toString().trim());
				payload.getBusinessDocument().getUsgDTO().getUsageHeader().getUsageRef().add(usageReference);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_INVO_Trans_Id)){
				invoiceRef.setInvRefValue(contents.toString().trim());
				payload.getBusinessDocument().getInvoDTO().getInvHeader().getInvRef().add(invoiceRef);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_GR_Trans_Id)){
				grRef.setGrRefValue(contents.toString().trim());
				payload.getBusinessDocument().getGrDTO().getGrHeader().getGrRef().add(grRef);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_IA_Trans_Id)){
				icRef.setIcReferenceValue(contents.toString().trim());
				payload.getBusinessDocument().getIcDTO().getIcHeader().getIcRef().add(icRef);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_IS_Trans_Id)){
				isRef.setIsReferenceValue(contents.toString().trim());
				payload.getBusinessDocument().getIsDTO().getIsHeader().getIsRef().add(isRef);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DM_Trans_Id)){
				dmRef.setDelMesRefVal(contents.toString().trim());
				payload.getBusinessDocument().getDmDTO().getDelMesBookHd().getDelMesRefList().add(dmRef);
			}
			else if(tagPath.equalsIgnoreCase(IPixB2BConstants.tag_DMW_Trans_Id)){
				dmRefWood.setDelMesRefVal(contents.toString().trim());
				payload.getBusinessDocument().getDmwDTO().getDelMesWoodHd().getDelMesRefList().add(dmRefWood);
			}
			tagStack.pop();			
		}
		/* (non-Javadoc)
		 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
		 */
		public void characters(char[] ch, int start, int length){
			contents.write(ch, start, length );
		}
		/**
		 * This method returns the tag path
		 * @return String
		 */
		private String getTagPath(){
		    String buffer = "";
		    Enumeration e = tagStack.elements();
		    while(e.hasMoreElements())		    	
		    	buffer  = buffer+"/"+(String)e.nextElement();
		    return buffer;
		}
	}//End B2BXmlHandler inner class.
	
	/**
	 * This method is called by the client to start the parsing of the XML passed as a string.
	 * This method sets the parser type and then call the startElement method of inner class
	 * and do the complete parsing and return the parsed XML data after parsing the complete XML.
	 * 
	 * @param xmlString
	 * @return String
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public PapiNetEnvelopeDTO parseXML(String xmlString, String xmlFileNameWithDir, String inDirArchiveXmlInvalid, String vendorSAN, InboundTransactionFactory inTransFactory) {		
		SAXParserFactory saxParserFactory = null;
		SAXParser saxParser 	= null;
		InputSource inputSource = null;
		String errorStr			= null;
		String strFileNameWithExtn	= null;
		String strFileNameWithoutExtn = null;
		String[] arrStrFileName = null;
		B2BHelper b2bHelper		= null;
		FileUtils fileUtils		= null;
		TransStatusHelper transStatusHelper = null;
		TransactionStatusDTO transStatusDTO = null;
		ErrorDTO errorDTO		= null;
		ArrayList errorList		= null;
		IErrorDAO errorDAO 		= null;
		
		try {
			B2BLogger.debug("XmlReaderSax.parseXML() method called");			
			saxParserFactory = SAXParserFactory.newInstance();
			saxParser = saxParserFactory.newSAXParser();
			inputSource = new InputSource(new StringReader(xmlString));
			saxParser.parse(inputSource, new B2BXmlHandler()); //pass the control to startElement()
			B2BLogger.debug("XmlReaderSax.parseXML() method return");
		} catch (FactoryConfigurationError e) {
			B2BLogger.error("FactoryConfigurationError :: "+StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (ParserConfigurationException e) {
			B2BLogger.error("ParserConfigurationException :: "+StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (SAXException e) {
			B2BLogger.error("SAXException :: "+StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (IOException e) {
			B2BLogger.error("IOException :: "+StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} finally{
			saxParserFactory = null;
			saxParser = null;
			inputSource = null;
		}
		if(pneDTO == null){
			transStatusHelper = new TransStatusHelper();			
			b2bHelper = new B2BHelper();
			fileUtils = new FileUtils();
			errorList = new ArrayList();
			
			fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlInvalid);
			B2BLogger.info("XmlReaderSax.parseXML() :: VENDOR XML "+xmlFileNameWithDir+" IS INVALID FOR vendorSAN = "+vendorSAN);
			
			strFileNameWithExtn = fileUtils.getFileName(xmlFileNameWithDir);
			strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
			arrStrFileName = strFileNameWithoutExtn.split("_");
				
			transStatusDTO = transStatusHelper.setTransactionStatus(IPixB2BConstants.processType_INBOUND, arrStrFileName[0], arrStrFileName[1],
					arrStrFileName[3], b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
					strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
					null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, IPixB2BConstants.status_KU,arrStrFileName[3],null);
			
			/*errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+ERROR_ID_71, strFileNameWithExtn, XML, 
					B2B, VEN_TO_PIX, 
					vendorSAN, VENDOR_SAN, inDirArchiveXmlInvalid, FILE_PATH,
					errorStr, flag_N, null, null);*/
			errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+IPixB2BConstants.ERROR_ID_71, strFileNameWithExtn, IPixB2BConstants.XML, 
					IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, 
					arrStrFileName[3], IPixB2BConstants.TRANS_ID, inDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
					errorStr, IPixB2BConstants.flag_N, null, null);
			errorList.add(errorDTO);
			
			errorDAO = inTransFactory.geErrorDAO();
			errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
		}
		return pneDTO;		
	}	
}