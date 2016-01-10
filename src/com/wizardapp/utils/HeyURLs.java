package com.wizardapp.utils;

public interface HeyURLs {
	
	String prodDomain = "",testDomain="http://188.166.255.205:8080/hey_pub_v2";
	String domain = testDomain ;
	
	
	interface Users{
		String userLoginPost = domain +"/user/login.json";
		String registerUser = domain + "/user/register.json";
		
	}
	
	
}
