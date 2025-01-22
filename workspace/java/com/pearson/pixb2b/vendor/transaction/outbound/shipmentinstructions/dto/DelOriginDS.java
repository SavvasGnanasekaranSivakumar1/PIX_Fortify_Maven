/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	DelOriginDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		20 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;


/**
 * DelOrigin is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Origin details and transfer the same between classes.
 * 
 * @author Ranu Sharma
 * 
 *  */
public class DelOriginDS implements java.io.Serializable {
	private static final long serialVersionUID = -6198535625865940260L;
	
	private DelOriginDateDS delOriginDate = null;
	private String delOriginTime = null;
	private LocationPartyDS locationParty = null;
	/**
	 * Default constructor.
	 */
	public DelOriginDS() {
		super();
	}
	/**
	 * @return the delOriginDate
	 */
	public DelOriginDateDS getDelOriginDate() {
		return delOriginDate;
	}
	/**
	 * @param delOriginDate the delOriginDate to set
	 */
	public void setDelOriginDate(DelOriginDateDS delOriginDate) {
		this.delOriginDate = delOriginDate;
	}
	/**
	 * @return the locationParty
	 */
	public LocationPartyDS getLocationParty() {
		return locationParty;
	}
	/**
	 * @param locationParty the locationParty to set
	 */
	public void setLocationParty(LocationPartyDS locationParty) {
		this.locationParty = locationParty;
	}
	/**
	 * @return the delOriginTime
	 */
	public String getDelOriginTime() {
		return delOriginTime;
	}
	/**
	 * @param delOriginTime the delOriginTime to set
	 */
	public void setDelOriginTime(String delOriginTime) {
		this.delOriginTime = delOriginTime;
	}
}