package com.insta.application.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.insta.application.controller.LoginController;
import com.insta.application.data.FilterAttributeData;
import com.insta.application.data.FilterDataList;
import com.insta.application.data.MyDataSource;
import com.insta.application.data.PaymentData;
import com.insta.application.data.PaymentEmailData;
import com.insta.application.data.PaymentFilterAttributeEnum;
import com.insta.application.data.PaymentInfoPayload;
import com.insta.application.data.PaymentLimitedFieldsData;
import com.insta.application.model.DBFile;
import com.insta.application.model.PaymentInfo;
import com.insta.application.model.Vehicle;
import com.insta.application.repository.PaymentRepo;
import com.insta.application.repository.VehicleRepository;

@Service
public class PaymentInfoService 
{
	@Autowired
	PaymentRepo pRepo;

	@Autowired
	VehicleRepository vRepo;

	@Autowired
	CustomerService custService;

	@Autowired
	VehicleService vehicleService;

	@Autowired
	InstaUtils instaUtils;

	@Autowired
	DBFileStorageService dbFileStorageService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	JwtUserDetailsService JWTServiceObj;

	public String fetchUserRole()
	{
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	}
	public Page<PaymentInfo> findAll(Pageable pageable)
	{
		
			return pRepo.findAll(pageable);
	}

	public PaymentData fetchPayments(FilterDataList paymentsFilterData,Integer page, Integer size) throws JsonProcessingException, ParseException
	{
		Pageable pageObj = PageRequest.of(page, size);
		Page<PaymentInfo> paginatedPaymentsList = pRepo.findPaginatedPayments(pageObj);
		//List<PaymentInfo> paymentDataList = paginatedPaymentsList.getContent();

		PaymentData paymentData = new PaymentData();
		List<FilterAttributeData> paymentFilterList = paymentsFilterData.getFilterData();
		for(FilterAttributeData filterData : paymentFilterList) 
		{
			paginatedPaymentsList = filterPayments(paginatedPaymentsList,filterData);		
		}
		populateTotals(paginatedPaymentsList,paymentData);
		paymentData.setPaymentsList(paginatedPaymentsList);
		return paymentData;
	}

