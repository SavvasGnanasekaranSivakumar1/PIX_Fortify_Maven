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
 * Title		: 	ESRIMaterial.java
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
 * ESRIMaterial is a data transfer object to store the Endsheet Reinforcement
 * Material details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ESRIMaterial implements java.io.Serializable {
	private static final long serialVersionUID = -3500293266887093853L;
	
	private String esReinforcementType		= null;
	private ESRIBMaterialChars esribmChars	= null;	
	private ArrayList esRIAddTextList		= null;	
	/**
	 * Default constructor.
	 */
	public ESRIMaterial() {
		super();
	}
	/**
	 * @return the esReinforcementType
	 */
	public String getEsReinforcementType() {
		return esReinforcementType;
	}
	/**
	 * @param esReinforcementType the esReinforcementType to set
	 */
	public void setEsReinforcementType(String esReinforcementType) {
		this.esReinforcementType = esReinforcementType;
	}
	/**
	 * @return the esribmChars
	 */
	public ESRIBMaterialChars getEsribmChars() {
		return esribmChars;
	}
	/**
	 * @param esribmChars the esribmChars to set
	 */
	public void setEsribmChars(ESRIBMaterialChars esribmChars) {
		this.esribmChars = esribmChars;
	}
	/**
	 * @return the esRIAddTextList
	 */
	public ArrayList getEsRIAddTextList() {
		return esRIAddTextList;
	}
	/**
	 * @param esRIAddTextList the esRIAddTextList to set
	 */
	public void setEsRIAddTextList(ArrayList esRIAddTextList) {
		this.esRIAddTextList = esRIAddTextList;
	}
}
