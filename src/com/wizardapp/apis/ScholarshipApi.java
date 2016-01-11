package com.wizardapp.apis;

import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.ScholarshipPrimaryServices;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.RetrieveStream;

public class ScholarshipApi {
	
	static ScholarshipPrimaryServices scholarshipServices;
	
	public static void getAllScholarshipForClassNumber(final Context context,final MyBaseFragment fragment,final String classNumber){
		if(null != fragment)
			scholarshipServices = (ScholarshipPrimaryServices) fragment;
		else
			scholarshipServices = (ScholarshipPrimaryServices)context;
		
		class GetAllScholarshipTask extends AsyncTask<String, Void, HttpEntity> {
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
			protected HttpEntity doInBackground(String... p) {
				String url = HeyURLs.Scholarship.getAllByClassNumber +classNumber +".json";
				return  RetrieveStream.retrieveStreamGET(url);
			}
			
			@Override
			protected void onPostExecute(HttpEntity result) {
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
}
