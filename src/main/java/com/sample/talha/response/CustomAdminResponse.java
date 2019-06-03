package com.sample.talha.response;

import org.springframework.http.HttpStatus;

public class CustomAdminResponse {

	private HttpStatus statusCode;
	private int value;
	private String response;
	
	
	
	public CustomAdminResponse(int value, HttpStatus statusCode, String response) {
		this.statusCode = statusCode;
		this.value = value;
		this.response = response;
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
