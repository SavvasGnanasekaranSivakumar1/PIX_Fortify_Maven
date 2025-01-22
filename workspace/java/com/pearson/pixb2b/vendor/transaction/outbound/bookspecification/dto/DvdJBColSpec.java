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
 * Title		: 	DvdJBColSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdJBColSpec is a data transfer object to store the Specification NonPress Component
 * DVD JewelBox ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdJBColSpec implements java.io.Serializable {
	private static final long serialVersionUID = -9153331109896556267L;
	
	private String colLocation		 		= null;
	private DvdJBCSColCode jbDvdCSColCode  	= null;
	private String colDesp					= null;
	private String inkTypeDesp				= null;
	private String colMatDescp				= null;
	private DvdJBCSClShade jbDvdCSClShade  	= null;
	private DvdJBCSInkCov jbDvdCSInkCov	 	= null;
	/**
	 * Default constructor.
	 */
	public DvdJBColSpec() {
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
	 * @return the jbDvdCSColCode
	 */
	public DvdJBCSColCode getJbDvdCSColCode() {
		return jbDvdCSColCode;
	}
	/**
	 * @param jbDvdCSColCode the jbDvdCSColCode to set
	 */
	public void setJbDvdCSColCode(DvdJBCSColCode jbDvdCSColCode) {
		this.jbDvdCSColCode = jbDvdCSColCode;
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
	 * @return the jbDvdCSClShade
	 */
	public DvdJBCSClShade getJbDvdCSClShade() {
		return jbDvdCSClShade;
	}
	/**
	 * @param jbDvdCSClShade the jbDvdCSClShade to set
	 */
	public void setJbDvdCSClShade(DvdJBCSClShade jbDvdCSClShade) {
		this.jbDvdCSClShade = jbDvdCSClShade;
	}
	/**
	 * @return the jbDvdCSInkCov
	 */
	public DvdJBCSInkCov getJbDvdCSInkCov() {
		return jbDvdCSInkCov;
	}
	/**
	 * @param jbDvdCSInkCov the jbDvdCSInkCov to set
	 */
	public void setJbDvdCSInkCov(DvdJBCSInkCov jbDvdCSInkCov) {
		this.jbDvdCSInkCov = jbDvdCSInkCov;
	}
}
