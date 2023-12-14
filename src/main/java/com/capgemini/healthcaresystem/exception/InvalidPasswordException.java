package com.capgemini.healthcaresystem.exception;

@SuppressWarnings("serial")
public class InvalidPasswordException extends RuntimeException {

	String msg;

	public InvalidPasswordException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
