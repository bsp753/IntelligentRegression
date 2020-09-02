package com.insta.application.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.application.model.RefererPayments;
import com.insta.application.repository.RefererPaymentRepo;

@Service
public class RefererService 
{
	@Autowired
	RefererPaymentRepo rpRepo;
	
	public ArrayList<RefererPayments> findAll()
	{
		return (ArrayList<RefererPayments>) rpRepo.findAll();
    }
	
	public RefererPayments createRefPayment(RefererPayments payload)
	{
		rpRepo.save(payload);
		return payload;
	}
	
	public RefererPayments updateRefPayment(Long id, RefererPayments payload) throws Exception 
	{
		Optional<RefererPayments> refPayForUpdateOpt = rpRepo.findById(id);
		RefererPayments refPayForUpdate = refPayForUpdateOpt.get();
		refPayForUpdate.setAmount(payload.getAmount());
		refPayForUpdate.setPayment_date(payload.getPayment_date());
		refPayForUpdate.setReferrerName(payload.getReferrerName());
        return rpRepo.save(refPayForUpdate);
        
    }
	
	public int deleteRefPayment(Long id) 
	{
		try
		{
			rpRepo.deleteById(id);
			return 1;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public ArrayList<RefererPayments> findRefPayByName(String name)
	{
		 ArrayList<RefererPayments> refPayList = rpRepo.findRefPayByName(name);
		 return refPayList;
	}

}
