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
 * Title		: 	LocationParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

/**
 * LocationParty is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Origin LocationParty details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class LocationParty implements java.io.Serializable {
	private static final long serialVersionUID = 9181061813048440174L;
	
	private String partyType					= null;
	private String logisticsRole				= null;
	private String locationType					= null;
	private ArrayList locationPartyIdList		= null;
	private LocationPartyNmAdd locPartyNmAdd	= null;
	private ArrayList locationPartyCCList 		= null;
	/**
	 * Default constructor.
	 */
	public LocationParty() {
		super();
	}
	/**
	 * @return the partyType
	 */
	public String getPartyType() {
		return partyType;
	}
	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	/**
	 * @return the logisticsRole
	 */
	public String getLogisticsRole() {
		return logisticsRole;
	}
	/**
	 * @param logisticsRole the logisticsRole to set
	 */
	public void setLogisticsRole(String logisticsRole) {
		this.logisticsRole = logisticsRole;
	}
	/**
	 * @return the locationType
	 */
	public String getLocationType() {
		return locationType;
	}
	/**
	 * @param locationType the locationType to set
	 */
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	/**
	 * @return the locationPartyIdList
	 */
	public ArrayList getLocationPartyIdList() {
		return locationPartyIdList;
	}
	/**
	 * @param locationPartyIdList the locationPartyIdList to set
	 */
	public void setLocationPartyIdList(ArrayList locationPartyIdList) {
		this.locationPartyIdList = locationPartyIdList;
	}
	/**
	 * @return the locPartyNmAdd
	 */
	public LocationPartyNmAdd getLocPartyNmAdd() {
		return locPartyNmAdd;
	}
	/**
	 * @param locPartyNmAdd the locPartyNmAdd to set
	 */
	public void setLocPartyNmAdd(LocationPartyNmAdd locPartyNmAdd) {
		this.locPartyNmAdd = locPartyNmAdd;
	}
	/**
	 * @return the locationPartyCCList
	 */
	public ArrayList getLocationPartyCCList() {
		return locationPartyCCList;
	}
	/**
	 * @param locationPartyCCList the locationPartyCCList to set
	 */
	public void setLocationPartyCCList(ArrayList locationPartyCCList) {
		this.locationPartyCCList = locationPartyCCList;
	}
}