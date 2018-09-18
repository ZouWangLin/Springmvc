package com.itheima.exception;

public class MyException extends Exception{
	public String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyException(String message) {
		super();
		this.message = message;
	}
	
}
