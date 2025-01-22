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
 * Title		: 	BoxItem.java
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
 * BoxItem is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information BoxItem details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxItem implements java.io.Serializable {
	private static final long serialVersionUID = 3985172531524281069L;
	
	private String mixProdIndr 			= null;
	private BoxItemCount boxItemCount	= null;
	private BoxQuantity boxQuantity		= null;
	private ArrayList unitItemList		= null;
	private BoxCharcs boxCharcs			= null;
	private ArrayList boxRefList		= null;
	/**
	 * Default constructor.
	 */
	public BoxItem() {
		super();
	}
	
	/**
	 * @return the boxRefList
	 */
	public ArrayList getBoxRefList() {
		return boxRefList;
	}
	/**
	 * @param boxRefList the boxRefList to set
	 */
	public void setBoxRefList(ArrayList boxRefList) {
		this.boxRefList = boxRefList;
	}
	/**
	 * @return the mixProdIndr
	 */
	public String getMixProdIndr() {
		return mixProdIndr;
	}
	/**
	 * @param mixProdIndr the mixProdIndr to set
	 */
	public void setMixProdIndr(String mixProdIndr) {
		this.mixProdIndr = mixProdIndr;
	}
	/**
	 * @return the boxItemCount
	 */
	public BoxItemCount getBoxItemCount() {
		return boxItemCount;
	}
	/**
	 * @param boxItemCount the boxItemCount to set
	 */
	public void setBoxItemCount(BoxItemCount boxItemCount) {
		this.boxItemCount = boxItemCount;
	}
	/**
	 * @return the boxQuantity
	 */
	public BoxQuantity getBoxQuantity() {
		return boxQuantity;
	}
	/**
	 * @param boxQuantity the boxQuantity to set
	 */
	public void setBoxQuantity(BoxQuantity boxQuantity) {
		this.boxQuantity = boxQuantity;
	}
	/**
	 * @return the unitItemList
	 */
	public ArrayList getUnitItemList() {
		return unitItemList;
	}
	/**
	 * @param unitItemList the unitItemList to set
	 */
	public void setUnitItemList(ArrayList unitItemList) {
		this.unitItemList = unitItemList;
	}
	/**
	 * @return the boxCharcs
	 */
	public BoxCharcs getBoxCharcs() {
		return boxCharcs;
	}
	/**
	 * @param boxCharcs the boxCharcs to set
	 */
	public void setBoxCharcs(BoxCharcs boxCharcs) {
		this.boxCharcs = boxCharcs;
	}
}