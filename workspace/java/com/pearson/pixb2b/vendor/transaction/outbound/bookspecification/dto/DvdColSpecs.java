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
 * Title		: 	DvdColSpecs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdColSpecs is a data transfer object to store the Specification NonPress Component Media
 * DVD ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdColSpecs implements java.io.Serializable {
	private static final long serialVersionUID = -7824726118644811633L;
	
	private String colLocation		 	= null;
	private DvdCSColCode dvdCSColCode	= null;
	private String colDesp				= null;
	private String inkTypeDesp		 	= null;
	private String colMatDescp		 	= null;
	private DvdCSClShade dvdCSClShade  	= null;
	private DvdCSInkCov dvdCSInkCov		= null;
	/**
	 * Default constructor.
	 */
	public DvdColSpecs() {
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
	 * @return the dvdCSColCode
	 */
	public DvdCSColCode getDvdCSColCode() {
		return dvdCSColCode;
	}
	/**
	 * @param dvdCSColCode the dvdCSColCode to set
	 */
	public void setDvdCSColCode(DvdCSColCode dvdCSColCode) {
		this.dvdCSColCode = dvdCSColCode;
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
	 * @return the dvdCSClShade
	 */
	public DvdCSClShade getDvdCSClShade() {
		return dvdCSClShade;
	}
	/**
	 * @param dvdCSClShade the dvdCSClShade to set
	 */
	public void setDvdCSClShade(DvdCSClShade dvdCSClShade) {
		this.dvdCSClShade = dvdCSClShade;
	}
	/**
	 * @return the dvdCSInkCov
	 */
	public DvdCSInkCov getDvdCSInkCov() {
		return dvdCSInkCov;
	}
	/**
	 * @param dvdCSInkCov the dvdCSInkCov to set
	 */
	public void setDvdCSInkCov(DvdCSInkCov dvdCSInkCov) {
		this.dvdCSInkCov = dvdCSInkCov;
	}
}
