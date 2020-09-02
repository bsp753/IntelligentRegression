package com.insta.application.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class RefererPayments 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NonNull
	String referrerName;

	@NonNull
	@JsonProperty("payment_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date payment_date;
	
	@NonNull
	Double Amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReferrerName() {
		return referrerName;
	}

	public void setReferrerName(String referrerName) {
		this.referrerName = referrerName;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}
	
	
}
