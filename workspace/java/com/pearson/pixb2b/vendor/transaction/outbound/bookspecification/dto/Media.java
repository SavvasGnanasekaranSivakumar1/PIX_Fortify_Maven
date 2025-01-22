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
 * Title		: 	Media.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * Media is a data transfer object to store the Specification NonPress Component 
 * Media details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Media implements java.io.Serializable {
	private static final long serialVersionUID = 670360017504734619L;
	
	private String masterSource 			= null;
	private MedTotRunLngth medTotRunLngth	= null;
	private MedTotNOUnits medTotNOUnits 	= null;
	private String masStoOption				= null;
	private ArrayList medAudCasList			= null;
	private ArrayList medVidCasList			= null;
	private ArrayList medCDList				= null;
	private ArrayList medDVDList			= null;
	private ArrayList medSlideList			= null;
	/**
	 * Default constructor.
	 */
	public Media() {
		super();
	}
	/**
	 * @return the masterSource
	 */
	public String getMasterSource() {
		return masterSource;
	}
	/**
	 * @param masterSource the masterSource to set
	 */
	public void setMasterSource(String masterSource) {
		this.masterSource = masterSource;
	}
	/**
	 * @return the medTotRunLngth
	 */
	public MedTotRunLngth getMedTotRunLngth() {
		return medTotRunLngth;
	}
	/**
	 * @param medTotRunLngth the medTotRunLngth to set
	 */
	public void setMedTotRunLngth(MedTotRunLngth medTotRunLngth) {
		this.medTotRunLngth = medTotRunLngth;
	}
	/**
	 * @return the medTotNOUnits
	 */
	public MedTotNOUnits getMedTotNOUnits() {
		return medTotNOUnits;
	}
	/**
	 * @param medTotNOUnits the medTotNOUnits to set
	 */
	public void setMedTotNOUnits(MedTotNOUnits medTotNOUnits) {
		this.medTotNOUnits = medTotNOUnits;
	}
	/**
	 * @return the masStoOption
	 */
	public String getMasStoOption() {
		return masStoOption;
	}
	/**
	 * @param masStoOption the masStoOption to set
	 */
	public void setMasStoOption(String masStoOption) {
		this.masStoOption = masStoOption;
	}
	/**
	 * @return the medAudCasList
	 */
	public ArrayList getMedAudCasList() {
		return medAudCasList;
	}
	/**
	 * @param medAudCasList the medAudCasList to set
	 */
	public void setMedAudCasList(ArrayList medAudCasList) {
		this.medAudCasList = medAudCasList;
	}
	/**
	 * @return the medVidCasList
	 */
	public ArrayList getMedVidCasList() {
		return medVidCasList;
	}
	/**
	 * @param medVidCasList the medVidCasList to set
	 */
	public void setMedVidCasList(ArrayList medVidCasList) {
		this.medVidCasList = medVidCasList;
	}
	/**
	 * @return the medCDList
	 */
	public ArrayList getMedCDList() {
		return medCDList;
	}
	/**
	 * @param medCDList the medCDList to set
	 */
	public void setMedCDList(ArrayList medCDList) {
		this.medCDList = medCDList;
	}
	/**
	 * @return the medDVDList
	 */
	public ArrayList getMedDVDList() {
		return medDVDList;
	}
	/**
	 * @param medDVDList the medDVDList to set
	 */
	public void setMedDVDList(ArrayList medDVDList) {
		this.medDVDList = medDVDList;
	}
	/**
	 * @return the medSlideList
	 */
	public ArrayList getMedSlideList() {
		return medSlideList;
	}
	/**
	 * @param medSlideList the medSlideList to set
	 */
	public void setMedSlideList(ArrayList medSlideList) {
		this.medSlideList = medSlideList;
	}
}
