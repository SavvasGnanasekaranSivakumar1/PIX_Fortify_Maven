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
 * Title		: 	DeliveryMessageDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

/**
 * DeliveryMessageDTO is a data transfer object to store the DeliveryMessage details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DeliveryMessageDTO implements java.io.Serializable {
	private static final long serialVersionUID = -3106140339071140162L;
	
	private String delMessageType		 = null;
	private String delMessageStatusType	 = null;
	private String delMessageContType	 = null;
	private String reissued				 = null;
	private String language				 = null;
	private DelMesBookHd delMesBookHd  	 = null;
	private ArrayList delMesBookShipList = null;
	private DelMesBookSum delMesBookSum	 = null;
	/**
	 * Default constructor.
	 */
	public DeliveryMessageDTO() {
		super();
		delMesBookHd = new DelMesBookHd();
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
	 * @return the delMesBookHd
	 */
	public DelMesBookHd getDelMesBookHd() {
		return delMesBookHd;
	}
	/**
	 * @param delMesBookHd the delMesBookHd to set
	 */
	public void setDelMesBookHd(DelMesBookHd delMesBookHd) {
		this.delMesBookHd = delMesBookHd;
	}
	/**
	 * @return the delMesBookShipList
	 */
	public ArrayList getDelMesBookShipList() {
		return delMesBookShipList;
	}
	/**
	 * @param delMesBookShipList the delMesBookShipList to set
	 */
	public void setDelMesBookShipList(ArrayList delMesBookShipList) {
		this.delMesBookShipList = delMesBookShipList;
	}
	/**
	 * @return the delMesBookSum
	 */
	public DelMesBookSum getDelMesBookSum() {
		return delMesBookSum;
	}
	/**
	 * @param delMesBookSum the delMesBookSum to set
	 */
	public void setDelMesBookSum(DelMesBookSum delMesBookSum) {
		this.delMesBookSum = delMesBookSum;
	}
}