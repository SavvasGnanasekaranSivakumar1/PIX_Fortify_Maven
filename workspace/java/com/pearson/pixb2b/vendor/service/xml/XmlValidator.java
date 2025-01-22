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
 * Title		: 	XmlValidator.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * XmlValidator class validate the multiple XML 
 * with the supplied schema (XSD).
 * 
 * @author Yogesh Tyagi
 */
public class XmlValidator{	
	private static final String JAXP_SCHEMA_LANGUAGE= "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	private static final String W3C_XML_SCHEMA 		= "http://www.w3.org/2001/XMLSchema";
	private static final String JAXP_SCHEMA_SOURCE 	= "http://java.sun.com/xml/jaxp/properties/schemaSource";
	
	private String xmlFileName	= null;
	private boolean inValidXml 	= false;	
	private String errorStr 	= null;
	private String errorXml 	= null;
	private ArrayList errorList = null;
		
	class XmlHandler extends DefaultHandler {		
		private boolean printError 	= false;				
	    
	    /* (non-Javadoc)
	     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	     */
	    public void startElement(String namespaceURI, String localName, String tagName, Attributes attributes){   
	    	if (printError){
	    		errorXml = xmlFileName;
	    		inValidXml = true;
	    		errorList.add(errorStr);
	    		printError	= false;    		
	    	}   	
	    	/* control will transefer to characters()*/
	    }
	    /* (non-Javadoc)
	     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	     */
	    public void endElement(String namespaceURI, String localName, String tagName) {
	    	//B2BLogger.debug("XmlValidator.endElement() -  localName = "+localName);    	
	    }
	    /* (non-Javadoc)
	     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	     */
	    public void characters(char[] ch, int start, int length){ 
	    	//tagValue = new String(ch, start, length);
	    	//B2BLogger.debug("XmlValidator.characters() - tagValue = "+tagValue);
	    }    
	    //Recoverable error
	    /* (non-Javadoc)
	     * @see org.xml.sax.ErrorHandler#error(org.xml.sax.SAXParseException)
	     */
	    public void error(SAXParseException spe) {
	    	printError	= true;
	    	errorStr = "Error at line " +spe.getLineNumber()+" : "+spe.toString();
	    	B2BLogger.error("XmlValidator.error() - ERROR = "+errorStr);
	    	/*Will go to startElement() now*/
	    }
	    //Non Recoverable error
	    /* (non-Javadoc)
	     * @see org.xml.sax.ErrorHandler#fatalError(org.xml.sax.SAXParseException)
	     */
	    public void fatalError(SAXParseException spe) throws SAXException{
	    	errorStr = "Fatal Error at line " +spe.getLineNumber()+" : "+spe.toString();
	    	B2BLogger.error("XmlValidator.XmlHandler.fatalError() - FATAL ERROR = "+errorStr);
	    	//Will Exit now
	    }
	    /* (non-Javadoc)
	     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
	     */
	    public void skippedEntity(String name){
	    	//B2BLogger.debug("XmlValidator.skippedEntity() - Skipped Entity : "+name);
	    }
	}//End XmlHandler inner class.
	
