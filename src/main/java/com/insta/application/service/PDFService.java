package com.insta.application.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.insta.application.data.PDFInputData;
import com.insta.application.model.PaymentInfo;
import com.insta.application.repository.PaymentRepo;

@Service
public class PDFService 
{
	
	@Autowired
	PaymentRepo pRepo;
	
	public String parsePDFandLoad(MultipartFile file)
	{
		ArrayList<PDFInputData> pdfInputData = readPDF(file);
		String output = pushValuesToDB(pdfInputData);
		return output;
	}
	public String pushValuesToDB(ArrayList<PDFInputData> piDataList)
	{
		String success="Success list - ";
		String failure="Failure list - ";
		for(PDFInputData piData:piDataList)
		{
			AdsfsdfrrayList<PaymentInfo> payments = (ArrayList<PaymentInfo>) pRepo.findPaymentInfoByPolicyno(piData.getPolicyno());
			System.out.println(piData.getPolicyno());
			if(payments.size()>0)
			{
				for(PaymentInfo pInfo : payments)
				{
					pInfo.setCommission(piData.getAmount());
					pRepo.save(pInfo);
					success = success + "\n"+piData.getPolicyno()+", ";
				}
			}
			else
			{
				failure = failure+"\n"+piData.getPolicyno()+", ";
			}
		}
		return success+"\n"+failure;
	}
	public ArrayList<PDFInputData> readPDF(MultipartFile file)
	{
		ArrayList<PDFInputData> piDataList=new ArrayList<PDFInputData>();
		System.out.println("readpdf");
		if (!file.isEmpty()) 
		{
			System.out.println("file is not empty");
	        try 
	        {
	        	System.out.println("inside try");
	            byte[] bytes = file.getBytes();
	            String completeData = new String(bytes);
	            String[] rows = completeData.split("\n");
	            for(int i=1;i<rows.length;i++)
	            {
	            	PDFInputData pdfData = new PDFInputData();
	            	String[] columns = rows[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	            	System.out.println(columns[2]);
	            	System.out.println(columns[7].replace("\"", ""));
	            	pdfData.setPolicyno(columns[2]);
	            	pdfData.setAmount(Double.valueOf(columns[7].replace("\"", "").replace(",", "")));
	            	
	            	piDataList.add(pdfData);
	            }
	           
	           
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        	return null;
	        }
		}
		 return piDataList;
	}

}
