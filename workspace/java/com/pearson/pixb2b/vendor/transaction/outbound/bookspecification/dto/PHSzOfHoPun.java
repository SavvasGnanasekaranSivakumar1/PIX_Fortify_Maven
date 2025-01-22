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
 * Title		: 	PHSzOfHoPun.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PHSzOfHoPun is a data transfer object to store the specification Binding   
 * PunchedHole SizeOf HolePunch details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PHSzOfHoPun implements java.io.Serializable {
	private static final long serialVersionUID = 5876214081135495721L;
	
	private String shapeOfHole			= null;
	private SzHolPunValue value			= null;
	private SzHolPunRangeMin rangeMin	= null;
	private SzHolPunRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public PHSzOfHoPun() {
		super();
	}
	/**
	 * @return the shapeOfHole
	 */
	public String getShapeOfHole() {
		return shapeOfHole;
	}
	/**
	 * @param shapeOfHole the shapeOfHole to set
	 */
	public void setShapeOfHole(String shapeOfHole) {
		this.shapeOfHole = shapeOfHole;
	}
	/**
	 * @return the value
	 */
	public SzHolPunValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SzHolPunValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SzHolPunRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SzHolPunRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SzHolPunRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SzHolPunRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
