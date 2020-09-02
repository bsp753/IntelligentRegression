
  package com.insta.application.controller;
  
  import javax.xml.bind.ValidationException;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.ResponseStatus; import
  org.springframework.web.bind.annotation.RestController;
  
  import com.insta.application.data.QuotePayload; import
  com.insta.application.data.SMSPayloadData; import
  com.insta.application.model.Quote; import
  com.insta.application.service.SMSService;
  
  
  @RequestMapping("/insta/sms")
  
  @RestController public class SMSController {
  
  @Autowired SMSService SMSSerObj;
  
  @PostMapping("/bulksms")
  
  @ResponseStatus(HttpStatus.OK) public String sendBulkSms(@RequestBody
  SMSPayloadData payload) throws Exception {
  System.out.println(payload.getIdList().toString());
  System.out.println(payload.getMessageType()); return
  SMSSerObj.sendMessage(payload.getIdList(), payload.getMessageType(),
  payload.getIdType()); //System.out.println("Sent"); 
  }
  }
 