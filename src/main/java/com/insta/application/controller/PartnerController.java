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

import com.insta.application.model.Partner;
import com.insta.application.service.PartnerService;

@RestController
@RequestMapping("insta/partners")
public class PartnerController 
{

	@Autowired
	PartnerService aService;
	
	@GetMapping
	public Page<Partner> returnAllPartners(Pageable pageable) 
	{
		return aService.findAll(pageable);
	}


	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable Long id)
	{
		return aService.deletePartner(id);
	}


	@GetMapping("/{id}")
	public Partner returnSinglePartner(@PathVariable Long id) 
	{
		return aService.findSinglePartner(id);
	}

	@PostMapping("/create") 
	@ResponseStatus(HttpStatus.CREATED)
	public Partner createPartner(@RequestBody Partner payload) throws Exception{
		 System.out.println("1");
		return aService.createPartner(payload);
		
	}
	
	

	@PutMapping("/{id}")
	public Partner updatePartner(@PathVariable Long id, @RequestBody Partner Partner) throws Exception 
	{
		return aService.updatePartner(id, Partner);
	} 
	
	@GetMapping("/name/{name}")
	public ArrayList<Partner> returnCusByName(@PathVariable String name) 
	{
		return aService.findPartnerByUsername(name);
	}
}
