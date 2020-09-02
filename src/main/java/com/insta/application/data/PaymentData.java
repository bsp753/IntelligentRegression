package com.insta.application.data;

import org.springframework.data.domain.Page;

import com.insta.application.model.PaymentInfo;


public class PaymentData{

	private double totalamountpaid;
	private double totalcommission;
	private double totaldueamount;
	private double totalinsurance_amount;
	private double totaldebate;

	private String filterAttEnum;

	private Page<PaymentInfo> paymentsList;

	public double getTotalamountpaid() {
		return totalamountpaid;
	}

	public void setTotalamountpaid(double totalamountpaid) {
		this.totalamountpaid = totalamountpaid;
	}

	public double getTotalcommission() {
		return totalcommission;
	}

	public void setTotalcommission(double totalcommission) {
		this.totalcommission = totalcommission;
	}

	public double getTotaldueamount() {
		return totaldueamount;
	}

	public void setTotaldueamount(double totaldueamount) {
		this.totaldueamount = totaldueamount;
	}

	public double getTotalinsurance_amount() {
		return totalinsurance_amount;
	}

	public void setTotalinsurance_amount(double totalinsurance_amount) {
		this.totalinsurance_amount = totalinsurance_amount;
	}

	public double getTotaldebate() {
		return totaldebate;
	}

	public void setTotaldebate(double totaldebate) {
		this.totaldebate = totaldebate;
	}

	public String getFilterAttEnum() {
		return filterAttEnum;
	}

	public void setFilterAttEnum(String filterAttEnum) {
		this.filterAttEnum = filterAttEnum;
	}

	public Page<PaymentInfo> getPaymentsList() {
		return paymentsList;
	}

	public void setPaymentsList(Page<PaymentInfo> paymentsList) {
		this.paymentsList = paymentsList;
	}

}
