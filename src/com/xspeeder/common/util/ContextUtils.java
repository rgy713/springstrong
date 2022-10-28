/**
 * Copyright University Development Team
 * All rights reserved.
 * 
 */

package com.xspeeder.common.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * WebApplicationContext
 *
 * @author    : 
 * @date    : 2013. 7. 18.
 * @version    : 1.0
 *
 */
public class ContextUtils {

	
	public static ServletContext servletContext() {
		
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		ServletContext sc = sra.getRequest().getServletContext();
		
		return sc;
	}
	
    
    public static WebApplicationContext getWebAppCtx( ServletContext sc ) {
        WebApplicationContext context =    WebApplicationContextUtils.
                getRequiredWebApplicationContext( sc );
        
        return context;
    }
    
    
    public static HttpServletRequest request() {
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        
        return req;
    }
    
    
    public static HttpServletResponse response() {
        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletResponse resp = sra.getResponse();
        
        return resp;
    }
    
   
    public static HttpSession session() {
    	return ContextUtils.request().getSession();
    }
    
	
    public static String joggerRealPath() {
		
		ServletContext sc = ContextUtils.request().getServletContext();
		
		
		String path = sc.getRealPath("/");
		
		
		path += "../jogger";
		
		return path;
	}


	public static String getClientIpAddr() {

		
    	String realIP = ContextUtils.request().getHeader("X-Real-IP");
		realIP = realIP != null ? realIP : ContextUtils.request().getRemoteAddr();
		return realIP;
	}

	
	public static int getClientPort() {
        
        return ContextUtils.request().getRemotePort();
    }
    
    
    public static String classPath() {
        return servletContext().getRealPath(File.separator + "WEB-INF" + File.separator + "classes");
    }
    
    public static String webInfPath() {
        return servletContext().getRealPath(File.separator + "WEB-INF");
    }
    
   
    public static String webRootPath() {
        return servletContext().getRealPath("/");
    }
    
    
    @SuppressWarnings({ "unchecked", "unused" })
	public static String getMultipartParams( HttpServletRequest req, String name ) throws Exception
    {
    	List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
    	String result = null;
    	
    	for (FileItem item : items) {
    		String fieldName = item.getFieldName();
    		
    		if( !fieldName.equals(name) ) continue;
    		
            if (item.isFormField()) {
                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
            	result = item.getString();
                // ... (do your job here)
            } else {
                // Process form file field (input type="file").
                String fileName = FilenameUtils.getName(item.getName());
                InputStream fileContent = item.getInputStream();
                // ... (do your job here)
                
                result = fileName;
            }
        }
    	
    	return result;
    }

	public static ApplicationContext appContext()  {
		// TODO Auto-generated method stub
		ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		return appContext;
	}
}
