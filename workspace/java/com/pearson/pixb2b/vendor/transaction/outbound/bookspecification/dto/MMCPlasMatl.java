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
 * Title		: 	MMCPlasMatl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MMCPlasMatl is a data transfer object to store the Mechanical Plastic Material  
 * Characteristics details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MMCPlasMatl implements java.io.Serializable {
	private static final long serialVersionUID = 3464481332777795588L;
	
	private String plaPrinting			= null;
	private String plaMatType			= null;
	private MMCThickness mmcThickness	= null;
	private String plaPrintDesp			= null;
	/**
	 * Default constructor.
	 */
	public MMCPlasMatl() {
		super();
	}
	/**
	 * @return the plaPrinting
	 */
	public String getPlaPrinting() {
		return plaPrinting;
	}
	/**
	 * @param plaPrinting the plaPrinting to set
	 */
	public void setPlaPrinting(String plaPrinting) {
		this.plaPrinting = plaPrinting;
	}
	/**
	 * @return the plaMatType
	 */
	public String getPlaMatType() {
		return plaMatType;
	}
	/**
	 * @param plaMatType the plaMatType to set
	 */
	public void setPlaMatType(String plaMatType) {
		this.plaMatType = plaMatType;
	}
	/**
	 * @return the mmcThickness
	 */
	public MMCThickness getMmcThickness() {
		return mmcThickness;
	}
	/**
	 * @param mmcThickness the mmcThickness to set
	 */
	public void setMmcThickness(MMCThickness mmcThickness) {
		this.mmcThickness = mmcThickness;
	}
	/**
	 * @return the plaPrintDesp
	 */
	public String getPlaPrintDesp() {
		return plaPrintDesp;
	}
	/**
	 * @param plaPrintDesp the plaPrintDesp to set
	 */
	public void setPlaPrintDesp(String plaPrintDesp) {
		this.plaPrintDesp = plaPrintDesp;
	}
}