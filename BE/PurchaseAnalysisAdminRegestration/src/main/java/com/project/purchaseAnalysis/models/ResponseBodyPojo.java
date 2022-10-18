package com.project.purchaseAnalysis.models;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class ResponseBodyPojo {

	private String message;
	private HttpStatus status;
	private Map<String, String> exceptionMap;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public Map<String, String> getExceptionMap() {
		return exceptionMap;
	}
	public void setExceptionMap(Map<String, String> exceptionMap) {
		this.exceptionMap = exceptionMap;
	}
	public ResponseBodyPojo(String message, HttpStatus status, Map<String, String> exceptionMap) {
		super();
		this.message = message;
		this.status = status;
		this.exceptionMap = exceptionMap;
	}
	
	
}
