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
 * Title		: 	SCFinidSize.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * SCFinidSize is a data transfer object to store the Specification Component 
 * Finished Size details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCFinidSize implements java.io.Serializable {
	private static final long serialVersionUID = -5742146255522218561L;
	
	private SCFSLength scFSLength		= null;
	private SCFSWidth scFSWidth			= null;
	private SCFSHeight SCFSHeight		= null;
	private ArrayList scFlapWidthList	= null;
	private ArrayList scFSAddTextList	= null;
	/**
	 * Default constructor.
	 */
	public SCFinidSize() {
		super();
	}
	/**
	 * @return the scFSLength
	 */
	public SCFSLength getScFSLength() {
		return scFSLength;
	}
	/**
	 * @param scFSLength the scFSLength to set
	 */
	public void setScFSLength(SCFSLength scFSLength) {
		this.scFSLength = scFSLength;
	}
	/**
	 * @return the scFSWidth
	 */
	public SCFSWidth getScFSWidth() {
		return scFSWidth;
	}
	/**
	 * @param scFSWidth the scFSWidth to set
	 */
	public void setScFSWidth(SCFSWidth scFSWidth) {
		this.scFSWidth = scFSWidth;
	}
	/**
	 * @return the sCFSHeight
	 */
	public SCFSHeight getSCFSHeight() {
		return SCFSHeight;
	}
	/**
	 * @param height the sCFSHeight to set
	 */
	public void setSCFSHeight(SCFSHeight height) {
		SCFSHeight = height;
	}
	/**
	 * @return the scFlapWidthList
	 */
	public ArrayList getScFlapWidthList() {
		return scFlapWidthList;
	}
	/**
	 * @param scFlapWidthList the scFlapWidthList to set
	 */
	public void setScFlapWidthList(ArrayList scFlapWidthList) {
		this.scFlapWidthList = scFlapWidthList;
	}
	/**
	 * @return the scFSAddTextList
	 */
	public ArrayList getScFSAddTextList() {
		return scFSAddTextList;
	}
	/**
	 * @param scFSAddTextList the scFSAddTextList to set
	 */
	public void setScFSAddTextList(ArrayList scFSAddTextList) {
		this.scFSAddTextList = scFSAddTextList;
	}
}
