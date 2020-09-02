package com.insta.application.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames = {"registration_number","vehicle_id"})
})
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long vehicle_id;
	
	@NotNull
	@Column(name="insurance_type",unique = true)
	private String insurance_type;
	
	@NotNull
	@Column(name="registration_number",unique = true)
	private String registration_number;
	
	@NotNull
	private String model_number;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date expiry_date;
	
	@ManyToOne
	@JoinColumn(name="customerid",nullable=false)
	private Customer customer;
	
	@ColumnDefault("true")
	boolean active;

	
	public String getInsurance_type() {
		return insurance_type;
	}

	public void setInsurance_type(String insurance_type) {
		this.insurance_type = insurance_type;
	}

	public Long getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getModel_number() {
		return model_number;
	}

	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}

	public Date getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
