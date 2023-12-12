package com.capgemini.healthcaresystem.exception;

public class IdAlreadyExistException extends Exception {
	String msg;

	public IdAlreadyExistException(String msg) {
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
