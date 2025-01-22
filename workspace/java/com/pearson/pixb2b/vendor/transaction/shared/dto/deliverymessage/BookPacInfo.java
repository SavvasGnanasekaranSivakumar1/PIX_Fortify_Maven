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
 * Title		: 	BookPacInfo.java
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
 * BookPacInfo is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BookPacInfo implements java.io.Serializable {
	private static final long serialVersionUID = 53759272483910906L;
	
	private String packageType 			= null;
	private String mixProdPalIndr 		= null;
	private ArrayList pacIdentifierList	= null;
	private PackItemCount packItemCount	= null;
	private PackQuantity packQuantity	= null;
	private ArrayList boxItemList		= null;
	/**
	 * Default constructor.
	 */
	public BookPacInfo() {
		super();
	}
	/**
	 * @return the packageType
	 */
	public String getPackageType() {
		return packageType;
	}
	/**
	 * @param packageType the packageType to set
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	/**
	 * @return the mixProdPalIndr
	 */
	public String getMixProdPalIndr() {
		return mixProdPalIndr;
	}
	/**
	 * @param mixProdPalIndr the mixProdPalIndr to set
	 */
	public void setMixProdPalIndr(String mixProdPalIndr) {
		this.mixProdPalIndr = mixProdPalIndr;
	}
	/**
	 * @return the pacIdentifierList
	 */
	public ArrayList getPacIdentifierList() {
		return pacIdentifierList;
	}
	/**
	 * @param pacIdentifierList the pacIdentifierList to set
	 */
	public void setPacIdentifierList(ArrayList pacIdentifierList) {
		this.pacIdentifierList = pacIdentifierList;
	}
	/**
	 * @return the packItemCount
	 */
	public PackItemCount getPackItemCount() {
		return packItemCount;
	}
	/**
	 * @param packItemCount the packItemCount to set
	 */
	public void setPackItemCount(PackItemCount packItemCount) {
		this.packItemCount = packItemCount;
	}
	/**
	 * @return the packQuantity
	 */
	public PackQuantity getPackQuantity() {
		return packQuantity;
	}
	/**
	 * @param packQuantity the packQuantity to set
	 */
	public void setPackQuantity(PackQuantity packQuantity) {
		this.packQuantity = packQuantity;
	}
	/**
	 * @return the boxItemList
	 */
	public ArrayList getBoxItemList() {
		return boxItemList;
	}
	/**
	 * @param boxItemList the boxItemList to set
	 */
	public void setBoxItemList(ArrayList boxItemList) {
		this.boxItemList = boxItemList;
	}
}