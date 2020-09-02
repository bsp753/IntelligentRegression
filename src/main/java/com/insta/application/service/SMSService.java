
package com.insta.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.insta.application.data.SMSData;
import com.insta.application.data.SMSIntegrationRequest;
import com.insta.application.data.SMSReplaceData;
import com.insta.application.model.PaymentInfo;
import com.insta.application.model.Vehicle;
import com.insta.application.repository.PaymentRepo;
import com.insta.application.repository.VehicleRepository;

@Service
public class SMSService {

	@Autowired
	VehicleRepository vRepo;

	@Autowired
	PaymentRepo pRepo;

	public String sendMessage(ArrayList<Long> idList, String smsType, String idType) throws Exception {
		ArrayList<SMSReplaceData> SMSDataList = new ArrayList<SMSReplaceData>();
		if (idType.toLowerCase().equals("vehicle")) {
			SMSDataList = fetchNumbersFromVehcileIds(idList);
		} else if (idType.toLowerCase().equals("payment")) {
			SMSDataList = fetchNumbersFromPaymentIds(idList);
		}
		String smsText = getSmsText(smsType);
		System.out.println("dsada" + smsText);

		String apiResponse = sendMessageToVehicleList(SMSDataList, smsText);
		return apiResponse;
	}

	private ArrayList<SMSReplaceData> fetchNumbersFromPaymentIds(ArrayList<Long> idList) {
		ArrayList<SMSReplaceData> smsReplaceDataList = new ArrayList<SMSReplaceData>();
		System.out.println("1");
		for (Long PaymentId : idList) {
			System.out.println("2");
			Optional<PaymentInfo> paymentOpt = pRepo.findById(PaymentId);
			PaymentInfo payment = paymentOpt.get();
			if (payment.getPayment_status().equalsIgnoreCase("due") == true
					&& payment.getVehicle().getCustomer().getPhone_no() != null && payment.getDueamount() > 0) {
				SMSReplaceData smsReplaceData = new SMSReplaceData();
				smsReplaceData.setExpiryDate(payment.getVehicle().getExpiry_date().toString());
				smsReplaceData.setPhoneNo(payment.getVehicle().getCustomer().getPhone_no());
				smsReplaceData.setRegistrationNo(payment.getVehicle().getRegistration_number());
				smsReplaceDataList.add(smsReplaceData);
			}

		}
		return smsReplaceDataList;
	}

	private ArrayList<SMSReplaceData> fetchNumbersFromVehcileIds(ArrayList<Long> idList) {
		ArrayList<SMSReplaceData> smsReplaceDataList = new ArrayList<SMSReplaceData>();
		for (Long vehicleId : idList) {
			Optional<Vehicle> vehicleOpt = vRepo.findById(vehicleId);
			if (vehicleOpt.isPresent()) {
				Vehicle vehicle = vehicleOpt.get();
				if (vehicle.getCustomer().getPhone_no() != null) {
					SMSReplaceData smsReplaceData = new SMSReplaceData();
					smsReplaceData.setExpiryDate(vehicle.getExpiry_date().toString());
					smsReplaceData.setPhoneNo(vehicle.getCustomer().getPhone_no());
					smsReplaceData.setRegistrationNo(vehicle.getRegistration_number());
					smsReplaceDataList.add(smsReplaceData);
				}
			}

		}
		return smsReplaceDataList;
	}

	private String sendMessageToVehicleList(ArrayList<SMSReplaceData> smsDataReplaceList, String smsText)
			throws UnsupportedOperationException, IOException {
		try {
			SMSIntegrationRequest smsIntReq = new SMSIntegrationRequest();
			smsIntReq.setCountry("91");
			smsIntReq.setRoute("4");
			smsIntReq.setSender("SBKINS");
			ArrayList<SMSData> smsList = new ArrayList<SMSData>();
			for (SMSReplaceData smsReplaceData : smsDataReplaceList) {
				SMSData sms = new SMSData();
				ArrayList<String> numbersList = new ArrayList<String>();
				numbersList.add(smsReplaceData.getPhoneNo());
				sms.setTo(numbersList);
				smsText = smsText.replace("<vehicleno>", smsReplaceData.getRegistrationNo());
				smsText = smsText.replace("<expirydate>", smsReplaceData.getExpiryDate());
				sms.setMessage(smsText);
				smsList.add(sms);
			}
			smsIntReq.setSms(smsList);
			String apiResponse = hitSMSAPI(smsIntReq);
			return apiResponse;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return e.getMessage();
		}
	}

	private String hitSMSAPI(SMSIntegrationRequest smsJsonData) throws
  UnsupportedOperationException, IOException { HttpClient httpclient =
  HttpClients.createDefault(); HttpPost httppost = new
  HttpPost("https://api.msg91.com/api/v2/sendsms?country=91"); // Request parameters and other properties.
  Gson gson = new Gson(); String apiResponse =
  ""; httppost.setHeader(new BasicHeader("Content-Type", "application/json"));
  httppost.setHeader(new BasicHeader("authkey", "291479Ax1Ma8NY3S5d65f14e"));
  System.out.println(gson.toJson(smsJsonData)); StringEntity postingString =
  new StringEntity(gson.toJson(smsJsonData));
  httppost.setEntity(postingString); HttpResponse response =
  httpclient.execute(httppost); HttpEntity entity = response.getEntity(); if
  (entity != null) { try (InputStream instream = entity.getContent()) {
  StringWriter writer = new StringWriter(); IOUtils.copy(instream, writer,
  "UTF-8"); apiResponse = writer.toString(); System.out.println(apiResponse); }
  } return apiResponse; }

	private String getSmsText(String smsType) {
		String smsText = "";
		if (smsType.toLowerCase().equals("expiry_reminder")) {
			smsText = "Greetings from SabkaInsurance. Insurance for your vehicle <vehicleno> is getting expired on <expirydate>.Please contact 9989895986 for renewal.";
		}
		if (smsType.toLowerCase().equals("payment_reminder")) {
			smsText = "Greetings from SabkaInsurance.Please be informed that your payment is due towards insurance of vehicle <vehicleno>. Please contact 9989895986 for details.";
		}
		return smsText;
	}

}
