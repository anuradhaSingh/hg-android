package com.wizardapp.main;

import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.apis.UserApi;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.MailServices;
import com.wizardapp.utils.SharedPreferencesHelper;

public class ContactUsActivity extends MyBaseActivity implements MailServices{
	
	SimpleSideDrawer slide_me;
	RelativeLayout back_layout;
	private UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
	LinearLayout linear;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_us);
		TextView emailid=(TextView)findViewById(R.id.email_text);
		final EditText query=(EditText)findViewById(R.id.query);
		Button sendEmail=(Button)findViewById(R.id.send_contact);
		sendEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editable queryE = query.getText();
				if(null != queryE){
					try{
						JSONObject jObj = new JSONObject();
						jObj.put("userEmail", userData.getEmail());
						jObj.put("userId", userData.getId());
						jObj.put("name", userData.getFirstName());
						jObj.put("query", queryE.toString());
					UserApi.contactUs(ContactUsActivity.this, null, jObj);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
			}
		});
		
		back_layout=(RelativeLayout)findViewById(R.id.backlayout);
		back_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent=new Intent(ContactUsActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		slide_me = new SimpleSideDrawer(this);
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
		slide_me.setRightBehindContentView(R.layout.right_menu);
		linear=(LinearLayout)findViewById(R.id.right_menu_header);
		
		
		RelativeLayout home=(RelativeLayout)findViewById(R.id.home_button);
		home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
				Intent intent=new Intent(ContactUsActivity.this,MyTestActivity.class);
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
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
				Intent intent=new Intent(ContactUsActivity.this,ProfileActivity.class);
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
				Intent intent=new Intent(ContactUsActivity.this,RefferFriendActivity.class);
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
			}
		});
		LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
		myTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
				Intent intent=new Intent(ContactUsActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
		scoreboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				linear.setVisibility(View.GONE);
				slide_me.closeRightSide();
				Intent intent=new Intent(ContactUsActivity.this,ScoreBoardActivity.class);
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
				Intent intent=new Intent(ContactUsActivity.this,StartTestActivity.class);
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
				Intent intent=new Intent(ContactUsActivity.this,LoginActivity.class);
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
				Intent intent=new Intent(ContactUsActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public void referToFriend(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void forgotPassword(String response) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void contactUs(String response) {
		try{
			if(null == response){
				Toast.makeText(ContactUsActivity.this, "We will contact you soon.", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(ContactUsActivity.this,MyTestActivity.class);
				startActivity(intent);
				ContactUsActivity.this.finish();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
