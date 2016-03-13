package com.wizardapp.main;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.apis.TestApi;
import com.wizardapp.model.UserScholarshipResult;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.SharedPreferencesHelper;

public class ResultActivity extends MyBaseActivity{
	SimpleSideDrawer slide_me;
	UserScholarshipResult result;
	TextView total_score, wronganswer, correct_answer;
	long schoid, userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		RelativeLayout back_layout=(RelativeLayout)findViewById(R.id.backlayout);
		back_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(ResultActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		Intent intent = getIntent();
		schoid = intent.getLongExtra("scholarId", 0);
		userid = intent.getLongExtra("userid", 0);
		result = (UserScholarshipResult) getIntent().getSerializableExtra(
				"result_response");
		total_score = (TextView) findViewById(R.id.total_score);
		wronganswer = (TextView) findViewById(R.id.wrong_answer);
		correct_answer = (TextView) findViewById(R.id.correct_answer);
		correct_answer.setText("" + result.getCurrectQuestion());
		wronganswer.setText("" + result.getWrongQuestion());
		total_score.setText("" + (result.getCurrectQuestion() * 2));
		slide_me = new SimpleSideDrawer(this);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		showCustomActionBar();
		Button thankyou = (Button) findViewById(R.id.btn_thanks);
		thankyou.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ResultActivity.this,
						MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void showCustomActionBar() {
		final ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater()
				.inflate(R.layout.custom_actionbar, null);

		// Set up your ActionBar
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setCustomView(actionBarLayout);
		RelativeLayout home=(RelativeLayout)findViewById(R.id.home_button);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent=new Intent(ResultActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		RelativeLayout action_drawer = (RelativeLayout) findViewById(R.id.action_drawer);
		action_drawer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slide_me.toggleRightDrawer();
			}
		});
		LinearLayout profile_view = (LinearLayout) findViewById(R.id.profile_view);
		profile_view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				Intent intent = new Intent(ResultActivity.this,
						ProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout refer_friend = (LinearLayout) findViewById(R.id.reffer_friend_view);
		refer_friend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				Intent intent = new Intent(ResultActivity.this,
						RefferFriendActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout contact_us = (LinearLayout) findViewById(R.id.contact_us_view);
		contact_us.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				// TODO Auto-generated method stub
				Intent intent = new Intent(ResultActivity.this,
						ContactUsActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout myTest = (LinearLayout) findViewById(R.id.mytest_view);
		myTest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent = new Intent(ResultActivity.this,
						MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout scoreboard = (LinearLayout) findViewById(R.id.scoreboard_view);
		scoreboard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent = new Intent(ResultActivity.this,
						ScoreBoardActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout start_test = (LinearLayout) findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent = new Intent(ResultActivity.this,
						StartTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout log_out = (LinearLayout) findViewById(R.id.log_out_view);
		log_out.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				SharedPreferencesHelper.setLoggedUserInfo(null);
				Intent intent = new Intent(ResultActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
		buy_test_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				Intent intent=new Intent(ResultActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
