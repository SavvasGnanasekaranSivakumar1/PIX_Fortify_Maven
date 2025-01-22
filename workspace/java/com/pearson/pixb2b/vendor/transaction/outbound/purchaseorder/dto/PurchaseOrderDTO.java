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
 * Title		: 	PurchaseOrderDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Aug, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;

/**
 * PurchaseOrderDTO is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class PurchaseOrderDTO implements java.io.Serializable {
	private static final long serialVersionUID = 583451883100981712L;
	
	private String poType		= null;
	private String poStatusType	= null;
	private POHeader poHeader	= null;
	private ArrayList poLineItem= null;
	private POSummary poSummary	= null;	
	/**
	 * Default constructor.
	 */
	public PurchaseOrderDTO() {
		super();
	}
	/**
	 * @return the poType
	 */
	public String getPoType() {
		return poType;
	}
	/**
	 * @param poType the poType to set
	 */
	public void setPoType(String poType) {
		this.poType = poType;
	}
	/**
	 * @return the poStatusType
	 */
	public String getPoStatusType() {
		return poStatusType;
	}
	/**
	 * @param poStatusType the poStatusType to set
	 */
	public void setPoStatusType(String poStatusType) {
		this.poStatusType = poStatusType;
	}
	/**
	 * @return the poHeader
	 */
	public POHeader getPoHeader() {
		return poHeader;
	}
	/**
	 * @param poHeader the poHeader to set
	 */
	public void setPoHeader(POHeader poHeader) {
		this.poHeader = poHeader;
	}
	/**
	 * @return the poLineItem
	 */
	public ArrayList getPoLineItem() {
		return poLineItem;
	}
	/**
	 * @param poLineItem the poLineItem to set
	 */
	public void setPoLineItem(ArrayList poLineItem) {
		this.poLineItem = poLineItem;
	}
	/**
	 * @return the poSummary
	 */
	public POSummary getPoSummary() {
		return poSummary;
	}
	/**
	 * @param poSummary the poSummary to set
	 */
	public void setPoSummary(POSummary poSummary) {
		this.poSummary = poSummary;
	}
}