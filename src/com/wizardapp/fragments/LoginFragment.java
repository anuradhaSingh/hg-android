package com.wizardapp.fragments;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.google.android.gcm.GCMRegistrar;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wizardapp.apis.UserApi;
import com.wizardapp.gcm.WakeLocker;
import com.wizardapp.main.MyTestActivity;
import com.wizardapp.model.UserDetail;
import com.wizardapp.services.UserServices;
import com.wizardapp.utils.Constants;
import com.wizardapp.utils.SharedPreferencesHelper;


public class LoginFragment extends MyBaseFragment  implements UserServices {
	private static Activity activity; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    EditText login_id,password;
    Button login,register;
    String registrationId = null;
    public LoginFragment(int layout) 
	{
	  layout_to_inflate = layout;
	}
	
	public LoginFragment() 
	{
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		activity = super.getActivity();
		RelativeLayout ll = (RelativeLayout) inflater.inflate(layout_to_inflate, container, false);
		activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    login_id=(EditText)ll.findViewById(R.id.login_id);
	    password=(EditText)ll.findViewById(R.id.password);
	    login=(Button)ll.findViewById(R.id.login_btn);
	    TextView forgotpassword=(TextView)ll.findViewById(R.id.forget_password);
	    forgotpassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createNewFragment(new ForgotPasswordFragment(R.layout.forgetpassword_layout));
			}
		});
	    register=(Button)ll.findViewById(R.id.registration_btn);
	    login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONObject requestObj = new JSONObject();
				try {
					registrationId = Constants.getRegistrationId(activity, mHandleMessageReceiver);
					requestObj.put("email", login_id.getText().toString());
					requestObj.put("password", password.getText().toString());
					requestObj.put("deviceId", Constants.getDeviceID(activity) );
					requestObj.put("device", "ANDROID");
					requestObj.put("registrationId", registrationId);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				UserApi.getLoggenInUser(activity, LoginFragment.this, requestObj);
			}
		});
	    register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//createNewFragment(new MobileVerificationFragment(R.layout.mobile_verification,true));
				createNewFragment(new RegisterFragment(R.layout.registeration_layout));
			}
		});
	    return ll;
	}

@Override
	public void userLoggingIn(String userResponse) {
	try{
		if(null != userResponse){
			   Type type = new TypeToken<UserDetail>(){}.getType();
	           UserDetail userdetail= new GsonBuilder().create().fromJson(userResponse, type);
	           SharedPreferencesHelper.setLoggedUserInfo(userdetail);
	           if(userdetail.isUserVerified()){
	        	   Intent intent=new Intent(activity,MyTestActivity.class);
	        	   startActivity(intent);
	        	   activity.finish();
	           }
	           else
	        	   createNewFragment(new MobileVerificationFragment(R.layout.mobile_verification,true));
		}
	}catch(Exception e){
		e.printStackTrace();
		
	}
	}

		@Override
	public void registerUser(String afterRegisteration) {
		// TODO Auto-generated method stub
		
	}

		@Override
		public void verifyOTP(String response) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void isUserExist(String response) {
			// TODO Auto-generated method stub
			
		}
	
		/**
		* Receiving push messages
		* */
		private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
		    String newMessage = intent.getExtras().getString(Constants.EXTRA_MESSAGE);
		    // Waking up mobile if it is sleeping
		    WakeLocker.acquire(activity);
		     
		    /**
		     * Take appropriate action on this message
		     * depending upon your app requirement
		     * For now i am just displaying it on the screen
		     * */
		     
		    // Showing received message
		    Toast.makeText(activity, "New Message: " + newMessage, Toast.LENGTH_LONG).show();
		     
		    // Releasing wake lock
		    WakeLocker.release();
		}
		};

		@Override
		public void onDestroy() {
		/*if (mRegisterTask != null) {
		    mRegisterTask.cancel(true);
		}*/
		try {
		    activity.unregisterReceiver(mHandleMessageReceiver);
		    GCMRegistrar.onDestroy(activity);
		} catch (Exception e) {
		    Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
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