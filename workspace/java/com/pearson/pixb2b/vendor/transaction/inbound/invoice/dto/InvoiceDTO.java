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
 * Title		: 	InvoiceDTO.java
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
 * InvoiceDTO is a data transfer object to store the 
 * Invoice details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class InvoiceDTO implements java.io.Serializable {
	private static final long serialVersionUID = 477311091548121974L;

	private String invoiceType 			= null;
	private String reissued 			= null;
	private InvoiceHeader invHeader		= null;
	private ArrayList invoiceLineItem	= null;
	private InvoiceSummary invSummary   = null;
	
	
	/**
	 * Default constructor.
	 */
	public InvoiceDTO() {
		super();
		invoiceLineItem = new ArrayList();
		invHeader = new InvoiceHeader();
	}
	
	/**
	 * @return Returns the invSummary.
	 */
	public InvoiceSummary getInvSummary() {
		return invSummary;
	}
	/**
	 * @param invSummary The invSummary to set.
	 */
	public void setInvSummary(InvoiceSummary invSummary) {
		this.invSummary = invSummary;
	}
	/**
	 * @return Returns the invoiceLineItem.
	 */
	public ArrayList getInvoiceLineItem() {
		return invoiceLineItem;
	}
	/**
	 * @param invoiceLineItem The invoiceLineItem to set.
	 */
	public void setInvoiceLineItem(ArrayList invoiceLineItem) {
		this.invoiceLineItem = invoiceLineItem;
	}
	
		
	/**
	 * @return Returns the invHeader.
	 */
	public InvoiceHeader getInvHeader() {
		return invHeader;
	}
	/**
	 * @param invHeader The invHeader to set.
	 */
	public void setInvHeader(InvoiceHeader invHeader) {
		this.invHeader = invHeader;
	}
	/**
	 * @return Returns the invoiceType.
	 */
	public String getInvoiceType() {
		return invoiceType;
	}
	/**
	 * @param invoiceType The invoiceType to set.
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * @return Returns the reissued.
	 */
	public String getReissued() {
		return reissued;
	}
	/**
	 * @param reissued The reissued to set.
	 */
	public void setReissued(String reissued) {
		this.reissued = reissued;
	}
	
}
