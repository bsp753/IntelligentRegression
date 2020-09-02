package com.insta.application.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insta.application.model.Partner;
import com.insta.application.model.Partner;
import com.insta.application.repository.PartnerRepo;
import com.insta.application.repository.PartnerRepo;
import com.insta.application.repository.UserRepo;

@Service
public class PartnerService 
{
	
	@Autowired
	PartnerRepo pRepo;
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	UserService uService;
	
	public Partner createPartner(Partner payload) throws Exception {
		Partner newPartner = new Partner();
        newPartner = payload;
        System.out.println();
        if( checkIfPartnerExists(newPartner,"create") == false)
        {
        	pRepo.save(newPartner);
        	uService.createUser(newPartner.getUsername(),newPartner.getPassword(),"partner");
        	return newPartner;
        }
        else
        {
        	throw new Exception("Partner with same name & mobile or username already exists!");
        }
    }
	
	private boolean checkIfPartnerExists(Partner newPartner,String type) 
	{
		ArrayList<Partner> Partners = pRepo.findPartnerByNameNumber(newPartner.getName(), newPartner.getMobileno());
		ArrayList<Partner> PartnersWithUsername = pRepo.findPartnerByUsername(newPartner.getUsername());
		if(type=="update" && Partners.size()>0)
		{
			return true;
		}
		else if(type=="create" && (Partners.size()>0 || PartnersWithUsername.size()>0))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Partner updatePartner(Long id, Partner payload) throws Exception 
	{
		Optional<Partner> PartnerForUpdateOpt = pRepo.findById(id);
        Partner PartnerForUpdate = PartnerForUpdateOpt.get();
        
		Partner newPartner = new Partner();
        newPartner = payload;
        if( checkIfPartnerExists(newPartner,"update") == false || (
        			newPartner.getName().equalsIgnoreCase(PartnerForUpdate.getName()) && 
        			newPartner.getMobileno().equalsIgnoreCase(PartnerForUpdate.getMobileno())))
        {		
        	PartnerForUpdate.setEmail(payload.getEmail());
            PartnerForUpdate.setName(payload.getName());
            PartnerForUpdate.setMobileno(payload.getMobileno());
            PartnerForUpdate.setUsername(payload.getUsername());
            PartnerForUpdate.setPassword(payload.getPassword());
            uService.updateUser(payload.getUsername(), payload.getPassword());
        }
        else 
        {
        	throw new Exception("Partner with same Name and Number already exists");
        }
        	
        return pRepo.save(PartnerForUpdate);
        
    }

	public Partner findSinglePartner(Long id) 
	{
		Optional<Partner> Partners = pRepo.findById(id);
		return Partners.get();
	}
	
	public ArrayList<Partner> findPartnerByNamePhoneno(String name, String phoneno) 
	{
		ArrayList<Partner> Partners = pRepo.findPartnerByNameNumber(name, phoneno);
		return Partners;
	}
	
	public ArrayList<Partner> findPartnerByUsername(String username) 
	{
		ArrayList<Partner> Partners = pRepo.findPartnerByUsername(username);
		return Partners;
	}
	public int deletePartner(Long id) 
	{
		return 0;
	}

	public Page<Partner> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return pRepo.findAll(pageable);
	}
}
