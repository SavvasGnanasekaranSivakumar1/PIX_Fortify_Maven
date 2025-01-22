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
 * Title		: 	BoxUnitChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxUnitChars is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box UnitItem Characteristics and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxUnitChars implements java.io.Serializable {
	private static final long serialVersionUID = -1666281404385410366L;
	
	private UnitHeight unitHeight 		= null;
	private UnitThickness unitThickness = null;
	private UnitWidth unitWidth 		= null;
	private UnitWeight unitWeight 		= null;
	/**
	 * Default constructor.
	 */
	public BoxUnitChars() {
		super();
	}
	/**
	 * @return the unitHeight
	 */
	public UnitHeight getUnitHeight() {
		return unitHeight;
	}
	/**
	 * @param unitHeight the unitHeight to set
	 */
	public void setUnitHeight(UnitHeight unitHeight) {
		this.unitHeight = unitHeight;
	}
	/**
	 * @return the unitThickness
	 */
	public UnitThickness getUnitThickness() {
		return unitThickness;
	}
	/**
	 * @param unitThickness the unitThickness to set
	 */
	public void setUnitThickness(UnitThickness unitThickness) {
		this.unitThickness = unitThickness;
	}
	/**
	 * @return the unitWidth
	 */
	public UnitWidth getUnitWidth() {
		return unitWidth;
	}
	/**
	 * @param unitWidth the unitWidth to set
	 */
	public void setUnitWidth(UnitWidth unitWidth) {
		this.unitWidth = unitWidth;
	}
	/**
	 * @return the unitWeight
	 */
	public UnitWeight getUnitWeight() {
		return unitWeight;
	}
	/**
	 * @param unitWeight the unitWeight to set
	 */
	public void setUnitWeight(UnitWeight unitWeight) {
		this.unitWeight = unitWeight;
	}
}