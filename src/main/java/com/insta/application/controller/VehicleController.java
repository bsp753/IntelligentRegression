
package com.insta.application.controller;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.FilteredVehicleData;
import com.insta.application.data.VehiclePayload;
import com.insta.application.model.Vehicle;
import com.insta.application.service.VehicleService;

@RequestMapping("/insta/vehicle")
@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	
	
	@GetMapping
	public Page<Vehicle> fetchallVechicles(Pageable pageable){
		return vehicleService.getAllVehicles(pageable);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public FilteredVehicleData filterVehicleData(@RequestBody FilterDataList filterDataList,@RequestParam(name="page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size) throws JsonProcessingException, ParseException 
	{
		page= page==null?0:page; size = (size==null) ? Integer.MAX_VALUE : size; 
		return vehicleService.filterVehicles(filterDataList,page,size);
	}
	
	@PostMapping("/create")
	public Vehicle addVehicle(@RequestBody VehiclePayload vehicleData) throws Exception {
		return vehicleService.addVehicle(vehicleData);
	}
	
	@RequestMapping("/{vno}")
	public ArrayList<Vehicle> returnFetchVehicleListFromRegNo(@PathVariable String vno) 
	{
		return vehicleService.fetchVehicleListByRegNo(vno);
	}
	
	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable Long id) throws Exception
	{
		return vehicleService.deleteVehicleInfo(id);
	}
	
	@PutMapping("/{id}")
	public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) throws Exception 
	{
	    return vehicleService.updateVehicle(id, vehicle);
	}
}
