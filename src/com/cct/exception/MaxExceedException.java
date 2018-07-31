package com.cct.exception;

public class MaxExceedException extends Exception {

	private static final long serialVersionUID = 1896899296798918384L;

	public MaxExceedException() {
		super("30013");
	}

	public MaxExceedException(String args0) {
		super(args0);
	}
}
