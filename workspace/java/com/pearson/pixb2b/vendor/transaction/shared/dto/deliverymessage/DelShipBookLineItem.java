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
 * Title		: 	DelShipBookLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;

/**
 * DelShipBookLineItem is a data transfer object to store the Delivery Shipment BookLineItem
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelShipBookLineItem implements java.io.Serializable {
	private static final long serialVersionUID = 9144552630006916391L;
	
	private String delShipLineItemNum	= null;
	private POInformation poInformation = null;
	private String poLineItemNumber		= null;
	private LineQuantity lineQty 		= null;
	private ArrayList infoQuantityList	= null;
	private ArrayList bookPacInfoList	= null;
	private ArrayList delMesRefList		= null;	
	/**
	 * Default constructor.
	 */
	public DelShipBookLineItem() {
		super();
	}
	/**
	 * @return the delShipLineItemNum
	 */
	public String getDelShipLineItemNum() {
		return delShipLineItemNum;
	}
	/**
	 * @param delShipLineItemNum the delShipLineItemNum to set
	 */
	public void setDelShipLineItemNum(String delShipLineItemNum) {
		this.delShipLineItemNum = delShipLineItemNum;
	}
	/**
	 * @return the poInformation
	 */
	public POInformation getPoInformation() {
		return poInformation;
	}
	/**
	 * @param poInformation the poInformation to set
	 */
	public void setPoInformation(POInformation poInformation) {
		this.poInformation = poInformation;
	}
	/**
	 * @return the poLineItemNumber
	 */
	public String getPoLineItemNumber() {
		return poLineItemNumber;
	}
	/**
	 * @param poLineItemNumber the poLineItemNumber to set
	 */
	public void setPoLineItemNumber(String poLineItemNumber) {
		this.poLineItemNumber = poLineItemNumber;
	}
	/**
	 * @return the lineQty
	 */
	public LineQuantity getLineQty() {
		return lineQty;
	}
	/**
	 * @param lineQty the lineQty to set
	 */
	public void setLineQty(LineQuantity lineQty) {
		this.lineQty = lineQty;
	}
	/**
	 * @return the infoQuantityList
	 */
	public ArrayList getInfoQuantityList() {
		return infoQuantityList;
	}
	/**
	 * @param infoQuantityList the infoQuantityList to set
	 */
	public void setInfoQuantityList(ArrayList infoQuantityList) {
		this.infoQuantityList = infoQuantityList;
	}
	/**
	 * @return the bookPacInfoList
	 */
	public ArrayList getBookPacInfoList() {
		return bookPacInfoList;
	}
	/**
	 * @param bookPacInfoList the bookPacInfoList to set
	 */
	public void setBookPacInfoList(ArrayList bookPacInfoList) {
		this.bookPacInfoList = bookPacInfoList;
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
}