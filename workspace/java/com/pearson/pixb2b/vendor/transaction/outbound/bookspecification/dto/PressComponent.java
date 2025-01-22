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
 * Title		: 	PressComponent.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PressComponent is a data transfer object to store the Specification 
 * Press Component details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PressComponent implements java.io.Serializable {
	private static final long serialVersionUID = -8696926330611931092L;
	
	private String bcSuppByPub		= null;
	private String numbOfPages 		= null;
	private ArrayList pcSignList	= null;
	private PCPressPrep pcPressPrep = null;
	private PCManfSpec pcManfSpec	= null;
	/**
	 * Default constructor.
	 */
	public PressComponent() {
		super();
	}
	/**
	 * @return the bcSuppByPub
	 */
	public String getBcSuppByPub() {
		return bcSuppByPub;
	}
	/**
	 * @param bcSuppByPub the bcSuppByPub to set
	 */
	public void setBcSuppByPub(String bcSuppByPub) {
		this.bcSuppByPub = bcSuppByPub;
	}
	/**
	 * @return the numbOfPages
	 */
	public String getNumbOfPages() {
		return numbOfPages;
	}
	/**
	 * @param numbOfPages the numbOfPages to set
	 */
	public void setNumbOfPages(String numbOfPages) {
		this.numbOfPages = numbOfPages;
	}
	/**
	 * @return the pcSignList
	 */
	public ArrayList getPcSignList() {
		return pcSignList;
	}
	/**
	 * @param pcSignList the pcSignList to set
	 */
	public void setPcSignList(ArrayList pcSignList) {
		this.pcSignList = pcSignList;
	}
	/**
	 * @return the pcPressPrep
	 */
	public PCPressPrep getPcPressPrep() {
		return pcPressPrep;
	}
	/**
	 * @param pcPressPrep the pcPressPrep to set
	 */
	public void setPcPressPrep(PCPressPrep pcPressPrep) {
		this.pcPressPrep = pcPressPrep;
	}
	/**
	 * @return the pcManfSpec
	 */
	public PCManfSpec getPcManfSpec() {
		return pcManfSpec;
	}
	/**
	 * @param pcManfSpec the pcManfSpec to set
	 */
	public void setPcManfSpec(PCManfSpec pcManfSpec) {
		this.pcManfSpec = pcManfSpec;
	}
}
