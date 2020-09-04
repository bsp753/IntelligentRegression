package com.insta.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"name","phone_no"})})
public class Customer 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Long customerid;
	
	@NotNull
	String name;
	
	
	@NotNull
	String phone_no;
	
	
	String type;
	
	
	String code;
	
	@NotNull
	String referer;
	
	@NotNull
	String email;
	
	/*
	 * @NotNull String client_company;
	 */
	
	@NotNull
	String acquisition_status;
	

	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

		public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}

	/*
	 * public String getClient_company() { return client_company; } public void
	 * setClient_company(String client_company) { this.client_company =
	 * client_company; }
	 */
	public String getAcquisition_status() {
		return acquisition_status;
	}
	public void setAcquisition_status(String acquisition_status) {
		this.acquisition_status = acquisition_status;
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
