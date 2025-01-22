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
 * Title		: 	Date.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto;
/**
 * Date is a helper data transfer object to store the 
 * Date details for any type of transactions.
 * 
 * @author Abhilasha Nigam
 */
public class LineShipDate implements java.io.Serializable {
	private static final long serialVersionUID = 5485300098339021672L;
	
	private String lineYear =  null;
	private String lineMonth = null;
	private String lineDay =   null;	
	/**
	 * @return Returns the lineDay.
	 */
	public String getLineDay() {
		return lineDay;
	}
	/**
	 * @param lineDay The lineDay to set.
	 */
	public void setLineDay(String lineDay) {
		this.lineDay = lineDay;
	}
	/**
	 * @return Returns the lineMonth.
	 */
	public String getLineMonth() {
		return lineMonth;
	}
	/**
	 * @param lineMonth The lineMonth to set.
	 */
	public void setLineMonth(String lineMonth) {
		this.lineMonth = lineMonth;
	}
	/**
	 * @return Returns the lineYear.
	 */
	public String getLineYear() {
		return lineYear;
	}
	/**
	 * @param lineYear The lineYear to set.
	 */
	public void setLineYear(String lineYear) {
		this.lineYear = lineYear;
	}
	/**
	 * Default constructor.
	 */
	public LineShipDate() {
		super();
	}
	
}