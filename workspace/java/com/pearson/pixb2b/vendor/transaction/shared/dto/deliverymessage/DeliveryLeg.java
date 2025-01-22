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
 * Title		: 	DeliveryLeg.java
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
 * DeliveryLeg is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Leg details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DeliveryLeg implements java.io.Serializable {
	private static final long serialVersionUID = 1911661093965170770L;
	
	private String deliveryModeType = null;
	private String deliveryLegType 	= null;
	private String eventType 		= null;
	private String legStageType 	= null;
	private String delLegSeqNumber	= null;
	private DelOrigin delOrigin		= null;
	private CarrierParty carParty 	= null;
	private ArrayList delLegRefList = null;
	/**
	 * Default constructor.
	 */
	public DeliveryLeg() {
		super();
	}
	/**
	 * @return the deliveryModeType
	 */
	public String getDeliveryModeType() {
		return deliveryModeType;
	}
	/**
	 * @param deliveryModeType the deliveryModeType to set
	 */
	public void setDeliveryModeType(String deliveryModeType) {
		this.deliveryModeType = deliveryModeType;
	}
	/**
	 * @return the deliveryLegType
	 */
	public String getDeliveryLegType() {
		return deliveryLegType;
	}
	/**
	 * @param deliveryLegType the deliveryLegType to set
	 */
	public void setDeliveryLegType(String deliveryLegType) {
		this.deliveryLegType = deliveryLegType;
	}
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the legStageType
	 */
	public String getLegStageType() {
		return legStageType;
	}
	/**
	 * @param legStageType the legStageType to set
	 */
	public void setLegStageType(String legStageType) {
		this.legStageType = legStageType;
	}
	/**
	 * @return the delLegSeqNumber
	 */
	public String getDelLegSeqNumber() {
		return delLegSeqNumber;
	}
	/**
	 * @param delLegSeqNumber the delLegSeqNumber to set
	 */
	public void setDelLegSeqNumber(String delLegSeqNumber) {
		this.delLegSeqNumber = delLegSeqNumber;
	}
	/**
	 * @return the delOrigin
	 */
	public DelOrigin getDelOrigin() {
		return delOrigin;
	}
	/**
	 * @param delOrigin the delOrigin to set
	 */
	public void setDelOrigin(DelOrigin delOrigin) {
		this.delOrigin = delOrigin;
	}
	/**
	 * @return the carParty
	 */
	public CarrierParty getCarParty() {
		return carParty;
	}
	/**
	 * @param carParty the carParty to set
	 */
	public void setCarParty(CarrierParty carParty) {
		this.carParty = carParty;
	}
	/**
	 * @return the delLegRefList
	 */
	public ArrayList getDelLegRefList() {
		return delLegRefList;
	}
	/**
	 * @param delLegRefList the delLegRefList to set
	 */
	public void setDelLegRefList(ArrayList delLegRefList) {
		this.delLegRefList = delLegRefList;
	}
}