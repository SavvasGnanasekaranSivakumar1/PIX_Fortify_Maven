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
 * Title		: 	SPPCPalChars.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPalChars is a data transfer object to store the Spec Packing Pallet
 * Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalChars implements java.io.Serializable {
	private static final long serialVersionUID = 7786394023204947984L;
	
	private String palLedgeType 			= null;
	private String palTopType 				= null;
	private String palCovType 				= null;
	private String palType 					= null;
	private String mixProdPalIndr 			= null;
	private String palAddsType 				= null;
	private SPPCProdId spPCProdId			= null;
	private SPPCPalLeng spPCPalLeng			= null;
	private SPPCPalWid spPPCPalWid			= null;
	private String itPerPallet 				= null;
	private String stPerPallet				= null;
	private String tiPerPallet				= null;
	private SPPCMxHgt spPCMxHgt				= null;
	private String stkgMethod				= null;
	private SPPCPalLabChars spPCPalLabChars	= null;
	/**
	 * Default constructor.
	 */
	public SPPCPalChars() {
		super();
	}
	/**
	 * @return the palLedgeType
	 */
	public String getPalLedgeType() {
		return palLedgeType;
	}
	/**
	 * @param palLedgeType the palLedgeType to set
	 */
	public void setPalLedgeType(String palLedgeType) {
		this.palLedgeType = palLedgeType;
	}
	/**
	 * @return the palTopType
	 */
	public String getPalTopType() {
		return palTopType;
	}
	/**
	 * @param palTopType the palTopType to set
	 */
	public void setPalTopType(String palTopType) {
		this.palTopType = palTopType;
	}
	/**
	 * @return the palCovType
	 */
	public String getPalCovType() {
		return palCovType;
	}
	/**
	 * @param palCovType the palCovType to set
	 */
	public void setPalCovType(String palCovType) {
		this.palCovType = palCovType;
	}
	/**
	 * @return the palType
	 */
	public String getPalType() {
		return palType;
	}
	/**
	 * @param palType the palType to set
	 */
	public void setPalType(String palType) {
		this.palType = palType;
	}
	/**
	 * @return the mixProdPalIndr
	 */
	public String getMixProdPalIndr() {
		return mixProdPalIndr;
	}
	/**
	 * @param mixProdPalIndr the mixProdPalIndr to set
	 */
	public void setMixProdPalIndr(String mixProdPalIndr) {
		this.mixProdPalIndr = mixProdPalIndr;
	}
	/**
	 * @return the palAddsType
	 */
	public String getPalAddsType() {
		return palAddsType;
	}
	/**
	 * @param palAddsType the palAddsType to set
	 */
	public void setPalAddsType(String palAddsType) {
		this.palAddsType = palAddsType;
	}
	/**
	 * @return the spPCProdId
	 */
	public SPPCProdId getSpPCProdId() {
		return spPCProdId;
	}
	/**
	 * @param spPCProdId the spPCProdId to set
	 */
	public void setSpPCProdId(SPPCProdId spPCProdId) {
		this.spPCProdId = spPCProdId;
	}
	/**
	 * @return the spPCPalLeng
	 */
	public SPPCPalLeng getSpPCPalLeng() {
		return spPCPalLeng;
	}
	/**
	 * @param spPCPalLeng the spPCPalLeng to set
	 */
	public void setSpPCPalLeng(SPPCPalLeng spPCPalLeng) {
		this.spPCPalLeng = spPCPalLeng;
	}
	/**
	 * @return the spPPCPalWid
	 */
	public SPPCPalWid getSpPPCPalWid() {
		return spPPCPalWid;
	}
	/**
	 * @param spPPCPalWid the spPPCPalWid to set
	 */
	public void setSpPPCPalWid(SPPCPalWid spPPCPalWid) {
		this.spPPCPalWid = spPPCPalWid;
	}
	/**
	 * @return the itPerPallet
	 */
	public String getItPerPallet() {
		return itPerPallet;
	}
	/**
	 * @param itPerPallet the itPerPallet to set
	 */
	public void setItPerPallet(String itPerPallet) {
		this.itPerPallet = itPerPallet;
	}
	/**
	 * @return the stPerPallet
	 */
	public String getStPerPallet() {
		return stPerPallet;
	}
	/**
	 * @param stPerPallet the stPerPallet to set
	 */
	public void setStPerPallet(String stPerPallet) {
		this.stPerPallet = stPerPallet;
	}
	/**
	 * @return the tiPerPallet
	 */
	public String getTiPerPallet() {
		return tiPerPallet;
	}
	/**
	 * @param tiPerPallet the tiPerPallet to set
	 */
	public void setTiPerPallet(String tiPerPallet) {
		this.tiPerPallet = tiPerPallet;
	}
	/**
	 * @return the spPCMxHgt
	 */
	public SPPCMxHgt getSpPCMxHgt() {
		return spPCMxHgt;
	}
	/**
	 * @param spPCMxHgt the spPCMxHgt to set
	 */
	public void setSpPCMxHgt(SPPCMxHgt spPCMxHgt) {
		this.spPCMxHgt = spPCMxHgt;
	}
	/**
	 * @return the stkgMethod
	 */
	public String getStkgMethod() {
		return stkgMethod;
	}
	/**
	 * @param stkgMethod the stkgMethod to set
	 */
	public void setStkgMethod(String stkgMethod) {
		this.stkgMethod = stkgMethod;
	}
	/**
	 * @return the spPCPalLabChars
	 */
	public SPPCPalLabChars getSpPCPalLabChars() {
		return spPCPalLabChars;
	}
	/**
	 * @param spPCPalLabChars the spPCPalLabChars to set
	 */
	public void setSpPCPalLabChars(SPPCPalLabChars spPCPalLabChars) {
		this.spPCPalLabChars = spPCPalLabChars;
	}
}
