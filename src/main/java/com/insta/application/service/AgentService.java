package com.insta.application.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insta.application.model.Agent;
import com.insta.application.model.Partner;
import com.insta.application.repository.AgentRepo;
import com.insta.application.repository.UserRepo;

@Service
public class AgentService 
{
	@Autowired
	AgentRepo aRepo;
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	UserService uService;
	
	public Agent createAgent(Agent payload) throws Exception {
		Agent newAgent = new Agent();
        newAgent = payload;
        System.out.println();
        if( checkIfAgentExists(newAgent,"create") == false)
        {cfdsf
        	aRepo.save(newAgent);
        	uService.createUser(newAgent.getUsername(),newAgent.getPassword(),"agent");
        	return newAgent;
        }
        else
        {
        	throw new Exception("Agent with same name & mobile or username already exists!");
        }
    }
	
	private boolean checkIfAgentExists(Agent newAgent,String type) 
	{
		ArrayList<Agent> Agents = aRepo.findAgentByNameNumber(newAgent.getName(), newAgent.getMobileno());
		ArrayList<Agent> AgentsWithUsername = aRepo.findAgentByUsername(newAgent.getUsername());
		
		if(type=="update" && Agents.size()>0)
		{
			return true;
		}
		else if(type=="create" && (Agents.size()>0 || AgentsWithUsername.size()>0))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Agent updateAgent(Long id, Agent payload) throws Exception 
	{
		Optional<Agent> AgentForUpdateOpt = aRepo.findById(id);
        Agent AgentForUpdate = AgentForUpdateOpt.get();
        
		Agent newAgent = new Agent();
        newAgent = payload;
        if( checkIfAgentExists(newAgent,"update") == false || (
        			newAgent.getName().equalsIgnoreCase(AgentForUpdate.getName()) && 
        			newAgent.getMobileno().equalsIgnoreCase(AgentForUpdate.getMobileno())))
        {		
        	AgentForUpdate.setEmail(payload.getEmail());
            AgentForUpdate.setName(payload.getName());
            AgentForUpdate.setCity(payload.getCity());
            AgentForUpdate.setMobileno(payload.getMobileno());
            AgentForUpdate.setUsername(payload.getUsername());
            AgentForUpdate.setPassword(payload.getPassword());
            uService.updateUser(payload.getUsername(), payload.getPassword());
        }
        else 
        {
        	throw new Exception("Agent with same Name and Number already exists");
        }
        	
        return aRepo.save(AgentForUpdate);
        
    }

	public Agent findSingleAgent(Long id) 
	{
		Optional<Agent> Agents = aRepo.findById(id);
		return Agents.get();
	}
	
	public ArrayList<Agent> findAgentByNamePhoneno(String name, String phoneno) 
	{
		ArrayList<Agent> Agents = aRepo.findAgentByNameNumber(name, phoneno);
		return Agents;
	}
	
	public ArrayList<Agent> findAgentByUsername(String username) 
	{
		ArrayList<Agent> Agents = aRepo.findAgentByUsername(username);
		return Agents;
	}
	public int deleteAgent(Long id) 
	{
		return 0;
	}

	public Page<Agent> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return aRepo.findAll(pageable);
	}
}
