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
 * Title		: 	BoxCharacs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BoxCharacs is a data transfer object to store the Spec Packing
 * Box Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BoxCharacs implements java.io.Serializable {
	private static final long serialVersionUID = -3291762933700523184L;
	
	private String boxMeasuAppTo 			= null;
	private String boxType 					= null;
	private SPBCProdId spbcProdId		 	= null;
	private SPBCHeight spbcHeight		  	= null;
	private SPBCLength spbcLength		 	= null;
	private SPBCWidth spbcWidth 			= null;
	private SPBCWeight spbcWeight 			= null;
	private SPBCBurst spbcBurst				= null;
	private SPBCBurstIndex spbcBurstIndex	= null;
	private SPBCUnitsPrCtn spbcUnitsPrCtn	= null;
	private ArrayList spbcLabCharcsList  	= null;
	private ArrayList spbcStenCharsList		= null;
	private SPBCWrapChars spbcWrapChars		= null;
	private SPBCBandChars spbcBandChacs		= null;
	private ArrayList spbcAddTextList		= null;
	/**
	 * Default constructor.
	 */
	public BoxCharacs() {
		super();
	}
	/**
	 * @return the boxMeasuAppTo
	 */
	public String getBoxMeasuAppTo() {
		return boxMeasuAppTo;
	}
	/**
	 * @param boxMeasuAppTo the boxMeasuAppTo to set
	 */
	public void setBoxMeasuAppTo(String boxMeasuAppTo) {
		this.boxMeasuAppTo = boxMeasuAppTo;
	}
	/**
	 * @return the boxType
	 */
	public String getBoxType() {
		return boxType;
	}
	/**
	 * @param boxType the boxType to set
	 */
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	/**
	 * @return the spbcProdId
	 */
	public SPBCProdId getSpbcProdId() {
		return spbcProdId;
	}
	/**
	 * @param spbcProdId the spbcProdId to set
	 */
	public void setSpbcProdId(SPBCProdId spbcProdId) {
		this.spbcProdId = spbcProdId;
	}
	/**
	 * @return the spbcHeight
	 */
	public SPBCHeight getSpbcHeight() {
		return spbcHeight;
	}
	/**
	 * @param spbcHeight the spbcHeight to set
	 */
	public void setSpbcHeight(SPBCHeight spbcHeight) {
		this.spbcHeight = spbcHeight;
	}
	/**
	 * @return the spbcLength
	 */
	public SPBCLength getSpbcLength() {
		return spbcLength;
	}
	/**
	 * @param spbcLength the spbcLength to set
	 */
	public void setSpbcLength(SPBCLength spbcLength) {
		this.spbcLength = spbcLength;
	}
	/**
	 * @return the spbcWidth
	 */
	public SPBCWidth getSpbcWidth() {
		return spbcWidth;
	}
	/**
	 * @param spbcWidth the spbcWidth to set
	 */
	public void setSpbcWidth(SPBCWidth spbcWidth) {
		this.spbcWidth = spbcWidth;
	}
	/**
	 * @return the spbcWeight
	 */
	public SPBCWeight getSpbcWeight() {
		return spbcWeight;
	}
	/**
	 * @param spbcWeight the spbcWeight to set
	 */
	public void setSpbcWeight(SPBCWeight spbcWeight) {
		this.spbcWeight = spbcWeight;
	}
	/**
	 * @return the spbcBurst
	 */
	public SPBCBurst getSpbcBurst() {
		return spbcBurst;
	}
	/**
	 * @param spbcBurst the spbcBurst to set
	 */
	public void setSpbcBurst(SPBCBurst spbcBurst) {
		this.spbcBurst = spbcBurst;
	}
	/**
	 * @return the spbcBurstIndex
	 */
	public SPBCBurstIndex getSpbcBurstIndex() {
		return spbcBurstIndex;
	}
	/**
	 * @param spbcBurstIndex the spbcBurstIndex to set
	 */
	public void setSpbcBurstIndex(SPBCBurstIndex spbcBurstIndex) {
		this.spbcBurstIndex = spbcBurstIndex;
	}
	/**
	 * @return the spbcUnitsPrCtn
	 */
	public SPBCUnitsPrCtn getSpbcUnitsPrCtn() {
		return spbcUnitsPrCtn;
	}
	/**
	 * @param spbcUnitsPrCtn the spbcUnitsPrCtn to set
	 */
	public void setSpbcUnitsPrCtn(SPBCUnitsPrCtn spbcUnitsPrCtn) {
		this.spbcUnitsPrCtn = spbcUnitsPrCtn;
	}
	/**
	 * @return the spbcLabCharcsList
	 */
	public ArrayList getSpbcLabCharcsList() {
		return spbcLabCharcsList;
	}
	/**
	 * @param spbcLabCharcsList the spbcLabCharcsList to set
	 */
	public void setSpbcLabCharcsList(ArrayList spbcLabCharcsList) {
		this.spbcLabCharcsList = spbcLabCharcsList;
	}
	/**
	 * @return the spbcStenCharsList
	 */
	public ArrayList getSpbcStenCharsList() {
		return spbcStenCharsList;
	}
	/**
	 * @param spbcStenCharsList the spbcStenCharsList to set
	 */
	public void setSpbcStenCharsList(ArrayList spbcStenCharsList) {
		this.spbcStenCharsList = spbcStenCharsList;
	}
	/**
	 * @return the spbcWrapChars
	 */
	public SPBCWrapChars getSpbcWrapChars() {
		return spbcWrapChars;
	}
	/**
	 * @param spbcWrapChars the spbcWrapChars to set
	 */
	public void setSpbcWrapChars(SPBCWrapChars spbcWrapChars) {
		this.spbcWrapChars = spbcWrapChars;
	}
	/**
	 * @return the spbcBandChacs
	 */
	public SPBCBandChars getSpbcBandChacs() {
		return spbcBandChacs;
	}
	/**
	 * @param spbcBandChacs the spbcBandChacs to set
	 */
	public void setSpbcBandChacs(SPBCBandChars spbcBandChacs) {
		this.spbcBandChacs = spbcBandChacs;
	}
	/**
	 * @return the spbcAddTextList
	 */
	public ArrayList getSpbcAddTextList() {
		return spbcAddTextList;
	}
	/**
	 * @param spbcAddTextList the spbcAddTextList to set
	 */
	public void setSpbcAddTextList(ArrayList spbcAddTextList) {
		this.spbcAddTextList = spbcAddTextList;
	}
}
