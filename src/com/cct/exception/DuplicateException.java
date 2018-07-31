package com.cct.exception;

public class DuplicateException extends Exception {

	private static final long serialVersionUID = 744756507979261403L;

	public DuplicateException() {
		super("10080");
	}

	public DuplicateException(String args0) {
		super(args0);
	}
}
