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
 * Title		: 	ApplicationInstanceManager.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal 		4 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * B2BInboundTransactions class initiate the B2B Inbound Transactions 
 * processing of various types of transactions for different vendors.

 * This class get the B2B vendor details for Inbound transactions  and 
 * start the separate thread for each vendor for asynchronous processing 
 * of transactions for Inbound case.
 * 
 * @author Ashish Agrawal
 */
public class ApplicationInstanceManager {
	
	static ServerSocket socket=null;
	/**
	 * 
	 * @param port
	 * @return
	 */
	public static boolean registerInstance(int port){
        try {
        		socket = new ServerSocket(port);
        		//new Thread(new InstanceListener()).start();
			}catch (IOException e) {
				return Boolean.FALSE;
			}
		return Boolean.TRUE;
	}
	/**
	 * 
	 */
	public static void unRegisterInstance(){
		try {
				socket.close();
		} catch (IOException e) {
			socket=null;		
		}
	}
}