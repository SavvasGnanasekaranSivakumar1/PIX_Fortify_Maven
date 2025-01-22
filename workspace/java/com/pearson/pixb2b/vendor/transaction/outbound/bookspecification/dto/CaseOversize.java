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
 * Title		: 	CaseOversize.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CaseOversize is a data transfer object to store the specification Binding   
 * CaseMaking CaseOversize Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CaseOversize implements java.io.Serializable {
	private static final long serialVersionUID = 6611354584975597885L;
	
	private COSLength cosLength = null;
	private COSWidth cosWidth 	= null;
	/**
	 * Default constructor.
	 */
	public CaseOversize() {
		super();
	}
	/**
	 * @return the cosLength
	 */
	public COSLength getCosLength() {
		return cosLength;
	}
	/**
	 * @param cosLength the cosLength to set
	 */
	public void setCosLength(COSLength cosLength) {
		this.cosLength = cosLength;
	}
	/**
	 * @return the cosWidth
	 */
	public COSWidth getCosWidth() {
		return cosWidth;
	}
	/**
	 * @param cosWidth the cosWidth to set
	 */
	public void setCosWidth(COSWidth cosWidth) {
		this.cosWidth = cosWidth;
	}
}
