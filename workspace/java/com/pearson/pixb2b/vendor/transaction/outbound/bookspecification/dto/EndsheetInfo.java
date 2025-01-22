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
 * Title		: 	EndsheetInfo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * EndsheetInfo is a data transfer object to store the 
 * Endsheet Information details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class EndsheetInfo implements java.io.Serializable {
	private static final long serialVersionUID = -962589052237973493L;
	
	private String esType				= null;
	private String esLocType			= null;
	private String esCopyDes			= null;
	private ESMaterial esMaterial		= null;
	private ESRIMaterial esRIMaterial	= null;
	private ArrayList esInAddTextList	= null;		
	/**
	 * Default constructor.
	 */
	public EndsheetInfo() {
		super();
	}
	/**
	 * @return the esType
	 */
	public String getEsType() {
		return esType;
	}
	/**
	 * @param esType the esType to set
	 */
	public void setEsType(String esType) {
		this.esType = esType;
	}
	/**
	 * @return the esLocType
	 */
	public String getEsLocType() {
		return esLocType;
	}
	/**
	 * @param esLocType the esLocType to set
	 */
	public void setEsLocType(String esLocType) {
		this.esLocType = esLocType;
	}
	/**
	 * @return the esCopyDes
	 */
	public String getEsCopyDes() {
		return esCopyDes;
	}
	/**
	 * @param esCopyDes the esCopyDes to set
	 */
	public void setEsCopyDes(String esCopyDes) {
		this.esCopyDes = esCopyDes;
	}
	/**
	 * @return the esMaterial
	 */
	public ESMaterial getEsMaterial() {
		return esMaterial;
	}
	/**
	 * @param esMaterial the esMaterial to set
	 */
	public void setEsMaterial(ESMaterial esMaterial) {
		this.esMaterial = esMaterial;
	}
	/**
	 * @return the esRIMaterial
	 */
	public ESRIMaterial getEsRIMaterial() {
		return esRIMaterial;
	}
	/**
	 * @param esRIMaterial the esRIMaterial to set
	 */
	public void setEsRIMaterial(ESRIMaterial esRIMaterial) {
		this.esRIMaterial = esRIMaterial;
	}
	/**
	 * @return the esInAddTextList
	 */
	public ArrayList getEsInAddTextList() {
		return esInAddTextList;
	}
	/**
	 * @param esInAddTextList the esInAddTextList to set
	 */
	public void setEsInAddTextList(ArrayList esInAddTextList) {
		this.esInAddTextList = esInAddTextList;
	}
}
