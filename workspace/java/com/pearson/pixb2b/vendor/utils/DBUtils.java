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
 * Title		: 	DBUtils.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PooledDataSource;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
import com.pearson.pixb2b.vendor.utils.constants.Ic3p0Constants;
/**
 * DBUtils is a utility class to make connection with the database and 
 * close the same and other database resource after the use.
 * 
 * This class provides a facility to use either connection pooling
 *  for database connections Or normal JDBC connections.  
 * 
 * @author Yogesh Tyagi
 */
public class DBUtils implements Ic3p0Constants {
	private static String c3p0PropsFile 		= null;
	private static Properties c3p0Props 		= null;

	private static DataSource pooledDataSource 	= null;
	
	private static final String user			= "user";
	private static final String password 		= "password";
	
	static {
    	try {
    		c3p0PropsFile = IConfigConstants.FILE_PATH+File.separatorChar+"c3p0.properties";
        	if (c3p0PropsFile == null) {
    			B2BLogger.info("FAILED TO READ c3p0.properties");
    			System.err.println("FAILED TO READ c3p0.properties");    			
    		}
		} catch (RuntimeException e) {
			B2BLogger.error("DBUtils : RuntimeException - FAILED TO READ c3p0.properties");
            B2BLogger.error("Exception",e);
		}
    };
	/**
	 * This method create database connection and returns the same to caller 
	 * for database communication. 
	 * 
	 * Based on the CONNECTION_POOLING property set to TRUE in b2b.config file,
	 * this method create a connection pool and return the connection from the pool.
	 * If CONNECTION_POOLING property set to FALSE then this method will create 
	 * a normal JDBC connection and return the same to caller.
	 * 
	 * @return Connection
	 * @throws SQLException
	 * @throws B2BException
	 */
	public static Connection getDBConnection() throws SQLException, B2BException {		
		DataSource unpooledDataSource 	= null;
		Connection dbCon 				= null;
		Map c3p0PropsMap				= null;		
		Properties b2bDBProps 			= null;
		
		try {
			if(IPixB2BConstants.TRUE.equalsIgnoreCase(IConfigConstants.CONNECTION_POOLING)){
				if(pooledDataSource == null){
					Class.forName (IConfigConstants.PIX_DB_DRIVER_CLASS);
					unpooledDataSource = DataSources.unpooledDataSource(IConfigConstants.PIX_DB_URL, IConfigConstants.PIX_DB_USERNAME, IConfigConstants.PIX_DB_PWD);
					//Comment next line of code if you want to use default properties of c3p0 connection pooling
					c3p0PropsMap = setc3p0Props();
					
					if(c3p0PropsMap != null && c3p0PropsMap.size() > 0)
						pooledDataSource = DataSources.pooledDataSource(unpooledDataSource, c3p0PropsMap); //Configured with custom properties - The DataSource pooledDataSource is now a fully configured and usable pooled DataSource.
					else
						pooledDataSource = DataSources.pooledDataSource(unpooledDataSource); //Configured without custom properties - The DataSource pooledDataSource is now a fully configured and usable pooled DataSource.
				}
				dbCon = pooledDataSource.getConnection();
			}else{
				b2bDBProps = new Properties();
				b2bDBProps.put (user, IConfigConstants.PIX_DB_USERNAME);
				b2bDBProps.put (password, IConfigConstants.PIX_DB_PWD);
				Class.forName (IConfigConstants.PIX_DB_DRIVER_CLASS);
				dbCon = DriverManager.getConnection(IConfigConstants.PIX_DB_URL, b2bDBProps);
			}
		} catch (Exception e) {			
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			throw new B2BException("Exception :: " + StringUtils.getStackTrace(e));
		} finally{
			unpooledDataSource 	= null;
			c3p0PropsMap		= null;		
			b2bDBProps 			= null;
			
		}
        return dbCon;
	}
	/**
	 * Close Database objects 
	 * @param obj
	 * @return boolean
	 */
	public static boolean close(Object obj){
		if(obj != null){
			if (obj instanceof PreparedStatement){
				try	{
					((PreparedStatement)obj).close ();
				}catch (Exception ex)	{
					B2BLogger.warn("PreparedStatement not closed properly");
					return false;
				}				
			}else if(obj instanceof ResultSet){
				try	{
					((ResultSet)obj).close ();
				}catch (Exception ex)	{
					B2BLogger.warn("ResultSet not closed properly");
					return false;
				}				
			}else if(obj instanceof CallableStatement){
				try	{
					((CallableStatement)obj).close ();
				}catch (Exception ex)	{
					B2BLogger.warn("CallableStatement not closed properly");
					return false;
				}				
			}else if (obj instanceof Statement){
				try	{
					((Statement)obj).close ();
				}catch (Exception ex)	{
					B2BLogger.warn("Statement not closed properly");
					return false;
				}				
			}else if (obj instanceof Connection){
				try	{
					((Connection)obj).close ();
				}catch (Exception ex)	{
					B2BLogger.warn("Connection not closed properly");
					return false;
				}				
			}else if (obj instanceof PooledDataSource){
				try	{
					((PooledDataSource)obj).close ();
				}catch (Exception e)	{
					B2BLogger.warn("PooledDataSource not closed properly");
					return false;
				}				
			}
		}else{
			return true;
		}
		return true;
	}	
	/**
	 * This method set the c3p0 connection pooling properties 
	 * from c3p0.properties file into HashMap
	 * @return HashMap
	 */
	private static HashMap setc3p0Props() {
		HashMap c3p0PropsMap = null;		
		
		if(c3p0Props == null)
			loadc3p0Props();
		
		if(c3p0Props != null && c3p0Props.size()>0){
			c3p0PropsMap = new HashMap();
			if(c3p0Props.getProperty(initialPoolSize) != null)
				c3p0PropsMap.put(initialPoolSize, c3p0Props.getProperty(initialPoolSize)); //Unreasonable values of initialPoolSize will be ignored, and minPoolSize will be used instead
			if(c3p0Props.getProperty(maxPoolSize) != null)
				c3p0PropsMap.put(maxPoolSize, c3p0Props.getProperty(maxPoolSize));
			if(c3p0Props.getProperty(minPoolSize) != null)
				c3p0PropsMap.put(minPoolSize, c3p0Props.getProperty(minPoolSize)); //minPoolSize <= maxPoolSize
			if(c3p0Props.getProperty(numHelperThreads) != null)
				c3p0PropsMap.put(numHelperThreads, c3p0Props.getProperty(numHelperThreads));
			if(c3p0Props.getProperty(acquireIncrement) != null)
				c3p0PropsMap.put(acquireIncrement, c3p0Props.getProperty(acquireIncrement));			
			if(c3p0Props.getProperty(acquireRetryAttempts) != null)
				c3p0PropsMap.put(acquireRetryAttempts, c3p0Props.getProperty(acquireRetryAttempts));			
			if(c3p0Props.getProperty(acquireRetryDelay) != null)
				c3p0PropsMap.put(acquireRetryDelay, c3p0Props.getProperty(acquireRetryDelay));
			if(c3p0Props.getProperty(maxConnectionAge) != null)
				c3p0PropsMap.put(maxConnectionAge, c3p0Props.getProperty(maxConnectionAge));
			if(c3p0Props.getProperty(maxIdleTime) != null)
				c3p0PropsMap.put(maxIdleTime, c3p0Props.getProperty(maxIdleTime));
			if(c3p0Props.getProperty(maxIdleTimeExcessConnections) != null)
				c3p0PropsMap.put(maxIdleTimeExcessConnections, c3p0Props.getProperty(maxIdleTimeExcessConnections));
			
			if(c3p0Props.getProperty(maxAdministrativeTaskTime) != null)
				c3p0PropsMap.put(maxAdministrativeTaskTime, c3p0Props.getProperty(maxAdministrativeTaskTime));
			if(c3p0Props.getProperty(maxStatements) != null)
				c3p0PropsMap.put(maxStatements, c3p0Props.getProperty(maxStatements));
			if(c3p0Props.getProperty(maxStatementsPerConnection) != null)
				c3p0PropsMap.put(maxStatementsPerConnection, c3p0Props.getProperty(maxStatementsPerConnection));		
			if(c3p0Props.getProperty(autoCommitOnClose) != null)
				c3p0PropsMap.put(autoCommitOnClose, c3p0Props.getProperty(autoCommitOnClose));
			if(c3p0Props.getProperty(automaticTestTable) != null)
				c3p0PropsMap.put(automaticTestTable, c3p0Props.getProperty(automaticTestTable));
			if(c3p0Props.getProperty(breakAfterAcquireFailure) != null)
				c3p0PropsMap.put(breakAfterAcquireFailure, c3p0Props.getProperty(breakAfterAcquireFailure));
			if(c3p0Props.getProperty(checkoutTimeout) != null)
				c3p0PropsMap.put(checkoutTimeout, c3p0Props.getProperty(checkoutTimeout));
			if(c3p0Props.getProperty(connectionCustomizerClassName) != null)
				c3p0PropsMap.put(connectionCustomizerClassName, c3p0Props.getProperty(connectionCustomizerClassName));
			if(c3p0Props.getProperty(connectionTesterClassName) != null)
				c3p0PropsMap.put(connectionTesterClassName, c3p0Props.getProperty(connectionTesterClassName));
			if(c3p0Props.getProperty(debugUnreturnedConnectionStackTraces) != null)
				c3p0PropsMap.put(debugUnreturnedConnectionStackTraces, c3p0Props.getProperty(debugUnreturnedConnectionStackTraces));
			if(c3p0Props.getProperty(factoryClassLocation) != null)
				c3p0PropsMap.put(factoryClassLocation, c3p0Props.getProperty(factoryClassLocation));
			if(c3p0Props.getProperty(forceIgnoreUnresolvedTransactions) != null)
				c3p0PropsMap.put(forceIgnoreUnresolvedTransactions, c3p0Props.getProperty(forceIgnoreUnresolvedTransactions));
			if(c3p0Props.getProperty(idleConnectionTestPeriod) != null)
				c3p0PropsMap.put(idleConnectionTestPeriod, c3p0Props.getProperty(idleConnectionTestPeriod));			
			if(c3p0Props.getProperty(overrideDefaultUser) != null)
				c3p0PropsMap.put(overrideDefaultUser, c3p0Props.getProperty(overrideDefaultUser));
			if(c3p0Props.getProperty(overrideDefaultPassword) != null)
				c3p0PropsMap.put(overrideDefaultPassword, c3p0Props.getProperty(overrideDefaultPassword));
			if(c3p0Props.getProperty(password) != null)
				c3p0PropsMap.put(password, c3p0Props.getProperty(password));
			if(c3p0Props.getProperty(preferredTestQuery) != null)
				c3p0PropsMap.put(preferredTestQuery, c3p0Props.getProperty(preferredTestQuery));
			if(c3p0Props.getProperty(propertyCycle) != null)
				c3p0PropsMap.put(propertyCycle, c3p0Props.getProperty(propertyCycle));
			if(c3p0Props.getProperty(testConnectionOnCheckin) != null)
				c3p0PropsMap.put(testConnectionOnCheckin, c3p0Props.getProperty(testConnectionOnCheckin));
			if(c3p0Props.getProperty(testConnectionOnCheckout) != null)
				c3p0PropsMap.put(testConnectionOnCheckout, c3p0Props.getProperty(testConnectionOnCheckout));
			if(c3p0Props.getProperty(unreturnedConnectionTimeout) != null)
				c3p0PropsMap.put(unreturnedConnectionTimeout, c3p0Props.getProperty(unreturnedConnectionTimeout));
			if(c3p0Props.getProperty(user) != null)
				c3p0PropsMap.put(user, c3p0Props.getProperty(user));
			if(c3p0Props.getProperty(usesTraditionalReflectiveProxies) != null)
				c3p0PropsMap.put(usesTraditionalReflectiveProxies, c3p0Props.getProperty(usesTraditionalReflectiveProxies));
		}
		return c3p0PropsMap;
	}
	/**
     * This method loads c3p0.properties file into Properties 
     * and then close the file. 
     * @return Properties
     */
    private static Properties loadc3p0Props() {
    	InputStream inStreamc3p0Props = null;
        try {
			c3p0Props = new Properties ();			
			inStreamc3p0Props = new BufferedInputStream (new FileInputStream(c3p0PropsFile));
			c3p0Props.load(inStreamc3p0Props);
			inStreamc3p0Props.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("DBUtils.loadc3p0Props() : FileNotFoundException - Error in loading properties file '"+c3p0PropsFile+"'");
        	B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
            B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("DBUtils.loadc3p0Props() : IOException - Error in loading properties file '"+c3p0PropsFile+"'");
        	B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
            B2BLogger.error("Exception",e);
		} finally{
        	inStreamc3p0Props = null;
        }
        return c3p0Props;
    }
	/**
	 * Test Method
	 * @param args
	 * @throws SQLException
	 * @throws B2BException
	 */
	/*public static void main(String args[]) throws SQLException, B2BException{
		Timestamp startTime = DateUtils.getCurrentTimestamp();	
    	System.out.println("STARTED ON : "+DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(startTime));
    	
    	Connection dbCon = null;
    	
    	dbCon = DBUtils.getDBConnection();
		System.out.println(dbCon);
		close(dbCon);
		
		dbCon = DBUtils.getDBConnection();
		System.out.println(dbCon);
		close(dbCon);
		
		dbCon = DBUtils.getDBConnection();
		System.out.println(dbCon);
		close(dbCon);
		
		dbCon = DBUtils.getDBConnection();
		System.out.println(dbCon);
		close(dbCon);
		
		dbCon = DBUtils.getDBConnection();
		System.out.println(dbCon);
		close(dbCon);
		
		Timestamp endTime = DateUtils.getCurrentTimestamp();
		long elapsedTimeMlSecs = endTime.getTime() - startTime.getTime();        
		String sTotalTimeTaken = DateUtils.millisecondsToString(elapsedTimeMlSecs);
		System.out.println("FINISHED ON : "+DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(endTime)+", TOTAL TIME TAKEN TO COMPLETE : "+sTotalTimeTaken);
	}*/
}