	private Page<PaymentInfo> filterPayments(Page<PaymentInfo> paymentsList, FilterAttributeData filterData) throws ParseException {

		if(PaymentFilterAttributeEnum.POLICYNO.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) {
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getPolicyno().equalsIgnoreCase(filterData.getAttrValue())).collect(Collectors.toList()));
		}else if(PaymentFilterAttributeEnum.STARTDATE.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(filterData.getAttrValue());
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getPaymentDate().compareTo(date) >=0).collect(Collectors.toList()));
		}
		else if(PaymentFilterAttributeEnum.REFERER.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
		{
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getVehicle().getCustomer().getReferer().toLowerCase().contains(filterData.getAttrValue().toLowerCase())).collect(Collectors.toList()));
		}
		else if(PaymentFilterAttributeEnum.ENDDATE.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(filterData.getAttrValue());
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getPaymentDate().compareTo(date) <= 0).collect(Collectors.toList()));
		}else if(PaymentFilterAttributeEnum.STATUS.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) {
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getPayment_status().equalsIgnoreCase(filterData.getAttrValue().toLowerCase())).collect(Collectors.toList()));
		}
		else if(PaymentFilterAttributeEnum.INSURERCOMPANY.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
		{
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getInsurer_company().toLowerCase().contains(filterData.getAttrValue().toLowerCase())).collect(Collectors.toList()));

		}
		else if(PaymentFilterAttributeEnum.INSURANCE_TYPE.toString().toLowerCase().equalsIgnoreCase(filterData.getAttrName())) 
		{
			paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getVehicle().getInsurance_type().toLowerCase().equalsIgnoreCase(filterData.getAttrValue().toLowerCase())).collect(Collectors.toList()));

		}
		return paymentsList;
	}

	public ArrayList<PaymentInfo> findByVehicleNo(String vehicleNo) {

		Vehicle vehicleModel = vehicleService.fetchVehiclefromPayload(vehicleNo);
		if(null != vehicleModel) {
			ArrayList<PaymentInfo> findAll = pRepo.findAllRecords();
			List<PaymentInfo> filteredPayments = findAll.stream().filter(payment -> payment.getVehicle().getRegistration_number().toLowerCase().contains(vehicleNo.toLowerCase())).collect(Collectors.toList());
			if(filteredPayments.size() > 0) {
				return (ArrayList<PaymentInfo>) filteredPayments;
			}
		}
		return null;
	}

	public PaymentInfo createPayment(PaymentInfoPayload payload) 
	{
		PaymentInfo newPaymentInfo = new PaymentInfo();
		//remove strings from registration no
		payload.setVehicleNo(payload.getVehicleNo().replaceAll("\\s+","").toUpperCase());
		populatePaymentInfo(payload, newPaymentInfo);
		//newPaymentInfo.setActive(true);
		pRepo.save(newPaymentInfo);

		//After payment is saved, update expiry date in vehicle table
		Optional<Vehicle> vehicleOpt = vRepo.findById(newPaymentInfo.getVehicle().getVehicle_id());
		Vehicle vehicle = vehicleOpt.get();
		vehicle.setExpiry_date(payload.getExpiryDate());
		vRepo.save(vehicle);
		return newPaymentInfo;
	}

	private void populatePaymentInfo(PaymentInfoPayload payload, PaymentInfo newPaymentInfo) {

		newPaymentInfo.setAmountpaid(payload.getAmountPaid());
		newPaymentInfo.setCommission(payload.getCommission());
		/*
		 * Customer singleCustomer =
		 * custService.findSingleCustomer(payload.getCustomerId()); if(null !=
		 * singleCustomer) { newPaymentInfo.setCustomer(singleCustomer); }
		 */
		Vehicle vehiclefromPayload = vehicleService.fetchVehiclefromPayload(payload.getVehicleNo());
		if(null != vehiclefromPayload) 
		{
			newPaymentInfo.setVehicle(vehiclefromPayload);
		}
		newPaymentInfo.setDueamount(payload.getDueAmount());
		newPaymentInfo.setInsurer_company(payload.getInsurerCompany());
		if(null != payload.getPaymentDate()) {
			newPaymentInfo.setPaymentDate(payload.getPaymentDate());
		}
		if(payload.getDueAmount()<=0)
		{
			newPaymentInfo.setPayment_status("Complete");
		}
		else
		{
			newPaymentInfo.setPayment_status("Due");
		}
		newPaymentInfo.setPaymentmode(payload.getPaymentMode());
		newPaymentInfo.setPolicyno(payload.getPolicyNo());
		newPaymentInfo.setRebate(payload.getRebate());
		newPaymentInfo.setTotalamount(payload.getTotalamount());
		newPaymentInfo.setCommission(payload.getCommission());
	}

	public PaymentInfo updatePaymentInfo(Long id, PaymentInfo payload) 
	{
		Optional<PaymentInfo> PaymentInfoForUpdateOpt = pRepo.findById(id);
		PaymentInfo PaymentInfoForUpdate=PaymentInfoForUpdateOpt.get();
		
		PaymentInfoForUpdate.setPaymentmode(payload.getPaymentmode());
		
		
		PaymentInfoForUpdate.setPaymentDate(payload.getPaymentDate());
		PaymentInfoForUpdate.setInsurer_company(payload.getInsurer_company());
		
		PaymentInfoForUpdate.setPolicyno(payload.getPolicyno());
		System.out.println(fetchUserRole());
		//restrict non-admin roles to update payment related fields
		if(fetchUserRole().equalsIgnoreCase("[admin]")==true)
		{
			PaymentInfoForUpdate.setTotalamount(payload.getTotalamount());
			PaymentInfoForUpdate.setDueamount(payload.getDueamount());
			PaymentInfoForUpdate.setRebate(payload.getRebate()); 
			PaymentInfoForUpdate.setAmountpaid(payload.getAmountpaid());
			PaymentInfoForUpdate.setCommission(payload.getCommission());
			if(payload.getDueamount()<=0)
			{
				PaymentInfoForUpdate.setPayment_status("Complete");
			}
			else 
			{
				PaymentInfoForUpdate.setPayment_status("Due");
			}
		}
		if(payload.getFileUuid()!=null)
		{
			PaymentInfoForUpdate.setFileUuid(payload.getFileUuid());
		}
		return pRepo.save(PaymentInfoForUpdate);
	}

	public PaymentInfo findSinglePaymentInfo(Long id) 
	{
		Optional<PaymentInfo> PaymentInfos = pRepo.findById(id);
		return PaymentInfos.get();
	}

	/*
	 * public Transaction updateTransaction(Integer id, Transaction payload) {
	 * Optional<Transaction> TransactionForUpdateOpt = tRepo.findById(id);
	 * Transaction TransactionForUpdate = TransactionForUpdateOpt.get();
	 * TransactionForUpdate.setAmount(payload.getAmount());
	 * TransactionForUpdate.setCommission(payload.getCommission());
	 * TransactionForUpdate.setCustomer(payload.getCustomer());
	 * TransactionForUpdate.setDate(payload.getDate());
	 * TransactionForUpdate.setInsurer_company(payload.getInsurer_company());
	 * TransactionForUpdate.setPaymentInfo_status(payload.getPaymentInfo_status());
	 * TransactionForUpdate.setPolicyno(payload.getPolicyno());
	 * TransactionForUpdate.setRebate(payload.getRebate()); return
	 * tRepo.save(TransactionForUpdate); }
	 */

	public int deletePaymentInfo(Long id) 
	{
		try
		{
			pRepo.deleteById(id);
			return 1;
		}
		catch(Exception e)
		{
			return 0;
		}
	}

	/*
	 * public List<PaymentInfo> fetchPaymentInfo(String vehicleNo) {
	 * 
	 * Vehicle vehicleModel = vehicleService.fetchVehiclefromPayload(vehicleNo);
	 * if(null != vehicleModel) { List<PaymentInfo> findAll = pRepo.findAll();
	 * List<PaymentInfo> filteredPaymentInfo = findAll.stream().filter(paymentInfo
	 * ->
	 * paymentInfo.getVehicle().getRegistration_number().equalsIgnoreCase(vehicleNo)
	 * ).collect(Collectors.toList()); if(filteredPaymentInfo.size() > 0) { return
	 * filteredPaymentInfo; } } return null; }
	 */

	public void saveInsuranceDoc(Long id, MultipartFile file) {

		DBFile dbFile = dbFileStorageService.storeFile(file);
		PaymentInfo paymentInfo = findSinglePaymentInfo(id);
		String fileUuid = paymentInfo.getFileUuid();
		if(null != fileUuid) {
			paymentInfo.setFileUuid(dbFile.getId());
		}else {
			paymentInfo.setFileUuid(dbFile.getId());
		}
		pRepo.save(paymentInfo);
	}

	public void sendEmailNotification(PaymentEmailData payload) {

		try {
			System.out.println(" Sending email ");
			sendEmail(payload);
			System.out.println("Email sent");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Double round2(Double val) {
	    return new BigDecimal(val.toString()).setScale(2,RoundingMode.HALF_UP).doubleValue();
	}
	private void populateTotals(Page<PaymentInfo> paymentDataList, PaymentData paymentData) {
		
		double totalamountpaid =  Double.valueOf(String.format("%1.2f", paymentData.getTotalamountpaid())); 
		double totalcommission = Double.valueOf(String.format("%1.2f", paymentData.getTotalcommission())); 
		double totaldebate = Double.valueOf(String.format("%1.2f", paymentData.getTotaldebate())); 
		double totaldueamount = Double.valueOf(String.format("%1.2f", paymentData.getTotaldueamount())); 
		double totalinsurance_amount = Double.valueOf(String.format("%1.2f", paymentData.getTotalinsurance_amount()));

		for(PaymentInfo paymentJoin : paymentDataList) {

			totalamountpaid = totalamountpaid +Double.valueOf(String.format("%1.2f", paymentJoin.getTotalamount()));
			totalcommission = totalcommission + Double.valueOf(String.format("%1.2f", paymentJoin.getCommission())); 
			System.out.println(paymentJoin.getCommission()+" --- "+Double.valueOf(String.format("%1.2f", totalcommission)));
			totaldebate    = totaldebate + Double.valueOf(String.format("%1.2f", paymentJoin.getRebate())); 
			totaldueamount = totaldueamount + Double.valueOf(String.format("%1.2f", paymentJoin.getDueamount())); 
		}
		paymentData.setTotalamountpaid(Double.valueOf(String.format("%1.2f", totalamountpaid)));
		paymentData.setTotalcommission(Double.valueOf(String.format("%1.2f", totalcommission)));
		
		paymentData.setTotaldebate(Double.valueOf(String.format("%1.2f", totaldebate)));
		paymentData.setTotaldueamount(Double.valueOf(String.format("%1.2f", totaldueamount)));
		paymentData.setTotalinsurance_amount(Double.valueOf(String.format("%1.2f", totalinsurance_amount)));
	}


	public void sendEmail(PaymentEmailData payload) throws Exception{

		Optional<PaymentInfo> paymentInfo = pRepo.findById(payload.getPaymentId());
		String toEmail;
		String SMTP_HOST_NAME = "smtpout.secureserver.net"; //smtp URL
		int SMTP_HOST_PORT = 465; //port number
		String SMTP_AUTH_USER = "vamsi@sabkainsurance.in"; //email_id of sender
		String SMTP_AUTH_PWD = "Wipro!123"; //password of sender email_id

		try {
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.host", SMTP_HOST_NAME);
			props.put("mail.smtps.auth", "true");

			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();
			MimeMessage message = new MimeMessage(mailSession);
			 MimeMessageHelper helper = new MimeMessageHelper(message, true);
			if(paymentInfo.isPresent()) 
			{
				PaymentInfo paymentData = paymentInfo.get();

				if(paymentData.getVehicle().getCustomer().getEmail()!=null && paymentData.getFileUuid()!=null)
				{

					toEmail = paymentData.getVehicle().getCustomer().getEmail();
					String fileUuid = paymentData.getFileUuid();
					String[] fileArray = fileUuid.split(",");
					List<String> fileList = Arrays.asList(fileArray);
					final String contentType;
					for(String fileId : fileList) 
					{
						DBFile dbFile = dbFileStorageService.getFile(fileId);
						
						byte[] fileBytes=dbFile.getData();
						System.out.println(new File("/tmp/tempfiles/"+dbFile.getFileName()).getAbsolutePath());
						//DataSource source = new FileDataSource(new File("/tmp/tempfiles/"+dbFile.getFileName()));
						File file=new File("/tmp/tempfiles/"+dbFile.getFileName());
						//File file=new File("/Users/bsridharpatnaik/Downloads/"+dbFile.getFileName());
						// because of the above problem, we are going to copy over the data ourselves:
						/*byte[] sourceBytes = dbFile.getData();
						OutputStream sourceOS = source.getOutputStream();
						MyDataSource ds = new MyDataSource ();
						InputStream is =  new ByteArrayInputStream(sourceBytes);
				        ds.setInputStream(is);
				        ds.setContentType("application/octet-stream");
				        ds.setName("some content name");
				        helper.addAttachment(dbFile.getFileName(), ds);
				        helper.att
						sourceOS.write(sourceBytes);*/
						OutputStream os = new FileOutputStream(file); 
						os.write(fileBytes); 
						helper.addAttachment(dbFile.getFileName(), file);
						
					}
					helper.setSubject("Email from SabkaInsurance");
			        helper.setText("<html>\r\n" + 
			        		"<p>Dear Sir/Madam,</p>\r\n" + 
			        		"<p>&nbsp;</p>\r\n" + 
			        		"<p>Please find attached a copy of your insurance. If you have any further queries, reach out to me on 9989895986.</p>\r\n" + 
			        		"<p>&nbsp;</p>\r\n" + 
			        		"<p>Thanks,</p>\r\n" + 
			        		"<p>Vamsi</p>\r\n" + 
			        		"</html>", true);
			        helper.setTo(payload.getToEmail());
			        helper.setFrom("vamsi@sabkainsurance.in");
			        
					transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
					transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
					transport.close();

				}
				else
				{
					throw new Exception("Emailid not present for customer or file not present in DB");
				}
			}
			else
			{
				throw new Exception("Payment not found");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	public ArrayList<PaymentLimitedFieldsData> findPaymentsForRegNo(String regno) 
	{
		Pageable pageObj = PageRequest.of(0, Integer.MAX_VALUE);
		Page<PaymentInfo> paymentsList = pRepo.findPaginatedPayments(pageObj);
		ArrayList<PaymentLimitedFieldsData> paymentLimitedData = new ArrayList<PaymentLimitedFieldsData>();
		paymentsList = new PageImpl<>(paymentsList.stream().filter(payment -> payment.getVehicle().getRegistration_number().toLowerCase().contains(regno.toLowerCase())).collect(Collectors.toList()));
		for(PaymentInfo payment:paymentsList.getContent())
		{
			PaymentLimitedFieldsData data = new PaymentLimitedFieldsData();
			data.setPaymentId(payment.getPaymentid());
			data.setFileUUID(payment.getFileUuid());
			data.setEmail(payment.getVehicle().getCustomer().getEmail());
			data.setRegistrationNumber(payment.getVehicle().getRegistration_number());
			paymentLimitedData.add(data);
		}
		return paymentLimitedData;
	}
	
}
