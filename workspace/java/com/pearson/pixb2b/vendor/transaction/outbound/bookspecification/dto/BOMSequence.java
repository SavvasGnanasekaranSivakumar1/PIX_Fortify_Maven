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
 * Title		: 	BOMSequence.java
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
 * BOMSequence is a data transfer object to store the 
 * Specification Assembly Bill Of Materials details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BOMSequence implements java.io.Serializable {
	private static final long serialVersionUID = 7247045345807523492L;
	
	private ProdIDBOM prodIDBOM 		= null;
	private ArrayList prodDescBOMList 	= null;
	private QuantityBOM	quantityBOM		= null;
	/**
	 * Default constructor.
	 */
	public BOMSequence() {
		super();
	}
	/**
	 * @return the prodIDBOM
	 */
	public ProdIDBOM getProdIDBOM() {
		return prodIDBOM;
	}
	/**
	 * @param prodIDBOM the prodIDBOM to set
	 */
	public void setProdIDBOM(ProdIDBOM prodIDBOM) {
		this.prodIDBOM = prodIDBOM;
	}
	/**
	 * @return the prodDescBOMList
	 */
	public ArrayList getProdDescBOMList() {
		return prodDescBOMList;
	}
	/**
	 * @param prodDescBOMList the prodDescBOMList to set
	 */
	public void setProdDescBOMList(ArrayList prodDescBOMList) {
		this.prodDescBOMList = prodDescBOMList;
	}
	/**
	 * @return the quantityBOM
	 */
	public QuantityBOM getQuantityBOM() {
		return quantityBOM;
	}
	/**
	 * @param quantityBOM the quantityBOM to set
	 */
	public void setQuantityBOM(QuantityBOM quantityBOM) {
		this.quantityBOM = quantityBOM;
	}
}
