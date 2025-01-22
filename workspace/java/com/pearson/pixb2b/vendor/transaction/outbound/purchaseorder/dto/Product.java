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
 * Title		: 	Product.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * Product is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = 4816839228304559451L;
	
	private ProductIdentifier prodIdentifier = null;
	private BookManufacturing bookManufacturing = null;
	private String productID_agency = null;
	private String productID_type = null;
	private String productID_value = null;	
	
	
	/**
	 * Default constructor.
	 */
	public Product() {
		super();
	}

	public BookManufacturing getBookManufacturing() {
		return bookManufacturing;
	}

	public void setBookManufacturing(BookManufacturing bookManufacturing) {
		this.bookManufacturing = bookManufacturing;
	}

	public String getProductID_agency() {
		return productID_agency;
	}

	public void setProductID_agency(String productID_agency) {
		this.productID_agency = productID_agency;
	}

	public String getProductID_type() {
		return productID_type;
	}

	public void setProductID_type(String productID_type) {
		this.productID_type = productID_type;
	}

	/**
	 * @return Returns the productID_value.
	 */
	public String getProductID_value() {
		return productID_value;
	}

	/**
	 * @param productID_value The productID_value to set.
	 */
	public void setProductID_value(String productID_value) {
		this.productID_value = productID_value;
	}

	/**
	 * @return Returns the prodIdentifier.
	 */
	public ProductIdentifier getProdIdentifier() {
		return prodIdentifier;
	}

	/**
	 * @param prodIdentifier The prodIdentifier to set.
	 */
	public void setProdIdentifier(ProductIdentifier prodIdentifier) {
		this.prodIdentifier = prodIdentifier;
	}
	
	
	
}