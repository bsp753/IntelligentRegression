package com.insta.application.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum VehicleFilterAttributeEnum  implements Serializable{

	VEHICLENO,
	EXPIRYIN,
	REFERER,
	CUSTOMERID,
	INSURANCE_TYPE;
	
	
}




