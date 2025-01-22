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
 * Title		: 	MMCWireMatl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MMCWireMatl is a data transfer object to store the Mechanical Wire Material  
 * Characteristics details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MMCWireMatl implements java.io.Serializable {
	private static final long serialVersionUID = 7497720265910199913L;
	
	private String wireCoating 			= null;
	private MMCWireGauge mmcWireGauge	= null;
	/**
	 * Default constructor.
	 */
	public MMCWireMatl() {
		super();
	}
	/**
	 * @return the wireCoating
	 */
	public String getWireCoating() {
		return wireCoating;
	}
	/**
	 * @param wireCoating the wireCoating to set
	 */
	public void setWireCoating(String wireCoating) {
		this.wireCoating = wireCoating;
	}
	/**
	 * @return the mmcWireGauge
	 */
	public MMCWireGauge getMmcWireGauge() {
		return mmcWireGauge;
	}
	/**
	 * @param mmcWireGauge the mmcWireGauge to set
	 */
	public void setMmcWireGauge(MMCWireGauge mmcWireGauge) {
		this.mmcWireGauge = mmcWireGauge;
	}
}