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
 * Title		: 	DeliveryMessageWoodDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		22 March 2010 		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.DeliverySchedule;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto.ShipToInformation;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DMDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesRef;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelOriginDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DeliveryLeg;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.DelMesShip;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.DelMesWoodHd;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.DelMessProdGroup;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.DelShipLineItem;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.TotNumOfUnits;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * DeliveryMessageWoodDAOImpl is a class to communicate with the PIX database
 * and process the B2B DeliveryMessageWood transaction data.
 * 
 * @author Ashish Agrawal
 */
public class DeliveryMessageWoodDAOImpl extends InboundTransStatusDAOImpl implements IDeliveryMessageWoodDAO{
	private static final String qry_sel_pix_msg_id_nextval = "SELECT SEQ_PIX_DELIVERY_MSG_ID.nextval msg_id_next FROM DUAL";
	
	private static final String qry_sel_status_id_ = "SELECT STATUS_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_STATUS_CODE"
	+" WHERE status_code = UPPER(?)"
	+" and TABLE_ID = ?";	

	private static final String qry_ins_pix_del_msg = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_DELIVERY_MSG"
		+" (MSG_ID, MSG_TYPE_ID, MSG_NO, PO_ID, PO_VERSION, COMMENTS, STATUS_ID, TRANSACTION_HISTORY_NO,"
		+" CREATED_BY, MODIFIED_BY, MOD_DATE_TIME, CREATION_DATE_TIME, PO_LINE_NO, APPROVAL_FLAG)"
		+" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?)";

	private static final String qry_sel_uom_id_ = "SELECT UOM_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_UOM_CODE"
		+" WHERE XUOM = ?";
	
	private static final String qry_ins_pix_del_msg_line = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_DELIVERY_MSG_LINE"
		+" (MSG_ID, MSG_LINE_NO, PO_ID, PO_VERSION, PO_LINE_NO, DELIVERY_DATE, DELIVERED_QUANTITY, BALANCE_QUANTITY, UOM_ID,"
		+" CREATED_BY, MODIFIED_BY, MOD_DATE_TIME, CREATION_DATE_TIME, APPROVAL_FLAG, INITIAL_DELIVERED_QUANTITY)"
		+" VALUES (?, ?, ?, ?, ?, to_date(?,'MM/DD/YYYY'), ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?)";

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.dao.IDeliveryMessageWoodDAO#storeDeliveryMessageWoodDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String storeDeliveryMessageWoodDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		String dbPOId				= null;
		String dbPOVersion			= null;
		HashMap hmPoIdpoVersion		= null;
		
		String dbDelMsgIdNext		= null;
		long dbTransIdNext 			= -1;

		DelMesWoodHd delMesWoodHd  				= null;
		ArrayList xmlDMWoodShipList				= null;
		DelMesShip delMesShipWood				= null;
		ArrayList delMessProdGroupList 			= null;
		DelMessProdGroup delMesProdGrWood		= null;
		ArrayList delShipLineItemList 			= null;
		DelShipLineItem delShipLineItem			= null;
		String xmlPOLineitemNumr				= null;
		POInformation poInformation	 			= null;
		String xmlPONumr						= null;
		TotNumOfUnits totNumOfUnits 			= null;	
		String lineQtyValue 					= null;
		ArrayList shipToInformationList			= null;
		ShipToInformation shipToInfo			= null;
		ArrayList delscheduleList				= null;
		DeliverySchedule delschedule			= null;
		ArrayList deliveryLegList				= null;
		DeliveryLeg deliveryLeg					= null;
		DelOriginDate delOriginDate 			= null;
		
