
package com.xspeeder.common.util.property;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.xspeeder.common.util.ContextUtils;

public class PropertyUtil {
	
	public static String getProperty(String	locale, String key) {
		
		ServletContext sc = ContextUtils.servletContext();
		Properties property = (Properties)ContextUtils.getWebAppCtx(sc).getBean("resource_" + locale);
		
		String value = "";
		
		if( property != null )
			value = property.getProperty(key);
				
		return value;
	}
	
	public static String getProperty(String 	locale,
							  String 	key, 
							  String[] 	params) {

		String value = PropertyUtil.getProperty(locale, key);
		
		if( params == null ) return value;
		
		for (int i = 0; i < params.length; i++) {
			if( params[i] == null )
				params[i] = "";
			
			value = value.replaceFirst("\\{" + i + "\\}", params[i].replace("$", "\\$"));
		}
		return value;
	}
}
