package com.cct.exception;

public class AuthenticateException  extends Exception {

	private static final long serialVersionUID = 1170045810112294610L;

	public AuthenticateException() {
		super("10081");
	}

	public AuthenticateException(String args0) {
		super(args0);
	}

}