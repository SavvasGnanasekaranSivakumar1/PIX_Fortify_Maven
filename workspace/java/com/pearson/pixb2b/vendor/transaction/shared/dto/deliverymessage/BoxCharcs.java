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
 * Title		: 	BoxCharcs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * BoxCharcs is a data transfer object to store the Delivery Shipment BookLineItem
 * BookPackage Information Box Characteristics and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxCharcs implements java.io.Serializable {
	private static final long serialVersionUID = -8792833069425111434L;
	
	private BoxHeight boxHeight 		= null;
	private BoxLength boxLength 		= null;
	private BoxWidth boxWidth 			= null;
	private BoxWeight boxWeight 		= null;
	private UnitsPerCrtn unitsPerCrtn	= null;
	/**
	 * Default constructor.
	 */
	public BoxCharcs() {
		super();
	}
	/**
	 * @return the boxHeight
	 */
	public BoxHeight getBoxHeight() {
		return boxHeight;
	}
	/**
	 * @param boxHeight the boxHeight to set
	 */
	public void setBoxHeight(BoxHeight boxHeight) {
		this.boxHeight = boxHeight;
	}
	/**
	 * @return the boxLength
	 */
	public BoxLength getBoxLength() {
		return boxLength;
	}
	/**
	 * @param boxLength the boxLength to set
	 */
	public void setBoxLength(BoxLength boxLength) {
		this.boxLength = boxLength;
	}
	/**
	 * @return the boxWidth
	 */
	public BoxWidth getBoxWidth() {
		return boxWidth;
	}
	/**
	 * @param boxWidth the boxWidth to set
	 */
	public void setBoxWidth(BoxWidth boxWidth) {
		this.boxWidth = boxWidth;
	}
	/**
	 * @return the boxWeight
	 */
	public BoxWeight getBoxWeight() {
		return boxWeight;
	}
	/**
	 * @param boxWeight the boxWeight to set
	 */
	public void setBoxWeight(BoxWeight boxWeight) {
		this.boxWeight = boxWeight;
	}
	/**
	 * @return the unitsPerCrtn
	 */
	public UnitsPerCrtn getUnitsPerCrtn() {
		return unitsPerCrtn;
	}
	/**
	 * @param unitsPerCrtn the unitsPerCrtn to set
	 */
	public void setUnitsPerCrtn(UnitsPerCrtn unitsPerCrtn) {
		this.unitsPerCrtn = unitsPerCrtn;
	}
}