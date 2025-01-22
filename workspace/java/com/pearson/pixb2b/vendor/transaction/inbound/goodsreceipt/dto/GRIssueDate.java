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
 * Title		: 	UsageIssueDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * GRIssueDate is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class GRIssueDate implements java.io.Serializable {
	 private static final long serialVersionUID = 1501057940904241377L;

	private GRIDate grIDate = null;
	
	/**
	 * Default constructor.
	 */
	public GRIssueDate() {
		super();
	}

	/**
	 * @return Returns the grIDate.
	 */
	public GRIDate getGrIDate() {
		return grIDate;
	}

	/**
	 * @param grIDate The grIDate to set.
	 */
	public void setGrIDate(GRIDate grIDate) {
		this.grIDate = grIDate;
	}


	/**
	 * @return Returns the usageDate.
	 
	public UsageDate getUsageDate() {
		return usageDate;
	}



	/**
	 * @param usageDate The usageDate to set.
	
	public void setUsageDate(UsageDate usageDate) {
		this.usageDate = usageDate;
	}
*/

}