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
 * Title		: 	PCMargins.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCMargins s a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications Margins details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCMargins implements java.io.Serializable {
	private static final long serialVersionUID = 6284565851408959781L;
	
	private MarHead marHead		= null;
	private MarThumb marThumb	= null;
	private MarGutter marGutter	= null;
	private MarFoot marFoot		= null;
	/**
	 * Default constructor.
	 */
	public PCMargins() {
		super();
	}
	/**
	 * @return the marHead
	 */
	public MarHead getMarHead() {
		return marHead;
	}
	/**
	 * @param marHead the marHead to set
	 */
	public void setMarHead(MarHead marHead) {
		this.marHead = marHead;
	}
	/**
	 * @return the marThumb
	 */
	public MarThumb getMarThumb() {
		return marThumb;
	}
	/**
	 * @param marThumb the marThumb to set
	 */
	public void setMarThumb(MarThumb marThumb) {
		this.marThumb = marThumb;
	}
	/**
	 * @return the marGutter
	 */
	public MarGutter getMarGutter() {
		return marGutter;
	}
	/**
	 * @param marGutter the marGutter to set
	 */
	public void setMarGutter(MarGutter marGutter) {
		this.marGutter = marGutter;
	}
	/**
	 * @return the marFoot
	 */
	public MarFoot getMarFoot() {
		return marFoot;
	}
	/**
	 * @param marFoot the marFoot to set
	 */
	public void setMarFoot(MarFoot marFoot) {
		this.marFoot = marFoot;
	}
}
