/**
 * Copyright University Development Team
 * All rights reserved.
 * 
 */

package com.xspeeder.common.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class CookieBox {
	
	static Logger log = Logger.getLogger(CookieBox.class.getName());
	
	private static final String UTF8 = "UTF-8";
	public static final int AGE_7_DAY = 60*60*24*7;
    
    @SuppressWarnings("rawtypes")
    private Map cookieMap = new java.util.HashMap();
    
    @SuppressWarnings("unchecked")
    public CookieBox(HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (int i = 0 ; i < cookies.length ; i++) {
	        	log.info(" Get Cookie = " + cookies[i].getName());
	        	cookieMap.put(cookies[i].getName(), cookies[i]);
	        }
	    }
    }
    
    public static Cookie createCookie(String name, String value) throws IOException {
        
    	return new Cookie(name, URLEncoder.encode(value, CookieBox.UTF8));
    }

    public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
        
	    Cookie cookie = new Cookie(name, 
	                URLEncoder.encode(value, CookieBox.UTF8));
	    cookie.setPath(path);
	    cookie.setMaxAge(maxAge);
	    return cookie;
    }
    
    public static Cookie createCookie( String name, String value, String domain, String path, int maxAge) throws IOException {
        
	    Cookie cookie = new Cookie(name, 
	          URLEncoder.encode(value, CookieBox.UTF8));
	    cookie.setDomain(domain);
	    cookie.setPath(path);
	    cookie.setMaxAge(maxAge);
	    return cookie;
    }
    
    public Cookie getCookie(String name) {
        
    	return (Cookie)cookieMap.get(name); 
    }
    
    public String getValue(String name) throws IOException {
        
    	Cookie cookie = (Cookie)cookieMap.get(name);
    	if (cookie == null) return null;
    	return URLDecoder.decode(cookie.getValue(), CookieBox.UTF8);
    }
    
    public boolean exists(String name) {
    	return cookieMap.get(name) != null;
    }
}