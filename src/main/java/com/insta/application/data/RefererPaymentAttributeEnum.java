package com.insta.application.data;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;


@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RefererPaymentAttributeEnum implements Serializable{
	
	REFERERNAME,
	PAYMENTTYPE,
	STARTDATE,
	ENDDATE;
	
}