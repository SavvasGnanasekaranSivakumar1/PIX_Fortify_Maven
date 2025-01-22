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
 * Title		: 	DateUtils.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * DateUtils is a utility class that provides certain utility methods
 * for date handling used in the application.
 * 
 * @author Yogesh Tyagi
 */
public class DateUtils {
	/**
     * This method returns the current date string in YYYY-MM-DD format. 
     * @return current date in YYYY-MM-DD String
     */
    public static String getCurrentDate(String dateFormat){
        return (new SimpleDateFormat(dateFormat)).format(new Date());
    } 
    /**
     * This method returns a new Date instance and initializes it so that it 
     * represents the time at which it was allocated measured to the nearest millisecond
     * @return new Timestamp instance
     */
    public static Timestamp getCurrentTimestamp(){
        Date date = new Date();
        long milliSecs = date.getTime();
        Timestamp currentTime = new Timestamp(milliSecs);
        return currentTime;       
    }       
    /**
     * This method returns a previous date from the passed date and number of days
     * @return new Date instance
     */
    public static Date getPreviousDate(Date date, int days){
        return new Date(date.getTime() - days*24*60*60*1000);
    }    
    /**
     * This method returns a next date from the passed date and number of days
     * @return new Date instance
     */
    public static Date getNextDate(Date date, int days){
        return new Date(date.getTime() + days*24*60*60*1000);
    }
    /**
	 * Utility method to parse and return Date object, given date-value string and date-format picture. 
	 * Sample Implementation:<br>
	 * <br> parseDate("22-Apr-2009 09:39 PDT", "dd-MMM-yyyy HH:mm zzz")
	 * <br>
	 * @param dateStr
	 * @param datePicture
	 * @return Date object
	 * @throws ParseException
	 */
	public static Date parseDate (String dateStr, String datePicture) throws ParseException {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePicture);
		date = dateFormat.parse(dateStr);
		return date;
	}	
	/**
	 * Utility method to return String representation of given date object and date-format picture.
	 * Sample Implementation:<br>
	 * <br> formatDate (new Date(), "dd-MMM-yyyy");
	 * <br>
	 * @param date
	 * @param datePicture
	 * @return Date as String
	 */
	public static String formatDate (Date date, String datePicture) {
		String dateStr = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePicture);
		dateStr = dateFormat.format(date);
		return dateStr;
	}
	/**
	 * This method change the date format from one to another format.
	 * @param strDate
	 * @param strOldFormat
	 * @param strNewFormat
	 * @return String
	 * @throws ParseException
	 */
	public static String changeDateFormat(String strDate, String strOldFormat, String strNewFormat) throws ParseException{
		String strReturnDate = null;
		Date date = parseDate(strDate, strOldFormat);
		SimpleDateFormat dateFormat = new SimpleDateFormat (strNewFormat);
		strReturnDate = dateFormat.format(date).toString();
		return strReturnDate;
	}
	/**
	 * This method will return you the String representation of the given Timestamp object 
	 * in "MMM dd, yyyy hh:mm:ss a" pattern. For Example if you are passing it Timestamp object 
	 * then it will return you the formatedDateTime in this format like "Apr 22, 2009 06:16:32 PM".
	 * @param oTimeStamp The Timestamp object
	 * @return String of formatted date time
	 */
	public static String formatTimestamp(Timestamp oTimeStamp, String dateTimeFormat) {
		String formatedDateTime = "";
		if(oTimeStamp == null){
			return formatedDateTime;
		}else{
			formatedDateTime = new SimpleDateFormat(dateTimeFormat).format(oTimeStamp);
			return formatedDateTime;
		}
	}
	/**
	 * This method return SQL date
	 * @param dateStr
	 * @return java.sql.Date
	 */
	public static java.sql.Date getSqlDate(String dateStr, String inFormat, String outFormat) throws ParseException{
		java.sql.Date date = null;
		date = java.sql.Date.valueOf(changeDateFormat(dateStr,inFormat,outFormat));
		return date;
	}
	/**
	 * @param dateStr
	 * @return java.sql.Timestamp
	 */
	public static java.sql.Timestamp getSqlTimestamp(String dateStr, String inFormat, String outFormat) throws ParseException{
		Timestamp timestamp = null;
		timestamp = Timestamp.valueOf(changeDateFormat(dateStr,inFormat,outFormat));
		return timestamp;
	}
	/**
	 * This method returns the month number as string if input is monthStr
	 * @param monthStr
	 * @return String
	 */
	public String getMonth(String monthStr){
		String month = null;		
		if("Jan".equalsIgnoreCase(monthStr))
			month = "01";
		else if("Feb".equalsIgnoreCase(monthStr))
			month = "02";
		else if("Mar".equalsIgnoreCase(monthStr))
			month = "03";
		else if("Apr".equalsIgnoreCase(monthStr))
			month = "04";
		else if("May".equalsIgnoreCase(monthStr))
			month = "05";
		else if("Jun".equalsIgnoreCase(monthStr))
			month = "06";
		else if("Jul".equalsIgnoreCase(monthStr))
			month = "07";
		else if("Aug".equalsIgnoreCase(monthStr))
			month = "08";
		else if("Sep".equalsIgnoreCase(monthStr))
			month = "09";
		else if("Oct".equalsIgnoreCase(monthStr))
			month = "10";
		else if("Nov".equalsIgnoreCase(monthStr))
			month = "11";
		else if("Dec".equalsIgnoreCase(monthStr))
			month = "12";
		return month;
	}
	 /**
	 * Converts time in milliseconds to String in the format hh mm ss.
	 * @param timeInMlSecs the time in milliseconds.
	 * @return String representing the time in the format hh mm ss.
	 * @throws Exception the runtime exception.
	 */
	public static String millisecondsToString(long timeInMlSecs){		
	 	long timeInSeconds = timeInMlSecs/1000;
    	long  hours, minutes, seconds;
    	hours = timeInSeconds / 3600;
    	timeInSeconds = timeInSeconds - (hours * 3600);
    	minutes = timeInSeconds / 60;
    	timeInSeconds = timeInSeconds - (minutes * 60);
    	seconds = timeInSeconds;
    	String hmsStr = hours + " hour(s) " + minutes + " minute(s) " + seconds + " second(s)";
    	return hmsStr;
	}
	/**
     * Logic to check the total time taken by a task to complete.
     */
    public static void getTotalTimeTaken(){
		Timestamp startTime = getCurrentTimestamp();	
    	//System.out.println("STARTED ON : "+DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(startTime));
		/**
		 * Your code is executing here for which you want to check the time taken by your code execution.
		 */	
    	Timestamp endTime = getCurrentTimestamp();
		long elapsedTimeMlSecs = endTime.getTime() - startTime.getTime();        
		String sTotalTimeTaken = DateUtils.millisecondsToString(elapsedTimeMlSecs);
		//System.out.println("FINISHED ON : "+DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(endTime)+", TOTAL TIME TAKEN TO COMPLETE : "+sTotalTimeTaken);
    }
}