	public String getErrorStr(String xmlType, String xmlFileName, Exception e){		
		B2BLogger.info("XmlValidator.getErrorStr() - xml validation falied of "+xmlType+" XML "+xmlFileName);
		
		String errorStr = StringUtils.getStackTrace(e);
		if(errorStr != null && errorStr.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
			errorStr = errorStr.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
				
		return errorStr;
	}	
	/**
	 * This method validates the XML with given schema(XSD)
	 * @param xmlType
	 * @param schemaFileName
	 * @param xmlFileName
	 * @return HashMap
	 */
	public HashMap validateXmlWithSchema(String xmlType, String schemaFileName, String xmlFileName, String vendorSAN, String vendorName){
		SAXParserFactory saxParserFactory = null;
		SAXParser saxParser		= null;
		File schemaFile 		= null;
		HashMap hmXmlWithError 	= null;
		
		try {
			this.xmlFileName = xmlFileName;
			this.inValidXml = false;
			this.errorList = new ArrayList();
			hmXmlWithError = new HashMap();
			
			saxParserFactory = SAXParserFactory.newInstance();
			saxParserFactory.setNamespaceAware(true);
			saxParserFactory.setValidating(true);			
			
			saxParser = saxParserFactory.newSAXParser();
			schemaFile = new File(schemaFileName);
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			saxParser.setProperty(JAXP_SCHEMA_SOURCE, schemaFile);
			saxParser.parse(xmlFileName, new XmlHandler()); // pass the control to startElement()
			
			if(inValidXml){
				hmXmlWithError.put(errorXml, errorList);
				B2BLogger.info("XmlValidator.validateXmlWithSchema() - "+xmlType+" XML "+errorXml+" VALIDATION FAILED FOR vendorSAN = "+vendorSAN+" & vendorName = "+vendorName);
			}
		} catch (SAXNotRecognizedException e) {
			errorList.add(getErrorStr(xmlType, xmlFileName, e));
			hmXmlWithError.put(xmlFileName, errorList);			
			B2BLogger.error("SAXNotRecognizedException :: " + StringUtils.getStackTrace(e));			
		} catch (SAXNotSupportedException e) {
			errorList.add(getErrorStr(xmlType, xmlFileName, e));
			hmXmlWithError.put(xmlFileName, errorList);	
			B2BLogger.error("SAXNotSupportedException :: " + StringUtils.getStackTrace(e));			
		} catch (FactoryConfigurationError e) {
			errorStr = StringUtils.getStackTrace(e);
			if(errorStr != null && errorStr.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				errorStr = errorStr.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);			
			errorList.add(errorStr);
			hmXmlWithError.put(xmlFileName, errorList);			
			B2BLogger.info("XmlValidator.validateXmlWithSchema() - "+xmlType+" XML "+xmlFileName+" VALIDATION FAILED FOR vendorSAN = "+vendorSAN+" & vendorName = "+vendorName);
			B2BLogger.error("FactoryConfigurationError :: " + StringUtils.getStackTrace(e));			
		} catch (ParserConfigurationException e) {
			errorList.add(getErrorStr(xmlType, xmlFileName, e));
			hmXmlWithError.put(xmlFileName, errorList);	
			B2BLogger.error("ParserConfigurationException :: " + StringUtils.getStackTrace(e));			
		} catch (SAXException e) {
			errorList.add(getErrorStr(xmlType, xmlFileName, e));
			hmXmlWithError.put(xmlFileName, errorList);	
			B2BLogger.error("SAXException :: " + StringUtils.getStackTrace(e));			
		} catch (IOException e) {
			errorList.add(getErrorStr(xmlType, xmlFileName, e));
			hmXmlWithError.put(xmlFileName, errorList);	
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));			
		} finally{
			saxParserFactory = null;
			saxParser	= null;
			schemaFile 	= null;
		}
		return hmXmlWithError;
	}
	/**
	 * This method validates multiple XML with given schema(XSD)
	 * @param transactionName
	 * @param xmlFileList
	 * @param schemaFileName
	 * @return HashMap
	 */
	public HashMap validateXml(ArrayList xmlFileList, String transactionName, String schemaFileName, String vendorSAN, String vendorName){
		ArrayList validXmlList		= null;
		ArrayList inValidXmlList	= null;
		XmlValidator xmlValidator 	= null;
		HashMap hmValidatedXmlList	= null;
		HashMap hmXmlWithError 		= null;
		
		try {
			B2BLogger.info("XmlValidator.validateXml() - VALIDATING "+transactionName+" XML WITH SCHEMA(XSD) "+schemaFileName+" FOR vendorSAN = "+vendorSAN+" & vendorName = "+vendorName);
			if(xmlFileList != null && xmlFileList.size()>0){
				validXmlList = new ArrayList();
				inValidXmlList = new ArrayList();			
				xmlValidator = new XmlValidator();
				hmValidatedXmlList = new HashMap();
				
				for(int i=0; i < xmlFileList.size(); i++){					
					xmlFileName = (String)xmlFileList.get(i);
					hmXmlWithError = xmlValidator.validateXmlWithSchema(transactionName, schemaFileName, xmlFileName, vendorSAN, vendorName);					
					if(hmXmlWithError != null && hmXmlWithError.containsKey(xmlFileName)){
						inValidXmlList.add(hmXmlWithError);
					}else{
						validXmlList.add(xmlFileName);
					}
				}
				
				if(validXmlList != null && validXmlList.size()>0)
					hmValidatedXmlList.put(IPixB2BConstants.VALID_XML_LIST, validXmlList);
				if(inValidXmlList != null && inValidXmlList.size()>0)
					hmValidatedXmlList.put(IPixB2BConstants.INVALID_XML_LIST, inValidXmlList);
			}
			B2BLogger.info("XmlValidator.validateXml() - FINISHED "+transactionName+" XML VALIDATION FOR vendorSAN = "+vendorSAN+" & vendorName = "+vendorName);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: ",e);
		} finally{
			validXmlList	= null;
			inValidXmlList	= null;
			xmlValidator	= null;
			hmXmlWithError	= null;			
			xmlFileName		= null;
			errorStr 		= null;
			errorXml 		= null;
			errorList 		= null;
		}
		return hmValidatedXmlList;
	}
}
