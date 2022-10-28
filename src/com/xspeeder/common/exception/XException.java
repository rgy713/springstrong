

package com.xspeeder.common.exception;


public class XException extends Exception {

	private static final long serialVersionUID = -2187524372224052771L;
	
	
	protected String	errCode	= null;
	protected String	optional = null;


	public XException() {
		super();
	}

	
	public XException(String errCode) {
		
		super(errCode);
		
		this.errCode = errCode;
	}

	/**
	 * constructor
	 */
	public XException(String errCode, String optional) {
		
		super(errCode);
		
		this.errCode = errCode;
		this.optional = optional;
	}

	/*
	 * getter & setter
	 */
	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}
}
