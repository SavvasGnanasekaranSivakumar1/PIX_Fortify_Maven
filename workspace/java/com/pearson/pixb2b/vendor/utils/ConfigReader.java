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
 * Title		: 	ConfigReader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * ConfigReader is a utility class to read the config/properties files.
 * This class provides utility methods to read configuration 
 * properties (key/value pairs). This class is a one point of contact to 
 * read properties in the application. The config/properties file that 
 * needs to be read should be located in the path. The config/properties 
 * file will be read once and maintained in the System.
 * 
 * @author Yogesh Tyagi
 */
public class ConfigReader{
	private static String b2bConfigFile = null;
	private static Properties b2bProps 	= null;
	
	static {
    	try {
			b2bConfigFile = IConfigConstants.CONFIG_FILE;
			if (b2bConfigFile == null) {
				B2BLogger.info(b2bConfigFile+" environment variable not set.");
				System.err.println(b2bConfigFile+" environment variable not set.");    			
			}
		} catch (RuntimeException e) {
			B2BLogger.error("ConfigReader : RuntimeException - FAILED TO READ b2b.config");
            B2BLogger.error("Exception",e);
		}
    };
    /**
	 * This method returns single property value from config file
	 * @param propertyKey property key
	 * @return String property value
	 */
	public static String getProperty(String propertyKey){
		String propertyValue = null;		
		try {
			if(propertyKey != null && !"".equals(propertyKey.trim())){
				if(b2bProps == null)
					loadB2BProps();
				propertyValue = b2bProps.getProperty(propertyKey);
				if(propertyValue != null)
					propertyValue = propertyValue.trim();
				else
					B2BLogger.info("ConfigReader.getProperty() - b2 property "+propertyKey+" value = "+propertyValue);
			}else{
				B2BLogger.info("ConfigReader.getProperty() - propertyKey is null or blank");
			}
		} catch (RuntimeException e) {
			B2BLogger.error("ConfigReader.getProperty() - Error in reading config property '" + propertyKey+"'");
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			return null;
		}
		return propertyValue;
	}
    /**
     * This method loads b2b.config file into Properties and then close the file. 
     * @return Properties
     */
    private static Properties loadB2BProps() {
    	InputStream inStreamB2BProps = null;
        try {
			b2bProps = new Properties ();			
			inStreamB2BProps = new BufferedInputStream (new FileInputStream(b2bConfigFile));
			b2bProps.load(inStreamB2BProps);
			inStreamB2BProps.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("ConfigReader.loadB2BProps() : FileNotFoundException - Error in loading config file '"+b2bConfigFile+"'");
        	B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
            B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("ConfigReader.loadB2BProps() : IOException - Error in loading config file '"+b2bConfigFile+"'");
        	B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
            B2BLogger.error("Exception",e);
		} finally{
        	inStreamB2BProps = null;
        }
        return b2bProps;
    }
}