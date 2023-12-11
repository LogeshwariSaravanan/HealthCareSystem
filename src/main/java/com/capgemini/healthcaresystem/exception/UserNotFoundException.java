package com.capgemini.healthcaresystem.exception;

public class UserNotFoundException extends Exception{
	String msg;

	public UserNotFoundException(String msg) {
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
