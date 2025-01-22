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
 * Title		: 	LocationPartyDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		20 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * LocationPartyDS is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Origin LocationParty details and transfer the same between classes.
 * 
 * @author Ranu Sharma
 */
public class LocationPartyDS implements Serializable{
	private static final long serialVersionUID = -3462945258998692052L;
	
	private String partyType					= null;
	private String logisticsRole				= null;
	private String locationType					= null;
	private ArrayList locationPartyIdList		= null;
	private LocationPartyNmAddDS locPartyNmAdd	= null;
	private ArrayList locationPartyCCList 		= null;
	/**
	 * Default constructor.
	 */
	public LocationPartyDS() {
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
	public LocationPartyNmAddDS getLocPartyNmAdd() {
		return locPartyNmAdd;
	}
	/**
	 * @param locPartyNmAdd the locPartyNmAdd to set
	 */
	public void setLocPartyNmAdd(LocationPartyNmAddDS locPartyNmAdd) {
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
