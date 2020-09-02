package com.insta.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.insta.application.data.ExternalEmailPayloadData;
import com.insta.application.data.SMSPayloadData;
import com.insta.application.service.ExternalEmailService;
import com.insta.application.service.SMSService;

@RequestMapping("/insta")
@RestController  class ExternalEmailController 
{
	
	@Autowired ExternalEmailService ExtEmailSerObj;
	
	@PostMapping("/extemail")
	 @ResponseStatus(HttpStatus.OK) public String sendEmail(@RequestBody
			 ExternalEmailPayloadData payload) throws Exception 
	{
		System.out.println(payload.getEmail());
		
		return ExtEmailSerObj.sendEmail(payload);
	}
}
