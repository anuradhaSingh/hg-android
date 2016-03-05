package com.wizardapp.apis;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.QuestionService;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.RetrieveStream;

public class QuestionApi {
	static QuestionService questionServices;
	public static void getQuestionList(final Context context,final MyBaseFragment fragment,final long userId){
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
				String url = HeyURLs.Scholarship.availableTest +userId +".json";
				return  RetrieveStream.retrieveStreamGET(url);
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
	
	
}
