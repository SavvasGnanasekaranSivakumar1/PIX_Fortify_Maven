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
 * Title		: 	JBColSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * JBColSpec is a data transfer object to store the Specification NonPress Component
 * CD JewelBox ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class JBColSpec implements java.io.Serializable {
	private static final long serialVersionUID = -4926627857539728384L;
	
	private String colLocation		 = null;
	private JBCSColCode jbCSColCode  = null;
	private String colDesp			 = null;
	private String inkTypeDesp		 = null;
	private String colMatDescp		 = null;
	private JBCSClShade jbCSClShade  = null;
	private JBCSInkCov jbCSInkCov	 = null;
	/**
	 * Default constructor.
	 */
	public JBColSpec() {
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
	 * @return the jbCSColCode
	 */
	public JBCSColCode getJbCSColCode() {
		return jbCSColCode;
	}
	/**
	 * @param jbCSColCode the jbCSColCode to set
	 */
	public void setJbCSColCode(JBCSColCode jbCSColCode) {
		this.jbCSColCode = jbCSColCode;
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
	 * @return the jbCSClShade
	 */
	public JBCSClShade getJbCSClShade() {
		return jbCSClShade;
	}
	/**
	 * @param jbCSClShade the jbCSClShade to set
	 */
	public void setJbCSClShade(JBCSClShade jbCSClShade) {
		this.jbCSClShade = jbCSClShade;
	}
	/**
	 * @return the jbCSInkCov
	 */
	public JBCSInkCov getJbCSInkCov() {
		return jbCSInkCov;
	}
	/**
	 * @param jbCSInkCov the jbCSInkCov to set
	 */
	public void setJbCSInkCov(JBCSInkCov jbCSInkCov) {
		this.jbCSInkCov = jbCSInkCov;
	}
}
