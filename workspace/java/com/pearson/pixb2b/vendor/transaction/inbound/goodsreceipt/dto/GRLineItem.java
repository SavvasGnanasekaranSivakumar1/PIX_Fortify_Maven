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
 * Title		: 	GRLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan,2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRProduct;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;


/**
 * GRLineItem is a data transfer object to store the 
 * goodsreceipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GRLineItem implements java.io.Serializable {
	private static final long serialVersionUID = 1898582772783678819L;
	
	private String grAcceptance = null;
	private String grReject = null;
	private String dmLineNo = null;
	private String grLineItemNo = null;	
	private POInformation poInfo = null;
	private GRProduct grProduct = null;
	private LineQuantity lineQty = null;
	private ArrayList comments = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public GRLineItem() {
		super();
	}


	
	/**
	 * @return Returns the comments.
	 */
	public ArrayList getComments() {
		return comments;
	}



	/**
	 * @param comments The comments to set.
	 */
	public void setComments(ArrayList comments) {
		this.comments = comments;
	}



	/**
	 * @return Returns the grAcceptance.
	 */
	public String getGrAcceptance() {
		return grAcceptance;
	}



	/**
	 * @param grAcceptance The grAcceptance to set.
	 */
	public void setGrAcceptance(String grAcceptance) {
		this.grAcceptance = grAcceptance;
	}



	/**
	 * @return Returns the grLineItemNo.
	 */
	public String getGrLineItemNo() {
		return grLineItemNo;
	}



	/**
	 * @param grLineItemNo The grLineItemNo to set.
	 */
	public void setGrLineItemNo(String grLineItemNo) {
		this.grLineItemNo = grLineItemNo;
	}



	/**
	 * @return Returns the lineQty.
	 */
	public LineQuantity getLineQty() {
		return lineQty;
	}



	/**
	 * @param lineQty The lineQty to set.
	 */
	public void setLineQty(LineQuantity lineQty) {
		this.lineQty = lineQty;
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
	 * @return Returns the grProduct.
	 */
	public GRProduct getGrProduct() {
		return grProduct;
	}



	/**
	 * @param grProduct The grProduct to set.
	 */
	public void setGrProduct(GRProduct grProduct) {
		this.grProduct = grProduct;
	}



	/**
	 * @return Returns the dmLineNo.
	 */
	public String getDmLineNo() {
		return dmLineNo;
	}



	/**
	 * @param dmLineNo The dmLineNo to set.
	 */
	public void setDmLineNo(String dmLineNo) {
		this.dmLineNo = dmLineNo;
	}



	/**
	 * @return Returns the grReject.
	 */
	public String getGrReject() {
		return grReject;
	}



	/**
	 * @param grReject The grReject to set.
	 */
	public void setGrReject(String grReject) {
		this.grReject = grReject;
	}
	
}
