package com.timbox.web.model;

public class ErrorObject {

	private Integer code;
	private String message;
	
	public ErrorObject() {
		this.code = 0;
		this.message = "Exito";
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
}
