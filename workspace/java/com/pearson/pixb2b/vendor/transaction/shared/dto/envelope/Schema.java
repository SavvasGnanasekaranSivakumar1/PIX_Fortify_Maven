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
 * Title		: 	Schema.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;


/**
 * Schema is a data transfer object to store the Schema
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class Schema implements java.io.Serializable {
	private static final long serialVersionUID = 878418629304723978L;
	
	private String version	 = null;
	private String build	 = null;
	private String schemaVal = null;
	/**
	 * Default constructor.
	 */
	public Schema() {
		super();
	}
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * @return the build
	 */
	public String getBuild() {
		return build;
	}
	/**
	 * @param build the build to set
	 */
	public void setBuild(String build) {
		this.build = build;
	}
	/**
	 * @return the schemaVal
	 */
	public String getSchemaVal() {
		return schemaVal;
	}
	/**
	 * @param schemaVal the schemaVal to set
	 */
	public void setSchemaVal(String schemaVal) {
		this.schemaVal = schemaVal;
	}
}
