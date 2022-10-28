package com.xspeeder.ws.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;

@Scope("request")
public class BaseLocale {

	@Autowired
	private MessageSource messageSource;

	public MessageSource msg()
	{
		return messageSource;
	}
	
	public Locale contextLocale()
	{
		Locale locale = LocaleContextHolder.getLocale();
		return locale;
	}
	
	public String langCountry()
	{
		Locale locale = LocaleContextHolder.getLocale();
		
		String result = locale.getLanguage() + "_" + locale.getCountry();
		
		return result;
	}

	public String getMsg( String key )
	{
		return this.messageSource.getMessage(key, null, this.contextLocale());
	}

	public String getMsg( String key, Object[] args )
	{
		return this.messageSource.getMessage(key, args, this.contextLocale());
	}
}
