package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.Toast;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.MailServices;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.HttpConnection;

public class MailApi {

	static MailServices mailServices;

	public static void referFriend(final Context context,
			final MyBaseFragment fragment, final JSONObject jObj) {
		if (null != fragment)
			mailServices = (MailServices) fragment;
		else
			mailServices = (MailServices) context;

		class ReferFriendTask extends AsyncTask<String, Void, String> {
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
				return HttpConnection.getResponse(HeyURLs.Mail.sendMailTo,jObj);
			}

			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if (null != result && result.length() != 0)
					mailServices.referToFriend(result);
				else {
					mailServices.referToFriend(null);
				}
			}

		}
		new ReferFriendTask().execute();
	}
	
	public static void forgotPassword(final Context context ,final MyBaseFragment fragment, final JSONObject jObj) {
		if (null != fragment)
			mailServices = (MailServices) fragment;
		else
			mailServices = (MailServices) context;

		class ForgotPasswordTask extends AsyncTask<String, Void, String> {
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
				return HttpConnection.getResponse(HeyURLs.Mail.forgotPassword,jObj);
			}

			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if (null != result && result.length() != 0)
					mailServices.forgotPassword(result);
				else {
					Toast.makeText(context, "Api error", Toast.LENGTH_SHORT).show();
					mailServices.forgotPassword(null);
				}
			}

		}
		new ForgotPasswordTask().execute();
	}

}
