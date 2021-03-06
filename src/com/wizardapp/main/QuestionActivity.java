package com.wizardapp.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.apis.QuestionApi;
import com.wizardapp.model.Question;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.model.UserScholarshipResult;
import com.wizardapp.services.QuestionService;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.CustomCountDown;
import com.wizardapp.utils.RandomNumbers;
import com.wizardapp.utils.SharedPreferencesHelper;

public class QuestionActivity extends MyBaseActivity implements QuestionService{
	SimpleSideDrawer slide_me;
	private RadioButton checkbox1, checkbox2, checkbox3, checkbox4;
	private boolean flag, isOptionSelected,state_of_drawer;
	TextView questiontext;
	Question question;
	UserDetail userdata=SharedPreferencesHelper.getLoggedInUserInfo();
	Scholarship scho;
	private String answer;
	RadioGroup radiogrp;
	CustomCountDown countDownTimer;
	Button next;
	List<Question> list;
	LinearLayout  linear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questions);
		LinearLayout ll = (LinearLayout) findViewById(R.id.question_main);
		ll.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(state_of_drawer){
					linear.setVisibility(View.INVISIBLE);
					state_of_drawer = false;
				}
				return false;
			}
		});
		scho=(Scholarship) getIntent().getSerializableExtra("object_test");
		questiontext=(TextView)findViewById(R.id.txtquestion);
		checkbox1=(RadioButton)findViewById(R.id.checkbox1);
		checkbox2=(RadioButton)findViewById(R.id.checkbox2);
		checkbox3=(RadioButton)findViewById(R.id.checkbox3);
		checkbox4=(RadioButton)findViewById(R.id.checkbox4);
		radiogrp=(RadioGroup)findViewById(R.id.radiogrp);
		next=(Button)findViewById(R.id.btn_next);
		next.setOnClickListener(nextListener);
		slide_me = new SimpleSideDrawer(this);
		showCustomActionBar();
		QuestionApi.getQuestionList(QuestionActivity.this, null, prepareJson());
	}
	private JSONObject prepareJson() {
		// TODO Auto-generated method stub
	JSONObject json=new JSONObject();
	try {
		json.put("classNumId", Long.valueOf(userdata.getClassType()));
		JSONArray array=new JSONArray();
		for(int j=0;j<scho.getScholarshipSubject().length;j++){
			array.put(Long.valueOf(j));
		}
		json.put("subjectIds", array);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return json;
	}
	private void showCustomActionBar() {
		// TODO Auto-generated method stub
	// TODO Auto-generated method stub
	final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater()
			.inflate(R.layout.custom_actionbar, null);

	// Set up your ActionBar
	final ActionBar actionBar = getActionBar();
	actionBar.setDisplayShowHomeEnabled(false);
	actionBar.setDisplayShowTitleEnabled(false);
	actionBar.setDisplayShowCustomEnabled(true);
	actionBar.setCustomView(actionBarLayout);
	slide_me.setRightBehindContentView(R.layout.right_menu);
	linear=(LinearLayout)findViewById(R.id.right_menu_header);
	
	
	RelativeLayout home=(RelativeLayout)findViewById(R.id.home_button);
	home.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,MyTestActivity.class);
			startActivity(intent);
			finish();
		}
	});
	RelativeLayout action_drawer=(RelativeLayout)findViewById(R.id.action_drawer);
	action_drawer.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			linear.setVisibility(View.VISIBLE);
			slide_me.toggleRightDrawer();
			state_of_drawer = true;
		}
	});
	LinearLayout profile_view=(LinearLayout)findViewById(R.id.profile_view);
	profile_view.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,ProfileActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
	refer_friend.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,RefferFriendActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout contact_us=(LinearLayout)findViewById(R.id.contact_us_view);
	contact_us.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,ContactUsActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
	myTest.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,MyTestActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
	scoreboard.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,ScoreBoardActivity.class);
			startActivity(intent);
		}
	});
	LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
	start_test.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,StartTestActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout log_out=(LinearLayout)findViewById(R.id.log_out_view);
	log_out.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			SharedPreferencesHelper.setLoggedUserInfo(null);
			Intent intent=new Intent(QuestionActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
	buy_test_layout.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			linear.setVisibility(View.GONE);
			slide_me.closeRightSide();
			Intent intent=new Intent(QuestionActivity.this,ScholarshipActivity.class);
			startActivity(intent);
			finish();
		}
	});
	}
	@Override
	public void getQuestionsList(String response) {
		// TODO Auto-generated method stub
		try{
	        Type listType = new TypeToken<ArrayList<Question>>(){}.getType();
            list = new GsonBuilder().create().fromJson(response, listType);
            System.out.println(list);
            questionDisplay();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	View.OnClickListener nextListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			try{
				radiogrp.refreshDrawableState();
			if(Constants.isNextQuestion<=list.size()-1){
				int selectedId = radiogrp.getCheckedRadioButtonId();
				if(selectedId > -1){
					RadioButton radioButton = (RadioButton) findViewById(selectedId);
					answer = String.valueOf(radioButton.getTag().toString());
					//answer = radioButton.getText().toString();
					JSONObject jObj = new JSONObject();
					try {
						jObj.put("userId", userdata.getId());
						jObj.put("scholarshipId",scho.getId());
						jObj.put("questionId",question.getId());
						jObj.put("userSelectOption", answer);
					if(answer.equalsIgnoreCase(question.getAnswer())){
						jObj.put("optedStatus", true);
					}else{
						jObj.put("optedStatus", false);	
					}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					QuestionApi.submitAnswerForQuestion(QuestionActivity.this, null, jObj);
				}else{
					Toast.makeText(QuestionActivity.this, "Please select some option", Toast.LENGTH_SHORT).show();
				}
			}else{
				// questions are over
			}
				
			}catch(Exception e){
				e.printStackTrace();
				Toast.makeText(QuestionActivity.this, "Please select some option", Toast.LENGTH_SHORT).show();
			}
			

		}
	};
	@Override
	public void submitAnswer(String response) {
		// TODO Auto-generated method stub
		try {
			if(response!=null){
				
				if(Constants.isNextQuestion==list.size()-1){
					Constants.isNextQuestion = 0; //reset the counter
					QuestionApi.getEndResult(QuestionActivity.this, null, scho.getId(), userdata.getId());
				}else{
					Constants.isNextQuestion++;
					questionDisplay();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void questionDisplay(){
		 	question = null;
  			if (list.size()> Constants.isNextQuestion ) {
  				question = list.get(Constants.isNextQuestion);
  			}
  			if (null != question) {

  				questiontext.setText("Q." + (Constants.isNextQuestion + 1)
  							+ " " + question.getQuestion());
  				String arr[] = {question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4()};
  				RandomNumbers randomNumbers = new RandomNumbers();
  					int i = RandomNumbers.generateRandomNumber(arr.length,randomNumbers);
  					checkbox1.setText(arr[i]);
  					checkbox1.setTag(i+1);
  					i = RandomNumbers.generateRandomNumber(arr.length,randomNumbers);
  					checkbox2.setText(arr[i]);
  					checkbox2.setTag(i+1);
  					i = RandomNumbers.generateRandomNumber(arr.length,randomNumbers);
  					checkbox3.setText(arr[i]);
  					checkbox3.setTag(i+1);
  					i = RandomNumbers.generateRandomNumber(arr.length,randomNumbers);
  					checkbox4.setText(arr[i]);
  					checkbox4.setTag(i+1);
  					randomNumbers =null;
  				}
  			
  		
	}
	public void onRadioButtonClicked (View view){
		
	}
	@Override
	public void getResult(String response) {
		// TODO Auto-generated method stub
		try {
			if(response!=null){
				Type type = new TypeToken<UserScholarshipResult>(){}.getType();
		        UserScholarshipResult userdetail= new GsonBuilder().create().fromJson(response, type);
		        Intent intent =new Intent(QuestionActivity.this,ResultActivity.class);
		        intent.putExtra("result_response", userdetail);
		        intent.putExtra("userid", userdata.getId());
		        intent.putExtra("scholarId", scho.getId());
		        startActivity(intent);
		        finish();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void getResultForScore(String response) {
		// TODO Auto-generated method stub
		
	}
}
