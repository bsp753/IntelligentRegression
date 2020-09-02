package com.insta.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.insta.application.service.PDFService;

@RequestMapping("/insta/uploadpdf")
@RestController
public class PDFController 
{
	@Autowired
	PDFService pdfService;
	
	@PostMapping
	//@ResponseStatus(HttpStatus.ACCEPTED)
	public String uploadDoc(@RequestParam("file") MultipartFile file)
	{
		System.out.println("1");
		return pdfService.parsePDFandLoad(file);
	}
	
}