package com.wizardapp.services;

import org.apache.http.HttpEntity;

public interface ScholarshipPrimaryServices {
	
	void getAllByClassNumber(HttpEntity response);
	
	void getDetailById(HttpEntity response);
}
