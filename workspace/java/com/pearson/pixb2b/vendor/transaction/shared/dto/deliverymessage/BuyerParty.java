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
 * Title		: 	BuyerParty.java
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
 * BuyerParty is a data transfer object to store the DeliveryMessageBook Header 
 * BuyerParty details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BuyerParty implements java.io.Serializable {
	private static final long serialVersionUID = 2445212105073978866L;
	
	private ArrayList buyPartyIdList	= null;
	private BuyPartyNmAdd buyPartyNmAdd	= null;
	private ArrayList buyPartyCCList 	= null;
	/**
	 * Default constructor.
	 */
	public BuyerParty() {
		super();
	}
	/**
	 * @return the buyPartyIdList
	 */
	public ArrayList getBuyPartyIdList() {
		return buyPartyIdList;
	}
	/**
	 * @param buyPartyIdList the buyPartyIdList to set
	 */
	public void setBuyPartyIdList(ArrayList buyPartyIdList) {
		this.buyPartyIdList = buyPartyIdList;
	}
	/**
	 * @return the buyPartyNmAdd
	 */
	public BuyPartyNmAdd getBuyPartyNmAdd() {
		return buyPartyNmAdd;
	}
	/**
	 * @param buyPartyNmAdd the buyPartyNmAdd to set
	 */
	public void setBuyPartyNmAdd(BuyPartyNmAdd buyPartyNmAdd) {
		this.buyPartyNmAdd = buyPartyNmAdd;
	}
	/**
	 * @return the buyPartyCCList
	 */
	public ArrayList getBuyPartyCCList() {
		return buyPartyCCList;
	}
	/**
	 * @param buyPartyCCList the buyPartyCCList to set
	 */
	public void setBuyPartyCCList(ArrayList buyPartyCCList) {
		this.buyPartyCCList = buyPartyCCList;
	}
}