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
 * Title		: 	MSColSpec.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSColSpec is a data transfer object to store the Specification NonPress Component 
 * Media Slide ColourSpecs details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSColSpec implements java.io.Serializable {
	private static final long serialVersionUID = -3234371693768829973L;
	
	private String colLocation		 = null;
	private MSCSColCode msCSColCode  = null;
	private String colDesp			 = null;
	private String inkTypeDesp		 = null;
	private String colMatDescp		 = null;
	private MSCSClShade msCSClShade  = null;
	private MSCSInkCov msCSInkCov	 = null;
	/**
	 * Default constructor.
	 */
	public MSColSpec() {
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
	 * @return the msCSColCode
	 */
	public MSCSColCode getMsCSColCode() {
		return msCSColCode;
	}
	/**
	 * @param msCSColCode the msCSColCode to set
	 */
	public void setMsCSColCode(MSCSColCode msCSColCode) {
		this.msCSColCode = msCSColCode;
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
	 * @return the msCSClShade
	 */
	public MSCSClShade getMsCSClShade() {
		return msCSClShade;
	}
	/**
	 * @param msCSClShade the msCSClShade to set
	 */
	public void setMsCSClShade(MSCSClShade msCSClShade) {
		this.msCSClShade = msCSClShade;
	}
	/**
	 * @return the msCSInkCov
	 */
	public MSCSInkCov getMsCSInkCov() {
		return msCSInkCov;
	}
	/**
	 * @param msCSInkCov the msCSInkCov to set
	 */
	public void setMsCSInkCov(MSCSInkCov msCSInkCov) {
		this.msCSInkCov = msCSInkCov;
	}
}
