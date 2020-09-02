package com.insta.application.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class InstaUtils {
	
	
	public Date parseDateFromString(String dateString){
		Date date;
		try {
			date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSSZ").parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String fetchValueFromPropertyFile(String attribute_key) {

		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(rootPath);
		String appConfigPath = rootPath + "application.properties";
		Properties appProps = new Properties();
		try {
			appProps.load(new FileInputStream(appConfigPath));
			String appVersion = appProps.getProperty(attribute_key);
			return appVersion;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";    	
	}

}
