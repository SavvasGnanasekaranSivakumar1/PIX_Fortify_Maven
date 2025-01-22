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
 * Title		: 	StringUtils.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * StringUtils is a utility class that provides the utility
 * methods for String handling.
 * 
 * @author Yogesh Tyagi
 */
public class StringUtils {
	private static final String REG_EXP = "[^a-zA-Z]";
	private static final String REG_EXP_SPACES = "\\b\\s{2,}\\b";
	
	/**
	 * This method returns stack trace as a string
	 * @param ex exception/error string
	 * @return String
	 */
	public static String getStackTrace(Throwable ex) {
	    if (ex == null) 
	      return null;
	    
	    StringWriter strWriter = new StringWriter();
	    ex.printStackTrace(new PrintWriter(strWriter));
	    return strWriter.toString();
	}
    /**
     * This method strips out the spaces & special characters from a string 
     * @param inStr input string
     * @return String
     */
    public static String cleanStr(String inStr){
    	if(inStr == null || "".equals(inStr))
             return inStr;
    	else
        	return (inStr.replaceAll(REG_EXP,"_"));
    }
	/**
	 * This method remove blank spaces from a string
	 * @param inputStr input string
	 * @return String
	 */
	public static String removeBlankSpaces(String inputStr){
		String outputStr = "";
		if(inputStr != null)
			outputStr = inputStr.replaceAll(" ","");
		return outputStr.trim();
	}
    /**
     * This method convert the input string to Title Case string.
     * @param inStr input string
     * @return String
     */
    public static String strTitleCase(String inStr) {
    	String outStr = "";
		if(inStr != null){
	    	char[] chars = inStr.trim().toLowerCase().toCharArray();
			boolean found = false;	 
			for (int i=0; i<chars.length; i++) {
				if(!found && Character.isLetter(chars[i])) {
					chars[i] = Character.toUpperCase(chars[i]);
					found = true;
				}else if (Character.isWhitespace(chars[i])) {
					found = false;
				}
			}	 
			outStr = String.valueOf(chars);
		}
		return outStr;
	}
    /**
     * This method replace multiple spaces between words of strings into single space.
     * @param inString
     * @return String
     */
    public String replaceMultipleSpaces(String inString) {
    	String outString = null;
    	outString = inString.replaceAll(REG_EXP_SPACES, " ");
    	return outString;
    }
    
    
    /** Left Padding function  */
    /**
     * This method addes left padding to make string of length 10.
     * @param xmlStr
     * @param maxLen
     * @param padding
     * @return String
     */
    public static String leftPad(String xmlStr,int maxLen,String padding){  	
    	return org.apache.commons.lang.StringUtils.leftPad(xmlStr,maxLen,padding);
    }
    
}