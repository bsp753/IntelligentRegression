package com.insta.application.controller;

import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.PaymentData;
import com.insta.application.data.PaymentEmailData;
import com.insta.application.data.PaymentInfoPayload;
import com.insta.application.data.PaymentLimitedFieldsData;
import com.insta.application.model.DBFile;
import com.insta.application.model.PaymentInfo;
import com.insta.application.service.DBFileStorageService;
import com.insta.application.service.PaymentInfoService;
import com.insta.application.service.PaymentPaginatedService;

@RestController
@RequestMapping("insta/payments")
public class PaymentController 
{
	@Autowired
	PaymentInfoService pServiceobj;
	
	@Autowired
	PaymentPaginatedService ppServiceObj;
	
	@Autowired
	DBFileStorageService dbFileStorage;

	@GetMapping
	public Page<PaymentInfo> returnAllPayments(Pageable pageable) 
	{
		
		return pServiceobj.findAll(pageable);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public PaymentData returnPayments(@RequestBody FilterDataList paymentFilterDataList,@RequestParam(name="page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size) throws JsonProcessingException, ParseException 
	{
		page= page==null?0:page; size = size==null?Integer.MAX_VALUE:size; 
		return pServiceobj.fetchPayments(paymentFilterDataList,page,size);
	}
	
	@GetMapping("/policydownload/{regno}")
	public ArrayList<PaymentLimitedFieldsData> returnSinglePayment(@PathVariable String regno) 
	{
		return pServiceobj.findPaymentsForRegNo(regno);
	}
	
	@GetMapping("/{id}")
	public PaymentInfo returnSinglePayment(@PathVariable Long id) 
	{
		return pServiceobj.findSinglePaymentInfo(id);
	}
	
	@PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentInfo createVehicle(@RequestBody PaymentInfoPayload payload) throws ValidationException{
		validatePaymentInfoLoad(payload);
       return pServiceobj.createPayment(payload);
    }
	
	@PutMapping("/{id}")
	public PaymentInfo updatepaymentInfo(@PathVariable Long id, @RequestBody PaymentInfo Payment) 
	{
	    return pServiceobj.updatePaymentInfo(id, Payment);
	}

	@DeleteMapping(value = "/{id}")
	public int delete(@PathVariable Long id)
	{
		return pServiceobj.deletePaymentInfo(id);
	}
	
	

	@PostMapping(value = "/{id}/upload")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void uploadDoc(@PathVariable Long id,@RequestParam("file") MultipartFile file)
	{
		pServiceobj.saveInsuranceDoc(id,file);
	}
	
	@GetMapping("/vehicle/{vehicleno}")
	public ArrayList<PaymentInfo> fetchPaymentsForRegNo(@PathVariable String vehicleno)
	{
		return pServiceobj.findByVehicleNo(vehicleno);
	}
	
	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
		// Load file from database
		DBFile dbFile = dbFileStorage.getFile(fileId);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	
	@PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmail(@RequestBody PaymentEmailData payload) throws Exception{
		 pServiceobj.sendEmailNotification(payload);
    }
	
	
	private void validatePaymentInfoLoad(PaymentInfoPayload payload) throws ValidationException {
		
		if(null  == payload.getPaymentDate()) {
			throw new ValidationException("Payment date cannot be null");
		}
		
	}

}
