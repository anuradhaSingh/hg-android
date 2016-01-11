package com.wizardapp.fragments;

import com.example.wizardapp.R;
import com.wizardapp.main.ScholarshipActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MobileVerificationFragment extends MyBaseFragment {
	
	private static Activity activitycontext; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    Button mobile_verified;
    LinearLayout back_button;
    EditText otp;
    public MobileVerificationFragment(int layout) 
   	{
   	  layout_to_inflate = layout;
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
				activitycontext.finish();
			}
		});
   		mobile_verified.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(activitycontext,ScholarshipActivity.class);
				startActivity(intent);
			}
		});
		return ll;
   	}

}
