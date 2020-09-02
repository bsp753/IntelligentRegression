package com.insta.application.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.insta.application.model.Customer;
import com.insta.application.service.CustomerService;


@RestController
@RequestMapping("insta/customers")
public class CustomerController 
{
	@Autowired
	CustomerService cServiceobj;

	@GetMapping
	public Page<Customer> returnAllCustomers(Pageable pageable) 
	{
		return cServiceobj.findAll(pageable);
	}

	@GetMapping("/vehicle/{vehicleNo}")
	public Customer findCustomerbyvehicleNoCustomers(@PathVariable String vehicleNo) 
	{
		return cServiceobj.findCustomerByRegNo(vehicleNo);
	}

	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable Long id)
	{
		return cServiceobj.deleteCustomer(id);
	}


	@GetMapping("/{id}")
	public Customer returnSingleCustomer(@PathVariable Long id) 
	{
		return cServiceobj.findSingleCustomer(id);
	}

	@PostMapping("/create") 
	@ResponseStatus(HttpStatus.CREATED)
	public Customer createCustomer(@RequestBody Customer payload) throws Exception{
		
		return cServiceobj.createCustomer(payload);
	}
	
	

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception 
	{
		return cServiceobj.updateCustomer(id, customer);
	} 
	
	@GetMapping("/name/{name}")
	public ArrayList<Customer> returnCusByName(@PathVariable String name) 
	{
		return cServiceobj.findCustomersByName(name);
	}
}
 