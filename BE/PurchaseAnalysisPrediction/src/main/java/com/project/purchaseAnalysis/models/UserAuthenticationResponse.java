package com.project.purchaseAnalysis.models;

import org.springframework.http.HttpStatus;

public class UserAuthenticationResponse {

	private String responseMessage;
	private HttpStatus httpStatusCode;
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(HttpStatus ok) {
		this.httpStatusCode = ok;
	}
	
	
	
	
}
