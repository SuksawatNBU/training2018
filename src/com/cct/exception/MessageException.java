package com.cct.exception;

public class MessageException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3327252322892434286L;

	public MessageException(String message) {
		super(message);
	}	
	
	public MessageException(String message, Exception e) {
		super(message, e);
		super.setStackTrace(e.getStackTrace());
	}	
}
