package com.insta.application.data;
import java.util.ArrayList;
public class RefererPaymentTotalData 
{
	Double totalIncoming;
	Double totalOutgoing;
	Double totalDue;
	
	
	public Double getTotalDue() {
		return totalDue;
	}
	public void setTotalDue(Double totalDue) {
		this.totalDue = totalDue;
	}
	ArrayList<PaymentInfoRefererData> payRefData;
	public Double getTotalIncoming() {
		return totalIncoming;
	}
	public void setTotalIncoming(Double totalIncoming) {
		this.totalIncoming = totalIncoming;
	}
	public Double getTotalOutgoing() {
		return totalOutgoing;
	}
	public void setTotalOutgoing(Double totalOutgoing) {
		this.totalOutgoing = totalOutgoing;
	}
	public ArrayList<PaymentInfoRefererData> getPayRefData() {
		return payRefData;
	}
	public void setPayRefData(ArrayList<PaymentInfoRefererData> payRefData) {
		this.payRefData = payRefData;
	}
	

}
