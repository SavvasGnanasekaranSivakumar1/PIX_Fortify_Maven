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
 * Title		: 	BusinessDocument.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   27 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;

import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GdsRecptDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.InventoryChangeDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.InventoryStatusDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OrderConfirmationDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageDTO;
import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto.BookSpecificationDTO;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PurchaseOrderDTO;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShippingInstructionsDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.BusinessAcknowledgementDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DeliveryMessageDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.DeliveryMessageWoodDTO;


/**
 * Payload is a data transfer object to store the Business Document 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class BusinessDocument implements java.io.Serializable {
	private static final long serialVersionUID = 6409834924153660019L;
	
	private ShippingInstructionsDTO siDTO 	= null;
	private PurchaseOrderDTO poDTO		  	= null;
	private OrderStatusDTO osDTO		 	= null;
	private OrderConfirmationDTO ocDTO	  	= null;
	private BusinessAcknowledgementDTO baDTO= null;
	private InventoryStatusDTO isDTO 		= null;
	private InventoryChangeDTO icDTO 		= null;
	private BookSpecificationDTO bsDTO		= null;
	private UsageDTO usgDTO					= null;
	private GdsRecptDTO grDTO               = null;
	private DeliveryMessageDTO dmDTO		= null;
	private InvoiceDTO invoDTO              = null;
	private DeliveryMessageWoodDTO dmwDTO	= null;
	/**
	 * @return Returns the invoDTO.
	 */
	public InvoiceDTO getInvoDTO() {
		return invoDTO;
	}
	/**
	 * @param invoDTO The invoDTO to set.
	 */
	public void setInvoDTO(InvoiceDTO invoDTO) {
		this.invoDTO = invoDTO;
	}
	/**
	 * Default constructor.
	 */
	public BusinessDocument() {
		super();
		
		osDTO = new OrderStatusDTO();
		ocDTO = new OrderConfirmationDTO();
		usgDTO = new UsageDTO();
		baDTO = new BusinessAcknowledgementDTO();
		invoDTO = new InvoiceDTO();
		grDTO = new GdsRecptDTO();
		icDTO = new InventoryChangeDTO();
		isDTO = new InventoryStatusDTO();
		dmDTO = new DeliveryMessageDTO();
		dmwDTO = new DeliveryMessageWoodDTO();
	}
	/**
	 * @return the siDTO
	 */
	public ShippingInstructionsDTO getSiDTO() {
		return siDTO;
	}
	/**
	 * @param siDTO the siDTO to set
	 */
	public void setSiDTO(ShippingInstructionsDTO siDTO) {
		this.siDTO = siDTO;
	}
	/**
	 * @return the poDTO
	 */
	public PurchaseOrderDTO getPoDTO() {
		return poDTO;
	}
	/**
	 * @param poDTO the poDTO to set
	 */
	public void setPoDTO(PurchaseOrderDTO poDTO) {
		this.poDTO = poDTO;
	}
	/**
	 * @return the osDTO
	 */
	public OrderStatusDTO getOsDTO() {
		return osDTO;
	}
	/**
	 * @param osDTO the osDTO to set
	 */
	public void setOsDTO(OrderStatusDTO osDTO) {
		this.osDTO = osDTO;
	}
	/**
	 * @return the ocDTO
	 */
	public OrderConfirmationDTO getOcDTO() {
		return ocDTO;
	}
	/**
	 * @param ocDTO the ocDTO to set
	 */
	public void setOcDTO(OrderConfirmationDTO ocDTO) {
		this.ocDTO = ocDTO;
	}
	/**
	 * @return the baDTO
	 */
	public BusinessAcknowledgementDTO getBaDTO() {
		return baDTO;
	}
	/**
	 * @param baDTO the baDTO to set
	 */
	public void setBaDTO(BusinessAcknowledgementDTO baDTO) {
		this.baDTO = baDTO;
	}
	/**
	 * @return Returns the isDTO.
	 */
	public InventoryStatusDTO getIsDTO() {
		return isDTO;
	}
	/**
	 * @param isDTO The isDTO to set.
	 */
	public void setIsDTO(InventoryStatusDTO isDTO) {
		this.isDTO = isDTO;
	}
	/**
	 * @return the icDTO
	 */
	public InventoryChangeDTO getIcDTO() {
		return icDTO;
	}
	/**
	 * @param icDTO the icDTO to set
	 */
	public void setIcDTO(InventoryChangeDTO icDTO) {
		this.icDTO = icDTO;
	}
	/**
	 * @return the bsDTO
	 */
	public BookSpecificationDTO getBsDTO() {
		return bsDTO;
	}
	/**
	 * @param bsDTO the bsDTO to set
	 */
	public void setBsDTO(BookSpecificationDTO bsDTO) {
		this.bsDTO = bsDTO;
	}
	/**
	 * @return Returns the usgDTO.
	 */
	public UsageDTO getUsgDTO() {
		return usgDTO;
	}
	/**
	 * @param usgDTO The usgDTO to set.
	 */
	public void setUsgDTO(UsageDTO usgDTO) {
		this.usgDTO = usgDTO;
	}
	/**
	 * @return Returns the grDTO.
	 */
	public GdsRecptDTO getGrDTO() {
		return grDTO;
	}
	/**
	 * @param grDTO The grDTO to set.
	 */
	public void setGrDTO(GdsRecptDTO grDTO) {
		this.grDTO = grDTO;
	}
	/**
	 * @return the dmDTO
	 */
	public DeliveryMessageDTO getDmDTO() {
		return dmDTO;
	}
	/**
	 * @param dmDTO the dmDTO to set
	 */
	public void setDmDTO(DeliveryMessageDTO dmDTO) {
		this.dmDTO = dmDTO;
	}
	/**
	 * @return the dmwDTO
	 */
	public DeliveryMessageWoodDTO getDmwDTO() {
		return dmwDTO;
	}
	/**
	 * @param dmwDTO the dmwDTO to set
	 */
	public void setDmwDTO(DeliveryMessageWoodDTO dmwDTO) {
		this.dmwDTO = dmwDTO;
	}
}
