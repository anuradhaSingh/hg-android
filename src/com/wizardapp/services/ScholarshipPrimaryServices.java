package com.wizardapp.services;

import java.io.InputStream;

public interface ScholarshipPrimaryServices {
	
	void getAllByClassNumber(InputStream response);
	
	void getDetailById(InputStream response);
}
