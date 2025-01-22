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
 * Title		: 	Ic3p0Constants.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	09 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils.constants;
/**
 * Ic3p0Constants interface contains all c3p0 library constants 
 * that are using in the application for database connection pooling.
 * 
 * @author Yogesh Tyagi
 */
public interface Ic3p0Constants {
	public static final String initialPoolSize						= "initialPoolSize";
	public static final String maxPoolSize							= "maxPoolSize";
	public static final String minPoolSize							= "minPoolSize";
	public static final String numHelperThreads						= "numHelperThreads";
	public static final String acquireIncrement						= "acquireIncrement";
	public static final String acquireRetryAttempts					= "acquireRetryAttempts";
	public static final String acquireRetryDelay					= "acquireRetryDelay";
	public static final String maxConnectionAge						= "maxConnectionAge";
	public static final String maxIdleTime							= "maxIdleTime";
	public static final String maxIdleTimeExcessConnections			= "maxIdleTimeExcessConnections";
	
	public static final String maxAdministrativeTaskTime			= "maxAdministrativeTaskTime";	
	public static final String maxStatements						= "maxStatements";
	public static final String maxStatementsPerConnection			= "maxStatementsPerConnection";	
	public static final String autoCommitOnClose					= "autoCommitOnClose";
	public static final String automaticTestTable					= "automaticTestTable";
	public static final String breakAfterAcquireFailure				= "breakAfterAcquireFailure";
	public static final String checkoutTimeout						= "checkoutTimeout";
	public static final String connectionCustomizerClassName		= "connectionCustomizerClassName";
	public static final String connectionTesterClassName			= "connectionTesterClassName";
	public static final String debugUnreturnedConnectionStackTraces	= "debugUnreturnedConnectionStackTraces";
	public static final String factoryClassLocation					= "factoryClassLocation";
	public static final String forceIgnoreUnresolvedTransactions	= "forceIgnoreUnresolvedTransactions";
	public static final String idleConnectionTestPeriod				= "idleConnectionTestPeriod";	
	public static final String overrideDefaultUser					= "overrideDefaultUser";
	public static final String overrideDefaultPassword				= "overrideDefaultPassword";
	public static final String password								= "password";
	public static final String preferredTestQuery					= "preferredTestQuery";
	public static final String propertyCycle						= "propertyCycle";
	public static final String testConnectionOnCheckin				= "testConnectionOnCheckin";
	public static final String testConnectionOnCheckout				= "testConnectionOnCheckout";
	public static final String unreturnedConnectionTimeout			= "unreturnedConnectionTimeout";
	public static final String user									= "user";
	public static final String usesTraditionalReflectiveProxies		= "usesTraditionalReflectiveProxies";
}