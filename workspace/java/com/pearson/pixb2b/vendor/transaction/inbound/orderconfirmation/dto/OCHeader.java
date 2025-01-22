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
 * Title		: 	OCHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;

import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.BuyerParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.SupplierParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.ShipToCharacteristics;

/**
 * OCHeader is a data transfer object to store the 
 * O C  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class OCHeader implements java.io.Serializable {	
	private static final long serialVersionUID = -8978560462110746922L;
	
	private String ocHeaderStatusType	= null;
	private String ocNumber 			= null;
	private OCIssueDate ocIssueDate 	= null;
	private POInformation poInformation	= null;
	private ArrayList ocReference 		= null;
	private BuyerParty ocBuyer 			= null;
	private SupplierParty ocSupplier 	= null;
	private ShipToCharacteristics ocShipTO = null;
	private ArrayList otherDate 		= null;
	private ArrayList headerNotes       = null;
	/**
	 * Default constructor.
	 */
	public OCHeader() {
		super();
		ocSupplier = new SupplierParty();
		ocReference = new ArrayList();
	}
	/**
	 * @return the ocHeaderStatusType
	 */
	public String getOcHeaderStatusType() {
		return ocHeaderStatusType;
	}
	/**
	 * @param ocHeaderStatusType the ocHeaderStatusType to set
	 */
	public void setOcHeaderStatusType(String ocHeaderStatusType) {
		this.ocHeaderStatusType = ocHeaderStatusType;
	}
	/**
	 * @return the ocNumber
	 */
	public String getOcNumber() {
		return ocNumber;
	}
	/**
	 * @param ocNumber the ocNumber to set
	 */
	public void setOcNumber(String ocNumber) {
		this.ocNumber = ocNumber;
	}
	/**
	 * @return the ocIssueDate
	 */
	public OCIssueDate getOcIssueDate() {
		return ocIssueDate;
	}
	/**
	 * @param ocIssueDate the ocIssueDate to set
	 */
	public void setOcIssueDate(OCIssueDate ocIssueDate) {
		this.ocIssueDate = ocIssueDate;
	}
	/**
	 * @return the poInformation
	 */
	public POInformation getPoInformation() {
		return poInformation;
	}
	/**
	 * @param poInformation the poInformation to set
	 */
	public void setPoInformation(POInformation poInformation) {
		this.poInformation = poInformation;
	}
	/**
	 * @return the ocReference
	 */
	public ArrayList getOcReference() {
		return ocReference;
	}
	/**
	 * @param ocReference the ocReference to set
	 */
	public void setOcReference(ArrayList ocReference) {
		this.ocReference = ocReference;
	}
	/**
	 * @return the ocBuyer
	 */
	public BuyerParty getOcBuyer() {
		return ocBuyer;
	}
	/**
	 * @param ocBuyer the ocBuyer to set
	 */
	public void setOcBuyer(BuyerParty ocBuyer) {
		this.ocBuyer = ocBuyer;
	}
	/**
	 * @return the ocSupplier
	 */
	public SupplierParty getOcSupplier() {
		return ocSupplier;
	}
	/**
	 * @param ocSupplier the ocSupplier to set
	 */
	public void setOcSupplier(SupplierParty ocSupplier) {
		this.ocSupplier = ocSupplier;
	}
	/**
	 * @return the ocShipTO
	 */
	public ShipToCharacteristics getOcShipTO() {
		return ocShipTO;
	}
	/**
	 * @param ocShipTO the ocShipTO to set
	 */
	public void setOcShipTO(ShipToCharacteristics ocShipTO) {
		this.ocShipTO = ocShipTO;
	}
	/**
	 * @return the otherDate
	 */
	public ArrayList getOtherDate() {
		return otherDate;
	}
	/**
	 * @param otherDate the otherDate to set
	 */
	public void setOtherDate(ArrayList otherDate) {
		this.otherDate = otherDate;
	}
	/**
	 * @return Returns the headerNotes.
	 */
	public ArrayList getHeaderNotes() {
		return headerNotes;
	}
	/**
	 * @param headerNotes The headerNotes to set.
	 */
	public void setHeaderNotes(ArrayList headerNotes) {
		this.headerNotes = headerNotes;
	}
}
