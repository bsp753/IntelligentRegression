package com.insta.application.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insta.application.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> 
{
	@Query(value="SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    public ArrayList<Customer> findCustomerByName(String name);
	
	@Query(value="SELECT c FROM Customer c WHERE c.name=:name AND c.phone_no=:phoneno")
    public ArrayList<Customer> findCustomerByNameNumber(String name,String phoneno);
	
	@Query(value="SELECT c FROM Customer c WHERE c.type=:type AND c.code=:code")
    public ArrayList<Customer> findCustomerByRefCode(String type,String code);
	
}
