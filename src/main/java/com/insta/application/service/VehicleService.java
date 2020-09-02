package com.insta.application.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.insta.application.data.FilterAttributeData;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.FilteredVehicleData;
import com.insta.application.data.VehicleFilterAttributeEnum;
import com.insta.application.data.VehiclePayload;
import com.insta.application.model.Customer;
import com.insta.application.model.PaymentInfo;
import com.insta.application.model.Quote;
import com.insta.application.model.Vehicle;
import com.insta.application.repository.PaymentRepo;
import com.insta.application.repository.QuoteRepo;
import com.insta.application.repository.VehicleRepository;

@Component
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepo;

	@Autowired
	private PaymentRepo pRepo;
	
	@Autowired
	private QuoteRepo qRepo;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	PaymentInfoService pServiceobj;
	
	@Autowired
	QuoteService qServiceobj;

	public List<Vehicle> getAllVehicles(){
		return vehicleRepo.findAll();
	}

	public Vehicle addVehicle(VehiclePayload vehicleData) throws Exception 
	{
		Vehicle vehicle = new Vehicle();
		populateVehicleData(vehicleData,vehicle);
		//remove all spaces from registration-no and change it to upper case
		vehicle.setRegistration_number(vehicle.getRegistration_number().replaceAll("\\s+","").toUpperCase());
		//vehicle.setActive(true);
		return vehicleRepo.save(vehicle);
	}

	
	public int deleteVehicleInfo(Long id) throws Exception 
	{
		try
		{
		deleteQuotesForVehicle(id);
		deletePaymentsForVehicle(id);
		System.out.println("Back to main method");
		vehicleRepo.deleteById(id);
		return 0;
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
			throw new Exception("Error occured while vehicle deletion");
			
		}
		
	}

	private void deletePaymentsForVehicle(Long id) 
	{
		List<PaymentInfo> paymentsForVehicle=new ArrayList<PaymentInfo>();
		paymentsForVehicle = pRepo.findByVehicleId(id);
		for(PaymentInfo payment:paymentsForVehicle)
			
		{
			System.out.println(payment.getPaymentid());
			pServiceobj.deletePaymentInfo(payment.getPaymentid());
		}
		
		
	}

	private void deleteQuotesForVehicle(Long id) 
	{
		List<Quote> quotesForVehicle=new ArrayList<Quote>();
		quotesForVehicle = qRepo.findByVehicleId(id);
		for(Quote quote:quotesForVehicle)
		{
			System.out.println(quote.getQuoteid());
			qServiceobj.deleteQuote(quote.getQuoteid());
		}
		
	}

	private void populateVehicleData(VehiclePayload vehicleData, Vehicle vehicle) throws Exception 
	{

		vehicle.setExpiry_date(vehicleData.getExpiryDate());
		vehicle.setModel_number(vehicleData.getModelNumber());
		vehicle.setRegistration_number(vehicleData.getRegistrationNumber());
		vehicle.setInsurance_type(vehicleData.getInsurance_type());
		Long customerId = vehicleData.getCustomerId();
		String registrationno = vehicleData.getRegistrationNumber();
		Customer singleCustomer = null;
		try 
		{
			singleCustomer = customerService.findSingleCustomer(customerId);
			if(null != singleCustomer) 
			{
				vehicle.setCustomer(singleCustomer);
			}
		}
		catch(Exception e) {
			throw new Exception(" User with Id is not found ");
		}

		Vehicle vehi = vehicleRepo.findByVehicleNo(registrationno.replaceAll("\\s+","").toUpperCase());
		if(null != vehi) 
		{
			throw new Exception("Vehicle already exists ");
		}
	}


	public Vehicle fetchVehiclefromPayload(String vehicleNo) {

		Vehicle byVehicleNo = vehicleRepo.findByVehicleNo(vehicleNo);
		if(null != byVehicleNo) {
			return byVehicleNo;
		}
		return null;
	}

	public ArrayList<Vehicle> fetchVehicleListByRegNo(String vehicleNo) 
	{

		ArrayList<Vehicle> byVehicleNo = vehicleRepo.findListByVehicleNo(vehicleNo);
		ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
		if(null != byVehicleNo && byVehicleNo.size()> 0) 
		{
			vehicleList.add(byVehicleNo.get(0));
		}
		return vehicleList;
	}

	public Vehicle updateVehicle(Long id, Vehicle vehicle) throws Exception 
	{
		Optional<Vehicle> VehicleForUpdateOpt = vehicleRepo.findById(id);
		Vehicle VehicleForUpdate=VehicleForUpdateOpt.get();
		Vehicle vehi = vehicleRepo.findByVehicleNo(vehicle.getRegistration_number().replaceAll("\\s+","").toUpperCase());
		System.out.println(VehicleForUpdate.getRegistration_number());
		
		if(null == vehi || (vehi.getRegistration_number().equalsIgnoreCase(VehicleForUpdate.getRegistration_number()))) 
		{
			VehicleForUpdate.setExpiry_date(vehicle.getExpiry_date());
			VehicleForUpdate.setModel_number(vehicle.getModel_number());
			VehicleForUpdate.setRegistration_number(vehicle.getRegistration_number());
			VehicleForUpdate.setInsurance_type(vehicle.getInsurance_type());
		}
		else
		{
			throw new Exception ("Vehicle Already Exists");
		}
		return vehicleRepo.save(VehicleForUpdate);
	}

	public Page<Vehicle> getAllVehicles(Pageable pageable) {
		return vehicleRepo.findAll(pageable);
	}

	/*
	 * private void deleteRelationalRecords(Vehicle vehicle) {
	 * 
	 * ArrayList<PaymentInfo> findByVehicleNo =
	 * pServiceobj.findByVehicleNo(vehicle.getRegistration_number());
	 * 
	 * for(PaymentInfo payment: findByVehicleNo) { payment.setActive(false); }
	 * 
	 * List<Quote> quoteList =
	 * qServiceobj.findByVehicleNo(vehicle.getRegistration_number()); for(Quote
	 * quote :quoteList) { quote.setActive(false); }
	 *
	}*/

	public FilteredVehicleData filterVehicles(FilterDataList filterDataList, Integer page, Integer size) {

		Pageable pageObj = PageRequest.of(page, size);
		Page<Vehicle> vehicleList = vehicleRepo.findAll(pageObj);
		FilteredVehicleData filteredVehicleData = new FilteredVehicleData();
		
		filteredVehicleData.setFilterAttEnum(Arrays.toString(VehicleFilterAttributeEnum.values()));
		List<FilterAttributeData> filterData = filterDataList.getFilterData();
		for(FilterAttributeData data: filterData) {
			vehicleList = filterVehicleList(vehicleList,data);
		}
		filteredVehicleData.setVehicleList(vehicleList);
		return filteredVehicleData;
	}

	private Page<Vehicle> filterVehicleList(Page<Vehicle> vehicleList, FilterAttributeData data) {

		if(VehicleFilterAttributeEnum.VEHICLENO.toString().equalsIgnoreCase(data.getAttrName())) {
			vehicleList = new PageImpl<>(vehicleList.stream().filter(vehicle -> vehicle.getRegistration_number().equalsIgnoreCase(data.getAttrValue())).collect(Collectors.toList()));
		}else if(VehicleFilterAttributeEnum.CUSTOMERID.toString().equalsIgnoreCase(data.getAttrName())) {
			vehicleList = new PageImpl<>(vehicleList.stream().filter(vehicle -> vehicle.getCustomer().getCustomerid().toString().equalsIgnoreCase(data.getAttrValue())).collect(Collectors.toList()));
		}
		else if(VehicleFilterAttributeEnum.REFERER.toString().equalsIgnoreCase(data.getAttrName())) 
		{
			vehicleList = new PageImpl<>(vehicleList.stream().filter(vehicle -> vehicle.getCustomer().getReferer().toString().equalsIgnoreCase(data.getAttrValue())).collect(Collectors.toList()));
		}
		else if(VehicleFilterAttributeEnum.EXPIRYIN.toString().equalsIgnoreCase(data.getAttrName())) 
		{
			ArrayList<Vehicle> filteredVehicleList = new ArrayList<Vehicle>();
			for(Vehicle vehicle :vehicleList)
			{
				//Date dt2 = new DateAndTime().getCurrentDateTime();
				long diff = vehicle.getExpiry_date().getTime() - new Date().getTime();
				long durationInDays = diff/1000/60/60/24;
				if(durationInDays < Integer.parseInt(data.getAttrValue()) && durationInDays>=0)
				{
					filteredVehicleList.add(vehicle);
				}
			}
			vehicleList = new PageImpl<Vehicle>(filteredVehicleList);
		}
		else if(VehicleFilterAttributeEnum.INSURANCE_TYPE.toString().equalsIgnoreCase(data.getAttrName())) 
		{
			vehicleList = new PageImpl<>(vehicleList.stream().filter(vehicle -> vehicle.getInsurance_type().toString().equalsIgnoreCase(data.getAttrValue())).collect(Collectors.toList()));
		}
		return vehicleList;
	}

}
