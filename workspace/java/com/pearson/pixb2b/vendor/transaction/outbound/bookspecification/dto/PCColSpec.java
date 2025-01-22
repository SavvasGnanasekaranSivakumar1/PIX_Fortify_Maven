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
 * Title		: 	PCColSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * PCColSpec is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Ink Characteristics ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCColSpec implements java.io.Serializable {
	private static final long serialVersionUID = -8286964254672125467L;
	
	private String colLocation		= null;
	private PCCSColCode pcCSColCode	= null;
	//private String colDesp		= null;
	private ArrayList colDespList	= null;
	private String inkTypeDesp		= null;
	private String colMatDescp		= null;
	private PCCSClShade pcCSClShade	= null;
	private PCCSInkCov pcCSInkCov	= null;
	/**
	 * Default constructor.
	 */
	public PCColSpec() {
		super();
	}
	/**
	 * @return the colLocation
	 */
	public String getColLocation() {
		return colLocation;
	}
	/**
	 * @param colLocation the colLocation to set
	 */
	public void setColLocation(String colLocation) {
		this.colLocation = colLocation;
	}
	/**
	 * @return the pcCSColCode
	 */
	public PCCSColCode getPcCSColCode() {
		return pcCSColCode;
	}
	/**
	 * @param pcCSColCode the pcCSColCode to set
	 */
	public void setPcCSColCode(PCCSColCode pcCSColCode) {
		this.pcCSColCode = pcCSColCode;
	}
	/**
	 * @return the colDespList
	 */
	public ArrayList getColDespList() {
		return colDespList;
	}
	/**
	 * @param colDespList the colDespList to set
	 */
	public void setColDespList(ArrayList colDespList) {
		this.colDespList = colDespList;
	}
	/**
	 * @return the inkTypeDesp
	 */
	public String getInkTypeDesp() {
		return inkTypeDesp;
	}
	/**
	 * @param inkTypeDesp the inkTypeDesp to set
	 */
	public void setInkTypeDesp(String inkTypeDesp) {
		this.inkTypeDesp = inkTypeDesp;
	}
	/**
	 * @return the colMatDescp
	 */
	public String getColMatDescp() {
		return colMatDescp;
	}
	/**
	 * @param colMatDescp the colMatDescp to set
	 */
	public void setColMatDescp(String colMatDescp) {
		this.colMatDescp = colMatDescp;
	}
	/**
	 * @return the pcCSClShade
	 */
	public PCCSClShade getPcCSClShade() {
		return pcCSClShade;
	}
	/**
	 * @param pcCSClShade the pcCSClShade to set
	 */
	public void setPcCSClShade(PCCSClShade pcCSClShade) {
		this.pcCSClShade = pcCSClShade;
	}
	/**
	 * @return the pcCSInkCov
	 */
	public PCCSInkCov getPcCSInkCov() {
		return pcCSInkCov;
	}
	/**
	 * @param pcCSInkCov the pcCSInkCov to set
	 */
	public void setPcCSInkCov(PCCSInkCov pcCSInkCov) {
		this.pcCSInkCov = pcCSInkCov;
	}
}
