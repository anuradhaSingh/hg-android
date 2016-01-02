package com.wizardapp.utils;

public interface HeyURLs {
	
	String prodDomain = "",testDomain="http://128.199.245.125:8080";
	String domain = testDomain ;
	
	
	interface Users{
		String userLoginPost = domain +"/user/login.json";
		String registerUser = domain + "/user/register.json";
		
	}
	
	
}
