package com.insta.application.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insta.application.data.UsernameTypeAheadData;
import com.insta.application.model.Agent;
import com.insta.application.service.AgentService;
import com.insta.application.service.TypeAheadService;

@RestController
@RequestMapping("insta/typeahead")
public class TypeAheadController 
{
	@Autowired
	TypeAheadService taService;
	
	@GetMapping
	public ArrayList<UsernameTypeAheadData> returnAllAgents(Pageable pageable) 
	{
		return taService.getUsernameTypeAheads();
	}

}
