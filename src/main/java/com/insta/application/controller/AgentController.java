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

import com.insta.application.model.Agent;
import com.insta.application.service.AgentService;

@RestController
@RequestMapping("insta/agents")
public class AgentController 
{

	@Autowired
	AgentService aService;
	
	@GetMapping
	public Page<Agent> returnAllAgents(Pageable pageable) 
	{
		return aService.findAll(pageable);
	}


	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable Long id)
	{
		return aService.deleteAgent(id);
	}


	@GetMapping("/{id}")
	public Agent returnSingleAgent(@PathVariable Long id) 
	{
		return aService.findSingleAgent(id);
	}

	@PostMapping("/create") 
	@ResponseStatus(HttpStatus.CREATED)
	public Agent createAgent(@RequestBody Agent payload) throws Exception{
		 System.out.println("1");
		return aService.createAgent(payload);
		
	}
	
	private static String newMethod(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int b : bytes) {
            b &= 0xff;
            sb.append(HEXDIGITS[b >> 4]);
            sb.append(HEXDIGITS[b & 15]);
            sb.append(' ');
        }
        return sb.toString();
    }
	
	

	@PutMapping("/{id}")
	public Agent updateAgent(@PathVariable Long id, @RequestBody Agent Agent) throws Exception 
	{
		return aService.updateAgent(id, Agent);
	} 
	
	@GetMapping("/name/{name}")
	public ArrayList<Agent> returnCusByName(@PathVariable String name) 
	{
		return aService.findAgentByUsername(name);
	}
}
