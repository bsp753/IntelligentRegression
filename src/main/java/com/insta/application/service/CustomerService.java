package com.insta.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insta.application.model.Agent;
import com.insta.application.model.Customer;
import com.insta.application.model.Partner;
import com.insta.application.model.Vehicle;
import com.insta.application.repository.AgentRepo;
import com.insta.application.repository.CustomerRepo;
import com.insta.application.repository.PartnerRepo;
import com.insta.application.repository.VehicleRepository;;

@Service
public class CustomerService 
{
	@Autowired
    CustomerRepo cRepo;
	
	@Autowired
	VehicleRepository vehicleRepo;
	
	@Autowired
	InstaUtils instaUtils;
	
	@Autowired
	VehicleService vSeriviceObj;
	
	@Autowired
	PartnerRepo pRepo;
	
	@Autowired
	AgentRepo aRepo;
	dsfsd Page<Customer> findAll(Pageable pageable)
	{
		return cRepo.findAll(pageable);
    }
	
	public Customer createCustomer(Customer payload) throws Exception {
		Customer newCustomer = new Customer();
        newCustomer = payload;
        System.out.println(payload.getType()+"eeee");
        System.out.println(payload.getCode()+"ffff");
        if( checkIfCustomerExists(newCustomer) == true)
        {
        	throw new Exception("Customer already exists");
        }
        else if((payload.getCode().isEmpty() && payload.getType().isEmpty()))
        {
        	cRepo.save(newCustomer);
        	return newCustomer;
        }
        else if(payload.getType().isEmpty()==false && payload.getType().equalsIgnoreCase("agent")==false && payload.getType().equalsIgnoreCase("partner")==false)
    	{
    		throw new Exception("Invalid type!");
    	}
        else if(payload.getType().isEmpty()==false && payload.getCode().isEmpty()==true)
    	{
    		throw new Exception("empty code");
    	}
        else if(payload.getType().equalsIgnoreCase("agent")|| payload.getType().equalsIgnoreCase("partner"))
        {
        	if(checkIfUsernameExists(payload.getCode(),payload.getType()))
        	{
        		cRepo.save(newCustomer);
            	return newCustomer;
        	}
        	else 
            {
        		throw new Exception("code does not exist for "+payload.getType());
        	}
        }
        else 
        {
    		throw new Exception("Error Saving Customer");
    	}
    }
	
	public boolean checkIfUsernameExists(String refcode, String reftype)
	{
		boolean userExists=false;
		ArrayList<Partner> partners;
		ArrayList<Agent> agents;
		if(reftype.equalsIgnoreCase("agent"))
		{
			agents = aRepo.findAgentByUsername(refcode);
			if(agents.size()==1)
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else if(reftype.equalsIgnoreCase("partner"))
		{
			partners = pRepo.findPartnerByUsername(refcode);
			if(partners.size()==1)
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		//return userExists;
				
	}
	
	private boolean checkIfCustomerExists(Customer newCustomer) 
	{
		ArrayList<Customer> customers = cRepo.findCustomerByNameNumber(newCustomer.getName(), newCustomer.getPhone_no());
		if(customers.size()>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Customer updateCustomer(Long id, Customer payload) throws Exception 
	{
		Optional<Customer> CustomerForUpdateOpt = cRepo.findById(id);
        Customer CustomerForUpdate = CustomerForUpdateOpt.get();
        
		Customer newCustomer = new Customer();
        newCustomer = payload;
        if( checkIfCustomerExists(newCustomer) == false || (
        			newCustomer.getName().equalsIgnoreCase(CustomerForUpdate.getName()) && 
        			newCustomer.getPhone_no().equalsIgnoreCase(CustomerForUpdate.getPhone_no())))
        {		
        	CustomerForUpdate.setEmail(payload.getEmail());
            CustomerForUpdate.setName(payload.getName());
            CustomerForUpdate.setPhone_no(payload.getPhone_no());
            CustomerForUpdate.setReferer(payload.getReferer());
            CustomerForUpdate.setAcquisition_status(payload.getAcquisition_status());
            
        }
        else 
        {
        	throw new Exception("Customer with same Name and Number already exists");
        }
        	
        return cRepo.save(CustomerForUpdate);
        
    }

	public Customer findSingleCustomer(Long id) 
	{
		Optional<Customer> customers = cRepo.findById(id);
		return customers.get();
	}
	
	public ArrayList<Customer> findCustomerByNamePhoneno(String name, String phoneno) 
	{
		ArrayList<Customer> customers = cRepo.findCustomerByNameNumber(name, phoneno);
		return customers;
	}
	public int deleteCustomer(Long id) 
	{
		try
		{
			deleteVehicleForCustomer(id);
			cRepo.deleteById(id);
			return 1;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	private void deleteVehicleForCustomer(Long id) throws Exception 
	{
		List<Vehicle> vehicleList = vehicleRepo.findByCustomer(id);
		for(Vehicle vehicle:vehicleList)	
		{
			vSeriviceObj.deleteVehicleInfo(vehicle.getVehicle_id());
		}
		
	}

	public Customer findCustomerByRegNo(String vno)
	{
		List<Vehicle> vehicleList = vehicleRepo.findAll();
		List<Vehicle> collect = vehicleList.stream().filter(vehicle -> vehicle.getRegistration_number().equals(vno)).collect(Collectors.toList());
		if(collect.size() > 0) {
			Vehicle vehicle = collect.get(0);
			Customer customer = vehicle.getCustomer();
			Optional<Customer> findById = cRepo.findById(customer.getCustomerid());
			if(findById.isPresent()) {
				 return findById.get();
			}
		}
		return null;
	}
	public ArrayList<Customer> findCustomersByName(String name)
	{
		 ArrayList<Customer> customerList = cRepo.findCustomerByName(name);
		 return customerList;
	}
}
