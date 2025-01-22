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
 * Title		: 	MSSzOfHoPun.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSSzOfHoPun is a data transfer object to store the Specification NonPress Component 
 * Media Slide SizeOf HolePunch details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSSzOfHoPun implements java.io.Serializable {
	private static final long serialVersionUID = 8939664592796896748L;
	
	private String shapeOfHole			= null;
	private MSSzHPunValue value			= null;
	private MSSzHPunRangeMin rangeMin	= null;
	private MSSzHPunRangeMax rangeMax 	= null;
	/**
	 * Default constructor.
	 */
	public MSSzOfHoPun() {
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
	public MSSzHPunValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(MSSzHPunValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public MSSzHPunRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(MSSzHPunRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public MSSzHPunRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(MSSzHPunRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
