package com.insta.application.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insta.application.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	@Query(value="SELECT c FROM Vehicle c WHERE c.registration_number=(:registration_number)")
	public Vehicle findByVehicleNo(String registration_number);

	@Query(value="SELECT c FROM Vehicle c WHERE c.registration_number LIKE %:registration_number%")
	public ArrayList<Vehicle> findListByVehicleNo(String registration_number);
	
	@Query(value="SELECT c from Vehicle c")
	public List<Vehicle> findAll();
	
	@Query(value="SELECT c from Vehicle c where customerid=:customerid")
	public List<Vehicle> findByCustomer(Long customerid);
	
}
