package com.insta.application.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insta.application.model.Agent;
import com.insta.application.model.Customer;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Long> 
{

	@Query(value="SELECT c FROM Agent c WHERE c.name=:name AND c.mobileno=:mobileno")
    public ArrayList<Agent> findAgentByNameNumber(String name,String mobileno);
	
	@Query(value="SELECT c FROM Agent c WHERE c.username=:username")
    public ArrayList<Agent> findAgentByUsername(String username);
}
