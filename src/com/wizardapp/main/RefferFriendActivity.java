package com.wizardapp.main;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.wizardapp.R;
import com.navdrawer.SimpleSideDrawer;
import com.wizardapp.apis.MailApi;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.MailServices;
import com.wizardapp.utils.SharedPreferencesHelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class RefferFriendActivity extends MyBaseActivity implements MailServices{
	SimpleSideDrawer slide_me;
	EditText enterMailId;
	Button sendMail;
	UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reffer_friend);
		slide_me = new SimpleSideDrawer(this);
		slide_me.setRightBehindContentView(R.layout.right_menu);
		enterMailId = (EditText) findViewById(R.id.enter_email_id);
		sendMail = (Button) findViewById(R.id.email_send);
		sendMail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editable text = enterMailId.getText();
				if(null != text){
					try{
					JSONObject jObj = new JSONObject();
					JSONArray jArr = new JSONArray();
					jArr.put( text.toString().trim());
					jObj.put("to", jArr);
					jObj.put("from", userData.getEmail());
					jObj.put("subject", "Invitation To Join WIzkid");
					jObj.put("message", "http://goto.google.play.store/");
					MailApi.referFriend(RefferFriendActivity.this, null, jObj);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
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
				Intent intent=new Intent(RefferFriendActivity.this,ProfileActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout refer_friend=(LinearLayout)findViewById(R.id.reffer_friend_view);
		refer_friend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				slide_me.closeRightSide();
			}
		});
		LinearLayout contact_us=(LinearLayout)findViewById(R.id.contact_us_view);
		contact_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RefferFriendActivity.this,ContactUsActivity.class);
				startActivity(intent);
			}
		});
		LinearLayout myTest=(LinearLayout)findViewById(R.id.mytest_view);
		myTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RefferFriendActivity.this,MyTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout scoreboard=(LinearLayout)findViewById(R.id.scoreboard_view);
		scoreboard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RefferFriendActivity.this,ScoreBoardActivity.class);
				startActivity(intent);
				finish();
			}
			
		});
		LinearLayout start_test=(LinearLayout)findViewById(R.id.start_view);
		start_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RefferFriendActivity.this,StartTestActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout log_out=(LinearLayout)findViewById(R.id.log_out_view);
		log_out.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPreferencesHelper.setLoggedUserInfo(null);
				Intent intent=new Intent(RefferFriendActivity.this,LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		LinearLayout buy_test_layout=(LinearLayout)findViewById(R.id.buy_test_layout);
		buy_test_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(RefferFriendActivity.this,ScholarshipActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	@Override
	public void referToFriend(String response) {
		try{
			if(null == response){
				Toast toast=Toast.makeText(RefferFriendActivity.this, "Request sent successfully", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
				Intent intent=new Intent(RefferFriendActivity.this,MyTestActivity.class);
				startActivity(intent);
				RefferFriendActivity.this.finish();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	@Override
	public void forgotPassword(String response) {
		// TODO Auto-generated method stub
		
	}
}
