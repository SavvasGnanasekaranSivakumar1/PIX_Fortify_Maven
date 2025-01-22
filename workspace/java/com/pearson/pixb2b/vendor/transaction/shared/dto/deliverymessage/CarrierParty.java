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
 * Title		: 	CarrierParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

/**
 * CarrierParty is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Leg Carrier Party details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CarrierParty implements java.io.Serializable {
	private static final long serialVersionUID = -3481208994506952674L;
	
	private ArrayList carPartyIdList	= null;
	private CarPartyNmAdd carPartyNmAdd	= null;
	private ArrayList carPartyCCList 	= null;
	/**
	 * Default constructor.
	 */
	public CarrierParty() {
		super();
	}
	/**
	 * @return the carPartyIdList
	 */
	public ArrayList getCarPartyIdList() {
		return carPartyIdList;
	}
	/**
	 * @param carPartyIdList the carPartyIdList to set
	 */
	public void setCarPartyIdList(ArrayList carPartyIdList) {
		this.carPartyIdList = carPartyIdList;
	}
	/**
	 * @return the carPartyNmAdd
	 */
	public CarPartyNmAdd getCarPartyNmAdd() {
		return carPartyNmAdd;
	}
	/**
	 * @param carPartyNmAdd the carPartyNmAdd to set
	 */
	public void setCarPartyNmAdd(CarPartyNmAdd carPartyNmAdd) {
		this.carPartyNmAdd = carPartyNmAdd;
	}
	/**
	 * @return the carPartyCCList
	 */
	public ArrayList getCarPartyCCList() {
		return carPartyCCList;
	}
	/**
	 * @param carPartyCCList the carPartyCCList to set
	 */
	public void setCarPartyCCList(ArrayList carPartyCCList) {
		this.carPartyCCList = carPartyCCList;
	}
}