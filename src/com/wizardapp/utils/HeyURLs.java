package com.wizardapp.utils;

public interface HeyURLs {
	
	String prodDomain = "",testDomain="http://188.166.255.205:8080/hey_pub_dev";
	String domain = testDomain ;
	
	
	interface Users{
		String userLoginPost = domain +"/user/login.json";
		String registerUser = domain + "/user/register.json";
		String verifyOTP = domain + "/master/verify/"; //append mobile num and otp.
	}
	
	interface Scholarship{
		String getAllByClassNumber = domain + "/scholarship/all/"; // append classnumber.json
		String getScholarshipDetail = domain + "/scholarship/"; // append scholarship_id.json
		String buyScholarship = domain + "/scholarship" + "/buy/";// append scholarshipId and userId
		
		String takenTest = domain + "/scholarship/taken";   //append userId
		String availableTest = domain + "/scholarship/available";   //append userId
	}
	
	interface Refer{
		String sendMailTo = domain + "/util/mail/send.json";
		String referTo = domain + "/util/refer.json";
	}
	
	interface Questions{
		String startTest = domain + "/questions/test/start.json";
		String submitAnswer = domain + "/questions/answer.json";
		String endTest = domain + "/questions/test/end"; // append scholarshipId and userid
		String getResult = domain + "/questions/test/result"; // append scholarshipId and userid
		
	}
	
	
}