		String delMesStatusType		= null;
		String tranHistNumr			= null;
		String delMesNumb 			= null;
		DMDate dmDate 				= null;
		String xmlDMDate			= null;
		String xmlDelDate			= null;
		ArrayList xmlDMRefList		= null;
		DelMesRef delMesRef			= null;
		String xmlDMTransId			= null;
		String delShipLineItemNum	= null;
		String uomLineQty			= null;
		String dmStatusId			= null;
		String uomId				= null;
		String dmwtatus 			= IPixB2BConstants.flag_N;
		Boolean correctDataFlag		= Boolean.FALSE;
		try {
			B2BLogger.debug("******* DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_DME);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) 
							&& receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						delMesStatusType = pneDTO.getPayload().getBusinessDocument().getDmwDTO().getDelMessageStatusType();
						delMesWoodHd = pneDTO.getPayload().getBusinessDocument().getDmwDTO().getDelMesWoodHd();
						
						if(null!=delMesWoodHd){
							tranHistNumr = delMesWoodHd.getTranHistNumr();
							delMesNumb = delMesWoodHd.getDelMeseNumb();
							
							/*deliveryLegList = delMesWoodHd.getDeliveryLegList();
							if(null!=deliveryLegList && deliveryLegList.size()>0){
								for(int m=0; m<deliveryLegList.size(); m++){
									deliveryLeg = (DeliveryLeg)deliveryLegList.get(m);
									if(null!=deliveryLeg){
										delOriginDate= deliveryLeg.getDelOrigin().getDelOriginDate();
										if(null!=delOriginDate){
											xmlDelDate = delOriginDate.getMonth()
											+"/"+delOriginDate.getDay()
											+"/"+delOriginDate.getYear();
										}
									}
								}
							}*/
							
							shipToInformationList = delMesWoodHd.getShipToInformationList();
							if(null!=shipToInformationList && shipToInformationList.size()>0){
								for(int p=0; p<shipToInformationList.size(); p++){
									shipToInfo = (ShipToInformation)shipToInformationList.get(p);
									if(null!=shipToInfo){
										delscheduleList = shipToInfo.getDeliverySchedule();
										if(null!=delscheduleList && delscheduleList.size()>0){
											for(int q=0; q<delscheduleList.size(); q++){
												delschedule = (DeliverySchedule)delscheduleList.get(q);
												if(null!=delschedule){
													deliveryLegList = delschedule.getDeliveryLegList();
													if(null!=deliveryLegList && deliveryLegList.size()>0){
														for(int r=0; r<deliveryLegList.size(); r++){
															deliveryLeg = (DeliveryLeg)deliveryLegList.get(r);
															if(null!=deliveryLeg){
																delOriginDate= deliveryLeg.getDelOrigin().getDelOriginDate();
																if(null!=delOriginDate){
																	xmlDelDate = delOriginDate.getMonth()
																	+"/"+delOriginDate.getDay()
																	+"/"+delOriginDate.getYear();
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
							dmDate =delMesWoodHd.getDelMesDate().getDmDate();
							if(null!=dmDate){
								xmlDMDate = dmDate.getMonth()
								+"/"+dmDate.getDay()
								+"/"+dmDate.getYear();
							}
							
							xmlDMRefList = delMesWoodHd.getDelMesRefList();							
							if(null!=xmlDMRefList && xmlDMRefList.size()>0){
								for(int i = 0; i<xmlDMRefList.size(); i++){
									delMesRef = (DelMesRef)xmlDMRefList.get(i);
									if(null!=delMesRef && null!=delMesRef.getDelMesRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(delMesRef.getDelMesRefType().trim())){
										xmlDMTransId = delMesRef.getDelMesRefVal();
									}
								}
								if(null!=xmlDMTransId && !"".equals(xmlDMTransId.trim())){
									dbCon.setAutoCommit(Boolean.FALSE);
									
									xmlDMWoodShipList = pneDTO.getPayload().getBusinessDocument().getDmwDTO().getDelMesShipList();
									if(null!=xmlDMWoodShipList && xmlDMWoodShipList.size() > 0){
										int rowCount = 1;
										for(int j=0; j<xmlDMWoodShipList.size(); j++){
											delMesShipWood = (DelMesShip)xmlDMWoodShipList.get(j);
											if(null!=delMesShipWood){
												delMessProdGroupList = delMesShipWood.getDelMessProdGroupList();
												if(null!=delMessProdGroupList && delMessProdGroupList.size() > 0){
													for(int m=0; m<delMessProdGroupList.size(); m++){
														delMesProdGrWood = (DelMessProdGroup)delMessProdGroupList.get(m);
														if(null!=delMesProdGrWood){
															delShipLineItemList = delMesProdGrWood.getDelShipLineItemList();
															if(null!=delShipLineItemList && delShipLineItemList.size() > 0){
																for(int n=0; n<delShipLineItemList.size(); n++){
																	xmlPONumr 			= null;
																	xmlPOLineitemNumr 	= null;
																	delShipLineItemNum 	= null;
																	totNumOfUnits		= null;
																	lineQtyValue 		= null;
																	uomLineQty 			= null;
																	
																	delShipLineItem = (DelShipLineItem)delShipLineItemList.get(n);
																	if(null!=delShipLineItem){
																		delShipLineItemNum = delShipLineItem.getDelShipLineItemNum();
																		xmlPOLineitemNumr = delShipLineItem.getPoLineItemNumber();
																		totNumOfUnits = delShipLineItem.getTotNumOfUnits();
																		if(null!=totNumOfUnits){
																			lineQtyValue = totNumOfUnits.getLineQtyValue().getQtyValue();
																			uomLineQty = totNumOfUnits.getLineQtyValue().getUOM();
																		}
																		poInformation = delShipLineItem.getPoInformation();
																		if(null!=poInformation){
																			xmlPONumr = poInformation.getPoNumber();
																		}
																		if(rowCount == 1){
																			hmPoIdpoVersion = b2bHelper.getPoIdPoVersion(dbCon, xmlPONumr);
																			if(hmPoIdpoVersion != null && hmPoIdpoVersion.size()>0){
																				dbPOId = (String)hmPoIdpoVersion.get("PO_ID");
																				dbPOVersion= (String)hmPoIdpoVersion.get("PO_VERSION");
																				if(null!=dbPOId && !"".equals(dbPOId.trim()) 
																						&& null!=dbPOVersion && !"".equals(dbPOVersion.trim())){
																					sqlQuery = qry_sel_pix_msg_id_nextval;
																					prepStmt1 = dbCon.prepareStatement(sqlQuery);
																					prepStmt1.clearParameters();
																					resultSet1 = prepStmt1.executeQuery();
																					while(resultSet1.next())
																						dbDelMsgIdNext = resultSet1.getString("msg_id_next");
																					
																					DBUtils.close(prepStmt1);
																					DBUtils.close(resultSet1);
																					if(null!=dbDelMsgIdNext && !"".equals(dbDelMsgIdNext.trim())){
																						dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
																								senderSAN, receiverSAN, 
																								dbPOId, dbDelMsgIdNext, IPixB2BConstants.PO_ID, IPixB2BConstants.DELIVERY_MESSAGE_ID, delMesNumb, xmlDMDate, xmlDMTransId);
																						if(dbTransIdNext > 0){
																							sqlQuery = qry_sel_status_id_;
																							prepStmt1 = dbCon.prepareStatement(sqlQuery);
																							prepStmt1.clearParameters();
																							prepStmt1.setString(IPixB2BConstants.ONE,delMesStatusType);
																							prepStmt1.setString(IPixB2BConstants.TWO,IPixB2BConstants.DM_TABLE_ID);
																							qryParams = delMesStatusType+","+IPixB2BConstants.DM_TABLE_ID;
																							resultSet1 = prepStmt1.executeQuery();
																							while(resultSet1.next())
																								dmStatusId = resultSet1.getString("STATUS_ID");
																							
																							DBUtils.close(prepStmt1);
																							DBUtils.close(resultSet1);
																							
																							sqlQuery = qry_ins_pix_del_msg;
																							prepStmt1 = dbCon.prepareStatement(sqlQuery);
																							prepStmt1.clearParameters();
																							prepStmt1.setString(IPixB2BConstants.ONE,dbDelMsgIdNext);
																							prepStmt1.setString(IPixB2BConstants.TWO,IPixB2BConstants.MSG_TYPE_ID);
																							prepStmt1.setString(IPixB2BConstants.THREE,delMesNumb);   
																							prepStmt1.setString(IPixB2BConstants.FOUR,dbPOId);
																							prepStmt1.setString(IPixB2BConstants.FIVE,dbPOVersion);
																							prepStmt1.setString(IPixB2BConstants.SIX,IPixB2BConstants.DM_COMMENTS);
																							prepStmt1.setString(IPixB2BConstants.SEVEN,dmStatusId);
																							prepStmt1.setString(IPixB2BConstants.EIGHT,xmlDMTransId);
																							prepStmt1.setString(IPixB2BConstants.NINE,IPixB2BConstants.JavaB2B);
																							prepStmt1.setString(IPixB2BConstants.TEN,IPixB2BConstants.JavaB2B);
																							prepStmt1.setString(IPixB2BConstants.ELEVEN,xmlPOLineitemNumr);
																							prepStmt1.setString(IPixB2BConstants.TWELVE,IPixB2BConstants.DM_APPROVAL_FLAG);
																							qryParams = dbDelMsgIdNext+","+IPixB2BConstants.MSG_TYPE_ID+","+delMesNumb+","+dbPOId+","+dbPOVersion+","+IPixB2BConstants.DM_COMMENTS
																										+dmStatusId+","+xmlDMTransId+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B+","+xmlPOLineitemNumr+","+IPixB2BConstants.DM_APPROVAL_FLAG;
																							recsNum = prepStmt1.executeUpdate();
																							recsCount = recsCount + recsNum;
																							
																							DBUtils.close(prepStmt1);
																							correctDataFlag = Boolean.TRUE;
																						}else{
																							correctDataFlag = Boolean.FALSE;
																							rowCount = 0;
																							B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - dbTransIdNext = "+dbTransIdNext+" for xmlTransId = "+transStatusDTO.getTransID());
																						}
																					}else{
																						correctDataFlag = Boolean.FALSE;
																						rowCount = 0;
																						B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - dbDelMsgIdNext = "+dbDelMsgIdNext);
																					}
																				}else{
																					correctDataFlag = Boolean.FALSE;
																					rowCount = 0;
																					B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - dbPOId = "+dbPOId+" , dbPOVersion = "+dbPOVersion+" FOR PO NUMBER = "+xmlPONumr);
																				}
																			}
																		}
																		rowCount++;
																		if(correctDataFlag){
																			sqlQuery = qry_sel_uom_id_;
																			prepStmt1 = dbCon.prepareStatement(sqlQuery);
																			prepStmt1.clearParameters();
																			prepStmt1.setString(IPixB2BConstants.ONE,uomLineQty);
																			qryParams = uomLineQty;
																			resultSet1 = prepStmt1.executeQuery();
																			while(resultSet1.next())
																				uomId = resultSet1.getString("UOM_ID");
																			
																			DBUtils.close(prepStmt1);
																			DBUtils.close(resultSet1);
																			
																			sqlQuery = qry_ins_pix_del_msg_line;
																			prepStmt1 = dbCon.prepareStatement(sqlQuery);
																			prepStmt1.clearParameters();
																			prepStmt1.setString(IPixB2BConstants.ONE,dbDelMsgIdNext);
																			prepStmt1.setString(IPixB2BConstants.TWO,delShipLineItemNum);
																			prepStmt1.setString(IPixB2BConstants.THREE,dbPOId);   
																			prepStmt1.setString(IPixB2BConstants.FOUR,dbPOVersion);
																			prepStmt1.setString(IPixB2BConstants.FIVE,xmlPOLineitemNumr);
																			prepStmt1.setString(IPixB2BConstants.SIX,xmlDelDate);
																			prepStmt1.setString(IPixB2BConstants.SEVEN,lineQtyValue);
																			prepStmt1.setString(IPixB2BConstants.EIGHT,IPixB2BConstants.ZERO+"");
																			prepStmt1.setString(IPixB2BConstants.NINE,uomId);
																			prepStmt1.setString(IPixB2BConstants.TEN,IPixB2BConstants.JavaB2B);
																			prepStmt1.setString(IPixB2BConstants.ELEVEN,IPixB2BConstants.JavaB2B);
																			prepStmt1.setString(IPixB2BConstants.TWELVE,IPixB2BConstants.DM_APPROVAL_FLAG);
																			prepStmt1.setString(IPixB2BConstants.THIRTEEN,null);
																			qryParams = dbDelMsgIdNext+","+delShipLineItemNum+","+dbPOId+","+dbPOVersion+","+xmlPOLineitemNumr+","+xmlDelDate
																						+lineQtyValue+","+IPixB2BConstants.ZERO+","+uomId+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.DM_APPROVAL_FLAG;
																			recsNum = prepStmt1.executeUpdate();
																			recsCount = recsCount + recsNum;
																			
																			DBUtils.close(prepStmt1);
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}else{
										B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - xmlDMWoodShipList is null or blank");
									}
									dbCon.commit();
								}else{
									B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - xmlDMTransId is null or blank");
								}
							}else{
								B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - xmlDMRefList is null or blank");
							}
						}else{
							B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails()- xmlDMBookHd is null");
						}
					}else{
						B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - pneDTO is null");
			}
			
			B2BLogger.info("DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() - recsCount = "+recsCount+", dbTransIdNext = "+dbTransIdNext);
			if(recsCount > 0)
				dmwtatus = IPixB2BConstants.flag_Y;			
			
			B2BLogger.debug("******* DeliveryMessageWoodDAOImpl.storeDeliveryMessageWoodDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			dmwtatus = StringUtils.getStackTrace(e);
			if(dmwtatus != null && dmwtatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				dmwtatus = dmwtatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("SQLException :: ",e1);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			dmwtatus = StringUtils.getStackTrace(e);
			if(dmwtatus != null && dmwtatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				dmwtatus = dmwtatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e);
			}
		} finally{			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);			
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			b2bHelper		= null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;
			dbPOId			= null;
			dbPOVersion		= null;
			hmPoIdpoVersion	= null;
			dbDelMsgIdNext	= null;
		}
		return dmwtatus;
	}
}