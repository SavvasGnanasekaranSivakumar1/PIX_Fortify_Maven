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
 * Title		: 	BBEdgeTrim.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * BBEdgeTrim is a data transfer object to store the Book Block 
 * EdgeTrim details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBEdgeTrim implements java.io.Serializable {
	private static final long serialVersionUID = 5727541899318208810L;
	
	private String edgeLocType		= null;
	private String edgeFinish 		= null;
	private ColourSpecs colSpec 	= null;
	private ArrayList bbAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public BBEdgeTrim() {
		super();
	}
	/**
	 * @return the edgeLocType
	 */
	public String getEdgeLocType() {
		return edgeLocType;
	}
	/**
	 * @param edgeLocType the edgeLocType to set
	 */
	public void setEdgeLocType(String edgeLocType) {
		this.edgeLocType = edgeLocType;
	}
	/**
	 * @return the edgeFinish
	 */
	public String getEdgeFinish() {
		return edgeFinish;
	}
	/**
	 * @param edgeFinish the edgeFinish to set
	 */
	public void setEdgeFinish(String edgeFinish) {
		this.edgeFinish = edgeFinish;
	}
	/**
	 * @return the colSpec
	 */
	public ColourSpecs getColSpec() {
		return colSpec;
	}
	/**
	 * @param colSpec the colSpec to set
	 */
	public void setColSpec(ColourSpecs colSpec) {
		this.colSpec = colSpec;
	}
	/**
	 * @return the bbAddTextList
	 */
	public ArrayList getBbAddTextList() {
		return bbAddTextList;
	}
	/**
	 * @param bbAddTextList the bbAddTextList to set
	 */
	public void setBbAddTextList(ArrayList bbAddTextList) {
		this.bbAddTextList = bbAddTextList;
	}
}