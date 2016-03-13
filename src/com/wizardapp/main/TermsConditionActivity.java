package com.wizardapp.main;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.apis.TestApi;
import com.wizardapp.model.Scholarship;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.TestService;
import com.wizardapp.utils.SharedPreferencesHelper;

public class TermsConditionActivity extends MyBaseActivity implements TestService{
	SimpleSideDrawer slide_me;
	Scholarship scho;
	UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
	LinearLayout linear;
	boolean state_of_drawer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.terms_condition);
		LinearLayout ll = (LinearLayout) findViewById(R.id.terms_main);
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
		RelativeLayout back_layout=(RelativeLayout)findViewById(R.id.backlayout);
        back_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TermsConditionActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});	
		scho=(Scholarship) getIntent().getSerializableExtra("object_test");
		slide_me = new SimpleSideDrawer(this);
		showCustomActionBar();
		final CheckBox chk=(CheckBox)findViewById(R.id.checkbox);
		Button startTest=(Button)findViewById(R.id.btn_start);
		startTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(chk.isChecked()==false){
					Toast toast=Toast.makeText(TermsConditionActivity.this, "Please accept terms and conditions!", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
					
				}else{
					TestApi.updateScholarshipStatus(TermsConditionActivity.this, null, scho.getId(), userData.getId(),true);
				}
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
	slide_me.setRightBehindContentView(R.layout.right_menu);
	linear=(LinearLayout)findViewById(R.id.right_menu_header);
	
	
	RelativeLayout home=(RelativeLayout)findViewById(R.id.home_button);
	home.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			slide_me.closeRightSide();
			Intent intent=new Intent(TermsConditionActivity.this,MyTestActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,ProfileActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,RefferFriendActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,ContactUsActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,MyTestActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,ScoreBoardActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,StartTestActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,LoginActivity.class);
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
			Intent intent=new Intent(TermsConditionActivity.this,ScholarshipActivity.class);
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
		
	}

	@Override
	public void updateTestStatus(String response) {
		try{
			if(null != response){
				Intent intent=new Intent(TermsConditionActivity.this,QuestionActivity.class);
				intent.putExtra("object_test", scho);
				startActivity(intent);
				finish();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
