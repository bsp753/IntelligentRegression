package com.insta.application.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "payment")
public class PaymentInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long paymentid;
	
	Double totalamount;
	
	Double amountpaid;
	
	Double dueamount;
	
	String paymentmode;
	
	/*
	 * @ManyToOne Customer customer;
	 */
	@JsonProperty("paymentDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date paymentDate;
	
	//Double insurance_amount;
	
	String policyno;
	
	String insurer_company;
	
	Double commission;
	
	Double rebate;
	
	String payment_status;
	
	@ManyToOne
	@JoinColumn(name="vehicleid",nullable=false)
	Vehicle vehicle;
	
	String fileUuid;
	
	/*
	 * @ColumnDefault("true") boolean active;
	 */
	
	public Long getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(Long paymentid) {
		this.paymentid = paymentid;
	}
	
	
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	public String getInsurer_company() {
		return insurer_company;
	}
	public void setInsurer_company(String insurer_company) {
		this.insurer_company = insurer_company;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public Double getAmountpaid() {
		return amountpaid;
	}
	public void setAmountpaid(Double amountpaid) {
		this.amountpaid = amountpaid;
	}
	public Double getDueamount() {
		return dueamount;
	}
	public void setDueamount(Double dueamount) {
		this.dueamount = dueamount;
	}
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public String getFileUuid() {
		return fileUuid;
	}
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	/*
	 * public boolean isActive() { return active; } public void setActive(boolean
	 * active) { this.active = active; }
	 */
}
