package com.wizardapp.main;

import java.lang.reflect.Type;

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

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.ScholarshipTestAdapter;
import com.wizardapp.apis.ScholarshipApi;
import com.wizardapp.fragments.MobileVerificationFragment;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.ScholarshipPrimaryServices;
import com.wizardapp.utils.SharedPreferencesHelper;

public class ScholarshipDetailActivity extends MyBaseActivity implements ScholarshipPrimaryServices{
	SimpleSideDrawer slide_me;
	LinearLayout linear;
	boolean state_of_drawer;
	long scholarid;
	TextView name,mobile,prize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scholarship_detail);
		Intent intent=getIntent();
		scholarid=intent.getLongExtra("schoid", 0);
		name=(TextView)findViewById(R.id.scholar_name);
		mobile=(TextView)findViewById(R.id.scholar_mobile);
		prize=(TextView)findViewById(R.id.scholar_prize);
		
		ScholarshipApi.ScholarshipDetail(ScholarshipDetailActivity.this, null, scholarid);
		LinearLayout ll = (LinearLayout) findViewById(R.id.sch_det_main);
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
		slide_me = new SimpleSideDrawer(this);
		showCustomActionBar();
		RelativeLayout scholarshipDetail=(RelativeLayout)findViewById(R.id.back_layout);
		scholarshipDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScholarshipDetailActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,MyTestActivity.class);
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
				state_of_drawer =true;
			}
		});
		LinearLayout profile_view=(LinearLayout)findViewById(R.id.profile_view);
		profile_view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
				Intent intent=new Intent(ScholarshipDetailActivity.this,ProfileActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,RefferFriendActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,ContactUsActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,MyTestActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,ScoreBoardActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,StartTestActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,LoginActivity.class);
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
				Intent intent=new Intent(ScholarshipDetailActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public void getAllByClassNumber(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getDetailById(String response) {
		// TODO Auto-generated method stub
		
		
		try{
			if(null != response){
				   Type type = new TypeToken<UserDetail>(){}.getType();
		           Scholarship userdetail= new GsonBuilder().create().fromJson(response, type);
		           name.setText(""+userdetail.getScholarshipName());
		           mobile.setText(""+userdetail.getScholarshipStartDate());
		           prize.setText(" Rs. "+userdetail.getPrizeMoney());
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	@Override
	public void buyScholarship(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tobuyTestList(String response) {
		// TODO Auto-generated method stub
		
	}
}
