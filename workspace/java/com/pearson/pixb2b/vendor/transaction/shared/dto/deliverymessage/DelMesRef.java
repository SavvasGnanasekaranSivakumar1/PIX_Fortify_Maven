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
 * Title		: 	DelMesRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * DelMesRef is a data transfer object to store the DeliveryMessage Reference details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesRef implements java.io.Serializable {
	private static final long serialVersionUID = -9212781850258512379L;
	
	private String delMesRefVal 	= null;
	private String delMesRefType 	= null;
	private String assignedBy		= null;
	/**
	 * Default constructor.
	 */
	public DelMesRef() {
		super();
	}
	/**
	 * @return the delMesRefVal
	 */
	public String getDelMesRefVal() {
		return delMesRefVal;
	}
	/**
	 * @param delMesRefVal the delMesRefVal to set
	 */
	public void setDelMesRefVal(String delMesRefVal) {
		this.delMesRefVal = delMesRefVal;
	}
	/**
	 * @return the delMesRefType
	 */
	public String getDelMesRefType() {
		return delMesRefType;
	}
	/**
	 * @param delMesRefType the delMesRefType to set
	 */
	public void setDelMesRefType(String delMesRefType) {
		this.delMesRefType = delMesRefType;
	}
	/**
	 * @return the assignedBy
	 */
	public String getAssignedBy() {
		return assignedBy;
	}
	/**
	 * @param assignedBy the assignedBy to set
	 */
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
}