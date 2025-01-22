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
 * Title		: 	OutboundTransactionFactory.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	07 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound;

import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.BookSpecificationGeneratorImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.IBookSpecificationGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dao.BookSpecificationDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dao.IBookSpecificationDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.BusinessAckGeneratorImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.IBusinessAckGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.dao.IBusinessAckGeneratorDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.dao.BusinessAckGeneratorDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao.IOutboundTransStatusDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao.OutboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.common.vendor.dao.IOutboundVendorDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.common.vendor.dao.OutboundVendorDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.IPurchaseOrderGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.PurchaseOrderGeneratorImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dao.IPurchaseOrderDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dao.PurchaseOrderDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.IShippingInstructionsGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.ShippingInstructionsGeneratorImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dao.IShippingInstructionsDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dao.ShippingInstructionsDAOImpl;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.ErrorDAOImpl;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
/**
 * OutboundTransactionFactory is a Factory class (Factory Design Pattern) 
 * containing the methods for returning the different types of objects
 * for Outbound transactions XML generation.
 * 
 * @author Yogesh Tyagi
 */
public class OutboundTransactionFactory {
   /**
    * Private Constructor made as no instance should 
    * be created explicitly outside this class
    */
   private OutboundTransactionFactory(){
	   
   }
   /**
    * Returns the new object of OutboundTransactionFactory (Factory Design Pattern)
    */
   public static OutboundTransactionFactory newInstance(){
	   return new OutboundTransactionFactory();
   }
   
   /**
    * Returns the OutboundVendorDAO
    */
   public IOutboundVendorDAO getOutboundVendorDAO(){
	   return new OutboundVendorDAOImpl();
   }
   
   /**
    * Returns the OutboundTransStatusDAO
    */
   public IOutboundTransStatusDAO getOutboundTransStatusDAO(){
	   return new OutboundTransStatusDAOImpl();
   }
   
   /**
    * Returns the ErrorDAO
    */
   public IErrorDAO geErrorDAO(){
	   return new ErrorDAOImpl();
   }
   
   /**
    * Returns the PurchaseOrderGenerator
    */
   public IPurchaseOrderGenerator getPurchaseOrderGenerator(){
	   return new PurchaseOrderGeneratorImpl();
   }
   /**
    * Returns the PurchaseOrderDAO
    */
   public IPurchaseOrderDAO getPurchaseOrderDAO(){
	   return new PurchaseOrderDAOImpl();
   }
   
   /**
    * Returns the ShippingInstructionsGenerator
    */
   public IShippingInstructionsGenerator getShippingInstructionsGenerator(){
	   return new ShippingInstructionsGeneratorImpl();
   }
   /**
    * Returns the ShippingInstructionsDAO
    */
   public IShippingInstructionsDAO getShippingInstructionsDAO(){
	   return new ShippingInstructionsDAOImpl();
   }
   
   /**
    * Returns the BookSpecificationGenerator
    */
   public IBookSpecificationGenerator getBookSpecificationGenerator(){
	   return new BookSpecificationGeneratorImpl();
   }
   /**
    * Returns the BookSpecificationDAO
    */
   public IBookSpecificationDAO getBookSpecificationDAO(){
	   return new BookSpecificationDAOImpl();
   }
   
   /**
    * Returns the BusinessAckGenerator
    */
   public IBusinessAckGenerator getBusinessAckGenerator(){
	   return new BusinessAckGeneratorImpl();
   }
   /**
    * Returns the BusinessAckGeneratorDAO
    */
   public IBusinessAckGeneratorDAO getBusinessAckGeneratorDAO(){
	   return new BusinessAckGeneratorDAOImpl();
   }
}
