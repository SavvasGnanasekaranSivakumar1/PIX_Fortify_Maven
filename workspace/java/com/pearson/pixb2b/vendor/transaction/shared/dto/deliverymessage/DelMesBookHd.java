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
 * Title		: 	DelMesBookHd.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShipToCharacteristics;

/**
 * DelMesBookHd is a data transfer object to store the DeliveryMessageBook Header details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesBookHd implements java.io.Serializable {
	private static final long serialVersionUID = -6761620104673644561L;
	
	private String delMeseNumb 		= null;
	private String tranHistNumr		= null;
	private DelMesDate delMesDate 	= null;
	private ArrayList delMesRefList	= null;
	private BuyerParty buyerParty  	= null;
	private BillToParty billToParty	= null;
	private SupplierParty supParty	= null;
	private ShipToCharacteristics shipToCharacteristics = null;
	private ArrayList deliveryLegList					= null;
	private ArrayList additionalTextList				= null;
	/**
	 * Default constructor.
	 */
	public DelMesBookHd() {
		super();
		supParty = new SupplierParty();
		delMesRefList = new ArrayList();
	}
	/**
	 * @return the delMeseNumb
	 */
	public String getDelMeseNumb() {
		return delMeseNumb;
	}
	/**
	 * @param delMeseNumb the delMeseNumb to set
	 */
	public void setDelMeseNumb(String delMeseNumb) {
		this.delMeseNumb = delMeseNumb;
	}
	/**
	 * @return the tranHistNumr
	 */
	public String getTranHistNumr() {
		return tranHistNumr;
	}
	/**
	 * @param tranHistNumr the tranHistNumr to set
	 */
	public void setTranHistNumr(String tranHistNumr) {
		this.tranHistNumr = tranHistNumr;
	}
	/**
	 * @return the delMesDate
	 */
	public DelMesDate getDelMesDate() {
		return delMesDate;
	}
	/**
	 * @param delMesDate the delMesDate to set
	 */
	public void setDelMesDate(DelMesDate delMesDate) {
		this.delMesDate = delMesDate;
	}
	/**
	 * @return the delMesRefList
	 */
	public ArrayList getDelMesRefList() {
		return delMesRefList;
	}
	/**
	 * @param delMesRefList the delMesRefList to set
	 */
	public void setDelMesRefList(ArrayList delMesRefList) {
		this.delMesRefList = delMesRefList;
	}
	/**
	 * @return the buyerParty
	 */
	public BuyerParty getBuyerParty() {
		return buyerParty;
	}
	/**
	 * @param buyerParty the buyerParty to set
	 */
	public void setBuyerParty(BuyerParty buyerParty) {
		this.buyerParty = buyerParty;
	}
	/**
	 * @return the billToParty
	 */
	public BillToParty getBillToParty() {
		return billToParty;
	}
	/**
	 * @param billToParty the billToParty to set
	 */
	public void setBillToParty(BillToParty billToParty) {
		this.billToParty = billToParty;
	}
	/**
	 * @return the supParty
	 */
	public SupplierParty getSupParty() {
		return supParty;
	}
	/**
	 * @param supParty the supParty to set
	 */
	public void setSupParty(SupplierParty supParty) {
		this.supParty = supParty;
	}
	/**
	 * @return the shipToCharacteristics
	 */
	public ShipToCharacteristics getShipToCharacteristics() {
		return shipToCharacteristics;
	}
	/**
	 * @param shipToCharacteristics the shipToCharacteristics to set
	 */
	public void setShipToCharacteristics(ShipToCharacteristics shipToCharacteristics) {
		this.shipToCharacteristics = shipToCharacteristics;
	}
	/**
	 * @return the deliveryLegList
	 */
	public ArrayList getDeliveryLegList() {
		return deliveryLegList;
	}
	/**
	 * @param deliveryLegList the deliveryLegList to set
	 */
	public void setDeliveryLegList(ArrayList deliveryLegList) {
		this.deliveryLegList = deliveryLegList;
	}
	/**
	 * @return the additionalTextList
	 */
	public ArrayList getAdditionalTextList() {
		return additionalTextList;
	}
	/**
	 * @param additionalTextList the additionalTextList to set
	 */
	public void setAdditionalTextList(ArrayList additionalTextList) {
		this.additionalTextList = additionalTextList;
	}
}