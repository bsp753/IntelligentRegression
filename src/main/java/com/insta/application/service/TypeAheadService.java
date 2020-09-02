package com.insta.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.application.data.UsernameTypeAheadData;
import com.insta.application.model.Agent;
import com.insta.application.model.Partner;
import com.insta.application.repository.AgentRepo;
import com.insta.application.repository.PartnerRepo;

@Service
public class TypeAheadService 
{

	@Autowired
	PartnerRepo pRepo;
	
	@Autowired
	AgentRepo aRepo;
	
	public ArrayList<UsernameTypeAheadData> getUsernameTypeAheads()
	{
		ArrayList<UsernameTypeAheadData> finaldata=new ArrayList<UsernameTypeAheadData>();
		UsernameTypeAheadData data=new UsernameTypeAheadData();
		UsernameTypeAheadData data1=new UsernameTypeAheadData();
		HashMap<String, String> agentData = new HashMap<String, String>();
		HashMap<String, String> partnerData = new HashMap<String, String>();
 		List<Agent> agents = aRepo.findAll();
 		List<Partner> partners = pRepo.findAll();
			for(Agent agent :agents)
			{
				agentData.put(agent.getName(), agent.getUsername());
			}
			data.setType("agents");
			data.setValues(agentData);
			finaldata.add(data);
			for(Partner partner :partners)
			{
				partnerData.put(partner.getName(), partner.getUsername());
			}
			data1.setType("partners");
			data1.setValues(partnerData);
			finaldata.add(data1);
		return finaldata;
	}
}
