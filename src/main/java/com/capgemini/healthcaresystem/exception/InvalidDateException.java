package com.capgemini.healthcaresystem.exception;

@SuppressWarnings("serial")
public class InvalidDateException extends Exception{

	String msg;

	public InvalidDateException(String msg) {
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
