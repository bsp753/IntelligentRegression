package com.insta.application.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentFilterAttributeEnum implements Serializable{ 
	
	POLICYNO,
	STARTDATE,
	ENDDATE,
	INSURERCOMPANY,
	REFERER,
	STATUS,
	INSURANCE_TYPE;
	
} 
