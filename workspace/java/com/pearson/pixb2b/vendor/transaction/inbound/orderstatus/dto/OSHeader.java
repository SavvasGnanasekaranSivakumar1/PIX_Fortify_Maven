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
 * Title		: 	OSHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

import java.util.ArrayList;


/**
 * OSHeader is a data transfer object to store the 
 * Order Status Header details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OSHeader implements java.io.Serializable {
	private static final long serialVersionUID = 1070969156663512230L;
	
	private String osNumber					= null;
	private OSResponseDate osResponseDate	= null;
	private ArrayList osReferenceList		= null;
	/**
	 * Default constructor.
	 */
	public OSHeader() {
		super();
		osReferenceList = new ArrayList();
	}
	/**
	 * @return the osNumber
	 */
	public String getOsNumber() {
		return osNumber;
	}
	/**
	 * @param osNumber the osNumber to set
	 */
	public void setOsNumber(String osNumber) {
		this.osNumber = osNumber;
	}
	/**
	 * @return the osResponseDate
	 */
	public OSResponseDate getOsResponseDate() {
		return osResponseDate;
	}
	/**
	 * @param osResponseDate the osResponseDate to set
	 */
	public void setOsResponseDate(OSResponseDate osResponseDate) {
		this.osResponseDate = osResponseDate;
	}
	/**
	 * @return the osReferenceList
	 */
	public ArrayList getOsReferenceList() {
		return osReferenceList;
	}
	/**
	 * @param osReferenceList the osReferenceList to set
	 */
	public void setOsReferenceList(ArrayList osReferenceList) {
		this.osReferenceList = osReferenceList;
	}
}
