package com.insta.application.data;

import java.util.Date;

public class PaymentLimitedFieldsData 
{
	Long paymentId;
	Date paymentDate;
	String registrationNumber;
	String email;
	String FileUUID;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getFileUUID() {
		return FileUUID;
	}
	public void setFileUUID(String fileUUID) {
		FileUUID = fileUUID;
	}

}
