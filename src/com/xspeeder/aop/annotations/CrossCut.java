package com.xspeeder.aop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xspeeder.ws.common.MediaType;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CrossCut {

	
	boolean bCheckLogin() 		default false;
	
	
	boolean bLogging() 			default false;
	
	
	String respType() 			default MediaType.APPLICATION_JSON;
}
