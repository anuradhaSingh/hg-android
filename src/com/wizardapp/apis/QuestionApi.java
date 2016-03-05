package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.QuestionService;
import com.wizardapp.services.QuestionServices;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.HttpConnection;
import com.wizardapp.utils.RetrieveStream;

public class QuestionApi {
	static QuestionService questionServices;
	public static void getQuestionList(final Context context,final MyBaseFragment fragment,final JSONObject jobj){
		if(null != fragment)
			questionServices = (QuestionService) fragment;
		else
			questionServices = (QuestionService)context;
		
		class GetQuestionsTask extends AsyncTask<String, Void, String> {
			ProgressDialog Dialog;
			@Override
			protected void onPreExecute() {
				
		         Dialog = new ProgressDialog(context);
		    	     Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			         Dialog.setMessage("Loading...");
			         Dialog.setCancelable(false);
			         Dialog.show();
			}

			@Override
			protected String doInBackground(String... p) {
				return   HttpConnection.getResponse(HeyURLs.Questions.startTest,jobj);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					questionServices.getQuestionsList(result);
				else{
					questionServices.getQuestionsList(null);
				}
			}

		}
		new GetQuestionsTask().execute();
	}
	public static void submitAnswerForQuestion(final Context context,final MyBaseFragment fragment, final JSONObject jObj) {
		if (null != fragment)
			questionServices = (QuestionService) fragment;
		else
			questionServices = (QuestionService) context;

		class SubmitQuesTask extends AsyncTask<String, Void, String> {
			ProgressDialog Dialog;

			@Override
			protected void onPreExecute() {

				Dialog = new ProgressDialog(context);
				Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				Dialog.setMessage("Loading...");
				Dialog.setCancelable(false);
				Dialog.show();
			}

			@Override
			protected String doInBackground(String... p) {
				return HttpConnection.getResponse(HeyURLs.Questions.submitAnswer,
						jObj);
			}

			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if (null != result && result.length() != 0)
					questionServices.submitAnswer(result);
				else {
					questionServices.submitAnswer(null);
				}
			}

		}
		new SubmitQuesTask().execute();
	}
	public static void getEndResult(final Context context,final MyBaseFragment fragment,final long schoId,final long userId){
		if(null != fragment)
			questionServices = (QuestionService) fragment;
		else
			questionServices = (QuestionService)context;
		
		class endTestTask extends AsyncTask<String, Void, String> {
			ProgressDialog Dialog;
			@Override
			protected void onPreExecute() {
				
		         Dialog = new ProgressDialog(context);
		    	     Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			         Dialog.setMessage("Loading...");
			         Dialog.setCancelable(false);
			         Dialog.show();
			}

			@Override
			protected String doInBackground(String... p) {
				String url = HeyURLs.Questions.endTest +"/"+schoId +"/"+userId+".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					questionServices.getResult(result);
				else{
					questionServices.getResult(null);
				}
			}

		}
		new endTestTask().execute();
	}
	public static void sendUpdateResult(final Context context,final MyBaseFragment fragment,final long schoId,final long userId){
		if(null != fragment)
			questionServices = (QuestionService) fragment;
		else
			questionServices = (QuestionService)context;
		
		class sendUpdateTask extends AsyncTask<String, Void, String> {
			ProgressDialog Dialog;
			@Override
			protected void onPreExecute() {
				
		         Dialog = new ProgressDialog(context);
		    	     Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			         Dialog.setMessage("Loading...");
			         Dialog.setCancelable(false);
			         Dialog.show();
			}

			@Override
			protected String doInBackground(String... p) {
				String url = HeyURLs.Questions.getResult +"/"+schoId +"/"+userId+".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					questionServices.sendUpadte(result);
				else{
					questionServices.sendUpadte(null);
				}
			}

		}
		new sendUpdateTask().execute();
	}
}
