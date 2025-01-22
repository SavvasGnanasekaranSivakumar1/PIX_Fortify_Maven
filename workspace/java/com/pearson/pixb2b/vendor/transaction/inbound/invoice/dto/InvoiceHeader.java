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
 * Title		: 	InvoiceHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 Feb,2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;

import java.util.ArrayList;


/**
 * InvoiceHeader is a data transfer object to store the 
 * Invoice Header details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class InvoiceHeader implements java.io.Serializable {
	private static final long serialVersionUID = 2755596990354521424L;

	private String invNumber		 = null;
	private InvoiceDate invoiceDate	 = null;	
	private ArrayList invRef 		 = null;
	private BillToParty  billToParty = null;
	private SupplierParty suppParty  = null;
	private BuyerParty buyerParty 	 = null;
	private RemitToParty remitParty  = null;
	private ArrayList headerComments    = null;
	/**
	 * Default constructor.
	 */
	public InvoiceHeader() {
		super();
		suppParty = new SupplierParty();
		invRef = new ArrayList();
	}

	/**
	 * @return Returns the billToParty.
	 */
	public BillToParty getBillToParty() {
		return billToParty;
	}

	/**
	 * @param billToParty The billToParty to set.
	 */
	public void setBillToParty(BillToParty billToParty) {
		this.billToParty = billToParty;
	}

	/**
	 * @return Returns the buyerParty.
	 */
	public BuyerParty getBuyerParty() {
		return buyerParty;
	}

	/**
	 * @param buyerParty The buyerParty to set.
	 */
	public void setBuyerParty(BuyerParty buyerParty) {
		this.buyerParty = buyerParty;
	}

	/**
	 * @return Returns the invNumber.
	 */
	public String getInvNumber() {
		return invNumber;
	}

	/**
	 * @param invNumber The invNumber to set.
	 */
	public void setInvNumber(String invNumber) {
		this.invNumber = invNumber;
	}

	/**
	 * @return Returns the invoiceDate.
	 */
	public InvoiceDate getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate The invoiceDate to set.
	 */
	public void setInvoiceDate(InvoiceDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	

	/**
	 * @return Returns the remitParty.
	 */
	public RemitToParty getRemitParty() {
		return remitParty;
	}

	/**
	 * @param remitParty The remitParty to set.
	 */
	public void setRemitParty(RemitToParty remitParty) {
		this.remitParty = remitParty;
	}

	/**
	 * @return Returns the suppParty.
	 */
	public SupplierParty getSuppParty() {
		return suppParty;
	}

	/**
	 * @param suppParty The suppParty to set.
	 */
	public void setSuppParty(SupplierParty suppParty) {
		this.suppParty = suppParty;
	}

	

	/**
	 * @return Returns the headerComments.
	 */
	public ArrayList getHeaderComments() {
		return headerComments;
	}

	/**
	 * @param headerComments The headerComments to set.
	 */
	public void setHeaderComments(ArrayList headerComments) {
		this.headerComments = headerComments;
	}

	/**
	 * @return Returns the invRef.
	 */
	public ArrayList getInvRef() {
		return invRef;
	}

	/**
	 * @param invRef The invRef to set.
	 */
	public void setInvRef(ArrayList invRef) {
		this.invRef = invRef;
	}
	
}
