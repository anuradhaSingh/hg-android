package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.Toast;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.MailServices;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.HttpConnection;


public class UserApi {
	
	static UserServices userServices;
	static MailServices mailServices;
	
	
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
					Toast.makeText(context, "Api error", Toast.LENGTH_SHORT).show();
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
					Toast.makeText(context, "Api error", Toast.LENGTH_SHORT).show();
					userServices.registerUser(null);
				}
			}
		}
		new RegisterUserTask().execute();
	}
	
	public static void verifyuser(final Context context,final MyBaseFragment fragment,final String mobileNum,final String otp){
		if(null != fragment)
			userServices = (UserServices) fragment;
		else
			userServices = (UserServices)context;
		
		class VerifyUserTask extends AsyncTask<String, Void, String> {
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
				return  HttpConnection.getResponse(HeyURLs.Users.verifyOTP+mobileNum+"/"+otp+".json",Constants.httpGet);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result && result.length() != 0)
					userServices.verifyOTP(result);
				else{
					Toast.makeText(context, "Api error", Toast.LENGTH_SHORT).show();
					userServices.verifyOTP(null);
				}
			}
		}
		new VerifyUserTask().execute();
	}
	
	public static void contactUs(final Context context,final MyBaseFragment fragment,final JSONObject jObj){
		if(null != fragment)
			mailServices = (MailServices) fragment;
		else
			mailServices = (MailServices)context;
		
		class ContactUsTask extends AsyncTask<String, Void, String> {
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
				return  HttpConnection.getResponse(HeyURLs.Users.contactUs,jObj);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result && result.length() != 0)
					mailServices.contactUs(result);
				else{
					mailServices.contactUs(null);
				}
			}
		}
		new ContactUsTask().execute();
	}

	public static void isUserExist(final Context context,final MyBaseFragment fragment,final String email){
		if(null != fragment)
			userServices = (UserServices) fragment;
		else
			userServices = (UserServices)context;
		
		class VerifyUserTask extends AsyncTask<String, Void, String> {
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
				return  HttpConnection.getResponse(HeyURLs.Users.isUserExist+email+".json",Constants.httpGet);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result && result.length() != 0)
					userServices.isUserExist(result);
				else{
					Toast.makeText(context, "Api error", Toast.LENGTH_SHORT).show();
					userServices.isUserExist(null);
				}
			}
		}
		new VerifyUserTask().execute();
	}
	
	public static void changePassword(final Context context,final MyBaseFragment fragment,final String email,final String password){
		if(null != fragment)
			userServices = (UserServices) fragment;
		else
			userServices = (UserServices)context;
		
		class ChangePasswordTask extends AsyncTask<String, Void, String> {
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
				return  HttpConnection.getResponse(HeyURLs.Users.changePassword+password+".json?email="+email,Constants.httpGet);
			}
			
			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if(null != result && result.length() != 0)
					userServices.changePassword(result);
				else{
					Toast.makeText(context, "Api error", Toast.LENGTH_SHORT).show();
					userServices.changePassword(null);
				}
			}
		}
		new ChangePasswordTask().execute();
	}
	
}
