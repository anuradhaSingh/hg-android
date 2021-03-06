package com.wizardapp.main;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.wizardapp.R;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.adapter.AvailableTestAdapter;
import com.wizardapp.apis.TestApi;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.SharedPreferencesHelper;

public class StartTestActivity extends MyBaseActivity implements TestService{
	SimpleSideDrawer slide_me;
	Button buyTest;
	ListView available_list;
	LinearLayout linear;
	boolean state_of_drawer;
	UserDetail user=SharedPreferencesHelper.getLoggedInUserInfo();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_test);
		LinearLayout ll = (LinearLayout) findViewById(R.id.start_test_main);
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
		TestApi.getAvailableList(StartTestActivity.this, null, user.getId());
		available_list=(ListView)findViewById(R.id.start_list);
		slide_me = new SimpleSideDrawer(this);
		showCustomActionBar();
	    buyTest=(Button)findViewById(R.id.test_buy);
		buyTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartTestActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
		RelativeLayout backlayout=(RelativeLayout)findViewById(R.id.back_layout);
		backlayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartTestActivity.this,MyTestActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,MyTestActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,ProfileActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,RefferFriendActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,ContactUsActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,MyTestActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,ScoreBoardActivity.class);
				startActivity(intent);
				finish();
			}
			
		});
		LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
				Intent intent=new Intent(StartTestActivity.this,StartTestActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,LoginActivity.class);
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
				Intent intent=new Intent(StartTestActivity.this,ScholarshipActivity.class);
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
            if(list.size()>0){
            	buyTest.setVisibility(View.GONE);
                available_list.setAdapter(new AvailableTestAdapter(StartTestActivity.this, list));
            }else{
            	buyTest.setVisibility(View.VISIBLE);
            }
            System.out.println(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void getTakenList(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateTestStatus(String response) {
		// TODO Auto-generated method stub
		
	}
}
