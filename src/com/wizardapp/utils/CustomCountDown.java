package com.wizardapp.utils;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.wizardapp.R;
import com.wizardapp.utils.HeyURLs.Questions;


public class CustomCountDown extends CountDownTimerPausable{

	public CustomCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
	}/*
	
	Activity activityContext;
	int totalQuestions,pointScored;
	TextView timerText;
	List<Questions> skippedOnes;
	QuizLevels quizLevel;
	String userId,userName;
	Fragment oldFragment;
	int updateCounter=20;
	
	public CustomCountDown(Fragment oldFragment,Activity activityContext,long millisInFuture, long countDownInterval,TextView timerText,int totalQuestions,List<Questions> skippedOnes,QuizLevels quizLevel,String userId,String userName,int pointScored) {
		super(millisInFuture, countDownInterval);
		this.timerText =timerText;
		this.totalQuestions =totalQuestions;
		this.skippedOnes = skippedOnes;
		this.quizLevel = quizLevel;
		this.activityContext = activityContext;
		this.userId =userId;
		this.userName =userName;
		this.pointScored =pointScored;
		this.oldFragment = oldFragment;
	}

	@Override
	public void onTick(long millisUntilFinished) {

	 long tempValue = millisUntilFinished / 1000L;
	 Constants.milisRemained = millisUntilFinished;
	 long minutes = tempValue / 60L;
     long seconds     = tempValue % 60L;
   	 timerText.setText(minutes+":"+seconds);
   	 if(Constants.isNextQuestion == totalQuestions ){
   		if(null != skippedOnes && skippedOnes.size() != 0){
   			openSkippedScreen(quizLevel.getCode(),true);
   		}
   		else{
   			Intent ide = new Intent(activityContext, ScoreBoaredActivity.class);
   			ide.putExtra("latestScore", SharedPreferencesHelper.getInstance().getCurrentscore(activityContext));
			ide.putExtra("userName", userName);
   			oldFragment.startActivity(ide);
   			
   		}
   		    updateScore();
   	 }
   	 if(Constants.oneTimeUpdateScore && Constants.isNextQuestion != 0 && Constants.isNextQuestion % 20 == 0){
   		updateScore();
   		Constants.oneTimeUpdateScore=false;
   	 }
		
	}

	@Override
	public void onFinish() {
		Intent ide = new Intent(activityContext, ScoreBoaredActivity.class);
			ide.putExtra("latestScore", SharedPreferencesHelper.getInstance().getCurrentscore(activityContext));
			ide.putExtra("userName", userName);
			ide.putExtra("shouldOpenNextLevel", true);
			oldFragment.startActivity(ide);
			oldFragment.onDestroy();
		
		updateScore();
	}
	
	public boolean openSkippedScreen(String level,boolean isTimeLeft){
		try{
			int levelNo=0;
			if("A".equalsIgnoreCase(level))
				levelNo = 1;
			else if("B".equalsIgnoreCase(level))
				levelNo = 2;
			else if("C".equalsIgnoreCase(level))
				levelNo = 3;
			
			createNewFragment(oldFragment,new SkippedQuestionsFragment(activityContext,R.layout.skipped_questions,skippedOnes,levelNo,userName,quizLevel,isTimeLeft,pointScored),activityContext);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void updateScore(){
		class updateScoreTask extends AsyncTask<String, Void, String>{

			private ProgressDialog Dialog;
			@Override
			protected void onPreExecute() {
	         Dialog = new ProgressDialog(activityContext);
	    	     Dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		         Dialog.setMessage("Loading...");
		         Dialog.setCancelable(false);
		         Dialog.show();
			}

			@Override
			protected String doInBackground(String... p) {
				String attempTemp = SharedPreferencesHelper.getInstance().getAttemptedquestions(activityContext);
				attempTemp = attempTemp.replaceAll("#", "");
				if(attempTemp.charAt(attempTemp.lastIndexOf(",")) == ','){
					attempTemp = attempTemp.substring(0,attempTemp.lastIndexOf(','));
				}
				String currentscore=SharedPreferencesHelper.getInstance().getCurrentscore(activityContext);
				return SendGET.updateUserScore(userId, userName, currentscore, quizLevel.getName(),attempTemp);
				
			}
			
			@Override
			protected void onPostExecute(String result) {
				//Dialog.dismiss();
				// condition to check if the result is OK or not 
				// currently we are not sure what is the response of this API.
				//Toast.makeText(activityContext, Constants.AppConstants.userScoreUpdated, 1000).show();
			}
		
		}
		
		new updateScoreTask().execute();
	}

*/

	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		
	}}
