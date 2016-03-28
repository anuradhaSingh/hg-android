package com.wizardapp.main;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.model.UserDetail;
import com.wizardapp.utils.SharedPreferencesHelper;

public class ProfileActivity extends MyBaseActivity{
	SimpleSideDrawer slide_me;
	UserDetail userdata=SharedPreferencesHelper.getLoggedInUserInfo();
	TextView firstName,lastName,dob,email_id,mobile,state,city,country,address,pincode,gender;
	 LinearLayout  linear; ToggleButton genderB ;
	 boolean state_of_drawer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);
		slide_me = new SimpleSideDrawer(this);
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.profile_main);
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
		
		showCustomActionBar();
		RelativeLayout back_button=(RelativeLayout)findViewById(R.id.back_button);
		back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ProfileActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		firstName=(TextView)findViewById(R.id.txtview_firstname);
		lastName=(TextView)findViewById(R.id.txtview_secondname);
		dob=(TextView)findViewById(R.id.txtview_dateofbirth);
		email_id=(TextView)findViewById(R.id.txt_email);
		mobile=(TextView)findViewById(R.id.txt_mobileno);
		state=(TextView)findViewById(R.id.txt_state);
		city=(TextView)findViewById(R.id.txt_city);
		address=(TextView)findViewById(R.id.txt_address);
		pincode=(TextView)findViewById(R.id.txt_pincode);
		gender=(TextView)findViewById(R.id.txtview_gender);
		genderB = (ToggleButton) findViewById(R.id.radiobtn);
		
		firstName.setText(userdata.getFirstName());
		lastName.setText(userdata.getLastName());
		email_id.setText(userdata.getEmail());
		mobile.setText(userdata.getMobile());
		state.setText(userdata.getState());
		city.setText(userdata.getCity());
		address.setText(userdata.getStreetAddress());
		pincode.setText(userdata.getZipCode());
		dob.setText(userdata.getDateOfBirth());
		gender.setText(userdata.getGender());
		if("Male".equalsIgnoreCase(userdata.getGender())){
			genderB.setChecked(true);
		}
	}
	private void showCustomActionBar() {
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
				Intent intent=new Intent(ProfileActivity.this,MyTestActivity.class);
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
			}
		});
		LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
		refer_friend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,RefferFriendActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout contact_us=(LinearLayout)findViewById(R.id.contact_us_view);
		contact_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,ContactUsActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
		myTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
		scoreboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,ScoreBoardActivity.class);
				startActivity(intent);
				finish();
			}
			
		});
		LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,StartTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout log_out=(LinearLayout)findViewById(R.id.log_out_view);
		log_out.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				SharedPreferencesHelper.setLoggedUserInfo(null);
				Intent intent=new Intent(ProfileActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
		buy_test_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				linear.setVisibility(View.GONE);
				Intent intent=new Intent(ProfileActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
