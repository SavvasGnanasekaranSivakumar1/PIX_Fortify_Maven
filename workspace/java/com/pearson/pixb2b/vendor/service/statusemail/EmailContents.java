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
 * Title		: 	EmailSender.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	29 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.statusemail;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorLogDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;

/**
 * EmailContents generates email body contents.
 * 
 * @author Yogesh Tyagi
 */
public class EmailContents{
	/**
	 * This method returns the success/failure email contents.
	 * @param currentDate
	 * @param ftpServerDir
	 * @param successXmlCount
	 * @param failureXmlCount
	 * @return StringBuffer
	 */
	public StringBuffer getEmailContents(String emailType, String currentDate, String ftpServerDir, int successXmlCount, int failureXmlCount, String failedXmlDir, ArrayList finalInValidXmlList) {
		StringBuffer sbBodyContent	= null;
		HashMap hmXmlWithError 		= null;
		Iterator itr 				= null;
		ArrayList errorList 		= null;
		
      	try {
      		B2BLogger.debug("EmailContents.getEmailContents() method called FOR EMAIL TYPE = "+emailType);
      		sbBodyContent = new StringBuffer();
      		sbBodyContent.append("<html>");
      		sbBodyContent.append("<head>");
      		sbBodyContent.append("<style>");
      		sbBodyContent.append("<!--");
      		sbBodyContent.append("p.MsoNormal, li.MsoNormal, div.MsoNormal {margin:0in; margin-bottom:.0001pt; font-size:12.0pt; font-family:Times New Roman,serif;}");
      		sbBodyContent.append("a:link, span.MsoHyperlink {mso-style-priority:99; color:blue; text-decoration:underline;}");
      		sbBodyContent.append("a:visited, span.MsoHyperlinkFollowed {mso-style-priority:99; color:purple; text-decoration:underline;}");
      		sbBodyContent.append("p {mso-style-priority:99; mso-margin-top-alt:auto; margin-right:0in; mso-margin-bottom-alt:auto; margin-left:0in; font-size:12.0pt; font-family:Times New Roman,serif;}");
      		sbBodyContent.append("@page Section1 {size:8.5in 11.0in; margin:1.0in 1.0in 1.0in 1.0in;} div.Section1 {page:Section1;}");
      		sbBodyContent.append("-->");
      		sbBodyContent.append("</style>");
      		sbBodyContent.append("</head>");
      		sbBodyContent.append("<body lang=EN-US link=blue vlink=purple>");      		
      		sbBodyContent.append("<div class=Section1>");
      		sbBodyContent.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'>");
      		
      		if(IPixB2BConstants.EMAIL_BA_XML_JOB_RUN.equals(emailType)){
      			sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>Dear All,<br><br>Greetings !!<br><br>PIX Usage Business Ack. XML processed successfully as on "+currentDate+". Please find the files at location:</span></p>");
          		sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>FTP:&nbsp;<b>"+ftpServerDir+"</b></span></p>");
      		}if(IPixB2BConstants.EMAIL_BA_XML_VALIDATION_FAILURE.equals(emailType)){
      			sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>Dear All,<br><br>Please find the below errors while doing validation of BA XML with the BA Schema as on "+currentDate+". Please check the failed xml at POEPYE server under <b>"+failedXmlDir+"</b> directory.</span></p>");
      		}  		
      		
      		sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>Summary of the files processed:</span></p>");
      		
      		if(IPixB2BConstants.EMAIL_BA_XML_JOB_RUN.equals(emailType)){
      			sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'><b>Success XML Count : "+successXmlCount+"<br>Failure XML Count : "+failureXmlCount+"</b></span></p>");
      		}if(IPixB2BConstants.EMAIL_BA_XML_VALIDATION_FAILURE.equals(emailType)){
      			if(finalInValidXmlList != null && finalInValidXmlList.size() > 0){
      				sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>");
      				sbBodyContent.append("<table width='100%' cellspacing='0' cellpadding='5' border='1'>");
      				sbBodyContent.append("<tr style='font-size:10.0pt; font-family:Arial,sans-serif'><td align='center'><b>S#</b></td><td align='center'><b>XML File</b></td><td align='center'><b>Error</b></td></tr>");
      				for(int i=0; i < finalInValidXmlList.size(); i++){
      					hmXmlWithError 	= (HashMap)finalInValidXmlList.get(i);
    					if(hmXmlWithError != null && hmXmlWithError.size() > 0){
    						itr = hmXmlWithError.keySet().iterator();
    						while (itr.hasNext()) {
    							String fileName = (String) itr.next();
    							errorList = (ArrayList)hmXmlWithError.get(fileName);
    							sbBodyContent.append("<tr style='font-size:10.0pt; font-family:Arial,sans-serif'>");
    							sbBodyContent.append("<td nowrap='nowrap'>"+(i+1)+"</td>");
    							sbBodyContent.append("<td nowrap='nowrap'>"+fileName.substring(fileName.lastIndexOf(File.separatorChar)+1)+"</td><td>");
    							int errorCount = 1;
    							for(int j=0; j<errorList.size(); j++){
    		      					String errorStr = (String)errorList.get(j);
    		      					sbBodyContent.append((j+1)+".&nbsp;"+errorStr);
    		      					if(errorList.size() > 1 && errorList.size() != errorCount)
    		      						sbBodyContent.append("<br><br>");
    		      					errorCount++;
    		      				}    
    							sbBodyContent.append("</td>");
    							sbBodyContent.append("</tr>");
    						}	
    					}
      				}
      				sbBodyContent.append("</table>");
      				sbBodyContent.append("</span></p>");
      			}
      		}      		
      		sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>Best Regards,<br>PIX Administrator</span></p>");
      		sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt;font-family:Arial,sans-serif; color:red'><i>*<b> This IS an auto-generated mail please don't reply TO it.</b></i></span></p>");
      		
      		sbBodyContent.append("</table>");
      		sbBodyContent.append("</div>");
      		sbBodyContent.append("</body>");
      		sbBodyContent.append("</html>");			
			B2BLogger.debug("EmailContents.getEmailContents() method return");
		} catch (Exception e) {
			B2BLogger.error("Exception :: "+StringUtils.getStackTrace(e));
			e.printStackTrace();
		} 
		return sbBodyContent;
	}

