package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import com.wizardapp.main.MyBaseFragment;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.HttpConnection;


public class UserApi {
	
	static UserServices userServices;
	
	
	public static void getLoggenInUser(final Context context,final MyBaseFragment fragment,final JSONObject jObj){
		if(null != fragment)
			userServices = (UserServices) fragment;
		else
			userServices = (UserServices)context;
		
		class AppLoginTask extends AsyncTask<String, Void, String> {
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
				return  HttpConnection.getResponse(HeyURLs.Users.userLoginPost,jObj);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result && result.length() != 0)
					userServices.userLoggingIn(result);
				else{
					userServices.userLoggingIn(null);
				}
			}

		}
		new AppLoginTask().execute();
	}
	
	
	public static void registerUser(final Context context,final MyBaseFragment fragment,final JSONObject jObj){
		if(null != fragment)
			userServices = (UserServices) fragment;
		else
			userServices = (UserServices)context;
		
		class RegisterUserTask extends AsyncTask<String, Void, String> {
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
				return  HttpConnection.getResponse(HeyURLs.Users.registerUser,jObj);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result && result.length() != 0)
					userServices.registerUser(result);
				else{
					userServices.registerUser(null);
				}
			}
		}
		new RegisterUserTask().execute();
	}
	

	
}
