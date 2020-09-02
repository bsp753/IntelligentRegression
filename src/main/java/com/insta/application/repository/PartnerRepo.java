package com.insta.application.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insta.application.model.Partner;

public interface PartnerRepo extends JpaRepository<Partner, Long> 
{
	@Query(value="SELECT c FROM Partner c WHERE c.name=:name AND c.mobileno=:mobileno")
    public ArrayList<Partner> findPartnerByNameNumber(String name,String mobileno);
	
	@Query(value="SELECT c FROM Partner c WHERE c.username=:username")
    public ArrayList<Partner> findPartnerByUsername(String username);
}
