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
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * EmailSender class is used to send email.
 * 
 * @author Yogesh Tyagi
 */
public class EmailSender{
	private static Properties props	= null;
	/**
	 * This method send email to users.
	 * @param hmMailData
	 * @return integer - email delivery status
	 */
	public int sendEmail(HashMap hmMailData){				
		Session session 	= null;
		MimeMessage message = null;
		InternetAddress[] toAddresses = null;
		InternetAddress[] ccAddresses = null;
		InternetAddress[] bccAddresses= null;
		
		try {
			B2BLogger.debug("EmailSender.sendEmail() method called");
			if (IConfigConstants.MAIL_SMTP_HOST != null && !"".equals(IConfigConstants.MAIL_SMTP_HOST.trim())){
				props = new Properties();
				props.put("mail.smtp.host",IConfigConstants.MAIL_SMTP_HOST);
				
				
				//lookup Session
				Authenticator auth = new SMTPAuthenticator();
				session = Session.getInstance(props,auth);
				
				//create MimeMessage.
				message = new MimeMessage(session);

				//set FROM
				if (hmMailData.get(IPixB2BConstants.FROM_ADDRESS) != null && !"".equals(((String)hmMailData.get(IPixB2BConstants.FROM_ADDRESS)).trim()))
					message.setFrom(new InternetAddress ((String)hmMailData.get(IPixB2BConstants.FROM_ADDRESS), true));
				
				//set TO (add RECIPIENTS)
				if (hmMailData.get(IPixB2BConstants.TO_ADDRESS) != null && !"".equals(((String)hmMailData.get(IPixB2BConstants.TO_ADDRESS)).trim())){
					toAddresses = InternetAddress.parse(((String)hmMailData.get(IPixB2BConstants.TO_ADDRESS)).trim());
					message.setRecipients(MimeMessage.RecipientType.TO,toAddresses);
				}

				//set CC if present
				if (hmMailData.containsKey(IPixB2BConstants.CC_ADDRESS) && hmMailData.get(IPixB2BConstants.CC_ADDRESS) != null && !"".equals(((String)hmMailData.get(IPixB2BConstants.CC_ADDRESS)).trim())){
					ccAddresses = InternetAddress.parse(((String)hmMailData.get(IPixB2BConstants.CC_ADDRESS)).trim());
					message.setRecipients(MimeMessage.RecipientType.CC,ccAddresses);
				}
				
				//set BCC if present
				if (hmMailData.containsKey(IPixB2BConstants.BCC_ADDRESS) && hmMailData.get(IPixB2BConstants.BCC_ADDRESS) != null && !"".equals(((String)hmMailData.get(IPixB2BConstants.BCC_ADDRESS)).trim())){	
					bccAddresses = InternetAddress.parse(((String)hmMailData.get(IPixB2BConstants.BCC_ADDRESS)).trim());
					message.setRecipients(MimeMessage.RecipientType.BCC,bccAddresses);
				}
				
				//set SUBJECT
				message.setSubject((String)hmMailData.get(IPixB2BConstants.SUBJECT), "UTF-8");		
				
				//set BODY message CONTENTS
				message.setContent((String)hmMailData.get(IPixB2BConstants.BODY_CONTENTS), IPixB2BConstants.MIME_TYPE_TEXT_HTML);
				
				//set sent DATE
				message.setSentDate(new Date ());
				
				//send Email
				Transport.send(message);
				
				B2BLogger.debug("EmailSender.sendEmail() method return");
				
				//return success status
				return IPixB2BConstants.EMAIL_SEND_SUCCESS;	
			}else{
				return IPixB2BConstants.EMAIL_SEND_FAILED;
			}
		} catch (AddressException e) {			
			B2BLogger.error("AddressException :: "+StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			return IPixB2BConstants.EMAIL_SEND_FAILED;
		} catch (MessagingException e) {			
			B2BLogger.error("MessagingException :: "+StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			return IPixB2BConstants.EMAIL_SEND_FAILED;
		} finally{
			session = null;
			message = null;
			toAddresses = null;
			ccAddresses = null;
			bccAddresses = null;
		}
	}
	
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           String username = "vagraas";//SMTP_AUTH_USER;
           String password = "Pearson@1";//SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
}