package com.insta.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insta.application.model.Quote;

@Repository
public interface QuoteRepo extends JpaRepository<Quote, Long> 
{

	@Override
	@Query("Select q from  Quote q")
	public List<Quote> findAll();
	
	
	@Query("Select q from  Quote q where vehicleid=:vehicleid")
	public List<Quote> findByVehicleId(Long vehicleid);
	
}