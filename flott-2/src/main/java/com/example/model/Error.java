package com.example.model;

public class Error {
	String code;
	String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Error(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Error() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
