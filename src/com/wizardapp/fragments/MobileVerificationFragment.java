package com.wizardapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.wizardapp.apis.UserApi;
import com.wizardapp.main.MyTestActivity;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.SharedPreferencesHelper;

public class MobileVerificationFragment extends MyBaseFragment implements UserServices {
	
	private static Activity activitycontext; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    Button mobile_verified;
    LinearLayout back_button;
    EditText otp;
    boolean flag=false;
    UserDetail userdata=SharedPreferencesHelper.getLoggedInUserInfo();
    public MobileVerificationFragment(int layout,boolean from) 
   	{
   	  layout_to_inflate = layout;
   	  flag=from; 
   	}
   	
   	public MobileVerificationFragment() 
   	{
   	}
   	
   	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
   	{   
   		activitycontext = getActivity();
   		final LinearLayout ll = (LinearLayout) inflater.inflate(layout_to_inflate, container, false);
   		otp=(EditText)ll.findViewById(R.id.otptext);
   		mobile_verified=(Button)ll.findViewById(R.id.confirm_btn);
   		back_button=(LinearLayout)ll.findViewById(R.id.back_layout);
   		back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createNewFragment(new LoginFragment(R.layout.login));
				/*if(flag==true){
					createNewFragment(new LoginFragment(R.layout.login));
				}else{
					createNewFragment(new RegisterFragment(R.layout.registeration_layout));
				}*/
			}
		});
   		//otp.setText(userdata.getOneTimePassword());
   		mobile_verified.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editable otpE = otp.getText();
				if(null != otpE && !otpE.toString().isEmpty())
				  UserApi.verifyuser(activitycontext, MobileVerificationFragment.this, userdata.getEmail().trim(), otp.getText().toString());
				else
				  Toast.makeText(activitycontext, "Please enter the OTP provided in your registered Email.", Toast.LENGTH_SHORT).show();	
			}
		});
		return ll;
   	}

	@Override
	public void userLoggingIn(String userResponse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerUser(String afterRegisteration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyOTP(String response) {
		try{
			if(null != response && Boolean.valueOf(response)){
				UserDetail userData = SharedPreferencesHelper.getLoggedInUserInfo();
				userData.setUserVerified(Boolean.valueOf(response));
				SharedPreferencesHelper.setLoggedUserInfo(userData);
				Intent intent=new Intent(activitycontext,MyTestActivity.class);
				startActivity(intent);
				activitycontext.finish();
			}else{
				Toast toast=Toast.makeText(activitycontext, "Incorrect OTP.", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
				toast.show();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void isUserExist(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(String updateUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String result) {
		// TODO Auto-generated method stub
		
	}

}
