package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.QuestionService;
import com.wizardapp.services.ScholarshipPrimaryServices;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.RetrieveStream;

public class TestApi {
	static TestService testServices;
	public static void getAvailableList(final Context context,final MyBaseFragment fragment,final long userId){
		if(null != fragment)
			testServices = (TestService) fragment;
		else
			testServices = (TestService)context;
		
		class GetAvailableTask extends AsyncTask<String, Void, String> {
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
				String url = HeyURLs.Scholarship.availableTest +userId +".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					testServices.getAvailableList(result);
				else{
					testServices.getAvailableList(null);
				}
			}

		}
		new GetAvailableTask().execute();
	}
	
	
	public static void getTakenList(final Context context,final MyBaseFragment fragment,final long userId){
		if(null != fragment)
			testServices = (TestService) fragment;
		else
			testServices = (TestService)context;
		
		class GetTakenListTask extends AsyncTask<String, Void, String> {
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
				String url = HeyURLs.Scholarship.takenTest+userId+".json";
				return  RetrieveStream.retrieveStreamPOST(url, new JSONObject());
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					testServices.getTakenList(result);
				else{
					testServices.getTakenList(null);
				}
			}

		}
		new GetTakenListTask().execute();
	}
	
	public static void updateScholarshipStatus(final Context context,final MyBaseFragment fragment,final long schoId,final long userId,final boolean status){
		if(null != fragment)
			testServices = (TestService) fragment;
		else
			testServices = (TestService)context;
		
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
				String url = HeyURLs.Scholarship.updateStatus +schoId +"/"+userId+"/"+status+".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					testServices.updateTestStatus(result);
				else{
					testServices.updateTestStatus(null);
				}
			}

		}
		new sendUpdateTask().execute();
	}
}
