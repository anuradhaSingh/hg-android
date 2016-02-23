package com.wizardapp.main;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ScoreBoardDetailActivity extends MyBaseActivity{
	SimpleSideDrawer slide_me;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.scoreboard_detail);
	    slide_me = new SimpleSideDrawer(this);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		showCustomActionBar();
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
				Intent intent=new Intent(ScoreBoardDetailActivity.this,ProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
		refer_friend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScoreBoardDetailActivity.this,RefferFriendActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout contact_us=(LinearLayout)findViewById(R.id.contact_us_view);
		contact_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScoreBoardDetailActivity.this,ContactUsActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
		myTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScoreBoardDetailActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
		scoreboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScoreBoardDetailActivity.this,ScoreBoardActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScoreBoardDetailActivity.this,StartTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
}