package com.insta.application.controller;

import java.text.ParseException;
import java.util.ArrayList;

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
import com.insta.application.data.PaymentData;
import com.insta.application.data.PaymentInfoRefererData;
import com.insta.application.data.RefererPaymentTotalData;
import com.insta.application.model.Customer;
import com.insta.application.model.RefererPayments;
import com.insta.application.service.CustomerService;
import com.insta.application.service.PaymentInfoRefererService;
import com.insta.application.service.RefererService;

@RestController
@RequestMapping("insta/refpayments")
public class RefererPaymentsController 
{

	@Autowired
	RefererService rpServiceobj;
	
	@Autowired
	PaymentInfoRefererService pirService;
	
	@GetMapping
	public ArrayList<RefererPayments> getAllRefPayments() 
	{
		return rpServiceobj.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public RefererPaymentTotalData returnPayments(@RequestBody FilterDataList paymentFilterDataList) throws JsonProcessingException, ParseException 
	{
		return pirService.fetchPayments(paymentFilterDataList);
	}
	
	@PostMapping("/create") 
	@ResponseStatus(HttpStatus.CREATED)
	public RefererPayments createRefPay(@RequestBody RefererPayments payload) throws Exception{
		
		return rpServiceobj.createRefPayment(payload);
	}
	
	@GetMapping("/name/{name}")
	public ArrayList<RefererPayments> returnRefPayByName(@PathVariable String name) 
	{
		return rpServiceobj.findRefPayByName(name);
	}
	
	@PutMapping("/{id}")
	public RefererPayments updateRefPay(@PathVariable Long id, @RequestBody RefererPayments RefererPayments) throws Exception 
	{
		return rpServiceobj.updateRefPayment(id, RefererPayments);
	} 
	
	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable Long id)
	{
		return rpServiceobj.deleteRefPayment(id);
	}
}
