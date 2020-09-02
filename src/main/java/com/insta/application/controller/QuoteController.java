package com.insta.application.controller;

import java.text.ParseException;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.FilteredQuoteData;
import com.insta.application.data.FilteredVehicleData;
import com.insta.application.data.QuotePayload;
import com.insta.application.model.Quote;
import com.insta.application.service.QuoteService;

@RequestMapping("/insta/quotes")
@RestController
public class QuoteController {

	@Autowired
	QuoteService qServiceobj;
	
	@GetMapping
	public Page<Quote> returnAllCustomers(Pageable pageable) 
	{
		return qServiceobj.findAll(pageable);
	}
	
	@RequestMapping("/{id}")
	public Quote returnSingleQuote(@PathVariable int id) 
	{
		return qServiceobj.findSingleQuote(id);
	}
	
	@PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Quote createVehicle(@RequestBody QuotePayload payload) throws ValidationException{
		validateQuotePayload(payload);
        return (qServiceobj.createQuote(payload));
    }
	
	@PutMapping("/update/{id}")
	public Quote updateQuote(@PathVariable long id, @RequestBody Quote Quote) 
	{
	    return qServiceobj.updateQuote(id, Quote);
	}
	
	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable int id)
	{
		return qServiceobj.deleteQuote(id);
	}
	
	@GetMapping("/vehicle/{vehicleNo}")
	public List<Quote> fetchAllQuotesForVehicle(@PathVariable String vehicleNo){
		return qServiceobj.findByVehicleNo(vehicleNo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public FilteredQuoteData filterQuoteData(@RequestBody FilterDataList filterDataList,@RequestParam(name="page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size) throws JsonProcessingException, ParseException 
	{
		page= page==null?0:page; size = (size==null) ? Integer.MAX_VALUE : size; 
		return qServiceobj.filterQuoteData(filterDataList,page,size);
	}
	
	private void validateQuotePayload(QuotePayload payload) throws ValidationException {

		if(null == payload.getVehicleNo()) {
			throw new ValidationException("Vehicle id cannot be null");
		}
		
	}

	
	
}
