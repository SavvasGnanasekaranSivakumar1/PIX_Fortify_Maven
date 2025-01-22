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
 * Title		: 	DeliveryMessageWoodDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

import java.util.ArrayList;

/**
 * DeliveryMessageWoodDTO is a data transfer object to store the DeliveryMessageWood details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DeliveryMessageWoodDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1072601689424345614L;
	
	private String delMessageType		= null;
	private String delMessageStatusType	= null;
	private String delMessageContType	= null;
	private String reissued				= null;
	private String language				= null;
	private DelMesWoodHd delMesWoodHd  	= null;
	private ArrayList delMesShipList 	= null;
	private DelMesWoodSum delMesWoodSum	= null;
	/**
	 * Default constructor.
	 */
	public DeliveryMessageWoodDTO() {
		super();
		delMesWoodHd = new DelMesWoodHd();
	}
	/**
	 * @return the delMessageType
	 */
	public String getDelMessageType() {
		return delMessageType;
	}
	/**
	 * @param delMessageType the delMessageType to set
	 */
	public void setDelMessageType(String delMessageType) {
		this.delMessageType = delMessageType;
	}
	/**
	 * @return the delMessageStatusType
	 */
	public String getDelMessageStatusType() {
		return delMessageStatusType;
	}
	/**
	 * @param delMessageStatusType the delMessageStatusType to set
	 */
	public void setDelMessageStatusType(String delMessageStatusType) {
		this.delMessageStatusType = delMessageStatusType;
	}
	/**
	 * @return the delMessageContType
	 */
	public String getDelMessageContType() {
		return delMessageContType;
	}
	/**
	 * @param delMessageContType the delMessageContType to set
	 */
	public void setDelMessageContType(String delMessageContType) {
		this.delMessageContType = delMessageContType;
	}
	/**
	 * @return the reissued
	 */
	public String getReissued() {
		return reissued;
	}
	/**
	 * @param reissued the reissued to set
	 */
	public void setReissued(String reissued) {
		this.reissued = reissued;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the delMesWoodHd
	 */
	public DelMesWoodHd getDelMesWoodHd() {
		return delMesWoodHd;
	}
	/**
	 * @param delMesWoodHd the delMesWoodHd to set
	 */
	public void setDelMesWoodHd(DelMesWoodHd delMesWoodHd) {
		this.delMesWoodHd = delMesWoodHd;
	}
	/**
	 * @return the delMesShipList
	 */
	public ArrayList getDelMesShipList() {
		return delMesShipList;
	}
	/**
	 * @param delMesShipList the delMesShipList to set
	 */
	public void setDelMesShipList(ArrayList delMesShipList) {
		this.delMesShipList = delMesShipList;
	}
	/**
	 * @return the delMesWoodSum
	 */
	public DelMesWoodSum getDelMesWoodSum() {
		return delMesWoodSum;
	}
	/**
	 * @param delMesWoodSum the delMesWoodSum to set
	 */
	public void setDelMesWoodSum(DelMesWoodSum delMesWoodSum) {
		this.delMesWoodSum = delMesWoodSum;
	}
}