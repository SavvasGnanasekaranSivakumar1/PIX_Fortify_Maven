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
 * Title		: 	DelShipLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;

/**
 * DelShipLineItem is a data transfer object to store the DeliveryMessageProductGroup 
 * DeliveryShipmentLineItem details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelShipLineItem implements java.io.Serializable {
	private static final long serialVersionUID = -5527410715361145374L;
	
	private String delShipLineItemNum	= null;
	private POInformation poInformation = null;
	private String poLineItemNumber		= null;
	private TotNumOfUnits totNumOfUnits = null;		
	/**
	 * Default constructor.
	 */
	public DelShipLineItem() {
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
	 * @return the totNumOfUnits
	 */
	public TotNumOfUnits getTotNumOfUnits() {
		return totNumOfUnits;
	}
	/**
	 * @param totNumOfUnits the totNumOfUnits to set
	 */
	public void setTotNumOfUnits(TotNumOfUnits totNumOfUnits) {
		this.totNumOfUnits = totNumOfUnits;
	}
}