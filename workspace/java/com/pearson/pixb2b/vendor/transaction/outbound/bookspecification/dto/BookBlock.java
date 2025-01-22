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
 * Title		: 	BookBlock.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BookBlock is a data transfer object to store the Book Block 
 * details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BookBlock implements java.io.Serializable {
	private static final long serialVersionUID = 6637592986859539790L;
	
	private BBBSpecBind bkBlBulk 		= null;
	private ArrayList bbEdgeTrimList	= null;
	private ArrayList bbFExtrasList		= null;
	/**
	 * Default constructor.
	 */
	public BookBlock() {
		super();
	}
	/**
	 * @return the bkBlBulk
	 */
	public BBBSpecBind getBkBlBulk() {
		return bkBlBulk;
	}
	/**
	 * @param bkBlBulk the bkBlBulk to set
	 */
	public void setBkBlBulk(BBBSpecBind bkBlBulk) {
		this.bkBlBulk = bkBlBulk;
	}
	/**
	 * @return the bbEdgeTrimList
	 */
	public ArrayList getBbEdgeTrimList() {
		return bbEdgeTrimList;
	}
	/**
	 * @param bbEdgeTrimList the bbEdgeTrimList to set
	 */
	public void setBbEdgeTrimList(ArrayList bbEdgeTrimList) {
		this.bbEdgeTrimList = bbEdgeTrimList;
	}
	/**
	 * @return the bbFExtrasList
	 */
	public ArrayList getBbFExtrasList() {
		return bbFExtrasList;
	}
	/**
	 * @param bbFExtrasList the bbFExtrasList to set
	 */
	public void setBbFExtrasList(ArrayList bbFExtrasList) {
		this.bbFExtrasList = bbFExtrasList;
	}
}
