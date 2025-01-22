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
 * Title		: 	XmlReaderCastor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	09 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.castor.xml.XMLConfiguration;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.exolab.castor.xml.XMLContext;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
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
 * XmlReaderCastor class read the B2B Inbound transactions XML 
 * of XBITS standards using castor API.
 * 
 * @author Yogesh Tyagi
 */
public class XmlReaderCastor{
	/**
	 * This method read B2B transactions XML of XBITS standards using castor API.
	 * 
	 * @param mapping
	 * @param xmlFileNameWithDir
	 * @param inDirArchiveXmlInvalid
	 * @param vendorSAN
	 * @param inTransFactory
	 * @return PapiNetEnvelopeDTO
	 */
	public PapiNetEnvelopeDTO readXML(Mapping mapping, String xmlFileNameWithDir, String inDirArchiveXmlInvalid, String vendorSAN, InboundTransactionFactory inTransFactory) {
		Unmarshaller unmarshaller 	= null;
		FileReader fileReader		= null; 
		PapiNetEnvelopeDTO pneDTO	= null;		
		String errorStr				= null;
		String strFileNameWithExtn	= null;
		String strFileNameWithoutExtn = null;
		String[] arrStrFileName 	= null;
		B2BHelper b2bHelper			= null;
		FileUtils fileUtils			= null;
		TransStatusHelper transStatusHelper = null;
		TransactionStatusDTO transStatusDTO = null;
		ErrorDTO errorDTO			= null;
		ArrayList errorList			= null;
		IErrorDAO errorDAO 			= null;	
		XMLContext xmlContext		=null;
		try {
				xmlContext = new XMLContext();
				//to resolve castor bug for AIX http://jira.codehaus.org/browse/CASTOR-2411
				xmlContext.setProperty(XMLConfiguration.SERIALIZER_FACTORY, "org.exolab.castor.xml.XercesXMLSerializerFactory");
				unmarshaller = xmlContext.createUnmarshaller();
				unmarshaller.setClass(PapiNetEnvelopeDTO.class);
				//unmarshaller = new Unmarshaller(PapiNetEnvelopeDTO.class);
				unmarshaller.setMapping(mapping);
			 
				fileReader = new FileReader(xmlFileNameWithDir);
				pneDTO = (PapiNetEnvelopeDTO) unmarshaller.unmarshal(fileReader);
			 
				fileReader.close();
		} catch (MarshalException e) {
			B2BLogger.error("MarshalException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (ValidationException e) {
			B2BLogger.error("ValidationException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			errorStr = StringUtils.getStackTrace(e);
			pneDTO = null;
		} finally{
			unmarshaller= null;
			fileReader	= null; 
		}
		if(pneDTO == null){			
			transStatusHelper = new TransStatusHelper();			
			b2bHelper = new B2BHelper();
			fileUtils = new FileUtils();
			errorList = new ArrayList();
			
			fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlInvalid);
			B2BLogger.info("XmlReaderCastor.readXML() :: VENDOR XML "+xmlFileNameWithDir+" IS INVALID FOR vendorSAN = "+vendorSAN);
			
			strFileNameWithExtn = fileUtils.getFileName(xmlFileNameWithDir);
			strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
			arrStrFileName = strFileNameWithoutExtn.split("_");
			
			transStatusDTO = transStatusHelper.setTransactionStatus(IPixB2BConstants.processType_INBOUND, arrStrFileName[0], arrStrFileName[1],
					arrStrFileName[3], b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
					strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
					null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, IPixB2BConstants.status_KU,null,null);
			
			errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+IPixB2BConstants.ERROR_ID_71, strFileNameWithExtn, IPixB2BConstants.XML, 
					IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, 
					vendorSAN, IPixB2BConstants.VENDOR_SAN, inDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
					errorStr, IPixB2BConstants.flag_N, null, null);			
			errorList.add(errorDTO);
			
			errorDAO = inTransFactory.geErrorDAO();
			errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
		}
		return pneDTO;
	}
}