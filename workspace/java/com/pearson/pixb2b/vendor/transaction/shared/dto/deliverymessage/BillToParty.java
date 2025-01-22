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
 * Title		: 	BillToParty.java
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
 * BillToParty is a data transfer object to store the DeliveryMessageBook Header 
 * BillToParty details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BillToParty implements java.io.Serializable {
	private static final long serialVersionUID = -5765478640468466274L;
	
	private ArrayList billToPartyIdList			= null;
	private BillToPartyNmAdd billToPartyNmAdd	= null;
	private ArrayList billToPartyCCList 		= null;
	/**
	 * Default constructor.
	 */
	public BillToParty() {
		super();
	}
	/**
	 * @return the billToPartyIdList
	 */
	public ArrayList getBillToPartyIdList() {
		return billToPartyIdList;
	}
	/**
	 * @param billToPartyIdList the billToPartyIdList to set
	 */
	public void setBillToPartyIdList(ArrayList billToPartyIdList) {
		this.billToPartyIdList = billToPartyIdList;
	}
	/**
	 * @return the billToPartyNmAdd
	 */
	public BillToPartyNmAdd getBillToPartyNmAdd() {
		return billToPartyNmAdd;
	}
	/**
	 * @param billToPartyNmAdd the billToPartyNmAdd to set
	 */
	public void setBillToPartyNmAdd(BillToPartyNmAdd billToPartyNmAdd) {
		this.billToPartyNmAdd = billToPartyNmAdd;
	}
	/**
	 * @return the billToPartyCCList
	 */
	public ArrayList getBillToPartyCCList() {
		return billToPartyCCList;
	}
	/**
	 * @param billToPartyCCList the billToPartyCCList to set
	 */
	public void setBillToPartyCCList(ArrayList billToPartyCCList) {
		this.billToPartyCCList = billToPartyCCList;
	}
}