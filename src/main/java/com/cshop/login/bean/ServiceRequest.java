package com.cshop.login.bean;

public class ServiceRequest {

	private AppUser formData;
	private String requestID;

	public AppUser getFormData() {
		return formData;
	}

	public void setFormData(AppUser formData) {
		this.formData = formData;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

}
