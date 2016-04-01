package com.wizardapp.services;


public interface UserServices {

	void userLoggingIn(String userResponse);
	
	void registerUser(String afterRegisteration);
	void updateUser(String updateUser);
	
	void verifyOTP(String response);
	
	void isUserExist(String response);
	
	void changePassword(String result);
	
}
