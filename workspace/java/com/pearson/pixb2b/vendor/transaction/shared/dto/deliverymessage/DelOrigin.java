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
 * Title		: 	DelOrigin.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * DelOrigin is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Origin details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelOrigin implements java.io.Serializable {
	private static final long serialVersionUID = -6198535625865940260L;
	
	private DelOriginDate delOriginDate = null;
	private LocationParty locationParty = null;
	/**
	 * Default constructor.
	 */
	public DelOrigin() {
		super();
	}
	/**
	 * @return the delOriginDate
	 */
	public DelOriginDate getDelOriginDate() {
		return delOriginDate;
	}
	/**
	 * @param delOriginDate the delOriginDate to set
	 */
	public void setDelOriginDate(DelOriginDate delOriginDate) {
		this.delOriginDate = delOriginDate;
	}
	/**
	 * @return the locationParty
	 */
	public LocationParty getLocationParty() {
		return locationParty;
	}
	/**
	 * @param locationParty the locationParty to set
	 */
	public void setLocationParty(LocationParty locationParty) {
		this.locationParty = locationParty;
	}
}