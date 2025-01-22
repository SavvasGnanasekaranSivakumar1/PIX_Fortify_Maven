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
 * Title		: 	ColourSpecs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ColourSpecs is a data transfer object to store the Book Block 
 * EdgeTrim ColourSpecs details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ColourSpecs implements java.io.Serializable {
	private static final long serialVersionUID = 5130586392172513471L;
	
	private String colLoc			= null;
	private BBColCode bbcolCode		= null;
	private String colDes			= null;
	private String inkTypeDes		= null;
	private String colMatchDes		= null;
	private BBColShade bbColShade	= null;
	private BBInkCov bbInkCov	 	= null; 
	/**
	 * Default constructor.
	 */
	public ColourSpecs() {
		super();
	}
	/**
	 * @return the colLoc
	 */
	public String getColLoc() {
		return colLoc;
	}
	/**
	 * @param colLoc the colLoc to set
	 */
	public void setColLoc(String colLoc) {
		this.colLoc = colLoc;
	}
	/**
	 * @return the bbcolCode
	 */
	public BBColCode getBbcolCode() {
		return bbcolCode;
	}
	/**
	 * @param bbcolCode the bbcolCode to set
	 */
	public void setBbcolCode(BBColCode bbcolCode) {
		this.bbcolCode = bbcolCode;
	}
	/**
	 * @return the colDes
	 */
	public String getColDes() {
		return colDes;
	}
	/**
	 * @param colDes the colDes to set
	 */
	public void setColDes(String colDes) {
		this.colDes = colDes;
	}
	/**
	 * @return the inkTypeDes
	 */
	public String getInkTypeDes() {
		return inkTypeDes;
	}
	/**
	 * @param inkTypeDes the inkTypeDes to set
	 */
	public void setInkTypeDes(String inkTypeDes) {
		this.inkTypeDes = inkTypeDes;
	}
	/**
	 * @return the colMatchDes
	 */
	public String getColMatchDes() {
		return colMatchDes;
	}
	/**
	 * @param colMatchDes the colMatchDes to set
	 */
	public void setColMatchDes(String colMatchDes) {
		this.colMatchDes = colMatchDes;
	}
	/**
	 * @return the bbColShade
	 */
	public BBColShade getBbColShade() {
		return bbColShade;
	}
	/**
	 * @param bbColShade the bbColShade to set
	 */
	public void setBbColShade(BBColShade bbColShade) {
		this.bbColShade = bbColShade;
	}
	/**
	 * @return the bbInkCov
	 */
	public BBInkCov getBbInkCov() {
		return bbInkCov;
	}
	/**
	 * @param bbInkCov the bbInkCov to set
	 */
	public void setBbInkCov(BBInkCov bbInkCov) {
		this.bbInkCov = bbInkCov;
	}
}