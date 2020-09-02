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
import org.springframework.stereotype.Service;

import com.insta.application.data.FilterAttributeData;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.FilteredQuoteData;
import com.insta.application.data.QuoteFilterAttributeEnum;
import com.insta.application.data.QuotePayload;
import com.insta.application.data.VehicleFilterAttributeEnum;
import com.insta.application.model.Quote;
import com.insta.application.model.Vehicle;
import com.insta.application.repository.CustomerRepo;
import com.insta.application.repository.QuoteRepo;

@Service
public class QuoteService 
{
	@Autowired
    QuoteRepo qRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	VehicleService vehicleService;
	
	public Page<Quote> findAll(Pageable pageable)
	{
		return qRepo.findAll(pageable);
    }
	
	public Quote createQuote(QuotePayload payload) {
        Quote newQuote = new Quote();
        //remove strings from registration no
        payload.setVehicleNo(payload.getVehicleNo().replaceAll("\\s+","").toUpperCase());
        populateQuoteData(payload, newQuote);
        Vehicle vehicleModel = vehicleService.fetchVehiclefromPayload(payload.getVehicleNo());
        System.out.println(payload.getVehicleNo());
        if(null != vehicleModel) {
   
        	newQuote.setVehicle(vehicleModel);
        }
        //newQuote.setActive(true);
        qRepo.save(newQuote);
        return newQuote;
    }
	
	private void populateQuoteData(QuotePayload payload, Quote newQuote) {

		newQuote.setAmount(payload.getAmount());
		newQuote.setDescription(payload.getDescription());
		newQuote.setIdv(payload.getIdv());
		newQuote.setInsurercompany(payload.getInsurercompany());
		newQuote.setPackagetype(payload.getPackagetype());
		newQuote.setQuote_date(payload.getQuote_date());
		newQuote.setQuote_status(payload.getQuote_status());
		//newQuote.setQuoteid(payload.getQuoteid());
	}

	public Quote updateQuote(long id, Quote payload) 
	{
        Optional<Quote> QuoteForUpdateOpt = qRepo.findById(id);
        Quote QuoteForUpdate = QuoteForUpdateOpt.get();
        QuoteForUpdate.setAmount(payload.getAmount());
        QuoteForUpdate.setDescription(payload.getDescription());
        QuoteForUpdate.setInsurercompany(payload.getInsurercompany());
        QuoteForUpdate.setIdv(payload.getIdv());
        QuoteForUpdate.setPackagetype(payload.getPackagetype());
        QuoteForUpdate.setQuote_date(payload.getQuote_date());
        QuoteForUpdate.setQuote_status(payload.getQuote_status());
        return qRepo.save(QuoteForUpdate);
    }
	public Quote findSingleQuote(long id) 
	{
		Optional<Quote> quotes = qRepo.findById(id);
		return quotes.get();
	}
	public int deleteQuote(long id) 
	{
		try
		{
			qRepo.deleteById(id);
			return 1;
		}
		catch(Exception e)
		{
			return 0;
		}
	}

	public List<Quote> findByVehicleNo(String vehicleNo) {
		
		 Vehicle vehicleModel = vehicleService.fetchVehiclefromPayload(vehicleNo);
		 if(null != vehicleModel) {
			 List<Quote> findAll = qRepo.findAll();
			 List<Quote> filteredQuotes = findAll.stream().filter(quote -> quote.getVehicle().getRegistration_number().equalsIgnoreCase(vehicleNo)).collect(Collectors.toList());
			 if(filteredQuotes.size() > 0) {
				 return filteredQuotes;
			 }
		 }
		return null;
	}

	public FilteredQuoteData filterQuoteData(FilterDataList filterDataList, Integer page, Integer size) {

		Pageable pageObj = PageRequest.of(page, size);
		Page<Quote> quoteList = qRepo.findAll(pageObj);
		FilteredQuoteData filteredQuoteData = new FilteredQuoteData();
		
		filteredQuoteData.setFilterAttEnum(Arrays.toString(QuoteFilterAttributeEnum.values()));
		List<FilterAttributeData> filterData = filterDataList.getFilterData();
		for(FilterAttributeData data: filterData) {
			quoteList = filterQuoteList(quoteList,data);
		}
		filteredQuoteData.setQuoteDataList(quoteList);
		return filteredQuoteData;
	}

	private Page<Quote> filterQuoteList(Page<Quote> quoteList, FilterAttributeData data) {

		if(QuoteFilterAttributeEnum.VEHICLENO.toString().equalsIgnoreCase(data.getAttrName())) {
			quoteList = new PageImpl<>(quoteList.stream().filter(quote -> quote.getVehicle().getRegistration_number().equalsIgnoreCase(data.getAttrValue())).collect(Collectors.toList()));
		}
		else if(QuoteFilterAttributeEnum.EXPIRYIN.toString().equalsIgnoreCase(data.getAttrName())) 
		{
			ArrayList<Quote> filteredQuoteList = new ArrayList<Quote>();
			for(Quote quote :quoteList)
			{
				Vehicle vehicle = quote.getVehicle();
				long diff = vehicle.getExpiry_date().getTime() - new Date().getTime();
				long durationInDays = diff/1000/60/60/24;
				System.out.println(durationInDays);
				if(durationInDays < Integer.parseInt(data.getAttrValue()) && durationInDays>=0)
				{
					filteredQuoteList.add(quote);
				}
			}
			quoteList = new PageImpl<Quote>(filteredQuoteList);
		}
		if(QuoteFilterAttributeEnum.INSURANCE_TYPE.toString().equalsIgnoreCase(data.getAttrName())) {
			quoteList = new PageImpl<>(quoteList.stream().filter(quote -> quote.getVehicle().getInsurance_type().equalsIgnoreCase(data.getAttrValue())).collect(Collectors.toList()));
		}
		return quoteList;
	}
}
