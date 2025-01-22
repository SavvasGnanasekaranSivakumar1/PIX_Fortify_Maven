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
 * Title		: 	PCSign.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCSign is a data transfer object to store the Specification 
 * Press Component Signatures details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCSign implements java.io.Serializable {
	private static final long serialVersionUID = -1611466258384007902L;
	
	private String pagPerSig = null;
	private String numOfSig	 = null;
	private String sigID	 = null;
	/**
	 * Default constructor.
	 */
	public PCSign() {
		super();
	}
	/**
	 * @return the pagPerSig
	 */
	public String getPagPerSig() {
		return pagPerSig;
	}
	/**
	 * @param pagPerSig the pagPerSig to set
	 */
	public void setPagPerSig(String pagPerSig) {
		this.pagPerSig = pagPerSig;
	}
	/**
	 * @return the numOfSig
	 */
	public String getNumOfSig() {
		return numOfSig;
	}
	/**
	 * @param numOfSig the numOfSig to set
	 */
	public void setNumOfSig(String numOfSig) {
		this.numOfSig = numOfSig;
	}
	/**
	 * @return the sigID
	 */
	public String getSigID() {
		return sigID;
	}
	/**
	 * @param sigID the sigID to set
	 */
	public void setSigID(String sigID) {
		this.sigID = sigID;
	}
}
