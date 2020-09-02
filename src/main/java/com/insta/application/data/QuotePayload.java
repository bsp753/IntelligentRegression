package com.insta.application.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuotePayload {

	
	Long quoteid;
	String vehicleNo;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date quote_date;
	String quote_status;
	String packagetype;
	String insurercompany;
	double idv;
	double amount;
	String description;
	
	
	public String getInsurercompany() {
		return insurercompany;
	}
	public void setInsurercompany(String insurercompany) {
		this.insurercompany = insurercompany;
	}
	public Long getQuoteid() {
		return quoteid;
	}
	public void setQuoteid(Long quoteid) {
		this.quoteid = quoteid;
	}
	public Date getQuote_date() {
		return quote_date;
	}
	public void setQuote_date(Date quote_date) {
		this.quote_date = quote_date;
	}
	public String getQuote_status() {
		return quote_status;
	}
	public void setQuote_status(String quote_status) {
		this.quote_status = quote_status;
	}
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
	public double getIdv() {
		return idv;
	}
	public void setIdv(double idv) {
		this.idv = idv;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	
}