	public StringBuffer getErrorEmailContents(String emailType,	String currentDate, ArrayList errorList) {
		StringBuffer sbBodyContent	= null;
		HashMap hmXmlWithError 		= null;
		Iterator itr 				= null;
		ErrorLogDTO errorLogDTO		= null;
		String errorDescription 	= null;
		String fileName				= null;
		String poNumber				= null;
      	try {
      		B2BLogger.debug("EmailContents.getEmailContents() method called FOR EMAIL TYPE = "+emailType);
      		sbBodyContent = new StringBuffer();
      		sbBodyContent.append("<html>");
      		sbBodyContent.append("<head>");
      		sbBodyContent.append("<style>");
      		sbBodyContent.append("<!--");
      		sbBodyContent.append("p.MsoNormal, li.MsoNormal, div.MsoNormal {margin:0in; margin-bottom:.0001pt; font-size:12.0pt; font-family:Times New Roman,serif;}");
      		sbBodyContent.append("a:link, span.MsoHyperlink {mso-style-priority:99; color:blue; text-decoration:underline;}");
      		sbBodyContent.append("a:visited, span.MsoHyperlinkFollowed {mso-style-priority:99; color:purple; text-decoration:underline;}");
      		sbBodyContent.append("p {mso-style-priority:99; mso-margin-top-alt:auto; margin-right:0in; mso-margin-bottom-alt:auto; margin-left:0in; font-size:12.0pt; font-family:Times New Roman,serif;}");
      		sbBodyContent.append("@page Section1 {size:8.5in 11.0in; margin:1.0in 1.0in 1.0in 1.0in;} div.Section1 {page:Section1;}");
      		sbBodyContent.append("-->");
      		sbBodyContent.append("</style>");
      		sbBodyContent.append("</head>");
      		sbBodyContent.append("<body lang=EN-US link=blue vlink=purple>");      		
      		sbBodyContent.append("<div class=Section1>");
      		sbBodyContent.append("<table width='100%' cellspacing='1' cellpadding='0' border='0'>");
      		
      		if(IPixB2BConstants.EMAIL_BA_PO_REPORT_JOB_RUN.equals(emailType)){
      			sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>Dear All,<br><br>Greetings !!<br><br>Following are the unprocessed Purchase Orders as on "+currentDate+". The details are mentioned below:</span></p>");
      			sbBodyContent.append("<table cellspacing='1' and cellpadding='5' align='Center' border='1'>");
      			sbBodyContent.append("<tr><td>PO NUMBER</td><td>File Name</td><td>Error Description</td>");
      			for(int i=0; i<errorList.size(); i++){
      				errorDescription= null;
      				fileName		= null;
      				poNumber		= null;
      				errorLogDTO = (ErrorLogDTO)errorList.get(i);
      				errorDescription = errorLogDTO.getErrorDescription();
      				fileName		 = errorLogDTO.getFileName();
      				poNumber		 = errorLogDTO.getPoNumber();
      				
      				sbBodyContent.append("<tr><td>"+poNumber+"</td><td>"+fileName+"</td><td>"+errorDescription+"</td>");
      			}
      			sbBodyContent.append("</table><br/>");
          		sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt; font-family:Arial,sans-serif'>Best Regards,<br>PIX Administrator</span></p>");
          		sbBodyContent.append("<p class=MsoNormal style='margin-bottom:12.0pt'><span style='font-size:10.0pt;font-family:Arial,sans-serif; color:red'><i>*<b> This IS an auto-generated mail please don't reply TO it.</b></i></span></p>");
          		
          		sbBodyContent.append("</table>");
          		sbBodyContent.append("</div>");
          		sbBodyContent.append("</body>");
          		sbBodyContent.append("</html>");			
    			B2BLogger.debug("EmailContents.getEmailContents() method return");
      		}
      	}catch (Exception e) {
			B2BLogger.error("Exception :: "+StringUtils.getStackTrace(e));
			e.printStackTrace();
		} 
		return sbBodyContent;
	}
}