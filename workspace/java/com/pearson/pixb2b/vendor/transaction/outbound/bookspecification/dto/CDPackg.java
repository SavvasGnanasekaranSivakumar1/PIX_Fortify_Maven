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
 * Title		: 	CDPackg.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CDPackg is a data transfer object to store the Specification NonPress Component Media
 * CD Packaging details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDPackg implements java.io.Serializable {
	private static final long serialVersionUID = -1434475332809405271L;
	
	private String bulNumOfUnit	= null;
	private CDJewlBox cdJewlBox	= null;
	private CDSleeve cdSleeve	= null;
	/**
	 * Default constructor.
	 */
	public CDPackg() {
		super();
	}
	/**
	 * @return the bulNumOfUnit
	 */
	public String getBulNumOfUnit() {
		return bulNumOfUnit;
	}
	/**
	 * @param bulNumOfUnit the bulNumOfUnit to set
	 */
	public void setBulNumOfUnit(String bulNumOfUnit) {
		this.bulNumOfUnit = bulNumOfUnit;
	}
	/**
	 * @return the cdJewlBox
	 */
	public CDJewlBox getCdJewlBox() {
		return cdJewlBox;
	}
	/**
	 * @param cdJewlBox the cdJewlBox to set
	 */
	public void setCdJewlBox(CDJewlBox cdJewlBox) {
		this.cdJewlBox = cdJewlBox;
	}
	/**
	 * @return the cdSleeve
	 */
	public CDSleeve getCdSleeve() {
		return cdSleeve;
	}
	/**
	 * @param cdSleeve the cdSleeve to set
	 */
	public void setCdSleeve(CDSleeve cdSleeve) {
		this.cdSleeve = cdSleeve;
	}
}
