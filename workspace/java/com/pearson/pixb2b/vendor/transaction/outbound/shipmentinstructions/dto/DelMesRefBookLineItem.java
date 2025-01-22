/**
 * 
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * @author Ranu.Sharma
 *
 */
public class DelMesRefBookLineItem {

private static final long serialVersionUID = -9212781850258512379L;
	
	private String delMesRefVal 	= null;
	private String delMesRefType 	= null;
	private String assignedBy		= null;
	/**
	 * Default constructor.
	 */
	public DelMesRefBookLineItem() {
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
