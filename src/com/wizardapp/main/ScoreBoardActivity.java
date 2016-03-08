package com.wizardapp.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.ScholarshipTestAdapter;
import com.wizardapp.adapter.ScoreBoardAdapter;
import com.wizardapp.adapter.TakenTestAdapter;
import com.wizardapp.apis.TestApi;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.SharedPreferencesHelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ScoreBoardActivity extends MyBaseActivity implements TestService{
	ListView listview;
	UserDetail user=SharedPreferencesHelper.getLoggedInUserInfo();
	 SimpleSideDrawer slide_me;
	 Button buytest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoreboard);
		TestApi.getTakenList(ScoreBoardActivity.this, null, user.getId());
		buytest=(Button)findViewById(R.id.test_buy);
		buytest.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(ScoreBoardActivity.this,ScholarshipActivity.class);
					startActivity(intent);
					finish();
				}
			});
		RelativeLayout backlayout=(RelativeLayout)findViewById(R.id.back_layout);
		backlayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScoreBoardActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		slide_me = new SimpleSideDrawer(this);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		showCustomActionBar();
		
		listview=(ListView)findViewById(R.id.scoreboard_listview);
		
				
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
		RelativeLayout home=(RelativeLayout)findViewById(R.id.home_button);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent=new Intent(ScoreBoardActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
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
				slide_me.closeRightSide();
				Intent intent=new Intent(ScoreBoardActivity.this,ProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
		refer_friend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent=new Intent(ScoreBoardActivity.this,RefferFriendActivity.class);
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
				Intent intent=new Intent(ScoreBoardActivity.this,ContactUsActivity.class);
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
				Intent intent=new Intent(ScoreBoardActivity.this,MyTestActivity.class);
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
				Intent intent=new Intent(ScoreBoardActivity.this,ScoreBoardActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
				Intent intent=new Intent(ScoreBoardActivity.this,StartTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout log_out=(LinearLayout)findViewById(R.id.log_out_view);
		log_out.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				SharedPreferencesHelper.setLoggedUserInfo(null);
				Intent intent=new Intent(ScoreBoardActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
		buy_test_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slide_me.closeRightSide();
				Intent intent=new Intent(ScoreBoardActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public void getAvailableList(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getTakenList(String response) {
		// TODO Auto-generated method stub
		try{
	        Type listType = new TypeToken<ArrayList<Scholarship>>(){}.getType();
            List<Scholarship> list = new GsonBuilder().create().fromJson(response, listType);
            if(list.size()>0){
            	buytest.setVisibility(View.GONE);
            listview.setAdapter(new TakenTestAdapter(ScoreBoardActivity.this, list));
            }else{
            	buytest.setVisibility(View.VISIBLE);
            }
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
