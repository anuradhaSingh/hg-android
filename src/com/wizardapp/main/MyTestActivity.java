package com.wizardapp.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.AvailableTestAdapter;
import com.wizardapp.adapter.TakenTestAdapter;
import com.wizardapp.apis.TestApi;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.SharedPreferencesHelper;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MyTestActivity extends MyBaseActivity implements TestService{
	Button available,taken;
	LinearLayout available_view,taken_view;
	SimpleSideDrawer slide_me;
	RelativeLayout backlayout;
	ListView available_list,taken_list;
	UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_test);
		available_list=(ListView)findViewById(R.id.available_list);
		taken_list=(ListView)findViewById(R.id.taken_list);
		backlayout=(RelativeLayout)findViewById(R.id.backlayout);
		backlayout.setVisibility(View.GONE);
		
		slide_me = new SimpleSideDrawer(this);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		showCustomActionBar();
		available=(Button)findViewById(R.id.available_test);
		available_view=(LinearLayout)findViewById(R.id.available_layout);
		taken_view=(LinearLayout)findViewById(R.id.taken_layout);
		taken=(Button)findViewById(R.id.taken_test);
		TestApi.getAvailableList(MyTestActivity.this, null, userData.getId());
		available.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  if(v instanceof Button){
		            
				((Button)v).setTextColor(Color.parseColor("#ffffff"));
				  }else{
					  ((Button)v).setTextColor(Color.parseColor("#FE642E"));
				  }
				  taken.setTextColor(Color.parseColor("#FE642E"));
				  available.setBackgroundColor(Color.parseColor("#FE642E"));
				taken.setBackgroundColor(Color.parseColor("#ffffff"));
				available_view.setVisibility(View.VISIBLE);
				taken_view.setVisibility(View.GONE);
				TestApi.getAvailableList(MyTestActivity.this, null, userData.getId());
			}
		});
		taken.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v instanceof Button){
		           ((Button)v).setTextColor(Color.parseColor("#ffffff"));
					  }else{
						  ((Button)v).setTextColor(Color.parseColor("#FE642E"));
					  }
				available.setTextColor(Color.parseColor("#FE642E"));
				available.setBackgroundColor(Color.parseColor("#ffffff"));
				taken.setBackgroundColor(Color.parseColor("#FE642E"));
				available_view.setVisibility(View.GONE);
				taken_view.setVisibility(View.VISIBLE);
				TestApi.getTakenList(MyTestActivity.this, null, userData.getId());
			}
		});
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
	RelativeLayout action_drawer=(RelativeLayout)findViewById(R.id.action_drawer);
	action_drawer.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			slide_me.toggleRightDrawer();
		}
	});
	LinearLayout profile_view=(LinearLayout)findViewById(R.id.profile_view);
	profile_view.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyTestActivity.this,ProfileActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
	refer_friend.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyTestActivity.this,RefferFriendActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout contact_us=(LinearLayout)findViewById(R.id.contact_us_view);
	contact_us.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyTestActivity.this,ContactUsActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
	myTest.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyTestActivity.this,MyTestActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
	scoreboard.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyTestActivity.this,ScoreBoardActivity.class);
			startActivity(intent);
		}
	});
	LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
	start_test.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(MyTestActivity.this,StartTestActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout log_out=(LinearLayout)findViewById(R.id.log_out_view);
	log_out.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			SharedPreferencesHelper.setLoggedUserInfo(null);
			Intent intent=new Intent(MyTestActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
		}
	});
	LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
	buy_test_layout.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(MyTestActivity.this,ScholarshipActivity.class);
			startActivity(intent);
			finish();
		}
	});
	
	}
	@Override
	public void getAvailableList(String response) {
		// TODO Auto-generated method stub
		try{
	        Type listType = new TypeToken<ArrayList<Scholarship>>(){}.getType();
            List<Scholarship> list = new GsonBuilder().create().fromJson(response, listType);
            available_list.setAdapter(new AvailableTestAdapter(MyTestActivity.this, list));
            System.out.println(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void getTakenList(String response) {
		// TODO Auto-generated method stub
		try{
	        Type listType = new TypeToken<ArrayList<Scholarship>>(){}.getType();
            List<Scholarship> list = new GsonBuilder().create().fromJson(response, listType);
            taken_list.setAdapter(new TakenTestAdapter(MyTestActivity.this, list));
            System.out.println(list);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void updateTestStatus(String response) {
		// TODO Auto-generated method stub
		
	}
	
}
