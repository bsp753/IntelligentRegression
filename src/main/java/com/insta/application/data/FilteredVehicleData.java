package com.insta.application.data;

import org.springframework.data.domain.Page;

import com.insta.application.model.Vehicle;

public class FilteredVehicleData {

	private Page<Vehicle> vehicleList;
	
	private String filterAttEnum;

	public Page<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(Page<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public String getFilterAttEnum() {
		return filterAttEnum;
	}

	public void setFilterAttEnum(String filterAttEnum) {
		this.filterAttEnum = filterAttEnum;
	}
	
	
}
