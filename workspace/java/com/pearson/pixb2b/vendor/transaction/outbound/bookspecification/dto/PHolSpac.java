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
 * Title		: 	PHolSpac.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PHolSpac is a data transfer object to store the specification Binding   
 * PunchedHole Hole Spacing details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PHolSpac implements java.io.Serializable {
	private static final long serialVersionUID = 6855210321513264351L;
	
	private EvenSpacing evenSpacing	= null;
	private String unevenSpacing 	= null;
	/**
	 * Default constructor.
	 */
	public PHolSpac() {
		super();
	}
	/**
	 * @return the evenSpacing
	 */
	public EvenSpacing getEvenSpacing() {
		return evenSpacing;
	}
	/**
	 * @param evenSpacing the evenSpacing to set
	 */
	public void setEvenSpacing(EvenSpacing evenSpacing) {
		this.evenSpacing = evenSpacing;
	}
	/**
	 * @return the unevenSpacing
	 */
	public String getUnevenSpacing() {
		return unevenSpacing;
	}
	/**
	 * @param unevenSpacing the unevenSpacing to set
	 */
	public void setUnevenSpacing(String unevenSpacing) {
		this.unevenSpacing = unevenSpacing;
	}
}
