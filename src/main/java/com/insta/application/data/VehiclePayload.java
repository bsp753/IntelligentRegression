package com.insta.application.data;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VehiclePayload {

	private String registrationNumber;
	private String modelNumber;
	private Long customerId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date expiryDate;
	private String insurance_type;
	
	
	public String getInsurance_type() {
		return insurance_type;
	}
	public void setInsurance_type(String insurance_type) {
		this.insurance_type = insurance_type;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
