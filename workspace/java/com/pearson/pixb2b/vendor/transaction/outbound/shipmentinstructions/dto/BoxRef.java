/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	BoxRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		25 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class BoxRef implements Serializable{

	private static final long serialVersionUID = -8769244122750540003L;
	
	private String boxRefType;
	private String boxRefValue;
	

	/**
	 * Default constructor.
	 */
	public BoxRef() {
		super();
	}
	
	
	/**
	 * @return the boxRefType
	 */
	public String getBoxRefType() {
		return boxRefType;
	}
	/**
	 * @param boxRefType the boxRefType to set
	 */
	public void setBoxRefType(String boxRefType) {
		this.boxRefType = boxRefType;
	}


	/**
	 * @return the boxRefValue
	 */
	public String getBoxRefValue() {
		return boxRefValue;
	}


	/**
	 * @param boxRefValue the boxRefValue to set
	 */
	public void setBoxRefValue(String boxRefValue) {
		this.boxRefValue = boxRefValue;
	}

}
