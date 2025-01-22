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
 * Title		: 	UsageLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;

/**
 * AggrUsageLineItem is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class AggrUsageLineItem implements java.io.Serializable {

	private static final long serialVersionUID = -5267393524946000998L;
	
	private UsageProduct usgProduct = null;
	private POInformation poInfo = null;
	private LineQuantity lineQty = null;
	ArrayList lineInfoQty = null;
	
	/**
	 * Default constructor.
	 */
	public AggrUsageLineItem() {
		super();
	}

	

	/**
	 * @return Returns the lineQty.
	 */
	public LineQuantity getLineQty() {
		return lineQty;
	}

	/**
	 * @param lineQty The lineQty to set.
	 */
	public void setLineQty(LineQuantity lineQty) {
		this.lineQty = lineQty;
	}

	/**
	 * @return Returns the poInfo.
	 */
	public POInformation getPoInfo() {
		return poInfo;
	}

	/**
	 * @param poInfo The poInfo to set.
	 */
	public void setPoInfo(POInformation poInfo) {
		this.poInfo = poInfo;
	}

	/**
	 * @return Returns the usgProduct.
	 */
	public UsageProduct getUsgProduct() {
		return usgProduct;
	}

	/**
	 * @param usgProduct The usgProduct to set.
	 */
	public void setUsgProduct(UsageProduct usgProduct) {
		this.usgProduct = usgProduct;
	}



	/**
	 * @return Returns the lineInfoQty.
	 */
	public ArrayList getLineInfoQty() {
		return lineInfoQty;
	}



	/**
	 * @param lineInfoQty The lineInfoQty to set.
	 */
	public void setLineInfoQty(ArrayList lineInfoQty) {
		this.lineInfoQty = lineInfoQty;
	}
	
}
