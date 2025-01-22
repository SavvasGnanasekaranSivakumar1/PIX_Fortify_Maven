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
 * Title		: 	CDColSpecs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CDColSpecs is a data transfer object to store the Specification NonPress Component Media
 * CD ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDColSpecs implements java.io.Serializable {
	private static final long serialVersionUID = -5571331822003901550L;
	
	private String colLocation		 = null;
	private CDCSColCode cdCSColCode  = null;
	private String colDesp			 = null;
	private String inkTypeDesp		 = null;
	private String colMatDescp		 = null;
	private CDCSClShade cdCSClShade  = null;
	private CDCSInkCov cdCSInkCov	 = null;
	/**
	 * Default constructor.
	 */
	public CDColSpecs() {
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
	 * @return the cdCSColCode
	 */
	public CDCSColCode getCdCSColCode() {
		return cdCSColCode;
	}
	/**
	 * @param cdCSColCode the cdCSColCode to set
	 */
	public void setCdCSColCode(CDCSColCode cdCSColCode) {
		this.cdCSColCode = cdCSColCode;
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
	 * @return the cdCSClShade
	 */
	public CDCSClShade getCdCSClShade() {
		return cdCSClShade;
	}
	/**
	 * @param cdCSClShade the cdCSClShade to set
	 */
	public void setCdCSClShade(CDCSClShade cdCSClShade) {
		this.cdCSClShade = cdCSClShade;
	}
	/**
	 * @return the cdCSInkCov
	 */
	public CDCSInkCov getCdCSInkCov() {
		return cdCSInkCov;
	}
	/**
	 * @param cdCSInkCov the cdCSInkCov to set
	 */
	public void setCdCSInkCov(CDCSInkCov cdCSInkCov) {
		this.cdCSInkCov = cdCSInkCov;
	}
}
