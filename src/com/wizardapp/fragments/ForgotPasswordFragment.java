package com.wizardapp.fragments;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wizardapp.R;
import com.wizardapp.apis.MailApi;
import com.wizardapp.services.MailServices;

public class ForgotPasswordFragment extends MyBaseFragment implements MailServices {
	
	private static Activity activitycontext; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    private EditText emailText;
    private Button sendButton;
    public ForgotPasswordFragment(int layout) 
   	{
   	  layout_to_inflate = layout;
   	}
   	
   	public ForgotPasswordFragment() 
   	{
   	}
   	
   	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
   	{   
   		activitycontext = getActivity();
   		final LinearLayout ll = (LinearLayout) inflater.inflate(layout_to_inflate, container, false);
   		LinearLayout forgot_header=(LinearLayout)ll.findViewById(R.id.forgot_header);
   		forgot_header.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createNewFragment(new LoginFragment(R.layout.login));
			}
		});
   		emailText = (EditText) ll.findViewById(R.id.emailtext);
   		sendButton = (Button) ll.findViewById(R.id.send_btn);
   		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Editable email = emailText.getText();
			if(null != email){
				try{
					JSONObject jObj = new JSONObject();
					jObj.put("email", email.toString());
					MailApi.forgotPassword(activitycontext, ForgotPasswordFragment.this, jObj);
				}catch(Exception e){
				    e.printStackTrace();
				}
			}
				
			}
		});
   		
		return ll;
   	}

	@Override
	public void referToFriend(String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forgotPassword(String response) {
		try{
			if(null != response){
				Toast.makeText(activitycontext, "Please check your inbox for password ", Toast.LENGTH_SHORT).show();
				createNewFragment(new LoginFragment(R.layout.login));
			}else{
				Toast.makeText(activitycontext, "you have registered from this Email ", Toast.LENGTH_SHORT).show();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void contactUs(String response) {
		// TODO Auto-generated method stub
		
	}

}
