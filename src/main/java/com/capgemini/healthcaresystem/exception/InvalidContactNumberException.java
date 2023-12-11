package com.capgemini.healthcaresystem.exception;

public class InvalidContactNumberException  extends RuntimeException{
	
	String msg;

	public InvalidContactNumberException(String msg) {
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
