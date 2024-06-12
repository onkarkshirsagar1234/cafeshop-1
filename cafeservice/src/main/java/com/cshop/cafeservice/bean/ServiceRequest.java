package com.cshop.cafeservice.bean;

public class ServiceRequest {

	private Cafe formData;
	private String requestID;

	public Cafe getFormData() {
		return formData;
	}

	public void setFormData(Cafe formData) {
		this.formData = formData;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

}
