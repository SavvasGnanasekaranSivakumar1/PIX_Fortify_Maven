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
 * Title		: 	AppException.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;
/**
 * AppException is a shallow cover of java.lang.Exception and 
 * thrown by the classes that are used for application if exception
 * occurs at runtime.
 * 
 * @author Yogesh Tyagi
 */
public class B2BException extends Exception{
	private static final long serialVersionUID = -5454647840945209236L;
	/**
     * Default Constructor.
     */
    public B2BException() {
    	super();
    }
    /**
     * @param message the message for the exception.
     */
    public B2BException(String message) {
        super(message);
    }
    
    public B2BException(String message,Exception e)
    {
    	super(message,e);
    }
}
