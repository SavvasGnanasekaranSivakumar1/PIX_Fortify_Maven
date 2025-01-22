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
 * Title		: 	BillOfMaterials.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BillOfMaterials is a data transfer object to store the 
 * Specification Assembly Bill Of Materials details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BillOfMaterials implements java.io.Serializable {
	private static final long serialVersionUID = 4985402588272053504L;
	
	private ArrayList bomSequenceList	= null;
	/**
	 * Default constructor.
	 */
	public BillOfMaterials() {
		super();
	}
	/**
	 * @return the bomSequenceList
	 */
	public ArrayList getBomSequenceList() {
		return bomSequenceList;
	}
	/**
	 * @param bomSequenceList the bomSequenceList to set
	 */
	public void setBomSequenceList(ArrayList bomSequenceList) {
		this.bomSequenceList = bomSequenceList;
	}
}
