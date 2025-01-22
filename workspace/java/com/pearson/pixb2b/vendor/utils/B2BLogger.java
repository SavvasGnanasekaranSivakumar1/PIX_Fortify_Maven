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
 * Title		: 	AppLogger.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/**
 * AppLogger is a shallow cover of org.apache.log4j.Logger and 
 * used by the classes that are logging the messages at runtime.
 * 
 * @author Yogesh Tyagi
 */
public class B2BLogger {	
	private static Logger logger = Logger.getLogger("b2b");
	
	static {
        try {
        	PropertyConfigurator.configure(IConfigConstants.FILE_PATH+File.separatorChar+"log4j.properties");
        } catch (Exception ex) {
            B2BLogger.error("FAILED TO READ log4j.properties",ex);
        }
    };    
	
	/**
     * This method write in INFO messages.
     */
    public static void info(String logMessage) {
    	logger.info(logMessage); 
    }
    /**
     * This method write in ERROR messages.
     */
    public static void error(String logMessage) {
    	logger.error(logMessage); 
    }
    /**
     * 
     * @param logMessage
     * @param e
     */
    public static void error(String logMessage,Exception e) {
    	logger.error(logMessage,e); 
    }
    
    /**
     * This method write in DEBUG messages.
     */
    public static void debug(String logMessage) {
    	logger.debug(logMessage); 
    }
    /**
     * This method write in WARN messages.
     */
    public static void warn(String logMessage) {
    	logger.warn(logMessage); 
    }
}