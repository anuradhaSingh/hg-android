package com.wizardapp.services;


public interface UserServices {

	void userLoggingIn(String userResponse);
	
	void registerUser(String afterRegisteration);
	
	void verifyOTP(String response);
	
	void isUserExist(String response);
	
}
