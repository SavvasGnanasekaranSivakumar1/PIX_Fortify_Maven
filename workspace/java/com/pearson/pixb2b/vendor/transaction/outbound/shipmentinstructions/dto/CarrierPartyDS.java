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
 * Title		: 	CarrierPartyDS.java
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
 * DelOrigin is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Leg Carrier Party details and transfer the same between classes.
 * 
 * @author Ranu.Sharma
 */
public class CarrierPartyDS implements Serializable{
	
	private static final long serialVersionUID = -2657497297859016918L;
	
	private ArrayList carPartyIdList	= null;
	private CarPartyNmAddDS carPartyNmAdd	= null;
	private ArrayList carPartyCCList 	= null;
	/**
	 * Default constructor.
	 */
	public CarrierPartyDS() {
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
	public CarPartyNmAddDS getCarPartyNmAdd() {
		return carPartyNmAdd;
	}
	/**
	 * @param carPartyNmAdd the carPartyNmAdd to set
	 */
	public void setCarPartyNmAdd(CarPartyNmAddDS carPartyNmAdd) {
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
