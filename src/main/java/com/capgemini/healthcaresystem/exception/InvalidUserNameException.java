package com.capgemini.healthcaresystem.exception;

public class InvalidUserNameException extends Exception {

	String msg;

	public InvalidUserNameException(String msg) {
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
