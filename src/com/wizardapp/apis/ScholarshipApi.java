package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.BaseAdapter;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.PaymentServices;
import com.wizardapp.services.ScholarshipPrimaryServices;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.RetrieveStream;

public class ScholarshipApi {
	
	static ScholarshipPrimaryServices scholarshipServices;
	static PaymentServices paymentServices;
	
	public static void getAllScholarshipForClassNumber(final Context context,final MyBaseFragment fragment,final String classNumber){
		if(null != fragment)
			scholarshipServices = (ScholarshipPrimaryServices) fragment;
		else
			scholarshipServices = (ScholarshipPrimaryServices)context;
		
		class GetAllScholarshipTask extends AsyncTask<String, Void, String> {
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
				String url = HeyURLs.Scholarship.getAllByClassNumber +classNumber +".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					scholarshipServices.getAllByClassNumber(result);
				else{
					scholarshipServices.getAllByClassNumber(null);
				}
			}

		}
		new GetAllScholarshipTask().execute();
	}
	
	public static void getToBuyTestList(final Context context,final MyBaseFragment fragment,final Long classNumber,final Long userId){
		if(null != fragment)
			scholarshipServices = (ScholarshipPrimaryServices) fragment;
		else
			scholarshipServices = (ScholarshipPrimaryServices)context;
		
		class ToBuyTestTask extends AsyncTask<String, Void, String> {
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
				String url = HeyURLs.Scholarship.toBuyScholarshipList +userId +"/"+classNumber+".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					scholarshipServices.tobuyTestList(result);
				else{
					scholarshipServices.tobuyTestList(null);
				}
			}

		}
		new ToBuyTestTask().execute();
	}
	
	
	public static void buyScholarship(final Context context,final BaseAdapter adapter,final Long scholarshipId,final Long userId){
		if(null != adapter)
			scholarshipServices = (ScholarshipPrimaryServices) adapter;
		else
			scholarshipServices = (ScholarshipPrimaryServices)context;
		
		class BuyScholarshipTask extends AsyncTask<String, Void, String> {
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
				String url = HeyURLs.Scholarship.buyScholarship +scholarshipId+"/"+userId+".json";
				return  RetrieveStream.retrieveStreamPOST(url, new JSONObject());
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					scholarshipServices.buyScholarship(result);
				else{
					scholarshipServices.buyScholarship(null);
				}
			}

		}
		new BuyScholarshipTask().execute();
	}
	
	
	public static void payForScholarship(final Context context,final BaseAdapter adapter,final JSONObject jObj,final Long userScholarshipDetailId){
		if(null != adapter)
			paymentServices = (PaymentServices) adapter;
		else
			paymentServices = (PaymentServices)context;
		
		class PayForTestTask extends AsyncTask<String, Void, String> {
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
				String url = HeyURLs.Scholarship.payForTest;
				return  RetrieveStream.retrieveStreamPOST(url, jObj);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result)
					paymentServices.payForTest(result, userScholarshipDetailId);
				else{
					paymentServices.payForTest(null, userScholarshipDetailId);
				}
			}
		}
		new PayForTestTask().execute();
	}
	
}
