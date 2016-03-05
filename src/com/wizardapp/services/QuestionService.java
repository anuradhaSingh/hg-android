package com.wizardapp.services;

public interface QuestionService {
	
      void getQuestionsList(String response);
	
	void submitAnswer(String response);
	void getResult(String response);
}
