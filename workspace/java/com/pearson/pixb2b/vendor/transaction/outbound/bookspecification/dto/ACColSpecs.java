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
 * Title		: 	ACColSpecs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ACColSpecs is a data transfer object to store the Specification NonPress Component 
 * Audio Cassette Label Characteristics ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACColSpecs implements java.io.Serializable {
	private static final long serialVersionUID = 4313421006580785683L;
	
	private String colLocation		 = null;
	private ACCSColCode acCSColCode	 = null;
	private String colDesp			 = null;
	private String inkTypeDesp		 = null;
	private String colMatDescp		 = null;
	private ACCSClShade acPMCClShade = null;
	private ACCSInkCov acPMCInkCov	 = null;
	/**
	 * Default constructor.
	 */
	public ACColSpecs() {
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
	 * @return the acCSColCode
	 */
	public ACCSColCode getAcCSColCode() {
		return acCSColCode;
	}
	/**
	 * @param acCSColCode the acCSColCode to set
	 */
	public void setAcCSColCode(ACCSColCode acCSColCode) {
		this.acCSColCode = acCSColCode;
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
	 * @return the acPMCClShade
	 */
	public ACCSClShade getAcPMCClShade() {
		return acPMCClShade;
	}
	/**
	 * @param acPMCClShade the acPMCClShade to set
	 */
	public void setAcPMCClShade(ACCSClShade acPMCClShade) {
		this.acPMCClShade = acPMCClShade;
	}
	/**
	 * @return the acPMCInkCov
	 */
	public ACCSInkCov getAcPMCInkCov() {
		return acPMCInkCov;
	}
	/**
	 * @param acPMCInkCov the acPMCInkCov to set
	 */
	public void setAcPMCInkCov(ACCSInkCov acPMCInkCov) {
		this.acPMCInkCov = acPMCInkCov;
	}
}
