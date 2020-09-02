package com.insta.application.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Quote 
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long quoteid;
	
	@NonNull
	@JsonProperty("quote_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date quote_date;
	
	@NonNull
	String quote_status;
	
	@NonNull
	String insurercompany;
	
	@NonNull
	String packagetype;
	
	@NonNull
	Double idv;
	
	@NonNull
	Double amount;
	
	
	@NonNull
	String description;

	@ManyToOne
	@JoinColumn(name="vehicleid",nullable=false)
	private Vehicle vehicle;
	
	/*
	 * @ColumnDefault("true") boolean active;
	 */
	
	
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
	public void setAmount(Double amount) {
		this.amount = amount;
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
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Double getIdv() {
		return idv;
	}
	public void setIdv(Double idv) {
		this.idv = idv;
	}
	/*
	 * public boolean isActive() { return active; } public void setActive(boolean
	 * active) { this.active = active; }
	 */
}
