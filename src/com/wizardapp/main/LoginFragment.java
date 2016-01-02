package com.wizardapp.main;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.wizardapp.R;
import com.indiahomes.main.PropertyListSellFragment;
import com.wizardapp.apis.UserApi;
import com.wizardapp.services.UserServices;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


public class LoginFragment extends MyBaseFragment  implements UserServices{
	private static Activity activity; // activity/context you can use to call any explicit activities like email,sms,etc...
    private int layout_to_inflate; // layout which you want to show
    private Bundle bundle; // Arguments which you want to pass to fragment
    EditText login_id,password;
    Button login,register;
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
	    LinearLayout ll = (LinearLayout) inflater.inflate(layout_to_inflate, container, false);
	    login_id=(EditText)ll.findViewById(R.id.login_id);
	    password=(EditText)ll.findViewById(R.id.password);
	    login=(Button)ll.findViewById(R.id.login_btn);
	    register=(Button)ll.findViewById(R.id.registration_btn);
	    login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JSONObject requestObj = new JSONObject();
				try {
					requestObj.put("email", login_id.getText().toString());
					requestObj.put("password", password.getText().toString());
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
				createNewFragment(new RegisterFragment(R.layout.registeration_layout));
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

}