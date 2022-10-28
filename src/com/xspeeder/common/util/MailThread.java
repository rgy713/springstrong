
package com.xspeeder.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class MailThread extends Thread {
	
	String smtp_host;
	String from;
	String from_name;
	String passwd;
	String to;
	String subject;
	String message;
	
	public MailThread(String smtp_host, String from, String from_name, String passwd, String to, String subject, String message) {
		this.smtp_host	= smtp_host;
		this.from		= from;
		this.from_name	= from_name;
		this.passwd		= passwd;
		this.to			= to;
		this.subject	= subject;
		this.message	= message;
	}
	
	@Override
	public void run() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host",			smtp_host);
			props.put("mail.smtp.auth",			"true");
			props.put("mail.debug",				"false");
			props.put("mail.smtp.ssl.enable",	"true");
			
		
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.starttls.enable", "true");
            
			Session session = Session.getInstance(props, new MailAuthenticator(from, passwd));
			Message msg = new MimeMessage(session);
			
			InternetAddress ia = new InternetAddress(from, from_name);
			msg.setFrom(ia);
			
			InternetAddress[] toAddresses = new InternetAddress[1];
		    toAddresses[0] = new InternetAddress(to);
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			
			msg.setSubject(subject);
			msg.setContent(message, "text/html; charset=utf-8");
			Transport.send(msg);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}
}
