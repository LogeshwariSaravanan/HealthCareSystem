package com.capgemini.healthcaresystem.exception;

public class IdNotFoundException extends Exception{

	String msg;

	public IdNotFoundException(String msg) {
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
