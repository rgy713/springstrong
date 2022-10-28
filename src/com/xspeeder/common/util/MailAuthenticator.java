
package com.xspeeder.common.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class MailAuthenticator extends Authenticator {
	
	
	private String e_mail	= null;
	
	
	private String password	= null;
	
	
	public MailAuthenticator(String e_mail, String password) {
		this.e_mail		= e_mail;
		this.password	= password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(e_mail, password);
	}
	
}
