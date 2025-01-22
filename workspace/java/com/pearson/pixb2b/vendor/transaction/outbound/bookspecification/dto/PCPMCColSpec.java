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
 * Title		: 	PCPMCColSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCColSpec is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Characteristics
 * ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCColSpec implements java.io.Serializable {
	private static final long serialVersionUID = 2979441752065844912L;
	
	private String colLocation			= null;
	private PCPMCColCode pcPMCColCode	= null;
	private String colDesp				= null;
	private String inkTypeDesp			= null;
	private String colMatDescp			= null;
	private PCPMCClShade pcPMCClShade	= null;
	private PCPMCInkCov pcPMCInkCov		= null;
	/**
	 * Default constructor.
	 */
	public PCPMCColSpec() {
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
	 * @return the pcPMCColCode
	 */
	public PCPMCColCode getPcPMCColCode() {
		return pcPMCColCode;
	}
	/**
	 * @param pcPMCColCode the pcPMCColCode to set
	 */
	public void setPcPMCColCode(PCPMCColCode pcPMCColCode) {
		this.pcPMCColCode = pcPMCColCode;
	}
	/**
	 * @return the colDesp
	 */
	public String getColDesp() {
		return colDesp;
	}
	/**
	 * @param colDesp the colDesp to set
	 */
	public void setColDesp(String colDesp) {
		this.colDesp = colDesp;
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
	 * @return the pcPMCClShade
	 */
	public PCPMCClShade getPcPMCClShade() {
		return pcPMCClShade;
	}
	/**
	 * @param pcPMCClShade the pcPMCClShade to set
	 */
	public void setPcPMCClShade(PCPMCClShade pcPMCClShade) {
		this.pcPMCClShade = pcPMCClShade;
	}
	/**
	 * @return the pcPMCInkCov
	 */
	public PCPMCInkCov getPcPMCInkCov() {
		return pcPMCInkCov;
	}
	/**
	 * @param pcPMCInkCov the pcPMCInkCov to set
	 */
	public void setPcPMCInkCov(PCPMCInkCov pcPMCInkCov) {
		this.pcPMCInkCov = pcPMCInkCov;
	}
}
