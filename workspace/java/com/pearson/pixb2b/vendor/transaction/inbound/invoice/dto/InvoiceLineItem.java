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
 * Title		: 	InvoiceLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 Feb, 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;
import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.Product;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQuantity;
/**
 * InvoiceLineItem is a data transfer object to store the 
 * InvoiceLineItem details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class InvoiceLineItem implements java.io.Serializable {
	private static final long serialVersionUID = -511551612066291322L;
	
	private String isFinalInvoice         = null;
	private String invoiceLineNo	 	  = null;
	private String poLineNo               = null;
	private POInformation poInfo		  = null;
	private Product product				  = null;
	private LineQuantity invLineQty 	  = null;		
	private ArrayList chargeInfo          = null;
	private ArrayList lineComments    = null;

	/**
	 * @return Returns the chargeInfo.
	 */
	public ArrayList getChargeInfo() {
		return chargeInfo;
	}



	/**
	 * @param chargeInfo The chargeInfo to set.
	 */
	public void setChargeInfo(ArrayList chargeInfo) {
		this.chargeInfo = chargeInfo;
	}



	/**
	 * @return Returns the invLineQty.
	 */
	public LineQuantity getInvLineQty() {
		return invLineQty;
	}



	/**
	 * @param invLineQty The invLineQty to set.
	 */
	public void setInvLineQty(LineQuantity invLineQty) {
		this.invLineQty = invLineQty;
	}



	/**
	 * @return Returns the isFinalInvoice.
	 */
	public String getIsFinalInvoice() {
		return isFinalInvoice;
	}



	/**
	 * @param isFinalInvoice The isFinalInvoice to set.
	 */
	public void setIsFinalInvoice(String isFinalInvoice) {
		this.isFinalInvoice = isFinalInvoice;
	}



	/**
	 * @return Returns the poInfo.
	 */
	public POInformation getPoInfo() {
		return poInfo;
	}



	/**
	 * @param poInfo The poInfo to set.
	 */
	public void setPoInfo(POInformation poInfo) {
		this.poInfo = poInfo;
	}



	/**
	 * Default constructor.
	 */
	public InvoiceLineItem() {
		super();
	}

	

	/**
	 * @return Returns the product.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product The product to set.
	 */
	public void setProduct(Product product) {
		this.product = product;
	}



	/**
	 * @return Returns the invoiceLineNo.
	 */
	public String getInvoiceLineNo() {
		return invoiceLineNo;
	}



	/**
	 * @param invoiceLineNo The invoiceLineNo to set.
	 */
	public void setInvoiceLineNo(String invoiceLineNo) {
		this.invoiceLineNo = invoiceLineNo;
	}



	

	/**
	 * @return Returns the lineComments.
	 */
	public ArrayList getLineComments() {
		return lineComments;
	}



	/**
	 * @param lineComments The lineComments to set.
	 */
	public void setLineComments(ArrayList lineComments) {
		this.lineComments = lineComments;
	}



	/**
	 * @return Returns the poLineNo.
	 */
	public String getPoLineNo() {
		return poLineNo;
	}



	/**
	 * @param poLineNo The poLineNo to set.
	 */
	public void setPoLineNo(String poLineNo) {
		this.poLineNo = poLineNo;
	}

}
