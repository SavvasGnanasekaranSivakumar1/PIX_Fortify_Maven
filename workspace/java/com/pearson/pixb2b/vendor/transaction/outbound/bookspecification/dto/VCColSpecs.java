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
 * Title		: 	VCColSpecs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * VCColSpecs is a data transfer object to store the Specification NonPress Component 
 * Video Cassette Label Characteristics ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class VCColSpecs implements java.io.Serializable {
	private static final long serialVersionUID = 1982324397422496898L;
	
	private String colLocation		 = null;
	private VCCSColCode vcCSColCode	 = null;
	private String colDesp			 = null;
	private String inkTypeDesp		 = null;
	private String colMatDescp		 = null;
	private VCCSClShade vcPMCClShade = null;
	private VCCSInkCov vcPMCInkCov	 = null;
	/**
	 * Default constructor.
	 */
	public VCColSpecs() {
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
	 * @return the vcCSColCode
	 */
	public VCCSColCode getVcCSColCode() {
		return vcCSColCode;
	}
	/**
	 * @param vcCSColCode the vcCSColCode to set
	 */
	public void setVcCSColCode(VCCSColCode vcCSColCode) {
		this.vcCSColCode = vcCSColCode;
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
	 * @return the vcPMCClShade
	 */
	public VCCSClShade getVcPMCClShade() {
		return vcPMCClShade;
	}
	/**
	 * @param vcPMCClShade the vcPMCClShade to set
	 */
	public void setVcPMCClShade(VCCSClShade vcPMCClShade) {
		this.vcPMCClShade = vcPMCClShade;
	}
	/**
	 * @return the vcPMCInkCov
	 */
	public VCCSInkCov getVcPMCInkCov() {
		return vcPMCInkCov;
	}
	/**
	 * @param vcPMCInkCov the vcPMCInkCov to set
	 */
	public void setVcPMCInkCov(VCCSInkCov vcPMCInkCov) {
		this.vcPMCInkCov = vcPMCInkCov;
	}
}
