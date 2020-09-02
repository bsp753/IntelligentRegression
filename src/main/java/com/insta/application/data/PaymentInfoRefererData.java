package com.insta.application.data;

import java.util.Date;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentInfoRefererData 
{
	
	@NonNull
	@JsonProperty("payment_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date paymentdate;
	String customerName;
	String registrationno;
	Double amount;
	String paymenttype;
	String refererName;
	
	
	public Date getPaymentdate() {
		return paymentdate;
	}
	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRegistrationno() {
		return registrationno;
	}
	public void setRegistrationno(String registrationno) {
		this.registrationno = registrationno;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPaymenttype() {
		return paymenttype;
	}
	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}
	public String getRefererName() {
		return refererName;
	}
	public void setRefererName(String refererName) {
		this.refererName = refererName;
	}

}
