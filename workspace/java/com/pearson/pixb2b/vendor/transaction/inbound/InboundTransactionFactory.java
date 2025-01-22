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
 * Title		: 	InboundTransactionFactory.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	07 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound;

import com.pearson.pixb2b.vendor.transaction.inbound.asn.AdvanceShipmentNoticeProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.asn.IAdvanceShipmentNoticeProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.asn.dao.AdvanceShipmentNoticeDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.asn.dao.IAdvanceShipmentNoticeDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.BusinessAckProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.IBusinessAckProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.dao.BusinessAckProcessorDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.dao.IBusinessAckProcessorDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.IInboundVendorDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.InboundVendorDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessage.DeliveryMessageProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessage.IDeliveryMessageProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessage.dao.DeliveryMessageDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessage.dao.IDeliveryMessageDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.DeliveryMessageWoodProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.IDeliveryMessageWoodProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.dao.DeliveryMessageWoodDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.dao.IDeliveryMessageWoodDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.GRProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.IGRProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dao.GoodsRecptDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dao.IGdsRecptDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.IInventoryChangeProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.InventoryChangeProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dao.IInventoryChangeDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dao.InventoryChangeDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.IInventoryStatusProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.InventoryStatusProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dao.IInventoryStatusDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dao.InventoryStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.IInvoiceProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.InvoiceProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dao.IInvoiceDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dao.InvoiceDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.IOrderConfirmationProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.OrderConfirmationProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dao.IOrderConfirmationDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dao.OrderConfirmationDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.OrderStatusProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dao.IOrderStatusDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dao.OrderStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.IUsageProcessor;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.UsageProcessorImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dao.IUsageDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dao.UsageDAOImpl;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.ErrorDAOImpl;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
/**
 * InboundTransactionFactory is a Factory class (Factory Design Pattern) 
 * containing the methods for returning different types of objects
 * for Inbound transactions XML processing.
 * 
 * @author Yogesh Tyagi
 */
public class InboundTransactionFactory {
   /**
    * Private Constructor made as no instance should 
    * be created explicitly outside this class
    */
   private InboundTransactionFactory(){
	   
   }
   
   /**
    * Returns the new object of InboundTransactionFactory (Factory Design Pattern)
    */
   public static InboundTransactionFactory newInstance(){
	   return new InboundTransactionFactory();
   }
   
   /**
    * Returns the InboundVendorDAO
    */
   public IInboundVendorDAO geInboundVendorDAO(){
	   return new InboundVendorDAOImpl();
   }
   
   /**
    * Returns the ErrorDAO
    */
   public IErrorDAO geErrorDAO(){
	   return new ErrorDAOImpl();
   }
   
   /**
    * Returns the OrderStatusProcessor
    */
   public IOrderStatusProcessor getOrderStatusProcessor(){
	   return new OrderStatusProcessorImpl();
   }
   /**
    * Returns the OrderStatusDAO
    */
   public IOrderStatusDAO getOrderStatusDAO(){
	   return new OrderStatusDAOImpl();
   }
   
   /**
    * Returns the OrderConfirmationProcessor
    */
   public IOrderConfirmationProcessor getOrderConfirmationProcessor(){
	   return new OrderConfirmationProcessorImpl();
   }
   /**
    * Returns the OrderConfirmationDAO
    */
   public IOrderConfirmationDAO getOrderConfirmationDAO(){
	   return new OrderConfirmationDAOImpl();
   }
   
   /**
    * Returns the InventoryChangeProcessor
    */
   public IInventoryChangeProcessor getInventoryChangeProcessor(){
	   return new InventoryChangeProcessorImpl();
   }
   /**
    * Returns the InventoryChangeDAO
    */
   public IInventoryChangeDAO getInventoryChangeDAO(){
	   return new InventoryChangeDAOImpl();
   }
   
   /**
    * Returns the InventoryStatusProcessor
    */
   public IInventoryStatusProcessor getInventoryStatusProcessor(){
	   return new InventoryStatusProcessorImpl();
   }
   /**
    * Returns the InventoryStatusDAO
    */
   public IInventoryStatusDAO getInventoryStatusDAO(){
	   return new InventoryStatusDAOImpl();
   }
   
   /**
    * Returns the UsageProcessor
    */
   public IUsageProcessor getUsageProcessor(){
	   return new UsageProcessorImpl();
   }
   /**
    * Returns the UsageDAO
    */
   public IUsageDAO getUsageDAO(){
	   return new UsageDAOImpl();
   }
   
   /**
    * Returns the BusinessAckProcessor
    */
   public IBusinessAckProcessor getBusinessAckProcessor(){
	   return new BusinessAckProcessorImpl();
   }
   /**
    * Returns the BusinessAckProcessorDAO
    */
   public IBusinessAckProcessorDAO getBusinessAckProcessorDAO(){
	   return new BusinessAckProcessorDAOImpl();
   }
   
   /**
    * Returns the GRProcessor
    */
   public IGRProcessor getGRProcessor(){
	   return new GRProcessorImpl();
   }
   /**
    * Returns the GRDAO
    */
   public IGdsRecptDAO getGdsRecptDAO(){
	   return new GoodsRecptDAOImpl();
   }
   
   /**
    * Returns the DeliveryMessageProcessor
    */
   public IDeliveryMessageProcessor getDeliveryMessageProcessor(){
	   return new DeliveryMessageProcessorImpl();
   }
   /**
    * Returns the DeliveryMessageProcessorDAO
    */
   public IDeliveryMessageDAO getDeliveryMessageDAO(){
	   return new DeliveryMessageDAOImpl();
   }
   
   
   /**
    * Returns the InvoiceProcessor
    */
   public IInvoiceProcessor getInvoiceProcessor(){
	   return new InvoiceProcessorImpl();
   }
   /**
    * Returns the InvoiceProcessorDAO
    */
   public IInvoiceDAO getInvoiceDAO(){
	   return new InvoiceDAOImpl();
   }
   
   /**
    * Returns the DeliveryMessageWoodProcessor
    */
   public IDeliveryMessageWoodProcessor getDeliveryMessageWoodProcessor(){
	   return new DeliveryMessageWoodProcessorImpl();
   }
   /**
    * Returns the DeliveryMessageWoodProcessorDAO
    */
   public IDeliveryMessageWoodDAO getDeliveryMessageWoodDAO(){
	   return new DeliveryMessageWoodDAOImpl();
   }
   
   /**
    * Returns the DeliveryMessageWoodProcessor
    */
   public IAdvanceShipmentNoticeProcessor getAdvanceShipmentNoticeProcessor(){
	   return new AdvanceShipmentNoticeProcessorImpl();
   }
   /**
    * Returns the DeliveryMessageWoodProcessorDAO
    */
   public IAdvanceShipmentNoticeDAO getAdvanceShipmentNoticeDAO(){
	   return new AdvanceShipmentNoticeDAOImpl();
   }
}
