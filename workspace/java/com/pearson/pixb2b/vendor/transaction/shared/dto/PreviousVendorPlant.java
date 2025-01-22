package com.pearson.pixb2b.vendor.transaction.shared.dto;



import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.OtherPartyNameAddress;


public class PreviousVendorPlant implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7842678850949230861L;
	private PreviousVendorNameAddress previousVendorNameAddress = null;
	private String otherPartyType 						= null;
	
	public PreviousVendorNameAddress getPreviousVendorNameAddress() {
		return previousVendorNameAddress;
	}
	public void setPreviousVendorNameAddress(
			PreviousVendorNameAddress previousVendorNameAddress) {
		this.previousVendorNameAddress = previousVendorNameAddress;
	}
	public String getOtherPartyType() {
		return otherPartyType;
	}
	public void setOtherPartyType(String otherPartyType) {
		this.otherPartyType = otherPartyType;
	}
	
	
	
	
}
