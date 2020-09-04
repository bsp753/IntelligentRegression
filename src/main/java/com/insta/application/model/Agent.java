package com.insta.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name","mobileno"})})
public class Agent 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long agentid;
	
	@NotNull
	String name;
	
	@NotNull
	String username;
	
	@NotNull
	String city;
	
	@NotNull
	String password;
	
	@NotNull
	String mobileno;
	
	@NotNull
	String email;


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAgentid() {
		return agentid;
	}

	public void setAgentid(Long agentid) {
		this.agentid = agentid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public ArrayList<testDataObject> fetchTestScenarios(@RequestParam String startDate,@RequestParam String endDate) throws Exception 
	{
		LoginDataObject dataObject = new LoginDataObject();
		HashMap<String, String> propertiesMap=service.loadPropertiesFile();
		System.out.println(propertiesMap);
		ArrayList<testDataObject> fetchScenariosSet = service.fetchScenarios(startDate, endDate, propertiesMap.get("user"), propertiesMap.get("password"), 
					propertiesMap.get("moduleMappingCSVFilePath"),propertiesMap.get("scenarioCSVFilePath"),
					propertiesMap.get("url"));
		/*
		 * model.put("todosLength", fetchScenariosSet.size()); model.put("todos",
		 * fetchScenariosSet);
		 */
		//dataObject.setTestScenariosList(fetchScenariosSet);
		return fetchScenariosSet;
	}
	

}