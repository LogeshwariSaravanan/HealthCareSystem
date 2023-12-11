package com.capgemini.healthcaresystem.exception;

public class InvalidEmailIdException extends RuntimeException{
	String msg;

	public InvalidEmailIdException(String msg) {
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
