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
 * Title		: 	ShippingInstructionsDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * ShippingInstructionsDTO is a data transfer object to store the 
 * Shipping Instruction details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ShippingInstructionsDTO implements java.io.Serializable {
	private static final long serialVersionUID = -8543691214928551960L;
	
	private String siType		= null;
	private String siStatusType	= null;
	private SIHeader siHeader	= null;
	private ByProduct byProduct = null;
	private ByShipTo byShipTo = null;
	/**
	 * Default constructor.
	 */
	public ShippingInstructionsDTO() {
		super();
	}
	/**
	 * @return the siType
	 */
	public String getSiType() {
		return siType;
	}
	/**
	 * @param siType the siType to set
	 */
	public void setSiType(String siType) {
		this.siType = siType;
	}
	/**
	 * @return the siStatusType
	 */
	public String getSiStatusType() {
		return siStatusType;
	}
	/**
	 * @param siStatusType the siStatusType to set
	 */
	public void setSiStatusType(String siStatusType) {
		this.siStatusType = siStatusType;
	}
	/**
	 * @return the siHeader
	 */
	public SIHeader getSiHeader() {
		return siHeader;
	}
	/**
	 * @param siHeader the siHeader to set
	 */
	public void setSiHeader(SIHeader siHeader) {
		this.siHeader = siHeader;
	}
	/**
	 * @return the byProduct
	 */
	public ByProduct getByProduct() {
		return byProduct;
	}
	/**
	 * @param byProduct the byProduct to set
	 */
	public void setByProduct(ByProduct byProduct) {
		this.byProduct = byProduct;
	}
	/**
	 * @return the byShipTo
	 */
	public ByShipTo getByShipTo() {
		return byShipTo;
	}
	/**
	 * @param byShipTo the byShipTo to set
	 */
	public void setByShipTo(ByShipTo byShipTo) {
		this.byShipTo = byShipTo;
	}
}
