package com.sample.talha.response;

import org.springframework.http.HttpStatus;

public class CustomAdminResponse {

	private HttpStatus statusCode;
	private int value;
	private String responseType;
	
	
	
	public CustomAdminResponse(int value, HttpStatus statusCode, String responseType) {
		this.statusCode = statusCode;
		this.value = value;
		this.responseType = responseType;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public int getValue() {
		return value;
	}
	public void setMessage(int value) {
		this.value = value;
	}
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	
}
