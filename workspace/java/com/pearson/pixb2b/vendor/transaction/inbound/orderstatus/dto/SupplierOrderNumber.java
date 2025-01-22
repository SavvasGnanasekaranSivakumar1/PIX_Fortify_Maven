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
 * Title		: 	SupplierOrderNumber.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * SupplierOrderNumber is a data transfer object to store the 
 * Supplier  OrderNumber detail and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SupplierOrderNumber implements java.io.Serializable {
	private static final long serialVersionUID = 4558832920137362016L;
	
	private String supplierOrderNumber = null;
	/**
	 * Default constructor.
	 */
	public SupplierOrderNumber() {
		super();
	}
	/**
	 * @return the supplierOrderNumber
	 */
	public String getSupplierOrderNumber() {
		return supplierOrderNumber;
	}
	/**
	 * @param supplierOrderNumber the supplierOrderNumber to set
	 */
	public void setSupplierOrderNumber(String supplierOrderNumber) {
		this.supplierOrderNumber = supplierOrderNumber;
	}
}
