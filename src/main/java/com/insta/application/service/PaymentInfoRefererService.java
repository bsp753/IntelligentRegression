package com.insta.application.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.util.PropertySource.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insta.application.data.FilterAttributeData;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.PaymentData;
import com.insta.application.data.PaymentFilterAttributeEnum;
import com.insta.application.data.PaymentInfoRefererData;
import com.insta.application.data.RefererPaymentAttributeEnum;
import com.insta.application.data.RefererPaymentTotalData;
import com.insta.application.model.PaymentInfo;
import com.insta.application.model.RefererPayments;
import com.insta.application.repository.PaymentRepo;
import com.insta.application.repository.RefererPaymentRepo;
import com.insta.application.service.DateSorterComparatorRefPayments;

@Service
public class PaymentInfoRefererService 
{
	@Autowired
	RefererPaymentRepo rpRepo;
	
	@Autowired
	PaymentRepo pRepo;
	
	public RefererPaymentTotalData fetchPayments(FilterDataList paymentsFilterData) throws JsonProcessingException, ParseException
	{
		List<FilterAttributeData> paymentFilterList = paymentsFilterData.getFilterData();
		ArrayList<RefererPayments> refPayDataList = (ArrayList<RefererPayments>) rpRepo.findAll();
		ArrayList<PaymentInfo> paymentList = (ArrayList<PaymentInfo>) pRepo.findAll();
		ArrayList<PaymentInfoRefererData> filteredData = new ArrayList<PaymentInfoRefererData>();
		
		//for(FilterAttributeData filterData : paymentFilterList) 
		//{
			//System.out.println(filterData.getAttrName()+"  -  "+filterData.getAttrValue());
			filteredData = filterPayments(refPayDataList,paymentList,paymentFilterList);		
		//}
		RefererPaymentTotalData refPatTotals = populateTotals(filteredData);
		
		return refPatTotals;
	}

	private RefererPaymentTotalData populateTotals(ArrayList<PaymentInfoRefererData> filteredData) 
	{
		RefererPaymentTotalData rpData = new RefererPaymentTotalData();
		double incomingTotal=0;
		double outgoingTotal=0;
		for(PaymentInfoRefererData data:filteredData)
		{
			if(data.getPaymenttype().equalsIgnoreCase("incoming"))
			{
				incomingTotal = incomingTotal+data.getAmount();
			}
			else if(data.getPaymenttype().equalsIgnoreCase("outgoing"))
			{
				outgoingTotal = outgoingTotal+data.getAmount();
			}
		}
		rpData.setTotalIncoming(Double.valueOf(String.format("%1.2f", incomingTotal)));
		rpData.setTotalOutgoing(Double.valueOf(String.format("%1.2f", outgoingTotal)));
		rpData.setPayRefData(filteredData);
		rpData.setTotalDue(Double.valueOf(String.format("%1.2f", outgoingTotal-incomingTotal)));
		return rpData;
	}

	private ArrayList<PaymentInfoRefererData> filterPayments(ArrayList<RefererPayments> refPayDataList, ArrayList<PaymentInfo> paymentList,
			List<FilterAttributeData> filterDataList) throws ParseException {
		ArrayList<PaymentInfoRefererData> dataList = new ArrayList<PaymentInfoRefererData>();
		for(PaymentInfo pi : paymentList)
		{
			PaymentInfoRefererData data = new PaymentInfoRefererData();
			// Reduce rebate before adding to main data list
			data.setAmount(pi.getTotalamount() - pi.getRebate());
			data.setCustomerName(pi.getVehicle().getCustomer().getName());
			data.setPaymenttype("Outgoing");
			data.setRefererName(pi.getVehicle().getCustomer().getReferer());
			data.setRegistrationno(pi.getVehicle().getRegistration_number());
			data.setPaymentdate(pi.getPaymentDate());
			dataList.add(data);
		}
		
		for(RefererPayments rp : refPayDataList)
		{
			PaymentInfoRefererData data = new PaymentInfoRefererData();
			data.setAmount(rp.getAmount());
			data.setCustomerName("");
			data.setPaymenttype("Incoming");
			data.setRefererName(rp.getReferrerName());
			data.setRegistrationno("");
			data.setPaymentdate(rp.getPayment_date());
			dataList.add(data);		
		}
		for(FilterAttributeData filterData:filterDataList)
		{
			if(RefererPaymentAttributeEnum.REFERERNAME.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
			{
				dataList = (ArrayList<PaymentInfoRefererData>) dataList.stream().filter(p -> p.getRefererName().equalsIgnoreCase(filterData.getAttrValue())).collect(Collectors.toList());
			}
			else if(RefererPaymentAttributeEnum.PAYMENTTYPE.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
			{
				dataList = (ArrayList<PaymentInfoRefererData>) dataList.stream().filter(p -> p.getPaymenttype().equalsIgnoreCase(filterData.getAttrValue())).collect(Collectors.toList());
			}
			else if(RefererPaymentAttributeEnum.STARTDATE.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(filterData.getAttrValue());
				dataList = (ArrayList<PaymentInfoRefererData>) dataList.stream().filter(p -> p.getPaymentdate().compareTo(date) >=0).collect(Collectors.toList());
			}
			else if(RefererPaymentAttributeEnum.ENDDATE.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(filterData.getAttrValue());
				dataList = (ArrayList<PaymentInfoRefererData>) dataList.stream().filter(p -> p.getPaymentdate().compareTo(date) <=0).collect(Collectors.toList());
			}
		}
		dataList.sort(new DateSorterComparatorRefPayments());
		return dataList;
	}

}
