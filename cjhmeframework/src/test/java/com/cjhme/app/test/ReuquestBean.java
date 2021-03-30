package com.cjhme.app.test;

import java.util.HashMap;
import java.util.Map;

public class ReuquestBean {

	public Map<String,Object> requestHeader=new HashMap<String,Object>();
	
	public Map<String,Object> requestBody=new HashMap<String,Object>();;

	public Map<String, Object> getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(Map<String, Object> requestHeader) {
		this.requestHeader = requestHeader;
	}

	public Map<String, Object> getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Map<String, Object> requestBody) {
		this.requestBody = requestBody;
	}
	
	
}
