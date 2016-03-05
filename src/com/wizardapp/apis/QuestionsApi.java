package com.wizardapp.apis;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;

import com.wizardapp.fragments.MyBaseFragment;
import com.wizardapp.services.QuestionServices;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.HeyURLs;
import com.wizardapp.utils.HttpConnection;

public class QuestionsApi {
	static QuestionServices questionServices;

	public static void getAnswerableQuestions(final Context context,final MyBaseFragment fragment, final JSONObject jObj) {
		if (null != fragment)
			questionServices = (QuestionServices) fragment;
		else
			questionServices = (QuestionServices) context;

		class StartTestQuestionsTask extends AsyncTask<String, Void, String> {
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
				return HttpConnection.getResponse(HeyURLs.Questions.startTest,
						jObj);
			}

			@Override
			protected void onPostExecute(String result) {
				Dialog.dismiss();
				if (null != result && result.length() != 0)
					questionServices.testQuestions(result);
				else {
					questionServices.testQuestions(null);
				}
			}

		}
		new StartTestQuestionsTask().execute();
	}
	
	public static void submitAnswerForQuestion(final Context context,final MyBaseFragment fragment, final JSONObject jObj) {
		if (null != fragment)
			questionServices = (QuestionServices) fragment;
		else
			questionServices = (QuestionServices) context;

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
	
}
