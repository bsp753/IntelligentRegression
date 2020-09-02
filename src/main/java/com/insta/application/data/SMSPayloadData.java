package com.insta.application.data;

import java.util.ArrayList;

public class SMSPayloadData 
{
	String messageType;
	String idType;
	ArrayList<Long> idList;
	
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public ArrayList<Long> getIdList() {
		return idList;
	}
	public void setIdList(ArrayList<Long> idList) {
		this.idList = idList;
	}
	

}
